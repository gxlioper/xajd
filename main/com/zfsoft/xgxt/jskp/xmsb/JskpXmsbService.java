package com.zfsoft.xgxt.jskp.xmsb;


import java.util.HashMap;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ʵ����
 * @�๦������: ��Ŀ�걨
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2017-7-5 ����04:45:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JskpXmsbService extends SuperServiceImpl<JskpXmsbForm, JskpXmsbDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SJZT = "0";// ���״̬��Ϊ1������״̬Ϊ0
	private static final String LCLX_SB="sb";//�������ͣ��걨
	private ShlcInterface shlc = new CommShlcImpl();
	private CsszDao jcszDao = new CsszDao();
	private JskpXmsbDao dao = new JskpXmsbDao();

	/**
	 * 
	 * @����:�жϼ���������Ƿ��ظ��걨
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-25 ����09:49:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public String xmsbCheck(JskpXmsbForm model, User user) throws Exception {
		return dao.xmsbCheck(model, user);
	}

	/**
	 * 
	 * @����:�걨����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-7 ����10:59:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXmsb(JskpXmsbForm model) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = jcszDao.getSplc(LCLX_SB).get("splc");
		model.setSqid(sqid);
		model.setSbsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplcid(splc);
		model.setShzt(Constants.YW_SHZ);// �����
		// ����������Ϣ
		boolean result = dao.runInsert(model);
		// ���������Ϣ
		result = shlc.runSubmit(sqid, splc, model.getXh(), "jskp_xmsh.do", "jskp_xmsb.do");
		return result;
	}
	
	
	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿���ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-7 ����10:59:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @����: ����������Ϊ0ʱ������б�ѧ�Ų鿴��xhLink��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-22 ����04:00:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXxck(String id) throws Exception {
		return dao.getXxck(id);
	}
}
