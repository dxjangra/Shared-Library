package snaatak.template

import snaatak.common.*
import snaatak.java.*
import snaatak.SalaryAMI


def call(String repoUrl, String credentialsId, String branchName) {
    def clean = new CleanWorkSpace() 
    def clone = new Clone()
    def cred = new CredentialScanning()
    def licence = new LicenceScanning()
    def dependancy = new dependencyScan()
    def compile = new codeCompile()
    def codeanalysis = new staticAnalysis()
    def unittesting = new unitTesting()
    def codebuild = new mavenBuild()
    def ami = new SalaryAMI()
    
    clean.call()
    clone.call(repoUrl, credentialsId, branchName)
    cred.call()
    licence.call()
    dependancy.call()
    compile.call()
    codeanalysis.call()
    unittesting.call()
    codebuild.call()
    ami.call()
  
}
