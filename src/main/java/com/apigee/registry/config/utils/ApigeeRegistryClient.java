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
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.apigee.registry.config.model.APIConfig;
import com.apigee.registry.config.model.data.APIArtifact;
import com.apigee.registry.config.model.data.Deployment;
import com.apigee.registry.config.model.data.Version;
import com.apigee.registry.config.model.data.version.Spec_;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.NotFoundException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.apigeeregistry.v1.Api;
import com.google.cloud.apigeeregistry.v1.ApiDeployment;
import com.google.cloud.apigeeregistry.v1.ApiDeploymentName;
import com.google.cloud.apigeeregistry.v1.ApiName;
import com.google.cloud.apigeeregistry.v1.ApiSpec;
import com.google.cloud.apigeeregistry.v1.ApiSpecName;
import com.google.cloud.apigeeregistry.v1.ApiVersion;
import com.google.cloud.apigeeregistry.v1.ApiVersionName;
import com.google.cloud.apigeeregistry.v1.ArtifactName;
import com.google.cloud.apigeeregistry.v1.DeleteApiDeploymentRequest;
import com.google.cloud.apigeeregistry.v1.DeleteApiRequest;
import com.google.cloud.apigeeregistry.v1.DeleteApiSpecRequest;
import com.google.cloud.apigeeregistry.v1.DeleteApiVersionRequest;
import com.google.cloud.apigeeregistry.v1.RegistryClient;
import com.google.cloud.apigeeregistry.v1.RegistrySettings;
import com.google.cloud.apigeeregistry.v1.UpdateApiDeploymentRequest;
import com.google.cloud.apigeeregistry.v1.UpdateApiRequest;
import com.google.cloud.apigeeregistry.v1.UpdateApiSpecRequest;
import com.google.cloud.apigeeregistry.v1.UpdateApiVersionRequest;
import com.google.protobuf.ByteString;

//SAMPLE - https://github.com/googleapis/google-cloud-java/tree/monorepo_script_output/java-apigee-registry/samples/snippets/generated/com/google/cloud/apigeeregistry/v1/registryclient

public class ApigeeRegistryClient {
	static Logger logger = LogManager.getLogger(ApigeeRegistryClient.class);
	
	
	/**
	 * Helper to check if API exist
	 * @param profile
	 * @param apiId
	 * @param apiConfig
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean getAPI(BuildProfile profile, String apiId, APIConfig apiConfig) throws Exception {
		try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			String name = ApiName.of(profile.getProjectId(), profile.getLocation(), apiId).toString();
		    Api response = registryClient.getApi(name);
		    if (response!=null && response.getCreateTime()!=null)
		    	return true;
		    else 
		    	return false;
		}catch (Exception e) {
			logger.error(format("API: %s not found", apiId));
			return false;
		}
	}
	
	/**
	 * Helper to check if API Version exist
	 * @param profile
	 * @param apiId
	 * @param version
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean getAPIVersion(BuildProfile profile, String apiId, Version version) throws Exception {
		try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			String name = ApiVersionName.of(profile.getProjectId(), profile.getLocation(), apiId, version.getMetadata().getName()).toString();
			ApiVersion response = registryClient.getApiVersion(name);
			if (response!=null && response.getCreateTime()!=null)
		    	return true;
		    else 
		    	return false;
		}catch (Exception e) {
			logger.error(format("API Version: %s not found", version.getMetadata().getName()));
			return false;
		}
	}
	
	
	/**
	 * Helper to check if API Version Spec exist
	 * @param profile
	 * @param apiId
	 * @param version
	 * @param spec
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean getAPIVersionSpec(BuildProfile profile, String apiId, String version, Spec_ spec) throws Exception {
		try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			String name = ApiSpecName.of(profile.getProjectId(), profile.getLocation(), apiId, version, spec.getMetadata().getName()).toString();
			ApiSpec response = registryClient.getApiSpec(name);
			if (response!=null && response.getCreateTime()!=null)
		    	return true;
		    else 
		    	return false;
		}catch (Exception e) {
			logger.error(format("API Version Spec: %s not found", spec.getMetadata().getName()));
			return false;
		}
	}
	
	/**
	 * Helper to check if API Deployment exist
	 * @param profile
	 * @param apiId
	 * @param depoloyment
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean getAPIDeployment(BuildProfile profile, String apiId, Deployment depoloyment) throws Exception {
		try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			String name = ApiDeploymentName.of(profile.getProjectId(), profile.getLocation(), apiId, depoloyment.getMetadata().getName()).toString();
			ApiDeployment response = registryClient.getApiDeployment(name);
			if (response!=null && response.getCreateTime()!=null)
		    	return true;
		    else 
		    	return false;
		}catch (Exception e) {
			logger.error(format("API Deployment: %s not found", depoloyment.getMetadata().getName()));
			return false;
		}
	}
	
	/**
	 * Helper to create API
	 * 
	 * @param profile
	 * @param apiId
	 * @param apiConfig
	 * @param action
	 * @throws Exception
	 */
	public static void createAPI(BuildProfile profile, String apiId, APIConfig apiConfig, String action) throws Exception {
		updateAPI(profile, apiId, apiConfig, action);
	}
	
	/**
	 * Helper to create API Version
	 * 
	 * @param profile
	 * @param apiId
	 * @param version
	 * @param action
	 * @throws Exception
	 */
	public static void createAPIVersion(BuildProfile profile, String apiId, Version version, String action) throws Exception {
		updateAPIVersion(profile, apiId, version, action);
	}
	
	/**
	 * Helper to create API Version Spec
	 * 
	 * @param profile
	 * @param apiId
	 * @param version
	 * @param spec
	 * @param action
	 * @throws Exception
	 */
	public static void createAPIVersionSpec(BuildProfile profile, String apiId, String version, Spec_ spec, String action) throws Exception {
		updateAPIVersionSpec(profile, apiId, version, spec, action);
	}
	
	/**
	 * Helper to create API Deployment
	 * 
	 * @param profile
	 * @param apiId
	 * @param deployment
	 * @param action
	 * @throws Exception
	 */
	public static void createAPIDeployment(BuildProfile profile, String apiId, Deployment deployment, String action) throws Exception {
		updateAPIDeployment(profile, apiId, deployment, action);
	}
	
	/**
	 * Helper to update API
	 * 
	 * @param profile
	 * @param apiId
	 * @param apiConfig
	 * @param action
	 * @throws Exception
	 */
	public static void updateAPI(BuildProfile profile, String apiId, APIConfig apiConfig, String action) throws Exception {
		try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			if(apiConfig!=null) {
				//API
				logger.info(format("%s API: %s", action, apiId));
				UpdateApiRequest apiRequest = UpdateApiRequest.newBuilder()
						.setApi(Api.newBuilder()
									.setName(format("projects/%s/locations/%s/apis/%s", profile.getProjectId(), profile.getLocation(), apiId))
									.setDisplayName(apiConfig.getData().getDisplayName()) //mandatory and checked in APIMojo itself
									.setDescription((apiConfig.getData()!=null && apiConfig.getData().getDescription()!=null)?apiConfig.getData().getDescription():"")
									.setRecommendedDeployment((apiConfig.getData()!=null && apiConfig.getData().getRecommendedDeployment()!=null)?format("projects/%s/locations/%s/apis/%s/deployments/%s", profile.getProjectId(), profile.getLocation(), apiId, apiConfig.getData().getRecommendedDeployment()):"")
									.setAvailability((apiConfig.getData()!=null && apiConfig.getData().getAvailability()!=null)?apiConfig.getData().getAvailability():"")
									.putAllLabels((apiConfig.getMetadata()!=null && apiConfig.getMetadata().getLabels()!=null)?apiConfig.getMetadata().getLabels():new HashMap<String, String>())
									.putAllAnnotations((apiConfig.getMetadata()!=null && apiConfig.getMetadata().getAnnotations()!=null)?apiConfig.getMetadata().getAnnotations():new HashMap<String, String>())
								.build())
						.setAllowMissing(true).build();
				Api apiResponse = registryClient.updateApi(apiRequest);
				logger.info(apiResponse.toString());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Helper to update API Version
	 * 
	 * @param profile
	 * @param apiId
	 * @param version
	 * @param action
	 * @throws Exception
	 */
	public static void updateAPIVersion(BuildProfile profile, String apiId, Version version, String action) throws Exception {
		try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			if(version!=null) {
				UpdateApiVersionRequest apiVersionRequest = UpdateApiVersionRequest.newBuilder()
			              .setApiVersion(ApiVersion.newBuilder()
		            		  .setName(format("projects/%s/locations/%s/apis/%s/versions/%s", profile.getProjectId(), profile.getLocation(), apiId, version.getMetadata().getName()))
		            		  .setDisplayName(version.getData().getDisplayName()) //mandatory and checked in APIVersionMojo itself
		            		  .setState((version.getData()!=null && version.getData().getState()!=null)?version.getData().getState():"")
		            		  .putAllAnnotations((version.getMetadata()!=null && version.getMetadata().getAnnotations()!=null)?version.getMetadata().getAnnotations():null)	  
			              .build())
			              .setAllowMissing(true)
			              .build();
				ApiVersion apiVersionResponse = registryClient.updateApiVersion(apiVersionRequest);
				logger.info(apiVersionResponse.toString());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Helper to update API Version Spec
	 * 
	 * @param profile
	 * @param apiId
	 * @param version
	 * @param spec
	 * @param action
	 * @throws Exception
	 */
	public static void updateAPIVersionSpec(BuildProfile profile, String apiId, String version, Spec_ spec, String action) throws Exception {
		try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			if(spec!=null) {
				//API Version Spec
				logger.info(format("%s API Version Spec: %s", action, spec.getMetadata().getName()));
				UpdateApiSpecRequest apiSpecRequest = UpdateApiSpecRequest.newBuilder()
				              .setApiSpec(ApiSpec.newBuilder()
			            		  .setName(format("projects/%s/locations/%s/apis/%s/versions/%s/specs/%s", profile.getProjectId(), profile.getLocation(), apiId, version, spec.getMetadata().getName()))
			            		  .setFilename((spec.getData()!=null && spec.getData().getFilename()!=null)?spec.getData().getFilename():null)
			            		  .setSourceUri((spec.getData()!=null && spec.getData().getSourceURI()!=null)?spec.getData().getSourceURI():"")
			            		  .setContents((spec.getData()!=null && spec.getData().getSourceURI()!=null)?ByteString.readFrom(new FileInputStream(spec.getData().getSourceURI().replace("file://",""))):null)
			            		  .setMimeType((spec.getData()!=null && spec.getData().getMimeType()!=null)?spec.getData().getMimeType():"")
			            		  .putAllAnnotations((spec.getMetadata()!=null && spec.getMetadata().getAnnotations()!=null)?spec.getMetadata().getAnnotations():new HashMap<String, String>())	  
				              .build())
				              .setAllowMissing(true)
				              .build();
				ApiSpec apiSpecResponse = registryClient.updateApiSpec(apiSpecRequest);
				logger.info(apiSpecResponse.toString());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * Helper to update API Deployment
	 * 
	 * @param profile
	 * @param apiId
	 * @param deployment
	 * @param action
	 * @throws Exception
	 */
	public static void updateAPIDeployment(BuildProfile profile, String apiId, Deployment deployment, String action) throws Exception {
		try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			if(deployment!=null) {
				String apiSpecRevision = getAPIRevisionForVersionSpec(profile, apiId, deployment.getData().getApiSpecRevision()); //fetch the revisionId
				UpdateApiDeploymentRequest request = UpdateApiDeploymentRequest.newBuilder()
			              .setApiDeployment(ApiDeployment.newBuilder()
			            		  .setName(format("projects/%s/locations/%s/apis/%s/deployments/%s", profile.getProjectId(), profile.getLocation(), apiId, deployment.getMetadata().getName()))
			            		  .setDisplayName(deployment.getData().getDisplayName()) //mandatory and checked in APIDeploymentMojo itself
			            		  .setApiSpecRevision(apiSpecRevision)
			            		  .setEndpointUri((deployment.getData()!=null && deployment.getData().getEndpointURI()!=null)?deployment.getData().getEndpointURI():"")
			            		  .setExternalChannelUri((deployment.getData()!=null && deployment.getData().getExternalChannelURI()!=null)?deployment.getData().getExternalChannelURI():"")
			            		  .setIntendedAudience((deployment.getData()!=null && deployment.getData().getIntendedAudience()!=null)?deployment.getData().getIntendedAudience():"")
			            		  .setAccessGuidance((deployment.getData()!=null && deployment.getData().getAccessGuidance()!=null)?deployment.getData().getAccessGuidance():"")
			            		  .putAllAnnotations((deployment.getMetadata()!=null && deployment.getMetadata().getAnnotations()!=null)?deployment.getMetadata().getAnnotations():new HashMap<String, String>())
			            		  .putAllLabels((deployment.getMetadata()!=null && deployment.getMetadata().getLabels()!=null)?deployment.getMetadata().getLabels():new HashMap<String, String>())
			              .build())
			              .setAllowMissing(true)
			              .build();
				ApiDeployment apiDeploymentResponse = registryClient.updateApiDeployment(request);
				logger.info(apiDeploymentResponse.toString());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Helper to update API Artifact
	 * 
	 * @param profile
	 * @param apiId
	 * @param artifact
	 * @param action
	 * @throws Exception
	 */
	public static void updateAPIArtifact(BuildProfile profile, String apiId, APIArtifact artifact, String action) throws Exception {
		/*try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			if(artifact!=null) {
				ReplaceArtifactRequest request = ReplaceArtifactRequest.newBuilder()
						.setArtifact(Artifact.newBuilder()
								.setName(format("projects/%s/locations/%s/apis/%s/artifacts/%s", profile.getProjectId(), profile.getLocation(), apiId, artifact.getMetadata().getName()))
			            		.setDisplayName((artifact.getData()!=null && artifact.getData().getDisplayName()!=null)?artifact.getData().getDisplayName():null)
						.build())
						.build();
				Artifact response = registryClient.replaceArtifact(request);
				      
				UpdateApiDeploymentRequest request = UpdateApiDeploymentRequest.newBuilder()
			              .setApiDeployment(ApiDeployment.newBuilder()
			            		  .setName(format("projects/%s/locations/%s/apis/%s/deployments/%s", profile.getProjectId(), profile.getLocation(), apiId, deployment.getMetadata().getName()))
			            		  .setDisplayName((deployment.getData()!=null && deployment.getData().getDisplayName()!=null)?deployment.getData().getDisplayName():null)
			            		  .setApiSpecRevision(apiSpecRevision)
			            		  .setEndpointUri((deployment.getData()!=null && deployment.getData().getExternalChannelURI()!=null)?deployment.getData().getExternalChannelURI():null)
			            		  .setExternalChannelUri((deployment.getData()!=null && deployment.getData().getExternalChannelURI()!=null)?deployment.getData().getExternalChannelURI():null)
			            		  .setIntendedAudience((deployment.getData()!=null && deployment.getData().getIntendedAudience()!=null)?deployment.getData().getIntendedAudience():null)
			            		  .setAccessGuidance((deployment.getData()!=null && deployment.getData().getAccessGuidance()!=null)?deployment.getData().getAccessGuidance():null)
			            		  .putAllAnnotations((artifact.getMetadata()!=null && artifact.getMetadata().getAnnotations()!=null)?artifact.getMetadata().getAnnotations():null)
			            		  .putAllLabels((artifact.getMetadata()!=null && artifact.getMetadata().getLabels()!=null)?artifact.getMetadata().getLabels():null)
			              .build())
			              .setAllowMissing(true)
			              .build();
				ApiDeployment apiDeploymentResponse = registryClient.updateApiDeployment(request);
				logger.info(apiDeploymentResponse.toString());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}*/
	}
	
	/**
	 * Helper to delete an API
	 * @param profile
	 * @param apiId
	 * @param config
	 * @throws Exception
	 */
	public static void deleteAPI(BuildProfile profile, String apiId, APIConfig config) throws Exception {
		try {			
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);			
			//delete API
			logger.info(format("Deleting API: %s", apiId));
			DeleteApiRequest request = DeleteApiRequest.newBuilder()
			              .setName(ApiName.of(profile.getProjectId(), profile.getLocation(), apiId).toString())
			              .setForce(true)
			              .build();
			registryClient.deleteApi(request);
		} catch(NotFoundException e) {
			if(e.getMessage()!=null && e.getMessage().contains("NOT_FOUND")) {
				logger.info(format("API: %s not found", apiId));
				return;
			}else {
				logger.error(e.getMessage());
				throw e;
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Helper to delete an API Version
	 * @param profile
	 * @param apiId
	 * @param version
	 * @throws Exception
	 */
	public static void deleteAPIVersion (BuildProfile profile, String apiId, Version version) throws Exception {
		try {			
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			//delete API versions
			logger.info(format("Deleting API version: %s",version.getMetadata().getName()));
			DeleteApiVersionRequest request = DeleteApiVersionRequest.newBuilder()
			              .setName(ApiVersionName.of(profile.getProjectId(), profile.getLocation(), apiId, version.getMetadata().getName()).toString())
			              .setForce(true)
			              .build();
			registryClient.deleteApiVersion(request);
		} catch(NotFoundException e) {
			if(e.getMessage()!=null && e.getMessage().contains("NOT_FOUND")) {
				logger.info(format("API Version: %s not found", version.getMetadata().getName()));
				return;
			}else {
				logger.error(e.getMessage());
				throw e;
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Helper to delete an API Version Spec
	 * @param profile
	 * @param apiId
	 * @param version
	 * @param spec
	 * @throws Exception
	 */
	public static void deleteAPIVersionSpec (BuildProfile profile, String apiId, String version, Spec_ spec) throws Exception {
		try {			
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			//delete versions spec
			logger.info(format("Deleting API version spec: %s",spec.getMetadata().getName()));
			DeleteApiSpecRequest request = DeleteApiSpecRequest.newBuilder()
		              .setName(ApiSpecName.of(profile.getProjectId(), profile.getLocation(), apiId, version, spec.getMetadata().getName()).toString())
		              .setForce(true)
		              .build();
			registryClient.deleteApiSpec(request);
		} catch(NotFoundException e) {
			if(e.getMessage()!=null && e.getMessage().contains("NOT_FOUND")) {
				logger.info(format("API Version Spec: %s not found", spec.getMetadata().getName()));
				return;
			}else {
				logger.error(e.getMessage());
				throw e;
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Helper to delete an API deployment
	 * @param profile
	 * @param apiId
	 * @param deployment
	 * @throws Exception
	 */
	public static void deleteAPIDeployment (BuildProfile profile, String apiId, Deployment deployment) throws Exception {
		try {			
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			//delete deployment
			logger.info(format("Deleting API deployment: %s", deployment.getMetadata().getName()));
			DeleteApiDeploymentRequest request = DeleteApiDeploymentRequest.newBuilder()
		          .setName(ApiDeploymentName.of(profile.getProjectId(), profile.getLocation(), apiId, deployment.getMetadata().getName()).toString())
		          .setForce(true)
		          .build();
			registryClient.deleteApiDeployment(request);
		} catch(NotFoundException e) {
			if(e.getMessage()!=null && e.getMessage().contains("NOT_FOUND")) {
				logger.info(format("API Deployment: %s not found", deployment.getMetadata().getName()));
				return;
			}else {
				logger.error(e.getMessage());
				throw e;
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Helper to delete an API artifact
	 * @param profile
	 * @param apiId
	 * @param artifact
	 * @throws Exception
	 */
	public static void deleteAPIArtifact (BuildProfile profile, String apiId, APIArtifact artifact) throws Exception {
		try {			
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			//delete artifact
			logger.info(format("Deleting API artifact: %s", artifact.getMetadata().getName()));
			String name = ArtifactName.ofProjectLocationApiArtifactName(profile.getProjectId(), profile.getLocation(), apiId, artifact.getMetadata().getName()).toString();
			registryClient.deleteArtifact(name);
		} catch(NotFoundException e) {
			if(e.getMessage()!=null && e.getMessage().contains("NOT_FOUND")) {
				logger.info(format("API Artifact: %s not found", artifact.getMetadata().getName()));
				return;
			}else {
				logger.error(e.getMessage());
				throw e;
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	
	/**
	 * Helper to get the revision of API Version Spec
	 * @param profile
	 * @param apiId
	 * @param version
	 * @return String
	 * @throws Exception
	 */
	public static String getAPIRevisionForVersionSpec(BuildProfile profile, String apiId, String version) throws Exception {
		String revision = null;
		try {
			GoogleCredentials credentials = GoogleCredsSingleton.getInstance(profile).getGoogleCredentials();
			//GoogleCredentials credentials = getCredentials(profile);
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			String name = format("projects/%s/locations/%s/apis/%s/versions/%s", profile.getProjectId(), profile.getLocation(), apiId, version.split("@")[0]);
			ApiSpec response = registryClient.getApiSpec(name);
			if (response!=null && response.getRevisionId()!=null)
				revision = format("projects/%s/locations/%s/apis/%s/versions/%s", profile.getProjectId(), profile.getLocation(), apiId, version.replace("latest", response.getRevisionId()));
		    else 
		    	revision = null;
		}catch (Exception e) {
			logger.error(format("API Version: %s not found", version));
		}
		return revision;
	}
	
	
	/**
	 * Helper method to set the Google Credentials
	 * @param profile
	 * @return
	 * @throws Exception
	 */
	/*private static GoogleCredentials getCredentials(BuildProfile profile) throws Exception {
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
	}*/
}
