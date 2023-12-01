// package snaatak.ansible

// def call(String playbookPath = 'roletool/tests/test.yaml', String inventoryPath = 'roletool/tests/inventory') {
//     stage('ansible-execution') {
//         sh "ansible-playbook -i ${inventoryPath} ${playbookPath}"
//     }
// }
package snaatak.ansible

def call(String playbookPath = 'roletool/tests/test.yaml', String inventoryPath = 'roletool/tests/aws_ec2.yml') {
    stage('ansible-execution') {
        def confirmation = input(            
            message: 'Do you want to execute the Ansible playbook?',
            parameters: [choice(choices: ['Yes', 'No'], description: 'Select Yes to execute the playbook', name: 'Confirmation')]
        )
        if (confirmation == 'Yes') {
            sh "ansible-playbook -i ${inventoryPath} ${playbookPath}"
        } else {
            echo "Ansible playbook execution skipped."
        }
    }
}

// package snaatak.ansible

// def call(String playbookPath = 'roletool/tests/test.yaml', String inventoryPath = 'roletool/tests/aws_ec2.yml') {
// // stage('ansible-execution') {
// //         input(            
// //             message: 'Do you want to execute the Ansible playbook?',
// //             parameters: [choice(choices: ['Yes', 'No'], description: 'Select Yes to execute the playbook', name: 'Confirmation')]
// //         )
//         script {
//             def confirmation = input('Confirmation')
//             if (confirmation != 'Yes') {
//             sh "ansible-playbook -i ${inventoryPath} ${playbookPath}"
//                 sh "ansible-playbook -i ${inventoryPath} ${playbookPath}"
//             } else {
//                 // sh "ansible-playbook -i ${inventoryPath} ${playbookPath}"
//             }
//         }
//     }

