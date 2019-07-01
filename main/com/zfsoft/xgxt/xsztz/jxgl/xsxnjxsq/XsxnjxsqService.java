/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-1 ����09:03:04 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xsxnjxsq;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsq.XnjxsqDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-1 ����09:03:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxnjxsqService extends SuperServiceImpl<XsxnjxsqForm, XsxnjxsqDao> {
	XsxnjxsqDao dao = new XsxnjxsqDao();
	XnjxsqDao xnjxsqDao = new XnjxsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	
	/** 
	 * @����:ѧ��У�ڽ������뱣��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-1 ����09:14:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSqjg(XsxnjxsqForm model, User user) throws Exception {
		String splc = xnjxsqDao.getShlc();
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
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xsxnjxsq.do");
			}
		}
		return flag;
	} 
	
	
	/**
	 * 
	 * �����޸Ľ��
	 */
	public boolean saveSqjgUpdate(XsxnjxsqForm model, User user) throws Exception {
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		model.setSfqq("0");
		boolean flag = dao.runUpdate(model);
		if(model.getType().equals("updatesubmit")){
			if(flag){
				String splc = model.getShlc();
				model.setShlc(splc);
				flag = shlc.runSubmit(model.getId(), model.getShlc(), model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xsxnjxsq.do");
			}
		}
		return flag;
	}
}
