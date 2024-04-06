FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar service-product.jar
ENTRYPOINT ["java","-jar","/service-product.jar"]
EXPOSE 8081