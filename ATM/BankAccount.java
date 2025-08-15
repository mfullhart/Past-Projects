public class BankAccount
{  
	private double _balance;

	/**
      Constructs a bank account with a zero balance.
	 */
	public BankAccount()
	{  
		_balance = 0;
	}

	/**
      Constructs a bank account with a given balance.
      @param initialBalance the initial balance
	 */
	public BankAccount(double initialBalance)
	{  
		_balance = initialBalance;
	}

	/** 
      Deposits money into the account.
      @param the amount of money to deposit
	 */
	public void deposit(double amount)
	{  
		_balance = _balance + amount;
	}

	/**
     * Withdraws money from the account.
     *
     * @param the amount of money to withdraw
     * @return
     */
	public boolean withdraw(double amount)
	{  
		if(amount > _balance)
		{
			System.out.println("Error: Insufficient funds in account");
			return false;
		}
		_balance = _balance - amount;
        return true;
    }

	/** 
      Gets the account balance.
      @return the account balance
	 */
	public double getBalance()
	{  
		return _balance; 
	}
}

