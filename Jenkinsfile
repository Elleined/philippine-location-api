pipeline {
    agent any

    tools {
        jdk 'java-25'
        gradle 'gradle-9.2.1'
    }

    stages {
        stage('Verifying git is available') {
            steps {
                powershell 'git --version'
            }
        }

        stage('Verifying java is available') {
            steps {
                powershell 'java --version'
            }
        }

        stage('Verifying gradle is available') {
            steps {
                powershell 'gradle --version'
            }
        }

        stage('Verifying docker is available') {
            steps {
                powershell 'docker --version'
            }
        }

        stage('Fetching the repository') {
            steps {
                powershell 'git fetch'
            }
        }

        stage('Pulling the repository') {
            steps {
                powershell 'git pull origin ${env.BRANCH_NAME}'
            }
        }

        stage('Building the project') {
            steps {
                powershell 'gradle clean build'
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