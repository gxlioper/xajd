package xsgzgl.pjpy.general.tjcx.hjmdtj;

import java.util.ArrayList;

import xgxt.DAO.DAO;
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

public class HjmdtjDAO extends CommDAO {

	/**
	 * 获奖名单统计首页数据查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getHjmdtjCx(HjmdtjForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		String[] colList = new String[] { "xh","xm","sfzh","nj","xymc","zymc","bjmc","xb","mzmc","rxrq","xmmc","xmje","yhkh" };
		// 用户属性
		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r,a.* from view_xg_pjpy_hjmdtj a where 1 = 1 ").append(searchTj);
		// ====================SQL拼装 end================================
//		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonPageQuery(myForm.getPages(),sql.toString(), inputV, colList);	
		
		return list;
	}
	
	/**
	 * 获奖名单统计首页数据查询(合计)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getHjmdtjCxAll(HjmdtjForm myForm) throws Exception {
		DAO dao = DAO.getInstance();
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select xmmc||'('||count(xh)||'人)' title from VIEW_xg_pjpy_hjmdtj ");
		sql.append(query);
		sql.append("  group by xmmc");
		// ====================SQL拼装 end================================
		String title = dao.getOneRs(sql.toString(), inputV, "title");
		return title;
	}
	
	/**
	 * 获得评奖历史信息中默认第一个作为首次查询的条件：评奖项目
	 * @return
	 */
	public String getPjlsxmMrtj(){
		DAO dao = DAO.getInstance();
		String sql = "select dm from (select distinct xmmc dm,xmmc mc from xg_view_pjpy_pjlsxx order by xmmc ) where rownum = 1 ";
		return dao.getOneRs(sql.toString(), new String[]{}, "dm");
	}
	
	/**
	 * 获得评奖历史信息中默认第一个作为首次查询的条件：评奖时间
	 * @return
	 */
	public String getPjzqMrtj(){
		DAO dao = DAO.getInstance();
		String sql = "select dm from (select distinct pjsj dm,pjsj mc from xg_view_pjpy_pjlsxx order by pjsj ) where rownum = 1 ";
		return dao.getOneRs(sql.toString(), new String[]{}, "dm");
	}
	
}