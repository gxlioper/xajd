/**
 * @部门:学工产品事业部
 * @日期：2016-7-1 上午09:57:25 
 */  
package com.zfsoft.xgxt.xlzx.zxzxthjl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2016-7-1 上午09:57:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxzxthjlDao extends SuperDAOImpl<ZxzxthjlForm>{
	/*
	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xljkzx_bjzyy_zxzxthjlb");
		super.setKey("id");
		super.setClass(ZxzxthjlForm.class);
	}
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/
	@Override
	public List<HashMap<String, String>> getPageList(ZxzxthjlForm t)
			throws Exception {
		return null;
	}
	/**
	 * 查询数据
	 */
	public List<HashMap<String, String>> getPageList(ZxzxthjlForm t, User user)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.id,a.xh,c.xm,c.xb,c.nj,c.xymc,c.zymc,c.bjmc,c.sjhm,a.ytr,d.xm zgxm,d.xb zgxb,(select bmmc from ZXBZ_XXBMDM where bmdm = d.bmdm) zgbmmc,a.thsj,a.jbqkms, ");
		sql.append(" decode(a.cbpgdm, '1', '没有问题，无需咨询', '2', '一般心理问题', '3', '心理障碍和精神疾病',a.cbpgdm)cbpgdm,a.ybwtlb,a.ybwtsfzx, ");
		sql.append(" a.zajb,a.cbpgjg, ");
		sql.append(" decode(a.zajbsmzx, '1', '是', '0', '否','2', '', a.zajbsmzx) zajbsmzx,decode(a.sfzj, '1', '是', '0', '否','2', '', a.sfzj) sfzj,a.bz,c.zydm,c.bjdm,c.xydm ");
		sql.append(" from XG_XLJKZX_BJZYY_ZXZXTHJLB a ");
		sql.append(" left join view_xsbfxx c on a.xh = c.xh ");
		sql.append(" left join view_fdybbqk d on a.ytr = d.zgh ");
		sql.append(" )a where 1 = 1 and ytr = '"+user.getUserName()+"'");
		//sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 增加判断数据是否重复
	 */
	public String addCheck(ZxzxthjlForm form){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String id =  form.getId();
		sql.append(" select count(1) num from xg_xljkzx_bjzyy_zxzxthjlb ");
		sql.append(" where xh = ? and ytr = ?  and thsj = ? ");
		params.add(form.getXh());
		params.add(form.getYtr());
		params.add(form.getThsj());
		if(!StringUtils.isEmpty(id)){
			sql.append(" and id <> ? ");
			params.add(id);
		}	
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
	/**
	 * @描述: 删除单个或多个在线咨询谈话记录信息
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-4 上午10:41:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delZxzxthjlId(String[] id) throws Exception {
		if (id == null || id.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xljkzx_bjzyy_zxzxthjlb");
		sql.append(" where  ");
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("id=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.runDelete(sql.toString(), id);
	}
	/**
	 * @描述: 获取老师的信息
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-5 下午07:41:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getInfoByZgh(String zgh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*, ");
		sql.append("        (select bmmc from ZXBZ_XXBMDM where bmdm=t.bmdm) bmmc  ");
		sql.append(" from VIEW_FDYBBQK t ");
		sql.append(" where zgh = ? ");		
		return dao.getMapNotOut(sql.toString(), new String[]{ zgh });
	}
	
	/**
	 * @描述: 单个查询
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-6 下午02:14:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getThjlxx(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.id,a.xh,c.xm,c.xb,c.nj,c.xymc,c.zymc,c.bjmc,c.sjhm,a.ytr,d.xm zgxm,d.xb zgxb,(select bmmc from ZXBZ_XXBMDM where bmdm = d.bmdm) zgbmmc,a.thsj,a.jbqkms, ");
		sql.append(" decode(a.cbpgdm, '1', '没有问题，无需咨询', '2', '一般心理问题', '3', '心理障碍和精神疾病')cbpgdmmc,a.cbpgdm,a.ybwtlb,a.ybwtsfzx, ");
		sql.append(" a.zajb,a.cbpgjg, ");
		sql.append(" decode(a.zajbsmzx, '1', '是', '0', '否','2', '', a.zajbsmzx) zajbsmzx,decode(a.sfzj, '1', '是', '0', '否','2', '', a.sfzj) sfzj,a.bz ");
		sql.append(" from XG_XLJKZX_BJZYY_ZXZXTHJLB a ");
		sql.append(" left join view_xsbfxx c on a.xh = c.xh ");
		sql.append(" left join view_fdybbqk d on a.ytr = d.zgh ");
		sql.append(" where a.id = ? ");		
		return dao.getMapNotOut(sql.toString(), new String[]{ id });
	}
	/**
	 * @描述: 获取一般心理问题类别代码和名称
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-5 上午09:48:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYblxwtlbList() {
		String sql = " select dm,mc from xg_xljkzx_bjzyy_xlwtdmb order by dm ";
		return dao.getList(sql, new String[] {}, new String[] { "dm","mc" });
	}
	/**
	 * @描述: 谈话记录（用于报表）
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-13 下午05:51:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getThjl(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,b.xm,b.xymc,b.zymc,b.sjhm,a.thsj,a.jbqkms,a.ybwtlb,a.cbpgjg,a.bz,a.zajb, ");
		sql.append(" decode(a.cbpgdm, '1', '没有问题，无需咨询', '2', '一般心理问题', '3', '心理障碍和精神疾病',a.cbpgdm)cbpgdm, ");
		sql.append(" decode(a.zajbsmzx, '1', '是', '0', '否','2', '', a.zajbsmzx) zajbsmzx, ");
		sql.append(" decode(a.sfzj, '1', '是', '0', '否','2', '', a.sfzj) sfzj ");
		sql.append(" from xg_xljkzx_bjzyy_zxzxthjlb a  left join view_xsbfxx b on a.xh = b.xh ");
		sql.append(" where a.id = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	/**
	 * @描述: 取一般问题类别
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-14 下午05:21:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYbwtlb(String id) {
		String sql = null;
		sql = "select ybwtlb from xg_xljkzx_bjzyy_zxzxthjlb where id=? ";
		return dao.getOneRs(sql, new String[]{id}, "ybwtlb");
	}
	/**
	 * @描述: 取心理障碍和精神疾病
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-14 下午05:23:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZajb(String id) {
		String sql = null;
		sql = "select zajb from xg_xljkzx_bjzyy_zxzxthjlb where id=? ";
		return dao.getOneRs(sql, new String[]{id}, "zajb");
	}
}
