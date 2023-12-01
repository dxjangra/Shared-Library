package snaatak.terraform

def call() {
    stage('init') {
        sh 'terraform init'    
    }
}
