/**
 * @部门:学工产品事业部
 * @日期：2016-7-25 上午08:52:53 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.comm;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-25 上午08:52:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CommTtxmService extends SuperServiceImpl<TttzxmForm, CommTtxmDao> {
	/**
	 * 
	 * @描述:查询学生信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 上午11:05:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXsxx(String xh,String xmdm,String[] xhs){
		return dao.getXsxx(xh,xmdm,xhs);
	}
	
	/**
	 * 
	 * @描述:学生列表查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 下午05:39:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(TttzxmForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		return dao.getXsxxList(model, user);
	}
	
	/**
	 * @描述:检查数据重复 一个人在一个项目中只能参加一个团队(一个人只能参加一个项目)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 上午11:32:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public String checkIsNotExists(String[] xh,String xmdm,String ttsqid){
		return dao.checkIsNotExists(xh, xmdm, ttsqid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存团队成员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午01:49:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveTtcy(String ttsqid,String xmdm,String[] xh) throws Exception{
		return dao.saveTtcy(ttsqid, xmdm, xh);
	}
	
	/**
	 * 
	 * @描述: 检查团队名称是否重复(同一项目下队伍名称不可以重复)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午02:19:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tdmc
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNameIsNotExists(String tdmc,String xmdm,String ttsqid,String flag){
		return dao.checkNameIsNotExists(tdmc, xmdm, ttsqid, flag);
	}
	
	/**
	 * 
	 * @描述: 检查是否已进入阶段维护
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午02:52:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotInJdwh(String jdcy){
		return dao.checkIsNotInJdwh(jdcy);
	}
	
	/**
	 * 
	 * @描述:获取排除队长外其他学生
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-27 上午09:01:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ttsqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDyxxNotDz(String ttsqid,String dzxh){
		return dao.getDyxxNotDz(ttsqid, dzxh);
	}
	
	/**
	 * 
	 * @描述: 获取项目信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-27 上午09:41:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmxxMap(String xmdm){
		return dao.getXmxxMap(xmdm);
	}
	
	/**
	 * 
	 * @描述:获取队长信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-29 上午09:50:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xmdm
	 * @param xhs
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDzxx(String xh){
		return dao.getDzxx(xh);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除团体成员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-29 上午11:27:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delTtcy(String[] ids) throws Exception{
		return dao.delTtcy(ids);
	}
	
}
