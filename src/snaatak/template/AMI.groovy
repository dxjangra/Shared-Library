package snaatak.template

import snaatak.common.*
import snaatak.AMI
def call(String repoUrl, String credentialsId, String branchName) {
    def clean = new CleanWorkSpace() 
    def clone = new Clone()
    def cred = new CredentialScanning()
    def licence = new LicenceScanning()
    def ami = new AMI()

    clean.call()
    clone.call(repoUrl, credentialsId, branchName)
    cred.call()
    licence.call()
    ami.call()
 
}
