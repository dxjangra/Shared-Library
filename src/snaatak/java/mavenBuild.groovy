package snaatak.java

def call () {
       stage('Build') {
        def jobName = env.JOB_NAME
         dir("/var/lib/jenkins/workspace/${jobName}"){
            sh 'mvn clean package -DskipTests'
        }
    }
}

