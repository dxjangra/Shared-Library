package snaatak.terraform

def call(String s3BucketName) {
    stage('Destroy') {
        // Destroy resources with Terraform
        sh 'terraform destroy --auto-approve'

        // Remove objects in the specified S3 bucket
        sh "aws s3 rm s3://${s3BucketName} --recursive"
    }
}

// package snaatak.terraform

// def call() {
//     stage('Destroy') {
//            sh 'terraform destroy --auto-approve'
//            sh 'aws s3 rm s3://snaatak-tfstates --recursive'
//     }
// }
