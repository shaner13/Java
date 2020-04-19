/*
 	Description: This class will find the frequencies of the datasheet attributes
 	and calculate the probabilities of a new entry using the Naive Bayes classifier.
 	
 	Author: Shane Riedy
 */

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class NaiveBayes {
	
	//Attributes
	double pIfYes;
	double pIfNo;
	double probability;
	double total;
	double accuracy;
	int i = 0;
	
	ArrayList<String> correctArray = new ArrayList<String>();
	ArrayList<String> predictArray = new ArrayList<String>();
	
	ArrayList<Entry> dataEntries = new ArrayList<Entry>();	
	
	ArrayList<String> temperature = new ArrayList<String>();
	ArrayList<String> aches = new ArrayList<String>();
	ArrayList<String> cough = new ArrayList<String>();
	ArrayList<String> soreThroat = new ArrayList<String>();
	ArrayList<String> dangerZone = new ArrayList<String>();
	ArrayList<String> hasCOVID19 = new ArrayList<String>();

	ArrayList<String> temperatureIfCOVID19 = new ArrayList<String>();
	ArrayList<String> achesIfCOVID19 = new ArrayList<String>();
	ArrayList<String> coughIfCOVID19 = new ArrayList<String>();
	ArrayList<String> soreThroatIfCOVID19 = new ArrayList<String>();
	ArrayList<String> dangerZoneIfCOVID19 = new ArrayList<String>();

	//Methods
	
	//Main method. Will populate the dataEntries array with entries then find the frequencies of 
	//the symptoms for the calculations. If test == true the program will only train itself on 70% of the data.
	public NaiveBayes(FileProcessor file, Boolean test) {	
		file.getData(dataEntries);
		if (test == false) { findFrequency(dataEntries.size());}	
		else {
			double split = ((float)dataEntries.size()/(float)100)*70;
			findFrequency((int)split); 
		}
	}
	
	//Getting the frequencies of the symptoms. The first set will be the total and the second set will
	//be the 'probability if yes', subtracting the first set from the second will give the probability if no.
	public void findFrequency(int size) {
		for(i=0;i<size;i++) {
			for (Entry en : dataEntries) {		
				temperature.add(en.getTemperature());
				aches.add(en.getAches());
				cough.add(en.getCough());
				soreThroat.add(en.getSoreThroat());
				dangerZone.add(en.getDangerZone());
				hasCOVID19.add(en.getHasCOVID19());
				
				if (en.getHasCOVID19().equals("yes")) {
					temperatureIfCOVID19.add(en.getTemperature());
					achesIfCOVID19.add(en.getAches());
					coughIfCOVID19.add(en.getCough());
					soreThroatIfCOVID19.add(en.getSoreThroat());
					dangerZoneIfCOVID19.add(en.getDangerZone());	
				}
			}
		}
	}
	
	//Using the collections.frequency to get the frequency of each value in the array. Then dividing
	//by the total. This is done for each symptom and mulitplied by each other to get probability if yes/no.
	public double calcProbs(Entry newEntry) {
		total = Collections.frequency(hasCOVID19, "yes");
		pIfYes += Collections.frequency(temperatureIfCOVID19,newEntry.getTemperature()) / total;
		pIfYes *= Collections.frequency(achesIfCOVID19,newEntry.getAches()) / total;
		pIfYes *= Collections.frequency(coughIfCOVID19,newEntry.getCough()) / total;
		pIfYes *= Collections.frequency(soreThroatIfCOVID19,newEntry.getSoreThroat()) / total;
		pIfYes *= Collections.frequency(dangerZoneIfCOVID19,newEntry.getDangerZone()) / total;
		pIfYes *= (double) total / dataEntries.size();
		
		total = Collections.frequency(hasCOVID19, "no");
		pIfNo += (Collections.frequency(temperature,newEntry.getTemperature()) - Collections.frequency(temperatureIfCOVID19,newEntry.getTemperature())) / total;
		pIfNo *= (Collections.frequency(cough,newEntry.getCough()) - Collections.frequency(coughIfCOVID19,newEntry.getCough())) / total;
		pIfNo *= (Collections.frequency(aches,newEntry.getAches()) - Collections.frequency(achesIfCOVID19,newEntry.getAches())) /total;
		pIfNo *= (Collections.frequency(soreThroat,newEntry.getSoreThroat()) - Collections.frequency(soreThroatIfCOVID19,newEntry.getSoreThroat())) /total;
		pIfNo *= (Collections.frequency(dangerZone,newEntry.getDangerZone()) - Collections.frequency(dangerZoneIfCOVID19,newEntry.getDangerZone())) / total;
		pIfNo *= (double) total / dataEntries.size();
		
		//Getting the result as a percentage and returning it.
		probability = (pIfYes / (pIfYes+pIfNo)) * 100;
		return probability;
	}
	
	//Testing the accuracy of the classifier. It will train itself on 70% of the data and compare 
	//the correct results to the predicted results of the other 30%. This is returned as a percentage.
	public double testAccuracy() {
		double accuracy = 0;
		int i = 0;
		double split = ((float)dataEntries.size()/(float)100)*70;
		
		//Filling arrays with the correct and predicted results to compare.
		for (i=(int)split; i<dataEntries.size(); i++) {
				Entry en = dataEntries.get(i);
				correctArray.add(en.getHasCOVID19());
				double diagnosis = calcProbs(new Entry(en.getTemperature(), en.getAches(), en.getCough(), en.getSoreThroat(), en.getSoreThroat()));
				if (diagnosis>0.5) { predictArray.add("yes"); }
				else { predictArray.add("no"); }
		}
		
		//Comparing the results of "hasCOVID19" to each other.
		for(i=0;i<correctArray.size();i++) {
			if (correctArray.get(i).equals(predictArray.get(i))) {
				accuracy++;
			}
		}
		
		//Getting the result as a percentage and returning it.
		accuracy /= correctArray.size();
		return accuracy * 100;
	}
}
