FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar statService.jar
ENTRYPOINT ["java","-jar","/statService.jar"]