package snaatak.ansible

def call(String playbookPath = 'roletool/tests/test.yaml') {
    stage('ansible-lint') {
        script {
            def ansibleLintInstalled = sh(script: 'command -v ansible-lint', returnStatus: true) == 0

            if (!ansibleLintInstalled) {
                sh 'sudo apt-get update'
                sh 'sudo apt-get install -y ansible-lint'
                sh 'sudo pip install ansible-lint'
            }

            sh "ansible-lint ${playbookPath}"
        }
    }
}
