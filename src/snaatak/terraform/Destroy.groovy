package snaatak.terraform

def call() {
    stage('Destroy') {
           sh 'terraform destroy --auto-approve'
    }
}
// package snaatak.terraform

// def call() {
//     stage('Destroy') {
//         if (params.What_do_you_want == 'Destroy') {
//             sh 'terraform destroy --auto-approve'
//         } else {
//             echo "Skipping terraform destroy because What_do_you_want is not 'Destroy'."
//         }
//     }
// }

