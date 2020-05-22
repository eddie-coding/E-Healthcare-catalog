FROM openjdk:8-jre-alpine
ADD target/medical.jar medical.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "medical.jar"]