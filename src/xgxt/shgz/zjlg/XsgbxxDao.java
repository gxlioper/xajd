package xgxt.shgz.zjlg;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.zjlg.util.XljkUtil;

public class XsgbxxDao {

	//学生干部信息增加
	public boolean dao_xlzxsAdd(XsgbxxModel model,String tableName,HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		String sql = "";
		String pk = model.getId();
		String[] input = null;
		String tjlx = model.getTjlx();
		if(StringUtils.isNotNull(pk)){
			if ("gzzj".equals(tjlx)) {
				input = new String[] {"gzzjlx","xn","xq","yf","gzzj"};
			}else if ("hdch".equals(tjlx)) {
				input = new String[] {"hdchlx","xn","xq","yf","hdcd"};
			}else {
				input = new String[] { "sszz", "srzw", "drxzsj", "zhaop","grjl", "jcqk", "gzzj", "hdch", "zwpd", "zwpdsj",
						"sjbmpdyj", "sjbmpdsj", "zongp", "bz", "xn", "xq","yf", "zwpdlx", "sjbmpdlx", "zppdsj", "zppdlx" };
			}
			sql = XljkUtil.updateSqlForModel(input, model, tableName, "id",
					pk);
			result = StandardOperation.update(tableName, sql, request);
		}else {
			if ("gzzj".equals(tjlx)) {
				input = new String[] {"xh","gzzjlx","xn","xq","yf","xm","xb","xydm","bjdm","gzzj"};
			}else if ("hdch".equals(tjlx)) {
				input = new String[] {"xh","hdchlx","xn","xq","yf","xm","xb","xydm","bjdm","hdcd"};
			}else {
				input = new String[] { "xh", "xm", "xb", "xydm", "bjdm","sszz", "srzw", "drxzsj", "zhaop", "grjl", "jcqk",
						"gzzj", "hdch", "zwpd", "zwpdsj", "sjbmpdyj","sjbmpdsj", "zongp", "bz", "xn", "xq", "yf", "zwpdlx",
						"sjbmpdlx", "zppdsj", "zppdlx" };
			}
			sql = XljkUtil.insertSqlForModel(input, model, tableName);
			result = dao.insert(sql, new String[] {});
		}
		return result;
	}
	//学生干部信息查询
	public ArrayList<String[]> dao_xlzxsQuery(XsgbxxModel model,String tableName) throws IllegalArgumentException, 
			SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		String xm = model.getXm();
		String xb = model.getXb();
		String xh = model.getXh();
		String xydm = model.getXydm();
		String bjdm = model.getBjdm();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		query.append(Base.isNull(xb) ? " and 1=1" : " and xb='"+xb.trim()+ "'");
		query.append(Base.isNull(xh) ? " and 1=1" : " and xh like '%"+xh.trim()+ "%'");	
//		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm like '%"+xydm.trim()+ "%'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm.trim()+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm.trim()+ "'");

		String[] colList = null;
		String sql = "";
		if ("zjlg_gzzjb".equals(tableName)) {
			colList = new String[]{"id","xh","gzzjlx","xm","xb","xn","xq","yf","xymc","bjmc","gzzjsj"};
			sql = "(select  rownum r,a.*,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjmc  from "+tableName+" a "+query.toString()+") ";
		}else if ("zjlg_hdchb".equals(tableName)) {
			colList = new String[]{"id","xh","hdchlx","xn","xq","yf","xm","xb","xymc","bjmc","hdcdsj"};
			sql = "(select  rownum r,a.*,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjmc  from "+tableName+" a "+query.toString()+") ";
		}else {
			colList = new String[]{"id","xh","xm","xb","xymc","bjmc","sszz","srzw"};
			sql = "(select  rownum r,a.*,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjmc  from "+tableName+" a "+query.toString()+") ";
		}
		return commonQuery(sql, query.toString(), new String[] {}, colList,"", model);	
	}
	/**
	 * Common query.
	 * 
	 * @param innerSql 查询内嵌sql语句
	 * @param query 查询条件
	 * @param inPutList the in put list
	 * @param colList the col list
	 * @param sql the sql
	 * @param model the model
	 * 
	 * @return the array list< string[]>
	 * 
	 */
	public static ArrayList<String[]> commonQuery(String innerSql,String query,String [] inPutList,
			String [] colList,String sql, Object model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
		int size = colList.length-1;
		Class<? extends Object> myClass = model.getClass();
		Pages pages = (Pages)myClass.getMethod("getPages",(Class[]) null).invoke(model,(Object[]) null);
		String count = dao.getOneRs("select count(*) count from "+innerSql, inPutList, "count");
		pages.setMaxRecord(Integer.parseInt(StringUtils.isNull(count)?"0":count));
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select * from (select rownum r,");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(innerSql); 
//			sqlBuffer.append(query);
			sqlBuffer.append(")");
			sqlBuffer.append(" where r > "); 
			sqlBuffer.append(pages.getStart());
			sqlBuffer.append(" and r <= ");
			sqlBuffer.append((pages.getStart() + pages.getPageSize()));
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	/*
	 * 获取表头
	 */
	public List<HashMap<String, String>> getToptrTitle(String tableName) {
			DAO dao = DAO.getInstance();
			String[] colList = null;
			if ("zjlg_gzzjb".equals(tableName)) {
				colList = new String[] {"id","xh","gzzjlx","xm","xb","xn","xq","yf","xydm","bjdm","gzzjsj"};
			}else if ("zjlg_hdchb".equals(tableName)) {
				colList = new String[] {"id","xh","hdchlx","xn","xq","yf","xm","xb","xydm","bjdm","hdcdsj"};
			}else if ("view_xsgzgb".equals(tableName)) {
				colList = new String[] {"id","xh","zwpdlx","xn","xq","yf","xm","xb","xydm","bjdm","hdcdsj"};
			}else{
				colList = new String[] {"id","xh","xm","xb","xydm","bjdm","sszz","srzw"};
			}
			String[] colListCN = dao.getColumnNameCN(colList, tableName);
			List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);
			return topTr;
	}
	//学生干部信息查询
	public HashMap<String, String> dao_idforQuery(String pk,String tableName){
		DAO dao = DAO.getInstance();
		String[] outputValue = null;
		String[] inputValue = {pk};
		String sql = "select * from "+tableName+" where id = ?";
		if ("zjlg_gzzjb".equals(tableName)) {
			sql = "select a.*,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjmc from zjlg_gzzjb a where id = ?";
			outputValue = new String[]{"id","xh","xn","xq","yf","xm","xb","xydm","bjdm","gzzj","gzzjsj","xymc","bjmc","gzzjlx"};
		}else if ("zjlg_hdchb".equals(tableName)) {
			sql = "select a.*,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjmc from zjlg_hdchb a where id = ?";
			outputValue = new String[]{"id","xh","xn","xq","yf","xm","xb","xydm","bjdm","hdcd","hdcdsj","xymc","bjmc","hdchlx"};
		}else {
			sql = "select a.*,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjmc from zjlg_xsgbxxb a where id = ?";
			outputValue = new String[]{"id","xymc","bjmc","xh","xm","xb","xydm","bjdm","sszz","srzw","drxzsj","zhaop","grjl","jcqk","gzzj","hdch","zwpd","zwpdsj","sjbmpdyj","sjbmpdsj","zongp","bz","xn","xq","yf","zwpdlx","sjbmpdlx","zppdsj","zppdlx"};
		}
		HashMap<String, String> map = dao.getMap(sql, inputValue, outputValue);
		return map;	
	}
	/**
	 * 批量删除
	 */
	public String dao_AllDelList(String pks,String tableName)
	throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from "+tableName+" where id = '" + pk + "'";
			sb.append(sql);
			sb.append("!!");
		}
		pksql = sb.toString().split("!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	/**
	 * 数据导出
	 */
	public void dao_expData(String tabName,HttpServletResponse response,XsgbxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		String xm = model.getXm();
		String xb = model.getXb();
		String xh = model.getXh();
		String xydm = model.getXydm();
		String bjdm = model.getBjdm();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		query.append(Base.isNull(xb) ? " and 1=1" : " and xb='"+xb.trim()+ "'");
		query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");	
//		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm like '%"+xydm.trim()+ "%'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm.trim()+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm.trim()+ "'");
//		query.append(Base.isNull(bj) ? " and 1=1" : " and bj='"+bj.trim()+ "'");
		Vector<Object> vec = new Vector<Object>();
		String sql = "";
		if ("zjlg_gzzjb".equals(tabName)) {
			sql = "select xh,xn,xq,yf,xm,xb,gzzj,gzzjsj,(select distinct xymc from view_njxyzybj b where a.xydm = b.xydm) xydm,(select distinct bjmc from view_njxyzybj b where a.bjdm = b.bjdm) bjdm from "
					+ tabName + " a " + query.toString();
		}else if ("zjlg_hdchb".equals(tabName)) {
			sql = "select xh,xn,xq,yf,xm,xb,hdcd,hdcdsj,(select distinct xymc from view_njxyzybj b where a.xydm = b.xydm) xydm,(select distinct bjmc from view_njxyzybj b where a.bjdm = b.bjdm) bjdm from "
				+ tabName + " a " + query.toString();
		}else if ("view_xsgzgb".equals(tabName)) {
			sql = "select xh,xm,xb,xymc,bjmc,sszz,srzw,drxzsj,zhaop,grjl,jcqk,zwpd,sjbmpdyj,zongp,bz,xn,xq,yf,zwpdlx,hdcd,hdcdsj,gzzj,gzzjsj from "+ tabName + " a " + query.toString();
		}else {
			sql = "select xh,xm,xb,sszz,srzw,drxzsj,grjl,jcqk,zwpd,xn,xq,yf,zwpdlx,(select distinct xymc from view_njxyzybj b where a.xydm = b.xydm) xydm,(select distinct bjmc from view_njxyzybj b where a.bjdm = b.bjdm) bjdm from "
				+ tabName + " a " + query.toString();
		}
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
			String ColumnName[] = dao.getColumnName(sql);
			String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, tabName.toUpperCase());
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	//
	public HashMap<String, String> dao_getxsInfo(String xh) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotNull(xh)) {
			String sql = "select xh,xm,xb,xydm,xymc,bjdm,bjmc from view_xsxxb where xh=?";
			String[] outputValue = new String[]{"xh","xm","xb","xydm","xymc","bjdm","bjmc"};
			map = dao.getMap(sql, new String[]{xh}, outputValue);
		}
		return map;
	}
	public boolean dao_isexistsxlwy(XsgbxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		boolean isexists = false;
		HashMap<String, String> map = new HashMap<String, String>();
		String xn = model.getXn();
		String xh = model.getXh();
		String xq = model.getXq();
		String yf = model.getYf();
		String pkValue = "";
		String pk = "";
		String zwpdlx = model.getZwpdlx();
		if ("学年".equals(zwpdlx)) {
			pk = "xh||xn";
			pkValue = xh+xn;
		}else if ("学期".equals(zwpdlx)) {
			pk = "xh||xn||xq";
			pkValue = xh+xn+xq;
		}else {
			pk = "xh||xn||xq||yf";
			pkValue = xh+xn+xq+yf;
		}
		if (StringUtils.isNotNull(xh)) {
			String sql = "select * from zjlg_xsgbxxb where "+pk+"=?";
			map = dao.getMap(sql, new String[]{pkValue}, new String[]{"xm"});
		}
		if(map != null && map.size()>0){
			isexists = true;
		}
		return isexists;
	}
	//是否主席团考核人员
	public String dao_iszxtkhry(String yhm,String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String userlx = "";
		String sql = "select * from yhb  where yhm=? and zdm = '00000241'";
		map = dao.getMap(sql, new String[]{yhm}, new String[]{"yhm"});
		if(map != null && map.size()>0){
			userlx = "khry";
		}else if ("xx".equals(userType) || "admin".equals(userType)) {
			userlx = "xx";
		}
		return userlx;
	}
	//评定意见查询
	public ArrayList<String[]> dao_pdyjQuery(XsgbxxModel model,String tableName) throws IllegalArgumentException, 
			SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		String xm = model.getXm();
		String xb = model.getXb();
		String xh = model.getXh();
		String xydm = model.getXydm();
		String bjdm = model.getBjdm();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		query.append(Base.isNull(xb) ? " and 1=1" : " and xb='"+xb.trim()+ "'");
		query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");	
//		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm like '%"+xydm.trim()+ "%'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm.trim()+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm.trim()+ "'");

		String[] colList = null;
		String sql = "";
		colList = new String[]{"id","xh","zwpdlx","xn","xq","yf","xm","xb","xydm","bjdm","hdcdsj"};
		sql = "(select a.*  from view_xsgzgb a "+query.toString()+") ";
		
		return commonQuery(sql, query.toString(), new String[] {}, colList,"", model);	
	}
	//学生干部信息增加
	public boolean dao_pdyjAdd(XsgbxxModel model,String tableName,HttpServletRequest request) throws Exception {
//		DAO dao = DAO.getInstance();
		boolean result = false;
		String sql = "";
		String pk = model.getId();
		String tjlx = model.getTjlx();
		if(StringUtils.isNotNull(pk)){
			if ("khry".equals(tjlx)) {
				sql = XljkUtil.updateSqlForModel(new String[]{"sjbmpdyj"}, model, tableName, "id", pk);
			} else {
				sql = XljkUtil.updateSqlForModel(new String[]{"zongp","bz"}, model, tableName, "id", pk);
			}
			result = StandardOperation.update("zjlg_xsgbxxb", sql, request);
		}
//		else {
//			if ("khry".equals(tjlx)) {
//				sql = XljkUtil.insertSqlForModel(new String[]{"sjbmpdyj"}, model, tableName);
//			} else {
//				sql = XljkUtil.updateSqlForModel(new String[]{"zongp","bz"}, model, tableName, "id", pk);
//			}
//			result = dao.insert(sql, new String[] {});
//		}
		return result;
	}
	//上传照片
	public String[] dao_ixexistxzp(String xh) throws Exception {
		DAO dao = DAO.getInstance();
		String sxsq = "select zhaop from zjlg_xsgbxxb where xh='"+xh+"'";
		String[] result = dao.getArray(sxsq, new String[]{},"zhaop");
		return result;
	}
	//上传照片
	public boolean dao_addzp(String xh,String savepath,HttpServletRequest request) throws Exception {
		String sql = "update zjlg_xsgbxxb set zhaop='"+savepath+"' where xh='"+xh+"'";
		boolean judge = StandardOperation.update("zjlg_xsgbxxb", sql, request);
		return judge;
	}
}
