/* 
lab2 exercise

*/

//Animal Class
public class Animal 
{
	String name;
	String breed;
	int age;
	boolean domesticAnimal;
	String colour;
	
	//Constructor to set up one attribute (name)
	public Animal(String name){
		
		this.setName(name);
		
	}
	
	//Constructor that sets up all attributes
	public Animal(String name, String breed, int age, boolean domesticAnimal, String colour){
		
		this.setName(name);
		this.setBreed(breed);
		this.setAge(age);
		this.setDomesticAnimal(domesticAnimal);
		this.setColour(colour);
		
	}
	
	//String method 
	public String toString()
    {
        String domestic = "domestic";
        if (!isDomesticAnimal())
        {
          domestic = "not domestic";
        }
         
        return "This animal is called " +getName()+ " and is " +
		getColour()+ " in colour and is a" +getBreed()+ " and is " +getAge()+ "years old and is"
        +domestic+"";
		
	}
	
	//make Noise method
    public void makeNoise()
	{
		switch(breed)
		{
		case "dog":
			System.out.println("bark");
			break;
		case "cat":
			System.out.println("meow");
			break;
		default:
			System.out.println("animal noises");
		}
	}

    //make Noise method (method overloading)
    public void makeNoise(boolean old)
    {  
    	if(old)
    	{
    		System.out.println("No noise the animal is old...");
    	}	
    	else
    	makeNoise();		    
    }
    
	//get and set methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isDomesticAnimal() {
		return domesticAnimal;
	}

	public void setDomesticAnimal(boolean domesticAnimal) {
		this.domesticAnimal = domesticAnimal;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
    
	
}
