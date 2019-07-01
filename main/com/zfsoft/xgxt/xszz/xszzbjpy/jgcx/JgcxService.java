package com.zfsoft.xgxt.xszz.xszzbjpy.jgcx;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyService;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;

public class JgcxService extends SuperServiceImpl<JgcxForm, JgcxDao> {

	private JgcxDao dao = new JgcxDao();
	private ShlcInterface shlc = new CommShlcImpl();

	public JgcxService() {
		super.setDao(dao);
	}
	
	/**
	 * 获取资助班级评议小组代表信息
	 */
	public HashMap<String,String> queryBjpyxzdb(String xh) throws Exception {
		return dao.queryBjpyxzdb(xh);
	}
	
	/**
	 * 查看班级评议结果
	 */
	public List<HashMap<String, String>> jgcxView(JgcxForm t, User user) throws Exception {
		return dao.jgcxView(t, user);
	}
	
	/**
	 * 提交资助班级评议结果
	 */
	public boolean jgcxTj(JgcxForm jgcxForm, XszzSqshForm xszzbjpyModel, User user) throws Exception {
		String guid = xszzbjpyModel.getGuid();
		String xn = xszzbjpyModel.getXn();
		String xq = xszzbjpyModel.getXq();
		String xmdm = xszzbjpyModel.getXmdm();
		String sqr = xszzbjpyModel.getXh();
		String pyshzt = jgcxForm.getShzt(); // 评议结果
		// 更新资助班级评议表
		boolean rs = dao.tjBjpyxzpy(xn, xq, xmdm, sqr);
		if(rs){
			// 更新结果查询表
			JgcxForm model = new JgcxForm();
			model.setSqid(jgcxForm.getSqid());
			model.setXn(xn);
			model.setXq(xq);
			model.setXmdm(xmdm);
			model.setSqr(sqr);
			model.setBjpydb(user.getUserName());
			model.setYlzd1(jgcxForm.getBjpyxzcyxms()); // 评议小组成员
			model.setYlzd2(jgcxForm.getBjpyxzdbxms()); // 评议小组代表
			model.setShzt(pyshzt);
			model.setPyjg(jgcxForm.getPyjg());
			model.setPyhsj(jgcxForm.getPyhsj());
			model.setPyhdd(jgcxForm.getPyhdd());
			model.setPyyj(jgcxForm.getPyyj());
			model.setTjzt("1");
			model.setTjsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			JgcxForm jgcxOldForm = super.getModel(model);
			if(jgcxOldForm != null){
				rs = super.runUpdate(model);
			}else{
				rs = super.runInsert(model);
			}
			if(rs){
				String lcid = xszzbjpyModel.getShlc();
				if("1".equals(pyshzt)){ // 评议结果【同意】
					// 更新审核状态表
					rs = shlc.runSubmit(guid, lcid, xszzbjpyModel.getXh(),"xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
				}
				if(rs){
					XszzSqshService xszzbjpyService = new XszzSqshService();
					//更新业务状态为'审核中'
					XszzSqshForm modelNew = new XszzSqshForm();
					modelNew.setGuid(guid);
					if("1".equals(pyshzt)){ // 评议结果【同意】
						modelNew.setShzt(Constants.YW_SHZ);
					}else{
						modelNew.setShzt(Constants.YW_BJPYBTG);
					}
					xszzbjpyService.updateModel(modelNew);
				}
			}
		}
		return rs;
	}
	/**
	 * 撤销资助班级评议结果
	 */
	public boolean jgcxCx(XszzSqshForm xszzbjpyModel) throws Exception {
		String xszzbjpyShzt = xszzbjpyModel.getShzt();
		String guid = xszzbjpyModel.getGuid();
		String xn = xszzbjpyModel.getXn();
		String xq = xszzbjpyModel.getXq();
		String xmdm = xszzbjpyModel.getXmdm();
		String sqr = xszzbjpyModel.getXh();
		boolean rs = true;
		if(!Constants.YW_BJPYBTG.equals(xszzbjpyShzt)){
			// 更新审核状态表
			rs = shlc.firstStepCancle(guid, xszzbjpyModel.getShlc());
		}
		// 更新资助班级评议表
		if(rs){
			//更新业务状态为'未提交'
			XszzSqshService xszzbjpyService = new XszzSqshService();
			XszzSqshForm modelNew = new XszzSqshForm();
			modelNew.setGuid(guid);
			modelNew.setShzt(Constants.YW_BJPYZ);
			xszzbjpyService.updateModel(modelNew);
			if(rs){
				rs = dao.cxBjpyxzpy(xn, xq, xmdm, sqr);
				// 更新结果查询表
				JgcxForm model = new JgcxForm();
				model.setSqid(guid);
				model.setTjzt("0");
				model.setTjsj(" ");
				model.setShzt(" ");
				super.runUpdate(model);
			}
		}
		return rs;
	}
	
}
