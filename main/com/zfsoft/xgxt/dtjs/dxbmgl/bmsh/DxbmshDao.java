package com.zfsoft.xgxt.dtjs.dxbmgl.bmsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @功能描述：党校报名审核dao
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午03:36:29 
 */
public class DxbmshDao extends SuperDAOImpl<DxbmshForm> {

	@Override
	public List<HashMap<String, String>> getPageList(DxbmshForm t) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DxbmshForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select * from ( select t1.*,t2.xm,t2.xb,t2.nj,t2.xymc,t2.xydm,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,");
		sql.append(" d.gwid,d.shr,d.shyj,f.mc || '[' || decode(d.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中','审核中') || ']' shztmc,");
		sql.append(" d.guid shid, row_number() over(partition by t1.sqid order by d.shsj desc) rn from (select x.*,y.pxqc,y.pxsj from XG_DTJS_DXBMSQB x left join xg_dtjs_dxpxb y on x.pxid=y.id) t1");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh left join xg_xtwh_shztb d on t1.sqid = d.ywid left join xg_xtwh_spgwyh e on d.gwid = e.spgw");
		sql.append(" left join xg_xtwh_spgw f on d.gwid = f.id where e.spyh = '"+user.getUserName()+"' and t1.shzt<>9 and d.shzt<>9 ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		}else{
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}	
		sql.append(" ) a where rn = 1 )a ");
		sql.append(" where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * @功能描述：更新申请信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-8 上午09:20:45 
	 * @param id
	 * @param zt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateSqxx(String id,String zt) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("update xg_dtjs_dxbmsqb set shzt=? where sqid=?");
		return dao.update(sql.toString(), new String[]{zt,id})>0?true:false;
	}

	/** 
	 * @功能描述：根据申请id获取信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-13 上午11:20:37 
	 * @param qsid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXspxBySqid(String qsid) throws Exception {
		StringBuffer sql = new StringBuffer("select * from (select a.sqid,a.xh,a.pxid,b.pxqc,b.pxsj,b.pxnr,b.kqcjbfb,b.sjcjbfb,b.bjcjbfb,b.kscjbfb,b.bmkssj,c.jgid,b.bmjssj,b.fbr");
		sql.append(" from xg_dtjs_dxbmsqb a left join xg_dtjs_dxpxb b on a.pxid = b.id left join xg_dtjs_dxbmjgb c on a.xh = c.xh and a.pxid = c.pxid) where sqid=?");
		String[] outV=new String[]{"xh","pxid","pxqc","pxsj","pxnr","kqcjbfb","sjcjbfb","bjcjbfb","kscjbfb","bmkssj","jgid"};
		return dao.getMap(sql.toString(), new String[]{qsid}, outV);
	}
	
	@Override
	protected void setTableInfo() {
		this.setKey("sqid");
		this.setTableName("xg_dtjs_dxbmsqb");
		this.setClass(DxbmshForm.class);
	}

}
