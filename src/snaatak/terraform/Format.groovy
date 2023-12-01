package snaatak.terraform

def call () {
   stage('Formatting') {
            sh 'terraform fmt'  
    }
}
