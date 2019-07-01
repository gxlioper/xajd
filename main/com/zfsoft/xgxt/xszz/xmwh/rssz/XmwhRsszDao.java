/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.rssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhDao;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护-人数设置
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhRsszDao extends SuperDAOImpl<XmwhRsszForm> {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhRsszForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = null;
		String cxb="(select *  FROM VIEW_XSJBXX where pycc = (select pycc from XG_XSZZ_NEW_ZZXMDMB where xmdm = '"+model.getXmdm()+"'))";
		String knsSqzq=MessageUtil.getText("xszz.knsrd.sqzq");//困难生申请周期
		if("0".equals(model.getXslb())){
			if("10335".equals(Base.xxdm)){
				cxb=" ( select a.xh,a.xn,max(to_number(decode(a.xq,'on','03',a.xq))),b.xydm,b.nj,b.zydm,b.bjdm from view_knsjgb_fqxrd a left join VIEW_XSJBXX b on a.xh=b.xh ";
				cxb+=" group by a.xh,a.xn,b.xydm, b.nj, b.zydm, b.bjdm ) ";
			}else{
				cxb="(select  a.xh,a.xn,max(to_number(decode(a.xq,'on','03',a.xq))),b.xydm,b.nj,b.zydm,b.bjdm from XG_XSZZ_NEW_KNSJGB a left join VIEW_XSJBXX b on a.xh=b.xh where a.xn='"+model.getPdxn()+"' ";
				if("xq".equals(knsSqzq)&&!"on".equals(model.getPdxq())){
					cxb+="and a.xq='"+model.getPdxq()+"'";
				}
				cxb+="  group by  a.xh,a.xn,b.xydm, b.nj, b.zydm, b.bjdm )";
			}
		}
		
		String rskzfw = model.getRskzfw();
		sql = new StringBuilder("SELECT * FROM (SELECT  M.*,N.BL,N.GUID,N.ZZRS,(CASE WHEN N.ZZRS IS NULL THEN '0' ELSE '1' END) SFYSZ");
		if (rskzfw != null) {
			if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
				sql.append(" FROM (SELECT DISTINCT A.*,B.XYMC,B.ZYMC,B.BJMC ");
				sql.append(" FROM (SELECT XYDM,NJ,ZYDM,BJDM,COUNT(*) ZRS ");
				sql.append(" FROM "+cxb+" ");
				sql
						.append(" GROUP BY XYDM,NJ,ZYDM,BJDM  HAVING XYDM IS NOT NULL AND NJ IS NOT NULL AND ZYDM IS NOT NULL AND BJDM IS NOT NULL) A ");
				sql
						.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.BJDM=B.BJDM) M  ");
				sql
						.append(" LEFT JOIN XG_XSZZ_NEW_ZZXMRSSZB N ON M.BJDM=N.BMDM  AND M.NJ=N.NJ");
			} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级+学院
				sql
						.append("  FROM (SELECT DISTINCT A.*,B.XYMC FROM (SELECT XYDM,NJ,COUNT(*) ZRS FROM "+cxb+"  GROUP BY XYDM,NJ  HAVING XYDM IS NOT NULL AND NJ IS NOT NULL) A ");
				sql
						.append("LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.XYDM=B.XYDM ) M   ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_ZZXMRSSZB N ON M.XYDM=N.BMDM AND M.NJ=N.NJ");

			} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级 + 专业
				sql.append(" FROM (SELECT DISTINCT A.*,B.XYMC,B.ZYMC ");
				sql.append(" FROM (");
				sql.append(" SELECT XYDM,ZYDM,NJ,COUNT(*) ZRS ");
				sql.append(" FROM "+cxb+"  ");
				sql
						.append(" GROUP BY XYDM,ZYDM,NJ HAVING XYDM IS NOT NULL AND NJ IS NOT NULL AND ZYDM IS NOT NULL) A ");
				sql
						.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON  A.ZYDM=B.ZYDM ) M  ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_ZZXMRSSZB N ON M.ZYDM=N.BMDM  AND M.NJ=N.NJ");
			} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
				sql
						.append("  FROM (SELECT DISTINCT A.*,B.XYMC FROM (SELECT XYDM,COUNT(*) ZRS FROM "+cxb+" ");

				String rskznj = new XmwhDao().getRskznj(model.getXmdm());// 通过项目代码获取已经设置的年级,年级以逗号分割,针对学院情况
				if (rskznj != null && !rskznj.trim().equals("")) {
					sql.append(" where NJ in(");
					sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
					sql.append(")");
				}
				sql.append("  GROUP BY XYDM  HAVING XYDM IS NOT NULL) A ");
				sql
						.append("LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.XYDM=B.XYDM ) M   ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_ZZXMRSSZB N ON M.XYDM=N.BMDM ");
			} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
				sql.append(" FROM (SELECT DISTINCT A.*,B.XYMC,B.ZYMC ");
				sql.append(" FROM (");
				sql.append(" SELECT XYDM,ZYDM,COUNT(*) ZRS ");
				sql.append(" FROM "+cxb+"  ");
				sql
						.append(" GROUP BY XYDM,ZYDM HAVING XYDM IS NOT NULL  AND ZYDM IS NOT NULL) A ");
				sql
						.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON  A.ZYDM=B.ZYDM ) M  ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_ZZXMRSSZB N ON M.ZYDM=N.BMDM ");
			} else if(rskzfw.equalsIgnoreCase(Constants.RSKZFW_SY)){//书院
				sql.append(" FROM (SELECT DISTINCT A.*,B.SYMC XYMC ");
				sql.append(" FROM (");
				sql.append(" SELECT Y.SYDM XYDM,COUNT(*) ZRS ");
				sql.append(" FROM "+cxb+" X ");
				sql.append(" LEFT JOIN XG_XTWH_SYBJGLB Y ON X.bjdm = Y.BJDM ");
				sql
						.append(" GROUP BY Y.SYDM HAVING Y.SYDM IS NOT NULL ) A ");
				sql
						.append(" LEFT JOIN XG_XTWH_SYDMB B ON  A.XYDM=B.SYDM ) M  ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_ZZXMRSSZB N ON M.XYDM=N.BMDM ");
			}
		}

		sql.append(" AND N.XMDM='");
		sql.append(model.getXmdm());
		sql.append("' AND N.XN='");
		sql.append(model.getXn());
		sql.append("' AND N.XQ='");
		sql.append(model.getXq());
		sql.append("'");
		sql.append(")");
		sql.append(" where 1=1 ");
		
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append(" AND SFYSZ='"+model.getSfysz()+"'");
		}
		if (!StringUtil.isNull(model.getNjq())){
			sql.append(" AND NJ='"+model.getNjq()+"'");
		}
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" AND XYDM='"+model.getXydm()+"'");
		}
		if (!StringUtil.isNull(model.getZydm())){
			sql.append(" AND ZYDM='"+model.getZydm()+"'");
		}
		if (!StringUtil.isNull(model.getBjdm())){
			sql.append(" AND BJDM='"+model.getBjdm()+"'");
		}
		
		
		List<HashMap<String, String>> pageList = getPageList(model, sql
				.toString(), params.toArray(new String[] {}));
		return pageList;
	}

	/**
	 * @描述:格式化rskznj，增加''，以便拼接sql语句
	 * @作者：ligl
	 * @日期：2013-7-5 上午09:26:19
	 * @修改记录:
	 * @param rskznj
	 * @return String 返回类型
	 * @throws
	 */
	private String setRskznj(String rskznj) {
		if (rskznj != null) {
			rskznj = rskznj.replaceAll(",", "','");
			rskznj = "'" + rskznj + "'";
		}
		return rskznj;
	}

	/**
	 * 
	 * @描述:高级查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhRsszForm model,
			User user) throws Exception {
		return null;
	}

	/**
	 * 
	 * @描述:比例设置
	 * @作者：ligl
	 * @日期：2013-4-22 下午07:20:02
	 * @修改记录:
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean runBlsz(XmwhRsszForm model, List<XmwhRsszForm> list)
			throws Exception {
		int[] result = null;

		List<String> sqlList = new ArrayList<String>();
		StringBuffer sb = null;
		String guid = null;
		sb = new StringBuffer();
		for (XmwhRsszForm model1 : list) {
			guid = model1.getGuid();
			if (guid != null && !guid.trim().equals("")
					&& !guid.trim().equals("null")) {
				sb = new StringBuffer();
				sb.append("update xg_xszz_new_zzxmrsszb set bl='");
				sb.append(model.getBl());
				sb.append("',zzrs=");
				sb.append(model1.getZzrs());
				sb.append(" where guid='");
				sb.append(guid);
				sb.append("'");
				sqlList.add(sb.toString());
			} else {
				sb = new StringBuffer();
				sb
						.append("insert into xg_xszz_new_zzxmrsszb(bl,bmdm,nj,xmdm,xn,xq,zzrs) ");
				sb.append(" values('");
				sb.append(model.getBl());
				sb.append("','");
				sb.append(model1.getBmdm());
				sb.append("','");
				sb.append(model1.getNj());
				sb.append("','");
				sb.append(model.getXmdm());
				sb.append("','");
				sb.append(model.getXn());
				sb.append("','");
				sb.append(model.getXq());
				sb.append("',");
				sb.append(model1.getZzrs());
				sb.append(")");
				sqlList.add(sb.toString());
			}
		}
		if (sqlList != null && sqlList.size() > 0) {
			String[] sqls = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				sqls[i] = sqlList.get(i);
			}
			result = dao.runBatch(sqls);
		}

		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @描述:比例设置
	 * @作者：ligl
	 * @日期：2013-4-22 下午07:20:02
	 * @修改记录:
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean runBlszAll(XmwhRsszForm model,
			List<HashMap<String, String>> list) throws Exception {
		int[] result = null;
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xszz_new_zzxmrsszb ");
		sb.append(" where xmdm=?");
		String[] input = { model.getXmdm() };
		dao.runDelete(sb.toString(), input);// 删除原记录

		sb = new StringBuffer();
		sb
				.append("insert into xg_xszz_new_zzxmrsszb(bl,bmdm,nj,xmdm,xn,xq,zzrs) ");
		sb.append(" values(?,?,?,?,?,?,?)");

		for (HashMap<String, String> map : list) {
			String[] param = { model.getBl(), map.get("bmdm"), map.get("nj"),
					model.getXmdm(), model.getXn(), model.getXq(),
					map.get("zzrs") };
			paramList.add(param);
		}
		result = dao.runBatch(sb.toString(), paramList);
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @描述:设置最终人数
	 * @作者：ligl
	 * @日期：2013-4-22 下午07:19:43
	 * @修改记录:
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean runZzrs(XmwhRsszForm model) throws Exception {
		int[] result = null;

		StringBuffer sb = null;
		List<String> sqlList = new ArrayList<String>();
		String guid = null;
		String zzrs = null;
		String bl = null;
		String nj = "";
		String bmdm = null;
		String rskzfw = null;

		String[] guids = model.getGuids();
		String[] zzrss = model.getZzrss();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		String[] bjdms = model.getBjdms();
		String[] zydms = model.getZydms();
		String[] bls = model.getBls();

		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				guid = guids[i];
				if (!StringUtil.isNull(guid)) {
					sqlList
							.add("delete from xg_xszz_new_zzxmrsszb where guid='"
									+ guid + "'");
				}
			}
		}

		if (zzrss != null && zzrss.length > 0) {
			for (int i = 0; i < zzrss.length; i++) {
				zzrs = zzrss[i];
				if (zzrs != null) {
					zzrs = zzrs.trim();
				}
				int iZzrs = -1;
				String yzzrs=null;
				try {
					iZzrs = Integer.parseInt(zzrs);
				} catch (Exception e) {
				}
				bl = bls[i];
				if (njs != null) {
					nj = njs[i];
				}
				rskzfw = model.getRskzfw();
				if (rskzfw != null) {
					if (rskzfw.equals(Constants.RSKZFW_BJ)) {
						bmdm = bjdms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {
						bmdm = zydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_XY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {
						bmdm = zydms[i];
					} else if(rskzfw.equals(Constants.RSKZFW_SY)){
						bmdm = xydms[i];
					}
				}
				if(iZzrs!=-1){
					yzzrs=String.valueOf(iZzrs);
				}
				sb = new StringBuffer();
				sb
						.append("insert into xg_xszz_new_zzxmrsszb(bl,bmdm,nj,xmdm,xn,xq,zzrs) ");
				sb.append(" values('" + bl + "','" + bmdm + "','" + nj + "'");
				sb.append(",'" + model.getXmdm() + "','" + model.getXn()
						+ "','" + model.getXq() + "'," + yzzrs + ")");
				sqlList.add(sb.toString());
			}
		}

		if (sqlList != null && sqlList.size() > 0) {
			String[] sqls = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				sqls[i] = sqlList.get(i);
			}
			result = dao.runBatch(sqls);
		}
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @描述:查询已设置人数设置值
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getRssz(String xmdm) throws Exception {
		HashMap<String, String> data = new XmwhDao().getDataById(xmdm);
		StringBuilder sql = new StringBuilder();
		sql
				.append("select a.*,b.rskzfw from xg_xszz_new_zzxmrsszb a,xg_xszz_new_zzxmdmb b  ");
		sql.append(" where a.xmdm=b.xmdm ");
		sql.append(" and a.xmdm=? ");
		sql.append(" and a.xn='");
		sql.append(Base.currXn);
		sql.append("' and a.xq='");
		sql.append(Base.currXq);
		sql.append("'");
		if (data.get("rskzfw").equals(Constants.RSKZFW_XY)) {// 学院
			String rskznj = data.get("rskznj");
			if (rskznj != null && !rskznj.trim().equals("")) {
				sql
						.append(" and a.bmdm in(select distinct xydm from view_njxyzybj_all where nj in(");
				sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
				sql.append("))");
			}
		}
		String[] inputValue = { xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:根据项目代码删除记录
	 * @作者：ligl
	 * @日期：2013-5-2 下午03:03:00
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean runDeleteByXmdm(String xmdm) throws Exception {
		String sql = "delete from xg_xszz_new_zzxmrsszb where xmdm=? ";
		String[] inputValue = { xmdm };
		int result = dao.runDelete(sql, inputValue);
		return result > 0;
	}

	/**
	 * 
	 * @描述:查询已设置的人数设置条数
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public int getRsszCount(String xmdm) throws Exception {
		HashMap<String, String> data = new XmwhDao().getDataById(xmdm);
		int count = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" select  count(*) count from xg_xszz_new_zzxmrsszb a  ");
		sql.append(" where a.zzrs is not null ");
		sql.append(" and a.xmdm=? ");
		sql.append(" and a.xn='");
		sql.append(Base.currXn);
		sql.append("' and a.xq='");
		sql.append(Base.currXq);
		sql.append("'");
		if (data.get("rskzfw").equals(Constants.RSKZFW_XY)) {// 学院
			String rskznj = data.get("rskznj");
			if (rskznj != null && !rskznj.trim().equals("")) {
				sql
						.append(" and a.bmdm in(select distinct xydm from view_njxyzybj_all where nj in(");
				sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
				sql.append("))");
			}
		}
		String[] input = { xmdm };
		String[] output = { "count" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);// ///此方法异常已被捕获掉。，出错无法处理
		// 。。。。。。。////。。。。。。////
		if (oneRs != null && oneRs.length > 0) {
			try {
				count = Integer.parseInt(oneRs[0]);
			} catch (Exception e) {
			}
		}
		return count;
	}

	/**
	 * 
	 * @描述:查询项目已设置人数
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public int getYszrs(XmwhRsszForm model) throws Exception {
		HashMap<String, String> data = new XmwhDao().getDataById(model
				.getXmdm());
		int num = 0;
		StringBuilder sql = new StringBuilder();
		sql
				.append("  select sum(zzrs) num from xg_xszz_new_zzxmrsszb   where xmdm=?  and xn=? and xq=? ");
		if (data.get("rskzfw").equals(Constants.RSKZFW_XY)) {// 学院
			String rskznj = data.get("rskznj");
			if (rskznj != null && !rskznj.trim().equals("")) {
				sql
						.append(" and bmdm in(select distinct xydm from view_njxyzybj_all where nj in(");
				sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
				sql.append("))");
			}
		}
		String[] input = { model.getXmdm(), model.getXn(), model.getXq() };
		String[] output = { "num" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				try {
					num = Integer.parseInt(oneRs[0]);
				} catch (Exception e) {
				}
			}
		}
		return num;
	}

	/**
	 * 
	 * @描述:获取总人数
	 * @作者：ligl
	 * @日期：2013-5-29 上午10:55:23
	 * @修改记录:
	 * @return
	 * @throws Exception int 返回类型
	 * @throws
	 */
	public int getZrs(XmwhRsszForm model, String rskznj) throws Exception {
		String rskzfw = model.getRskzfw();

		int num = 0;
		StringBuilder sql = new StringBuilder();
		String cxb="VIEW_XSJBXX";
		String knsSqzq=MessageUtil.getText("xszz.knsrd.sqzq");//困难生申请周期
		if("0".equals(model.getXslb())){
			
			if("10335".equals(Base.xxdm)){
				cxb=" ( select a.xh,a.xn,max(to_number(decode(a.xq,'on','03',a.xq))),b.xydm,b.nj,b.zydm,b.bjdm from view_knsjgb_fqxrd a left join VIEW_XSJBXX b on a.xh=b.xh ";
				cxb+=" group by a.xh,a.xn,b.xydm, b.nj, b.zydm, b.bjdm ) ";
			}else{
				cxb="(select  a.xh,a.xn,max(to_number(decode(a.xq,'on','03',a.xq))),b.xydm,b.nj,b.zydm,b.bjdm from XG_XSZZ_NEW_KNSJGB a left join VIEW_XSJBXX b on a.xh=b.xh where a.xn='"+model.getPdxn()+"' ";
				if("xq".equals(knsSqzq)&&!"on".equals(model.getPdxq())){
					cxb+="and a.xq='"+model.getPdxq()+"'";
				}
				cxb+="  group by  a.xh,a.xn,b.xydm, b.nj, b.zydm, b.bjdm )";
			}
		}
		if(Constants.RSKZFW_SY.equals(rskzfw)){
			sql.append("select count(*) num from(select a.* from "+cxb+" a left join XG_XTWH_SYBJGLB b on a.bjdm=b.bjdm where b.sydm is not null) ");
		} else {
			sql.append("select count(*) num from "+cxb+" where xydm is not null ");
		}

		if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
			if (rskznj != null && !rskznj.trim().equals("")) {
				sql.append(" and NJ in(");
				sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
				sql.append(")");
			}
		}

		String[] input = {};
		String[] output = { "num" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				try {
					num = Integer.parseInt(oneRs[0]);
				} catch (Exception e) {
				}
			}
		}
		return num;
	}

	/**
	 * 
	 * @描述:根据项目参数，查询最终人数等
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getRsszOne(String xmdm, String xh,
			String rskzfw) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.zzrs,a.xn,a.xq  from (select t1.xn,t1.xq,t1.nj,t1.bmdm,t1.bl,t1.zzrs,t2.xmdm from xg_xszz_new_zzxmrsszb t1 ");
		sb.append(" left join xg_xszz_new_zzxmdmb t2 on t1.xmdm=t2.xmdm and t1.xn=t2.sqxn and t1.xq=t2.sqxq) a,view_xsjbxx b ");
		sb.append(" where 1=1  ");
		if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
			sb.append("  and a.bmdm=b.bjdm ");
		} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级专业
			sb.append("  and a.bmdm=b.zydm ");
			sb.append(" and a.nj=b.nj ");
		} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级学院
			sb.append("  and a.bmdm=b.xydm ");
			sb.append(" and a.nj=b.nj ");
		} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
			sb.append("  and a.bmdm=b.xydm ");
		} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
			sb.append("  and a.bmdm=b.zydm ");
		} else if(rskzfw.equals(Constants.RSKZFW_SY)){ //书院
			sb.append("  and a.bmdm = b.sydm1 ");
		}
 		sb.append("  and a.xmdm=? ");
		sb.append("  and b.xh=? ");
		String[] inputValue = new String[] { xmdm, xh };
		String[] outputValue = new String[] { "zzrs", "xn", "xq" };
		String sql = sb.toString();
		HashMap<String, String> map = dao.getMap(sql, inputValue, outputValue);
		return map;
	}

	/**
	 * 
	 * @描述:查询所有学院
	 * @作者：ligl
	 * @日期：2013-5-28 下午02:48:35
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSyxy() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xydm,xymc from view_njxyzybj_all  ");
		String[] inputValue = {};
		List<HashMap<String, String>> result = dao.getListNotOut(
				sql.toString(), inputValue);
		return result;
	}

	public List<HashMap<String, String>> getSyList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select sydm,symc from XG_XTWH_SYDMB");
		return dao.getListNotOut(sql.toString(),new String[]{});
	}
	/**
	 * 
	 * @描述:通过学院代码取到学院名称
	 * @作者：ligl
	 * @日期：2013-6-9 下午03:42:06
	 * @修改记录:
	 * @param dm
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String getXymc(String dm) throws Exception {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select xymc name from view_njxyzybj_all where xydm=? ");
		String[] input = { dm };
		String[] output = { "name" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				name = oneRs[0];
			}
		}
		return name;
	}

	public String getSymc(String dm)throws Exception{
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select symc name from XG_XTWH_SYDMB where sydm=?");
		String[] input = { dm };
		String[] output = { "name" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				name = oneRs[0];
			}
		}
		return name;
	}
	/**
	 * 
	 * @描述:通过专业代码取到专业名称
	 * @作者：ligl
	 * @日期：2013-6-9 下午03:42:06
	 * @修改记录:
	 * @param dm
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String getZymc(String dm) throws Exception {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select zymc name from view_njxyzybj_all where zydm=? ");
		String[] input = { dm };
		String[] output = { "name" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				name = oneRs[0];
			}
		}
		return name;
	}

	/**
	 * 
	 * @描述:通过班级代码取到班级名称
	 * @作者：ligl
	 * @日期：2013-6-9 下午03:42:06
	 * @修改记录:
	 * @param dm
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String getBjmc(String dm) throws Exception {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select bjmc name from view_njxyzybj_all where bjdm=? ");
		String[] input = { dm };
		String[] output = { "name" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				name = oneRs[0];
			}
		}
		return name;
	}

	/**
	 * 
	 * @描述:获取所有包含学生的年级
	 * @作者：ligl
	 * @日期：2013-7-5 上午08:37:45
	 * @修改记录:
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String> getNj() throws Exception {
		List<String> result = null;
		StringBuilder sql = new StringBuilder();
		sql
				.append("select distinct nj from VIEW_XSJBXX where nj is not null order by nj ");
		String[] inputValue = {};
		result = dao.getList(sql.toString(), inputValue, "nj");
		return result;
	}

	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmrsszb");
		super.setKey("guid");
	}

	/** 
	 * @描述:根据项目代码查询人数分配方式
	 * @作者：cq [工号：785]
	 * @日期：2014-4-22 上午11:49:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public Map<String, String> getRsfpfs(String xmdm) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		StringBuilder sql =  new StringBuilder();
		sql.append(" select rskzfw,decode(rskzfw,'bj','班级','njxy','年级+"+Base.YXPZXY_KEY+"','njzy','年级+专业',");
		sql.append(" 'xy','"+Base.YXPZXY_KEY+"','zy','专业','sy','书院') rskzfwmc from xg_xszz_new_zzxmdmb  where  xmdm = ? ");
		return dao.getMap(sql.toString(), new String[]{xmdm}, new String[]{"rskzfw","rskzfwmc"});
	}

}
