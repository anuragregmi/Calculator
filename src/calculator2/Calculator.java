package calculator2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import calculator2.NumPad;

/**
 * A calculator that can do basic arithmetic operations 
 * @author anurag
 *
 */

public class Calculator {
	protected JFrame frame;  // main frame
	protected JTextField input;  // input field
	protected NumPad numpad;  // numbers pad
	protected JPanel foot_panel,top_panel;  // top and footer panels
	protected  JLabel footer;  // footer for the app 
	
	/**
	 * Creates a frame for calculator, adds its components and displays it
	 */
	Calculator(){
		System.setProperty("file.encoding", "UTF-16");
		frame = new JFrame("हिसाब यन्त्र");
		input = new JTextField();
		numpad = new NumPad(input);
		footer = new JLabel();
		
		input.setFont(new Font("Serif", Font.BOLD, 30));
		input.setBorder(new CompoundBorder(new LineBorder(Color.BLACK),new EmptyBorder(10,5,10,5)));
			
		input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				byte code = (byte)e.getKeyChar();
				
				// ESC to clear
				if(code == 27){
					
					input.setText("");
					input.requestFocus();
				}
				
				// ENTER key to evaluate
				else if(code == 10){
					// if expression is valid then calculate
					if(PostfixCalculator.validate(input.getText()))
					{
						input.setText(PostfixCalculator.calculate(input.getText()));
						input.requestFocus();
					}
					// else show message dialog
					else
					{
						JOptionPane.showMessageDialog(frame, "Invalid expression !!");
					}
				}
				// filter content
				filterContent(e.getKeyChar());
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				super.keyPressed(arg0);
				filterContent(arg0.getKeyChar());  // filter content
			}
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				super.keyTyped(arg0);
				filterContent(arg0.getKeyChar());  // filter content
			}
			
		});
		
		
		frame.getRootPane().setBorder(new EmptyBorder(10, 0, 0, 0));  // set padding to frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // enable exit on close button
		frame.setSize(400, 500);  // setting frame size
		frame.setLayout(new BorderLayout());  // setting layout for frame
		frame.setResizable(false);  // disable resize option
		frame.setLocation(MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y);  // opens window the mouse is
		
		
		top_panel = new JPanel();
		top_panel.setLayout(new GridLayout(1, 1));
		top_panel.setBorder(new EmptyBorder(0, 5, 5, 5));
		
		
		foot_panel = new JPanel();
		foot_panel.setBackground(SystemColor.control);
		
		 	}
	
	
	/**
	 * Packs the  elements to respective panel and adds panel to frame.
	 */
	public void pack(){
		top_panel.add(input);
		foot_panel.add(footer);
		frame.add(top_panel, BorderLayout.NORTH);  // placing top_panel text field to the top
		frame.add(numpad, BorderLayout.CENTER);  // placing numpad to center
		frame.add(foot_panel, BorderLayout.PAGE_END);
		
	}
	
	/**
	 * Sets footer text
	 * 
	 * @param text (String) footer text
	 */
	public void setFooterText(String text){
		footer.setText(text);
	}
	
	
	
	/**
	 * Displays the frame
	 */
	public void show(){
		frame.setVisible(true); // showing the frame
	}
	
	
	/**
	 * 
	 * Filters and removes illegal characters ( a-z, A-Z, $, &, #, @ etc)
	 * 
	 * @param c (char) character sent from KeyPressed event 
	 */
	public void filterContent(char c){
			if((c>=58 && c<=93)||
				(c>=95 && c <=126) ||
				(c == 44) || 
				(c >= 32 && c<=36)||
				(c >= 38 && c <=39))
			{				
				input.setText(input.getText().replaceAll(String.valueOf(c), ""));
			}
	}
	
	
	public static void main(String[] args){
		Calculator c = new Calculator();
		c.setFooterText("LICENSED UNDER GPL3 | Developed By Anurag Regmi");
		c.pack();
		c.show();
	}
}
