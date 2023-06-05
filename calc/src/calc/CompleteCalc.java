package calc;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CompleteCalc extends JFrame implements ActionListener {

	JLabel accumulator;
	JPanel panel;

	String[] numbers = { "0", "CLR", "=", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	String[] operators = { "+", "-", "*", "/" };

	String gee;
	JButton[] operatorbutton = new JButton[operators.length];
	JButton[] button = new JButton[numbers.length];

	Font F1 = new Font("Serif", Font.BOLD, 32);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CompleteCalc cal = new CompleteCalc();
	}

	CompleteCalc() {
		setTitle("Calculator");
		setVisible(true);
		setSize(350, 450);
		setLayout(new GridLayout(2, 1));

		accumulator = new JLabel();
		accumulator.setOpaque(true);
		accumulator.setFont(F1);
		gee = accumulator.getText();
		accumulator.setBackground(Color.gray);
		add(accumulator);

		panel = new JPanel(new GridLayout(4, 4));
		panel.setOpaque(true);
		panel.setBackground(Color.gray);
		add(panel);

		buttons();
	}

	public void buttons() {

		int value = 0;
		int limit = 2;
		int jvalue = 0;
		int jlimit = 1;
		for (int k = 1; k <= 4; k++) {

			for (int i = value; i <= limit; i++) {
				button[i] = new JButton(numbers[i]);
				button[i].addActionListener(this);
				panel.add(button[i]);
			}

			for (int j = jvalue; j < jlimit; j++) {
				operatorbutton[j] = new JButton(operators[j]);
				operatorbutton[j].addActionListener(this);
				panel.add(operatorbutton[j]);

			}

			value += 3;
			limit += 3;
			jvalue += 1;
			jlimit += 1;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// gets the button that was pressed and stores it in 'source'
		// e.getSource() method returns the object(button) that triggered the event
		// (then stores in source)
		// NOTE that e.getSource() is casted as JButoon so it'll do the follows only if
		// the object triggered is a button
		JButton source = (JButton) e.getSource();
		accumulator.setText(gee + source.getText()); // source.getText() gets the text of the stored object(button) x
		gee = accumulator.getText();
		accumulator.setText(gee);
		// NOTE: ALL THIS SO THE BUTTONS VALUE IS PRINTED ON THE ACCUMULATOR

		// if CLR button pressed
		if (e.getSource() == button[1]) {
			accumulator.setText("");
			gee = "";
		}

		boolean buttonPressed = false;
		for (JButton OPbutton : operatorbutton) { // basically with this statement you can call the WHOLE
													// 'operatorbutton' array with 'OPbutoon'
			if (source.getActionCommand().equals(OPbutton.getActionCommand())) { // checks is any of the buttons pressed
				buttonPressed = true; // if yes then 'buttonPressed' becomes true
			}
			// if not then remain false as declared before
		}
		// at this point the 'buttonPressed' already has its final value
		// if conditions checks if that value is true and executes accordingly
		if (buttonPressed == true) {
			disabling();
		} else {
			enabling();
		}

		if (e.getSource() == button[2]) {
			gee = accumulator.getText();
			int end = gee.lastIndexOf("=");

			double result = 0;
			int index = -1;

			if (gee.contains("=")) {
				ArrayList<String> numbers = new ArrayList<>(
						Arrays.asList(gee.substring(0, end).split("(?<=[-+*/])|(?=[-+*/])")));

				while (numbers.contains("*") || numbers.contains("/") || numbers.contains("+")
						|| numbers.contains("-")) {

					for (int i = 0; i < numbers.size(); i++) {
						String number = numbers.get(i).trim();

						if (numbers.contains("*")) {
							index = numbers.indexOf("*");
							result = Double.parseDouble(numbers.get(index - 1))
									* Double.parseDouble(numbers.get(index + 1));
							numbers.remove(index - 1);
							numbers.remove(index - 1);
							numbers.remove(index - 1);
							numbers.add(index - 1, String.valueOf(result));
							
						}
					}

					for (int i = 0; i < numbers.size(); i++) {
						String number = numbers.get(i).trim();

						if (numbers.contains("/")) {
							index = numbers.indexOf("/");
							result = Double.parseDouble(numbers.get(index - 1))
									/ Double.parseDouble(numbers.get(index + 1));
							numbers.remove(index - 1);
							numbers.remove(index - 1);
							numbers.remove(index - 1);
							numbers.add(index - 1, String.valueOf(result));

						}
					}

					for (int i = 0; i < numbers.size(); i++) {
						String number = numbers.get(i).trim();

						if (numbers.contains("+")) {
							index = numbers.indexOf("+");
							result = Double.parseDouble(numbers.get(index - 1))
									+ Double.parseDouble(numbers.get(index + 1));
							numbers.remove(index - 1);
							numbers.remove(index - 1);
							numbers.remove(index - 1);
							numbers.add(index - 1, String.valueOf(result));

						}
					}

					for (int i = 0; i < numbers.size(); i++) {
						String number = numbers.get(i).trim();

						if (numbers.contains("-")) {
							index = numbers.indexOf("-");
							result = Double.parseDouble(numbers.get(index - 1))
									- Double.parseDouble(numbers.get(index + 1));
							numbers.remove(index - 1);
							numbers.remove(index - 1);
							numbers.remove(index - 1);
							numbers.add(index - 1, String.valueOf(result));

						}

					}
				}

				gee = String.valueOf(result);
				accumulator.setText(gee);
			}
		}
	}

	public void disabling() {
		for (JButton OPbutton : operatorbutton)
			OPbutton.setEnabled(false);
	}

	public void enabling() {
		for (JButton OPbutton : operatorbutton)
			OPbutton.setEnabled(true);
	}
}
