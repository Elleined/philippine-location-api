pipeline {
    agent any

    tools {
        jdk 'java-25'
        gradle 'gradle-9.2.1'
    }

    stages {
        stage('Building the project') {
            steps {
                powershell './gradlew clean build'
            }
        }

        stage('Building docker images') {
            steps {
                powershell 'echo \$env:BRANCH_NAME - \$env:BRANCH_NUMBER'
                powershell 'docker build -t elleined/philippine-location-api:'
            }
        }
    }

    post {
        always {
            cleanWs()
        }

        success {
            echo 'Philippine location API jenkins pipeline success'
        }

        failure {
            echo 'Philippine location API jenkins pipeline failed'
        }
    }
}