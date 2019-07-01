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
	 * ��ȡ�����༶����С�������Ϣ
	 */
	public HashMap<String,String> queryBjpyxzdb(String xh) throws Exception {
		return dao.queryBjpyxzdb(xh);
	}
	/**
	 * �鿴�༶������
	 */
	public List<HashMap<String, String>> jgcxView(JgcxForm t, User user) throws Exception {
		return dao.jgcxView(t, user);
	}
	
	/**
	 * �ύ�����༶������
	 */
	public boolean jgcxTj(JgcxForm jgcxForm, SqshModel pjpybjpyModel, User user) throws Exception {
		String sqid = pjpybjpyModel.getSqid();
		String xn = pjpybjpyModel.getXn();
		String xq = pjpybjpyModel.getXq();
		String xmdm = pjpybjpyModel.getXmdm();
		String sqr = pjpybjpyModel.getXh();
		String pyshzt = jgcxForm.getShzt(); // ������
		// ���������༶�����
		boolean rs = dao.tjBjpyxzpy(xn, xq, xmdm, sqr);
		if(rs){
			// ���½����ѯ��
			JgcxForm model = new JgcxForm();
			model.setSqid(jgcxForm.getSqid());
			model.setXn(xn);
			model.setXq(xq);
			model.setXmdm(xmdm);
			model.setSqr(sqr);
			model.setBjpydb(user.getUserName());
			model.setYlzd1(jgcxForm.getBjpyxzcyxms()); // ����С���Ա
			model.setYlzd2(jgcxForm.getBjpyxzdbxms()); // ����С�����
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
				if("1".equals(pyshzt)){ // ��������ͬ�⡿
					// �������״̬��
					rs = shlc.runSubmit(sqid, lcid, pjpybjpyModel.getXh(),"pj_jxsh.do", "pj_pjxmsq.do");
				}
				if(rs){
					SqshService pjpybjpyService = new SqshService();
					//����ҵ��״̬Ϊ'�����'
					SqshModel modelNew = new SqshModel();
					modelNew.setSqid(sqid);
					if("1".equals(pyshzt)){ // ��������ͬ�⡿
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
	 * ���������༶������
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
			// �������״̬��
			rs = shlc.firstStepCancle(sqid, pjpybjpyModel.getSplc());
		}
		if(rs){
			//����ҵ��״̬Ϊ'δ�ύ'
			SqshService pjpybjpyService = new SqshService();
			SqshModel modelNew = new SqshModel();
			modelNew.setSqid(sqid);
			modelNew.setShzt(Constants.YW_BJPYZ);
			pjpybjpyService.updateModel(modelNew);
			if(rs){
				// ���������༶�����
				rs = dao.cxBjpyxzpy(xn, xq, xmdm, sqr);
				// ���½����ѯ��
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
