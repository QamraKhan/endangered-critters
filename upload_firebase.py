
import sys
import requests
import firebase_admin
from firebase_admin import credentials
from firebase_admin import storage

#image_url = sys.argv[1] #we pass the url as an argument

cred = credentials.Certificate('/home/pi/Hackathon/crittercam-baa64-firebase-adminsdk-1dqny-82f60b08f8.json')
firebase_admin.initialize_app(cred, {
    'storageBucket': 'crittercam-baa64.appspot.com'
})
bucket = storage.bucket()

filename = "/home/pi/Hackathon/Photos/2018.09.22-183147.jpg"

#image_data = requests.get('/home/pi/Hackathon/Photos/2018.09.22-183147.jpg').content
blob = bucket.blob('2018.09.22-183147.jpg')
#blob.upload_from_string(
 #       image_data,
  #      content_type='image/jpg'
  #  )
#print(blob.public_url)
with open(filename, "rb") as fp:
        blob.upload_from_file(fp)

url = blob.public_url
