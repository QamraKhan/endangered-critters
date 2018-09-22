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

json_file = open('model.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)
# load weights into new model
loaded_model.load_weights("model.h5")
print("Loaded model from disk")

logreg = pickle.load(open('abc.model', 'rb'))

dogs_list = ['affenpinscher',
 'afghan_hound',
 'african_hunting_dog',
 'airedale',
 'american_staffordshire_terrier',
 'appenzeller',
 'australian_terrier',
 'basenji',
 'basset',
 'beagle',
 'bedlington_terrier',
 'bernese_mountain_dog',
 'black-and-tan_coonhound',
 'blenheim_spaniel',
 'bloodhound',
 'bluetick',
 'border_collie',
 'border_terrier',
 'borzoi',
 'boston_bull',
 'bouvier_des_flandres',
 'boxer',
 'brabancon_griffon',
 'briard',
 'brittany_spaniel',
 'bull_mastiff',
 'cairn',
 'cardigan',
 'chesapeake_bay_retriever',
 'chihuahua',
 'chow',
 'clumber',
 'cocker_spaniel',
 'collie',
 'curly-coated_retriever',
 'dandie_dinmont',
 'dhole',
 'dingo',
 'doberman',
 'english_foxhound',
 'english_setter',
 'english_springer',
 'entlebucher',
 'eskimo_dog',
 'flat-coated_retriever',
 'french_bulldog',
 'german_shepherd',
 'german_short-haired_pointer',
 'giant_schnauzer',
 'golden_retriever',
 'gordon_setter',
 'great_dane',
 'great_pyrenees',
 'greater_swiss_mountain_dog',
 'groenendael',
 'ibizan_hound',
 'irish_setter',
 'irish_terrier',
 'irish_water_spaniel',
 'irish_wolfhound',
 'italian_greyhound',
 'japanese_spaniel',
 'keeshond',
 'kelpie',
 'kerry_blue_terrier',
 'komondor',
 'kuvasz',
 'labrador_retriever',
 'lakeland_terrier',
 'leonberg',
 'lhasa',
 'malamute',
 'malinois',
 'maltese_dog',
 'mexican_hairless',
 'miniature_pinscher',
 'miniature_poodle',
 'miniature_schnauzer',
 'newfoundland',
 'norfolk_terrier',
 'norwegian_elkhound',
 'norwich_terrier',
 'old_english_sheepdog',
 'otterhound',
 'papillon',
 'pekinese',
 'pembroke',
 'pomeranian',
 'pug',
 'redbone',
 'rhodesian_ridgeback',
 'rottweiler',
 'saint_bernard',
 'saluki',
 'samoyed',
 'schipperke',
 'scotch_terrier',
 'scottish_deerhound',
 'sealyham_terrier',
 'shetland_sheepdog',
 'shih-tzu',
 'siberian_husky',
 'silky_terrier',
 'soft-coated_wheaten_terrier',
 'staffordshire_bullterrier',
 'standard_poodle',
 'standard_schnauzer',
 'sussex_spaniel',
 'tibetan_mastiff',
 'tibetan_terrier',
 'toy_poodle',
 'toy_terrier',
 'vizsla',
 'walker_hound',
 'weimaraner',
 'welsh_springer_spaniel',
 'west_highland_white_terrier',
 'whippet',
 'wire-haired_fox_terrier',
 'yorkshire_terrier']


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
    return dogs_list[logreg.predict(test_x_bf)[0]]

    
