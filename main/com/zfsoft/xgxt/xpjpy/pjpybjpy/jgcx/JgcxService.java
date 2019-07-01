package com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshModel;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;

public class JgcxService extends SuperServiceImpl<JgcxForm, JgcxDao> {

	private JgcxDao dao = new JgcxDao();
	private ShlcInterface shlc = new CommShlcImpl();

	public JgcxService() {
		super.setDao(dao);
	}
	
	/**
	 * 获取评奖班级评议小组代表信息
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
	 * 提交评奖班级评议结果
	 */
	public boolean jgcxTj(JgcxForm jgcxForm, SqshModel pjpybjpyModel, User user) throws Exception {
		String sqid = pjpybjpyModel.getSqid();
		String xn = pjpybjpyModel.getXn();
		String xq = pjpybjpyModel.getXq();
		String xmdm = pjpybjpyModel.getXmdm();
		String sqr = pjpybjpyModel.getXh();
		String pyshzt = jgcxForm.getShzt(); // 评议结果
		// 更新评奖班级评议表
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
				String lcid = pjpybjpyModel.getSplc();
				if("1".equals(pyshzt)){ // 评议结果【同意】
					// 更新审核状态表
					rs = shlc.runSubmit(sqid, lcid, pjpybjpyModel.getXh(),"pj_jxsh.do", "pj_pjxmsq.do");
				}
				if(rs){
					SqshService pjpybjpyService = new SqshService();
					//更新业务状态为'审核中'
					SqshModel modelNew = new SqshModel();
					modelNew.setSqid(sqid);
					if("1".equals(pyshzt)){ // 评议结果【同意】
						modelNew.setShzt(Constants.YW_SHZ);
					}else{
						modelNew.setShzt(Constants.YW_BJPYBTG);
					}
					pjpybjpyService.updateModel(modelNew);
				}
			}
		}
		return rs;
	}
	/**
	 * 撤销评奖班级评议结果
	 */
	public boolean jgcxCx(SqshModel pjpybjpyModel) throws Exception {
		String pjpybjpyShzt = pjpybjpyModel.getShzt();
		String sqid = pjpybjpyModel.getSqid();
		String xn = pjpybjpyModel.getXn();
		String xq = pjpybjpyModel.getXq();
		String xmdm = pjpybjpyModel.getXmdm();
		String sqr = pjpybjpyModel.getXh();
		boolean rs = true;
		if(!Constants.YW_BJPYBTG.equals(pjpybjpyShzt)){
			// 更新审核状态表
			rs = shlc.firstStepCancle(sqid, pjpybjpyModel.getSplc());
		}
		if(rs){
			//更新业务状态为'未提交'
			SqshService pjpybjpyService = new SqshService();
			SqshModel modelNew = new SqshModel();
			modelNew.setSqid(sqid);
			modelNew.setShzt(Constants.YW_BJPYZ);
			pjpybjpyService.updateModel(modelNew);
			if(rs){
				// 更新评奖班级评议表
				rs = dao.cxBjpyxzpy(xn, xq, xmdm, sqr);
				// 更新结果查询表
				JgcxForm model = new JgcxForm();
				model.setSqid(sqid);
				model.setTjzt("0");
				model.setTjsj(" ");
				model.setShzt(" ");
				super.runUpdate(model);
			}
		}
		return rs;
	}
	
}
