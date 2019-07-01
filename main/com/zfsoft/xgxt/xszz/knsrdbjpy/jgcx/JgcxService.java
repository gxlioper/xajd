package com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx;

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
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyService;

public class JgcxService extends SuperServiceImpl<JgcxForm, JgcxDao> {

	private JgcxDao dao = new JgcxDao();
	private ShlcInterface shlc = new CommShlcImpl();

	public JgcxService() {
		super.setDao(dao);
	}
	
	/**
	 * 获取班级评议小组代表信息
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
	 * 提交班级评议结果
	 */
	public boolean jgcxTj(JgcxForm jgcxForm, KnsrdbjpyForm knsrdbjpyModel, User user) throws Exception {
		String guid = knsrdbjpyModel.getGuid();
		String xn = knsrdbjpyModel.getXn();
		String xq = knsrdbjpyModel.getXq();
		String sqr = knsrdbjpyModel.getXh();
		String pyjg = jgcxForm.getShzt(); // 评议结果
		// 更新班级评议表
		boolean rs = dao.tjBjpyxzpy(xn, xq, sqr);
		if(rs){
			// 更新结果查询表
			JgcxForm model = new JgcxForm();
			model.setSqid(jgcxForm.getSqid());
			model.setXn(xn);
			model.setXq(xq);
			model.setSqr(sqr);
			model.setBjpydb(user.getUserName());
			model.setYlzd1(jgcxForm.getBjpyxzcyxms()); // 评议小组成员
			model.setYlzd2(jgcxForm.getBjpyxzdbxms()); // 评议小组代表
			model.setShzt(pyjg);
			model.setPddc(jgcxForm.getPddc());
			model.setPyhsj(jgcxForm.getPyhsj());
			model.setPyhdd(jgcxForm.getPyhdd());
			model.setRdly(jgcxForm.getRdly());
			model.setTjzt("1");
			model.setTjsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			JgcxForm jgcxOldForm = super.getModel(model);
			if(jgcxOldForm != null){
				rs = super.runUpdate(model);
			}else{
				rs = super.runInsert(model);
			}
			if(rs){
				String lcid = knsrdbjpyModel.getShlc();
				KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
				KnsjcszbjpyForm jcszModel = jcszService.getModel();
				lcid = jcszModel.getSplc();
				if("1".equals(pyjg)){ // 评议结果【同意】
					// 更新审核状态表
					rs = shlc.runSubmit(guid, lcid, knsrdbjpyModel.getXh(),"xszz_knsrdbjpy_sh.do","xszz_knsrdbjpy_sq.do");
				}
				if(rs){
					KnsrdbjpyService knsrdbjpyService = new KnsrdbjpyService();
					//更新业务状态为'审核中'
					KnsrdbjpyForm modelNew = new KnsrdbjpyForm();
					modelNew.setGuid(guid);
					if("1".equals(pyjg)){ // 评议结果【同意】
						modelNew.setShzt(Constants.YW_SHZ);
					}else{
						modelNew.setShzt(Constants.YW_BJPYBTG);
					}
					modelNew.setShlc(lcid);
					modelNew.setPddc(jgcxForm.getPddc());
					knsrdbjpyService.updateModel(modelNew);
				}
			}
		}
		return rs;
	}
	/**
	 * 撤销班级评议结果
	 */
	public boolean jgcxCx(KnsrdbjpyForm knsrdbjpyModel) throws Exception {
		String knsrdbjpyShzt = knsrdbjpyModel.getShzt();
		String guid = knsrdbjpyModel.getGuid();
		String xn = knsrdbjpyModel.getXn();
		String xq = knsrdbjpyModel.getXq();
		String sqr = knsrdbjpyModel.getXh();
		boolean rs = true;
		if(!Constants.YW_BJPYBTG.equals(knsrdbjpyShzt)){
			// 更新审核状态表
			rs = shlc.firstStepCancle(guid, knsrdbjpyModel.getShlc());
		}
		if(rs){
			//更新业务状态为'未提交'
			KnsrdbjpyService knsrdbjpyService = new KnsrdbjpyService();
			KnsrdbjpyForm modelNew = new KnsrdbjpyForm();
			modelNew.setGuid(guid);
			modelNew.setShzt(Constants.YW_BJPYZ);
			modelNew.setPddc("");
			knsrdbjpyService.updateModel(modelNew);
			if(rs){
				// 更新班级评议表
				rs = dao.cxBjpyxzpy(xn, xq, sqr);
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
	
	public HashMap<String,String> getBjpyTjdc(KnsrdbjpyForm knsrdbjpyModel){
		return dao.getBjpyTjdc(knsrdbjpyModel);
	}
	
}
