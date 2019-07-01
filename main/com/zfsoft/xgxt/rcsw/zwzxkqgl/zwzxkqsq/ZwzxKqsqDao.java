/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:37:39 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午02:37:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwzxKqsqDao extends SuperDAOImpl<ZwzxKqsqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqsqForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select t1.*,");
		sql.append(" (select xqmc");
		sql.append("  from xqdzb b");
		sql.append("  where t1.xq = b.xqdm) xqmc,");
		sql.append(" t2.nj,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.bjmc,");
		sql.append(" t3.lxmc cclxmc,");
		sql.append(" nvl(t4.qqxss, 0) qqxss,");
		sql.append(" decode(t1.shzt,");
		sql.append(" '0',");
		sql.append(" '未提交',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '退回',");
		sql.append(" '5',");
		sql.append(" '审核中',");
		sql.append(" t1.shzt) shztmc,");
		sql.append(" t5.xm jlrxm,");
		sql.append(" t6.bmmc jlrbmmc");
		/**
		 * 苏州旅游个性化代码
		 */
		if("2297".equals(Base.xxdm)){
			sql.append(" ,nvl(t7.rs,0) ydrsszly");
		}

		/**
		 * 西安科技
		 */
		if("10704".equals(Base.xxdm)){
			sql.append(" ,c.dbfdy ");
		}
		sql.append(" from XG_RCSW_ZXKQ_SQB t1");
		sql.append(" left join VIEW_NJXYZYBJ_all t2");
		sql.append(" on t1.bjdm = t2.bjdm");
		sql.append(" left join XG_RCSW_ZWZXKQ_CCLXB t3");
		sql.append(" on t1.cclxdm = t3.lxdm");
		sql.append(" left join (select count(1) qqxss, id");
		sql.append(" from XG_RCSW_ZWZXKQ_XSKQXXB");
		sql.append(" group by id) t4");
		sql.append(" on t1.sqid = t4.id");
		sql.append(" left join fdyxxb t5");
		sql.append(" on t1.jlr = t5.zgh");
		sql.append(" left join zxbz_xxbmdm t6");
		sql.append(" on t6.bmdm = t5.bmdm");
		/**
		 * 苏州旅游个性化代码
		 */
		if("2297".equals(Base.xxdm)){
			sql.append(" left join (select count(1) rs, bjdm");
			sql.append(" from view_xsxxb");
			sql.append(" group by bjdm) t7");
			sql.append(" on t1.bjdm = t7.bjdm");
		}

		//西安科技
		if("10704".equals(Base.xxdm)){
			sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		}

		sql.append("  ) t");
		sql.append("  where 1 = 1");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:判断是否存在填写记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午04:29:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveSqJl(ZwzxKqsqForm model,String czlx) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_RCSW_ZXKQ_SQB where xn=? and xq=? and bjdm=? and ccrq=?");
		if("update".equals(czlx)){
			sql.append(" and sqid<>'"+model.getSqid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getBjdm(),model.getCcrq()}, "num");
		return Integer.parseInt(num)>0;
	}

	public ZwzxKqsqForm getKqsq(ZwzxKqsqForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.xm jlrxm,c.dbfdy ");
		sql.append(" from XG_RCSW_ZXKQ_SQB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm");
		sql.append(" left join fdyxxb t4 on t1.jlr=t4.zgh ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		sql.append(" where t1.sqid=? ");
		return super.getModel(sql.toString(), new String[]{t.getSqid()});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZwzxKqsqForm.class);
		super.setKey("sqid");
		super.setTableName("XG_RCSW_ZXKQ_SQB");
		
	}
	
	/**
	 * 
	 * @描述: 苏州旅游实时计算应到人数
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-10 上午10:40:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public String getYdrsSzly(String bjdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select rs from (");
		sql.append(" select count(1) rs, bjdm from view_xsxxb  where bjdm = ? group by bjdm");
		sql.append(" )");
		String num = dao.getOneRs(sql.toString(), new String[]{bjdm}, "rs");
		if(StringUtils.isNull(num)){
			num = "0";
		}
		return num;
	}
}
