FROM java:8 AS java-builder
WORKDIR /workspace
ADD . /workspace
RUN ./gradlew -x test build

FROM node:12 AS node-builder
WORKDIR /workspace
ADD webapp /workspace
RUN npm install
RUN npm run build

FROM java:8
COPY --from=java-builder /workspace/build/libs/app-0.0.1-SNAPSHOT.jar /app.jar
COPY --from=node-builder /workspace/build /static
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]