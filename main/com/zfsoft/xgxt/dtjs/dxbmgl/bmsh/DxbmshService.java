package com.zfsoft.xgxt.dtjs.dxbmgl.bmsh;

import java.util.HashMap;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @������������У�������service
 * @author������ ��1346��
 * @date��2017-11-1 ����03:37:56 
 */
public class DxbmshService extends SuperServiceImpl<DxbmshForm, DxbmshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	public boolean saveSh(DxbmshForm form, User user) {
		//���������
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShzt());
		model.setThgw(form.getThgw());
		model.setGwid(form.getGwid());//
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("dtxxshbase.do");
		model.setTzljsq("dtxxsqbase.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			reuslt = dao.updateSqxx(form.getSqid(), zhzt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	/** 
	 * @������������������id��ȡ��Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-13 ����11:20:37 
	 * @param qsid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXspxBySqid(String qsid) throws Exception {
		return dao.getXspxBySqid(qsid);
	}
}
