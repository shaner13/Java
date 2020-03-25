/*
 	Job Class
 */
package lab7;

public class Job {
	
	//Attributes
	
	private double salary;
	private int jobID;
	private String role;
	
	
	//Constructor
	
	public Job(double salary, int jobID, String role) {
		super();
		this.salary = salary;
		this.jobID = jobID;
		setRole(role);
	}
	
	//Methods

	public String toString() {
		return "Job [salary=" + salary + ", jobID=" + jobID + ", role=" + role + "]";
	}

	//Getters and Setters
	
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		
		FileProcessor filecheck = new FileProcessor("src/roles.txt","read");
		
		if (filecheck.roleChecker(role))
		{
			this.role = role;
		}
		else
		{
			System.out.println("Not a valid role.");
		}
		
	}

}
