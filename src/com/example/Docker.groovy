#!usr/bin/env groovy

package com.example

// implements serializable to support the saving the state of the execution if the pipeline is paused and resumed.
class Docker implements Serializable {
    // all env variables and shell commands sh, |, etc. used in Jenkinsfile can be used in jenkins shared libraries, but not directly in the src package.
    // to make them available, we pass a variable from the vars using a script and the script holds all these variables and commands.
    def script



    Docker(script) {
        // the code sets the objects own script value to whatever was passed in. 
        // Like saying, Store the incoming value in my own script property.
        this.script = script
    }


    // To make the variables and methods available we pass them through the script by adding script.
    def buildDockerImage(String imageName) {
        script.echo "building image..."
        script.sh "docker build -t $imageName ."

    }

    def dockerPush(String imageName) {
        script.sh "docker push $imageName"
    } 
    
    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"   
        }
    }

}

