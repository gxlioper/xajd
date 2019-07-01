/**
 * @部门:学工产品事业部
 * @日期：2016-11-24 上午11:25:16 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl.LxmdwhjlDao;
import com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl.LxmdwhjlForm;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-24 上午11:25:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxmdwhService extends SuperServiceImpl<LxmdwhForm, LxmdwhDao> {
	/**
	 * 
	 * @描述: 获取留校项目名称List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午10:27:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmmcList(){
		return dao.getXmmcList();
	}
	
	/**
	 * 
	 * @描述: 获取留校项目名称List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午10:27:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmmcMap(String xmid){
		return dao.getXmmcMap(xmid);
	}
	
	/**
	 * 
	 * @描述:批量维护时判断是否可以保存，判断依据xh,xmid为唯一键
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午11:15:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIfCanSave(String[] xhs,String xmid){
		return dao.checkIfCanSave(xhs, xmid);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午11:32:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param xhs
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savePlwh(LxmdwhForm t,String[] xhs,String czr) throws SQLException{
		
		boolean result = dao.savePlwh(t, xhs);
		
		//增加对维护记录表的操作
		if(result){
			result = new LxmdwhjlDao().saveLxmdwhjlList(t,xhs,czr);
		}
		return result;
		
	}
	
	/**
	 * @throws Exception 
	 * @描述: 获取可以添加的学生List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-28 下午01:32:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCanAddStuList(LxmdwhForm t, User user,String xhs) throws Exception{
		return dao.getCanAddStuList(t, user, xhs);
	}

	/**
	 * @throws Exception  
	 * @描述:留校名单修改
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月28日 下午2:40:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateLxmd(LxmdwhForm myForm,String czr) throws Exception {
		LxmdwhForm lxmdwhForm = dao.getModel(myForm);
		boolean result = dao.runUpdate(myForm);
		if(result){
			String xgqlxqksm = lxmdwhForm.getLxqksm();
			String xghlxqksm = myForm.getLxqksm();
			if(StringUtils.isNotNull(xghlxqksm)&&(!xghlxqksm.equals(xgqlxqksm))){
				LxmdwhjlForm lxmdwhjlForm = new LxmdwhjlForm();
				lxmdwhjlForm.setCzr(czr);
				lxmdwhjlForm.setCzlx("2");
				lxmdwhjlForm.setXh(lxmdwhForm.getXh());
				lxmdwhjlForm.setXmid(lxmdwhForm.getXmid());
				lxmdwhjlForm.setXgqlxqksm(xgqlxqksm);
				lxmdwhjlForm.setXghlxqksm(xghlxqksm);
				result = new LxmdwhjlDao().runInsert(lxmdwhjlForm);
			}
		}
		return result;
	}

	/**
	 * @throws Exception  
	 * @描述:批量删除留校名单
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月28日 下午3:45:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int deleteLxmd(String[] ids,String czr) throws Exception {
		int num = 0;
		//删除前对记录表操作
		List<HashMap<String,String>> lxmdList = dao.getLxmdListByIds(ids);//查询将要删除的留校名单信息列表
		boolean result = new LxmdwhjlDao().saveLxmdwhjlList(lxmdList, czr);
		
		if(result){
			num = dao.runDelete(ids);
		}
		return num;
	}
}
