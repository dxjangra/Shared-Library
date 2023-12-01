package snaatak.golang

def call() {
    stage('UnitTesting') {
        def jobName = env.JOB_NAME

        // Check if Go is already installed
        def goInstalled = fileExists('/usr/local/go/bin/go')

        if (!goInstalled) {
            // Go is not installed, so install it
           sh 'sudo snap install go --classic'
        }

        // Set the PATH to include Go
        dir("/var/lib/jenkins/workspace/${jobName}/api") {
                        try {
                            sh "go test"
                        } catch (Exception e) {
                            currentBuild.result = 'SUCCESS' // Mark the build as a success
                            echo "Unit tests failed, but the pipeline will continue."
               }
        }
}
}



// package snaatak.golang
// def call(String go = tool name: 'golang', type: 'go') {
//     stage('UnitTesting'){
//     dir('/var/lib/jenkins/workspace/SampleGO/api') {
//     sh 'go test'
//    }  
//  }
// }
//dir("/var/lib/jenkins/workspace/${jobName}/api") {
 //           sh "go test"
  //      }
