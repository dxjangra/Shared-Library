package snaatak.java
def call() {
        stage('Dependency Scan') {
            def jobName = env.JOB_NAME
            dir("/var/lib/jenkins/workspace/${jobName}") {
                // Check if Maven is installed
                sh 'mvn --version || sudo apt install maven -y'
                sh 'sudo apt install openjdk-17-jdk -y'
                sh 'mvn dependency:resolve'
        
            }
       }
  }



// package snaatak.java

// def call () {
//     node {
//         stage('Dependency Scan') {
//             dir('/var/lib/jenkins/workspace/Sample_Java'){
//             // sh 'sudo apt install maven -y'
//             sh 'mvn dependency:resolve'
//         }
//     }
// }
// }
