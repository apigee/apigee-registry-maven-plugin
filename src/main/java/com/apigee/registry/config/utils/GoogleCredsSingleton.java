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

import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

public class GoogleCredsSingleton {

	static Logger logger = LogManager.getLogger(GoogleCredsSingleton.class);
	
	// Static variable reference of creds of type GoogleCredsSingleton
	private static GoogleCredsSingleton creds = null;
	
	private GoogleCredentials googleCredentials;
	
	public void setGoogleCredentials(GoogleCredentials googleCredentials) {
		this.googleCredentials = googleCredentials;
	}
	
	public GoogleCredentials getGoogleCredentials() {
		return googleCredentials;
	}

	private GoogleCredsSingleton(BuildProfile profile) throws Exception {
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
			setGoogleCredentials(credentials);
		} catch (Exception e) {
			throw e;
		}
	}
	
    // Static method to create instance of GoogleCredsSingleton class
    public static GoogleCredsSingleton getInstance(BuildProfile profile) throws Exception
    {
        if (creds == null)
        	creds = new GoogleCredsSingleton(profile);
 
        return creds;
    }
}
