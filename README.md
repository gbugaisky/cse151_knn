# README for Assignment

Assignment 2 problem 3:

In this problem, we look at the task of classifying images of digits using k-nearest neighbor classification.
Download the files hw2train.txt, hw2validate.txt and hw2test.txt from the class website. These files
contain your training, validation and test data sets respectively.
For your benefit, we have already converted the images into vectors of pixel colors. The data lables are in
ASCII text format, and each line of the ?les contains a feature vector, followed by its label. The coordinates
of the feature vector are separated by spaces.
1. For k = 1, 3, 5, 11, 16, and 21, build k-nearest neighbor classifiers from the training data. For each of
these values of k, write down a table of training errors (error on the training data) and the validation
errors (error on the validation data). Which of these classifiers performs the best on validation data?
What is the test error of this classi?er?
2. For k = 3, construct a 3-nearest neighbor classi?er based on the data in hw2train.txt. Compute
the confusion matrix of the classi?er based on the data in hw2test.txt. The confusion matrix is a
10x10 matrix, where each row is labelled 0,...,9 and each column is labelled 0,...,9. The entry of
the matrix at row i and column j is Cij=Nj where Cij is the number of test examples that have label
j but are classi?ed as label i by the classi?er, and Nj is the number of test examples that have label j.
Based on your calculation of the confusion matrix, what are i and j in the following statements:
(a) The 3-NN classifer has the highest accuracy for examples that belong to class i.
(b) The 3-NN classifer has the least accuracy for examples that belong to class i.
(c) The 3-NN classifer most often mistakenly classi?es an example in class j as belonging to class i.
Based on your answers, which digits do you think are the easiest and the hardest to classify?