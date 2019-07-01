/**
 * @部门:学工产品事业部
 * @日期：2013-5-16 下午01:34:17 
 */  
package com.zfsoft.xgxt.szdw.sdkj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cmj 
 * @时间： 2013-5-16 下午01:34:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwSdkjZdjskhDAO extends SuperDAOImpl<ZdlskhForm> {

	protected void setTableInfo() {
		super.setTableName("zdlskhb");
		super.setKey("id");
		super.setClass(ZdlskhForm.class);
	}
	
	/**
	 * 单个月份查看教师考核信息
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cmj
	 * @日期：2013-5-17 上午09:03:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListxx(ZdlskhForm t)
	throws Exception {
		StringBuilder sql=new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select * from zdlskhb where 1=1 ");
		
		if (!StringUtil.isNull(t.getNf())){
			params.add(t.getNf());
			sql.append("and nf like '%'||?||'%'");
		}
		if (!StringUtil.isNull(t.getYf())){
			params.add(t.getYf());
			sql.append("and yf like '%'||?||'%'");
		}
		if (!StringUtil.isNull(t.getXb())){
			params.add(t.getXb());
			sql.append("and xb like '%'||?||'%'");
		}
		if (!StringUtil.isNull(t.getZdls())){
			params.add(t.getZdls());
			sql.append("and zdls like '%'||?||'%'");
		}
		if (!StringUtil.isNull(t.getZgh())){
			params.add(t.getZgh());
			sql.append("and zgh like '%'||?||'%'");
		}
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(ZdlskhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql=new StringBuilder();
		List<String> params = new ArrayList<String>();
		String nf="";
		if (!StringUtil.isNull(t.getNf())){
			nf=t.getNf();
		}else{
			nf=Base.currNd;
		}
		String[] yfs=new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
		sql.append("select * from (");
		sql.append("select a.nf,a.yf,zrs,nvl(rs,0)rs from ( ");
		for (int i=0;i<yfs.length-1;i++) {
			sql.append("(select '"+nf+"' nf,'"+yfs[i]+"' yf,(select count(distinct zgh) from(select * from fdybjb union select * from bzrbbb))zrs from dual) union all ");
		}
		sql.append("(select '"+nf+"' nf,'"+yfs[11]+"' yf,(select count(distinct zgh) from(select * from fdybjb union select * from bzrbbb))zrs from dual)");
		sql.append(") a left join (select nf,yf,count(*)rs from zdlskhb group by nf,yf) b on a.nf=b.nf and a.yf=b.yf) where 1=1 ");
		
		if (!StringUtil.isNull(t.getYf())){
			params.add(t.getYf());
			sql.append(" and yf like '%'||?||'%'");
		}
		sql.append(" order by nf, yf");
		
		
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}


	
	@Override
	public List<HashMap<String, String>> getPageList(ZdlskhForm model, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql=new StringBuilder();
		sql.append("select a.nf,a.yf,zrs,nvl(rs,0)rs from (select '2013' nf, yf,(select count(distinct zgh) from(select * from fdybjb union all select * from bzrbbb))zrs from yfb) ");
		sql.append(" a left join (select nf,yf,count(*)rs from zdlskhb group by nf,yf) b on a.nf=b.nf and a.yf=b.yf");
		return getPageList(model, sql.toString(), inputV);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cmj
	 * @日期：2013-5-23 上午10:04:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getndList() {
		// TODO 自动生成方法存根
		return dao.getXnndList();
	}
}
