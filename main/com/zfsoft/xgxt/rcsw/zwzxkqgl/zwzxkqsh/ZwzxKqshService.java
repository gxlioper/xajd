/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:38:46 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsh;

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
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgDao;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgForm;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq.ZwzxKqsqDao;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq.ZwzxKqsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-26 ����02:38:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwzxKqshService extends SuperServiceImpl<ZwzxKqshForm, ZwzxKqshDao>{
	private static final String SJZT = "0";// ���״̬��Ϊ1������״̬Ϊ0
	private ShlcInterface shlc = new CommShlcImpl();
	private ZwzxKqshDao dao = new ZwzxKqshDao();
	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����10:40:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKqshInfo(String sqid) {
		return dao.getKqshInfo(sqid);

	}
	/**
	 * 
	 * @����:��˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����02:35:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(ZwzxKqshForm form, User user) {
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
		model.setTzlj("rcsw_zwzxkq_kqsh.do");
		model.setTzljsq("rcsw_zwzxkq_kqsq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			ZwzxKqshForm kqshForm = new ZwzxKqshForm();
			kqshForm.setSqid(form.getSqid());
			kqshForm.setShzt(zhzt);
			result = dao.runUpdate(kqshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				ZwzxKqjgForm kqjgForm = new ZwzxKqjgForm();
				ZwzxKqjgService kqjgService = new ZwzxKqjgService();
				ZwzxKqsqForm kqsqForm = new ZwzxKqsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(kqjgForm, StringUtils.formatData(kqsqForm));
				kqjgForm.setJgid(kqsqForm.getSqid());
				kqjgForm.setLrsj(kqsqForm.getSqsj());
				kqjgForm.setSjly("1");
				kqjgService.runInsert(kqjgForm);
				result = kqjgService.updateQqxs(kqsqForm.getSqid(),"1");
				
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
	 * @���ڣ�2015-1-27 ����02:35:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(ZwzxKqshForm t, User user) {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] bjdms = t.getBjdms();
		List<String> failBjs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			Map<String, String> result = dao.getKqshInfo(ids[i]);
			ZwzxKqshForm model = new ZwzxKqshForm();
			model.setSplc(result.get("splc"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setBjdm(bjdms[i]);
			model.setXn(t.getXn());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failBjs.add(bjdms[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failBjs);
		if (failBjs.isEmpty()) {
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
	 * @���ڣ�2015-1-27 ����01:59:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(ZwzxKqshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// ɾ��������е�����
			ZwzxKqjgDao kqjgDao = new ZwzxKqjgDao();
			result = kqjgDao.delKqjgBySqid(myForm.getSqid());
			if(result){
				//����ѧ��ȱ����Ϣ����״̬Ϊ'0'
				result = kqjgDao.updateQqxs(myForm.getSqid(), SJZT);
			}
		return result;
	}



}
