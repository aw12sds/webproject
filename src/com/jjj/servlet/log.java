package com.jjj.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjj.bean.user;
import com.jjj.dao.CrimeDAO;
import com.jjj.dao.CrimeDAOImpl;

/**
 * Servlet implementation class log
 */
@WebServlet("/log")
public class log extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public log() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("username");
		String pass=request.getParameter("password");
		CrimeDAO crimedao=new CrimeDAOImpl();
		List list=crimedao.getusername(name);
		 System.out.println(list.size());
		 String password=null;
		 String label=null;
		for(int i=0; i<list.size(); i++){ 
       	 user user = (com.jjj.bean.user) list.get(i);
       	 password=user.getPass();
       	label=user.getlabel();
		}
		
		if(pass.equals(password))
		{
			HttpSession session=request.getSession();
			
			session.setAttribute("username", name);
			session.setAttribute("password", pass);
			if(label.equals("1"))
			{
				response.sendRedirect("board.jsp");
			}
			else if(label.equals("0")){
				response.sendRedirect("welcome.jsp");
			}
			else if(label.equals("3")){
				response.sendRedirect("log.jsp");
			}
			
		}
		
		
	}

	

}
