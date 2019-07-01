package xsgzgl.xsxx.zjjtzyjsxy.xxxg.lsxs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.xsxx.general.XsxxGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_历史学生_浙江交通职业技术学院_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxLsxsDAO extends CommDAO {
	
	// ==================执行查询操作 begin========================
	/**
	 * 获得结果集(历史学生)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXsxxLsxsList(XsxxGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 用户类型
		String yhlx = model.getYhlx();

		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.* ");
		tableSql.append("from (");
		tableSql.append("select a.xh pk, a.xh, a.xm, a.xb, a.nj, a.bjmc, a.byny ");
		tableSql.append("from view_xsxxb a ");
		tableSql.append("where sfyby = '是' ");
		tableSql.append("and sfzx = '不在校' ");
		tableSql.append(searchTj);
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.nj,a.bjdm,a.xh ) a ");
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj", "bjmc",
				"byny" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	// ==================执行查询操作 end========================
}
