pipeline {
    agent any

    stages {
        stage('Building the project') {
            steps {
                powershell '.\\gradlew.bat clean build'
            }
        }

        stage('Building docker images') {
            steps {
                powershell 'echo \$env:BRANCH_NAME - \$env:BRANCH_NUMBER'
                powershell 'docker build -t elleined/philippine-location-api:\$env:BRANCH_NAME-\$env:BRANCH_NUMBER'
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