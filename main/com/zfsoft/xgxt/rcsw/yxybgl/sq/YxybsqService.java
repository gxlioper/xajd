/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-23 ����11:41:42 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.sq;

import java.sql.SQLException;
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
 * @ʱ�䣺 2016-3-23 ����11:41:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YxybsqService extends SuperServiceImpl<YxybsqForm, YxybsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private YxybsqDao dao = new YxybsqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	
	/** 
	 * @����:��ȡԺϵ�б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-23 ����01:49:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXylist(User user){
		return dao.getXylist(user);
	}
	
	/** 
	 * @����:�õ�ѧ������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-23 ����02:01:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqdm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	/** 
	 * @����:����ѧ��õ��·��б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-23 ����03:35:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * List<String> �������� 
	 * @throws 
	 */
	public List<String> getYueFenByXn() throws SQLException {
		return dao.getYueFenByXn();
	}
	
	/** 
	 * @����:�ж��Ƿ�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-23 ����03:37:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecord(YxybsqForm form){
		return dao.isHaveRecord(form);
	}
	
	/** 
	 * @����:�������ӽ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-23 ����03:56:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSqjg(YxybsqForm model, User user) throws Exception {
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
				flag = shlc.runSubmit(sqid, splc, model.getXydm(), "rcsw_yxybgl_sh.do", "rcsw_yxybgl_sq.do");
			}
		}
		return flag;
	}
	
	/** 
	 * @����:�����޸Ľ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-23 ����03:59:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSqjgUpdate(YxybsqForm model, User user) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXydm(), "rcsw_yxybgl_sh.do", "rcsw_yxybgl_sq.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;
	}
	
	/** 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-23 ����04:24:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * Map<String,String> �������� 
	 * @throws 
	 */
	public Map<String,String> getSqxx(YxybsqForm form){
		return dao.getSqxx(form);
	}
	
	/** 
	 * @����:�ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-23 ����05:07:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean submitSqjg(YxybsqForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);// �����
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXydm(), "rcsw_yxybgl_sh.do", "rcsw_yxybgl_sq.do");
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
	
	
}
