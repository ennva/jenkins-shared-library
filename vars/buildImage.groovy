#!/usr/bin/env groovy

def call(String imageName) {
    echo "Building docker image"
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USERNAME')]){
        sh('docker build -t $imageName:${VERSION} .')
        sh('echo $PASS | docker login -u $USERNAME --password-stdin localhost:8085')
        sh('docker push $imageName:${VERSION}')
    }
}