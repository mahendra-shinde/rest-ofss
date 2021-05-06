package com.mahendra;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("account")
public class AccountResource {

	@GET
	@Produces({"application/xml","application/json"})
	//@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	public Account findByNumber(@QueryParam("acc")  String accNumber) {
		Account ac= new Account();
		ac.setAccHolderName("Natwarlal");
		ac.setAccNumber(accNumber);
		ac.setType("Savings");
		ac.setOpeningDate(new Date());
		return ac;
	}
}
