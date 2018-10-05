FROM jenkins/jenkins:lts

USER root

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

RUN cd /usr/local/bin
RUN curl -SLO https://download.docker.com/linux/static/stable/x86_64/docker-18.03.1-ce.tgz
RUN	tar xzvf docker-18.03.1-ce.tgz --strip 1 -C /usr/local/bin docker/docker
RUN rm docker-18.03.1-ce.tgz

RUN curl -sL https://deb.nodesource.com/setup_10.x | bash -
RUN apt-get update
RUN apt-get install -y apparmor \
        git \
        nodejs \
        npm

RUN npm link @angular/cli && npm install -g @angular/cli && npm -v && ng -v

# Setup Maven
ENV MAVEN_VERSION 3.5.4

RUN curl -fsSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
COPY settings.xml /root/.m2/settings.xml

# Confiure Jenkins
COPY plugins.txt /usr/share/jenkins/plugins.txt
COPY config.groovy /usr/share/jenkins/ref/init.groovy.d/config.groovy
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt


COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY /users "$JENKINS_HOME"/users/

RUN mkdir -p "$JENKINS_HOME"/workspace/cursosboot
RUN mkdir -p "$JENKINS_HOME"/jobs/cursosboot
COPY /jobs/cursosboot_config.xml "$JENKINS_HOME"/jobs/cursosboot/config.xml
RUN mkdir -p "$JENKINS_HOME"/jobs/cursosboot/latest/
RUN mkdir -p "$JENKINS_HOME"/jobs/cursosboot/builds/1/

RUN mkdir -p "$JENKINS_HOME"/workspace/cursosfront
RUN mkdir -p "$JENKINS_HOME"/jobs/cursosfront
COPY /jobs/cursosfront_config.xml "$JENKINS_HOME"/jobs/cursosfront/config.xml
RUN mkdir -p "$JENKINS_HOME"/jobs/cursosfront/latest/
RUN mkdir -p "$JENKINS_HOME"/jobs/cursosfront/builds/1/