/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午02:11:01 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-6-25 下午02:11:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZjsySxhbDAO extends SuperDAOImpl<ZjsySxhbForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZjsySxhbForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZjsySxhbForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "m",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( select a.*,b.jdmc,c.xydm,c.zydm,c.nj,c.bjdm, ");
		sql.append("c.bjmc,c.xymc,c.xm,(select sum(sjfs)jdzs from xg_dtjs_zjsy_sxhbjg where  ");
		sql.append(" a.jddm=jddm and a.xh=xh group by xh,jddm ) jdzs from ");
		sql.append(" xg_dtjs_zjsy_sxhbjg a left join xg_dtjs_jbszb  b on a.jddm=b.jddm  ");
		sql.append("left join view_xsbfxx c on a.xh=c.xh order by c.nj,c.xydm,c.bjdm ) m where 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);	
	}

	
	@Override
	protected void setTableInfo() {
		super.setKey("sxhbid");
		super.setTableName("XG_DTJS_ZJSY_SXHBJG");
		super.setClass(ZjsySxhbForm.class);
		
	}
   
	public HashMap<String,String> getXsdtxxMore(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.jdmc,b.jddm,b.kssj from view_xsbfxx a ");
		sql.append("left join view_new_dc_dtxx_jg b on a.xh=b.xh) where xh=?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
	public boolean isHasExists(ZjsySxhbForm model){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) zs from xg_dtjs_zjsy_sxhbjg b where xh=? and jddm=? and sjsj=?");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXh(),
			model.getJddm(),model.getSjsj()}, "zs");
		return Integer.parseInt(num)>0?true:false;
	}
	
}
