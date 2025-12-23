pipeline {
    agent any

    tools {
        jdk 'java-25'
        gradle 'gradle-9.2.1'
    }

    stages {
        stage('Building the project') {
            steps {
                powershell 'echo \$env:BRANCH_NAME-\$env:BUILD_NUMBER'
                powershell 'gradle --no-daemon clean build'
            }
        }

        stage('Building docker images') {
            steps {
                powershell 'docker build -t elleined/philippine-location-api:\$env:BRANCH_NAME-\$env:BUILD_NUMBER'
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