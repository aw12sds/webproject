package com.jjj.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException {
		// TODO Auto-generated method stub
		 HttpServletRequest req = (HttpServletRequest)request;  
	        HttpServletResponse res = (HttpServletResponse)response;  
	        String root = req.getContextPath();  
	        try {
	        HttpSession session = req.getSession(true);  
	        String path = (String)req.getRequestURI();
	        String username = (String) session.getAttribute("username");
	        if(path.equals("/myproject/log.jsp")||path.equals("/myproject/")||path.equals("/myproject/log"))
            {
            	 
            	 chain.doFilter(request, response);
            }
	        else
	        {
	        	 if (username!=null) {        //  
						chain.doFilter(request, response);
		        } else{  
		        	res.sendRedirect(root + "/log.jsp");
		        }  
	        }
	       
	        } catch (ServletException e) {
				// TODO Auto-generated catch block
	        	res.sendRedirect(root + "/log.jsp");
				e.printStackTrace();
			} // 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
