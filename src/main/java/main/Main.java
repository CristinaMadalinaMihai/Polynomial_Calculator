package main;

import view.CalculatorView;
import model.CalculatorModel;
import model.Polynomial;
import model.Monomial;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.CalculatorController;

public class Main {
	
	public static void main(String[] args) {
		
		CalculatorModel model = new CalculatorModel();
		CalculatorView view = new CalculatorView(model); 
		CalculatorController controller = new CalculatorController(model, view);
		
		view.setVisible(true);
		view.setLocationRelativeTo(null);
	
	}

}
