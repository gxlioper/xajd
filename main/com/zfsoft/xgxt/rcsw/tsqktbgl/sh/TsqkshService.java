/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-18 ����11:51:11 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.sh;

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
import com.zfsoft.xgxt.rcsw.tsqktbgl.jg.TsqkjgDao;
import com.zfsoft.xgxt.rcsw.tsqktbgl.jg.TsqkjgForm;
import com.zfsoft.xgxt.rcsw.tsqktbgl.jg.TsqkjgService;
import com.zfsoft.xgxt.rcsw.tsqktbgl.sq.TsqktbDao;
import com.zfsoft.xgxt.rcsw.tsqktbgl.sq.TsqktbForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-18 ����11:51:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsqkshService extends SuperServiceImpl<TsqkshForm, TsqkshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private TsqkshDao dao = new TsqkshDao();
	
	public boolean saveSh(TsqkshForm form, User user) {
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
		model.setZd1("����㼶");
		model.setZd2(form.getClcj());
		if(form.getClcj().equals("1")){
			model.setZd3("Ժϵ");
		}else{
			model.setZd3("ѧ����");
		}
		model.setTzlj("rcsw_tsqktbgl_sh.do");
		model.setTzljsq("rcsw_tsqktbgl_tb.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			TsqkshForm sbshForm = new TsqkshForm();
			sbshForm.setSqid(form.getSqid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				TsqkjgForm tsqkjgForm = new TsqkjgForm();
				TsqkjgService tsqkjgService = new TsqkjgService();
				TsqktbForm tsqktbForm = new TsqktbDao().getModel(form.getSqid());
				BeanUtils.copyProperties(tsqkjgForm, StringUtils.formatData(tsqktbForm));
				tsqkjgForm.setLylcywid(tsqktbForm.getSqid());
				tsqkjgForm.setSjly("1");
				tsqkjgForm.setClcj(form.getClcj());
				if(tsqkjgService.isHaveRecordForjg(tsqkjgForm)){
					//���������д������ݣ���ɾ���ٲ���
					new TsqkjgDao().deleteForSq(tsqkjgForm);
					tsqkjgService.runInsert(tsqkjgForm);
				}else{
					tsqkjgService.runInsert(tsqkjgForm);
				}								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	 * @����:�õ�����㼶
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-21 ����11:59:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getCurentCjcj(TsqkshForm form){
		return dao.getCurentCjcj(form);
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-21 ����11:59:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String cxshnew(String ywid, TsqkshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;
	}
	
	/** 
	 * @����:������ɾ�������������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-21 ����01:41:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(TsqkshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// ɾ��������е�����
			TsqkjgDao tsqkjgDao = new TsqkjgDao();
			result = tsqkjgDao.delByLclyywid(myForm.getSqid());
		return result;
	}
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-21 ����03:05:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(TsqkshForm t, User user) throws Exception{
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String clcj = t.getClcj();
		List<String> failXms = new ArrayList<String>();
		TsqkshForm model = new TsqkshForm();
		for (int i = 0, n = ids.length; i < n; i++) {
			TsqkshForm form = dao.getModel(ids[i]);			
			model.setSplc(form.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setClcj(clcj);
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
