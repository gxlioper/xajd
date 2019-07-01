package xsgzgl.jcsj.comm;

public class JcsjService {
	
	private JcsjDao dao=new JcsjDao();
	
	public String getYcsjCount(String ycb){
		return dao.getYcsjCount(ycb);
	}

}
