/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����11:10:02 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dyzp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjDao;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjModel;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjService;
import com.zfsoft.xgxt.xsxx.dyxj.zpjg.ZpjgDao;
import com.zfsoft.xgxt.xsxx.dyxj.zpjg.ZpjgModel;

/** 
 * @�๦������: ��������
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-19 ����11:10:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DyzpService extends SuperAuditService<DyzpModel, DyzpDao> {

	
	private static final String SQSH = "1";
	private ShlcInterface shlc = new CommShlcImpl();
	private ZpdjDao djDao = new ZpdjDao();
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(DyzpModel model) {
		
		ZpjgModel zpjgModel = new ZpjgModel();
		
		try {
			dao.runUpdate(model);
			
			BeanUtils.copyProperties(zpjgModel, model);
			zpjgModel.setSjly(SQSH);
			
			ZpjgDao zpjgDao = new ZpjgDao();
			if (Integer.valueOf(zpjgDao.getCount(zpjgModel)) == 0){
				return zpjgDao.runInsert(zpjgModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(DyzpModel model) {
		try {
			return new ZpjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public List<HashMap<String, String>> getAudingList(DyzpModel t, User user)
		throws Exception {

		return dao.getAudingList(t, user);
	}

	
	/**��ѯ�Ƿ�������**/
	public String getCount(DyzpModel model){
		return dao.getCount(model);
	}
	
	
	
	/**
	 * @throws Exception 
	 * ����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(DyzpModel form,User user) throws Exception{
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
		model.setGwid(form.getXtgwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getId());
		// ======= ҵ���ֶ� begin======
		model.setSqrid(form.getXh());
		// ======= ҵ���ֶ� end======
		model.setTzlj("xsxx_dyxj_pysh.do");
		model.setTzljsq("xsxx_dyxj_pysq.do");
		
		if(form.getShjg().equals("1")) {
			// �O��ҵ���ֶ�1[ҵ������]
			model.setZd1("�����ȼ�");
			// �O��ҵ���ֶ�2[ҵ��ID]
			model.setZd2(form.getPddjdm());
			// �O��ҵ���ֶ�3
			ZpdjModel djFrom = new ZpdjModel();
			djFrom.setDjdm(form.getPddjdm());
			djFrom = djDao.getModel(djFrom);
			model.setZd3(djFrom.getDjmc());		
		}
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			DyzpModel upForm = new DyzpModel();
			upForm.setId(form.getId());
			if(zhzt.equals(Constants.SH_TG)) {
				upForm.setPddjdm(form.getPddjdm());
			}else{
				upForm.setPddjdm("");
			}
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getId());
			//���״̬Ϊͨ��
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				ZpjgModel zpjgModel = new ZpjgModel();
				form = getModel(form);
				form.setDjdm(form.getPddjdm());
        		BeanUtils.copyProperties(zpjgModel, StringUtils.formatData(form));
        		zpjgModel.setSjly("1");
        		ZpjgDao zpjgDao = new ZpjgDao();
        		this.delZpjg(zpjgModel);
        		zpjgDao.runInsert(zpjgModel);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ������˱���  
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-11-3 ����02:47:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(DyzpModel t, User user) throws Exception {
		String[] ids = t.getSqid();
		String[] splcids = t.getSplcids();
		String[] xtgwid = t.getXtgwids();
		// ======= ҵ���ֶ� begin======
		String[] xhs = t.getXhs();
		// ======= ҵ���ֶ� end======
		List<String> fails = new ArrayList<String>();
		DyzpModel checkmodel = this.getModel(ids[0]);
	    ZpdjDao zpdjdao = new ZpdjDao();
	    HashMap<String,String> zpdjmodel = zpdjdao.getDjmc(t.getPddjdm());
		if(zpdjmodel.get("djmc").equals("��") && this.isOverLimit(checkmodel.getXn(), checkmodel.getXq(), checkmodel.getXh(),ids.length+"")){
			fails.add(xhs[0]);
			JSONArray json = JSONArray.fromObject(fails);
			return MessageUtil.getText(MessageKey.SYS_YXSX_OVER, json.toString());
		}
		for (int i = 0, n = ids.length; i < n; i++) {
			DyzpModel model = new DyzpModel();
			model.setId(ids[i]);
			model.setSplcid(splcids[i]);
			model.setXtgwid(xtgwid[i]);
			model.setPddjdm(t.getPddjdm());
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			// ======= ҵ���ֶ� begin======
			model.setXh(xhs[i]);
			// ======= ҵ���ֶ� end======
		
		
				boolean isSuccess = saveSh(model, user);
				if (!isSuccess) {
					fails.add(xhs[i]);
				}
			
		}
		JSONArray json = JSONArray.fromObject(fails);
		if (fails.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * �����������ʱ�ж��Ƿ�������������30%
	 */
	public boolean isOverLimit(String xn,String xq,String xh,String rs){
		
		return dao.isOverLimit(xn, xq, xh,rs);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���ͨ��֮��ɾ��ԭ����������������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-20 ����09:55:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zpjgModel
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delZpjg(ZpjgModel zpjgModel) throws Exception{
		return dao.delZpjg(zpjgModel);
	}
}
