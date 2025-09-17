# Build Docker Images

``
 docker build -t accounts .
 docker build . -t eroskoller/accounts:s04
``

# Run Docker Images
``
docker run --name accounts-s04 -d -p 8080:8080 eroskoller/accounts:s04
``

# Stop Docker Images
``
docker stop accounts-s04
``
# Remove Docker Images
``
docker rmi accounts-s04
``
# Remove Docker Containers
``
docker rm accounts-s04
``
# Push Docker Images
``
docker push eroskoller/accounts:s04
``
# Pull Docker Images
``
docker pull eroskoller/accounts:s04
``

#Build with maven Buildpacks
## Prepare pom.xml for buildpacks
**_Ex_**
``
<plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <image>
                        <name>eroskoller/${project.artifactId}:${project.version}</name>
                    </image>
``
# Build with buildpacks
``
    mvn spring-boot:build-image
``

#Build with Google Jib

## Maven Plugin
``
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
``
## Jib CLI build command
``
    mvn compile jib:dockerBuild
``

