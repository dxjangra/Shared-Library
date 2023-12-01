package snaatak.terraform

def call() {
    stage('Dry_Run') {
        sh 'terraform plan'
    }
}
