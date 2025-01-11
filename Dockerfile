FROM debian:bullseye

RUN apt-get update && apt-get install -y openjdk-17-jdk openssh-server

WORKDIR /app

COPY target/gcPOC-0.0.1-SNAPSHOT.jar gcPOC.jar

EXPOSE 8080 22

ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# SSH Configuration
RUN mkdir /var/run/sshd
RUN echo "root:password" | chpasswd
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -i 's/PasswordAuthentication no/PasswordAuthentication yes/' /etc/ssh/sshd_config
# Start SSH and the Java application
CMD ["/bin/bash", "-c", "/usr/sbin/sshd -D & java $JAVA_OPTS -jar /app/gcPOC.jar"]