/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-26 ����02:02:28 
 */  
package com.zfsoft.xgxt.dekt.xfsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.DailyRollingFileAppender;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgDao;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgForm;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgService;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqDao;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dekt.xfjg.DektxfjgDao;
import com.zfsoft.xgxt.dekt.xfjg.DektxfjgForm;
import com.zfsoft.xgxt.dekt.xfjg.DektxfjgService;
import com.zfsoft.xgxt.dekt.xfsq.DektxfsqDao;
import com.zfsoft.xgxt.dekt.xfsq.DektxfsqForm;


public class DektxfshService extends SuperServiceImpl<DektxfshForm, DektxfshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private DektxfjgDao dektxfjgDao=new DektxfjgDao();
	private DektxfsqDao dektxfsqDao=new DektxfsqDao();
	
	public boolean saveSh(DektxfshForm form, User user) {
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		model.setZd1("ѧ��");
		model.setZd2(form.getXf());
		model.setZd3(form.getXf());
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("dekt_xfsh_list.do");
		model.setTzljsq("dekt_xfsq_sqlb.do");
		boolean result = false;
		try {
			String shzt = shlc.runAuditing(model);
			result = dao.updateShzt(form.getSqid(),shzt);
			// ���浽�����
			if (shzt.equalsIgnoreCase(Constants.YW_TG)) {
				//��ȡ����
				DektxfjgForm dektxfjgForm = new DektxfjgForm();
				DektxfshForm dektxfshForm=new DektxfshForm();
				dektxfshForm = dao.getModel(form);
				BeanUtils.copyProperties(dektxfjgForm, StringUtils.formatData(dektxfshForm));
				//ɾ�������
				if(dektxfjgDao.checkExist(dektxfjgForm)){
					dektxfjgDao.deleteExist(dektxfjgForm);
				}
				dektxfjgForm.setXf(form.getXf());
				dektxfjgForm.setSjly("1");
				result=dektxfjgDao.runInsert(dektxfjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @�������������
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public String savePlsh(DektxfshForm t, User user) throws Exception {
		DektxfshForm model = new DektxfshForm();
		String[] sqids = t.getSqids();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs=t.getSplcs();
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = sqids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			model.setGwid(gwids[i]);
			model.setSqid(sqids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setSplc(splcs[i]);

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
	 * @���������һ����������
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public boolean cxshdel(DektxfshForm form) throws Exception {
		boolean result = dektxfsqDao.updateShzt(form.getSqid(), Constants.YW_SHZ);
		if (result) {
			// ɾ��������е�������
			result=dektxfjgDao.deleteBysqid(form.getSqid());
		}
		return result;
	}

	public Map<String, String> getView(DektxfshForm model) throws Exception {
		return dao.getView(model);
	}
	
}
