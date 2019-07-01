/**
 * @部门:学工产品(1)部
 * @日期：2018-4-18 上午11:04:34 
 */  
package com.zfsoft.xgxt.jskp.pjjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生评价管理模块
 * @类功能描述: 学生评价结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-18 上午11:03:53 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjjgDao extends SuperDAOImpl<XspjjgForm>{
	private static final String XN_Yf = "09";	//切换学年的月份

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.guid,");
		sql.append(" a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,");
		sql.append(" decode(a.sjly,'1','申请审核','2','结果增加','3','导入',a.sjly) sjly ");
		sql.append("from xg_xspj_new_pjjgb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		if("nlsy".equals(t.getType())){
			sql.append(" where e.xmlbmc like '%能力素养-%' ");
		}else{
			sql.append(" where e.xmlbmc not like '%能力素养-%' ");
		}
		sql.append(")t where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setClass(XspjjgForm.class);
		this.setKey("guid");
		this.setTableName("xg_xspj_new_pjjgb");
	}
	
	/**
	 * @描述: 通过申请id删除结果
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-18 下午05:29:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delShjgById(String sqid)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_xspj_new_pjjgb where guid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{sqid});
	}
	
	/**
	 * @描述: 项目类别List
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 上午09:11:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmlbList()throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select xmlbdm xmlb,xmlbmc from xg_jskp_xmlbb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 指导部门List
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 上午09:11:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZdbmList()throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select bmdm zdbm,bmmc zdbmmc from zxbz_xxbmdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 短学期List
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 上午11:49:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDxqList()throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select dxqdm,dxqmc from xg_jskp_dxq");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 验证学号是否存在学生信息表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:13:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkXhisTrue(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select xm from view_xg_xspj_xsxx where xh = ?");
		String xm = dao.getOneRs(sql.toString(), new String[]{xh}, "xm");
		if(StringUtils.isNull(xm)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @描述: 验证指导部门
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:23:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yrdwmc
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkZdbm(String zdmbmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select bmdm from ZXBZ_XXBMDM where bmmc = ? ");
		return dao.getOneRs(sql.toString(), new String[]{zdmbmc}, "bmdm");
	}
	
	/**
	 * @描述: 获得学生的学院名称
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-24 下午05:46:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXsbmmc(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xymc from view_xg_xspj_xsxx where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xymc");
	}
	
	/**
	 * @描述: 验证项目类别是否存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:28:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdmbmc
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkXmlb(String xmlbmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmlbdm from xg_jskp_xmlbb where xmlbmc = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmlbmc}, "xmlbdm");
	}
	
	/**
	 * @描述: 验证项目类别为能力素养的数据不能导入
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-19 下午02:18:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmlbmc
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkNlsy(String xmlbmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmlbdm from xg_jskp_xmlbb where xmlbmc = ? and xmlbmc not like '能力素养-%' ");
		return dao.getOneRs(sql.toString(), new String[]{xmlbmc}, "xmlbdm");
	}
	
	/**
	 * @描述: 验证短学期是否存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:51:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmlbmc
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkDxq(String dxqmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select dxqdm from xg_jskp_dxq where dxqmc = ?");
		return dao.getOneRs(sql.toString(), new String[]{dxqmc}, "dxqdm");
	}
	
	/**
	 * @描述: 唯一主键验证
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:59:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc
	 * @param cysj
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExists(String xmmc,String cysj,String xh,String id){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(1) num from xg_xspj_new_pjjgb where xmmc = ? and cysj = ? ");
		sql.append(" and xh = ?");
		paraList.add(xmmc);
		paraList.add(cysj);
		paraList.add(xh);
		if(StringUtils.isNotNull(id)){
			sql.append(" and juid != ?");
			paraList.add(id);
		}
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return (num).equals("0") ? true : false;
	}
	
	/**
	 * @描述: 判断学号是否存在xsxxb
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-5-30 下午05:28:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkXh(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*)cnt from view_xg_xspj_xsxx where xh = ?");
		String cnt = dao.getOneRs(sql.toString(), new String[]{xh}, "cnt");
		return "0".equals(cnt) ? false : true;
	}
	
	/**
	 * @描述: 插入结果表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-4-20 下午04:22:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xspj_new_pjjgb( ");
		sql.append("xh,xn,fz,dxqdm,xmmc,zdbmdm,xmlbdm,cysj,fzrxm,fzrlxfs,");
		sql.append("zdlsxm,zdlslxfs,sqly,sjlrr,sjly,sjlrsj");
		sql.append(" )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}
	
	/**
	 * @描述: 判断唯一键，学号(xh),项目名称(xmmc),参与时间(cysj)
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-24 上午09:58:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(XspjjgForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_xspj_new_pjjgb where xh = ? and xmmc = ? and cysj = ? ");
		paraList.add(model.getXh());
		paraList.add(model.getXmmc());
		paraList.add(model.getCysj());
		if(StringUtils.isNotNull(model.getGuid())){
			sql.append(" and guid <> ? ");
			paraList.add(model.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*存在数据时返回false，不存在时返回true*/
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * @描述: 查看
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-4-25 上午09:51:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String guid)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.guid,a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,");
		sql.append(" decode(a.sjly,'1','申请审核','2','结果增加','3','导入',a.sjly) sjly ");
		sql.append("from xg_xspj_new_pjjgb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append(") where guid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
	
	/**
	 * @描述: 获取单个人的学年汇总
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-4-25 上午10:23:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSingleSummary(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select xn,xmlbmc,sum(fz) fz from ( ");
		sql.append("select b.xmlbmc,fz,case when to_char(to_date(cysj,'yyyy-mm-dd'),'mm') >= "+XN_Yf);
		sql.append(" then to_char(to_date(cysj,'yyyy-mm-dd'),'yyyy')||'-'||to_char(to_number(to_char(to_date(cysj,'yyyy-mm-dd'),'yyyy'))+1) ");
		sql.append("else to_char(to_number(to_char(to_date(cysj,'yyyy-mm-dd'),'yyyy'))-1)||'-'||to_char(to_date(cysj,'yyyy-mm-dd'),'yyyy') end xn ");
		sql.append(" from xg_xspj_new_pjjgb a left join xg_jskp_xmlbb b on a.xmlbdm = b.xmlbdm ");
		sql.append("where a.xh = ? ) group by xn,xmlbmc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @描述: 评价结果数据统计
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-25 下午03:24:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param SearchXn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDateForSearchXn (XspjjgForm t, User user, String SearchXn) throws Exception{

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select b.xh,b.xm,b.nj,b.xymc,b.zymc,b.bjmc,b.zf,b.xn,");
		sql.append("       b.hdjszf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.hdjszf desc) hdjsxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.hdjszf desc) hdjszypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.hdjszf desc) hdjsbjpm,");
		sql.append("       b.cxcyzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.cxcyzf desc) cxcyxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.cxcyzf desc) cxcyzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.cxcyzf desc) cxcybjpm,");
		sql.append("       b.dwjlzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.dwjlzf desc) dwjlxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.dwjlzf desc) dwjlzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.dwjlzf desc) dwjlbjpm,");
		sql.append("       b.gyfwzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.gyfwzf desc) gyfwxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.gyfwzf desc) gyfwzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.gyfwzf desc) gyfwbjpm,");
		sql.append("       b.shgzzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.shgzzf desc) shgzxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.shgzzf desc) shgzzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.shgzzf desc) shgzbjpm,");
		sql.append("       b.wthdzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.wthdzf desc) wthdxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.wthdzf desc) wthdzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.wthdzf desc) wthdbjpm ");
		sql.append(" from(");
		sql.append("  select * from( ");
		sql.append("    select xh,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,");
		sql.append("           sum(fz)zf,");
		sql.append("           sum(case a.xmlbmc when '思政素质-活动记实' then to_number(a.fz) else 0 end) hdjszf,");
		sql.append("           sum(case a.xmlbmc when '能力素养-创新创业' then to_number(a.fz) else 0 end) cxcyzf,");
		sql.append("           sum(case a.xmlbmc when '能力素养-对外交流' then to_number(a.fz) else 0 end) dwjlzf,");
		sql.append("           sum(case a.xmlbmc when '能力素养-公益服务' then to_number(a.fz) else 0 end) gyfwzf,");
		sql.append("           sum(case a.xmlbmc when '能力素养-社会工作' then to_number(a.fz) else 0 end) shgzzf,");
		sql.append("           sum(case a.xmlbmc when '能力素养-文体活动' then to_number(a.fz) else 0 end) wthdzf ");
		sql.append("     from view_xg_xspj_pjjg a");
		sql.append("     where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append("     group by xh,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn");
		sql.append("  ) where 1 = 1");
		sql.append(")b order by xymc,zf desc,zymc,bjmc ");
		/*return dao.getListNotOut(sql.toString(),StringUtils.joinStrArr(new String[]{SearchXn},inputV)); 先放在这以便用到双数组*/
		return dao.getListNotOut(sql.toString(),inputV);
	}
}
