/**
 * @部门:学工产品事业部
 * @日期：2015-7-6 上午09:53:06 
 */  
package xsgzgl.jxgl.general.jxqjgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-6 上午09:53:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JxqjjgDao extends SuperDAOImpl<JxqjjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JxqjjgForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JxqjjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.* from view_new_jxgl_jxqjjg a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取请假类型
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-6 上午10:25:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjlx() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select qjlxid,qjlxmc from xg_rcsw_qjgl_qjlx order by qjlxid asc");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	public HashMap<String, String> getQjjgForPrint(String qjjgid) {
		String sql = "select * from view_new_jxgl_jxqjjg where qjid=?";
		return dao.getMapNotOut(sql.toString(), new String[] { qjjgid });
	}
	@Override
	public JxqjjgForm getModel(JxqjjgForm myForm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,(select xqmc from xqdzb where xqdm = a.xq) xqmc from xg_jxgl_jxqjjgb a  where a.qjid=? ");
		return getModel(sql.toString(), new String[]{myForm.getQjid()});

	}

	@Override
	protected void setTableInfo() {
		this.setKey("qjid");
		this.setTableName("xg_jxgl_jxqjjgb");
		this.setClass(JxqjjgForm.class);

	}
	
	/*
	 * 最高级建制ID
	 */
	public String getJzid(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select jxid from xg_jxgl_jxxxwhb where jxzt = 'start' ");
		return dao.getOneRs(sql.toString(), new String[]{}, "jxid");
	}
	
	/*
	 * 建制名称
	 */
	public String getJzmc(String jxid,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh, a.jzmc001 || a.jzmc002 || a.jzmc003 || a.jzmc004 jzmc from (select a.*, b.xh ");
        sql.append(" from (select jxjz001.jzid jzid001, jxjz001.jzmc jzmc001, jxjz002.jzid jzid002, jxjz002.jzmc jzmc002, jxjz003.jzid jzid003, ");
        sql.append(" jxjz003.jzmc jzmc003, jxjz004.jzid jzid004, jxjz004.jzmc jzmc004 from xg_jxgl_jxjzwhb a left join xg_jxgl_jxjzwhb jxjz001 ");
        sql.append(" on a.jzid = jxjz001.sjid left join xg_jxgl_jxjzwhb jxjz002 on jxjz001.jzid = jxjz002.sjid left join xg_jxgl_jxjzwhb jxjz003 ");
        sql.append(" on jxjz002.jzid = jxjz003.sjid left join xg_jxgl_jxjzwhb jxjz004 on jxjz003.jzid = jxjz004.sjid where a.jzid = ?) a ");
        sql.append(" left join xg_jxgl_jxjzmdb b on a.jzid004 = b.jzid left join (select a.xh from (select xh from bks_xsjbxx a where not exists ");
        sql.append(" (select 1 from xsxxb b where a.xh = b.xh) union all select a.xh from xsxxb a where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null)) a) c ");
        sql.append(" on b.xh = c.xh) a where a.xh = ? ");
		return dao.getOneRs(sql.toString(), new String[]{jxid,xh}, "jzmc");
	}


	public String getJzmc1(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh, a.jzmc002 || a.jzmc003 || a.jzmc006 jzmc from (select a.*, b.xh ");
        sql.append(" from (select jxjz001.jzid jzid001, jxjz001.jzmc jzmc001, jxjz002.jzid jzid002, jxjz002.jzmc jzmc002, jxjz003.jzid jzid003, ");
        sql.append(" jxjz003.jzmc jzmc003, jxjz004.jzid jzid004, jxjz004.jzmc jzmc004,jxjz005.jzid jzid005,jxjz005.jzmc jzmc005,jxjz006.jzid jzid006,jxjz006.jzmc jzmc006 from xg_jxgl_jxjzwhb a left join xg_jxgl_jxjzwhb jxjz001 ");
        sql.append(" on a.jzid = jxjz001.sjid left join xg_jxgl_jxjzwhb jxjz002 on jxjz001.jzid = jxjz002.sjid left join xg_jxgl_jxjzwhb jxjz003 ");
        sql.append(" on jxjz002.jzid = jxjz003.sjid left join xg_jxgl_jxjzwhb jxjz004 on jxjz003.jzid = jxjz004.sjid  left join xg_jxgl_jxjzwhb jxjz005 on jxjz004.jzid = jxjz005.sjid left join xg_jxgl_jxjzwhb jxjz006 on jxjz005.jzid = jxjz006.sjid) a ");
        sql.append(" left join xg_jxgl_jxjzmdb b on a.jzid006 = b.jzid left join (select a.xh from (select xh from bks_xsjbxx a where not exists ");
        sql.append(" (select 1 from xsxxb b where a.xh = b.xh) union all select a.xh from xsxxb a where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null)) a) c ");
        sql.append(" on b.xh = c.xh) a where a.xh = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "jzmc");
	}
	

}
