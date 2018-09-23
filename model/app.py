import os
from flask import Flask, flash, request, redirect, url_for
from werkzeug.utils import secure_filename
from flask import send_from_directory
from animal_model import predict
from flask import Flask, session
from database_animal import get_data, add_data
import ast

UPLOAD_FOLDER = r'/home/anubhavlandmark/endangered-critters/model/static'
#UPLOAD_FOLDER = r'E:\ML\upload\endangered-critters\model\static'
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
    temperature = request.headers['temperature']
    humidity = request.headers['humidity']
    filename = secure_filename(file.filename)
    file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
    animal = predict(os.path.join(app.config['UPLOAD_FOLDER'], filename))
    add_data(filename, '35.229.42.247:5000/static/' + filename, animal, humidity, temperature)
    return animal

@app.route("/imgs/<path:path>")
def images(path):
    return '35.229.42.247:5000/static/' + filename


@app.route('/uploads/<filename>')
def uploaded_file(filename):
    return send_from_directory(app.config['UPLOAD_FOLDER'],
                               filename)

@app.route("/get_data")
def all_data():
    return get_data()


if __name__ == '__main__':
    app.run(host='0.0.0.0')