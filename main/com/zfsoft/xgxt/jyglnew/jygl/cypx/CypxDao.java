package com.zfsoft.xgxt.jyglnew.jygl.cypx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jyglnew.jygl.byqx.ByqxForm;

public class CypxDao extends SuperDAOImpl<CypxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_JYGL_CYPX");
		super.setKey("id");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CypxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CypxForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t2.lxmc pxlxmc,t3.lxmc jyxsmc,decode(t1.sfqdzs,'1','是','否') sfqdzsmc, ");
		sql.append(" t4.xm,t4.xb,t4.xymc,t4.xydm,t4.bjdm,t4.bjmc,t4.zydm,t4.zymc,t4.nj,t4.qqhm,t4.sjhm,t4.ksh,t4.sydmc,t4.xz, ");
		sql.append(" (select pyccmc from xg_xsxx_pyccdmb c where t4.pycc=c.pyccdm) pyccmc ");
		sql.append(" from XG_JYGL_CYPX t1 ");
		sql.append(" left join xg_jygl_jygl_pxlxb t2 on t1.pxlx=t2.lxdm ");
		sql.append(" left join xg_jygl_jygl_jyxsb t3 on t1.jyxs=t3.lxdm ");
		sql.append(" left join view_xsxxb t4 on t1.xh=t4.xh ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 培训类型列表
	 */
	public List<HashMap<String, String>> getPxlxList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jygl_jygl_pxlxb ");
		return dao.getListNotOut(sql.toString(), new String[]{ });
	}
	/**
	 * 就业形式列表
	 */
	public List<HashMap<String, String>> getJyxsList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jygl_jygl_jyxsb ");
		return dao.getListNotOut(sql.toString(), new String[]{ });
	}
	public HashMap<String, String> getModelMap(CypxForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.lxmc pxlxmc,t3.lxmc jyxsmc,decode(t1.sfqdzs,'1','是','否') sfqdzsmc, ");
		sql.append(" t4.xm,t4.xb,t4.xymc,t4.xydm,t4.bjdm,t4.bjmc,t4.zydm,t4.zymc,t4.nj,t4.qqhm,t4.sjhm,t4.ksh,t4.sydmc,t4.xz, ");
		sql.append(" (select pyccmc from xg_xsxx_pyccdmb c where t4.pycc=c.pyccdm) pyccmc ");
		sql.append(" from XG_JYGL_CYPX t1 ");
		sql.append(" left join xg_jygl_jygl_pxlxb t2 on t1.pxlx=t2.lxdm ");
		sql.append(" left join xg_jygl_jygl_jyxsb t3 on t1.jyxs=t3.lxdm ");
		sql.append(" left join view_xsxxb t4 on t1.xh=t4.xh ");
		sql.append(" where t1.id = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getId() });
	}
	/**
	 * 是否已存在
	 */
	public boolean checkExistSave(CypxForm model, User user) {
		String id = "-1";
		StringBuilder sql = new StringBuilder(" select count(1) num from XG_JYGL_CYPX where xh = ? and pxsj = ? and id <> ? ");
		if(model.getId() != null){
			id = model.getId();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(), model.getPxsj(), id }, "num");
		return "0".equals(num);
	}
}
