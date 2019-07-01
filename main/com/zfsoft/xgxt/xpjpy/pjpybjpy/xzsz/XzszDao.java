package com.zfsoft.xgxt.xpjpy.pjpybjpy.xzsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XzszDao extends SuperDAOImpl<XzszForm> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("guid");
		super.setTableName("xg_pjpy_new_pjpy_bjpyxz");
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XzszForm t)
			throws Exception {
		return null;
	}

		/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XzszForm t, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
//		sql.append(" select a.bjrs,case when d.bjdm is null then 'false' else 'true' end sfksbjpy,nvl(c.xzrs,'0') xzrs,nvl(c.tjzt,'0') tjzt,decode(c.tjzt,'1','已提交','未提交') tjztmc,b.*,c.xms,c.xsdbxm from ( ");
		sql.append(" select a.bjrs,'false' sfksbjpy,nvl(c.xzrs,'0') xzrs,nvl(c.tjzt,'0') tjzt,decode(c.tjzt,'1','已提交','未提交') tjztmc,b.*,c.xms,c.xsdbxm from ( ");
		sql.append(" select bjdm,count(1) bjrs  from xg_pjpy_new_cpmdb where xn||xq=(select xn||(case when xq is null then 'on' else xq end) from xg_pjpy_new_csszb) group by bjdm ");
		sql.append(" ) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.bjdm,a.tjzt,count(1) over (partition by a.bjdm order by a.xh asc) xzrs, ");
		sql.append(" WM_CONCAT(b.xm) over (partition by a.bjdm order by a.xh asc) xms, ");
		sql.append(" WM_CONCAT(decode(a.sfxsdb,'1',b.xm,'')) over (partition by a.bjdm order by a.xh asc) xsdbxm, ");
		sql.append(" row_number() over (partition by a.bjdm order by a.xh desc) rn ");
		sql.append(" from xg_pjpy_new_pjpy_bjpyxz a ");
		sql.append(" left join xsxxb b on a.xh=b.xh ");
		sql.append(" ) a where rn='1' ");
		sql.append(" ) c on a.bjdm=c.bjdm ");
		// ============ 是否开始评奖班级评议 < =================
//		sql.append(" left join ( ");
//		sql.append(" select distinct b.bjdm from xg_pjpy_new_pjpy_bjpyxzpy a ");
//		sql.append(" left join (select xh,xm,bjdm from xg_pjpy_new_cpmdb where xn||xq=(select xn||(case when xq is null then 'on' else xq end) from xg_pjpy_new_csszb)) b on a.bjpyr=b.xh ");
//		sql.append(" where a.tjzt='1' and a.xn||a.xq=(select xn||(case when xq is null then 'on' else xq end) from xg_pjpy_new_csszb) ");
//		sql.append(" ) d on a.bjdm=d.bjdm ");
		// ============ 是否开始评奖班级评议 > =================
		sql.append(" ) t1 where 1=1 and bjrs is not null ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}
	/**
	 * 增加评奖班级评议小组设置
	 */
	public List<HashMap<String, String>> getAddXzszList(XzszForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select a.xh,a.xm,b.* from ( ");
		sql.append(" select a.xh,a.xm,a.bjdm from (select xh,xm,bjdm from xg_pjpy_new_cpmdb where xn||xq=(select xn||(case when xq is null then 'on' else xq end) from xg_pjpy_new_csszb)) a where a.bjdm =? ");
		sql.append(" and not exists (select 1 from xg_pjpy_new_pjpy_bjpyxz b where a.bjdm=b.bjdm and a.xh=b.xh) ");
		sql.append(" ) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getBjdm()}, inputValue));
	}
	/**
	 * 修改评奖班级评议小组设置
	 */
	public List<HashMap<String, String>> getUpdateXzszList(XzszForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select a.xh,a.xm,a.guid,a.sfxsdb,a.sfxsdbmc,b.* from ( ");
		sql.append(" select a.xh,a.xm,a.bjdm,b.guid,b.sfxsdb,decode(b.sfxsdb,'1','是','否') sfxsdbmc from xg_pjpy_new_pjpy_bjpyxz b left join (select xh,xm,bjdm from xg_pjpy_new_cpmdb where xn||xq=(select xn||(case when xq is null then 'on' else xq end) from xg_pjpy_new_csszb)) a on a.xh=b.xh where b.bjdm =? ");
		sql.append(" ) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getBjdm()}, inputValue));
	}
	
	/**
	 * 重置整个班级的提交状态
	 */
	public boolean sftjNotAll(String bjdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_pjpy_new_pjpy_bjpyxz ");
		sql.append(" set ");
		sql.append(" tjr = null, ");
		sql.append(" tjsj = null, ");
		sql.append(" tjzt = '0' ");
		sql.append(" where bjdm = ?");
		return dao.runUpdate(sql.toString(),new String[]{ bjdm });
	}
	/**
	 * 提交整个班级
	 */
	public boolean sftjYesAll(String bjdm, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_pjpy_new_pjpy_bjpyxz ");
		sql.append(" set ");
		sql.append(" tjr = ?, ");
		sql.append(" tjsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), ");
		sql.append(" tjzt = '1' ");
		sql.append(" where bjdm = ?");
		return dao.runUpdate(sql.toString(),new String[]{ user.getUserName(),bjdm });
	}
	
	/**
	 * 保存设置学生代表
	 */
	public boolean xsdbBc(String[] guids, String bjdm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_pjpy_new_pjpy_bjpyxz ");
		sql.append(" set ");
		sql.append(" sfxsdb = '0' ");
		sql.append(" where bjdm = ?");
		boolean rs = dao.runUpdate(sql.toString(),new String[]{ bjdm });
		if(rs){
			sql = new StringBuilder();
			sql.append(" update xg_pjpy_new_pjpy_bjpyxz ");
			sql.append(" set ");
			sql.append(" sfxsdb = '1' ");
			sql.append(" where guid = ?");
			rs = dao.runUpdate(sql.toString(),new String[]{ guids[0] });
		}
		return rs;
	}
	/**
	 * 删除班级评议
	 */
	public boolean delBjpyxzpy(String[] guids) throws Exception {
		List<String[]> params = new ArrayList<String[]>();
		String sql = " delete from xg_pjpy_new_pjpy_bjpyxzpy where xn||xq=(select xn||(case when xq is null then 'on' else xq end) from xg_pjpy_new_csszb) and bjpyr=(select xh from xg_pjpy_new_pjpy_bjpyxz where guid = ?) ";
		for (int i = 0; i < guids.length; i++) {
			params.add(new String[]{ guids[i] });
		}
		int[] num = dao.runBatch(sql, params);
		return dao.checkBatchResult(num);
	}
}
