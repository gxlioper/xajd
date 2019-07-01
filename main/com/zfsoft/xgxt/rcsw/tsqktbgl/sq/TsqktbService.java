/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-17 ����01:38:53 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.sq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-17 ����01:38:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsqktbService extends SuperServiceImpl<TsqktbForm, TsqktbDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private TsqktbDao dao = new TsqktbDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	/** 
	 * @����:�õ�ѧ������б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-17 ����02:49:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXqflList(){
		return dao.getXqflList();
	}
	
	/** 
	 * @����:�Ƿ��м�¼
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-17 ����03:38:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecord(TsqktbForm form){
		return dao.isHaveRecord(form);
	}
	
	/** 
	 * @����:�������ӽ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-17 ����03:52:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveTbjg(TsqktbForm model, User user) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		String splc = dao.getShlcID();
		model.setTxsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "rcsw_tsqktbgl_sh.do", "rcsw_tsqktbgl_tb.do");
			}
		}
		return flag;
	}
	
	/** 
	 * @����:�����޸Ľ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-17 ����03:59:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveTbjgUpdate(TsqktbForm model, User user) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "rcsw_tsqktbgl_sh.do", "rcsw_tsqktbgl_tb.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;
	}
	
	/** 
	 * @����:�ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-18 ����09:13:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean submitTbjg(TsqktbForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);
		String splc = dao.getShlcID();
		model.setSplc(splc);// �����
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "rcsw_tsqktbgl_sh.do", "rcsw_tsqktbgl_tb.do");
		}
		return result;
	}
	
	/**
	 * 
	 * ����
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * 
	 * ��ȡͨ����Ϣ
	 */
	public Map<String, String> getTbxx(TsqktbForm form){
		return dao.getTbxx(form);
	}
	
}
