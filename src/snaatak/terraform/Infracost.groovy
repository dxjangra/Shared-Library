package snaatak.terraform

def call() {
    def infracostInstalled = false

    // Check if Infracost is installed
    script {
        try {
            sh 'infracost --version'
            infracostInstalled = true
        } catch (Exception e) {
            // Infracost is not installed
        }
    }

    stage('Cost Estimation') {
        script {
            if (!infracostInstalled) {
                sh 'curl -fsSL https://raw.githubusercontent.com/infracost/infracost/master/scripts/install.sh | sh'
                withCredentials([string(credentialsId: 'infracost_api_key', variable: 'INFRACOST_API_KEY')]) {
                    sh "infracost configure set api_key $INFRACOST_API_KEY"
                }
            }
            sh 'infracost breakdown --path .'
        }
    }
}
