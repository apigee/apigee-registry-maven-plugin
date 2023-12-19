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

package com.apigee.registry.config.model.data.artifact;

import java.util.ArrayList;
import java.util.List;

public class Data____ {
	private String displayName;
	private String description;
	private List<Reference____> references = new ArrayList<Reference____>();
	private String action;
	private String resultUri;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getResultUri() {
		return resultUri;
	}

	public void setResultUri(String resultUri) {
		this.resultUri = resultUri;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<Reference____> getReferences() {
		return references;
	}

	public void setReferences(List<Reference____> references) {
		this.references = references;
	}
}
