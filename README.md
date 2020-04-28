# lince-predict-java
Microservice responsável pelas previsões estatísticas.

* Solução
* Autenticação com Spring Security e uso de Header
* Testes Unitários com JUnit/Spock/Groove
* Testes Funcionais com JUnit/Spock/Groove
* Swagger2
* Docker

![](https://github.com/lince-open/lince-predict-java/workflows/Java%20CI/badge.svg)
[![Known Vulnerabilities](https://snyk.io/test/github/lince-open/lince-predict-java/badge.svg)](https://snyk.io/test/github/pedrozatta/lince-predict-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-predict-java&metric=coverage)](https://sonarcloud.io/dashboard?id=lince-open_lince-predict-java)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-predict-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=lince-open_lince-predict-java)

## Docker Hub

https://hub.docker.com/repository/docker/linceopen/lince-predict

mvn clean package dockerfile:build

docker run --name lince-predict \
-e LINCE_PREDICT_PORT='8080' \
-e LINCE_JAVA_OPT='-Xms64m -Xmx128m' \
-p 51001:8080 \
-t lince-open/lince-kvs:latest

docker tag lince-open/lince-predict:latest linceopen/lince-predict:0.0.1

docker push linceopen/lince-predict:0.0.1

docker tag lince-open/lince-predict:latest linceopen/lince-predict:latest

docker push linceopen/lince-predict:latest

## Execução
mvn spring-boot:run

