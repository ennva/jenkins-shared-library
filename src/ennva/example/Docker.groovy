#!/usr/bin/env groovy

package ennva.example

class Docker implements Serializable {

    def script

    Docker(script){
        this.script = script;
    }

    def buildDockerImage(String host, String imageName) {
        script.echo "Building docker image"
        script.sh "docker build -t $host/$imageName:${script.VERSION} ."
    }

    def dockerLogin(String host = '') {
        script.withCredentials([script.usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USERNAME')]){
            script.sh("echo login in $host")
            script.sh('echo $script.PASS | docker login -u $script.USERNAME --password-stdin $host')
        }
    }

    def dockerPush(String host = '', String imageName) {
        script.sh "docker push $host/$imageName:${script.VERSION}"
    }
}
