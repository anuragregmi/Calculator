
package calculator2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;


/**
 * Numpad for calculator. Has numbers,operators and special purpose buttons.
 * 
 * @author anurag
 *
 */


public class NumPad extends JPanel{

	protected JTextField input;
	protected JButton one,two,three,four,five,
			six,seven,eight,nine,zero,
			dot,equals,
			plus,minus,
			mul,divide,
			power,percent,
			br_open,br_close,
			clear,back_space;
	
	/**
	 * creates a panel with numbers and operators ands adds action listeners to them
	 * 
	 * @param textField (JTextfield) text field from where it reads input and displays result.
	 */
	NumPad(JTextField textField){
		input = textField;
		
		// numbers --> Center
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		

		// bottom elements --> Bottom
		zero = new JButton("0");
		zero.setPreferredSize(new Dimension(WIDTH, HEIGHT*30));
		
		dot = new JButton(".");
		equals = new JButton("=");
		
		
		// side operators --> Right
		plus = new JButton("+");
		plus.setBackground(new Color(100,100,255));
		plus.setForeground(Color.WHITE);
		
		minus = new JButton("-");
		minus.setBackground(new Color(100,100,255));
		minus.setForeground(Color.WHITE);
		
		mul = new JButton("*");
		mul.setBackground(new Color(100,100,255));
		mul.setForeground(Color.WHITE);
		
		divide = new JButton("/");
		divide.setBackground(new Color(100,100,255));
		divide.setForeground(Color.WHITE);
		
		power = new JButton("/\\");
		power.setBackground(new Color(100,100,255));
		power.setForeground(Color.WHITE);
		
		percent = new JButton("%");
		percent.setBackground(new Color(100,100,255));
		percent.setForeground(Color.WHITE);
		
		minus.setPreferredSize(new Dimension(WIDTH*50, HEIGHT));
		
		
		// tools --> Top
		clear = new JButton("A/C");
		clear.setBackground(new Color(200,10,10));
		clear.setForeground(Color.WHITE);
		clear.setBorder(new LineBorder(Color.RED));
		back_space = new JButton(new ImageIcon(getClass().getResource("/backspace.png")));
		
		// top operators --> below tools
		br_open = new JButton("(");
		br_close = new JButton(")");
		clear.setPreferredSize(new Dimension(WIDTH, HEIGHT*20));
		
		addHandlers();  // adding event handlers to buttons
		
		setLayout(new BorderLayout(5,5));
		
		// CENTER
		JPanel numbers = new JPanel();
		numbers.setLayout(new GridLayout(3, 3, 5, 5));
		numbers.add(seven);
		numbers.add(eight);
		numbers.add(nine);
		numbers.add(four);
		numbers.add(five);
		numbers.add(six);
		numbers.add(one);
		numbers.add(two);
		numbers.add(three);
		
		// BOTTOM | SOUTH
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1, 3, 5, 5));
		bottom.add(dot);
		bottom.add(zero);
		bottom.add(equals);
		
		// RIGHT | EAST
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(3, 2, 5, 5));
		right.add(power);
		right.add(percent);
		right.add(mul);
		right.add(divide);
		right.add(plus);
		right.add(minus);
		
		// TOP | NORTH
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1, 4, 5, 5));
		top.add(clear);
		top.add(br_open);
		top.add(br_close);
		top.add(back_space);
		
		setBorder(new EmptyBorder(10, 3, 3, 3));
		
		// adding elements
		add(top, BorderLayout.NORTH);
		add(numbers, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		add(right, BorderLayout.EAST);
	}
	
	
	
	/**
	 * adds action listeners and handles the events to the buttons
	 */
	protected void addHandlers(){
		
		// numbers 
		
		one.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("1");
			}
		});
		two.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("2");
			}
		});
		three.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("3");
			}
		});
		four.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("4");
			}
		});
		five.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("5");
			}
		});
		six.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("6");
			}
		});
		seven.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("7");
			}
		});
		eight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("8");
			}
		});
		nine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("9");
			}
		});
		zero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("0");
			}
		});
		
		// dot
		dot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter(".");
			}
		});
		
		// operators
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("+");
			}
		});
		minus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("-");
			}
		});
		divide.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("/");
			}
		});
		mul.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("*");
			}
		});
		power.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				input_charter("^");
				
			}
		});
		percent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				input_charter("%");
				
			}
		});
		br_close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter(")");
			}
		});

		br_open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				input_charter("(");
			}
		});
		
		// tools
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// clear the field
				input.setText("");
				input.requestFocus();
				
			}
		});
		back_space.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// remove the character
				int cursor_position = input.getCaretPosition();
				try {
					input.getDocument().remove(cursor_position-1, 1);
					input.requestFocus();
					input.setCaretPosition(cursor_position-1);
				} catch (BadLocationException e1) {
					System.out.println(e1.getMessage());
					input.requestFocus();
				}
				
			}
		});
		equals.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(PostfixCalculator.validate(input.getText()))
				{
					// Set result to Input field
					input.setText(PostfixCalculator.calculate(input.getText()));
					input.requestFocus();
				}
				else
				{
					// Display Message Dialogue if expression is wrong
					JOptionPane.showMessageDialog(input.getParent(), "Invalid expression !!");
				}
			}
		});
	}
	
	/**
	 * Enters given string to input box
	 * 
	 * @param ch (String) character to be inserted in input box
	 */
	public void input_charter(String ch){
		input.setText(input.getText()+ch);
		input.requestFocus();
	}
		
}
	
