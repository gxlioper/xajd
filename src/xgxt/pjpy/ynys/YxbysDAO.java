package xgxt.pjpy.ynys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class YxbysDAO extends PjpyYnysDAO {

	public List<String> values = new ArrayList<String>();// 保存查询条件值

	/**
	 * 学生辅导信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> stuDetailsInfo(String xh) throws Exception {
		dao = DAO.getInstance();
		return dao.getMap("select distinct xh,xm,xb,nj,xymc,zymc,bjmc,syd,jtdz,"
				+ "(case when length(csrq)=10 then"
				+ " substr(csrq,0,4)||substr(csrq,6,2) when length(csrq)=8 "
				+ "then substr(csrq,0,6) else csrq end) csrq,rxrq,"
				+ "(select mzmc from mzdmb"
				+ " b where b.mzdm=view_xsxxb.mz) mz,(select zzmmmc from zzmmdmb b"
				+ " where b.zzmmdm=view_xsxxb.zzmm) zzmm from view_xsxxb where xh=?",
				new String[] { xh }, new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc", "syd", "jtdz", "csrq", "rxrq",
						"mz", "zzmm" });
	}

	/**
	 * 优秀毕业生申请保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYxbysxx(YxbysModel model, HttpServletRequest request)
			throws Exception {
		StandardOperation.delete("ynys_yxbysb", new String[] { "xh", "xn" },
				new String[] { model.getXh(), model.getXn() }, request);
		return StandardOperation.insert("ynys_yxbysb", new String[] { "xh",
				"xn", "yxsj", "bjyj" }, new String[] { model.getXh(),
				model.getXn(), DealString.toGBK(model.getYxsj()),
				DealString.toGBK(model.getBjyj()) }, request);
	}

	/**
	 * 优秀毕业生辅导信息保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYxbysfzxx(YxbysModel model, HttpServletRequest request)
			throws Exception {
		dao = DAO.getInstance();
		String num = dao.getOneRs(
				"select count(*) num from xsrychxxb where xh=?",
				new String[] { model.getXh() }, "num");
		if (!StringUtils.isNull(num) && !StringUtils.isEqual("0", num)) {// update
			return StandardOperation.update("xsrychxxb", new String[] { "mz",
					"zzmm", "syd", "jtdz", "jkzk" }, new String[] {
					DealString.toGBK(model.getMz()),
					DealString.toGBK(model.getZzmm()),
					DealString.toGBK(model.getSyd()),
					DealString.toGBK(model.getJtdz()),
					DealString.toGBK(model.getJkzk()) }, "xh", model.getXh(),
					request);
		} else {// insert
			return StandardOperation.insert("xsrychxxb", new String[] { "xh",
					"mz", "zzmm", "syd", "jtdz", "jkzk" }, new String[] {
					model.getXh(), DealString.toGBK(model.getMz()),
					DealString.toGBK(model.getZzmm()),
					DealString.toGBK(model.getSyd()),
					DealString.toGBK(model.getJtdz()),
					DealString.toGBK(model.getJkzk()) }, request);
		}
	}

	/**
	 * 优秀毕业生查询结果
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> yxbysQryRes(YxbysModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xn||xh pk,rownum,xn,nj,xh,xm,xymc,zymc,bjmc,'优秀毕业生' grry"
				+ ",yxsh,xxsh from view_ynys_yxbys where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xn", "nj", "xh",
				"xm", "xymc", "zymc", "bjmc", "grry", "yxsh", "xxsh" };
		return dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}

	/**
	 * 查询条件值
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(YxbysModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
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
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		return whereSql;
	}

	/**
	 * 显示优秀毕业生信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewYxbysxx(String pkValue) {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,zzmm,mz,syd,jkzk," 
						+ "(case when length(csrq)=10 then"
						+ " substr(csrq,0,4)||substr(csrq,6,2) when length(csrq)=8 "
						+ "then substr(csrq,0,6) else csrq end) csrq,"
						+ "rxrq,jtdz,yxsj,bjyj from (select a.xh,a.xm,a.xb,a.nj,a.xymc," +
								"a.zymc,a.bjmc,a.xn,a.zzmm,a.mz,a.syd,a.jkzk,a.jtdz," +
								"a.yxsj,a.bjyj,b.csrq,b.rxrq from view_ynys_yxbys a left " +
								"join view_xsxxb b on a.xh=b.xh where a.xn||a.xh = ?)",
						new String[] { pkValue });
	}

	/**
	 * 修改优秀毕业生信息
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean modiYxbysxx(String pkValue, YxbysModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation
				.update("ynys_yxbysb", new String[] { "yxsj", "bjyj" },
						new String[] { DealString.toGBK(model.getYxsj()),
								DealString.toGBK(model.getBjyj()) }, "xn||xh",
						pkValue, request);
	}
	
	/**
	 * 批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delYxbysxx(String[] keys, HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.delete("ynys_yxbysb", "xn||xh",
					whichpk, request);
			if (!bFlag) {//删除失败后记录删除失败的行号
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	
	/**
	 * 优秀毕业生报表打印
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> yxbysPrint(String pkValue) throws Exception {
		dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao.getMap("select a.xh,a.xm,a.xb,a.jkzk,a.zymc,a.yxsj,a.bjyj," +
				"a.yxyj,a.xxyj,a.jytyj,b.syd,b.rxrq,b.jtdz,(case when length(b.csrq)=10 " +
				"then substr(b.csrq,0,4)||substr(b.csrq,6,2) when length(b.csrq)=8 " +
				"then substr(b.csrq,0,6) else b.csrq end) csrq,(select c.mzmc from mzdmb" +
				" c where b.mz=c.mzdm) mz,(select c.zzmmmc from zzmmdmb c where" +
				" b.zzmm=c.zzmmdm) zzmm,to_char(sysdate,'yyyymmdd')" +
				" tbrq from " +
				"view_ynys_yxbys a left join view_xsxxb b on a.xh=b.xh where a.xn||a.xh=?", 
				new String[] { pkValue }, new String[] { "xh", "xm",
								"xb", "jkzk", "zymc", "yxsj", "bjyj", "yxyj",
								"xxyj", "jytyj", "syd", "rxrq", "jtdz", "csrq",
								"mz", "zzmm", "tbrq" });
		String tbrq = rs.get("tbrq");
		if (!StringUtils.isNull(tbrq) && tbrq.length() == 8) {
			tbrq = tbrq.substring(0, 4) + "年"
					+ tbrq.subSequence(4, 6) + "月" + tbrq.subSequence(6, 8)
					+ "日";
		}
		rs.put("tbrq", tbrq);
		return rs;
	}
}
