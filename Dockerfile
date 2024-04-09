FROM openjdk:18
EXPOSE 8080
ADD build/libs/journal-service-0.1.jar journal-service-0.1.jar
ENTRYPOINT ["java", "-jar", "/journal-service-0.1.jar"]
