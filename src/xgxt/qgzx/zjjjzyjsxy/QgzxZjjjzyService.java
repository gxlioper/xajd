package xgxt.qgzx.zjjjzyjsxy;

import java.util.HashMap;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧģ���㽭����ְҵ����ѧԺService</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-03</p>
 */
public class QgzxZjjjzyService {
	QgzxZjjjzyDAO dao = new QgzxZjjjzyDAO();
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		return dao.getStuInfo(xh);
	}
	
	/**
	 * �ж�ѧ���Ƿ���������
	 * @param xh
	 * @return boolean
	 * */
	public boolean isKns(String xh){
		return dao.isKns(xh);
	}
	
	/**
	 * ��ȡ��ǰʱ��
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getCurrTime(){
		return dao.getCurrTime();
	}
}
