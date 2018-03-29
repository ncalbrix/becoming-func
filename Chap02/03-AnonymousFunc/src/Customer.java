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

	// "Explicit" definitions of function objects
	/*static private class CustomerToName implements Function1<Customer, String> {
		public String call(Customer c) {return c.name;}
	}

	static private class CustomerToId implements Function1<Customer, Integer> {
		public Integer call(Customer c) {return c.id;}
	}

	static private class CustomerToAddress implements Function1<Customer, String> {
		public String call(Customer c) {return c.address;}
	}

	static private class CustomerToCustomer implements Function1<Customer, Customer> {
		public Customer call(Customer c) {return c;}
	}

	public static ArrayList<String> getEnabledCustomerNames() {
		return getEnabledCustomerField(new CustomerToName());
	}

	public static ArrayList<Integer> getEnabledCustomerIds() {
		return getEnabledCustomerField(new CustomerToId());
	}

	public static ArrayList<String> getEnabledCustomerAddresses() {
		return getEnabledCustomerField(new CustomerToAddress());
	}

	public static ArrayList<Customer> getEnabledCustomers() {
		return getEnabledCustomerField(new CustomerToCustomer());
	}*/

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
	}
}