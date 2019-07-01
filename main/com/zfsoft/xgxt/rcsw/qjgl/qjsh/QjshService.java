/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import com.zfsoft.xgxt.rcsw.qjgl.qjgz.QjgzForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjgz.QjgzService;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgDao;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: 请假申请service
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjshService extends SuperServiceImpl<QjshForm, QjshDao> {
	QjshDao dao = new QjshDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	private BaseDbcz dbcz=new BaseDbcz();
	public QjshService() {
		this.setDao(dao);
		dbcz.setShPath("qjshbase.do");
		dbcz.setGnmkMc("请假申请");
		dbcz.setXmmc("请假管理");
	}

	/**
	 * 
	 * @描述:获取对应的请假规则
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午04:26:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public HashMap<String, String> getSplc(QjshForm model) throws Exception {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("splcid", "无需审核");
		if (StringUtils.isNotNull(model.getSplcid())) {// 已经存在对应流程id，直接返回
			data.put("splcid", model.getSplcid());
			return data;
		}
		// 获取具体对应请假规则
		QjgzService qjgz = new QjgzService();
		List<HashMap<String, String>> list = qjgz.getAllList(new QjgzForm());
		for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			Integer qjtsInt = Integer.parseInt(model.getQjts());
			if (Integer.parseInt(kssj) == qjtsInt
					&& qjtsInt == Integer.parseInt(jssj)) {
				return hm;
			}
			if (Integer.parseInt(kssj) < qjtsInt
					&& qjtsInt <= Integer.parseInt(jssj)) {
				return hm;
			}
		}
		return data;
	}

	@Override
	public QjshForm getModel(QjshForm t) throws Exception {
		String gwid = t.getGwid();
		t = super.getModel(t);
		if(t!=null){
			// 把学期代码转换为学期名称
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
			t.setShid(dao.getShid(t.getQjsqid(),gwid));
		}
		if(Base.xxdm.equals("10695")){//西藏民族大学专用
			QjsqService sqService = new QjsqService();
			t.setJtgjmc(sqService.getJtgjmc(t.getJtgj()));
		}
		return t;
	}

	/**
	 * 
	 * @描述:是否可以进行添加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午07:19:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean isCanAdd(QjshForm qf) {
		return dao.checkQjlx(qf);
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
	 * @描述:审批流程信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-11 下午12:00:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getSplcInfo(QjshForm model) {
		if (StringUtil.isNull(model.getQjsqid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getSplcInfo(model);
	}
	
	public String cancelSh(QjshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String cancelFlg = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplcid());
		//请假申请状态更改
		QjshForm upForm = new QjshForm();
		upForm.setQjsqid(model.getQjsqid());
		upForm.setShzt(Constants.YW_SHZ);
		dao.runUpdate(upForm, model.getQjsqid());
		return cancelFlg;
	}

	/**
	 * @throws Exception 
	 * 
	 * @描述:审核
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:47:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean saveSh(QjshForm form, User user) throws Exception {
		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getQjsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("qjshbase.do");
		model.setTzljsq("qjsqbase.do");
	
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditing(model);
			QjshForm upForm = new QjshForm();
			upForm.setQjsqid(form.getQjsqid());
			upForm.setShzt(zhzt);
			// 审核状态为退回时修改请假申请请假状态
			if (zhzt.equalsIgnoreCase(Constants.SH_TH)){
				upForm.setQjzt(QjsqService._SQZT_CGZT);
			}
			reuslt = dao.runUpdate(upForm, form.getQjsqid());
			// 审核状态为通过的往请假结果表中保存该条数据
			if (zhzt.equalsIgnoreCase(Constants.SH_TG)) {
				// 获取数据库申请数据
				QjshForm data = getModel(form);
				QjjgForm qf = new QjjgForm();
				QjjgDao qjjg = new QjjgDao();
				// 对应属性复制到结果库
				BeanUtils.copyProperties(qf, StringUtils.formatData(data));
				qf.setQjsqid(data.getQjsqid());
				qf.setQjzt(QjsqService._SQZT_CGZT);
				qf.setShzt(Constants.SH_TG);
				qjjg.runInsert(qf);
			}
		
		return reuslt;
	}

	public List<HashMap<String, String>> getQjlx() {
		return dao.getQjlx();
	}

	/**
	 * 
	 * @描述:是否存在对应天数的审核流程
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午06:58:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean isHaveSplcForTs(QjshForm model) throws Exception {
		HashMap<String, String> splc = getSplc(model);
		if (null == splc) {
			return false;
		}
		return true;
	}
	/** 
	 * @描述:最后一级撤销审核
	 * @作者：qilm
	 * @日期：2013-10-10
	 * @param shlc 审核流程ID
	 * @param ssydsqid 申请ID
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(String shlc, String sqid) throws Exception{
		boolean result=false;
		//请假申请状态更改
		QjshForm upForm = new QjshForm();
		upForm.setQjsqid(sqid);
		upForm.setShzt(Constants.YW_SHZ);
		result=dao.runUpdate(upForm, sqid)&&dao.deleteQjjgForQjsqId(sqid)>0?true:false;
		
		upForm=dao.getModel(sqid);
		//设置代办信息
		dbcz.cancel(upForm.getQjsqid(), upForm.getSplcid());
		return result;
	}

	/** 
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-2-27 下午02:08:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public String batchSave(QjshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		
		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			QjshForm Form = new QjshForm();
			Form.setQjsqid(ids[i]);
			
			HashMap<String, String> tmpMap = shlc.getShxxByCondition(ids[i],gwids[i]);
			QjshForm s = dao.getModel(Form);
			
			
			Form.setGwid(gwids[i]);
			Form.setShyj(t.getShyj());
			Form.setShjg(t.getShzt());
			Form.setThgw(t.getThgw());
			Form.setSplcid(tmpMap.get("lcid"));
			Form.setXh(s.getXh());
			Form.setXn(s.getXn());
			Form.setXq(s.getXq());

			boolean isSuccess = saveSh(Form, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
}
