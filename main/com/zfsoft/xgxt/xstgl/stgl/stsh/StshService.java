/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:38:46 
 */  
package com.zfsoft.xgxt.xstgl.stgl.stsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgDao;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgForm;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgService;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqDao;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqForm;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-4 ����02:38:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class StshService extends SuperServiceImpl<StshForm, StshDao>{
	private static final String SJZT = "0";// ���״̬��Ϊ1������״̬Ϊ0
	private ShlcInterface shlc = new CommShlcImpl();
	private StshDao dao = new StshDao();
	private StsqDao sqDao = new StsqDao();
	private StsqService stsqService = new StsqService();
	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����10:40:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXmshInfo(String Stid) {
		return dao.getXmshInfo(Stid);

	}
	/**
	 * 
	 * @����:��˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����02:35:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(StshForm form, User user) {
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
		model.setTzlj("stgl_stgl_stsh.do");
		model.setTzljsq("stgl_stgl_stsq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			StshForm stshForm = new StshForm();
			stshForm.setSqid(form.getSqid());
			stshForm.setShzt(zhzt);
			result = dao.runUpdate(stshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				StjgForm stjgForm = new StjgForm();
				StjgService stjgService = new StjgService();
				StsqForm sqForm = new StsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(stjgForm, StringUtils.formatData(sqForm));
				stjgForm.setStid(sqForm.getSqid());
				stjgForm.setSjly("1");
				//�汾�������޸ģ������������������ݣ������ͨ���������ݱ�ʱ�����������̣�splc������Ϊ��
				stjgForm.setSplc(null);
				stjgService.runInsert(stjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:������˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����02:35:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(StshForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			StshForm model = new StshForm();
			StsqForm stsq = stsqService.getModel(ids[i]);
		//	Map<String, String> result = sqDao.getSqxx(ids[i]);
			model.setSplc(stsq.getSplc());
			model.setYwid(ids[i]);
			model.setSqid(ids[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * 
	 * @����:���һ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����01:59:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(StshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// ɾ��������е�����
			StjgDao stjgDao = new StjgDao();
			result = stjgDao.delStjg(myForm.getSqid());
		return result;
	}



}
