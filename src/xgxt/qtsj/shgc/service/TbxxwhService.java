package xgxt.qtsj.shgc.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.qtsj.shgc.dao.TbxxDAO;
import xgxt.qtsj.shgc.form.ShgcTbxxForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 上海工程保险管理模块Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-17</p>
 */
public class TbxxwhService {
	TbxxDAO tbDao = new TbxxDAO();
	/**
	 * 组合查询班级的参数值
	 * @param model
	 * @return String
	 * */
	public String getBjKey(ShgcTbxxForm model){
		String bjKey = "";
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String nj = model.getNj();
		
		xydm = xydm==null ||"".equalsIgnoreCase(xydm) ? "" : xydm;
		zydm = zydm==null ||"".equalsIgnoreCase(zydm) ? "" : zydm;
		nj = nj==null ||"".equalsIgnoreCase(nj) ? "" : nj;
		bjKey = xydm+ "!!" + zydm + "!!" + nj;
		
		return bjKey;
	}
	
	/**
	 * 组合多条件查询条件
	 * @param model
	 * @return String 
	 * */
	public String getQueryCondition(ShgcTbxxForm model){
		String sql = "";
		
		String xh = model.getXh();
		String xm = model.getXm();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String nd = model.getNd();
		String yesNo = model.getYesNo();
		String userType = model.getUserType();		
		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		sb.append(xh==null || "".equalsIgnoreCase(xh) ? "" : " and xh='" + xh + "'");
		sb.append(xm==null || "".equalsIgnoreCase(xm) ? "" : " and xm like '%" + xm + "%'");
		sb.append(xydm==null || "".equalsIgnoreCase(xydm) ? "" : " and xydm='" + xydm + "'");
		sb.append(zydm==null || "".equalsIgnoreCase(zydm) ? "" : " and zydm='" + zydm + "'");
		sb.append(bjdm==null || "".equalsIgnoreCase(bjdm) ? "" : " and bjdm='" + bjdm + "'");
		sb.append(nj==null || "".equalsIgnoreCase(nj) ? "" : " and nj='" + nj + "'");
		sb.append(nd==null || "".equalsIgnoreCase(nd) ? "" : " and nd='" + nd + "'");		
		if(userType != null && userType.equalsIgnoreCase("xy")){
			sb.append(yesNo==null || "".equalsIgnoreCase(yesNo) ? "" : " and xysh='" + yesNo + "'");
		}else{
			sb.append(yesNo==null || "".equalsIgnoreCase(yesNo) ? "" : " and xxsh='" + yesNo + "'");
		}
		
		sql = sb.toString();		
		return sql;		
	}
	
	/**
	 * 获取保险险种代码列表
	 * @return List
	 * */
	public List getBxxzList(){
		//保险险种列表
		List bxxzList = null;
		
		bxxzList = tbDao.getBxxzList();
		return bxxzList;
	}
	
	/**
	 * 获取保险公司代码列表
	 * @return List
	 * */
	public List getBxgsList(){		
		//保险公司列表
		List bxgsList = null;
		
		bxgsList = tbDao.getBxgsList();
		return bxgsList;
	}
	
	/**
	 * 模糊查询学生投保信息 
	 * @param model
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public ShgcTbxxForm queryTbxx(ShgcTbxxForm model){	
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh, xm, nd, bxgsmc, tbsj, bxnx,jfbj ";
		DAO dao = DAO.getInstance();
		List bxxzList = null;
		List rs = null;
		String sCondition = "";
		String[] colList = {"xh","xm","nd","bxgsmc","tbsj","bxnx","jfbj"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_xsbxxx");
		
		List topTr = dao.arrayToList(colList, colListCN);
		
		sCondition = this.getQueryCondition(model);
		bxxzList = this.getBxxzList();
		String[] output = new String[7+bxxzList.size()];
		for(int i=0; i<colList.length; i++){
			output[i] = colList[i];
		}
		
		//循环组合投保险种
		for(int i=0; i<bxxzList.size(); i++){
			map = (HashMap<String, String>)bxxzList.get(i);
			String bxxzmc = map.get("bxxzmc");
			sql += ",max(decode(bxxzmc,'" + bxxzmc +"','"+ bxxzmc +"','')) 投保险种"+ (i+1);
			//表头
			map = new HashMap<String, String>();
			map.put("en", "投保险种" + (i+1));
			map.put("cn", "投保险种" + (i+1));
			topTr.add(map);
			
			//输出字段
			output[7+i]="投保险种" + (i+1);
		}
		
		sql = sql + " from View_Xsbxxx " + sCondition + "  and xxsh='通过' group by xh,xm,nd,bxgsmc,tbsj,bxnx,jfbj";	
		
		rs = dao.rsToVator(sql, new String[]{}, output);
		model.setRs(rs);
		model.setTopTr(topTr);			
		return model;
	}
	
	/**
	 * 投保信息维护数据导出
	 * @param model
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public String expData(ShgcTbxxForm model){	
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh, xm, nd, bxgsmc, tbsj, bxnx,jfbj ";
		List bxxzList = null;
		String sCondition = "";
		
		sCondition = this.getQueryCondition(model);
		bxxzList = this.getBxxzList();
		//循环组合投保险种
		for(int i=0; i<bxxzList.size(); i++){
			map = (HashMap<String, String>)bxxzList.get(i);
			String bxxzmc = map.get("bxxzmc");
			sql += ",max(decode(bxxzmc,'" + bxxzmc +"','"+ bxxzmc +"','')) 投保险种"+ (i+1);//			
		}		
		sql = sql + " from View_Xsbxxx " + sCondition + " and bxpzh is not null group by xh,xm,nd,bxgsmc,tbsj,bxnx,jfbj";
		
		return sql;
	}
	
	/**
	 * 投保信息增加
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean addTbxx(ShgcTbxxForm model,HttpServletRequest request){
		boolean flag = true;
		String xh = model.getXh();
		String nd = model.getNd();
		String tbgsdm = model.getTbgsdm();
		String tbsj = model.getTbsj();
		String jfbj = model.getJfbj();
		String tbbj = model.getTbbj();
		String tbxzdm = model.getTbxzdm();
		String bf = model.getBf();
		String bxpzh = model.getBxpzh();
		String tuibsj = model.getTbsj();
		String bxnx = model.getBxnx();
		String bz = model.getBz();
		String xxsh= model.getXxsh();
		String sqsj = model.getSqsj();
		
		String[] columns = {"xh","tbgsdm","tbxzdm","tbsj","tuibsj","tbbj","bxpzh","bxnx","bf","nd","bz","jfbj","xxsh","sqsj"};
		String[] values = {xh,tbgsdm,tbxzdm,tbsj,tuibsj,tbbj,bxpzh,bxnx,bf,nd,bz,jfbj,xxsh,sqsj};
		
		if(tbxzdm!=null && !"".equalsIgnoreCase(tbxzdm)){		
			flag = StandardOperation.insert("xsbxb", columns, values, request);
		}
		return flag ;
	}
	
	/**
	 *  根据主键查询投保信息
	 *  @param model
	 *  @return HashMap
	 * */
	public HashMap<String, String> getTbxxByPk(ShgcTbxxForm model){
		DAO dao = DAO.getInstance();	
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = model.getXh();
		String nd = model.getNd();
		
		String sql = "select distinct xh,xm,xb,nj,xymc,zymc,bjmc,nd,tbgsdm,tbbj,tbsj,tuibsj,jfbj ,bxnx,sqsj from view_xsbxxx where xh=? and nd=?";
		map = dao.getMap(sql, new String[]{xh,nd}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","nd","tbgsdm","tbbj","tbsj","tuibsj","jfbj","bxnx","sqsj"});
		model.setSqsj(map.get("sqsj"));
		return map;
	}
	
	/**
	 * 删除投保信息
	 * @param model
	 * @param request
	 * @return boolean 
	 * */	
	public boolean delTbxx(ShgcTbxxForm model,HttpServletRequest request){
		boolean flag = false;
		String xh = model.getXh();
		String nd = model.getNd();		
		try {
			flag = StandardOperation.delete("xsbxb", "xh||nd", xh+nd, request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * 获取投保险种
	 * @param model
	 * @return List
	 * */
	public List getTbxzList(ShgcTbxxForm model){
		List tbxzList = null;
		tbxzList = tbDao.getTbxzList(model);
		return tbxzList;
	}
	
	/**
	 * 缴费标记批量确认
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String jfbjAll(String[] keys, String jfbj) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "update xsbxb set jfbj = '" + jfbj + "' where xh = '"
					+ pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条更新失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据审核失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
}
