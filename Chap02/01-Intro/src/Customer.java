package becofunc;

import java.util.ArrayList;
import java.util.List;

public class Customer {
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


	public static ArrayList<String> getEnabledCustomerNames() {
		ArrayList<String> outList = new ArrayList<String>();
		for (Customer c : Customer.allCustomers) {
			if (c.enabled) {
				outList.add(c.name);
			}
		}

		return outList;
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
	}
}