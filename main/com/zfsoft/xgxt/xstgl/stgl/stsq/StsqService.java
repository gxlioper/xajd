/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-3 ����05:04:19 
 */
package com.zfsoft.xgxt.xstgl.stgl.stsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.message.MessageKey;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-8-3 ����05:04:19
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class StsqService extends SuperServiceImpl<StsqForm, StsqDao> {

	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	StsqDao dao = new StsqDao();

	/**
	 * 
	 * @����:�������뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����02:26:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveStsq(StsqForm model) throws Exception {
		String Sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(Sqid);
		model.setStid(Sqid);
		String splc = dao.getShlcID(model);
		//model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
		} else {
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}

		// ����������Ϣ
		boolean result = dao.runInsert(model);
		//����ָ����ʦ��
		boolean aa =dao.saveZdls(Sqid, model.getStid(), model.getXhs());
		// ���������Ϣ
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(Sqid, splc, model.getSqid(), "stgl_stgl_stsh.do", "stgl_stgl_stsq.do");
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * @����:���������޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����02:26:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public String saveEditStsq( StsqForm model) throws Exception {
		boolean result = false;
		String messageKey = "";
		// ����ύ���������״̬
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID(model);
			model.setSplc(splc);
			result = runUpdate(model);
			//����ָ����ʦ��
			boolean aa =dao.saveZdls(model.getSqid(), model.getStid(), model.getXhs());
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "stgl_stgl_stsh.do", "stgl_stgl_stsq.do");
			}
			messageKey= result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
		} else {
			result = runUpdate(model);
			//����ָ����ʦ��
			boolean aa =dao.saveZdls(model.getSqid(), model.getStid(), model.getXhs());
			messageKey= result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
		}
		return messageKey;
	
	}
	/**
	 * 
	 * @����:���������ύ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-8-3 ����03:17:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitStsq(StsqForm model) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID(model);
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "stgl_stgl_stsh.do", "stgl_stgl_stsq.do");
			}
			return result;
	}

	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����03:02:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	/**
	 * �ж��Ƿ��������¼
	 */
	public boolean isHaveSqJl(StsqForm model) throws Exception {
		return dao.isHaveSqJl(model);
	}
	
	public HashMap<String,String> getSqxx(StsqForm model) throws Exception{
		return dao.getSqxx(model);
	}
	
	public String getBmmc(String bmdm) {
		return dao.getBmmc(bmdm);
	}
	
	public List<HashMap<String, String>> getBmList() {
		return dao.getBmList();
	}
	
	//���������������ѧ����ѧ��ѡ��ҳ��
	public List<HashMap<String, String>> getXsxxList(StsqForm model, User user) throws Exception {
		return dao.getXsxxList(model, user);
	}
	
	//�����������������ʦ����ʦѡ��ҳ��
	public List<HashMap<String, String>> getTeaxxList(StsqForm model, User user) throws Exception {
		return dao.getTeaList(model, user);
	}

	//��ȡ��ʦְ���б�
	public List<HashMap<String, String>> getZclblist(){
		return dao.getZclblist();
	}
	
	//��ȡ���Ŵ����б�
	public List<HashMap<String, String>> getBbdmlist(){
		return dao.getBbdmlist();
	}

	//ָ����ʦ��Ϣ�б�
	public List<HashMap<String,String>> getZdlsInfo(StsqForm model) {
		return dao.getZdlsInfo(model);
	}

	public List<HashMap<String,String>> getstxjList() {
		List<HashMap<String,String>> stxjList = new ArrayList<HashMap<String, String>>();
		for (int i = 1; i <6 ; i++) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put( "xj", String.valueOf(i));
			stxjList.add(map);
		}
		return stxjList;
	}

	public List<HashMap<String,String>> getsqAll(StsqForm model, User user) throws Exception {
		return dao.getsqAll(model,user);
	}
}
