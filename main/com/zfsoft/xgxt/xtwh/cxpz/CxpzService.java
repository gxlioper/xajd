/**
 * @部门:学工产品事业部
 * @日期：2013-5-27 下午02:23:14 
 */  
package com.zfsoft.xgxt.xtwh.cxpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import net.sf.json.JSONObject;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 自定义查询列 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-5-27 下午02:23:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxpzService extends SuperServiceImpl<CxpzForm, CxpzDao> {

	private CxpzDao dao = new CxpzDao();
	
	public CxpzService(){
		super.setDao(dao);
	}
	
	
	
	/**
	 * 
	 * @描述: 获取自定义查询相关功能列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-27 下午03:48:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnmc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCxgnList(String gnmc){
		return dao.getCxgnList(gnmc);
	}
	
	
	
	/**
	 * 
	 * @描述: 按功能标志查询具体的列配置
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-27 下午03:49:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnbz
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getColList(String gnbz){
		return dao.getColList(gnbz);
	}
	
	
	
	/**
	 * 
	 * @描述: 修改列配置信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-29 下午05:12:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateColInfo(CxpzForm model) throws Exception{
		
		return dao.updateColInfo(model);
	}
	
	
	
	/**
	 * 
	 * @描述: 自定义查询功能配置信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-31 下午02:12:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnbz
	 * @return
	 * JSONObject 返回类型 
	 * @throws
	 */
	public JSONObject getCxbzForJson(String gnbz,String cxpz){
		
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,String> cxpzMap = dao.getCxpz(gnbz);
		map.putAll(cxpzMap);
		
		List<HashMap<String,String>> colList = dao.getAllColList(gnbz);
		List<Map<String,Object>> newColList = new ArrayList<Map<String,Object>>();
		
		for (Map<String,String> colMap : colList){
			Map<String,Object> newMap = new HashMap<String, Object>();
			newMap.put("label", colMap.get("label"));
			newMap.put("name", colMap.get("name"));
			newMap.put("index", colMap.get("pxzd"));
			newMap.put("width", colMap.get("width")+"%");
			newMap.put("key", "Y".equals(colMap.get("iskey")));
			newMap.put("hidden","Y".equals(colMap.get("ishidden")));
			
			if (!StringUtils.isNull(colMap.get("formatter")) && !"yes".equals(cxpz)){
				newMap.put("formatter", colMap.get("formatter"));
			}
			
			newColList.add(newMap);
		}
		
		map.put("colList",newColList);
		return JSONObject.fromMap(map);
	}
}
