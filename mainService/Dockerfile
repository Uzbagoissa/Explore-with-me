FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar mainService.jar
ENTRYPOINT ["java","-jar","/mainService.jar"]