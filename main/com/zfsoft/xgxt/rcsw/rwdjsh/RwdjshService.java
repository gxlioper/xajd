/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-4 ����05:46:24 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.rwdj.RwdjForm;
import com.zfsoft.xgxt.rcsw.rwdj.RwdjService;
import com.zfsoft.xgxt.rcsw.rwdjsq.RwdjsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-4 ����05:46:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwdjshService extends SuperServiceImpl<RwdjshForm, RwdjshDao> {
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @����: ������˱���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-5 ����09:54:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(RwdjshForm form, User user) {
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
//		model.setZd1("��Ч��ʱ");
//		model.setZd2(form.getGs());
//		model.setZd3(form.getYxgs());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getRwdjid());
		model.setSqrid(form.getXh());
		model.setTzlj("rwdjshbase.do");
		model.setTzljsq("rwdjsqbase.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			RwdjshForm shForm = new RwdjshForm();
			shForm.setRwdjid(form.getRwdjid());
			shForm.setShzt(zhzt);
			reuslt = dao.runUpdate(shForm, shForm.getRwdjid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				RwdjForm jgForm = new RwdjForm();
				RwdjService jgService = new RwdjService();
				shForm = this.getModel(form.getRwdjid());
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
				
				if(!jgService.checkIsNotExist(shForm.getXh())){
					jgService.delJgbyXh(shForm.getXh());
				}
				jgForm.setRwdjid(shForm.getRwdjid());
				jgForm.setSjly("1");
				reuslt = jgService.runInsert(jgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-5 ����10:37:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(RwdjshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		RwdjshForm model = new RwdjshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setRwdjid(ids[i]);
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
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-5 ����10:39:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(RwdjshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getRwdjid());
		if (result) {
			//ɾ��������е�����
			new RwdjService().delete(new String[]{myForm.getRwdjid()});
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-5 ����10:39:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cxshnew(String ywid, RwdjshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		dao.runUpdate(model, ywid);
		return cancelFlag;

	}
	
	@Override
	public RwdjshForm getModel(RwdjshForm t) throws Exception {
		return dao.getModel(t);
	}
}
