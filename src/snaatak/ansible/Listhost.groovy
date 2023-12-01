package snaatak.ansible

def call(String playbookPath = 'playbook.yml', String inventoryPath = 'aws_ec2.yml') {
    stage('List Hosts and Tasks') {
        script {
            sh 'sudo apt update'
            sh 'sudo apt install python3-pip -y'
            sh 'sudo pip install boto3 botocore'
            sh 'sudo pip3 install ansible==2.9.6'
            // sh 'sudo apt install -y software-properties-common'
            // sh 'sudo add-apt-repository ppa:deadsnakes/ppa -y'
            // sh 'sudo apt install -y python3.11 python3-pip'

            sh "ansible-playbook -i ${inventoryPath} ${playbookPath} --list-hosts"
            sh "ansible-playbook -i ${inventoryPath} ${playbookPath} --list-tasks"
        }
    }
}
