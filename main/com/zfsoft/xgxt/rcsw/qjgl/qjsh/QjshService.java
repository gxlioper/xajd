/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import com.zfsoft.xgxt.rcsw.qjgl.qjgz.QjgzForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjgz.QjgzService;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgDao;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: �������service
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:06:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjshService extends SuperServiceImpl<QjshForm, QjshDao> {
	QjshDao dao = new QjshDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	private BaseDbcz dbcz=new BaseDbcz();
	public QjshService() {
		this.setDao(dao);
		dbcz.setShPath("qjshbase.do");
		dbcz.setGnmkMc("�������");
		dbcz.setXmmc("��ٹ���");
	}

	/**
	 * 
	 * @����:��ȡ��Ӧ����ٹ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����04:26:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public HashMap<String, String> getSplc(QjshForm model) throws Exception {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("splcid", "�������");
		if (StringUtils.isNotNull(model.getSplcid())) {// �Ѿ����ڶ�Ӧ����id��ֱ�ӷ���
			data.put("splcid", model.getSplcid());
			return data;
		}
		// ��ȡ�����Ӧ��ٹ���
		QjgzService qjgz = new QjgzService();
		List<HashMap<String, String>> list = qjgz.getAllList(new QjgzForm());
		for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			Integer qjtsInt = Integer.parseInt(model.getQjts());
			if (Integer.parseInt(kssj) == qjtsInt
					&& qjtsInt == Integer.parseInt(jssj)) {
				return hm;
			}
			if (Integer.parseInt(kssj) < qjtsInt
					&& qjtsInt <= Integer.parseInt(jssj)) {
				return hm;
			}
		}
		return data;
	}

	@Override
	public QjshForm getModel(QjshForm t) throws Exception {
		String gwid = t.getGwid();
		t = super.getModel(t);
		if(t!=null){
			// ��ѧ�ڴ���ת��Ϊѧ������
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
			t.setShid(dao.getShid(t.getQjsqid(),gwid));
		}
		if(Base.xxdm.equals("10695")){//���������ѧר��
			QjsqService sqService = new QjsqService();
			t.setJtgjmc(sqService.getJtgjmc(t.getJtgj()));
		}
		return t;
	}

	/**
	 * 
	 * @����:�Ƿ���Խ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����07:19:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws
	 */
	public boolean isCanAdd(QjshForm qf) {
		return dao.checkQjlx(qf);
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
	 * @����:����������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-11 ����12:00:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getSplcInfo(QjshForm model) {
		if (StringUtil.isNull(model.getQjsqid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getSplcInfo(model);
	}
	
	public String cancelSh(QjshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String cancelFlg = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplcid());
		//�������״̬����
		QjshForm upForm = new QjshForm();
		upForm.setQjsqid(model.getQjsqid());
		upForm.setShzt(Constants.YW_SHZ);
		dao.runUpdate(upForm, model.getQjsqid());
		return cancelFlg;
	}

	/**
	 * @throws Exception 
	 * 
	 * @����:���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:47:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return boolean ��������
	 * @throws
	 */
	public boolean saveSh(QjshForm form, User user) throws Exception {
		// ��˲���Model��ʼ��
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
		model.setYwid(form.getQjsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("qjshbase.do");
		model.setTzljsq("qjsqbase.do");
	
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditing(model);
			QjshForm upForm = new QjshForm();
			upForm.setQjsqid(form.getQjsqid());
			upForm.setShzt(zhzt);
			// ���״̬Ϊ�˻�ʱ�޸�����������״̬
			if (zhzt.equalsIgnoreCase(Constants.SH_TH)){
				upForm.setQjzt(QjsqService._SQZT_CGZT);
			}
			reuslt = dao.runUpdate(upForm, form.getQjsqid());
			// ���״̬Ϊͨ��������ٽ�����б����������
			if (zhzt.equalsIgnoreCase(Constants.SH_TG)) {
				// ��ȡ���ݿ���������
				QjshForm data = getModel(form);
				QjjgForm qf = new QjjgForm();
				QjjgDao qjjg = new QjjgDao();
				// ��Ӧ���Ը��Ƶ������
				BeanUtils.copyProperties(qf, StringUtils.formatData(data));
				qf.setQjsqid(data.getQjsqid());
				qf.setQjzt(QjsqService._SQZT_CGZT);
				qf.setShzt(Constants.SH_TG);
				qjjg.runInsert(qf);
			}
		
		return reuslt;
	}

	public List<HashMap<String, String>> getQjlx() {
		return dao.getQjlx();
	}

	/**
	 * 
	 * @����:�Ƿ���ڶ�Ӧ�������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����06:58:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean isHaveSplcForTs(QjshForm model) throws Exception {
		HashMap<String, String> splc = getSplc(model);
		if (null == splc) {
			return false;
		}
		return true;
	}
	/** 
	 * @����:���һ���������
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param shlc �������ID
	 * @param ssydsqid ����ID
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(String shlc, String sqid) throws Exception{
		boolean result=false;
		//�������״̬����
		QjshForm upForm = new QjshForm();
		upForm.setQjsqid(sqid);
		upForm.setShzt(Constants.YW_SHZ);
		result=dao.runUpdate(upForm, sqid)&&dao.deleteQjjgForQjsqId(sqid)>0?true:false;
		
		upForm=dao.getModel(sqid);
		//���ô�����Ϣ
		dbcz.cancel(upForm.getQjsqid(), upForm.getSplcid());
		return result;
	}

	/** 
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-2-27 ����02:08:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	@TransactionControl
	public String batchSave(QjshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		
		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			QjshForm Form = new QjshForm();
			Form.setQjsqid(ids[i]);
			
			HashMap<String, String> tmpMap = shlc.getShxxByCondition(ids[i],gwids[i]);
			QjshForm s = dao.getModel(Form);
			
			
			Form.setGwid(gwids[i]);
			Form.setShyj(t.getShyj());
			Form.setShjg(t.getShzt());
			Form.setThgw(t.getThgw());
			Form.setSplcid(tmpMap.get("lcid"));
			Form.setXh(s.getXh());
			Form.setXn(s.getXn());
			Form.setXq(s.getXq());

			boolean isSuccess = saveSh(Form, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
}
