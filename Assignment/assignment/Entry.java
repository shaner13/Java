/*
 	Description: This class represents a data entry in the datasheet.
 	
 	Author: Shane Riedy
 */

import java.util.Scanner;

public class Entry {
	
	//Attributes
	Scanner scanner;
	private String temperature;
	private String aches;
	private String cough;
	private String soreThroat;
	private String dangerZone;
	private String hasCOVID19;

	//Constructors
	public Entry(String temperature, String aches, String cough, String soreThroat, String dangerZone) {
		super();
		setTemperature(temperature);
		setAches(aches);
		setCough(cough);
		setSoreThroat(soreThroat);
		setDangerZone(dangerZone);
	}
	
	public Entry(String[] array) {
		setTemperature(array[0]);
		setCough(array[1]);
		setAches(array[2]);
		setSoreThroat(array[3]);
		setDangerZone(array[4]);
		setHasCOVID19(array[5]);
	}
	
	//Methods
		//to String method
		public String toString() {
			return "Entry [temperature=" + getTemperature() + ", aches=" + getAches() + ", cough=" + getCough() + ", soreThroat="
					+ getSoreThroat() + ", dangerZone=" + getDangerZone() + ", hasCOVID19=" + getHasCOVID19() + "]";
		}

		//Getters and Setters
		public String getTemperature() { return temperature; }
		public void setTemperature(String temperature) { this.temperature = temperature; }

		public String getAches() { return aches; }
		public void setAches(String aches) { this.aches = aches; }

		public String getCough() { return cough; }
		public void setCough(String cough) { this.cough = cough; }

		public String getSoreThroat() { return soreThroat; }
		public void setSoreThroat(String soreThroat) { this.soreThroat = soreThroat; }

		public String getDangerZone() { return dangerZone; }
		public void setDangerZone(String dangerZone) { this.dangerZone = dangerZone; }

		public String getHasCOVID19() { return hasCOVID19; }
		public void setHasCOVID19(String hasCOVID19) { this.hasCOVID19 = hasCOVID19; }
}
