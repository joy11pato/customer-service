# -----------------------------
# 1) Runtime: empaqueta el WAR
# -----------------------------
FROM eclipse-temurin:21-jdk-alpine


RUN apk --no-cache add curl

WORKDIR /opt/app/microservice-customers


COPY target/*.jar customer-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "customer-service.jar"]