FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} crm.jar
ENTRYPOINT ["java", "-jar", "crm.jar"]