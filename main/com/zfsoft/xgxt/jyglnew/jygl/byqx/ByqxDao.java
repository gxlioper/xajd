package com.zfsoft.xgxt.jyglnew.jygl.byqx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ByqxDao extends SuperDAOImpl<ByqxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_JYGL_BYQX");
		super.setKey("xh");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ByqxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ByqxForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t2.lxmc jydwxzmc,t3.lxmc jyxsmc, ");
		sql.append(" t4.xm,t4.xb,t4.xymc,t4.xydm,t4.bjdm,t4.bjmc,t4.zydm,t4.zymc,t4.nj,t4.qqhm,t4.sjhm,t4.ksh,t4.sydmc,t4.xz, ");
		//徐州医药高等职业学校
		if(Base.xxdm.equals("70002")){
			sql.append(" t5.lxmc byqxmc,t6.lxmc jylbmc,t7.lxmc jyzkmc,");
		}
		sql.append(" (select pyccmc from xg_xsxx_pyccdmb c where t4.pycc=c.pyccdm) pyccmc ");
		sql.append(" from XG_JYGL_BYQX t1 ");
		sql.append(" left join xg_jygl_jygl_jydwxzb t2 on t1.jydwxz=t2.lxdm ");
		sql.append(" left join xg_jygl_jygl_jyxsb t3 on t1.jyxs=t3.lxdm ");
		sql.append(" left join view_xsxxb t4 on t1.xh=t4.xh ");
		//徐州医药高等职业学校
		if(Base.xxdm.equals("70002")){
			sql.append(" left join xg_jygl_jygl_byqxb t5 on t1.byqx = t5.lxdm");
			sql.append(" left join xg_jygl_jygl_jylbb t6 on t1.jylb = t6.lxdm");
			sql.append(" left join xg_jygl_jygl_jyzkb t7 on t1.jyzk = t7.lxdm");
		}
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 就业单位性质列表
	 */
	public List<HashMap<String, String>> getJydwxzList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jygl_jygl_jydwxzb ");
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
	
	/**
	 * @description	： 毕业去向列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-10 下午05:04:05
	 * @return
	 */
	public List<HashMap<String,String>> getByqxList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jygl_jygl_byqxb ");
		return dao.getListNotOut(sql.toString(), new String[]{ });
	}
	
	/**
	 * @description	： 就业类别列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-10 下午05:04:05
	 * @return
	 */
	public List<HashMap<String,String>> getJylbList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jygl_jygl_jylbb ");
		return dao.getListNotOut(sql.toString(), new String[]{ });
	}
	
	/**
	 * @description	： 就业状况列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-10 下午05:04:05
	 * @return
	 */
	public List<HashMap<String,String>> getJyzkList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jygl_jygl_jyzkb ");
		return dao.getListNotOut(sql.toString(), new String[]{ });
	}
	
	public HashMap<String, String> getModelMap(ByqxForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.lxmc jydwxzmc,t3.lxmc jyxsmc, ");
		sql.append(" t4.xm,t4.xb,t4.xymc,t4.xydm,t4.bjdm,t4.bjmc,t4.zydm,t4.zymc,t4.nj,t4.qqhm,t4.sjhm,t4.ksh,t4.sydmc,t4.xz, ");
		//徐州医药高等职业学校
		if(Base.xxdm.equals("70002")){
			sql.append(" t5.lxmc byqxmc,t6.lxmc jylbmc,t7.lxmc jyzkmc,");
		}
		sql.append(" (select pyccmc from xg_xsxx_pyccdmb c where t4.pycc=c.pyccdm) pyccmc ");
		sql.append(" from XG_JYGL_BYQX t1 ");
		sql.append(" left join xg_jygl_jygl_jydwxzb t2 on t1.jydwxz=t2.lxdm ");
		sql.append(" left join xg_jygl_jygl_jyxsb t3 on t1.jyxs=t3.lxdm ");
		sql.append(" left join view_xsxxb t4 on t1.xh=t4.xh ");
		//徐州医药高等职业学校
		if(Base.xxdm.equals("70002")){
			sql.append(" left join xg_jygl_jygl_byqxb t5 on t1.byqx = t5.lxdm");
			sql.append(" left join xg_jygl_jygl_jylbb t6 on t1.jylb = t6.lxdm");
			sql.append(" left join xg_jygl_jygl_jyzkb t7 on t1.jyzk = t7.lxdm");
		}
		sql.append(" where t1.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getXh() });
	}
	/**
	 * 是否已存在
	 */
	public boolean checkExistSave(ByqxForm model, User user) {
		String xh = "-1";
		StringBuilder sql = new StringBuilder(" select count(1) num from XG_JYGL_BYQX where xh = ? ");
		if(model.getXh() != null){
			xh = model.getXh();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { xh }, "num");
		return "0".equals(num);
	}
}
