/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jswh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class JswhDao extends SuperDAOImpl<JswhForm>{

	@Override
	public List<HashMap<String, String>> getPageList(JswhForm t)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String cxlx = t.getJslx();
		if ("ywh".equals(cxlx)) {
			sql.append(" select * from (select zgh,xm,xb,lxdh,bmmc,'已分配' sfbl,jslbdm,jslbmc,(case zzshen when '1' then '是'  else '否' end )zzshen from view_fdyxx  where sfbl = '1' )a ");
		}else {
			sql.append(" select * from (select zgh,xm,xb,lxdh,bmmc,'未分配' sfbl,jslbdm,jslbmc from view_fdyxx  where sfbl <>'1' or sfbl is null )a ");
		}
		sql.append(" where 1 = 1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(JswhForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String cxlx = t.getJslx();
		if ("ywh".equals(cxlx)) {
			sql.append(" select * from (select zgh,xm,bmdm,bmmc from view_fdyxx  where sfbl = '1' )a where 1 = 1");
		}else {
			sql.append(" select * from (select zgh,xm,bmdm,bmmc from view_fdyxx  where sfbl <> '1' )a where 1 = 1");
			sql.append(searchTjByUser);
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override 
	protected void setTableInfo() {
		
	}

	public boolean jsFp(String[] zgharr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update fdyxxb set sfbl='1' where zgh in (");
		for(int i = 0;i<zgharr.length;i++){
			if(i==zgharr.length-1){
				sql.append("'"+zgharr[i]+"'");
			}else{
				sql.append("'"+zgharr[i]+"',");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	public boolean jsSfkyy(String[] zgharr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update fdyxxb set zzshen='1' where zgh in (");
		for(int i = 0;i<zgharr.length;i++){
			if(i==zgharr.length-1){
				sql.append("'"+zgharr[i]+"'");
			}else{
				sql.append("'"+zgharr[i]+"',");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{});
	}

	public boolean jsQxfp(String[] zgharr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update fdyxxb set sfbl='0',zzshen='0' where zgh in (");
		for(int i = 0;i<zgharr.length;i++){
			if(i==zgharr.length-1){
				sql.append("'"+zgharr[i]+"'");
			}else{
				sql.append("'"+zgharr[i]+"',");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	public boolean dektSave(String zgh, String zzshen) throws Exception{
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" update fdyxxb set zzshen=? where  ");
		params.add(zzshen);
		String[] arr = zgh.split("!!array!!");
		for (int i = 0; i < arr.length; i++) {
			sql.append(" zgh=? ");
			params.add(arr[i]);
			if(i < arr.length - 1){
				sql.append(" or ");
			}
		}
		DAO dao = DAO.getInstance();
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

}
