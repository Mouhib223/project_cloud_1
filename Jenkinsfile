pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK11' 
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
                    def mvnHome = tool name: 'Maven', type: 'hudson.tasks.Maven$MavenInstallation'
                    def mavenHome = mvnHome?.home
                    def maven = "${mavenHome}/bin/mvn"

                    sh "${maven} clean install"
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
                   
                    sh "az aks get-credentials --resource-group Uptech_crud --name Upteck"

                   
                    sh "kubectl apply -f upetck-app-deployment.yaml"
                    sh "kubectl apply -f upetck-app-service.yaml"
                }
            }
        }
    }

    post {
        always {
            deleteDir() // Delete workspace to clean up
        }
    }
}
