package xgxt.qgzx.zgdzdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mortbay.html.Page;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 中国地质大学勤工助学DAO
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 李容
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-09-23
 * </p>
 */
public class QgzxZgdzdxDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();

	/**
	 * 组合查询条件
	 * 
	 * @param model
	 * @return StringBuffer
	 */
	public StringBuffer getWhereSql(QgzxZgdzdxForm model) {
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		String xm = model.getXm();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String sqdwmc = model.getSqdwmc();
		String sqsj = model.getSqsj();
		String sfygw = model.getSfygw();
		String userType = model.getUserType();
		String yesNo = model.getYesNo();
		String gwdm = model.getGwdm();
		String yrdwdm = model.getYrdwdm();
		String gwxzdm = model.getGwxzdm();
		String xxsh = model.getXxsh();
		String xysh = model.getXysh();

		StringBuffer sb = new StringBuffer("where 1=1 ");
		if (xydm != null && !xydm.equalsIgnoreCase("")) {
			sb.append(" and xydm=?");
			value.add(xydm);
		}
		if (zydm != null && !zydm.equalsIgnoreCase("")) {
			sb.append(" and zydm=?");
			value.add(zydm);
		}
		if (bjdm != null && !bjdm.equalsIgnoreCase("")) {
			sb.append(" and bjdm=?");
			value.add(bjdm);
		}
		if (nj != null && !nj.equalsIgnoreCase("")) {
			sb.append(" and nj=?");
			value.add(nj);
		}
		if (xh != null && !xh.equalsIgnoreCase("")) {
			sb.append(" and xh like '%'||?||'%'");
			value.add(xh);
		}
		if (xm != null && !xm.equalsIgnoreCase("")) {
			sb.append(" and xm like '%'||?||'%'");
			value.add(xm);
		}
		if (xn != null && !xn.equalsIgnoreCase("")) {
			sb.append(" and xn=?");
			value.add(xn);
		}
		if (nd != null && !nd.equalsIgnoreCase("")) {
			sb.append(" and nd=?");
			value.add(nd);
		}
		if (xq != null && !xq.equalsIgnoreCase("")) {
			sb.append(" and xq=?");
			value.add(xq);
		}
		if (sqdwmc != null && !sqdwmc.equalsIgnoreCase("")) {
			sb.append(" and sqdwmc=?");
			value.add(sqdwmc);
		}
		if (sqsj != null && !sqsj.equalsIgnoreCase("")) {
			sb.append(" and sqsj like ? ");
			value.add("%" + sqsj + "%");
		}
		if (sfygw != null && !sfygw.equalsIgnoreCase("")) {
			sb.append(" and sfygw = ? ");
			value.add(sfygw);
		}
		if (gwdm != null && !gwdm.equalsIgnoreCase("")) {
			sb.append(" and gwdm = ? ");//修改
			value.add(gwdm);
		}
		if (yrdwdm != null && !yrdwdm.equalsIgnoreCase("")) {
			sb.append(" and yrdwdm = ? ");
			value.add(yrdwdm);
		}
		if (!StringUtils.isNull(gwxzdm)) {
			sb.append(" and gwxzdm = ? ");
			value.add(gwxzdm);
		}
		if (!StringUtils.isNull(xxsh)) {
			sb.append(" and xxsh = ? ");
			value.add(xxsh);
		}
		if (!StringUtils.isNull(xysh)) {
			sb.append(" and xysh = ? ");
			value.add(xysh);
		}
		if (yesNo != null && !yesNo.equalsIgnoreCase("")) {
			if (userType != null
					&& ("admin".equalsIgnoreCase(userType) || "xx"
							.equalsIgnoreCase(userType))) {
				sb.append(" and xxsh = ? ");
				value.add(yesNo);
			} else {
				sb.append(" and xysh = ? ");
				value.add(yesNo);
			}
		}
		return sb;
	}
	
	/**
	 * 组合查询条件
	 * 
	 * @param model
	 * @return StringBuffer
	 */
	public StringBuffer getGwmcWhereSql(QgzxZgdzdxForm model) {
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		String xm = model.getXm();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String sqdwmc = model.getSqdwmc();
		String sqsj = model.getSqsj();
		String sfygw = model.getSfygw();
		String userType = model.getUserType();
		String yesNo = model.getYesNo();
		String gwdm = model.getGwdm();
		String yrdwdm = model.getYrdwdm();
		String gwxzdm = model.getGwxzdm();
		String xxsh = model.getXxsh();
		String xysh = model.getXysh();

		StringBuffer sb = new StringBuffer("where 1=1 ");
		if (xydm != null && !xydm.equalsIgnoreCase("")) {
			sb.append(" and xydm=?");
			value.add(xydm);
		}
		if (zydm != null && !zydm.equalsIgnoreCase("")) {
			sb.append(" and zydm=?");
			value.add(zydm);
		}
		if (bjdm != null && !bjdm.equalsIgnoreCase("")) {
			sb.append(" and bjdm=?");
			value.add(bjdm);
		}
		if (nj != null && !nj.equalsIgnoreCase("")) {
			sb.append(" and nj=?");
			value.add(nj);
		}
		if (xh != null && !xh.equalsIgnoreCase("")) {
			sb.append(" and xh like '%'||?||'%'");
			value.add(xh);
		}
		if (xm != null && !xm.equalsIgnoreCase("")) {
			sb.append(" and xm like '%'||?||'%'");
			value.add(xm);
		}
		if (xn != null && !xn.equalsIgnoreCase("")) {
			sb.append(" and xn=?");
			value.add(xn);
		}
		if (nd != null && !nd.equalsIgnoreCase("")) {
			sb.append(" and nd=?");
			value.add(nd);
		}
		if (xq != null && !xq.equalsIgnoreCase("")) {
			sb.append(" and xq=?");
			value.add(xq);
		}
		if (sqdwmc != null && !sqdwmc.equalsIgnoreCase("")) {
			sb.append(" and sqdwmc=?");
			value.add(sqdwmc);
		}
		if (sqsj != null && !sqsj.equalsIgnoreCase("")) {
			sb.append(" and sqsj like ? ");
			value.add("%" + sqsj + "%");
		}
		if (sfygw != null && !sfygw.equalsIgnoreCase("")) {
			sb.append(" and sfygw = ? ");
			value.add(sfygw);
		}
		if (gwdm != null && !gwdm.equalsIgnoreCase("")) {
			sb.append(" and gwdm = ? ");
			value.add(gwdm);
		}
		if (yrdwdm != null && !yrdwdm.equalsIgnoreCase("")) {
			sb.append(" and yrdwdm = ? ");
			value.add(yrdwdm);
		}
		if (!StringUtils.isNull(gwxzdm)) {
			sb.append(" and gwxzdm = ? ");
			value.add(gwxzdm);
		}
		if (!StringUtils.isNull(xxsh)) {
			sb.append(" and xxsh = ? ");
			value.add(xxsh);
		}
		if (!StringUtils.isNull(xysh)) {
			sb.append(" and xysh = ? ");
			value.add(xysh);
		}
		if (yesNo != null && !yesNo.equalsIgnoreCase("")) {
			if (userType != null
					&& ("admin".equalsIgnoreCase(userType) || "xx"
							.equalsIgnoreCase(userType))) {
				sb.append(" and xxsh = ? ");
				value.add(yesNo);
			} else {
				sb.append(" and xysh = ? ");
				value.add(yesNo);
			}
		}
		return sb;
	}

	/**
	 * 组合导出查询条件
	 * 
	 * @param model
	 * @return StringBuffer
	 */
	public StringBuffer getWhereExportSql(QgzxZgdzdxForm model) {
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		String xm = model.getXm();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String sfygw = model.getSfygw();
		String gwdm = model.getGwdm();
		String userType = model.getUserType();
		String yesNo = model.getYesNo();

		StringBuffer sb = new StringBuffer("where 1=1 ");
		if (xydm != null && !xydm.equalsIgnoreCase("")) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("'");
		}
		if (zydm != null && !zydm.equalsIgnoreCase("")) {
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("'");
		}
		if (bjdm != null && !bjdm.equalsIgnoreCase("")) {
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("'");
		}
		if (nj != null && !nj.equalsIgnoreCase("")) {
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("'");
		}
		if (xh != null && !xh.equalsIgnoreCase("")) {
			sb.append(" and xh='");
			sb.append(xh);
			sb.append("'");
		}
		if (xm != null && !xm.equalsIgnoreCase("")) {
			sb.append(" and xm='");
			sb.append(xm);
			sb.append("'");
		}
		if (xn != null && !xn.equalsIgnoreCase("")) {
			sb.append(" and xn='");
			sb.append(xn);
			sb.append("'");
		}
		if (nd != null && !nd.equalsIgnoreCase("")) {
			sb.append(" and nd='");
			sb.append(nd);
			sb.append("'");
		}
		if (xq != null && !xq.equalsIgnoreCase("")) {
			sb.append(" and xq='");
			sb.append(xq);
			sb.append("'");
		}
		if (gwdm != null && !gwdm.equalsIgnoreCase("")) {
			sb.append(" and gwdm||'-'||gwsbsj='");
			sb.append(gwdm);
			sb.append("'");
		}
		if (sfygw != null && !sfygw.equalsIgnoreCase("")) {
			sb.append(" and 是否有岗位='");
			sb.append(sfygw);
			sb.append("'");
		}
		if (yesNo != null && !yesNo.equalsIgnoreCase("")) {
			if (userType != null
					&& ("admin".equalsIgnoreCase(userType) || "xx"
							.equalsIgnoreCase(userType))) {
				sb.append(" and xxsh='");
				sb.append(sfygw);
				sb.append("'");
			} else {
				sb.append(" and xysh='");
				sb.append(sfygw);
				sb.append("'");
			}
		}
		return sb;
	}

	/**
	 * 查询勤工助学黑名单信息
	 * 
	 * @param model
	 * @return List
	 */
	public List getHmdInfo(QgzxZgdzdxForm model) {
		String[] outputValue = { "xh", "xm", "nj", "xymc", "zymc", "bjmc","jrsj" };
		String sql = "select xh,xm,nj,xymc,zymc,bjmc,bz,jrsj from view_qgzx_zgdzdx_hmdb ";
		String whereSql = getWhereSql(model).toString();
		model.getPages().setMaxRecord(Integer.parseInt(
				getOneRs("select count(*)num from(" +sql+whereSql+")", 
				value != null ? value.toArray(new String[0]) : new String[] {}, 
				"num")));
		sql = "select * from (select rownum r,a.* from ( " +sql+whereSql+")a) where r>" +(model.getPages().getStart()) + " and r<=" + (model.getPages().getStart()+model.getPages().getPageSize());
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[] {}, outputValue);
	}

	/**
	 * 查询单个学生黑名单信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getOneHmd(String pkValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outputValue = { "xh", "xm", "xymc", "zymc", "bjmc", "nj", "bz" };
		String sql = "select xh,xm,xymc,zymc,bjmc,nj,bz from view_qgzx_zgdzdx_hmdb where xh=?";

		map = getMap(sql, new String[] { pkValue }, outputValue);
		return map;
	}

	/**
	 * 判断记录是否存在
	 * 
	 * @param model
	 * @return int
	 */
	public int getCount(QgzxZgdzdxForm model) {
		String xh = model.getXh();
		String sql = "select count(*)num from qgzx_zgdzdx_hmdb where xh=?";
		String num = getOneRs(sql, new String[] { xh }, "num");
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;

		return Integer.parseInt(num);
	}

	/**
	 * 查询单个校外勤工助学岗位信息
	 * 
	 * @param model
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getXwgwxx(QgzxZgdzdxForm model) {
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = "gwmc||sqsj";
		String pkValue = DealString.toGBK(model.getPkValue());
		String[] outputValue = { "gwmc", "sqdwmc", "sqdwdz", "gzkssj",
				"gzjssj", "xyrs", "xyknsrs", "jcfs", "jcbz", "lxdh", "gzsj",
				"gznr", "gzyq", "bz", "xn", "nd", "xq", "sqsj" };
		String sql = "select * from qgzx_xwgwxxb where " + pk + "=?";

		map = getMap(sql, new String[] { pkValue }, outputValue);
		return map;
	}

	/**
	 * 获取校外单位列表
	 * 
	 * @return List
	 */
	public List getXwsqdwList() {
		String sql = "select distinct sqdwmc from qgzx_xwgwxxb";
		return getList(sql, new String[] {}, new String[] { "sqdwmc" });
	}

	/**
	 * 校外岗位信息查询
	 * 
	 * @param model
	 * @return List
	 */
	public List searchXwgw(QgzxZgdzdxForm model) {
		model.setXh("");
		model.setNj("");
		model.setXydm("");
		model.setZydm("");
		model.setBjdm("");
		model.setXm("");
		String tableName = "qgzx_xwgwxxb";
		String sql = "select gwmc||sqsj,xn,nd,xq,gwmc,sqdwmc,xyrs,xyknsrs,sqsj,gzsj,gznr,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from "
				+ tableName + " a ";
		String[] outputValue = { "gwmc||sqsj", "xn", "nd", "xqmc", "gwmc",
				"sqdwmc", "xyrs", "xyknsrs", "sqsj", "gzsj", "gznr" };
		String whereSql = getWhereSql(model).toString();

		return rsToVator(sql + whereSql, value != null ? value
				.toArray(new String[0]) : new String[] {}, outputValue);
	}

	/**
	 * 获取校外岗位信息列表
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwList() {
		String sql = "select distinct gwmc, gwmc||sqsj gwpk from qgzx_xwgwxxb where gzjssj>to_char(sysdate,'yyyymmdd') ";
		return getList(sql, new String[] {}, new String[] { "gwmc", "gwpk" });
	}
	
	/**
	 * 获取校外岗位信息列表
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwByOld() {
		String sql = "select distinct gwmc, gwmc||sqsj gwpk from qgzx_xwgwxxb  ";
		return getList(sql, new String[] {}, new String[] { "gwmc", "gwpk" });
	}

	/**
	 * 获取学生基本信息
	 * 
	 * @param xh
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> time = new HashMap<String, String>();
		String sfpks = "";
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,lxdh,zzmm zzmmdm ,ssbh,(select jjzk from view_xsjtxx b where a.xh=b.xh)jjqk from view_xsxxb a where xh=?";
		map = getMap(sql, new String[] { xh }, new String[] { "xh", "xm", "xb","zzmmdm",
				"nj", "xymc", "zymc", "bjmc", "lxdh","ssbh", "jjqk" });

		sql = "select xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from gwsqsjb a";
		time = getMap(sql, new String[] {}, new String[] { "xn", "nd", "xq","xqmc" });

		sfpks = isKns(xh) == true ? "是" : "否";
		map.put("sfpks", sfpks);
		map.put("xn", time.get("xn"));
		map.put("nd", time.get("nd"));
		map.put("xq", time.get("xq"));
		map.put("xqmc", time.get("xqmc"));
		return map;
	}

	/**
	 * 判断学生是否被列人黑名单
	 * 
	 * @param xh
	 * @return boolean
	 */
	public boolean isHmdMember(String xh) {
		String result = "";
		String sql = "select count(*)num from qgzx_zgdzdx_hmdb where xh=?";

		result = getOneRs(sql, new String[] { xh }, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;

		return Integer.parseInt(result) > 0 ? true : false;
	}

	/**
	 * 根据主键查询岗位信息
	 * 
	 * @param pk
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwmap(String pk) {
		String sql = "select gwmc,sqsj from qgzx_xwgwxxb where gwmc||sqsj=?";
		return getMap(sql, new String[] { pk }, new String[] { "gwmc", "sqsj" });
	}

	/**
	 * 根据主键查询岗位信息
	 * 
	 * @param pk
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwInfo(String pk) {
		String sql = "select gwdm gwmc,gwsbsj from view_gwxx where gwdm||'-'||gwsbsj=?";
		return getMap(sql, new String[] { pk },
				new String[] { "gwmc", "gwsbsj" });
	}

	/**
	 * 学生岗位信息是否存在
	 * 
	 * @param xh
	 * @param gwpk
	 * @return boolean
	 */
	public boolean xwgwsqExists(String xh, String gwpk) {
		String sql = "select count(*)num from xsxwgwxxb where xh=? and gwmc||gwsqsj=?";
		String result = getOneRs(sql, new String[] { xh, gwpk }, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;

		return Integer.parseInt(result) > 0 ? true : false;
	}

	/**
	 * 学生岗位申请结果查询
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> getXwGwsq(QgzxZgdzdxForm model) {
		String sql = "select xn||nd||xq||xh||gwmc||gwsqsj 主键,xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xh,xm,xymc,bjmc,gwmc,xxsh,zpsj,zpdz from view_xsxwgwxxb a ";
		String whereSql = getWhereSql(model).toString();
		String[] outputValue = { "主键", "xn", "nd", "xqmc", "xh", "xm", "xymc",
				"bjmc", "gwmc", "xxsh", "zpsj", "zpdz" };

		return rsToVator(sql + whereSql, value != null ? value .toArray(new String[0]) : new String[] {}, outputValue);
	}

	/**
	 * 根据主键获取学生申请岗位的信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwsqInfo(String pkValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh,xm,xymc,zymc,bjmc,nj,xb,gwmc||gwsqsj gwmc,gwsqsj,xn,nd,xq,lxdh,kcjqgzxsj,sqly,bz,xxsh from view_xsxwgwxxb where xn||nd||xq||xh||gwmc||gwsqsj=?";
		map = getMap(sql, new String[] { DealString.toGBK(pkValue) },
				new String[] { "xh", "xm", "xymc", "zymc", "bjmc", "nj", "xb",
						"gwmc", "gwsqsj", "xn", "nd", "xq", "lxdh",
						"kcjqgzxsj", "sqly", "bz", "xxsh" });

		map.put("sfpks", isKns(map.get("xh")) == true ? "是" : "否");
		return map;
	}

	/**
	 * 查询学校审核结果
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> getXwGwsqSh(QgzxZgdzdxForm model) {
		String sql = "select case xxsh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color,xn||nd||xq||xh||gwmc||gwsqsj 主键,"
				+ "xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xh,xm,xymc,bjmc,gwmc,xxsh from view_xsxwgwxxb a ";
		String whereSql = getWhereSql(model).toString();
		String[] outputValue = { "color", "主键", "xn", "nd", "xqmc", "xh", "xm",
				"xymc", "bjmc", "gwmc", "xxsh" };

		return rsToVator(sql + whereSql + " order by xxsh",
				value != null ? value.toArray(new String[0]) : new String[] {},
				outputValue);
	}

	public HashMap<String, String> getGwsqshInfo(String pkValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh,xm,xb,xymc,zymc,bjmc,nj,gwmc,gwsqsj,xn,nd,xq,kcjqgzxsj,lxdh,sqly,bz,xxsh,zpsj,zpdz from view_xsxwgwxxb where xn||nd||xq||xh||gwmc||gwsqsj=?";
		map = getMap(sql, new String[] { DealString.toGBK(pkValue) },
				new String[] { "xh", "xm", "xb", "xymc", "zymc", "bjmc", "nj",
						"gwmc", "gwsqsj", "xn", "nd", "xq", "kcjqgzxsj",
						"lxdh", "sqly", "bz", "zpsj", "zpdz", "xxsh" });
		return map;
	}

	/**
	 * 查询校外岗位详细信息
	 * 
	 * @return pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwxxDetail(String pkValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		pkValue = DealString.toGBK(pkValue);
		String sql = "select * from qgzx_xwgwxxb where gwmc||sqsj=?";
		String[] outputValue = { "xn", "nd", "xq", "gwmc", "sqsj", "sqdwmc",
				"sqdwdz", "gzkssj", "gzjssj", "xyrs", "xyknsrs", "jcfs",
				"jcbz", "gzsj", "gznr", "gzyq", "lxdh", "bz" };
		map = getMap(sql, new String[] { pkValue }, outputValue);

		sql = "select count(*) num from view_xsxwgwxxb where xxsh='通过' and gwmc||gwsqsj=?";
		map.put("shtgrs", getOneRs(sql, new String[] { pkValue }, "num"));

		sql = "select count(*) num from view_xsxwgwxxb where xxsh='未审核' and gwmc||gwsqsj=?";
		map.put("wshrs", getOneRs(sql, new String[] { pkValue }, "num"));
		return map;
	}

	/**
	 * 查询学院人数分配人数
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> getQgzxxy(QgzxZgdzdxForm model) {
		// 获取学年学期
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String xxdm = StandardOperation.getXxdm();
		String xmdm = model.getXmdm();
		String[] value = new String[] { xn, nd, xq };	
		String[] output = new String[] { "pk", "xn", "nd", "xqmc", "xymc", "fprs"};
		if(!StringUtils.isNull(xmdm)){
			String[] xmArr = model.getXmdm().split("-");
			model.setGwdm(xmArr[0]);
			model.setGwsbsj(xmArr[1]);
		}

		String sql = "select xydm||'!!'||xn||'!!'||nd||'!!'||xq pk, a.* from (select distinct xydm, xymc, '"
				+ xn
				+ "' xn,'"
				+ nd
				+ "' nd,'"
				+ xq
				+ "' xq ,(select xqmc from xqdzb where xqdm='"+xq+"')xqmc,(select fprs from qgzx_xyrsb d where d.xn=? and d.nd=? and d.xq=? and d.xydm=a.xydm)fprs from view_njxyzybj a)a ";
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) || Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			//广州大学学院人数分配精确到岗位
			sql = "select gwdm||'!!'||gwsbsj||'!!'||xydm||'!!'||xn||'!!'||nd||'!!'||xq pk, a.* from (select distinct xydm, xymc, '"
				+ xn
				+ "' xn,'"
				+ nd
				+ "' nd,'"
				+ xq
				+ "' xq ,(select xqmc from xqdzb where xqdm='" + xq + "') xqmc,'"
				+ model.getGwdm() + "'gwdm,'"
				+ model.getGwsbsj() + "'gwsbsj,(select fprs from qgzx_xyrsb d where d.xn=? and d.nd=? and d.xq=? and d.gwdm=? and d.gwsbsj=? and d.xydm=a.xydm)fprs from view_njxyzybj a )a";
			
//			if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
//				sql += " where exists (select 1 from qgzx_xyrsb b where a.xn = b.xn and a.xq = b.xq "
//						+ "and a.nd = b.nd and a.xydm = b.xydm and b.fprs is not null and b.fprs > 0)";
//			}
			value = new String[] { xn, nd, xq, model.getGwdm(), model.getGwsbsj()};
			output = new String[] { "pk", "xn", "nd", "xqmc","gwdm", "xymc", "fprs"};
		}
		return rsToVator(sql, value, output);
	}

	/**
	 * 根据部门名称获取代码
	 * 
	 * @param xymc
	 * @return String
	 */
	public String getXydm(String xymc) {
		return getOneRs("select distinct bmdm from zxbz_xxbmdm where bmmc=?",
				new String[] { xymc }, "bmdm");
	}

	/**
	 * 判断人数是否已经分配
	 * 
	 * @param xn
	 * @param nd
	 * @param xq
	 * @param xydm
	 * @return boolean
	 */
	public boolean distributeExists(String xn, String nd, String xq, String xydm) {
		String sql = "select count(*) num from qgzx_xyrsb where xn=? and nd=? and xq=? and xydm=?";
		String result = getOneRs(sql, new String[] { xn, nd, xq, xydm }, "num");
		result = result == null || result.equalsIgnoreCase("") ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}

	/**
	 * 勤工助学申请是否存在
	 * 
	 * @param model
	 * @return boolean
	 */
	public boolean isExistsQgzxsq(QgzxZgdzdxForm model) {
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String sql = "select count(*) num from qgzxsqb where xn=? and nd=? and xq=? and xh=?";
		String result = getOneRs(sql, new String[] { xn, nd, xq, xh }, "num");
		result = result == null || result.equalsIgnoreCase("") ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}

	/**
	 * 查询勤工助学申请信息
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> selectQgzxsqshInfo(QgzxZgdzdxForm model) {
		String xxdm = StandardOperation.getXxdm();
		String userType = model.getUserType();
		String sql = "select case xysh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color,xh||xn||nd||xq 主键, xn,nd,xq,xqmc,xh,xm,xymc,bjmc,gwxzmc,lxdh,xysh,xxsh from view_qgzxsqb ";
		String[] input = { "color", "主键", "xn", "nd", "xqmc", "xh", "xm", "xymc", "bjmc", "lxdh", "xysh","xxsh" };
		String whereSql = getWhereSql(model).toString();
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			//-------2010/5/14 edit by luojw --------
			input = new String[]{ "color", "主键","disabled", "xn", "nd", "xqmc", "xh", "xm", "xymc", "bjmc", "gwxzmc", "lxdh", "xysh", "xxsh" };
			sql = "select case xysh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color,xh||xn||nd||xq||gwxzdm 主键, case xxsh when '通过' then 'disabled' when '不通过' then 'disabled' else ''end disabled, xn,nd,xq,xqmc,xh,xm,xymc,bjmc,gwxzmc,lxdh,xysh,xxsh from (select * from view_qgzxsqb where gwxzmc like '%校内%') ";
			if (userType != null
					&& ("xx".equalsIgnoreCase(userType) || "admin"
							.equalsIgnoreCase(userType))) {
				String xyQuery="";
				//校内岗位需要2级审核
				if("001".equalsIgnoreCase(model.getGwxzdm())){
					xyQuery=" and xysh='通过' ";
				}
				sql = "select ''disabled,case xxsh when '通过' then '#99CCFF' when '不通过' "
						+ "then '#FF9999' else '#FFFFFF' end color,xh||xn||nd||xq||gwxzdm 主键, "
						+ "xn,nd,xq,xqmc,xh,xm,xymc,bjmc,gwxzmc,lxdh,xysh,xxsh from (select * "
						+ "from view_qgzxsqb where gwxzmc like '校外' union all select * from "
						+ "view_qgzxsqb where gwxzmc like '%校内%' "+xyQuery+" union all "
						+ "select * from view_qgzxsqb where gwxzmc like '临时岗位') ";
			}
			// -------end --------
		}else{
			if (userType != null
					&& ("xx".equalsIgnoreCase(userType) || "admin"
							.equalsIgnoreCase(userType))) {
				sql = "select case xxsh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color,xh||xn||nd||xq 主键, xn,nd,xq,xqmc,xh,xm,xymc,bjmc,gwxzmc,lxdh,xysh,xxsh from view_qgzxsqb ";
				// whereSql += " and xysh='通过'";
			}
		}
		//=======2011.1.10 edit by lr 分页=========
		Pages pages = model.getPages();
		String[] inputV = value != null ? value.toArray(new String[0]) : new String[] {};
		//总记录数
		int count = Integer.parseInt(getOneRs("select count(*) count from (" +sql+whereSql+")a", inputV, "count"));
		pages.setMaxRecord(count);
		//查询记录
		sql = StringUtils.joinStr("select a.* from (select a.*,rownum r from (", sql, whereSql, ")a)a where r>",pages.getStart()+""," and r<=",(pages.getStart()+pages.getPageSize())+"");
		//=========end===============
		//System.out.println(sql + whereSql);
		return rsToVator(sql, inputV, input);
	}
	
	/**
	 * 查询勤工助学申请信息
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> selectQgzxsqb(QgzxZgdzdxForm model) {
		String xxdm = StandardOperation.getXxdm();
		String userType = model.getUserType();
		String whereSql = getWhereSql(model).toString();
		String sql = "select * from (select case xysh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color,rownum r,xh||xn||nd||xq 主键, xn,nd,xq,xqmc,xh,xm,xymc,bjmc,gwxzmc,lxdh,xysh,xxsh from view_qgzxsqb " + whereSql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize()) ;
		String[] input = { "color", "主键", "xn", "nd", "xqmc", "xh", "xm", "xymc", "bjmc", "lxdh", "xxsh" };
		StringBuilder sb=new StringBuilder();
		String queryGks="";
		if (userType != null
				&& ("xx".equalsIgnoreCase(userType) || "admin"
						.equalsIgnoreCase(userType))) {
			sql = "select * from (select case xxsh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color,rownum r,xh||xn||nd||xq||gwxzdm 主键, xn,nd,xq,xqmc,xh,xm,xymc,bjmc,gwxzmc,lxdh,xysh,xxsh from view_qgzxsqb " + whereSql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize()) ;
			// whereSql += " and xysh='通过'";
		}
		
		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			input = new String[]{ "color", "主键", "xn", "nd", "xqmc", "xh", "xm", "xymc", "bjmc", "lxdh", "xysh","xxsh" };
		}
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			sb.append(sql);
			//是否有违纪处分情况
			if("01".equalsIgnoreCase(model.getSfwj())){
				whereSql+=" and exists (select 1 from (select a.xh,b.shbm from wjcfb a,cflbdmb b  where a.cflb=b.cflbdm and shbm='校审' and a.xxsh='通过')b where a.xh=b.xh )  ";
			}else if("02".equalsIgnoreCase(model.getSfwj())){
				whereSql+=" and not exists (select 1 from (select a.xh,b.shbm from wjcfb a,cflbdmb b  where a.cflb=b.cflbdm and shbm='校审' and a.xxsh='通过')b where a.xh=b.xh)  ";
			}
			//挂科情况
			if("01".equalsIgnoreCase(model.getPdfh())){
				whereSql+="and  ((select count(b.xh) gkcs from cjb b where cj < 60 and a.xh = b.xh  ) > '"+model.getGkms()+"')";
			}else if("02".equalsIgnoreCase(model.getPdfh())){
				whereSql+="and  ((select count(b.xh) gkcs from cjb b where cj < 60 and a.xh = b.xh  ) = '"+model.getGkms()+"')";
			}
			input = new String[]{ "color", "主键", "xn", "nd", "xqmc", "xh", "xm", "xymc", "bjmc", "gwxzmc", "lxdh", "xysh", "xxsh" };
			sql = "select * from (select case xysh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color,rownum r,xh||xn||nd||xq||gwxzdm 主键, xn,nd,xq,xqmc,xh,xm,xymc,bjmc,gwxzmc,lxdh,xysh,xxsh from view_qgzxsqb a " + whereSql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize()) ;
		}
		String num = getOneRs("select count(*) num from view_qgzxsqb a " + whereSql, value != null ? value.toArray(new String[0]) : new String[] {}, "num");
		num = StringUtils.isNull(num) ? "0" : num;
		model.getPages().setMaxRecord(Integer.parseInt(num) );
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[] {}, input);
	}
	
	
	/**
	 * 获取勤工助学申请详细信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxsqInfo(QgzxZgdzdxForm model) {
		String pkValue = model.getPkValue();
		String userType = model.getUserType();
		String xxdm = StandardOperation.getXxdm();
		String xydm = model.getXydm();
		String[]outPut=null;
		String sql = "select xh,xb,xymc,zymc,bjmc,xm,xn,nd,xq,xqmc,lxdh,kcjqgzxsj,sqly,bz,xysh yesNo,(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh,yhtc,(select zzmmdm from qgzxsqb b where a.xh||a.xn||a.nd||a.xq=b.xh||b.xn||b.nd||b.xq)zzmmdm,kcsgz,jjqk,gwxzdm,gwxzmc,zdgwdm,zdgwdmgwsbsj from view_qgzxsqb a where xh||xn||nd||xq=?";
		// ------------------2010/5/14 edit by luojw -----------------------
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			sql = "select xh,xb,xymc,zymc,bjmc,xm,xn,nd,xq,xqmc,lxdh,kcjqgzxsj,sqly,bz,xysh yesNo,xxsh,"
					+ "(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh,yhtc,"
					+ "(select zzmmdm from qgzxsqb b where a.xh||a.xn||a.nd||a.xq=b.xh||b.xn||b.nd||b.xq and rownum = 1)zzmmdm,"
					+ "kcsgz,jjqk,gwxzdm,gwxzmc,zdgwdm,zdgwdmgwsbsj from view_qgzxsqb a where xh||xn||nd||xq||gwxzdm=? and rownum = 1";
			outPut= new String[] { "xh", "xb",
						"xymc", "zymc", "bjmc", "xm", "xn", "nd", "xq", "xqmc", "lxdh",
						"kcjqgzxsj", "sqly", "bz", "yesNo", "yhtc", "zzmmdm", "ssbh",
						"kcsgz", "jjqk", "gwxzdm", "gwxzmc","xxsh", "zdgwdm", "zdgwdmgwsbsj" };
		}
		// -----------------end-------------------------
		if (userType != null
				&& ("xx".equalsIgnoreCase(userType) || "admin"
						.equalsIgnoreCase(userType))) {
			// ------------------2010/5/14 edit by luojw -----------------------
			if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				sql = "select xh,xb,xymc,zymc,bjmc,xm,xn,nd,xq,xqmc,lxdh,kcjqgzxsj,sqly,bz,xxsh yesNo,"
						+ "(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh,yhtc,"
						+ "(select zzmmdm from qgzxsqb b where a.xh||a.xn||a.nd||a.xq=b.xh||b.xn||b.nd||b.xq and rownum = 1)zzmmdm,"
						+ "kcsgz,jjqk,gwxzdm,gwxzmc,zdgwdm,zdgwdmgwsbsj from view_qgzxsqb a where xh||xn||nd||xq||gwxzdm=? and rownum = 1";

			} else {
				sql = "select xh,xb,xymc,zymc,bjmc,xm,xn,nd,xq,xqmc,lxdh,kcjqgzxsj,sqly,bz,xxsh yesNo,(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh,yhtc,(select zzmmdm from qgzxsqb b where a.xh||a.xn||a.nd||a.xq=b.xh||b.xn||b.nd||b.xq)zzmmdm,kcsgz,jjqk,gwxzdm,gwxzmc,zdgwdm,zdgwdmgwsbsj from view_qgzxsqb a where xh||xn||nd||xq=?";
			}
			// -----------------end-------------------------
			outPut=new String[] { "xh", "xb",
					"xymc", "zymc", "bjmc", "xm", "xn", "nd", "xq", "xqmc", "lxdh",
					"kcjqgzxsj", "sqly", "bz", "yesNo", "yhtc", "zzmmdm", "ssbh",
					"kcsgz", "jjqk", "gwxzdm", "gwxzmc", "zdgwdm", "zdgwdmgwsbsj" };
		}else{
			sql = "select xh,xb,xymc,zymc,bjmc,xm,xn,nd,xq,xqmc,lxdh,kcjqgzxsj,sqly,bz,xxsh yesNo,"
					+ "(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh,yhtc,(select zzmmdm "
					+ "from qgzxsqb b where a.xh||a.xn||a.nd||a.xq=b.xh||b.xn||b.nd||b.xq)zzmmdm,"
					+ "kcsgz,jjqk,gwxzdm,gwxzmc,zdgwdm,zdgwdmgwsbsj from view_qgzxsqb a where xh||xn||nd||xq=? "
					+ " and xydm = '" + xydm + "'";

			outPut = new String[] { "xh", "xb", "xymc", "zymc", "bjmc", "xm",
					"xn", "nd", "xq", "xqmc", "lxdh", "kcjqgzxsj", "sqly",
					"bz", "yesNo", "yhtc", "zzmmdm", "ssbh", "kcsgz", "jjqk",
					"gwxzdm", "gwxzmc", "zdgwdm", "zdgwdmgwsbsj" };
		}
		return getMap(sql, new String[] { pkValue }, outPut);
	}
	
	/**
	 * 获取勤工助学申请详细信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 * @author luo
	 */
	public HashMap<String, String> getQgzxsqZjfp(QgzxZgdzdxForm model) {
		String pkValue = model.getPkValue();
		String userType = model.getUserType();
		String sql = "select xh,xb,xymc,zymc,bjmc,xm,xn,nd,xq,lxdh,kcjqgzxsj,sqly,bz,xysh yesNo,(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh,yhtc,(select zzmmdm from qgzxsqb b where a.xh||a.xn||a.nd||a.xq=b.xh||b.xn||b.nd||b.xq)zzmmdm from view_qgzxsqb a where xh=?";
		if (userType != null
				&& ("xx".equalsIgnoreCase(userType) || "admin"
						.equalsIgnoreCase(userType))) {
			sql = "select xh,xb,xymc,zymc,bjmc,xm,xn,nd,xq,lxdh,kcjqgzxsj,sqly,bz,xxsh yesNo,(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh,yhtc,(select zzmmdm from qgzxsqb b where a.xh||a.xn||a.nd||a.xq=b.xh||b.xn||b.nd||b.xq)zzmmdm from view_qgzxsqb a where xh=?";
		}
		return getMap(sql, new String[] { pkValue }, new String[] { "xh", "xb",
				"xymc", "zymc", "bjmc", "xm", "xn", "nd", "xq", "lxdh",
				"kcjqgzxsj", "sqly", "bz", "yesNo", "yhtc","zzmmdm", "ssbh" });
	}

	/**
	 * 判断是否在申请时间内
	 * 
	 * @return boolean
	 */
	public boolean checkTime() throws SQLException {
		// 判断是否在设置的允许申请时间范围内 tag 为empty不在申请时间内
		String sql = "select * from gwsqsjb where kssj<to_char(sysdate,'yyyymmddhh24miss') and jssj>to_char(sysdate,'yyyymmddhh24miss')";

		String tag = returntag(sql, new String[] {});
		if (tag != null && tag.equalsIgnoreCase("empty")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断学生是否一通过学院推荐
	 * 
	 * @param xh
	 * @return boolean
	 */
	public boolean checkStuPass(String xh) {
		String sql = "select count(*) num from qgzxsqb where xh=? and xn=? and nd=? and xq=? and xysh='通过'";
		String result = getOneRs(sql, new String[] { xh, Base.currXn,
				Base.currNd, Base.currXq }, "num");
		result = result == null || result.equalsIgnoreCase("") ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}

	/**
	 * 获取岗位信息列表
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getDWRGwList(String gwxzdm) {
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx where shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd') and gwxz = ?";
		List<HashMap<String, String>> gwList = getList(sql, new String[] {gwxzdm}, new String[] { "gwdm", "gwdmgwsbsj" });
		return gwList;
	}
	
	/**
	 * 获取岗位信息列表
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getDWRGwGzdxList(String gwxzdm,
			String xydm) {
		//TODO 剔除临时岗位
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a "
				+ "where shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd') and gwxz = ?"
				+ " and exists(select 1 from qgzx_xyrsb b where a.gwdm = b.gwdm "
				+ "and a.gwsbsj = b.gwsbsj and b.fprs is not null and b.fprs > 0 and b.xydm = ?)";
		System.out.println(sql);
		List<HashMap<String, String>> gwList = getList(sql, new String[] {
				gwxzdm, xydm }, new String[] { "gwdm", "gwdmgwsbsj" });
		return gwList;
	}
	
	/**
	 * 获取岗位信息列表 
	 * @return List
	 */
	public List<HashMap<String, String>> getGwList(String userName) {
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx where shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd')";
		String xxdm = StandardOperation.getXxdm();
		if(checkExists("yrdwdmb", "dlm", userName)){
			sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd') and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='"+userName+"')";
		}
		List<HashMap<String, String>> gwList = getList(sql, new String[] {}, new String[] { "gwdm", "gwdmgwsbsj" });
		return gwList;
	}
	
	/**
	 * 获取岗位信息列表
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getGwList() {
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx where shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd')";
		List<HashMap<String, String>> gwList = getList(sql, new String[] {}, new String[] { "gwdm", "gwdmgwsbsj" });
		return gwList;
	}
	
	/**
	 * 获取岗位信息列表 
	 * @return List
	 */
	public List<HashMap<String, String>> getRsfpgwList(String userName,String gwxz,HttpServletRequest request) {
		
		String sql = "";
		
		String xxdm = StandardOperation.getXxdm();
		//广州大学
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			String userType = (String) request.getSession().getAttribute("userType");
			String userDep = (String) request.getSession().getAttribute("userDep");
			
			sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd') and gwxzmc='"
					+ gwxz + "'";
			
			if ("xy".equalsIgnoreCase(userType)) {
				sql += " and exists(select 1 from qgzx_xyrsb b where a.gwdm = b.gwdm and a.gwsbsj = b.gwsbsj and b.fprs is not null and b.fprs > 0 and b.xydm = '"
						+ userDep + "')";
			}
			if (checkExists("yrdwdmb", "dlm", userName)) {
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd') and gwxzmc='"
						+ gwxz
						+ "' and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='"
						+ userName + "')";
			}
		} else {
			sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx where shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd') and gwxzmc='"
					+ gwxz + "'";
			if (checkExists("yrdwdmb", "dlm", userName)) {
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd') and gwxzmc='"
						+ gwxz
						+ "' and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='"
						+ userName + "')";
			}
		}
		System.out.println(sql);
		List<HashMap<String, String>> gwList = getList(sql, new String[] {}, new String[] { "gwdm", "gwdmgwsbsj" });
		return gwList;
	}
	
	/**
	 * 获取岗位信息列表 
	 * @return List
	 */
	public List<HashMap<String, String>> getRsfpgwListOfNbtyxy(String userName) {
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx where shjg='通过' and gzjsrq>=to_char(sysdate,'yyyymmdd')";
		if(checkExists("yrdwdmb", "dlm", userName)){
			sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where shjg='通过' and gzjsrq>=to_char(sysdate,'yyyymmdd') and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='"+userName+"')";
		}
		List<HashMap<String, String>> gwList = getList(sql, new String[] {}, new String[] { "gwdm", "gwdmgwsbsj" });
		return gwList;
	}
	
	
	/**
	 * 获取已安排人数列表
	 * 
	 * @return List
	 * @author luo
	 */
	public List<HashMap<String, String>> getGwApList() {
		String sql = "select count(*) num,gwdm||'-'||gwsbsj gwdmgwsbsj from view_xsgwxx where xyyj='通过' group by gwdm|| '-' ||gwsbsj";
		List<HashMap<String, String>> gwList = getList(sql, new String[] {}, new String[] { "num", "gwdmgwsbsj" });
		return gwList;
	}

	/**
	 * 获取审批使用人数列表
	 * 
	 * @return List<HashMap<String, String>>
	 * @author luo
	 */
	public List<HashMap<String, String>> getGwSqList() {

		String sql = "select sqsyrs, gwdm ||'-'|| gwsbsj gwdmgwsbsj from view_gwxx";

		List<HashMap<String, String>> gwList = getList(sql, new String[] {}, new String[] { "sqsyrs",
				"gwdmgwsbsj" });
		return gwList;
	}

	/**
	 * 判断岗位申请是否存在
	 * 
	 * @param xh
	 * @param gwPk
	 * @return boolean
	 */
	public boolean gwsqExists(String xh, String gwPk) {
		String sql = "select count(*) num from view_xsgwxx where xh=? and gwdm||'-'||gwsbsj=?";
		String result = getOneRs(sql, new String[] { xh, gwPk }, "num");
		result = result == null || result.equalsIgnoreCase("") ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}

	/**
	 * 判断记录是否存在
	 * 
	 * @param String
	 *            tableName
	 * @param String
	 *            pk
	 * @param String
	 *            pkValue
	 * @return boolean
	 */
	public boolean checkExists(String tableName, String pk, String pkValue) {
		String sql = "select count(*) num from " + tableName + " where " + pk
				+ "=?";
		String result = getOneRs(sql, new String[] { pkValue }, "num");
		result = result == null || result.equalsIgnoreCase("") ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}

	/**
	 * 勤工助学之星申请结果查询
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> qgzxzxSearch(QgzxZgdzdxForm model) {
		String sql = "select xh||xn||nd||xq 主键, xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xh,xm,bjmc,sqsj,yrdwsh,xysh,xxsh from view_qgzxzx a ";
		String whereSql = getWhereSql(model).toString();
		String[] outputValue = { "主键", "xn", "nd", "xqmc", "xh", "xm", "bjmc",
				"sqsj", "yrdwsh", "xysh", "xxsh" };
		return rsToVator(sql + whereSql, value != null ? value
				.toArray(new String[0]) : new String[] {}, outputValue);
	}

	/**
	 * 查询勤工助学之星申请详细信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxzxInfo(String pkValue) {
		String sql = "select * from view_qgzxzx where xh||xn||nd||xq=?";
		return getMap(sql, new String[] { pkValue }, new String[] { "xh", "xm",
				"xb", "bjmc", "xn", "nd", "xq", "zwjd" });
	}

	/**
	 * 勤工助学之星审核查询
	 * 
	 * @param model
	 * @param userType
	 * @param userName
	 * @return List
	 */
	public List<String[]> getQgzxzxSh(QgzxZgdzdxForm model, String userType,
			String userName) {
		String condition = " ";
		String sql = "";
		String whereSql = getWhereSql(model).toString();
		String[] outputValue = { "color", "主键", "xn", "nd", "xqmc", "xh", "xm",
				"bjmc", "sqsj", "yrdwsh", "xysh", "xxsh" };
		// 判断用户类型
		String dlm = getOneRs("select dlm from yrdwdmb where dlm=?",
				new String[] { userName }, "dlm");
		if (dlm != null && !dlm.equalsIgnoreCase("")) {
			condition = " and exists(select 1 from view_xsgwxx b where a.xh=b.xh and b.xxyj='通过' and exists(select 1 from yrdwdmb c where b.yrdwdm=c.yrdwdm and c.dlm='" + userName + "'))";
			sql = "select xh||xn||nd||xq 主键,case yrdwsh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color, xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xh,xm,nj,bjmc,sqsj,yrdwsh,xysh,xxsh from view_qgzxzx a ";
			
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				condition = " and yrdwsh = '通过'";
				sql = "select xh||xn||nd||xq 主键,case xysh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color, xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xh,xm,nj,bjmc,sqsj,yrdwsh,xysh,xxsh from view_qgzxzx a ";
			} else {
				condition = " and xysh = '通过' and yrdwsh='通过'";
				sql = "select xh||xn||nd||xq 主键,case xxsh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end color, xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xh,xm,nj,bjmc,sqsj,yrdwsh,xysh,xxsh from view_qgzxzx a ";
			}
		}
		System.out.println(sql + whereSql + condition);
		return rsToVator(sql + whereSql + condition, value != null ? value.toArray(new String[0]) : new String[] {}, outputValue);
	}

	/**
	 * 查询勤工助学之星审核详细信息
	 * 
	 * @param pkValue
	 * @param userType
	 * @param userName
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxzxShInfo(String pkValue,
			String userType, String userName) {
		String sql = "";
		String[] outputValue = { "主键", "xn", "nd", "xq", "xqmc", "xh", "xm", "bjmc",
				"sqsj", "yesNo", "zwjd", "szbmyj", "xyyj", "xxyj" };

		// 判断用户类型
		String dlm = getOneRs("select dlm from yrdwdmb where dlm=?",
				new String[] { userName }, "dlm");
		if (dlm != null && !dlm.equalsIgnoreCase("")) {
			sql = "select xh||xn||nd||xq 主键,xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xh,xm,nj,bjmc,sqsj,zwjd,yrdwsh yesNo,szbmyj,xyyj,xxyj from view_qgzxzx a ";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select xh||xn||nd||xq 主键,xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xh,xm,nj,bjmc,sqsj,zwjd,xysh yesNo,szbmyj,xyyj,xxyj from view_qgzxzx a ";
			} else {
				sql = "select xh||xn||nd||xq 主键,xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xh,xm,nj,bjmc,sqsj,zwjd,xxsh yesNo,szbmyj,xyyj,xxyj from view_qgzxzx a ";
			}
		}

		return getMap(sql + " where xh||xn||nd||xq=?",
				new String[] { pkValue }, outputValue);
	}

	/**
	 * 获取当前月份
	 * 
	 * @return String
	 */
	public String currYf() {
		String yf = getOneRs(
				"select substr(to_char(sysdate,'yyyy-mm-dd'),6,2)yf from dual",
				new String[] {}, "yf");
		return yf;
	}

	/**
	 * 获取酬金发放信息
	 * 
	 * @param nd
	 * @param yf
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getWorkPayMap(String nd, String yf) {
		String sql = "select count(*)count,sum(cjje)zje ,sum(gzsj)gzxs from view_xscjff where nd=? and yf=? and xxsh='通过'";
		return getMap(sql, new String[] { nd, yf }, new String[] { "count",
				"zje", "gzxs" });
	}

	/***************************************************************************
	 * 获取酬金信息
	 * 
	 * @param nd
	 * @param yf
	 * @return List
	 **************************************************************************/
	public List<HashMap<String, String>> getCjffInfo(String nd, String yf) {
		String sql = "select yrdwmc dwmc,yrdwmc bmmc,bjmc,bjdm,xh,xm,cjje,gzsj,(select lxdh from view_xsjbxx b where a.xh=b.xh)lxdh from view_xscjff a where nd=? and yf=? and xxsh='通过' order by bjdm";
		return getList(sql, new String[] { nd, yf }, new String[] { "dwmc",
				"bmmc", "bjmc", "xh", "xm", "cjje", "gzsj", "lxdh" });
	}

	/**
	 * 检测是否在校外学生申请岗位时间之内
	 * 
	 * @return boolean
	 */
	public boolean checkOutTime() {
		boolean flag = true;
		String sql = "select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') time from dual";
		String result = getOneRs(sql, new String[] {}, "time");

		result = result.replaceAll("-", "");
		result = result.replaceAll(":", "");
		result = result.replaceAll(" ", "");

		sql = "select xwkssj, xwjssj from gwsqsjb";
		String[] values = getOneRs(sql, new String[] {}, new String[] {
				"xwkssj", "xwjssj" });

		values[0] = values[0] == null || "".equalsIgnoreCase(values[0]) ? "0"
				: values[0];
		values[1] = values[1] == null || "".equalsIgnoreCase(values[1]) ? "0"
				: values[1];

		flag = Double.parseDouble(result) < Double.parseDouble(values[0])
				|| Double.parseDouble(result) > Double.parseDouble(values[1]) ? false
				: true;
		return flag;
	}

	/**
	 * 检测用户是否是用人单位用户
	 * 
	 * @param userName
	 * @return boolean
	 */
	public boolean isYrdw(String userName) {
		// boolean flag = false;
		String sql = "select count(*)count from yrdwdmb where dlm=?";
		return Integer.parseInt(getOneRs(sql, new String[] { userName },
				"count")) > 0 ? true : false;
	}

	public boolean checkPostCount(QgzxZgdzdxForm model) {
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String xh = model.getXh();
		String userType = model.getUserType();
		String rs = "0";
		String tgrs = "0";
		String sql = "select fprs from qgzx_xyrsb a where xn=? and nd=? and xq=? and exists(select 1 from view_xsjbxx b where a.xydm=b.xydm and b.xh=?)";
		rs = getOneRs(sql, new String[] { xn, nd, xq, xh }, "fprs");

		sql = "select count(*)num from view_qgzxsqb a where xn=? and nd=? and xq=? and xysh='通过' and xxsh<>'不通过' and exists(select 1 from view_xsjbxx b where a.xydm=b.xydm and b.xh=?)";

		if (userType != null
				&& ("xx".equalsIgnoreCase(userType) || "admin"
						.equalsIgnoreCase(userType))) {
			sql = "select count(*)num from view_qgzxsqb a where xn=? and nd=? and xq=? and xxsh='通过' and exists(select 1 from view_xsjbxx b where a.xydm=b.xydm and b.xh=?) and xh <> '"+xh+"'";
		}
		tgrs = getOneRs(sql, new String[] { xn, nd, xq, xh }, "num");

		rs = rs == null || "".equalsIgnoreCase(rs) ? "0" : rs;
		tgrs = tgrs == null || "".equalsIgnoreCase(tgrs) ? "0" : tgrs;

		return Integer.parseInt(tgrs) >= Integer.parseInt(rs) ? true : false;
	}
	
	/**
	 * 广州大学检测学院人数分配是否已经超过限制
	 * */
	public boolean checkPostCountByGzdx(QgzxZgdzdxForm model){
	String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String xh = model.getXh();
		String gwdm = model.getGwdm();
		String gwsbsj = model.getGwsbsj();
		
		String rs = "0";
		String tgrs = "0";
		String sql = "select fprs from qgzx_xyrsb a where xn=? and nd=? and xq=? and gwdm=? and gwsbsj=? and exists(select 1 from view_xsjbxx b where a.xydm=b.xydm and b.xh=?)";
		rs = getOneRs(sql, new String[] { xn, nd, xq, gwdm,  gwsbsj, xh}, "fprs");

		sql = "select count(*)num from view_xsgwxx a where xn=? and nd=? and xq=? and gwdm=? and gwsbsj=? and sfyx='有效' and exists(select 1 from view_xsjbxx b where a.xydm=b.xydm and b.xh=?)";
		tgrs = getOneRs(sql, new String[] { xn, nd, xq, gwdm, gwsbsj,xh}, "num");//通过人数

		rs = rs == null || "".equalsIgnoreCase(rs) ? "0" : rs;
		tgrs = tgrs == null || "".equalsIgnoreCase(tgrs) ? "0" : tgrs;

		return Integer.parseInt(tgrs)+model.getPlczrs() > Integer.parseInt(rs) ? true : false;
	}
	/**
	 * 获取用人单位列表
	 * 
	 * @return List
	 */
	public List getYrdwList() {
		String sql = "select distinct yrdwdm, yrdwmc from yrdwdmb";
		return getList(sql, new String[] {},
				new String[] { "yrdwdm", "yrdwmc" });
	}

	/**
	 * 判断用户是否属于用人单位用户
	 * 
	 * @param userName
	 * @return boolean
	 */
	public String checkUserIsYrdw(String userName) {
		String sql = "select yrdwdm from yrdwdmb where dlm=?";
		String result = getOneRs(sql, new String[] { userName }, "yrdwdm");

		return result;
	}

	public String getTableSql(QgzxZgdzdxForm model) {
//		String yrdwdm = model.getYrdwdm();
//		String userType = model.getUserType();
		String sql = "";
		// ================ begin 骆嘉伟 2009/3/25 ==============
//		if (userType != null && userType.equalsIgnoreCase("yrdw")) {
//			sql = "select xh 主键, xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,nd,xq,sfygw 是否有岗位,gwdm 岗位名称 ,gwdm,gwsbsj,ssbh,zzmmmc,lxdh,gwzydj,yrdwdm from("
//					+ "select xh,xn,nd,xq,sfygw,(select xm from view_xsjbxx b where a.xh=b.xh)xm,(select nj from view_xsjbxx b where a.xh=b.xh)nj,"
//					+ "(select xb from view_xsjbxx b where a.xh=b.xh)xb,(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,"
//					+ "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,(select zydm from view_xsjbxx b where a.xh=b.xh)zydm,"
//					+ "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,(select bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,"
//					+ "(select ssbh from view_xsjbxx b where a.xh = b.xh) ssbh,(select zzmmmc from view_xsxxb b where a.xh = b.xh) zzmmmc,(select lxdh from view_qgzxsqb b where a.xh = b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq) lxdh,"
//					+ "(select bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,decode(sfygw,'有岗',(select gwdm from view_xsgwxx b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and b.xyyj='通过'),"
//					+ "(select gwdm from view_xsgwxx b where a.xh = b.xh and a.xn = b.xn  and a.xq = b.xq and a.nd = b.nd and b.xxyj = '通过'))gwdm,"
//					+ "decode(sfygw,'有岗',(select gwsbsj from view_xsgwxx b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and b.xyyj='通过'),"
//					+ "(select gwsbsj from view_xsgwxx b where a.xh = b.xh and a.xn = b.xn  and a.xq = b.xq and a.nd = b.nd and b.xxyj = '通过'))gwsbsj,"
//					+ " (select gwzydj from view_xsgwxx b where a.xh = b.xh and a.xn = b.xn  and a.xq = b.xq and a.nd = b.nd and b.xxyj = '通过') gwzydj,"
//					+ "(select yrdwdm from view_xsgwxx b where a.xh = b.xh and a.xn = b.xn and a.xq = b.xq and a.nd = b.nd and b.xxyj = '通过') yrdwdm from ("
//					+ "select a.xh,a.xn,a.nd,a.xq ,'无岗' sfygw from qgzxsqb a where a.xxsh='通过' and not exists (select 1 from xsgwxxb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.xyyj='通过')"
//					+ " union  all "
//					+ "select a.xh,a.xn,a.nd,a.xq,'有岗' sfygw from qgzxsqb a where a.xxsh='通过' and exists (select 1 from view_xsgwxx b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq and b.xyyj='通过' and b.yrdwdm='"
//					+ yrdwdm + "')" + ")a where sfygw='无岗'" + ") ";
//		} else if (userType != null
//				&& ("admin".equalsIgnoreCase(userType) || "xx"
//						.equalsIgnoreCase(userType))) {
//          原始sql
//			sql = "SELECT xh 主键, xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,nd,xq,sfygw 是否有岗位,gwdm 岗位名称,gwdm,gwsbsj,ssbh,zzmmmc,lxdh,gwzydj,yrdwdm FROM("
//					+ "SELECT xh,xn,nd,xq,sfygw,(SELECT xm FROM view_xsjbxx b WHERE a.xh=b.xh)xm,(SELECT nj FROM view_xsjbxx b WHERE a.xh=b.xh)nj,"
//					+ "(SELECT xb FROM view_xsjbxx b WHERE a.xh=b.xh)xb,(SELECT xydm FROM view_xsjbxx b WHERE a.xh=b.xh)xydm,"
//					+ "(SELECT xymc FROM view_xsjbxx b WHERE a.xh=b.xh)xymc,(SELECT zydm FROM view_xsjbxx b WHERE a.xh=b.xh)zydm,"
//					+ "(SELECT zymc FROM view_xsjbxx b WHERE a.xh=b.xh)zymc,(SELECT bjdm FROM view_xsjbxx b WHERE a.xh=b.xh)bjdm,"
//					+ "(SELECT ssbh FROM view_xsjbxx b WHERE a.xh = b.xh) ssbh,(SELECT zzmmmc FROM view_xsxxb b WHERE a.xh = b.xh) zzmmmc,(SELECT lxdh FROM view_qgzxsqb b WHERE a.xh = b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq) lxdh,"
//					+ "(SELECT bjmc FROM view_xsjbxx b WHERE a.xh=b.xh)bjmc,decode(sfygw,'有岗',(SELECT gwdm FROM view_xsgwxx b WHERE a.xh=b.xh AND a.xn=b.xn AND a.xq=b.xq AND a.nd=b.nd and b.xxyj='通过'),"
//					+ "(SELECT gwdm FROM view_xsgwxx b WHERE a.xh = b.xh AND a.xn = b.xn  AND a.xq = b.xq AND a.nd = b.nd and b.xxyj = '通过'))gwdm,"
//					+ "decode(sfygw,'有岗',(SELECT gwsbsj FROM view_xsgwxx b WHERE a.xh=b.xh AND a.xn=b.xn AND a.xq=b.xq AND a.nd=b.nd and b.xxyj='通过'),"
//					+ "(SELECT gwsbsj FROM view_xsgwxx b WHERE a.xh = b.xh AND a.xn = b.xn  AND a.xq = b.xq AND a.nd = b.nd and b.xxyj = '通过'))gwsbsj,"
//					+ " (SELECT gwzydj FROM view_xsgwxx b WHERE a.xh = b.xh AND a.xn = b.xn  AND a.xq = b.xq AND a.nd = b.nd and b.xxyj = '通过') gwzydj,"
//					+ "(SELECT yrdwdm FROM view_xsgwxx b WHERE a.xh = b.xh AND a.xn = b.xn  AND a.xq = b.xq AND a.nd = b.nd and b.xxyj = '通过') yrdwdm FROM ("
//					+ "SELECT a.xh,a.xn,a.nd,a.xq ,'无岗' sfygw FROM qgzxsqb a WHERE NOT EXISTS (SELECT 1 FROM xsgwxxb b WHERE a.xh=b.xh AND a.xn=b.xn AND a.xq=b.xq AND b.xxyj='通过') and xxsh='通过'"
//					+ " UNION  ALL "
//					+ "SELECT a.xh,a.xn,a.nd,a.xq,'有岗' sfygw FROM qgzxsqb a WHERE EXISTS (SELECT 1 FROM view_xsgwxx b WHERE a.xh=b.xh AND a.xn=b.xn AND a.nd=b.nd AND a.xq=b.xq AND b.xxyj='通过') and xxsh='通过'"
//					+ ")a" + ")";
			sql = StringUtils.joinStr("select a.*, gwdm 岗位名称, sfygw 是否有岗位,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from (",
										 "select xh 主键 ,xh ,xn,nd,xq,lxdh,",
					                     "(SELECT xm FROM view_xsjbxx b WHERE a.xh = b.xh) xm,",
					                     "(SELECT nj FROM view_xsjbxx b WHERE a.xh = b.xh) nj,",
					                     "(SELECT xb FROM view_xsjbxx b WHERE a.xh = b.xh) xb,",
					                     "(SELECT xydm FROM view_xsjbxx b WHERE a.xh = b.xh) xydm,",
					                     "(SELECT xymc FROM view_xsjbxx b WHERE a.xh = b.xh) xymc,",
					                     "(SELECT zydm FROM view_xsjbxx b WHERE a.xh = b.xh) zydm,",
					                     "(SELECT zymc FROM view_xsjbxx b WHERE a.xh = b.xh) zymc,",
					                     "(SELECT bjmc FROM view_xsjbxx b WHERE a.xh = b.xh) bjmc,",
					                     "(SELECT bjdm FROM view_xsjbxx b WHERE a.xh = b.xh) bjdm,",
					                     "(SELECT ssbh FROM view_xsjbxx b WHERE a.xh = b.xh) ssbh,",
					                     "(SELECT zzmmmc FROM view_stu_details b WHERE a.xh = b.xh) zzmmmc,",
					                     "gwdm,gwsbsj,",
					                     "(select yrdwdm from xsgwxxb b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj)yrdwdm,",
					                     "(select gwzydj from xsgwxxb b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj)gwzydj",
					                     ",decode(num,1,'有岗',0,'无岗','')sfygw from (",
					                     "select xh ,xn,nd,xq,lxdh,",
					                     "(select gwdm from xsgwxxb b where a.xn=b.xn and a.xh=b.xh and a.nd=b.nd and a.xq=b.xq and xxyj='通过' and rownum=1) gwdm,",
					                     "(select gwsbsj from xsgwxxb b where a.xn=b.xn and a.xh=b.xh and a.nd=b.nd and a.xq=b.xq and xxyj='通过' and rownum=1) gwsbsj,",
					                     "(select count(*) from xsgwxxb b where a.xn=b.xn and a.xh=b.xh and a.nd=b.nd and a.xq=b.xq and xxyj='通过')num",
					                     " from qgzxsqb a where xxsh='通过'",
					                     ") a ", 
					                     ")a ");
//		}
		// ================ end 骆嘉伟 2009/3/25 ==============
		return sql;
	}

	/**
	 * 岗位直接分配用人单位用户查询学生信息
	 * 
	 * @param model
	 * @return List
	 */
	public List selectGwzjfpYrdwList(QgzxZgdzdxForm model) {
		// ================ begin 骆嘉伟 2009/3/25 ==============
		String[] outputValue = { "主键", "xh", "xn", "nd", "xq", "xm", "xb",
				"nj", "xymc", "bjmc", "是否有岗位", "岗位名称", "ssbh", "zzmmmc", "lxdh","gwzydj" };
		// ================ end 骆嘉伟 2009/3/25 ==============
		model.setUserType("yrdw");
		String sql = getTableSql(model);
		String whereSql = getWhereSql(model).toString();
		return rsToVator(sql + whereSql, value != null ? value
				.toArray(new String[0]) : new String[] {}, outputValue);
	}

	/**
	 * 岗位直接分配用人单位用户查询学生信息
	 * 
	 * @param model
	 * @return List
	 */
	public List selectGwzjfpXxList(QgzxZgdzdxForm model) {
		value = new ArrayList<String>();
		// ================ begin 骆嘉伟 2009/3/25 ==============
		String[] outputValue = { "主键", "xn", "nd", "xqmc", "xh", "xm", "xb",
				                 "nj", "xymc", "bjmc", "是否有岗位", "岗位名称", 
				                 "ssbh", "zzmmmc", "lxdh","gwzydj" };
		// ================ end 骆嘉伟 2009/3/25 ==============
		model.setUserType("xx");

		String sql = getTableSql(model);
		String whereSql = getGwmcWhereSql(model).toString();
		System.out.println("select * from (select a.*,rownum r from (" + sql + whereSql + ") a)" + " where r<="
				+ (model.getPages().getStart() + model
						.getPages().getPageSize()) + " and r> ");
		return rsToVator("select * from (select a.*,rownum r from (" + sql + whereSql + ") a)" + " where r<="
				+ (model.getPages().getStart() + model
						.getPages().getPageSize()) + " and r> "
				+ model.getPages().getStart(), value != null ? value .toArray(new String[0]) : new String[] {}, outputValue);
	}
	
	/**
	 * 岗位直接分配用人单位用户查询学生信息总记录数
	 * 
	 * @param model
	 * @return List
	 */
	public int selectGwzjfpXxNum(QgzxZgdzdxForm model) {
		value = new ArrayList<String>();
		// ================ begin 骆嘉伟 2009/3/25 ==============
		String[] outputValue = { "主键", "xn", "nd", "xqmc", "xh", "xm", "xb",
				                 "nj", "xymc", "bjmc", "是否有岗位", "岗位名称", 
				                 "ssbh", "zzmmmc", "lxdh","gwzydj" };
		// ================ end 骆嘉伟 2009/3/25 ==============
		model.setUserType("xx");

		String sql = getTableSql(model);
		String whereSql = getWhereSql(model).toString();
		return rsToVator(sql + whereSql, value != null ? value .toArray(new String[0]) : new String[] {}, outputValue).size();
	}

	/**
	 * 用人单位可分配的岗位
	 * 
	 * @param yrdwdm
	 * @return List
	 */
	public List getYrdwKfpgw(String yrdwdm) {
		List rs = null;
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx where SHJG='通过' and gzjsrq>to_char(sysdate,'yyyymmdd') and sqdw=?";
		rs = getList(sql, new String[] { yrdwdm }, new String[] { "gwdm",
				"gwdmgwsbsj" });
		return rs;
	}

	/**
	 * 获取参数设置中的参数值
	 * 
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwsqsjInfo() {
		String sql = "select * from gwsqsjb";
		String[] outputValue = { "xn", "nd", "xq", "kssj", "jssj", "knsbl",
				"mxsbc", "mtzgxs", "myzgxs", "myzgbc", "xwkssj" };

		return getMap(sql, new String[] {}, outputValue);
	}

	/**
	 * 获取岗位剩余人数
	 * 
	 * @param model
	 * @return int
	 */
	public int getGwSyrs(QgzxZgdzdxForm model) {
		String sql = "select sqsyrs from gwxxb where gwdm||'-'||gwsbsj=?";
		String spsyrs = getOneRs(sql, new String[] { model.getGwdm() },
				"sqsyrs");
		spsyrs = spsyrs == null || "".equalsIgnoreCase(spsyrs) ? "0" : spsyrs;

		sql = "select count(*)num from view_xsgwxx where gwdm||'-'||gwsbsj=? and xxyj='通过'";
		String num = getOneRs(sql, new String[] { model.getGwdm() }, "num");
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;

		return Integer.parseInt(spsyrs) - Integer.parseInt(num);
	}

	/**
	 * 检测通过的人数是否已经大于分配的人数
	 * 
	 * @param pkValue
	 * @param userType
	 * @return boolean
	 */
	public boolean checkPostNumber(String pkValue, String userType) {
		String sql = "select count(*) num from view_qgzxsqb a where exists(select 1 from view_qgzxsqb b where a.xydm=b.xydm and a.xn=b.xn and a.xq=b.xq and b.nd=b.nd and b.xh||b.xn||b.nd||b.xq=?)  and xysh='通过' and xxsh<>'不通过'";
		if (userType != null
				&& ("admin".equalsIgnoreCase(userType) || "xx"
						.equalsIgnoreCase(userType))) {
			sql = "select count(*) num from view_qgzxsqb a where exists(select 1 from view_qgzxsqb b where a.xydm=b.xydm and a.xn=b.xn and a.xq=b.xq and b.nd=b.nd and b.xh||b.xn||b.nd||b.xq=?) and xxsh='通过' and a.xh||a.xn||a.nd||a.xq<>'"+pkValue+"'";
		}

		String num = getOneRs(sql, new String[] { pkValue }, "num");
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;

		sql = "select fprs from qgzx_xyrsb a where xn||nd||xq||xydm=(select xn||nd||xq||xydm from view_qgzxsqb where xh||xn||nd||xq=?)";
		String fprs = getOneRs(sql, new String[] { pkValue }, "fprs");
		fprs = fprs == null || "".equalsIgnoreCase(fprs) ? "0" : fprs;

		return Integer.parseInt(fprs) - Integer.parseInt(num) > 0 ? true : false;
	}

	/**
	 * 根据勤工助学申请主键查询学生信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxsqStuInfo(String pkValue) {
		String sql = "select xh,xm,xn,nd,xq ,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xymc from view_qgzxsqb a where xh||xn||nd||xq=?";
		return getMap(sql, new String[] { pkValue }, new String[] { "xh", "xm",
				"xn", "nd", "xq", "xymc", "xqmc" });
	}
	
	/**
	 * 查询单个学院人数分配信息
	 * @param QgzxZgdzdxForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXyrsfpxx(QgzxZgdzdxForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String gwdm = model.getGwdm();
		String gwsbsj = model.getGwsbsj();
		StringBuffer sb = new StringBuffer();
		if(!StringUtils.isNull(xn)){
			sb.append(" and xn='");
			sb.append(xn);
			sb.append("'");
		}
		if(!StringUtils.isNull(nd)){
			sb.append(" and nd='");
			sb.append(nd);
			sb.append("'");
		}
		if(!StringUtils.isNull(xq)){
			sb.append(" and xq='");
			sb.append(xq);
			sb.append("'");
		}
		if(!StringUtils.isNull(xydm)){
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("'");
		}
		if(!StringUtils.isNull(gwdm)){
			sb.append(" and gwdm='");
			sb.append(gwdm);
			sb.append("'");
		}
		if(!StringUtils.isNull(gwsbsj)){
			sb.append(" and gwsbsj='");
			sb.append(gwsbsj);
			sb.append("'");
		}
		String sql = "select a.* ,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm)xymc from (select nvl(xn,'"+xn+"')xn,nvl(nd,'"+nd+"')nd,nvl(xq,'"+xq+"')xq,nvl(xydm,'"+xydm+"')xydm,nvl(fprs,'')fprs,nvl(gwdm,'"+gwdm+"')gwdm,nvl(gwsbsj,'"+gwsbsj+"')gwsbsj from (select * from qgzx_xyrsb where 1=1 " + sb.toString()+ "))a";
		map = getMap(sql, new String[]{}, new String[]{"xn","nd","xq","xqmc","xydm","xymc","fprs","gwdm","gwsbsj"});
		if(map == null || map.size()<1){
			sql = "select (select xqmc from xqdzb  where xqdm=?) xqmc,(select distinct xymc from view_njxyzybj where xydm=?) xymc from dual";
			map = getMap(sql, new String[]{xq,xydm}, new String[]{"xqmc","xymc"});
			map.put("xn", xn);
			map.put("nd", nd);
			map.put("xq", xq);
			map.put("xydm", xydm);
			map.put("gwdm", gwdm);
			map.put("gwsbsj", gwsbsj);
		}
		return map;
	}
	
	/**
	 * 根据主键查询勤工助学申请信息
	 * @param QgzxZgdzdxForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectQgzxsqInfoOne(QgzxZgdzdxForm model){
		String[] outputValue = {"bjdm","bjmc","bz","gwxzdm","gwxzmc","jjqk","kcjqgzxsj","kcsgz","lxdh","nd","nj","sqly","xb","xh","xm","xn","xq","xqmc","xxsh","xydm","xymc","xysh","yhtc","zydm","zymc","ssbh","zzmmdm"};
		String sql = "select a.*,(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh from view_qgzxsqb a where xh||xn||nd||xq=?";
		
		String xxdm = StandardOperation.getXxdm();
		//广州大学
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			sql = "select a.*,(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh from view_qgzxsqb a where xh||xn||nd||xq||gwxzdm=?";
		}
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * 删除勤工助学申请信息
	 * @param QgzxZgdzdxForm model
	 * @return boolean
	 * */
	public boolean deleteQgzxsq(QgzxZgdzdxForm model){
		boolean result = true;
		String[] value = model.getCbv();
		String[] sqlArr = new String[value.length];
		
		String xxdm = Base.xxdm;
		for(int i=0; i<value.length; i++){
			// --------------2010/6/10 edit by luojw ---------------
			if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				sqlArr[i] = "delete from qgzxsqb where xh||xn||nd||xq||gwxzdm ='" + value[i] + "'";
			} else {
				sqlArr[i] = "delete from qgzxsqb where xh||xn||nd||xq||gwxzdm ='" + value[i] + "'";
			}
			// --------------enf ---------------
			
		}
		try{
			runBatch(sqlArr);
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * 查询勤工助学申请信息导出
	 * */
	public List<String[]> selectXsqgzxsqForExp(QgzxZgdzdxForm model, String[] input){
		String sql = "select * from view_qgzxsqb " + getWhereSql(model);
		return rsToVator(sql, value != null ? value .toArray(new String[0]) : new String[] {}, input);
	}	
	
	/**
	 * 获得所申请岗位剩余人数
	 * 
	 * @author luojw
	 */
	public String getGwsyrs(QgzxZgdzdxForm model) {

		DAO dao = DAO.getInstance();

		String xn = model.getXn();// 学年
		String nd = model.getNd();// 年度
		String xq = model.getXq();// 学期
		String gwdm = model.getGwdm();// 岗位代码
		String gwsbsj = model.getGwsbsj();// 岗位申报时间
		String xydm = model.getXydm();// 岗位代码
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.sqsyrs, nvl(b.fprs, 0) fprs from ( select * from ");
		sql.append("gwxxb a where a.gwdm = ? and a.gwsbsj = ?) a ");
		sql.append("left join (select b.gwdm, b.gwsbsj, sum(fprs) fprs ");
		sql.append("from qgzx_xyrsb b where b.gwdm = ? and b.gwsbsj = ? ");
		sql.append("and xn =? and xq=? and nd = ? and xydm <> ? group by b.gwdm, ");
		sql.append("b.gwsbsj) b on a.gwdm = b.gwdm and a.gwsbsj = b.gwsbsj ");

		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
				gwdm, gwsbsj,gwdm, gwsbsj, xn, xq, nd,xydm },
				new String[] { "sqsyrs", "fprs" });

		String sqsyrs = Base.isNull(map.get("sqsyrs")) ? "0" : map.get("sqsyrs");// 岗位人数上限
		String fprs = Base.isNull(map.get("fprs")) ? "0" : map.get("fprs");//分配总人数
		String syrs = "0";
		
		if (Integer.parseInt(sqsyrs) > Integer.parseInt(fprs)) {
			syrs = String.valueOf(Integer.parseInt(sqsyrs) - Integer.parseInt(fprs));
		}
		
		return syrs;
	}
	
	/**
	 * 获得临时岗位列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getLsgwList() {
		
		DAO dao = DAO.getInstance();

		String sql = "select gwdm dm,gwdm mc from view_gwxx where gzjsrq > to_char(sysdate,'yyyymmdd') and gwxzmc = '临时岗位'";
		
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 获得勤工助学申请信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getQgzxsqInfoList(String[] pkValue) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();

		sql.append(" select * from qgzxsqb ");

		if (pkValue != null && pkValue.length > 0) {
			for (int i = 0; i < pkValue.length; i++) {
				if (i == 0) {
					sql.append("where xh||xn||nd||xq||gwxzdm= ?");
				} else {
					sql.append("or xh||xn||nd||xq||gwxzdm= ?");
				}
			}
		}

		return dao.getList(sql.toString(), pkValue, new String[] { "xh", "xn",
				"xq", "nd", "lxdh", "kcjqgzxsj", "sqly", "bz", "zzmmdm",
				"yhtc", "kcsgz", "jjqk" });
	}
	
	/**
	 * 获得岗位申请信息
	 * 
	 * @author luojw
	 */
	public HashMap<String,String> getGwsqXx(String pk) {
		
		DAO dao = DAO.getInstance();

		String sql = "select * from xsgwxxb where xh||gwdm||gwsbsj = ?";
		
		return dao.getMap(sql, new String[]{pk}, new String[]{"xyyj","xxyj"});
	}
	
	public HashMap<String, String> qgzxzsPrint(HashMap<String, String> rs) {
		DAO dao = DAO.getInstance();
		return dao.getMapNotOut("select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.zzmmmc," +
				"a.mzmc,a.xb,a.lxdh||'/'||a.sjhm lxdh,b.yrdwmc from view_xsxxb a left join view_xsgwxx b " +
				"on a.xh=b.xh where b.xh=? and b.xn=? and b.nd=? and b.xq=?", new String[]{rs.get("xh"), rs.get("xn"), rs.get("nd"), rs.get("xq")});
	}
	
	/**
	 * 广州大学
	 * 学生
	 * 上学年旷课次数
	 * qlj 2010.9.14
	 */
	public List<HashMap<String,String>> checkGks(String xh){
		DAO dao =DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("select count(*)sxnkks from cjb a where a.xh=? and  cj<60 and kcxz not like '%选修%' and exists ");
		sql.append(" (select * from xtszb where a.xn=to_char(to_number(substr(dqxn,0,4))-1)||'-'||to_char(to_number(substr(dqxn,6,9))-1))");
		return dao.getList(sql.toString(),new String[]{xh}, new String[]{"sxnkks"});
	}
	
	/**
	 * 广州大学
	 * 学生
	 * 在校违纪处分情况
	 * qlj 2010.9.14
	 */
	public List<HashMap<String,String>>checkWjqk(String xh){
		DAO dao=DAO.getInstance();
		String sql="select count(*)wjcfcs from wjcfb where xh=? and xxsh='通过'";
		return dao.getList(sql, new String[]{xh}, new String[]{"wjcfcs"});
	}
	
	/**
	 * 广州大学
	 * 获取辅导员姓名
	 * qlj 2010.9.14
	 */
	public List<HashMap<String,String>>getFdyXm(String userName){
		DAO dao=DAO.getInstance();
		String sql="select xm from yhb where yhm=?";
		return dao.getList(sql, new String[]{userName}, new String[]{"xm"});
	}
	
	
}
