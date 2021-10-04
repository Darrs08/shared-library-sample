def build(){
  sh 'mvn package'
}

def buildNum(){
  echo "Build image with tag: ${env.BUILD_ID}"
}

def buildImage(){
  myapp = docker.build("darrs08/ledger-service:${env.BUILD_ID}", "--build-arg VERSION=${env.BUILD_ID} .")
}
