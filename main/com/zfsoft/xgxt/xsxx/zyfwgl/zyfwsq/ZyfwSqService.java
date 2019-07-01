/**
 * @部门:学工产品事业部
 * @日期：2017年5月4日 下午2:22:28 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务申请Service
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月4日 下午2:22:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwSqService extends SuperServiceImpl<ZyfwSqForm,ZyfwSqDao>{
	
	private ShlcInterface shlc = new CommShlcImpl();
	ZyfwSqDao zyfwSqDao = new ZyfwSqDao();

	/** 
	 * @描述:判断当前时间是否有申请记录（包含结果表）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午3:59:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwSqForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isRepeat(ZyfwSqForm zyfwSqForm) {
		
		return dao.isRepeat(zyfwSqForm);
	}

	/**
	 * @描述:志愿服务申请（新增）的保存
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午4:13:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwSqForm
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean zyfwSqSaveForAdd(ZyfwSqForm zyfwSqForm) throws Exception {
		
		String fwid = UniqID.getInstance().getUniqIDHash();
		zyfwSqForm.setFwid(fwid);
		//获取基础设置中设置的审核流程
		String splc = zyfwSqDao.getShlcID();
		zyfwSqForm.setSplc(splc);
		if ("submit".equals(zyfwSqForm.getType())) {
			zyfwSqForm.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			zyfwSqForm.setShzt(Constants.YW_WTJ);// 未提交
		}
		// 保存申请信息
		boolean result = dao.runInsert(zyfwSqForm);
		// 保存审核信息
		if ("submit".equals(zyfwSqForm.getType())) {
			if (result) {
				result = shlc.runSubmit(fwid, splc, zyfwSqForm.getXh(), 
						"xsxx_zyfwgl_sh.do?method=zyfwShList", "xsxx_zyfwgl_sq.do?method=zyfwSqList");
			}
		}
		return result;
	}

	/**
	 * @描述:志愿服务申请（修改）的保存
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午6:02:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwSqForm
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean zyfwSqSaveForEdit(ZyfwSqForm zyfwSqForm) throws Exception {
		
		boolean result = false;
		// 如果提交，插入审核状态
		if ("submit".equalsIgnoreCase(zyfwSqForm.getType())) {
			zyfwSqForm.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID();
			zyfwSqForm.setSplc(splc);
			result = runUpdate(zyfwSqForm);
			if (result) {
				result = shlc.runSubmit(zyfwSqForm.getFwid(), splc,zyfwSqForm.getXh() , 
						"xsxx_zyfwgl_sh.do?method=zyfwShList", "xsxx_zyfwgl_sq.do?method=zyfwSqList");
			}
		} else {
			result = runUpdate(zyfwSqForm);
		}
		return result;
	}

	/**
	 * @描述：志愿服务申请的提交
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午10:47:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwSqForm
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean zyfwSqSubmit(ZyfwSqForm zyfwSqForm) throws Exception {
		
		boolean result = false;
		zyfwSqForm.setShzt(Constants.YW_SHZ);// 审核中
		String splc = dao.getShlcID();
		zyfwSqForm.setSplc(splc);
		result = runUpdate(zyfwSqForm);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(zyfwSqForm.getFwid(), splc,zyfwSqForm.getXh() , 
					"xsxx_zyfwgl_sh.do?method=zyfwShList", "xsxx_zyfwgl_sq.do?method=zyfwSqList");
		}
		return result;
	}

	/**
	 * @描述:志愿服务申请的撤销
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午11:52:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fwid
	 * @param lcid
	 * @return
	 * boolean 返回类型 
	 * @throws Exception  
	 */
	public boolean zyfwSqCancel(String fwid, String lcid) throws Exception {
		
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = shlc.firstStepCancle(fwid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			ZyfwSqForm zyfwSqForm = new ZyfwSqForm();
			zyfwSqForm.setFwid(fwid);
			zyfwSqForm.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(fwid)) > 0) {
				zyfwSqForm.setShzt(Constants.YW_YTH);
			} else {
				zyfwSqForm.setShzt(Constants.YW_WTJ);
			}
			result = this.runUpdate(zyfwSqForm);
		}
		return result;
	}
	
	/**
	 * @描述:重写：查询一条申请详细信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午11:52:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fwid
	 * @param lcid
	 * @return
	 * boolean 返回类型 
	 * @throws Exception  
	 */
	public ZyfwSqForm getModel(String fwid) throws Exception{
		return dao.getModel(fwid);
	}

}
