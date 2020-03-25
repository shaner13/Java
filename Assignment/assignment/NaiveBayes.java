/*
 	Description: This class will find the frequencies of the datasheet attributes
 	and calculate the probabilities of a new entry using the Naive Bayes classifier.
 	
 	Author: Shane Riedy
 */
package assignment;

import java.util.ArrayList;
import java.util.Collections;

public class NaiveBayes {
	
	//Attributes
	double pIfYes;
	double pIfNo;
	double probability;
	float total;
	
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
	public NaiveBayes(FileProcessor file) {	
		file.getData(dataEntries);
		findFrequency();
	}

	public void findFrequency() {	
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
	
	public double calcProbs(Entry newEntry) {
		total = Collections.frequency(hasCOVID19, "yes");
		pIfYes += Collections.frequency(temperatureIfCOVID19,newEntry.getTemperature()) / total;
		pIfYes *= Collections.frequency(achesIfCOVID19,newEntry.getAches()) / total;
		pIfYes *= Collections.frequency(coughIfCOVID19,newEntry.getCough()) / total;
		pIfYes *= Collections.frequency(soreThroatIfCOVID19,newEntry.getSoreThroat()) / total;
		pIfYes *= Collections.frequency(dangerZoneIfCOVID19,newEntry.getDangerZone()) / total;
		pIfYes *= (double) Collections.frequency(hasCOVID19,"yes") / dataEntries.size();
		
		total = Collections.frequency(hasCOVID19, "no");
		pIfNo += (Collections.frequency(temperature,newEntry.getTemperature()) - Collections.frequency(temperatureIfCOVID19,newEntry.getTemperature())) / total;
		pIfNo *= (Collections.frequency(cough,newEntry.getCough()) - Collections.frequency(coughIfCOVID19,newEntry.getCough())) / total;
		pIfNo *= (Collections.frequency(aches,newEntry.getAches()) - Collections.frequency(achesIfCOVID19,newEntry.getAches())) /total;
		pIfNo *= (Collections.frequency(soreThroat,newEntry.getSoreThroat()) - Collections.frequency(soreThroatIfCOVID19,newEntry.getSoreThroat())) /total;
		pIfNo *= (Collections.frequency(dangerZone,newEntry.getDangerZone()) - Collections.frequency(dangerZoneIfCOVID19,newEntry.getDangerZone())) / total;
		pIfNo *= (double) Collections.frequency(hasCOVID19,"no") / dataEntries.size();
		
		probability = (pIfYes / (pIfYes+pIfNo)) * 100;
		return probability;
	}
}