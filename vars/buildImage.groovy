#!/usr/bin/env groovy

// import com.example.Docker

// def call(String imageName) {
//     // this refers to the variables and methods from this groovy file that are going to be passed to the script of the Docker class
//     return new Docker(this).buildDockerImage(imageName)
// }

def call(String imageName) {
    echo 'building the image'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t $imageName ."
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
        sh "docker push $imageName"
    }

}

