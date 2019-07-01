package xgxt.qtsj.shgc.service;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.qtsj.shgc.form.ShgcTbxxForm;
import xgxt.studentInfo.dao.StuInfoDAO;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 上海工程保险管理模块投保申请查询Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-17</p>
 */
public class TbsqcxService {
	
	/**
	 * 模糊查询学生户口状态信息 
	 * @param model
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public ShgcTbxxForm queryTbxx(ShgcTbxxForm model){	
		TbxxwhService tbwhService = new TbxxwhService();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh,max(xysh)xysh,max(xxyj)xxyj,max(xyyj)xyyj";
		DAO dao = DAO.getInstance();
		List bxxzList = null;
		List rs = null;
		String sCondition = "";
		String[] colList = {"xh","xm","nd","bxgsmc","sqsj"};
		String[] colListCN = null;
		
		sCondition = tbwhService.getQueryCondition(model);
		bxxzList = tbwhService.getBxxzList();
		String[] output = new String[9+bxxzList.size()];
		for(int i=0; i<colList.length; i++){
			output[i] = colList[i];
		}
		//循环组合投保险种
		for(int i=0; i<bxxzList.size(); i++){
			map = (HashMap<String, String>)bxxzList.get(i);
			String bxxzmc = map.get("bxxzmc");
			sql += ",max(decode(bxxzmc,'" + bxxzmc +"','"+ bxxzmc +"','')) 投保险种"+ (i+1);
						
			//输出字段
			output[5+i]="投保险种" + (i+1);
		}
				
		sql = sql + " from View_Xsbxxx " + sCondition + " and sqsj is not null group by xh,xm,nd,bxgsmc";	
		
		output[5+bxxzList.size()] = "xysh";
		output[6+bxxzList.size()] = "xxsh";
		output[7+bxxzList.size()] = "xyyj";
		output[8+bxxzList.size()] = "xxyj";
		
		rs = dao.rsToVator(sql, new String[]{}, output);
		model.setRs(rs);
		
		//获取表头
		colListCN = dao.getColumnNameCN(output, "view_xsbxxx");
		List topTr = dao.arrayToList(output, colListCN);		
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
		TbxxwhService tbService = new TbxxwhService();
		String sql = "select xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh";
		List bxxzList = null;
		String sCondition = "";
		
		sCondition = tbService.getQueryCondition(model);
		bxxzList = tbService.getBxxzList();	
		
		//循环组合投保险种
		for(int i=0; i<bxxzList.size(); i++){
			map = (HashMap<String, String>)bxxzList.get(i);
			String bxxzmc = map.get("bxxzmc");
			sql += ",max(decode(bxxzmc,'" + bxxzmc +"','"+ bxxzmc +"','')) 投保险种"+ (i+1);		
			
		}		
		sql = sql + " from View_Xsbxxx " + sCondition + " and sqsj is not null group by xh,xm,nd,bxgsmc";		
		return sql;
	}
	
	/**
	 * 投保信息审核查询
	 * @param model
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public ShgcTbxxForm audtingQue(ShgcTbxxForm model){	
		HashMap<String, String> map = new HashMap<String, String>();
		TbxxwhService tbService = new TbxxwhService();
		DAO dao = DAO.getInstance();
		String sCondition = tbService.getQueryCondition(model);
		String userType = model.getUserType();
		List rs = null;
		String[] colList = {"bgcolor", "主键", "行号","xh","xm","nd","bxgsmc","sqsj"};
		String[] colListCN = null;
		
		String sql = "select xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh, max(xysh) xysh,max(xyyj)xyyj, max(xxyj) xxyj";
		sql = "select rownum 行号,(case nvl(a.xxsh,'未审核') when '通过' then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
			+ " a.* from(select xh||nd 主键, xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh,max(xysh) xysh,max(xxyj)xxyj,max(xyyj)xyyj" ;
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			sql = "select rownum 行号,(case nvl(a.xysh,'未审核') when '通过' then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ " a.* from(select xh||nd 主键, xh, xm, nd, bxgsmc, max(sqsj)sqsj, max(xxsh) xxsh,max(xysh) xysh,max(xxyj)xxyj,max(xyyj)xyyj" ; 
		}
		
		List bxxzList = null;
		bxxzList = tbService.getBxxzList();
		String[] output = new String[12+bxxzList.size()];
		for(int i=0; i<colList.length; i++){
			output[i] = colList[i];
		}
		//循环组合投保险种
		for(int i=0; i<bxxzList.size(); i++){
			map = (HashMap<String, String>)bxxzList.get(i);
			String bxxzmc = map.get("bxxzmc");
			sql += ",max(decode(bxxzmc,'" + bxxzmc +"','"+ bxxzmc +"','')) 投保险种"+ (i+1);
			
			//输出字段
			output[8+i]="投保险种" + (i+1);
		}	
		
		output[8+bxxzList.size()] = "xysh";
		output[9+bxxzList.size()] = "xxsh";
		output[10+bxxzList.size()] = "xyyj";
		output[11+bxxzList.size()] = "xxyj";
		
		if(userType != null && userType.equalsIgnoreCase("xy")){
			sql = sql + " from View_Xsbxxx a" + sCondition + " and sqsj is not null group by xh,xm,nd,bxgsmc order by xysh desc) a ";
		}else{
			sql = sql + " from View_Xsbxxx a" + sCondition + " and sqsj is not null and xysh='通过' group by xh,xm,nd,bxgsmc order by xxsh desc) a ";
		}
		rs = dao.rsToVator(sql, new String[]{}, output);
		
		//获取表头
		colListCN = dao.getColumnNameCN(output, "view_xsbxxx");
		List topTr = dao.arrayToList(output, colListCN);
		
		model.setRs(rs);
		model.setTopTr(topTr);
		return model;
	}
	
	
	/**
	 * 获取当前时间
	 * @return String
	 * */
	public String getCurrDate(){
		String date = "";
		date = StuInfoDAO.getCurrDate();
		return date;
	}
}
