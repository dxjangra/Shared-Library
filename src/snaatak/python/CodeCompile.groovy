package snaatak.python

def call() {
    stage('codeCompile'){
             
             sh 'sudo apt install software-properties-common -y'
             sh 'sudo add-apt-repository ppa:deadsnakes/ppa -y'
             sh 'sudo apt install python3.11 -y'
             sh 'sudo apt install python3-pip -y '
             sh 'python3 -m py_compile app.py'    
    }
   sh 'python3 -m py_compile app.py'
}
