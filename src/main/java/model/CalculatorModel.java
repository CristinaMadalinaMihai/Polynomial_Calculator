package model;

import javax.swing.JFrame; 
import javax.swing.JOptionPane;

public class CalculatorModel extends JFrame {

	// constants
	private static final int INITIAL_VALUE = 0;
	private String m_result;
	private String m_result_reminder;

	/* Constructor */ // ===================================== constructor
	public CalculatorModel() {
		reset();
	}

	/* reset to initial value */ // =========================== reset
	public void reset() {
		m_result = Integer.toString(INITIAL_VALUE);
	}

	/* set the result string */ // =========================== set result
	public void setValue (Polynomial firstPoly, Polynomial secondPoly, String operation) {
		switch (operation) {
		case "+": 
			firstPoly.computeSum(secondPoly);
			break;
		case "-":
			firstPoly.computeDifference(secondPoly);
			break;
		case "*":
			firstPoly.computeMultiplication(secondPoly);
			break;
		case "/": 
			Polynomial firstPolyCopy = new Polynomial();
			firstPolyCopy.setPolynomial(firstPoly.getPolynomial());
			m_result_reminder = firstPolyCopy.computeDivision(secondPoly, 1).toString();
			setStringReminderValue(m_result_reminder);
			firstPoly.computeDivision(secondPoly, 0);
			break;
		default:
			JOptionPane.showMessageDialog(rootPane, "Select operation!", "Error message", JOptionPane.ERROR_MESSAGE);
		}

		m_result = firstPoly.toString();
		setStringValue(m_result);
	}
	
	public void setStringValue (String stringValue) {
		m_result = stringValue;
	}

	/* get the result string */ // ============================ get result
	public String getValue() {
		return m_result;
	}
	
	public void setStringReminderValue (String stringReminderValue) {
		m_result_reminder = stringReminderValue;
	}
	
	/* get reminder value */
	public String getReminderValue() {
		return m_result_reminder;
	}

}
