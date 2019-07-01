/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-28 ����11:17:42 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqsh;

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
 * @ģ������: �ճ�����_����������ģ��
 * @�๦������: �������dao
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-10-28 ����11:17:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqshService extends SuperServiceImpl<KqshForm, KqshDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * 
	 * @����:��˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-28 ����04:19:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(KqshForm form, User user) {
		
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
		model.setYwid(form.getId());
		model.setSqrid(user.getUserName());
		model.setTzlj("rcsw_zjsy_kqsh.do");
		model.setTzljsq("rcsw_zjsy_kqwh.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			KqshForm kqshForm = new KqshForm();
			kqshForm.setId(form.getId());
			kqshForm.setShzt(zhzt);
			result = dao.runUpdate(kqshForm, form.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 
	 * @����:������˳���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-29 ����10:01:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public String kqshcx(KqshForm model, User user) throws Exception {
		
		ShlcInterface service = new CommShlcImpl();
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(Constants.YW_SHZ);
		runUpdate(model);
		return cancelFlag;
	}

	/** 
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-29 ����10:56:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(String values, KqshForm model, User user) throws Exception {
		
		List<String> failIds = new ArrayList<String>();
		
		List<HashMap<String, String>> checkShs = checkShs(values,model,user);
		
		for (int i = 0, n = checkShs.size(); i < n; i++) {
			
			model.setSplc(checkShs.get(i).get("splc"));
			model.setId(checkShs.get(i).get("id"));
			model.setGwid(checkShs.get(i).get("gwid"));
			
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failIds.add(checkShs.get(i).get("id"));
			}
		}
		JSONArray json = JSONArray.fromObject(failIds);
		if (failIds.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(model.getShzt())) {
			return MessageUtil.getText(MessageKey.RCSW_KQGL_KQSH_BZCK, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.RCSW_KQGL_KQSH_PLSH, json.toString());
		}
	}

	/** 
	 * @����:��ѯ�ж����������Ա����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-29 ����11:00:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param kqshForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> checkShs(String values,KqshForm kqshForm, User user) throws Exception {
		return dao.checkShs(values, kqshForm, user);
	}

}
