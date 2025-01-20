pipeline {
    agent any
    
    environment {
        DOCKER_REGISTRY_CREDENTIALS = 'docker123' 
    }
    
    stages {
        stage('Clone Repository') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [[$class: 'CloneOption', timeout: 15]],
                    submoduleCfg: [],
                    userRemoteConfigs: [[url: 'https://github.com/nikki00011/spekube.git']]
                ])
            }
        }
        stage('Build Backend Services') {
            steps {
                script {
                    // Build each Spring Boot service
                    def services = ['question-service', 'contribute-service', 'quiz-service','service-registry','api-gateway','cloud-config-server']
                    for (service in services) {
                        dir(service) {
                            sh "mvn clean package -DskipTests"
                        }
                    }
                }
            }
        }
        
        stage('Test Backend Services') {
            steps {
                script {
                    // Test each Spring Boot service
                    def services = ['question-service', 'contribute-service', 'quiz-service','service-registry','api-gateway','cloud-config-server']
                    for (service in services) {
                        dir(service) {
                            sh "mvn test"
                        }
                    }
                }
            }
        }
        
        stage('Build and Test Frontend') {
            steps {
                script {
                    // Navigate to frontend directory
                    dir('frontend') {
                        // Install dependencies and build React app
                        sh 'npm install'
                        sh 'npm run build'
                    }
                }
            }
        }
        
        stage('Dockerize') {
            steps {
                script {
                    // Dockerize and push backend services
                    sh 'docker build -t nikki00011/question-service:0.0.1 ./question-service'
                    sh 'docker build -t nikki00011/contribute-service:0.0.1 ./contribute-service'
                    sh 'docker build -t nikki00011/quiz-service:0.0.1 ./quiz-service'
                    sh 'docker build -t nikki00011/front-end:0.0.1 ./frontend'
                    
                    // Dockerize other services
                    sh 'docker build -t nikki00011/config-server:0.0.1 ./cloud-config-server'
                    sh 'docker build -t nikki00011/service-registry:0.0.1 ./service-registry'
                    sh 'docker build -t nikki00011/api-gateway:0.0.1 ./api-gateway'
                }
            }
        }
        stage('docker push images'){
          steps{
              script{
                  docker.withRegistry('', 'docker123') {
                      sh 'docker push nikki00011/question-service:0.0.1'
                      sh 'docker push nikki00011/contribute-service:0.0.1'
                      sh 'docker push nikki00011/quiz-service:0.0.1'
                      sh 'docker push nikki00011/front-end:0.0.1'
                      sh 'docker push nikki00011/config-server:0.0.1'
                      sh 'docker push nikki00011/service-registry:0.0.1'
                      sh 'docker push nikki00011/api-gateway:0.0.1' 
                  }
              }
          }
        }
        Sign up in https://ngrok.com/

2. Download ngrok from: https://bin equinox.io/c/4VmDzA7iaHb/ngrok-stable-linux-amd64.tgz

3. Then extract ngrok from the terminal: Ssudo tar xvzf/Downloads/ngrok-stable-linux-amd64.tgz-C/usr/local/bin

4. Copy Authtoken from: https://dashboard.ngrok.com/get-started/your-authtoken

5. Add Authtoken: Sngrok authtoken <token>
     //  stage('deploy to kubernetes') {https://github.com/saihemath2000/SPEMAJOR/tree/master/frontend
     //      steps {
     //         withKubeConfig(caCertificate: '', clusterName: 'minikube', contextName: '', credentialsId: 'k8-cred', namespace: 'webapps', restrictKubeConfigAccess: false, serverUrl: 'https://192.168.49.2:8443') {
     //           sh "kubectl apply -f k8s/"  
     //         } 
     //      }
     // }
     //  stage('verify the deployment') {
     //      steps { 
     //         withKubeConfig(caCertificate: '', clusterName: 'minikube', contextName: '', credentialsId: 'k8-cred', namespace: 'webapps', restrictKubeConfigAccess: false, serverUrl: 'https://192.168.49.2:8443') {
     //           sh "kubectl get pods -n webapps"     
     //         }
     //      }
     // }   
}
}    
