package xsgzgl.xtwh.general.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.xtwh.comm.splc.XtwhShlcForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系統維護_审核流_通用_DAO类
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

public class XtwhFlowDAO extends CommDAO {

	/**
	 * 获取可选用户(系统维护-审批流程维护-审批流程)
	 * @param sfbzr 
	 * @param sffdy 
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getKxyhList(String zdm, String yhm,
			String gwdm, String sffdy, String sfbzr) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.yhm,a.xm from yhb a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(zdm)) {
			sql.append("and zdm like '%" + zdm + "%' ");
		}
		if (!Base.isNull(yhm)) {
			sql.append("and (yhm like '%" + yhm + "%' ");
			sql.append("or xm like '%" + yhm + "%') ");
		}
		sql.append("and not exists( ");
		sql.append("select 1 from xg_xtwh_spgwyh b ");
		sql.append("where a.yhm=b.spyh ");
		sql.append("and b.spgw=? ");
		sql.append(")");
		if("1".equalsIgnoreCase(sffdy)){
			sql.append("and exists(select 1 from fdybjb c where c.zgh=a.yhm)");
		}else if("2".equalsIgnoreCase(sffdy)){
			sql.append("and not exists(select 1 from fdybjb c where c.zgh=a.yhm)");
		}
		if("1".equalsIgnoreCase(sfbzr)){
			sql.append("and exists(select 1 from bzrbbb c where c.zgh=a.yhm)");
		}else if("2".equalsIgnoreCase(sfbzr)){
			sql.append("and not exists(select 1 from bzrbbb c where c.zgh=a.yhm)");
		}
		

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { gwdm }, new String[] { "yhm", "xm" });

		return list;
	}
	/**
	 * 获取可选用户(日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别)
	 * @param sfbzr 
	 * @param sffdy 
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getKxyhListRcxwwh(String zdm, String yhm,
			String gwdm, String sffdy, String sfbzr) {
		
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.yhm,a.xm from yhb a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(zdm)) {
			sql.append("and zdm like '%" + zdm + "%' ");
		}
		if (!Base.isNull(yhm)) {
			sql.append("and (yhm like '%" + yhm + "%' ");
			sql.append("or xm like '%" + yhm + "%') ");
		}
		sql.append("and not exists( ");
		sql.append("select 1 from XG_RCSW_NEW_RCXWSQB b ");
		sql.append("where a.yhm=b.zgh ");
		sql.append("and b.rcxwlbdm=? ");
		sql.append(")");
		if("1".equalsIgnoreCase(sffdy)){
			sql.append("and exists(select 1 from fdybjb c where c.zgh=a.yhm)");
		}else if("2".equalsIgnoreCase(sffdy)){
			sql.append("and not exists(select 1 from fdybjb c where c.zgh=a.yhm)");
		}
		if("1".equalsIgnoreCase(sfbzr)){
			sql.append("and exists(select 1 from bzrbbb c where c.zgh=a.yhm)");
		}else if("2".equalsIgnoreCase(sfbzr)){
			sql.append("and not exists(select 1 from bzrbbb c where c.zgh=a.yhm)");
		}
		
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { gwdm }, new String[] { "yhm", "xm" });
		
		return list;
	}

	/**
	 * 获取已选用户（系统维护-审批流程维护-审批流程）
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYxyhList(String gwdm, String yhm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.yhm,a.xm from yhb a ");
		sql.append(",xg_xtwh_spgwyh b ");
		sql.append("where a.yhm = b.spyh ");
		sql.append("and b.spgw =? ");
		if (!Base.isNull(yhm)) {
			sql.append("and (yhm like '%" + yhm + "%' ");
			sql.append("or xm like '%" + yhm + "%') ");
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { gwdm }, new String[] { "yhm", "xm" });

		return list;
	}
	/**
	 * 获取已选用户(日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别)
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYxyhListRcxwwh(String gwdm, String yhm) {
		
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.yhm,a.xm from yhb a ");
		sql.append(",XG_RCSW_NEW_RCXWSQB b ");
		sql.append("where a.yhm = b.zgh ");
		sql.append("and b.rcxwlbdm =? ");
		if (!Base.isNull(yhm)) {
			sql.append("and (yhm like '%" + yhm + "%' ");
			sql.append("or xm like '%" + yhm + "%') ");
		}
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { gwdm }, new String[] { "yhm", "xm" });
		
		return list;
	}

	/**
	 * 保存已选用户（系统维护-审批流程维护-审批流程）
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean saveYxyh(String gwdm, String[] spyh, User user)
			throws Exception {

		// 表名
		String tableName = "xg_xtwh_spgwyh";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xtwh_spgwyh ");
		sql.append("(spgw,spyh)");
		sql.append("values(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spyh.length; i++) {
			String[] value = new String[] { gwdm, spyh[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	/**
	 * 保存已选用户（日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别）
	 */
	public boolean saveYxyhRcxwwh(String gwdm, String[] spyh, User user)
			throws Exception {
		
		// 表名
		String tableName = "XG_RCSW_NEW_RCXWSQB";
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into XG_RCSW_NEW_RCXWSQB ");
		sql.append("(rcxwlbdm,zgh)");
		sql.append("values(?,?)");
		
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spyh.length; i++) {
			String[] value = new String[] { gwdm, spyh[i] };
			params.add(value);
		}
		
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		
		return flag;
	}
	
	/**
	 * 删除已选用户（系统维护-审批流程维护-审批流程）
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean deleteYxyh(String gwdm, String[] spyh, User user)
			throws Exception {

		// 表名
		String tableName = "xg_xtwh_spgwyh";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xtwh_spgwyh ");
		sql.append("where spgw = ? ");
		sql.append("and spyh = ? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spyh.length; i++) {
			String[] value = new String[] { gwdm, spyh[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	/**
	 * 删除已选用户（日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别）
	 */
	public boolean deleteYxyhRcxwwh(String gwdm, String[] spyh, User user)
	throws Exception {
		
		// 表名
		String tableName = "XG_RCSW_NEW_RCXWSQB";
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from XG_RCSW_NEW_RCXWSQB ");
		sql.append("where rcxwlbdm = ? ");
		sql.append("and zgh = ? ");
		
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spyh.length; i++) {
			String[] value = new String[] { gwdm, spyh[i] };
			params.add(value);
		}
		
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		
		return flag;
	}
}
