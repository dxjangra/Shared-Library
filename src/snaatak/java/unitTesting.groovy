package snaatak.java

def call () {
     stage('Unit Testing') {
         def jobName = env.JOB_NAME
         dir("/var/lib/jenkins/workspace/${jobName}"){
            sh 'mvn test -Dtest=!com.opstree.microservice.salary.SalaryApplicationTests'
            junit '**/target/surefire-reports/TEST-*.xml'
            archive 'target/*.jar'
        }
    }
}

