pipeline {

    agent any

    tools {
        maven 'Maven-3.9'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/JamesLaurino/mcp-server-fotova.git'
            }
        }

        stage('Build & publish') {
            steps {
                sh 'mvn clean deploy -DskipTests'
            }
        }

        stage('Stop & Cleanup') {
            steps {
                script {
                    echo "Arrêt de l'ancienne instance..."
                    sh "pkill -f 'java -jar target/mcp-server-fotova.jar' || echo 'Aucun processus en cours.'"
                }
            }
        }

        stage('Run Application') {

            steps {

                withCredentials([

                    string(credentialsId: 'SERVER_HOST', variable: 'SERVER_HOST'),
                    string(credentialsId: 'DB_NAME', variable: 'DB_NAME'),
                    string(credentialsId: 'DB_USERNAME', variable: 'DB_USERNAME'),
                    string(credentialsId: 'DB_PASSWORD', variable: 'DB_PASSWORD')
                ]) {
                    script{
                        withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                            sh '''
                            nohup java -jar target/mcp-server-fotova.jar --spring.profiles.active=acc > app.log 2>&1 &
                            sleep 20
                            tail -n 200 app.log
                            '''
                        }
                    }
                }
            }
        }
    }
}