/**
 * @部门:学工产品1部
 * @日期：2017-3-21 上午09:19:30 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 新评奖评优_基本设置_参数设置
 * @作者：  Meng.Wei[工号:1186]
 * @时间： 2017-3-21 上午09:19:30 
 * @版本：V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<CsszForm>{
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(CsszForm.class);
		setTableName("xg_zjdx_pjpy_csszb");
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		return null;
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user) throws Exception {
		/*不分页*/
		t.getPages().setPageSize(Integer.MAX_VALUE);
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.xmdm,a.xn,RPAD(' ', 2 * (LEVEL - 1)) || a.xmmc xmmc,a.fjdm,px, ");
		sql.append(" case when xmlx = '0' then result when xmlx = '1' then zxfz || '-' || zdfz end result, ");
		sql.append(" decode(a.xmlx,'0','等级','1','分值',a.xmlx)xmlx ");
		sql.append(" from xg_zjdx_pjpy_zcxmb a ");
		sql.append(" left join (select xmdm, max(result) result ");
		sql.append(" from (select xmdm,mc, wm_concat(mc) over(partition by xmdm order by px) result ");
		sql.append(" from xg_zjdx_pjpy_zcxmxxb t) s ");
		sql.append(" group by xmdm) b ");
		sql.append(" on a.xmdm = b.xmdm ");
		sql.append(" START WITH a.fjdm = 'top' ");
		sql.append(" CONNECT BY PRIOR a.xmdm = a.fjdm ");
		sql.append(" ORDER SIBLINGS BY to_number(px) ");
		sql.append(" )t where xn = '"+t.getXn()+"' ");
		return getPageList(t, sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 更新评奖周期（评奖学年）
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-24 上午09:13:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updatePjzq(String[] xn) throws Exception{
		String sql = " update xg_zjdx_pjpy_csszb set xn = ? where rownum = 1";
		return dao.runUpdate(sql, xn);
	}
	
	/**
	 * @描述: 更新评奖起、止时间和评奖开关，具体看JS方法
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-24 上午09:14:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateCssz(String key,String value) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_zjdx_pjpy_csszb set ");
		sql.append(key);
		sql.append(" = ? where rownum = 1 ");
		return dao.runUpdate(sql.toString(), new String[]{value});
	}
	
	/**
	 * @描述: 查询参数设置信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-21 下午04:34:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * CsszForm 返回类型 
	 * @throws
	 */
	public CsszForm getModel() throws Exception{
		String sql = " select * from xg_zjdx_pjpy_csszb where rownum = 1 ";
		return getModel(sql, new String[]{});
	}
	
	/*
	 * 删除操作__根据项目代码删除综测项目表数据
	 */
	public int numDj(String[] values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zjdx_pjpy_zcxmxxb where ( ");
		for (int i = 0, n = values.length; i < n; i++) {
			sql.append(" xmdm = ? ");

			if (i != n - 1) {
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	/*
	 * 删除操作__根据项目代码删除综测项目选项表数据
	 */
	public int numFz(String[] values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zjdx_pjpy_zcxmb where ( ");
		for (int i = 0, n = values.length; i < n; i++) {
			sql.append(" xmdm = ? ");

			if (i != n - 1) {
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}

	/*
	 * 增加检验，同一个项目名称、学年不能重复
	 */
	public String isExistByZcxm(CsszForm model) {
		String sql = "select count(1) cont from xg_zjdx_pjpy_zcxmb where xn = ? and xmmc = ? ";
		return dao.getOneRs(sql, new String[]{model.getXn(),model.getXmmc()}, "cont");
	}
	
	/*
	 * 修改返回综测项目(点击等级)checkBox数据
	 */
	public List<HashMap<String, String>> getZcxmDjList(String xmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zjdx_pjpy_zcxmxxb where xmdm = ? order by to_number(px) asc");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}

	/*
	 * 修改返回页面__查找综测项目表数据，返回到model
	 */
	public HashMap<String, String> getZcxmDate(String xmdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_zjdx_pjpy_zcxmb where xmdm = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{xmdm});
	}
	
	/*
	 * 选择周期__判断当前周期是否有综测记录
	 */
	public boolean getSfcz(){
		String sql = "select count(1) num " +
					 "  from xg_zjdx_pjpy_cpmdb a " +
					 " where xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1) " +
					 "   and exists (select 1 " +
					 "          from xg_zjdx_pjpy_zcfsb b " +
					 "         where a.xh = b.xh " +
					 "           and a.xn = b.xn " +
					 "           and fs is not null) ";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{}, "num")) > 0;
	}
	
	/*对评奖人员库执行初始化操作*/
	public boolean initDel() throws Exception {
		String sql = "delete from xg_zjdx_pjpy_cpmdb where xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1) ";
		return dao.runUpdate(sql, new String[]{});
	}
	
	/*对评奖人员库执行初始化操作*/
	public boolean init() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_zjdx_pjpy_cpmdb (xn, xh, bjdm) ");
		sql.append("select (select xn from xg_zjdx_pjpy_csszb where rownum = 1) xn, xh, bjdm from view_xsjbxx where nj >= '2017'");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/*查询综测项目列表*/
	public List<HashMap<String,String>> getZcxmList(String xn){
		String sql = "select xmdm,xn,xmmc,fjdm,xmlx,px,zxfz,zdfz from xg_zjdx_pjpy_zcxmb where xn = ?";
		return dao.getListNotOut(sql, new String[]{xn});
	}
	
	/*查询系统中的综测项目数*/
	public int getZcxmCount() throws SQLException{
		String sql = "select count(1) num from xg_zjdx_pjpy_zcxmb";
		return dao.getOneRsint(sql);
	}
	
	/*查询最近周期的综测项目表  数据*/
	public List<HashMap<String,String>> getZjzqZcxm(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zjdx_pjpy_zcxmb where substr(xn,6,9)=(select max(substr(xn,6,9)) from xg_zjdx_pjpy_zcxmb)");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/*构建综测项目表*/
	public boolean initZcxmList(List<String[]> params) throws SQLException{
		String sql = "insert into xg_zjdx_pjpy_zcxmb(xmdm,xn,xmmc,fjdm,xmlx,px,zxfz,zdfz) values (?,?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/*查询综测项目表中的最近周期*/
	public String maxZczq() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct xn maxXn from xg_zjdx_pjpy_zcxmb where substr(xn,6,9)=(select max(substr(xn,6,9)) from xg_zjdx_pjpy_zcxmb)");
		return dao.getOneRs(sb.toString(), new String[] {}, "maxXn");
	}
	
	/*构建综测项目选项表*/
	public boolean initZcxmxxList(String xn,String maxZczq) throws Exception{
		String sql = "insert into xg_zjdx_pjpy_zcxmxxb(xmdm,mc,px)" +
				" select (select xmdm from xg_zjdx_pjpy_zcxmb c where a.xmmc=c.xmmc and xn = ?) xmdm,b.mc,b.px " +
				" from xg_zjdx_pjpy_zcxmb a left join xg_zjdx_pjpy_zcxmxxb b on a.xmdm = b.xmdm where a.xn = ? and b.xmdm is not null";
		String [] input = new String[]{xn,maxZczq};
		return dao.runUpdate(sql, input);
	}
	
	/*判断当前周期内是否有分数提交记录表数据*/
	public boolean isHaveFstjjl(String xn){
		String sql = "select count(*)num from xg_zjdx_pjpy_fstjjlb where xn = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xn}, "num")) > 0;
	}
	
	/*删除分数提交记录表数据（实际也没必要进行删除操作）*/
	public boolean delFsTjjl(String xn,User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zjdx_pjpy_fstjjlb t1 where t1.xn = ? ");
		return dao.runUpdate(sql.toString(), new String[]{xn});
	}
	
	/*
	 * 初始化分数提交记录表数据，现需要把综测项目代码插入进去
	 * 为了综测维护那边分条显示
	 * 插入的项目代码，父级代码为top项
	 */
	public boolean insertFstjjl(String xn,User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_zjdx_pjpy_fstjjlb(xn,xydm,xmdm) ");
		sql.append("select distinct ? xczq, t1.xydm,t2.xmdm from view_njxyzybj_all t1,(select * from view_xg_zjdx_pjpy_cssz t2 where fjdm = 'top' and xn = ?)t2 ");
		return dao.runUpdate(sql.toString(), new String[]{xn,xn});
	}
	
	/**
	 * @描述: 查询配置表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 上午11:22:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCsz(String csdm) {
		String sql = "select csz from xg_pjpy_new_cspzb where csdm = ? ";
		return dao.getOneRs(sql, new String[]{csdm}, "csz");
	}
	
	/**
	 * @描述: 检测综测项目是否被使用
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-24 上午11:11:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkZcxmMade(String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) count from xg_zjdx_pjpy_zcxmb ");
		sql.append("where xmdm in (select distinct xmdm from xg_zjdx_pjpy_zcfsb) and xn = ?");
		return dao.getOneRs(sql.toString(), new String[]{xn},"count");
	}
	
	/**
	 * @描述: 获取参数设置信息表数据，以前调用的方法有点问题
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-6 下午02:38:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getCszzInfo() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zjdx_pjpy_csszb where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
}
