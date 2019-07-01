/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-25 ����06:09:25 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-25 ����06:09:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnjxsqService extends SuperServiceImpl<XnjxsqForm, XnjxsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private XnjxsqDao dao = new XnjxsqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	public XnjxsqService() {
		super.setDao(dao);	
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-26 ����11:08:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYiShen(String xh){
		return dao.getYiShen(xh);
	}
	
	/**
	 * 
	 * �������ӽ��
	 */
	public boolean saveSqjg(XnjxsqForm model, User user) throws Exception {
		String splc = dao.getShlc();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setShlc(splc);
		model.setSfqq("0");
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				String sqid = dao.getSqid(model);
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xnjxsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * �����޸Ľ��
	 */
	public boolean saveSqjgUpdate(XnjxsqForm model, User user) throws Exception {
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		model.setSfqq("0");
		boolean flag = dao.runUpdate(model);
		if(model.getType().equals("updatesubmit")){
			if(flag){
				String splc = dao.getShlc();
				model.setShlc(splc);
				flag = shlc.runSubmit(model.getId(), model.getShlc(), model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xnjxsq.do");
			}
		}
		return flag;
	}
	
	//�õ��������
	public String getShlc(){
		return dao.getShlc();
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	//�ύ
	public boolean submitBusi(XnjxsqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		String splc = dao.getShlc();
		model.setShlc(splc);
		boolean flag = dao.runUpdate(model);		
		if(flag){
			 shlc.runSubmit(model.getId(), model.getShlc(), model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xnjxsq.do");
		}
		return flag;
	}
}
