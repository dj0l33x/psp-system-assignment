# PSP System Assignment
[![Build Workflow](https://github.com/dj0l33x/psp-system-assignment/actions/workflows/build.yaml/badge.svg)](https://github.com/dj0l33x/psp-system-assignment/actions/workflows/build.yaml)

## Environment

#### Required
- JDK 21
- Docker

#### Optional
- SDKMAN (https://sdkman.io)
- Dive (https://github.com/wagoodman/dive)

If you use SDKMAN, you can install jdk with the following command 
```shell
sdk env install
```

## Project

#### Tech Stack
- Kotlin 1.9.5
- Spring Boot 3.5.6

#### Build the project
```shell
./gradlew clean build
```

#### Environment variables
All used environment variables are defined in `.env` file. This configuration
file is used by `docker-compose` to start the application. Also, you can add
to the Spring Boot run configuration in your IDE.

## Containerization

#### Build the docker image

```shell
./gradlew clean build -x test && docker build -t psp-system-assignment:local .
```

#### Run the local docker image

```shell
docker run --rm -p 8080:8080 psp-system-assignment:local
```

#### Image information from `dive` command:
To see the image information, you can use the command
```shell
dive psp-system-assignment:local
```

Output example:
```
Image name: psp-system-assignment:local
Total Image size: 196 MB
Potential wasted space: 56 kB
Image efficiency score: 99 %
```
#### Continuous integration build
Each push to the repository main branch triggers the docker image build. The image is
stored in GitHub Container Registry. There is no `latest` tag. Instead, you can use 
a commit hash from the main branch or git tag from the repository. 

You can pull the same image with the commands:
```shell
docker pull ghcr.io/dj0l33x/psp-system-assignment:v0.0.3
```
and
```shell
docker pull ghcr.io/dj0l33x/psp-system-assignment:645649fd5d5272f6593d6192f849f62048750580
```

