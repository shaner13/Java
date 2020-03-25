/*
	Program to implement a simple GUI
*/

//Importing components
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;

import java.util.Random;

public class Screen extends JFrame implements ActionListener
{
	JPanel centerPanel;
	JButton generateButton, checkButton;
	JTextField guessField;
	JLabel instructionLabel;
	Random random = new Random();
	int randomNumber = random.nextInt(100);
	int guess = 0;
	int guesses = 0;
	
	
	
	public Screen()
	{
		super("My GUI");
		
		setSize(300,300);
		setLayout(new BorderLayout());
		
		centerPanel = new JPanel();
		
		generateButton = new JButton("Generate the random number");
		generateButton.addActionListener(this);
		
		instructionLabel = new JLabel("Then make a guess..");
		
		checkButton = new JButton("Check your guess");
		checkButton.addActionListener(this);
		
		guessField = new JTextField("Enter your guess");
		guessField.setToolTipText("Enter your guess here");
		guessField.addActionListener(this);
		
		add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.add(generateButton);
		centerPanel.add(instructionLabel);
		centerPanel.add(guessField);
		centerPanel.add(checkButton);
				
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==generateButton)  {
			
			newGame();
		}
		
		else if (e.getSource()==checkButton) {
			
			try
			{
				guess = Integer.parseInt(guessField.getText());
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Please enter a valid number.");
				return;
			}
			
			if (guess == randomNumber) {
				JOptionPane.showMessageDialog(this,"Congratulations! Your guess of: "+guess+" is equal to: "+randomNumber+". It took you:"+guesses+" guesses!");
				newGame();
				
			}
			
			else if (guess > randomNumber) {
				JOptionPane.showMessageDialog(this,"Your guess of: "+guess+" is too high!");
				guesses += 1;
				
			}
			
			else if (guess < randomNumber) {
				JOptionPane.showMessageDialog(this,"Your guess of: "+guess+" is too low!");
				guesses += 1;
				
			}
		}

	}
	
	public void newGame()
	{
		randomNumber = random.nextInt(100);
		guesses = 0;
		JOptionPane.showMessageDialog(this,"A new game has started! Time to guess again!");
	}
	
}