package xgxt.rcgl;

import java.util.HashMap;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��Ʊ����DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2008-12-17</p>
 */
public class TicketDAO extends DAO {
	
	/**
	 * �ж��Ƿ��ڳ˳�ʱ��������
	 * @param ccrq
	 * @return boolean
	 * */
	public boolean ccrqCheck(String ccrq){		
		boolean flag = false;
		ccrq = ccrq == null || "".equalsIgnoreCase(ccrq) ? "0" : ccrq;
		ccrq = ccrq.trim();
		ccrq = ccrq + "235959";//23��59��59��
		
		//��ȡ�趨������ʱ��
		String sql = "select * from cpydsjb";
		String[] outputValue = {"cckssj", "ccjssj"};
		HashMap<String, String> map = new HashMap<String, String>();
		
		map = getMap(sql, new String[]{}, outputValue);
		//ʱ��Ա�
		flag = Double.parseDouble(map.get("cckssj"))> Double.parseDouble(ccrq) || Double.parseDouble(map.get("ccjssj"))<Double.parseDouble(ccrq) ? false : true;
		
		return flag;
	}
}
