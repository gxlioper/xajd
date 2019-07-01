package xgxt.gygl.zjjs.xszd;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForward;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.MakeQuery;

/**
 * ����ְҵ���������Ž�ѧ��ά��DAO
 */
public class GyglXszdDao extends CommDAO {

	DAO dao = DAO.getInstance();

	/**
	 * ����ѧ���߶�����
	 * 
	 * @author luojw
	 */
	public boolean saveXszdSq(GyglXszdForm model) {

		// ѧ��
		String xh = model.getXh();
		// ID
		String id = model.getId();
		// ס�޵ص�
		String zsdd = model.getZsdd();
		// ��ϵ�绰
		String lxdh = model.getLxdh();
		// ��ͥ��ַ
		String jtdz = model.getJtdz();
		// ��ͥ�绰
		String jtdh = model.getJtdh();
		// ����ʱ��
		String sqsj = model.getSqsj();
		// �߶���ʼʱ��
		String zdkssj = model.getZdkssj();
		// �߶�����ʱ��
		String zdjssj = model.getZdjssj();
		// ��������
		String sqly = model.getSqly();
		// ��ע
		String bz = model.getBz();
		
		DAO dao = DAO.getInstance();
		
		boolean flag = true;
		
		StringBuilder deleteSql = new StringBuilder();
		deleteSql.append("delete from xg_gygl_zjjs_zdssqb ");
		deleteSql.append("where 1=1 ");
		deleteSql.append("and xh||sqsj = ? ");
		
		try {
			flag = dao.runProcuder(deleteSql.toString(), new String[] { xh
					+ sqsj });
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuilder insertSql = new StringBuilder();
		insertSql.append("insert into xg_gygl_zjjs_zdssqb");
		insertSql.append("(xh,lxdh,zsdd,jtdz,jtdh,zdkssj,zdjssj,sqly,bz)");
		insertSql.append("values(?,?,?,?,?,?,?,?,?)");

		try {
			flag = dao.runProcuder(insertSql.toString(), new String[] { xh,
					lxdh, zsdd, jtdz, jtdh, zdkssj, zdjssj, sqly, bz });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ���ѧ���߶�������Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getXszdSqInfo(String pk) {

		String[] colList = new String[] { "pk","id", "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "lxdh", "zsdd", "jtdz",
				"sqsj", "jtdh", "zdkssj", "zdjssj", "sqly", "bz", "bjsh",
				"bjshr", "bjshyj", "bjshsj", "xysh", "xyshr", "xyshyj",
				"xyshsj" };

		return CommonQueryDAO.commonQueryOne("xg_view_gygl_zjjs_zdssqb",
				colList, "pk", pk);
	}

	/**
	 * ��ý�ѧ������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdshList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		String userType = user.getUserType();
		String userStatus = user.getUserStatus();
		
		//	====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		//query += searchTjByUser;
		// ====================�������� end================================
		
		StringBuilder tableName = new StringBuilder();
		tableName.append("select rownum r,a.* from (");
		tableName.append("select a.pk,a.xh,a.xm,a.bjmc,a.lxdh, ");
		tableName.append("a.nj,a.xydm,a.zydm,a.bjdm,a.zsdd, ");
		tableName.append("a.jtdz,a.jtdh,a.zdkssj,a.zdjssj, ");
		tableName.append("a.bjsh,a.xysh,a.sqsj ");
		if ("fdy".equalsIgnoreCase(userStatus)||"jd".equalsIgnoreCase(userStatus)) {
			tableName.append(",bjsh shzt ");
		}else if("xy".equalsIgnoreCase(userType)){
			tableName.append(",xysh shzt ");
		}
		tableName.append("from xg_view_gygl_zjjs_zdssqb a ");
		tableName.append("where 1=1 ");
		
		if ("fdy".equalsIgnoreCase(userStatus)||"jd".equalsIgnoreCase(userStatus)) {
			tableName.append(" and xysh = 'δ���' ");
		}else if("xy".equalsIgnoreCase(userType)){
			tableName.append(" and bjsh = 'ͨ��' ");
		}
		tableName.append(searchTjByUser);
		tableName.append(") a");
		tableName.append(query);
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
		.commonPageQuery(model.getPages(), tableName.toString(), inputV,
				colList);
		
		return list;
	}
	
	/**
	 * ���潱ѧ�����״̬
	 * 
	 * @author luojw
	 */
	public boolean saveXszdShzt(GyglXszdForm model, User user) {

		DAO dao = DAO.getInstance();

		boolean flag = true;

		String pk = model.getPk();
		String shr = user.getUserName();
		String shsj = getNowTime("YYYYMMDD");
		String shzt = model.getShzt();

		String[] inputV = null;

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_gygl_zjjs_zdssqb ");
		sql.append("set");
		
		if ("fdy".equalsIgnoreCase(user.getUserStatus())||"jd".equalsIgnoreCase(user.getUserStatus())) {
			sql.append(" bjsh=?");
			sql.append(",bjshsj=?");
			sql.append(",bjshr=?");
			sql.append(",bjshyj=?");

			inputV = new String[] { shzt, shsj, shr, model.getBjshyj(), pk };
		}else if ("xy".equalsIgnoreCase(user.getUserType())) {
			sql.append(" xysh=?");
			sql.append(",xyshsj=?");
			sql.append(",xyshr=?");
			sql.append(",xyshyj=?");

			inputV = new String[] { shzt, shsj, shr, model.getXyshyj(), pk };
		}

		sql.append(" where xh||sqsj=?");

		try {
			flag = dao.runProcuder(sql.toString(), inputV);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ��������ѧ���߶����״̬
	 * 
	 * @author luojw
	 */
	public boolean savePlXszdShzt(GyglXszdForm model, User user) {

		DAO dao = DAO.getInstance();

		boolean flag = true;

		String[] pk = model.getPrimarykey_checkVal();
		String shr = user.getUserName();
		String shsj = getNowTime("YYYYMMDD");
		String shzt = model.getShzt();

		String[] inputV = null;

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_gygl_zjjs_zdssqb ");
		sql.append("set");
		if ("fdy".equalsIgnoreCase(user.getUserStatus())||"jd".equalsIgnoreCase(user.getUserStatus())) {
			sql.append(" bjsh=?");
			sql.append(",bjshsj=?");
			sql.append(",bjshr=?");
		}else if ("xy".equalsIgnoreCase(user.getUserType())) {
			sql.append(" xysh=?");
			sql.append(",xyshsj=?");
			sql.append(",xyshr=?");
		}

		sql.append(" where 1=1 ");
		
		sql.append(" and (");
		for (int i = 0; i < pk.length; i++) {
			if (i != 0) {
				sql.append(" or ");
			}
			sql.append(" xh||sqsj='" + pk[i] + "' ");
		}
		sql.append(" )");

		inputV = new String[] { shzt, shsj, shr };
		
		try {
			flag = dao.runProcuder(sql.toString(), inputV);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ��ý�ѧ�����б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdjgList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		String userType = user.getUserType();
		String userStatus = user.getUserStatus();
		
		//	====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		//query += searchTjByUser;
		// ====================�������� end================================
		
		StringBuilder tableName = new StringBuilder();
		tableName.append("select rownum r,a.* from (");
		tableName.append("select a.pk,a.xh,a.xm,a.bjmc,a.lxdh, ");
		tableName.append("a.nj,a.xydm,a.zydm,a.bjdm,a.zsdd, ");
		tableName.append("a.jtdz,a.jtdh,a.zdkssj,a.zdjssj, ");
		tableName.append("a.xymc,a.zymc,a.xb, ");
		tableName.append("a.bjsh,a.xysh,a.sqsj,a.sqly ");
		tableName.append("from xg_view_gygl_zjjs_zdssqb a ");
		tableName.append("where 1=1 ");
		
		tableName.append(searchTjByUser);
		tableName.append(") a");
		tableName.append(query);
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
		.commonPageQuery(model.getPages(), tableName.toString(), inputV,
				colList);
		
		return list;
	}
	
	/**
	 * ��ý�ѧ�����б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdTjList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		String userType = user.getUserType();
		String userStatus = user.getUserStatus();
		
		//	====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		//query += searchTjByUser;
		// ====================�������� end================================
		
		StringBuilder tableName = new StringBuilder();
		tableName.append("select rownum r,a.* from (");
		tableName.append("select a.pk,a.xh,a.xm,a.bjmc,a.lxdh, ");
		tableName.append("a.nj,a.xydm,a.zydm,a.bjdm,a.zsdd, ");
		tableName.append("a.jtdz,a.jtdh,a.zdkssj,a.zdjssj, ");
		tableName.append("a.xymc,a.zymc,a.xb, ");
		tableName.append("a.bjsh,a.xysh,a.sqsj,a.sqly ");
		tableName.append("from xg_view_gygl_zjjs_zdssqb a ");
		tableName.append("where 1=1 ");
		
		tableName.append(searchTjByUser);
		tableName.append(") a");
		tableName.append(query);
		
		DAO dao = DAO.getInstance();
		ArrayList<String[]> list = dao.rsToVator(tableName.toString(), inputV,
				colList);
		
		return list;
	}
}
	