/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-25 ����4:14:25 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:����Ա��ѵ���
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-25 ����4:14:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class FdypxXmshService extends SuperServiceImpl<FdypxXmshForm, FdypxXmshDAO> {
	
	private FdypxXmshDAO dao = new FdypxXmshDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	public FdypxXmshService() {
		// TODO �Զ����ɷ������
		super.setDao(dao);
	}
	/**
	 * @����:����Ա��ѵ���
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-26 ����2:10:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean fdypxsh(FdypxXmshForm form,User user) throws Exception{
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShzt());
		model.setGwid(form.getGwid());//
		model.setYwid(form.getSqid());
		model.setThgw(form.getThgw());
		
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getFbr());
		model.setTzlj("szdw_fdypxxmsh.do?method=fdypxxmList");
		model.setTzljsq("szdw_fdypxxmsq.do?method=fdypxxmsqList");
		
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			FdypxXmshForm upForm = new FdypxXmshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			if(zhzt.equals(Constants.OPEN)){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public boolean newCancelSh(FdypxXmshForm model){
		boolean resultFdyrzsq = false;
		try {
			model.setShzt(Constants.YW_SHZ);
			//�����ճ���Ϊ��Ϣά��
			resultFdyrzsq = dao.updateFdyrzsq(model)>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultFdyrzsq;
	}
	
	
	/**
	 * @throws Exception  
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-29 ����04:06:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(FdypxXmshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			FdypxXmshForm model = new FdypxXmshForm();
			model.setSplc(splcs[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setFbr(xhs[i]);
			
			boolean isSuccess = fdypxsh(model, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
		
	}
	
	/**
	 * 
	 * @����:��ý����Ϣ���������Լ�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-24 ����05:05:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqr
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSqjg(String sqid){
		return dao.getSqjg(sqid);
	}
	
}
