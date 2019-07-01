/**
 * @部门:学工产品事业部
 * @日期：2016-2-19 上午09:56:32 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-2-19 上午09:56:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class Util extends SuperDAOImpl {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List getPageList(Object t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List getPageList(Object t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-2-19 上午09:58:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isNotExists(UtilForm utilform){
		StringBuilder sql = new StringBuilder();
		String type = utilform.getType();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean flag = true;
		//进行申请表和结果表的重复验证,qb(全部)
		if(type.equalsIgnoreCase("qb")){
			sql.append(" select sum(flag) flag from");
			sql.append(" (select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjkjm_sqb");
			sql.append(" where xh = ?");
			sql.append(" union all");
			sql.append(" select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjkjm_jgb");
			sql.append(" where xh = ?)");
			parameter.add(utilform.getXh());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("sq")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjkjm_sqb");
			sql.append(" where xh = ?");
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("jg")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjkjm_jgb");
			sql.append(" where xh = ?");
			parameter.add(utilform.getXh());
		}
		String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
	
	//获取家庭成员信息
	public List<HashMap<String, String>> getjtcyxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.cyxm,");
		sql.append(" t1.mc cw,");
		sql.append(" t.cyxxdw,");
		sql.append(" t.cynsr cyysr");
		sql.append(" from xg_xszz_new_jtcyb t");
		sql.append(" left join xszz_jtcygxb t1");
		sql.append(" on t.cygx = t1.dm where t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	//获取学生基本信息
	public HashMap<String, String> getxsxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xm,");
		sql.append(" t.xb,");
		sql.append(" t.jgmc,");
		sql.append(" t.xh,");
		sql.append(" t.mzmc mz,");
		sql.append(" t.sfzh,");
		sql.append(" t.xymc, ");
		sql.append(" t.bjmc,");
		sql.append(" t.zymc,");
		sql.append(" t.sjhm,");
		sql.append(" t.jtdz,");
		sql.append(" t.yxmc ssyq,");
		sql.append(" t.nj,");
		sql.append(" t.dzyx,");
		sql.append(" t.jtyb");
		sql.append(" from view_xsxxb t");
		sql.append(" where t.xh  = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	//获取学生学制和年级用于判断是否为毕业生
	public boolean getSfbys(String xh,String usertype){
		StringBuilder sql = new StringBuilder();
		boolean flag = false;
		if(!usertype.equals("stu")){
			flag = true;
		}
		sql.append(" select xz,nj from view_xsjbxx where xh = ?");
		HashMap<String, String>  map = dao.getMapNotOut(sql.toString(), new String[]{xh});
		if(map != null && !map.equals("") && map.size() != 0){
			int nd = Integer.parseInt(Base.currXn.split("-")[1]);
		    int nj = Integer.parseInt(map.get("nj"));
		    int xz = Integer.parseInt(map.get("xz"));
		    if(nj+xz >  nd){
		    	flag = false;
		    }else{
		    	flag = true;
		    }
		}
	    
	    return flag;
	}
	
	//获取进步比例数据
    public List<HashMap<String, String>> getjmyjList(String jml){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select * from ");
    	sql.append(" (select t.* ,t.jml jmbl,replace(t.jml,'%','') jmlsx from xg_zdgxh_wxjkjm_jmyj t ) t");
    	sql.append(" where jml = ? order by to_number(jmlsx) asc,to_number(xssx) asc");
    	return dao.getListNotOut(sql.toString(), new String[]{jml});
    }
    
    //获取进步比例数据
    public List<HashMap<String, String>> getJml(){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select distinct t.jml jmbl  from xg_zdgxh_wxjkjm_jmyj t order by to_number(replace(t.jml,'%','')) asc");
    	return dao.getListNotOut(sql.toString(), new String[]{});
    }
    //获取毕业生无息借款总和
    public String getXsxxWxjkzh(String xh){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select sum(sqje) sqjezh from xg_zdgxh_wxjk_jgb where xh = ?");
    	return dao.getOneRs(sql.toString(), new String[]{xh}, "sqjezh");
    }
    //查询是否有无息贷款记录
    public String getWxdkjl(String xh){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) sfydk from xg_zdgxh_wxjk_jgb where xh = ?");
    	return dao.getOneRs(sql.toString(), new String[]{xh}, "sfydk");
    }
    
    //查看时获取进步比例list
    public List<HashMap<String, String>> getJmlCk(String[]jmyj,String jmbl){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> paralist = new ArrayList<String>();
    	sql.append(" select distinct t.jml jmbl,t.jmyj,t.xssx  from xg_zdgxh_wxjkjm_jmyj t where t.jml = ?  ");
    	paralist.add(jmbl);
    	sql.append(" and t.xssx in (");
    	for(int i = 0; i < jmyj.length; i++) {
			sql.append("?");
			if(i != jmyj.length-1){
				sql.append(",");
			}
			paralist.add(jmyj[i]);
		}
    	sql.append(")");
        sql.append(" order by to_number(t.xssx) asc");
    	return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
    }
    
    public List<HashMap<String, String>> getWxjmHz(String xydm,String nj){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select a.*,");
    	sql.append(" round(a.sqjmbl * a.zje / 100, 2) sqjmje,");
    	sql.append(" round(a.tyjmbl * a.zje / 100, 2) tyjmje");
    	sql.append(" from (select t.*,");
    	sql.append(" t1.xm,");
    	sql.append(" t1.bjmc,");
    	sql.append(" t1.xymc,");
    	sql.append(" t1.nj,");
    	sql.append(" t2.sqly,");
    	sql.append(" replace(nvl(t3.jmbl, t2.jmbl), '%', '') sqjmbl,");
    	sql.append(" replace(t2.jmbl, '%', '') tyjmbl");
    	sql.append(" from (select sum(sqje) zje, xh");
    	sql.append(" from xg_zdgxh_wxjk_jgb");
    	sql.append(" where xh in");
    	sql.append(" (select xh from view_xsjbxx t1 where t1.nj = ? and t1.xydm = ?)  and xh in (select xh from xg_zdgxh_wxjkjm_jgb)");
    	sql.append("  group by xh) t");
    	sql.append(" left join view_xsjbxx t1");
    	sql.append(" on t.xh = t1.xh");
    	sql.append(" left join xg_zdgxh_wxjkjm_jgb t2");
    	sql.append(" on t.xh = t2.xh");
    	sql.append("  left join xg_zdgxh_wxjkjm_sqb t3");
    	sql.append("  on t2.jgid = t3.sqid) a");
    	return dao.getListNotOut(sql.toString(), new String[]{nj,xydm});
    }

}
