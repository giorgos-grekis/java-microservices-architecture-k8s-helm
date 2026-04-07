# ==========================================
# Stage 1: Build (Χτίσιμο JAR και Custom JRE)
# ==========================================
FROM amazoncorretto:25-alpine AS build

# 1. Εγκαθιστούμε το binutils που απαιτεί το jlink για το --strip-debug
RUN apk add --no-cache binutils

# 2. Φτιάχνουμε το JAR μας
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests

# 3. Φτιάχνουμε το Custom JRE με τη σωστή σύνταξη της Java 25
RUN jlink \
    --add-modules java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument,jdk.unsupported,java.compiler \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress zip-6 \
    --output /customjre

# ==========================================
# Stage 2: Run
# ==========================================
FROM alpine:3.21

# Ορίζουμε πού θα μπει η Java μας
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Αντιγράφουμε το Custom JRE που φτιάξαμε στο Stage 1
COPY --from=build /customjre $JAVA_HOME

# Αντιγράφουμε το JAR του microservice
COPY --from=build /app/target/cards-0.0.1-SNAPSHOT.jar cards.jar

ENTRYPOINT ["java", "-jar", "cards.jar"]