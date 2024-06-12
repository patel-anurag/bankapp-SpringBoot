FROM openjdk:17
ADD Bankapp.jar Bankapp.jar
ENTRYPOINT [ "java", "-jar", "Bankapp.jar"]