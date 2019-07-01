/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-27 ����04:06:05 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxsh;

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
import com.zfsoft.xgxt.xsztz.jxgl.xnjxjg.XnjxjgDao;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxjg.XnjxjgForm;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxjg.XnjxjgService;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsq.XnjxsqDao;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsq.XnjxsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-27 ����04:06:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnjxshService extends SuperServiceImpl<XnjxshForm, XnjxshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private XnjxshDao dao = new XnjxshDao();
	private XnjxsqDao xnjxsqDao = new XnjxsqDao();
	
	public String getJxmc(String jxid){
		return dao.getJxmc(jxid);
	}
	
	public String getJxfs(String jxid){
		return dao.getJxfs(jxid);
	}
	/**
	 * 
	 *��˱���
	 */
	public boolean saveSh(XnjxshForm form, User user) throws Exception {
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getShlc());
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
		model.setTzlj("sztz_jxgl_xnjxsh.do");
		model.setTzljsq("sztz_jxgl_xnjxsq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XnjxshForm sbshForm = new XnjxshForm();
			//sbshForm.setXmdm(form.getXmdm());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getId());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				XnjxjgForm xnjxjgForm= new XnjxjgForm();
				XnjxjgService xnjxjgService = new XnjxjgService();
				XnjxsqForm xnjxsqForm = new XnjxsqDao().getModel(form.getId());
				BeanUtils.copyProperties(xnjxjgForm, StringUtils.formatData(xnjxsqForm));
				xnjxjgForm.setLylcywid1(xnjxsqForm.getId());
				xnjxjgForm.setSjly1("1");
				xnjxjgForm.setYlzd2("0");
				xnjxjgService.runInsert(xnjxjgForm);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-28 ����01:28:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(XnjxshForm t, User user) throws Exception{
		String[] ids = t.getIds();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			XnjxsqForm rsForm = xnjxsqDao.getModel(ids[i]);
			XnjxshForm model = new XnjxshForm();
			model.setShlc(rsForm.getShlc());
			model.setId(ids[i]);
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
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-28 ����02:14:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(XnjxshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getId());
		XnjxsqForm updateForm = xnjxsqDao.getModel(myForm.getId());
		XnjxjgForm form = new XnjxjgForm();
		BeanUtils.copyProperties(form, updateForm);
		form.setLylcywid1(updateForm.getId());
			// ɾ��������е�����
			XnjxjgDao xnjxjgDao = new XnjxjgDao();
			result = xnjxjgDao.runDel(form);
		return result;
	}
}
