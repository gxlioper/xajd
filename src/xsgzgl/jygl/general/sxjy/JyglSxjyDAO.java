package xsgzgl.jygl.general.sxjy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 就业管理_实习就业_通用_DAO类
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

public class JyglSxjyDAO extends CommDAO {

	// ==================执行查询操作 begin==============================

	/**
	 * 获得结果集【历史评奖】
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getSxjyList(JyglGeneralForm myForm,
			JyglSxjyModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ====================初始化数据begin===============================
		// 查询表
		String tableName = model.getSearch_table();
		// 输出值
		String[] colList = model.getSearch_zd();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询权限
		String searchQx = SearchService.getSearchTjByUser(user, "a", "xydm",
				"bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// ====================初始化数据 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append(" select rownum r,a.* from(");
		tableSql.append(" select a.*  ");
		tableSql.append(" from ");
		tableSql.append(tableName);
		tableSql.append(" a where 1=1 ");
		tableSql.append(searchTj);
		tableSql.append(searchQx);
		tableSql.append(" ) a ");
		tableSql.append(" where 1=1 ");

		// ====================SQL拼装 end================================

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}

	// ==================执行查询操作 end==============================
}
