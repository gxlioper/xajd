/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-6 ����02:13:57 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: ��ҵ�������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-6 ����02:13:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ByhkglSqService extends SuperServiceImpl<ByhkglSqForm, ByhkglSqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private ByhkglSqDao dao = new ByhkglSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	
	/**
	 * 
	 * @����:�������ӽ��
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-9 ����04:29:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSqjg(ByhkglSqForm model, User user) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		model.setXh(model.getXh());
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "zxdk_byhkgl_byhksh.do", "zxdk_byhkgl_byhksq.do");
			}
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @����:�����޸Ľ��
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-9 ����04:29:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSqjgUpdate(ByhkglSqForm model, User user) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "zxdk_byhkgl_byhksh.do", "zxdk_byhkgl_byhksq.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @����: չ��ԭ��LIST
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-9 ����03:01:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZqyyList() {
		return dao.getZqyyList();
	}
	
	/**
	 * 
	 * @����: Ӧ�����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-9 ����03:51:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String yhjeCx(String xh) {
		return dao.yhjeCx(xh);
	}
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-9 ����06:19:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitByhkglsq(ByhkglSqForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);// �����
		String splc = dao.getShlcID();
		model.setSplc(splc);
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "zxdk_byhkgl_byhksh.do", "zxdk_byhkgl_byhksq.do");
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-9 ����06:35:42
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
	 * @����: ��ȡzqyymc
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-10 ����01:44:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZqyymc(String xh) {
		return dao.getZqyymc(xh);
	}
}
