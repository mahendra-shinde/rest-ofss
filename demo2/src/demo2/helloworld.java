package demo2;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("hello")
public class helloworld {

	@GET
	@Produces("text/plain")
	public String sayHello() {
		return "Hello World";
	}

	@POST
	@Produces("text/plain")
	public String sayPost() {
		return "Hello Post!";
	}

	@DELETE
	@Produces("text/plain")
	public String sayDelete() {
		return "Hello Post!";
	}

}
