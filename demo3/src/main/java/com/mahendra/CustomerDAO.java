package com.mahendra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDAO {

	private List<Customer> customerList = new ArrayList<>();

	private static int lastId = 1;
	
	void init() {
		Customer customer = new Customer();
		customer.setFirstName("Natasha");
		customer.setLastName("Romanolf");
		customer.setPhone("5765765656");
		customer.setCustomerId(lastId++);
		customerList.add(customer);
		
		customer = new Customer();
		customer.setFirstName("Bruce");
		customer.setLastName("Banner");
		customer.setPhone("47685765656");
		customer.setCustomerId(lastId++);
		customerList.add(customer);
		
		customer = new Customer();
		customer.setFirstName("Bruce");
		customer.setLastName("Wyne");
		customer.setPhone("55685765656");
		customer.setCustomerId(lastId++);
		customerList.add(customer);
		
	}
	
	public CustomerDAO() {
		init();
	}
	
	//Create Method
	public String create(Customer customer) {
		customer.setCustomerId(lastId++);
		customerList.add(customer);
		return "success";
	}
	
	//Get All
	public List<Customer> getAll(){
		return Collections.unmodifiableList(customerList);
	}
	
	//Find customer by firstname
	public List<Customer> findByFName(String name) {
		//Java8 Streams (Java8,9,10,...16)
		return 	customerList.stream().filter(c -> c.getFirstName().equalsIgnoreCase(name)).collect(Collectors.toList());
		
		/*Older Alternative
		List<Customer> temp = new ArrayList<>();
		for(Customer c: customerList) {
			if(c.getFirstName().equalsIgnoreCase(name)) {
				temp.add(c);
			}
		}
		return temp;
		*/
	}
	
	//Find customer by lastname
	public List<Customer> findByLName(String name) {
		return 	customerList.stream().filter(c -> c.getLastName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}
	
	//find by id
	public Customer findById(Integer id) {
		return customerList.stream().filter(c -> c.getCustomerId() == id).findFirst().get();
	}
	
	//Update method
	public String update(int id, Customer customer) {
		Customer old = findById(id);
		old.setFirstName(customer.getFirstName());
		old.setLastName(customer.getLastName());
		old.setPhone(customer.getPhone());
		return "success";
	}
	
	//Delete method
	public String deleteById(int id) {
		Customer cust = findById(id);
		customerList.remove(cust);
		return "success";
	}
	
	//Implement Singleton patter
	private static CustomerDAO dao = new CustomerDAO();
	
	public static CustomerDAO getInstance() {
		return dao;
	}
}
