package xsgzgl.jcsj.comm;

import xgxt.DAO.DAO;

public class JcsjDao extends DAO{
	
	public String getYcsjCount(String ycb){
		String sql="select count(1) num from xg_excdatalsb where ycb=lower(trim(?))";
		String num=this.getOneRs(sql, new String[]{ycb}, "num");
		return num;
	}

}
