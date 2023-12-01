package snaatak.python

def call() {
    stage('staticAnalysis') {
        def pylintInstalled = sh(script: 'command -v pylint', returnStatus: true) == 0
        if (!pylintInstalled) {
            sh '''
                pip install flasgger                        # For swagger documentation for flask APIs.
                pip install pylint                            # Install pylint to analyze and check your Python code.
                pip3 install poetry gunicorn                 # Install gunicorn for serving web applications using pip3.
                pip install psycopg2-binary                # Allowing you to connect and interact with PostgreSQL database.
                sudo apt-get install -y libpq-dev             # Install the development files for the PostgreSQL database client library (libpq).
                pip install python-json-logger             # To allow formatting log messages as JSON objects.
                pip install Flask                             # To create web-based projects with Python, install Flask.
                pip install Flask-Caching                    # To provide caching capabilities to this application.
                sudo apt-get install -y imagemagick-6.q16     # To install ImageMagick-6.q16.
                pip install peewee                         # To install the Peewee library.
                pip install voluptuous                     # To install the Voluptuous library.
                pip install prometheus-flask-exporter       # To install the Prometheus Flask Exporter library.
            '''
        }
        
        sh 'python3 --version'

        sh 'python3 -m pylint app.py'
        // sh 'pylint app.py'
    }
}

















// package snaatak.python

// def call() {
//     stage('staticAnalysis'){
//     def pylintInstalled = sh(script: 'command -v pylint', returnStatus: true) == 0
//          if (!pylintInstalled) {
//              sh 'sudo apt-get update'
//              sh '''
//                  pip install flasgger                        # For swagger documentation for flask APIs.
//                 sudo apt install pylint                    # Install pylint to analyze and check your python code
//                 pip3 install poetry gunicorn                 # Install gunicorn for serving web applications usingpip3
//                 pip install psycopg2-binary                # Allowing you to connect and interact with PostgreSQL database
//                 sudo apt install libpq-dev                  # Install the development files for the PostgreSQL database client library (libpq).
//                 pip install python-json-logger             # To allow format log messages as JSON objects
//                 pip install Flask                         # To create web-based projects with Python install Flask
//                 pip install Flask-Caching                    # To provide caching capabilities to this application.
//                 sudo apt install imagemagick-6.q16           # To install imagemagic-6.q16
//                 pip install peewee                         # To install the Peewee library
//                 pip install voluptuous                     # To install the Voluptuous library,
//                 pip install prometheus-flask-exporter       # To install the Prometheus Flask Exporter library.
//             '''
             
//              sh 'pip install pylint'
   
//              // sh 'sudo apt install -y pylint'
//              sh 'pylint app.py'

             
//         }
        
//     }
// } 
// 
