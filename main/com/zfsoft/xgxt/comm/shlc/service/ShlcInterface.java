/**
 * @部门:学工产品事业部
 * @日期：2013-6-13 下午04:11:35 
 */  
package com.zfsoft.xgxt.comm.shlc.service;


import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;



/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 通用模块
 * @类功能描述: 审核操作
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-6-13 下午04:11:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public interface ShlcInterface {

	
	/**
	 * 
	 * @描述: 审核操作
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-13 下午04:22:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return String 当前状态
	 * @throws Exception 
	 */
	public String runAuditing(ShxxModel model) throws Exception;
	
	public String runAuditingNotCommit(ShxxModel model) throws Exception;
	
	/**
	 * 
	 * @描述:申请人的撤销
	 * @作者：945
	 * @日期：2013-11-26 上午08:59:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean firstStepCancle(String ywid,String lcid) throws Exception;
	
	/**
	 * 
	 * @描述: 撤消审核操作
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-14 下午01:36:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid 业务ID
	 * @param shlc 审核流程ID
	 * @param gwid 岗位ID
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 */
	public boolean runCancel(String shr,String ywid,String shlc,String gwid) throws Exception;

	/**
	 * 描述: 撤销审核【最后一级不可撤销】
	 * @作者：qilm
	 * @日期：2013-10-11 
	 * @param shr 审核人
	 * @param shid 审核ID（系统维护-审核状态表：GUID）
	 * @param shlc 审核流程ID
	 */
	public boolean runCancel(String shr, String shid,String shlc) throws Exception;
	
	/**
	 * 描述: 撤销审核【最后可撤销】
	 * @作者：qilm
	 * @日期：2013-10-11 
	 * @param shr 审核人
	 * @param shid 审核ID（系统维护-审核状态表：GUID）
	 * @param shlc 审核流程ID
	 */
	public String runCancelNew(String shr, String shid, String shlc) throws Exception;
	
	/**
	 * 
	 * @描述: 申请提交
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-13 下午04:21:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param shlc
	 * @return
	 * boolean 返回类型 
	 * @throws Exception 
	 */
	public boolean runSubmit(String ywid,String shlc,String xh,String tzlj,String tzljsq) throws Exception;
	
	/**
	 * 
	 * @描述:撤销的时候删除提交记录
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-11-28 下午01:46:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteShjl(String ywid) throws Exception;


	/**
	 * 
	 * @描述: 可退回的目标岗位列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 下午02:09:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param gwid
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getKthSpgw(String splc,String gwid);
	
	/**
	 * 
	 * @描述:通过业务id和岗位id得到该条件下最新的一条记录
	 * @作者：hj[945]
	 * @日期：2013-12-17 上午10:31:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param gwid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getShxxByCondition(String ywid,String gwid);
	
	/**
	 * 
	 * @描述:审批流程是否完成,true:已完成，false:未完成
	 * @作者：945
	 * @日期：2013-12-27 上午10:27:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean splcsfwc(String ywid);
	
	
	
	
	/**
	 * 
	 * @描述: 按用户、功能id查询常用审核意见
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-5-4 下午03:47:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gnid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getShyjList(User user,String gnid);
	
	
	/**
	 * 
	 * @描述: 保存常用审核意见
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-5-5 上午08:42:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gnid
	 * @param shyj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCyyj(User user,String gnid,String[] shyj);
	
	 public  boolean runSubmitBatchNotCommit(String[] ywids, String shlc,String[] xhs,String tzlj,String tzljsq)throws Exception;
}
