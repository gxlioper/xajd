/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:14:33 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为 管理模块
 * @类功能描述: 日常行为 代码维护
 * @作者： dlq [工号：995]
 * @时间： 2013-7-30 下午04:14:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RcxwdmwhDao extends SuperDAOImpl<RcxwdmwhForm> {

	/**
	 * 
	 * 获取行为大类
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 下午03:54:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXwdlPageList(RcxwdmwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

	/*	sql.append(" select d.rcxwlbdldm,d.rcxwlbdlmc,decode(e.lcxx,'','无需审核',e.lcxx)lcxx from  xg_rcsw_rcxwdbdlb d ");
		sql.append(" left join   ( select splc, mc || '：' || replace(max(lcxx), ',', '->') lcxx from (select splc, ");
		sql.append(" a.mc,to_char(WM_CONCAT(c.mc) over(partition by splc order by xh)) lcxx ");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw' and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) group by splc, mc )  e on  d.splc = e.splc  where 1=1 ");*/

		sql.append(" select d.rcxwlbdldm,d.rcxwlbdlmc,d.sqkssj,d.sqjssj,d.sqkg,");
		sql.append(" decode(e.lcxx, '', '无需审核', mc || '：' || replace(lcxx, ';', '->')) lcxx ");
		sql.append(" from xg_rcsw_rcxwdbdlb d left join (select splc, mc, lcxx ");
		sql.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh)) lcxx, xh,row_number() over(partition by splc order by xh desc ) as ddd ");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw'and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splc = e.splc where 1 = 1 ");
	
		
		if (!StringUtil.isNull(model.getRcxwlbdldm())) {
			params.add(model.getRcxwlbdldm());
			sql.append(" and d.rcxwlbdldm = ? ");
		}

		if (!StringUtil.isNull(model.getRcxwlbdlmc())) {
			params.add(model.getRcxwlbdlmc());
			sql.append(" and d.rcxwlbdlmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));

	}

	/**
	 * 获得行为大类信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXwdlList(RcxwdmwhForm model) throws Exception {
		String searchTj = "";
		List<String> inputV = new ArrayList<String>();
		String sql = "select * from (select a.*,rownum r from xg_rcsw_rcxwdbdlb a order by a.rcxwlbdldm) ";
		String[] output = new String[] { "RCXWLBDLDM", "RCXWLBDLDM",
				"RCXWLBDLMC" };
		return CommonQueryDAO.commonQuery(sql, searchTj,
				inputV.toArray(new String[] {}), output, model);

	}
	

	/**
	 * 
	 * 删除大类信息
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-7 上午10:54:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 *             int 返回类型
	 * @throws
	 */
	public int deleteXwdlInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from xg_rcsw_rcxwdbdlb where rcxwlbdldm in ("
				+ values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}
	
	/**
	 * 根据大类代码查询大类名称
	 */
	public String getRcxwlbdlmcById(String id) throws Exception {
		String sql = "select rcxwlbdlmc from xg_rcsw_rcxwdbdlb where rcxwlbdldm=? ";
		return dao.getOneRs(sql, new String[] { id }, "rcxwlbdlmc");
	}

	/**
	 * 
	 * 检验行为大类代码是否已使用
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-7 上午10:14:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 *             String[] 返回类型
	 * @throws
	 */
	public String[] checkXwdlForXwlb(String values) throws Exception {
		StringBuilder sql = new StringBuilder(
				" select distinct a.rcxwlbdlmc from xg_rcsw_rcxwdbdlb a, xg_rcsw_rcxwlbdmb b where a.rcxwlbdldm = b.rcxwlbdldm and b.SFQY='1' "
						+ " and a.rcxwlbdldm  in(" + values + ")");
		String[] rcxwlbdlmc = dao.getRs(sql.toString(), new String[] {},
				"rcxwlbdlmc");
		return rcxwlbdlmc;
	}
	/** 
	 * 获取审核流未结束的大类
	 */
	public List<HashMap<String,String>> getRcxwdlShwjs(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t3.rcxwlbdldm ");
		sql.append(" from xg_rcsw_rcxwxxwh t1 ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.rcxwlbdm, b.rcxwlbdldm ");
		sql.append(" from xg_rcsw_rcxwlbdmb a ");
		sql.append(" left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" ) ");
		sql.append(" ) t3 on t1.rcxwlbdm = t3.rcxwlbdm ");
		sql.append(" where t1.shzt in ('4','5') "); // '需重审'、'审核中'
		sql.append(" and t3.rcxwlbdldm in (" + values + ") ");
		return dao.getListNotOut(sql.toString(), new String[] {  });
	}
	/** 
	 * 获取审核流未结束的类别
	 */
	public List<HashMap<String,String>> getRcxwlbShwjs(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t1.rcxwlbdm ");
		sql.append(" from xg_rcsw_rcxwxxwh t1 ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.rcxwlbdm, b.rcxwlbdldm ");
		sql.append(" from xg_rcsw_rcxwlbdmb a ");
		sql.append(" left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" ) ");
		sql.append(" ) t3 on t1.rcxwlbdm = t3.rcxwlbdm ");
		sql.append(" where t1.shzt in ('4','5') "); // '需重审'、'审核中'
		sql.append(" and t1.rcxwlbdm in (" + values + ") ");
		return dao.getListNotOut(sql.toString(), new String[] {  });
	}
	/** 
	 * 更新类别
	 */
	public boolean updateRcxwlbSfqy(RcxwdmwhForm model) throws Exception {
		String sql = "update xg_rcsw_rcxwlbdmb set sfqy=? where rcxwlbdm=?";
		return dao.runUpdate(sql, new String[] { model.getSfqy(), model.getRcxwlbdm() });
	}
	
	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_rcsw_rcxwdbdlb");
		super.setKey("rcxwlbdldm");
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwdmwhForm model)
			throws Exception {
		// TODO 自动生成方法存根

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				"select * from xg_rcsw_rcxwdbdlb  where 1=1");

		if (!StringUtil.isNull(model.getRcxwlbdldm())) {
			params.add(model.getRcxwlbdldm());
			sql.append(" and rcxwlbdldm = ? ");
		}

		if (!StringUtil.isNull(model.getRcxwlbdlmc())) {
			params.add(model.getRcxwlbdlmc());
			sql.append(" and rcxwlbdlmc like '%'||?||'%'");
		}

		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));
	}

	
	/**
	 * 
	 * @描述:获取行为大类代码最大序号值
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 下午12:41:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getMaxXwdldm() {
		String sql = "select max(to_number(RCXWLBDLDM)) max from xg_rcsw_rcxwdbdlb";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	
	/**
	 * 
	 * @描述:获取行为类别代码最大序号值
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 下午12:41:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getMaxXwlbdm() {
		String sql = "select max(to_number(rcxwlbdm)) max from xg_rcsw_rcxwlbdmb ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	
	/**
	 * 
	 * @描述:增加日常行为大类信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 下午12:29:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addXwdlInfo(RcxwdmwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from xg_rcsw_rcxwdbdlb where RCXWLBDLMC=? and SPLC= ? ";
		String num = dao.getOneRs(sql,
				new String[] { model.getRcxwlbdlmc(),model.getSplc() }, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_rcsw_rcxwdbdlb(RCXWLBDLDM,RCXWLBDLMC,SPLC) values(?,?,?)";
			b = dao.runUpdate(sql, new String[] { model.getRcxwlbdldm(),
					model.getRcxwlbdlmc(), model.getSplc() });
		} else {
			// msg="该行为大类代码已存在！";
			throw new SystemException(MessageKey.RCSW_RCXWWH_XWDLCZ);
		}
	
		return b;
	}
	
	/**
	 * 
	 * @描述:修改日常行为大类信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 下午12:29:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXwdlInfo(RcxwdmwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_rcxwdbdlb where RCXWLBDLMC=? and SPLC= ? ";
		String num = dao.getOneRs(sql1,
				new String[] { model.getRcxwlbdlmc(),model.getSplc() }, "num");
		
		//用相同的类别名称和大类代码  查找行为类别代码是否已存在
		sql = "select RCXWLBDLDM  from xg_rcsw_rcxwdbdlb where RCXWLBDLMC = ? and SPLC = ? ";
		String rcxwlbdldm = dao.getOneRs(sql, new String[] { model.getRcxwlbdlmc().trim(),model.getSplc() },
		"rcxwlbdldm");
		
		if ((rcxwlbdldm.equals(model.getRcxwlbdldm())) || "0".equals(num)) {
			sql = "update xg_rcsw_rcxwdbdlb set RCXWLBDLMC=?,SPLC=? where RCXWLBDLDM=?";
			b = dao.runUpdate(sql,
					new String[] { model.getRcxwlbdlmc().trim(), model.getSplc(),
							model.getRcxwlbdldm() });
		} else {
			// msg="该行为大类代码已存在！";
			throw new SystemException(MessageKey.RCSW_RCXWWH_XWDLCZ);
		}
		
		
		
		
		
		return b;
	}

	
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwdmwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 获得行为类别信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXwlbList(RcxwdmwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				"select *  from (select a.*,decode(a.rcxwfzlx,'01','加分','02','减分','未知')fzlxmc,"
						+" decode(a.sfqy,'1','启用','停用') sfqymc, "
						+"(case when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is not null then a.rcxwlbzdfz||'-'||a.rcxwlbzgfz when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is null then a.rcxwlbzdfz when a.rcxwlbzdfz is null and a.rcxwlbzgfz is not null then a.rcxwlbzgfz else '' end) fzqj,"
						+"b.rcxwlbdlmc  from xg_rcsw_rcxwlbdmb a "
						+ "left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm  ) a "
						+ "where 1=1");

		if (!StringUtil.isNull(model.getRcxwlbdm())) {
			params.add(model.getRcxwlbdm());
			sql.append(" and rcxwlbdm = ? ");
		}
		if (!StringUtil.isNull(model.getSfqy())) {
			params.add(model.getSfqy());
			sql.append(" and a.sfqy = ? ");
		}
		
		if(!StringUtil.isNull(model.getRcxwlbmc()) && !StringUtil.isNull(model.getRcxwlbdlmc())){
			params.add(model.getRcxwlbmc());
			sql.append(" and rcxwlbmc like '%'||?||'%'");
			
			params.add(model.getRcxwlbdlmc());
			sql.append(" and rcxwlbdlmc = ? ");
			
			
		}else{
			if (!StringUtil.isNull(model.getRcxwlbmc())) {
				params.add(model.getRcxwlbmc());
				sql.append(" and rcxwlbmc like '%'||?||'%'");
			}
			if (!StringUtil.isNull(model.getRcxwlbdlmc())) {
				params.add(model.getRcxwlbdlmc());
				sql.append(" and rcxwlbdlmc = ? ");
			}
		}
		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));

	}

	/**
	 * 保存行为类别信息
	 * 
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean addXwlbInfo(RcxwdmwhForm rcxwdmwhForm, String type)
			throws Exception {

		boolean b = false;
		RcxwdmwhForm model = (RcxwdmwhForm) StringUtils.formatBean(rcxwdmwhForm);
		String sql;
		sql = "select count(1) num from xg_rcsw_rcxwlbdmb where rcxwlbmc = ? and rcxwlbdldm = ? ";
		String num = dao.getOneRs(sql, new String[] { model.getRcxwlbmc(),model.getRcxwlbdldm() },
				"num");
		if ("0".equals(num)) {
			sql = "insert into xg_rcsw_rcxwlbdmb(rcxwlbdm,rcxwlbmc,rcxwlbdldm,rcxwfzlx,rcxwlbbz,rcxwlbzgfz,rcxwlbzdfz) values(?,?,?,?,?,?,?)";
			b = dao.runUpdate(sql,
					new String[] { model.getRcxwlbdm(), model.getRcxwlbmc(),
							model.getRcxwlbdldm(),model.getRcxwfzlx(),model.getRcxwlbbz(),model.getRcxwlbzgfz(),model.getRcxwlbzdfz() });
		} else {

			throw new SystemException(MessageKey.RCSW_RCXWWH_XWLBCZ);
		}
		return b;
	}

	/**
	 * 更改行为类别信息
	 * 
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean updateXwlbInfo(RcxwdmwhForm rcxwdmwhForm, String type)
			throws Exception {

		boolean b = false;
		RcxwdmwhForm model = (RcxwdmwhForm) StringUtils.formatBean(rcxwdmwhForm);
		String sql1;
		String sql;
		sql1 = "select count(1) num  from xg_rcsw_rcxwlbdmb where rcxwlbmc = ? and rcxwlbdldm = ? ";
		String num = dao.getOneRs(sql1, new String[] { model.getRcxwlbmc().trim(),model.getRcxwlbdldm() },
		"num");
		
		//用相同的类别名称和大类代码  查找行为类别代码是否已存在
		sql = "select rcxwlbdm  from xg_rcsw_rcxwlbdmb where rcxwlbmc = ? and rcxwlbdldm = ? ";
		String rcxwlbdm = dao.getOneRs(sql, new String[] { model.getRcxwlbmc().trim(),model.getRcxwlbdldm() },
		"rcxwlbdm");
		
		if ((rcxwlbdm.equals(model.getRcxwlbdm())) || "0".equals(num)) {
			sql = "update xg_rcsw_rcxwlbdmb set rcxwlbmc=?,rcxwlbdldm=? ,rcxwfzlx=?,rcxwlbbz=?,rcxwlbzgfz=?,rcxwlbzdfz=? where rcxwlbdm=?";
			b = dao.runUpdate(sql,
					new String[] { model.getRcxwlbmc().trim(), model.getRcxwlbdldm(),model.getRcxwfzlx(),
						model.getRcxwlbbz(),model.getRcxwlbzgfz(),model.getRcxwlbzdfz(), model.getRcxwlbdm() });
		} else {

			throw new SystemException(MessageKey.RCSW_RCXWWH_XWLBCZ);
		}
		
		return b;
	}

	/**
	 * 获得行为类别List<HashMap<String,String>>
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getXwlbListMap(RcxwdmwhForm model) {
		String sql = "select rcxwlbdldm,rcxwlbdlmc from xg_rcsw_rcxwdbdlb order by rcxwlbdldm";
		return dao.getList(sql, new String[] {}, new String[] { "rcxwlbdldm",
				"rcxwlbdlmc" });
	}

	/**
	 * 
	 * 修改行为类别 单个查询
	 * 
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午04:02:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rcxwlbdm
	 * @return
	 * @throws Exception
	 *             RcxwdmwhForm 返回类型
	 * @throws
	 */
	public RcxwdmwhForm getRcxwdmwhForm(RcxwdmwhForm t, String rcxwlbdm)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*, b.rcxwlbdlmc from xg_rcsw_rcxwlbdmb a ");
		sql.append("left join xg_rcsw_rcxwdbdlb b ");
		sql.append("on a.rcxwlbdldm = b.rcxwlbdldm) c ");
		sql.append(" where rcxwlbdm = ? ");

		return this.getModel(t, sql.toString(), new String[] { rcxwlbdm });

	}

	/**
	 * 删除类别信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int deleteXwlbInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from xg_rcsw_rcxwlbdmb where rcxwlbdm in ("
				+ values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}

	/**
	 * 
	 * 检验行为类别代码是否已使用
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-7 上午10:55:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 *             String[] 返回类型
	 * @throws
	 */
	public String[] checkXwlbForXwwh(String values) throws Exception {
		StringBuilder sql = new StringBuilder(
				" select distinct a.rcxwlbmc from xg_rcsw_rcxwlbdmb a, (select rcxwlbdm from xg_rcsw_rcxwxxwh union select rcxwlbdm from xg_rcsw_rcxwjg) b where a.rcxwlbdm = b.rcxwlbdm "
						+ " and a.rcxwlbdm in(" + values + ")");
		String[] rcxwlbmc = dao.getRs(sql.toString(), new String[] {},
				"rcxwlbmc");
		return rcxwlbmc;
	}
	/**
	 * 
	 * @描述:获取日常行为大类列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-14 上午08:48:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getRcxwdlList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct RCXWLBDLDM,RCXWLBDLMC,RCXWDLJCF,RCXWDLFSSX from XG_RCSW_RCXWDBDLB");
		return dao.getListNotOut(sql.toString(), new String[]{});
						
		
	}

}
