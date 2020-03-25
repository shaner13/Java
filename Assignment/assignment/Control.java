/*
 	Description: Driver program for the COVID-19 Diagnostic tool.
 	
 	Author: Shane Riedy
 */
package assignment;

public class Control
{
	public static void main(String[] args)
	{
		FileProcessor file = new FileProcessor("src/MLdata.csv");
		NaiveBayes test = new NaiveBayes(file);
		double diagnosis = test.calcProbs(new Entry("hot","yes","yes","yes","yes"));
		System.out.println("\n Your chances of having COVID-19 are: "+diagnosis+".");
	}
}
