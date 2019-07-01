/**
 * @部门:学工产品事业部
 * @日期：2014-7-4 下午02:27:43 
 */
package xsgzgl.qgzx.xsgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 勤工助学学生维护
 * @作者： 陶钢军 [1075]
 * @时间： 2014-7-4 下午02:27:43 
 * @版本： V5.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QgzxXsglDao extends SuperDAOImpl<QgzxXsglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QgzxXsglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 学生结果查询
	 */
	public List<HashMap<String, String>> getPageList(QgzxXsglForm t, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.* from (select b.*,b.sydm1 sydm,b.symc1 symc,case when a.sfgmbx = '0' then '否' else '是' end sfgmbx from xg_qgzx_qgzxxsb a join view_xsjbxx b on a.xh = b.xh ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by nj,xymc,zymc,bjmc desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	protected void setTableInfo() {
		super.setKey("xh");
		super.setTableName("xg_qgzx_qgzxxsb");
	}
	
	/**
	 * 
	 * @描述:获取学生
	 * @作者：陶钢军 [1075]
	 * @日期：2014-7-7 下午04:30:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsPageList(QgzxXsglForm model, User user) throws Exception{
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select a.*,a.sydm1 sydm,a.symc1 symc from view_xsjbxx a where not exists (select 1 from xg_qgzx_qgzxxsb c where a.xh = c.xh ) ");
		sql.append("order by a.nj, a.xydm, a.zydm, a.bjdm) a where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by nj,xymc,zymc,bjmc desc");
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:添加勤工助学学生
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-30 下午02:14:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveQgzxXs(String xh) throws Exception{
		StringBuffer sql = new StringBuffer();
		return dao.runInsert("xg_qgzx_qgzxxsb", new String[]{"xh"}, new String[]{xh});
	}

	/**
	 * @功能描述:维护购买保险信息
	 * @auther: 王晨辉[1692]
	 */
	public boolean updateSfgmbx(QgzxXsglForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_qgzx_qgzxxsb set sfgmbx = ? where xh = ?");
		return dao.runUpdate(sql.toString(),new String[]{model.getSfgmbx(),model.getXh()});
	}

	public HashMap<String, String> getDetail(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_qgzx_qgzxxsb where xh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}

	public boolean plUpdate(String xh) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_qgzx_qgzxxsb set sfgmbx = '1' where xh = ?");
		return dao.runUpdate(sql.toString(),new String[]{xh});
	}

	public List<HashMap<String,String>> getQgryAllList(QgzxXsglForm t, User user)throws Exception {
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		//权限控制
		sql.append("select t1.* from (select b.*,b.sydm1 sydm,b.symc1 symc,case when a.sfgmbx = '0' then '否' else '是' end sfgmbx from xg_qgzx_qgzxxsb a join view_xsjbxx b on a.xh = b.xh ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by nj,xymc,zymc,bjmc desc");
		return getPageList(t, sql.toString(), inputValue);
	}
}
