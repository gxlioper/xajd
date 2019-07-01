package xgxt.rcsw.jssp.zds;

import java.util.HashMap;

import xgxt.DAO.DAO;

public class JsspZdsService {
	
	/**
	 * 初始化时间
	 * @return
	 * @throws Exception 
	 */
	public boolean zxdksjcsh() throws Exception{
		DAO dao=DAO.getInstance();
		boolean blog=false;
		String delSql="delete from rcsw_jssp_sjsz";
		String  insertSql=" insert into rcsw_jssp_sjsz(xydm,sqkssj,sqjssj) select distinct(xydm),'19000101','19000101' from view_njxyzybj ";
		blog=dao.runUpdate(delSql, new String[]{});
		if(blog){
			blog=dao.runUpdate(insertSql, new String[]{});
		}
		return blog;
	}
	
	/**
	 * 
	 */
	public boolean checkSqsj(JsspZdsForm zdsForm){
		JsspZdsDao dao=new JsspZdsDao();
		return dao.checkSqsj(zdsForm);
	}
}
