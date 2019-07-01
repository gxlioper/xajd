package xsgzgl.rcsw.qjgl.bjfxdj;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
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
 * Time:2012-7-17 下午13:13:22
 * </p>
 */

public class BjfxdjDAO extends CommDAO {

	/**
	 * 病假返校登记首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getBjfxdjCx(BjfxdjForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		String[] colList = new String[] { "id", "xh", "xm", "nj", "bjmc", "qjkssj", "qjjssj", "qjts","sffxx","djr","djsj","xy","zy","bj" };
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select id,xh,xm,nj,bjmc,qjkssj,qjjssj,qjts,sffxx,sffx,djr,djsj,xy,zy,bj,rownum r from (");
		sql.append(" select id,xh,xm,nj,bjmc,qjkssj,qjjssj,qjts,sffx sffxx,(case when sffx ='是' then '是' else '否' end) sffx,djr,djsj,xy,zy,bj from view_xg_rcsw_qjgl_fxdjb) ");
		sql.append(query);
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 病假返校登记单条记录查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCkfxMap(BjfxdjForm myForm) {
		DAO dao = DAO.getInstance();
		String guid = myForm.getGuid();
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,(select xymc from view_xsjbxx c where b.xh = c.xh) xy,(select zymc from view_xsjbxx d where b.xh = d.xh) zy,nj,bjmc bj,qjlx,qjts,qjkssj,qjjssj,sffx,djr,djsj,bz from xg_rcsw_qjgl_fxdjb a right join view_xg_rcsw_qjgl_qjcxb b on a.sqid=b.id where id=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { guid });
	}

	/**
	 * 病假返校登记信息登记
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean bjfxdjDj(BjfxdjForm myForm, String str, String sffx,
			String bz, String username) throws Exception {
		DAO dao = DAO.getInstance();
		List<String> sqlArr = new ArrayList<String>();
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String djsj = df.format(date);
		
		String[] idVal = str.split("!@");
		for (String id : idVal) {
				String sql = "insert into xg_rcsw_qjgl_fxdjb (SQID,SFFX,DJR,DJSJ,BZ) values ('";
				sql += id;
				sql +="','";
				sql +=sffx;
				sql +="','";
				sql +=username;
				sql +="','";
				sql +=djsj;
				sql +="','";
				sql +=bz;
				sql +="') ";
				sqlArr.add(sql);
			}
		boolean flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));
		return flag;
	}

	/**
	 * 病假返校登记信息取消
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean bjfxdjQx(BjfxdjForm myForm, String str) throws Exception {
		List<String[]> sqlList = new ArrayList<String[]>();
		String sql = "delete from xg_rcsw_qjgl_fxdjb where sqid=?";
		String[] val = null;
		String[] idVal = str.split("!@");
		for (String id : idVal) {
			val = new String[1];
			val[0] = id;
			sqlList.add(val);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatchResult(dao.runBatch(sql, sqlList));
		return flag;
	}
}