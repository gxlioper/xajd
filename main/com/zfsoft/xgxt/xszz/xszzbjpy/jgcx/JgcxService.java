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
	public boolean jgcxTj(JgcxForm jgcxForm, XszzSqshForm xszzbjpyModel, User user) throws Exception {
		String guid = xszzbjpyModel.getGuid();
		String xn = xszzbjpyModel.getXn();
		String xq = xszzbjpyModel.getXq();
		String xmdm = xszzbjpyModel.getXmdm();
		String sqr = xszzbjpyModel.getXh();
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
				String lcid = xszzbjpyModel.getShlc();
				if("1".equals(pyshzt)){ // ��������ͬ�⡿
					// �������״̬��
					rs = shlc.runSubmit(guid, lcid, xszzbjpyModel.getXh(),"xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
				}
				if(rs){
					XszzSqshService xszzbjpyService = new XszzSqshService();
					//����ҵ��״̬Ϊ'�����'
					XszzSqshForm modelNew = new XszzSqshForm();
					modelNew.setGuid(guid);
					if("1".equals(pyshzt)){ // ��������ͬ�⡿
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
	 * ���������༶������
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
			// �������״̬��
			rs = shlc.firstStepCancle(guid, xszzbjpyModel.getShlc());
		}
		// ���������༶�����
		if(rs){
			//����ҵ��״̬Ϊ'δ�ύ'
			XszzSqshService xszzbjpyService = new XszzSqshService();
			XszzSqshForm modelNew = new XszzSqshForm();
			modelNew.setGuid(guid);
			modelNew.setShzt(Constants.YW_BJPYZ);
			xszzbjpyService.updateModel(modelNew);
			if(rs){
				rs = dao.cxBjpyxzpy(xn, xq, xmdm, sqr);
				// ���½����ѯ��
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
