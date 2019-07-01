package com.zfsoft.xgxt.jskp.xmsb;


import java.util.HashMap;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 纪实考评
 * @类功能描述: 项目申报
 * @作者： xiaxia[工号:1104]
 * @时间： 2017-7-5 下午04:45:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JskpXmsbService extends SuperServiceImpl<JskpXmsbForm, JskpXmsbDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SJZT = "0";// 审核状态不为1的数据状态为0
	private static final String LCLX_SB="sb";//流程类型：申报
	private ShlcInterface shlc = new CommShlcImpl();
	private CsszDao jcszDao = new CsszDao();
	private JskpXmsbDao dao = new JskpXmsbDao();

	/**
	 * 
	 * @描述:判断间隔周期内是否重复申报
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-25 上午09:49:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public String xmsbCheck(JskpXmsbForm model, User user) throws Exception {
		return dao.xmsbCheck(model, user);
	}

	/**
	 * 
	 * @描述:申报保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-7 上午10:59:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXmsb(JskpXmsbForm model) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = jcszDao.getSplc(LCLX_SB).get("splc");
		model.setSqid(sqid);
		model.setSbsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplcid(splc);
		model.setShzt(Constants.YW_SHZ);// 审核中
		// 保存申请信息
		boolean result = dao.runInsert(model);
		// 保存审核信息
		result = shlc.runSubmit(sqid, splc, model.getXh(), "jskp_xmsh.do", "jskp_xmsb.do");
		return result;
	}
	
	
	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-7 上午10:59:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @描述: 当参数设置为0时，审核列表学号查看（xhLink）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-22 下午04:00:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXxck(String id) throws Exception {
		return dao.getXxck(id);
	}
}
