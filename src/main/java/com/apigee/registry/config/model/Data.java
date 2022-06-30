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

package com.apigee.registry.config.model;

import java.util.ArrayList;
import java.util.List;

import com.apigee.registry.config.model.data.Artifact;
import com.apigee.registry.config.model.data.Deployment;
import com.apigee.registry.config.model.data.Version;

public class Data {
	private String displayName;
	private String description;
	private String availability;
	private String recommendedDeployment;
	private List<Version> versions = new ArrayList<Version>();
	private List<Deployment> deployments = new ArrayList<Deployment>();
	private List<Artifact> artifacts = new ArrayList<Artifact>();

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecommendedDeployment() {
		return recommendedDeployment;
	}

	public void setRecommendedDeployment(String recommendedDeployment) {
		this.recommendedDeployment = recommendedDeployment;
	}

	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	public List<Deployment> getDeployments() {
		return deployments;
	}

	public void setDeployments(List<Deployment> deployments) {
		this.deployments = deployments;
	}

	public List<Artifact> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}
}
