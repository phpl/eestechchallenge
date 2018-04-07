from cassandra.cluster import Cluster
from sklearn import datasets
from sklearn import metrics
import pandas as pd
from sklearn.svm import SVC
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.grid_search import GridSearchCV
import pandas as pd

from sklearn.model_selection import KFold
from sklearn.feature_extraction.text import CountVectorizer

cluster = Cluster(['localhost'])
session = cluster.connect('keyspace_name')

data = {'id': 0, 'col2': 0,'username_id': 0,'created_date': 0,'folowers_count': 0,'country': 0,'tweet': 0,'language': 0}

id = []
username_id = []
created_date= []
folowers_count= []
country = []
tweet = []
language = []

rows = session.execute('SELECT * FROM keyspace_name.sample_table;')
for user_row in rows:
    username_id.append(user_row.id)
    created_date.append(user_row.created_date)
    folowers_count.append(user_row.folowers_count)
    country.append(user_row.country)
    tweet.append(user_row.tweet)
    language.append(user_row.language)

sales = [('username_id', username_id,
         ('created_date', created_date),
         ('folowers_count', folowers_count),
         ('country', country),
          'tweet', tweet),
          'language', language]

df = pd.DataFrame({'username_id':username_id,
                   'created_date':created_date,'folowers_count':folowers_count,'country':country,'tweet':tweet,'language':language})

print(df['language'].value_counts())
print(df['country'].value_counts())
#print(df['tweet'][56])

import matplotlib.pyplot as plt
import pandas as pd

prob = df['language'].value_counts(normalize=True)
threshold = 0.02
mask = prob > threshold
tail_prob = prob.loc[~mask].sum()
prob = prob.loc[mask]
prob['other'] = tail_prob
prob.plot(kind='bar')
plt.xticks(rotation=25)
plt.show()
