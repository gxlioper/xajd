/**
 * @部门:学工产品事业部
 * @日期：2017年4月10日 上午9:57:13 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生成绩汇总导出
 * @类功能描述: 学生成绩汇总班级类
 * @作者： xuwen[工号:1426]
 * @时间： 2017年4月10日 上午9:57:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ClassSummary {
	private String xn;	//学年
	
	private String xqmc;	//学期名称
	
	private String xymc;	//学院名称
	
	private String zymc;	//专业名称
	
	private String bjdm;	//班级代码
	
	private String bjmc;	//班级名称

	private Map<String,XskcModel> kcMap;	//课程集合：包含第一学期课程和第二学期课程，key为xq
	
	private Map<String,XscjModel> stuMap;	//学生集合，key为xh
	
	/**
	 * @描述 ：构造函数并初始化课程集合与学生集合
	 */
	public ClassSummary() {
		
		kcMap = new LinkedHashMap<String,XskcModel>();
		stuMap = new LinkedHashMap<String,XscjModel>();
	}
	/**
	 * @param map 
	 * @param id  
	 * @描述:向班级对象中增加一条记录，会向学生列表中增加一条记录，同时可能影响该班级的课程集合
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月10日 上午11:00:15
	 * @修改记录: xuwen-2017年5月17日下午-班级的课程又分为第一学期课程和第二学期课程
	 * void 返回类型 
	 * @throws 
	 */
	public void addRecord(HashMap<String, String> map) {
		
		String xh = map.get("xh");	//学号
		String kcmc = map.get("kcmc");	//课程名称
		String kccj = map.get("cj");	//课程成绩
		String kcjd = map.get("jd");	//课程绩点
		String xq = map.get("xq");	//学期代码
		
		String pjcj = map.get("pjcj");	//平均成绩
		String pjjd = map.get("pjjd");	//平均绩点
		String pjcjpm = map.get("pjcjpm");	//平均成绩排名
		
		XskcModel xskcModel = kcMap.get(xq);
		XscjModel xscjModel = stuMap.get(xh);
		
		if(xskcModel==null){
			
			String xqmc = map.get("xqmc"); //学期名称
			xskcModel = new XskcModel(xqmc);
			kcMap.put(xq, xskcModel);
		}
		if(xscjModel==null){
			
			String xm = map.get("xm");
			xscjModel = new XscjModel(xh, xm, pjcj, pjjd, pjcjpm);
			//这里学号可能重复，因为有一二两个学期的学生记录，不用管直接覆盖即可
			stuMap.put(xh, xscjModel);	
		}
		
		
		
		Map<String,String[]> cj = xscjModel.getCjMap().get(xq);	//获取对应学期的成绩存取map
		Set<String> kc = xskcModel.getKcSet();
		
//		if(cj==null){
//			cj = new LinkedHashMap<String,String>();
//			xscjModel.getCjMap().put(xq, cj);
//			//这里直接写死2个学期了，可以改为从数据库取学期代码来创建
//			xscjModel.getCjMap().put("01", new LinkedHashMap<String,String>());
//			xscjModel.getCjMap().put("02", new LinkedHashMap<String,String>());
//		}
	
		if(StringUtils.isNotNull(kcmc)){
			kc.add(kcmc);	//收集班级课程
			cj.put(kcmc,new String[]{kccj,kcjd});	//收集学生科目成绩和绩点
		}
		
		
	}
	
	/**
	 * @return the kcMap
	 */
	public Map<String, XskcModel> getKcMap() {
		return kcMap;
	}
	/**
	 * @return the stuMap
	 */
	public Map<String, XscjModel> getStuMap() {
		return stuMap;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymc要设置的 zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param xn
	 * @param xqmc
	 * @param xymc
	 * @param zymc
	 * @param bjmc
	 */
	public ClassSummary(String xn, String xqmc, String xymc, String zymc, String bjdm,String bjmc) {
		this();
		this.xn = xn;
		this.xqmc = xqmc;
		this.xymc = xymc;
		this.zymc = zymc;
		this.bjdm = bjdm;
		this.bjmc = bjmc;
	}
	
	
	
}
