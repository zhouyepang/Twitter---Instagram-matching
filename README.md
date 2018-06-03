# Twitter---Instagram-matching

Java: folder - Features
This program generates feature values for positive instances and negative instances. The generated file can be used to construct machine learning dataset.

Jupyter Notebook: folder - /ipynb
match:  find link accounts for Twitter and Instagram, generate truth data.
tweet: modified from online scripts tweepy, collect tweets based on username.
model: fit the model with dataset provided in the folder, and use the model to predict then print out a list of scores (method: username-based identification).
stylext: modified from online scripts https://github.com/analyticascent/stylext (method: stylometry)   
  
Folder - /ipynb/csv
This folder contains a list of tweets datasets for stylometry.

Folder - /datasets
This folder contains a list of datasets with different ratios for username-based identification.

Folder - /username pairs
87637 positive name pairs (truth data), 350550 negative name pairs. 
