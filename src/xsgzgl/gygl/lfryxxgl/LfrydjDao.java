/**
 * @部门:学工产品事业部
 * @日期：2014-8-19 下午05:08:08 
 */  
package xsgzgl.gygl.lfryxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述: 来访人员登记管理  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2014-8-19 下午05:08:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LfrydjDao extends SuperDAOImpl<LfrydjForm> {
	
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_GYGL_LFDJ");
		setKey("lfrdjid");
		setClass(LfrydjForm.class);
	
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LfrydjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LfrydjForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				//"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
				
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (select a.lfrdjid,a.lfrxm,a.lfsydm,(select lfsymc from xg_gygl_lfsydm where lfsydm = a.lfsydm) lfsymc,");
		sql.append("a.lfrxb,a.lfsj,a.lqsj,a.lfrsfzh,a.zbry,b.*,d.ldmc,c.qsh");
		sql.append(" from xg_gygl_lfdj a left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join view_xg_gygl_new_cwxx c on a.xh = c.xh ");
		sql.append(" left join xg_gygl_new_ldxxb d on a.lddm = d.lddm) t where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @描述: 获取来访人员登记信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-20 下午03:35:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lfrydjid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLfrydjxx(String lfrdjid){
		String sql = "select * from (select a.lfrdjid,a.lfrxm,a.lfrxb,a.lfsj,a.lqsj,a.lfrsfzh,a.zbry,a.bz,b.*,d.ldmc,c.qsh  "
			+ ",a.lfsydm,(select lfsymc from xg_gygl_lfsydm where lfsydm = a.lfsydm) lfsymc"
			+ "  from xg_gygl_lfdj a " + "  left join view_xsbfxx b " + "    on a.xh = b.xh " 
			+ "    left join view_xg_gygl_new_cwxx c on a.xh = c.xh"
			+ " left join xg_gygl_new_ldxxb d on a.lddm = d.lddm) where  lfrdjid = ?";
		return dao.getMapNotOut(sql, new String[]{lfrdjid});
	}

	/**
	 * 
	 * @描述: 删除信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-21 上午10:07:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lfrdjid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteLfrydjxx(String lfrdjid) throws Exception{
		String sql = "delete from XG_GYGL_LFDJ where LFRDJID = ? ";
		return dao.runDelete(sql, new String[]{lfrdjid});
			
	}
	
	
	/**
	 * 
	 * @描述: 删除信息批量
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-21 上午10:45:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] deleteLfrydjxxPl(List<String[]> pks) throws Exception{
		String sql = "delete from XG_GYGL_LFDJ where LFRDJID = ? ";
		return dao.runBatch(sql, pks);
		
	}
		
	/**
	 * 
	 * @描述:获取单个学生信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-26 上午09:27:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsxxOne(String xh){
		String sql="select a.xh,b.ldmc,b.qsh,a.xm,a.zymc,a.bjmc,a.xymc " +
				"from view_xsjbxx a,view_xg_gygl_new_cwxx b where a.xh=b.xh and a.xh=?";
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 
	 * @描述: 在校生信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-2 下午02:36:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxx(LfrydjForm t, String pk,String type) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xh,t1.xm,t1.nj,t1.xymc,t1.bjmc,t1.ldmc,t1.qsh,t1.cwh from (select c.nj||'!!luojw!!'||c.xydm||'!!luojw!!'||c.zydm||'!!luojw!!'||a.bjdm pk,a.xh,a.xm,a.nj,c.xymc,c.bjmc,b.ldmc,b.qsh,b.cwh from xsxxb a left join view_xg_gygl_new_cwxx b on a.xh = b.xh left join view_njxyzybj_all c ");
		if("bzr".equals(type)){
			sql.append(" on a.zybj=c.bjdm where a.sfzx = '在校' or a.sfzx is null order by a.xh) t1 where 1 = 1");
		} else {
			sql.append(" on a.bjdm=c.bjdm where a.sfzx = '在校' or a.sfzx is null order by a.xh) t1 where 1 = 1");
		}
		sql.append(" and pk='");
		sql.append(pk);
		sql.append("'");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/** 
	 * @描述:查询来访事由列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月17日 上午9:00:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLfsyList() {
		String sql = "SELECT lfsydm,lfsymc FROM xg_gygl_lfsydm";
		return dao.getListNotOut(sql, new String[]{});
	}
	
}
