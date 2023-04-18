#!/usr/bin/env groovy

def call() {
    echo "Building docker image"
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USERNAME')]){
        sh('docker build -t localhost:8085/spring-boot-zero-hero:${VERSION} .')
        sh('echo $PASS | docker login -u $USERNAME --password-stdin localhost:8085')
        sh('docker push localhost:8085/spring-boot-zero-hero:${VERSION}')
    }
}