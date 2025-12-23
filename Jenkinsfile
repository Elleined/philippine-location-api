pipeline {
    agent any

    stages {
        stage('Fetch and Pull Github Repository') {
            steps {
                sh 'echo heelo'
                sh 'git fetch'
                sh 'git pull origin ${env.BRANCH_NAME}  '
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}