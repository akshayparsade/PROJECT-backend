pipeline{
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials') // Use the stored Jenkins credentials
    }
    stages{
        stage("Pull"){
           steps{
                  git branch: 'dev', url: 'https://github.com/akshayparsade/PROJECT-backend.git '
            }
        }
        stage("Build"){
           steps{
              sh 'mvn clean package'
            }
        } 
        stages {
        stage('Login to Docker Hub') {
            steps {
                script {
                    // Use Jenkins credentials to login to Docker Hub
                    sh "echo $DOCKER_HUB_CREDENTIALS_PSW | docker login -u $DOCKER_HUB_CREDENTIALS_USR --password-stdin"
                }
            }
        }
        stage("Deploy"){
           steps{
                sh '''
                   docker build . -t akshayparsade/my_cbz_project:latest
                   docker push akshayparsade/my_cbz_project:latest
                   docker rmi akshayparsade/my_cbz_project:latest
                   kubectl apply -f ./deploy/
                 '''
            }
            
        }

    }
        
}
