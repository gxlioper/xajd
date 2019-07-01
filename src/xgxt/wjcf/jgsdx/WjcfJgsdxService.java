
package xgxt.wjcf.jgsdx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧΥ�ʹ���Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-24</p>
 */
public class WjcfJgsdxService {

	WjcfJgsdxDAO dao = null;//���õ����ݲ���DAO
	
	/**
	 * ͨ��ѧ�Ż�ȡѧ�������Ϣ
	 * getStuInfo ---- ��ȡѧ����Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getStuInfo(xh);
	}
	
	/**
	 * ����ǰ���ѧ������ʱ���Ƿ���һ��
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean chkStuTj(String cfsj) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.chkStuTj(cfsj);
	}
	
	/**
	 * ���泷��������Ϣ
	 * @param cxcfSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfSqlInfo(CxcfSqSaveModel cxcfSaveModel, HttpServletRequest request) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.saveCxcfSqlInfo(cxcfSaveModel, request);
	}
	
	/**
	 * �������ֲ�ѯ��ͷ
	 * getCxcfSearchTitle ---- �������ֲ�ѯ��ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfSearchTitle() throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfSearchTitle();
	}
	
	/**
	 * �������ֲ�ѯ���
	 * getCxcfSearchResult ---- �������ֲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfSearchResult(CxcfQryModel cxcfModel) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfSearchResult(cxcfModel);
	}
	
	/**
	 * ͨ��������ȡѧ�������Ϣ
	 * getStuInfo1 ---- ͨ��������ȡѧ�������Ϣ 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo1(String pkValue) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getStuInfo1(pkValue);
	}
	
	/**
	 * ������������ɾ��
	 * wjcfCxcfPlDel ---- Υ�ʹ��ֳ�����������ɾ�� 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String wjcfCxcfPlDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.wjcfCxcfPlDel(keys, request);
	}
	
	/**
	 * ��������������ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfSpTitle() throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfSpTitle();
	}
	
	/**
	 * ��������������ѯ���
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfSpResult(CxcfQryModel cxcfModel) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfSpResult(cxcfModel);
	}
	
	/**
	 * ͨ��������ȡ�������������Ϣ
	 * getCxcfInfoByPk ---- ͨ��������ȡ�������������Ϣ 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCxcfInfoByPk(String pkValue) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getCxcfInfoByPk(pkValue);
	}
	
	/**
	 * ��������б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChList(int type) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getChList(type);
	}
	
	/**
	 * ������������
	 * cxcfSp ---- ������������ 
	 * @param cxcfsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean cxcfSp(CxcfSpSaveModel cxcfsqModel, HttpServletRequest request) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.cxcfSp(cxcfsqModel, request);
	}
	
	/**
	 * ѧ��Υ�ʹ��������Ϣ
	 * getStuWjcfinfo ---- ѧ��Υ�ʹ��������Ϣ 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfinfo(String xh) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getStuWjcfinfo(xh);
	}
	
	/**
	 * ѧ��Υ�ʹ��������Ϣ��ͷ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getStuWjcfTit(String xh) throws Exception {
		dao = new WjcfJgsdxDAO();
		return dao.getStuWjcfTit(xh);
	}
}
