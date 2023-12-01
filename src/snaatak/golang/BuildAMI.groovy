package snaatak.golang
def call() {
    stage('GenerateAMI') {
        def jobName = env.JOB_NAME

        // Check if Packer is already installed
        def packerInstalled = fileExists('/usr/local/bin/packer')

        if (!packerInstalled) {
            // Packer is not installed, so install it
            sh 'wget -O packer.zip https://releases.hashicorp.com/packer/1.7.0/packer_1.7.0_linux_amd64.zip'
            sh 'sudo apt install unzip'
            sh 'sudo unzip packer.zip -d /usr/local/bin'
            sh 'packer --version'
        }

        // Now, Packer is installed and available

        dir("/var/lib/jenkins/workspace/${jobName}") {
            // Use the packerConfigDirectory argument to build the AMI
            sh 'packer --version'
            sh '/usr/local/bin/packer init .'
            sh 'packer build packerConfig.pkr.hcl'
        }
    }
}
























// def call(packerConfig) {
//     stage('GenerateAMI') {
//         def jobName = env.JOB_NAME

//         // Check if Packer is already installed
//         def packerInstalled = fileExists('/usr/local/bin/packer')

//         if (!packerInstalled) {
//             // Packer is not installed, so install it
//             sh 'wget -O packer.zip https://releases.hashicorp.com/packer/1.7.0/packer_1.7.0_linux_amd64.zip'
//             sh 'sudo unzip packer.zip -d /usr/local/bin'
//         }

//         // Now, Packer is installed and available

//         dir("/var/lib/jenkins/workspace/${jobName}") {
//             // Use the packerConfig argument to build the AMI
//             sh "packer build ${packerConfig}"
//         }
//     }
// }






