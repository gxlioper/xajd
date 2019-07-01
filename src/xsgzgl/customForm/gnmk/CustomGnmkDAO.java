package xsgzgl.customForm.gnmk;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xsgzgl.customForm.table.CustomTableForm;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖_DAO类
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

public class CustomGnmkDAO extends CommDAO {

	// ===================查询================================

	/**
	 * 获得结果集(自定义功能模块)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getGnmkManageList(
			CustomGnmkForm model, User user) {

		// 项目表
		String xmb = model.getXmb();
		// 查询内容列表
		List<HashMap<String, String>> searchContentList = model
				.getSearchContentList();

		List<HashMap<String, String>> list = null;

		StringBuilder sql = new StringBuilder();

		if (searchContentList != null && searchContentList.size() > 0) {

			for (int i = 0; i < searchContentList.size(); i++) {

				String zd = searchContentList.get(i).get("zd");

				if (i != 0) {
					sql.append("union all ");
				}
				sql.append("select zd,zdz,pk ");
				sql.append("from ");
				sql.append(xmb);
				sql.append(" where zd = '" + zd + "' ");
			}

			String[] colList = new String[] { "zd", "zdz", "pk" };

			DAO dao = DAO.getInstance();

			list = dao.getList(sql.toString(), new String[] {}, colList);
		}

		return list;
	}

	/**
	 * 获得表头(自定义功能模块)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getGnmkTopList(CustomGnmkForm model,
			User user) {

		DAO dao = DAO.getInstance();

		// 功能模块代码
		String gnmkdm = model.getGnmkdm();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.dm, a.mc, b.pk_id, a.xssx ");
		sql.append("from (select c.id dm, ");
		sql.append("(select d.content ");
		sql.append("from xg_custom_table_content d ");
		sql.append("where c.table_id = d.table_id ");
		sql.append("and c.row_num = d.row_num ");
		sql.append("and d.column_num = (c.column_num-1)) mc ");
		sql.append(",b.id gnmk_id, a.xssx ");
		sql.append("from xg_custom_search_content a, ");
		sql.append("xg_custom_gnmkb          b, ");
		sql.append("xg_custom_table_content  c ");
		sql.append("where a.gnmk_id = b.id ");
		sql.append("and a.content_id = c.id ");
		sql.append("and b.gnmkdm = ?) a ");
		sql.append("left join xg_custom_pk_content b on a.dm = b.pk_id ");
		sql.append("and a.gnmk_id = b.gnmk_id ");
		sql.append("order by to_number(xssx) ");

		return dao.getList(sql.toString(), new String[] { gnmkdm },
				new String[] { "dm", "mc", "pk_id", "xssx" });
	}
	
	/**
	 * 查询内容列表(自定义功能模块)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSearchContentList(CustomGnmkForm model,
			User user) {

		DAO dao = DAO.getInstance();

		// 功能模块代码
		String gnmkdm = model.getGnmkdm();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.content_id zd,a.xssx ");
		sql.append("from xg_custom_search_content a, ");
		sql.append("xg_custom_gnmkb b ");
		sql.append("where a.gnmk_id = b.id ");
		sql.append("and b.gnmkdm = ? ");
		sql.append("order by to_number(a.xssx) ");

		return dao.getList(sql.toString(), new String[] { gnmkdm },
				new String[] { "zd", "xssx" });
	}
	// ===================查询 end ===========================
	
	// ===================详细================================
	/**
	 * 获得学生信息
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws IOException
	 */
	public List<HashMap<String, String>> getXsInfoList(CustomGnmkForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		Pages page = model.getPages();
		// 当前页
		int currentPage = page.getCurrentPage();
		
		int page_size = 8;
		int min = (currentPage - 1) * page_size;
		int max = currentPage * page_size;
		
		StringBuilder pageSql = new StringBuilder();
		StringBuilder sql = new StringBuilder();
		
		String ryfw = model.getRyfw();
		ryfw = Base.isNull(ryfw) ? "view_xsbfxx" : ryfw;
		
		pageSql.append("select a.xh,a.xm,a.xb ");
		pageSql.append(",a.nj,a.xymc,a.zymc,a.bjmc ");
		pageSql.append("from ");
		pageSql.append(ryfw);
		pageSql.append(" a ");
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm" };
		String[] queryLikeList = new String[] { "xh", "xm" };

		// 过滤条件
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		pageSql.append(myQuery.getQueryString());

		// 用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		pageSql.append(searchTjByUser);

		// 计数
		int num = Integer.parseInt(dao.getOneRs("select count(1) num from ("
				+ pageSql + ")", myQuery.getInputList(), "num"));

		int maxPage = (num % page_size == 0) ? (num / page_size) : (num
				/ page_size + 1);

		page.setMaxPage(maxPage);

		sql.append("select a.* from ( select a.*,rownum num from( ");
		sql.append(pageSql);
		sql.append(" order by a.xh ");
		sql.append(" ) a where rownum <= " + max + " ");
		sql.append(" ) a where num > " + min + "");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				myQuery.getInputList(), new String[] { "num", "xh", "xm", "xb",
						"nj", "xymc", "zymc", "bjmc" });

		return list;
	}

	/**
	 * 获得主键
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getGnmkPkList(CustomGnmkForm model,
			User user) {

		DAO dao = DAO.getInstance();

		// 功能模块代码
		String gnmkdm = model.getGnmkdm();

		StringBuilder sql = new StringBuilder();
//		sql.append("select distinct d.id pk_id, d.content, d.row_num, d.column_num ");
//		sql.append("from xg_custom_gnmkb         a, ");
//		sql.append("xg_custom_pk_content    b, ");
//		sql.append("xg_custom_table         c, ");
//		sql.append("xg_custom_table_content d ");
//		sql.append("where a.id = b.gnmk_id ");
//		sql.append("and b.gnmk_id = c.gnmk_id ");
//		sql.append("and c.id = d.table_id ");
//		sql.append("and a.gnmkdm = ? ");
//		sql.append("and exists ");
//		sql.append("(select 1 ");
//		sql.append("from (select f.* ");
//		sql.append("from xg_custom_pk_content e, xg_custom_table_content f ");
//		sql.append("where e.pk_id = f.id ");
//		sql.append("and exists (select 1 ");
//		sql.append("from xg_custom_gnmkb g ");
//		sql.append("where e.gnmk_id = g.id ");
//		sql.append("and g.gnmkdm = ?)) h ");
//		sql.append("where d.table_id = h.table_id ");
//		sql.append("and d.row_num = h.row_num ");
//		sql.append("and d.column_num = (h.column_num + 1)) ");
//		sql.append("order by pk_id ");

		sql.append("select a.pk_id ");
		sql.append("from xg_custom_pk_content a, xg_custom_gnmkb b ");
		sql.append("where a.gnmk_id = b.id ");
		sql.append("and b.gnmkdm = ? ");
		sql.append("order by pk_id ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { gnmkdm }, new String[] { "pk_id" });

		return list;
	}
	
	// ===================详细 end================================
}
