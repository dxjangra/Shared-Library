package snaatak.common

def call() {
    stage('Licence Scanning'){
    def scancodeInstalled = sh(script: 'command -v scancode', returnStatus: true) == 0
    if (!scancodeInstalled) {
        // Install scancode-toolkit and python3-pip
        sh 'sudo apt-get update'
        sh 'sudo apt-get install -y python3-pip'
        sh 'sudo pip install scancode-toolkit'
    }

    // Run Scancode
    sh 'scancode . --json reportfile.json'
  }
}
