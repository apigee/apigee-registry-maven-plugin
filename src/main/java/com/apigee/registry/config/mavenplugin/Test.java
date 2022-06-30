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

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

	public static void main(String[] args) {
		try {
			
			Path path = Paths.get("file://./samples/specs/openapi-103.yaml");
			
			FileInputStream i = new FileInputStream("https://raw.githubusercontent.com/OAI/OpenAPI-Specification/main/examples/v3.0/petstore.yaml");
			System.out.println(i);
			/*ServerProfile profile = new ServerProfile();
			profile.setProjectId("ssv-apigee-asm-demo");
			profile.setLocation("global");
			profile.setConfigFile("/Users/ssvaidyanathan/GitHub/apigee-registry-maven-plugin/samples/api-config.json");
			profile.setServiceAccountFilePath("/Users/ssvaidyanathan/Downloads/ssv-apigee-asm-demo-b089f9f55e94.json");
			
			GoogleCredentials credentials = GoogleCredentials
					.fromStream(new FileInputStream(profile.getServiceAccountFilePath()))
					.createScoped("https://www.googleapis.com/auth/cloud-platform");
			RegistrySettings registrySettings = RegistrySettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			
			ApiName api = ApiName.of(profile.getProjectId(), profile.getLocation(), "api001");
			//delete versions
			for (ApiVersion version : registryClient.listApiVersions(api.toString()).iterateAll()) {
				//logger.info()
		        registryClient.deleteApiVersion(version.getName());
		      }*/
			
			//profile.setBearer("ya29.A0ARrdaM-EJsllLKOt2SXiYfrzA7Ur_6sElsMPx1OgonokMaAlgWvp9QPZ_UsxbhhZCcxf99QBvJG8jlVjw71ACP6u-han64AQkPCFignISWAtQY9ivAe7xQVjWL3slOR85zfeKYpGJZpxz2CujpbGDF-3WqxOHkDLvEy1d6cYUNnWUtBVEFTQVRBU0ZRRl91NjFWMC0tUkdyTzlfTGduWXZJUjJQRmdTQQ0174");
			//ApigeeRegistryClient.deleteAPI(profile, "api001");
			
			/*GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/Users/ssvaidyanathan/Downloads/ssv-apigee-asm-demo-b089f9f55e94.json"))
											.createScoped("https://www.googleapis.com/auth/cloud-platform");
			RegistrySettings registrySettings =
			        RegistrySettings.newBuilder()
			            .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
			            .build();
			RegistryClient registryClient = RegistryClient.create(registrySettings);
			
			UpdateApiRequest request = UpdateApiRequest.newBuilder()
					.setApi(Api.newBuilder()
							.setName(format("projects/%s/locations/%s/apis/%s", "ssv-apigee-asm-demo", "global", "api004"))
							.setDisplayName("Test API4")
							.setDescription("Test API4")
							.setAvailability("NONE")
							.putLabels("apihub-team", "example")
							.putLabels("apihub-business-unit", "example")
							.putLabels("apihub-lifecycle", "retired")
							.putLabels("apihub-style", "apihub-openapi")
							.putLabels("apihub-target-users", "partner")
							.putAnnotations("apihub-primary-contact", "abc@xyz.com")
							.putAnnotations("apihub-primary-contact-description", "ABC Contact")
							.build())
		            .setAllowMissing(true)
					.build();
			Api response = registryClient.updateApi(request);
			System.out.println(response.toString());*/

			/*CreateApiVersionRequest versionRequest = CreateApiVersionRequest.newBuilder()
					.setParent(ApiName.of("ssv-apigee-asm-demo", "global", "api001").toString())
					.setApiVersion(ApiVersion.newBuilder().build()).setApiVersionId("1").build();
			ApiVersion apiVersionResponse = registryClient.createApiVersion(versionRequest);
			System.out.println(apiVersionResponse.toString()); 
			
			File spec = new File("/Users/ssvaidyanathan/GitHub/apigee-registry-maven-plugin/samples/specs/petstore.yaml");
			CreateApiSpecRequest apiSpecRequest =
			          CreateApiSpecRequest.newBuilder()
			              .setParent(ApiVersionName.of("ssv-apigee-asm-demo", "global", "api001", "1").toString())
			              .setApiSpec(ApiSpec.newBuilder()
			            		  			.setFilename(spec.getName())
		            		  				.setContents(ByteString.readFrom(new FileInputStream(spec)))
		            		  				.build())
			              .setApiSpecId("2")
			              .build();
			      ApiSpec apiSpecResponse = registryClient.createApiSpec(apiSpecRequest);
			      System.out.println(apiSpecResponse.toString());*/
			      
			/*CreateArtifactRequest createArtifactRequest =
		              CreateArtifactRequest.newBuilder()
		                  .setParent(LocationName.of("ssv-apigee-asm-demo", "global").toString())
		                  .setArtifact(Artifact.newBuilder().build())
		                  .setArtifactId("1")
		                  .build();
		          Artifact artifactResponse = registryClient.createArtifact(createArtifactRequest);
		          System.out.println(artifactResponse.toString()); 
		     
	         CreateApiDeploymentRequest createApiDeploymentRequest =
	                  CreateApiDeploymentRequest.newBuilder()
	                      .setParent(ApiName.of("ssv-apigee-asm-demo", "global", "api001").toString())
	                      .setApiDeployment(ApiDeployment.newBuilder().build())
	                      .setApiDeploymentId("2")
	                      .build();
	              ApiDeployment apiDeploymentResponse = registryClient.createApiDeployment(createApiDeploymentRequest);
	              System.out.println(apiDeploymentResponse.toString());*/

		} catch (Exception e) {
			throw new RuntimeException("Failure: " + e.getMessage());
		}

	}

}
