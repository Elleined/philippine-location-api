pipeline {
    agent any

    stages {
        stage('Verifying gradle is available') {
            steps {
                powershell './gradlew --version'
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
                powershell './gradlew clean build'
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