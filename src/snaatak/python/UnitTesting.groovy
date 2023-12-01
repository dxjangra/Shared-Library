package snaatak.python

def call() {
    stage('unitTesting'){
    def pytestInstalled = sh(script: 'command -v pytest', returnStatus: true) == 0
         if (!pytestInstalled) {
             // sh 'sudo apt-get update'
             sh 'sudo apt install software-properties-common'
             sh 'sudo add-apt-repository ppa:deadsnakes/ppa -y'
             sh 'sudo apt install python3.11 -y'
             sh 'sudo apt install python3-pip -y '
             sh 'sudo pip install pytest'
             
        }
            try {      // Run pytest
                        sh 'python3 -m pytest'
                 }
            catch (Exception e) {
                        // Handle the error and prompt the user for input
                        def userInput = input(
                            id: 'userInput',
                            message: 'An error occurred. Do you want to continue the pipeline? (yes/no)',
                            parameters: [choice(name: 'CONTINUE_ABORT', choices: 'yes\nno', description: 'Select an action')]
                        )

                        if (userInput == 'no') {
                            error "Pipeline aborted due to error."
                            currentBuild.result = 'ABORTED'
                        }
                    }
        
    }
}
