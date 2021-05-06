package com.mahendra;

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
			resourceConfig.register(AccountResource.class);
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
