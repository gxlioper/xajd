package xsgzgl.pjpy.general.pjsz.pjry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_评奖人员_通用_DAO类
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

public class PjszPjryDAO extends CommDAO {
	
	// ==================执行查询操作==============================
	/**
	 * 获得结果集(评奖人员)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPjszPjryList(PjpyGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.pk,a.xh,a.xm,a.nj,a.bjmc,");
		tableSql.append("a.xydm,a.zydm,a.bjdm,");
		tableSql.append("case when a.bjdm=a.ssbjdm then '未调换' ");
		tableSql.append("else a.pjbjmc end pjbjmc, ");
		tableSql.append("case when a.bjdm=a.ssbjdm then '否' ");
		tableSql.append("else '是' end sfdh, ");
		tableSql.append("decode(a.sfcp,'yes','是','否') sfcp ");
		tableSql.append("from (");
		tableSql.append("select a.xh pk,a.xh,c.xm,d.nj, ");
		tableSql.append("d.xydm,d.xymc,d.zydm,d.zymc,c.bjdm ssbjdm,c.bjmc, ");
		tableSql.append("a.bjdm,a.bjmc pjbjmc,a.cpz,a.sfcp ");
		tableSql.append("from xg_pjpy_pjrykb a left join view_xsbfxx c ");
		tableSql.append("on a.xh = c.xh ");
		tableSql.append("left join view_njxyzybj_all d ");
		tableSql.append("on a.bjdm = d.bjdm ");
		//tableSql.append(searchTjByUser);
		tableSql.append("  ) a order by a.nj desc,a.xydm,a.zydm,a.bjdm,a.xh");
		tableSql.append(") a ");
		tableSql.append(query);
		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "xh", "xm", "nj", "bjmc",
				"pjbjmc", "sfcp" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}

	// ==================执行查询操作 end==============================
	
	// ==================执行更新操作 =============================
	
	/**
	 * 修改数据（xg_pjpy_pjrykb:评奖人员库）
	 * 
	 * @table 评奖流程等级表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updatePjrykb(String[] xh, String bjdm, String bjmc, User user)
			throws Exception {

		boolean flag = false;

		// 表名
		String tableName = "xg_pjpy_pjrykb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_pjrykb ");
		sql.append("set bjdm = ? ");
		sql.append(", bjmc = ? ");
		sql.append("where xh = ? ");

		List<String[]> params = new ArrayList<String[]>();
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				String[] value = new String[] { bjdm, bjmc, xh[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	
	/**
	 * 撤销评奖人员班级调整
	 * @author qlj 
	 */
	public boolean disfrockPjry(String[] xh,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
	
		StringBuilder sql =new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" update xg_pjpy_pjrykb a set ");
		sql.append(" bjdm=(select bjdm from view_xsjbxx b  ");
		sql.append(" where a.xh=b.xh) where a.xh in( ");
		for(int i=0;i<xh.length;i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			inputV.add(xh[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(),inputV.toArray(new String[]{}));
	}
	
	/**
	 * 修改数据（xg_pjpy_pjrykb:评奖人员库）
	 * 
	 * @table 评奖流程等级表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updatePjrykb(String[] xh, String sfcp, User user)
			throws Exception {

		boolean flag = false;

		// 表名
		String tableName = "xg_pjpy_pjrykb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_pjrykb ");
		sql.append("set sfcp = ? ");
		sql.append("where xh = ? ");

		List<String[]> params = new ArrayList<String[]>();
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				String[] value = new String[] { sfcp, xh[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	
	// ==================执行更新操作 end==============================
	
}
