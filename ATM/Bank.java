
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Bank
{  
	private ArrayList<Customer> _customers;
	
	/**
      Constructs a bank with no customers.
	 */
	public Bank()
	{
		_customers = new ArrayList<>();
	}

	/**
      Reads the customer numbers and pins
      and initializes the bank accounts.
      @param filename the name of the customer file
	 */
	public void readCustomers(String filename) throws IOException
	{
		File infile = new File(filename);
		Scanner in = new Scanner(infile);
		int number;
		int pin;
		Customer cust;

		// load customers from file into the bank.
		while (in.hasNext())
		{
			number = in.nextInt();
			pin = in.nextInt();

			cust = new Customer(number, pin);
			addCustomer(cust);
		}
		
		in.close();
	}

	/**
      Adds a customer to the bank.
      @param c the customer to add
	 */
	public void addCustomer(Customer c)
	{
		_customers.add(c);
	}

	/**
      Finds a customer in the bank.
      @param aNumber a customer number
      @param aPin a personal identification number
      @return the matching customer, or null if no customer
      matches
	 */
	public Customer findCustomer(int aNumber, int aPin)
	{
		Customer c = null;
		int i = 0;
		boolean found = false;

		// iterate over customers looking for a match
		while (i < _customers.size() && !found)
		{
			c = (Customer)_customers.get(i);
			if (c.match(aNumber, aPin))
			{
				found = true;
			}
			else
			{
				c = null;
			}
			i++;
		}
		return c;
	}
}


