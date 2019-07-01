package xgxt.pjpy.nbty.jxj;

import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统 Description:宁波天一清寒天使奖学金Service Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-7-14
 */
public class QhtsNbtyService {
	
	// jxjdm表
	private final String jxjb = "jxjdmb";
	// 清寒天使申请表
	private final String qhtsb = "nbty_qhtssqb";
	// 学生信息视图
	private final String xsxxb = "view_xsxxb"; 
	// 清寒天使视图
	private final String qhtsview = "view_nbty_qhtsjxj";
	
	/**
	 * 根据奖学金名称获得奖学金信息
	 * @param jxjmc
	 * @return
	 */
	public Map<String, String> getJxjxx(String jxjmc) {
		DAO dao = DAO.getInstance();
		Map<String, String> map = dao.getMapNotOut("select jxjdm,jxjlb,jlje,jxjmc from jxjdmb where jxjmc=?", 
				new String[]{jxjmc});
		return map;
	}
	
	/**
	 * 根据学号获得学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		String[] colList = new String[]{"xh","xm", "xb", "csrq", "mz", "mzmc", "nj",
								"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc"};		
		Map<String, String> map = CommonQueryDAO.commonQueryOne(xsxxb, colList, "xh", xh);
		return map;
	}
	
	/**
	 * 验证是否通过
	 * @param xh
	 * @param xn
	 * @return
	 */
	public boolean isAuditing(String xh, String xn){
		boolean isFlag = false;
		
		// 只需判断班级是否审核，学院和学校审核通过，辅导员一定已经通过	
		DAO dao =  DAO.getInstance();
		Map<String,String> map = dao.getMapNotOut("select bjsh from nbty_qhtssqb where xh=? and xn=?", new String[]{xh,xn});
		// 判断是否审核了
		if(map!=null){
			if("通过".equals( map.get("bjsh"))){
				isFlag = true;
			}
		}
		return isFlag;
	}

	/**
	 * 获取清寒天使信息
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getQhtsxx(String pkValue) {
		
		String[] colList = new String[]{"xm","xb","xh","xn","csrq","mzmc","zymc","xymc","bjmc","nj","jcqk",
				"pddd","hxnhzzz","sqly","sfcjqgzx","sfsqzxdk","bjpy","bjsh","bjshsj","xysh","xyshyj",
				"xyshsj","xxsh","xxshsj","xxshyj","sqsj"};
		
		Map<String, String> map = CommonQueryDAO.commonQueryOne(qhtsview, colList, "xh||xn", pkValue);
		if(StringUtils.isNull(map.get("sqsj"))){
			map.put("sjsj", "&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;");
		}
		if(StringUtils.isNull(map.get("bjshsj"))){
			map.put("bjshsj", "&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;");
		}
		if(StringUtils.isNull(map.get("xyshsj"))){
			map.put("xyshsj", "&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;");
		}
		if(StringUtils.isNull(map.get("xxshsj"))){
			map.put("xxshsj", "&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;");
		}
		
		if("是".equalsIgnoreCase(map.get("sfcjqgzx")) || "是".equalsIgnoreCase("sfsqzxdk")){
			map.put("help", "是");
		}else{
			map.put("help", "否");
		}
		return map;
	}
}
