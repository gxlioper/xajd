package xgxt.pjpy.nblg;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyNblgRychDAO {

	DAO dao = DAO.getInstance();
	
	ArrayList<String> values = null;
	
	public static PjpyNblgRychDAO mydao = new PjpyNblgRychDAO();
	
	public static PjpyNblgRychDAO getInstance() {
		return mydao;
	}
	
	/**
	 * 条件表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> tjTitle() throws Exception {
		String[] enList = new String[]{"pk", "rownum", "xn", "rychmc", "tj", "pm"};
		String[] cnList = new String[]{"pk", "行号", "学年", "荣誉称号", "字段", "条件"};
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 条件结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> tjResult(String xn, String rychdm) throws Exception {
		StringBuffer sql = new StringBuffer(
				"select xn||rychdm||tj pk, rownum,xn,(select rychmc from rychdmb b where" +
				" a.rychdm=b.rychdm) rychmc,(case tj when 'dycj' then '德育测评班级排名'" +
				" when 'tycj' then '体育成绩' when 'jxj' then '当年所获奖学金' else '' end) " +
				"tj,pm||bz pm from nblg_rychtjszb a where 1=1");
		String[] opList = new String[] { "pk", "rownum", "xn", "rychmc", "tj",
				"pm" };
		if (!StringUtils.isNull(xn)) {
			sql.append(" and xn='");
			sql.append(xn);
			sql.append("'");
		}
		if (!StringUtils.isNull(rychdm)) {
			sql.append(" and rychdm='");
			sql.append(rychdm);
			sql.append("'");
		}
		return dao.rsToVator(sql.toString(), new String[] {}, opList);
	}
	
	/**
	 * 条件保存
	 * @param model
	 * @param request
	 * @return 成功为true,失败为false
	 * @throws Exception
	 */
	public boolean saveTj(PjpyNblgModel model, HttpServletRequest request) throws Exception {
		String bz = "";
		String tj = model.getTj();
		if ("dycj".equalsIgnoreCase(tj)) {
			bz = "(%)";
		} else if ("tycj".equalsIgnoreCase(tj)) {
			bz = "(分及以上)";
		} else if ("jxj".equalsIgnoreCase(tj)) {
			bz = "(同等及以上)";
			String jxjmc = dao.getOneRs(
					"select jxjmc from jxjdmb where jxjdm=?", new String[] {model.getPm()},
					"jxjmc");
			model.setPm(jxjmc);
		}
		return StandardOperation.insert("nblg_rychtjszb", new String[] { "xn",
				"rychdm", "tj", "pm", "bz" }, new String[] { model.getXn(),
				model.getRychdm(), model.getTj(),
				model.getPm(), bz }, request);
	}
	
	/**
	 * 条件删除
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delTj(String[] keys) throws Exception {
		String res = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = dao.runUpdate(
					"delete from nblg_rychtjszb where xn||rychdm||tj=?",
					new String[] { keys[i] });
			if (!bFlag) {
				res += (i + 1) + ",";
			}
		}
		return StringUtils.isNull(res) ? "" : res
				.substring(0, res.length() - 1);
	}
	
	/**
	 * 荣誉称号查询表头(学院,学校)
	 *    根据条件过滤
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> rychqryTit(String userType) throws Exception {
		String[] enList = new String[] { "pk", "dis", "rownum", "xh", "xm",
				"xn", "bjmc", "rychmc", "dycj","dypm", "tycj",  "xxsh" };
		String[] cnList = new String[] { "pk", "dis", "行号", "学号", "姓名", "学年",
				"班级", "荣誉称号", "德育成绩","班级排名", "体育成绩",  "学校审核" };
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "pk", "dis", "rownum", "xh", "xm", "xn",
					"bjmc", "rychmc", "dycj","dypm", "tycj", "xysh" };
			cnList = new String[] { "pk", "dis", "行号", "学号", "姓名", "学年", "班级",
					"荣誉称号", "德育成绩","班级排名", "体育成绩",  "学院审核" };
		} 
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 荣誉称号查询结果(学院)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyrychqryRes(PjpyNblgModel model) throws Exception {
		values = new ArrayList<String>();
		List<String[]> rs = new ArrayList<String[]>();
		List<String[]> tjList = dao.rsToVator(
				"select tj,pm from nblg_rychtjszb where xn=? and rychdm=?",
				new String[] {model.getXn(), model.getRychdm()}, new String[] {"tj", "pm"});
		int bjrs = dao.getOneRsint("select count(xh) from view_xsjbxx where bjdm='"+model.getBjdm()+"'");
		StringBuffer whereSql = new StringBuffer("");
		if (tjList != null) {
			for (int i=0;i<tjList.size();i++) {
				String[] tmpList = tjList.get(i);
				if ("dycj".equalsIgnoreCase(tmpList[0])) {
					whereSql.append(" and dypm<=");
					bjrs = bjrs * (StringUtils.isNull(tmpList[1]) ? 1 : Integer.parseInt(tmpList[1])) / 100;
 					whereSql.append(tmpList[1]);
				} else if ("tycj".equalsIgnoreCase(tmpList[0])) {
					whereSql.append(" and tycj>=");
					whereSql.append(tmpList[1]);
				} else if ("jxj".equalsIgnoreCase(tmpList[0])) {
					String jxjjb = dao.getOneRs(
							"select jxjjb from jxjdmb where jxjmc =?",
							new String[] { tmpList[1] }, "jxjjb");
					if (!StringUtils.isNull(jxjjb)) {
						whereSql.append(" and jxjzdjb<=");
						whereSql.append(jxjjb);
					}
				}
			}
		}
		String sql = "select a.xh||b.xn||b.rychdm pk,(case b.xxsh when '通过' then 'disabled' else '' end) dis,rownum,a.xh,a.xm,'"
				+ model.getXn()
				+ "' xn,a.bjmc,(select rychmc from rychdmb where rychdm='"
				+ model.getRychdm()
				+ "') rychmc,a.dycj,a.dypm,a.tycj,b.xysh from view_nblg_rychtjb a left join xsrychb b on a.xh=b.xh and b.xn=? and b.rychdm=? where 1=1";
		String[] opList = new String[] { "pk", "dis", "rownum", "xh", "xm",
				"xn", "bjmc", "rychmc", "dycj", "dypm", "tycj", "xysh" };
		values.add(model.getXn());
		values.add(model.getRychdm());
		
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		rs = dao
				.rsToVator(
						"select * from ("
								+ sql
								+ whereSql.toString()
								+ ") a where 1=1 and not exists (select 1 from xsrychb b where a.xh=b.xh and b.xn='"
								+ model.getXn() + "' and b.rychdm<>'"
								+ model.getRychdm() + "')",
						values != null ? values.toArray(new String[0])
								: new String[] {}, opList);//此SQL带过滤荣誉称号兼得条件
		return rs;
	}
	
	/**
	 * 荣誉称号查询结果(学校)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxrychqryRes(PjpyNblgModel model) throws Exception {
		String sql = "select xh||xn||rychdm pk,'' dis,rownum,xh,xm,xn,bjmc,rychmc,(select b.dycj from view_nblg_rychtjb b where a.xh=b.xh) " +
				"dycj,(select b.dypm from view_nblg_rychtjb b where a.xh=b.xh) dypm,(select b.tycj from view_nblg_rychtjb b where a.xh=b.xh) tycj,xxsh from view_xsrychb a where xysh='通过'";
		String[] opList = new String[] { "pk", "dis", "rownum", "xh", "xm", "xn",
				"bjmc", "rychmc", "dycj", "dypm", "tycj", "xxsh" };
		StringBuffer whereSql = getWhereSql(model);
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public StringBuffer getWhereSql(PjpyNblgModel model) throws Exception {
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
		if (!StringUtils.isNull(model.getRychdm())) {
			whereSql.append(" and rychdm = ?");
			values.add(model.getRychdm());
		}
		return whereSql;
	}

	/**
	 * 传入荣誉称号代码 获取该荣誉称号审核限制人数
	 * 
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public int rychXzrs(String rychdm) throws Exception {
		String rychpxbl = dao.getOneRs(
				"select pxbl from rychdmb where rychdm = ?",
				new String[] { rychdm }, "pxbl");//荣誉称号比例
		String xsrs = dao.getOneRs("select count(*) num from view_xsjbxx",
				new String[] {}, "num");//总学生人数
		int pxbl = 0;
		int ixsrs = !StringUtils.isNull(xsrs) ? Integer.parseInt(xsrs) : 0;
		if (!StringUtils.isNull(rychpxbl) && rychpxbl.lastIndexOf("%") > 0) {
			pxbl = Integer.parseInt(rychpxbl
					.substring(0, rychpxbl.length() - 1))
					* ixsrs / 100;
		} else if (!StringUtils.isNull(rychpxbl)
				&& rychpxbl.lastIndexOf("%") <= 0) {
			pxbl = Integer.parseInt(rychpxbl) * ixsrs / 100;
		} else {
			pxbl = 0;
		}
		return pxbl;
	}
	
	/**
	 * 学院审核荣誉称号结果
	 *    审核时加人数限制,及兼得限制
	 *    keys中存放的可能是主键,也可能是学号
	 * @param keys
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public String xyrychshRes(String[] keys, String rychdm, String type) throws Exception {
		String xn = Base.getJxjsqxn();
		String rs = "";
		String jd = "";
		String cb = "";
		int rychxzrs = rychXzrs(rychdm);//荣誉称号审核限制人数
		int tgrs = dao
		.getOneRsint("select nvl(count(*),0) from xsrychb where xn='"
				+ Base.getJxjsqxn() + "' and rychdm='" + rychdm
				+ "' and xysh='通过'");//学院审核通过的人数
		type = StringUtils.isNull(type) ? "未审核"
				: ("tg".equalsIgnoreCase(type) ? "通过" : ("btg"
						.equalsIgnoreCase(type) ? "不通过" : "未审核"));
		for (int i = 0; i < keys.length; i++) {
			if ("通过".equalsIgnoreCase(type)) {//状态为通过
				if (tgrs >= rychxzrs && rychxzrs != 0) {// 通过人数大于限制人数则记录数据行
					cb += (i + 1) + ",";
					continue;
				} else {
					boolean bFlag = chkDataExists(keys[i]);//检查数据是否存在
					if (bFlag) {// 存在则更新数据状态
						int num = dao
								.getOneRsint("select nvl(count(*),0) from xsrychb where xh=(select xh from xsrychb where xh||xn||rychdm='"
										+ keys[i]
										+ "') and xn='"
										+ xn
										+ "' and xysh='通过'");// 本学年是否有兼得其它荣誉称号
						if (num > 0) {// 有兼得则记录数据行
							jd += (i + 1) + ",";
							continue;
						} else {// 更新数据状态
							boolean bRes = dao
									.runUpdate(
											"update xsrychb set xysh=? where xh||xn||rychdm=?",
											new String[] { type, keys[i] });
							if (!bRes) {
								rs += (i+1) + ",";
							}
						}
					} else {// 不存在直接插入到库
						int num = dao
								.getOneRsint("select nvl(count(*),0) from xsrychb where xh='"
										+ keys[i]
										+ "' and xn='"
										+ xn
										+ "' and xysh='通过'");// 本学年是否有兼得其它荣誉称号
						if (num > 0) {// 有兼得则记录数据行
							jd += (i + 1) + ",";
							continue;
						} else {// 插入数据
							boolean bRes = dao
									.runUpdate(
											"insert into xsrychb (xh,xn,rychdm,xysh) values (?,?,?,?)",
											new String[] { keys[i], xn, rychdm, type });
							if (!bRes) {
								rs += (i + 1) + ",";
							}
						}
					}
				}
			} else {//状态为不通过
				boolean bFlag = chkDataExists(keys[i]);//检查数据是否存在
				if (bFlag) {//存在则更新数据状态
					boolean bRes = dao.runUpdate(
							"update xsrychb set xysh=? where xh||xn||rychdm=?",
							new String[] { type, keys[i] });
					if (!bRes) {
						rs += (i+1) + ",";
					}
				} else {//不存在直接插入到库
					boolean bRes = dao
					.runUpdate(
							"insert into xsrychb (xh,xn,rychdm,xysh) values (?,?,?,?)",
							new String[] { keys[i], xn, rychdm, "不通过" });
					if (!bRes) {
						rs += (i+1) + ",";
					}
				}
			}
			tgrs ++;
		}
		jd = StringUtils.isNull(jd) ? "" : "其中第"
				+ jd.substring(0, jd.length() - 1) + "条数据操作失败,原因是该生已兼得其它荣誉称号!";
		cb = StringUtils.isNull(cb) ? "" : "其中第"
			+ cb.substring(0, cb.length() - 1) + "条数据操作失败,原因是通过人数已超限制人数!";
		if (StringUtils.isNull(jd) && StringUtils.isNull(cb)) {
			return "";
		}
		rs = "操作完成:" + jd + "\n          " + cb;
		return rs;
	}
	
	/**
	 * 学校荣誉称号审核结果
	 * @param keys
	 * @param rychdm
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String xxrychshRes(String[] keys, String rychdm, String type) throws Exception {
		String xn = Base.getJxjsqxn();
		String rs = "";
		int rychxzrs = rychXzrs(rychdm);//荣誉称号审核限制人数
		int tgrs = dao
		.getOneRsint("select nvl(count(*),0) from xsrychb where xn='"
				+ Base.getJxjsqxn() + "' and rychdm='" + rychdm
				+ "' and xxsh='通过'");//学院审核通过的人数
		type = StringUtils.isNull(type) ? "未审核"
				: ("tg".equalsIgnoreCase(type) ? "通过" : ("btg"
						.equalsIgnoreCase(type) ? "不通过" : "未审核"));
		String cb = "";
		String jd = "";
		for (int i=0;i<keys.length;i++) {
			if ("通过".equalsIgnoreCase(type)) {
				if (tgrs >= rychxzrs && rychxzrs != 0) {// 通过人数大于限制人数则记录数据行
					cb += (i + 1) + ",";
					continue;
				} else {
					int num = dao
							.getOneRsint("select nvl(count(*),0) from xsrychb where xh=(select xh from xsrychb where xh||xn||rychdm='"
									+ keys[i]
									+ "') and xn='"
									+ xn
									+ "' and xxsh='通过'");// 本学年是否有兼得其它荣誉称号
					if (num > 0) {// 有兼得则记录数据行
						jd += (i + 1) + ",";
						continue;
					} else {// 更新数据状态
						dao
								.runUpdate(
										"update xsrychb set xxsh=? where xh||xn||rychdm=?",
										new String[] { type, keys[i] });
					}
				}
			} else {
				dao.runUpdate(
						"update xsrychb set xxsh=? where xh||xn||rychdm=?",
						new String[] { type, keys[i] });
			}
			tgrs++;
		}
		jd = StringUtils.isNull(jd) ? "" : "其中第"
			+ jd.substring(0, jd.length() - 1) + "条数据操作失败,原因是该生已兼得其它荣誉称号!";
		cb = StringUtils.isNull(cb) ? "" : "其中第"
		+ cb.substring(0, cb.length() - 1) + "条数据操作失败,原因是通过人数已超限制人数!";
		if (StringUtils.isNull(jd) && StringUtils.isNull(cb)) {
			return "";
		}
		rs = "操作完成:" + jd + "\n          " + cb;
		return rs;
	}
	
	/**
	 * 检查该主键是否存在
	 *    存在返回true,反之为false
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(String pkValue) throws Exception {
		int num = dao
				.getOneRsint("select nvl(count(*),0) from xsrychb where xh||xn||rychdm='"
						+ pkValue + "'");
		if (num>0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过班级代码获取年级
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public String getNjBybjdm(String bjdm) throws Exception {
		return dao.getOneRs("select distinct nj from bks_xsjbxx where bjdm=?", new String[]{bjdm}, "nj");
	}
	
	/**
	 * 成绩过滤条件列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjtjszList() throws Exception {
		return dao.rsToVator("select tjdm,tjmc,(case tjlx when 'kcmc' then '课程名称' when 'kcxz' then '课程性质' else tjlx end) tjlx from pjpy_cjtjglb",
				new String[] {}, new String[] { "tjlx","tjmc" });
	}
	
	/**
	 * 保存成绩条件
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveCjtj(PjpyNblgModel model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.insert("pjpy_cjtjglb", new String[] { "tjmc",
				"tjlx" }, new String[] { DealString.toGBK(model.getTjmc()),
				model.getTjxz() }, request);
	}
	
	/**
	 * 显示成绩条件信息
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewCjtj(String pkValue) throws Exception {
		return dao.getMapNotOut(
				"select tjdm,tjmc,tjlx from pjpy_cjtjglb where tjdm=?",
				new String[] { pkValue });
	}
	
	/**
	 * 修改成绩条件
	 * @param model
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateCjtj(PjpyNblgModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_cjtjglb", new String[] { "tjmc",
				"tjlx" }, new String[] { DealString.toGBK(model.getTjmc()),
				model.getTjxz() }, "tjdm", pkValue, request);
	}
	
	/**
	 * 删除成绩条件信息
	 * 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteCjtj(String[] keys, HttpServletRequest request)
			throws Exception {
		String rs = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = dao.runUpdate(
					"delete from pjpy_cjtjglb where tjdm=?",
					new String[] { keys[i] });
			if (!bFlag) {
				rs += (i + 1) + ",";
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
	}
}
