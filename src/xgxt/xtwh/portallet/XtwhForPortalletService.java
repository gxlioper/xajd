package xgxt.xtwh.portallet;

import java.util.List;

import xgxt.form.CommanForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ϵͳά���ṩ��Portallet������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-078</p>
 */
public class XtwhForPortalletService {
	XtwhForPortalletDAO dao = new XtwhForPortalletDAO();
	
	/**
	 * ��ȡģ���б�
	 * @return List
	 * */
	public List getModelList(){
		return dao.getModelList();
	}
	
	/**
	 * ������Ϣ��ѯ
	 * @param model
	 * @return List
	 * */
	public List queryNews(CommanForm model){		
		return dao.queryNews(model);
	}
	
	/**
	 * ���Ų�ѯ��ͷ
	 * @return List
	 * */
	public List getQueryNewTitle(){
		String[] colList = {"newsid","newspart","lesstitle","pubtime","puber","newstitle"};
		String[] CNList = {"����ID", "����ģ��","���ű���","����ʱ��","������","ȫ����"};
		return dao.arrayToList(colList, CNList);
	}
}
