/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-7 ����04:13:52 
 */
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgDao;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgForm;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgService;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdsq.SthdsqDao;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdsq.SthdsqForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-7 ����04:13:52
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class SthdshService extends SuperServiceImpl<SthdshForm, SthdshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private SthdshDao dao = new SthdshDao();
	private SthdjgDao hdjgDao = new SthdjgDao();

	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-28 ����08:42:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getHdshInfo(SthdshForm t) {
		return dao.getHdshInfo(t);

	}

	/**
	 * 
	 * @����:��˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-28 ����08:51:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return boolean ��������
	 * @throws
	 */
	public boolean saveSh(SthdshForm form, User user) {
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
		model.setYwid(form.getHdid());
		model.setSqrid(form.getXh());
		model.setTzlj("stgl_sthdgl_sthdsq.do");
		model.setTzljsq("stgl_sthdgl_sthdsh.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			SthdshForm hdshForm = new SthdshForm();
			hdshForm.setHdid(form.getHdid());
			hdshForm.setShzt(zhzt);
			hdshForm.setYxgs(form.getYxgs());
			reuslt = dao.runUpdate(hdshForm, form.getHdid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				SthdjgForm hdjgForm = new SthdjgForm();
				SthdjgService hdjgService = new SthdjgService();
				SthdsqForm hdsqForm = new SthdsqDao().getModel(form.getHdid());
				BeanUtils.copyProperties(hdjgForm, StringUtils.formatData(hdsqForm));
				hdjgForm.setHdid(hdsqForm.getHdid());
				hdjgForm.setLrr(user.getUserName());
				hdjgForm.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
				hdjgForm.setSjly("1");
				reuslt = hdjgService.delByFwsj(hdjgForm);
				if(reuslt)
					reuslt = hdjgService.runInsert(hdjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:������˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-28 ����03:22:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return String ��������
	 * @throws
	 */
	public String savePlsh(SthdshForm t, User user) throws Exception {
		SthdshForm model = new SthdshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setHdid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);

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
	 * @����:���һ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-28 ����03:22:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cancel(SthdshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getHdid());
		if (result) {
			hdjgDao.delSthdjgById(myForm.getHdid());
		}
		return result;
	}


}
