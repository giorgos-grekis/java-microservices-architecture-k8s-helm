# ==========================================
# Stage 1: Build
# ==========================================
FROM amazoncorretto:25-alpine AS build

RUN apk add --no-cache binutils

WORKDIR /app

# [DOCKER CACHE]
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Αντιγράφουμε τον κώδικα και χτίζουμε
COPY src src
RUN ./mvnw clean package -DskipTests

# Φτιάχνουμε το Custom JRE
# Περιλαμβάνονται τα απαραίτητα modules για WebFlux, Netty, Actuator και Eureka
RUN jlink \
    --add-modules java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument,jdk.unsupported,java.compiler,java.xml,java.net.http,jdk.crypto.ec,jdk.charsets,jdk.naming.dns,jdk.naming.rmi \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress zip-6 \
    --output /customjre

# ==========================================
# Stage 2: Run
# ==========================================
FROM alpine:3.21

# Δημιουργία non-root χρήστη για ασφάλεια
RUN addgroup -S spring && adduser -S spring -G spring

# Ορισμός του Java Home στο custom JRE που φτιάξαμε
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Αντιγραφή του JRE από το build stage
COPY --from=build /customjre $JAVA_HOME

# Αντιγραφή του JAR του Gateway Server
COPY --from=build /app/target/gatewayserver-0.0.1-SNAPSHOT.jar gatewayserver.jar

USER spring:spring

EXPOSE 8072

# Εκκίνηση της εφαρμογής
ENTRYPOINT ["java", "-jar", "gatewayserver.jar"]