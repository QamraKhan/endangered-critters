## import libaries
import pandas as pd
import numpy as np
import os, sys
from tqdm import tqdm
import glob
import pickle

from keras import applications
from keras.preprocessing import image
from keras.models import Model
from keras import optimizers
from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten, Activation
from keras.layers import Conv2D, MaxPooling2D
from keras.layers.normalization import BatchNormalization
from keras.metrics import categorical_accuracy
from keras.preprocessing.image import ImageDataGenerator
from keras.callbacks import EarlyStopping
from keras.utils import to_categorical
from keras.preprocessing.image import ImageDataGenerator
from keras.callbacks import ModelCheckpoint
# from keras.applications.inception_v3 import InceptionV3, preprocess_input

from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten, Convolution2D, MaxPooling2D
from keras.callbacks import EarlyStopping
from keras.utils import to_categorical

from sklearn.model_selection import train_test_split


from keras.applications.vgg16 import VGG16
from keras.applications import xception
from keras.applications import inception_v3
# from keras.applications.vgg16 import preprocess_input, decode_predictions
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import log_loss, accuracy_score
import pickle

from keras.applications.resnet50 import ResNet50
from keras.applications.resnet50 import preprocess_input
# inception_resnet_v2.InceptionResNetV2

from keras.applications.xception import preprocess_input as preprocess_xception

from keras.models import model_from_json


##define VARIABLES

HEIGHT=400
WIDTH=400
VAL_SPLIT = 0.2 #Percentage of training examples used for validation

json_file = open('model_10_animal.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)
loaded_model._make_predict_function()
# load weights into new model
loaded_model.load_weights("model_10_animals.h5")
print("Loaded model from disk")

logreg = pickle.load(open('logreg_10_animals.model', 'rb'))

animals_list = ['buffalo',
 'chihuahua',
 'chimpanzee',
 'elephant',
 'giant+panda',
 'grizzly+bear',
 'hippopotamus',
 'horse',
 'lion',
 'tiger']


def read_img(img_path,size):
    img = image.load_img(img_path, target_size=size)
    img = image.img_to_array(img)
    return img

def predict(image_path):
    x_train = np.zeros((1, HEIGHT, WIDTH, 3), dtype='float32')
    x = read_img(image_path,(HEIGHT, WIDTH))
    x_train[0] = preprocess_xception(np.expand_dims(x.copy(), axis=0))
    
    test_x_bf = loaded_model.predict(x_train, batch_size=32, verbose=1)
    print (logreg.predict(test_x_bf)[0])
    return animals_list[logreg.predict(test_x_bf)[0]]

    
