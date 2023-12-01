package snaatak.terraform

def call(String sourceStateFile, String destinationBucket, String destinationPath) {
    stage('StateUpdate') {
        sh "aws s3 cp ${sourceStateFile} s3://${destinationBucket}/${destinationPath}"
    }
}

// package snaatak.terraform

// def call() {
//     stage('StateUpdate') {
//            sh 'aws s3 cp terraform.tfstate s3://snaatak-project-tfstate-storage/backend-pipeline/'
//     }
// }
