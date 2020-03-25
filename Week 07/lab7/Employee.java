/*
 	Employee Class
 */
package lab7;

public class Employee extends Person{
	
	//Attributes
	
	private Job job;
	private int personnelNumber;
	private Date startDate;
	private static int nextNumber = 0;

	
	//Constructors
	
	public Employee(String firstName, String surName, String gender, Date dateOfBirth, Date startDate, Job job) {
		super(firstName, surName, gender, dateOfBirth);
		this.personnelNumber = nextNumber;
		this.startDate = startDate;
		nextNumber++;
	}
	
	//Methods

	public String toString() {
		FileProcessor writer = new FileProcessor("src/names.txt","write");
		String name = getFirstName()+" "+getSurName();
		writer.write(name);
		return "Employee [personnelNumber=" + personnelNumber + ", startDate=" + startDate + "]";
	}
	
	
	//Getters and Setters
	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}


	public int getPersonnelNumber() {
		return personnelNumber;
	}


	public void setPersonnelNumber(int personnelNumber) {
		this.personnelNumber = personnelNumber;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public static int getNextNumber() {
		return nextNumber;
	}


	public static void setNextNumber(int nextNumber) {
		Employee.nextNumber = nextNumber;
	}
	
}
