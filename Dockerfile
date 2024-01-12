FROM gradle:8.5-jdk17 AS build

RUN wget https://groovy.jfrog.io/artifactory/dist-release-local/groovy-zips/apache-groovy-binary-3.0.17.zip
RUN unzip apache-groovy-binary-3.0.17.zip -d /opt
RUN rm apache-groovy-binary-3.0.17.zip
ENV GROOVY_HOME=/opt/groovy-3.0.17
ENV PATH=$GROOVY_HOME/bin:$PATH

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build --no-daemon -x test

FROM openjdk:17-ea-jdk

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
