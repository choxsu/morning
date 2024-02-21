FROM java:8u351

ENV SERVER_NAME=ycc-imt

WORKDIR /app

ADD $SERVER_NAME.jar /app

EXPOSE 8160

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/ycc-imt.jar"]
