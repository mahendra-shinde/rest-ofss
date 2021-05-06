# JAXB : Java for XML Binding

+ Auto-convert XML Element into Java Object
+ Auto-convert Java Object into an xml-element

+ Java 1.5, 1.6 & 1.7 (Older version of 1.8) Already had jaxb included
+ Java 1.9+ JAXB is migrated into JavaEE (It will be available in Full Java EE Server like Glassfish, WildFly, WebLogic)

> Maven based project could use following new dependency to add support for jax-b

    ```xml
    <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-jaxb</artifactId>
            <version>2.34</version>
    </dependency>
    ```

# Steps

1. Open `demo1` and add the dependency in `pom.xml`

2. Create a new MODEL class (Entity) for "Account"
    A Model class must have:
    1. An Empty constructor (No-Arg Constructor)
    2. All properties MUST be PRIVATE
    3. Create Public Getter/Setter methods (Accessors and Mutators) for properties
    4. `TitleCase` convention for Classname and `camelCase` conventions for properties and methods
    5. Use `@XmlRootElement` annotation on model-class

    ```java
    import java.util.Date;

    import javax.xml.bind.annotation.XmlRootElement;

    @XmlRootElement
    public class Account {

        private String accHolderName;
        private String accNumber;
        private Date openingDate;
        private String type;
        //Generate Setters and Getters from "Source" menu
        //Generate Constructor from Superclass
    }
    ```

3.  Create `AccountResource` class and write following code:

    ```java
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

    ```

4. Modify the `App` class, replace following lines:

    ```java
    final ResourceConfig resourceConfig = new ResourceConfig(HelloWorldResource.class);
    final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
    ```
    
    with new lines:
    ```java
    final ResourceConfig resourceConfig = new ResourceConfig(HelloWorldResource.class);
    resourceConfig.register(AccountResource.class); //new-entry
    final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
    ```

5. Run application `App` and try visiting URL `http://localhost:8080/account?acc=A10000`