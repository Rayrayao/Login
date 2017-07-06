package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.CustomerMapper;
import com.po.Customer;
import com.po.UserState;
import com.service.CustomerService;

@Service
public class ServiceManager implements CustomerService {
	@Resource
	private CustomerMapper cmp;

	public List<Customer> getInfo() {
		return cmp.getInfo();
	}

	public void addUser(Customer userInfo) {
		cmp.addUser(userInfo);

	}

	public boolean checkUserName(String value) {

		return (cmp.checkUserName(value) > 0);
	}

	public boolean check(Customer c) {
		List<Customer> list = cmp.getInfo();
		int i;
		for (i = 0; i < list.size(); i++) {
			Customer c1 = (Customer) list.get(i);

			if (c1.getCustname().equals(c.getCustname())
					&& c1.getPwd().equals(c.getPwd())) {
				return true;
			}
		}
		return false;

	}

	public Customer getCustomer(String name) {
		List<Customer> list = cmp.getInfo();
		int i;
		for (i = 0; i < list.size(); i++) {
			Customer c1 = (Customer) list.get(i);

			if (c1.getCustname().equals(name)) {
				return c1;
			}
		}
		return null;
	}

	public void updatePwd(Customer customer) {
		cmp.updatePwd(customer);

	}

	public void delete(String name) {
		cmp.delete(name);

	}

	public void addState(UserState userState) {
		cmp.addState(userState.getName());
		
	}

	public List<UserState> getStates() {
		
		return cmp.getStates();
	}

}
