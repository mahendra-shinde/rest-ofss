package demo2;

import java.util.Date;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("account")
public class AccountResource {

	@GET
	@Produces("application/json")
	public Account findByNumber(@QueryParam("acc")  String accNumber) {
		Account ac= new Account();
		ac.setAccHolderName("Natwarlal");
		ac.setAccNumber(accNumber);
		ac.setType("Savings");
		ac.setOpeningDate(new Date());
		return ac;
	}
}
