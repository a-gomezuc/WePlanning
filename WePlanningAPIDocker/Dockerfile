FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD WePlanning.jar app.jar
ADD src src
EXPOSE 8443
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
