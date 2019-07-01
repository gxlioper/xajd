package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * 日常行为 代码维护
 */
public class RcxwdmwhDao extends SuperDAOImpl<RcxwdmwhForm> {

	/**
	 * 获取行为大类
	 */
	public List<HashMap<String, String>> getRcxwdlPageList(RcxwdmwhForm model, User user)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

	/*	sql.append(" select d.rcxwlbdldm,d.rcxwlbdlmc,decode(e.lcxx,'','无需审核',e.lcxx)lcxx from  XG_RCSW_NEW_RCXWDLDMB d ");
		sql.append(" left join   ( select splc, mc || '：' || replace(max(lcxx), ',', '->') lcxx from (select splc, ");
		sql.append(" a.mc,to_char(WM_CONCAT(c.mc) over(partition by splc order by xh)) lcxx ");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw' and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) group by splc, mc )  e on  d.splc = e.splc  where 1=1 ");*/

		sql.append(" select d.rcxwlbdm,d.rcxwlbdldm,d.rcxwlbdlmc,d.sqkssj,d.sqjssj,d.sqkg,");
		sql.append("d.ssxydm,CASE WHEN d.ssxydm = 'qx' THEN '全校' ELSE g.bmmc END ssxymc,");
		sql.append(" decode(e.lcxx, '', '无需审核', mc || '：' || replace(lcxx, ';', '->')) lcxx, ");
		sql.append(" f.rcxwlbmc ");
		sql.append(" from XG_RCSW_NEW_RCXWDLDMB d left join (select splc, mc, lcxx ");
		sql.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh)) lcxx, xh,row_number() over(partition by splc order by xh desc ) as ddd ");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw'and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splc = e.splc ");
		sql.append(" left join ZXBZ_XXBMDM g on g.bmdm=d.ssxydm ");
		sql.append(" left join ( ");
		sql.append(getRcxwlbListByYhsqSQL(user, params));
		sql.append(" ) f on d.rcxwlbdm=f.rcxwlbdm ");
		sql.append(" where 1 = 1 and f.rcxwlbdm is not null ");

		if (!StringUtil.isNull(model.getRcxwlbdm())) {
			params.add(model.getRcxwlbdm());
			sql.append(" and d.rcxwlbdm = ? ");
		}

		if (!StringUtil.isNull(model.getRcxwlbdlmc())) {
			params.add(model.getRcxwlbdlmc());
			sql.append(" and d.rcxwlbdlmc like '%'||?||'%'");
		}

		if (!StringUtil.isNull(model.getSsxydm())) {
			params.add(model.getSsxydm());
			sql.append(" and d.ssxydm = ? ");
		}else{
			if(!"xx".equalsIgnoreCase(user.getUserStatus())){
				params.add(user.getUserDep());
				sql.append(" and (d.ssxydm = ? or d.ssxydm = 'qx')");
			}
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));

	}
	/**
	 * 获得行为大类信息
	 */
	public List<String[]> getXwdlList(RcxwdmwhForm model) throws Exception {
		String searchTj = "";
		List<String> inputV = new ArrayList<String>();
		String sql = "select * from (select a.*,rownum r from XG_RCSW_NEW_RCXWDLDMB a order by a.rcxwlbdldm) ";
		String[] output = new String[] { "RCXWLBDLDM", "RCXWLBDLDM",
				"RCXWLBDLMC" };
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[] {}), output, model);

	}
	/**
	 * 删除大类信息
	 */
	public int deleteRcxwdlInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from XG_RCSW_NEW_RCXWDLDMB where rcxwlbdldm in (" + values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}
	/**
	 * 根据大类代码查询大类名称
	 */
	public String getRcxwlbdlmcById(String id) throws Exception {
		String sql = "select rcxwlbdlmc from XG_RCSW_NEW_RCXWDLDMB where rcxwlbdldm=? ";
		return dao.getOneRs(sql, new String[] { id }, "rcxwlbdlmc");
	}
	/**
	 * 检验行为大类代码是否已使用
	 */
	public String[] checkRcxwdl(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.rcxwlbdlmc from ( ");
		sql.append(" select a.rcxwlbdlmc from XG_RCSW_NEW_RCXWDLDMB a ");
		sql.append(" left join XG_RCSW_NEW_RCXWXLDMB b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" where b.rcxwlbdldm is not null and a.rcxwlbdldm in (" + values + ") ");
		sql.append(" ) a ");
		String[] rcxwlbdlmc = dao.getRs(sql.toString(), new String[] {}, "rcxwlbdlmc");
		return rcxwlbdlmc;
	}
	/**
	 * 判断行为小类是否被行为维护应用
	 */
	public String[] checkRcxwxl(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.rcxwlbxlmc from ");
		sql.append(" XG_RCSW_NEW_RCXWXLDMB a, ");
		sql.append(" (select rcxwlbxldm from XG_RCSW_NEW_RCXWXXWH union select rcxwlbxldm from XG_RCSW_NEW_RCXWJG) b ");
		sql.append(" where a.rcxwlbxldm = b.rcxwlbxldm ");
		sql.append(" and a.rcxwlbxldm in(" + values + ") ");
		String[] rcxwlbxlmc = dao.getRs(sql.toString(), new String[] {}, "rcxwlbxlmc");
		return rcxwlbxlmc;
	}
	/** 
	 * 获取审核流未结束的大类
	 */
	public List<HashMap<String,String>> getRcxwdlShwjs(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t3.rcxwlbdldm ");
		sql.append(" from XG_RCSW_NEW_RCXWXXWH t1 ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.rcxwlbdm, b.rcxwlbdldm ");
		sql.append(" from XG_RCSW_NEW_RCXWXLDMB a ");
		sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm ");
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
		sql.append(" from XG_RCSW_NEW_RCXWXXWH t1 ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.rcxwlbdm, b.rcxwlbdldm ");
		sql.append(" from XG_RCSW_NEW_RCXWXLDMB a ");
		sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm ");
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
		String sql = "update XG_RCSW_NEW_RCXWXLDMB set sfqy=? where rcxwlbdm=?";
		return dao.runUpdate(sql, new String[] { model.getSfqy(), model.getRcxwlbdm() });
	}
	/** 
	 * 获取审核流未结束的小类
	 */
	public List<HashMap<String,String>> getRcxwxlShwjs(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t1.rcxwlbxldm ");
		sql.append(" from XG_RCSW_NEW_RCXWXXWH t1 ");
		sql.append(" left join XG_RCSW_NEW_RCXWXLDMB t3 on t1.rcxwlbxldm = t3.rcxwlbxldm ");
		sql.append(" where t1.shzt in ('4','5') "); // '需重审'、'审核中'
		sql.append(" and t1.rcxwlbxldm in (" + values + ") ");
		return dao.getListNotOut(sql.toString(), new String[] {  });
	}
	/** 
	 * 更新小类
	 */
	public boolean updateRcxwxlSfqy(RcxwdmwhForm model) throws Exception {
		String sql = "update XG_RCSW_NEW_RCXWXLDMB set sfqy=? where rcxwlbxldm=?";
		return dao.runUpdate(sql, new String[] { model.getSfqy(), model.getRcxwlbxldm() });
	}
	
	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("XG_RCSW_NEW_RCXWDLDMB");
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
		StringBuilder sql = new StringBuilder("select * from XG_RCSW_NEW_RCXWDLDMB  where 1=1");

		if (!StringUtil.isNull(model.getRcxwlbdldm())) {
			params.add(model.getRcxwlbdldm());
			sql.append(" and rcxwlbdldm = ? ");
		}

		if (!StringUtil.isNull(model.getRcxwlbdlmc())) {
			params.add(model.getRcxwlbdlmc());
			sql.append(" and rcxwlbdlmc like '%'||?||'%'");
		}

		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 * 获取行为类别代码最大序号值
	 */
	public String getMaxRcxwlbdm() {
		String sql = "select max(to_number(rcxwlbdm)) max from XG_RCSW_NEW_RCXWLBDMB ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	/**
	 * 获取行为大类代码最大序号值
	 */
	public String getMaxRcxwdldm() {
		String sql = "select max(to_number(RCXWLBDLDM)) max from XG_RCSW_NEW_RCXWDLDMB";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	/**
	 * 获取行为小类代码最大序号值
	 */
	public String getMaxRcxwxldm() {
		String sql = "select max(to_number(rcxwlbxldm)) max from XG_RCSW_NEW_RCXWXLDMB ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	/**
	 * 增加日常行为大类信息
	 */
	public boolean addRcxwdlInfo(RcxwdmwhForm model) throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from XG_RCSW_NEW_RCXWDLDMB where RCXWLBDLMC=? and SPLC= ? and rcxwlbdm=? ";
		String num = dao.getOneRs(sql, new String[] { model.getRcxwlbdlmc().trim(),model.getSplc(),model.getRcxwlbdm() }, "num");
		if ("0".equals(num)) {
				sql = "insert into XG_RCSW_NEW_RCXWDLDMB(RCXWLBDLDM,RCXWLBDLMC,SPLC,rcxwlbdm,ssxydm) values(?,?,?,?,?)";
				b = dao.runUpdate(sql, new String[] { model.getRcxwlbdldm(), model.getRcxwlbdlmc().trim(),
						model.getSplc(),model.getRcxwlbdm(),model.getSsxydm() });

		} else {
			throw new SystemException(MessageKey.RCSW_RCXWWH_NEW_RCXWDLCZ);
		}
		return b;
	}
	/**
	 * 修改日常行为大类信息
	 */
	public boolean updateRcxwdlInfo(RcxwdmwhForm model) throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from XG_RCSW_NEW_RCXWDLDMB where RCXWLBDLMC=? and SPLC= ? and rcxwlbdm=? and rcxwlbdldm <> ? ";
		String num = dao.getOneRs(sql1, new String[] { model.getRcxwlbdlmc().trim(),model.getSplc(),model.getRcxwlbdm(),model.getRcxwlbdldm() }, "num");
		if ("0".equals(num)) {
				if(StringUtil.isNull(model.getRcxwlbdm())){
					sql = "update XG_RCSW_NEW_RCXWDLDMB set RCXWLBDLMC=? where RCXWLBDLDM=?";
					b = dao.runUpdate(sql, new String[] { model.getRcxwlbdlmc().trim(), model.getRcxwlbdldm() });
				}else{
					sql = "update XG_RCSW_NEW_RCXWDLDMB set RCXWLBDLMC=?,SPLC=?,rcxwlbdm=? where RCXWLBDLDM=?";
					b = dao.runUpdate(sql, new String[] { model.getRcxwlbdlmc().trim(), model.getSplc(),model.getRcxwlbdm(), model.getRcxwlbdldm() });
				}

		} else {
			throw new SystemException(MessageKey.RCSW_RCXWWH_NEW_RCXWDLCZ);
		}
		return b;
	}
	/**
	 * 增加日常行为小类信息
	 */
	public boolean addRcxwxlInfo(RcxwdmwhForm model) throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from XG_RCSW_NEW_RCXWXLDMB where rcxwlbxlmc = ? and rcxwlbdldm = ? ";
		String num = dao.getOneRs(sql, new String[] { model.getRcxwlbxlmc().trim(),model.getRcxwlbdldm() }, "num");
		if ("0".equals(num)) {
			sql = "insert into XG_RCSW_NEW_RCXWXLDMB(rcxwlbxldm,rcxwlbxlmc,rcxwlbdldm,rcxwlbdm,rcxwfzlx,rcxwlbfz,rcxwlbbz,rcxwlbzgfz,rcxwlbzdfz) values(?,?,?,?,?,?,?,?,?)";
			b = dao.runUpdate(sql, new String[] { model.getRcxwlbxldm(), model.getRcxwlbxlmc().trim(), model.getRcxwlbdldm(),model.getRcxwlbdm(), model.getRcxwfzlx(),model.getRcxwlbfz(),model.getRcxwlbbz(),model.getRcxwlbzgfz(),model.getRcxwlbzdfz() });
		} else {
			throw new SystemException(MessageKey.RCSW_RCXWWH_NEW_RCXWXLCZ);
		}
		return b;
	}
	/**
	 * 修改日常行为小类信息
	 */
	public boolean updateRcxwxlInfo(RcxwdmwhForm model) throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from XG_RCSW_NEW_RCXWXLDMB where rcxwlbxlmc = ? and rcxwlbdldm = ? and rcxwlbxldm <> ? ";
		String num = dao.getOneRs(sql, new String[] { model.getRcxwlbxlmc().trim(),model.getRcxwlbdldm(),model.getRcxwlbxldm() }, "num");
		if ("0".equals(num)) {
			sql = "update XG_RCSW_NEW_RCXWXLDMB set rcxwlbxlmc=?,rcxwlbdldm=?,rcxwlbdm=?,rcxwfzlx=?,rcxwlbfz=?,rcxwlbbz=?,rcxwlbzgfz=?,rcxwlbzdfz=? where rcxwlbxldm=? ";
			b = dao.runUpdate(sql, new String[] { model.getRcxwlbxlmc().trim(), model.getRcxwlbdldm(),model.getRcxwlbdm(), model.getRcxwfzlx(),model.getRcxwlbfz(),model.getRcxwlbbz(),model.getRcxwlbzgfz(),model.getRcxwlbzdfz(),model.getRcxwlbxldm() });
		} else {
			throw new SystemException(MessageKey.RCSW_RCXWWH_NEW_RCXWXLCZ);
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
	 */
	public List<HashMap<String, String>> getRcxwlbList(RcxwdmwhForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				"select *  from (select a.* from XG_RCSW_NEW_RCXWLBDMB a order by a.rcxwlbdm asc) a where 1=1 ");

		if (!StringUtil.isNull(model.getRcxwlbdm())) {
			params.add(model.getRcxwlbdm());
			sql.append(" and rcxwlbdm = ? ");
		}
		
		if(!StringUtil.isNull(model.getRcxwlbmc())){
			params.add(model.getRcxwlbmc());
			sql.append(" and rcxwlbmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 * 根据用户授权获得大类
	 */
	public List<HashMap<String, String>>  getRcxwlbdlListByYhsq(User user) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select b.* from XG_RCSW_NEW_RCXWDLDMB b ");
		sql.append("left join ( ");
		sql.append(getRcxwlbListByYhsqSQL(user, params));
		sql.append(" ) c on b.rcxwlbdm=c.rcxwlbdm ");
		sql.append(" where c.rcxwlbdm is not null ");
		if(!"xx".equals(user.getUserStatus())){
			sql.append(" and b.ssxydm='"+user.getUserDep()+"' or b.ssxydm='qx'");
		}
		return dao.getListNotOut(sql.toString(), params.toArray(new String[] { }));
	}
	/**
	 * 根据类别代码查询大类
	 */
	public List<HashMap<String, String>>  queryRcxwlbdlListByLbdm(String rcxwlbdm,User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select b.* from XG_RCSW_NEW_RCXWDLDMB b ");
		sql.append(" where b.rcxwlbdm = ? ");
		if(!"xx".equalsIgnoreCase(user.getUserStatus())){
			sql.append(" and b.ssxydm='"+user.getUserDep()+"'");
		}else{
			sql.append(" and b.ssxydm='qx'");
		}
		return dao.getListNotOut(sql.toString(), new String[] { rcxwlbdm });
	}
	/**
	 * 根据大类代码查询小类
	 */
	public List<HashMap<String, String>>  queryRcxwlbxlListByDldm(String rcxwlbdldm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select b.* from XG_RCSW_NEW_RCXWXLDMB b ");
		sql.append(" where b.rcxwlbdldm = ? ");
		return dao.getListNotOut(sql.toString(), new String[] { rcxwlbdldm });
	}
	/**
	 * 根据用户授权获得类别
	 */
	public List<HashMap<String, String>>  getRcxwlbListByYhsq(User user) throws Exception {
		List<String> params = new ArrayList<String>();
		String sql = getRcxwlbListByYhsqSQL(user, params);
		return dao.getListNotOut(sql, params.toArray(new String[] { }));
	}
	/**
	 * 根据用户授权获得类别SQL
	 */
	public String getRcxwlbListByYhsqSQL(User user, List<String> params) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from XG_RCSW_NEW_RCXWLBDMB a ");
		sql.append(" left join XG_RCSW_NEW_RCXWSQB b on a.rcxwlbdm=b.rcxwlbdm ");
		sql.append(" where (b.zgh=? or b.zgh is null) ");
		params.add(user.getUserName());
		return sql.toString();
	}
	/**
	 * 保存行为类别信息
	 */
	public boolean addRcxwlbInfo(RcxwdmwhForm rcxwdmwhForm, String type) throws Exception {
		boolean b = false;
		RcxwdmwhForm model = (RcxwdmwhForm) StringUtils.formatBean(rcxwdmwhForm);
		String sql;
		sql = "select count(1) num from XG_RCSW_NEW_RCXWLBDMB where rcxwlbmc = ? ";
		String num = dao.getOneRs(sql, new String[] { model.getRcxwlbmc().trim() }, "num");
		if ("0".equals(num)) {
			sql = "insert into XG_RCSW_NEW_RCXWLBDMB(rcxwlbdm,rcxwlbmc) values(?,?)";
			b = dao.runUpdate(sql, new String[] { model.getRcxwlbdm(), model.getRcxwlbmc().trim() });
		} else {
			throw new SystemException(MessageKey.RCSW_RCXWWH_NEW_RCXWLBMCCZ);
		}
		return b;
	}
	/**
	 * 更改行为类别信息
	 */
	public boolean updateRcxwlbInfo(RcxwdmwhForm rcxwdmwhForm, String type) throws Exception {
		boolean b = false;
		RcxwdmwhForm model = (RcxwdmwhForm) StringUtils.formatBean(rcxwdmwhForm);
		String sql1;
		String sql;
		sql1 = "select count(1) num  from XG_RCSW_NEW_RCXWLBDMB where rcxwlbmc = ? and rcxwlbdm <> ? ";
		String num = dao.getOneRs(sql1, new String[] { model.getRcxwlbmc().trim(),model.getRcxwlbdm() }, "num");
		if ("0".equals(num)) {
			sql = "update XG_RCSW_NEW_RCXWLBDMB set rcxwlbmc=? where rcxwlbdm=?";
			b = dao.runUpdate(sql, new String[] { model.getRcxwlbmc().trim(), model.getRcxwlbdm() });
		} else {
			throw new SystemException(MessageKey.RCSW_RCXWWH_NEW_RCXWLBMCCZ);
		}
		return b;
	}
	/**
	 * 行为小类
	 */
	public List<HashMap<String,String>> getRcxwxlList(RcxwdmwhForm model, User user) throws Exception{
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select *  from ( select a.*,decode(a.rcxwfzlx,'01','加分','02','减分','未知')fzlxmc,");
		sql.append(" decode(a.sfqy,'1','启用','停用') sfqymc, ");
		sql.append(" b.ssxydm,CASE WHEN b.ssxydm = 'qx' THEN '全校' ELSE g.bmmc END ssxymc,");
		sql.append("(case when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is not null then a.rcxwlbzdfz||'-'||a.rcxwlbzgfz when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is null then a.rcxwlbzdfz when a.rcxwlbzdfz is null and a.rcxwlbzgfz is not null then a.rcxwlbzgfz else '' end) fzqj,");
		sql.append("b.rcxwlbdlmc,c.rcxwlbmc  from XG_RCSW_NEW_RCXWXLDMB a ");
		sql.append("left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" left join ZXBZ_XXBMDM g on g.bmdm=b.ssxydm ");
		sql.append("left join ( ");
		sql.append(getRcxwlbListByYhsqSQL(user, params));
		sql.append(" ) c on a.rcxwlbdm=c.rcxwlbdm ");
		sql.append(" where c.rcxwlbdm is not null ");
		sql.append(" ) a where 1 = 1 ");
				
		if (!StringUtil.isNull(model.getSfqy())) {
			params.add(model.getSfqy());
			sql.append(" and a.sfqy = ? ");
		}
//		if(!StringUtil.isNull(model.getRcxwlbdlmc())){
//			params.add(model.getRcxwlbdlmc());
//			sql.append(" and a.rcxwlbdlmc like '%'||?||'%'");
//		}
		if(!StringUtil.isNull(model.getRcxwlbdldm())){
			params.add(model.getRcxwlbdldm());
			sql.append(" and a.rcxwlbdldm = ? ");
		}
		if (!StringUtil.isNull(model.getRcxwlbxlmc())) {
			params.add(model.getRcxwlbxlmc());
			sql.append(" and a.rcxwlbxlmc like '%'||?||'%'");
		}

		if (!StringUtil.isNull(model.getSsxydm())) {
			params.add(model.getSsxydm());
			sql.append(" and a.ssxydm = ? ");
		}else{
			if(!"xx".equalsIgnoreCase(user.getUserStatus())){
				params.add(user.getUserDep());
				sql.append(" and (a.ssxydm = ? or a.ssxydm = 'qx') ");
			}
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 * 查询行为类别
	 */
	public RcxwdmwhForm getRcxwlbForm(RcxwdmwhForm t)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from XG_RCSW_NEW_RCXWLBDMB a where a.rcxwlbdm = ? ");
		return this.getModel(t, sql.toString(), new String[] { t.getRcxwlbdm() });
	}
	/**
	 * 查询行为小类
	 */
	public RcxwdmwhForm getRcxwxlForm(RcxwdmwhForm t)
	throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from XG_RCSW_NEW_RCXWXLDMB a where a.rcxwlbxldm = ? ");
		return this.getModel(t, sql.toString(), new String[] { t.getRcxwlbxldm() });
	}
	/**
	 * 删除行为类别信息
	 */
	public int deleteRcxwlbInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from XG_RCSW_NEW_RCXWLBDMB where rcxwlbdm in (" + values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}
	/**
	 * 删除行为类别用户授权信息
	 */
	public int deleteRcxwsqbInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from XG_RCSW_NEW_RCXWSQB where rcxwlbdm in (" + values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}
	/**
	 * 删除行为小类信息
	 */
	public int deleteRcxwxlInfo(String values) throws Exception{
		int num = 0;
		String sql = "delete from XG_RCSW_NEW_RCXWXLDMB where rcxwlbxldm in (" + values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}
	/**
	 * 检验行为类别代码是否已使用
	 */
	public String[] checkRcxwlb(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.rcxwlbmc from ( ");
		sql.append(" select a.rcxwlbmc from XG_RCSW_NEW_RCXWLBDMB a ");
		sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdm = b.rcxwlbdm ");
		sql.append(" where b.rcxwlbdm is not null and a.rcxwlbdm in (" + values + ") ");
		sql.append(" ) a ");
		String[] rcxwlbmc = dao.getRs(sql.toString(), new String[] {}, "rcxwlbmc");
		return rcxwlbmc;
	}
	/**
	 * 获取日常行为大类列表
	 */
	public List<HashMap<String,String>> getRcxwdlList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct RCXWLBDLDM,RCXWLBDLMC from XG_RCSW_NEW_RCXWDLDMB");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

    public List<HashMap<String,String>> getBmlist(User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm,bmmc from ZXBZ_XXBMDM t where bmlb='5' ");
		if(!"xx".equalsIgnoreCase(user.getUserStatus())){
			sql.append(" and t.bmdm = '"+user.getUserDep()+"'");
		}
		sql.append(" order by bmmc");
		return dao.getListNotOut(sql.toString(), new String[]{});

    }

	public String getSsxy(String rcxwlbdldm) {
		String sql = "select CASE WHEN a.ssxydm = 'qx' THEN '全校' ELSE b.bmmc END ssxymc from XG_RCSW_NEW_RCXWDLDMB a " +
				"left join ZXBZ_XXBMDM b on b.bmdm=a.ssxydm where a.RCXWLBDLDM=? ";
		return dao.getOneRs(sql,new String[]{rcxwlbdldm},"ssxymc");
	}
}
