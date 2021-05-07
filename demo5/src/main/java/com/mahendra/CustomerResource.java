package com.mahendra;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("customer")
// Actual URL http://localhost:8080/customer
public class CustomerResource {

	private CustomerDAO dao = CustomerDAO.getInstance();

	@GET
	@Path("{ID}")
	@Produces({ "application/xml", "application/json" })
	public Response byId(@PathParam("ID") int id) {
		try {
			Customer c = dao.findById(id);
			return Response.ok(c).build();
		} catch (Exception ex) {
			return Response.status(404, "No customer with id " + id + " found!").build();
		}

	}

	@GET
	@Produces({ "application/xml", "application/json" })
	public Response getAll() {
		List<Customer> custs = dao.getAll();
		if (custs == null) {
			return Response.status(404, "No records could be fetched").build();
		} else if (custs.size() == 0) {
			return Response.status(450, "Found zero records!").build();
		} else {
			return Response.ok(custs).build();
		}
	}
	/*
	 * public Customer[] getAll(){ Customer customers[] = new
	 * Customer[customerList.size()]; return customerList.toArray(customers); }
	 */

	@POST
	// Consume annotation would READ request body in XML format
	@Consumes("application/xml")
	// Convert the XML in request-body into "customer" object
	public Response create(Customer customer) {
		System.out.println("Creating a new record for " + customer.getFirstName() + " " + customer.getLastName());
		try {
			dao.create(customer);
			return Response.ok("Record created").build();
		} catch (Exception ex) {
			return Response.status(550, "Could not create record, " + ex.getMessage()).build();
		}
	}

	@GET
	@Path("by-firstname/{fname}")
	@Produces({ "application/json", "application/xml" })
	// Actual URL http://localhost:8080/customer/by-firstname/Bruce
	public Response findByFName(@PathParam("fname") String fname) {
		List<Customer> custs = dao.findByFName(fname);
		if (custs == null || custs.isEmpty()) {
			return Response.status(404, "Record not found!").build();
		} else {
			return Response.ok(custs).build();
		}
	}

	@GET
	@Path("by-lastname/{lname}")
	@Produces({ "application/json", "application/xml" })
	// Actual URL http://localhost:8080/customer/by-firstname/natasha
	public Response findByLName(@PathParam("lname") String lname) {
		List<Customer> custs = dao.findByLName(lname);
		if (custs == null || custs.isEmpty()) {
			return Response.status(404, "Record not found!").build();
		} else {
			return Response.ok(custs).build();
		}
	}

	@PUT
	@Path("{ID}")
	@Consumes({ "application/json", "application/xml" })
	public Response update(@PathParam("ID") int id, Customer customer) {
		try {
			dao.update(id, customer);
			return Response.status(200, "Record created").build();
		} catch (Exception ex) {
			return Response.status(410, "Could not create record: " + ex.getMessage()).build();
		}
	}

	@DELETE
	@Path("{ID}")
	// Actual URL [DEL] http://localhost:8080/customer/1
	public Response delete(@PathParam("ID") int id) {
		try {
			dao.deleteById(id);
			return Response.status(200, "Record deleted!").build();
		} catch (Exception ex) {
			return Response.status(410, "Could not create record: " + ex.getMessage()).build();
		}
	}
}
