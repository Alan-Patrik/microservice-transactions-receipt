FROM openjdk:11.0.12-slim-buster

ARG PROFILE

ENV PROFILE=${PROFILE}

COPY /target/transactionreceipt*.jar transactionreceipt.jar

SHELL ["/bin/sh", "-c"]

CMD java -jar transactionreceipt.jar --spring.profiles.active=${PROFILE}
