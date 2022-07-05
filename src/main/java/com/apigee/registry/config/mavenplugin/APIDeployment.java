/**
 * Copyright 2022 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apigee.registry.config.mavenplugin;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.apigee.registry.config.model.APIConfig;
import com.apigee.registry.config.model.Data;
import com.apigee.registry.config.model.data.Deployment;
import com.apigee.registry.config.utils.ApigeeRegistryClient;
import com.apigee.registry.config.utils.BuildProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Goal to configure API Deployment in Apigee Registry
 *
 * @author ssvaidyanathan
 * @goal apideployment
 * @phase install
 */

public class APIDeployment extends APIRegistryAbstractMojo {
	static Logger logger = LogManager.getLogger(APIDeployment.class);

	public static final String ____ATTENTION_MARKER____ = "************************************************************************";

	enum OPTIONS {
		none, create, update, delete, sync
	}

	OPTIONS buildOption = OPTIONS.none;

	private BuildProfile serverProfile;

	/**
	 * Constructor.
	 */
	public APIDeployment() {
		super();
	}

	/**
	 * Initilization
	 * @throws MojoExecutionException
	 * @throws MojoFailureException
	 */
	public void init() throws MojoExecutionException, MojoFailureException {
		try {
			logger.info(____ATTENTION_MARKER____);
			logger.info("API Deployment");
			logger.info(____ATTENTION_MARKER____);

			String options = "";
			serverProfile = super.getProfile();

			options = super.getOptions();
			if (options != null) {
				buildOption = OPTIONS.valueOf(options);
			}
			if (buildOption == OPTIONS.none) {
				logger.info("Skipping API Deployment (default action)");
				return;
			}

			logger.debug("Build option " + buildOption.name());

			
			if (serverProfile.getProjectId() == null) {
				throw new MojoExecutionException("Apigee Registry Project ID is missing");
			}
			if (serverProfile.getLocation() == null) {
				throw new MojoExecutionException("Apigee Registry Location is missing");
			}
			if (serverProfile.getServiceAccountFilePath() == null && serverProfile.getBearer() == null) {
				throw new MojoExecutionException("Service Account file path or Bearer token is missing");
			}
			if (serverProfile.getConfigFile() == null) {
				throw new MojoExecutionException("API Confile File is missing");
			}
			 

		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid apigee.option provided");
		} catch (RuntimeException e) {
			throw e;
		} 

	}

	/**
	 * Entry point for the mojo.
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (super.isSkip()) {
			getLog().info("Skipping");
			return;
		}

		try {
			init();
			if (buildOption == OPTIONS.none) {
				return;
			}

			if (buildOption == OPTIONS.create) {
				doUpsert("Create");
			}

			if (buildOption == OPTIONS.update) {
				doUpsert("Update");
			}

			if (buildOption == OPTIONS.delete) {
				doDelete();
			}

			if (buildOption == OPTIONS.sync) {
				doDelete();
				doUpsert("Create");
			}

		} catch (MojoFailureException e) {
			throw e;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * Creates an API Deployment in the Registry
	 *
	 * 
	 * @throws MojoExecutionException
	 */
	public void doUpsert(String action) throws MojoExecutionException {
		try {
			APIConfig config = parseConfig(serverProfile.getConfigFile());
			if(config!=null) {
				if(config.getMetadata()!=null) {
					String apiId = config.getMetadata().getName();
					if(apiId != null && !apiId.equals("")) {
						if(config.getData()!=null) {
							Data data = config.getData();
							for (Deployment deployment : data.getDeployments()) {
								if(action.equalsIgnoreCase("Update"))
									ApigeeRegistryClient.updateAPIDeployment(serverProfile, apiId, deployment, action);
								else {
									if(!ApigeeRegistryClient.getAPIDeployment(serverProfile, apiId, deployment)) {
										ApigeeRegistryClient.createAPIDeployment(serverProfile, apiId, deployment, action);
									}else {
										logger.info(format("API Version: %s already exist", deployment.getMetadata().getName()));
									}
								}
							}
						}
					}
					else {
						throw new RuntimeException("ID in the config file is missing or empty");
					}
				}
			} 
			else {
				throw new RuntimeException("API config is missing");
			}
		}catch (Exception e) {
			throw new RuntimeException("Update failure: " + e.getMessage());
		}
	}

	/**
	 * Deletes API Deployment from Registry
	 * 
	 * @throws MojoExecutionException
	 */
	public void doDelete() throws MojoExecutionException {
		try {
			APIConfig config = parseConfig(serverProfile.getConfigFile());
			if(config!=null) {
				if(config.getMetadata()!=null) {
					String apiId = config.getMetadata().getName();
					if(apiId != null && !apiId.equals("")) {
						if(config.getData()!=null) {
							Data data = config.getData();
							for (Deployment deployment : data.getDeployments()) {
								ApigeeRegistryClient.deleteAPIDeployment(serverProfile, apiId, deployment);
							}
						}
					}
					else {
						throw new RuntimeException("ID in the config file is missing or empty");
					}
				}
			}
			else {
				throw new RuntimeException("API config is missing");
			}
		} catch (Exception e) {
			throw new RuntimeException("Delete failure: " + e.getMessage());
		}
	}

	/**
	 * Parses the api configurations
	 * @param configFile
	 * @return Metadata
	 * @throws IOException
	 */
	public APIConfig parseConfig(String configFile) throws IOException {
		APIConfig config = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
			config = objectMapper.readValue(new File(configFile), APIConfig.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}
}
