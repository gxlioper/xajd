package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsh;

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
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjg.XsgzzbjgDao;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjg.XsgzzbjgForm;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq.XsgzzbsqService;

public class XsgzzbshService extends SuperServiceImpl<XsgzzbshForm, XsgzzbshDao> {

	private XsgzzbshDao dao = new XsgzzbshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public XsgzzbshService() {
		super.setDao(dao);
	}
	
	/**
	 * �༶�ܱ�
	 */
	public List<HashMap<String, String>> getPageListBj(XsgzzbshForm t, User user)
		throws Exception {
		return dao.getPageListBj(t, user);
	}
	
	@Override
	public List<HashMap<String, String>> getAllList(XsgzzbshForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		String gzzblx = t.getGzzblx();
		if("bj".equals(gzzblx)){
			return dao.getPageListBj(t, user);
		}
		return dao.getPageList(t, user);
	}

	/**
	 * �༶�ܱ�
	 */
	public XsgzzbshForm getModelBj(XsgzzbshForm t) throws Exception{
		return dao.getModelBj(t);
	}
	
	/**
	 * ����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(XsgzzbshForm form,User user){
		String gzzblx = form.getGzzblx();
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
		model.setSqrid(form.getLrr());
		// ======= ҵ���ֶ� end======
		String sqURL = "rcsw_xsgzzb_xsgzzbsq.do";
		String shURL = "rcsw_xsgzzb_xsgzzbsh.do";
		if("bj".equals(gzzblx)){
			sqURL = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
			shURL = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
		}
		model.setTzlj(shURL);
		model.setTzljsq(sqURL);
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XsgzzbshForm upForm = new XsgzzbshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			dao.setTableInfo(form);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//���״̬Ϊͨ��
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				XsgzzbjgForm xsgzzbjgForm = new XsgzzbjgForm();
				if("bj".equals(gzzblx)){
					form = getModelBj(form);
				}else{
					form = getModel(form);
				}
        		BeanUtils.copyProperties(xsgzzbjgForm, StringUtils.formatData(form));
        		xsgzzbjgForm.setSjly("1");
        		XsgzzbjgDao xsgzzbjgDao = new XsgzzbjgDao();
        		xsgzzbjgForm.setGzzblx(gzzblx);
        		XsgzzbsqService xsgzzbsq = new XsgzzbsqService();
        		if(Base.xxdm.equalsIgnoreCase("13815")){
        			String filegid = xsgzzbsq.getFileGID(xsgzzbjgForm.getSqid());
            		xsgzzbjgForm.setFilegid(filegid);
        		}
        		xsgzzbjgDao.setTableInfo(xsgzzbjgForm);
        		xsgzzbjgDao.runInsert(xsgzzbjgForm);	
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
	public String savePlsh(XsgzzbshForm t, User user) {
		String[] ids = t.getId();
		String[] splcs = t.getSplcs();
		String[] gwid = t.getGwids();
		// ======= ҵ���ֶ� begin======
		String[] lrrs = t.getLrrs();
		// ======= ҵ���ֶ� end======
		List<String> fails = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			XsgzzbshForm model = new XsgzzbshForm();
			model.setGzzblx(t.getGzzblx());
			model.setSqid(ids[i]);
			model.setSplc(splcs[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			// ======= ҵ���ֶ� begin======
			model.setLrr(lrrs[i]);
			// ======= ҵ���ֶ� end======
			
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				fails.add(lrrs[i]);
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
	 * @����:�����ܱ����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean newCancelSh(XsgzzbshForm model, User user){
		String gzzblx = model.getGzzblx();
		boolean resultXsgzzb = false;
		boolean resultXsgzzbjg = false;
		try {
			//���������
			resultXsgzzb = dao.updateXsgzzbsq(model.getSqid(), Constants.YW_SHZ, gzzblx);
			if(resultXsgzzb){
				//ɾ�������
				resultXsgzzbjg = dao.deleteXsgzzbjg(model.getSqid(), gzzblx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultXsgzzbjg;
	}

}
