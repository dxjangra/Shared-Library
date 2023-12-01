package snaatak.template

import snaatak.common.*
import snaatak.terraform.*

def call(String repoUrl, String credentialsId, String branchName) {
    def clean = new CleanWorkSpace() 
    def clone = new Clone()
    def cred = new CredentialScanning()
    def licence = new LicenceScanning()
    def Init = new InitTerra()
    def format = new Format()
    def syntax = new Syntax()
    def infracost = new Infracost()
    def terralint = new TerraLint()
    def dryrun = new DryRun()
    def apply = new Apply()
    def destroy = new Destroy()

    // Default build result to SUCCESS
    currentBuild.result = 'SUCCESS'

    clean.call()
    clone.call(repoUrl, credentialsId, branchName)
    cred.call()
    licence.call()
    Init.call()
    format.call()
    syntax.call() 
    infracost.call()

    def tflintSuccess = false

    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
        terralint.call()
        tflint.call()
        tflintSuccess = true  // Set to true if tflint stage completes without errors
        dryrun.call()
    }

    stage('Terraform Ops') {
        script {
            if (!tflintSuccess) {
                // tflint stage failed, ask for approval to continue
                def approvalResult = input(id: 'user-input', message: "The 'tflint' stage failed. Do you want to continue the pipeline?", submitter: 'admin', parameters: [[$class: 'BooleanParameterDefinition', defaultValue: false, description: 'Continue or Abort', name: 'CONTINUE']])
                if (!approvalResult) {
                    // Manually set the build result to ABORTED
                    currentBuild.result = 'ABORTED'
                    echo "Pipeline aborted by user."
                    return
                }
            }

            // Continue with other stages
            if (params.What_do_you_want == 'Create') {
                input("Approve the 'Apply' stage?")  // Pause for manual approval
                apply.call()
            } else if (params.What_do_you_want == 'Destroy') {
                input("Approve the 'Destroy' stage?")  // Pause for manual approval
                destroy.call()
            } else {
                echo "Unknown option for What_do_you_want: ${params.What_do_you_want}"
            }
        }
    }

    // Explicitly set build result based on the success or failure of the pipeline
    if (currentBuild.result == null) {
        echo "Setting build result to SUCCESS"
        currentBuild.result = 'SUCCESS'
    }
}
parameters {
    choice(name: 'What_do_you_want', choices: ['Create', 'Destroy'], description: 'Select the environment')
}
