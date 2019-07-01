/**
 * @部门:学工产品事业部
 * @日期：2016-12-6 下午03:18:23 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.jcjy;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgModel;
import com.zfsoft.xgxt.zxdk.ysjxj.ysjxj.YsjxjForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-基层就业
 * @类功能描述: 查询
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-12-6 下午03:18:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcjyDao extends SuperDAOImpl<JcjyModel>{
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setClass(JcjyModel.class);
		super.setKey("juid");
		super.setTableName("xg_zxdk_jcjy");
	}
	
	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(JcjyModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(JcjyModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.sjhm,t2.dzyx,t2.mzmc,t2.xb,t2.xz,t2.zzmmmc,t2.jtdzxx,t2.sfzh,t2.yhkh,t2.xm ");
		sql.append(" ,t1.*, t3.hylbmc from xg_zxdk_jcjy t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_zxdk_jcjyhylb t3 on t1.hylb = t3.hylbdm  ");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 查询行业类别名称、行业类别代码
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-12 上午09:08:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getHylbList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select hylbdm,hylbmc from xg_zxdk_jcjyhylb order by hylbdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 同一个学号代偿类别只能存在一条记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-12 上午09:09:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param dclb
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecord(String xh,String dclb){
		String sql = " select count(1) num from xg_zxdk_jcjy where xh = ? and dclb = ? ";
		String num = dao.getOneRs(sql, new String[]{ xh,dclb }, "num");
		return Integer.valueOf(num)>0;	
	}
	
	/**
	 * @描述: 取行业类别名称
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-12 上午09:10:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHylb(String xh){
		String sql = " select a.hylbmc from xg_zxdk_jcjyhylb a left join xg_zxdk_jcjy b on a.hylbdm = b.hylb where b.xh = ? ";
		return dao.getOneRs(sql, new String[]{xh}, "hylbmc");
	}
}
