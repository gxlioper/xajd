/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����02:40:57 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.jg;

import java.util.HashMap;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-18 ����02:40:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkJgService extends SuperServiceImpl<XnwxdkJgModel, XnwxdkJgDao> {
	XnwxdkJgDao dao = new XnwxdkJgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * ������������
	 */
	public boolean saveDkjg(XnwxdkJgModel model, User user) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		boolean flag = dao.runInsert(model);
		return flag;
	}
	/**
	 * 
	 * �����޸�����
	 */
	public boolean saveDkjgUpdate(XnwxdkJgModel model, User user) throws Exception {
		boolean flag = dao.runUpdate(model);
		return flag;
	}
	
	
	/*
	 * ����������ʱ���ȡѧ��������Ϣ�Լ�ס��������Ϣ
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsSqForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getXyzsxxMap(model, user);
	
		return xsxxmap;
	}
	
	
	//��ȡ�������
	public String getJesx() {
		return dao.getJesx();
	}
}
