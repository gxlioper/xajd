
package xgxt.pjpy.ahjg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս�����ҵѧԺ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class PjpyAhjgService implements Serializable {
	
	private PjpyAhjgDAO dao = null;//���ݿ����DAO
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ͨ������TYPE��ѡ��ͬ��ҳ���ѯTITLE
	 * @param enCol
	 * @param cnCol
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle(int iType) throws Exception {
		dao = new PjpyAhjgDAO();
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String,String>>();
		String[] enCol = null;
		String[] cnCol = null;
		if (iType == 1) {//�����1�����ѧ���ɼ���ѯ��ͷ
			enCol = new String[]{"xn", "xq", "xh", "xm", "nj", "xymc", "bjmc", "kcsbm","kcxz", "cj"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����","�꼶",  Base.YXPZXY_KEY+"����","�༶����","�γ�����","�γ�����","�ɼ�"};
		}//end if
		else if (iType == 2) {//�����2������༶�����ʲ�ѯ��ͷ
			enCol = new String[]{"xn||bjdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "zccql"};
			cnCol = new String[]{"����", "bgcolor", "�к�", "ѧ��",  Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�೤����", "ѧ������", "������", "�༶������"};
		}//end if
		else if (iType == 3) {//�����3�������ٳ����ʲ�ѯ��ͷ
			enCol = new String[]{"xn||bjdm", "bgcolor", "rownum", "xn", "xq", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "zccql"};
			cnCol = new String[]{"����", "bgcolor", "�к�", "ѧ��", "ѧ��",  Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�೤����", "ѧ������", "������", "��ٳ�����"};
		}//end if
		else if (iType == 4) {//�����4������Ƚ��������ȷ����ͷ
			enCol = new String[]{"rownum","rychdm","rychmc","pxbl","bjzs"};
			cnCol = new String[]{"�к�","�����ƺŴ���","�����ƺ�����","��ѡ����", "��ѡ�༶����"};
		}
		else if (iType == 5) {
			enCol = new String[]{"rownum","rychdm","rychmc","pxbl","bjzs"};
			cnCol = new String[]{"�к�","�����ƺŴ���","�����ƺ�����","��ѡ����", "��ѡ��������ռ����"};
		}
		else if (iType == 6) {
			enCol = new String[]{"xn", "xq", "xh", "xm", "nj", "xymc", "bjmc", "djksmc", "ksrq", "cj"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����","�꼶",  Base.YXPZXY_KEY+"����","�༶����","�ȼ���������", "��������" , "�ɼ�"};
		}
		else if (iType == 7) {//�����1�����ѧ���ɼ���ѯ��ͷ
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc", "bjmc", "kcsbm","kcxz","khfs","cj","xf","pjf"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����", Base.YXPZXY_KEY+"����","�༶����","�γ�����","�γ�����","���˷�ʽ","�ɼ�","ѧ��", "�ܿ�ƽ����"};
		}
		else if (iType == 8) {//�����1�����ѧ���ɼ���ѯ��ͷ
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc","zymc", "bjmc", "kcsbm","khfs","cj","pjf"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����", Base.YXPZXY_KEY+"����","רҵ����","�༶����","�γ�����","���˷�ʽ","�ɼ�", "�ܿ�ƽ����"};
		} else if (iType==9) {
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc", "bjmc", "kcsbm","kcxz", "cj", "kscj", "kccj"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����", Base.YXPZXY_KEY+"����","�༶����","�γ�����","�γ�����","�ɼ�", "���Կ�ƽ���ɼ�", "�����ƽ���ɼ�"};
		} else if (iType == 10) {
			enCol = new String[] { "xn", "xqmc", "xh", "xm", "xymc", "bjmc",
					"kcmc", "kcxz", "xf", "cj", "bkcx" };
			cnCol = new String[] { "ѧ��", "ѧ��", "ѧ��", "����",  Base.YXPZXY_KEY+"����", "�༶����",
					"�γ�����", "�γ�����", "ѧ��", "�ɼ�", "����������" };
			// TODO
		}
		/*if (iType == 4) {
			enCol = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "fdysh"};
			cnCol = new String[]{"����", "bgcolor", "�к�", "ѧ��", "ѧԺ����", "רҵ����", "�༶����", "�೤����", "ѧ������", "����Ա", "����Ա���"};
		}*/
		listTopTr = dao.getSearchTitle(enCol, cnCol);
		return listTopTr;
	}
	
	/**
	 * ѧ���ɼ���ѯ���
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getPjpyXscjResult(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel,
			PjpyAhjgActionForm dataSearchForm, String userType, String userName) throws Exception {
		dao = new PjpyAhjgDAO();
		List<String[]> listRs = dao.getPjpyXscjResult(pjpyahjgxscjqryModel,
				dataSearchForm,userType,userName);
		return listRs;
	}
	
	/**
	 * ѧ���ɼ���ѯ�����
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public int getPjpyXscjResultNum(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel, String userType, String userName) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getPjpyXscjResultNum(pjpyahjgxscjqryModel,userType,userName);
	}
	
	/**
	 * �����б�ѡ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRysqList(int type) throws Exception {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] en = null;
		String[] cn = null;
		if (type == 1) {
			en = new String[] {"xjjt" , "xjgr"};
			cn = new String[] {"�Ƚ�����" ,"�Ƚ�����"};
		} else if (type == 2) {
			en = new String[] {"xjbj" , "wmss"};
			cn = new String[] {"�Ƚ��༶" ,"��������"};
		} else if (type == 3) {
			en = new String[] {"xjbj" , "wmss" , "xjgr"};
			cn = new String[] {"�Ƚ��༶" , "��������", "�Ƚ�����"};
		} else if (type == 4) {
			en = new String[] {"xjbj" };
			cn = new String[] {"�Ƚ��༶"};
		}
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			list.add(tempMap);//����������ҳѡ��������Ϣ
		}//end for
		return list;
	}

	/**
	 * �༶�����ʲ�ѯ���
	 * bjbklqryresult ---- �༶�����ʲ�ѯ���
	 * @param pjpyahjgxscjqryModel(����) �༶�����ʲ�ѯMODEL
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBjbklQryResult(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjbklQryResult(pjpyahjgxscjqryModel);
	}
	
	/**
	 * ����༶��������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveBjbkl ----  ����༶������
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveBjbkl(BjbklSaveModel bjbklSaveModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.saveBjbkl(bjbklSaveModel, request);
	}
	
	/**
	 * ����ǰ��������Ƿ��ظ�,���ڷ���TRUE����֮����FALSE
	 * chkbjbkl ---- ���༶������
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkBjbkl(BjbklSaveModel bjbklSaveModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.chkBjbkl(bjbklSaveModel);
	}

	/**
	 * ͨ��������ȡ�༶��������Ϣ
	 * getbjbklbypk ---- ͨ��������ȡ�༶��������Ϣ 
	 * @param sPk
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBjbklByPk(String sPk) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjbklByPk(sPk);
	}
	
	/**
	 * �޸İ༶������Ϣ
	 * updatebjbkl ---- �޸İ༶������ 
	 * @param sPk
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateBjbkl(String sPk, BjbklSaveModel bjbklSaveModel, HttpServletRequest request) throws Exception{
		dao = new PjpyAhjgDAO();
		return dao.updateBjbkl(sPk, bjbklSaveModel, request);
	}
	
	/**
	 * �༶����������ɾ��
	 * delbjbkl ---- ɾ���༶������
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delBjbkl(String[] keys) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.delBjbkl(keys);
	}

	/**
	 * ��ٳ����ʲ�ѯ���
	 * getzccqlresult ---- ��ȡ��ٳ����ʲ�ѯ��� 
	 * @param zccqlQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZccqlResult(ZccqlQueryModel zccqlQryModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getZccqlResult(zccqlQryModel);
	}

	/**
	 * �����ٳ����Ƿ��ظ����ظ�����TURE����֮����FALSE
	 * chkzccql ---- �����ٳ��� 
	 * @param zccqlSaveModel ��ٳ��ڱ���MODEL
	 * @return
	 * @throws Exception
	 */
	public boolean chkZccql(ZccqlSaveModel zccqlSaveModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.chkZccql(zccqlSaveModel);
	}

	/**
	 * ������ٳ�����Ϣ,�ɹ�����TRUE����֮����FALSE
	 * saveZccql ---- ������ٳ��� 
	 * @param zccqlSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveZccql(ZccqlSaveModel zccqlSaveModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.saveZccql(zccqlSaveModel, request);
	}

	/**
	 * ͨ��������ȡ��ٳ�����Ϣ
	 * getzccqlbypk ---- ͨ��������ȡ��ٳ��� 
	 * @param zccqlSaveModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZccqlByPk(String sPk) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getZccqlByPk(sPk);
	}
	
	/**
	 * �޸���ٳ�����Ϣ,�ɹ�����TRUE����֮����FALSE
	 * updatezccql ---- �޸���ٳ��� 
	 * @param sPk
	 * @param zccqlSaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateZccql(String sPk, ZccqlSaveModel zccqlSaveModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.updateZccql(sPk, zccqlSaveModel, request);
	}
	
	/**
	 * ��ٳ���������ɾ��
	 * delbjbkl ---- ��ٳ�����
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delZccql(String[] keys) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.delZccql(keys);
	}
	
	/**
	 * �����Ƚ��༶��Ϣ
	 * savexjbjinfo ---- �����Ƚ��༶��Ϣ 
	 * @param xjbjSqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjBjInfo(XjBjSqModel xjbjSqModel, HttpServletRequest request) throws Exception {
		 dao = new PjpyAhjgDAO();
		 return dao.saveXjBjInfo(xjbjSqModel, request);
	 }

	/**
	 * ��֤�����Ƿ��ظ�
	 * chkDataByXjbj ---- ��֤�Ƚ��༶�����Ƿ��ظ� 
	 * @param xjbjSqModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataByXjbj(XjBjSqModel xjbjSqModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.chkDataByXjbj(xjbjSqModel);
	}
	
	/**
	 * ��ȡ�����ƺŲ�ѯ��ͷ(����Ա��ѧԺ��ѧУ)
	 * getxjbjtitle ---- ��ȡ�Ƚ��༶��ѯ��ͷ
	 * @param xjbjQryModel
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjBjTitle(XjBjQryModel xjbjQryModel, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyAhjgDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//topList = dao.getXjbjTitleByFdy();
		}*///end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") ) {
			topList = dao.getXjbjTitleByXy();
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			topList = dao.getXjbjTitleByXx();
		}// end if ѧУ�û�,����Ա
		return topList;
	}
	
	/**
	 * ��ȡ�����ƺŲ�ѯ��ͷ(����Ա��ѧԺ��ѧУ)
	 * getxjbjresult ----  ��ȡ�Ƚ��༶��ѯ���
	 * @param xjbjQryModel
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjBjResult(XjBjQryModel xjbjQryModel, String sUserType, String sIsFdy, String userName) throws Exception {
		dao = new PjpyAhjgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//resList = dao.getXjbjResultByFdy(xjbjQryModel, sIsFdy, userName);
		}*///end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy")) {
			resList = dao.getXjbjResultByXy(xjbjQryModel);
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			resList = dao.getXjbjResultByXx(xjbjQryModel);
		}// end if ѧУ�û�,����Ա
		return resList;
	}
	
	/**
	 * �Ƚ�������˽��
	 * submitshresult ---- �ύ��˽��
	 * @param sRes
	 * @param sUserType
	 * @param sIsFdy
	 * @param xjjtShModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String submitShResult(String sRes, String sUserType, String sIsFdy, XjjtShModel xjjtShModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		String bFlag = "";
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//dao.fdyshResult(xjjtShModel, sRes, request);
		}*///end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") ) {
			bFlag = dao.xyshResult(xjjtShModel, sRes, request);
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			bFlag = dao.xxshResult(xjjtShModel, sRes, request);
		}// end if ѧУ�û�,����Ա
		return bFlag;
	}
	
	/**
	 * ������ȡ�Ƚ�������Ϣ
	 * getxjbjresultbyone ---- ������ȡ�Ƚ�������Ϣ 
	 * @param sPkValue
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXjBjResultByOne(String sPkValue, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyAhjgDAO();
		HashMap<String, String> resList = new HashMap<String, String>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//resList = dao.getXsJtJgByFdy(sPkValue);
		}*///end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy")) {
			resList = dao.getXsJtJgByXy(sPkValue);
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			resList = dao.getXsJtJgByXx(sPkValue);
		}// end if ѧУ�û�,����Ա
		return resList;
	}
	
	/**
	 * ��ȡ�б�ֵ
	 * @param iType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int iType) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getChkList(iType);
	}
	
	/**
	 * ��������Ƚ�����(����Ա��ѧԺ��ѧУ)
	 * savexjjtone ---- ��������Ƚ�����
	 * @param xjjtshModel
	 * @param sUserType
	 * @param sIsFdy
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjjtOne(XjjtShModel xjjtshModel, String sUserType, String sIsFdy, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		boolean bFlag = false;
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//bFlag = dao.fdySaveXjjtOne(xjjtshModel, request);
		}*///end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") ) {
			bFlag = dao.xySaveXjjtOne(xjjtshModel, request);
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			bFlag = dao.xxSaveXjjtOne(xjjtshModel, request);
		}// end if ѧУ�û�,����Ա
		return bFlag;
	}
	
	/**
	 * �õ�ȫУ�༶������6%
	 * getbjzs ---- �õ��༶���� 
	 * @return
	 * @throws Exception
	 */
	public String getBjzs() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjzs();
	}
	
	/**
	 * ��ȡ�༶ѧ��Υ������
	 * getbjcfrs ---- ��ȡ�༶ѧ��Υ������ 
	 * @param bjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String getBjcfRs(String bjdm, String xn) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjcfRs(bjdm, xn);
	}
	
	/**
	 * �Ƚ��༶��ѯ�����ͷ
	 * xjbjjgcxbt ---- �Ƚ��༶��ѯ�����ͷ 
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xjbjJgCxBt() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.xjbjJgCxBt();
	}
	
	/**
	 * �Ƚ��༶��ѯ���
	 * xjbjcxjg ---- �Ƚ��༶��ѯ��� 
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xjbjCxJg(XjBjQryModel xjbjqryModel, String isFdy, String userName) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.xjbjCxJg(xjbjqryModel, isFdy, userName);
	}
	
	/**
	 * ͨ��������ȡ�Ƚ��༶��Ϣ
	 * xjbjxxbypk ---- ͨ��������ȡ�Ƚ��༶��Ϣ 
	 * @param sPk
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xjbjXxByPk(String sPk) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.xjbjXxByPk(sPk);
	}
	
	/**
	 * �����Ƚ��༶���
	 * bcxjbjjg ---- �����Ƚ��༶��� 
	 * @param xjbjsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean bcxjbjJg (XjBjSqModel xjbjsqModel, String sPk, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.bcxjbjJg(xjbjsqModel, sPk, request);
	}
	
	/**
	 * �Ƚ��༶����ɾ��
	 * delxjbjxx ---- �Ƚ��༶��Ϣ����ɾ��
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delXjbjXx(String[] keys) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.delXjbjXx(keys);
	}
	
	/**
	 * �Ƚ�������������
	 * getbjzsbygr ---- �Ƚ������������� 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBjzsByGr() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjzsByGr();
	}
	
	/**
	 * ��ȡ�����ƺ��б�
	 * getrychlist ---- ��ȡ�����ƺ��б� 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychList() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getRychList();
	}
	
	/**
	 * ��ȡ�Ƚ����˲�ѯ��ͷ
	 * getxjgrtitle ---- ��ȡ�Ƚ����˲�ѯ��ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjgrTitle(String sUserType, String sIsFdy)throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = null;
		String[] cn = null;
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			en = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "rychmc", "fdysh" };
			cn = new String[] {"bgcolor" , "����", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�����ƺ�", "����Ա���"};
		}*///end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") ) {
			en = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "rychmc", "xysh" };
			cn = new String[] {"bgcolor" , "����", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�����ƺ�",  Base.YXPZXY_KEY+"���"};
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			en = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "rychmc", "xxsh" };
			cn = new String[] {"bgcolor" , "����", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�����ƺ�", "ѧУ���"};
		}// end if ѧУ�û�,����Ա
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", en[i]);
			resTemp.put("cn", cn[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * �Ƚ����˲�ѯ�������Ա��ѧԺ��ѧУ
	 * getxjgrresult ---- �Ƚ����˲�ѯ��� 
	 * @param sUserType
	 * @param sIsFdy
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjgrResult(String sUserType, String sIsFdy, XjgrQryModel xjgrqryModel, String isFdy, String userName)throws Exception {
		dao = new PjpyAhjgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			resList = dao.fdyqryXjgrResult(xjgrqryModel, isFdy, userName);
		}*///end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") ) {
			resList = dao.xyqryXjgrResult(xjgrqryModel);
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			resList = dao.xxqryXjgrResult(xjgrqryModel);
		}// end if ѧУ�û�,����Ա
		return resList;
	}
	
	/**
	 * ��ȡ��ѧ������ѧ�꣬���
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqxn() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getJxjsqxn();
	}
	
	/**
	 * ͨ��������ȡ�Ƚ�������Ϣ(����Ա,ѧԺ,ѧУ)
	 * @param sUserType
	 * @param sIsFdy
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXjgrByPk(String sUserType, String sIsFdy, String pkValue) throws Exception {
		dao = new PjpyAhjgDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			resMap = dao.fdyqryXjgrByPk(pkValue);
		}*///end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") ) {
			resMap = dao.xyqryXjgrByPk(pkValue);
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			resMap = dao.xxqryXjgrByPk(pkValue);
		}// end if ѧУ�û�,����Ա
		return resMap;
	}
	
	/**
	 * �Ƚ����˵������
	 * @param sUserType
	 * @param sIsFdy
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public String xjgrshByOne(String sUserType, String sIsFdy, String yesNo, String pkValue, HttpServletRequest request, String rychdm, String oldxh) throws Exception {
		dao = new PjpyAhjgDAO();
		String sFlag = "";
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			bFlag = dao.fdyshXjgr(yesNo, request, pkValue);
		}*///end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") ) {
			sFlag = dao.xyshXjgr(yesNo, request, pkValue, rychdm, oldxh);
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			sFlag = dao.xxshXjgr(yesNo, request, pkValue, rychdm, oldxh);
		}// end if ѧУ�û�,����Ա
		return sFlag;
	}
	
	/**
	 * ��ȡѧϰ������ѯ��ͷ
	 * getxxjstitle ---- ��ȡѧϰ������ѯ��ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXxjsTitle() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXxjsTitle();
	}
	
	/**
	 * ��ȡѧϰ������ѯ���
	 * getxxjsqryresult ---- ��ȡѧϰ������ѯ��� 
	 * @param xxjsqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXxjsResult(XxjsQryModel xxjsqryModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXxjsResult(xxjsqryModel);
	}
	
	/**
	 * ѧϰ�����б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXxjSList() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXxjSList();
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡѧ��������ѧԺ��רҵ���༶
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsInfo(String xh) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXsInfo(xh);
	}

	/**
	 * ����ѧϰ������Ϣ
	 * @param xsjxsaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXxjsInfo(XsjxSaveModel xsjxsaveModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.saveXxjsInfo(xsjxsaveModel, request);
	}
	
	/**
	 * ͨ��������ȡѧϰ������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxjsInfoByPk(String pkValue)throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXxjsInfoByPk(pkValue);
	}
	
	/**
	 * ѧϰ��������ɾ��
	 * xxjsdel ---- ѧϰ��������ɾ�� 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String xxjsDel(String[] keys) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.xxjsDel(keys);
	}
	
	/**
	 * ��ȡѧ��ѧ����ƽ���ּ��༶����
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	/*public HashMap<String, String> getXsPjfMc(String xh, String xn) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXsPjfMc(xh, xn);
	}*/
	
	/**
	 * ѧ���������Ϣ��
	 */
	public HashMap<String, String> getXsPsxxb(String xh, String xn) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXsPsxxb(xh, xn);
	}
	
	/**
	 * ѧ��֤��ӡλ��
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String[] getTopLeftStr(String page) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getTopLeftStr(page);
	}
	
	/**
	 * �ȼ����Գɼ���ѯ���
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getPjpyDjkscjResult(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel,
			PjpyAhjgActionForm dataSearchForm) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getPjpyDjkscjResult(pjpyahjgxscjqryModel, dataSearchForm);
	}
	
	public int getPjpyDjkscjResultNum(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getPjpyDjkscjResultNum(pjpyahjgxscjqryModel);
	}
	
	/**
	 * �ɼ�ͬ��
	 * @param cjlx
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean cjTb(String cjlx, String xn, String xq) throws Exception {
		dao = new PjpyAhjgDAO();
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "cjb")) {
			return dao.cjbTb(xn, xq);
		} else {
			return dao.djksCjTb(xn, xq);
		}
	}
	
	/**
	 * ��ѧ������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getJxjList();
	}
	
	/**
	 * ����TYPE��������õ������б�0��ѧ��,1�����ƺ�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjTjList(int type) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getJxjTjList(type);
	}
	
	/**
	 * ����TYPE��������õ������б�0��ѧ��,1�����ƺ�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdczList(int type) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getZdczList(type);
	}
	
	/**
	 * ����TYPE��������õ������б�0��ѧ��,1�����ƺ�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdbjList(int type) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getZdbjList(type);
	}
	
	public List<HashMap<String, String>> getRych() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getRych();
	}
	
	/**
	 * �������ñ���
	 * @param tjzdModel
	 * @return
	 * @throws Exception
	 */
	public boolean tjSave(TjszModel tjzdModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.tjSave(tjzdModel, request);
	}
	
	/**
	 * �������ò�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTjResult(String xmdm) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getTjResult(xmdm);
	}
	
	/**
	 * �������ò�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTjTitle() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getTjTitle();
	}
	
	/**
	 * ������������ɾ��
	 * @return
	 * @throws Exception
	 */
	public boolean tjszplDel() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.tjszplDel();
	}
	
	/**
	 * �������õ���ɾ��
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean tjszdgDel(String pkValue) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.tjszdgDel(pkValue);
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡ������ʮ�Ѵ�ѧ���÷ּ�����
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String[] getStudfAndPm(String xh) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getStudfAndPm(xh);
	}
	
	public int getXjbjShjg(HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXjbjShjg(request);
	}
	
	/**
	 * ѧ���ɼ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscj(String xh) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXscj(xh);
	}
	
	public String[] getJxjsqxnxq() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getJxjsqxnxq();
	}
	
	/**
	 * ��ѯ����汾 1Ϊѧ��,0Ϊ����,����Ϊ������˾����
	 * @return
	 */
	public String getJwFlag() {
		dao = new PjpyAhjgDAO();
		return dao.getJwFlag();
	}
	
	/**
	 * ���ѧ�����������б�
	 * 
	 * @author luojw
	 * @return
	 */
	public List<HashMap<String, String>> getKsxzList() {
		dao = new PjpyAhjgDAO();
		return dao.getKsxzList();
	}
	
	public List<HashMap<String, String>> getDjksmc() {
		dao = new PjpyAhjgDAO();
		return dao.getDjksmc();
	}
}
