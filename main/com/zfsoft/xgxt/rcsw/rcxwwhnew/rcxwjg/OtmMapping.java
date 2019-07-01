/**
 * @部门:学工产品事业部
 * @日期：2017年3月30日 下午4:58:39 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 用于sql结果集的一对多映射 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年3月30日 下午4:58:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class OtmMapping {
	private Map<String,List<Map<String,String>>> resultMap;	//存储结果集
	
	/**
	 * @描述 ：构造函数，同时初始化结果集Map对象
	 */
	public OtmMapping(){
		resultMap = new HashMap<String,List<Map<String,String>>>();
	}

	/**
	 * @return the resultMap
	 */
	public Map<String, List<Map<String, String>>> getResultMap() {
		return resultMap;
	}

	/**
	 * @描述:向结果集中增加一条记录，该条记录根据根据id对应的关键值划分到不同list中，
	 * 		  如果在结果集中已经存在存放相应记录的list，直接加入新记录，
	 * 		  否则，新建list，再添加记录
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月30日 下午5:59:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @param map
	 * void 返回类型 
	 * @throws
	 */
	public void setResultMap(String id,HashMap<String,String> map) {
		String key = map.get(id);
		List<Map<String, String>> list = resultMap.get(key);
		if(list==null){
			list = new ArrayList<Map<String,String>>();
			resultMap.put(key, list);
		}
		list.add(map);
	}
	
	/**
	 * @描述:向结果集中增加多条记录，每条记录根据id对应的关键值划分到不同list中，
	 * 		  如果在结果集中已经存在存放相应记录的list，直接加入新记录，
	 * 		  否则，新建list，再添加记录
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月30日 下午5:58:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @param list
	 * void 返回类型 
	 * @throws
	 */
	public OtmMapping setResultMap(String id,List<HashMap<String,String>> list){
		for(HashMap<String,String> map:list){
			this.setResultMap(id, map);
		}
		return this;
	}
	
	
}
