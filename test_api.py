import requests

r = requests.post('http://35.229.42.247:5000', files = {"file": open("/home/pi/Hackathon/Photos/20180923011628.jpg", "rb")})
