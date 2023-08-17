pipeline {
    agent any

    environment {
        
          
        DOCKER_IMAGE_NAME = "raykadri/upteck_crud_springboot-app:v1"
    
    }

    stages {
        stage('Checkout') {
            steps {
             
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                  // Use the Maven tool directly
                withMaven(
                    maven: 'Maven', 
                    mavenSettingsConfig: 'my-maven-settings' 
                ) {
                    // Run Maven goals directly
                    sh 'mvn clean install'
                }

                   
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def mvnHome = tool name: 'Maven', type: 'hudson.tasks.Maven$MavenInstallation'
                    def mavenHome = mvnHome?.home
                    def maven = "${mavenHome}/bin/mvn"

               
                    sh "${maven} test"
                }
            }
        }
         
 
        stage('Build and Push Docker Image') {
            steps {
              

                // Build the Docker image
                sh 'docker build -t $DOCKER_IMAGE_NAME .'

                // Log in to Docker Hub
                withDockerRegistry(credentialsId: 'dockerhub_id', url: '') {
                    // Push the Docker image to Docker Hub
                    sh 'docker push $DOCKER_IMAGE_NAME'
                }
            }
        }
        stage('Generate Checksum') {
            steps {
                script {
                    def checksum = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    writeFile file: 'checksum.txt', text: checksum
                }
            }
        }

        stage('Deploy') {
            steps {
              script {
                withKubeConfig([credentialsId: 'K8S', serverUrl: '']) {
                sh ('kubectl apply -f upteck-back-deployment.yaml')
                }}
            

            }
        }
    }

    post {
        always {
            deleteDir() // Delete workspace to clean up
        }
    }
}
