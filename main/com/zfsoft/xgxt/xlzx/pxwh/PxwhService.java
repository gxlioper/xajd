/**
 * @部门：学工产品事业部
 * @日期：2016年11月17日
 */  
package com.zfsoft.xgxt.xlzx.pxwh;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.date.DateUtils;

/**
 * @系统名称：学生工作管理系统
 * @模块名称：心理健康培训维护 管理模块
 * @类功能描述：心理健康培训维护 业务层
 * @作者：卓耐[工号:1391]
 * @时间：2016年11月17日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class PxwhService extends SuperServiceImpl<PxwhForm,PxwhDao> {
	private PxwhDao dao= new PxwhDao();
	
	public PxwhService(){
		super.setDao(dao);
	 }
	 
	/**
	 * @描述：检验唯一性
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return ture,校验通过；false,校验不通过,存在重复项
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean checkExist(PxwhForm form) throws Exception {
		return "0".equals(dao.checkExist(form));
	}

	/** 
	 * @描述：获取培训报名列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月18日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getPxbmList(PxwhForm form,User user) throws Exception {
		List<HashMap<String, String>> list=dao.getPxbmList(form,user);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		for(HashMap<String,String> map:list){
			if(DateUtils.betweenDate(format.parse(map.get("kssj")),format.parse(map.get("jssj")))  //判断当前时间是否在区间内
				&&"开".equals(map.get("bmkg"))  //判断报名开关是否打开
				&&(Integer.valueOf(map.get("sxrs"))>(Integer.valueOf(map.get("ybmrs"))))){  //判断已报名人数是否未超过上限
				map.put("sfkfbm", "1");  //是否开关报名 置1
			}else{
				map.put("sfkfbm", "0");
			}
		}
		return list;
	}
	
	public boolean  bmcz(String pxid,String xh,String bmtype) throws Exception{
		return dao.bmcz(pxid, xh, bmtype);
	}

	/** 
	 * @描述：已报名学生列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月19日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getYbmxsList(PxwhForm model) throws Exception {
		return dao.getYbmxsList(model);
	}
	
	/**
	 * @描述：报名学生导出列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年2月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getBmxsdcList(PxwhForm model) throws Exception {
		//不分页
		Pages pages = (Pages) model.getClass().getMethod("getPages").invoke(model);
		pages.setPageSize(Integer.MAX_VALUE);
		model.getClass().getMethod("setPages",Pages.class).invoke(model, pages);
		
		return dao.getBmxsdcList(model);
	}
	
} 


