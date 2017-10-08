package com.jjj.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jjj.bean.crime;
import com.jjj.bean.news;
import com.jjj.bean.newstatues;
import com.jjj.bean.pass;
import com.jjj.bean.stock;
import com.jjj.bean.url;
import com.jjj.bean.user;
import com.jjj.service.trans.baidu.TransApi;
import com.jjj.util.DB;


public class CrimeDAOImpl implements CrimeDAO {

	
	@Override
	public int add(crime c) {
		// TODO Auto-generated method stub
		return 0;
	}
	//得到用户名
	public List<user> getusername(String username) {
		// TODO Auto-generated method stub
		List<user> user = new ArrayList<user>();
		Connection conn = DB.getConn();
		String sql=null;
		if(username.indexOf(' ')>-1){
			
		}
		else
		{
			sql = "select * from user where user='"+username+"'";
		}
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				user c = this.getuserFromRs(rs);
				System.out.println(c.getlabel());
				user.add(c);
			}
			System.out.println(user.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return user;
	}
	public List<pass> getpass() {
		List<pass> pass = new ArrayList<pass>();
		Connection conn = DB.getConn();
		String sql = "select * from pass";
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				pass c = this.getpassFromRs(rs);
				pass.add(c);
			}
			System.out.println(pass.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return pass;
		
	} 
	public List<crime> getcrimes() {
		List<crime> crimes = new ArrayList<crime>();
		Connection conn = DB.getConn();
		String sql = "select * from crime where country='中国' order by enddata desc";
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				crime c = this.getcrimeFromRs(rs);
				crimes.add(c);
			}
			System.out.println(crimes.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return crimes;
		
	} 
	public List<news> getnews() {
		List<news> news = new ArrayList<news>();
		Connection conn = DB.getConn();
		String sql = "select * from news order by  data desc";
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				news c = this.getnewsFromRs(rs);
				news.add(c);
			}
			System.out.println(news.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return news;
		
	} 
	public List<url> geturl() {
		List<url> url = new ArrayList<url>();
		Connection conn = DB.getConn();
		String sql = "select * from url order by id";
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				url c = this.geturlFromRs(rs);
				url.add(c);
			}
			System.out.println(url.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return url;
		
	} 
	private crime getcrimeFromRs(ResultSet rs) {
		crime c = new crime();
		try {
			c.setId(rs.getInt("id"));
			c.setKind(rs.getString("kind"));
			c.setDepartment(rs.getString("department"));
			c.setStartdata(rs.getString("startdata"));
			c.setProvince(rs.getString("province"));
			c.setDesc(rs.getString("desc"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	private pass getpassFromRs(ResultSet rs) {
		pass c = new pass();
		try {
			c.setId(rs.getInt("id"));
			c.setPass(rs.getString("pass"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	private user getuserFromRs(ResultSet rs) {
		user c = new user();
		try {
			c.setId(rs.getInt("id"));
			c.setUser(rs.getString("user"));
			c.setPass(rs.getString("pass"));
			c.setlabel(rs.getString("label"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	private news getnewsFromRs(ResultSet rs) {
		news c = new news();
		try {
			c.setId(rs.getInt("id"));
			c.setNewscap(rs.getString("newscap"));
			c.setNewcaption(rs.getString("newcaption"));
			c.setNewssource(rs.getString("newssource"));
			c.setData(rs.getString("data"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	private url geturlFromRs(ResultSet rs) {
		url c = new url();
		try {
			c.setId(rs.getInt("id"));
			c.setCode(rs.getString("code"));
			c.setUrl(rs.getString("url"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	private stock getstockFromRs(ResultSet rs) {
		stock c = new stock();
		try {
			c.setId(rs.getInt("id"));
			c.setStockname(rs.getString("stockname"));
			c.setProvince(rs.getString("province"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	private newstatues getnewstatueFromRs(ResultSet rs) {
		newstatues c = new newstatues();
		try {
			c.setId(rs.getInt("id"));
			c.setTime(rs.getString("time"));
			c.setStatus(rs.getString("status"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public String getnewstatue(String time) {
		Connection conn = DB.getConn();
		String sql = "select * from newstatue where time='"+time+"'";
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		newstatues c=null;
		String status = null;
		try {
			while (rs.next()) {
				c = this.getnewstatueFromRs(rs);
				
				
			}
			if(c==null)
			{
				status=0+"";
			}
			else{
				status=	c.getStatus();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return status;
	}
	//插入新闻
	public void add(Iterator<Element> it) throws ParseException, SQLException
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String  datanow= df.format(new Date());
		Date datanow1=(Date) df.parse(datanow);
		Date date = null;
		Connection conn = DB.getConn();
		while (it.hasNext()) {
			Element element = (Element) it.next();
			// Node titleNode= element.child(0).childNode(0);
			String s = element.getElementsByTag("a").text();
			String time = element.getElementsByTag("dt").text();
			date=(Date) df.parse(time);
			String datanow2 = df.format(date);
			String p = element.getElementsByTag("p").text();
			String sql = "insert into news values (null, ?, ?, ? ,?)";
			if(datanow.equals(datanow2))
			{
				PreparedStatement pstmt = DB.prepare(conn, sql);
				ResultSet rsKey = null;
					pstmt.setString(1, s);
					pstmt.setString(2, p);
					pstmt.setString(3, "新京报");
					pstmt.setString(4,time);
					pstmt.executeUpdate();
			}
			
			
	                                }
	}
	//插入新闻
		public void addsec(Elements its) throws ParseException, SQLException
		{
			Date date = null;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String datanow = df.format(new Date());
			Date datanow1=(Date) df.parse(datanow);
			Connection conn = DB.getConn();
			Locale locale = new Locale("en", "US");
	        DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
	        for(int i=0;i<its.size();i++)
	        {
	        	
	        	Element it=its.get(i);
	        	String time = it.children().last().getElementsByTag("a").get(0).text();
	        	String name=it.children().last().getElementsByTag("a").get(1).text();
	        	date= fullDateFormat.parse(time);
				String data1=df.format(date);
				Elements post_header_wrapper=it.getElementsByClass("post_header_wrapper");
 				Elements postdetail_large=it.getElementsByClass("post_wrapper_inner");
				String header_wrapper=postdetail_large.html();
				String ee=post_header_wrapper.first().getElementsByTag("a").text();
				String post_wrapper_inner=it.getElementsByClass("post_wrapper_inner").first().getElementsByTag("p").text();
				String ch=trans(ee);
				String postch=trans(post_wrapper_inner);
				String sql = "insert into secnews values (null, ?, ?, ? ,?)";
				/*if(data1.equals(datanow))
				{*/
					PreparedStatement pstmt = DB.prepare(conn, sql);
					pstmt.setString(1, ch);
					pstmt.setString(2, postch);
					pstmt.setString(3, "securityaffairs");
					pstmt.setString(4,data1);
					
					pstmt.executeUpdate();
				//}
				
	        }
			/*while (it.hasNext()) {
				Element post_inner_wrapper = (Element) it.next();
				Elements post_detail_large_space = (Elements) it.next().child(3).getElementsByTag("a");
				String time=post_detail_large_space.get(0).text();
				String name=post_detail_large_space.get(1).text();
				date= fullDateFormat.parse(time);
				String data1=df.format(date);
				Elements post_header_wrapper=post_inner_wrapper.getElementsByClass("post_header_wrapper");
 				Elements postdetail_large=post_inner_wrapper.getElementsByClass("post_wrapper_inner");
				String header_wrapper=postdetail_large.html();
				String ee=post_header_wrapper.first().getElementsByTag("a").text();
				//Elements e=new Elements(ee);
				String post_wrapper_inner=post_inner_wrapper.getElementsByClass("post_wrapper_inner").first().getElementsByTag("p").text();
				String ch=trans(ee);
				String postch=trans(post_wrapper_inner);
				String sql = "insert into secnews values (null, ?, ?, ? ,?)";
				System.out.println(ch);
				PreparedStatement pstmt = DB.prepare(conn, sql);
					pstmt.setString(1, ch);
					pstmt.setString(2, postch);
					pstmt.setString(3, "securityaffairs");
					pstmt.setString(4,data1);
					
					pstmt.executeUpdate();
				
		                                }*/
		}
	//改变状态
	public void addstatus() throws ParseException, SQLException
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String datanow = df.format(new Date());
		Connection conn = DB.getConn();
		
		
			String sql = "insert into newstatue values (null, ?, ?)";
			PreparedStatement pstmt = DB.prepare(conn, sql);
			ResultSet rsKey = null;
				pstmt.setString(1, datanow);
				pstmt.setString(2, "1");
				
				pstmt.executeUpdate();
			
	}
	public String trans(String en)
	{
		String APP_ID = "20170619000059350";
	    String SECURITY_KEY = "XgjWsK049O6imhi9N8h2";
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		String src=null;
		String st=null;
		try {
			String s=api.getTransResult(en, "en", "zh");
			JSONObject parse =new JSONObject(s);
			String s1=parse.get("trans_result").toString();
			JSONArray js=new JSONArray(s1);
			System.out.println();
			for (int i = 0; i < js.length(); i++) { 
				JSONObject jsonobj = js.getJSONObject(i); 
		        String dst = jsonobj.getString("dst"); 
		       src = jsonobj.getString("src");
		       st=dst+"\n"+src;
		       System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
	public void addurl(String code,String url) throws ParseException, SQLException
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String datanow = df.format(new Date());
		Date datanow1=(Date) df.parse(datanow);
		Date date = null;
		Connection conn = DB.getConn();
		
			// Node titleNode= element.child(0).childNode(0);
			String sql = "insert into url values (null, ?, ?)";
			PreparedStatement pstmt = DB.prepare(conn, sql);
			ResultSet rsKey = null;
				pstmt.setString(1, code);
				pstmt.setString(2, url);
				pstmt.executeUpdate();
			
	}
	public void addstock(String name1,String stockcode,String stockname,String province,String mail,String address,String buniness) throws ParseException, SQLException
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String datanow = df.format(new Date());
		Date datanow1=(Date) df.parse(datanow);
		Date date = null;
		Connection conn = DB.getConn();
		
			// Node titleNode= element.child(0).childNode(0);
			String sql = "insert into stock values (null,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = DB.prepare(conn, sql);
			ResultSet rsKey = null;
				pstmt.setString(1, name1);
				pstmt.setString(2, stockcode);
				pstmt.setString(3, stockname);
				pstmt.setString(4, province);
				pstmt.setString(5, mail);
				pstmt.setString(6, address);
				pstmt.setString(7, buniness);
				pstmt.executeUpdate();
			
	}
	public List<stock> search()
	{
		List<stock> stock = new ArrayList<stock>();
		Connection conn = DB.getConn();
		String sql = "select * from stock order by id";
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				stock c = this.getstockFromRs(rs);
				stock.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return stock;
	}
	public List<stock> getuser()
	{
		List<stock> stock = new ArrayList<stock>();
		Connection conn = DB.getConn();
		String sql = "select * from user order by id";
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				stock c = this.getstockFromRs(rs);
				stock.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return stock;
	}
}
	