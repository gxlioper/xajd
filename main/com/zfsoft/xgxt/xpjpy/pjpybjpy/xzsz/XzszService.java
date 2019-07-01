package com.zfsoft.xgxt.xpjpy.pjpybjpy.xzsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XzszService extends SuperServiceImpl<XzszForm, XzszDao> {

	private XzszDao dao = new XzszDao();

	public XzszService() {
		super.setDao(dao);
	}
	/**
	 * ���������༶����С������
	 */
	public List<HashMap<String, String>> getAddXzszList(XzszForm t, User user) throws Exception {
		return dao.getAddXzszList(t, user);
	}
	
	/**
	 * �������������༶����С������
	 */
	public boolean addXzszBc(String[] xhs, String bjdm, User user) throws Exception {
		boolean rs = true;
		for (String xh : xhs) {
			XzszForm model = new XzszForm();
			model.setBjdm(bjdm);
			model.setXh(xh);
			model.setSfxsdb("0");
			model.setTjr(user.getUserName());
			model.setTjsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setTjzt("0");
			if(rs){
				rs = super.runInsert(model);
			}
		}
		if(rs){
			rs = dao.sftjNotAll(bjdm);
		}
		return rs;
	}
	/**
	 * �޸������༶����С������
	 */
	public List<HashMap<String, String>> getUpdateXzszList(XzszForm t, User user) throws Exception {
		return dao.getUpdateXzszList(t, user);
	}
	
	/**
	 * ���������༶���ύ״̬
	 */
	public boolean sftjNotAll(String bjdm) throws Exception {
		return dao.sftjNotAll(bjdm);
	}
	/**
	 * �ύ�����༶
	 */
	public boolean sftjYesAll(String bjdm, User user) throws Exception {
		return dao.sftjYesAll(bjdm, user);
	}
	/**
	 * ��������ѧ������
	 */
	public boolean xsdbBc(String[] guids, String bjdm) throws Exception {
		return dao.xsdbBc(guids, bjdm);
	}
	/**
	 * ɾ��ѧ������
	 */
	public int xzszDel(String[] guids) throws Exception {
		boolean rs = dao.delBjpyxzpy(guids);
		if(rs){
			int num = dao.runDelete(guids);
			return num;
		}
		return 0;
	}
	
}
