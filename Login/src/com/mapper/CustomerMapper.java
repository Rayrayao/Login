package com.mapper;

import java.util.List;

import com.po.Customer;
import com.po.UserState;

public interface CustomerMapper {

	public List<Customer> getInfo();

	public void addUser(Customer userInfo);

	public int checkUserName(String value);

	public void updatePwd(Customer customer);

	public void delete(String name);
	
	public void addState(String name);
	
	public List<UserState> getStates();

}
