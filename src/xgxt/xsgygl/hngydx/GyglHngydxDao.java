/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-21 下午07:14:56</p>
 */
package xgxt.xsgygl.hngydx;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class GyglHngydxDao {
	public List<HashMap<String,String>> dao_HmcTj(String xb){
		DAO dao = DAO.getInstance();
		StringBuffer sql    = new StringBuffer();
		StringBuffer querry = new StringBuffer(" where 1=1 ");		
		querry.append(Base.isNull(xb)?"":" and xb='"+xb+"' ");		
		sql.append(" select lddm, ldmc,fjzs,mfjrs,cwzs,rzrs,cwzs-rzrs kyrs,xb from (select lddm,ldmc ,count(ssbh)fjzs,");
	    sql.append(" (select cws from ssxxb b where a.lddm=b.lddm and rownum=1)mfjrs,sum(nvl(cws,0))cwzs,");
	    sql.append(" (select count(xh) from view_xszsxx b where a.lddm=b.lddm )rzrs,(select xbxd from sslddmb b ");
	    sql.append(" where a.lddm=b.lddm)xb from view_ssxx a group by lddm,ldmc order by lddm)"+querry+" order by lddm,xb");
	    List<HashMap<String,String>> result = dao.getList(sql.toString(),new String[]{},new String[]{"ldmc","fjzs","mfjrs","cwzs","rzrs","kyrs" });	    
	    return result;	    
	}
}
