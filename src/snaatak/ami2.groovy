package snaatak

def call() {
    stage('AMI') {
        // Check if Packer is already installed
        def packerInstalled = sh(script: 'which packer', returnStatus: true)

        if (packerInstalled == 0) {
            echo 'Packer is already installed.'
        } else {
            // Install Packer dependencies
            sh 'sudo apt -y install apt-transport-https ca-certificates curl software-properties-common'
            sh 'curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo gpg --dearmor -o /etc/apt/trusted.gpg.d/hashicorp.gpg'
            sh 'sudo apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com $(lsb_release -cs) main" -y'
            sh 'sudo apt update && sudo apt install packer'
        }

        // Define your Packer configuration as a multi-line string
        def packerConfig = '''
                   {
              "builders": [
                {
                  "type": "amazon-ebs",
                  "region": "us-east-1",
                  "source_ami": "ami-0123456789abcdef0",
                  "instance_type": "t2.micro",
                  "ssh_username": "ubuntu"
                }
              ],
              "provisioners": [
                {
                  "type": "file",
                  "source": "provision.sh",
                  "destination": "/tmp/provision.sh"
                },
                {
                  "type": "shell",
                  "inline": [
                    "chmod +x /tmp/provision.sh",
                    "/tmp/provision.sh"
                  ]
                }
              ]
            }

        '''

       // Write the Packer configuration to a file (e.g., aws-ami.pkr.hcl)
        writeFile file: 'aws-ami.json', text: packerConfig

        // Initialize the Packer project
        sh 'packer init .'

        // Validate the Packer template
        sh 'packer validate aws-ami.pkr.hcl'

        // Build the AMI using Packer
        sh 'packer build aws-ami.pkr.hcl'
    }
}

