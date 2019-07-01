package com.zfsoft.xgxt.pjpy.jtpj.jtpjsq;

/**
 * @部门:学工产品事业部
 * @日期：2014-4-28 上午10:54:14 
 */
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-4-28 上午10:54:14
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjSqService extends
		SuperServiceImplExtend<JtpjSqForm, JtpjSqDao> {
	JtpjSqDao jsd = new JtpjSqDao();
	private final String SQRLX_LS = "1";
	private final String SQRLX_XS = "0";
	private ShlcInterface shlc = new CommShlcImpl();

	public JtpjSqService() {
		this.setDao(jsd);
	}

	public boolean submitRecord(String pkValue, String lcid, String xh)
			throws Exception {
		return shlc.runSubmit(pkValue, lcid, xh, "jtpjshbase.do",
				"jtpjsqbase.do");
	}

	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	/**
	 * 
	 * @描述: 获取班级寝室信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-6 下午02:44:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForQsxx(JtpjSqForm t,
			String bjdm) throws Exception {
		return jsd.getPageListForQsxx(t, bjdm);
	}
	
	public List<HashMap<String, String>> getQsxsxxList(JtpjSqForm t) throws Exception {
		return jsd.getQsxsxxList(t);
	}

	/**
	 * 
	 * @描述: 获取申请人类型
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午04:17:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userTpye
	 * @return String 返回类型
	 */
	public String getSqrlx(String userTpye) {
		if ("stu".equals(userTpye)) {
			return SQRLX_XS;
		}
		return SQRLX_LS;
	}

	/**
	 * 
	 * @描述: 保存
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 上午09:42:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean save(JtpjSqForm myForm) throws Exception {
		myForm.setSqsj(GetTime.getNowTime4());
		myForm.setSqxn(Base.currXn);
		myForm.setSqxq(Base.currXq);
		myForm.setShzt(Constants.YW_WTJ);
		return runInsert(myForm);
	}

	/**
	 * 
	 * @描述: 修改
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 上午09:42:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean update(JtpjSqForm myForm) throws Exception {
		myForm.setSqsj(GetTime.getNowTime4());
		return runUpdate(myForm);
	}

	/**
	 * 
	 * @描述: 班级人数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午11:30:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return String 返回类型
	 */
	public String getBjrs(String bjdm) {
		return jsd.getBjrs(bjdm);
	}

	/**
	 * 
	 * @描述: 班级寝室数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午11:29:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return String 返回类型
	 */
	public String getQss(String bjdm) {
		return jsd.getQss(bjdm);
	}

	/**
	 * 
	 * @描述: 班级班主任信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午11:29:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getBzrxx(String bjdm) {
		return jsd.getBzrxx(bjdm);
	}

	/**
	 * 
	 * @描述: 班级辅导员信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午11:29:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getFdyxx(String bjdm) {
		return jsd.getFdyxx(bjdm);
	}

	/**
	 * 
	 * @描述: 学院人数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午01:41:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return String 返回类型
	 */
	public String getXyrs(String xydm) {
		return jsd.getXyrs(xydm);
	}

	/**
	 * 
	 * @描述: 学院寝室数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午01:41:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return String 返回类型
	 */
	public String getXyQss(String xydm) {
		return jsd.getXyQss(xydm);
	}

	/**
	 * @return the jsd
	 */
	public JtpjSqDao getJsd() {
		return jsd;
	}

	/**
	 * @param jsd要设置的
	 *            jsd
	 */
	public void setJsd(JtpjSqDao jsd) {
		this.jsd = jsd;
	}

	/** 
	 * @描述:验证是否可提交
	 * @作者：qlm
	 * @日期：2014-5-26 上午11:18:56
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkSfktj(JtpjSqForm model) {
		String num = dao.checkSfktj(model.getJxid());
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @描述: 打印Word登记表（上海电机学院）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-4 上午11:30:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 */
	public HashMap<String, String> getDjbInfo(String sqid) {
		return dao.getDjbInfo(sqid);
	}
	
	public HashMap<String, String> getDjb(String sqid) {
		return dao.getDjb(sqid);
	}
	
	public String getBjmc(String bjdm){
		return dao.getBjmc(bjdm);
	}
	
	/**
	 * 
	 * @描述:检验是否不存在申请记录，不存在返回true
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-16 上午11:20:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxid
	 * @param jtpjid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExists(String jxid,String pjjtid,String pjjtmc,String sqid,String flag){
		return dao.checkIsNotExists(jxid, pjjtid,pjjtmc,sqid,flag);
	}
	
	/**
	 * 
	 * @描述: 获取以班级为评奖单位的评奖集体其他单位信息（学院，年级等）
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-17 上午11:10:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjmc
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getBjxgxx(String bjmc){
		return dao.getBjxgxx(bjmc);
	}

	public HashMap<String,String> getjtpjInfo(String sqid) {
		return dao.getjtpjInfo(sqid);
	}

	public List<HashMap<String,String>> getbjgbInfo(String bjdm) {
		return dao.getbjgbInfo(bjdm);
	}
}
