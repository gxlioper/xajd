/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��10�� ����8:41:37 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgDao;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgForm;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgService;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqDao;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը�������Service
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��10�� ����8:41:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwShService extends SuperServiceImpl<ZyfwShForm,ZyfwShDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();

	/** 
	 * @����:����־Ը���񵥸����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��10�� ����4:53:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwShForm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveForDgsh(ZyfwShForm zyfwShForm, User user) {
		
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(zyfwShForm.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(zyfwShForm.getShyj());
		// ���״̬
		model.setShzt(zyfwShForm.getShjg());
		//��λ�˻�
		model.setThgw(zyfwShForm.getThgw());
		// ��˸�λID
		model.setGwid(zyfwShForm.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(zyfwShForm.getFwid());
		//������id
		model.setSqrid(zyfwShForm.getXh());
		
		model.setTzlj("xsxx_zyfwgl_sh.do?method=zyfwShList");
		model.setTzljsq( "xsxx_zyfwgl_sq.do?method=zyfwSqList");
		
		boolean reuslt = false;
		try {
			String shzt = shlc.runAuditing(model);
			zyfwShForm.setShzt(shzt);
			reuslt = dao.runUpdate(zyfwShForm);
			// ���浽�����
			if (shzt.equalsIgnoreCase(Constants.YW_TG)) {
				ZyfwJgForm zyfwJgForm = new ZyfwJgForm();
				ZyfwJgService zyfwJgService = new ZyfwJgService();
				ZyfwSqForm zyfwSqForm = new ZyfwSqDao().getModel(zyfwShForm.getFwid());
				BeanUtils.copyProperties(zyfwJgForm,zyfwSqForm);
				zyfwJgForm.setSjly("1");
				reuslt = zyfwJgService.runInsert(zyfwJgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/** 
	 * @����:����־Ը�����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��11�� ����2:52:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwShForm
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String saveForPlsh(ZyfwShForm zyfwShForm, User user) {
		
		ZyfwShForm model = new ZyfwShForm();
		String[] fwids = zyfwShForm.getFwids();
		String[] gwids = zyfwShForm.getGwids();
		String[] xhs = zyfwShForm.getXhs();
		
		model.setSplc(zyfwShForm.getSplc());
		model.setShyj(zyfwShForm.getShyj());
		model.setShjg(zyfwShForm.getShjg());
		
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = fwids.length; i < n; i++) {
			
			model.setGwid(gwids[i]);
			model.setFwid(fwids[i]);
			model.setXh(xhs[i]);

			boolean isSuccess = saveForDgsh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(zyfwShForm.getShzt())) {
			//���ݱ����������������ʱû��û�������˻�
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}

	/**
	 * @����:־Ը������ˣ����һ���ĳ������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��11�� ����4:53:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwShForm
	 * @return
	 * boolean �������� 
	 * @throws Exception  
	 */
	public boolean cancelShLast(ZyfwShForm zyfwShForm) throws Exception {
		
		zyfwShForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(zyfwShForm);
		if (result) {
			new ZyfwJgDao().runDelete(new String[]{zyfwShForm.getFwid()});
		}
		return result;
	}

}
