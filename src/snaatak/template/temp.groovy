package snaatak.template

import snaatak.common.*
import snaatak.ami
def call(String repoUrl, String credentialsId, String branchName) {
    def clean = new CleanWorkSpace() 
    def clone = new Clone()
    def cred = new CredentialScanning()
    def licence = new LicenceScanning()
    // def ami = new ami()

    clean.call()
    clone.call(repoUrl, credentialsId, branchName)
    cred.call()
    licence.call()
    // ami.call()
 
}


















// def ami = new ami()
//    ami.call()
   // Now, call the notifications script from the common package
    // def notifications = new Notifications()
    // notifications.sendSuccessNotification("channel", "#439FE0", "teamDomain", "tokenCredentialId", "username", "replyTo", "subject", "to")
    // // or notifications.sendFailureNotification(...) if you need to send a failure notification
