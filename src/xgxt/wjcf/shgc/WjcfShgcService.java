package xgxt.wjcf.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: �Ϻ����̼�����ѧΥ�ʹ���Service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ����
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-05-19
 * </p>
 */
public class WjcfShgcService {

	WjcfShgcDAO dao = null;// ����DAO

	/**
	 * ��ȡ��������б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCflbList() throws Exception {
		dao = new WjcfShgcDAO();
		List<HashMap<String, String>> cflbList = new ArrayList<HashMap<String, String>>();
		cflbList = dao.getCflbList();
		return cflbList;
	}

	/**
	 * ��ȡ����ԭ���б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfyyList() throws Exception {
		dao = new WjcfShgcDAO();
		List<HashMap<String, String>> cfyyList = new ArrayList<HashMap<String, String>>();
		cfyyList = dao.getCfyyList();
		return cfyyList;
	}

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @param xh
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQryStuInfo(String xh, String userType)
			throws Exception {
		HashMap<String, String> map = null;
		dao = new WjcfShgcDAO();
		map = dao.getQryStuInfo(xh, userType);
		return map;
	}

	/**
	 * ����ѧԺ�걨��Ϣ
	 * 
	 * @param shgcxysbModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXysbXx(WjcfShgcXysbModel shgcxysbModel, String xn,
			String nd) throws Exception {
		dao = new WjcfShgcDAO();
		boolean flag = false;
		flag = dao.saveXysbXx(shgcxysbModel, xn, nd);
		return flag;
	}

	/**
	 * ��ȡ��ǰѧ������б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getXnNdList() throws Exception {
		dao = new WjcfShgcDAO();
		String[] xnndList = null;
		xnndList = dao.getXnNdList();
		return xnndList;
	}

	/**
	 * ��ȡ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getSearchTitle() throws Exception {
		dao = new WjcfShgcDAO();
		ArrayList<HashMap<String, String>> result = dao.getSearchTitle();
		return result;
	}

	/**
	 * ��ȡ���
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getSearchResult(
			WjcfShgcXxshQryModel shgcxxshqryModel) throws Exception {
		dao = new WjcfShgcDAO();
		ArrayList<String[]> result = dao.getSearchResult(shgcxxshqryModel);
		return result;
	}

	/**
	 * ѧУ����б�
	 * 
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getChkList(int type) {
		dao = new WjcfShgcDAO();
		List<HashMap<String, String>> map = dao.getChkList(3);
		return map;
	}

	/**
	 * ��ȡѧУ��˵ĵ���ѧ����Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxShInfo(String pkVal) throws Exception {
		dao = new WjcfShgcDAO();
		HashMap<String, String> map = dao.getXxShInfo(pkVal);
		return map;
	}

	/**
	 * Υ�ʹ���ѧУ�����Ϣ����
	 * 
	 * @param shgcxxshModel
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public boolean saveXxshInfo(WjcfShgcXxshModel shgcxxshModel, String pkVal)
			throws Exception {
		dao = new WjcfShgcDAO();
		boolean flag = dao.saveXxshInfo(shgcxxshModel, pkVal);
		;
		return flag;
	}

	/**
	 * ����ɾ��ѧУ�����Ϣ
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delxxshInfo(String[] keys) throws Exception {
		dao = new WjcfShgcDAO();
		String pk = dao.delxxshInfo(keys);
		return pk;
	}

	/**
	 * ͨ��PKVAL��ȡѧ����Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getwjcfysbInfo(String pkVal, String xh, String cflb, String cfyy)
			throws Exception {
		dao = new WjcfShgcDAO();
		HashMap<String, String> map = dao.getwjcfysbInfo(pkVal, xh, cflb, cfyy);
		return map;
	}

	/**
	 * ��ȡ���ں��ĺ�ά����ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getSearchTitle1()
			throws Exception {
		dao = new WjcfShgcDAO();
		ArrayList<HashMap<String, String>> result = dao.getSearchTitle1();
		return result;
	}

	/**
	 * ���ں��ĺ�ά����ѯ���
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getSearchResult1(
			WjcfShgcXxshQryModel shgcxxshqryModel) throws Exception {
		dao = new WjcfShgcDAO();
		ArrayList<String[]> result = dao.getSearchResult1(shgcxxshqryModel);
		return result;
	}

	/**
	 * ���ں��ĺ�ά��
	 * 
	 * @param pkVal
	 * @param cfsj
	 * @param cfwh
	 * @return
	 * @throws Exception
	 */
	public boolean wjcfrqwhsh(String pkVal, String cfsj, String cfwh, String cflb, String cfyy)
			throws Exception {
		dao = new WjcfShgcDAO();
		boolean flag = dao.wjcfrqwhsh(pkVal, cfsj, cfwh,cflb,cfyy);
		return flag;
	}

	/**
	 * ��ȡ����ԭ��
	 * 
	 * @param cfyy
	 * @return
	 * @throws Exception
	 */
	public String getCfyy(String cfyy) throws Exception {
		dao = new WjcfShgcDAO();
		String cfyydm = dao.getCfYy(cfyy);
		return cfyydm;
	}

	/**
	 * ������������ά����ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle2() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getSearchTitle2();
	}

	/**
	 * ������������ά����ѯ���
	 * 
	 * @param shgcxxshqryModel
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult2(
			WjcfShgcXxshQryModel shgcxxshqryModel, String cfyydm)
			throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getSearchResult2(shgcxxshqryModel, cfyydm);
	}

	/**
	 * ͨ��PK��PKVAL��TABLENAME��ȡѧ����Ϣ���÷���
	 * 
	 * @param pk
	 * @param pkVal
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getxxInfo(String pk, String pkVal,
			String tableName) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getxxinfo(pk, pkVal, tableName);
	}

	/**
	 * ��ȡѧ������
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public String getxxNn(HashMap<String, String> rs) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getxxNn(rs);
	}

	/**
	 * δͨ�������Ϣ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle3() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getSearchTitle3();
	}

	/**
	 * δͨ�������Ϣ��ѯ���
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult3(WjcfShgcXxshQryModel shgcxxshqryModel)
			throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getSearchResult3(shgcxxshqryModel);
	}

	/**
	 * δͨ�������Ϣ����ɾ��
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteWtgWjxx(String[] keys) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.deleteWtgWjxx(keys);
	}

	/**
	 * ���÷���ͨ��PK��PKVAL��tableName��COLLIST,TYPE��ȡ��ѯ����������
	 * 
	 * @param pk
	 * @param pkVal
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getFindResult(String pk, String pkVal,
			String tableName, int type) throws Exception {
		dao = new WjcfShgcDAO();
		HashMap<String, String> result = new HashMap<String, String>();
		String[] colList = null;
		if (tableName.equalsIgnoreCase("view_wjcf_xsssxx") && (type == 1)) {// ͨ���������������ж��������
			colList = new String[] { "XH", "XM", "XB", "NJ", "XN", "XYMC",
					"ZYMC", "BJMC", "SSSJ", "SH", "JD", "CFLBMC", "CFYYMC",
					"CFSJ", "CFWH", "JDWH", "JDSJ", "TLLY", "JDLY", "SLQK",
					"SLRQ", "SLTZS", "YQ" };
		} else if(tableName.equalsIgnoreCase("view_wjcf_xsssxx") && (type == 2)) {
			colList = new String[] { "XH", "XM", "XB", "NJ", "XN", "XYMC",
					"ZYMC", "BJMC", "SSSJ", "SH", "JD", "CFLBMC", "CFYYMC",
					"CFSJ", "CFWH", "JDWH", "JDSJ", "TLLY", "JDLY", "SLQK",
					"SLRQ", "SLTZS", "YQ" ,"FCRQ","MQZT","CSQK","FCQK","FCTZS"};
		}//end if
		result = dao.getFindResult(pk, pkVal, tableName, colList);
		return result;
	}

	// ��ȡ�ļ��ϴ��б�
	public List<HashMap<String, String>> GetFileList(String pkValue) {
		dao = new WjcfShgcDAO();
		return dao.getFileList(pkValue);
	}

	/**
	 * ͨ��INT PARAM �������ͬ���б�
	 */
	public List<HashMap<String, String>> getChkList1(int type) {
		dao = new WjcfShgcDAO();
		return dao.getChkList1(type);
	}
	
	/**
	 * ��������������Ϣ
	 * @param shgcssslModel
	 * @param pkVal
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsSlXx(WjcfShgcSsSlModel shgcssslModel, String pkVal, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.saveSsSlXx(shgcssslModel, pkVal, request);
	}
	
	/*public String getSlqk(String slqk) throws Exception {
		//���Ϊ�վ�RETURN �ȴ�����
		if (StringUtils.isNull(slqk)) {
			return "0";
		}
		if (StringUtils.isEqual(slqk, "�ȴ�����")) {
			return "0";
		}else if (StringUtils.isEqual(slqk, "������")) {
			return "1";
		}else {
			return "2";
		}//end if
	}*/
	
	/**
	 * �������߾�����Ϣ
	 * 
	 * @param shgcssjdModel
	 * @param pkVal
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsJdXx(WjcfShgcSsJdModel shgcssjdModel, String pkVal,
			HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.saveSsJdXx(shgcssjdModel, pkVal, request);
	}
	
	public List<String[]> kswjQryRes(KswjModel model) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjQryRes(model);
	}
	
	/**
	 * ����Υ�Ͳ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> kswjTitle() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjTitle();
	}
	
	/**
	 * ����Υ�ʹ���ԭ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKscfyyList() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getKscfyyList();
	}
	
	/**
	 * ����Υ�ʹ�������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKscflbList() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getKscflbList();
	}
	
	public HashMap<String, String> getStuDetails(String xh) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.getStuDetails(xh);
	}
	
	public boolean savekswjInfo(KswjModel model, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.savekswjInfo(model, request);
	}
	
	public String kswjDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjDel(keys, request);
	}
	
	public HashMap<String, String> kswjModi(String pkValue) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjModi(pkValue);
	}
	
	public boolean kswjModisave(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjModisave(pkValue, model, request);
	}
	
	public List<HashMap<String, String>> kswjgzjyTitle() throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjgzjyTitle();
	}
	
	public List<String[]> kswjgzjyResult(KswjModel model) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjgzjyResult(model);
	}
	
	public HashMap<String, String> viewKswjgzjyxx(String pkValue) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.viewKswjgzjyxx(pkValue);
	}
	
	public boolean kswjGzjysaveres(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.kswjGzjysaveres(pkValue, model, request);
	}
	
	public String wjshres(String[] keys, HttpServletRequest request, String ress) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.wjshres(keys, request, ress);
	}
	
	public String sjzy(String[] keys) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.sjzy(keys);
	}
	
	public HashMap<String, String> wtgview(String pkValue) throws Exception {
		dao = new WjcfShgcDAO();
		return dao.wtgview(pkValue);
	}
}
