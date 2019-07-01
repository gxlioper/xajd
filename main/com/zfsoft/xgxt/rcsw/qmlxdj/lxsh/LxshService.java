/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-11 ����09:04:03 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxsh;

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
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjService;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxjg.LxjgForm;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxjg.LxjgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-11 ����09:04:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxshService extends SuperServiceImpl<LxshForm, LxshDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * 
	 * @����: ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����11:02:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(LxshForm form, User user) {
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_qmlxsh.do");
		model.setTzljsq("rcsw_qmlxdj.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			LxshForm  shForm = new LxshForm();
			shForm.setSqid(form.getSqid());
			shForm.setShzt(zhzt);
			reuslt = dao.runUpdate(shForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				LxjgForm jgForm = new LxjgForm();
				LxjgService service = new LxjgService();
				LxshForm lxshForm = this.getModel(form);
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(lxshForm));
//				khjgService.Cjcl(khjgForm);
				
				
				jgForm.setXh(form.getXh());
				LxdjService sqService = new LxdjService();
				if(!sqService.checkNotExist(lxshForm.getXh(), lxshForm.getXn(), lxshForm.getXq(), "jg")){
					service.delJgbyShTg(lxshForm.getXh(),lxshForm.getXn(), lxshForm.getXq());
				}
				jgForm.setJgid(lxshForm.getSqid());
				jgForm.setSjly("1");
				reuslt = service.runInsert(jgForm);
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
	 * @���ڣ�2017-1-12 ����11:42:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(LxshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		LxshForm model = new LxshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] xns = t.getXns();
		String[] xqs = t.getXqs();
		List<String> failXhs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplcid(t.getSplcid());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(xns[i]);
			model.setXq(xqs[i]);
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
	 * @���ڣ�2017-1-12 ����11:51:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(LxshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			LxjgService jgService = new LxjgService();
		
			// ɾ��������е�������
			jgService.runDelete(new String[]{myForm.getSqid()});
		
		}
		return result;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����11:56:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cxshnew(String ywid, LxshForm shForm, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		shForm.setShzt(shzt);
		String cancelFlag = service.runCancelNew(user.getUserName(), shForm.getShid(), shForm.getShlc());
		dao.runUpdate(shForm, shForm.getSqid());
		return cancelFlag;

	}

}
