package snaatak.golang
def call() {
    archiveArtifacts artifacts: 'dependency-check/*.xml, dependency-check/*.html', allowEmptyArchive: true
    archiveArtifacts artifacts: '/*.json, /*.xml', allowEmptyArchive: true
}
