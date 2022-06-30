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

public class BuildProfile {

	private String projectId;
	private String location;
	private String options;
	private String configFile;
	private String specDirectory; // Directory holding specs
	private String serviceAccountFilePath;
	private String bearer;
	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the options
	 */
	public String getOptions() {
		return options;
	}
	/**
	 * @param options the options to set
	 */
	public void setOptions(String options) {
		this.options = options;
	}
	/**
	 * @return the configFile
	 */
	public String getConfigFile() {
		return configFile;
	}
	/**
	 * @param configFile the configFile to set
	 */
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	/**
	 * @return the specDirectory
	 */
	public String getSpecDirectory() {
		return specDirectory;
	}
	/**
	 * @param specDirectory the specDirectory to set
	 */
	public void setSpecDirectory(String specDirectory) {
		this.specDirectory = specDirectory;
	}
	/**
	 * @return the serviceAccountFilePath
	 */
	public String getServiceAccountFilePath() {
		return serviceAccountFilePath;
	}
	/**
	 * @param serviceAccountFilePath the serviceAccountFilePath to set
	 */
	public void setServiceAccountFilePath(String serviceAccountFilePath) {
		this.serviceAccountFilePath = serviceAccountFilePath;
	}
	/**
	 * @return the bearer
	 */
	public String getBearer() {
		return bearer;
	}
	/**
	 * @param bearer the bearer to set
	 */
	public void setBearer(String bearer) {
		this.bearer = bearer;
	}
	
	

}
