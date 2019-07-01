package xgxt.dtjs.czxx;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.DtjsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class CzxxDtjsDAO extends DtjsDAO {

	/**
	 * ���������б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(CzxxDtjsForm myForm, HttpServletRequest request)
			throws Exception {
		
		//FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		String xydm = myForm.getXydm();
		String xxdm = StandardOperation.getXxdm();//ѧУ����
		String zhdj = myForm.getZhdj();//ת���ȼ�
		
		List<HashMap<String, String>> zbmcList = getSelectList("sjxy_dzbb", "sszb", "sszb","","xydm",xydm);//������֧��
		List<HashMap<String, String>> xsccList = getSelectList("dtjs_xsccb", "xsccdm", "xsccmc","","","");//ѧ�����
		List<HashMap<String, String>> zzlxList = getSelectList("zzlx");//ת������
		List<HashMap<String, String>> dyzzlxList = getSelectList("dyzzlx");//��Աת������
		List<HashMap<String, String>> sflxList = getSelectList("sflx2");//�Ƿ�����
		List<HashMap<String, String>> zhList = getZhdjList(zhdj);
		
		request.setAttribute("zbmcList", zbmcList);
		request.setAttribute("xsccList", xsccList);
		request.setAttribute("zzlxList", zzlxList);
		request.setAttribute("dyzzlxList", dyzzlxList);
		request.setAttribute("sflxList", sflxList);
		request.setAttribute("zhList", zhList);
		
		request.setAttribute("xxdm", xxdm);
	}

	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();
		
		if (Base.isNull(msg)) {
			msg = "----��ѡ��-----";
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '" + msg + "'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName);
		if (!Base.isNull(pk)) {
			sql.append(" where " + pk + "= '" + pkValue + "'");
		}
		sql.append(" order by dm nulls first");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * �������ά��������ֵ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("zzlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "�ӳ�Ԥ����", "Ԥ��������ȡ��Ԥ����Ա�ʸ�", "Ԥ����δ����ȡ��Ԥ����Ա�ʸ�" };
			mc = new String[] { "�ӳ�Ԥ����", "Ԥ��������ȡ��Ԥ����Ա�ʸ�", "Ԥ����δ����ȡ��Ԥ����Ա�ʸ�" };
		}else if ("dyzzlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "����ת��", "�ӳ�Ԥ������ת��"};
			mc = new String[] { "����ת��", "�ӳ�Ԥ������ת��"};
		}else if ("sflx1".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��"};
			mc = new String[] { "��", "��"};
		}else if ("sflx2".equalsIgnoreCase(lx)) {
			dm = new String[] { "��"};
			mc = new String[] { "��"};
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(dm, mc);
	}
}
	