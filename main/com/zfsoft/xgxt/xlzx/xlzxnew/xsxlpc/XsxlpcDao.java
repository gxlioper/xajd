package com.zfsoft.xgxt.xlzx.xlzxnew.xsxlpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XsxlpcDao extends SuperDAOImpl<XsxlpcForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XsxlpcForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XsxlpcForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,t1.XM,t1.XB,t1.NJ,t1.XYMC,t1.xydm,t1.ZYDM,t1.zymc,t1.BJDM,t1.BJMC,decode(t.sfgz,'0','否','1','是',t.sfgz) sfgzmc");
		sql.append(" from xg_xlzx_xsxlpcb t	left join view_xsbfxx t1 on t.xh = t1.XH");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by jlsj desc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(XsxlpcForm.class);
		this.setKey("id");
		this.setTableName("xg_xlzx_xsxlpcb");
	}
	
	/**
	 * 
	 * @描述:
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午05:16:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJtqkMap(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select jtdz,jtdh,decode(sfdq,'0','否','1','是',sfdq) sfdq,decode(sfdb,'0','否','1','是',sfdb) sfdb,decode(sfgc,'0','否','1','是',sfgc) sfgc,decode(lszn,'0','否','1','是',lszn) lszn,jtrs,jtnzsr from xg_xszz_new_jtqkdcb t where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:验证是否存在
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午06:52:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExists(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_xlzx_xsxlpcb where xh = ?");
		String cnt = dao.getOneRs(sql.toString(),new String[]{xh}, "cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:关注取消关注
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-9 下午04:55:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param zt
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean sz(String[] ids,String sfgz) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" update xg_xlzx_xsxlpcb set sfgz = ? where id in(");
		paraList.add(sfgz);
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
			paraList.add(ids[i]);
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
}
