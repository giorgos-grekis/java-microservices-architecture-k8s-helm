# ==========================================
# Stage 1: Build (Χτίσιμο JAR και Custom JRE)
# ==========================================
FROM amazoncorretto:25-alpine AS build

# 1. Εγκαθιστούμε το binutils που απαιτεί το jlink για το --strip-debug
RUN apk add --no-cache binutils

WORKDIR /app

# 2. Φτιάχνουμε το JAR μας
#COPY . /app
#WORKDIR /app
#RUN ./mvnw clean package -DskipTests

# [ΒΕΛΤΙΣΤΟΠΟΙΗΣΗ DOCKER CACHE]
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
# Κατεβάζουμε τα dependencies πρώτα
RUN ./mvnw dependency:go-offline

# Αντιγράφουμε τον κώδικα και χτίζουμε
COPY src src
RUN ./mvnw clean package -DskipTests

# Φτιάχνουμε το Custom JRE (με τα απαραίτητα XML και HTTP modules)
RUN jlink \
    --add-modules java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument,jdk.unsupported,java.compiler,java.xml,java.net.http \
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

# Ορίζουμε πού θα μπει η Java μας
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Αντιγράφουμε το Custom JRE που φτιάξαμε στο Stage 1
COPY --from=build /customjre $JAVA_HOME

# Αντιγράφουμε το JAR του microservice
COPY --from=build /app/target/loans-0.0.1-SNAPSHOT.jar loans.jar

USER spring:spring

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "loans.jar"]