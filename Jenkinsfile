pipeline {
    
    agent none
    
    stages{
        
         stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3.9-eclipse-temurin-21-alpine'
                    args '-u root -v /tmp/m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

         stage('Create Docker Image'){
          steps {
            script {
                    app = docker.build('sudha0425/airlineselenium')
                }
           }
         }
     
         stage('Push Docker Image'){ 
           steps {
            script {
                    // registry url is blank for dockerhub
                    docker.withRegistry('', 'dockerHub_creds') {
                        app.push("latest")
                    }
            }
          }        
    }
    }
    post{
        always{
            sh "docker logout"
        }
    }
    
}
