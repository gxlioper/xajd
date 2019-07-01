/**
 * @部门:学工产品事业部
 * @日期：2016-12-2 下午05:38:07 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zgk;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszModel;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-2 下午05:38:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class zgkDao extends SuperDAOImpl<zgkForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(zgkForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	/**
	 * 添加学生列表，只查在校生
	 */
	public List<HashMap<String, String>> getPageList(zgkForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		CsszModel csszmodel = new CsszService().getModel();
		sql.append(" select t.* from ( ");
		sql.append(" select *");
		sql.append(" from view_xsjbxx t");
		sql.append(" where t.XH not in (select xh");
		sql.append(" from xg_xsxx_dyxj_zgk");
		sql.append(" where xn = '"+csszmodel.getSqxn()+"'");
		sql.append(" and xq = '"+csszmodel.getSqxq()+"'");
		sql.append(" )");
		sql.append(" ) t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(zgkForm.class);
		this.setTableName("xg_xsxx_dyxj_zgk");
		this.setKey("id");
	}
	
	/**
	 * 
	 * @描述: 检验是否存在项目被使用
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 上午10:35:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotUsed(String[] ids){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) num");
		sql.append(" from (select xh || xn || xq uq");
		sql.append(" from xg_xsxx_dyxj_dypyjg");
		sql.append(" union");
		sql.append(" select xh || xn || xq uq");
		sql.append(" from xg_xsxx_dyxj_dypysqb)");
		sql.append("  where uq in (select xh || xn || xq from xg_xsxx_dyxj_zgk where id in (");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			paraList.add(ids[i]);
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		sql.append(" )");
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return (num).equals("0") ? true : false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 下午02:57:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean  saveBatchXs(List<String[]> paraList) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("insert into xg_xsxx_dyxj_zgk(xn,xq,xh) values(?,?,?)");
		int[] res = dao.runBatch(sql.toString(), paraList);
		boolean flag = true;
		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述: 从资格库中筛选学生
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 下午05:13:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZgkStuList(zgkForm t, User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ");
		sql.append(" (select t.xh,");
		sql.append(" t.xn,");
		sql.append(" t.xq,");
		sql.append(" t1.XM,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.ZYMC,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.XB");
		sql.append(" from xg_xsxx_dyxj_zgk t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" )t ");
		if(StringUtils.isNotNull(t.getXn()) && StringUtils.isNotNull(t.getXq()) ){
			sql.append(" where t.xn = '"+t.getXn()+"'");
			sql.append(" and t.xq = '"+t.getXq()+"' ");
		}else if(StringUtils.isNotNull(t.getXn()) && StringUtils.isNull(t.getXq())){
			sql.append(" where t.xn = '"+t.getXn()+"' ");
		}else if(StringUtils.isNull(t.getXn()) && StringUtils.isNotNull(t.getXq())){
			sql.append(" where t.xq = '"+t.getXq()+"'");
		}else{
			sql.append(" where 1 = 1");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述: 验证学号，学年，学期是否存在资格库中
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-6 上午09:24:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsInZgk(String xh,String xn,String xq){
		StringBuffer sql = new StringBuffer();
		sql.append("  select count(1) num from xg_xsxx_dyxj_zgk where xh = ? and xn = ? and xq = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{xh,xn,xq}, "num");
		return (num).equals("1") ? true : false; 
	}

}
