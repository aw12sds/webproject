package com.jjj.service;

import java.util.List;

import com.jjj.bean.crime;
import com.jjj.bean.news;
import com.jjj.bean.pass;
import com.jjj.dao.CrimeDAO;
import com.jjj.dao.CrimeDAOImpl;

public class CrimeService {
private static CrimeService service;

	private CrimeService() {
	}

	public static CrimeService getInstance() {
		if(service == null) {
			service = new CrimeService();
		}
		return service;
	}
	public static void main(String[] args) {
		
	}
		
	public List<crime> getcrimes()
	{
		CrimeDAO crimedao=new CrimeDAOImpl();
		List<crime> list=crimedao.getcrimes();
		 for(int i=0; i<list.size(); i++){ 
        	 crime parent = list.get(i);
        	 System.out.println(parent.getDepartment());
        	
		 }
		return list;
	}
	public List<pass> getpass()
	{
		CrimeDAO crimedao=new CrimeDAOImpl();
		List<pass> list=crimedao.getpass();
		 for(int i=0; i<list.size(); i++){ 
        	 pass parent = list.get(i);
        	 System.out.println(parent.getPass());
        	
		 }
		return list;
	}
	public List<news> getnews()
	{
		CrimeDAO crimedao=new CrimeDAOImpl();
		List<news> list=crimedao.getnews();
		return list;
	}
}
