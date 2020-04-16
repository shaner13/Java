/*
  Class that will implement a GUI for the Coronavirus Diagnostic Tool
  
  Author: Shane Riedy
*/

//Importing components
package assignment;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;


public class GUI extends JFrame implements ActionListener
{
	JPanel instructionPanel, addPanel;
	
	JButton predictButton;
	
	JLabel addLabel;
	
	JTextField tempField, achesField, coughField, soreThroatField, dangerZoneField;
	
	String temp, aches, cough, soreThroat, dangerZone;
	
	public GUI()
	{
		super("Coronavirus Diagnostic Tool");
		
		setResizable(false);
		setSize(400,300);
		setLayout(new BorderLayout());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex) {
			System.out.println("Error.");
		}
		
		//Adding panels and buttons
		addPanel = new JPanel();
		instructionPanel = new JPanel();

		predictButton = new JButton("Diagnose");
		predictButton.addActionListener(this);
		predictButton.setBackground(new Color(255,255,207));
		
		addLabel = new JLabel("Enter the data into each box. Click diagnose to predict.");
		instructionPanel.setBackground(new Color(255,255,207));

		//Adding fields for input
		tempField = new JTextField("Enter your temperature.");
		tempField.setToolTipText("Hot/Normal/Cool/Cold");
		tempField.addActionListener(this);
		
		achesField = new JTextField("Do you have aches?");
		achesField.setToolTipText("Yes/No");
		achesField.addActionListener(this);
		
		coughField = new JTextField("Do you have any cough?");
		coughField.setToolTipText("Yes/No");
		coughField.addActionListener(this);
		
		soreThroatField = new JTextField("Do you have a sore throat?");
		soreThroatField.setToolTipText("Yes/No");
		soreThroatField.addActionListener(this);
		
		dangerZoneField = new JTextField("Have you been to a danger zone recently?");
		dangerZoneField.setToolTipText("Yes/No");
		dangerZoneField.addActionListener(this);
		

		add(instructionPanel, BorderLayout.NORTH);
		add(addPanel, BorderLayout.CENTER);
		add(predictButton, BorderLayout.SOUTH);
		
		instructionPanel.add(addLabel);
		addPanel.add(tempField);
		addPanel.add(achesField);
		addPanel.add(coughField);
		addPanel.add(soreThroatField);
		addPanel.add(dangerZoneField);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==predictButton) {

				
			if (Arrays.asList("hot", "cold", "normal", "cool").contains(tempField.getText().toLowerCase())
					&& Arrays.asList("yes", "no").contains(achesField.getText().toLowerCase())
					&& Arrays.asList("yes", "no").contains(coughField.getText().toLowerCase())
					&& Arrays.asList("yes", "no").contains(soreThroatField.getText().toLowerCase())
					&& Arrays.asList("yes", "no").contains(dangerZoneField.getText().toLowerCase())) {
					
				
				temp = tempField.getText().toLowerCase();
				aches = achesField.getText().toLowerCase();
				cough = coughField.getText().toLowerCase();
				soreThroat = soreThroatField.getText().toLowerCase();
				dangerZone = dangerZoneField.getText().toLowerCase();
				
				FileProcessor file = new FileProcessor("src/MLdata.csv");
				NaiveBayes test = new NaiveBayes(file);
				double diagnosis = test.calcProbs(new Entry(temp, aches, cough, soreThroat, dangerZone));
				JOptionPane.showMessageDialog(this,"Your chances of having COVID-19 are: "+String.format("%.2f", diagnosis)+"%.");
			}
      
			else {
				JOptionPane.showMessageDialog(this,"Error. Invalid data.");
				return;
			}		
      
		}
	}
}
