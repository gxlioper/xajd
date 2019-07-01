package xgxt.rcsw.jssp.zds;

import java.util.HashMap;

import xgxt.DAO.DAO;

public class JsspZdsDao {


	/**
	 * …Í«Î ±º‰
	 */
	public boolean checkSqsj(JsspZdsForm zdsForm){
		 DAO dao=DAO.getInstance();  
		 String sql=" select count(1)num from rcsw_jssp_sjsz where xydm=? and replace(sqkssj,'-','')<=? and replace(sqjssj,'-','')>=? ";
		 HashMap<String,String>hashMap=dao.getMap(sql, new String[]{zdsForm.getSave_xydm(),zdsForm.getSave_sqsj(),zdsForm.getSave_sqsj()}, new String[]{"num"});
		 int num=Integer.parseInt(hashMap.get("num"));
		 if(num>0){
			 return true;
		 }else {
			 return false;
		 }
	}
}
