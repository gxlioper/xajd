/**
 * @部门:学工产品1部
 * @日期：2017-4-7 上午11:02:42 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优_项目设置_项目维护
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-7 上午10:57:56 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmwhDao extends SuperDAOImpl<XmwhForm>{
	@Override
	protected void setTableInfo() {
		super.setClass(XmwhForm.class);
		super.setTableName("xg_zjdx_pjpy_pjxmb");
		super.setKey("xmdm");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmwhForm model, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
//		sql.append("select * from ( ");
//		sql.append("select t1.*,t2.lxmc,t3.xzmc, ");
//		sql.append("(select case when count(*) > 0 then '1' else '0' end from xg_pjpy_new_xmtjb t4 where t1.xmdm = t4.xmdm) tjsz ");
//		sql.append("from xg_zjdx_pjpy_pjxmb t1 ");
//		sql.append("left join xg_zjdx_pjpy_xmlx t2 on t1.lxdm = t2.lxdm ");
//		sql.append("left join xg_zjdx_pjpy_xmxz t3 on t1.xzdm = t3.xzdm ");
//		sql.append("order by to_number(t1.xssx) )t where 1=1 ");
		sql.append("select * from ( ");
		sql.append("select a.xmdm,a.xn,a.xzdm,a.lxdm,a.xmmc,a.ywmc,a.xmje,a.shlc,a.xmsm,a.sqkg,a.sqkssj,a.sqjssj,a.shkg,a.shkssj,a.shjssj,a.kgbz,a.djb, ");
		sql.append("a.sbb,a.rskzjb,a.rsfpfs,a.rsfpz,a.rsfpnj,a.zcfpm,to_number(a.xssx) xssx,b.lxmc,c.xzmc, ");
		sql.append("(select case when count(*) > 0 then '1' else '0' end from xg_zjdx_pjpy_jdszb e where a.xmdm = e.xmdm) jdsz, ");
		sql.append("(select case when count(*) > 0 then '1' else '0' end from xg_pjpy_new_xmtjb f where a.xmdm = f.xmdm) tjsz, ");
		sql.append("(select case when count(*) > 0 then sum(zzme) else 0 end from xg_pjpy_new_rsszb g where a.xmdm = g.xmdm) rssz ");
		sql.append("from xg_zjdx_pjpy_pjxmb a, xg_zjdx_pjpy_xmlx b, xg_zjdx_pjpy_xmxz c ");
		sql.append("where a.lxdm = b.lxdm and a.xzdm = c.xzdm ");
		sql.append(")t where 1=1 ");
		if (!StringUtil.isNull(model.getXmmc())) {
			params.add(model.getXmmc());
			sql.append(" and xmmc like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getLxdm())) {
			params.add(model.getLxdm());
			sql.append(" and lxdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getXzdm())) {
			params.add(model.getXzdm());
			sql.append(" and xzdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getSqkg())) {
			String sqkg = model.getSqkg();
			if(sqkg.equals("1")){
				params.add(model.getSqkg());
				sql.append(" and sqkg like '%'||?||'%' and");
				sql.append(" (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");	
			}else{
				params.add(model.getSqkg());
				sql.append(" and (sqkg like '%'||?||'%' or");
				sql.append(" (sysdate not between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')))");	
			}
		}
		sql.append(" and xn = (select xn from xg_zjdx_pjpy_csszb) ");
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}
	
	/**
	 * @描述: 获取参数设置信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 上午09:39:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCsszMap(){
		StringBuffer sql =  new StringBuffer();
		sql.append(" select * from xg_zjdx_pjpy_csszb where rownum = 1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 项目类型List
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 上午10:24:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdm dm,lxmc mc from xg_zjdx_pjpy_xmlx order by lxmc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @描述: 项目性质List
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 上午10:25:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select xzdm dm,xzmc mc from xg_zjdx_pjpy_xmxz order by xzmc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @描述: 验证同一个学年是否有显示顺序重复
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 下午02:27:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistXssx(XmwhForm model) throws Exception {
	    StringBuilder sql = new StringBuilder();
	    List<String> params = new ArrayList<String>();
	    sql.append(" select count(1) num from xg_zjdx_pjpy_pjxmb ");
	    sql.append(" where xn = ? and xssx = ? ");
	    /*参数设置学年*/
	    params.add(getCsszMap().get("xn"));
	    /*获得显示顺序*/
	    params.add(model.getXssx());
	    String xmdm = model.getXmdm();
	    if(!StringUtils.isEmpty(xmdm)){
			sql.append(" and xmdm <> ? ");
			params.add(xmdm);
		}
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
	
	/**
	 * @描述: 验证同一个学年是否有显示顺序重复
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 下午02:39:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistXmmc(XmwhForm model) throws Exception {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) count from xg_zjdx_pjpy_pjxmb t ");
		sql.append(" where t.xmmc = ? ");
		if (model.getXmdm() != null && !model.getXmdm().trim().equals("")) {
			sql.append(" and t.xmdm != '" + model.getXmdm() + "'");
		}
		String csszXn = getCsszMap().get("xn");
		sql.append(" and t.xn = '");
		sql.append(csszXn);
		sql.append("'");
		String xmmc = model.getXmmc();
		if (xmmc != null) {
			xmmc = xmmc.trim();
		}
		String[] input = { xmmc };
		String[] output = { "count" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (!oneRs[0].equals("0")) {
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * @描述: 是否在项目申请时被使用
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-11 下午04:00:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFlowData(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*) count from xg_zjdx_pjpy_xmsq where xmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "count");
	}
	
	/**
	 * @描述: 删除评奖项目的同时时删除与其相关联的表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-11 下午04:33:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param keys
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteRelationalTable(String keys) throws Exception {
		int[] result = null;
		List<String> sqlList = new ArrayList<String>();
		if (keys != null) {
			keys = StringUtils.replace(keys, ",", "','");
			keys = "'" + keys + "'";
		} else {
			return true;
		}
		/*删除【人数设置表】相关联数据*/
		String sql = " delete from xg_pjpy_new_rsszb ";
		sql += " where xmdm in(" + keys + ") ";
		sqlList.add(sql);
		/*删除【条件设置表】相关联数据（使用老评奖的表结构）*/
		sql = " delete from xg_pjpy_new_xmtjb ";
		sql += "where xmdm in(" + keys + ") ";
		sqlList.add(sql);
		/*删除【兼得设置表】相关联数据*/
		sql = " delete from xg_zjdx_pjpy_jdszb ";
		sql += "where xmdm in(" + keys + ") ";
		sqlList.add(sql);
		/*删除【兼得设置表】相关联数据*/
		sql = "delete from  xg_zjdx_pjpy_jdszb ";
		sql += "where bjdxmdm in(" + keys + ") ";
		sqlList.add(sql);
		/*删除【审核调整奖项目设置表】相关联数据（这张表是不是有问题，待确认！）*/
//		sql = "delete from  xg_pjpy_new_jxtzsz ";
//		sql += "where xmdm in(" + keys + ") ";
//		sqlList.add(sql);
		/*删除【评奖项目表】相关联数据*/
		sql = "delete from  xg_zjdx_pjpy_pjxmb ";
		sql += "where xmdm in(" + keys + ") ";
		sqlList.add(sql);
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	/**
	 * @描述: 根据项目代码获得项目名称
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-12 上午10:01:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select xmmc xmmc ");
		sb.append("from xg_zjdx_pjpy_pjxmb ");
		sb.append("where xmdm = ? ");
		String[] inputValue = { xmdm };
		String[] outputValue = { "xmmc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if (oneRs != null && oneRs.length > 0) {
			xmmc = oneRs[0];
		}
		return xmmc;
	}
	
	/**
	 * @描述: 取所有评奖项目表中的学年
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-12 下午03:53:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfzXn() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xn ");
		sql.append("from xg_zjdx_pjpy_pjxmb ");
		sql.append("order by xn desc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @描述: 复制目标学年的评奖项目数据
	 * 	      PS：如果本学年与复制学年有相同的项目名称，则不复制相同名称的数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-12 下午04:45:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fzXn
	 * @param currXn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfz(String fzXn,String currXn) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zjdx_pjpy_pjxmb ");
		sql.append("where xn='");
		sql.append(fzXn);
		sql.append("' ");
		sql.append("and xmmc not in(select xmmc from xg_zjdx_pjpy_pjxmb where xn='");
		sql.append(currXn);
		sql.append("')");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @描述: 奖项复制插入数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-12 下午05:37:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @param currXn
	 * @param currXq
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJxfzData(List<HashMap<String, String>> list, String currXn)
		throws Exception {
		int[] result = null;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into xg_zjdx_pjpy_pjxmb ");
		sql.append("(xmdm,xzdm,lxdm,xmmc,ywmc,xmje,xssx,shlc,xmsm,djb,sbb,xn) ");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,?,'");
		sql.append(currXn);
		sql.append("')");
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (HashMap<String, String> map : list) {
			param = new String[11];
			param[0] = map.get("xmdm");
			param[1] = map.get("xzdm");
			param[2] = map.get("lxdm");
			param[3] = map.get("xmmc");
			param[4] = map.get("ywmc");
			param[5] = map.get("xmje");
			param[6] = map.get("xssx");
			param[7] = map.get("shlc");
			param[8] = map.get("xmsm");
			param[9] = map.get("djb");
			param[10] = map.get("sbb");
			params.add(param);
		}
		result = dao.runBatch(sql.toString(), params);
		return true;
	}
	
	public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("查询参数为空！");
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*, c.xmdm fzbjdxmdm from xg_zjdx_pjpy_jdszb a ");
		sql.append("left join xg_zjdx_pjpy_pjxmb b on a.bjdxmdm = b.xmdm ");
		sql.append("left join (select * from xg_zjdx_pjpy_pjxmb where xn = ?) c on b.xmmc = c.xmmc ");
		sql.append("where a.xmdm = ? ");
		String[] input = {currXn,xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), input);
		return result;
	}
	
	public boolean runJdsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;

		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_zjdx_pjpy_jdszb where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		sql = "delete from xg_zjdx_pjpy_jdszb where bjdxmdm='"+xmdm+"'";
		sqlList.add(sql);		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_zjdx_pjpy_jdszb(xmdm,bjdxmdm) values('"+xmdm+"','"+arr[i]+"')";
					sqlList.add(sql);
					sql = "insert into xg_zjdx_pjpy_jdszb(xmdm,bjdxmdm) values('"+arr[i]+"','"+xmdm+"')";
					sqlList.add(sql);
				}
			}
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	/**
	 * @描述: 根据项目代码查询记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-5-16 下午03:55:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from xg_zjdx_pjpy_pjxmb where xmdm = ? ");
		return dao.getMapNotOut(sb.toString(), new String[]{xmdm});
	}
	
	/**
	 * @描述: 通过项目代码获取已经设置的年级,年级以逗号分割
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-6-1 上午10:50:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getRsfpnj(String xmdm) throws Exception {
		String rsfpnj = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select rsfpnj from xg_zjdx_pjpy_pjxmb where xmdm = ? ");
		String[] input = { xmdm };
		String[] output = { "rsfpnj" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				rsfpnj = oneRs[0];
			}
		}
		return rsfpnj;
	}
	
	/**
	 * @描述: 根据项目代码设置总名额
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 上午09:20:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param zme
	 * @param rsfpnj
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean setZme(String xmdm, String zme, String rsfpnj)
		throws Exception {
		String sql = "update xg_zjdx_pjpy_pjxmb set rsfpz = ?,rsfpnj = ? where xmdm = ? ";
		String[] input = { zme, rsfpnj, xmdm };
		return dao.runUpdate(sql, input);
	}
	
	/**
	 * @描述: 根据学年、项目名称查询项目对应数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-14 上午11:34:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc
	 * @param xn
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn) 
		throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zjdx_pjpy_pjxmb where xn = ? and xmmc = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xn,xmmc});
	}
}
