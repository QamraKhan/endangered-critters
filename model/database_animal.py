import pandas as pd
import numpy as np
import ast
import json

def add_data(image_name, image_url, classification, humidity, temperature):
    data = pd.read_csv('data.csv')
    new_data = pd.DataFrame([[image_name, image_url, classification, humidity, temperature]], columns = data.columns.tolist())
    data = pd.concat([data, new_data])
    data.to_csv('data.csv', index=False)
    
    
def get_data():
    data = pd.read_csv('data.csv')
    return json.dumps(ast.literal_eval(data.to_json(orient='records')))