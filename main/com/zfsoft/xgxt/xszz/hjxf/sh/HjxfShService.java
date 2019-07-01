/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-15 ����11:50:19 
 */  
package com.zfsoft.xgxt.xszz.hjxf.sh;

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
import com.zfsoft.xgxt.xszz.hjxf.jg.HjxfJgDao;
import com.zfsoft.xgxt.xszz.hjxf.jg.HjxfJgForm;
import com.zfsoft.xgxt.xszz.hjxf.jg.HjxfJgService;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqDao;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqForm;
import com.zfsoft.xgxt.xszz.hjxf.unit.Unit;
import com.zfsoft.xgxt.xszz.hjxf.unit.UnitForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-15 ����11:50:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjxfShService extends SuperServiceImpl<HjxfShForm, HjxfShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *��˱���
	 */
	public boolean saveSh(HjxfShForm form, User user) {
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
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getHjxfid());
		model.setSqrid(form.getXh());
		model.setTzlj("xszz_hjxf_sh.do");
		model.setTzljsq("xszz_hjxf_sq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			HjxfShForm hjxfshform = new HjxfShForm();
			hjxfshform.setHjxfid(form.getHjxfid());
			hjxfshform.setShzt(zhzt);
			reuslt = dao.runUpdate(hjxfshform, form.getHjxfid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				HjxfJgForm hjxfjgform = new HjxfJgForm();
				HjxfJgService hjxfjgservice = new HjxfJgService();
				HjxfSqForm sqForm = new HjxfSqDao().getModel(form.getHjxfid());
				BeanUtils.copyProperties(hjxfjgform, StringUtils.formatData(sqForm));
//				khjgService.Cjcl(khjgForm);
				
				hjxfjgform.setXh(form.getXh());
				Unit util = new Unit();
				UnitForm utilform = new UnitForm();
				utilform.setXh(sqForm.getXh());
				utilform.setXn(sqForm.getXn());
				utilform.setXq(sqForm.getXq());
				utilform.setType("jg");
				if(!util.isNotExists(utilform)){
					hjxfjgservice.delDkjg(utilform.getXh(),utilform.getXn(),utilform.getXq());
				}
				hjxfjgform.setJgid(sqForm.getHjxfid());
				hjxfjgform.setSjly("1");
				hjxfjgform.setCzy(user.getUserName());
				reuslt = hjxfjgservice.runInsert(hjxfjgform);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	//�������
	public String savePlsh(HjxfShForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		HjxfShForm model = new HjxfShForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplcid(t.getSplcid());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setHjxfid(ids[i]);
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
	
	public boolean cancel(HjxfShForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getHjxfid());
		if (result) {
			HjxfJgDao jgdao = new HjxfJgDao();
		
			// ɾ��������е�������
			
			jgdao.runDelete(new String[]{myForm.getHjxfid()});
		
		}
		return result;
	}

	public String cxshnew(String ywid, HjxfShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}
	
	
}
