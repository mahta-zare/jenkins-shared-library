#!/usr/bin/env groovy

def call() {
    echo "building image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh 'docker build -t mahtazare/test_repo:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push mahtazare/test_repo:jma-2.0'
    }
}