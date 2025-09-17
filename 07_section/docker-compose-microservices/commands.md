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

# RabbitMQ
```shell
# latest RabbitMQ 4.x
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management

# Docker Compose for RabbitMQ
version: "3.8"
services:
  rabbitmq:
    image: rabbitmq:4-management
    container_name: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      - RABBITMQ_DEFAULT_USER=guest
      - RABBITMQ_DEFAULT_PASS=guest
    restart: always

```

