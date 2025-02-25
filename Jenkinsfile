def gv

pipeline {
	agent any
	environment {
		SEMGREP_APP_TOKEN = credentials('SEMGREP_APP_TOKEN')
	}
	tools {
		// maven name in Jenkins configuration
		maven "maven-3.9.9"
		
	}
	stages {
		stage("init"){
			steps{
				script {
					gv = load "script.groovy"
				}
			}
		}
		stage("build jar"){
			steps{
				script {
					gv.buildJar()
				}
			}
		}
		stage('Semgrep-Scan') {
        		steps {
          			sh 'pip3 install semgrep'
          			sh 'semgrep ci'
      			}
		}
		stage("build image"){
			steps{
				script {
					gv.buildImage()
				}
			}
		}
		stage("deploy"){
			steps{
				script {
					gv.deployApp()
				}
			}
		}
	}
}
