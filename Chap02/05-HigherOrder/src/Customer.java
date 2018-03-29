import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private interface Function1<A1, B> {
		public B call(A1 in1);
	}

	
	static public ArrayList<Customer> allCustomers = new ArrayList<Customer>();

	public Integer id;
	public String name;
	public String address;
	public Boolean enabled;

	public Customer(Integer id, String name, String address, Boolean enabled) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.enabled = enabled;

		Customer.allCustomers.add(this);
	}

	// Functional style definition
	public static <B> ArrayList<B> getEnabledCustomerField(Function1<Customer, B> f) {
		ArrayList<B> outList = new ArrayList<B>();
		for (Customer c : Customer.allCustomers) {
			if (c.enabled) {
				outList.add(f.call(c));
			}
		}

		return outList;
	}

	// Declarations of function objects as Anonymous Classes (functions)
	public static ArrayList<String> getEnabledCustomerNames() {
		return getEnabledCustomerField(new Function1<Customer, String>() {
			public String call(Customer c) {return c.name;}
		});
	}

	public static ArrayList<String> getEnabledCustomerAddresses() {
		return getEnabledCustomerField(new Function1<Customer, String>() {
			public String call(Customer c) {return c.address;}
		});
	}

	public static ArrayList<Integer> getEnabledCustomerIds() {
		return getEnabledCustomerField(new Function1<Customer, Integer>() {
			public Integer call(Customer c) {return c.id;}
		});
	}

	/* Definition of a getEnabledCustomerField as a closure : it
	can access the domain parameter whereas it is not declared in 
	its scope, and not passed in its parameter list. This is the
	main advantage of closures : without that, we would have been
	forced to define a new interface of generic function, or something
	else. */
	/* The domain is supposed to be declared as a final parameter,
	but it seems to work without it. */
	public static ArrayList<String> getEnabledCustomerSomeoneEmail(final String domain) {
		return getEnabledCustomerField(new Function1<Customer, String>() {
			public String call(Customer c) {return (c.name + "@" + domain);}
		});
	}

	public static void main(String[] args) {
		Customer c1 = new Customer(1, "Michel", "123", true);
		Customer c2 = new Customer(2, "Michal", "123", true);
		Customer c3 = new Customer(3, "Michou", "123", false);
		Customer c4 = new Customer(4, "Francis", "123", true);
		Customer c5 = new Customer(5, "Carlo", "123", true);
		Customer c6 = new Customer(6, "Bert", "123", true);

		ArrayList<String> names = Customer.getEnabledCustomerNames();

		for (String name : names) {
			System.out.println(name);
		}

		ArrayList<Integer> ids = Customer.getEnabledCustomerIds();

		for (Integer id : ids) {
			System.out.println(id);
		}

		ArrayList<String> mails = Customer.getEnabledCustomerSomeoneEmail("jacky.com");

		for (String mail : mails) {
			System.out.println(mail);
		}
	}
}