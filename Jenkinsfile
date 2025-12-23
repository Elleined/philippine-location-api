pipeline {
    agent any

    stages {
        stage('Fetch and Pull Github Repository') {
            steps {
                powershell 'echo heelo'
                powershell 'git fetch'
                powershell 'git pull origin ${env.BRANCH_NAME}  '
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}