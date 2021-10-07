@Library('shared-library') _
def mvnBuild(dockerUsrName, containerName, dockerCredential) {
pipeline {
  agent any

  tools {
    maven 'mvn-version'
  }

  stages {
        stage('Build') {
           steps {
              script {
                 mavenPackage()
               }
           }
       }
    
        stage("Build image") {
            steps {
                 script {
                    step.buildNum()
                    step.buildImage(${dockerUsrName}, ${containerName})
                }
            }
        }
    

        stage("Push image") {
             steps {
                script {
                    step.pushImage(${dockerCredential})
                  }
              }
          }
     }
 }
}
