pipeline {
    
    agent any
    
    stages{
        
         stage('Create Jar file'){
           steps {
             sh  "mvn clean package -DskipTests"
           }
         }

         stage('Create Docker Image'){
          steps {
             sh "docker build -t sudha0425/airlineselenium ."
           }
         }
     
         stage('Push Docker Image'){ 
           environment{
               DOCKER_HUB = credentials('dockerHub_creds')
           }
           steps {
             sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
             sh  "docker push sudha0425/airlineselenium:latest"
             sh "docker tag sudha0425/airlineselenium:latest sudha0425/airlineselenium:${env.BUILD_NUMBER}"
             sh "docker push sudha0425/airlineselenium:${env.BUILD_NUMBER}"
           }
         } 
       
    }

    post{
        always{
            sh "docker logout"
        }
    }
    
}
