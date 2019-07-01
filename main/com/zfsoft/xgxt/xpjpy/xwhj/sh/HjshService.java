/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-26 ����05:59:32 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sh;

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
import com.zfsoft.xgxt.xpjpy.xwhj.jg.HjjgDao;
import com.zfsoft.xgxt.xpjpy.xwhj.jg.HjjgForm;
import com.zfsoft.xgxt.xpjpy.xwhj.jg.HjjgService;
import com.zfsoft.xgxt.xpjpy.xwhj.sq.HjsqDao;
import com.zfsoft.xgxt.xpjpy.xwhj.sq.HjsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: �����  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-26 ����05:59:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjshService extends SuperServiceImpl<HjshForm, HjshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private HjshDao dao = new HjshDao();
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-27 ����03:14:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(HjshForm form, User user) {
		
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
		model.setTzlj("pjpy_hjgl_sh.do");
		model.setTzljsq("pjpy_hjgl_sq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			HjshForm sbshForm = new HjshForm();
			sbshForm.setSqid(form.getSqid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				HjjgForm jgForm = new HjjgForm();
				HjjgService jgService = new HjjgService();
				HjsqForm sqForm = new HjsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(sqForm));
				jgForm.setLylcywid(sqForm.getSqid());
				jgForm.setSjly("1");
				if(jgService.isHaveRecordForjg(jgForm)) {
					//���������д������ݣ���ɾ���ٲ���
					new HjjgDao().deleteForSq(jgForm);
					jgService.runInsert(jgForm);
				}else {
					jgService.runInsert(jgForm);
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
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-27 ����03:15:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cxshnew(String ywid, HjshForm model, User user) throws Exception {
		
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		dao.updateSqjl(ywid, shzt);
		
		return cancelFlag;
		
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-27 ����03:15:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(HjshForm myForm) throws Exception {
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		// ɾ��������е�����
		HjjgDao jgDao = new HjjgDao();
		result = jgDao.delByLclyywid(myForm.getSqid());
		
		return result;	
	}
	
	/**
	 * 
	 * @����: ������˱���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-27 ����03:32:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(HjshForm t, User user) throws Exception{
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			HjshForm form = dao.getModel(ids[i]);
			HjshForm model = new HjshForm();
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
