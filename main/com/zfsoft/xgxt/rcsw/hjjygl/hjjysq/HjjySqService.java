/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-9 ����10:45:19 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.hjjysq;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-5-9 ����10:45:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjjySqService extends SuperServiceImpl<HjjySqForm, HjjySqDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	HjjySqDao dao = new HjjySqDao();

	

	/**
	 * 
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����11:44:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveHjjySq(HjjySqForm model) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
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
				result = shlc.runSubmit(sqid, splc, model.getXh(), "rcsw_hjjy_jysh.do", "rcsw_hjjy_jysq.do");
			}
		}
		return result;
	
	}
	

	/**
	 * 
	 * @����:�޸����뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����11:44:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveEditHjjySq( HjjySqForm model) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			String splc = dao.getShlcID();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "rcsw_hjjy_jysh.do", "rcsw_hjjy_jysq.do");
			}
		} else {
			result = runUpdate(model);
		}
		return result;
	
	}
    /**
     * 
     * @����:�ύ����
     * @���ߣ�xiaxia[���ţ�1104]
     * @���ڣ�2016-5-9 ����11:44:51
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param model
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
	public boolean submitHjjySq(HjjySqForm model) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "rcsw_hjjy_jysh.do", "rcsw_hjjy_jysq.do");
			}
			return result;
	}

	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-05-09 ����03:02:42
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
	public boolean isHaveGhJl(HjjySqForm model) throws Exception {
		return dao.isHaveGhJl(model);
	}
	public HashMap<String, String> getJyxx(String sqid) {
		return dao.getJyxx(sqid);
	}


		
	}
