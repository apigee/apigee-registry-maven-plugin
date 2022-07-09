------------
Plugin Usage
------------
```
mvn install -P{profile} -DprojectId=${project} -Dfile={path}

  # Options

  -P<profile>
    Pick a profile in the pom.xml.
    Apigee Registry location, config file, option are picked from the profile.

  -Dapigee.registry.config.options
    none   - No action (default)
    create - Creates the API in the Apigee Registry
    update - Updates the API in the Apigee Registry
    delete - Deletes the API in the Apigee Registry
    sync   - executes delete and update options mentioned above
    
  -Dfile
  	path to the service account key file that has the appropriate Apigee Registry permissions
  
  -Dbearer
  	access token. Service Account file takes precedence
    
```


# Samples

## Prerequisites (Apigee Hub setup)
- Follow the [steps](https://cloud.google.com/apigee/docs/api-hub/get-started-api-hub) to provision Apigee Hub
- Create a Service Account with the Apigee Registry permissions, download the service account key file

## API Configuration

- Check out the sample [config yaml file](./api-config.yaml) that includes the structure of the API, specs, deployment and artifact objects needed to push an API to the API Registry


### Basic Implementation

**Please ensure all prerequisites have been followed prior to continuing.**

```
/samples
```

This project demonstrates use of apigee-registry-maven-plugin to push API to a Apigee Registry. 

To use, edit samples/pom.xml and update values as specified.

```xml
<apigee.registry.projectId>${projectId}</apigee.registry.projectId> <!-- GCP Project ID where Apigee Registry is provisioned -->
<apigee.registry.location>global</apigee.registry.location> <!-- Apigee Registry location. Default is global -->
<apigee.registry.config.file>./api-config.yaml</apigee.registry.config.file> <!-- Path to the API configuration yaml-->
<apigee.registry.config.options>${options}</apigee.registry.config.options> <!-- Options like none, create, update, delete, sync. Default is none-->
<apigee.registry.serviceaccount.file>${file}</apigee.registry.serviceaccount.file> <!-- Service Account File. Use this or "apigee.registry.bearer". Service Account takes precedence -->
<apigee.registry.bearer>${bearer}</apigee.registry.bearer> <!-- Bearer Token. Use this or  "apigee.registry.serviceaccount.file" -->
```

To run, jump to the sample project `cd /samples` and run 

```bash
mvn install -P{profile} -DprojectId={projectId} -Doptions={option} -Dfile={path}
```
where
-  `profile` is the Maven profile in the pom.xml
-  `projectId` is the GCP Project ID where Apigee Registry is provisioned
-  `options` to either "create", "update", "delete" or "sync" the API into the API Registry
-  `path` is the path to your Service Account Key file


