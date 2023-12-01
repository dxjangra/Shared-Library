package snaatak.java

def call () {
     stage('Compilation') {
            def jobName = env.JOB_NAME
            dir("/var/lib/jenkins/workspace/${jobName}"){
            sh 'mvn compile'
        }
    }
}

