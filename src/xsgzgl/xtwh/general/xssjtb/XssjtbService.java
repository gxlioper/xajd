package xsgzgl.xtwh.general.xssjtb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_学生数据检测
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class XssjtbService  extends CommService{
	
	protected static HashMap<String, List<HashMap<String, String>>> zyMap = new HashMap<String, List<HashMap<String,String>>>();
	
	public static HashMap<String, List<HashMap<String, String>>> bjMap = new HashMap<String, List<HashMap<String,String>>>();
	
	XssjtbDao dao= new  XssjtbDao();
	
	/**
	 * 数据同步_日志数据
	 */
	public List<String[]> getSjjcRzList(XssjtbForm myForm)throws Exception{
		return dao.getSjjcRzList(myForm);
	}
	
	/**
	 * 数据同步_异常数据
	 */
	public List<String[]> getYcsjList(XssjtbForm myForm)throws Exception{
		return dao.getYcsjList(myForm);
	}
	
	public List<HashMap<String,String>> getTopTr(XssjtbForm myForm){
		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;
		colListCN=new String[]{"同步时间","日志信息"};
		colListEN= new String[]{"jcsj", "jcnr"};
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * 异常数据详情列表表头
	 */
	public List<HashMap<String,String>> getCkTopTr(XssjtbForm myForm){

		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;
		
		if("xg_xtwh_bmdmb".equals(myForm.getJcb())){
			colListCN=new String[]{"部门代码","部门名称","异常原因"};
			colListEN= new String[]{"zj","bmmc","ycyy"};
		}if("xg_xtwh_zydmb".equals(myForm.getJcb())){
			colListCN=new String[]{"专业代码","专业名称","部门代码","部门名称","异常原因"};
			colListEN= new String[]{"zj","zymc","bmdm","bmmc","ycyy"};
		}if("xg_xtwh_bjdmb".equals(myForm.getJcb())){
			colListCN=new String[]{"班级代码","班级名称","专业代码","专业名称","年级","异常原因"};
			colListEN= new String[]{"zj","bjdm","bjmc","zydm","zymc","nj","ycyy"};
		}if("xg_xtwh_xsxxb".equals(myForm.getJcb())){
			colListCN=new String[]{"学号","姓名","性别","异常原因"};
			colListEN= new String[]{"zj","xm","xb","ycyy"};
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * 执行数据同步
	 */
	public boolean cshsjTb()throws Exception{
		boolean flag = false;
		flag = dao.cshsjTb();
		return flag;
	}
	
	/**
	 * 执行数据同步
	 */
	public boolean xssjTb()throws Exception{
		boolean flag = false;
		flag = dao.xysjTb();
		if(flag){
			flag = dao.zysjTb();
		}
		if(flag){
			flag = dao.bjsjTb();	
		}
		if(flag){
			flag = dao.xssjTb();
		}
		//加载初始化数据
		if(flag){
			initialNj();
			initialXy();
			initialZy();
			initialBj();
			initalSearch();
		}
		return flag;
	}
	
	//年级列表
	public void initialNj(){
		Base.setNjallList(dao.getNjallList());
	}
	
	//学院列表
	public void initialXy(){
		Base.setXyallList(dao.getXyallList());
	}
	
	//专业列表
	public void initialZy(){
		String xydm;
		List<HashMap<String, String>> nullZyList = dao.getZyallList("");
		zyMap.put("", nullZyList);
		for(HashMap<String, String> xy : dao.getXyallList()){
			xydm = xy.get("xydm");
			List<HashMap<String, String>> subZyList = dao.getZyallList(xydm);
			zyMap.put(xydm, subZyList);
		}
		Base.setZyallMap(zyMap);
	}
	
	//班级列表
	public void initialBj(){
		String xydm;
		String zydm;
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> allBjList = dao.getBjAllList();
		bjMap.put("!!!!", allBjList);
		for (HashMap<String,String> xy : dao.getXyallList()){
			//xydm!!!! 对应的班级列表
			xydm = xy.get("xydm");
			List<HashMap<String,String>> bjList = new ArrayList<HashMap<String,String>>(); 
			for (HashMap<String,String> map : allBjList){
				if (xydm.equalsIgnoreCase(map.get("xydm"))){
					bjList.add(map);
				}
			}
			bjMap.put(xydm+"!!!!", bjList);
			for(HashMap<String, String> zyMaps : zyMap.get(xydm)){
				//xydm!!zydm!! 对应的班级列表
				zydm = zyMaps.get("zydm");
				List<HashMap<String, String>> subBjList = new ArrayList<HashMap<String,String>>(); 
				for (HashMap<String,String> map : allBjList){
					if (xydm.equalsIgnoreCase(map.get("xydm")) && zydm.equalsIgnoreCase(map.get("zydm"))){
						subBjList.add(map);
					}
				}
				bjMap.put(xydm+"!!"+zydm+"!!", subBjList);
				bjMap.put("!!"+zydm+"!!", subBjList);
				
				for(HashMap<String, String> njMap : dao.getNjallList()){
					
					String nj = njMap.get("nj");
					 List<HashMap<String, String>> bjListForNj = new ArrayList<HashMap<String,String>>();                   
					 List<HashMap<String, String>> bjListForNjXy = new ArrayList<HashMap<String,String>>();                   
					 List<HashMap<String, String>> bjListForNjXyZy = new ArrayList<HashMap<String,String>>(); 
					 for (HashMap<String, String> map : allBjList) {
						
						if (nj.equalsIgnoreCase(map.get("nj"))){
							bjListForNj.add(map);
						} 
						
						if (nj.equalsIgnoreCase(map.get("nj"))
								&& xydm.equalsIgnoreCase(map.get("xydm"))){
							bjListForNjXy.add(map);
						}
						
						if (xydm.equalsIgnoreCase(map.get("xydm"))
								&& zydm.equalsIgnoreCase(map.get("zydm"))
								&& nj.equalsIgnoreCase(map.get("nj"))) {
							bjListForNjXyZy.add(map);
						}
					}
					 //xydm!!zydm!!nj 对应的班级列表
					bjMap.put(xydm+"!!"+zydm+"!!"+nj, bjListForNjXyZy);
					//!!zydm!!nj 对应的班级列表
					bjMap.put("!!"+zydm+"!!"+nj, bjListForNjXyZy);
					//!!!!nj 对应的班级列表
					bjMap.put("!!!!"+nj, bjListForNj);
					//xydm!!!!nj  对应的班级列表
					bjMap.put(xydm+"!!!!"+nj, bjListForNjXy);
				}
			}
		}
		Base.setBjallMap(bjMap);
	}
	
	//高级查询条件
	public void initalSearch(){
		SearchService search = new SearchService();
		// 学院
		List<HashMap<String, Object>> xyTjList = search.getNewXyPxList("","","","");
		// 专业
		List<HashMap<String, Object>> zyTjList = search.getNewZyPxList(null,"","","","");
		// 班级
		List<HashMap<String, Object>> bjTjList = search.getNewBjPxList(null,"","","","");
		
		SearchService.setXyTjList(xyTjList);
		SearchService.setZyTjList(zyTjList);
		SearchService.setBjTjList(bjTjList);
	}
	
	/**
	 * 获取导出表头
	 */
	public String[] getExportTop(XssjtbForm myForm){
		String[] title = null;
		if("xg_xtwh_bmdmb".equals(myForm.getJcb())){
			title = new String[]{"异常表","部门代码","部门名称","异常原因"};
		}
		if("xg_xtwh_zydmb".equals(myForm.getJcb())){
			title = new String[]{"异常表","专业代码","专业名称","部门代码","部门名称","异常原因"};
		}
		if("xg_xtwh_bjdmb".equals(myForm.getJcb())){
			title = new String[]{"异常表","班级代码","班级名称","专业代码","专业名称","年级","异常原因"};
		}if("xg_xtwh_xsxxb".equals(myForm.getJcb())){
			title = new String[]{"异常表","学号","姓名","性别","异常原因"};
		}
		return title;
	}

	public static HashMap<String, List<HashMap<String, String>>> getZyMap() {
		return zyMap;
	}

	public static void setZyMap(HashMap<String, List<HashMap<String, String>>> zyMap) {
		XssjtbService.zyMap = zyMap;
	}

	public static HashMap<String, List<HashMap<String, String>>> getBjMap() {
		return bjMap;
	}

	public static void setBjMap(HashMap<String, List<HashMap<String, String>>> bjMap) {
		XssjtbService.bjMap = bjMap;
	}
	
}
