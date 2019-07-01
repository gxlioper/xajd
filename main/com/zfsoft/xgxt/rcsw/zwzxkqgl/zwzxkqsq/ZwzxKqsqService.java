/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:37:50 
 */
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.jcsz.JcszDao;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgDao;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-26 ����02:37:50
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZwzxKqsqService extends SuperServiceImpl<ZwzxKqsqForm, ZwzxKqsqDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SJZT = "0";// ���״̬��Ϊ1������״̬Ϊ0
	private ShlcInterface shlc = new CommShlcImpl();
	private JcszDao jcszDao = new JcszDao();
	private ZwzxKqjgDao kqjgDao = new ZwzxKqjgDao();
	private ZwzxKqsqDao dao = new ZwzxKqsqDao();

	/**
	 * 
	 * @����:�ж��Ƿ������д��¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����04:29:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean isHaveSqJl(ZwzxKqsqForm model, String czlx) throws Exception {
		return dao.isHaveSqJl(model, czlx);
	}

	/**
	 * 
	 * @����:��ȡһ�������¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����06:43:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             ZwzxKqsqForm ��������
	 * @throws
	 */
	public ZwzxKqsqForm getKqsq(ZwzxKqsqForm t) throws Exception {
		return dao.getKqsq(t);
	}

	/**
	 * 
	 * @����:������д���뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����04:42:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveKqsq(ZwzxKqsqForm model, List<ZwzxKqsqForm> qqxxList) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = jcszDao.getModel().getSplc();
		model.setSqid(sqid);
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		// ��������������趨��ʼֵ
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
		} else {
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}
		// ����������Ϣ
		boolean result = dao.runInsert(model);
		// ���������Ϣ
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getBjdm(), "rcsw_zwzxkq_kqsh.do", "rcsw_zwzxkq_kqsq.do");
			}
		}
		return qqxsPlbc(model, qqxxList);
	}
	/**
	 * 
	 * @����:�����޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����07:21:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveEditKqsq(ZwzxKqsqForm model, List<ZwzxKqsqForm> qqxxList) throws Exception {
		boolean result = false;
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			String splc = jcszDao.getModel().getSplc();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getBjdm(), "rcsw_zwzxkq_kqsh.do", "rcsw_zwzxkq_kqsq.do");
			}
		}
		else {
			result = runUpdate(model);
			
		}
		//ɾ��ȱ��ѧ����Ϣ���ٲ���
		result = kqjgDao.delQqxs(model.getSqid());
		return qqxsPlbc(model, qqxxList);
	}
	/**
	 * 
	 * @����:�����ύ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����08:04:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitKqsq(HttpServletRequest request, ZwzxKqsqForm model) throws Exception {
		boolean result = false;
			String values = request.getParameter("values");
			model.setSqid(values);
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			String splc = jcszDao.getModel().getSplc();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getBjdm(), "rcsw_zwzxkq_kqsh.do", "rcsw_zwzxkq_kqsq.do");
			}
			return result;
	}
	
	/**
	 * 
	 * @����:ѧ��������Ϣ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����07:18:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws SQLException boolean ��������
	 * @throws
	 */
	private boolean qqxsPlbc(ZwzxKqsqForm model, List<ZwzxKqsqForm> qqxxList) throws SQLException {
		List<String[]> qqxsxxList = new ArrayList<String[]>();
		String[] qqxsxx = null;
		for (ZwzxKqsqForm kqsqForm : qqxxList) {
			qqxsxx = new String[16];
			qqxsxx[0] = model.getSqid();
			qqxsxx[1] = kqsqForm.getXh();
			qqxsxx[2] = model.getXn();
			qqxsxx[3] = model.getXq();
			qqxsxx[4] = model.getSqsj();
			qqxsxx[5] = model.getCclxdm();
			qqxsxx[6] = kqsqForm.getQqlxdm();
			qqxsxx[7] = kqsqForm.getKkjs();
			qqxsxx[8] = model.getBjdm();
			qqxsxx[9] = model.getCcrq();
			qqxsxx[10] = model.getJlf();
			qqxsxx[11] = model.getJlr();
			qqxsxx[12] = kqsqForm.getYlzd1();
			qqxsxx[13] = "";
			qqxsxx[14] = "";
			qqxsxx[15] = SJZT;
			qqxsxxList.add(qqxsxx);
		}
		return kqjgDao.qqxsPlbc(qqxsxxList);
	}
	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����08:45:01
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
	 * 
	 * @����: ��������ʵʱ����Ӧ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-10 ����10:40:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public String getYdrsSzly(String bjdm){
		return dao.getYdrsSzly(bjdm);
	}

}
