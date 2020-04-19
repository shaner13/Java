Naive Bayes Machine Learning Model
Java Assignment by Shane Riedy

In your own words write a short (< 200 words) how your application works

This program trains itself on a CSV file with records of COVID-19 patients and their symptoms and predicts the probability of a new entry having COVID-19 using the Naive Bayes machine learning classifier. 

The program will read in the CSV file and store each row as a new Entry object in an array list. The frequencies of the symptoms are then sorted into two sets of array lists. One with the total amount of symptoms and one with the total amount of symptoms given a positive result. These are then used to calculate the probability of the inputted entry having COVID-19.

The user is given a GUI to interact with, and is prompted to enter the symptoms of a new patient using checkboxes and a dropdown menu and may then predict the results using the diagnose button. The program will retrieve the states of these boxes and perform the calculations.

The user may also select the Test Accuracy button which will train the program on only 70% of the data sheet provided and predict the results of the other 30% and then compare these to the actual results to determine its accuracy.













The classes you created, with a description.

Control:
This class simply instantiates a new GUI object.

Entry:
This class represents each row of data in the datasheet. It has six attributes representing the symptoms of the patient.
There are two constructors for this class, the first one takes only five attributes and is used when the user is entering a new Entry to be predicted. 
The other constructor takes an array of strings and populates each element with a string from the row, separated by a comma, using the get and set methods. 
This class only has a toString and get and set methods.

FileProcessor:
This class performs all necessary file handling. There is only one method.
The constructor will take a string ‘fileName’ and use this to instantiate a new file object. This file is then used to create a Scanner object. This is all handled in a try catch block.
The method, getData will take the array list dataEntries and populate it with Entry objects using a while loop and employing the split(“,”) method as each row belongs to a CSV. It will then return the populated dataEntries array.

NaiveBayes:
This class performs all the calculations of the Naive Bayes machine learning classifier. There is one constructor and three methods. 
There are two sets of array lists, and some doubles which are all used for the calculations. 
The constructor will take two arguments. A fileProcessor object and a boolean, “test”.
The latter is used to determine if the program is testing the accuracy of the classifier. 
The method findFrequency fills the first set of array lists with the total number of each symptom in the dataEntries array list, using a for loop. It will also get the total number of each symptom given COVID-19 is equal to “yes”. If the test boolean is true it will only get the frequency of 70% of the data.
The method calcProbs is used to calculate the probability of the entry the user entered having COVID-19. The program avails of the imported collections.frequency() method for array lists. The probability of each positive symptom in the entry divided by the total number of positive entries are multiplied by each other then multiplied once more by the probability of positive entries (P(Yes)). It will then do the same for the negative results. (P(No))
These are then used in the equation: P(Yes)/(P(Yes)/P(No)) * 100 (to get a percentage) and returned. 
The last method, getAccuracy will fill two arrays with the results based on the last 30% of the data sheet. One array is populated with predicted results using the classifier and the other with the correct results. It will then compare these to each other and return the percentage it got correct.


GUI: 
This class creates a GUI for the user to interact with. It makes use of checkboxes and a dropdown menu to input the symptoms, (mitigating input error), outputting the results after the diagnose button is selected. The GUI will retrieve the states of these components and instantiate a new NaiveBayes object using them. 
The user may also select the test accuracy button to check the classifier accuracy.

