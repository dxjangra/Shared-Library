package snaatak.common

def call(String repoUrl = 'https://github.com/OT-MICROSERVICES/attendance-api.git', String credentialsId = 'shared', String branchName = 'main') {
       stage('Cloning Repo') {
        git branch: branchName, credentialsId: credentialsId, url: repoUrl 
    }
}
