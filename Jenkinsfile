pipeline {
  agent any
  
  stages {
    stage("build"){
      steps {
        echo "Start building the application...."
        sh "mvn clean install"
        echo "End building the application"
      }
    }
  }
}
