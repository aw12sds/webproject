package test;

import java.util.List;

import com.jjj.dao.CrimeDAO;
import com.jjj.dao.CrimeDAOImpl;

public class triangle {
	private CrimeDAOImpl cd;
	
	public CrimeDAOImpl getCd() {
		return cd;
	}

	public void setCd(CrimeDAOImpl cd) {
		this.cd = cd;
	}

	public void draw()
	{
		CrimeDAOImpl cd=getCd();
		
		
		System.out.println(cd.getnews().size()+"aa");
	}
}
