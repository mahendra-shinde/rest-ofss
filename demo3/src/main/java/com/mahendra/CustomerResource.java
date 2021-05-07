package com.mahendra;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;


@Path("customer")
public class CustomerResource {
	
	private List<Customer> customerList = new ArrayList<>();
	
	public CustomerResource() {
		System.out.println("New Customer Resource");
		init();
	}
	
	void init() {
		Customer customer = new Customer();
		customer.setFirstName("Natasha");
		customer.setLastName("Romanolf");
		customer.setPhone("5765765656");
		customerList.add(customer);
	}
	
	@GET
	@Produces({"application/xml","application/json"})
	public List<Customer> getAll(){
		
		return customerList;
	}
	/*public Customer[] getAll(){
		Customer customers[] = new Customer[customerList.size()];
		return customerList.toArray(customers);
	}*/
	
	@POST
	// Consume annotation would READ request body in XML format
	@Consumes("application/xml")
	// Convert the XML in request-body into "customer" object
	public String create(Customer customer) {
		System.out.println("Creating a new record for "+customer.getFirstName()+" "+customer.getLastName());
		customerList.add(customer);
		return "Customer added successfuly!";
	}
}
