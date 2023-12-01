package snaatak.terraform

def call() {
    stage('Lint') {
        installTflint()
        runTflint()
    }
}

def installTflint() {
    echo "Checking if tflint is installed..."
    script {
        if (!isTflintInstalled()) {
            echo "Installing tflint..."
            sh ''' 
                wget https://github.com/terraform-linters/tflint/releases/latest/download/tflint_linux_amd64.zip
                unzip tflint_linux_amd64.zip
                sudo mv tflint /usr/local/bin/
                tflint --version
            '''
        } else {
            echo "tflint is already installed"
        }
    }
}

def runTflint() {
    echo "Running tflint..."
    sh 'tflint'
}

def isTflintInstalled() {
    def tflintVersion = sh(script: 'tflint --version', returnStatus: true)
    return tflintVersion == 0
}
