/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description:������Service </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-7 ����11:21:10</p>
 */
package xgxt.action.zgkd.bkManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.zgkd.SyltForm;

public class bkManageService {
	/** ģ�����õ��б� */

	bkManageDao dao = null;

	/**
	 * ��ȡҳ���б��û��б�����б�
	 * 
	 * @param index
	 * @return
	 */
	public List<HashMap<String, String>> getCommonList(int index) {
		List<HashMap<String, String>> list = null;
		bkManageDao dao = new bkManageDao();
		if (index == 1) {
			list = dao.getCommonList(1);
		} else {
			list = dao.getCommonList(2);
		}
		return list;
	}

	/**
	 * ��ȡ������Աƥ���ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBkGlyppTitle() throws Exception {
		dao = new bkManageDao();
		return dao.getBkGlyppTitle();
	}

	/**
	 * ��ȡ������Աƥ���ѯ���
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBkGlyppResult(SyltForm bkglModel) throws Exception {
		dao = new bkManageDao();
		return dao.getBkGlyppResult(bkglModel);
	}

	/**
	 * ��֤�û��Ƿ�ע��
	 * 
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public boolean chkYhmisReg(String yhm) throws Exception {
		dao = new bkManageDao();
		return dao.chkYhmisReg(yhm);
	}

	/**
	 * ��ȡ�û�������Ϣ:�ǳ�,����ǩ��
	 * 
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public String[] getYhInfo(String yhm) throws Exception {
		dao = new bkManageDao();
		return dao.getYhInfo(yhm);
	}

	/**
	 * ��������Ϣ����
	 * 
	 * @param bkglModel
	 * @return
	 * @throws Exception
	 */
	public boolean bkglInfoSave(BkglyPpModel bkglModel,
			HttpServletRequest request) throws Exception {
		dao = new bkManageDao();
		return dao.bkglInfoSave(bkglModel, request);
	}

	/**
	 * ��ȡ������Աƥ����Ϣ
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBkglyPpInfo(String pkValue)
			throws Exception {
		dao = new bkManageDao();
		return dao.getBkglyPpInfo(pkValue);
	}

	/**
	 * ������Աƥ����Ϣ����ɾ��
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delBkglyPpInfo(String[] keys, HttpServletRequest request)
			throws Exception {
		dao = new bkManageDao();
		return dao.delBkglyPpInfo(keys, request);
	}

	/**
	 * ������Աƥ����Ϣ�޸�
	 * 
	 * @param pkValue
	 * @param yhm
	 * @param bkdm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean bkglInfoModi(String pkValue, String yhm, String bkdm,
			HttpServletRequest request) throws Exception {
		dao = new bkManageDao();
		return dao.bkglInfoModi(pkValue, yhm, bkdm, request);
	}

	/**
	 * ��ȡ�û�����
	 * 
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public String[] getYhxm(String yhm) throws Exception {
		dao = new bkManageDao();
		return dao.getYhxm(yhm);
	}

	/** ��ȡ���ά����ѯ��� */
	public ArrayList<String[]> getBKResult(SyltForm syltForm) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		bkManageDao dao = new bkManageDao();
		result = dao.BKResult(syltForm);
		return result;
	}

	public List<HashMap<String, String>> getBkDmMcList() {
		bkManageDao dao = new  bkManageDao();
		return dao.getBkDmMcList();
	}

	/** ��ȡ���ά����ѯ�����ͷ */
	public ArrayList<HashMap<String, String>> getBKWHSearchTitle() {
		ArrayList<HashMap<String, String>> result = 
			new ArrayList<HashMap<String, String>>();
		bkManageDao dao = new bkManageDao();
		result = dao.BKResultTitle();
		return result;
	}

	/**
	 * �����ӱ���
	 * 
	 * @throws Exception
	 */
	public boolean bkInfoSave(BkWeiHuModel model, HttpServletRequest request)
			throws Exception {
		dao = new bkManageDao();
		return dao.bkwhaddsave(model, request);
	}

	/** ���ص��������Ϣ */
	public HashMap<String, String> viewBKInfo(String pkValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		dao = new bkManageDao();
		map = dao.viewBKInfo(pkValue);
		return map;
	}

	/**
	 * �����Ϣ����ɾ��
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public int bkInFoDel(String[] keys, HttpServletRequest request)
			throws Exception {
		dao = new bkManageDao();
		return dao.bkInFoDel(keys, request);
	}
}
