pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "your-docker-repo/your-app:latest" // Docker 이미지 이름
        DOCKER_REGISTRY = "https://index.docker.io/v1/"  // Docker Hub 주소
        DEPLOY_SERVER = "user@your-server-ip"           // 배포할 서버
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://gitlab.com/your-repo.git'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-credentials-id', variable: 'DOCKER_PASSWORD')]) {
                    sh '''
                        echo $DOCKER_PASSWORD | docker login -u your-dockerhub-username --password-stdin
                        docker push $DOCKER_IMAGE
                    '''
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh '''
                        ssh -o StrictHostKeyChecking=no $DEPLOY_SERVER "
                        docker pull $DOCKER_IMAGE &&
                        docker stop your-container-name || true &&
                        docker rm your-container-name || true &&
                        docker run -d --name your-container-name -p 8080:8080 $DOCKER_IMAGE
                        "
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Deployment successful.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}