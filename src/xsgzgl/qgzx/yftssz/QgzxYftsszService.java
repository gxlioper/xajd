package xsgzgl.qgzx.yftssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

/**
 * �ڹ���ѧ-�ڹ���λ����-��λ��Ϣ����
 */
public class QgzxYftsszService extends BasicService {
	
	
	public String[] getYftsszArr(QgzxYftsszForm t, User user) throws Exception{
		return qgzxYftsszDAO.getYftsszArr(t, user);
	}
	/**
	 * 
	 * @����: �޸ı���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-19 ����11:03:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	QgzxYftsszDAO qgzxYftsszDAO = new QgzxYftsszDAO();
	public boolean bcYftssz(List<String[]> params,QgzxYftsszForm t) throws Exception {
		List<QgzxYftsszForm> gwsqrList = new ArrayList<QgzxYftsszForm>();
		boolean result = true;
		result = qgzxYftsszDAO.xgYftssz(params,t);
		return result;
	}
	
	
}
