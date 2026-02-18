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
           steps {
             sh  "docker push sudha0425/airlineselenium"
           }
         } 
       
    }
    
}
