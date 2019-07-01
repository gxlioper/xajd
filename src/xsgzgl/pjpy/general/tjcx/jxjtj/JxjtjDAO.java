package xsgzgl.pjpy.general.tjcx.jxjtj;

import java.util.ArrayList;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-28 下午14:19:22
 * </p>
 */

public class JxjtjDAO extends CommDAO {

	/**
	 * 奖学金统计首页数据查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjtjCx(JxjtjForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		String[] colList = new String[] { "xn", "xymc", "xmmc", "rs", "je" };
		// 用户属性
		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rownum r from view_xg_pjpy_jxjtj_gd a ");
		sql.append(query);
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		return list;
	}
}