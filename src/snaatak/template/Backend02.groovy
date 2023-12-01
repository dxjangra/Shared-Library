package snaatak.template

import snaatak.common.*
import snaatak.terraform.*

def call(String repoUrl, String credentialsId, String branchName, boolean RunClean = true) {
    if (RunClean) {
        def clean = new CleanWorkSpace() 
        clean.call()
    }
    
    def clone = new Clone()
    def cred = new CredentialScanning()
    def licence = new LicenceScanning()
    def Init = new InitTerra()
    def format = new Format()
    def syntax = new Syntax()
    def infracost = new Infracost()
    def dryrun = new DryRun()
    def apply = new Apply()
    def destroy = new Destroy()
    // def state = new State()

    clone.call(repoUrl, credentialsId, branchName)
    cred.call()
    licence.call()
    Init.call()
    format.call()
    syntax.call() 
    infracost.call()
    dryrun.call()
    
    stage('Terraform Ops') {
        script {
            if (params.What_do_you_want == 'Create') {
                apply.call()
                // state.call(sourceStateFile, destinationBucket, destinationPath)
            } else if (params.What_do_you_want == 'Destroy') {
                destroy.call()
            } else {
                echo "Unknown option for What_do_you_want: ${params.What_do_you_want}"
            }
        }
    }
}

parameters {
    choice(name: 'What_do_you_want', choices: ['Create', 'Destroy'], description: 'Select the environment')
    booleanParam(name: 'RunClean', defaultValue: true, description: 'Run the clean step')
}
