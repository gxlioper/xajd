package xgxt.pjpy.shgc;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: ѧ����������ϵͳ
 * Description:�Ϻ����̼�����ѧ��������Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-04-03
 */
public class PjpyShgcService {

	PjpyShgcDAO dao = null;// ���ݲ���DAO

	/**
	 * @author ZhouMi
	 * @describe ���潱ѧ��ѧԺ����
	 */
	public void saveJxjxyrs(String[] pks, String[] szrs,
			HttpServletRequest request) throws Exception {
		dao = new PjpyShgcDAO();
		dao.saveJxjxyrs(pks, szrs, request);
	}
	
	/**
	 * @author ZhouMi
	 * @describe ���潱ѧ��רҵ����
	 */
	public void saveJxjzyrs(String[] pks, String[] szrs,
			HttpServletRequest request) throws Exception {
		dao = new PjpyShgcDAO();
		dao.saveJxjzyrs(pks, szrs, request);
	}
	
	/**
	 * @author ZhouMi
	 * @describe ���潱ѧ��༶����
	 */
	public void saveJxjbjrs(String[] pks, String[] szrs,
			HttpServletRequest request) throws Exception {
		dao = new PjpyShgcDAO();
		dao.saveJxjbjrs(pks, szrs, request);
	}
}
