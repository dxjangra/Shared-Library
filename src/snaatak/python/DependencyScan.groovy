package snaatak.python
def call() {
    stage('DependencyScan') {
        // Get the job name dynamically
        def jobName = env.JOB_NAME

        // Construct the workspace directory based on the job name
        def workspaceDir = "/var/lib/jenkins/workspace/${jobName}/dependency-check"

        // Construct the full path to the marker file indicating installation
        def markerFile = "${workspaceDir}/bin/dependency-check.sh"

        // Check if DependencyCheck is already marked as installed
        def dependencyCheckInstalled = fileExists(markerFile)
        echo "DependencyCheck Installed: ${dependencyCheckInstalled}"

        if (!dependencyCheckInstalled) {
            // DependencyCheck not marked as installed, so download and install it
            echo "Installing DependencyCheck..."
            sh "mkdir -p ${workspaceDir}/bin" // Create the bin directory if it doesn't exist
            sh "wget https://github.com/jeremylong/DependencyCheck/releases/download/v8.4.0/dependency-check-8.4.0-release.zip"
            sh "unzip dependency-check-8.4.0-release.zip -d ${workspaceDir}"

            // Create the marker file to indicate installation
            sh "touch ${markerFile}"

            // Cleanup: Remove the downloaded zip file
            sh 'rm dependency-check-8.4.0-release.zip'
        } else {
            echo "DependencyCheck is already installed. Skipping installation."
        }

        // Now run DependencyCheck
        dir(workspaceDir) {
            echo "Running DependencyCheck in ${workspaceDir}..."
            sh "chmod +x ./bin/dependency-check.sh"
            sh "./bin/dependency-check.sh --scan ${workspaceDir}"
        }
    }
}

// def call() {
//     stage('dependencyScan') {
//         def safetyInstalled = sh(script: 'command -v safety', returnStatus: true) == 0
//         if (!safetyInstalled) {
//             sh 'sudo pip install safety'
//         }
//         sh 'safety check'
        
//         // Check if vulnerabilities were found
//         def vulnerabilitiesFound = sh(script: 'safety check --output json', returnStatus: true) != 0

//         if (vulnerabilitiesFound) {
//             // Debug statement
//             echo "Vulnerabilities found. Prompting user for input."

//             // Prompt the user for input
//             def userInput = input(
//                 id: 'userInput',
//                 message: 'Vulnerabilities were found. Do you want to continue the pipeline?',
//                 parameters: [choice(name: 'CONTINUE_ABORT', choices: 'Continue\nAbort', description: 'Select an action')]
//             )

//             // Debug statement
//             echo "User input: ${userInput}"

//             // Check the user's choice
//             if (userInput == 'Abort') {
//                 error "Pipeline aborted due to vulnerabilities."
//                 currentBuild.result = 'ABORTED'
//             }
//         }
//     }
// }



// def call() {
//     stage('dependencyScan'){
//     def safetyInstalled = sh(script: 'command -v safety', returnStatus: true) == 0
//          if (!safetyInstalled) {
//              // sh 'sudo apt-get update'
//              sh 'sudo pip install safety'
//         }
//         // sh 'sudo apt update'
//         sh 'safety check'
//         sh 'safety check --output json'
//         sh 'safety check --output text'
//     }
// }

