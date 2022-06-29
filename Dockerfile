FROM jenkins/jenkins:lts

USER 0

RUN apt update && \
    apt install -y unzip wget && \
    wget -O /opt/liquibase-4.12.0.zip https://github.com/liquibase/liquibase/releases/download/v4.12.0/liquibase-4.12.0.zip && \
    unzip /opt/liquibase-4.12.0.zip -d /opt/liquibase

USER 1000

ENTRYPOINT ["/sbin/tini", "--", "/usr/local/bin/jenkins.sh"]