/**
 * @部门:学工产品事业部
 * @日期：2015-5-26 下午02:03:11 
 */  
package com.zfsoft.xgxt.dekt.xfsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.qgzx.cjffsq.QgzxCjffsqForm;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class DektxfshDao extends SuperDAOImpl<DektxfshForm> {

	
	@Override
	public List<HashMap<String, String>> getPageList(DektxfshForm t)	throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DektxfshForm t, User user)	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		//String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.sqid,t1.xh,t1.xmid,t1.cyfs,decode(t1.cyfs,'gr','个人','tt','团体') cyfsmc,t1.hjsj,t1.sqsm,t1.filepath,t1.splc,t1.shzt sqzt, ");
		sql.append("t2.ssxydm,t2.xmdl,t2.lx,t2.rdxm,t2.rdnrbz,t2.dj,t2.xf,t2.yjsm,t3.xm,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc, ");
		sql.append("row_number() over(partition by t1.sqid order by m.shsj desc) rn,m.guid shid,m.gwid,m.shzt, ");
		sql.append("n.mc || '[' ||decode(m.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc, ");
		sql.append("replace( t2.lx||'->'||t2.rdxm||'->'||t2.rdnrbz||'->'||t2.dj,'->无',' ') rdnr ");
		sql.append("from XG_DEKT_DEKTXFSQB t1 left join XG_DEKT_XMDMB t2 on t1.xmid=t2.xmid ");
		sql.append("left join view_xsjbxx t3 on t1.xh=t3.xh ");
		sql.append("left join xg_xtwh_shztb m on t1.sqid=m.ywid ");
		sql.append("left join xg_xtwh_spgw n  on m.gwid = n.id ");
		sql.append("left join xg_xtwh_spgwyh o on m.gwid = o.spgw  where o.spyh='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (m.shzt<>0 and  m.shzt<>4)");
		} else {
			sql.append(" and (m.shzt=0  or m.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
	    sql.append(" and  rn = 1 ");
	    sql.append(searchTjByUser);
		sql.append(searchTj);
		//sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setClass(DektxfshForm.class);
		super.setKey("sqid");
		super.setTableName("XG_DEKT_DEKTXFSQB");
		
	}
	
	public boolean updateShzt (String sqid,String shzt) throws Exception{
		String sql="update XG_DEKT_DEKTXFSQB set shzt=? where sqid=? ";
		return dao.runUpdate(sql, new String[]{shzt,sqid});
	}
	
	public Map<String, String> getView(DektxfshForm form) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("select * from (select t1.sqid,t1.xh,t1.xmid,t1.cyfs,decode(t1.cyfs,'gr','个人','tt','团体') cyfsmc,t1.hjsj,t1.sqsm, ");
		sql.append("t1.filepath,t1.splc,t1.shzt sqzt,t2.ssxydm,t2.xmdl,t2.lx,t2.rdxm,t2.rdnrbz,t2.dj,t2.yjsm, ");
		sql.append("row_number() over(partition by t1.sqid order by t3.shsj desc) rn,nvl(t3.zd2,t2.xf) xf,replace( t2.lx||'->'||t2.rdxm||'->'||t2.rdnrbz||'->'||t2.dj,'->无',' ') rdnr ");
		sql.append("from XG_DEKT_DEKTXFSQB t1 left join XG_DEKT_XMDMB t2 on t1.xmid=t2.xmid left join xg_xtwh_shztb t3 on t1.sqid=t3.ywid) ");
		sql.append("where rn=1 and sqid=?");
		return dao.getMapNotOut(sql.toString(), new String[]{form.getSqid()});
	}
	
}
