package xgxt.xtwh.kjfs;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.xtwh.XtwhDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_快捷方式_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class KjfsDAO extends XtwhDAO {

	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYhAllkjfsList(KjfsForm model, User user) {

		DAO dao = DAO.getInstance();

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// SQL
		StringBuilder sql = new StringBuilder();

		sql.append(" select distinct a.* from xg_view_xtwh_kjfs a, gnmkdmb b,");
		sql.append("stu".equalsIgnoreCase(userType) ? "yhzqxb" : "yhqxb");
		sql.append(" c where replace(a.gnmk,'!!@!!','&')  = b.dyym and b.gnmkdm = c.gnmkdm ");
		sql.append("stu".equalsIgnoreCase(userType) ? " and c.zdm = ? " : " and c.yhm = ? ");
		if(!"".equalsIgnoreCase(model.getFwlb()) && null!=model.getFwlb()){
			sql.append(" and b.gnmkdm like '%'||'"+model.getFwlb()+"'||'%' ");
		}
		if(!"".equalsIgnoreCase(model.getKjfs())  && null!=model.getKjfs()){
			sql.append(" and mkms like  '%'||'"+model.getKjfs()+"'||'%' ");
		}
		sql.append(" and (c.dxq = '1' or c.dxq = '0') order by a.picpath ");

		// 输入字段
		String[] inputV = "stu".equalsIgnoreCase(userType) ? new String[] { "6727" }
				: new String[] { userName };
		// 输出字段
		String[] colList = new String[] { "picpath", "gnmk", "mkms", "bz",
				"showmk", "showbz" };
		System.out.println(sql.toString());
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputV, colList);

		return list;
	}

	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKjfsList(User user) {

		// 表
		String tableName = "xg_view_xtwh_yhkjfs";
		// 输出字段
		String[] colList = new String[] { "picpath", "gnmk", "mkms", "showmk" };

		String query = " where yhm = '" + user.getUserName() + "'";

		// 快捷方式列表
		List<HashMap<String, String>> kjfsList = CommonQueryDAO
				.commonQueryforList(tableName, query, new String[] {}, colList,
						"");

		return kjfsList;
	}

	/**
	 * 是否拥有权限
	 * 
	 * @author 伟大的骆
	 */
	public Boolean hadQx(User user, String path) {

		DAO dao = DAO.getInstance();

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();

		String sql = "";

		// 非学生用户
		if (!"stu".equalsIgnoreCase(userType)) {

			sql = "select count(1) num from (select nvl(dxq,'0') dxq,(select gnmkmc "
					+ "from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||"
					+ "(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from"
					+ " view_yhqx a where yhm=? and dyym=?)";
		} else {

			sql = "select count(1) num from (select nvl(dxq,'0') dxq,(select "
					+ "gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||"
					+ "(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from"
					+ " view_yhzqx a where zdm=? and dyym=?)";
			userName = "6727";
		}

		String num = dao.getOneRs(sql, new String[] { userName, path }, "num");

		boolean flag = false;

		if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
	
	public List<HashMap<String, String>> getFwlb(KjfsForm model, User user) {
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		sql.append(" select a.gnmkdm dm,a.gnmkmc mc from gnmkdmb a, ");
		if ("stu".equalsIgnoreCase(userType)) {
			sql.append(" yhzqxb b ");
		}else {
			sql.append(" yhqxb b ");
		}
		sql.append(" where a.gnmkdm=b.gnmkdm  ");
		if ("stu".equalsIgnoreCase(userType)) {
			sql.append(" and b.zdm='6727'  ");
		}else {
			sql.append("and b.yhm='"+userName+"' ");
		}
		sql.append("  and a.gnmkdm like 'N__' order by b.gnmkdm  ");
		System.out.println(sql.toString());
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc"});

	}

	public List<HashMap<String, String>> getYhAllkjfsListNew(KjfsForm model, User user) {

		DAO dao = DAO.getInstance();

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// SQL
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.* from xg_view_xtwh_kjfs a, gnmkdmb b,");
		sql.append("( select gnmkdm from (select gnmkdm from (select a.jsdm ");
		sql.append("from xg_xtwh_jswhb a,xg_xtwh_yhjsb b where b.yhm = ?");
		sql.append(" and sfqy='是' and b.jsdm = a.jsdm ) a left join (select ");
		sql.append("distinct gnmkdm, jsdm from xg_xtwh_jscdqxb t) b  on a.jsdm = b.jsdm ");
		sql.append(" union select a.gnmkdm from xg_xtwh_yhcdqxb a where a.yhm=?)");
		sql.append(" order by gnmkdm) ");
		sql.append(" c where replace(a.gnmk,'!!@!!','&')  = b.dyym and b.gnmkdm = c.gnmkdm and b.sfqy='是'");
		if(!"".equalsIgnoreCase(model.getFwlb()) && null!=model.getFwlb()){
			sql.append(" and b.gnmkdm like '%'||'"+model.getFwlb()+"'||'%' ");
		}
		if(!"".equalsIgnoreCase(model.getKjfs())  && null!=model.getKjfs()){
			sql.append(" and mkms like  '%'||'"+model.getKjfs()+"'||'%' ");
		}
		sql.append(" order by a.picpath ");

		// 输入字段
		String[] inputV = new String[] { userName,userName };
		// 输出字段
		String[] colList = new String[] { "picpath", "gnmk", "mkms", "bz",
				"showmk", "showbz" };
		System.out.println(sql.toString());
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputV, colList);

		return list;
	} 
}
