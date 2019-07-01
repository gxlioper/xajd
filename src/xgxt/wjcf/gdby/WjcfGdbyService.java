
package xgxt.wjcf.gdby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ѧԺΥ�ʹ���Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-15</p>
 */
public class WjcfGdbyService {

	WjcfGdbyDAO dao = null;
	
	/**
	 * ��ȡ��������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCflbList() throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getCflbList();
	}
	
	/**
	 * ��ȡ����ԭ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfyyList() throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getCfyyList();
	}
	
	/**
	 * ��ȡ�û���ѯ��ͷ��ѧԺ��ѧУ��ѧ������
	 * @param userType
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfshTitle(String userType, String userName) throws Exception {
		dao = new WjcfGdbyDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		boolean bFlag = dao.chkUserExists(userName);
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ
			topList = dao.getWjcfshTitleByXy();
		}else if (StringUtils.isEqual(userType, "xx")) {
			if (bFlag) {//У�쵼
				topList = dao.getWjcfshTitleByXx();
			} else {//ѧ����
				topList = dao.getWjcfshTitleByXsc();
			}
		}else if (StringUtils.isEqual(userType, "admin")) {//����Ա�û�
			topList = dao.getWjcfshTitleByXsc();
		}
		if (StringUtils.isEqual(userName, "zf01")) {
			topList = dao.getAdminQryTitle();
		}
		return topList;
	}
	
	/**
	 * ��ȡ�û���ѯ�����ѧԺ��ѧУ��ѧ������
	 * @param userType
	 * @param userName
	 * @param wjcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWjcfshResult(String userType, String userName, WjcfShModel wjcfModel) throws Exception {
		dao = new WjcfGdbyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		boolean bFlag = dao.chkUserExists(userName);
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ
			resList = dao.getWjcfshResultByXy(wjcfModel);
		} else if (StringUtils.isEqual(userType, "xx")) {
			if (bFlag) {//У�쵼
				resList = dao.getWjcfshResultByXnd(wjcfModel);
			} else {//ѧ����
				resList = dao.getWjcfshResultByXsc(wjcfModel);
			}
		} else if (StringUtils.isEqual(userType, "admin")) {//����Ա�û�
			resList = dao.getWjcfshResultByXsc(wjcfModel);
		}
		if (StringUtils.isEqual(userName, "zf01")) {
			resList = dao.getAdminQryResult(wjcfModel);
		}
		return resList;
	}
	
	/**
	 * Υ�ʹ��ֵ��������ʾ��ѧԺ��ѧУ��ѧ������
	 * @param userType
	 * @param userName
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWjcfShView(String userType, String userName, String pkValue) throws Exception {
		dao = new WjcfGdbyDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		boolean bFlag = dao.chkUserExists(userName);
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ
			resMap = dao.wjcfShViewByXy(pkValue);
		}
		if (StringUtils.isEqual(userType, "xx")) {
			if (bFlag) {//У�쵼
				resMap = dao.wjcfShViewByXnd(pkValue);
			} else {//ѧ����
				resMap = dao.wjcfShViewByXsc(pkValue);
			}
		}
		if (StringUtils.isEqual(userType, "admin") || StringUtils.isEqual(userName, "zf01")) {
			resMap = dao.wjcfShViewByXsc(pkValue);
		}
		return resMap;
	}
	
	/**
	 * ��ȡ����б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int type) throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getChkList(type);
	}
	
	/**
	 * �������
	 * @param shModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean shResult(String userType, String userName, ShResultModel shModel, HttpServletRequest request) throws Exception {
		dao = new WjcfGdbyDAO();
		boolean bFlag = false;
		boolean bExists = dao.chkUserExists(userName);
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ
			bFlag = dao.shResultByXy(shModel, request);
		} else if (StringUtils.isEqual(userType, "xx")) {
			if (bExists) {//У�쵼
				bFlag = dao.shResultByXnd(shModel, request);
			} else {//ѧ����
				bFlag = dao.shResultByXsc(shModel, request);
			}
		} else if (StringUtils.isEqual(userType, "admin") || StringUtils.isEqual(userName, "zf01")) {//����Ա�û������κ��޸�
			bFlag = dao.shResultByXsc(shModel, request);
		}
		return bFlag;
	}
	
	/**
	 * ��������Ƿ��ظ�
	 * @param shModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(ShResultModel shModel, String pkValue) throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.chkDataExists(shModel, pkValue);
	}
	
	/**
	 * ��ȡ�����������༶�б�
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFdybjList(String userName)
			throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getFdybjList(userName);
	}
	
	public List<HashMap<String, String>> getJxjxdmList() throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getJxjxdmList();
	}
	
	public List<HashMap<String, String>> getZyList(String xydm) {
		dao = new WjcfGdbyDAO();
		return dao.getZyList(xydm);
	}
	
	public List<HashMap<String, String>> getBjList(String xydm, String zydm, String nj) {
		dao = new WjcfGdbyDAO();
		return dao.getBjList(xydm, zydm, nj);
	}
}
