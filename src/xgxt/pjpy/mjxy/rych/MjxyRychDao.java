package xgxt.pjpy.mjxy.rych;

import xgxt.DAO.DAO;

public class MjxyRychDao {
	public void insertRychxx(String xh,String brjl,String drzw,String zhpfmc){
		DAO dao=new DAO();
		String delSql="delete from xsrychxxb where xh=?";
		String istSql="insert into xsrychxxb(xh,brjl,drzw,zhpfmc) values(?,?,?,?)";
		try{
			//œ»…æ≥˝‘Ÿ≤Â»Î
			dao.runUpdate(delSql, new String[]{xh});
			dao.runUpdate(istSql, new String[]{xh,brjl,drzw,zhpfmc});
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
