package xgxt.rcgl.zjjdzyjsxy.service;

import java.util.HashMap;

import xgxt.rcgl.zjjdzyjsxy.dao.RcswXfcjDAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭�����ճ�����ģ��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-02</p>
 */
public class RcswXfcjService {
	
	/**
	 * ѧ�Ѵ߽���ȡѧ�ѻ�������Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXfhjInfo(String xh){
		RcswXfcjDAO dao = new RcswXfcjDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getXfhjInfo(xh);
		return map;
	}
}
