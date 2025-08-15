import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
   A component that lets the user enter a number, using
   a button pad labeled with digits
*/

public class KeyPad extends JPanel
{ 
	private ActionListener _listener;
	private JPanel _buttonPanel;
	private JButton _clearButton;
	private JTextField _display;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Set up keypad
	 */
	public KeyPad()
	{  
		setLayout(new BorderLayout());

		// add display field

		_display = new JTextField();
		add(_display, "North");

		// make button panel

		_buttonPanel = new JPanel();
		_buttonPanel.setLayout(new GridLayout(4, 3));

		// add digit buttons

		_listener = new DigitButtonListener();
		addButton("7", _listener);
		addButton("8", _listener);
		addButton("9", _listener);
		addButton("4", _listener);
		addButton("5", _listener);
		addButton("6", _listener);
		addButton("1", _listener);
		addButton("2", _listener);
		addButton("3", _listener);
		addButton("0", _listener);
		addButton(".", _listener);

		// add clear entry button

		_clearButton = new JButton("CE");
		_buttonPanel.add(_clearButton);
		_clearButton.addActionListener(new ClearButtonListener());

		add(_buttonPanel, "Center");
	}

	/**
      Gets the value that the user entered.
      @return the value in the text field of the keypad
	 */
	public double getValue()
	{  
		return Double.parseDouble(_display.getText());
	}

	/**
      Clears the display.
	 */
	public void clear()
	{  
		_display.setText("");
	}

	/**
      Adds a button to the button panel
      @param label the button label
      @param listener the button listener
	 */
	public void addButton(String label, ActionListener listener)
	{  
		JButton button = new JButton(label);
		_buttonPanel.add(button);
		button.addActionListener(listener);
	}

	/**
	 * Set up button listener
	 */
	private class DigitButtonListener implements ActionListener
	{  
		public void actionPerformed(ActionEvent event)
		{  
			// Get the button label
			// it is a digit or decimal point
			JButton source = (JButton)event.getSource();
			String label = source.getText();

			// don't add two decimal points
			if (!label.equals(".") || _display.getText().indexOf(".") == -1)
			{
				_display.setText(_display.getText() + label);
			}
		}
	}

	/**
	 * Clears button
	 */
	private class ClearButtonListener implements ActionListener
	{  
		public void actionPerformed(ActionEvent event)
		{ 
			clear();
		}
	}
}

