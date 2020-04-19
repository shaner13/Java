/*
  Class that will implement a GUI for the Coronavirus Diagnostic Tool
  
  Author: Shane Riedy
*/

//Importing components
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;



public class GUI extends JFrame implements ActionListener
{
	JPanel instructionPanel, addPanel, buttonPanel;
	
	JButton predictButton, testAccuracyButton;
	
	JLabel addLabel, result, accuracy;
	
	JCheckBox achesBox, coughBox, soreThroatBox, dangerZoneBox;
	
	JComboBox tempMenu;
	
	String temp, aches, cough, soreThroat, dangerZone;
	
	public GUI()
	{
		super("Coronavirus Diagnostic Tool");
		
		//Setting up the window and layout
		setResizable(false);
		setSize(400,300);
		setLayout(new BorderLayout());
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex) {
			System.out.println("Error.");
		}
		
		//Adding Panels
		addPanel = new JPanel();
		addPanel.setBackground(new Color(128,206,225));
		instructionPanel = new JPanel();
		instructionPanel.setBackground(new Color(255,255,116));
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(255,255,116));
		
		//Adding Labels
		addLabel = new JLabel("Select the boxes for the symptoms you have. Click diagnose to predict.");
		result = new JLabel("");
		result.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		accuracy = new JLabel("");
		accuracy.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		
		//Adding check boxes
		achesBox = new JCheckBox("Aches");
		achesBox.setBackground(new Color(128,206,225));
		coughBox = new JCheckBox("Cough");
		coughBox.setBackground(new Color(128,206,225));
		soreThroatBox = new JCheckBox("Sore throat");
		soreThroatBox.setBackground(new Color(128,206,225));
		dangerZoneBox = new JCheckBox("Danger Zone");
		dangerZoneBox.setBackground(new Color(128,206,225));
		
		//Adding drop down menu for the temperature
		tempMenu = new JComboBox<>(new String[] {"Hot","Normal","Cool","Cold"});
		tempMenu.setSelectedIndex(0);
		
		//Adding buttons
		predictButton = new JButton("Diagnose");
		predictButton.addActionListener(this);
		predictButton.setBackground(new Color(255,255,207));
		
		testAccuracyButton = new JButton("Test Accuracy");
		testAccuracyButton.addActionListener(this);
		testAccuracyButton.setBackground(new Color(255,255,207));
		
		//Adding components to panels
		instructionPanel.add(addLabel);
		addPanel.add(achesBox);
		addPanel.add(coughBox);
		addPanel.add(soreThroatBox);
		addPanel.add(dangerZoneBox);
		addPanel.add(tempMenu);
		addPanel.add(result);
		buttonPanel.add(predictButton);
		buttonPanel.add(testAccuracyButton);
		
		//Adding panels to the screen
		add(instructionPanel, BorderLayout.NORTH);
		add(addPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//If the test accuracy button is selected it will create a new NaiveBayes object and return the
		//results of the test accuracy function.
		if (e.getSource()==testAccuracyButton) {
			FileProcessor file = new FileProcessor("MLdata.csv");
			NaiveBayes test = new NaiveBayes(file, true);
			double accuracy = test.testAccuracy();
			result.setText("Classifier accuracy is: "+String.format("%.2f", accuracy)+"%.");
		}
		
		//If the predict button is selected the values from the components will be assigned to 
		//the values needed to create a new entry and then used to create a new entry and the 
		//probability will be calculated.
		else if (e.getSource() == predictButton)
		{
			//Assigning variables with the values from the components.
			temp = (String) tempMenu.getSelectedItem();
			if(achesBox.isSelected()) {aches = "yes"; } else {aches = "no"; }
			if(coughBox.isSelected()) {cough = "yes"; } else {cough = "no"; }
			if(soreThroatBox.isSelected()) {soreThroat = "yes"; } else {soreThroat = "no"; }
			if(dangerZoneBox.isSelected()) {dangerZone = "yes"; } else {dangerZone = "no"; }
			
			//Creating a new fileprocessor object and using this to create a new NaiveBayes object
			//then calculating the probability for this entry.
			try
			{
				FileProcessor file = new FileProcessor("MLdata.csv");
				NaiveBayes test = new NaiveBayes(file, false);
				double diagnosis = test.calcProbs(new Entry(temp.toLowerCase(), aches, cough, soreThroat, dangerZone));
				result.setText("The chances of having COVID-19 are: "+String.format("%.2f", diagnosis)+"%.");
			}
			catch (Exception ex)
			{
				result.setText("Error. Invalid data.");
			}
		}
			
	}
}
