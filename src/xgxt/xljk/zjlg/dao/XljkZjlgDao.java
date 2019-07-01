package xgxt.xljk.zjlg.dao;


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
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.zjlg.model.XljkZjlgmodel;
import xgxt.xljk.zjlg.util.XljkUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class XljkZjlgDao.
 */
public class XljkZjlgDao {

	//心里咨询师增加
	/**
	 * Dao_xlzxs add.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dao_xlzxsAdd(XljkZjlgmodel model,String tableName,HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		String sql = "";
		String pk = model.getId();
		String[] input = null;
		if(StringUtils.isNotNull(pk)){
			FormModleCommon.formToGBK(model);
			if ("zjlg_xlzxs".equals(tableName)) {
				input = new String[] {"id","xm","xb","nl","dw","bgdh","sjhm","dzyx","zyzz","zxgznx","scfx","zxzbsj","xgpx","kqtj","bz"};
				sql = "update " + tableName + " set xm='" + model.getXm()+ "',xb='" + model.getXb() + "',nl='" + model.getNl()+ "'," + "dw='" + model.getDw() + "',bgdh='"+ model.getBgdh() + "',sjhm='" + model.getSjhm()+ "',dzyx='" + model.getDzyx() + "'," + "zyzz='"+ model.getZyzz() + "',zxgznx='" + model.getZxgznx()+ "',scfx='" + model.getScfx() + "',zxzbsj='"+ model.getZxzbsj()+ "',xgpx='"+ model.getXgpx()+ "',kqtj='"+ model.getKqtj()+ "',bz='"+ model.getBz() + "' where id='" + model.getId()+"'";
			}else if ("zjlg_xljkzdls".equals(tableName)) {
				input = new String[] {"id","xm","xb","nl","xy","bgdh","sjhm","dzyx","xl","byxy","byzy","sc","zxzbsj","xgpx","kqtj","bz"};
				sql = "update " + tableName + " set xm='"+model.getXm()+"',xb='"+model.getXb()+"',nl='"+model.getNl()+"',xy='"+model.getXy()+"',bgdh='"+model.getBgdh()+"',sjhm='"+model.getSjhm()+"',dzyx='"+model.getDzyx()+"',xl='"+model.getXl()+"',byxy='"+model.getByxy()+"',byzy='"+model.getByzy()+"',sc='"+model.getSc()+"',zxzbsj='"+model.getZxzbsj()+"',xgpx='"+ model.getXgpx()+ "',kqtj='"+ model.getKqtj()+ "',bz='"+ model.getBz() + "' where id='" + model.getId()+"'";
			}else if ("zjlg_xlzxxszl".equals(tableName)) {
				input = new String[] {"id","xm","xb","nl","xy","bjdm","lxfs","dzyx","gwlx","zbsj","zc","xgpx","bz"};
				sql = "update " + tableName + " set xm='"+model.getXm()+"',xb='"+model.getXb()+"',nl='"+model.getNl()+"',xy='"+model.getXy()+"',bjdm='"+model.getBjdm()+"',lxfs='"+model.getLxfs()+"',dzyx='"+model.getDzyx()+"',gwlx='"+model.getGwlx()+"',zbsj='"+model.getZbsj()+"',zc='"+model.getZc()+"',xgpx='"+ model.getXgpx()+"',bz='"+ model.getBz() + "' where id='" + model.getId()+ "'";
			}else if ("zjlg_xlxhhy".equals(tableName)) {
				input = new String[] {"id","xm","xb","nl","xy","bj","lxfs","dzyx","xhzy","zc","bz"};
				sql = "update " + tableName + " set xm='"+model.getXm()+"',xb='"+model.getXb()+"',nl='"+model.getNl()+"',xy='"+model.getXy()+"',bj='"+model.getBj()+"',lxfs='"+model.getLxfs()+"',dzyx='"+model.getDzyx()+"',xhzy='"+model.getXhzy()+"',zc='"+model.getZc()+"',bz='"+model.getBz()+"' where id='" + model.getId()+ "'";
			}else if ("zjlg_xlwy".equals(tableName)) {
				input = new String[] {"id","xy","bj","xm","xb","lxfs","qs","cslb","pxcj","pxjl","gzkh","xlwy","xlwypxqk"};
				sql = "update " + tableName + " set xy='"+model.getXy()+"',bj='"+model.getBj()+"',xm='"+model.getXm()+"',xb='"+model.getXb()+"',lxfs='"+model.getLxfs()+"',qs='"+model.getQs()+"',cslb='"+model.getCslb()+"',pxcj='"+model.getPxcj()+"',pxjl='"+model.getPxjl()+"',gzkh='"+model.getGzkh()+"',xlwy='"+model.getXlwy()+"',xlwypxqk='"+model.getXlwypxqk()+"' where id='" + model.getId()+"'";
			}else if ("zjlg_gzlxb".equals(tableName)) {
				input = new String[] {"id","lxfs","jlsd","txdt","txqk","xycljg","sffk","xyxljkzdls"};
				sql = "update " + tableName + " set lxfs='"+model.getLxfs()+"',jlsd='"+model.getJlsd()+"',txdt='"+model.getTxdt()+"',txqk='"+model.getTxqk()+"',xycljg='"+model.getXycljg()+"',sffk='"+model.getSffk()+"',xyxljkzdls='"+model.getXyxljkzdls()+"' where id='" + model.getId()+"'";
			}
			result = StandardOperation.update(tableName, sql, request);
		}else {
			if ("zjlg_xlzxs".equals(tableName)) {
				input = new String[]{"xm","xb","nl","dw","bgdh","sjhm","dzyx","zyzz","zxgznx","scfx","zxzbsj","xgpx","kqtj","bz"};
				sql = "insert into "+tableName+"(xm,xb,nl,dw,bgdh,sjhm,dzyx,zyzz,zxgznx,scfx,zxzbsj,xgpx,kqtj,bz) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			}else if ("zjlg_xljkzdls".equals(tableName)) {
				input = new String[]{"xm","xb","nl","xy","bgdh","sjhm","dzyx","xl","byxy","byzy","sc","zxzbsj","xgpx","kqtj","bz"};
				sql = "insert into "+tableName+"(xm,xb,nl,xy,bgdh,sjhm,dzyx,xl,byxy,byzy,sc,zxzbsj,xgpx,kqtj,bz) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			}else if ("zjlg_xlzxxszl".equals(tableName)) {
				input = new String[]{"xh","xm","xb","nl","xy","bjdm","lxfs","dzyx","gwlx","zbsj","zc","xgpx","bz"};
				sql = "insert into "+tableName+"(xh,xm,xb,nl,xy,bjdm,lxfs,dzyx,gwlx,zbsj,zc,xgpx,bz) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			}else if ("zjlg_xlxhhy".equals(tableName)) {
				input = new String[]{"xm","xh","xb","nl","xy","bj","lxfs","dzyx","xhzy","zc","bz"};
				sql = "insert into "+tableName+"(xm,xh,xb,nl,xy,bj,lxfs,dzyx,xhzy,zc,bz) values(?,?,?,?,?,?,?,?,?,?,?)";
			}else if ("zjlg_xlwy".equals(tableName)) {
				input = new String[]{"xy","bj","xm","xb","lxfs","qs","cslb","pxcj","pxjl","gzkh","xlwy","xlwypxqk","xh"};
				sql = "insert into "+tableName+"(xy,bj,xm,xb,lxfs,qs,cslb,pxcj,pxjl,gzkh,xlwy,xlwypxqk,xh) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			}else if ("zjlg_gzlxb".equals(tableName)) {
				input = new String[] {"xh","xlwyxm","xydm","bjdm","lxfs","jlsd","txdt","txqk","xycljg","xyxljkzdls"};
				sql = "insert into "+tableName+"(xh,xlwyxm,xydm,bjdm,lxfs,jlsd,txdt,txqk,xycljg,xyxljkzdls) values(?,?,?,?,?,?,?,?,?,?)";
			}
			String[] inputList = FormModleCommon.modelToStrings(input, model);
			result = dao.insert(sql, inputList);
		}
		
		return result;
	}
	//心里咨询师查询
	/**
	 * Dao_xlzxs query.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return the array list< string[]>
	 * 
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws SecurityException the security exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 */
	public ArrayList<String[]> dao_xlzxsQuery(XljkZjlgmodel model,String tableName) throws IllegalArgumentException, 
			SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String xm = model.getXm();
		String xb = model.getXb();
		String nl = model.getNl();
		String dw = model.getDw();
		String xy = model.getXy();
		String bjdm = model.getBjdm();
		String bj = model.getBj();
		String byxx = model.getByxx();
		String byzy = model.getByzy();
		String zxzbsj = model.getZxzbsj();
		String zydm = model.getZydm();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm.trim()+ "'");
		query.append(Base.isNull(xb) ? " and 1=1" : " and xb='"+xb.trim()+ "'");
		query.append(Base.isNull(nl) ? " and 1=1" : " and nl='"+nl.trim()+ "'");	
		query.append(Base.isNull(dw) ? " and 1=1" : " and dw like '%"+dw.trim()+ "%'");
		if ("zjlg_gzlxb".equals(tableName)) {
			query.append(Base.isNull(xy) ? " and 1=1" : " and xydm='"+xy.trim()+ "'");
			query.append(Base.isNull(xm) ? " and 1=1" : " and xlwyxm like '%"+xm.trim()+ "%'");
			if (StringUtils.isNotNull(model.getUserlx())) {
				query.append(Base.isNull(model.getUserlx()) ? " and 1=1" : " and sffk like '%是%'");
			}
		}else if ("zjlg_xlcb".equals(tableName)) {
			String xh = model.getXh();
			
			String sfdszn = model.getSfdszn();
			String sfkns = model.getSfkns();
			String sfdq = model.getSfdq();
			String xlcslb = model.getXlcslb();
			String xlwtlx = model.getXlwtlx();
			
			query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");
			query.append(Base.isNull(xy) ? " and 1=1" : " and xydm='"+xy.trim()+ "'");
			query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
			query.append(Base.isNull(sfdszn) ? " and 1=1" : " and sfdszn='"+sfdszn.trim()+ "'");
			query.append(Base.isNull(sfkns) ? " and 1=1" : " and sfkns='"+sfkns.trim()+ "'");
			query.append(Base.isNull(sfdq) ? " and 1=1" : " and sfdq='"+sfdq.trim()+ "'");
			query.append(Base.isNull(xlcslb) ? " and 1=1" : " and xlcslb='"+xlcslb.trim()+ "'");
			query.append(Base.isNull(xlwtlx) ? " and 1=1" : " and xlwtlx='"+xlwtlx.trim()+ "'");
		} else if("zjlg_xljkzdls".equals(tableName)){
			query.append(Base.isNull(xy) ? " and 1=1" : " and xy='"+xy.trim()+ "'");
			query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		}else {
			query.append(Base.isNull(xy) ? " and 1=1" : " and xydm='"+xy.trim()+ "'");
			query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		}
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm.trim()+ "'");
		query.append(Base.isNull(bj) ? " and 1=1" : " and bjdm='"+bj.trim()+ "'");
		query.append(Base.isNull(byxx) ? " and 1=1" : " and byxx like '%"+byxx.trim()+ "%'");
		query.append(Base.isNull(byzy) ? " and 1=1" : " and byzy like '%"+byzy.trim()+ "%'");
		query.append(Base.isNull(zxzbsj) ? " and 1=1" : " and zxzbsj='"+zxzbsj.trim()+ "'");
		String[] colList = null;
		String sql = "";
		if ("zjlg_xlzxs".equals(tableName)) {
			colList = new String[]{"id","xm","xb","nl","dw","bgdh","sjhm","dzyx","zyzz","zxgznx","zxzbsj"};
			sql = "(select  rownum r,id,xm,xb,nl,dw,bgdh,sjhm,dzyx,zyzz,zxgznx,scfx,zxzbsj from "+tableName+" a "+query.toString()+") ";
		}else if ("zjlg_xljkzdls".equals(tableName)) {
			colList = new String[]{"id","xm","xb","nl","xy","bgdh","sjhm","dzyx","xl","byxy","byzy","zxzbsj"};
			sql = "(select  rownum r,id,xm,xb,nl,(select distinct xymc from view_njxyzybj b where a.xy=b.xydm) xy,xy xydm,bgdh,sjhm,dzyx,xl,byxy,byzy,sc,zxzbsj from "+tableName+" a "+query.toString()+") ";
		}else if ("zjlg_xlzxxszl".equals(tableName)) {
			colList = new String[]{"id","xm","xb","nl","xy","bj","lxfs","dzyx","gwlx","zbsj"};
			sql = "(select * from zjlg_xlzxxszl_view a "+query.toString()+") ";
		}else if ("zjlg_xlxhhy".equals(tableName)) {
			colList = new String[]{"id","xm","xb","nl","xy","bj","lxfs","dzyx","xhzy"};
			sql = "(select  rownum r,id,xm,xb,nl,xy,bj,lxfs,dzyx,xhzy,zc from zjlg_xlxhhy_view a "+query.toString()+") ";
		}else if ("zjlg_xlwy".equals(tableName)) {
			colList = new String[]{"id","xh","xm","xb","xy","bj","lxfs","qs","cslb","pxcj","pxjl"};
			sql = "(select * from zjlg_xlwy_view a "+query.toString()+") ";
		}else if ("zjlg_xlcb".equals(tableName)) {
			colList = new String[]{"id","xh","xm","xymc","bjmc","nj","xb","jg","nl","mz"};
			sql = "(select * from zjlg_xlcb_view a "+query.toString()+") ";
		}else if ("zjlg_gzlxb".equals(tableName)) {
			colList = new String[]{"id","xlwyxm","xymc","bjmc","lxfs","jlsd","sffk"};
			sql = "(select * from zjlg_gzlxb_view a "+query.toString()+") ";
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
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws SecurityException the security exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 */
	public static ArrayList<String[]> commonQuery(String innerSql,String query,String [] inPutList,
			String [] colList,String sql, Object model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
		int size = colList.length-1;
		Class myClass = model.getClass();
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
	/**
	 * Gets the toptr title.
	 * 
	 * @param tableName the table name
	 * 
	 * @return the toptr title
	 */
	public List<HashMap<String, String>> getToptrTitle(String tableName) {
			DAO dao = DAO.getInstance();
			String[] colList = null;
			if ("zjlg_xlzxs".equals(tableName)) {
				colList = new String[] {"id","xm","xb","nl","dw","bgdh","sjhm","dzyx","zyzz","zxgznx","zxzbsj"};
			}else if ("zjlg_xljkzdls".equals(tableName)) {
				colList = new String[]{"id","xm","xb","nl","xy","bgdh","sjhm","dzyx","xl","byxy","byzy","zxzbsj"};
			}else if ("zjlg_xlzxxszl".equals(tableName)) {
				colList = new String[]{"id","xm","xb","nl","xy","bjdm","lxfs","dzyx","gwlx","zbsj"};
			}else if ("zjlg_xlxhhy".equals(tableName)) {
				colList = new String[]{"id","xm","xb","nl","xy","bj","lxfs","dzyx","xhzy"};
			}else if ("zjlg_xlwy".equals(tableName)) {
				colList = new String[]{"id","xh","xm","xb","xy","bj","lxfs","qs","cslb","pxcj","pxjl"};
			}else if ("zjlg_xlcb".equals(tableName)) {
				colList = new String[]{"id","xh","xm","xydm","bjdm","nj","xb","jg","nl","mz"};
			}else if ("zjlg_gzlxb".equals(tableName)) {
				colList = new String[]{"id","xlwyxm","xydm","bjdm","lxfs","jlsd","sffk"};
			}
			String[] colListCN = dao.getColumnNameCN(colList, tableName);
			List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);
			return topTr;
	}
	//心里咨询师查询
	/**
	 * Dao_idfor query.
	 * 
	 * @param pk the pk
	 * @param tableName the table name
	 * 
	 * @return the hash map< string, string>
	 */
	public HashMap<String, String> dao_idforQuery(String pk,String tableName){
		DAO dao = DAO.getInstance();
		String[] outputValue = null;
		if ("zjlg_xlzxs".equals(tableName)) {
			outputValue = new String[]{"id","xm","xb","nl","dw","bgdh","sjhm","dzyx","zyzz","zxgznx","scfx","zxzbsj","xgpx","kqtj","bz"};
		}else if ("zjlg_xljkzdls".equals(tableName)) {
			outputValue = new String[]{"id","xm","xb","nl","xy","bgdh","sjhm","dzyx","xl","byxy","byzy","sc","zxzbsj","xgpx","kqtj","bz"};
		}else if ("zjlg_xlzxxszl".equals(tableName)) {
			outputValue = new String[]{"id","xh","xm","xb","nl","xymc","bjmc","lxfs","dzyx","gwlx","zbsj","zc","xgpx","bz"};
		}else if ("zjlg_xlxhhy".equals(tableName)) {
			outputValue = new String[]{"id","xh","xm","xb","nl","xy","bj","xymc","bjmc","lxfs","dzyx","xhzy","zc","bz"};
		}else if ("zjlg_xlwy".equals(tableName)) {
			outputValue = new String[]{"id","xy","bj","xm","xb","lxfs","qs","cslb","pxcj","pxjl","gzkh","xlwy","xlwypxqk","xh","xymc","bjmc"};
		}else if ("zjlg_xlcb".equals(tableName)) {
			outputValue = new String[]{"id","xh","xm","xydm","xymc","bjdm","bjmc","nj","xb","jg","nl","mz","sfdszn","csny","xlwtlx","qthbzf","jlbzf","yybzf","zbbzf","sjtsbzf","xxlzabzf","pzbzf","qpbzf","ynbzf","cdbzf","jsbqxbzf","wtgs","zxjy","zzjl","xlcslb","sfkns","sfdq"};
		}else if ("zjlg_gzlxb".equals(tableName)) {
			outputValue = new String[]{"id","xlwyxm","xydm","xymc","bjdm","bjmc","lxfs","jlsd","txdt","txqk","xycljg","sffk","xyxljkzdls","sj"};
		}
		String[] inputValue = {pk};
		String sql = "select * from "+tableName+" where id = ?";
		if ("zjlg_xlwy".equals(tableName)) {
			sql = "select * from zjlg_xlwy_view a where id = ?";
		}else if("zjlg_xlcb".equals(tableName)) {
			sql = "select * from zjlg_xlcb_view a where id = ?";
		}else if("zjlg_gzlxb".equals(tableName)) {
			sql = "select * from zjlg_gzlxb_view a where id = ?";
		}else if("zjlg_xlxhhy".equals(tableName)) {
			sql = "select a.*,xy xymc,bj bjmc from zjlg_xlxhhy_view a where id = ?";
		}else if("zjlg_xlzxxszl".equals(tableName)) {
			sql = "select * from zjlg_xlzxxszl_view a where id = ?";
		}else {
			sql = "select * from "+tableName+" where id = ?";
		}
		HashMap<String, String> map = dao.getMap(sql, inputValue, outputValue);
		return map;	
	}
	
	/**
	 * 批量删除.
	 * 
	 * @param pks the pks
	 * @param tableName the table name
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
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
	 * 批量删除.
	 * 
	 * @param tabName the tab name
	 * @param response the response
	 * @param model the model
	 * 
	 * @throws Exception the exception
	 */
	public void dao_expData(String tabName,HttpServletResponse response,XljkZjlgmodel model) throws Exception {
		DAO dao = DAO.getInstance();
		String xm = model.getXm();
		String xb = model.getXb();
		String nl = model.getNl();
		String dw = model.getDw();
		String xy = model.getXy();
		String bjdm = model.getBjdm();
		String bj = model.getBj();
		String byxx = model.getByxx();
		String byzy = model.getByzy();
		String zxzbsj = model.getZxzbsj();
		String zydm = model.getZydm();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm.trim()+ "'");
		query.append(Base.isNull(xb) ? " and 1=1" : " and xb='"+xb.trim()+ "'");
		query.append(Base.isNull(nl) ? " and 1=1" : " and nl='"+nl.trim()+ "'");	
		query.append(Base.isNull(dw) ? " and 1=1" : " and dw like '%"+dw.trim()+ "%'");
		if ("zjlg_gzlxb".equals(tabName)) {
			query.append(Base.isNull(xy) ? " and 1=1" : " and xydm='"+xy.trim()+ "'");
			query.append(Base.isNull(xm) ? " and 1=1" : " and xlwyxm like '%"+xm.trim()+ "%'");
//			if (StringUtils.isNotNull(model.getUserlx())) {
//			query.append(Base.isNull(model.getUserlx()) ? " and 1=1" : " and sffk like '%是%'");
//			}
			query.append(" and sffk like '%是%'");
		} else if ("zjlg_xlcb".equals(tabName)) {
			String xh = model.getXh();
			query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");
			query.append(Base.isNull(xy) ? " and 1=1" : " and xydm='"+xy.trim()+ "'");
			query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		} else if("zjlg_xljkzdls".equals(tabName)){
			query.append(Base.isNull(xy) ? " and 1=1" : " and xy='"+xy.trim()+ "'");
			query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		} else {
			query.append(Base.isNull(xy) ? " and 1=1" : " and xydm='"+xy.trim()+ "'");
			query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		}
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm.trim()+ "'");
		query.append(Base.isNull(bj) ? " and 1=1" : " and bjdm='"+bj.trim()+ "'");
		query.append(Base.isNull(byxx) ? " and 1=1" : " and byxx like '%"+byxx.trim()+ "%'");
		query.append(Base.isNull(byzy) ? " and 1=1" : " and byzy like '%"+byzy.trim()+ "%'");
		query.append(Base.isNull(zxzbsj) ? " and 1=1" : " and zxzbsj='"+zxzbsj.trim()+ "'");
		Vector<Object> vec = new Vector<Object>();
		String sql = "";
//		if ("zjlg_xlcb".equals(tabName)) {
//			sql = "select xh,xm,nj,xb,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xydm,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjdm,jg,nl,mz,sfdszn,csny,xlwtlx,qthbzf,jlbzf,yybzf,zbbzf,sjtsbzf,xxlzabzf,pzbzf,qpbzf,ynbzf,cdbzf,jsbqxbzf,wtgs,zxjy,zzjl from zjlg_xlcb a "+query.toString();
//		}else {
//			sql = "select * from "+tabName+query.toString();
//		}
		String[] outputValue = null;
		if ("zjlg_xlzxs".equals(tabName)) {
			outputValue = new String[]{"xm","xb","nl","dw","bgdh","sjhm","dzyx","zyzz","zxgznx","scfx","zxzbsj","xgpx","kqtj","bz"};
		}else if ("zjlg_xljkzdls".equals(tabName)) {
			outputValue = new String[]{"xm","xb","nl","xy","bgdh","sjhm","dzyx","xl","byxy","byzy","sc","zxzbsj","xgpx","kqtj","bz"};
		}else if ("zjlg_xlzxxszl".equals(tabName)) {
			outputValue = new String[]{"xh","xm","xb","nl","xy","bjdm","lxfs","dzyx","gwlx","zbsj","zc","xgpx","bz"};
		}else if ("zjlg_xlxhhy".equals(tabName)) {
			outputValue = new String[]{"xh","xm","xb","nl","xy","bj","lxfs","dzyx","xhzy","zc","bz"};
		}else if ("zjlg_xlwy".equals(tabName)) {
			outputValue = new String[]{"xh","xy","bj","xm","xb","lxfs","qs","cslb","pxcj","pxjl","gzkh","xlwypxqk"};
		}else if ("zjlg_xlcb".equals(tabName)) {
			outputValue = new String[]{"xh","xm","xydm","bjdm","nj","xb","jg","nl","mz","sfdszn","csny","xlwtlx","qthbzf","jlbzf","yybzf","zbbzf","sjtsbzf","xxlzabzf","pzbzf","qpbzf","ynbzf","cdbzf","jsbqxbzf","wtgs","zxjy","zzjl"};
		}else if ("zjlg_gzlxb".equals(tabName)) {
			outputValue = new String[]{"xlwyxm","xydm","bjdm","lxfs","jlsd","txdt","txqk","xycljg","sffk"};
		}
		sql = "select * from "+tabName+" ";
		if ("zjlg_xlwy".equals(tabName)) {
			sql = "select * from zjlg_xlwy_view a "+query;
		}else if("zjlg_xlcb".equals(tabName)) {
			sql = "select * from zjlg_xlcb_view a "+query;
		}else if("zjlg_gzlxb".equals(tabName)) {
			sql = "select * from zjlg_gzlxb_view a "+query;
		}else if("zjlg_xlxhhy".equals(tabName)) {
			sql = "select a.*,xy xymc,bj bjmc from zjlg_xlxhhy_view a "+query;
		}else if("zjlg_xlzxxszl".equals(tabName)) {
			sql = "select * from zjlg_xlzxxszl_view a "+query;
		}else if("zjlg_xljkzdls".equals(tabName)) {
			sql = "select xm,xb,nl,(select distinct xymc from view_njxyzybj b where a.xy=b.xydm) xy,bgdh,sjhm,dzyx,xl,byxy,byzy,sc,zxzbsj,xgpx,kqtj,bz from zjlg_xljkzdls a "+query;
		}else {
			sql = "select * from "+tabName+" "+query;
		}
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
//			String ColumnName[] = dao.getColumnName(sql);
//			String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, tabName.toUpperCase());
			String ColumnNameCN[] = dao.getColumnNameCN(outputValue, tabName.toUpperCase());
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			if ("zjlg_xlcb".equals(tabName)) {
				outputValue = new String[]{"xh","xm","xymc","bjmc","nj","xb","jg","nl","mz","sfdszn","csny","xlwtlx","qthbzf","jlbzf","yybzf","zbbzf","sjtsbzf","xxlzabzf","pzbzf","qpbzf","ynbzf","cdbzf","jsbqxbzf","wtgs","zxjy","zzjl"};
			}
			if ("zjlg_xlzxxszl".equals(tabName)) {
				outputValue = new String[]{"xh","xm","xb","nl","xymc","bjmc","lxfs","dzyx","gwlx","zbsj","zc","xgpx","bz"};
			}
			if ("zjlg_gzlxb".equals(tabName)) {
				outputValue = new String[]{"xlwyxm","xymc","bjmc","lxfs","jlsd","txdt","txqk","xycljg","sffk"};
			}
//			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			vec.addAll(dao.rsToVator(sql, new String[] {}, outputValue));
//			int k = ColumnName.length;
			int k = outputValue.length;
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
	//心里咨询师增加
	/**
	 * Dao_xlpc add.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dao_xlpcAdd(XljkZjlgmodel model,String tableName,HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		String sql = "";
		String pk = model.getId();
		String[] insertList = null;
		if(StringUtils.isNotNull(pk)){
			FormModleCommon.formToGBK(model);
			insertList = new String[] {"xh","xm","xydm","bjdm","nj","xb","jg","nl","mz","sfdszn","csny","xlwtlx","qthbzf","jlbzf","yybzf","zbbzf","sjtsbzf","xxlzabzf","pzbzf","qpbzf","ynbzf","cdbzf","jsbqxbzf","wtgs","zxjy","zzjl","xlcslb","sfkns","sfdq"};
			sql = XljkUtil.updateSqlForModel(insertList, model, tableName, "id", pk);
			result = StandardOperation.update(tableName, sql, request);
		}else {
			insertList = new String[]{"xh","xm","xydm","bjdm","nj","xb","jg","nl","mz","sfdszn","csny","xlwtlx","qthbzf","jlbzf","yybzf","zbbzf","sjtsbzf","xxlzabzf","pzbzf","qpbzf","ynbzf","cdbzf","jsbqxbzf","wtgs","zxjy","zzjl","xlcslb","sfkns","sfdq"};
			sql = "insert into "+tableName+"(xh,xm,xydm,bjdm,nj,xb,jg,nl,mz,sfdszn,csny,xlwtlx,qthbzf,jlbzf,yybzf,zbbzf,sjtsbzf,xxlzabzf,pzbzf,qpbzf,ynbzf,cdbzf,jsbqxbzf,wtgs,zxjy,zzjl,xlcslb,sfkns,sfdq) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String[] inputList = FormModleCommon.modelToStrings(insertList, model);
			result = dao.insert(sql, inputList);
		}
		return result;
	}
	//
	/**
	 * Dao_getxs info.
	 * 
	 * @param xh the xh
	 * 
	 * @return the hash map< string, string>
	 * 
	 * @throws Exception the exception
	 */
	public HashMap<String, String> dao_getxsInfo(String xh) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotNull(xh)) {
			String sql = "select xh,xm,xb,xydm,xymc,bjdm,bjmc,mzmc,jg,nj from view_xsxxb where xh=?";
			String[] outputValue = new String[]{"xh","xm","xb","xydm","xymc","bjdm","bjmc","mzmc","jg","nj"};
			map = dao.getMap(sql, new String[]{xh}, outputValue);
		}
		return map;
	}
	
	/**
	 * Dao_isexistsxlwy.
	 * 
	 * @param xh the xh
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dao_isexistsxlwy(String xh) throws Exception {
		DAO dao = DAO.getInstance();
		boolean isexists = false;
		HashMap<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotNull(xh)) {
			String sql = "select * from zjlg_xlwy where xh=?";
			map = dao.getMap(sql, new String[]{xh}, new String[]{"xm"});
		}
		if(map != null && map.size()>0){
			isexists = true;
		}
		return isexists;
	}
	
	/**
	 * Dao_get xljkzdls.
	 * 
	 * @return the array list< hash map< string, string>>
	 * 
	 * @throws Exception the exception
	 */
	public ArrayList<HashMap<String, String>> dao_getXljkzdls() throws Exception {
		DAO dao = DAO.getInstance();
//		HashMap<String, String> map = new HashMap<String, String>();
	    String sql = "select xm from zjlg_xljkzdls a";
		String[] outputValue = new String[]{"xm"};
//		map = dao.getMap(sql, new String[]{}, outputValue);
		ArrayList<HashMap<String, String>> map = dao.getArrayList(sql, new String[]{}, outputValue);
		return map;
	}
	
	/**
	 * Dao_getxllbxlwtlx.
	 * 
	 * @param pk the pk
	 * @param tableName the table name
	 * 
	 * @return the hash map< string, hash map< string, string>心理测试类别--xlcslb、心理问题类型 --xlwtlx
	 */
	public HashMap<String, ArrayList<HashMap<String, String>>> dao_getxllbxlwtlx(){
		DAO dao = DAO.getInstance();
		String[] outputValue = null;
		HashMap<String, ArrayList<HashMap<String, String>>> map = new HashMap<String, ArrayList<HashMap<String, String>>>();
		String sql = "select * from xlcslbdmb";
		outputValue = new String[]{"xlcslbdm","xlcslbmc"};
		map.put("xlcslb", dao.getArrayList(sql, new String[]{}, outputValue));
		sql = "select * from xlwtlxdmb";
		outputValue = new String[]{"xlwtlxdm","xlwtlxmc"};
		map.put("xlwtlx", dao.getArrayList(sql, new String[]{}, outputValue));
		return map;	
	}
}
