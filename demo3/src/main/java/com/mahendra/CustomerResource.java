package com.mahendra;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;


@Path("customer")
// Actual URL http://localhost:8080/customer
public class CustomerResource {
	
	
	private CustomerDAO dao = CustomerDAO.getInstance();
	
	@GET
	@Produces({"application/xml","application/json"})
	public List<Customer> getAll(){
		return dao.getAll();
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
		dao.create(customer);
		return "Customer added successfuly!";
	}
}
