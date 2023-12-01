package snaatak.java

def call() {
    stage('Static Analysis') {
        def jobName = env.JOB_NAME
        dir("/var/lib/jenkins/workspace/${jobName}") {
            def userInput // Declare userInput here

            // Run the static analysis step regardless of build status
            withSonarQubeEnv(installationName: 'sonar-server', credentialsId: 'sonar-token') {
                sh 'mvn verify sonar:sonar -Dtest=!com.opstree.microservice.salary.SalaryApplicationTests'
            }

            if (currentBuild.result == 'FAILURE') {
                // Prompt the user to decide whether to skip the static analysis
                userInput = input(
                    message: 'The build failed. Do you want to skip the static analysis? (y/n)',
                    parameters: [choice(name: 'Skip static analysis?', choices: 'y\nn', description: 'Choose "y" to skip the static analysis.')]
                )
                
                if (userInput == 'y') {
                    currentBuild.result = 'SUCCESS' // Mark the build as successful
                }
            }
        }
    }
}









// def call () {
    
//         stage('Static Analysis') {
//         def jobName = env.JOB_NAME
//          dir("/var/lib/jenkins/workspace/${jobName}"){
//             withSonarQubeEnv(installationName: 'sonar-server', credentialsId: 'sonar-token-gcp') {
//                 sh 'mvn verify sonar:sonar -Dtest=!com.opstree.microservice.salary.SalaryApplicationTests'
//             }
//         }
//     }
//  }

