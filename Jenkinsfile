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
                  // Use the Maven tool directly
                withMaven(
                    maven: 'Maven', 
                ) {
                    // Run Maven goals directly
                    sh 'mvn test'
                }

                   
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
        success {
            emailext subject: 'Build Success',
                     body: 'The build was successful. Congratulations!',
                     to: 'rayenekadri15@gmail.com' // Specify the recipient's email address here
        }
        failure {
            emailext subject: 'Build Failure',
                     body: 'The build failed. Please check the logs for details.',
                     to: 'rayenekadri15@gmail.com' // Specify the recipient's email address here
        }
    }
}
