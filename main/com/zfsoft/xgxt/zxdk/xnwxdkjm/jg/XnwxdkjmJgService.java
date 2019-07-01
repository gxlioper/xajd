/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����02:53:05 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.jg;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.sq.XnwxdkjmsqDao;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.sq.XnwxdkjmsqModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-24 ����02:53:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkjmJgService extends SuperServiceImpl<XnwxdkjmJgModel, XnwxdkjmJgDao> {
	XnwxdkjmJgDao dao = new XnwxdkjmJgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * ������������
	 */
	public boolean saveDksq(XnwxdkjmJgModel model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		boolean flag = dao.runInsert(model);
		return flag;
	}
	/**
	 * 
	 * �����޸�����
	 */
	public boolean saveDksqUpdate(XnwxdkjmJgModel model, User user) throws Exception {
		boolean flag = dao.runUpdate(model);
		return flag;
	}
}
