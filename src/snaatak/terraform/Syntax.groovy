package snaatak.terraform

def call() {
    stage('Syntax') {
        sh 'terraform validate'
    }
}
