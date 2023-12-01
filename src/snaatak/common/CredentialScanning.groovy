package snaatak.common

def call() {
    stage('Cred Scanning'){
    def gitleaksInstalled = sh(script: 'command -v gitleaks', returnStatus: true) == 0

    if (!gitleaksInstalled) {
        sh 'wget https://github.com/zricethezav/gitleaks/releases/download/v7.0.2/gitleaks-linux-amd64 -O gitleaks'
        sh 'chmod +x gitleaks'
        sh 'sudo mv gitleaks /usr/local/bin/'
    }

    sh 'gitleaks --report-format=toml > .gitleaks.toml'

    sh 'gitleaks detect -p . --files-at-commit=latest . > output.txt 2>&1'

    // Grep the "No leaks found" keyword from the output.txt file
    def grepOutput = sh(script: 'grep "No leaks found" output.txt', returnStdout: true, returnStatus: true)

    if (grepOutput == 0) {
        echo 'No leaks found.'
    } else {
        error 'Gitleaks detected leaks. Failing the pipeline.'
    }
 }
}
// def performCredentialScanning() {
//     if (!gitleaksInstalled()) {
//         sh 'wget https://github.com/zricethezav/gitleaks/releases/download/v7.0.2/gitleaks-linux-amd64 -O gitleaks'
//         sh 'chmod +x gitleaks'
//         sh 'sudo mv gitleaks /usr/local/bin/'
//     }

//     sh 'gitleaks --version' // Check Gitleaks version to verify installation

//     if (!gitleaksInstalled()) {
//         sh 'gitleaks --report-format=toml > .gitleaks.toml'
//         sh '''
//             echo '[[rules]]
//             pattern = "api_key"
//             exclude = ["*.md"]
//             severity = "high"

//             [[rules]]
//             pattern = "password"
//             commit = true' > .gitleaks.toml
//         '''
//         sh 'cat .gitleaks.toml'
//     }

//     sh 'gitleaks detect -p . --files-at-commit=latest .'
// }

// def gitleaksInstalled() {
//     def result = sh(script: 'which gitleaks', returnStatus: true)
//     return result == 0
// }
