/**
 * @部门:学工产品事业部
 * @日期：2017年4月10日 上午9:14:58 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-我的评奖-评奖审核-学生成绩汇总导出
 * @类功能描述: 学生成绩汇总结果集映射
 * @作者： xuwen[工号:1426]
 * @时间： 2017年4月10日 上午9:14:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ResultSummary {

	private Map<String,ClassSummary> resultMap;	//存储结果集

	/**
	 * @描述 ：构造函数，同时初始化结果集Map对象
	 */
	public ResultSummary() {
		resultMap = new HashMap<String,ClassSummary>();
	}

	/**
	 * @return the resultMap
	 */
	public Map<String,ClassSummary> getResultMap() {
		return resultMap;
	}
	
	/**
	 * @描述:向结果集中增加一条记录，该条记录根据根据id对应的关键值划分到不同班级对象中，
	 * 		  如果在结果集中已经存在存放相应记录的班级，直接加入新记录，
	 * 		  否则，新建班级，再添加记录
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月10日 上午11:01:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param map
	 * void 返回类型 
	 * @throws
	 */
	public void setResultMap(String id,HashMap<String,String> map) {
		String key = map.get(id);	//班级代码
		String xqmc = map.get("xqmc");
		ClassSummary classSummary = resultMap.get(key);
		if(classSummary==null){
			String xn = map.get("xn");
			String xymc = map.get("xymc");
			String zymc = map.get("zymc");
			String bjdm = map.get("bjdm");
			String bjmc = map.get("bjmc");
			classSummary = new ClassSummary(xn, xqmc, xymc, zymc,bjdm, bjmc);
			resultMap.put(key, classSummary);
		}else{
			if(StringUtils.isNotNull(classSummary.getXqmc())&&(!classSummary.getXqmc().equals(xqmc))&&(!classSummary.getXqmc().contains("、"))){
				classSummary.setXqmc(classSummary.getXqmc()+"、"+xqmc);
			}
		}
		classSummary.addRecord(map);
	}

	/**
	 * @描述:向结果集中增加多条记录，每条记录根据根据id对应的关键值划分到不同班级对象中，
	 * 		  如果在结果集中已经存在存放相应记录的班级，直接加入新记录，
	 * 		  否则，新建班级，再添加记录
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月10日 上午11:02:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param list
	 * @return
	 * ResultSummary 返回类型 
	 * @throws
	 */
	public ResultSummary setResultMap(String id,List<HashMap<String,String>> list) {
		for(HashMap<String,String> map:list){
			this.setResultMap(id, map);
		}
		return this;
	}
	
	
	
}
