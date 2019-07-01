/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����04:34:13 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgDao;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjlsq.GzjlsqDao;
import com.zfsoft.xgxt.szdw.gzjl.gzjlsq.GzjlsqForm;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����04:34:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjlshService extends SuperServiceImpl<GzjlshForm, GzjlshDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	private GzjlshDao dao = new GzjlshDao();
	private GzjljgDao gzjljgDao = new GzjljgDao();
	
	public HashMap<String, String> getGzjlshInfo(GzjlshForm t) {
		return dao.getGzjlshInfo(t);
	}
	public boolean saveSh(GzjlshForm form, User user) {
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
		model.setSqrid(form.getZgh());
		model.setTzlj("qgzx_mrgzkh_khsh.do");
		model.setTzljsq("qgzx_mrgzkh_khsq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			GzjlshForm gzjlshForm = new GzjlshForm();
			gzjlshForm.setSqid(form.getSqid());
			gzjlshForm.setShzt(zhzt);
			
			reuslt = dao.runUpdate(gzjlshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				GzjljgForm gzjljgForm = new GzjljgForm();
				GzjlsqForm khsqForm = new GzjlsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(gzjljgForm, StringUtils.formatData(khsqForm));
				gzjljgForm.setJgid(khsqForm.getSqid());
				gzjljgForm.setSqid(khsqForm.getSqid());
				gzjljgForm.setSjly("1");
				reuslt = gzjljgDao.runInsert(gzjljgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public String savePlsh(GzjlshForm t, User user) throws Exception {
		GzjlshForm model = new GzjlshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] zghs = t.getZghs();
		List<String> failzghs = new ArrayList<String>();
		
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setZgh(zghs[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failzghs.add(zghs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failzghs);
		if (failzghs.isEmpty()) {
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
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-2 ����09:07:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(GzjlshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// ɾ��������е�����
		result=gzjljgDao.delJgById(myForm.getSqid());
		return result;
	}

}
