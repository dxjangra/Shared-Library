package snaatak.common

def call() {
    stage('Clean Workspace') {
            cleanWs() // Use the cleanWs() step to clean the workspace
    }
}
