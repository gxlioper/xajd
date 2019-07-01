/**
 * @部门:学工产品事业部
 * @日期：2017-2-8 上午10:08:51 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-2-8 上午10:08:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcsqDAO extends SuperDAOImpl<ZcsqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcsqForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcsqForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from (select t.*,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XB,");
		sql.append(" t1.XM,");
		sql.append(" t1.csrq,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.ZYMC,");
		sql.append(" t2.jc,");
		sql.append(" t3.dzbmc,");
		sql.append(" decode(t.shzt,");
		sql.append(" '0',");
		sql.append(" '未提交',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '已退回',");
		sql.append(" '5',");
		sql.append(" '审核中',");
		sql.append("  t.shzt) shztmc");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcsqb t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join zzmmdmb t2");
		sql.append(" on t1.ZZMM = t2.zzmmdm");
		sql.append(" left join xg_dtjs_zzgxzc_dzbdmb t3");
		sql.append("  on t.szdzb = t3.dzbdm) t");
		sql.append("  where 1 = 1 ");
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
		this.setClass(ZcsqForm.class);
		this.setKey("sqid");
		this.setTableName("xg_dtjs_zzgxzc_zzgxzcsqb");
	}
	
	/**
	 * 
	 * @描述: 党支部集合List
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-8 下午05:15:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDzbList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_DTJS_ZZGXZC_DZBDMB t");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 查看转出申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 下午02:05:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> ckZcsq(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select t.*,t2.jc,t3.dzbmc");
		sql.append("  from xg_dtjs_zzgxzc_zzgxzcsqb t");
		sql.append("  left join view_xsxxb t1");
		sql.append("  on t.xh = t1.XH");
		sql.append("  left join zzmmdmb t2");
		sql.append("  on t1.ZZMM = t2.zzmmdm");
		sql.append("  left join xg_dtjs_zzgxzc_dzbdmb t3");
		sql.append("  on t.szdzb = t3.dzbdm");
		sql.append("  where t.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述: 是否不存在申请和结果表当中
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 下午03:21:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean IsNotExist(ZcsqForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num");
		sql.append(" from ((select xh");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcsqb");
		sql.append(" union");
		sql.append(" select xh");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcjgb))"); 
		sql.append("  where xh = ?"); 
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXh()}, "num");
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @描述: 验证访问该页面的学生是否为党员
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-10 上午09:35:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param username
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean IsDy(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.jc from view_xsxxb t");
		sql.append(" left join zzmmdmb t1");
		sql.append(" on t.ZZMM = t1.zzmmdm");
		sql.append(" where t.xh = ?");
		String jc = dao.getOneRs(sql.toString(), new String[]{username},"jc");
		return "正式党员".equals(jc) || "预备党员".equals(jc) ? true : false;
	}
}
