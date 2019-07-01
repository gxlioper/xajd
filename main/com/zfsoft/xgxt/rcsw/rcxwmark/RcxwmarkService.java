/**
 * @部门:学工产品事业部
 * @日期：2016-10-28 下午02:44:59 
 */  
package com.zfsoft.xgxt.rcsw.rcxwmark;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-10-28 下午02:44:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwmarkService extends SuperServiceImpl<RcxwmarkForm, RcxwmarkDao> {
	/**
	 *
	 * @描述: 已处理查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-31 上午10:54:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYclList(RcxwmarkForm t, User user)
	throws Exception {
       // TODO 自动生成方法存根
       return dao.getYclList(t, user);
    }
	
	/**
	 * 
	 * @描述: 取标题名和读写权限
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-1 下午02:20:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param username
	 * @param dyym
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	 public HashMap<String, String> getWriteAble(String username,String dyym){
		 return dao.getWriteAble(username, dyym);
	 }
	 
	 /**
	  * 
	  * @描述:获取查看数据
	  * @作者：yxy[工号：1206]
	  * @日期：2016-11-1 下午06:56:53
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param id
	  * @return
	  * HashMap<String,String> 返回类型 
	  * @throws
	  */
	 public HashMap<String, String> getCkData(String id){
		 return dao.getCkData(id);
	 }
	 
	 /**
	  * 
	  * @描述:获取查看数据
	  * @作者：yxy[工号：1206]
	  * @日期：2016-11-1 下午06:56:53
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param id
	  * @return
	  * HashMap<String,String> 返回类型 
	  * @throws
	  */
	 public HashMap<String, String> getCkDataWcl(String rcxwjgid){
		 return dao.getCkDataWcl(rcxwjgid);
	 }
	 
	 /**
	  * 
	  * @描述: 批量设置保存
	  * @作者：yxy[工号：1206]
	  * @日期：2016-11-1 下午07:00:03
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @return
	  * boolean 返回类型 
	  * @throws
	  */
	 public boolean insertData(String[] rcxwjgids,String jxdm,String pjxn,String bz,String czr){
		 return dao.insertData(rcxwjgids, jxdm, pjxn, bz, czr);
	 }
}
