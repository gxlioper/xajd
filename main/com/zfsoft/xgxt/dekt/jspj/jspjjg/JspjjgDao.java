/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class JspjjgDao extends SuperDAOImpl<JspjjgForm>{

	@Override
	public List<HashMap<String, String>> getPageList(JspjjgForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JspjjgForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*");
		sql.append(" from ( select t1.*,");
		sql.append("t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.xz,t2.rxrq,  ");
		sql.append(" t2.sfzh,t2.bjmc,t2.lxdh,t2.xydm,t2.zydm,t2.bjdm,t2.xymc,t2.zybj,t2.zybjmc,t2.zymc,t2.nj,t4.sydm,t4.symc from (select xh,count(1) pjjss from xg_dekt_jspjglb group by xh) t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_xtwh_sybjglb t3 on t2.bjdm=t3.bjdm ");
		sql.append(" left join xg_xtwh_sydmb t4 on t3.sydm=t4.sydm ");
		sql.append(" left join xg_dekt_jspjglb t5 on t1.xh=t5.xh ");
		sql.append(" ) a  ");
		sql.append(" where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("jgid");
		super.setTableName("xg_dekt_jspjglb");
	}

	public List<String[]> getPjxqInfo(String[] inputValue, String[] outputValue) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xqmc,c.xm pjjsxm,d.rstjmc,a.pjsj xpjsj from xg_dekt_jspjglb a left join xqdzb b on a.xq=b.xqdm ");
		sql.append(" left join view_fdyxx c on c.zgh=a.pjjszgh"); 
		sql.append(" left join XG_DEKT_RSTJ d on a.ylzd1 = d.rstjdm "); 
		sql.append(" where a.xh=?");
		return dao.rsToVator(sql.toString(), inputValue, outputValue);
	}

	public boolean deleteExist(JspjjgForm model) throws Exception {
		StringBuilder sql = new StringBuilder(
		" delete from xg_dekt_jspjglb where xh=? and xn = ? and xq = ? and pjjszgh=?");
		return dao.runUpdate(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq(),model.getPjjszgh()});
	}

}
