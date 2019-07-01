/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-8 ����05:29:50 
 */
package com.zfsoft.xgxt.axcs.wpsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.axcs.wpjg.WpjgDao;
import com.zfsoft.xgxt.axcs.wpjg.WpjgForm;
import com.zfsoft.xgxt.axcs.wpjg.WpjgService;
import com.zfsoft.xgxt.axcs.wpsq.WpsqDao;
import com.zfsoft.xgxt.axcs.wpsq.WpsqForm;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-8 ����05:29:50
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WpshService extends SuperServiceImpl<WpshForm, WpshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private WpshDao dao = new WpshDao();

	/**
	 * 
	 * @����:��Ʒ��˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����06:43:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return boolean ��������
	 * @throws
	 */
	public boolean saveSh(WpshForm form, User user) {
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
		model.setSqrid(form.getXh());
		model.setTzlj("axcs_axcswpsh.do");
		model.setTzljsq("axcs_axcswpsq_stu.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			WpshForm wpshForm = new WpshForm();
			wpshForm.setSqid(form.getSqid());
			wpshForm.setShzt(zhzt);
			reuslt = dao.runUpdate(wpshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				WpjgForm wpjgForm = new WpjgForm();
				WpjgService wpjgService = new WpjgService();
				WpsqForm wpsqForm = new WpsqDao().getModel(form.getSqid());
				// ����ѧ�š�ѧ�ꡢ��Ŀ����ɾ�������������
				wpjgService.delWpjgxx(wpsqForm.getXh(), wpsqForm.getXn(), wpsqForm.getXmdm());
				BeanUtils.copyProperties(wpjgForm, StringUtils.formatData(wpsqForm));
				wpjgForm.setJgid(wpsqForm.getSqid());
				wpjgForm.setSjly("1");
				reuslt = wpjgService.runInsert(wpjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * 
	 * @����:�����Ϣ�鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-9 ����09:04:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getWpshInfo(WpshForm model) {
		if (StringUtil.isNull(model.getSqid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getWpshInfo(model);
	}

	/**
	 * 
	 * @����:������˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-9 ����09:12:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return String ��������
	 * @throws
	 */
	public String savePlsh(WpshForm t, User user) {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			Map<String, String> result = dao.getWpsqInfo(ids[i]);
			WpshForm model = new WpshForm();
			model.setSplc(result.get("splc"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());
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
	 * @���ڣ�2014-12-9 ����09:20:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param ywid
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(WpshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			// ɾ��������е�����
			WpjgDao jgdao = new WpjgDao();
			jgdao.delWpjgBySqid(myForm.getSqid());
		}
		return result;
	}
}
