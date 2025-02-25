def gv

pipeline {
	agent any
	
	tools {
		// maven name in Jenkins configuration
		maven "maven-3.9.9"
		
	}
	environment{
		SEMGREP_APP_TOKEN = credentials('808a0fb01f96405bfffa3d4ba05bd390d6e91c232a6968c974278d7cb058b76c')
	}
	stages {
		stage("init"){
			steps{
				script {
					gv = load "script.groovy"
				}
			}
		}
		/*stage('Semgrep-Scan') {
        		steps {
          			sh 'pip3 install semgrep'
          			sh 'semgrep ci'
      			}
		}*/
		stage("build jar"){
			steps{
				script {
					gv.buildJar()
				}
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
