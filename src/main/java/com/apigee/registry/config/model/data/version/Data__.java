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

package com.apigee.registry.config.model.data.version;

import java.util.List;

import com.apigee.registry.config.model.data.APIArtifact;

public class Data__ {
	private String sourceURI;
	private String filename;
	private String description;
	private List<APIArtifact> artifacts;
	private String mimeType;

	public String getFilename() {
		return filename;
	}

	public String getSourceURI() {
		return sourceURI;
	}

	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<APIArtifact> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(List<APIArtifact> artifacts) {
		this.artifacts = artifacts;
	}
}
