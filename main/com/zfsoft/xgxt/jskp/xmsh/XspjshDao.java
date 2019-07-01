/**
 * @部门:学工产品(1)部
 * @日期：2018-4-16 下午07:36:06 
 */  
package com.zfsoft.xgxt.jskp.xmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生评价管理模块
 * @类功能描述: 学生评价审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-16 下午07:36:06 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjshDao extends SuperDAOImpl<XspjshForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select a.sqid,a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,a.splcid,");
		sql.append(" g.guid shid,g.gwid,g.shr,g.shyj,g.shzt,i.mc || '[' ||");
		sql.append(" decode(g.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,i.gwz,");
		sql.append(" row_number() over(partition by a.sqid order by g.shsj desc) rn ");
		sql.append("from xg_xspj_new_zzsqb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append("left join xg_xtwh_shztb g on a.sqid = g.ywid ");
		sql.append("left join xg_xtwh_spgwyh h on g.gwid = h.spgw ");
		sql.append("left join xg_xtwh_spgw i on g.gwid = i.id ");
		sql.append("where h.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (g.shzt <> '0' and g.shzt <> '4')");
		} else {
			sql.append(" and (g.shzt = '0' or g.shzt = '4' )");
		}
		sql.append(" ) t where 1 = 1 and rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(XspjshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_xspj_new_zzsqb");
	}
	
	/**
	 * @描述: 学生审核数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-17 下午03:57:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXspjshInfo(XspjshForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select a.sqid,");
		sql.append(" a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.shzt,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',a.shzt) shztmc,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,a.splcid,");
		sql.append("case when length(a.sjlrr) = '10' then '学生自主申请' when length(a.sjlrr) <> '10' then '此数据为' || i.bmmc || ',' || i.xm || '(' || '职工号：' || i.yhm || ')' || '录入' else a.sjlrr end sjlyfs ");
		sql.append("from xg_xspj_new_zzsqb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append("left join (select g.yhm,g.xm,g.szbm,h.bmmc from yhb g left join zxbz_xxbmdm h on g.szbm = h.bmdm) i on a.sjlrr = i.yhm ");
		sql.append(") where sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
	
	/**
	 * @描述: 取审核状态表中的最新一条记录的分数
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-17 下午04:34:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getLevelXxBySqid(XspjshForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from xg_xtwh_shztb where shsj is not null order by shsj desc) ");
		sql.append("where ywid = ? and rownum = 1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
	
	/**
	 * @描述: 根据ID查询当下有多少审批岗位
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-18 下午04:51:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getShxhForId(String id){
		StringBuffer sql = new StringBuffer();
		String[] ids = id.split(",");
		List<String> params = new ArrayList<String>();
		
		sql.append("select xh,count(1)count from ( ");
		sql.append("select decode(t3.xh,'1','first','','emp','second')xh from xg_xspj_new_zzsqb t1 ");
		sql.append("left join xg_xtwh_shztb t2 on t1.sqid = t2.ywid and shr is null ");
		sql.append("left join xg_xtwh_spbz t3 on t2.gwid = t3.spgw and t2.lcid = t3.splc where t1.sqid in ( ");
		for (int i = 0; i < ids.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append(" ? ");
			params.add(ids[i]);
		}
		sql.append(")) group by xh");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * @描述: 根据id获取前一次分数
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-4-19 上午09:54:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBeforeMark(String[] ids){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select * from ( ");
		sql.append("select sqid,t3.xh,t2.shr,t2.shsj,lag(t2.zd3,1) over(partition by sqid order by sqid,shsj) sjfs ");
		sql.append("from xg_xspj_new_zzsqb t1 ");
		sql.append("left join xg_xtwh_shztb t2 on t1.sqid = t2.ywid ");
		sql.append("left join xg_xtwh_spbz t3 on t2.gwid = t3.spgw and t2.lcid = t3.splc ");
		sql.append("where sqid in (");
		for (int i = 0; i < ids.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append(" ? ");
			params.add(ids[i]);
		}
		sql.append(") ) where shr is null and xh >1 ");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/**
	 * @描述: 根据高级查询条件查询出的数据(无勾选)，审核并做保存操作
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-23 下午03:04:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> xspjPlshxx(XspjshForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select a.sqid,a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,a.splcid,");
		sql.append(" g.guid shid,g.gwid,g.shr,g.shyj,g.shzt,i.mc || '[' ||");
		sql.append(" decode(g.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,i.gwz,");
		sql.append(" row_number() over(partition by a.sqid order by g.shsj desc) rn ");
		sql.append("from xg_xspj_new_zzsqb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append("left join xg_xtwh_shztb g on a.sqid = g.ywid ");
		sql.append("left join xg_xtwh_spgwyh h on g.gwid = h.spgw ");
		sql.append("left join xg_xtwh_spgw i on g.gwid = i.id ");
		sql.append("where h.spyh ='" + user.getUserName() + "' ");
		sql.append(" and (g.shzt = '0' or g.shzt = '4' )");
		sql.append(" ) t where 1 = 1 and rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
}
