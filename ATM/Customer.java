/**
   A bank customer
*/

public class Customer
{  
	public static final int CHECKING_ACCOUNT = 0;
	public static final int SAVINGS_ACCOUNT = 1;

	private int _customerNumber;
	private int _pin;
	private BankAccount[] _accounts;

	/**
      Constructs a customer with a given number and PIN.
      @param aNumber the customer number
      @param PIN the personal identification number
	 */
	public Customer(int aNumber, int aPin)
	{  
		_customerNumber = aNumber;
		_pin = aPin;
		_accounts = new BankAccount[2];
		_accounts[CHECKING_ACCOUNT] = new BankAccount();
		_accounts[SAVINGS_ACCOUNT] = new BankAccount();

	}

	/** 
      Tests if this customer matches a customer number 
      and PIN.
      @param aNumber a customer number
      @param aPin a personal identification number
      @return true if the customer number and PIN match
	 */
	public boolean match(int aNumber, int aPin)
	{  
		return (_customerNumber == aNumber && _pin == aPin);
	}

	/** 
      Gets an account of this customer.
      @param account one of Customer.CHECKING_ACCOUNT
      or Customer.SAVINGS_ACCOUNT
      @return the selected account, or null if account 
      number not valid.
	 */
	public BankAccount getAccount(int a)
	{  
		BankAccount temp = null;
		if (0 <= a && a < _accounts.length)
			temp = _accounts[a];
		
		return temp;
	}
}