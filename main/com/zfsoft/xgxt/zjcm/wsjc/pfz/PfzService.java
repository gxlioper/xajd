/**
 * @部门:学工产品事业部
 * @日期：2016-2-26 下午04:58:36 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.pfz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述:  传媒卫生分_评分组
 * @作者： cq [工号:785]
 * @时间： 2016-2-26 下午04:58:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PfzService extends SuperServiceImpl<PfzForm, PfzDao> {
	
	private PfzDao pfzDao = new PfzDao();

	/** 
	 * @描述:判断评分组是否重复
	 * @作者：cq [工号：785]
	 * @日期：2016-2-29 下午07:34:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHave(PfzForm model) {
		String num = dao.ChkmcExist(model);
		return Integer.parseInt(num)>0;
	}

	/** 
	 * @描述:保存评分组
	 * @作者：cq [工号：785]
	 * @日期：2016-2-29 下午07:36:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean editPfz(PfzForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String pfzid = UniqID.getInstance().getUniqIDHash();
			model.setPfzid(pfzid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
		
	}
	
	
	/**
	 * 
	 * @描述:判断评分是否有使用
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 下午06:45:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isUsed(String values) {
		boolean flag = false;
		if (values != null) {
			String[] pfzArr = values.split(",");
			for (int i = 0; i < pfzArr.length; i++) {
				flag = dao.isUsed(pfzArr[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * @throws Exception  
	 * @描述:删除对应的评分成员（暂时不考虑返回消息
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午09:59:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * void 返回类型 
	 * @throws 
	 */
	public boolean delPfcy(String[] ids) throws Exception {
		if(null==ids||ids.length==0){
			return false;
		}
		return dao.delPfcy(ids);
	}

	/**
	 * @throws Exception  
	 * @描述:评分成员列表
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午10:24:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPfcyList(PfzForm myForm, User user) throws Exception {
		return dao.getPfcyList(myForm, user);
	}

	/**
	 * @throws Exception  
	 * @描述:保存评分成员
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午10:37:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param value
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean savePfcy(PfzForm model, String value) throws Exception {
		return dao.savePfcy(model,value);
	}

	/** 
	 * @描述:取消评分成员
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午11:06:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param value
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean savePfcyQxFp(PfzForm model, String value) throws Exception {
		return dao.savePfcyQx(model, value);
	}

	/** 
	 * @描述:评分对象查看
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 下午03:20:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPfzList(PfzForm model, User user) throws Exception {
		return dao.getPfzList(model,user);
	}
	
	/**
	 * 
	 * @描述:查询评分组信息
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 下午07:29:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pfzid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getZxx(String pfzid) throws Exception{
		return dao.getZxx(pfzid);
	}

}
