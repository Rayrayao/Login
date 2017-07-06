package com.service;

import java.util.List;

import com.po.Customer;
import com.po.UserState;

public interface CustomerService {

	public List<Customer> getInfo();

	public void addUser(Customer userInfo);

	public boolean checkUserName(String value);

	public boolean check(Customer c);

	public Customer getCustomer(String name);

	public void updatePwd(Customer customer);

	public void delete(String name);
	
	public void addState(UserState userState);
	
	public List<UserState> getStates();
}
