# Cloud Foundry Exercise

## Create a Eureka Server on PCF

1. Login to Pivotal Apps Manager (if you are using Pivotal's public PCF instance, the URL is https://run.pivotal.io/)
1. Navigate to your org/space
1. Select the "services" tab
1. Press the "Add a Service" button
1. Create a new service...
   - Select "Spring Cloud Service Registry"
   - Select the standard plan type
   - Set the instance name to "xxxregistry" where "xxx" are your initials

## Deploy Microservices to Cloud Foundry

For each of the four microservices, create a `manifest.yml` file in the root of the application. The manifest should look similar to this:

```yml
applications:
- name: movie-award-service
  path: build/libs/movie-award-service-0.0.1-SNAPSHOT.jar
  random-route: true
  services:
  - xxxregistry
```

1. Change the name and path appropriately for each application, and set the service name to the service instance you created above.
1. Build each application with `./gradlew clean bootJar`
1. Deploy each application to PCF with `cf push`
1. Once everything is deployed, test that the application is running by navigating to the root of the aggregator application and starting the traffic simulator

## Access the Eureka Dashboard on PCF

1. Logon to Pivotal apps manager
1. Navigate to the registry service you created above
1. Select the "Manage" link at the top of the page. You will likely have to authenticate again, but you should see the Eureka dashboard
