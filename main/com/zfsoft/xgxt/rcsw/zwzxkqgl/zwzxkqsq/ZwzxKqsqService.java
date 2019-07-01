/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:37:50 
 */
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.jcsz.JcszDao;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgDao;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午02:37:50
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZwzxKqsqService extends SuperServiceImpl<ZwzxKqsqForm, ZwzxKqsqDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SJZT = "0";// 审核状态不为1的数据状态为0
	private ShlcInterface shlc = new CommShlcImpl();
	private JcszDao jcszDao = new JcszDao();
	private ZwzxKqjgDao kqjgDao = new ZwzxKqjgDao();
	private ZwzxKqsqDao dao = new ZwzxKqsqDao();

	/**
	 * 
	 * @描述:判断是否存在填写记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午04:29:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean isHaveSqJl(ZwzxKqsqForm model, String czlx) throws Exception {
		return dao.isHaveSqJl(model, czlx);
	}

	/**
	 * 
	 * @描述:获取一条申请记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午06:43:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 *             ZwzxKqsqForm 返回类型
	 * @throws
	 */
	public ZwzxKqsqForm getKqsq(ZwzxKqsqForm t) throws Exception {
		return dao.getKqsq(t);
	}

	/**
	 * 
	 * @描述:考勤填写申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午04:42:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveKqsq(ZwzxKqsqForm model, List<ZwzxKqsqForm> qqxxList) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = jcszDao.getModel().getSplc();
		model.setSqid(sqid);
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		// 有审批流的情况设定初始值
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		// 保存申请信息
		boolean result = dao.runInsert(model);
		// 保存审核信息
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getBjdm(), "rcsw_zwzxkq_kqsh.do", "rcsw_zwzxkq_kqsq.do");
			}
		}
		return qqxsPlbc(model, qqxxList);
	}
	/**
	 * 
	 * @描述:申请修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午07:21:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveEditKqsq(ZwzxKqsqForm model, List<ZwzxKqsqForm> qqxxList) throws Exception {
		boolean result = false;
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			String splc = jcszDao.getModel().getSplc();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getBjdm(), "rcsw_zwzxkq_kqsh.do", "rcsw_zwzxkq_kqsq.do");
			}
		}
		else {
			result = runUpdate(model);
			
		}
		//删除缺勤学生信息，再插入
		result = kqjgDao.delQqxs(model.getSqid());
		return qqxsPlbc(model, qqxxList);
	}
	/**
	 * 
	 * @描述:申请提交
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午08:04:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitKqsq(HttpServletRequest request, ZwzxKqsqForm model) throws Exception {
		boolean result = false;
			String values = request.getParameter("values");
			model.setSqid(values);
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			String splc = jcszDao.getModel().getSplc();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getBjdm(), "rcsw_zwzxkq_kqsh.do", "rcsw_zwzxkq_kqsq.do");
			}
			return result;
	}
	
	/**
	 * 
	 * @描述:学生考勤信息批量保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午07:18:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws SQLException boolean 返回类型
	 * @throws
	 */
	private boolean qqxsPlbc(ZwzxKqsqForm model, List<ZwzxKqsqForm> qqxxList) throws SQLException {
		List<String[]> qqxsxxList = new ArrayList<String[]>();
		String[] qqxsxx = null;
		for (ZwzxKqsqForm kqsqForm : qqxxList) {
			qqxsxx = new String[16];
			qqxsxx[0] = model.getSqid();
			qqxsxx[1] = kqsqForm.getXh();
			qqxsxx[2] = model.getXn();
			qqxsxx[3] = model.getXq();
			qqxsxx[4] = model.getSqsj();
			qqxsxx[5] = model.getCclxdm();
			qqxsxx[6] = kqsqForm.getQqlxdm();
			qqxsxx[7] = kqsqForm.getKkjs();
			qqxsxx[8] = model.getBjdm();
			qqxsxx[9] = model.getCcrq();
			qqxsxx[10] = model.getJlf();
			qqxsxx[11] = model.getJlr();
			qqxsxx[12] = kqsqForm.getYlzd1();
			qqxsxx[13] = "";
			qqxsxx[14] = "";
			qqxsxx[15] = SJZT;
			qqxsxxList.add(qqxsxx);
		}
		return kqjgDao.qqxsPlbc(qqxsxxList);
	}
	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 上午08:45:01
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
	 * 
	 * @描述: 苏州旅游实时计算应到人数
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-10 上午10:40:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public String getYdrsSzly(String bjdm){
		return dao.getYdrsSzly(bjdm);
	}

}
