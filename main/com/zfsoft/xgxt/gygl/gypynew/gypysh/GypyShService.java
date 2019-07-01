package com.zfsoft.xgxt.gygl.gypynew.gypysh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.portallet.gyglAction;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.gygl.gypynew.gypyjg.GypyJgDao;
import com.zfsoft.xgxt.gygl.gypynew.gypyjg.GypyJgForm;
import com.zfsoft.xgxt.gygl.gypynew.gypyjg.GypyJgService;

public class GypyShService extends SuperServiceImpl<GypyShForm, GypyShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *��˱���
	 */
	@TransactionControl
	public boolean saveSh(GypyShForm form, User user) {
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
		model.setYwid(form.getSqid());
		//model.setSqrid();
		model.setTzlj("gygl_gypynew_gypysh.do");
		model.setTzljsq("gygl_gypynew_gypysq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditingNotCommit(model);
			GypyShForm shForm = new GypyShForm();
			shForm.setSqid(form.getSqid());
			shForm.setShzt(zhzt);
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				shForm.setGxsj(form.getGxsj());
				reuslt = dao.runUpdateNotCommit(shForm, form.getSqid());
				GypyJgForm jgForm = new GypyJgForm();
				GypyJgService jgService = new GypyJgService();
				shForm = this.getModel(form.getSqid());
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
				jgForm.setJgid(shForm.getSqid());
				String jgid = dao.checkJgIsExists(shForm);
				if(StringUtils.isNotNull(jgid)){
					jgService.runDelete(new String[]{jgid});
				}
				jgForm.setJgid(shForm.getSqid());
				jgForm.setSjly("1");
				jgForm.setCxzt("0");
				jgForm.setGxsj(form.getGxsj());
				reuslt = new GypyJgDao().runInsertNotCommit(jgForm);
			}else{
				reuslt = dao.runUpdateNotCommit(shForm, form.getSqid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	//�������
	@TransactionControl
	public String savePlsh(GypyShForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		GypyShForm model = new GypyShForm();
		String[] ids = t.getSqids();
		String[] gwid = t.getGwids();
		String[] splcs = t.getSplcs();
		List<String> failXhs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setSplc(splcs[i]);
			model.setGxsj(t.getGxsj());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(ids[i]);
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
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-2 ����10:05:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(GypyShForm myForm) throws Exception {
		String shzt = myForm.getShzt();
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result && "1".equals(shzt)) {
			GypyJgDao jgdao = new GypyJgDao();
		
			// ɾ��������е�������
			
			jgdao.runDelete(new String[]{myForm.getSqid()});
		
		}
		return result;
	}

	public String cxshnew(String ywid, GypyShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		dao.runUpdate(model, ywid);
		return cancelFlag;

	}
	/**
	 * 
	 * @����: ��֤�Ƿ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-8 ����05:10:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dshgwid
	 * @param splc
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsLastGw(String dshgwid,String splc){
		return dao.checkIsLastGw(dshgwid, splc);
	}
}
