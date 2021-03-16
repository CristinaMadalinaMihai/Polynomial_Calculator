package model;

public class Monomial implements Comparable<Monomial>{
	
	private double doubleCoefficient;
	private int power;
	
	public Monomial (double doubleCoefficient, int power) {
		this.doubleCoefficient = doubleCoefficient;
		this.power = power;
		//this.doubleCoefficient = doubleCoefficient;
	}
	
	public Monomial(Integer coefficient2, int power2) {
		// TODO Auto-generated constructor stub
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int newPower) {
		this.power = newPower;
	}
	
	public double getDoubleCoefficient() {
		return doubleCoefficient;
	}
	
	public void setDoubleCoefficient(double newDoubleCoefficient) {
		this.doubleCoefficient = newDoubleCoefficient;
	}

	public int compareTo(Monomial o) {
		return o.getPower() - this.getPower();
	}

}
