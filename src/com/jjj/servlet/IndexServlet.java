package com.jjj.servlet;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.jjj.service.Spider;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	static  Spider spider=new Spider();
    public IndexServlet() {  
        super();  
    }  
      
    public void destroy() {  
        super.destroy();   
    }  
     
    public void init() throws ServletException {  
        System.out.println("加...................................载网站首页生成任务");  
        this.timerTask();  
    }  
      
    public static void timerTask() {  
    	
        new Timer().schedule(new TimerTask(){  
            @Override  
            public void run() {  
            	spider.spider();
            }  
        },new Date(),1000);  
        
   }  
}
