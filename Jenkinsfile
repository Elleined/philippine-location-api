pipeline {
    agent any

    tools {
        jdk 'java-25'
        gradle 'gradle-9.2.1'
    }

    environment {
        IMAGE_NAME = 'elleined/philippine-location-api'
        IMAGE_TAG = "\$env:BRANCH_NAME-\$env:BUILD_NUMBER"
    }

    stages {
        stage('Building the project') {
            steps {
                powershell 'gradle clean build'
            }
        }

        stage('Building docker image') {
            steps {
                powershell 'docker build -t \$env:IMAGE_NAME:\$env:IMAGE_TAG .'
            }
        }

        stage('Logging in to dockerhub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'docker-access-token-for-psa-pc-jenkins',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    powershell 'docker login -u elleined -p \$DOCKER_PASSWORD'
                }
            }
        }

        stage('Pushing docker image') {
            steps {
                powershell 'docker push \$env:IMAGE_NAME:\$env:IMAGE_TAG'
            }
        }
    }

    post {
        always {
            cleanWs()
            powershell 'docker logout'
        }

        success {
            echo 'Philippine location API jenkins pipeline success'
        }

        failure {
            echo 'Philippine location API jenkins pipeline failed'
        }
    }
}