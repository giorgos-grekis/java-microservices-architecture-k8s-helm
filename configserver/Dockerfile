# ==========================================
# Stage 1: Build (Χτίσιμο JAR και Custom JRE)
# ==========================================
FROM amazoncorretto:25-alpine AS build

RUN apk add --no-cache binutils

WORKDIR /app

# [ΒΕΛΤΙΣΤΟΠΟΙΗΣΗ DOCKER CACHE]
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Αντιγράφουμε τον κώδικα και χτίζουμε
COPY src src
RUN ./mvnw clean package -DskipTests

# Φτιάχνουμε το Custom JRE
# ΠΡΟΣΟΧΗ: Προστέθηκαν τα jdk.crypto.ec και jdk.charsets για το Git και το encrypt.key
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

# Δημιουργία non-root χρήστη
RUN addgroup -S spring && adduser -S spring -G spring

ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=build /customjre $JAVA_HOME

COPY --from=build /app/target/configserver-0.0.1-SNAPSHOT.jar configserver.jar

USER spring:spring

# Ενημερώνουμε ότι ακούμε στην 8071 (βάσει του application.yml)
EXPOSE 8071

ENTRYPOINT ["java", "-jar", "configserver.jar"]