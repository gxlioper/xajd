/**
 * @部门:学工产品事业部
 * @日期：2016-11-29 下午04:19:05 
 */  
package com.zfsoft.xgxt.rcsw.gjgl.gjjg;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


public class GjjgDao extends SuperDAOImpl<GjjgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GjjgForm t)
			throws Exception {
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(GjjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

			StringBuilder sql = new StringBuilder();
			sql.append(" select * from");
			sql.append("( select a.gjxxid,a.qsh,a.zbffzr,a.sy,a.qjkssj,a.qjjssj,a.qjjc,a.sfgq,a.bgqsj,a.bz,b.* ");
			sql.append(" from xg_rcsw_xsgjxxb a "); 
			sql.append(" left join view_xsbfxx b  "); 
			sql.append("on a.xh = b.xh) t  "); 
			sql.append(" where 1 = 1"); 
			sql.append(searchTjByUser);
			sql.append(searchTj);
			return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_xsgjxxb");
		super.setKey("gjxxid");
		super.setClass(GjjgForm.class);
		
	}

	public GjjgForm getModel() throws Exception{
		String sql = "select * from xg_rcsw_xsgjxxb ";
		return super.getModel(sql, new String[]{});
	}


	public HashMap<String, String> getFdyxx(String zgh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdh,xm,zgh,bmmc");
		sql.append("  from view_fdyxx");
		sql.append(" where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}


	public boolean checkXhXmIsExist(String xh, String xm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xsxxb where xh = ? and xm = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh,xm}, "num").equals("1") ? true : false;
	}
	public boolean checkXhQjsjIsExist(String xh,String xm, String qjkssj, String qjjssj, String qjjc){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_rcsw_xsgjxxb where xh = ? and xm = ? and qjkssj= ? and qjjssj= ? and qjjc= ? ");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{xh,xm,qjkssj,qjjssj,qjjc}, "num"))>=1 ? true : false;
	}
	public boolean checkJsDrSjfw(String xh, GjjgForm t) {
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(t.getUser(), "t", "xydm", "bjdm");
		sql.append(" select count(1) num from view_xsxxb t where xh = ?  ");
		sql.append(searchTjByUser);
		return dao.getOneRs(sql.toString(), new String[]{xh}, "num").equals("1") ? true : false;
	}


	public int[] saveDrDataIntoDb(List<String[]> paralist) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_rcsw_xsgjxxb( ");
		sql.append(" xh,");
		sql.append(" xm,");
		sql.append(" qsh,");
		sql.append(" zbffzr,");
		sql.append(" sy,");
		sql.append(" qjkssj,");
		sql.append(" qjjssj,");
		sql.append(" qjjc,");
		sql.append(" sfgq,");
		sql.append(" bgqsj,");
		sql.append(" bz");
		sql.append(" )values(?,?,?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
 		
	}


	/** 
	 * @描述:根据学号取宿舍楼栋寝室号
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getQsh(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select concat(ldmc,qsh) qsh from view_xg_gygl_new_cwxx where xh=? ");
		return dao.getOneRs(sb.toString(), new String[] {xh }, "qsh");
	}
	
}
