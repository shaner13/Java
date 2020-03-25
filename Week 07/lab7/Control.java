/*
 	Driver program for LAB7
 */
package lab7;

public class Control {

	public static void main(String[] args) {
		
		Date date1 = new Date(10,2,2000);
		Date date2 = new Date(25,12,2020);
		
		Job job = new Job(10000,513,"Model");
		System.out.println(job);
		
		Employee Shane = new Employee("Shane","Riedy","Male",date1,date2,job);
		System.out.println(Shane);
		
		
	}

}
