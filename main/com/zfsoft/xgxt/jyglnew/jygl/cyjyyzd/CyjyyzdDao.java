package com.zfsoft.xgxt.jyglnew.jygl.cyjyyzd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class CyjyyzdDao extends SuperDAOImpl<CyjyyzdForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_JYGL_CYJY");
		super.setKey("jyid");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CyjyyzdForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CyjyyzdForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t2.lxmc sshymc,decode(t1.gslx,'st','实体','网店') gslxmc, ");
		sql.append(" t4.xm,t4.xb,t4.xymc,t4.xydm,t4.bjdm,t4.bjmc,t4.zydm,t4.zymc,t4.nj,t4.qqhm,t4.sjhm,t4.ksh,t4.sydmc,t4.xz, ");
		sql.append(" (select pyccmc from xg_xsxx_pyccdmb c where t4.pycc=c.pyccdm) pyccmc ");
		sql.append(" from XG_JYGL_CYJY t1 ");
		sql.append(" left join xg_jygl_jygl_sshyb t2 on t1.sshy=t2.lxdm ");
		sql.append(" left join view_xsxxb t4 on t1.xh=t4.xh ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 所属行业列表
	 */
	public List<HashMap<String, String>> getSshyList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jygl_jygl_sshyb ");
		return dao.getListNotOut(sql.toString(), new String[]{ });
	}
	public HashMap<String, String> getModelMap(CyjyyzdForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.lxmc sshymc,decode(t1.gslx,'st','实体','网店') gslxmc, ");
		sql.append(" t4.xm,t4.xb,t4.xymc,t4.xydm,t4.bjdm,t4.bjmc,t4.zydm,t4.zymc,t4.nj,t4.qqhm,t4.sjhm,t4.ksh,t4.sydmc,t4.xz, ");
		sql.append(" (select pyccmc from xg_xsxx_pyccdmb c where t4.pycc=c.pyccdm) pyccmc ");
		sql.append(" from XG_JYGL_CYJY t1 ");
		sql.append(" left join xg_jygl_jygl_sshyb t2 on t1.sshy=t2.lxdm ");
		sql.append(" left join view_xsxxb t4 on t1.xh=t4.xh ");
		sql.append(" where t1.jyid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getJyid() });
	}
	/**
	 * 是否已存在
	 */
	public boolean checkExistSave(CyjyyzdForm model, User user) {
		String xh = "-1";
		StringBuilder sql = new StringBuilder(" select count(1) num from XG_JYGL_CYJY where xh = ? and gsmc = ? ");
		if(model.getXh() != null){
			xh = model.getXh();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { xh,model.getGsmc() }, "num");
		return "0".equals(num);
	}
}
