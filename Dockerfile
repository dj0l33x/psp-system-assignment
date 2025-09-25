FROM bellsoft/liberica-openjdk-alpine:21-cds AS builder
WORKDIR /app
COPY ./build/libs/psp-system-assignment-0.0.1-SNAPSHOT.jar psp-system-assignment-0.0.1-SNAPSHOT.jar
RUN java -Djarmode=layertools -jar psp-system-assignment-0.0.1-SNAPSHOT.jar extract

FROM bellsoft/liberica-openjdk-alpine:21-cds
WORKDIR /app
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/application/ ./
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:InitialRAMPercentage=70.0", "-XX:MaxRAMPercentage=70.0", "org.springframework.boot.loader.launch.JarLauncher"]