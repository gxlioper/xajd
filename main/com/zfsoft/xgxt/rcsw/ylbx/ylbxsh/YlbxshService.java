package com.zfsoft.xgxt.rcsw.ylbx.ylbxsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjg.YlbxjgDao;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjg.YlbxjgForm;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxsq.YlbxsqForm;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjModel;

public class YlbxshService extends SuperServiceImpl<YlbxshForm, YlbxshDao> {

	private YlbxshDao dao = new YlbxshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public YlbxshService() {
		super.setDao(dao);
	}
	
	/**
	 * ����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(YlbxshForm form,User user){
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
		// ======= ҵ���ֶ� begin======
		model.setSqrid(form.getXh());
		// ======= ҵ���ֶ� end======
		model.setTzlj("rcsw_ylbx_ylbxsh.do");
		model.setTzljsq("rcsw_ylbx_ylbxsq.do");
		if("13573".equals(Base.xxdm)) {
			if(form.getShjg().equals("1")) {
				// �O��ҵ���ֶ�1[ҵ������]
				model.setZd1("ҽ����");
				// �O��ҵ���ֶ�2[ҵ��ID]
				model.setZd2(form.getZd30()); // ҽ����
				model.setZd3(form.getZd30()); // ҽ����		
			}
		}
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			YlbxshForm upForm = new YlbxshForm();
			upForm.setSqid(form.getSqid());
			if("13573".equals(Base.xxdm)) {
				if(zhzt.equals(Constants.SH_TG)) {
					upForm.setZd30(form.getZd30());
				}
			}
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//���״̬Ϊͨ��
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				YlbxjgForm ylbxjgForm = new YlbxjgForm();
				form = getModel(form);
				if("13573".equals(Base.xxdm)) {
					form.setZd30(form.getZd30());
				}
        		BeanUtils.copyProperties(ylbxjgForm, StringUtils.formatData(form));
        		ylbxjgForm.setSjly("1");
        		YlbxjgDao ylbxjgDao = new YlbxjgDao();
        		ylbxjgDao.runInsert(ylbxjgForm);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/** 
	 * @����:������˱���
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(YlbxshForm t, User user) {
		String[] ids = t.getId();
		String[] splcs = t.getSplcs();
		String[] gwid = t.getGwids();
		// ======= ҵ���ֶ� begin======
		String[] xhs = t.getXhs();
		// ======= ҵ���ֶ� end======
		List<String> fails = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			YlbxshForm model = new YlbxshForm();
			model.setSqid(ids[i]);
			model.setSplc(splcs[i]);
			model.setGwid(gwid[i]);
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
	 * @����:����ҽ�Ʊ������
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean newCancelSh(YlbxshForm model, User user){
		boolean resultYlbx = false;
		try {
			model = getModel(model);
			//���������
			resultYlbx = dao.updateYlbxsq(model.getSqid(), Constants.YW_SHZ);
			if(resultYlbx && Constants.YW_TG.equals(model.getShzt())){
				//ɾ�������
				resultYlbx = dao.deleteYlbxjg(model.getSqid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultYlbx;
	}

	/** 
	 * ����
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxshForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getExportAllList(t, user);
	}
	
	/**
	 * 
	 * @����:ȡ�������״̬����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-5 ����03:06:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getLastShxx(String sqid) {		
		return dao.getLastShxx(sqid);	
	}
	
	/**
	 * 
	 * @����: ��˹����жϽ��������������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-6 ����01:23:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(String xh, String xn) {	
		return dao.checkExistForSave(xh, xn);
	}
}
