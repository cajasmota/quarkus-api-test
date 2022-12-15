# WC-2026 / Players API

This project contains the API endpoints to create, read, update and delete Players

## OpenAPI Documentation
The project exposes documentation using the OpenAPI format at `/players/openapi` endpoint.  
In dev mode, there is a Swagger-UI to see the API Documentation at `/players/q/swagger-ui`

## JHusky
The project uses JHusky to run GIT hooks at pre-commit and pre-push events.
See [JHusky documentation](https://github.com/Dathin/JHusky) for setup details.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev # or quarkus build if the quarkus cli is installed
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Kubernetes Configuration
All kubernetes configuration for service and deployment will be generated automatically after executing `./mvnw install` or `quarkus build` if the quarkus cli is available.

Service and deployment can be configured using the following properties at `src/main/resources/application.properties`
for more information about used properties, see [Quarkus Kubernetes documentation](https://quarkus.io/guides/deploying-to-kubernetes#tuning-the-generated-resources-using-application-properties)

```properties
# Docker image tag to be used with the format registry/group/name:version
# quarkus.container-image.version=1.0  # Defaults to application version declared on pom.xml
quarkus.container-image.group=ayd2-dic2022
quarkus.container-image.name=players-api
quarkus.container-image.registry=registry.gitlab.com
# above configuratio will create tags like: registry.gitlab.com/ayd2-dic2022/players-api:1.0.7-SNAPSHOT

# service and deployment configuration
quarkus.kubernetes.namespace=wc2026
quarkus.kubernetes.ports.http.path=/players
quarkus.kubernetes.part-of=players-services
quarkus.kubernetes.labels.type=api
quarkus.kubernetes.version:latest
```


### Ingress
A custom ingress configuration was added to `/src/main/kubernetes/ingress.yml` to use the ingress-nginx controller previously installed on the cluster
