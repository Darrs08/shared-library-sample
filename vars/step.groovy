def buildNum(){
  echo "Build image with tag: ${env.BUILD_ID}"
}

def buildImage(user, containerName){
  myapp = docker.build("${user}/${containerName}:${env.BUILD_ID}", "--build-arg VERSION=${env.BUILD_ID} .")
}

def pushImage(dockerCred){
  docker.withRegistry('https://registry.hub.docker.com', "${dockerCred}"){
    myapp.push("latest")
    myapp.push("${env.BUILD_ID}")
  }
}
