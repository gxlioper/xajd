package xgxt.xszz.portallet;

import java.util.HashMap;
import java.util.List;

import xgxt.shgz.model.common.CommonModel;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ѧ�������ṩPortal��ѯService</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-08</p>
 */

public class XszzForPortalletService {
	XszzForPortalletDAO dao = new XszzForPortalletDAO();
	
	/**
	 * ��ȡѧ�����������Ϣ
	 * @param model
	 * @return List
	 * */
	public List getXszz(String xh){		
		return dao.getXszz(xh);
	}
	
	/**
	 * ��ȡѧ�����������ͷ
	 * @return List
	 * */
	public List getXszzTortr(){
		String[] col = {"nd", "xmmc", "zzje"};
		String[] colCN = {"���", "��������", "�������"};
		return dao.arrayToList(col, colCN);
	}
	
	/**
	 * ��ȡ������ѧ������Ϣ
	 * @param model
	 * @return List
	 * */
	public List getGjzxdk(String xh){		
		return dao.getGjzxdk(xh);
	}
	
	/**
	 * ��ȡ������ѧ�����ͷ
	 * @return List
	 * */
	public List getGjzxdkTortr(){
		String[] col = {"nd", "hth", "dkje"};
		String[] colCN = {"���", "��ͬ��", "������"};
		return dao.arrayToList(col, colCN);
	}
	
	
	/**
	 * ѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String,String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	
	/**
	 * ���������
	 * @param model
	 * @return
	 */
	public List<String[]> getSzzqk(CommonModel model) {
		return dao.getSzzqk(model);
	}
}
