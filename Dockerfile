FROM openjdk:11
LABEL Eleonardo Oliveira
COPY target/mercado-livre-v1.jar mercado-livre-v1.jar
ENTRYPOINT ["java","-jar","/mercado-livre-v1.jar"]