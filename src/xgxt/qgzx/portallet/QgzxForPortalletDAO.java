package xgxt.qgzx.portallet;


import java.util.List;

import xgxt.DAO.DAO;
import xgxt.qgzx.form.QgzxForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧ�ṩPortal��ѯDAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-08</p>
 */
public class QgzxForPortalletDAO extends DAO {
	/**
	 * ��ȡѧ���ڹ���ѧ��Ϣ
	 * @param model
	 * @return List
	 * */
	public List getXscjffxx(QgzxForm model){
		String[] inputValue = {model.getXh()};
		String[] outputValue = {"nd", "yf", "gwdm", "gzsj", "cjje"};
		String sql = "select a.* from (select nd,yf,gwdm,gzsj,cjje from xscjffb where xh=? order by nd desc)a where rownum<6";
		
		return  rsToVator(sql, inputValue, outputValue);
	}
}
