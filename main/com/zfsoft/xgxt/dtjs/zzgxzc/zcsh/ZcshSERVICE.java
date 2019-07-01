/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-10 ����04:55:48 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsh;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.exception.SystemException;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-2-10 ����04:55:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcshSERVICE extends SuperServiceImpl<ZcshForm,ZcshDAO> {
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����11:02:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(ZcshForm form, User user) throws Exception {
		//����˽��Ϊͨ��ʱ������sqb�ֶ�
		if("1".equals(form.getShjg())){
			dao.runUpdateNotCommit(form);
		}
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("dtjs_dzzgxsh.do");
		model.setTzljsq("dtjs_dzzgxsq.do");
		boolean reuslt = false;
		String zhzt = shlc.runAuditingNotCommit(model);
		ZcshForm  shForm = new ZcshForm();
		shForm.setSqid(form.getSqid());
		shForm.setShzt(zhzt);
		reuslt = dao.runUpdateNotCommit(shForm, form.getSqid());
		// ���浽�����
		if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
			ZcshForm zcshForm = this.getModel(form);
			form.setSqsj(zcshForm.getSqsj());
			form.setSqr(zcshForm.getSqr());
			//���һ��ͨ����Ҫ��֤��д�Ľ����ű���Ƿ��Ѵ��ڽ������
			if(!dao.isNotJsxInJgb(form.getJsxbh())){
				throw new SystemException(MessageKey.DTJS_ZZGXZC_XXJG_JSXBH_REPEAT);
			}
			if(!dao.isNotExistInjgb(form.getXh())){
				dao.delJgByXh(form.getXh());
			}
			reuslt = dao.saveZcJg(form);
		}
		return reuslt;
	}
	
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����02:06:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(ZcshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			// ɾ��������е�������
			dao.delJg(myForm.getSqid());
		
		}
		return result;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����02:21:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param shForm
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cxshnew(String ywid, ZcshForm shForm, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		shForm.setShzt(shzt);
		String cancelFlag = service.runCancelNew(user.getUserName(), shForm.getShid(), shForm.getShlc());
		dao.runUpdate(shForm, shForm.getSqid());
		return cancelFlag;

	}


	/** 
	 * @����:�ж��Ƿ���ʾ�����ű�ţ���˸�λΪ�����һ����ʾ������������ֻ��һ����λ�����һ����ʾ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��14�� ����10:35:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isJsxbhShow(ZcshForm zcshform) {
		boolean r = false;
		List<HashMap<String,String>> splcgwList = dao.getSplcgwList(zcshform.getSplcid());
		if(splcgwList.size()==1){
			r = true;
		}else{
			String spgw=splcgwList.get(0).get("spgw");
			r = !zcshform.getGwid().equalsIgnoreCase(spgw);
		}
		return r;
	}
}
