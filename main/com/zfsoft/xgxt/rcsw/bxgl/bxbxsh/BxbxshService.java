/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-2 ����10:29:31 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.bxbxsh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.bxgl.bxbxsq.BxbxSqDao;
import com.zfsoft.xgxt.rcsw.bxgl.bxbxsq.BxbxSqForm;
import com.zfsoft.xgxt.rcsw.bxgl.xsbxbx.XsbxbxDao;
import com.zfsoft.xgxt.rcsw.bxgl.xsbxbx.XsbxbxForm;
import com.zfsoft.xgxt.rcsw.bxgl.xsbxbx.XsbxbxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-4-2 ����10:29:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BxbxshService extends SuperServiceImpl<BxbxshForm,BxbxshDao>{
	private static final String SJZT = "0";// ���״̬��Ϊ1������״̬Ϊ0
	private ShlcInterface shlc = new CommShlcImpl();
	private BxbxshDao dao = new BxbxshDao();
	/**
	 * 
	 * @����:��˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-2 ����02:55:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(BxbxshForm form, User user) {
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		model.setSqrid(user.getUserName());
		model.setTzlj("rcsw_bxgl_bxbxsh.do");
		model.setTzljsq("rcsw_bxgl_bxbxsq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			BxbxshForm bxshForm = new BxbxshForm();
			bxshForm.setSqid(form.getSqid());
			bxshForm.setShzt(zhzt);
			result = dao.runUpdate(bxshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				XsbxbxForm bxbxForm = new XsbxbxForm();
				XsbxbxService bxbxService = new XsbxbxService();
				BxbxSqForm bxbxsqForm = new BxbxSqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(bxbxForm, StringUtils.formatData(bxbxsqForm));
				bxbxForm.setBxid(bxbxsqForm.getSqid());
				bxbxForm.setSjly("1");
				result=bxbxService.runInsert(bxbxForm);
			
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @����:������˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-2 ����03:32:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(BxbxshForm t, User user) {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] splcs = t.getSplcs();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			BxbxshForm model = new BxbxshForm();
			model.setSplc(splcs[i]);
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
		
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	/**
	 * 
	 * @����:���һ����˳���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-2 ����03:16:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelSh(BxbxshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// ɾ��������е�����
			XsbxbxDao bxbxDao = new XsbxbxDao();
			result = bxbxDao.delBxbxjg(myForm.getSqid());
		return result;
	}

}
