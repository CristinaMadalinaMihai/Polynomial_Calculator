package model;

import java.util.ArrayList;
import java.util.Collections;

public class Polynomial {
	
	private ArrayList<Monomial> polynomial = new ArrayList<Monomial>();

	/* Constructor */
	public Polynomial(ArrayList<Monomial> polynomial) {
		this.polynomial = polynomial;
	}
	
	public Polynomial() {
		
	}

	/* get polynomial */
	public ArrayList<Monomial> getPolynomial() {
		return polynomial;
	}

	/* set polynomial */
	public void setPolynomial(ArrayList<Monomial> polynomial) {
		this.polynomial = polynomial;
	}
	
	/* rank */
	public int getRank() {
		int max = 0;
		for (Monomial i : polynomial) {
			if (i.getPower() > max)
				max = i.getPower();
		}
		return max;
	}
	
	/* compute sum */
	public Polynomial computeSum(Polynomial polynomial2){
		int maxRank = Math.max(this.getRank(), polynomial2.getRank());
		Polynomial sum = new Polynomial();
		
		for (int i = 0; i <= maxRank; i ++) {
			sum.polynomial.add(new Monomial(0, i));
		}
	
		for (Monomial i : polynomial) {
			 sum.polynomial.get(i.getPower()).setDoubleCoefficient(
					 sum.polynomial.get(i.getPower()).getDoubleCoefficient() + i.getDoubleCoefficient()  
					 ); 
		}
		
		for (Monomial i : polynomial2.polynomial) {
			sum.polynomial.get(i.getPower()).setDoubleCoefficient(
					sum.polynomial.get(i.getPower()).getDoubleCoefficient() + i.getDoubleCoefficient()
					);
		}
		
		this.polynomial = sum.polynomial;
		return this;
	}
	
	/* compute difference */
	public Polynomial computeDifference(Polynomial polynomial2){
		int maxRank = Math.max(this.getRank(), polynomial2.getRank());
		Polynomial difference = new Polynomial();
		
		for (int i = 0; i <= maxRank; i ++) {
			difference.polynomial.add(new Monomial(0, i)); 
		}
	
		for (Monomial i : polynomial) {
			 difference.polynomial.get(i.getPower()).setDoubleCoefficient(
					 difference.polynomial.get(i.getPower()).getDoubleCoefficient() + i.getDoubleCoefficient()  
					 ); 
		}
		
		for (Monomial i : polynomial2.polynomial) {
			difference.polynomial.get(i.getPower()).setDoubleCoefficient(
					difference.polynomial.get(i.getPower()).getDoubleCoefficient() - i.getDoubleCoefficient()
					);
		}
		
		this.polynomial = difference.polynomial;
		return this;
	}
	
	/* compute multiplication */
	public Polynomial computeMultiplication(Polynomial polynomial2) {
		int maxRank = this.getRank() + polynomial2.getRank();
		Polynomial multiplication = new Polynomial();
		
		for (int i = 0; i <= maxRank; i++) {
			multiplication.polynomial.add(new Monomial(0, i));
		}
		
		for (Monomial i : polynomial) {
			for (Monomial j : polynomial2.polynomial) {
				int power = i.getPower() + j.getPower();
				double doubleCoefficient = i.getDoubleCoefficient() * j.getDoubleCoefficient();
				multiplication.polynomial.get(power).setDoubleCoefficient(
							multiplication.polynomial.get(power).getDoubleCoefficient() + doubleCoefficient
						);
			}
		}
		this.polynomial = multiplication.polynomial;
		return this;
	}
	
	/* compute division */
	public Polynomial transformInStandardForm() {
		Polynomial newP = new Polynomial();
		
		for (int i = 0; i <= this.getRank(); i++) {
			newP.polynomial.add(new Monomial(0, i));
		}
		
		for (Monomial i : polynomial) {
			newP.polynomial.get(i.getPower()).setDoubleCoefficient(
					newP.polynomial.get(i.getPower()).getDoubleCoefficient() + i.getDoubleCoefficient()
					);
		}
						
		return newP;
	}
	
	public Polynomial computeDivision(Polynomial polynomial2, int returnRequest) {		
		ArrayList<Monomial> divisionMonomial = new ArrayList<Monomial>();
		Polynomial polynomial1 = new Polynomial();
		polynomial1.polynomial = polynomial;
		polynomial1.setPolynomial(polynomial1.transformInStandardForm().polynomial);
		polynomial2.setPolynomial(polynomial2.transformInStandardForm().polynomial);
		Polynomial reminder = new Polynomial();
		while (polynomial1.getRank() >= polynomial2.getRank()) {
			double doubleCoefficient = polynomial1.polynomial.get(polynomial1.getRank()).getDoubleCoefficient() / polynomial2.polynomial.get(polynomial2.getRank()).getDoubleCoefficient();
			int power = polynomial1.polynomial.get(polynomial1.getRank()).getPower() - polynomial2.polynomial.get(polynomial2.getRank()).getPower();			
			Monomial monomial = new Monomial (doubleCoefficient, power);
			divisionMonomial.add(monomial);
			ArrayList<Monomial> mon = new ArrayList<Monomial>();
			mon.add(monomial);
			Polynomial monPoly = new Polynomial();
			monPoly.setPolynomial(mon);
			monPoly.computeMultiplication(polynomial2);	
			polynomial1.computeDifference(monPoly);
			polynomial1.polynomial.remove(polynomial1.getRank());
			reminder.setPolynomial(polynomial1.polynomial);
		}
		if (returnRequest == 0) { // Q
			this.setPolynomial(divisionMonomial);
			return this;
		} else {
			this.setPolynomial(reminder.polynomial);
			return this;} // R
	}
	
	/* compute differentiation */
	public Polynomial computeDifferentiation() {
		for (Monomial i : this.polynomial) {
			if (i.getDoubleCoefficient() != 0) {
				i.setDoubleCoefficient(i.getDoubleCoefficient() * i.getPower());
				i.setPower(i.getPower() - 1);
			}
		}
		
		return this;
	}
	
	/* compute integration */
	public Polynomial computeIntegration() {
		for (Monomial i : this.polynomial) {
			i.setDoubleCoefficient(i.getDoubleCoefficient() / ( i.getPower() + 1 ));
			i.setPower(i.getPower() + 1);
		}
		return this;
	}

	@Override 
	public String toString() {
		String expression = new String("");
		for (Monomial i : this.polynomial) {
			if (i.getDoubleCoefficient() > 0.0) { // positive coefficient
				if (i.getDoubleCoefficient() > 1.0 || i.getDoubleCoefficient() < 1.0) { // coefficient != 1
					expression += "+" + Double.toString(i.getDoubleCoefficient());
					if (i.getPower() == 1) { expression += "X"; // power = 1 
					} else if (i.getPower() > 1) { expression += "X^" + Integer.toString(i.getPower()); } // power > 1
				} else { // coefficient = 1
					if (i.getPower() == 1) { expression += "+X"; // power = 1 
					} else if (i.getPower() > 1) { expression += "+X^" + Integer.toString(i.getPower()); } // power > 1
					else { expression += "+1"; } // power = 0
				}
			} else if (i.getDoubleCoefficient() < 0.0) { // negative coefficient
				if (i.getDoubleCoefficient() > -1.0 || i.getDoubleCoefficient() < -1.0) { // coefficient != -1
					expression += Double.toString(i.getDoubleCoefficient());
					if (i.getPower() == 1) { expression += "X"; // power = 1 
					} else if (i.getPower() > 1) { expression += "X^" + Integer.toString(i.getPower()); } // power > 1
				} else { // coefficient = -1
					if (i.getPower() == 1) { expression += "-X"; // power = 1 
					} else if (i.getPower() > 1) { expression += "-X^" + Integer.toString(i.getPower()); } // power > 1
					else { expression += "-1"; } // power = 0
					}
				}
			} 
		return expression;
	}
	
}
