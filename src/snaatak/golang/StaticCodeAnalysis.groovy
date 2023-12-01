package snaatak.golang
def call(String GOSEC_PATH = '/usr/local/bin/gosec') {
    stage('StaticCodeAnalysis') {
        def jobName = env.JOB_NAME

        dir("/var/lib/jenkins/workspace/${jobName}") {
            // Ensure that gosec is installed
            if (!fileExists(GOSEC_PATH)) {
                echo "gosec is not installed at $GOSEC_PATH"
                sh 'sudo snap install gosec'
            } else {
                // Run gosec and capture the exit code
                def gosecExitCode = sh(returnStatus: true, script: "${GOSEC_PATH} .")

                // Check the exit code for issues
                if (gosecExitCode == 0) {
                    echo "No Gosec issues found. Continuing the pipeline."
                } else {
                    // Log a message and skip the step
                    echo "Gosec found issues, but we will continue the pipeline."

                    // Set a variable to skip the next stage
                    env.SKIP_GOSEC = true
                }
            }
        }
    }
}






// def call( String GOSEC_PATH = '/usr/local/bin/gosec') {
//     stage('StaticCodeAnalysis') {
//        def jobName = env.JOB_NAME
    
//         dir('/var/lib/jenkins/workspace/${jobName}') {
//         // Ensure that gosec is installed
//         if (!fileExists(GOSEC_PATH)) {
//             echo "gosec is not installed at $GOSEC_PATH"
//             sh 'sudo snap install gosec'
//         } else {
//             // Run gosec and capture the exit code
//             def gosecExitCode = sh(returnStatus: true, script: "${GOSEC_PATH} .")

//             // Check the exit code for success (0)
//             if (gosecExitCode == 0) {
//                 echo "No Gosec issues found. Continuing the pipeline."
//             } else {
//                 // Prompt the user for input
//                 def userInput = input(
//                     id: 'userInput',
//                     message: 'Gosec found issues. Do you want to continue the pipeline?',
//                     parameters: [choice(name: 'CONTINUE_ABORT', choices: 'Continue\nAbort', description: 'Select an action')]
//                 )

//                 // Check the user's choice
//                 if (userInput == 'Continue') {
//                     echo "User chose to continue the pipeline despite Gosec issues."
//                 } else {
//                     error "User chose to abort the pipeline due to Gosec issues."
//                     currentBuild.result = 'ABORTED'
//                     error("Pipeline aborted due to Gosec issues.")
//                 }
//             }
//         }
//     }
//  }
// }
