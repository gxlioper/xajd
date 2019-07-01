/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-3-26 ����09:35:45 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.bxbxsq;

import java.util.HashMap;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.bxgl.cssz.CsszDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-3-26 ����09:35:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BxbxSqService extends SuperServiceImpl<BxbxSqForm, BxbxSqDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SJZT = "0";// ���״̬��Ϊ1������״̬Ϊ0
	private ShlcInterface shlc = new CommShlcImpl();
	private CsszDao csszDao = new CsszDao();
	/**
	 * 
	 * @����:��ȡ���ձ�����ϸ��Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-27 ����03:15:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getBxsqxx(String sqid){
		return dao.getBxsqxx(sqid);
		
		
	}
	/**
	 * 
	 * @����:���ձ������뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-26 ����03:48:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveBxbxsq(BxbxSqForm model) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = csszDao.getModel().getSplc();
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
				result = shlc.runSubmit(sqid, splc, model.getXh(), "rcsw_bxgl_bxbxsh.do", "rcsw_bxgl_bxbxsq.do");
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @����:���ձ��������޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-27 ����02:59:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBxbxsq(BxbxSqForm model) throws Exception {
		String splc = csszDao.getModel().getSplc();
		model.setSplc(splc);
		// ��������������趨��ʼֵ
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
		} else {
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}
		// ����������Ϣ
		boolean result = dao.runUpdate(model);
		// ���������Ϣ
		if (!"save".equals(model.getType())) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "rcsw_bxgl_bxbxsh.do", "rcsw_bxgl_bxbxsq.do");
		}
		return result;
	}
	/**
	 * 
	 * @����:�����ύ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����04:41:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBxbxsq(BxbxSqForm model) throws Exception {
		boolean result =true;
		String splc = csszDao.getModel().getSplc();
		model.setSplc(splc);
		model.setShzt(Constants.YW_SHZ);// �����
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		// ����������Ϣ
		result = dao.runUpdate(model);
		// ���������Ϣ
		if (!"save".equals(model.getType())) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "rcsw_bxgl_bxbxsh.do", "rcsw_bxgl_bxbxsq.do");
		}
		return result;
	}
	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����04:55:11
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
	 * @������У�����ﱨ�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��28�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean ��������
	 */
	public boolean checkMzje(String bxje,String csfysj,String xh,String id){
		//�ڱ�������������ʱ��Ǳ��������£���У��������
		if(StringUtils.isNull(bxje)||StringUtils.isNull(csfysj)){
			return true;
		}
		return dao.checkMzje(bxje,csfysj,xh,id);
	}
	
	/**
	 * 
	 * @����: ��ȡ�ܶ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-21 ����07:02:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZe(String xh,String csfysj){
		return dao.getZe(xh,csfysj);
	}
}
