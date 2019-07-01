/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-10 ����03:59:53 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sh;

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
import com.zfsoft.xgxt.zxdk.byhkgl.jg.ByhkglJgDao;
import com.zfsoft.xgxt.zxdk.byhkgl.jg.ByhkglJgForm;
import com.zfsoft.xgxt.zxdk.byhkgl.jg.ByhkglJgService;
import com.zfsoft.xgxt.zxdk.byhkgl.sq.ByhkglSqDao;
import com.zfsoft.xgxt.zxdk.byhkgl.sq.ByhkglSqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: ��ҵ������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-10 ����03:59:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ByhkglShService extends SuperServiceImpl<ByhkglShForm, ByhkglShDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private ByhkglShDao dao = new ByhkglShDao();
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-11 ����01:29:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(ByhkglShForm form, User user) {
		
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
		model.setTzlj("zxdk_byhkgl_byhksh.do");
		model.setTzljsq("zxdk_byhkgl_byhksq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			ByhkglShForm sbshForm = new ByhkglShForm();
			sbshForm.setSqid(form.getSqid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				ByhkglJgForm byhkglJgForm = new ByhkglJgForm();
				ByhkglJgService byhkglJgService = new ByhkglJgService();
				ByhkglSqForm byhkglSqForm = new ByhkglSqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(byhkglJgForm, StringUtils.formatData(byhkglSqForm));
				byhkglJgForm.setLclyywid(byhkglSqForm.getSqid());
				byhkglJgForm.setSjly("1");
				if(byhkglJgService.isHaveRecordForjg(byhkglJgForm)) {
					//���������д������ݣ���ɾ���ٲ���
					new ByhkglJgDao().deleteForSq(byhkglJgForm);
					byhkglJgService.runInsert(byhkglJgForm);
				}else {
					byhkglJgService.runInsert(byhkglJgForm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-11 ����01:56:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cxshnew(String ywid, ByhkglShForm model, User user) throws Exception {
		
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		dao.updateSqjl(ywid, shzt);
		
		return cancelFlag;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-11 ����01:56:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(ByhkglShForm myForm) throws Exception {
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		// ɾ��������е�����
		ByhkglJgDao byhkglJgDao = new ByhkglJgDao();
		result = byhkglJgDao.delByLclyywid(myForm.getSqid());
		
		return result;	
	}
	
	/**
	 * 
	 * @����: ������˱���
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-11 ����02:08:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(ByhkglShForm t, User user) throws Exception{
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			ByhkglShForm form = dao.getModel(ids[i]);
			ByhkglShForm model = new ByhkglShForm();
			model.setSplc(form.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
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
	
}
