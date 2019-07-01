package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.xmgl;


import java.sql.SQLException;
import java.util.HashMap;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @��������������������Ŀ����-��Ŀ����service
 * @author��Lu.Yao ��1271��
 * @date��2017-10-20 ����10:43:57 
 */
public class XmglService extends SuperServiceImpl<ZzyrxmglActionForm, XmglDao> {

	private XmglDao dao = new XmglDao();
	
	public XmglService() {
		super.setDao(dao);
	}

	/** 
	 * @description���鿴������¼
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����08:22:25 
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap(ZzyrxmglActionForm t) {
		return dao.getModelMap(t);
	}
	
	/** 
	 * @description������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-18 ����09:30:37 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertFdxx(ZzyrxmglActionForm model, User user) throws SQLException{
		return dao.insertFdxx(model,user);
	}

	/** 
	 * @description��ȡ������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-18 ����04:28:38 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteFdxx(ZzyrxmglActionForm model, User user) throws Exception{
		return dao.deleteFdxx(model,user);
	}

	/** 
	 * @description��ͬ�⸨��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-18 ����04:41:56 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertTyfdxx(ZzyrxmglActionForm model, User user) throws SQLException{
		return dao.insertTyfdxx(model,user);
	}

	/** 
	 * @description��ȡ��ͬ�⸨��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-18 ����04:44:19 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteTyfdxx(ZzyrxmglActionForm model, User user) throws Exception{
		return dao.deleteTyfdxx(model,user);
	}

	/** 
	 * @description���жϱ��������Ƿ�����
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����11:46:47 
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkBmrs(ZzyrxmglActionForm model) {
		String ybmrs = dao.getFdbmrs(model);
		String xdrs = dao.getFdXdrs(model);
		if(Integer.parseInt(ybmrs) < Integer.parseInt(xdrs)){
			return true;
		}else{
			return false;
		}
	}

	/** 
	 * @description����ѯ�������״̬
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����01:46:06 
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkBmshzt(ZzyrxmglActionForm model,User user) {
		return "2".equals(dao.checkBmshzt(model,user));
	}

	/** 
	 * @description���жϸ�������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����01:58:01 
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkFdrs(ZzyrxmglActionForm model) {
		return "0".equals(dao.checkFdrs(model));
	}

	/** 
	 * @description�����ݸ�����¼�ж��ܷ�������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����02:08:23 
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkFdjlBfcx(ZzyrxmglActionForm model) {
		return "0".equals(dao.checkFdjlBfcx(model));
	}
}
