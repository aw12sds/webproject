package com.jjj.dao;

import java.util.List;

import com.jjj.bean.crime;
import com.jjj.bean.news;
import com.jjj.bean.pass;
import com.jjj.bean.user;


public interface CrimeDAO {
	/*public void update(SalesOrder so);
	public int add(SalesOrder so);
	public List<SalesOrder> getOrders();
	public int getOrders(List<SalesOrder> orders, int pageNo, int pageSize);
	public void delete(int id);
	public SalesOrder loadById(int id);
	public void delete(String conditionStr);
	public int find(List<SalesOrder> products, int pageNo, int pageSize, String queryStr);
	public List<SalesItem> getSalesItems(int orderId);
	public void updateStatus(SalesOrder order);*/
	public int add(crime c);
	public List<crime> getcrimes();
	public List<news> getnews();
	public List<user> getusername(String username);
	public List<pass> getpass();
	
}
