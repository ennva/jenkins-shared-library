#!/usr/bin/env groovy

def call(String host, String imageName) {
    echo "Building docker image"
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USERNAME')]){
        sh "docker build -t $host/$imageName:${VERSION} ."
        sh "echo $PASS | docker login -u $USERNAME --password-stdin $host"
        sh "docker push $host/$imageName:${VERSION}"
    }
}