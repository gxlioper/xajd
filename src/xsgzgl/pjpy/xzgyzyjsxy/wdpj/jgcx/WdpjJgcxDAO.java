package xsgzgl.pjpy.xzgyzyjsxy.wdpj.jgcx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖__结果查询_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class WdpjJgcxDAO extends xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxDAO {
	
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
	public ArrayList<String[]> getLspjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws IllegalArgumentException,
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
				.commonPageByPjQuery(myForm.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/** 
	 * 获取我的评奖本次评奖信息
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getWdpjBcpjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();
		//高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		
		// 用户属性
		String userType=user.getUserType();
		
		//====================过滤条件===================================
		//user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);
		
		String pjxn=jbszForm.getPjxn();
		
		String pjxq=jbszForm.getPjxq();
		
		String pjnd=jbszForm.getPjnd();

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r,a.xmdm||'!!@@!!'||a.xh pkValue from ( ");
		sql.append(" select a.xmmc,a.xmdm,a.pjxn, a.pjxq, ");
		sql.append(" a.pjnd,a.xh,a.xm, a.nj, a.xydm,a.xymc, ");
		sql.append(" a.zydm,a.zymc,a.sqsj,a.bjdm,a.bjmc,b.yhkh, a.sqjg, ");
		sql.append(" case when a.sqjg is null then '未审核' ");
		sql.append(" when a.sqjg='wsh' then '未审核' ");
		sql.append(" when a.sqjg='tg' then '审核通过' ");
		sql.append(" when a.sqjg='btg' then '审核不通过' ");
		sql.append(" when a.sqjg='shz' then '审核中'  ");
		sql.append(" when a.sqjg='wxsh' then '无需审核' end shzt ");
		sql.append(" from (select a.sqsj,c.xmmc,a.xmdm, a.pjxn, a.pjxq, a.pjnd, a.sqjg, b.* ");
		sql.append(" from xg_pjpy_pjxmsqb a, xg_view_pjpy_pjryk b,xg_pjpy_pjxmwhb c ");
		sql.append(" where a.xh = b.xh and a.xmdm=c.xmdm and a.pjxn = '"+pjxn+"' ");
		sql.append(" and a.pjxq = '"+pjxq+"' and a.pjnd =  '"+pjnd+"' ) a ");
		sql.append(" left join xsxxb b on a.xh = b.xh ");
		sql.append(" )a ");

		sql.append(query);
		
		String[]colList={"pkValue","xh","xm","nj","bjmc","yhkh","sqsj","xmmc","shzt"};
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, myForm);
		
		return list;
	}
	
	/**
	 * 获得评奖历史信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getLspjList(String xh) {
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select xmmc,hdsj,xmje,");
		sql.append("decode(xmlx,'01','奖学金','荣誉称号') xmlx ");
		sql.append("from xg_pjpy_pjlsxxb ");
		sql.append("where xh = ? ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh }, new String[] { "xmmc", "xmlx", "hdsj","xmje" });
		
		return list;
	}
	
	// ==================执行查询操作 end==============================
}
