pipeline{
    agent any
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
