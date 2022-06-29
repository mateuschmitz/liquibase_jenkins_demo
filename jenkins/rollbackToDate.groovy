pipeline {
    agent any
    stages {
        stage('Configure Credentials') {
            steps {
                sh '''
                git config --global user.email "jenkins@solfacil.com.br" && git config --global user.name "Jenkins" && echo "driver: org.postgresql.Driver\nurl: jdbc:postgresql://postgres_test:5432/solfacil\nusername: solfacil\npassword: solfacil\nchangeLogFile: ./db.changelog.xml\nliquibase.hub.mode=off" > liquibase.properties
                '''
            }
        }
        stage('Rollback Database') {
            steps {
                sh '''
                    /opt/liquibase/liquibase rollbackToDate ${ROLLBACK_DATE}
                '''
            }
        }
    }
}
