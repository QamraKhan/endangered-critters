import os
from flask import Flask, flash, request, redirect, url_for
from werkzeug.utils import secure_filename
from flask import send_from_directory
from animal_model import predict
from flask import Flask, session

UPLOAD_FOLDER = r'E:\ML\upload\uploads'
ALLOWED_EXTENSIONS = set(['txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'])

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app.secret_key = "anubhav"

def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/', methods=['GET', 'POST'])
def upload_file():
        
    file = request.files['file']
    # if user does not select file, browser also
    # submit an empty part without filename

    filename = secure_filename(file.filename)
    file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
    animal = predict(os.path.join(app.config['UPLOAD_FOLDER'], filename))
    return animal

@app.route("/imgs/<path:path>")
def images(path):
    return '35.229.42.247:5000'+url_for('static',filename=path+'.jpg')


@app.route('/uploads/<filename>')
def uploaded_file(filename):
    return send_from_directory(app.config['UPLOAD_FOLDER'],
                               filename)


if __name__ == '__main__':
    app.run(host='0.0.0.0')