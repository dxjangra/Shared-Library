package snaatak.golang
def call(){
stage('CodeBuild'){
       sh 'go build -o employee-api'
    }
}
