package xgxt.pjpy.whlghxxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyHxxyDAO {

	private static PjpyHxxyDAO mydao = new PjpyHxxyDAO();

	public static PjpyHxxyDAO getInstance() {
		return mydao;
	}

	DAO dao = DAO.getInstance();

	private ArrayList<String> values = new ArrayList<String>();

	/**
	 * 追加查询条件
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(PjpyHxxyModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like '%" + DealString.toGBK(model.getXm())
					+ "%'");

		}
		if (!StringUtils.isNull(model.getJxjdm())) {
			whereSql.append(" and jxjdm = ?");
			values.add(model.getJxjdm());
		}
		if (!StringUtils.isNull(model.getRychdm())) {
			whereSql.append(" and rychdm = ?");
			values.add(model.getRychdm());
		}

		return whereSql;
	}

	/**
	 * 传入英文数组，中文数组，返回查询表头
	 * 
	 * @param enList
	 * @param cnList
	 * @return
	 */
	public List<HashMap<String, String>> getTitleList(String[] enList,
			String[] cnList) {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}

	/**
	 * 查询综合素质结果
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpList(PjpyHxxyModel model, PjpyHxxyActionForm dataSearchForm) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] opCol = new String[] { "pk", "r", "xn", "xh", "xm",
				"bjmc", "dcj", "zcj", "tcj", "mcj", "xf", "jlf", "cff" };
		return dao
				.rsToVator(
						"select * from (select xh||xn pk,rownum r,xh,xm,xn,bjmc,dcj,zcj,tcj,mcj,xf,jlf,cff from view_hxxy_zhszcp where 1=1"
								+ whereSql.toString() + ") where r<="
								+ (dataSearchForm.getPages().getStart() + dataSearchForm
										.getPages().getPageSize()) + " and r> "
								+ dataSearchForm.getPages().getStart(), values != null ? values
								.toArray(new String[0]) : new String[] {},
						opCol);
	}

	public int queryZhszcpListNum(PjpyHxxyModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] opCol = new String[] { "pk", "rownum", "xn", "xh", "xm",
				"bjmc", "dcj", "zcj", "tcj", "mcj", "xf", "jlf", "cff" };
		return dao
				.rsToVator(
						"select xh||xn pk,rownum,xh,xm,xn,bjmc,dcj,zcj,tcj,mcj,xf,jlf,cff from view_hxxy_zhszcp where 1=1"
								+ whereSql.toString(), values != null ? values
								.toArray(new String[0]) : new String[] {},
						opCol).size();
	}

	
	/**
	 * 删除综合素质分
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteZhszf(String[] keys) throws Exception {
		String rs = "";
		String[] sql = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			StringBuffer sb = new StringBuffer(
					"delete from zhszcp where xh||xn='");
			sb.append(keys[i]);
			sb.append("'");
			sql[i] = sb.toString();
		}
		int flag[] = dao.runBatch(sql);
		for (int i = 0; i < flag.length; i++) {
			if (flag[i] == -1) {
				rs += (i + 1) + ",";
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
	}

	/**
	 * 获取个人奖学金评定信息
	 * 
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjpdxx(PjpyHxxyModel model)
			throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = dao
				.getMap(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,mzmc,zzmmmc,csrq,rxrq,lxdh from view_xsxxb where xh=?",
						new String[] { model.getXh() }, new String[] { "xh",
								"xm", "xb", "nj", "xymc", "zymc", "bjmc",
								"mzmc", "zzmmmc", "csrq", "rxrq", "lxdh" });
		
		String[] opList = new String[] { "xf", "xfpm", "zf", "zfpm", "zdcj", "dcj", "jlf"};
		String sql = "select xf,xfpm,zf,zfpm,zdcj,dcj,jlf from View_hxxy_zhszcp c where xh=? and xn=?";
		String[] tempList = dao.getOneRs(sql, new String[] {model.getXh(), model.getXn() }, opList);
		if (tempList != null) {
			for (int i = 0; i < opList.length; i++) {
				resMap.put(opList[i], tempList[i]);
			}
		}

		return resMap;
	}

	/**
	 * 奖学金申请保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(PjpyHxxyModel model, HttpServletRequest request)
			throws Exception {
		boolean bDel = StandardOperation.delete("xsjxjb",
				"xh||xn||xq||nd||jxjdm", model.getXh() + model.getXn()
						+ model.getXq() + model.getNd() + model.getJxjdm(),
				request);
		if (bDel) {
			return StandardOperation.insert("xsjxjb", new String[] { "xh",
					"xn", "xq", "nd", "jxjdm", "wysp", "drzw", "tzjkbzdj",
					"sqly" }, new String[] { model.getXh(), model.getXn(),
					model.getXq(), model.getNd(), model.getJxjdm(),
					DealString.toGBK(model.getWysp()),
					DealString.toGBK(model.getDrzw()),
					DealString.toGBK(model.getTzjkbzdj()),
					DealString.toGBK(model.getSqly()) }, request);
		}
		return false;
	}

	/**
	 * 传入二个数组返回LIST查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String[] enList,
			String[] cnList) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		if (enList != null && cnList != null) {
			for (int i = 0; i < enList.length; i++) {
				HashMap<String, String> tmpMap = new HashMap<String, String>();
				tmpMap.put("en", enList[i]);// 英文名称
				tmpMap.put("cn", cnList[i]);// 中文名称
				topList.add(tmpMap);
			}
		}
		return topList;
	}

	/**
	 * 学生奖学金查询信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stuJxjSqxx(String xh) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||xn||xq||nd||jxjdm pk, rownum,xn,xq,nd,jxjmc,xysh,xxsh from view_xsjxjb where xh= ?";
		resList = dao.rsToVator(sql, new String[] { xh }, new String[] { "pk",
				"rownum", "xn", "xq", "nd", "jxjmc", "xysh", "xxsh" });
		return resList;
	}

	/**
	 * 自动计算综合分
	 * 
	 * @param xn
	 * @param nd
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean autoCount(String xn, String nd, String xydm)
			throws Exception {
		String qxn = String.valueOf(Integer.parseInt(xn.substring(0, 4))-1) + "-" + xn.substring(0,4); 
		return dao.runProcuder("{call WHLGHXXY_ZHSZCPACCOUNT(?,?,?,?)}",
				new String[] { xn, nd, xydm,qxn });
	}

	/**
	 * 显示综合素质测评分
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewZhszcp(String pkValue) {
		return dao
				.getMapNotOut(
						"select xh,xn,xm,xb,nj,xymc,zymc,bjmc,dcj,zcj,tcj,mcj,xf,jlf,cff,zf,zfpm,xfpm from view_hxxy_zhszcp where xh||xn=?",
						new String[] { pkValue });
	}

	/**
	 * 修改综合分
	 * 
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcp(String pkValue, PjpyHxxyModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("zhszcp", new String[] { "dcj", "mcj",
				"zcj", "tcj", "jlf", "cff", "xn", "xf" }, new String[] {
				model.getDcj(), model.getMcj(), model.getZcj(), model.getTcj(),
				model.getJlf(), model.getCff(), model.getXn(), model.getXf() },
				"xh||xn", pkValue, request);
	}

	public List<String[]> queryJxjshResultByxy(PjpyHxxyModel model)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String zt = "";
		if (!StringUtils.isNull(model.getZt())) {
			zt = " and xysh='" + DealString.toGBK(model.getZt()) + "'";
		}
		String[] opCol = new String[] { "pk", "dis", "rownum", "xn", "xh",
				"xm", "bjmc", "jxjmc", "xf", "xfpm", "zf", "zfpm", "xysh" };
		return dao
				.rsToVator(
						"select a.*,rownum from (select a.xh||a.xn||a.jxjdm pk,(case when xxsh='通过' then 'disabled' else '' end) dis,a.xn,a.xh,a.xm,a.bjmc,a.jxjdm,a.xydm,a.zydm,a.bjdm,a.nj,a.jxjmc,a.xysh,b.xf,b.xfpm,b.zf,b.zfpm from view_xsjxjb a left join view_hxxy_zhszcp b on a.xh=b.xh and a.xn=b.xn) a where 1=1"
								+ whereSql.toString() + zt,
						values != null ? values.toArray(new String[0])
								: new String[] {}, opCol);
	}

	public List<String[]> queryJxjshResultByxx(PjpyHxxyModel model)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String zt = "";
		if (!StringUtils.isNull(model.getZt())) {
			zt = " and xxsh='" + DealString.toGBK(model.getZt()) + "'";
		}
		String[] opCol = new String[] { "pk", "dis", "rownum", "xn", "xh",
				"xm", "bjmc", "jxjmc", "xf", "xfpm", "zf", "zfpm", "xxsh" };
		return dao
				.rsToVator(
						"select a.*,rownum from (select a.xh||a.xn||a.jxjdm pk,'' dis,a.xn,a.xh,a.xm,a.bjmc,a.jxjdm,a.xydm,a.zydm,a.bjdm,a.nj,a.jxjmc,a.xxsh,b.xf,b.xfpm,a.xysh,b.zf,b.zfpm from view_xsjxjb a left join view_hxxy_zhszcp b on a.xh=b.xh and a.xn=b.xn) a where xysh='通过'"
								+ whereSql.toString() + zt,
						values != null ? values.toArray(new String[0])
								: new String[] {}, opCol);
	}

	/**
	 * 审核
	 * 
	 * @param key
	 * @param jg
	 * @param userType
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String jxjplsh(String[] key, String jg, String userType,
			HttpServletRequest request) throws Exception {
		String sh = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sh = "xysh";
		} else {
			sh = "xxsh";
		}
		String rs = "";
		jg = !StringUtils.isNull(jg) ? ("tg".equalsIgnoreCase(jg) ? "通过"
				: "不通过") : "未审核";
		for (int i = 0; i < key.length; i++) {
			boolean falg = dao.runUpdate("update xsjxjb set " + sh
					+ "=? where xh||xn||jxjdm=?", new String[] { jg, key[i] });
			if (!falg) {
				rs += (i + 1) + ",";
			}
		}
		return rs;
	}

	public HashMap<String, String> viewJxjshOne(String pkValue, String userType)
			throws Exception {
		String sql = "select a.xh,a.xm,a.xb,a.xn,a.nj,a.xymc,a.zymc,a.bjmc,a.jxjmc,a.xxsh sh,b.xf,b.xfpm,b.zf,b.zfpm,b.dcj,b.zdcj,b.jlf,a.tzjkbzdj,a.fdyyj,a.xyshyj,a.xxshyj yj from view_xsjxjb "
				+ "a left join view_hxxy_zhszcp b on a.xh=b.xh and a.xn=b.xn where a.xh||a.xn||a.jxjdm=?";
		if ("xy".equalsIgnoreCase(userType)) {
			sql = "select a.xh,a.xm,a.xb,a.xn,a.nj,a.xymc,a.zymc,a.bjmc,a.jxjmc,a.xysh sh,b.xf,b.xfpm,b.zf,b.zfpm,b.dcj,b.zdcj,b.jlf,a.tzjkbzdj,a.fdyyj,a.xyshyj yj,a.xxsh  from view_xsjxjb "
					+ "a left join view_hxxy_zhszcp b on a.xh=b.xh and a.xn=b.xn where a.xh||a.xn||a.jxjdm=?";
		}
		return dao.getMapNotOut(sql, new String[] { pkValue });
	}

	public boolean jxjshOne(String pkValue, String userType, String sh,
			String yj, String fdyyj) throws Exception {
		String sql = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sql = "update xsjxjb set xysh=?,fdyyj=?,xyshyj=? where xh||xn||jxjdm=?";
			return dao.runUpdate(sql, new String[] { sh, fdyyj, yj, pkValue });
		} else {
			sql = "update xsjxjb set xxsh=?,xxshyj=? where xh||xn||jxjdm=?";
			return dao.runUpdate(sql, new String[] { sh, yj, pkValue });
		}
	}

	public List<String[]> queryrychshResultByxy(PjpyHxxyModel model)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
//		String tt = model.getXydm();
		String zt = "";
		if (!StringUtils.isNull(model.getZt())) {
			zt = " and xysh='" + DealString.toGBK(model.getZt()) + "'";
		}
		String[] opCol = new String[] { "pk", "dis", "rownum", "xn", "xh",
				"xm", "xb", "rychmc","xysh"};
		return dao
				.rsToVator(
						"select a.*,rownum from (select a.xh||a.xn||a.rychdm pk,(case when xxsh='通过' then 'disabled' else '' end) " +
						"dis,a.xh, a.nd, a.xm, a.xn, a.xb,a.xymc, a.zymc, a.bjmc,a.xydm,a.zydm,a.bjdm, a.rychdm," +
						"a.rychmc,a.zysj,a.drshgzqk,a.fdyyj,a.xyyj,a.xxyj,a.xysh,a.xxsh,a.nj,b.mzmc, " +
						"b.wydj, b.csrq, b.lxdh, b.zzmmmc,b.rxrq from view_Xsrychb a left join view_xsxxb b on a.xh=b.xh ) a where 1=1"
								+ whereSql.toString() + zt,
						values != null ? values.toArray(new String[0]): new String[] {}, opCol);
	}

	public List<String[]> queryrychshResultByxx(PjpyHxxyModel model)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String zt = "";
		if (!StringUtils.isNull(model.getZt())) {
			zt = " and xxsh='" + DealString.toGBK(model.getZt()) + "'";
		}
		String[] opCol = new String[] {"pk", "dis", "rownum", "xn", "xh",
				"xm", "xb", "rychmc","xysh","xxsh"};
		return dao
				.rsToVator(
						"select a.*,rownum from (select a.xh||a.xn||a.rychdm pk,''" +
						" dis,a.xh, a.nd, a.xm,a.zydm,a.bjdm, a.xn, a.xb,a.xydm,a.xymc, a.zymc, a.bjmc, a.rychdm,a.rychmc" +
						",a.zysj,a.drshgzqk,a.fdyyj,a.xyyj,a.xxyj,a.xysh,a.xxsh,a.nj,b.mzmc, b.wydj, b.csrq, b.lxdh," +
						" b.zzmmmc,b.rxrq from view_Xsrychb a left join view_xsxxb b on a.xh=b.xh ) a where 1=1 and xysh='通过'"
								+ whereSql.toString() + zt,
						values != null ? values.toArray(new String[0]): new String[] {}, opCol);
	}

	/**
	 * 审核
	 * 
	 * @param key
	 * @param jg
	 * @param userType
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String rychplsh(String[] key, String jg, String userType,
			HttpServletRequest request) throws Exception {
		String sh = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sh = "xysh";
		} else {
			sh = "xxsh";
		}
		String rs = "";
		jg = !StringUtils.isNull(jg) ? ("tg".equalsIgnoreCase(jg) ? "通过"
				: "不通过") : "未审核";
		for (int i = 0; i < key.length; i++) {
			boolean falg = dao.runUpdate("update xsrychb set " + sh
					+ "=? where xh||xn||rychdm=?", new String[] { jg, key[i] });
			if (!falg) {
				rs += (i + 1) + ",";
			}
		}
		return rs;
	}

	public HashMap<String, String> viewrychshOne(String pkValue, String userType)
			throws Exception {
		String sql = " select a.xh, a.nd, a.xm, a.xn, a.xb,a.xydm,a.xymc, a.zymc, a.bjmc, a.rychdm,a.rychmc,a.zysj,a.drshgzqk,a.fdyyj,a.xyyj,a.xxyj,a.xysh,a.xxsh sh,a.nj,b.mzmc, a.wydj, b.csrq, b.lxdh, b.zzmmmc,b.rxrq from view_xsrychb a left join view_xsxxb b on a.xh=b.xh WHERE a.xh||a.xn||a.rychdm=?";
		if ("xy".equalsIgnoreCase(userType)) {
			sql = "select a.xh, a.nd, a.xm, a.xn, a.xb,a.xydm,a.xymc, a.zymc, a.bjmc, a.rychdm,a.rychmc,a.zysj,a.drshgzqk,a.fdyyj,a.xyyj,a.xxyj,a.xysh sh,a.xxsh,a.nj,b.mzmc, a.wydj, b.csrq, b.lxdh, b.zzmmmc,b.rxrq from view_xsrychb a left join view_xsxxb b on a.xh=b.xh WHERE a.xh||a.xn||a.rychdm=?";
		}
		return dao.getMapNotOut(sql, new String[] { pkValue });
	}

	public boolean rychshOne(String pkValue, String userType, String sh,
			String yj, String fdyyj) throws Exception {
		String sql = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sql = "update xsrychb set xysh=?,fdyyj=?,xyyj=? where xh||xn||rychdm=?";
			return dao.runUpdate(sql, new String[] { sh, fdyyj, yj, pkValue });
		} else {
			sql = "update xsrychb set xxsh=?,xxyj=? where xh||xn||rychdm=?";
			return dao.runUpdate(sql, new String[] { sh, yj, pkValue });
		}

	}
	
	public List<String[]> jxjsqjgQuery(PjpyHxxyModel model, PjpyHxxyActionForm dataSearchForm) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		if (!StringUtils.isNull(model.getZt())) {
			whereSql.append(" and xysh=xxsh and xysh='" + DealString.toGBK(model.getZt()) + "'") ;
		}
		String[] opCol = new String[]{"pk", "r", "xn", "xh", "xm", "bjmc", "xysh", "xxsh"};
		String sql = "select * from (select a.xh||a.xn||a.jxjdm pk,rownum r,a.xn,a.xh,a.xm,a.bjmc,a.jxjmc,a.xysh,a.xxsh from view_xsjxjb a where 1=1";
		return dao.rsToVator(sql + whereSql.toString() 
				+ ") where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " and r> "
				+ dataSearchForm.getPages().getStart(), values != null ? values.toArray(new String[0]) : new String[]{}, opCol);
	}
	
	public int jxjsqjgQueryNum(PjpyHxxyModel model, PjpyHxxyActionForm dataSearchForm) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		if (!StringUtils.isNull(model.getZt())) {
			whereSql.append(" and xysh=xxsh and xysh='" + DealString.toGBK(model.getZt()) + "'") ;
		}
		String[] opCol = new String[]{"pk", "rownum", "xn", "xh", "xm", "bjmc", "xysh", "xxsh"};
		String sql = "select a.xh||a.xn||a.jxjdm pk,rownum,a.xn,a.xh,a.xm,a.bjmc,a.jxjmc,a.xysh,a.xxsh from view_xsjxjb a where 1=1";
		return dao.rsToVator(sql + whereSql.toString()
				, values != null ? values.toArray(new String[0]) : new String[]{}, opCol).size();
	}
	
	public String jxjsqDelete(String[] keys) throws Exception {
		String rs = "";
		String[] sql = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			StringBuffer sb = new StringBuffer("delete from xsjxjb where xh||xn||jxjdm='");
			sb.append(keys[i]);
			sb.append("'");
			sql[i] = sb.toString();
		}
		int flag[] = dao.runBatch(sql);
		for (int i = 0;i < flag.length; i++) {
			if (flag[i] == -1) {
				rs += (i+1) + ",";
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
	}
	
	public HashMap<String, String> jxjsqView(String pkValue) {
//		String[] opList = dao
//		.getOneRs(
//				"select xh,xm,xb,nj,xymc,zymc,bjmc,mzmc,zzmmmc,csrq,rxrq,lxdh from view_xsxxb where xh=?",
//				new String[] { }, new String[] { "xh",
//						"xm", "xb", "nj", "xymc", "zymc", "bjmc",
//						"mzmc", "zzmmmc", "csrq", "rxrq", "lxdh" });
		return dao.getMapNotOut("select a.*,b.mzmc,b.zzmmmc,b.csrq,b.rxrq,b.lxdh from (select a.xh,a.xm,a.nj,a.xb,a.xymc,a.zymc,a.bjmc,a.jxjdm,a.tzjkbzdj,a.wysp,a.drzw,a.sqly,a.fdyyj,a.xyshyj,a.xxshyj,b.xf,b.xfpm,b.zf,b.zfpm,b.dcj,b.jlf,b.zdcj,a.xn,a.jxjmc,a.xq,a.nd from view_xsjxjb a left join view_hxxy_zhszcp b on a.xh=b.xh and a.xn=b.xn where a.xh||a.xn||a.jxjdm=? ) a left join view_xsxxb b on a.xh=b.xh", new String[]{pkValue});
	}
	
	public boolean jxjsqUpdate(String pkValue, PjpyHxxyModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.update("xsjxjb", new String[] { "wysp",
				"drzw", "jxjdm", "tzjkbzdj", "sqly" }, new String[] {
				DealString.toGBK(model.getWysp()),
				DealString.toGBK(model.getDrzw()),
				DealString.toGBK(model.getJxjdm()),
				DealString.toGBK(model.getTzjkbzdj()),
				DealString.toGBK(model.getSqly())},
				"xh||xn||jxjdm", pkValue, request);
	}
	
	public boolean chksqtj(String xh) {
		String xn = Base.getJxjsqxn();
		String wjnum = dao.getOneRs(
				"select count(*) num from wjcfb where xh=? and xn=? and xxsh='通过'",
				new String[] { xh, xn }, "num");
		if (!StringUtils.isNull(wjnum) && !"0".equalsIgnoreCase(wjnum)) {
			return false;
		}
		String cjnum =  dao.getOneRs(
				"select count(*) num from cjb where zpcj2<60 and xh=? and xn=? and kclx like '%必修%' and kcmc not like '%体育%'",
				new String[] { xh, xn }, "num");
		if (!StringUtils.isNull(cjnum) && !"0".equalsIgnoreCase(cjnum)) {
			return false;
		}
		return true;
	}
	
}
