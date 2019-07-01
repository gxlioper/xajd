/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.strtsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class StrtsqDao extends SuperDAOImpl<StrtsqForm>{

	@Override
	public List<HashMap<String, String>> getPageList(StrtsqForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(StrtsqForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,nvl(t.sqzt,'未申请') zt from (select a.jgid,a.stlx,a.stqc,a.stjc,a.styx,a.ywzddw,a.stzdls,a.stjs,a.gzh,a.stzt,a.sjly,b.xm stzdlsxm ,c.bmmc,"); 
		sql.append(" (select count(1) from XG_TTGL_STCYB where jgid=a.jgid and shzt='1') strs,"); 
		sql.append(" (select count(1) from XG_TTGL_STCYB where jgid=a.jgid and xh='"+user.getUserName()+"' ) sfcz,"); 
		sql.append(" (select  case  when (k.shzt ='0'and tnzw is null) then '待审核'  when k.shzt = '2' then '已拒绝' when (k.shzt ='1'and tnzw ='成员') then '成员' "); 
		sql.append(" when tnzw='负责人' then '负责人' end zt from xg_ttgl_stcyb k   where k.jgid = a.jgid and xh='"+user.getUserName()+"') sqzt"); 
		sql.append(" from xg_ttgl_stgljgb a left join fdyxxb b on a.stzdls=b.zgh "); 
		sql.append(" left join zxbz_xxbmdm c on a.ywzddw = c.bmdm where a.stzt <>'2' ) t ");
		sql.append(" where 1 = 1 "); 
		if(StringUtils.isNotNull(t.getStqc())){
			sql.append(" and t.stqc like '%' || '"+t.getStqc()+"' || '%'");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("guid");
		super.setTableName("xg_ttgl_stcyb");
	}

	public boolean isExist(StrtsqForm model) {
		String sql = "select count(1) num from xg_ttgl_stcyb where xh=? and jgid = ? " ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getJgid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public HashMap<String, String> getStxxInfo(StrtsqForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm zdlsxm,t3.bmmc ");
		sql.append(" from xg_ttgl_stgljgb t1 left join fdyxxb t2 on t1.stzdls = t2.zgh");
		sql.append(" left join zxbz_xxbmdm t3 on t1.ywzddw = t3.bmdm  ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getJgid()});
	}

	public List<HashMap<String,String>> getFzrxx(StrtsqForm myForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,b.xm,b.xb,b.bjmc,b.sjhm,b.xymc,b.zymc,d.symc from xg_ttgl_stglfzrb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_xtwh_sybjglb c on b.bjdm=c.bjdm");
		sql.append(" left join xg_xtwh_sydmb d on c.sydm=d.sydm where");
		if ("1".equals(myForm.getSjly())) {
			sql.append(" a.sqid = ?");
		}else {
			sql.append(" a.jgid = ?");
		}
		return dao.getListNotOut(sql.toString(),new String[]{myForm.getJgid()});
	}

	public boolean cancelSq(StrtsqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_ttgl_stcyb where xh = ? and jgid=?");
		return dao.runUpdate(sql.toString(),new String[]{model.getXh(),model.getJgid()});
	}

}
