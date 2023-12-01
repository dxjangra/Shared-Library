package snaatak.ansible

def call(String playbookPath = 'roletool/tests/test.yaml', String inventoryPath = 'roletool/tests/inventory') {
    stage('ansible-DryRun') {
        script {

            sh "ansible-playbook ${playbookPath} --check"
        }
    }
}
