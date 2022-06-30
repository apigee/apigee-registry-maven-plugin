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

import com.apigee.registry.config.model.data.version.Data_;
import com.apigee.registry.config.model.data.version.Metadata_;

public class Version {
	private Metadata_ metadata;
	private Data_ data;

	public Metadata_ getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata_ metadata) {
		this.metadata = metadata;
	}

	public Data_ getData() {
		return data;
	}

	public void setData(Data_ data) {
		this.data = data;
	}
}
