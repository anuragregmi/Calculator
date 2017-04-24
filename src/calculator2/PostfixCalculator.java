package calculator2;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * A back-end calculator for the project. Comes with its set of methods. 
 * 
 * @author anurag
 * 
 */
public abstract class PostfixCalculator {
	
	
	/**
	 * converts infix string array to post fix string array
	 * 
	 * @param infix_array  an array of string (operators and operands in infix)
	 * @return array of string ( post fix operands and operators)
	 */
	public static String[] toPostFix(String[] infix_array){
		
		Stack<String> stack = new Stack<String>();  // stack to hold operators
		stack.push(" "); 
		String[] p = new String[infix_array.length+2];  // post fix string array
		
		int i = 1;
		int count_brackets = 0;
		p[0] = "(";  // add ( at the beginning of post fix string
		for(String c: infix_array)  // count brackets and length
		{  
			if(c.equals("(") || c.equals(")"))
			{
				count_brackets++;
			}
			p[i++] = c;
		}
		p[i] = ")";  // add ) at the end of string
		
		String[] q = new String[i-1-count_brackets];  // initializing array to store post fix string
		i = 0;
		
		for(String c: p)  // scan all characters
		{

			if(c.equals("("))  // if character is ( then push to stack
			{

				stack.push(c);
			}
			else if(c.equals(")"))  // if character is ) then pop all operators in stack until matching ( arrives
			{
				while(!stack.peek().equals("(")){
					
					q[i++] = stack.pop();
				}
				stack.pop();
			}
			else if(c.equals("-") ||c.equals( "+") || c.equals("/") ||c.equals("*") || c.equals("^"))  // if character is operator
			{
				// if stack already has operator of same precedence at top ,
				// and put it to post fix string and push current operator to stack
				// else just put operator to stack

				if(c.equals("+") || c.equals("-"))  
				{
					
					if((stack.peek().equals("+")) || (stack.peek().equals("-") ||(stack.peek().equals("*")) || (stack.peek().equals("/") || (stack.peek().equals("^"))))){
						q[i++] = stack.pop();
						stack.push(c);
					}
					else{
						stack.push(c);
					}
				}
				else if((c.equalsIgnoreCase("*") || c.equals("/")))
				{
					if((stack.peek().equals("*")) || (stack.peek().equals("/") || (stack.peek().equals("^")))){
						q[i++] = stack.pop();
						stack.push(c);
					}
					
					else{
						stack.push(c);
					}
				}
				else{
					if(stack.peek().equals("^")){
						q[i++] = stack.pop();
						stack.push(c);
					}
					else{
						stack.push(c);
					}
				}
			}
			else  // if character is operand just add it to input string
			{
				q[i++] = c;
			}
		}

		return q;
	}
	
	
	
	/**
	 * @param q  post fix string array
	 * @return result after evaluating post fix string
	 */
	public static double  calculate_postfix(String[] q) throws Exception{
		double result = 0;
		Stack<Double> stack = new Stack<Double>();
		stack.push(0.0);
		for(String c: q){
			if(c.equals("-") ||c.equals( "+") || c.equals("/") ||c.equals("*") || c.equals("^"))
			{
				try{
					double op2 = stack.pop();
					double op1 = stack.pop();
					
					if(c.equals("+")){
						stack.push(op1+op2);
					}
					else if(c.equals("-")){
						stack.push(op1-op2);
					}
					else if(c.equals("*")){
						// System.out.println(op1*op2);
						stack.push(op1*op2);
					}
					else if(c.equals("/")){
						stack.push(op1/op2);
					}
					else{
						stack.push(Math.pow(op1, op2));
					}
				}
				catch(Exception e){
					System.out.println(e.getMessage());
					throw(new Exception("Invalid Input"));
				}
			}
			else
			{
				stack.push(Double.valueOf(c));
			}
		}
		result = stack.pop();
		return result;
	}
	
	
	
	/**
	 * 
	 * breaks infix string to string array of its operands and operators
	 * eg. "a+b*c*(d+e)" to {"a","+","b","*","c","(","d","+","e",")"} 
	 * 
	 * @param infix_string (String) a correct infix string
	 * @return (String[]) array of string(operands and operators)
	 */
	public static String[] toStringArray(String infix_string){
		
		ArrayList<String> arrl = new ArrayList<String>();
		String out = "";
		for(char c: infix_string.toCharArray()){
			if(c == '+' || c == '-' || c == '*' || c == '^' || c == '/' || c == '(' || c == ')')
			{
				if(out != "")
				{
					if(out.endsWith("%")){
						double num = Double.valueOf(out.substring(0, out.length()-1));
						num = num/100;
						out = String.valueOf(num);
					}
					arrl.add(out);
				}
				arrl.add(String.valueOf(c));
				out = "";
			}
			else{
				out = out + String.valueOf(c);
			}
		}
		if(out != "")
		{	
			if(out.endsWith("%")){
				double num = Double.valueOf(out.substring(0, out.length()-1));
				num = num/100;
				out = String.valueOf(num);
			}
			arrl.add(out);
		}
		String[] str = new String[arrl.size()];
		
		for(int i=0;i<arrl.size();i++){
			str[i] = arrl.get(i).toString();
		}
		return str;
	}
	
	/**
	 * evaluates given infix string
	 * 
	 * @param infix_string  infix string to evaluate
	 * @return (string) value after evaluation [ERROR if any exception occurred]
	 */
	public static String calculate(String infix_string){
		String result= "";
		String r1[] = toStringArray(infix_string);
		String r2[] = toPostFix(r1);

		try{
			result = remove_extra_precision(String.valueOf(calculate_postfix(r2)));
		}
		catch(Exception e){
			result = "ERROR";
		}
		return result;
	}
	
	
	
	
	/**
	 * 
	 * Removes extra zeros in decimal 
	 * eg. for 0.10000000000004 it will return 0.10 
	 * 
	 * @param value (String) floating point value 
	 * @return result by reducing decimal value if it has consecutive 6 zeros
	 */
	public static String remove_extra_precision(String value){
		String result = "";
		boolean is_decimal = false;
		boolean is_prev_zero = false;
		int count_zero = 0;  // counter for zero
		int length= 0;
		if(value.contains("E")){
			return value;
		}
		else{
			for(char c: value.toCharArray()){
				if(is_decimal){
					if( c == '0'){
						if(is_prev_zero){
							if(count_zero == 6){
								// if consecutive number of zero exceeds 6
								result =  result.substring(0, length-5);
								break;
							}
							count_zero++;
							result+=String.valueOf(c);
						}
						else{
							is_prev_zero = true;
							// Zero detected"
							count_zero++;
						}
					}
					else{
						is_prev_zero = false;
						// Other Number Detected
					}
				}
				else{
					if(c == '.'){
						// Given is decimal"
						is_decimal = true;
					}
				}
				result+=String.valueOf(c);
				length++;
			}
			
			return result;
		}
	}
	
	
	
	/**
	 * validates the infix string
	 * 
	 * @param infix_string  (String) infix string to evaluate
	 * @return (boolean) true if it is valid expression else false
	 */
	public static boolean validate(String infix_string){
		String[] postfix_string_array = toStringArray(infix_string);
		boolean valid = false;
		int operands = 0;
		int operators = 0;
		int brackets = 0;
		for(String c: postfix_string_array){
			if(c.equals("-") ||c.equals( "+") || c.equals("/") ||c.equals("*") || c.equals("^")){
				operators++;
			}
			else if(c.equals("(") || c.equals(")")){
				brackets++;
			}
			else{
				operands++;
			}
		}
		if((operands-operators == 1) && (brackets%2 == 0)){
			valid = true;
		}
		return valid;
		
	}

}
