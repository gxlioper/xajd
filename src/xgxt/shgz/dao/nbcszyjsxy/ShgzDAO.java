/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2010-2-3 上午11:30:01</p>
 */
package xgxt.shgz.dao.nbcszyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.shgz.model.nbcszysjxy.ShsjModel;
import xgxt.utils.CommonQueryDAO;

public class ShgzDAO {
	public ArrayList<String[]> shsjXxSearch(ShsjModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String xydm = DealString.toGBK(model.getXydm());
		String zydm = model.getZydm();
		String nj = DealString.toGBK(model.getNj());
		String xn = DealString.toGBK(model.getXn());
		String bjdm = model.getBjdm();
		String xq = model.getXq();
		String xh = model.getXh();		
		String xm = model.getXm();			
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " " : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(nj) ? " " : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " " : " and xn='"+xn+ "'");	
		query.append(Base.isNull(zydm) ? " " : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm)?"":" and bjdm = '"+bjdm+"' ");	
		query.append(Base.isNull(xq)?"":" and xq = '"+xq+"' ");	
		query.append(Base.isNull(xh)?"":" and xh =  '"+xh.trim()+"' ");	
		query.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");	
		String[] colList = new String[]{"pk","xn","xqmc","xh","xm","xymc","bjmc","hdxm","hdnr"};
		StringBuffer sql = new StringBuffer();
		sql.append("(select a.rowid pk,a.xn,a.xq,(select b.xqmc from xqdzb b where a.xq=b.xqdm)xqmc,a.xh,a.hdxm,a.hdnr,b.xm,b.xb,b.nj,b.xydm,b.xymc, ");
		sql.append("b.zydm,b.zymc,b.bjdm,b.bjmc from NBCS_KNSSHHDXXB a, view_xsjbxx b where a.xh=b.xh) ");
		return CommonQueryDAO.commonQuery(sql.toString(), query.toString(), new String[] {}, colList,"", model);		
	}
	public boolean shsjXxSave(ShsjModel model,String pkValue) throws Exception{
		DAO dao = DAO.getInstance(); 
		String  xh = model.getXh();
		String  hdxm = model.getHdxm();
		String  hdnr = model.getHdnr();
		String  hdxz = model.getHdxz();
		String  xn = model.getXn();
		String  xq = model.getXq();
		String  khjg = model.getKhjg();
		String  sjts = model.getSjts();
		String  shry = model.getShry();
		boolean done = false;
		String sql = "";
		if(Base.isNull(pkValue)){
			sql = "insert into nbcs_knsshhdxxb(xh,hdxm,hdnr,hdxz,xn,xq,khjg,sjts,shry)values(?,?,?,?,?,?,?,?,?)";
			done = dao.runUpdate(sql,new String[]{xh,hdxm,hdnr,hdxz,xn,xq,khjg,sjts,shry});
		}else{
			sql = "update nbcs_knsshhdxxb set hdxm=?,hdnr=?,hdxz=?,xn=?,xq=?,khjg=?,sjts=?,shry=? where rowid=?";
			done = dao.runUpdate(sql,new String[]{hdxm,hdnr,hdxz,xn,xq,khjg,sjts,shry,pkValue});
		}
		return done;
	}
	public HashMap<String,String> getShsjXx(String pkValue){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.xn,a.xq,(select b.xqmc from xqdzb b where a.xq=b.xqdm)xqmc,a.xh,a.sj,a.hdxm, ");
		sql.append(" a.hdnr,a.hdxz,a.khjg,a.sjts,a.shry,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm, ");
		sql.append(" b.bjmc from NBCS_KNSSHHDXXB a, view_xsjbxx b where a.xh=b.xh and a.rowid=?   ");
		return dao.getMap(sql.toString(),new String[]{pkValue},new String[]{"xn","xq","xqmc","xh","sj","hdxm","hdnr",
			              "hdxz","khjg","sjts","shry","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc"});		
	}
	public boolean shsjXxDel(String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();			
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete NBCS_KNSSHHDXXB where rowid= '"+pkValueA[i]+"'  ";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
}
