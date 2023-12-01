package snaatak.template

import snaatak.common.*
import snaatak.terraform.*

def call(
    String repoUrl,
    String credentialsId,
    String branchName,
    String sourceStateFile,
    String destinationBucket,
    String destinationPath,
    String s3BucketName,
    boolean RunClean = true,
    boolean RunClone = true, // New parameter for controlling the Clone stage
    boolean RunCredentialScanning = true, // Add more parameters for other stages as needed
    boolean RunLicenceScanning = true,
    boolean RunInitTerra = true,
    boolean RunFormat = true,
    boolean RunSyntax = true,
    boolean RunInfracost = true,
    boolean RunDryRun = true,
    boolean RunApply = true,
    boolean RunDestroyBackend = true,
    boolean RunState = true
) {
    if (RunClean) {
        def clean = new CleanWorkSpace()
        clean.call()
    }

    if (RunClone) {
        def clone = new Clone()
        clone.call(repoUrl, credentialsId, branchName)
    }

    if (RunCredentialScanning) {
        def cred = new CredentialScanning()
        cred.call()
    }

    if (RunLicenceScanning) {
        def licence = new LicenceScanning()
        licence.call()
    }

    if (RunInitTerra) {
        def Init = new InitTerra()
        Init.call()
    }

    if (RunFormat) {
        def format = new Format()
        format.call()
    }

    if (RunSyntax) {
        def syntax = new Syntax()
        syntax.call()
    }

    if (RunInfracost) {
        def infracost = new Infracost()
        infracost.call()
    }

    if (RunDryRun) {
        def dryrun = new DryRun()
        dryrun.call()
    }

    stage('Terraform Ops') {
        script {
            if (params.What_do_you_want == 'Create' && RunApply) {
                def apply = new Apply()
                apply.call()
                if (RunState) {
                    def state = new State()
                    state.call(sourceStateFile, destinationBucket, destinationPath)
                }
            } else if (params.What_do_you_want == 'Destroy' && RunDestroyBackend) {
                def destroy = new DestroyBackend()
                destroy.call(s3BucketName)
            } else {
                echo "Unknown option for What_do_you_want: ${params.What_do_you_want}"
            }
        }
    }
}

parameters {
    choice(name: 'What_do_you_want', choices: ['Create', 'Destroy'], description: 'Select the environment')
    booleanParam(name: 'RunClean', defaultValue: true, description: 'Run the clean step')
    booleanParam(name: 'RunClone', defaultValue: true, description: 'Run the clone step')
    booleanParam(name: 'RunCredentialScanning', defaultValue: true, description: 'Run credential scanning')
    booleanParam(name: 'RunLicenceScanning', defaultValue: true, description: 'Run license scanning')
    booleanParam(name: 'RunInitTerra', defaultValue: true, description: 'Run Terraform initialization')
    booleanParam(name: 'RunFormat', defaultValue: true, description: 'Run Terraform formatting')
    booleanParam(name: 'RunSyntax', defaultValue: true, description: 'Run Terraform syntax checking')
    booleanParam(name: 'RunInfracost', defaultValue: true, description: 'Run infrastructure cost estimation')
    booleanParam(name: 'RunDryRun', defaultValue: true, description: 'Run Terraform dry run')
    booleanParam(name: 'RunApply', defaultValue: true, description: 'Run Terraform apply')
    booleanParam(name: 'RunDestroyBackend', defaultValue: true, description: 'Run backend destruction')
    booleanParam(name: 'RunState', defaultValue: true, description: 'Run state management')
}

// package snaatak.template

// import snaatak.common.*
// import snaatak.terraform.*

// def call(String repoUrl, String credentialsId, String branchName, String sourceStateFile, String destinationBucket, String destinationPath, String s3BucketName, boolean RunClean = true) {
//     if (RunClean) {
//         def clean = new CleanWorkSpace() 
//         clean.call()
//     }
    
//     def clone = new Clone()
//     def cred = new CredentialScanning()
//     def licence = new LicenceScanning()
//     def Init = new InitTerra()
//     def format = new Format()
//     def syntax = new Syntax()
//     def infracost = new Infracost()
//     def dryrun = new DryRun()
//     def apply = new Apply()
//     def destroy = new DestroyBackend()
//     def state = new State()

//     clone.call(repoUrl, credentialsId, branchName)
//     cred.call()
//     licence.call()
//     Init.call()
//     format.call()
//     syntax.call() 
//     infracost.call()
//     dryrun.call()
    
//     stage('Terraform Ops') {
//         script {
//             if (params.What_do_you_want == 'Create') {
//                 apply.call()
//                 state.call(sourceStateFile, destinationBucket, destinationPath)
//             } else if (params.What_do_you_want == 'Destroy') {
//                 destroy.call(s3BucketName)
//             } else {
//                 echo "Unknown option for What_do_you_want: ${params.What_do_you_want}"
//             }
//         }
//     }
// }

// parameters {
//     choice(name: 'What_do_you_want', choices: ['Create', 'Destroy'], description: 'Select the environment')
//     booleanParam(name: 'RunClean', defaultValue: true, description: 'Run the clean step')
// }

// package snaatak.template

// import snaatak.common.*
// import snaatak.terraform.*

// def call(String repoUrl, String credentialsId, String branchName, String sourceStateFile, String destinationBucket, String destinationPath, String s3BucketName) {
//     def clean = new CleanWorkSpace() 
//     def clone = new Clone()
//     def cred = new CredentialScanning()
//     def licence = new LicenceScanning()
//     def Init = new InitTerra()
//     def format = new Format()
//     def syntax = new Syntax()
//     def infracost = new Infracost()
//     def dryrun = new DryRun()
//     def apply = new Apply()
//     def destroy = new DestroyBackend()
//     def state = new State() // Fixed the class name

//     clean.call()
//     clone.call(repoUrl, credentialsId, branchName)
//     cred.call()
//     licence.call()
//     Init.call()
//     format.call()
//     syntax.call() 
//     infracost.call()
//     dryrun.call()
    
//     stage('Terraform Ops') {
//         script {
//             if (params.What_do_you_want == 'Create') {
//                 apply.call()
//                 state.call(sourceStateFile, destinationBucket, destinationPath)
//             } else if (params.What_do_you_want == 'Destroy') {
//                 destroy.call(s3BucketName) // Use the existing destroy variable
//             } else {
//                 echo "Unknown option for What_do_you_want: ${params.What_do_you_want}"
//             }
//         }
//     }
    
//     // Correct way to call the method without specifying parameter types
//     // state.call(sourceStateFile, destinationBucket, destinationPath)
// }

// parameters {
//     choice(name: 'What_do_you_want', choices: ['Create', 'Destroy'], description: 'Select the environment')
// }

// // package snaatak.template

// // import snaatak.common.*
// // import snaatak.terraform.*

// // def call(String repoUrl, String credentialsId, String branchName, String sourceStateFile, String destinationBucket, String destinationPath) {
// //     def clean = new CleanWorkSpace() 
// //     def clone = new Clone()
// //     def cred = new CredentialScanning()
// //     def licence = new LicenceScanning()
// //     def Init = new InitTerra()
// //     def format = new Format()
// //     def syntax = new Syntax()
// //     def infracost = new Infracost()
// //     def dryrun = new DryRun()
// //     def apply = new Apply()
// //     def destroy = new Destroy()
// //     def state = new state()

// //     clean.call()
// //     clone.call(repoUrl, credentialsId, branchName)
// //     cred.call()
// //     licence.call()
// //     Init.call()
// //     format.call()
// //     syntax.call() 
// //     infracost.call()
// //     dryrun.call()
    
// //     stage('Terraform Ops') {
// //         script {
// //             if (params.What_do_you_want == 'Create') {
// //                 apply.call()
// //             } else if (params.What_do_you_want == 'Destroy') {
// //                 destroy.call() // Use the existing destroy variable
// //             } else {
// //                 echo "Unknown option for What_do_you_want: ${params.What_do_you_want}"
// //             }
// //         }
// //     }
// //     state.call(String sourceStateFile, String destinationBucket, String destinationPath)
// // }

// // parameters {
// //     choice(name: 'What_do_you_want', choices: ['Create', 'Destroy'], description: 'Select the environment')
// // }
