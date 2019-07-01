/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqService;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: 请假申请service
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DtxxshService extends SuperServiceImpl<DtxxshForm, DtxxshDao> implements Constants{
	DtxxshDao dao = new DtxxshDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	private BaseDbcz dbcz = new BaseDbcz();
	public DtxxshService() {
		this.setDao(dao);
		dbcz.setShPath("dtxxshbase.do");
		dbcz.setGnmkMc("党团信息申请");
		dbcz.setXmmc("党团信息管理");
	}

	@Override
	public DtxxshForm getModel(DtxxshForm t) throws Exception {
		DtxxshForm df = super.getModel(t);
		DtxxsqService ds = new DtxxsqService();
		HashMap<String, String> otherP = ds.getAllProperty(t.getDtxxsqid());
		df.setJdmc(otherP.get("jdmc"));
		// 审核使用
		df.setShid(dao.getShid(t.getDtxxsqid(), t.getGwid()));
		df.setGwid(t.getGwid());
		return df;
	}

	/**
	 * 查询学生个人信息
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetail(xh);
	}

	/**
	 * 
	 * @描述:更新申请信息状态
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-24 下午06:29:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @param dqzt
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean updateSqxx(String sqid, String dqzt) throws Exception {
		/*
		 * DtxxshForm upForm = new DtxxshForm(); upForm.setDtxxsqid(sqid);
		 * upForm.setShzt(dqzt);
		 */
		return dao.updateSqxx(sqid, dqzt);
	}

	/**
	 * 
	 * @描述:审核
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:47:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return boolean 返回类型
	 */
	public boolean saveSh(DtxxshForm form, User user) {
		//审批流相关
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShzt());
		model.setThgw(form.getThgw());
		model.setGwid(form.getGwid());//
		model.setYwid(form.getDtxxsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("dtxxshbase.do");
		model.setTzljsq("dtxxsqbase.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			reuslt = updateSqxx(form.getDtxxsqid(), zhzt);
			// 审核状态为通过de 结果表中保存该条数据
			if (zhzt.equalsIgnoreCase(Constants.SH_TG)) {
				DtxxjgService jgs = new DtxxjgService();
				reuslt=jgs.saveForDtxxsq(form);
			}
			if (reuslt) {
				// 设置代办信息
				dbcz.shPush(form.getDtxxsqid(), form.getSplc());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * 
	 * @描述:撤销审核
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-28 上午09:36:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlc
	 * @param sqid
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean cancel(String shlc, String sqid) throws Exception {
		boolean result = false;
		// 申请数据状态更改
		DtxxsqForm sq=new DtxxsqForm();
		sq.setDtxxsqid(sqid);
		sq.setShzt(Constants.YW_SHZ);
		DtxxsqService sqs=new DtxxsqService();
		dao.deleteDtxxjgForDtxxsqId(sqid);
		DtxxsqForm df= dao.getModel(sqid);
		dbcz.cancel(df.getDtxxsqid(), df.getSplc());
		result = sqs.runUpdate(sq) ? true : false;
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(批量审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-16 上午09:44:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(DtxxshForm t, User user) throws Exception {
		String[] ids = t.getIds();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		//修改bug
		String[] jddms = t.getJddms();
		
		List<String> failXhs = new ArrayList<String>();
		//循环批量审核
		for (int i = 0, n = ids.length; i < n; i++) {
			DtxxshForm model = new DtxxshForm();
			model.setDtxxsqid(ids[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setXh(xhs[i]);
			model.setSplc(splcs[i]);
			model.setJddm(jddms[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	

}
