pipeline {
    agent any

    tools {
        jdk 'java-25'
        gradle 'gradle-9.2.1'
    }

    environment {
        DOCKER_USERNAME = "elleined"
        DOCKER_IMAGE_BUILD = "${DOCKER_USERNAME}/philippine-location-api:${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
    }

    stages {
        stage('Building the project') {
            steps {
                powershell 'gradle clean build'
            }
        }

        stage('Building docker image') {
            steps {
                powershell 'docker build -t \$env:DOCKER_IMAGE_BUILD .'
            }
        }

        stage('Logging in to dockerhub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'docker-access-token-for-psa-pc-jenkins',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    powershell '\$DOCKER_PASSWORD | docker login -u \$env:DOCKER_USERNAME --password-stdin'
                }
            }
        }

        stage('Pushing docker image') {
            steps {
                powershell 'docker push \$env:DOCKER_IMAGE_BUILD'
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