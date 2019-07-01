/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:38:46 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbsh;
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
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbForm;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgForm;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-7-13����02:38:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmsbshService extends SuperServiceImpl<XmsbshForm, XmsbshDao>{
	private static final String SJZT = "0";// ���״̬��Ϊ1������״̬Ϊ0
	private ShlcInterface shlc = new CommShlcImpl();
	private XmsbshDao dao = new XmsbshDao();
	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����10:40:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXmshInfo(String xmdm) {
		return dao.getXmshInfo(xmdm);

	}
	/**
	 * 
	 * @����:��˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����02:35:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(XmsbshForm form, User user) {
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
		model.setYwid(form.getXmdm());
		model.setSqrid(user.getUserName());
		model.setTzlj("sztz_xmsbgl_xmsh.do");
		model.setTzljsq("sztz_xmsbgl_xmsb.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XmsbshForm sbshForm = new XmsbshForm();
			sbshForm.setXmdm(form.getXmdm());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getXmdm());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				XmsbjgForm xmsbjgForm = new XmsbjgForm();
				XmsbjgService xmsbjgService = new XmsbjgService();
				XmsbForm xmsbForm = new XmsbDao().getModel(form.getXmdm());
				BeanUtils.copyProperties(xmsbjgForm, StringUtils.formatData(xmsbForm));
				xmsbjgForm.setJgid(xmsbForm.getXmdm());
				xmsbjgForm.setSjly("1");
				xmsbjgService.runInsert(xmsbjgForm);
				
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
	 * @���ڣ�2015-7-13 ����02:35:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(XmsbshForm t, User user) {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			Map<String, String> result = dao.getXmshInfo(ids[i]);
			XmsbshForm model = new XmsbshForm();
			model.setSplc(result.get("splc"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setXmdm(ids[i]);
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
	 * @���ڣ�2015-7-13 ����01:59:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(XmsbshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getXmdm());
			// ɾ��������е�����
			XmsbjgDao xmsbjgDao = new XmsbjgDao();
			result = xmsbjgDao.delXmsbjg(myForm.getXmdm());
		return result;
	}



}
