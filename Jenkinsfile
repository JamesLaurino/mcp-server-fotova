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
                    string(credentialsId: 'MAIL_HOG_HOST', variable: 'MAIL_HOG_HOST'),
                    string(credentialsId: 'MAIL_HOG_USERNAME', variable: 'MAIL_HOG_USERNAME'),
                    string(credentialsId: 'MAIL_HOG_PASSWORD', variable: 'MAIL_HOG_PASSWORD'),
                    string(credentialsId: 'RABBIT_HOST', variable: 'RABBIT_HOST'),
                    string(credentialsId: 'RABBIT_USERNAME', variable: 'RABBIT_USERNAME'),
                    string(credentialsId: 'RABBIT_PASSWORD', variable: 'RABBIT_PASSWORD'),
                    string(credentialsId: 'SERVER_PROTOCOL', variable: 'SERVER_PROTOCOL'),
                    string(credentialsId: 'SENDER_EMAIL', variable: 'SENDER_EMAIL'),
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