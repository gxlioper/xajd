/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-1-4 下午04:30:09</p>
 */
package xgxt.sztz.csmzzyjsxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class SztzCsmzDAO {
	public HashMap<String,String> dao_getInfo(String table,String pk,String pkValue){
		DAO dao = DAO.getInstance();
		String[] colList =  dao.getColumnName("select * from "+table);
		for(int i=0;i<colList.length;i++){
			colList[i]=colList[i].toLowerCase();
		}
		String sql = "select * from "+table;
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		if(Base.isNull(pk)){
			querry.append(" and 1=1 ");
		}else{
			querry.append(" and "+pk+"='"+pkValue+"'");
		}
		return dao.getMap(sql+querry.toString(),new String[]{},colList);
	}
	
	public ArrayList<String []> dao_getxfSbResult(String userName,String bjdm){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		StringBuffer querry = new StringBuffer();
		sql.append(" select bjdm,xm,xh,''sxddsy_xf,''zyfw_xf,''kxjs_xf,''whys_xf,''shstgz_xf,''jnpx_xf,''xfhj from view_xsjbxx a where 1=1 ");
		if(!Base.isNull(bjdm)){
			sql.append(" and bjdm= '"+bjdm+"' order by a.xh ");
		}else{
			sql.append(" and exists (select bjdm from view_xsjbxx b where a.bjdm=b.bjdm and xh='"+userName+"') order by a.xh");
		}
		String[] colList = new String[]{"bjdm","xm","xh","sxddsy_xf","zyfw_xf","kxjs_xf","whys_xf","shstgz_xf","jnpx_xf","xfhj"};
		return dao.rsToVator(sql + querry.toString(), new String[]{},colList);		  
	}
	public boolean dao_xfSbSave(SztzCsmzXfsbModel model) throws SQLException{
		DAO dao   = DAO.getInstance();
		String xn   = model.getXn();
		String[] xm = model.getXm();
		String[] xh = model.getXh();
		String[] bjdmV = model.getBjdmV();
		String[] sxddsy_xf = model.getSxddsy_xf();
		String[] zyfw_xf   = model.getZyfw_xf();
		String[] kxjs_xf   = model.getKxjs_xf();
		String[] whys_xf   = model.getWhys_xf();
		String[] shstgz_xf = model.getShstgz_xf();
		String[] jnpx_xf   = model.getJnpx_xf();
		String[] xfhj      = model.getXfhj();
		String[] inserSql  = new String[xh.length];
		String[] delSql    = new String[xh.length];
        for(int i=0;i<xh.length;i++){
        	delSql[i] = "delete from sztz_xfb where xn='"+DealString.toGBK(xn)+"' and bjdm ='"+DealString.toGBK(bjdmV[i])+"' and xh='"+DealString.toGBK(xh[i])+"'";
        	inserSql[i] = "insert into sztz_xfb(xn,bjdm,xh,xm,sxddsy_xf,zyfw_xf,kxjs_xf,whys_xf,shstgz_xf,jnpx_xf,xfhj) " +
        			     "values('"+DealString.toGBK(xn)+"','"+DealString.toGBK(bjdmV[i])+"','"+DealString.toGBK(xh[i])+"','"+DealString.toGBK(xm[i])+"','" +
        			     DealString.toGBK(sxddsy_xf[i])+"','"+DealString.toGBK(zyfw_xf[i])+"','"+DealString.toGBK(kxjs_xf[i])+"','" +
        			     DealString.toGBK(whys_xf[i])+"','"+DealString.toGBK(shstgz_xf[i])+"','"+DealString.toGBK(jnpx_xf[i])+"','"+DealString.toGBK(xfhj[i])+"')";
        }
		String[] exesql = dao.unionArray(delSql, inserSql);
		int[] array = null;
		array = dao.runBatch(exesql);
		boolean doFlag = dao.checkBatch(array);			
        return doFlag;
	}
	public ArrayList<HashMap<String, String>> dao_getxfSbMTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "xn","nj","xymc","zymc","bjmc","xxsh"};//查询显示字段 
		colListCN = new String[]{ "主键","学年","年级",Base.YXPZXY_KEY,"专业","班级","学校审核"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	} 	
	public ArrayList<String[]> dao_getxfSbMResult(SztzCsmzXfsbModel model,String yesNo,String userType) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String pk   = "xn||bjdm";
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//查询条件	
		String xn = DealString.toGBK(model.getXn());
		String nj = DealString.toGBK(model.getNj());
		String xydm = DealString.toGBK(model.getXydm());
		String zydm = DealString.toGBK(model.getZydm());
		String bjdm = DealString.toGBK(model.getBjdm());
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"'");
		colList = new String[]{"prkey", "xn","nj","xymc","zymc","bjmc","xxsh"};//查询显示字段 
		sql     = " select "+pk+" prkey,xn,nj,xymc,zymc,bjmc,xxsh from view_xfsbxx ";  
//		执行查询
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public ArrayList<String []> dao_getxfSbXx(String pkValue){
		DAO dao = DAO.getInstance();
		String pk   = "xn||bjdm";
		StringBuffer sql = new StringBuffer();		
		sql.append(" select bjdm,xm,xh,sxddsy_xf,zyfw_xf,kxjs_xf,whys_xf,shstgz_xf,jnpx_xf,xfhj from sztz_xfb  where "+pk+"= ? order by xh");
		String[] colList = new String[]{"bjdm","xm","xh","sxddsy_xf","zyfw_xf","kxjs_xf","whys_xf","shstgz_xf","jnpx_xf","xfhj"};
		return dao.rsToVator(sql.toString(), new String[]{pkValue},colList);		  
	}
	public boolean dao_xfSbxxDel(String pkValue) throws Exception{
		DAO dao = DAO.getInstance();
		String pk   = "xn||bjdm";
		String sql  = "delete sztz_xfb where "+pk+"=? ";
		return dao.runUpdate(sql,new String[]{pkValue});
	}
	public boolean dao_xfsbSh(String pkValue,String shType,String userType) throws Exception{		
		DAO dao = DAO.getInstance();
		String shV = "未审核";
		String pk   = "xn||bjdm";
		if("yes".equalsIgnoreCase(shType)){
			shV="通过";
		}else{
			shV="不通过";
		}
		String sql = " update sztz_xfb set xxsh=? where "+pk+"=? ";
		return dao.runUpdate(sql, new String[]{shV,pkValue});
	}
	public boolean dao_plXfSh(String pkValue,String shType,String userType) throws Exception{		
		DAO dao = DAO.getInstance();
		String[] pkValueA = pkValue.split("!!");
		String sql = "";
		StringBuffer sqlStr = new StringBuffer();
		String shV = "未审核";
		if("yes".equalsIgnoreCase(shType)){
			shV="通过";
		}else{
			shV="不通过";
		}
		for(int i=0;i<pkValueA.length;i++){
			sql = " update sztz_xfb set xxsh='"+shV+"' where xn||bjdm='"+pkValueA[i]+"' ";
			sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
		}
		boolean doFlag = false;
		int[] array = dao.runBatch(sqlStr.toString().split("!!"));
		doFlag = dao.checkBatch(array); 	    
		return doFlag; 
	}	
}
