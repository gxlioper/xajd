package com.zfsoft.xgxt.dtjs.tyzcgl.tyzc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * 团员注册
 */
public class TyzcDao extends SuperDAOImpl<TyzcForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TyzcForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TyzcForm t, User user)
			throws Exception {
		// =============== 学年 < ===================
		List<HashMap<String, String>> xnndList = Base.getXnndList2();
		StringBuilder xnndSql = new StringBuilder();
		for (int i = 0; i < xnndList.size(); i++) {
			HashMap<String, String> xnndMap = xnndList.get(i);
			xnndSql.append(" select '"+xnndMap.get("xn")+"' xn from dual ");
			if(i < xnndList.size() - 1){
				xnndSql.append(" union all ");
			}
		}
		// =============== 学年 > ===================
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append("  select a.*,a.xn||'!!@@!!'||a.xh pk,b.id,b.zcsj,c.xm zcrxm, ");
		sql.append("   decode(b.zczt, '1' , '已注册', '未注册') zcztmc, ");
		sql.append("   decode(b.zczt, '1' , '1', '-1') zczt ");
		sql.append("  from ( ");
		sql.append("    select a.*,b.xn from (   ");
		sql.append("      select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.zzmmmc,a.sfzh,a.sjhm  ");
		sql.append("      from view_xsjbxx a where a.zzmmmc like '%团员%' ");
		sql.append("    ) a, ");
		sql.append("    ("+xnndSql.toString()+") b  ");
		sql.append("  ) a left join xg_dtjs_new_tyzcb b on a.xh = b.xh and a.xn = b.xn  ");
		sql.append("  left join fdyxxb c on b.zcr = c.zgh ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 团员注册信息
	 */
	public HashMap<String , String> getTyzcxx(String xn, String xh)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("  select a.*,a.xn||'!!@@!!'||a.xh pk,b.id,b.zcsj, ");
		sql.append("   decode(b.zczt, '1' , '已注册', '未注册') zcztmc, ");
		sql.append("   decode(b.zczt, '1' , '1', '-1') zczt ");
		sql.append("  from ( ");
		sql.append("    select a.*,b.xn from (   ");
		sql.append("      select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm  ");
		sql.append("      from view_xsjbxx a where a.xh = ? ");
		sql.append("    ) a, ");
		sql.append("    (select '"+xn+"' xn from dual) b  ");
		sql.append("  ) a left join xg_dtjs_new_tyzcb b on a.xh = b.xh and a.xn = b.xn  ");
		return dao.getMapNotOut(sql.toString(), new String[]{ xh });
	}
	/**
	 * 批量删除
	 */
	public boolean tyzcDelete(String[] xhArr, String[] xnArr) throws Exception{
		String sql = "delete from xg_dtjs_new_tyzcb where xh=? and xn=?";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < xhArr.length; i++) {
			params.add(new String[]{xhArr[i], xnArr[i]});
		}
		int[] rs = dao.runBatch(sql, params);
		return dao.checkBatchResult(rs);
	}
	/**
	 * 批量增加
	 */
	public boolean tyzcInsert(String[] xhArr, String[] xnArr, String zcsj, String zcr) throws Exception{
		String sql = "insert into xg_dtjs_new_tyzcb(xh,xn,zczt,zcsj,zcr) values (?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < xhArr.length; i++) {
			params.add(new String[]{xhArr[i], xnArr[i], "1", zcsj, zcr });
		}
		int[] rs = dao.runBatch(sql, params);
		return dao.checkBatchResult(rs);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setClass(TyzcForm.class);
		super.setTableName("xg_dtjs_new_tyzcb");
	}

}
