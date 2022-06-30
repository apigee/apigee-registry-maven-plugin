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

package com.apigee.registry.config.utils;

import static java.lang.String.format;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.apigee.registry.config.model.APIConfig;
import com.apigee.registry.config.model.Data;
import com.apigee.registry.config.model.data.Deployment;
import com.apigee.registry.config.model.data.Version;
import com.apigee.registry.config.model.data.version.Spec_;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.apigeeregistry.v1.Api;
import com.google.cloud.apigeeregistry.v1.ApiDeployment;
import com.google.cloud.apigeeregistry.v1.ApiName;
import com.google.cloud.apigeeregistry.v1.ApiSpec;
import com.google.cloud.apigeeregistry.v1.ApiVersion;
import com.google.cloud.apigeeregistry.v1.Artifact;
import com.google.cloud.apigeeregistry.v1.RegistryClient;
import com.google.cloud.apigeeregistry.v1.RegistrySettings;
import com.google.cloud.apigeeregistry.v1.UpdateApiDeploymentRequest;
import com.google.cloud.apigeeregistry.v1.UpdateApiRequest;
import com.google.cloud.apigeeregistry.v1.UpdateApiSpecRequest;
import com.google.cloud.apigeeregistry.v1.UpdateApiVersionRequest;
import com.google.protobuf.ByteString;


public class ApigeeRegistryClient {
	static Logger logger = LogManager.getLogger(ApigeeRegistryClient.class);
	
	/**
	 * Helper to update/create API
	 * 
	 * @param profile
	 * @param apiId
	 * @param apiConfig
	 * @throws IOException
	 */
	public static void updateAPI(BuildProfile profile, String apiId, APIConfig apiConfig, String action) throws Exception {
		try {
			GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			if(apiConfig!=null) {
				//API
				logger.info(format("%s API: %s", action, apiId));
				UpdateApiRequest apiRequest = UpdateApiRequest.newBuilder()
						.setApi(Api.newBuilder()
									.setName(format("projects/%s/locations/%s/apis/%s", profile.getProjectId(), profile.getLocation(), apiId))
									.setDisplayName((apiConfig.getData()!=null && apiConfig.getData().getDisplayName()!=null)?apiConfig.getData().getDisplayName():null)
									.setDescription((apiConfig.getData()!=null && apiConfig.getData().getDescription()!=null)?apiConfig.getData().getDescription():null)
									.setAvailability((apiConfig.getData()!=null && apiConfig.getData().getDisplayName()!=null)?apiConfig.getData().getAvailability():null)
									.putAllLabels((apiConfig.getMetadata()!=null && apiConfig.getMetadata().getLabels()!=null)?apiConfig.getMetadata().getLabels():null)
									.putAllAnnotations((apiConfig.getMetadata()!=null && apiConfig.getMetadata().getAnnotations()!=null)?apiConfig.getMetadata().getAnnotations():null)
								.build())
						.setAllowMissing(true).build();
				Api apiResponse = registryClient.updateApi(apiRequest);
				logger.info(apiResponse.toString());
				
				if(apiConfig.getData()!=null) {
					Data data = apiConfig.getData();
					//Versions
					for (Version version : data.getVersions()) {
						logger.info(format("%s API Version: %s", action, version.getMetadata().getName()));
						UpdateApiVersionRequest apiVersionRequest = UpdateApiVersionRequest.newBuilder()
					              .setApiVersion(ApiVersion.newBuilder()
				            		  .setName(format("projects/%s/locations/%s/apis/%s/versions/%s", profile.getProjectId(), profile.getLocation(), apiId, version.getMetadata().getName()))
				            		  .setDisplayName((version.getData()!=null && version.getData().getDisplayName()!=null)?version.getData().getDisplayName():null)
				            		  .setState((version.getData()!=null && version.getData().getState()!=null)?version.getData().getState():null)
				            		  .putAllAnnotations((version.getMetadata()!=null && version.getMetadata().getAnnotations()!=null)?version.getMetadata().getAnnotations():null)	  
					              .build())
					              .setAllowMissing(true)
					              .build();
						ApiVersion apiVersionResponse = registryClient.updateApiVersion(apiVersionRequest);
						logger.info(apiVersionResponse.toString());
						
						//Spec
						for(Spec_ spec : version.getData().getSpecs()) {
							logger.info(format("%s API Version Spec: %s", action, spec.getMetadata().getName()));
							UpdateApiSpecRequest apiSpecRequest = UpdateApiSpecRequest.newBuilder()
							              .setApiSpec(ApiSpec.newBuilder()
						            		  .setName(format("projects/%s/locations/%s/apis/%s/versions/%s/specs/%s", profile.getProjectId(), profile.getLocation(), apiId, version.getMetadata().getName(), spec.getMetadata().getName()))
						            		  .setFilename((spec.getData()!=null && spec.getData().getFilename()!=null)?spec.getData().getFilename():null)
						            		  .setSourceUri((spec.getData()!=null && spec.getData().getSourceURI()!=null)?spec.getData().getSourceURI():null)
						            		  .setContents((spec.getData()!=null && spec.getData().getSourceURI()!=null)?ByteString.readFrom(new FileInputStream(spec.getData().getSourceURI().replace("file://",""))):null)
						            		  .setMimeType((spec.getData()!=null && spec.getData().getMimeType()!=null)?spec.getData().getMimeType():null)
						            		  .putAllAnnotations((spec.getMetadata()!=null && spec.getMetadata().getAnnotations()!=null)?spec.getMetadata().getAnnotations():new HashMap<String, String>())	  
							              .build())
							              .setAllowMissing(true)
							              .build();
							ApiSpec apiSpecResponse = registryClient.updateApiSpec(apiSpecRequest);
							logger.info(apiSpecResponse.toString());
						}
					}
					//Deployments
					for (Deployment deployment : data.getDeployments()) {
						logger.info(format("%s API Version: %s", action, deployment.getMetadata().getName()));
						UpdateApiDeploymentRequest apiDeploymentRequest = UpdateApiDeploymentRequest.newBuilder()
						              .setApiDeployment(ApiDeployment.newBuilder()
						            		  .setName(format("projects/%s/locations/%s/apis/%s/deployments/%s", profile.getProjectId(), profile.getLocation(), apiId, deployment.getMetadata().getName()))
						            		  .setDisplayName((deployment.getData()!=null && deployment.getData().getDisplayName()!=null)?deployment.getData().getDisplayName():null)
						            		  .setApiSpecRevision((deployment.getData()!=null && deployment.getData().getApiSpecRevision()!=null)?deployment.getData().getApiSpecRevision():null)
						            		  .setEndpointUri((deployment.getData()!=null && deployment.getData().getEndpointURI()!=null)?deployment.getData().getEndpointURI():null)
						            		  .setExternalChannelUri((deployment.getData()!=null && deployment.getData().getExternalChannelURI()!=null)?deployment.getData().getExternalChannelURI():null)
						            		  .setIntendedAudience((deployment.getData()!=null && deployment.getData().getIntendedAudience()!=null)?deployment.getData().getIntendedAudience():null)
						            		  .setAccessGuidance((deployment.getData()!=null && deployment.getData().getAccessGuidance()!=null)?deployment.getData().getAccessGuidance():null)
						            		  .putAllLabels((deployment.getMetadata()!=null && deployment.getMetadata().getLabels()!=null)?deployment.getMetadata().getLabels():new HashMap<String, String>())	  
						            		  .putAllAnnotations((deployment.getMetadata()!=null && deployment.getMetadata().getAnnotations()!=null)?deployment.getMetadata().getAnnotations():new HashMap<String, String>())	  
						              .build())
						              .setAllowMissing(true)
						              .build();
						      ApiDeployment apiDeploymentResponse = registryClient.updateApiDeployment(apiDeploymentRequest);
						      logger.info(apiDeploymentResponse.toString());
					}
				}
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Helper to delete an API
	 * @param profile
	 * @param apiId
	 * * @param config
	 * @throws IOException
	 */
	public static void deleteAPI(BuildProfile profile, String apiId, APIConfig config) throws Exception {
		try {			
			GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			ApiName api = ApiName.of(profile.getProjectId(), profile.getLocation(), apiId);
			
			//delete artifacts
			for (Artifact artifact : registryClient.listArtifacts(api).iterateAll()) {
				logger.info(format("Deleting API artifact: %s",artifact.getName()));
				registryClient.deleteArtifact(artifact.getName());
			}
			
			//delete deployments
			for (ApiDeployment deployment : registryClient.listApiDeployments(api.toString()).iterateAll()) {
				logger.info(format("Deleting API deployment: %s", deployment.getName()));
		        registryClient.deleteApiDeployment(deployment.getName());
		    }

			//delete versions
			for (ApiVersion version : registryClient.listApiVersions(api.toString()).iterateAll()) {
				for (ApiSpec spec : registryClient.listApiSpecs(version.getName()).iterateAll()) {
					for (Artifact specArtifact : registryClient.listArtifacts(spec.getName()).iterateAll()) {
						logger.info(format("Deleting API spec artifact: %s",specArtifact.getName()));
						registryClient.deleteArtifact(specArtifact.getName());
					}
					logger.info(format("Deleting API version spec: %s",spec.getName()));
					registryClient.deleteApiSpec(spec.getName());
				}
				logger.info(format("Deleting API version: %s",version.getName()));
		        registryClient.deleteApiVersion(version.getName());
		    }
			
			//delete API
			logger.info(format("Deleting API: %s", apiId));
			//TODO: Remove the above logic and replace with delete API including --force flag
			registryClient.deleteApi(api);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws Exception
	 */
	private static GoogleCredentials getCredentials(BuildProfile profile) throws Exception {
		GoogleCredentials credentials = null;
		try {
			if(profile.getServiceAccountFilePath() == null && profile.getBearer() == null) {
				throw new Exception("Service Account or Bearer Token is missing");
			}
			else if(profile.getServiceAccountFilePath()!=null) {
				logger.info("Using the service account file to authenticate");
				credentials = GoogleCredentials
						.fromStream(new FileInputStream(profile.getServiceAccountFilePath()))
						.createScoped("https://www.googleapis.com/auth/cloud-platform");
			}else{
				logger.info("Using the bearer token");
				credentials = GoogleCredentials.newBuilder().setAccessToken(new AccessToken(profile.getBearer(), null)).build();
			}
		} catch (Exception e) {
			throw e;
		}
		return credentials;
	}
}
