# Rest Demo

> A Standalone REST Application with built-in http server. 
> Launch command `java -jar demo1.jar`

## Steps

1.	Create new Maven project with following details:

	```
	Project Name: demo1
	Artifact: demo1
	groupId: com.ofss
	version: 1.0
	packaging: Jar
	```
	
2. Modify POM.XML file, just add followings:

	```xml
	<properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.glassfish.jersey.containers</groupId>
			<artifactId>jersey-container-grizzly2-http</artifactId>
			<version>2.34</version>
		</dependency>

		<dependency>
			<groupId>org.glassfish.jersey.inject</groupId>
			<artifactId>jersey-hk2</artifactId>
			<version>2.34</version>
		</dependency>


	</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>exec-maven-plugin</artifactId>
				<configuration>
					<mainClass>com.mahendra.App</mainClass>
				</configuration>
			</plugin>
		</plugins>
	</build>
	```
	
3.	Create new Java class `App` in package `com.ofss`

	```java
	package com.ofss;
	import java.io.IOException;
	import java.net.URI;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	
	import org.glassfish.grizzly.http.server.HttpServer;
	import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
	import org.glassfish.jersey.server.ResourceConfig;
	
	public class App {
		private static final URI BASE_URI = URI.create("http://localhost:8080/");
		public static final String ROOT_PATH = "helloworld";
	
		public static void main(String[] args) {
			System.out.println("\"Hello World\" Jersey Example App");
			try {
				final ResourceConfig resourceConfig = new ResourceConfig(HelloWorldResource.class);
				final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
				
				// Allow JVM to Shutdown the HTTP Server when it "jvm" receives kill 
				// request from User
				
				Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
					@Override
					public void run() {
						server.shutdownNow();
					}
				}));
				server.start();
	
				System.out.println(String.format("Application started.\nTry out %s%s\nStop the application using CTRL+C",
						BASE_URI, ROOT_PATH));
				Thread.currentThread().join();
			} catch (IOException | InterruptedException ex) {
				Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
			}
	
		}
	}
	```

4.	Create `HelloWorldResource` class

	```
	package com.mahendra;

	import javax.ws.rs.GET;
	import javax.ws.rs.Path;
	import javax.ws.rs.Produces;
	
	@Path("helloworld")
	public class HelloWorldResource {
	    public static final String CLICHED_MESSAGE = "Hello World!";
			
	    @GET
	    // Resulting URL Endpoint http://localhost:8080/helloworld 
	    @Produces("text/plain")
	    public String getHello() {
	        return CLICHED_MESSAGE;
	    }
	
	}
		
	```

5. Run the `App` Class and try url `http://localhost:8080/helloworld` from any web browser.