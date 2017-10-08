package com.jjj.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jjj.bean.stock;
import com.jjj.bean.url;
import com.jjj.dao.CrimeDAOImpl;
import com.jjj.service.trans.baidu.TransApi;

public class Spider {
	CrimeDAOImpl cirmedao = new CrimeDAOImpl();

	public static void main(String[] args) throws ParseException {
		
		 Spider s=new Spider(); 
		 s.spidersec();
		 


	}

	public void spider() {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String datanow = df.format(new Date());
		String datanow2 = df1.format(new Date());

		try {
			Date datanow1 = (Date) df.parse(datanow);
			String time1 = "04:00:00";
			String time2 = "04:30:00";
			Date date1 = df.parse(time1);
			Date date2 = df.parse(time2);
			int result1 = date1.compareTo(datanow1);
			int result2 = date2.compareTo(datanow1);
			System.out.println(datanow);
			
			if (result1 == -1 && result2 == 1) {
				System.out.println("到时间了");
				String status = cirmedao.getnewstatue(datanow2);
				// 0代表数据库没有今天的新闻
				if ("0".equals(status)) {
					//spidernews();
					spidersec(); 
					// 改变状态
					try {
						
						cirmedao.addstatus();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("已经抓取过了");
				}
			} else {
				System.out.println("不在时间范围内");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void spidernews() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String datanow = df.format(new Date());
		Date datanow1 = (Date) df.parse(datanow);
		Document doc = null;
		spiderxinjing();
		// 新京报

	}

	public void spiderxinjing() {
		Document doc = null;
		for(int i=1;i<6;i++)
		{
		String url="http://www.bjnews.com.cn/news/?page="+i;
		Connection conn = Jsoup.connect(url);
		try {
			doc = conn.timeout(10000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("新京报");
		Elements caption = doc.getElementsByClass("fl lleft");
		System.out.println(caption.size());
		java.util.Iterator<Element> it = caption.iterator();
		try {
			cirmedao.add(it);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	public void spidersec() {
		Document doc = null;
		Connection conn = Jsoup.connect("http://securityaffairs.co/wordpress/category/cyber-crime");
		try {
			doc = conn.timeout(10000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("securityaffairs");
		Elements caption = doc.getElementsByClass("post_inner_wrapper");
		System.out.println(caption.size());
		try {
			cirmedao.addsec(caption);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void url() {
		Connection conn = Jsoup.connect("http://quote.cfi.cn/stockList.aspx?t=13");
		Document doc = null;
		try {
			doc = conn.timeout(10000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 得到标题
		Element divcontent = doc.getElementById("divcontent");
		Elements td = divcontent.getElementsByTag("td");
		java.util.Iterator<Element> itpolice = td.iterator();
		int i = 2018;
		while (itpolice.hasNext()) {
			i++;
			Element element = (Element) itpolice.next();
			String s = element.text();
			String name = s.substring(0, s.indexOf("("));
			String code = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
			System.out.println(name);

			String url = "http://quote.cfi.cn/" + code + ".html";
			String company = company(url);
			System.out.println(company);
			try {
				cirmedao.addurl(code, company);
			} catch (ParseException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(i);
	}

	public static String company(String url) {
		String s = null;
		boolean flag = true;
		try {
			Document doc = null;
			Connection conn = Jsoup.connect(url);
			doc = conn.timeout(10000).get();
			// 得到标题
			Element nodea14 = doc.getElementById("nodea14");
			Elements a = nodea14.getElementsByTag("a");

			s = ("http://quote.cfi.cn" + a.attr("href"));
			// 答案数量
			/*
			 * Elements num = doc.getElementsByClass("List-headerText"); String
			 * e=num.text();
			 * 
			 * 
			 * 
			 * if(!"".equals(e)) { logger.info(caption.text()+"---"+e); } else {
			 * logger.info(caption.text()+"---0个回答"); }
			 */

		} catch (Exception e) {
			// TODO: handle exception
			flag = false;

		}
		return s;

	}

	public void geturl() {
		List<url> list = cirmedao.geturl();
		System.out.println(list.size());
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			url str = (url) iter.next();
			String stockcode = str.getCode();
			String url = str.getUrl();
			Document doc = null;
			Connection conn = Jsoup.connect(url);
			try {
				doc = conn.timeout(10000).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 得到标题
			Elements vertical_table = doc.getElementsByClass("vertical_table");
			Element tr = vertical_table.first();
			Elements tr1 = tr.getElementsByTag("tr");
			Element trname = tr1.get(0);
			Element tr2 = tr1.get(1);
			Element tr3 = tr1.get(2);
			Element tr4 = tr1.get(6);
			Element tr5 = tr1.get(33);
			Element tr6 = tr1.get(15);
			String name = trname.getElementsByTag("td").get(0).text();
			String name1 = name.substring(0, name.indexOf("("));
			String stockname = tr2.getElementsByTag("td").get(1).text();
			String province = tr3.getElementsByTag("td").get(1).text();
			String mail = tr4.getElementsByTag("td").get(1).text();
			String address = tr6.getElementsByTag("td").get(1).text();
			String buniness = tr5.getElementsByTag("td").get(1).text();
			try {
				cirmedao.addstock(name1, stockcode, stockname, province, mail, address, buniness);
			} catch (ParseException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(stockname);
			System.out.println(province);
			System.out.println(mail);
			System.out.println(buniness);
		}

	}

	public void search() {
		List<stock> list = cirmedao.search();
		List<String> list1=new  ArrayList<String>();
		Set set = new HashSet();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		ArrayList<Integer> newList = new ArrayList<Integer>(map.values());
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			stock str = (stock) iter.next();
			list1.add(str.getProvince());

		}
		for (String obj : list1) {
			if (map.containsKey(obj)) {
				map.put(obj, map.get(obj).intValue()+ 1);
			} else {
				map.put(obj, 1);
			}
		}
		  
		for (Map.Entry<String, Integer> entry : map.entrySet()) {  
		  
		    System.out.println(entry.getKey() + ","+entry.getValue());  
		  
		} 
		
	}
	public void test()
	{
		;
	}

}
