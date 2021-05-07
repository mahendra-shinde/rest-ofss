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
	

	@GET
	@Path("by-firstname/{fname}")
	@Produces({"application/json","application/xml"})
	// Actual URL http://localhost:8080/customer/by-firstname/Bruce
	public List<Customer> findByFName(@PathParam("fname") String fname) {
		return dao.findByFName(fname);
	}
	
	@GET
	@Path("by-lastname/{lname}")
	@Produces({"application/json","application/xml"})
	// Actual URL http://localhost:8080/customer/by-firstname/natasha
	public List<Customer> findByLName(@PathParam("lname") String lname) {
		return dao.findByLName(lname);
	}
	
	
	@PUT
	@Path("{ID}")
	@Consumes({"application/json","application/xml"})
	public String update(@PathParam("ID") int id, Customer customer) {
		dao.update(id, customer);
		return "Update successful!";
	}
	
	@DELETE
	@Path("{ID}")
	//Actual URL [DEL] http://localhost:8080/customer/1
	public String delete(@PathParam("ID") int id) {
		dao.deleteById(id);
		return "success";
	}
}
