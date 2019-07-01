/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��4�� ����2:22:28 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը��������Service
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��4�� ����2:22:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwSqService extends SuperServiceImpl<ZyfwSqForm,ZyfwSqDao>{
	
	private ShlcInterface shlc = new CommShlcImpl();
	ZyfwSqDao zyfwSqDao = new ZyfwSqDao();

	/** 
	 * @����:�жϵ�ǰʱ���Ƿ��������¼�����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��5�� ����3:59:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwSqForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isRepeat(ZyfwSqForm zyfwSqForm) {
		
		return dao.isRepeat(zyfwSqForm);
	}

	/**
	 * @����:־Ը�������루�������ı���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��5�� ����4:13:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwSqForm
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean zyfwSqSaveForAdd(ZyfwSqForm zyfwSqForm) throws Exception {
		
		String fwid = UniqID.getInstance().getUniqIDHash();
		zyfwSqForm.setFwid(fwid);
		//��ȡ�������������õ��������
		String splc = zyfwSqDao.getShlcID();
		zyfwSqForm.setSplc(splc);
		if ("submit".equals(zyfwSqForm.getType())) {
			zyfwSqForm.setShzt(Constants.YW_SHZ);// �����
		} else {
			zyfwSqForm.setShzt(Constants.YW_WTJ);// δ�ύ
		}
		// ����������Ϣ
		boolean result = dao.runInsert(zyfwSqForm);
		// ���������Ϣ
		if ("submit".equals(zyfwSqForm.getType())) {
			if (result) {
				result = shlc.runSubmit(fwid, splc, zyfwSqForm.getXh(), 
						"xsxx_zyfwgl_sh.do?method=zyfwShList", "xsxx_zyfwgl_sq.do?method=zyfwSqList");
			}
		}
		return result;
	}

	/**
	 * @����:־Ը�������루�޸ģ��ı���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��5�� ����6:02:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwSqForm
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean zyfwSqSaveForEdit(ZyfwSqForm zyfwSqForm) throws Exception {
		
		boolean result = false;
		// ����ύ���������״̬
		if ("submit".equalsIgnoreCase(zyfwSqForm.getType())) {
			zyfwSqForm.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID();
			zyfwSqForm.setSplc(splc);
			result = runUpdate(zyfwSqForm);
			if (result) {
				result = shlc.runSubmit(zyfwSqForm.getFwid(), splc,zyfwSqForm.getXh() , 
						"xsxx_zyfwgl_sh.do?method=zyfwShList", "xsxx_zyfwgl_sq.do?method=zyfwSqList");
			}
		} else {
			result = runUpdate(zyfwSqForm);
		}
		return result;
	}

	/**
	 * @������־Ը����������ύ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����10:47:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwSqForm
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean zyfwSqSubmit(ZyfwSqForm zyfwSqForm) throws Exception {
		
		boolean result = false;
		zyfwSqForm.setShzt(Constants.YW_SHZ);// �����
		String splc = dao.getShlcID();
		zyfwSqForm.setSplc(splc);
		result = runUpdate(zyfwSqForm);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(zyfwSqForm.getFwid(), splc,zyfwSqForm.getXh() , 
					"xsxx_zyfwgl_sh.do?method=zyfwShList", "xsxx_zyfwgl_sq.do?method=zyfwSqList");
		}
		return result;
	}

	/**
	 * @����:־Ը��������ĳ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����11:52:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fwid
	 * @param lcid
	 * @return
	 * boolean �������� 
	 * @throws Exception  
	 */
	public boolean zyfwSqCancel(String fwid, String lcid) throws Exception {
		
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = shlc.firstStepCancle(fwid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			ZyfwSqForm zyfwSqForm = new ZyfwSqForm();
			zyfwSqForm.setFwid(fwid);
			zyfwSqForm.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(fwid)) > 0) {
				zyfwSqForm.setShzt(Constants.YW_YTH);
			} else {
				zyfwSqForm.setShzt(Constants.YW_WTJ);
			}
			result = this.runUpdate(zyfwSqForm);
		}
		return result;
	}
	
	/**
	 * @����:��д����ѯһ��������ϸ��Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����11:52:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fwid
	 * @param lcid
	 * @return
	 * boolean �������� 
	 * @throws Exception  
	 */
	public ZyfwSqForm getModel(String fwid) throws Exception{
		return dao.getModel(fwid);
	}

}
