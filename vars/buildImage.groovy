#!/usr/bin/env groovy

// import com.example.Docker

// def call(String imageName) {
//     // this refers to the variables and methods from this groovy file that are going to be passed to the script of the Docker class
//     return new Docker(this).buildDockerImage(imageName)
// }

def call() {
    echo 'building the image'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t mahtazare/my-repo:jma-2.0 .'
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
        sh 'docker push mahtazare/my-repo:jma-2.0'
    }

}
