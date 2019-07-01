/**
 * @部门:学工产品事业部
 * @日期：2016-7-4 上午10:35:25 
 */  
package com.zfsoft.xgxt.xszz.jtqkxgzd;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.JsonUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-4 上午10:35:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XgzdService extends SuperServiceImpl<XgzdForm, XgzdDao> {
	/**
	 * 
	 * @描述: 家庭情况调查字段设置信息查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-4 下午01:42:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnmk
	 * @param xxdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtqkdcZdxx(String gnmk,String xxdm){
		return dao.getJtqkdcZdxx(gnmk, xxdm);
	}
	
	/**
	 * 
	 * @描述:获取json格式 的字段信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-4 下午02:30:08
	 * @修改记录: xuwen-2017/3/1-增加附件信息相关
	 * @return
	 * JSONObject 返回类型 
	 * @throws
	 */
	public JSONObject getJsonData(){
		String xxdm = Base.xxdm;
		//家庭情况修改字段
		List<HashMap<String, String>> jtqkdclist = this.getJtqkdcZdxx("jtqkdc", xxdm);
		//如果不存在学校个性化配置，就取public
		if(jtqkdclist == null || jtqkdclist.size() == 0){
			jtqkdclist = this.getJtqkdcZdxx("jtqkdc", "public");
		}
		//家庭成员修改字段
		List<HashMap<String, String>> jtcylist = this.getJtqkdcZdxx("jtcy", xxdm);
		//如果不存在学校个性化配置，就取public
		if(jtcylist == null || jtcylist.size() == 0){
			jtcylist = this.getJtqkdcZdxx("jtcy", "public");
		}
		
		//附件信息修改字段
		List<HashMap<String, String>> fjxxList = this.getJtqkdcZdxx("fjxx", xxdm);
		//如果不存在学校个性化配置，就取public
		if(fjxxList == null || fjxxList.size() == 0){
			fjxxList = this.getJtqkdcZdxx("fjxx", "public");
		}
				
		//将数据转换成json格式
		JSONArray json_jtqkdclist = JsonUtil.ListToJSON(jtqkdclist);
		JSONArray json_jtcylist = JsonUtil.ListToJSON(jtcylist);
		JSONArray json_fjxxList = JsonUtil.ListToJSON(fjxxList);
		HashMap<String, JSONArray> datamap = new HashMap<String, JSONArray>();
		datamap.put("jtqkdclist", json_jtqkdclist);
		datamap.put("jtcylist", json_jtcylist);
		datamap.put("fjxxList", json_fjxxList);
		JSONObject JsonObj = JSONObject.fromObject(datamap);
		return JsonObj;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存字段必填非必填信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-5 下午01:46:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveData(XgzdForm model) throws Exception{
		
		return dao.saveData(model);
	}
}
