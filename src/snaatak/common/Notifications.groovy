package snaatak.common

def sendSuccessNotification(String channel, String color, String teamDomain, String tokenCredentialId, String username, String replyTo, String subject, String to) {
    def message = "JOB_NAME: ${env.JOB_NAME} BUILD_ID: ${env.BUILD_ID} WORKSPACE: ${env.WORKSPACE} Successful"
    sendNotification("Build Success", channel, color, message, teamDomain, tokenCredentialId, username, replyTo, subject, to)
}

def sendFailureNotification(String channel, String color, String teamDomain, String tokenCredentialId, String username, String replyTo, String subject, String to) {
    def message = "JOB_NAME: ${env.JOB_NAME} BUILD_ID: ${env.BUILD_ID} WORKSPACE: ${env.WORKSPACE} Unsuccessful"
    sendNotification("Build Failure", channel, color, message, teamDomain, tokenCredentialId, username, replyTo, subject, to)
}

private def sendNotification(String notificationType, String channel, String color, String message, String teamDomain, String tokenCredentialId, String username, String replyTo, String subject, String to) {
    slackSend(
        channel: channel,
        color: color,
        message: message,
        teamDomain: teamDomain,
        tokenCredentialId: tokenCredentialId,
        username: username
    )
    
    def emailSubject = "Build Status: $notificationType"
    def emailBody = "Build status: $notificationType"
    
    emailext(
        body: emailBody,
        subject: emailSubject,
        replyTo: replyTo,
        to: to,
        recipientProviders: [developers()]
    )
}

// package snaatak.common

// def sendSuccessNotification(String channel, String color, String message, String teamDomain, String tokenCredentialId, String username, String replyTo, String subject, String to) {
//     sendNotification("Build Success", channel, color, message, teamDomain, tokenCredentialId, username, replyTo, subject, to)
// }

// def sendFailureNotification(String channel, String color, String message, String teamDomain, String tokenCredentialId, String username, String replyTo, String subject, String to) {
//     sendNotification("Build Failure", channel, color, message, teamDomain, tokenCredentialId, username, replyTo, subject, to)
// }

// private def sendNotification(String notificationType, String channel, String color, String message, String teamDomain, String tokenCredentialId, String username, String replyTo, String subject, String to) {
//     slackSend(
//         channel: channel,
//         color: color,
//         message: message,
//         teamDomain: teamDomain,
//         tokenCredentialId: tokenCredentialId,
//         username: username
//     )
    
//     def emailSubject = "Build Status: $notificationType"
//     def emailBody = "Build status: $notificationType"
    
//     emailext(
//         body: emailBody,
//         subject: emailSubject,
//         replyTo: replyTo,
//         to: to,
//         recipientProviders: [developers()]
//     )
// }
