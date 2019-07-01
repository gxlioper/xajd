/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhqjdr;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class HcqjDao  extends SuperDAOImpl<HcqjForm>{

	@Override
	public List<HashMap<String, String>> getPageList(HcqjForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(HcqjForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

			StringBuilder sql = new StringBuilder();
			sql.append(" select * from");
			sql.append("( select * ");
			sql.append(" from xg_rcsw_hcyhk a "); 
			sql.append(" left join view_xsbfxx b  "); 
			sql.append("on a.xh = b.xh) t  "); 
			sql.append(" where 1 = 1"); 
			sql.append(searchTjByUser);
			sql.append(searchTj);
			return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_hcyhk");
		super.setKey("id");
		super.setClass(HcqjForm.class);
		
	}

	public boolean checkXhYhkIsExist(String xh,String xm, String hcyhk) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_rcsw_hcyhk where xh = ? and xm = ? and hcyhk= ?  ");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{xh,xm,hcyhk}, "num"))>=1 ? true : false;
	}

	public boolean checkJsDrSjfw(String xh, HcqjForm t) {
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(t.getUser(), "t", "xydm", "bjdm");
		sql.append(" select count(1) num from view_xsxxb t where xh = ?  ");
		sql.append(searchTjByUser);
		return dao.getOneRs(sql.toString(), new String[]{xh}, "num").equals("1") ? true : false;
	}

	public int[] saveDrDataIntoDb(List<String[]> paralist) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_rcsw_hcyhk( ");
		sql.append(" xh,");
		sql.append(" xm,");
		sql.append(" hcyhk");
		sql.append(" )values(?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
 		
	}

	public boolean updateBpmx(List<String[]> paralist) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("update zfsoft_bpmx.sys_xsxxb ");
		sb.append(" set hcyhk=? ");
		sb.append(" where xh=? ");
		return dao.runBatchBoolean(sb.toString(), paralist);
	}

	public boolean claerBpmx(String[] xhs) throws Exception {
		   String sql = "update zfsoft_bpmx.sys_xsxxb set hcyhk='' where xh in ( ";
		   for (int i = 0; i < xhs.length; i++) {
		        sql+= "?,";
		    }
		   sql= sql.substring(0,sql.length()-1) +")";
		return dao.runUpdate(sql, xhs);
	}
}
