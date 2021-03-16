package PT2020.demo.Assignment_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import model.CalculatorModel;
import model.Monomial;
import model.Polynomial;

public class CalculatorTest {
	
	private static CalculatorModel m;
	private static int EXECUTED_TESTS = 0;
	private static int SUCCESSFUL_TESTS = 0;

	public CalculatorTest() {
		System.out.println("constructor");
	}
	
	// BeforeCLass
	public static void setUpBeforeClass() throws Exception { // one time before executing the set of tests
		m = new CalculatorModel(); 
	}

	// AfterClass
	public static void tearDownAfterClass() throws Exception { // one time after executing the set of tests
		System.out.println("There has been executed " + EXECUTED_TESTS + ", from which "+ SUCCESSFUL_TESTS + " were successful!");
	}

	// Before
	public void setUp() throws Exception { // start a new test
		EXECUTED_TESTS++;
	}

	// After 
	public void tearDown() throws Exception {
		System.out.println("End of the current test!");
	}
	
	@Test
	public void testSum() {
		Monomial m1 = new Monomial(2, 5);
		Monomial m2 = new Monomial(1, 7);
		Monomial m3 = new Monomial(9, 9);
		Monomial m4 = new Monomial(1, 4);
		Monomial m5 = new Monomial(2, 7);
		Monomial m6 = new Monomial(1, 10);
		
		ArrayList<Monomial> poly1 = new ArrayList<Monomial>();
		poly1.add(m1);
		poly1.add(m2);
		poly1.add(m3);
		ArrayList<Monomial> poly2 = new ArrayList<Monomial>();
		poly2.add(m4);
		poly2.add(m5);
		poly2.add(m6);
		
		Polynomial p1 = new Polynomial();
		p1.setPolynomial(poly1);
		Polynomial p2 = new Polynomial();
		p2.setPolynomial(poly2);
		
		String sum = "+X^4+2.0X^5+3.0X^7+9.0X^9+X^10";
		assertEquals(sum, p1.computeSum(p2).toString()); 
		SUCCESSFUL_TESTS++;		
	}

	@Test
	public void testDifference() {
		Monomial m1 = new Monomial(2, 5);
		Monomial m2 = new Monomial(1, 7);
		Monomial m3 = new Monomial(9, 9);
		Monomial m4 = new Monomial(1, 4);
		Monomial m5 = new Monomial(2, 7);
		Monomial m6 = new Monomial(1, 10);
		
		ArrayList<Monomial> poly1 = new ArrayList<Monomial>();
		poly1.add(m1);
		poly1.add(m2);
		poly1.add(m3);
		ArrayList<Monomial> poly2 = new ArrayList<Monomial>();
		poly2.add(m4);
		poly2.add(m5);
		poly2.add(m6);
		
		Polynomial p1 = new Polynomial();
		p1.setPolynomial(poly1);
		Polynomial p2 = new Polynomial();
		p2.setPolynomial(poly2);
		
		String difference = "-X^4+2.0X^5-X^7+9.0X^9-X^10";
		assertEquals(difference, p1.computeDifference(p2).toString()); 
		SUCCESSFUL_TESTS++;	
	}
	
	@Test
	public void testMultiplication() {
		Monomial m1 = new Monomial(2, 5);
		Monomial m2 = new Monomial(1, 7);
		Monomial m3 = new Monomial(9, 9);
		Monomial m4 = new Monomial(1, 4);
		Monomial m5 = new Monomial(2, 7);
		Monomial m6 = new Monomial(1, 10);
		
		ArrayList<Monomial> poly1 = new ArrayList<Monomial>();
		poly1.add(m1);
		poly1.add(m2);
		poly1.add(m3);
		ArrayList<Monomial> poly2 = new ArrayList<Monomial>();
		poly2.add(m4);
		poly2.add(m5);
		poly2.add(m6);
		
		Polynomial p1 = new Polynomial();
		p1.setPolynomial(poly1);
		Polynomial p2 = new Polynomial();
		p2.setPolynomial(poly2);
		
		String multiplication = "+2.0X^9+X^11+4.0X^12+9.0X^13+2.0X^14+2.0X^15+18.0X^16+X^17+9.0X^19";
		assertEquals(multiplication, p1.computeMultiplication(p2).toString()); 
		SUCCESSFUL_TESTS++;	
	}
	
	@Test
	public void testDivision() {
		Monomial mm1 = new Monomial(3, 4);
		Monomial mm2 = new Monomial(-4, 3);
		Monomial mm3 = new Monomial(-12, 2);
		Monomial mm4 = new Monomial(5, 0);
		Monomial mm5 = new Monomial(2, 2);
		Monomial mm6 = new Monomial(1, 1);
		
		ArrayList<Monomial> ppoly1 = new ArrayList<Monomial>();
		ppoly1.add(mm1);
		ppoly1.add(mm2);
		ppoly1.add(mm3);
		ppoly1.add(mm4);
		ArrayList<Monomial> ppoly2 = new ArrayList<Monomial>();
		ppoly2.add(mm5);
		ppoly2.add(mm6);
		
		Polynomial pp1 = new Polynomial();
		pp1.setPolynomial(ppoly1);
		Polynomial pp2 = new Polynomial();
		pp2.setPolynomial(ppoly2);
		
		Polynomial pp3 = new Polynomial();
		pp3.setPolynomial(ppoly1);

		Polynomial pp4 = new Polynomial();
		pp4.setPolynomial(ppoly2);
		
		String division = "Q: +1.5X^2-2.75X-4.625 R: +5.0+4.625X";
		assertEquals(division, "Q: " + pp1.computeDivision(pp2, 0).toString() + " R: " + pp3.computeDivision(pp4, 1).toString()); 
		SUCCESSFUL_TESTS++;	
	}
	
	@Test 
	public void testDifferentiation() {
		Monomial mm1 = new Monomial(3, 4);
		Monomial mm2 = new Monomial(-4, 3);
		Monomial mm3 = new Monomial(-12, 2);
		Monomial mm4 = new Monomial(5, 0);
		
		ArrayList<Monomial> ppoly1 = new ArrayList<Monomial>();
		ppoly1.add(mm1);
		ppoly1.add(mm2);
		ppoly1.add(mm3);
		ppoly1.add(mm4);
		
		Polynomial pp1 = new Polynomial();
		pp1.setPolynomial(ppoly1);
		
		String differentiation = "+12.0X^3-12.0X^2-24.0X";
		assertEquals(differentiation, pp1.computeDifferentiation().toString());
		SUCCESSFUL_TESTS++;
	}
	
	@Test
	public void testIntegration() {
		Monomial mm1 = new Monomial(3, 4);
		Monomial mm2 = new Monomial(-4, 3);
		Monomial mm3 = new Monomial(-12, 2);
		Monomial mm4 = new Monomial(5, 0);
		
		ArrayList<Monomial> ppoly1 = new ArrayList<Monomial>();
		ppoly1.add(mm1);
		ppoly1.add(mm2);
		ppoly1.add(mm3);
		ppoly1.add(mm4);
		
		Polynomial pp1 = new Polynomial();
		pp1.setPolynomial(ppoly1);
		
		String integration = "+0.6X^5-X^4-4.0X^3+5.0X";
		assertEquals(integration, pp1.computeIntegration().toString());
		SUCCESSFUL_TESTS++;
	}
	
}
