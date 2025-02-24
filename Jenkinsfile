pipeline {
	agent any
	tools {
		// maven name in Jenkins configuration
		maven "maven-3.9.9"
		
	}
	stages {
		stage("build jar"){
			steps{
				script {
					echo "building the application..."
					sh "mvn package"
				}
			}
		}
		stage("build image"){
			steps{
				script {
					echo "building the docker image..."
					// ID of docker credentials in Jenkins
					withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')])
					{
						sh 'docker build -t 192.168.109.130:8083/java-maven-app:2.0 .'
						sh 'echo $PASS | docker login -u $USER --password-stdin 192.168.109.130:8083'
						sh 'docker push 192.168.109.130:8083/java-maven-app:2.0'
					}
				}
			}
		}
		stage("deploy"){
			steps{
				script {
					echo "deploying..."
				}
			}
		}
	}
}
