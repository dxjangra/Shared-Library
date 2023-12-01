package snaatak.template

import snaatak.common.*
import snaatak.python.*
import snaatak.AttendanceAMI


def call(String repoUrl, String credentialsId, String branchName) {
    def clean = new CleanWorkSpace() 
    def clone = new Clone()
    def cred = new CredentialScanning()
    def licence = new LicenceScanning()
    def dependancy = new DependencyScan()
    def codecompile = new CodeCompile()
    def dependency = new DependencyScan()
    def statanalysis = new StaticAnalysis()
    def unittest = new UnitTesting()
    def ami = new AttendanceAMI()
  
    

    clean.call()
    clone.call(repoUrl, credentialsId, branchName)
    cred.call()
    licence.call()
    codecompile.call()
    dependency.call()
    statanalysis.call()
    unittest.call()
    ami.call()
  
}
