# Showcase TechStack with SpringBoot  OAuth2 KeyCloak Integration Project

This project demonstrates the implementation of Docker, Kubernetes, Hibernate/JPA ....and so on with OAuth2 authentication using KeyCloak in a microservices architecture. It
showcases various modern technologies and practices in cloud-native application development.

## Key Technologies

- **Spring Cloud Gateway**: API Gateway implementation with Spring WebFlux
- **OAuth2/KeyCloak**: Security implementation for authentication and authorization
- **Spring Security**: Security configuration with WebFlux support
- **Docker**: Containerization and deployment
- **Maven**: Build automation and dependency management
- **Spring Cloud**: Microservices ecosystem components
- **Resilience4j**: Circuit breaker implementation
- **Redis**: Reactive caching support
- **Prometheus**: Metrics monitoring
- **Eureka**: Service discovery
- **Spring Cloud Sleuth**: Distributed tracing
- **Spring Cloud Sleuth Zipkin**: Distributed tracing with Zipkin
- **Spring Cloud Config Server**: Centralized configuration management
- **Spring Cloud Config Client**: Dynamic configuration management
- **Spring Cloud Load Balancer**: Load balancing
- **Spring Cloud Circuit Breaker**: Circuit breaker
- **Spring Cloud OpenFeign**: Client-side load balancing and circuit breaker
- **Spring Cloud Security**: Security implementation
- **Spring Cloud Security OAuth2**: OAuth2 implementation
- **Telemetri**: Traceability and logging  ??? Needs to be implemented OpenTelemetry not working

## Docker Commands

The following commands help manage Docker containers and images for the application deployment:

# Build Docker Images

```shell
 
 docker build -t accounts .
 docker build . -t eroskoller/accounts:s04
```

# Run Docker Images
```shell

 docker run --name accounts-s04 -d -p 8080:8080 eroskoller/accounts:s04
```

# Stop Docker Images
```shell
 
 docker stop accounts-s04
```
# Remove Docker Images
```shell

 docker rmi accounts-s04
```
# Remove Docker Containers
```shell 

 docker rm accounts-s04
```
# Push Docker Images
```shell

 docker push eroskoller/accounts:s04
```
# Pull Docker Images
```shell

 docker pull eroskoller/accounts:s04
```

# Build with Maven Buildpacks
## Prepare pom.xml for Buildpacks
**_Ex_**
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <image>
            <name>eroskoller/${project.artifactId}:${project.version}</name>
        </image>
    </configuration>
</plugin>
```
## Build with buildpacks
```shell
 
    mvn spring-boot:build-image
```

# Build with Google Jib

## Maven Plugin

```xml 

<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>${jib.version}</version>
    <configuration>
        <to>
            <image>eroskoller/${project.artifactId}:${project.version}</image>
        </to>
        <from>
            <image>gcr.io/distroless/java21-debian12</image>
        </from>
    </configuration>
</plugin>
```

## Jib CLI build command
```shell
    mvn compile jib:dockerBuild
```

## Quick build all with Jib
- Using helper script (runs from this folder):
```shell
./build-jib-images.sh
```
- Or run manually:
```shell
cd accounts
mvn compile jib:dockerBuild
cd ../cards
mvn compile jib:dockerBuild
cd ../configserver
mvn compile jib:dockerBuild
```

