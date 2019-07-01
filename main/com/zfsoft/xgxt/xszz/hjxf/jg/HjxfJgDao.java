/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 上午11:52:03 
 */  
package com.zfsoft.xgxt.xszz.hjxf.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xszz.hjxf.unit.UnitForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-15 上午11:52:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjxfJgDao extends SuperDAOImpl<HjxfJgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	private static final String  RDZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	@Override
	public List<HashMap<String, String>> getPageList(HjxfJgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjxfJgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t.xn || t2.xqmc xnxq");
		sql.append(" from XG_XSZZ_NEW_HJXFJGB t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh " +
				" left join xqdzb t2 on t.xq = t2.xqdm " +
				") t where 1= 1  ");
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
		// TODO 自动生成方法存根
		super.setClass(HjxfJgForm.class);
		super.setKey("jgid");
		super.setTableName("XG_XSZZ_NEW_HJXFJGB");
	}
	

	/**
	 * 
	 * @描述:获取困难生贫困等级(如果为空显示家庭经济不困难)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-3-16 下午02:22:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKnsdj(HjxfJgForm model){
		StringBuffer sql = new  StringBuffer();
		sql.append(" select t1.dcmc djmc ");
		sql.append(" from XG_XSZZ_NEW_HJXFJGB z");
		sql.append("   left join xg_xszz_new_knsjgb t ");
		sql.append(" on z.xh = t.xh and z.xn = t.xn");
		sql.append(" left join ");
		sql.append(" xg_xszz_new_kndcdmb t1");
		sql.append(" on t.rddc = t1.dcdm where z.jgid = ? and (t.xq = 'on' or t.xq = ?)");
	    String result = dao.getOneRs(sql.toString(), new String[]{model.getJgid(),model.getXq()}, "djmc");
		if(StringUtils.isNotNull(result)){
			return result;
		}else{
			return "家庭经济不困难";
		}
	}
	
	//删除结果表中已存在的数据
	//申请流过来覆盖原来结果表中的数据时先删除原来的数据，在进行插入
	public boolean delDkjg(String xh,String xn,String xq)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_XSZZ_NEW_HJXFJGB where xh = ? and xn = ? ");
		return dao.runUpdate(sql.toString(),new String[]{xh,xn});
	}
	
	//查询
	public List<HashMap<String,String>> getJtknXshjList(String xn ,User user ){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xm,");
		sql.append(" t1.bjmc,");
		sql.append(" case t3.dcmc ");
		sql.append(" when '特别困难' then  'A'");
		sql.append(" when '一般困难' then  'B'");
		sql.append(" when '突发事件困难' then 'C'");
		sql.append(" else   '' end dcmc,");
		sql.append(" case t.dkqk ");
		sql.append(" when '生源地助学贷款' then '生源地'");
		sql.append(" when '高校国家助学贷款' then '高校'");
		sql.append(" when '无' then '无'");
		sql.append(" else '无' end dkqk,");
		sql.append(" t.hjje,");
		sql.append(" t.hjje+t.wnqfje ljqf,");
		sql.append(" t.wnqfje,");
		sql.append(" t.jqjzsj,");
		sql.append(" t.sqyy");
		sql.append(" from XG_XSZZ_NEW_HJXFJGB t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh ");
		sql.append(" left join xg_xszz_new_knsjgb t2");
		sql.append(" on t.xh = t2.xh and  (t2.xq = 'on' or t2.xq is null or t.xq = t2.xq) and t.xn = t2.xn");
		sql.append(" left join xg_xszz_new_kndcdmb t3");
		sql.append(" on t2.rddc = t3.dcdm");
		sql.append(" where t.xn = ?");
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), new String[]{xn});
	}
	
	//数据汇总
	public HashMap<String, String> getSumHz(String xn,User user){
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(t.hjje) hjjezh,sum(t.wnqfje+t.hjje) ljqfje  from XG_XSZZ_NEW_HJXFJGB t where xn = ?  ");
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(searchTjByUser);
		return dao.getMapNotOut(sql.toString(), new String[]{xn});
	}
	
	//申请表获取缴清截止时间
	public HashMap<String, String> getJqNdYf(String jqjzsj){
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char(add_months(trunc(to_date(?,'yyyymmdd')),1),'yyyy') nd,to_char(add_months(trunc(to_date(?,'yyyymmdd')),1),'mm') yf from dual");
		return dao.getMapNotOut(sql.toString(), new String[]{jqjzsj,jqjzsj});
	}
	
	public boolean isNotExists(HjxfJgForm utilform){
		StringBuilder sql = new StringBuilder();
		String type = utilform.getType();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean flag = true;
		//进行申请表和结果表的重复验证,qb(全部)
	
			sql.append(" select count(1) flag from ");
			sql.append(" XG_XSZZ_NEW_HJXFJGB");
			sql.append(" where xn = ?");
			sql.append(" and xh = ?");
			sql.append(" and jgid <> ? ");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXh());
			parameter.add(utilform.getJgid());
		String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
}
