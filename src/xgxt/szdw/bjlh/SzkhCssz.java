/**
 * @部门:学工产品事业部
 * @日期：2013-12-13 上午11:39:22 
 */  
package xgxt.szdw.bjlh;

import java.util.HashMap;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍
 * @类功能描述: 思政队伍考核参数设置初始化
 * @作者： 陈敏杰
 * @时间： 2013-12-13 上午11:39:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzkhCssz {
	public static String KHDX ="all";   //考核对象fdy：辅导员考核、bzr：班主任考核、all：辅导员班主任考核，默认all;
	public static String KHRY ="all";   //考核人员：xs.学生考核;cpz.参评组考核；all.学生、参评组考核，默认all;
	
	static{
		csInit();
	}
	public static void csInit(){
		SzkhCsszDao dao=new SzkhCsszDao(); 
		HashMap<String, String> map=dao.getSzkhCssz();
		
		if(map.get("khry") !=null && !"".equalsIgnoreCase(map.get("khry"))){
			KHRY = map.get("khry");
		}
		if(map.get("khdx") !=null && !"".equalsIgnoreCase(map.get("khdx"))){
			KHDX = map.get("khdx");
		}
	}
}
