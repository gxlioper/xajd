/**
 * @部门:学工产品事业部
 * @日期：2013-6-13 下午04:29:55 
 */  
package com.zfsoft.xgxt.comm.shlc.service.impl;


import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.znxgl.wdznx.JgxxtsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 通用审核模块
 * @类功能描述: 通用审核流程实现（不满足个性化业务）
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-6-13 下午04:29:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public final class CommShlcImpl implements ShlcInterface,Constants {

	private ShlcDao dao = new ShlcDao();
	private Log logger = LogFactory.getLog(ShlcDao.class);
	
	
	/*
	 * 
	      描述: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#runAuditing(com.zfsoft.xgxt.comm.shlc.model.ShxxModel)
	 */
	//验证是否可插入结果库
//	public synchronized String canSubmit(ShxxModel model) throws Exception {
//		HashMap<String,String> dshMap = dao.getDshGwid(model.getYwid());
//		String dshGwid = dshMap.get("gwid");
//		// 查询下一级审批岗位
//		String xjgw = dao.getNextGwid(model.getShlc(), dshGwid);
//		String lastGwid = dao.getLastGwid(model.getShlc());
//		if(StringUtils.isNull(xjgw)&&lastGwid.equalsIgnoreCase(dshGwid)){
//			
//		}
//	}
	
	public synchronized String runAuditing(ShxxModel model) throws Exception {
		logger.info("调用 CommShlcImpl.runAuditing 开始");
		//待审记录信息~
		HashMap<String,String> dshMap = dao.getDshGwid(model.getYwid());
		String dshGwid = dshMap.get("gwid");
		logger.info("验证【要审的岗位与待审岗位是否相符】：根据ywid【"+model.getYwid()+"】查询gwid=" + dshGwid + " 实际前台传入gwid=" + model.getGwid());
 		if (!model.getGwid().equals(dshGwid)){
 			//要审的岗位与待审岗位不符，说明待审级已被其它人审核
 			throw new SystemException(MessageKey.SYS_AUD_FAIL);
 		}
 		logger.info("判断是否已插入审核状态表：根据ywid【"+model.getYwid()+"】 实际前台传入gwid=" + model.getGwid());
		//判断是否已插入审核状态表,避免产生重复数据   备注：测试发现此方法多余，写好了暂时就不删了
		if(dao.getExistForShzt(model.getYwid(),model.getGwid())){
			//重复提交
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
			
		// 查询下一级审批岗位
		String xjgw = dao.getNextGwid(model.getShlc(), dshGwid);
		logger.info("SH_TG 查询下一级审批岗位： xjgw=" + xjgw + " Shlc=" + model.getShlc() + " dshGwid=" + dshGwid);
		//判断当前审核岗位是否为最后一级
		String lastGwid = dao.getLastGwid(model.getShlc());
		if (!lastGwid.equalsIgnoreCase(dshGwid)&&StringUtils.isNull(xjgw)) {
			//审核流异常
 			throw new SystemException(MessageKey.SYS_AUD_FAIL);
		}
		
		logger.info("更新本级审核状态开始：guid=" + dshMap.get("guid") + ",shzt=" + model.getShzt());
		
		// 更新本级审核状态
		//boolean isSuccess = dao.updateShxx(model.getShr(),model.getShzt(),model.getShyj(),dshMap.get("guid"));
		boolean isSuccess = dao.updateShxx(model,dshMap.get("guid"));
		logger.info("更新本级审核状态结束");
		if (SH_BTG.equals(model.getShzt())) {
			logger.info("SH_BTG 不通过、流程截止");
			//插入推送信息
			dao.insertShjgXxts(dshMap.get("guid"));
			//不通过、流程截止
			return SH_BTG;
		} else if (SH_TH.equals(model.getShzt())) {

			if (StringUtils.isNull(model.getThgw())||model.getThgw().equals("-1")) {
				logger.info("SH_TH 退回给申请人");
				//插入推送信息
				dao.insertShjgXxts(dshMap.get("guid"));
				//退回给申请人 无目标岗位
				return SH_TH;
			}
			logger.info("SH_TH 退回的目标岗位插入待审数据(需重审)开始：Ywid=" + model.getYwid() + " Thgw=" + model.getThgw() + " SH_XCS=" + SH_XCS + " Shlc=" + model.getShlc() + " Sqrid=" + model.getSqrid() + " Tzlj=" + model.getTzlj() + " Tzljsq=" + model.getTzljsq());
			// 退回的目标岗位插入待审数据(需重审)
			boolean result = dao.insertThjd(model.getYwid(), model.getThgw(),SH_XCS,model.getShlc(),model.getSqrid(),model.getTzlj(),model.getTzljsq());
			logger.info("SH_TH 退回的目标岗位插入待审数据(需重审)结束");

			//TODO 向退回目标岗位推送消息
			sendDshMsg(model.getThgw(),model.getSqrid(),model.getShlc(),result);
			return YW_SHZ;

		} else if (isSuccess && SH_TG.equals(model.getShzt())) {
			
			if(StringUtils.isNull(xjgw)&&lastGwid.equalsIgnoreCase(dshGwid)){
				logger.info("SH_TG 最后一级审核");
				//最后一级审核
				return SH_TG;
			}
			logger.info("SH_TG 插入下一级待审开始：Ywid=" + model.getYwid() + " xjgw=" + xjgw  + " Shlc=" + model.getShlc() + " Sqrid=" + model.getSqrid() + " Tzlj=" + model.getTzlj() + " Tzljsq=" + model.getTzljsq());
			// 插入下一级待审
			boolean result = dao.insertDbjd(model.getYwid(), xjgw ,model.getShlc(),model.getSqrid(),model.getTzlj(),model.getTzljsq());
			logger.info("SH_TG 插入下一级待审结束");

			//TODO 向下一级待审核岗位推送消息
			sendDshMsg(xjgw,model.getSqrid(),model.getShlc(),result);
		}
		logger.info("调用 CommShlcImpl.runAuditing 结束");
		return YW_SHZ;

	}
	public synchronized String runAuditingNotCommit(ShxxModel model) throws Exception {
		logger.info("调用 CommShlcImpl.runAuditingNotCommit 开始");
		//待审记录信息~
		HashMap<String,String> dshMap = dao.getDshGwid(model.getYwid());
		String dshGwid = dshMap.get("gwid");
		logger.info("验证【要审的岗位与待审岗位是否相符】：根据ywid【"+model.getYwid()+"】查询gwid=" + dshGwid + " 实际前台传入gwid=" + model.getGwid());
 		if (!model.getGwid().equals(dshGwid)){
 			//要审的岗位与待审岗位不符，说明待审级已被其它人审核
 			throw new SystemException(MessageKey.SYS_AUD_FAIL);
 		}
 		logger.info("判断是否已插入审核状态表：根据ywid【"+model.getYwid()+"】 实际前台传入gwid=" + model.getGwid());
		//判断是否已插入审核状态表,避免产生重复数据   备注：测试发现此方法多余，写好了暂时就不删了
		if(dao.getExistForShzt(model.getYwid(),model.getGwid())){
			//重复提交
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
			
		// 查询下一级审批岗位
		String xjgw = dao.getNextGwid(model.getShlc(), dshGwid);
		logger.info("SH_TG 查询下一级审批岗位： xjgw=" + xjgw + " Shlc=" + model.getShlc() + " dshGwid=" + dshGwid);
		//判断当前审核岗位是否为最后一级
		String lastGwid = dao.getLastGwid(model.getShlc());
		if (!lastGwid.equalsIgnoreCase(dshGwid)&&StringUtils.isNull(xjgw)) {
			//审核流异常
 			throw new SystemException(MessageKey.SYS_AUD_FAIL);
		}
		
		logger.info("更新本级审核状态开始：guid=" + dshMap.get("guid") + ",shzt=" + model.getShzt());
		
		// 更新本级审核状态
		//boolean isSuccess = dao.updateShxx(model.getShr(),model.getShzt(),model.getShyj(),dshMap.get("guid"));
		boolean isSuccess = dao.updateShxxNotCommit(model,dshMap.get("guid"));
		logger.info("更新本级审核状态结束");
		if (SH_BTG.equals(model.getShzt())) {
			logger.info("SH_BTG 不通过、流程截止");
			//不通过、流程截止
			return SH_BTG;
		} else if (SH_TH.equals(model.getShzt())) {

			if (StringUtils.isNull(model.getThgw())||model.getThgw().equals("-1")) {
				logger.info("SH_TH 退回给申请人");
				//退回给申请人 无目标岗位
				return SH_TH;
			}
			logger.info("SH_TH 退回的目标岗位插入待审数据(需重审)开始：Ywid=" + model.getYwid() + " Thgw=" + model.getThgw() + " SH_XCS=" + SH_XCS + " Shlc=" + model.getShlc() + " Sqrid=" + model.getSqrid() + " Tzlj=" + model.getTzlj() + " Tzljsq=" + model.getTzljsq());
			// 退回的目标岗位插入待审数据(需重审)
			dao.insertThjdNotCommit(model.getYwid(), model.getThgw(),SH_XCS,model.getShlc(),model.getSqrid(),model.getTzlj(),model.getTzljsq());
			logger.info("SH_TH 退回的目标岗位插入待审数据(需重审)结束");
			return YW_SHZ;

		} else if (isSuccess && SH_TG.equals(model.getShzt())) {
			
			if(StringUtils.isNull(xjgw)&&lastGwid.equalsIgnoreCase(dshGwid)){
				logger.info("SH_TG 最后一级审核");
				//最后一级审核
				return SH_TG;
			}
			logger.info("SH_TG 插入下一级待审开始：Ywid=" + model.getYwid() + " xjgw=" + xjgw  + " Shlc=" + model.getShlc() + " Sqrid=" + model.getSqrid() + " Tzlj=" + model.getTzlj() + " Tzljsq=" + model.getTzljsq());
			// 插入下一级待审
			dao.insertDbjdNotCommit(model.getYwid(), xjgw ,model.getShlc(),model.getSqrid(),model.getTzlj(),model.getTzljsq());
			logger.info("SH_TG 插入下一级待审结束");
		}
		logger.info("调用 CommShlcImpl.runAuditing 结束");
		return YW_SHZ;

	}

	
	/*
	      描述: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#runSubmit(java.lang.String, java.lang.String)
	 */
	public synchronized boolean runSubmit(String ywid, String shlc,String xh,String tzlj,String tzljsq) throws Exception {
		
		//判断是否已插入审核状态表,避免产生重复数据
		if(dao.getExistForShzt(ywid)){
			//重复提交
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		
		//查询第一级岗位ID
		String firstGwid = dao.getFirstGwid(shlc);
		//保存第一级待审节点
		boolean result = dao.insertDbjd(ywid,firstGwid,shlc,xh,tzlj,tzljsq);

		//TODO 向第一级待审核岗位推送消息
		sendDshMsg(firstGwid,xh,shlc,result);
		return result;
	}

	
	/*
	      描述: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#runCancel(java.lang.String, java.lang.String, java.lang.String)
	 */
	public synchronized boolean runCancel(String shr,String ywid, String shlc, String gwid)
			throws Exception {
		/*
		 * -------------检测是否可以撤消审核-----------
		 * 先决条件：先确定自己最后审批给出的状态
		 * 撤消检测：
		 * 
		 * ①不通过
		 * ② 通过，当前待审的记录是自己的下一级并且未审核
		 * ③ 退回 ，不能撤消、抛异常
		 * 
		 */
		String lastShzt = dao.getLastShzt(shr, ywid, gwid);
		String nextSpgw = dao.getNextGwid(shlc, gwid);
		
//		if(StringUtils.isNull(nextSpgw)){
//			throw new SystemException(MessageKey.SYS_CANCEL_END);
//		}
		boolean isFhtj = false;
		if (SH_BTG.equals(lastShzt)){
			//不通过可以直接撤消
			isFhtj = true;
		} else if (SH_TG.equals(lastShzt)){
			isFhtj = true;
			//删除参数岗位的下一级待审数据，删除成功可撤消否则不能。
			if (!StringUtil.isNull(nextSpgw)){
				isFhtj = dao.delNextDsjl(ywid,nextSpgw);
			}
			
		} else if (SH_TH.equals(lastShzt)){
			//退回状态不允许
			throw new SystemException(MessageKey.SYS_CANCEL_TH);
		} else {
			//不知名状态不被允许
			throw new SystemException(MessageKey.SYS_AUD_CANCEL_FAIL);
		}
		
		//符合撤消条件--更新参数岗位的状态为撤消、增加参数岗位待审数据
		if (isFhtj){
			dao.updateShzt(ywid, gwid, SH_DSH);
			//dao.insertDbjd(ywid, gwid);

			// 我的待办回滚
			MyJobsManager manager = new MyJobsImpl();
			manager.cancelJob(ywid, gwid);
			
			return true;
			
		}else{
			throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
		}
	}
	
	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：945
	 * @日期：2013-11-25 下午06:41:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public synchronized boolean firstStepCancle(String ywid,String lcid) throws Exception{
		boolean isFhtj = false;
		List<HashMap<String, String>> firstZt = dao.getFirstShzt(ywid, lcid);
		if(firstZt!=null&&firstZt.size()>0){
			if(firstZt.size()>1){
				throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
			}else{
				String shzt = firstZt.get(0).get("shzt");
//				String gwid = firstZt.get(0).get("gwid");
				
				if(shzt.equals("0")){
					isFhtj = true;
				}else{
					throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
				}
				//符合撤消条件--删除该提交记录、增加参数岗位待审数据
				if (isFhtj){
					dao.delelteShjl(ywid);
//					// 我的待办回滚
//					MyJobsManager manager = new MyJobsImpl();
//					manager.cancelJob(ywid, gwid);
					return true;
				}else{
					throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
				}
			}
		}else{
			throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
		}
	}
	

	/**
	 * 描述: 撤销审核【最后一级不可撤销】
	 * @作者：qilm
	 * @日期：2013-10-11 
	 * @param shr 审核人
	 * @param shid 审核ID（系统维护-审核状态表：GUID）
	 * @param shlc 审核流程ID
	 */
	public synchronized boolean runCancel(String shr, String shid,
			String shlc) throws Exception {

		// 取得业务ID/审批岗位ID
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);
		String ywid = shxx.get("ywid");
		String gwid = shxx.get("gwid");
		/*
		 * -------------检测是否可以撤消审核-----------
		 * 先决条件：先确定自己最后审批给出的状态
		 * 撤消检测：
		 * 
		 * ①不通过
		 * ② 通过，当前待审的记录是自己的下一级并且未审核
		 * ③ 退回 ，不能撤消、抛异常
		 * 
		 */
		String lastShzt = dao.getLastShzt(shr, ywid, gwid);
		String nextSpgw = dao.getNextGwid(shlc, gwid);
		
		if(StringUtils.isNull(nextSpgw)){
			throw new SystemException(MessageKey.SYS_CANCEL_END);
		}
		boolean isFhtj = false;
		if (SH_BTG.equals(lastShzt)){
			//不通过可以直接撤消
			isFhtj = true;
		} else if (SH_TG.equals(lastShzt)){
			//删除参数岗位的下一级待审数据，删除成功可撤消否则不能。
			isFhtj = dao.delNextDsjl(ywid,nextSpgw);
		} else if (SH_TH.equals(lastShzt)){
			//退回状态不允许
			throw new SystemException(MessageKey.SYS_CANCEL_TH);
		} else {
			//不知名状态不被允许
			throw new SystemException(MessageKey.SYS_AUD_CANCEL_FAIL);
		}
		
		//符合撤消条件--更新参数岗位的状态为撤消、增加参数岗位待审数据
		if (isFhtj){
			 dao.updateShzt(shid, SH_DSH);
			 //dao.insertDbjd(ywid, gwid);

			// 我的待办回滚
			MyJobsManager manager = new MyJobsImpl();
			manager.cancelJob(ywid, gwid);
			 
			return true;
		}else{
			throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
		}
	}
	
	/**
	 * 描述: 撤销审核【最后可撤销】
	 * @作者：qilm
	 * @日期：2013-10-11 
	 * @param shr 审核人
	 * @param shid 审核ID（系统维护-审核状态表：GUID）
	 * @param shlc 审核流程ID
	 */
	public synchronized String runCancelNew(String shr, String shid,
			String shlc) throws Exception {
		
		// 取得业务ID/审批岗位ID
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);
		String ywid = shxx.get("ywid");
		String gwid = shxx.get("gwid");
		String shqzt= shxx.get("shqzt");//获取开始审核时的数据状态，用于撤销时审核状态的回滚，优化撤销时默认状态为未审核中的bug
		/*
		 * -------------检测是否可以撤消审核----------- 
		 * 先决条件：先确定自己最后审批给出的状态 撤消检测：
		 * 
		 * ①不通过 
		 * ② 通过，下一级未审核 
		 * ③ 退回 ，不能撤消、抛异常
		 */
		String lastShzt = dao.getLastShzt(shr, ywid, gwid);
		String nextSpgw = dao.getNextGwid(shlc, gwid);

		boolean isFhtj = false;
		if (SH_BTG.equals(lastShzt)) {
			// 不通过可以直接撤消
			isFhtj = true;
		} else if (SH_TG.equals(lastShzt)) {
			// 删除参数岗位的下一级待审数据，删除成功可撤消否则不能。
			
			isFhtj = dao.delNextDsjl(ywid, nextSpgw);
		} else if (SH_TH.equals(lastShzt)) {
			// 退回状态不允许
			throw new SystemException(MessageKey.SYS_CANCEL_TH);
		} else {
			// 不知名状态不被允许
			throw new SystemException(MessageKey.SYS_AUD_CANCEL_FAIL);
		}

		// 符合撤消条件--更新参数岗位的状态为撤消、增加参数岗位待审数据
		if (isFhtj || StringUtils.isNull(nextSpgw)) {
			
				// 更新审核状态，增加参数岗位待审数据
			if (dao.updateShzt(shid, shqzt) ) {
				
//				// 我的待办回滚
//				MyJobsManager manager = new MyJobsImpl();
//				manager.cancelJob(ywid, gwid);
				
				// 判断是否最后一级撤销
				if (StringUtils.isNull(nextSpgw) || SH_BTG.equals(lastShzt)) {
					return CANCLE_FLG_SUCCESS_ZHYJ;
				}else {
					return CANCLE_FLG_SUCCESS;
				}
			}
		} else {
			throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
		}
		return CANCLE_FLG_ERROR;
	}

	
	/**
	 * @描述:删除审核记录
	 * @作者：zhangjw
	 * @日期：2013-7-1 下午03:47:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid 业务ID
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean deleteShjl(String ywid) throws Exception {
		return dao.delelteShjl(ywid);
	}


	
	
	/*
	      描述: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#getKthSpgw(java.lang.String, java.lang.String)
	 */
	
	public List<HashMap<String, String>> getKthSpgw(String splc, String gwid) {
		return dao.getKthSpgw(splc, gwid);
	}
	
	public HashMap<String, String> getShxxByCondition(String ywid,String gwid) {
		return dao.getShxxByCondition(ywid,gwid);
	}
	
	public boolean splcsfwc(String ywid){
		List<HashMap<String, String>> l = dao.splcsfwc(ywid);
		if(l!=null&&l.size()>0){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#getShyjList(xgxt.form.User, java.lang.String)
	 */
	
	public List<HashMap<String, String>> getShyjList(User user, String gnid) {
		
		return dao.getShyjList(user, gnid);
	}
	
	
	
	/*
	 * 
	      描述: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#saveCyyj(xgxt.form.User, java.lang.String, java.lang.String[])
	 */
	public boolean saveCyyj(User user,String gnid,String[] shyj){
		
		if (shyj == null || shyj.length == 0 || StringUtil.isNull(gnid))
			return false;
		
		
		try {
			boolean b = dao.delCyyj(user, gnid);
			
			if (b){
				return dao.saveCyyj(user, gnid, shyj);
			}
			
			return b;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @描述:根据业务id获取对应的各岗位级别最终通过时的审核意见列表
	 * 		 列表顺序按岗位级别从先到后，每个HashMap包含shr,shsj,shyj,shrmc
	 * 		 通常用于下载登记表，方便获取一级审核信息，二级审核信息...
	 *		 这里返回的list的默认大小>=7，根据业务需求取相应级别信息，如果级别不存在，
	 *		则依然可以取到HashMap，但其内容为empty（HashMap不为NULL，无需自行初始化对象）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月16日 上午10:46:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getShyjListByYwid(String ywid){
		
		List<HashMap<String,String>> list = dao.getShyjListByYwid(ywid);
		for(int i=list.size();i<7;i++){
			list.add(new HashMap<String,String>());
		}
		return list;
	}
	
  public synchronized boolean runSubmitBatchNotCommit(String[] ywids, String shlc,String[] xhs,String tzlj,String tzljsq) throws Exception {
	  //查询第一级岗位ID
	    String firstGwid = dao.getFirstGwid(shlc);
	    List<String[]> paraList = new ArrayList<String[]>();
		//判断是否已插入审核状态表,避免产生重复数据
		for (int i = 0; i < ywids.length; i++) {
			if(dao.getExistForShzt(ywids[i])){
				//重复提交
				throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
			}
			paraList.add(new String[]{ywids[i],firstGwid,shlc,xhs[i],tzlj,tzljsq});
		}
		
		
		
		//保存第一级待审节点
		return dao.insertDbjdBatchNotCommit(paraList);
	}

	/**
	 *  根据splc查询模块名称.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-12 19:07
	 * @param splc
	 * @return java.lang.String
	 * @throw
	 */
	public String getSsmkBySplc(String splc){

		return dao.getSsmkBySplc(splc);
	}

	/**
	 *  根据gwid，申请人xh查询审核人职工号列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-12 19:09
	 * @param gwid
	 * @param sqr
	 * @return java.util.List<xgxt.form.User>
	 * @throw SQLException
	 */
	public List<String> getShrListByGwidAndSqr(String gwid,String sqr) throws SQLException {

		return dao.getShrListByGwidAndSqr(gwid,sqr);
	}

	/**
	 *  极光推送：向审核人推送待办消息.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-13 15:12
	 * @param gwid
	 * @param sqr
	 * @return void
	 * @throw SQLException
	 */
	public void sendDshMsg(String gwid,String sqr,String splc,boolean result) throws SQLException {

		//send jiguang   message
		if (result && "1".equalsIgnoreCase(Base.getInitProperties().get("isOpen"))) {//open
			List<String> zghList = getShrListByGwidAndSqr(gwid,sqr);
			String mkmc = getSsmkBySplc(splc);
			mkmc = StringUtils.isNull(mkmc)?"未知模块":mkmc;

			JgxxtsService js = new JgxxtsService();
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("content", "您收到一条关于'"+mkmc+"'的待审核消息");
			paramMap.put("newsCount", "1");
			paramMap.put("webUrl", Base.getInitProperties().get("dbdz"));
			paramMap.put("webName", "学工站内信");
			paramMap.put("tsry", sqr);

			for (String account : zghList) {
				paramMap.put("account", account);
				String msg = js.sendMsg(paramMap);
			}

		}
	}
}
