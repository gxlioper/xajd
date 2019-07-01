package xgxt.xsgygl.bjlh.cwfp;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.xsgygl.bjlh.BjlhGyglForm;

public class CwfpService {
	CwfpDAO dao = new CwfpDAO();

	/**
	 * δ����ѧ����������������Ե���ѧ��������
	 * @param xydm
	 * @param fpbj ȫ���ƣ���ί��������ѧ�������д������˽�����
	 * @return
	 */
	public String[] getCwFpZsData(String xydm, String zydm, String bjdm,
			String nj, String fpbj) {
		return dao.getCwFpZsData(xydm, zydm, bjdm, nj, fpbj);
	}

	/**
	 * �������б�
	 * @return
	 */
	public List<HashMap<String, String>> getFpbjList() {
		return dao.getSelectList("fpbj");
	}

	/**
	 * List
	 * @return
	 * @throws Exception 
	 */
	public void setList(BjlhGyglForm myForm, HttpServletRequest request)
			throws Exception {
		dao.setList(myForm, request);
	}

	/**
	 * �ѷ��䴲λ������������Ե��Ǵ�λ������
	 * @param xydm
	 * @param fpbj ȫ���ƣ���ί��������ѧ�������д������˽�����
	 * @return
	 */
	public String[] getCwFpYhfcws(String xydm, String zydm, String bjdm,
			String nj, String lddm, String cs, String fpbj) {
		return dao.getCwFpYhfcws(xydm, zydm, bjdm, nj, lddm, cs, fpbj);
	}

	/**
	 * δ���䴲λ������������Ե��Ǵ�λ������
	 * 
	 * @param xydm
	 * @param fpbj
	 *            ȫ���ƣ���ί��������ѧ�������д������˽�����
	 * @return
	 */
	public String[] getCwFpWhfcws(String xydm, String lddm, String cs,
			String fpbj) {
		return dao.getCwFpWhfcws(xydm, lddm, cs, fpbj);
	}

	/**
	 * δ���䴲λ�б�
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getCwFpCwXxList(String xqdm,
			String xb, String lddm, String cs, String fplx) {
		return dao.getCwFpCwXxList(xqdm, xb, lddm, cs, fplx);
	}

	/**
	 * δ����ѧ���б�
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getCwFpXsXxList(String xydm,
			String zydm, String bjdm, String nj, String xb, String fplx) {
		return dao.getCwFpXsXxList(xydm, zydm, bjdm, nj, xb, fplx);
	}

	/**
	 * �ѷ���ѧ���б�
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getCwFpYfpXsXxList(String xydm,
			String zydm, String bjdm, String nj, String xb, String lddm,
			String cs, String fplx) {
		return dao.getCwFpYfpXsXxList(xydm, zydm, bjdm, nj, xb, lddm, cs, fplx);
	}

	/**
	 * ���洲λ������Ϣ
	 * @param oldMappingItems
	 * @param newMappingItems
	 * @return
	 * @throws Exception
	 */
	public boolean saveCwfpxx(String oldMappingItems, String newMappingItems, String fplx)
			throws Exception {
		return dao.saveCwfpxx(oldMappingItems, newMappingItems, fplx);
	}
	
	/**
	 * �������λ������Ϣ
	 * @param cwList
	 * @return
	 * @throws Exception
	 */
	public boolean saveXlCwfpxx(String cwList)
			throws Exception {
		return dao.saveXlCwfpxx(cwList);
	}
}
