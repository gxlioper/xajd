/**
 * @部门:学工产品事业部
 * @日期：2015-5-26 下午02:03:11 
 */  
package xsgzgl.gygl.xyzsgl.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-5-26 下午02:03:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyzsshDao extends SuperDAOImpl<XyzsShForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyzsShForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyzsShForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		//String qxfw = SearchService.getQxfw(user, "t.gwid", "t.qxfw", "t.yrdw",searchTjByUser);
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.sqbh,t1.xh,t1.xn,t1.sqsjstart,t1.sqsjend,t1.sqsj,t1.splc,t1.xxdz,t1.lxdh,t1.parentslxdy,t1.xl,t1.zwjzyy,t1.bz,t4.shzt,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,");
		sql.append(" substr(t1.sqsjstart, 0, 4) || '-' || substr(t1.sqsjstart, 5, 2) || '-' ||substr(t1.sqsjstart, 7, 2) ||'  ' ||'至' || ' ' ||substr(t1.sqsjend, 0, 4) || '-' || substr(t1.sqsjend, 5, 2) || '-' ||substr(t1.sqsjend, 7, 2)  as sqsj1, ");
		sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append("decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t6.gwz, ");
		sql.append(" row_number() over(partition by t1.sqbh order by t4.shsj desc) rn ");
		sql.append("from xg_gygl_xyzsgl_sqb t1 left join view_xsjbxx t2 on t1.xh = t2.xh  ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqbh = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id where t5.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
	    sql.append(" and  rn = 1 ");
	    sql.append(searchTjByUser);
		sql.append(searchTj);
		//sql.append(qxfw);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XyzsShForm.class);
		super.setKey("sqbh");
		super.setTableName("xg_gygl_xyzsgl_sqb");
		
	}
	
	public boolean updateSqjl(String ywid, String shzt) throws Exception{
		String sql = "update xg_gygl_xyzsgl_sqb set shzt=?  where sqbh = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,ywid});
		
	}

}
