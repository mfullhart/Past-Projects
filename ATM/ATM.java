import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ATM extends JFrame
{
	private int _state;
	private int _customerNumber;
	private Customer _currentCustomer;
	private BankAccount _currentAccount;
	private final Bank _theBank;

	private final JButton _aButton;
	private final JButton _bButton;
	private final JButton _cButton;
	private final JButton _dButton;

	private final KeyPad _pad;
	private final JTextArea _display;

	private enum State
	{
		START_STATE,
		PIN_STATE,
		ACCOUNT_STATE,
		TRANSACT_STATE,
		ERROR_STATE,
		FAST_CASH_STATE
	}

	/**
	 Constructs the user interface of the ATM application.
	 */
	public ATM()
	{
		final int FRAME_WIDTH = 600;
		final int FRAME_HEIGHT = 200;

		JPanel buttonPanel;
		Container contentPane;

		setSize(FRAME_WIDTH, FRAME_HEIGHT);

		addWindowListener(new WindowCloser());

		// initialize bank and customers

		_theBank = new Bank();
		try
		{
			_theBank.readCustomers("customers.txt");
		}
		catch(final IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error opening accounts file.");
		}

		// construct components

		_pad = new KeyPad();

		_display = new JTextArea(4, 20);

		_aButton = new JButton("  A  ");
		_aButton.addActionListener(new AButtonListener());

		_bButton = new JButton("  B  ");
		_bButton.addActionListener(new BButtonListener());

		_cButton = new JButton("  C  ");
		_cButton.addActionListener(new CButtonListener());

		_dButton = new JButton("  D  ");
		_dButton.addActionListener(new DButtonListener());



		// add components to content pane

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1));
		buttonPanel.add(_aButton);
		buttonPanel.add(_bButton);
		buttonPanel.add(_cButton);
		buttonPanel.add(_dButton);


		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(_pad);
		contentPane.add(_display);
		contentPane.add(buttonPanel);

		setState(State.START_STATE);
		setVisible(true);
	}

	/**
	 Sets the current customer number to the keypad value
	 and sets state to PIN.
	 */
	public void setCustomerNumber()
	{
		_customerNumber = (int)_pad.getValue();
		setState(State.PIN_STATE);
	}

	/**
	 Gets PIN from keypad, finds customer in bank.
	 If found sets state to ACCOUNT, else to START.
	 */
	public void selectCustomer()
	{
		int pin = (int) _pad.getValue();
		_currentCustomer = _theBank.findCustomer(_customerNumber, pin);

		if (_currentCustomer == null)
		{
			setState(State.START_STATE);
		} else
		{
			setState(State.ACCOUNT_STATE);
		}
	}

	/**
	 Sets current account to checking or savings. Sets
	 state to TRANSACT
	 @param account one of Customer.CHECKING_ACCOUNT
	 or Customer.SAVINGS_ACCOUNT
	 */
	public void selectAccount(final int account)
	{
		_currentAccount = _currentCustomer.getAccount(account);
		setState(State.TRANSACT_STATE);
	}

	/**
	 Withdraws amount typed in keypad from current account.
	 Sets state to ACCOUNT.
	 */
	public void withdraw()
	{
		if (_currentAccount.withdraw(_pad.getValue()))
		{
			setState(State.TRANSACT_STATE);
		}
		else
		{
			setError("Error: Unable to process transaction.");
		}
	}

	/**
	 Deposits amount typed in keypad to current account.
	 Sets state to ACCOUNT.
	 */
	public void deposit()
	{
		_currentAccount.deposit(_pad.getValue());
		setState(State.ACCOUNT_STATE);
	}

	/**
	 Sets state and updates display message.
	 @param state the next state
	 */
	public void setState(final State newState)
	{
		_state = newState.ordinal();
		_pad.clear();
		if (newState == State.START_STATE)
		{
			_display.setText("Enter customer number\nA = OK");
		}
		else if (newState == State.PIN_STATE)
		{
			_display.setText("Enter PIN\nA = OK");
		}
		else if (newState == State.ACCOUNT_STATE)
		{
			_display.setText("Select Account\n"
					+ "A = Checking\nB = Savings\nC = Exit");
		}
		else if (newState == State.TRANSACT_STATE)
		{
			_display.setText("Balance = "
					+ _currentAccount.getBalance()
					+ "\nEnter amount and select transaction\n"
					+ "A = Withdraw\nB = Deposit\nC = Cancel\nD = Fast Cash");
		}
		else if (newState == State.ERROR_STATE)
		{
			// Do nothing here as error messages are handled separately
		}
		else if (newState == State.FAST_CASH_STATE) {
			fastCash();
		}
	}

	private class AButtonListener implements ActionListener
	{
		public void actionPerformed(final ActionEvent event)
		{
			if (_state == State.START_STATE.ordinal())
			{
				setCustomerNumber();
			}
			else if (_state == State.PIN_STATE.ordinal())
			{
				selectCustomer();
			}
			else if (_state == State.ACCOUNT_STATE.ordinal())
			{
				selectAccount(Customer.CHECKING_ACCOUNT);
			}
			else if (_state == State.TRANSACT_STATE.ordinal())
			{
				withdraw();
			}
		}
	}

	private class BButtonListener implements ActionListener
	{
		public void actionPerformed(final ActionEvent event)
		{
			if (_state == State.ACCOUNT_STATE.ordinal())
			{
				selectAccount(Customer.SAVINGS_ACCOUNT);
			}
			else if (_state == State.TRANSACT_STATE.ordinal())
			{
				deposit();
			}
		}
	}

	private class CButtonListener implements ActionListener
	{
		public void actionPerformed(final ActionEvent event)
		{
			if (_state == State.ACCOUNT_STATE.ordinal())
			{
				setState(State.START_STATE);
			}
			else if (_state == State.TRANSACT_STATE.ordinal())
			{
				setState(State.ACCOUNT_STATE);
			}
		}
	}

	private class DButtonListener implements ActionListener
	{
		public void actionPerformed(final ActionEvent event)
		{
			if (_state == State.TRANSACT_STATE.ordinal())
			{
				setState(State.FAST_CASH_STATE);
			}
		}
	}

	private void fastCash()
	{
		double amount = 20.00;

		if (_currentAccount.withdraw(amount))
		{
			setState(State.TRANSACT_STATE);
		}
		else
		{
			_display.setText("Error: Unable to process transaction.");
		}
	}

	private String formatAmount(double amount)
	{
		return String.format("%.2f", amount);
	}

	private void displayMessage(String message)
	{
		_display.setText(message);
	}

	private void setError(String errorMessage)
	{
		setState(State.ERROR_STATE);
		displayMessage(errorMessage);
	}

	private class WindowCloser extends WindowAdapter
	{
		public void windowClosing(final WindowEvent event)
		{
			System.exit(0);
		}
	}
}