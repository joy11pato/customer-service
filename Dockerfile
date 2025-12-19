# -----------------------------
# 1) Runtime: empaqueta el WAR
# -----------------------------
FROM eclipse-temurin:21-jdk-alpine

ADD target/customer-service.jar customer-service.jar

ENTRYPOINT ["java", "-jar", "customer-service.jar"]