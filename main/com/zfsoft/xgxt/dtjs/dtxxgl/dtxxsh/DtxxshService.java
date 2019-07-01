/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqService;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: �������service
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:06:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DtxxshService extends SuperServiceImpl<DtxxshForm, DtxxshDao> implements Constants{
	DtxxshDao dao = new DtxxshDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	private BaseDbcz dbcz = new BaseDbcz();
	public DtxxshService() {
		this.setDao(dao);
		dbcz.setShPath("dtxxshbase.do");
		dbcz.setGnmkMc("������Ϣ����");
		dbcz.setXmmc("������Ϣ����");
	}

	@Override
	public DtxxshForm getModel(DtxxshForm t) throws Exception {
		DtxxshForm df = super.getModel(t);
		DtxxsqService ds = new DtxxsqService();
		HashMap<String, String> otherP = ds.getAllProperty(t.getDtxxsqid());
		df.setJdmc(otherP.get("jdmc"));
		// ���ʹ��
		df.setShid(dao.getShid(t.getDtxxsqid(), t.getGwid()));
		df.setGwid(t.getGwid());
		return df;
	}

	/**
	 * ��ѯѧ��������Ϣ
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetail(xh);
	}

	/**
	 * 
	 * @����:����������Ϣ״̬
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-24 ����06:29:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param dqzt
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean updateSqxx(String sqid, String dqzt) throws Exception {
		/*
		 * DtxxshForm upForm = new DtxxshForm(); upForm.setDtxxsqid(sqid);
		 * upForm.setShzt(dqzt);
		 */
		return dao.updateSqxx(sqid, dqzt);
	}

	/**
	 * 
	 * @����:���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:47:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return boolean ��������
	 */
	public boolean saveSh(DtxxshForm form, User user) {
		//���������
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShzt());
		model.setThgw(form.getThgw());
		model.setGwid(form.getGwid());//
		model.setYwid(form.getDtxxsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("dtxxshbase.do");
		model.setTzljsq("dtxxsqbase.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			reuslt = updateSqxx(form.getDtxxsqid(), zhzt);
			// ���״̬Ϊͨ��de ������б����������
			if (zhzt.equalsIgnoreCase(Constants.SH_TG)) {
				DtxxjgService jgs = new DtxxjgService();
				reuslt=jgs.saveForDtxxsq(form);
			}
			if (reuslt) {
				// ���ô�����Ϣ
				dbcz.shPush(form.getDtxxsqid(), form.getSplc());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * 
	 * @����:�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-28 ����09:36:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param sqid
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean cancel(String shlc, String sqid) throws Exception {
		boolean result = false;
		// ��������״̬����
		DtxxsqForm sq=new DtxxsqForm();
		sq.setDtxxsqid(sqid);
		sq.setShzt(Constants.YW_SHZ);
		DtxxsqService sqs=new DtxxsqService();
		dao.deleteDtxxjgForDtxxsqId(sqid);
		DtxxsqForm df= dao.getModel(sqid);
		dbcz.cancel(df.getDtxxsqid(), df.getSplc());
		result = sqs.runUpdate(sq) ? true : false;
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-16 ����09:44:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(DtxxshForm t, User user) throws Exception {
		String[] ids = t.getIds();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		//�޸�bug
		String[] jddms = t.getJddms();
		
		List<String> failXhs = new ArrayList<String>();
		//ѭ���������
		for (int i = 0, n = ids.length; i < n; i++) {
			DtxxshForm model = new DtxxshForm();
			model.setDtxxsqid(ids[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setXh(xhs[i]);
			model.setSplc(splcs[i]);
			model.setJddm(jddms[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	

}
