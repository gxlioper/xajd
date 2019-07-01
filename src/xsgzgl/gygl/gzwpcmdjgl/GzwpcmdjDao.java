/**
 * @部门:学工产品事业部
 * @日期：2014-8-27 下午04:03:51 
 */  
package xsgzgl.gygl.gzwpcmdjgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述: 贵重物品出门登记管理  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2014-8-27 下午04:03:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzwpcmdjDao extends SuperDAOImpl<GzwpcmdjForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzwpcmdjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzwpcmdjForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
		//"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.gzwpdjid,a.wpmc,a.cmsj,a.zbry,a.bz,b.*,c.ldmc,c.qsh" + 
				" from xg_gygl_gzwpcmdj a left join view_xsbfxx b on a.xh = b.xh" + 
				" left join view_xg_gygl_new_cwxx c on a.xh = c.xh where 1=1 ")
		.append(searchTj);
			
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述: 获取贵重物品出门登记信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午09:37:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzwpdjid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getGzwpcmdjxx(String gzwpdjid) {
		String sql = "select * from (select a.gzwpdjid,a.wpmc,a.cmsj,a.zbry,a.bz,b.*,c.ldmc,c.qsh  "
			+ "  from xg_gygl_gzwpcmdj a " + "  left join view_xsbfxx b " + "    on a.xh = b.xh " 
			+ "    left join view_xg_gygl_new_cwxx c on a.xh = c.xh) where  gzwpdjid = ?";
		return dao.getMapNotOut(sql, new String[]{gzwpdjid});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setTableName("XG_GYGL_GZWPCMDJ");
		setClass(GzwpcmdjForm.class);

	}
	
	/**
	 * 
	 * @描述: 删除信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午09:48:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzwpdjid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteGzwpcmdjxx(String gzwpdjid) throws Exception{
		String sql = "delete from XG_GYGL_GZWPCMDJ where GZWPDJID = ? ";
		return dao.runDelete(sql, new String[]{gzwpdjid});
		
	}
	
	
	/**
	 * 
	 * @描述: 删除信息批量
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午09:56:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] deleteGzwpcmdjxxPl(List<String[]> pks) throws Exception{
		String sql = "delete from XG_GYGL_GZWPCMDJ where GZWPDJID = ? ";
		return dao.runBatch(sql, pks);
		
	}
	
	/**
	 * 
	 * @描述: 保存更新新增贵重物品出门登记信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午11:23:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveGzwpcmdjxx(GzwpcmdjForm model) throws Exception{
		
		String select = "select GZWPDJID , WPMC , CMSJ , ZBRY , BZ , XH from XG_GYGL_GZWPCMDJ where GZWPDJID = ? ";
		String update = "update XG_GYGL_GZWPCMDJ set WPMC = ? , CMSJ = ? , ZBRY = ? , BZ = ? , XH = ? where GZWPDJID = ? ";
		
		/**
		 * 查询
		 */
		List<HashMap<String, String>> ls = dao.getListNotOut(select, new String[]{model.getGzwpdjid()});
		
		/**
		 * 更新
		 */
		if(ls != null && ls.size() >= 1) {
			return dao.runUpdate(update, new String[]{model.getWpmc() , model.getCmsj() , model.getZbry() , model.getBz() , model.getXh() , model.getGzwpdjid()});
		/**
		 * 新增	
		 */			
		}else{
			
			return runInsert(model);
		}
		
	}
	
	/**
	 * 
	 * @描述: 获取单个学生信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午10:48:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsxxOne(String xh){
		String sql = "select a.xh,b.ldmc,b.qsh,a.xm,a.zymc,a.bjmc,a.xymc,a.sfzh,a.xb " +
				"from view_xsjbxx a,view_xg_gygl_new_cwxx b where a.xh=b.xh and a.xh=?";
		return dao.getMapNotOut(sql, new String[]{xh});
	}
}
