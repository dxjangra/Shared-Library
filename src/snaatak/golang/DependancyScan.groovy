package snaatak.golang

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
            sh "./bin/dependency-check.sh --scan /var/lib/jenkins/workspace/${JOB_NAME}"
        }
    }
}


// def call() {
//     stage('DependencyScan') {
//         def dependencyCheckDir = '/var/lib/jenkins/workspace/SampleGO/dependency-check'

//         // Check if DependencyCheck is already available
//         def dependencyCheckInstalled = fileExists("${dependencyCheckDir}/bin/dependency-check.sh")

//         if (!dependencyCheckInstalled) {
//             // DependencyCheck not installed, so download and install it
//             sh 'wget https://github.com/jeremylong/DependencyCheck/releases/download/v8.4.0/dependency-check-8.4.0-release.zip'
//             sh 'unzip dependency-check-8.4.0-release.zip'


//             // Cleanup: Remove the downloaded zip file
//             sh 'rm dependency-check-8.4.0-release.zip'
//         }

//         // Now run DependencyCheck
//         dir('/var/lib/jenkins/workspace/SampleGO/dependency-check') {
               

//             sh './bin/dependency-check.sh --scan /var/lib/jenkins/workspace/SampleGO'
//         }
//     }
// }




// def call() {
//     stage('DependancyScan'){
//        sh 'wget https://github.com/jeremylong/DependencyCheck/releases/download/v8.4.0/dependency-check-8.4.0-release.zip'
//        sh 'unzip dependency-check-8.4.0-release.zip'

//     dir('/var/lib/jenkins/workspace/SampleGO/dependency-check') {
//         sh './bin/dependency-check.sh --scan /var/lib/jenkins/workspace/SampleGO' 
//     }
//   }
// }                 
         
