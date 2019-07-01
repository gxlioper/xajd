package xsgzgl.xtwh.general.power;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系統維護_权限_通用_DAO类
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

public class XtwhPowerDAO extends CommDAO {

	/**
	 * 获取子系统一级菜单列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getFirstGnmkList(String zdm, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.gnmkdm dm, a.gnmkmc mc from gnmkdmb a ");
		sql.append("where length(a.gnmkdm) = 3 ");
		sql.append("order by to_number(a.xssx)");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}

	/**
	 * 获取子系统二级菜单列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSecondGnmkList(String gnmkdm,
			String zdm, String yhm, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.gnmkdm dm, a.gnmkmc mc from gnmkdmb a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(gnmkdm)) {
			sql.append("and gnmkdm like '" + gnmkdm + "%' ");
		}
		if (!Base.isNull(zdm)) {
			sql.append("and not exists( ");
			sql.append("select 1 from yhzqxb b ");
			sql.append("where a.gnmkdm = b.gnmkdm ");
			sql.append("and b.zdm = '" + zdm + "' ");
			sql.append(") ");
		}
		if (!Base.isNull(yhm)) {
			sql.append("and not exists( ");
			sql.append("select 1 from yhqxb b ");
			sql.append("where a.gnmkdm = b.gnmkdm ");
			sql.append("and b.yhm = '" + yhm + "' ");
			sql.append(") ");
		}
		sql.append("order by a.gnmkdm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}

	/**
	 * 获取用户组菜单列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getYhzGnmkList(String zdm, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.gnmkdm dm, a.gnmkmc mc,b.dxq, ");
		sql.append("decode(b.dxq, '1', '可写', '只读') dxqmc ");
		sql.append("from gnmkdmb a, yhzqxb b ");
		sql.append("where a.gnmkdm = b.gnmkdm ");
		if (!Base.isNull(zdm)) {
			sql.append("and b.zdm = '" + zdm + "' ");
		}
		sql.append("order by a.gnmkdm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc", "dxq", "dxqmc" });

		return list;
	}
	
	/**
	 * 获取用户菜单列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getYhGnmkList(String yhm, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.gnmkdm dm, a.gnmkmc mc,b.dxq, ");
		sql.append("decode(b.dxq, '1', '可写', '只读') dxqmc ");
		sql.append("from gnmkdmb a, yhqxb b ");
		sql.append("where a.gnmkdm = b.gnmkdm ");
		if (!Base.isNull(yhm)) {
			sql.append("and b.yhm = '" + yhm + "' ");
		}
		sql.append("order by a.gnmkdm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc", "dxq", "dxqmc" });

		return list;
	}
	
	
	/**
	 * 获取用户菜单列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getUserList(String yhm, String xm,
			String zdm, String szbm, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.yhm||'/'||a.xm||'/'||b.zmc||'/'||c.bmmc||'/'||d.dwmc||'/'||decode(a.qx,'1','启用','不启用') xm, ");
		sql.append("a.yhm, a.xm, a.zdm,b.zmc ,a.szbm, ");
		sql.append("c.bmmc, a.dwdm,d.dwmc, a.qx  ");
		sql.append("from yhb a  ");
		sql.append("left join (select distinct yhm,zmc from view_yhz_yhxxb) b on a.yhm = b.yhm ");
		sql.append("left join zxbz_xxbmdm c on a.szbm = c.bmdm ");
		sql.append("left join bks_dwdmb d on a.dwdm = d.dwdm ");
		sql.append("where 1=1 ");
		if (!Base.isNull(yhm)) {
			sql.append("and a.yhm like '%" + yhm + "%' ");
		}
		if (!Base.isNull(xm)) {
			sql.append("and a.xm like '%" + xm + "%' ");
		}
		if (!Base.isNull(zdm)) {
			sql.append("and a.zdm = '" + zdm + "' ");
		}
		if (!Base.isNull(szbm)) {
			sql.append("and a.szbm = '" + szbm + "' ");
		}
		sql.append("order by to_number(a.xssx) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "yhm", "xm"});

		return list;
	}
}
