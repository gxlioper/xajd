/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-27 ����05:04:19 
 */
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsq;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-27 ����05:04:19
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class SthdsqService extends SuperServiceImpl<SthdsqForm, SthdsqDao> {

	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	SthdsqDao dao = new SthdsqDao();

	/**
	 * 
	 * @����:�������뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-27 ����02:26:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveSthdsq(SthdsqForm model) throws Exception {
		String hdid = UniqID.getInstance().getUniqIDHash();
		model.setHdid(hdid);
		String splc = dao.getShlcID();
		model.setSplc(splc);
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
				result = shlc.runSubmit(hdid, splc, model.getHdid(), "stgl_sthdgl_sthdsh.do", "stgl_sthdgl_sthdsq.do");
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * @����:���Ż�����޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-27 ����02:26:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveEditSthdsq( SthdsqForm model) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getHdid(), splc, model.getHdid(), "stgl_sthdgl_sthdsh.do", "stgl_sthdgl_sthdsq.do");
			}
		} else {
			result = runUpdate(model);
		}
		return result;
	
	}
	/**
	 * 
	 * @����:���Ż�����ύ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-27 ����03:17:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitSthdsq(SthdsqForm model) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getHdid(), splc, model.getHdid(), "stgl_sthdgl_sthdsh.do", "stgl_sthdgl_sthdsq.do");
			}
			return result;
	}

	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-27 ����03:02:42
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
	public boolean isHaveSqJl(SthdsqForm model) throws Exception {
		return dao.isHaveSqJl(model);
	}
    /**
     * 
     * @����:��ʼ�������б�
     * @���ߣ�����[���ţ�1104]
     * @���ڣ�2015-7-27 ����10:59:11
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param request
     * @param user
     * @throws Exception
     * void �������� 
     * @throws
     */
	public void initParam(HttpServletRequest request,User user) throws Exception{
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("sthdList", dao.getStxmList());
		
	}
	
	public SthdsqForm getSqxx(SthdsqForm model) throws Exception{
		return dao.getSqxx(model);
	}
	

}
