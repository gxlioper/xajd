package com.zfsoft.xgxt.pjpy.jtpj.jtpjsq;

/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-28 ����10:54:14 
 */
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-28 ����10:54:14
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjSqService extends
		SuperServiceImplExtend<JtpjSqForm, JtpjSqDao> {
	JtpjSqDao jsd = new JtpjSqDao();
	private final String SQRLX_LS = "1";
	private final String SQRLX_XS = "0";
	private ShlcInterface shlc = new CommShlcImpl();

	public JtpjSqService() {
		this.setDao(jsd);
	}

	public boolean submitRecord(String pkValue, String lcid, String xh)
			throws Exception {
		return shlc.runSubmit(pkValue, lcid, xh, "jtpjshbase.do",
				"jtpjsqbase.do");
	}

	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	/**
	 * 
	 * @����: ��ȡ�༶������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-6 ����02:44:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForQsxx(JtpjSqForm t,
			String bjdm) throws Exception {
		return jsd.getPageListForQsxx(t, bjdm);
	}
	
	public List<HashMap<String, String>> getQsxsxxList(JtpjSqForm t) throws Exception {
		return jsd.getQsxsxxList(t);
	}

	/**
	 * 
	 * @����: ��ȡ����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����04:17:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param userTpye
	 * @return String ��������
	 */
	public String getSqrlx(String userTpye) {
		if ("stu".equals(userTpye)) {
			return SQRLX_XS;
		}
		return SQRLX_LS;
	}

	/**
	 * 
	 * @����: ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-4 ����09:42:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean save(JtpjSqForm myForm) throws Exception {
		myForm.setSqsj(GetTime.getNowTime4());
		myForm.setSqxn(Base.currXn);
		myForm.setSqxq(Base.currXq);
		myForm.setShzt(Constants.YW_WTJ);
		return runInsert(myForm);
	}

	/**
	 * 
	 * @����: �޸�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-4 ����09:42:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean update(JtpjSqForm myForm) throws Exception {
		myForm.setSqsj(GetTime.getNowTime4());
		return runUpdate(myForm);
	}

	/**
	 * 
	 * @����: �༶����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����11:30:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return String ��������
	 */
	public String getBjrs(String bjdm) {
		return jsd.getBjrs(bjdm);
	}

	/**
	 * 
	 * @����: �༶������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����11:29:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return String ��������
	 */
	public String getQss(String bjdm) {
		return jsd.getQss(bjdm);
	}

	/**
	 * 
	 * @����: �༶��������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����11:29:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getBzrxx(String bjdm) {
		return jsd.getBzrxx(bjdm);
	}

	/**
	 * 
	 * @����: �༶����Ա��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����11:29:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getFdyxx(String bjdm) {
		return jsd.getFdyxx(bjdm);
	}

	/**
	 * 
	 * @����: ѧԺ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����01:41:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return String ��������
	 */
	public String getXyrs(String xydm) {
		return jsd.getXyrs(xydm);
	}

	/**
	 * 
	 * @����: ѧԺ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����01:41:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return String ��������
	 */
	public String getXyQss(String xydm) {
		return jsd.getXyQss(xydm);
	}

	/**
	 * @return the jsd
	 */
	public JtpjSqDao getJsd() {
		return jsd;
	}

	/**
	 * @param jsdҪ���õ�
	 *            jsd
	 */
	public void setJsd(JtpjSqDao jsd) {
		this.jsd = jsd;
	}

	/** 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�qlm
	 * @���ڣ�2014-5-26 ����11:18:56
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkSfktj(JtpjSqForm model) {
		String num = dao.checkSfktj(model.getJxid());
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @����: ��ӡWord�ǼǱ��Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-4 ����11:30:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 */
	public HashMap<String, String> getDjbInfo(String sqid) {
		return dao.getDjbInfo(sqid);
	}
	
	public HashMap<String, String> getDjb(String sqid) {
		return dao.getDjb(sqid);
	}
	
	public String getBjmc(String bjdm){
		return dao.getBjmc(bjdm);
	}
	
	/**
	 * 
	 * @����:�����Ƿ񲻴��������¼�������ڷ���true
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-16 ����11:20:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxid
	 * @param jtpjid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExists(String jxid,String pjjtid,String pjjtmc,String sqid,String flag){
		return dao.checkIsNotExists(jxid, pjjtid,pjjtmc,sqid,flag);
	}
	
	/**
	 * 
	 * @����: ��ȡ�԰༶Ϊ������λ����������������λ��Ϣ��ѧԺ���꼶�ȣ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-17 ����11:10:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjmc
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getBjxgxx(String bjmc){
		return dao.getBjxgxx(bjmc);
	}

	public HashMap<String,String> getjtpjInfo(String sqid) {
		return dao.getjtpjInfo(sqid);
	}

	public List<HashMap<String,String>> getbjgbInfo(String bjdm) {
		return dao.getbjgbInfo(bjdm);
	}
}
