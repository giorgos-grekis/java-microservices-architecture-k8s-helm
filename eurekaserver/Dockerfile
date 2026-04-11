# Stage 1: Build
FROM amazoncorretto:25-alpine AS build

RUN apk add --no-cache binutils

WORKDIR /app

# [DOCKER CACHE]
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean package -DskipTests

RUN jlink \
    --add-modules java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument,jdk.unsupported,java.compiler,java.xml,java.net.http,jdk.crypto.ec,jdk.charsets,jdk.naming.dns,jdk.naming.rmi \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress zip-6 \
    --output /customjre

# Stage 2: Run
FROM alpine:3.21

RUN addgroup -S spring && adduser -S spring -G spring

ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=build /customjre $JAVA_HOME

COPY --from=build /app/target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver.jar

USER spring:spring

EXPOSE 8070

ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]