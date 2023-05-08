FROM java:11
EXPOSE 8088

VOLUME /tmp
ADD timbercld-starter.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
