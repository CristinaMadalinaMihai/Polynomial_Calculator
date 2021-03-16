package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import model.CalculatorModel; 
import model.*;

public class CalculatorView extends JFrame {
	
	// constants
	private static final String MULTIPLICATION_CONSTANT = "1";
	
	// components
	/* text fields : 1st polynomial, 2nd polynomial (if needed), result polynomial */
	private JTextField userInputTF1 = new JTextField(100);
	private JTextField userInputTF2 = new JTextField(100);
	private JTextField userOutputTF3 = new JTextField(100);
	
	/* comboBox: Add, Subtract, Multiply, Divide, Differentiante, Integrate*/
	//private String[] operation = {"Add", "Subtract", "Multiply", "Divide"};
	//private JComboBox selectedOperation = new JComboBox(operation);
	//private JLabel lblText = new JLabel();
	private JComboBox selectedOperation = new JComboBox();
	
	/* labels: enter 1st & 2nd polynomials */
	private JLabel firstPoly = new JLabel("7x10+3x4-2x2-9");
	private JLabel secondPoly = new JLabel();
	private JLabel chooseOperation = new JLabel("Choose operation");
	
	/* button: display result */
	private JButton computeResult = new JButton("Compute result");
	private JButton integrateButton = new JButton("Integrate polynomial");
	private JButton differentiateButton = new JButton("Differentiate polynomial");

	private CalculatorModel m_model;
	
	/* Constructor */ //======================================== constructor
	public CalculatorView (CalculatorModel model){
		//================================================= set up the logic
		m_model = model;
		m_model.setStringValue("0");
		
		//============================================ initialize components
		/* 1st polynomial Text Field initialization + actions */
		firstPoly.setHorizontalAlignment(SwingConstants.RIGHT);
        firstPoly.setText("Enter 1st polynomial:");
        
        userInputTF1.setText("7x10+3x4-2x2-9");
		userInputTF1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userInputTF1MouseClicked(evt);
            }

			private void userInputTF1MouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				userInputTF1.setText("");
				userInputTF1.setText(getUserInput1());
			}
        });
		
		/* 2nd polynomial Text Field initialization */
		secondPoly.setHorizontalAlignment(SwingConstants.RIGHT);
        secondPoly.setText("Enter 2nd polynomial:");

		secondPoly.setHorizontalAlignment(SwingConstants.RIGHT);
		userInputTF2.setText(getUserInput2());
		
		//====== TO BE IMPLEMENTED /* output result initialization */
		userOutputTF3.setText(m_model.getValue());
		userOutputTF3.setEditable(false);
		
		computeResult.setText("Compute Result");
        computeResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computeResultActionPerformed(evt);
            }

			private void computeResultActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				
			}
        });
		
		//====== TO BE IMPLEMENTED /* choose operation Combo Box initialization + actions */ 
		chooseOperation.setHorizontalAlignment(SwingConstants.RIGHT);

		selectedOperation.setModel(new DefaultComboBoxModel(new String[] {"None", "Add", "Subtract", "Multiply", "Divide" }));
        selectedOperation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedOperationActionPerformed(evt);
            }

			public void selectedOperationActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				String userChoice = selectedOperation.getSelectedItem().toString();
				System.out.println(userChoice);
				switch (userChoice) {
					case "None":
						userInputTF2.setEditable(true);
						break;
					case "Add":
						userInputTF2.setEditable(true);
						break;
					case "Subtract":
						userInputTF2.setEditable(true);
						break;
					case "Multiply":
						userInputTF2.setEditable(true);
						break;
					case "Divide":
						userInputTF2.setEditable(true);
						break;
					default:
						JOptionPane.showMessageDialog(rootPane, "Select operation!", "Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
        });
        
        //====== TO BE IMPLEMENTED /* integrate Button initialization + actions */
        integrateButton.setText("Integrate Polynomial");
        integrateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                integrateButtonActionPerformed(evt);
            }

			private void integrateButtonActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				
			}
        });
        

      //====== TO BE IMPLEMENTED /* differentiate Button initialization + actions */
        differentiateButton.setText("Differentiate polynomial");
        differentiateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                differentiateButtonActionPerformed(evt);
            }

			private void differentiateButtonActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				
			}
        });
        
		// layout the components
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(computeResult, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(secondPoly, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userOutputTF3, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userInputTF2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chooseOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(selectedOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(firstPoly, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(differentiateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(userInputTF1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                                    .addComponent(integrateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userInputTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstPoly, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(differentiateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(integrateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectedOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(secondPoly, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userInputTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userOutputTF3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(computeResult, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        ); 
        pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/* get user 1st input */
	public String getUserInput1() {
		return userInputTF1.getText();
	}
	
	/* get user 2nd polynomial */
	public String getUserInput2() {
		return userInputTF2.getText();
	}
	
	/* get user operation */
	public String getUserOperation() {
		String userChoice = selectedOperation.getSelectedItem().toString();
		String operation = "";
		switch (userChoice) {
			case "Add":
				userInputTF2.setEditable(true);
				operation = "+";
				break;
			case "Subtract":
				userInputTF2.setEditable(true);
				operation = "-";
				break;
			case "Multiply":
				userInputTF2.setEditable(true);
				operation = "*";
				break;
			case "Divide":
				userInputTF2.setEditable(true);
				operation = "/";
				break;
			default:
				JOptionPane.showMessageDialog(rootPane, "Select operation!", "Error message", JOptionPane.ERROR_MESSAGE);
		}
		return operation;
	}
	
	/* give computed output to the user */
	public void setOutput(String newResult) {
		userOutputTF3.setText(newResult);
	}
	
	//====== TO BE IMPLEMENTED /* compute result Button actions */
	public void addComputeResultActionListener(ActionListener evt) {                                              
        // TODO add your handling code here:
		computeResult.addActionListener(evt);
    }  
	
	//====== TO BE IMPLEMENTED /* differentiate polynomial Button actions */
	public void addDifferentiateActionListener(ActionListener evt) {
		differentiateButton.addActionListener(evt);
	}
	 
	//====== TO BE IMPLEMENTED /* integrate polynomial Button actions */
	public void addIntegrateActionListener(ActionListener evt) {
		integrateButton.addActionListener(evt);
	}
	
	//====== TO BE IMPLEMENTED /* select operation ComboBox actions */
	public void addOperationActionListener(ActionListener evt) {
		selectedOperation.addActionListener(evt);
	}
	
	public void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}

}
