
package xgxt.action.zgkd.sxjy;

import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.szdw.dao.common.CommonDAO;
/**
* <p>Title: ѧ������ϵͳ</p>
* <p>Description: ѧ����Ϣ����˼�����-�й����-ѧ������DAO��</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: zfsoft</p>
* @author ³��
* @version 1.0
*/

public class XssyDAO extends CommonDAO{
   
	private static XssyDAO mydao = new XssyDAO();
	public static XssyDAO getInstance(){
		return mydao;
	}
	private DAO dao = DAO.getInstance();
	
	/**
	 * ���ع�������
	 * @return
	 */
	public List<HashMap<String,String>> getFilterCondi(){
		String[] value = {"0","1","2","3","4"};
		String[] condi = {"����д","ȫ��","δ��д","�ѻظ�","δ�ظ�"};
		return dao.arrayToList(value, condi);
	}

	public List<HashMap<String,String>> getXzztList() {
		String[] value = {"��չ����","�뵳��������","Ԥ����Ա","��ʽ��Ա"};
		String[] condi = {"��չ����","�뵳��������","Ԥ����Ա","��ʽ��Ա"};
		//�й����û���뵳��������
		if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(Base.xxdm)) {
			value = new String[]{"��չ����","Ԥ����Ա","��ʽ��Ա"};
			condi = new String[]{"��չ����","Ԥ����Ա","��ʽ��Ա"};
		}
		return dao.arrayToList(value, condi);
	}
}
