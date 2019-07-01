package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmjg;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class KycxxmjgService extends SuperServiceImpl<KycxxmjgForm, KycxxmjgDao> {

	private KycxxmjgDao dao = new KycxxmjgDao();
	
	public KycxxmjgService() {
		super.setDao(dao);
	}
	/**
	 * �Ƿ��Ѵ���
	 */
	public boolean checkExistSave(KycxxmjgForm model, User user) throws Exception {
		return dao.checkExistSave(model, user);
	}
	/**
	 * ѧ���б�
	 */
	public List<HashMap<String, String>> getXsxxList(KycxxmjgForm model, User user) throws Exception {
		return dao.getXsxxList(model, user);
	}
	/**
	 * ���ݿ��д�����Ŀ��ȡѧ���б�
	 */
	public List<HashMap<String, String>> getKycxxmcyList(String jgid, User user) throws Exception {
		return dao.getKycxxmcyList(jgid, user);
	}
	/**
	 * ���ӿ��д�����Ŀ���
	 */
	public boolean insertKycxxmjg(KycxxmjgForm model) throws Exception {
		boolean result = super.runInsert(model);
		if(result){
			result = dao.insertKycxxmcy(model.getJgid(),model.getXhArr());
		}
		return result;
	}
	/**
	 * �޸Ŀ��д�����Ŀ���
	 */
	public boolean updateKycxxmjg(KycxxmjgForm model) throws Exception {
		boolean result = true;
		if("0".equals(model.getSjly())){
			result = super.runUpdate(model);
		}
		if(result){
			result = dao.insertKycxxmcy(model.getJgid(),model.getXhArr());
		}
		return result;
	}
	/**
	 * ɾ�����д�����Ŀ���
	 */
	public boolean delKycxxmcy(String[] jgidArr) throws Exception {
		return dao.delKycxxmcy(jgidArr);
	}
	/**
	 * ������Ŀ��ѯ
	 */
	public List<HashMap<String, String>> kycxxmjgManageCx(KycxxmjgForm t, User user) throws Exception {
		return dao.kycxxmjgManageCx(t, user);
	}
	/**
	 * ������Ŀ��ѯ������ҳ��
	 */
	public List<HashMap<String, String>> getAllListCx(KycxxmjgForm t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.kycxxmjgManageCx(t, user);
	}
}
