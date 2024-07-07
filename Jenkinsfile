pipeline {
  agent any

  tools {
    maven 'maven' 
    helm 'helm'
  }

  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/Aym2016/AmsRest' 
        git 'https://github.com/Aym2016/amsSA1'
      }
    }

    stage('Tests') {
      steps {
        dir('AmsRest') {
          sh 'mvn test'
        }
      }
    }

    stage('Deploy to Nexus') {
      steps { 
        dir('AmsRest') {
          sh 'mvn deploy'
        }  
      }
    }

    stage('SonarQube Analysis') {
      steps {
        dir('AmsRest') {
          withSonarQubeEnv('Sonarqube') {
            sh 'mvn sonar:sonar'
          }
        }

        dir('amsSA1') {
          withSonarQubeEnv('Sonarqube') {
            sh '''
              npm install
              npm run lint
              npm run test
              npm run build
              sonar-scanner
              '''
          }
        }
      }
    }

    stage('Build Docker image') {
      steps {
        dir('AmsRest') {
          sh 'docker build -t registry.example.com/amsrest:latest .'
        }

        dir('amsSA1') {
          sh 'docker build -t registry.example.com/amssafront:latest .'
        }
      }
    }

    stage('Push to Docker Registry') {
      steps {
        dir('AmsRest') {
          sh 'docker push registry.example.com/amsrest:latest'
        }

        dir('amsSA1') {
          sh 'docker push registry.example.com/amssafront:latest' 
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        dir('deploy') {
          helm 'install . --name myapp' 
        }  
      }
    }
  }
}