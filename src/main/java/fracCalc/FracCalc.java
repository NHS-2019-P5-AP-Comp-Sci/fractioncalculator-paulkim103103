/**
 * @author Mr. Rasmussen
 */

	package fracCalc;
 

	import java.util.Scanner;

	public class FracCalc {

	public static void main(String[] args) {
	// TODO: Read the input from the user and call produceAnswer with an
	// equation
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Paul's Frac Calc!");
		System.out.print("What is your equation? ");
		
		while (true) {
			String input = sc.nextLine();
			if (input.equals("quit")) {
				break;
			} else {
				System.out.println(produceAnswer(input));
				System.out.print("What is your equation? ");
		}
	}
	sc.close();
	}
	// interactive part of the FracCalc where you get to input an equation and get the answer until you hit quit.
	
	public static String incorrectfrac(String value) {
		String fraction = null;
		long complete = 0;
		long whole = 0;
		long denom = 1;

		if (value.contains("_")) {
			String values[] = value.split("_");
			complete = Long.parseLong(values[0]);
			fraction = values[1];
		} else if (value.contains("/")) {
			fraction = value;
		} else {
			complete = Long.parseLong(value);
	}

		if (fraction != null) {
			whole = wholeeratorz(fraction);
			denom = denominatorz(fraction);
	
		if (complete < 0) {
			whole *= -1;
		}
	}
	whole = denom * complete + whole;
	
		if (denom < 0) {
			denom *= -1;
			whole *= -1;
	}
	
		return stringconvers(whole, denom);
	}
	//if statements determining a fraction.
	
	public static String stringconvers(long whole, long denom) {
		return whole + "/" + denom;
	}

	public static long wholeeratorz(String fraction) {
		if (fraction.contains("/")) {
			String values[] = fraction.split("/");
			return Long.parseLong(values[0]);
		} else {
			return Long.parseLong(fraction);
		}
	}
	//focuses on the numerators
	
	public static long denominatorz(String fraction) {
		if (fraction.contains("/")) {
			String values[] = fraction.split("/");
			return Long.parseLong(values[1]);
		} else {
		return 1;
		}
	}
	//focuses on the denominators
	

	public static String combin(String fraction) {
		int whole_sign = 1, den_sign = 1;
		long whole = wholeeratorz(fraction);
		long denom = denominatorz(fraction);
		if (whole < 0) {
			whole_sign = -1;
			whole *= -1;
		}
		if (denom < 0) {
			den_sign = -1;
			denom *= -1;
		}
		int sign = whole_sign * den_sign;

		long complete = whole / denom;
		whole = whole % denom;
		complete *= sign;
		whole *= sign;
		return stringcon(complete, whole, denom);
	}
	
	public static String stringcon(long complete, long whole, long denom) {
		if (whole == 0) {
			return Long.toString(complete);
		} else if (complete == 0) {
			return "" + whole + "/" + denom;
		} else {
			if (complete > 0) {
				return "" + complete + "_" + whole + "/" + denom;
			} else {
				return "" + complete + "_" + -whole + "/" + denom;
			}
		}
	}
	
	public static String reduce(long whole, long denom) {
		long gcd = combination.gcd(whole, denom);
		
		if (gcd < 0) {
			gcd *= -1;
		}	
		
		whole /= gcd;
		denom /= gcd;
		return stringconvers(whole, denom);
	}
	//reduces using Greatest common denominator
	
	public static class combination {
		public long complete = 0;
		public long whole = 0;
		public long denom = 1;
		
		public combination reduced() {
			combination newest = second();
			long gcd = gcd(whole, denom);
		
			if (gcd < 0) {
				gcd *= -1;
			}
			newest.whole /= gcd;
			newest.denom /= gcd;
			return newest;
		}
	//reduces using Greatest common denominator
		
	public static combination fromString(String value) {
		combination newest = new combination();
		String fraction = null;
	
		if (value.contains("_")) {
			String values[] = value.split("_");
			newest.complete = Long.parseLong(values[0]);
			fraction = values[1];
		} else if (value.contains("/")) {
			fraction = value;
		} else {
			newest.complete = Long.parseLong(value);
	}

	if (fraction != null) {
		if (fraction.contains("/")) {
			String values[] = fraction.split("/");
			newest.whole = Long.parseLong(values[0]);
			newest.denom = Long.parseLong(values[1]);
		if (newest.complete < 0) {
			newest.whole *= -1;
			}
		}
	}
	return newest;
	}
	//string combinations
	
	public String description() {
	return "complete:" + complete + " whole:" + whole + " denom:" + denom;
	}

	public String toString() {
		if (whole == 0) {
			return Long.toString(complete);
		} else if (complete == 0) {
			return "" + whole + "/" + denom;
		} else {
			if (complete > 0) {
			return "" + complete + "_" + whole + "/" + denom;
			} else {
				return "" + complete + "_" + -whole + "/" + denom;
				}
			}
	}
	//converts to string
	
	public combination second() {
		combination newest = new combination();
		newest.complete = complete;
		newest.whole = whole;
		newest.denom = denom;
		return newest;
	}

	public combination improper() {
		combination newest = new combination();
		newest.denom = denom;
		newest.whole = denom * complete + whole;
	
		if (newest.denom < 0) {
			newest.denom *= -1;
			newest.whole *= -1;
		}
		return newest;
	}
	//changes improper fractions to usuable ones
	
	public static long gcd(long a, long b) {
		while (true) {
			if (b == 0) {
				return a;
			} else {
				long temp = a % b;
				a = b;
				b = temp;
			}
		}
	}

	public combination reciprocal() {
		combination p = improper();
		long temp = p.denom;
		p.denom = p.whole;
		p.whole = temp;
		if (p.denom < 0) {
			p.denom *= -1;
			p.whole *= -1;
	}
		return p;
	}
	//deals with reciprocals
	
	public combination add(combination other) {
		combination p = improper();
		other = other.improper();
		p.whole *= other.denom;
		p.whole += other.whole * p.denom;
		p.denom *= other.denom;
		return p.reduced().mixed();
	}
	//addition procedure when there is a + sign
	
	public combination multiply(combination other) {
		combination p = improper();
		other = other.improper();
		p.whole *= other.whole;
		p.denom *= other.denom;
		return p.reduced().mixed();
	}
	//multiplication procedure when there is a * sign
	
	public combination div(combination other) {
		return multiply(other.reciprocal());
	}
	//division procedure
	
	public combination sub(combination other) {
		other = other.improper();
		other.whole *= -1;
		return add(other);
	}

	public combination mixed() {
		combination newest = new combination();
		int complete_sign = 1, whole_sign = 1, den_sign = 1;
		
		if (complete < 0) {
		complete_sign = -1;
		complete *= -1;
		}
		if (whole < 0) {
			whole_sign = -1;
			whole *= -1;
		}
		if (denom < 0) {
			den_sign = -1;
			denom *= -1;
	}
	
		int sign = complete_sign * whole_sign * den_sign;
		newest.complete = complete + whole / denom;
		newest.whole = whole % denom;
		newest.denom = denom;
		newest.complete *= sign;
		newest.whole *= sign;
		return newest;
	}
	
	}
	
	public static String produceAnswer(String input) {
		String values[] = input.split(" ");
		String improper = incorrectfrac(values[0]);
		long whole = wholeeratorz(improper);
		long denom = denominatorz(improper);
		
		for (int ii = 1; ii < values.length; ii += 2) {
			String improper2 = incorrectfrac(values[ii + 1]);
			long whole2 = wholeeratorz(improper2);
			long denom2 = denominatorz(improper2);

	switch (values[ii]) {
		case "+":
			whole *= denom2;
			whole += whole2 * denom;
			denom *= denom2;
		break;
	
		case "-":
			whole *= denom2;
			whole -= whole2 * denom;
			denom *= denom2;
		break;
	
		case "*":
			whole *= whole2;
			denom *= denom2;
		break;
	
		case "/":
			whole *= denom2;
			denom *= whole2;
		break;
		}
	}
	
		return combin(reduce(whole, denom));
	}
	//all other static methods go back to this, producing the final answer for it to be shown in main.
	
	
	
	
	
	//** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
		// test your code
		// This function takes a String 'input' and produces the result
		//
		// input is a fraction string that needs to be evaluated. For your program, this
		// will be the user input.
		// e.g. input ==> "1/2 + 3/4"
		//
		// The function should return the result of the fraction after it has been
		// calculated
		// e.g. return ==> "1_1/4"
	}
