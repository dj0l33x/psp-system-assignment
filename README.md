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

```
Image name: psp-system-assignment:local                                                                                   -rw-r--r--         0:0     1.7 kB  │                   │   └── ZipInflaterInputStream.class                               
Total Image size: 196 MB                                                                                                  drwxr-xr-x         0:0     1.0 kB  │                   ├── jarmode                                                        
Potential wasted space: 56 kB                                                                                             -rw-r--r--         0:0      383 B  │                   │   ├── JarMode.class                                              
Image efficiency score: 99 %                                                                                              -rw-r--r--         0:0      661 B  │                   │   └── JarModeErrorException.class                                
```
