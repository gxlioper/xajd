/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-5 ����02:21:29 
 */  
package com.zfsoft.xgxt.axcs.wpsq;

import java.util.ArrayList;

import com.zfsoft.xgxt.axcs.wpsz.WpszDao;
import com.zfsoft.xgxt.axcs.wpsz.WpszForm;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-5 ����02:21:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WpsqService extends SuperServiceImpl<WpsqForm,WpsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	WpsqDao dao  = new WpsqDao();
	
	public ArrayList<String[]> getWpsqList(WpszForm t) throws Exception{
		return dao.getWpsqList(t);
		
	}
	
	/**
	 * 
	 * @����:ѧ��������Ʒ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����11:44:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean wpsqBc(String[] xmdm, WpsqForm t) throws Exception {
		if (xmdm == null || xmdm.length == 0) {
			return false;
		}
		for (String wp : xmdm) {
			WpsqForm model = new WpsqForm();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setXmdm(wp);
			model.setType(t.getType());
			model.setSqid(t.getSqid());
			saveWpsq(model, t.getXh());
		}
		return true;
	}
	/**
	 * 
	 * @����:��Ʒ���뵥������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����11:40:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveWpsq(WpsqForm model, String userName) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		WpszDao wpszDao = new WpszDao();
		WpszForm wpszForm = wpszDao.getModel(model.getXmdm());
		String splc = wpszForm.getSplc();
		model.setSqr(userName);
		model.setXn(wpszForm.getXn());
		model.setSqid(sqid);
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
				result = shlc.runSubmit(sqid, splc, model.getXh(),"axcs_axcswpsh.do", "axcs_axcswpsq_tea.do");
			}
		}
		return result;
	}
	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����01:48:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	

}
