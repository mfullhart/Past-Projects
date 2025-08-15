"""
Iris class prediction

mfullhart20@georgefox.edu
"""

import matplotlib.pyplot as plt
import pandas as pd
import sklearn.datasets
import sklearn.model_selection
import sklearn.svm

# load iris dataset as pandas DataFrame
iris_dataset = sklearn.datasets.load_iris(as_frame=True)
data = iris_dataset['data']
data['target'] = iris_dataset['target']
data['target_name'] = [iris_dataset['target_names'][i] for i in data['target']]

print()

# TODO implement script

# Dictionary to convert target number to corresponding color
class_colors = {0: 'red', 1: 'green', 2: 'blue'}

# Use dictionary to convert the target numbers to colors for each row in the data set
colors = [class_colors[target] for target in data['target']]

# Scatter plot using the colors
data.plot.scatter('sepal length (cm)', 'sepal width (cm)', c=colors)

# x-y scatter plot of petal length vs petal width
data.plot.scatter('petal length (cm)', 'petal width (cm)', c=colors)
plt.show()


# Lab part 2

# Split iris data into training and testing sets
train_data, test_data = (sklearn.model_selection.train_test_split
                         (data, test_size=0.1, shuffle=True, stratify=data['target_name']))

# Verify that the training and testing sets are stratified the same as the entire data set
print(data['target_name'].value_counts())
print(train_data['target_name'].value_counts())
print(test_data['target_name'].value_counts())


# Implement a basic SVM classifier
model = sklearn.svm.SVC()

# Pull out just the features and store it as X
X_train = train_data[['sepal length (cm)', 'sepal width (cm)', 'petal length (cm)', 'petal width (cm)']]

# Pull out just the target and store it as y
y_train = train_data['target_name']

# Fit the model by training it on the training data
model.fit(X_train, y_train)


# Pull out just the features of the test set and store them
X_test = test_data[['sepal length (cm)', 'sepal width (cm)', 'petal length (cm)', 'petal width (cm)']]

# Pull out the test target and store as y
y_test = test_data['target_name']

# Use the fitted/trained model to predict the target/y/class/flower type based on the features/X for the data set
y_pred = model.predict(X_test)

# See how it did
print('true:', list(y_test))
print('pred:', list(y_pred))


# NOTES related to metrics and accuracy
# If the classes are very balanced, use accuracy_score
# If they are not balanced, don't use accuracy_score... use balanced_accuracy score instead!
#
# Say you have a file with 10,000 rows of baseball players
# And the classes are 0-not in the HOF, 1-yes in the HOF
# And say that 9,000 are 0-not HOF, and 1,000 are 1-yes HOF
#
# You could easily get 90% accuracy by simply predicting everyone as a 0-not HOF

# Accuracy
accuracy = sklearn.metrics.accuracy_score(y_test, y_pred)
print(f'accuracy: {accuracy: .3f}')

# Classification report
report = sklearn.metrics.classification_report(y_test, y_pred)
print(f'classification report: \n{report}')

# Confusion matrix
# Columns: predicted class  -  Rows: true class
cm = sklearn.metrics.confusion_matrix(y_test, y_pred)
print(f'confusion matrix: \n{cm}')

# Plot a fancy confusion matrix
sklearn.metrics.ConfusionMatrixDisplay.from_predictions(y_test, y_pred, cmap='Blues')
plt.show()
