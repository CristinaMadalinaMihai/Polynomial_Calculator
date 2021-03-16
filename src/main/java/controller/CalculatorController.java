package controller;

import view.CalculatorView;
import model.*;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.awt.event.*;

public class CalculatorController extends JFrame{
	
	// it interacts with both Model & View
	private CalculatorModel m_model;
	private CalculatorView m_view;
	
	/* Constructor */ //================================== constructor
	public CalculatorController (CalculatorModel model, CalculatorView view) {
		m_model = model;
		m_view = view;

		view.addComputeResultActionListener(new ComputeResultListener());
		view.addDifferentiateActionListener(new DifferentiateListener());
		view.addIntegrateActionListener(new IntegrateListener());
	}
	
	/* compute result button control */
	class ComputeResultListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String firstPolynomial = "";
			String secondPolynomial = ""; 
			try {
				firstPolynomial = m_view.getUserInput1();
				secondPolynomial = m_view.getUserInput2();
				
				Polynomial firstPoly = matchRegEx(firstPolynomial); // transform String to Polynomial
				Polynomial secondPoly = matchRegEx(secondPolynomial); // transform String to Polynomial
				String operation = m_view.getUserOperation();
				
				m_model.setValue(firstPoly, secondPoly, operation);
				if (operation != "/") {
					m_view.setOutput(m_model.getValue());
				} else {
					m_view.setOutput("Q: " + m_model.getValue() + " R: " + m_model.getReminderValue());
				}
				
			} catch (Exception e1){
				m_view.showError("Select operation");
			}
			
		}
	}
	
	/* compute differentiation button control */
	class DifferentiateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String firstPolynomial = "";
			try {
				firstPolynomial = m_view.getUserInput1();				
				Polynomial firstPoly = matchRegEx(firstPolynomial); // transform String to Polynomial
				firstPoly.computeDifferentiation();
				m_model.setStringValue(firstPoly.toString()); 
				m_view.setOutput(firstPoly.toString()); 
			} catch (Exception e1){
				m_view.showError("Bad input!");
			}
			
		}
	}
	
	/* compute integration button control */
	class IntegrateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String firstPolynomial = "";
			try {
				firstPolynomial = m_view.getUserInput1();				
				Polynomial firstPoly = matchRegEx(firstPolynomial); // transform String to Polynomial
				firstPoly.computeIntegration();
				m_model.setStringValue(firstPoly.toString()); 
				m_view.setOutput(firstPoly.toString()); 
			} catch (Exception e1){ 
				m_view.showError("Bad input!");
			}
			
		}
	}
	
	/* RegEx */	
	class Coefficient {
	    protected double coeff;
	    Coefficient(double coeff) {
	        this.coeff = coeff;
	    }
	    public void setName(double coeff) {
	        this.coeff = coeff;
	    }
	    public double getName() {
	        return this.coeff;
	    }
	}
	
	class Power {
	    protected int pow;
	    Power(int pow) {
	        this.pow = pow;
	    }
	    public void setName(int pow) {
	        this.pow = pow;
	    }
	    public int getName() {
	        return this.pow;
	    }
	}
	
	public void signedNumberCoefficient(String[] arrOfStr, Coefficient coefficient, Power power) {
		if (arrOfStr[0].charAt(0) == '+') {
			if (Double.parseDouble(arrOfStr[0].substring(1)) == 0) {
				JOptionPane.showMessageDialog(rootPane, "Wrong input.\nNo 0 coefficients allowed", "Error message", JOptionPane.ERROR_MESSAGE);
			} else {
				coefficient.setName(Double.parseDouble(arrOfStr[0].substring(1)));
			}
		} else if (arrOfStr[0].charAt(0) == '-') {
			if (Double.parseDouble(arrOfStr[0].substring(1)) == 0) {
				JOptionPane.showMessageDialog(rootPane, "Wrong input.\nNo 0 coefficients allowed", "Error message", JOptionPane.ERROR_MESSAGE);
			} else {
				coefficient.setName(-Double.parseDouble(arrOfStr[0].substring(1)));
			}
		} else {
			if (Double.parseDouble(arrOfStr[0]) == 0) {
				JOptionPane.showMessageDialog(rootPane, "Wrong input.\nNo 0 coefficients allowed", "Error message", JOptionPane.ERROR_MESSAGE);
			} else {
				coefficient.setName(Double.parseDouble(arrOfStr[0]));
			}
		}
		if (arrOfStr[1].length() == 0) {
			power.setName(1);
		} else {
			power.setName(Integer.parseInt(arrOfStr[1]));
		}
	}
	
	public void singleDecimalCoefficient(String[] arrOfStr, Coefficient coefficient, Power power) {
		if (arrOfStr[0].charAt(0) == '+') {
			coefficient.setName(1.0);
		} else if (arrOfStr[0].charAt(0) == '-') {
			coefficient.setName(-1);
		} else {
			if (Double.parseDouble(arrOfStr[0])  == 0) {
				JOptionPane.showMessageDialog(rootPane, "Wrong input.\nNo 0 coefficients allowed", "Error message", JOptionPane.ERROR_MESSAGE);
			} else {
				coefficient.setName(Double.parseDouble(arrOfStr[0]));
			}
		}
		if (arrOfStr[1].length() == 0) {
			power.setName(1);
		} else {
			power.setName(Integer.parseInt(arrOfStr[1]));
		}
	}
	
	public void noCoefficient(String[] arrOfStr, Coefficient coefficient, Power power) {
		coefficient.setName(1);            
		if (arrOfStr[1].length() == 0) {
			power.setName(1);
		} else {
			power.setName(Integer.parseInt(arrOfStr[1]));
		}
	}
	
	public void noPowerMonomial(String monomial, Coefficient coefficient, Power power) {
		if (monomial.length() == 1) { // monomial form : x
			coefficient.setName(1);
		} else
		if (monomial.length() == 2) { // monomial form: either -x, or +x
			if (monomial.charAt(0) == '-') {
				coefficient.setName(-1);
			} else if (monomial.charAt(0) == '+') {
				coefficient.setName(1);
			}
		} else {
			if (Double.parseDouble(monomial.substring(0, monomial.length() - 1)) == 0) {
				JOptionPane.showMessageDialog(rootPane, "Wrong input.\nNo 0 coefficients allowed", "Error message", JOptionPane.ERROR_MESSAGE);
			} else {
			coefficient.setName(Double.parseDouble(monomial.substring(0, monomial.length() - 1)));
			}
		}
		power.setName(1);
	}

	public void getMonomial(String monomial, Coefficient coefficient, Power power) {
		boolean x = false;
		int i = 0;
		char[] chars = monomial.toCharArray();
		for (char ch : chars) {
			if (ch == 'x' || ch == 'X') {
				x = true;
				break;
			}
			i++;
		}
		int len = i + 1;
		if (x == false) { // the monomial is just a number
			if (Double.parseDouble(monomial) == 0) {JOptionPane.showMessageDialog(rootPane, "Wrong input.\nNo 0 coefficients allowed", "Error message", JOptionPane.ERROR_MESSAGE);} else {
			coefficient.setName(Double.parseDouble(monomial));
			power.setName(0);}
		} else if (monomial.length() == len) { // monomial pattern : -aX
			noPowerMonomial(monomial, coefficient, power);
		} else {
			String[] arrOfStr = monomial.split("[x]{1}\\^{1}|[X]{1}\\^{1}", 2); // split the monomial of form : ax^b, where a and b are integers
			if (arrOfStr[0].length() == 0) { // no sign or no coefficient ----> for the the first term
				noCoefficient(arrOfStr, coefficient, power);
			} else if (arrOfStr[0].length() == 1) { // sign or one decimal number 
				singleDecimalCoefficient(arrOfStr, coefficient, power);
			} else if (arrOfStr[0].length() > 1) { // just the number OR (sign & number)
				signedNumberCoefficient(arrOfStr, coefficient, power);
			} 
		}
	}
	
	public Polynomial matchRegEx (String input){
		String pattern = "(\\A[-+]?(\\d*)?([xX]\\^?(\\d*)?)?)|([-+]{1}(\\d*)?([xX]\\^?(\\d*)?)?)"; /* create pattern to match input string */ // it matches input of form: x-x^7-X^10-x^2-3x^2+3x+3 
		String patternError = "[^-0-9xX^+]|(x{2,})|(X{2,})|(-{2,})"; // no spaces or other characters, no double "x", no double "-" allowed 
		Pattern r = Pattern.compile(pattern); /* create pattern object */
		Pattern rError = Pattern.compile(patternError);
		Matcher m = r.matcher(input); /* create matcher object */
		Matcher mError = rError.matcher(input);
		boolean ok = true;
		while (mError.find()) { // check the correctness of the user input
			ok = false;
		}
		ArrayList<Monomial> polynomial = new ArrayList<Monomial>(); // create polynomial to return
		if (ok == false) {
			JOptionPane.showMessageDialog(rootPane, "Wrong input\nRemember:\n-> No spaces allowed.\n-> No zeros allowed.\n-> Polynomial must have the following similar form: x-x^7-X^10+1-x-x^2+3x^2+3x+3", "Error message", JOptionPane.ERROR_MESSAGE);
		} else {
			while (m.find()) {
				System.out.println("gr(0)= " + m.group(0));
				String monomial = m.group(0);
				Coefficient coefficient = new Coefficient(0);
				Power power = new Power(0);
				getMonomial(monomial, coefficient, power);
				Monomial mono = new Monomial(coefficient.getName(), power.getName());
				polynomial.add(mono);
			}
		}
		Polynomial poly = new Polynomial();
		poly.setPolynomial(polynomial);
		return poly;
	}
}