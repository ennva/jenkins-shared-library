#!/usr/bin/env groovy

package ennva.example

class Docker implements Serializable {

    def script

    Docker(script){
        this.script = script;
    }

    def buildDockerImage(String host, String imageName) {
        script.echo "Building docker image"
        script.withCredentials([script.usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USERNAME')]){
            script.sh "docker build -t $host/$imageName:${script.VERSION} ."
            script.sh "echo $script.PASS | docker login -u $script.USERNAME --password-stdin $host"
            script.sh "docker push $host/$imageName:${script.VERSION}"
        }
    }
}