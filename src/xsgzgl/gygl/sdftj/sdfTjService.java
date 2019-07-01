/**
 * @部门:学工产品事业部
 * @日期：2016-12-9 下午02:26:12 
 */  
package xsgzgl.gygl.sdftj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-9 下午02:26:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class sdfTjService extends SuperServiceImpl<sdfTjForm, sdfTjDao> {
	/**
	 * 
	 * @描述: 获取楼栋代码List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:02:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(){
		return dao.getLddmList();
	}
	
	/**
	 * 
	 * @描述: 获取寝室List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:04:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(String lddm,String ch){
		return dao.getQshList(lddm, ch);
	}
	
	/**
	 * 
	 * @描述: 获取层号List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(String lddm){
		return dao.getChList(lddm);
	}
	
	/**
	 * 
	 * @描述: 获取层号
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public String getCh(String qsh){
		return dao.getCh(qsh);
	}
	
	/**
	 * 
	 * @描述: 获取楼栋
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public String getLdmc(String lddm){
		return dao.getLdmc(lddm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午05:22:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String saveData(sdfTjForm form) throws Exception{
		boolean rs = true;
		if("save".equals(form.getType())){
			if(!dao.checkIsNotExists(form.getNd(),form.getJd(),form.getLddm(),form.getQsh())){
				return form.getNd()+"年度"+form.getJd()+"数据已存在！";
			}
			rs = dao.runInsert(form);
			return rs ? "保存成功！" :"保存失败！";
			
		}else{
			rs = dao.runUpdate(form);
			return rs ? "保存成功！" :"保存失败！";
		}
	}

}
