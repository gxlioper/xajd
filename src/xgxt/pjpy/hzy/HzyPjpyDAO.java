
package xgxt.pjpy.hzy;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class HzyPjpyDAO{
	public List<HashMap<String, String>> getCjryList(DAO dao,String xn,String xq,String bmdm,String hdmc){
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String,String>>();
		String sql = "";
		String[] rs = null;
		if(hdmc == null || hdmc.trim().equals("")){
			sql = "select bjlb,cjry from jtxmsqb where rowid = (select max(rowid) from jtxmsqb where xn=? and xq=? and bmdm=?)";
			rs  = dao.getOneRs(sql, new String[]{xn,xq,bmdm}, new String[]{"bjlb","cjry"});
		} else {
			sql = "select bjlb,cjry from jtxmsqb where xn=? and xq=? and bmdm=? and hdmc=?";
			rs  = dao.getOneRs(sql, new String[]{xn,xq,bmdm,hdmc}, new String[]{"bjlb","cjry"});
		}
		if(rs != null){
			String[] bjArr = rs[0].split("!!");
			String[] cjryArr = rs[1].split("!!");
			for(int i=0;i<bjArr.length;i+=2){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bjmc1",bjArr[i]);
				map.put("xm1",cjryArr[i]);
				map.put("bjmc2",bjArr[i+1]);
				map.put("xm2",cjryArr[i+1]);
				rsList.add(map);
			}
		}
		while(rsList==null || rsList.size()<10){//总共要有10行信息
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("bjmc1"," ");
			map.put("xm1"," ");
			map.put("bjmc2"," ");
			map.put("xm2"," ");
			rsList.add(map);
		}
		return rsList;
	}
	
	/**
	 * 获得相应的活动信息
	 * */
	public HashMap<String, String> getHdxx(DAO dao,String xn,String xq,String bmdm,String hdmc){
		HashMap<String, String> rsMap = new HashMap<String,String>();
		String sql = "";
		String[] rs = null;
		String[] cols = {"hdmc","hdsj","hjmc","sqje"};
		if(hdmc == null || hdmc.trim().equals("")){
			sql = "select hdmc,hdsj,hjmc,sqje from jtxmsqb where rowid = (select max(rowid) from jtxmsqb where xn=? and xq=? and bmdm=?)";
			rs  = dao.getOneRs(sql, new String[]{xn,xq,bmdm}, cols);
		} else {
			sql = "select hdmc,hdsj,hjmc,sqje from jtxmsqb where xn=? and xq=? and bmdm=? and hdmc=?";
			rs  = dao.getOneRs(sql, new String[]{xn,xq,bmdm,hdmc}, cols);
		}
		
		if(rs != null){
			for(int i=0 ;i<cols.length;i++){
				rsMap.put(cols[i], rs[i]);
			}
		}
		return rsMap;
	}
	
	public boolean saveJtxmtbjlsqDML(DAO dao,String[] cols,String[] vals,String primarykey,String pkVal,HttpServletRequest request){
		boolean rs = false;
		try{
		if(StandardOperation.delete("jtxmsqb",primarykey,pkVal,request))
			rs = StandardOperation.insert("jtxmsqb", cols, vals, request);
		} catch(Exception e){
			e.printStackTrace();
			rs = false;
		}
		return rs;
	}
	
	public List<HashMap<String, String>> hzyShList(){
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		String[][] shList = {{"先进班级","view_pjpy_xjbjsqb"},{"文明班级","view_pjpy_wmbjsqb"}}; //列表里是要查询的表  //{"集体项目特别奖励","view_pjpy_jtxmsqb"}
//		HashMap<String, String[]> shColumns = new HashMap<String, String[]>();
		for(int i=0;i<shList.length;i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("shxm", shList[i][1]);
			map.put("shxmmc", shList[i][0]);
			rs.add(map);
		}
		return rs;
	}
	
	public HashMap<String, String[]> hzyShENColumns(String userType){
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		//String[][] shList = {{"先进班级","view_pjpy_xjbjsqb"},{"文明班级","view_pjpy_wmbjsqb"}}; //列表里是要查询的表  //{"集体项目特别奖励","view_pjpy_jtxmsqb"}
		if("xy".equalsIgnoreCase(userType)){
			rs.put("view_pjpy_xjbjsqb", new String[]{"bgcolor","key","xn","xq","rychmc","bjmc","xysh"});
			rs.put("view_pjpy_wmbjsqb", new String[]{"bgcolor","key","xn","xq","rychmc","bjmc","xysh"});
			rs.put("view_pjpy_xjbjandwmsq", new String[]{"bgcolor","key","xn","rychmc","nj","bjmc","xysh"});
		} else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			rs.put("view_pjpy_xjbjsqb", new String[]{"bgcolor","key","xn","xq","rychmc","bjmc","xysh","xxsh"});
			rs.put("view_pjpy_wmbjsqb", new String[]{"bgcolor","key","xn","xq","rychmc","bjmc","xysh","xxsh"});
			rs.put("view_pjpy_xjbjandwmsq", new String[]{"bgcolor","key","xn","rychmc","nj","bjmc","xysh","xxsh"});
		}
		
		return rs;
	}
	
	public HashMap<String, String[]> hzyShCNColumns(String userType){
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		String xxdm = Base.xxdm;
		if("xy".equalsIgnoreCase(userType)){
			rs.put("view_pjpy_xjbjsqb", new String[]{"学年","学期","荣誉称号","班级名称","学院审核"});
			rs.put("view_pjpy_wmbjsqb", new String[]{"学年","学期","荣誉称号","班级名称","学院审核"});
			rs.put("view_pjpy_xjbjandwmsq", new String[]{"学年","荣誉称号","年级","班级名称","院团委审核"});
			if (!Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
				rs.put("view_pjpy_xjbjandwmsq", new String[]{"学年","荣誉称号","年级","班级名称","院系审核"});
			}
		} else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			rs.put("view_pjpy_xjbjsqb", new String[]{"学年","学期","荣誉称号","班级名称","学院审核","学校审核"});
			rs.put("view_pjpy_wmbjsqb", new String[]{"学年","学期","荣誉称号","班级名称","学院审核","学校审核"});
			rs.put("view_pjpy_xjbjandwmsq", new String[]{"学年","荣誉称号","年级","班级名称","院团委审核","学生处审核"});
			if (!Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
				rs.put("view_pjpy_xjbjandwmsq", new String[]{"学年","荣誉称号","年级","班级名称","院系审核","学生处审核"});
			}
		}
		
		return rs;
	}
	
	public HashMap<String, String> hzyShSqlColumn(String userType){
		HashMap<String, String> rs = new HashMap<String, String>();
		if("xy".equalsIgnoreCase(userType)){
			rs.put("view_pjpy_xjbjsqb", "select distinct (case xysh when '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||rychdm||bjdm key,xn,xq,rychmc,bjmc,xysh ");
			rs.put("view_pjpy_wmbjsqb", "select distinct (case xysh when '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||rychdm||bjdm key,xn,xq,rychmc,bjmc,xysh ");
			rs.put("view_pjpy_xjbjandwmsq", "select distinct (case xysh when '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||rychdm||bjdm key,xn,xq,rychmc,nj,bjmc,xysh ");
		} else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			rs.put("view_pjpy_xjbjsqb", "select distinct (case xxsh when '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||rychdm||bjdm key,xn,xq,rychmc,bjmc,xysh,xxsh ");
			rs.put("view_pjpy_wmbjsqb", "select distinct (case xxsh when '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||rychdm||bjdm key,xn,xq,rychmc,bjmc,xysh,xxsh ");
			rs.put("view_pjpy_xjbjandwmsq", "select distinct (case xxsh when '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||rychdm||bjdm key,xn,xq,rychmc,nj,bjmc,xysh,xxsh ");
		}
		
		return rs;
	}
	
	public HashMap<String, String> hzyShViewVsTable(){
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("view_pjpy_xjbjsqb", "pjpy_xjbjandwmsqb");
		rs.put("view_pjpy_wmbjsqb", "pjpy_xjbjandwmsqb");
		rs.put("view_pjpy_xjbjandwmsq", "pjpy_xjbjandwmsqb");
		return rs;
	}
	
	public HashMap<String, String> hzyShTablePrimaryKey(){
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("view_pjpy_xjbjsqb", "xn||xq||rychdm||bjdm");
		rs.put("view_pjpy_wmbjsqb", "xn||xq||rychdm||bjdm");
		rs.put("view_pjpy_xjbjandwmsq", "xn||xq||rychdm||bjdm");
		return rs;
	}
	
	public boolean saveXjbjAndWmbjSq(String tableName,String primaryKey,String primaryKeyValue,String[] columns,String[] values,HttpServletRequest request){
		// TODO 用事务来实现两个操作
		boolean result = false;
//		DAO dao = DAO.getInstance();
		try{
			boolean firstStep = StandardOperation.delete(tableName, primaryKey, primaryKeyValue, request);
			boolean secondStep = StandardOperation.insert(tableName, columns, values, request);
			String xxdm = StandardOperation.getXxdm();
			if (secondStep) {
				if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
					if (values != null) {
						updateXjbjbz(values);//修改备注信息
					}
				}
			}
			result = firstStep && secondStep;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result ;
	}
	
	public String getBjmc(String bjdm){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select bjmc from view_njxyzybj where bjdm=? group by bjmc", new String[]{bjdm}, "bjmc");
	}
	
	public String[] getMes(String primaryKey,String primaryKeyValue,String[] columns){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xn,xq,rychdm,bzxm,xsrs,bzr,zysj,bjdm,jtch from pjpy_xjbjandwmsqb where "+primaryKey+"=?", new String[]{ primaryKeyValue }, columns);
	}
	
	public void updateXjbjbz(String[] values) throws Exception {
		DAO dao = DAO.getInstance();
		if (values != null) {
			String xn = Base.getJxjsqxn();
			String bjdm = values[3];
			String bz = "";//中国美术用于记录班级违纪,旷课,体育信息
			HashMap<String, String> tmp = dao
					.getMapNotOut(
							"select (select count(*) wjrs from view_wjcf a where xn=? and bjdm=? and exists (select 1 from view_xsjbxx b where b.bjdm=a.bjdm)) wjrs,(select count(*) kkrs from view_pjpy_xskqb a where xn=? and bjdm=? and exists (select 1 from view_xsjbxx b where b.bjdm=a.bjdm)) kkrs,(select count(*) tyrs from view_pjpy_tydbqkb a where xn=? and bjdm = ? and tydb like '%不及格%' and exists (select 1 from view_xsjbxx b where b.bjdm=a.bjdm)) tyrs from dual",
							new String[] { xn, bjdm, xn, bjdm, xn,
									bjdm }); 
			if (!StringUtils.isNull(tmp.get("wjrs"))
					&& !"0".equalsIgnoreCase(tmp.get("wjrs"))) {//是否有违纪
				bz += xn + "学年班级学生受处分:" + tmp.get("wjrs") + "人.\n" ;		
			} else {
				bz += xn + "学年班级无学生受处分,\n";
			}
			if (!StringUtils.isNull(tmp.get("kkrs"))
					&& !"0".equalsIgnoreCase(tmp.get("kkrs"))) {//是否有旷课
				bz += xn + "学年班级学生旷课:" + tmp.get("kkrs") + "人.\n" ;		
			} else {
				bz += xn + "学年班级无学生旷课,\n";
			}
			if (!StringUtils.isNull(tmp.get("tyrs"))
					&& !"0".equalsIgnoreCase(tmp.get("tyrs"))) {//是否有体育未达标
				bz += xn + "学年班级学生体育未达标:" + tmp.get("tyrs") + "人." ;		
			} else {
				bz += xn + "学年班级学生体育全部达标.";
			}
			dao
					.runUpdate(
							"update pjpy_xjbjandwmsqb set bz = ? where bjdm = ? and xn=? and rychdm=?",
							new String[] { bz, bjdm, xn, values[2] });
		
		}
	}
}
