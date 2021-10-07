def call(name, dockerUsrName, containerName, dockerCredential) {
pipeline {
  agent any

  tools {
    maven 'mvn-version'
  }

  stages {
        stage('Build') {
           steps {
              script {
                welcome("${name}")
                  mavenPackage()
               }
           }
       }
    
        stage("Build image") {
            steps {
                 script {
                    step.buildNum()
                    step.buildImage("${dockerUsrName}","${containerName}")
                }
            }
        }
    

        stage("Push image") {
             steps {
                script {
                    step.pushImage("${dockerCredential}")
                  }
              }
          }
     }
 }
}
