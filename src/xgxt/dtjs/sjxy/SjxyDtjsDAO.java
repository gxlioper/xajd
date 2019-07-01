package xgxt.dtjs.sjxy;


import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class SjxyDtjsDAO extends CommonQueryDAO {

	/**
	 * ���������б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(SjxyDtjsForm myForm, HttpServletRequest request)
			throws Exception {
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		
		String xydm = myForm.getXydm();
		
		List<HashMap<String, String>> zbmcList = getSelectList("sjxy_dzbb", "sszb", "sszb","","xydm",xydm);//������֧��
		List<HashMap<String, String>> xsccList = getSelectList("dtjs_xsccb", "xsccdm", "xsccmc","","","");//ѧ�����
		List<HashMap<String, String>> zzlxList = getSelectList1("zzlx");//ת������
		List<HashMap<String, String>> dyzzlxList = getSelectList1("dyzzlx");//��Աת������
		request.setAttribute("zbmcList", zbmcList);
		request.setAttribute("xsccList", xsccList);
		request.setAttribute("zzlxList", zzlxList);
		request.setAttribute("dyzzlxList", dyzzlxList);
	}

	/**
	 * ��õ�֧����Ϣ�������ɲ���
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDzbxxList(String pk) {
		DAO dao = DAO.getInstance();
		String sql = "select id, sszb,sj, fsj, zzwy, xcwy, jjwy,xsrs from view_sjxy_dzb where xydm = ?";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { pk }, new String[] { "id", "sszb", "sj", "fsj",
						"zzwy", "xcwy", "jjwy", "xsrs" });
		return list;
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
	public List<HashMap<String, String>> getSelectList1(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("zzlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "�ӳ�Ԥ����", "Ԥ��������ȡ��Ԥ����Ա�ʸ�", "Ԥ����δ����ȡ��Ԥ����Ա�ʸ�" };
			mc = new String[] { "�ӳ�Ԥ����", "Ԥ��������ȡ��Ԥ����Ա�ʸ�", "Ԥ����δ����ȡ��Ԥ����Ա�ʸ�" };
		}else if ("dyzzlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "����ת��", "�ӳ�Ԥ������ת��"};
			mc = new String[] { "����ת��", "�ӳ�Ԥ������ת��"};
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * ��õ���֧��Ϣ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDzz(String xydm) {

		DAO dao = DAO.getInstance();
		String sql = "select xydm, xymc, dzzmc, khqk, xsrs, dyrs, sqrs, bz from view_sjxy_dzz where xydm = ?";
		return dao
				.getMap(sql.toString(), new String[] { xydm }, new String[] {
						"xydm", "xymc", "dzzmc", "khqk", "xsrs", "dyrs",
						"sqrs", "bz" });
	}
}
	