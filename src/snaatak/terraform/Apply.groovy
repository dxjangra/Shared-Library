package snaatak.terraform

def call() {
    stage('Apply') {
           sh 'terraform apply --auto-approve'
    }
}


// package snaatak.terraform

// def call() {
//     stage('Apply') {
//         if (params.What_do_you_want == 'Create') {
//             sh 'terraform apply --auto-approve'
//         } else {
//             echo "Skipping terraform apply because What_do_you_want is not 'Create'."
//         }
//     }
// }


