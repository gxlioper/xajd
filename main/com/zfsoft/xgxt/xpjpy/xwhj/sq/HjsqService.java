/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-21 ����03:45:27 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sq;

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
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ������
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-21 ����03:45:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjsqService extends SuperServiceImpl<HjsqForm, HjsqDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	private HjsqDao dao = new HjsqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * 
	 * @����: ��̬ȡֵ(��������)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����02:02:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxlbdm
	 * @param jsfs
	 * @param jxdjdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxmcList(String jxlbdm, String jsfs, String jxdjdm) {
		return dao.getJxmcList(jxlbdm, jsfs, jxdjdm);	
	}
	
	/**
	 * 
	 * @����: ��̬ȡֵ(���)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����04:20:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxlbdm
	 * @param jsfs
	 * @param jxdjdm
	 * @param jxmcdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJe(String jxlbdm, String jsfs, String jxdjdm, String jxmcdm) {
		return dao.getJe(jxlbdm, jsfs, jxdjdm, jxmcdm);
	}
	
	/**
	 * 
	 * @����: ����(����)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����04:40:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSqjg(HjsqForm model, User user) throws Exception {
		
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
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "pjpy_hjgl_sh.do", "pjpy_hjgl_sq.do");
			}
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @����: ����(�޸�)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����04:44:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSqjgUpdate(HjsqForm model, User user) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "pjpy_hjgl_sh.do", "pjpy_hjgl_sq.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����05:36:37
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
	 * @����: �ύ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����05:50:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitHjsq(HjsqForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);// �����
		String splc = dao.getShlcID();
		model.setSplc(splc);
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "pjpy_hjgl_sh.do", "pjpy_hjgl_sq.do");
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @����: �ظ���֤
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-26 ����11:23:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByHjsq(HjsqForm model,String type) throws Exception {
		
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {
			String num = dao.isExistByHjsqSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.isExistByHjsqUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}
	
		return flag;	
	}
	
}
