package snaatak.template
import snaatak.common.*
import snaatak.python.*


def call(String repoUrl, String credentialsId, String branchName) {
    def clean = new CleanWorkSpace() 
    def clone = new Clone()
    def cred = new CredentialScanning()
    def licence = new LicenceScanning()
    def codecompile = new CodeCompile()
    def dependency = new DependencyScan()
    def statanalysis = new StaticAnalysis()
    def unittest = new UnitTesting()
  
    

    clean.call()
    clone.call(repoUrl, credentialsId, branchName)
    cred.call()
    licence.call()
    codecompile.call()
    dependency.call()
    statanalysis.call()
    unittest.call()

}
// package snaatak.template
// import snaatak.common.*
// import snaatak.python.*
// import snaatak.ami

// def call(String repoUrl, String credentialsId, String branchName, String amiName, String sourceAmi, String instanceType, String region, String sshUsername) {
//     def clean = new CleanWorkSpace() 
//     def clone = new Clone()
//     def cred = new CredentialScanning()
//     def licence = new LicenceScanning()
//     def codecompile = new CodeCompile()
//     def dependency = new DependencyScan()
//     def statanalysis = new StaticAnalysis()
//     def unittest = new UnitTesting()
//     def ami = new ami()

//     clean.call()
//     clone.call(repoUrl, credentialsId, branchName)
//     cred.call()
//     licence.call()
//     codecompile.call()
//     dependency.call()
//     statanalysis.call()
//     unittest.call()
//     ami.call(amiName, sourceAmi, instanceType, region, sshUsername)
// }
