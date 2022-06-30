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

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;

import com.apigee.registry.config.utils.BuildProfile;

public abstract class APIRegistryAbstractMojo extends AbstractMojo {

	/**
	 * Directory containing the build files.
	 *
	 * @parameter property="project.build.directory"
	 */
	private File buildDirectory;

	/**
	 * Base directory of the project.
	 *
	 * @parameter property="basedir"
	 */
	private File baseDirectory;

	/**
	 * Project Name
	 *
	 * @parameter property="project.name"
	 */
	private String projectName;

	/**
	 * Project version
	 *
	 * @parameter property="project.version"
	 */
	private String projectVersion;

	/**
	 * Project artifact id
	 *
	 * @parameter property="project.artifactId"
	 */
	private String artifactId;

	/**
	 * Profile id
	 *
	 * @parameter property="apigee.profile"
	 */
	private String id;

	/**
	 * Build option
	 *
	 * @parameter property="build.option"
	 */
	private String buildOption;

	/**
	 * Gateway options
	 *
	 * @parameter property="apigee.registry.config.options"
	 */
	private String options;

	/**
	 * Config File
	 *
	 * @parameter property="apigee.registry.config.file"
	 */
	private String configFile;
	
	/**
	 * Apigee Registry Project ID
	 *
	 * @parameter property="apigee.registry.projectId"
	 */
	private String projectId;
	
	/**
	 * Apigee Registry Location
	 * @parameter default-value="global"
	 * @parameter property="apigee.registry.location"
	 */
	private String location;

	/**
	 * Spec Directory
	 *
	 * @parameter property="apigee.registry.spec.dir"
	 */
	private String specDirectory;
	
	/**
	 * service account file
	 * @parameter property="apigee.registry.serviceaccount.file"
 	 */
	private String serviceAccountFilePath;
	
	/**
	 * Gateway bearer token
	 *
	 * @parameter property="apigee.registry.bearer"
	 */
	private String bearer;

	/**
	 * Skip running this plugin. Default is false.
	 *
	 * @parameter default-value="false"
	 */
	private boolean skip = false;

	public BuildProfile buildProfile;

	public APIRegistryAbstractMojo() {
		super();
	}

	public BuildProfile getProfile() {
		this.buildProfile = new BuildProfile();
		this.buildProfile.setLocation(this.location);
		this.buildProfile.setProjectId(this.projectId);
		this.buildProfile.setOptions(this.options);
		this.buildProfile.setConfigFile(this.configFile);
		this.buildProfile.setSpecDirectory(this.specDirectory);
		this.buildProfile.setServiceAccountFilePath(this.serviceAccountFilePath);
		this.buildProfile.setBearer(this.bearer);
		return buildProfile;
	}
	
	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

}
