package snaatak.template

import snaatak.common.*
import snaatak.ansible.*

def call(String repoUrl, String credentialsId, String branchName, String inventoryPath, String playbookPath) {
    def clean = new CleanWorkSpace() 
    def clone = new Clone()
    def dryrun = new DryRun()
    def host = new Listhost()
    def lint = new Linting()
    def cred = new CredentialScanning()
    def licence = new LicenceScanning()

    clean.call()
    clone.call(repoUrl, credentialsId, branchName)
    cred.call()
    host.call(playbookPath)
    lint.call(playbookPath)
    dryrun.call(playbookPath)
   
    
}
