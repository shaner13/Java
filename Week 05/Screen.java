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

public class Screen extends JFrame implements ActionListener, MouseListener
{
	JPanel northPanel, centerPanel, southPanel, touchPanel;
	JButton button1, button2;
	JLabel label;
	JTextField textfield;
	JToggleButton toggleButton;
	
	public Screen()
	{
		super("GUI");
		
		setSize(450,450);
		setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();
		
		button1 = new JButton("Click me");
		button1.addActionListener(this);
		
		button2 = new JButton("Click me as well");
		button2.addActionListener(this);
		
		label = new JLabel("the panel is here");
		
		toggleButton = new JToggleButton("toggle me");
		
		textfield = new JTextField("Name");
		textfield.setToolTipText("Enter your name here");
		textfield.addActionListener(this);
		
		touchPanel = new JPanel();
		touchPanel.setBackground(Color.red);
		touchPanel.addMouseListener(this);
		
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		
		northPanel.add(button1);
		northPanel.add(button2);
				
		centerPanel.add(touchPanel);
		touchPanel.add(label);
		
		southPanel.add(textfield);
		southPanel.add(toggleButton);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==button1) JOptionPane.showMessageDialog(this,"You clicked me");
		else if (e.getSource()==button2) JOptionPane.showMessageDialog(this,"You clicked the me");
		else if (e.getSource()==textfield) JOptionPane.showMessageDialog(this,"You entered "+textfield.getText());
	}
	
	public void mouseEntered(MouseEvent e)
	{
		label.setText("Mouse has entered the panel");
	}
	
	public void mouseExited(MouseEvent e)
	{
		label.setText("Mouse has exited the panel");
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if (e.getButton()==1) label.setText("You have left clicked the panel");
		else label.setText("You have right clicked the panel");
	}
	
	public void mouseReleased(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
}