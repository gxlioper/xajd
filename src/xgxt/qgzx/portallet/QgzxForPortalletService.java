package xgxt.qgzx.portallet;

import java.util.List;

import xgxt.qgzx.form.QgzxForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧ�ṩPortal��ѯService</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-08</p>
 */

public class QgzxForPortalletService {
	QgzxForPortalletDAO dao = new QgzxForPortalletDAO();
	
	/**
	 * ��ȡѧ���ڹ���ѧ��Ϣ
	 * @param model
	 * @return List
	 * */
	public List getXscjffxx(QgzxForm model){		
		return dao.getXscjffxx(model);
	}
	
	/**
	 * ��ȡѧ����𷢷���Ϣ��ͷ
	 * @return List
	 * */
	public List getXscjffTortr(){
		String[] col = {"nd", "yf", "gwdm", "gzsj", "cjje"};
		String[] colCN = {"���", "�·�", "��λ����", "����ʱ��", "�����"};
		return dao.arrayToList(col, colCN);
	}
}
