package snaatak.template

import snaatak.common.*
import snaatak.golang.*



def call(String repoUrl, String credentialsId, String branchName) {
    
    def clean = new CleanWorkSpace() 
    def clone = new Clone()
    def cred = new CredentialScanning()
    def licence = new LicenceScanning()
    def dependancy = new DependancyScan()
    def codeanalysis = new StaticCodeAnalysis()
    def unittesting = new UnitTesting()
    def codebuild = new CodeBuild()
    def buildami    = new BuildAMI()
    
    clean.call()
    clone.call(repoUrl, credentialsId, branchName)
    cred.call()
    licence.call()
    dependancy.call()
    codeanalysis.call()
    unittesting.call()
    codebuild.call()  
    buildami.call()
   
}
// def call() {
    
   
// }
