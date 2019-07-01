package xgxt.pjpy.zjcm.xwjxj;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.zjcm.ZhszcpDAO;

public class XwjxjDAO extends ZhszcpDAO {

	/**
	 * @author luo
	 * @describe ��ý�ѧ�������б�
	 */
	public ArrayList<String[]> getJxjSqList(String tableName, XwjxjModel model,
			String[] colList, String userType)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

//		DAO dao =DAO.getInstance();
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xq = DealString.toGBK(model.getXq());
		// ѧ��
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		String jxjdm = model.getJxjdm();
		
		StringBuffer query = new StringBuffer();

		query.append(" where zhf is not null");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='" + xydm
				+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='" + zydm
				+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='" + bjdm
				+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='" + nj + "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='" + xn + "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='" + xq + "'");
		query.append(Base.isNull(jxjdm) ? " and 1=1" : " and jxjdm='" + jxjdm + "'");
		
		query.append(" and xh like ?");
		query.append(" and xm like ?");

		String[] inPutList = new String[] { xh, xm };

		ArrayList<String[]> list = commonQuery(tableName, query.toString(),
				inPutList, colList, "", model);
		//System.out.println(query);
		return list;
	}

	/**
	 * ����걨��ѧ����Ϣ
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSbJxjXx(String pk) {
		String[] colList = new String[] { "xn", "xq", "xh", "xm", "xb", "nj",
				"xymc", "zymc", "bjmc", "zhf", "dyf", "zyf", "tyf", "nlf",
				"jxjdm", "jxjmc", "hgjjxjqk", "bjgkms", "yygjqk", "jsjgjqk",
				"zhfpm", "tqxf", "fdysh", "xysh", "xxsh", "fdyshsj", "xyshsj",
				"xxshsj", "fdyyj", "xyyj", "xxyj" };
		return commonQueryOne("view_zjcm_jxjsq", colList, "pk", pk);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ���潱ѧ�����
	 */
	public boolean saveJxjsh(XwjxjModel model, String pk, String shzt,
			String userType, HttpServletRequest request) throws Exception {

		String[] col = null;
		String[] value = null;
		String sh = "tg".equalsIgnoreCase(shzt) ? "ͨ��" : "δͨ��";

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());

		if ("xy".equalsIgnoreCase(userType)) {
			col = new String[] { "xyyj", "xyshsj", "xysh" };
			value = new String[] { DealString.toGBK(model.getXyyj()), time, sh };
		} else {
			col = new String[] { "xxyj", "xxshsj", "xxsh" };
			value = new String[] { DealString.toGBK(model.getXxyj()), time, sh };
		}

		boolean flg = StandardOperation.update("zjcm_jxjsq", col, value,
				"xh||xn||xq||jxjdm", pk, request);

		return flg;
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe ���潱ѧ�����
	 */
	public boolean saveJxjsh(String[] key, String jxjdm, String shzt,
			String userType) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;
		
		String sh = "tg".equalsIgnoreCase(shzt) ? "ͨ��" : "δͨ��";
		StringBuffer sb = new StringBuffer();
		String sql="";
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());
		String shr = "";
		String shsj = "";
		
		if ("fdy".equalsIgnoreCase(userType)) {
			shr="fdysh";
			shsj="fdyshsj";
		} else if ("xy".equalsIgnoreCase(userType)) {
			shr="xysh";
			shsj="xyshsj";
		} else {
			shr="xxsh";
			shsj="xxshsj";
		}
		
		for (int i = 0; i < key.length; i++) {
			String pk = key[i];
			sql = "update zjcm_jxjsq set " + shr + " = '" + sh + "'," + shsj
					+ "='" + time + "' where xh||xn||xq||jxjdm ='" + pk + "'";
			sb.append(sql);
			sb.append("!!#!!");
		}
		
		String[] inssql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(inssql);
		
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * ��ý�ѧ���б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlb) {
		DAO dao = DAO.getInstance();
		if(Base.isNull(jxjlb)){
			jxjlb="%";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm ,'---��ѡ��---'mc from dual union ");
		sql.append("select jxjdm dm, jxjmc mc from jxjdmb where jxjlb like ? order by dm nulls first");
		return dao.getList(sql.toString(), new String[] { jxjlb },
				new String[] { "dm", "mc" });

	}
	
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe ��ý�ѧ�����
	 */
	public String getJxjlb(String jxjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select jxjlb from jxjdmb where jxjdm = ?";
		return dao.getOneRs(sql, new String[] { jxjdm }, "jxjlb");
	}
	
	/**
	 * ���Υ�ʹ����б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select * from view_wjcf where xh = ? and xxsh = 'ͨ��'";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { xh }, new String[] {"xn","xqmc","cflbmc"});
		return list;
	}
	
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe ��ý�ѧ����������
	 */
	public String getJxjRs(String jxjdm) {
		DAO dao = DAO.getInstance();
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		String sql = "select rs from zjcm_xwjxjrs where jxjdm = ? and xn = ? and xq = ?";
		return dao.getOneRs(sql, new String[] { jxjdm,xn,xq }, "rs");
	}
	
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe ��ý�ѧ������
	 */
	public String getHdJxjRs(String jxjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from zjcm_jxjsq where jxjdm = ? and xxsh = 'ͨ��' and xn=? and xq=?";
		return dao.getOneRs(sql, new String[] { jxjdm ,Base.getJxjsqxn(),Base.getJxjsqxq()}, "num");
	}
	
	/**
	 * @author luo
	 * @describe ���ѧ���б�
	 */
	public List<HashMap<String, String>> getXqList() {
		return commonQueryforList("xqdzb", "", new String[] {}, new String[] {
				"xqmc", "xqdm" }, "");
	}

	/**
	 * ���Ӣ��ȼ������б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYyList() {
		DAO dao = DAO.getInstance();
		return dao
				.getList(
						"select djksdm dm, djksmc mc from djksdmb where djksmc like '%CET%' or djksmc like '%Ӣ��%'",
						new String[] {}, new String[] { "dm", "mc" });

	}
	
	/**
	 * ��ü�����ȼ��б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJsjList() {
		DAO dao = DAO.getInstance();
		return dao.getList("select djksdm dm, djksmc mc from djksdmb where djksmc like '%�����%'",
						new String[] {}, new String[] { "dm", "mc" });

	}
}
