package snaatak.ansible

def call(String playbookPath = 'roletool/tests/test.yaml', String inventoryPath = 'aws_ec2.yml') {
    node {
        stage('ansible-syntaxcheck') {
           // sh "ansible-playbook -i roletool/tests/aws_ec2.yml roletool/tests/test.yaml --syntax-check"
              sh "ansible-playbook -i ${inventoryPath} ${playbookPath} --syntax-check"
        }
    }
}
