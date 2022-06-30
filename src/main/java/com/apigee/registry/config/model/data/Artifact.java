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


package com.apigee.registry.config.model.data;

import com.apigee.registry.config.model.data.artifact.Data____;
import com.apigee.registry.config.model.data.artifact.Metadata____;

public class Artifact {
	private String kind;
	private Metadata____ metadata;
	private Data____ data;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Metadata____ getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata____ metadata) {
		this.metadata = metadata;
	}

	public Data____ getData() {
		return data;
	}

	public void setData(Data____ data) {
		this.data = data;
	}
}
