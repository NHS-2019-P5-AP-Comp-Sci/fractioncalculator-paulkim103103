/**
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.Scanner;
public class FracCalc {
    public static void main(String[] args)
    {
		Scanner s = new Scanner(System.in);
		
		System.out.print("What is your equation? ");
		String response = s.nextLine();
		while(!response.equalsIgnoreCase("quit")) {
		System.out.println(produceAnswer(response));
		System.out.print("What is your equation? ");
		response = s.nextLine();
    	// TODO: Read the input from the user and call produceAnswer with an equation
    }
    }
		
    public static String produceAnswer(String input){
    	String frac = input;
    	String firstfrac = frac.substring(0, frac.indexOf(' '));
    	frac = frac.substring(frac.indexOf(' ') + 1);
    	String operator = frac.substring(0, frac.indexOf(' '));
    	frac = frac.substring(frac.indexOf(' ') + 1);
    	String secFrac = frac;
    	
    	String Whole2 = findWhole(secFrac);
    	String Num2 = findNum(secFrac);
    	String Denom2 = findDenom(secFrac);

    	String answer2 = "Whole:" + Whole2 + " numerator:" + Num2 + " denominator:" + Denom2;
    	return answer2;
    }
    	
    public static String findWhole(String str) {
    	if(str.contains("_")) {
    		return str.substring(0, str.indexOf('_'));
    	}else if(str.contains("/")) {
    		return "0";
    	}else return str;
    }
    
    public static String findNum(String str) {
    	if(str.contains("_")) {
    		return str.substring(str.indexOf('_') + 1, str.indexOf('/'));
    	}else if(str.contains("/")) {
    		return str.substring(0, str.indexOf('/'));
    	}else {
    		return "0";
    	}
    }
    
    public static String findDenom(String str) {
    	if(str.contains("/")) {
    		return str.substring(str.indexOf("/")+1);
    	}else {
    		 return "1";
    		
    	}
    }
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.//      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
}
    // TODO: Fill in the space below with any helper methods that you think you will need


