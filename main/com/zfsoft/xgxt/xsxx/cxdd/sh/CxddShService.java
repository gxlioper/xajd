/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:21:50 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgForm;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgService;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgService;
import com.zfsoft.xgxt.zxdk.xnwxdk.sh.XnwxdkShModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.Util;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.UtilForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-28 ����05:21:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxddShService extends SuperServiceImpl<CxddShForm, CxddShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	public List<HashMap<String, String>> getViewList(CxddShForm t, User user) throws Exception{
		return dao.getViewList(t, user);
	}
	
	/**
	 * 
	 *��˱���
	 */
	public boolean saveSh(CxddShForm form, User user) {
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
		model.setYwid(form.getBjid());
		model.setSqrid(form.getXh());
		model.setTzlj("xsxx_cxdd_pysh.do");
		model.setTzljsq("xsxx_cxdd_pysb.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			CxddShForm cxddshform = new CxddShForm();
			cxddshform.setBjid(form.getBjid());
			cxddshform.setShzt(zhzt);
			reuslt = dao.runUpdate(cxddshform, form.getBjid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				this.delJg(form);
				this.insertIntoJg(form);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public boolean insertIntoJg(CxddShForm t) throws Exception{
		return dao.insertIntoJg(t);
	}
	
	public boolean delJg(CxddShForm t)throws Exception{
		return dao.delJg(t);
	}
	
	//�������
	public String savePlsh(CxddShForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		CxddShForm model = new CxddShForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] bjdms = t.getBjdms();
		List<String> failXhs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setBjid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setBjdm(bjdms[i]);
			model.setXn(t.getXn());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(bjdms[i]);
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
	
	public boolean cancel(CxddShForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getBjid());
		if (result) {
		
			// ɾ��������е�������
			 this.delJg(myForm);
		
		}
		return result;
	}

	public String cxshnew(String ywid, CxddShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		this.updateSqjl(ywid, shzt);
		return cancelFlag;

	}
	
	public boolean updateSqjl(String ywid, String shzt) throws Exception {
		return dao.updateSqjl(ywid, shzt);
	}
}
