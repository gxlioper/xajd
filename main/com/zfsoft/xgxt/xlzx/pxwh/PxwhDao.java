/**
 * @部门：学工产品事业部
 * @日期：2016年11月17日
 */  
package com.zfsoft.xgxt.xlzx.pxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称：学生工作管理系统
 * @模块名称：心理健康培训维护 管理模块
 * @类功能描述：心理健康培训维护 数据层
 * @作者：卓耐[工号:1391]
 * @时间：2016年11月17日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class PxwhDao extends SuperDAOImpl<PxwhForm> {
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_XLZX_PXWHB");
		super.setKey("pxid");
		super.setClass(PxwhForm.class);
	}
	
	public PxwhForm getModel(PxwhForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,nvl(c.ybmrs,0) ybmrs from XG_XLZX_PXWHB a left join (select count(1) ybmrs, pxid ");
		sql.append("from XG_XLZX_PXBMB group by pxid) c on a.pxid = c.pxid where a.pxid = ? ");
		return super.getModel(sql.toString(), new String[] {model.getPxid()});
	}
	
	
	@Override
	public List<HashMap<String, String>> getPageList(PxwhForm model) throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*, a.kssj || '至' || a.jssj bmkfsj,nvl(c.ybmrs,0) ybmrs,nvl(c.ybmrs,0)||'/'||sxrs rs, ");
		sql.append("case when to_char(sysdate,'yyyy-mm-dd')>=a.kssj and to_char(sysdate,'yyyy-mm-dd')<=a.jssj then '开' else '关' end kgzt from XG_XLZX_PXWHB a ");
		sql.append("left join (select count(1) ybmrs, pxid from XG_XLZX_PXBMB group by pxid) c on a.pxid = c.pxid ) where 1 = 1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(PxwhForm model, User user) throws Exception {
		return null;
	}

	/**
	 * @描述：唯一性校验
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月17日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return 存在相同值的数量
	 * String 返回类型
	 */
	public String checkExist(PxwhForm form){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from XG_XLZX_PXWHB where pxzt = ? and pxsj = ? ");
		if(StringUtils.isNotNull(form.getPxid())){
			sql.append("and pxid<> '");
			sql.append(form.getPxid());
			sql.append("'");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{form.getPxzt(),form.getPxsj()}, "num");
		return num;
	}
	
	/** 
	 * @描述：获取培训报名列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月18日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getPxbmList(PxwhForm model,User user) throws Exception {
		String xh=user.getUserName();
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,a.kssj||'至'||a.jssj bmkfsj,b.xh,b.bmsj,nvl(c.ybmrs,0) ybmrs,nvl(c.ybmrs,0)||'/'||sxrs rs, ");
		sql.append("case when to_char(sysdate,'yyyy-mm-dd')>a.jssj then '1' else '0' end bmyg from XG_XLZX_PXWHB a ");
		sql.append("left join (select * from XG_XLZX_PXBMB where xh= ? ) b on a.pxid=b.pxid ");
		sql.append("left join (select count(1) ybmrs, pxid from XG_XLZX_PXBMB group by pxid) c on a.pxid = c.pxid ");
		if("1".equals(model.getSfybm())){ //已报名
			sql.append("where xh is not null ");
		}else{  //未报名
			sql.append("where xh is null ");
		}
		sql.append(searchTj);
		String[] both = (String[]) ArrayUtils.addAll(new String[]{xh}, inputV);
		return getPageList(model, sql.toString(), both);
	}
	
	/**
	 * 
	 * @描述：报名/取消报名 
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月19日  
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pxid
	 * @param xh
	 * @param bmtype
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean  bmcz(String pxid,String xh,String bmtype) throws Exception{
		boolean flag=false;
		String sql;
		if("1".equals(bmtype)){  //报名
			sql = "insert into XG_XLZX_PXBMB(pxid,xh) values(?,?) ";
		}else{  //取消报名
			sql = "delete from XG_XLZX_PXBMB where pxid=? and xh=? ";
		}
		flag = dao.runUpdate(sql, new String[] {pxid,xh});
		return flag;
	}

	/** 
	 * @描述：
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月19日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getYbmxsList(PxwhForm model) throws Exception{
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.pxid,a.bmsj,b.* from XG_XLZX_PXBMB a left join VIEW_XSJBXX b on a.xh=b.xh where a.pxid = ? ) where 1=1 ");
		sql.append(searchTj);
		String[] both = (String[]) ArrayUtils.addAll(new String[]{model.getPxid()}, inputV);
		return getPageList(model, sql.toString(), both);
	}
	
	/**
	 * @描述：报名学生导出列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年2月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getBmxsdcList(PxwhForm model) throws Exception{
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*, a.kssj || '至' || a.jssj bmkfsj,nvl(c.ybmrs,0) ybmrs,nvl(c.ybmrs,0)||'/'||sxrs rs, ");
		sql.append("case when to_char(sysdate,'yyyy-mm-dd')>=a.kssj and to_char(sysdate,'yyyy-mm-dd')<=a.jssj then '开' else '关' end kgzt, ");
		sql.append("d.xh,e.xm,e.bjmc,e.zymc,e.xymc from XG_XLZX_PXWHB a ");
		sql.append("left join (select count(1) ybmrs, pxid from XG_XLZX_PXBMB group by pxid) c on a.pxid = c.pxid "); 
		sql.append("left join XG_XLZX_PXBMB d on a.pxid=d.pxid ");
		sql.append("left join VIEW_XSJBXX e on d.xh=e.xh ) where 1=1");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
}
