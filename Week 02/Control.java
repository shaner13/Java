/*
	Driver program to run the animal class.
*/

import java.util.Scanner;

//Main Class
public class Control
{
	public static void main (String[] args)
	{
		Animal animal_1 = new Animal("Shane");
		Animal animal_2 = new Animal("Shane2", "rabbit", 9, true, "white" );
		
		System.out.println(animal_1);
		System.out.println(animal_2);
		
		System.out.println(animal_1.name);
		
		animal_2.makeNoise();
		animal_2.setBreed("cat");
		
		animal_2.makeNoise(true);
		animal_2.makeNoise(false);
		
		
		/*** Code to read an input string from the keyboaard*/
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the name please.");
		String name = input.nextLine();
		
		System.out.println("Enter the breed please.");
		String breed = input.nextLine();
		
		System.out.println("Enter the age please.");
		int age = Integer.parseInt(input.nextLine());
		
		System.out.println("Enter whether the animal is domestic or not please.");
		boolean domesticAnimal = Boolean.parseBoolean(input.nextLine());
		
		System.out.println("Enter the colour please.");
		String colour = input.nextLine();
		
		input.close();
		
		Animal animal_3 = new Animal(name,breed,age,domesticAnimal,colour);
		System.out.println(animal_3);
	}
}