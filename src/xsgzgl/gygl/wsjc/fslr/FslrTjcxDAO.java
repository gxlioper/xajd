package xsgzgl.gygl.wsjc.fslr;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class FslrTjcxDAO extends SuperDAOImpl<FslrForm> {

	@Override
	public List<HashMap<String, String>> getPageList(FslrForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FslrForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, decode(zs, 0, 0, round(t.yxrs / zs * 100, 2)) yxbfb,");
		sql.append(" decode(zs, 0, 0, round(t.hgrs / zs * 100, 2)) hgbfb,");
		sql.append(" decode(zs, 0, 0, round(t.bhgrs / zs * 100, 2)) bhgbfb");
		sql.append(" from (select t.xymc,t.xydm,sum(case when t1.dj = '优秀' then '1' else '0' end) yxrs,");
		sql.append(" sum(case when t1.dj in('合格','优秀') then '1' else '0' end) hgrs,");
		sql.append(" sum(case when t1.dj = '不合格' then '1' else '0' end) bhgrs, count(t1.nd) zs");
		sql.append(" from (select distinct xydm, xymc from view_njxyzybj) t");
		sql.append(" left join (select * from view_xg_gygl_qsfsb_zjcmtjcx a where 1 = 1");
		sql.append(searchTj);
		sql.append(") t1 on t.xydm = t1.xydm group by t.xydm, t.xymc) t order by t.xydm asc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}

}
