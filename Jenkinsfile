pipeline {

    agent any

    tools {
        jdk 'JDK21'
    }

    stages{

        stage('Check Java Version') {
                steps {
                    sh 'java -version'
                    sh 'mvn -version'
                    sh 'mvn clean compile'
                }
            }

         stage('Create Jar file'){
           steps {
             sh  "mvn clean package -DskipTests"
           }
         }

         stage('Create Docker Image'){
          steps {
             sh "docker build sudha0425/AirlineSelenium"
           }
         }

         stage('Push Docker Image'){
           steps {
             sh "docker push sudha0425/AirlineSelenium"
           }
         }

    }

}
