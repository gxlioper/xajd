package xgxt.jygl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.jygl.model.JyglModel;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class ZgldgxxyDao {

	String sql;
	String[] colList;
	private ArrayList<String> values = new ArrayList<String>();
	DAO dao = DAO.getInstance();
	ArrayList<Object> rs = new ArrayList<Object>();

	public ZgldgxxyDao() {
		dao = DAO.getInstance();
	}

	/**
	 * 追加查询条件
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(JyglModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = '" + model.getXydm() + "'");
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = '" + model.getZydm() + "'");
		}
		if (!StringUtils.isNull(model.getXm())) {
			model.setXm(DealString.toGBK(model.getXm()));
			whereSql.append(" and xm like '%" + model.getXm() + "%'");
		}
		if (!StringUtils.isNull(model.getXh())) {
			model.setXh(DealString.toGBK(model.getXh()));
			whereSql.append(" and xh like '%" + model.getXh() + "%'");
		}
		if (!StringUtils.isNull(model.getXb1())) {
			model.setXb1(DealString.toGBK(model.getXb1()));
			whereSql.append(" and xb = '" + model.getXb1() + "'");
		}
		if (!StringUtils.isNull(model.getXz())) {
			whereSql.append(" and xz = '" + model.getXz() + "'");
		}
		if (!StringUtils.isNull(model.getXxsh())) {
			whereSql.append(" and xxsh = '" + DealString.toGBK(model.getXxsh()) + "'");
		}
		if (!StringUtils.isNull(model.getXsxh())) {
			whereSql.append(" and xsxh = '" + model.getXsxh() + "'");
		}
		if (!StringUtils.isNull(model.getName())) {
			whereSql.append(" and name = '" + DealString.toGBK(model.getName())
					+ "'");
		}
		if (!StringUtils.isNull(model.getZplx())) {
			whereSql.append(" and zplx = '" + DealString.toGBK(model.getZplx())
					+ "'");
		}
		if (!StringUtils.isNull(model.getBynd())) {
			whereSql.append(" and (select nj+xz from xsxxb b where a.xh=b.xh) = '"
							+ model.getBynd() + "'");
		}
		if (!StringUtils.isNull(model.getLxr())) {
			whereSql.append(" and lxr = '" + DealString.toGBK(model.getLxr())
					+ "'");
		}
		if (!StringUtils.isNull(model.getLxdh())) {
			whereSql.append(" and lxdh = '" + DealString.toGBK(model.getLxdh())
					+ "'");
		}
		if (!StringUtils.isNull(model.getBmxm())) {
			whereSql.append(" and bmxm = '" + DealString.toGBK(model.getBmxm())
					+ "'");
		}
		if (!StringUtils.isNull(model.getBmbynd())) {
			whereSql.append(" and (select xz+nj from view_xsjbxx a where exists(select xh from bysbmb b where a.xh=b.xh) and rownum<2)='" + model.getBmbynd()
					+ "'");
		}
		return whereSql;  
	}

	/**
	 * 传入二个数组返回LIST查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTitle(String[] enList,
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
	 * 传入二个数组返回LIST查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getTopTr(String[] enList, String[] cnList)
			throws Exception {
		HashMap<String, String> tmpMap = new HashMap<String, String>();
		if (enList != null && cnList != null) {
			for (int i = 0; i < enList.length; i++) {
				tmpMap.put(enList[i], cnList[i]);// 中文名称
			}
		}
		return tmpMap;
	}

	/**
	 * 查询分页数
	 * 
	 * @return
	 * @throws Exception
	 */
	public int queryListNum(JyglModel model, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String[] opCol = new String[] { "id", "xydm", "xyjbqk", "zydm", "rs1",
				"pycc", "xz", "xw", "pymb", "zykc", "jyqj", "xxsh" };
		if ("xx".equals(userType) || "admin".equals(userType)) {
			sql = "select id,xydm,xyjbqk,zydm,rs1,pycc,xz,xw,pymb,zykc,jyqj,xxsh from yxjzyjsb where 1=1 "
					+ whereSql.toString();
		} else {
			sql = "select id,xydm,xyjbqk,zydm,rs1,pycc,xz,xw,pymb,zykc,jyqj,xxsh from yxjzyjsb where 1=1 and xxsh='已通过√' "
					+ whereSql.toString();
		}
		String[] inputValue = values != null ? values.toArray(new String[values
				.size()]) : new String[] {};
		int num = dao.rsToVator(sql, inputValue, opCol).size();
		return num;
	}

	public int querybysjytjNum(JyglModel model, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
//		String userType = (String) request.getSession()
//				.getAttribute("userType");
		String[] opCol = new String[] { "xsxh" };
		sql = "select xsxh from bysjytjb a,view_xsxxb b where a.xsxh=b.xh " + whereSql.toString();
		String[] inputValue = values != null ? values.toArray(new String[values
				.size()]) : new String[] {};
		int num = dao.rsToVator(sql, inputValue, opCol).size();
		return num;
	}

	public int jyyxdcListNum(JyglModel model, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] opCol = new String[] { "xm" };
		sql = "select xm from jyyxdxb a where 1=1 " + whereSql.toString();
		String[] inputValue = values != null ? values.toArray(new String[values
				.size()]) : new String[] {};
		int num = dao.rsToVator(sql, inputValue, opCol).size();
		return num;
	}

	public int bysbmListNum(JyglModel model, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String[] opCol = new String[] { "id" };
		if ("xx".equals(userType) || "admin".equals(userType)
				|| "xy".equals(userType)) {
			sql = "select id from bysbmb where 1=1 " + whereSql.toString();
		} else {
			sql = "select id from bysbmb where 1=1 " + whereSql.toString();
		}
		String[] inputValue = values != null ? values.toArray(new String[values
				.size()]) : new String[] {};
		int num = dao.rsToVator(sql, inputValue, opCol).size();
		return num;
	}

	public int jyqkbsListNum(JyglModel model, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] opCol = new String[] { "id" };
		sql = "select id from jyjzqkbsb where 1=1 " + whereSql.toString();
		String[] inputValue = values != null ? values.toArray(new String[values
				.size()]) : new String[] {};
		int num = dao.rsToVator(sql, inputValue, opCol).size();
		return num;
	}

	public int zphcsListNum(JyglModel model, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] opCol = new String[] { "id" };
		sql = "select id from zphxxfbb where 1=1 " + whereSql.toString();
		String[] inputValue = values != null ? values.toArray(new String[values
				.size()]) : new String[] {};
		int num = dao.rsToVator(sql, inputValue, opCol).size();
		return num;
	}

	/**
	 * 增加
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addXyjzyjs(JyglModel model, HttpServletRequest request)
			throws Exception {
		boolean bool = false;
		bool = StandardOperation.insert("yxjzyjsb", new String[] { "xydm",
				"xyjbqk", "zydm", "rs1", "pycc", "xz", "xw", "pymb", "zykc",
				"jyqj" }, new String[] {
				// DealString.toGBK(request.getParameter("xydm")),
				// DealString.toGBK(request.getParameter("xyjbqk")),
				// DealString.toGBK(request.getParameter("zydm")),
				// DealString.toGBK(request.getParameter("rs1")),
				// DealString.toGBK(request.getParameter("pycc")),
				// DealString.toGBK(request.getParameter("xz")),
				// DealString.toGBK(request.getParameter("xw")),
				// DealString.toGBK(request.getParameter("pymb")),
				// DealString.toGBK(request.getParameter("zykc")),
				// DealString.toGBK(request.getParameter("jyqj")), }, request);
				DealString.toGBK(model.getXydm()),
				DealString.toGBK(model.getXyjbqk()),
				DealString.toGBK(model.getZydm()),
				DealString.toGBK(model.getRs1()),
				DealString.toGBK(model.getPycc()),
				DealString.toGBK(model.getXz()),
				DealString.toGBK(model.getXw()),
				DealString.toGBK(model.getPymb()),
				DealString.toGBK(model.getZykc()),
				DealString.toGBK(model.getJyqj()), }, request);
		return bool;
	}

	public boolean addBysjytjb(JyglModel model, HttpServletRequest request)
			throws Exception {
		boolean bool = false;
		dao.runUpdate("delete from bysjytjb where xsxh=?", new String[]{model.getXsxh()});
		bool = StandardOperation.insert("bysjytjb", new String[] { "xsxh",
				"name", "xb", "id", "csrq", "zzmm", "sydq", "lxdh", "sjhm",
				"txdz", "yzbm", "byxx", "xlmc", "zymc", "xz", "bysj", "jlqk",
				"shsj1", "shsj2", "shsj3", "wyyz", "jb", "jsjsp", "tcnl",
				"pyfs", "jyfw", "yxyj", "jybmyj", "lxbm", "bmlxr", "bmlxdh",
				"bz" }, new String[] { 
				DealString.toGBK(model.getXsxh()),
				DealString.toGBK(model.getName()),
				DealString.toGBK(model.getXb()),
				DealString.toGBK(model.getId()),
				DealString.toGBK(model.getCsrq()),
				DealString.toGBK(model.getZzmm()),
				DealString.toGBK(model.getSydq()),
				DealString.toGBK(model.getLxdh()),
				DealString.toGBK(model.getSjhm()),
				DealString.toGBK(model.getTxdz()),
				DealString.toGBK(model.getYzbm()),
				DealString.toGBK(model.getByxx()),
				DealString.toGBK(model.getXlmc()),
				DealString.toGBK(model.getZymc()),
				DealString.toGBK(model.getXz()),
				DealString.toGBK(model.getBysj()),
				DealString.toGBK(model.getJlqk()),
				DealString.toGBK(model.getShsj1()),
				DealString.toGBK(model.getShsj2()),
				DealString.toGBK(model.getShsj3()),
				DealString.toGBK(model.getWyyz()),
				DealString.toGBK(model.getJb()),
				DealString.toGBK(model.getJsjsp()),
				DealString.toGBK(model.getTcnl()),
				DealString.toGBK(model.getPyfs()),
				DealString.toGBK(model.getJyfw()),
				DealString.toGBK(model.getYxyj()),
				DealString.toGBK(model.getJybmyj()),
				DealString.toGBK(model.getLxbm()),
				DealString.toGBK(model.getBmlxr()),
				DealString.toGBK(model.getBmlxdh()),
				DealString.toGBK(model.getBz())}, request);
		return bool;
	}

	public boolean addJyyxdx(JyglModel model, HttpServletRequest request)
			throws Exception {
		boolean bool = false;
		bool = StandardOperation.insert("jyyxdxb", new String[] { "xh", "xm",
				"xb1", "xydm", "zydm", "ky", "bkgwy", "szyf", "xbjh", "zgz",
				"zzcy", "zgzs" }, new String[] {
				DealString.toGBK(model.getXh()),
				DealString.toGBK(model.getXm()),
				DealString.toGBK(model.getXb1()),
				DealString.toGBK(model.getXydm()),
				DealString.toGBK(model.getZydm()),
				DealString.toGBK(model.getKy()),
				DealString.toGBK(model.getBkgwy()),
				DealString.toGBK(model.getSzyf()),
				DealString.toGBK(model.getXbjh()),
				DealString.toGBK(model.getZgz()),
				DealString.toGBK(model.getZzcy()),
				DealString.toGBK(model.getZgzs()) }, request);
		return bool;
	}

	public boolean addBysbm(JyglModel model, HttpServletRequest request)
			throws Exception {
		boolean bool = false;
		bool = StandardOperation.insert("bysbmb", new String[] { "xh", "bmxm",
				"xm", "xb", "xydm", "zydm", "syd", "bkqx", "lxfs", "bz" },
				new String[] {
						// DealString.toGBK(request.getParameter("xydm")),
						// DealString.toGBK(request.getParameter("xyjbqk")),
						// DealString.toGBK(request.getParameter("zydm")),
						// DealString.toGBK(request.getParameter("rs1")),
						// DealString.toGBK(request.getParameter("pycc")),
						// DealString.toGBK(request.getParameter("xz")),
						// DealString.toGBK(request.getParameter("xw")),
						// DealString.toGBK(request.getParameter("pymb")),
						// DealString.toGBK(request.getParameter("zykc")),
						// DealString.toGBK(request.getParameter("jyqj")), },
						// request);
						DealString.toGBK(model.getXh()),
						DealString.toGBK(model.getBmxm()),
						DealString.toGBK(model.getXm()),
						DealString.toGBK(model.getXb()),
						DealString.toGBK(model.getXydm()),
						DealString.toGBK(model.getZydm()),
						DealString.toGBK(model.getSyd()),
						DealString.toGBK(model.getBkqx()),
						DealString.toGBK(model.getLxfs()),
						DealString.toGBK(model.getBz()) }, request);
		return bool;
	}

	public boolean addJyjzqkbs(JyglModel model, HttpServletRequest request)
			throws Exception {
		boolean bool = false;
		bool = StandardOperation.insert("jyjzqkbsb", new String[] { "gzjcs",
				"zywt", "jyxsfx", "lxr", "lxdh", "tbsm" }, new String[] {
				DealString.toGBK(model.getGzjcs()),
				DealString.toGBK(model.getZywt()),
				DealString.toGBK(model.getJyxsfx()),
				DealString.toGBK(model.getLxr()),
				DealString.toGBK(model.getLxdh()),
				DealString.toGBK(model.getTbsm()) }, request);
		return bool;
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean yxjzyjs_modify(JyglModel model, String pk,
			HttpServletRequest request) throws Exception {
		boolean bool = false;
		bool = StandardOperation.update("yxjzyjsb", new String[] { "xydm",
				"xyjbqk", "zydm", "rs1", "pycc", "xz", "xw", "pymb", "zykc",
				"jyqj" }, new String[] {
				// DealString.toGBK(request.getParameter("xydm")),
				// DealString.toGBK(request.getParameter("xyjbqk")),
				// DealString.toGBK(request.getParameter("zydm")),
				// DealString.toGBK(request.getParameter("rs1")),
				// DealString.toGBK(request.getParameter("pycc")),
				// DealString.toGBK(request.getParameter("xz")),
				// DealString.toGBK(request.getParameter("xw")),
				// DealString.toGBK(request.getParameter("pymb")),
				// DealString.toGBK(request.getParameter("zykc")),
				// DealString.toGBK(request.getParameter("jyqj")), }, request);
				DealString.toGBK(model.getXydm()),
				DealString.toGBK(model.getXyjbqk()),
				DealString.toGBK(model.getZydm()),
				DealString.toGBK(model.getRs1()),
				DealString.toGBK(model.getPycc()),
				DealString.toGBK(model.getXz()),
				DealString.toGBK(model.getXw()),
				DealString.toGBK(model.getPymb()),
				DealString.toGBK(model.getZykc()),
				DealString.toGBK(model.getJyqj()) }, "id", pk, request);
		return bool;
	}

	public boolean bysjytj_modify(JyglModel model, String pk,
			HttpServletRequest request) throws Exception {
		boolean bool = false;
		bool = StandardOperation.update("bysjytjb", new String[] { "csrq",
				"zzmm", "sydq", "lxdh", "sjhm", "txdz", "yzbm", "byxx", "xlmc",
				"zymc", "xz", "bysj", "jlqk", "shsj1", "shsj2", "shsj3",
				"wyyz", "jb", "jsjsp", "tcnl", "pyfs", "jyfw", "yxyj",
				"jybmyj", "lxbm", "bmlxr", "bmlxdh","bz"}, new String[] {
				DealString.toGBK(model.getCsrq()),
				DealString.toGBK(model.getZzmm()),
				DealString.toGBK(model.getSyd()),
				DealString.toGBK(model.getLxdh()),
				DealString.toGBK(model.getSjhm()),
				DealString.toGBK(model.getTxdz()),
				DealString.toGBK(model.getYzbm()),
				DealString.toGBK(model.getByxx()),
				DealString.toGBK(model.getXlmc()),
				DealString.toGBK(model.getZymc()),
				DealString.toGBK(model.getXz()),
				DealString.toGBK(model.getBysj()),
				DealString.toGBK(model.getJlqk()),
				DealString.toGBK(model.getShsj1()),
				DealString.toGBK(model.getShsj2()),
				DealString.toGBK(model.getShsj3()),
				DealString.toGBK(model.getWyyz()),
				DealString.toGBK(model.getJb()),
				DealString.toGBK(model.getJsjsp()),
				DealString.toGBK(model.getTcnl()),
				DealString.toGBK(model.getPyfs()),
				DealString.toGBK(model.getJyfw()),
				DealString.toGBK(model.getYxyj()),
				DealString.toGBK(model.getJybmyj()),
				DealString.toGBK(model.getLxbm()),
				DealString.toGBK(model.getBmlxr()),
				DealString.toGBK(model.getBmlxdh()),
				DealString.toGBK(model.getBz())}, "xsxh", pk, request);
		return bool;
	}

	public boolean bysbm_modify(JyglModel model, String pk,
			HttpServletRequest request) throws Exception {
		boolean bool = false;
		bool = StandardOperation.update("bysbmb", new String[] { "xh", "bmxm",
				"xm", "xb", "xydm", "zydm", "syd", "bkqx", "lxfs", "bz" },
				new String[] { DealString.toGBK(model.getXh()),
						DealString.toGBK(model.getBmxm()),
						DealString.toGBK(model.getXm()),
						DealString.toGBK(model.getXb()),
						DealString.toGBK(model.getXydm()),
						DealString.toGBK(model.getZydm()),
						DealString.toGBK(model.getSyd()),
						DealString.toGBK(model.getBkqx()),
						DealString.toGBK(model.getLxfs()),
						DealString.toGBK(model.getBz()) }, "id", pk, request);
		return bool;
	}

	public boolean jyjzqk_modify(JyglModel model, String pk,
			HttpServletRequest request) throws Exception {
		boolean bool = false;
		bool = StandardOperation.update("jyjzqkbsb", new String[] { "gzjcs",
				"zywt", "jyxsfx", "lxr", "lxdh", "tbsm" }, new String[] {
				DealString.toGBK(model.getGzjcs()),
				DealString.toGBK(model.getZywt()),
				DealString.toGBK(model.getJyxsfx()),
				DealString.toGBK(model.getLxr()),
				DealString.toGBK(model.getLxdh()),
				DealString.toGBK(model.getTbsm()) }, "id", pk, request);
		return bool;
	}

	public boolean zphcs_modify(JyglModel model, String pk,
			HttpServletRequest request) throws Exception {
		boolean bool = false;
		bool = StandardOperation.update("zphxxfbb", new String[] { "xydm",
				"zplx", "zpcs", "dwsl", "gwsl", "zpsj" }, new String[] {
				DealString.toGBK(model.getXydm()),
				DealString.toGBK(model.getZplx()),
				DealString.toGBK(model.getZpcs()),
				DealString.toGBK(model.getDwsl()),
				DealString.toGBK(model.getGwsl()),
				DealString.toGBK(model.getZpsj()), }, "id", pk, request);
		return bool;
	}

	/**
	 * 院系及专业介绍add
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public ArrayList<String[]> getQueryList(JyglModel model,
			CommanForm dataSearchForm, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] opCol = new String[] { "id", "r", "xymc", "zymc", "rs1",
				"pycc", "xz", "xw", "xxsh" };
//		String userType = (String) request.getSession()
//				.getAttribute("userType");
//		if ("xx".equals(userType) || "admin".equals(userType)) {
			sql = "select * from (select * from (select id,rownum r,b.xydm,b.xymc,a.xyjbqk,b.zydm,b.zymc,a.rs1,a.pycc,a.xz,a.xw,pymb,"
					+ "zykc,jyqj,xxsh from yxjzyjsb a,(select distinct xydm,xymc,zydm,zymc from view_njxyzybj) b "
					+ "where a.zydm=b.zydm) where 1=1 "
					+ whereSql.toString()
					+ ") where r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize())
					+ " and r> "
					+ dataSearchForm.getPages().getStart();
//		} else {
//			sql = "select * from (select * from (select id,rownum r,b.xydm,b.xymc,a.xyjbqk,b.zydm,b.zymc,a.rs1,a.pycc,a.xz,a.xw,pymb,"
//					+ "zykc,jyqj,xxsh from yxjzyjsb a,(select distinct xydm,xymc,zydm,zymc from view_njxyzybj) b "
//					+ "where a.zydm=b.zydm and a.xxsh='已通过√') where 1=1 "
//					+ whereSql.toString()
//					+ ") where r<="
//					+ (dataSearchForm.getPages().getStart() + dataSearchForm
//							.getPages().getPageSize())
//					+ " and r> "
//					+ dataSearchForm.getPages().getStart();
//		}
		ArrayList<String[]> list = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);

		return list;
	}

	public ArrayList<String[]> getJybjbList(JyglModel model,
			CommanForm dataSearchForm, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);

		String[] opCol = new String[] { "xsxh", "r", "name", "xb", "id",
				"csrq", "zzmm", "sydq", "lxdh", "sjhm", "txdz", "yzbm", "byxx",
				"xlmc", "zymc", "xz", "bysj", "jlqk", "shsj1", "shsj2",
				"shsj3", "wyyz", "jb", "jsjsp", "tcnl", "pyfs", "jyfw", "yxyj",
				"jybmyj", "lxbm", "bmlxr", "bmlxdh","bz"};
//		String userType = (String) request.getSession()
//				.getAttribute("userType");
		sql = "select * from (select a.* from (select xsxh,rownum r,name,xb,id,csrq,zzmm,sydq,lxdh,sjhm,txdz,yzbm,byxx,xlmc,"
				+ "zymc,xz,bysj,jlqk,shsj1,shsj2,shsj3,wyyz,jb,jsjsp,tcnl,pyfs,jyfw,yxyj,jybmyj,lxbm,bmlxr,bmlxdh,bz "
				+ "from bysjytjb a ) a,view_xsxxb b where a.xsxh=b.xh "
				+ whereSql.toString()
				+ ") where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ " and r> "
				+ dataSearchForm.getPages().getStart();
		ArrayList<String[]> list = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);

		return list;
	}

	public ArrayList<String[]> getjyyxdcList(JyglModel model,
			CommanForm dataSearchForm, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);

		String[] opCol = new String[] { "xh", "xm", "xb1", "ky", "bkgwy",
				"szyf", "xbjh", "zgz", "zzcy", "zgzs" };
		sql = "select * from (select rownum r,a.* from jyyxdxb a where 1=1 "
				+ whereSql.toString()
				+ ") a where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " and r> "
				+ dataSearchForm.getPages().getStart();
		ArrayList<String[]> list = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);

		return list;
	}

	public ArrayList<HashMap<String, String>> getjyyxdcListtj(JyglModel model,
			CommanForm dataSearchForm, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);

		String[] opCol = new String[] { "xh", "xm", "xb1", "ky", "szyf",
				"xbjh", "zzcy", "zgzs", "gjj", "bjs", "dfss", "wd", "bj" };
		StringBuffer sqlsb = new StringBuffer();
		// sql = "select * from jyyxdxb a where 1=1 ";
		sql = "select xh,xm,xb1,ky,szyf,xbjh,zzcy,nvl(zgzs,'否') zgzs,"
				+ "(case when bkgwy='国家级' then '是' else '否' end) gjj ,"
				+ "(case when bkgwy='北京市' then '是' else '否' end) bjs ,"
				+ "(case when bkgwy='地方省市' then '是' else '否' end) dfss,"
				+ "(case when zgz='北京' then '是' else '否' end) bj ,"
				+ "(case when zgz='外地' then '是' else '否' end) wd from jyyxdxb a where 1=1 ";
		sqlsb.append(sql).append(whereSql);
		// ArrayList<String[]> list = dao.rsToVator(sql, values != null ? values
		// .toArray(new String[0]) : new String[] {}, opCol);
		if (StringUtils.isNotNull(model.getBynd())) {
			String sql1 = " and exists(select xh from xsxxb b where (nj+xz)='"
					+ model.getBynd() + "' and a.xh=b.xh)";
			sqlsb.append(sql1);
		}
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sqlsb
				.toString(), values != null ? values.toArray(new String[0])
				: new String[] {}, opCol);
		return list;
	}

	public ArrayList<HashMap<String, String>> getjyjzqkbsbListtj(
			JyglModel model, CommanForm dataSearchForm,
			HttpServletRequest request) throws Exception {
		
		String[] opCol = new String[] { "byszs", "byszsbj", "byszsjw",
				"byszsbks", "byszszks", "byszsbkbj", "byszsbkjw", "byszszkbj",
				"byszszkjw", "bysqys", "bysqysbks", "bysqyszks", "bysqysbksbj",
				"bysqysbksjw", "bysqyszksbj", "bysqyszksjw", "bysqyl",
				"bysqylbks", "bysqylzks", "bysqylqnbks", "bysqylqnzks",
				"jtjjknbysrs", "jtjjknbysqyrs", "jtjjknbysrs", "jtjjknbysqyrs",
				"yqyyybysrs", "yqyyybysbksrs", "yqyyybyszksrs",
				"yqyyybyszksrs", "bysqys1", "bysqysbks", "bysqyszks",
				"bysqysbksysx", "bysqysbkssydw", "bysqysbksqydw",
				"bysqysbksqt", "bysqyszksysx", "bysqyszkssydw", "bysqyszksqy",
				"bysqyszksqt", "gwxqshj", "gwxqshjbks", "gwxqshjzks",
				"gwxqshjbksqntq", "gwxqshjzksqntq", "dxzphcs", "dxzphdwsl",
				"dwxjhgwsl", "dwsjhdwsl", "zphxjxb2", "zphxjxb3",
				"zphxjxb4", "zphxjxb1","dxzphgwsl","dwxjhcs" };
		StringBuffer whereSql1 = new StringBuffer();
		StringBuffer whereSql12 = new StringBuffer();
		StringBuffer whereSql13 = new StringBuffer();
		StringBuffer whereSql2 = new StringBuffer();
		StringBuffer whereSql3 = new StringBuffer();
		StringBuffer whereSql4 = new StringBuffer();
		StringBuffer whereSql5 = new StringBuffer();
		StringBuffer whereSql51 = new StringBuffer();
		whereSql3.append(model.getBynf()).append(model.getByyf());
		if ((!StringUtils.isNull(model.getBynf()))&&(!StringUtils.isNull(model.getByyf()))) {
			whereSql1.append(" and substr(tjsj,0,6) = '" + whereSql3.toString() + "'");
		}
		if ((!StringUtils.isNull(model.getXymc()))) {
			whereSql1.append(" and a.xymc = '" + DealString.toGBK(model.getXymc()) + "'");
		}
		if ((!StringUtils.isNull(model.getBynf()))&&(!StringUtils.isNull(model.getByyf()))) {
			whereSql13.append(" and substr(tjsj,0,6) = '" + whereSql3.toString() + "'");
		}
		if ((!StringUtils.isNull(model.getXymc()))) {
			whereSql13.append(" and b.xymc = '" + DealString.toGBK(model.getXymc()) + "'");
		}
		if ((!StringUtils.isNull(model.getBynf()))&&(!StringUtils.isNull(model.getByyf()))) {
			whereSql12.append(" and substr(sbsj,0,6) = '" + whereSql3.toString() + "'");
		}
		if ((!StringUtils.isNull(model.getXymc()))) {
			whereSql12.append(" and xymc = '" + DealString.toGBK(model.getXymc()) + "'");
		}
		if ((!StringUtils.isNull(model.getBynf()))&&(!StringUtils.isNull(model.getByyf()))) {
			whereSql2.append(" and substr(tjsj,0,6) = '" + whereSql3.toString() + "'");
		}
		if ((!StringUtils.isNull(model.getXymc()))) {
			whereSql2.append(" and xymc = '" + DealString.toGBK(model.getXymc()) + "'");
		}
		if ((!StringUtils.isNull(model.getBynf()))&&(!StringUtils.isNull(model.getByyf()))) {
			whereSql4.append(" and substr(zpsj,0,4)||substr(zpsj,6,2) = '" + whereSql3.toString() + "'");
		}
		if ((!StringUtils.isNull(model.getXymc()))) {
			whereSql4.append(" and xydm=(select distinct b.xydm from zphxxfbb a," 
					+"(select xydm,xymc from view_njxyzybj where xymc='"+DealString.toGBK(model.getXymc())+"' and rownum<2) b " 
					+"where a.xydm=b.xydm)");
		}
		if ((!StringUtils.isNull(model.getXymc()))) {
			whereSql5.append(" and xydm=(select distinct b.xydm from zphxxfbb a," 
					+"(select xydm,xymc from view_njxyzybj where xymc='"+DealString.toGBK(model.getXymc())+"' and rownum<2) b " 
					+"where a.xydm=b.xydm)");
		}
		if ((!StringUtils.isNull(model.getBynf()))&&(!StringUtils.isNull(model.getByyf()))) {
			whereSql5.append(" and substr(zpsj,0,4)-1||substr(zpsj,6,2) = '" + whereSql3.toString() + "'");
		}
		if ((!StringUtils.isNull(model.getBynf()))) {
			whereSql51.append(" and substr(sbsj,0,6) = '" + whereSql3.toString() + "'");
		}
		if ((!StringUtils.isNull(model.getXymc()))) {
			whereSql51.append(" and b.xymc = '" + DealString.toGBK(model.getXymc()) + "'");
		}
		String sql ="select distinct"  
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh"+whereSql1+") byszs,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.sydqdm='110000'"+whereSql1+") byszsbj,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.sydqdm <> '110000'"+whereSql1+") byszsjw,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='本科生'"+whereSql1+") byszsbks," 
			+ "(select count(a.xsxh) from jygl_xsjbxxb  a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='专科生'"+whereSql1+") byszszks,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.sydqdm='110000' and a.xslb='本科生'"+whereSql1+") byszsbkbj,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.sydqdm <> '110000' and a.xslb='本科生'"+whereSql1+") byszsbkjw,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.sydqdm='110000' and a.xslb='专科生'"+whereSql1+") byszszkbj,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.sydqdm <>'110000' and a.xslb='专科生'"+whereSql1+") byszszkjw,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh"+whereSql1+") bysqys,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and b.xzdm>3"+whereSql1+") bysqysbks,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and b.xzdm=3"+whereSql1+") bysqyszks,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and b.xzdm>3 and b.sydqdm='110000'"+whereSql1+") bysqysbksbj,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and b.sydqdm not in '110000' and b.xzdm>3"+whereSql1+") bysqysbksjw,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and b.xzdm=3 and b.sydqdm='110000'"+whereSql1+") bysqyszksbj,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and b.xzdm=3 and b.sydqdm not in'110000'"+whereSql1+") bysqyszksjw,"
			+ "(round((select distinct count(*) from jygl_jyxy b where 1=1 "+whereSql13+")/(select distinct decode(count(*),0,1,count(*)) from jygl_xsjbxxb a,view_xsxxb b where a.xsxh=b.xh "+whereSql51+")*100,2)) bysqyl,"
//			+ "((select distinct round((select distinct count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql1+")/(select distinct case when (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql1+")=0 then 1 else (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql1+") end from jygl_jyxy),2) from jygl_jyxy)*100) bysqylbks,"
//			+ "((select distinct round((select distinct count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql1+")/(select distinct case when (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql1+")=0 then 1 else (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql1+") end from jygl_jyxy),2) from jygl_jyxy)*100) bysqylzks,"
//			+ "((select distinct round((select distinct count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql1+")/(select distinct case when (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql1+")=0 then 1 else (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql1+") end from jygl_jyxy),2) from jygl_jyxy)*100) bysqylqnbks,"
//			+ "((select distinct round((select distinct count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql1+")/(select distinct case when (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql1+")=0 then 1 else (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql1+") end from jygl_jyxy),2) from jygl_jyxy)*100) bysqylqnzks,"
//			+ "((select distinct round((select distinct count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生')/(select distinct case when (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql13+")=0 then 1 else (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql13+") end from jygl_jyxy),2) from jygl_jyxy)*100) bysqylbks,"
//			+ "((select distinct round((select distinct count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生')/(select distinct case when (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql13+")=0 then 1 else (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql13+") end from jygl_jyxy),2) from jygl_jyxy)*100) bysqylzks,"
//			+ "((select distinct round((select distinct count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生')/(select distinct case when (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql13+")=0 then 1 else (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='本科生'"+whereSql13+") end from jygl_jyxy),2) from jygl_jyxy)*100) bysqylqnbks,"
//			+ "((select distinct round((select distinct count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生')/(select distinct case when (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql13+")=0 then 1 else (select count(a.xsxh) from jygl_jyxy a,jygl_xsjbxxb b where a.xsxh=b.xsxh and 1=1 and b.xslb='专科生'"+whereSql13+") end from jygl_jyxy),2) from jygl_jyxy)*100) bysqylqnzks,"
			+ "(round((select distinct count(*) from jygl_jyxy b where xslb='本科生' "+whereSql13+")/(select distinct decode(count(*),0,1,count(*)) from jygl_xsjbxxb a,view_xsxxb b where a.xsxh=b.xh and xslb='本科生'"+whereSql51+")*100,2)) bysqylbks,"
			+ "(round((select distinct count(*) from jygl_jyxy b where xslb='专科生' "+whereSql13+")/(select distinct decode(count(*),0,1,count(*)) from jygl_xsjbxxb a,view_xsxxb b where a.xsxh=b.xh and xslb='专科生'"+whereSql51+")*100,2)) bysqylzks,"
			+ "(round((select distinct count(*) from jygl_jyxy b where xslb='本科生' "+whereSql13+")/(select distinct decode(count(*),0,1,count(*)) from jygl_xsjbxxb a,view_xsxxb b where a.xsxh=b.xh and xslb='本科生'"+whereSql51+")*100,2)) bysqylqnbks,"
			+ "(round((select distinct count(*) from jygl_jyxy b where xslb='专科生' "+whereSql13+")/(select distinct decode(count(*),0,1,count(*)) from jygl_xsjbxxb a,view_xsxxb b where a.xsxh=b.xh and xslb='专科生'"+whereSql51+")*100,2)) bysqylqnzks,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and qkqk='是'"+whereSql1+") jtjjknbysrs,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and qkqk='是'"+whereSql1+") jtjjknbysqyrs,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and qkqk='是'"+whereSql1+") jtjjknbysrs,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and qkqk='是'"+whereSql1+") jtjjknbysqyrs,"
			+ "(select count(xsxh) from (select b.* from jyyxdxb a,jygl_xsjbxxb b where a.xh=b.xsxh) where 1=1"+whereSql12+") yqyyybysrs,"
			+ "(select count(xsxh) from (select b.* from jyyxdxb a,jygl_xsjbxxb b where a.xh=b.xsxh and xslb='本科生') where 1=1"+whereSql12+") yqyyybysbksrs,"
			+ "(select count(xsxh) from (select b.* from jyyxdxb a,jygl_xsjbxxb b where a.xh=b.xsxh and xslb='专科生') where 1=1"+whereSql12+") yqyyybyszksrs,"
			+ "(select count(xh) from jyyxdxb b,jygl_xsjbxxb a,jygl_jyxy c where b.xh=a.xsxh and a.xzdm not in '4'"+whereSql1+") yqyyybyszksrs,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and 1=1"+whereSql1+") bysqys1,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='本科生'"+whereSql1+") bysqysbks,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='专科生'"+whereSql1+") bysqyszks,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='本科生' and nvl(b.zgbm,'1')='已升学'"+whereSql1+") bysqysbksysx,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='本科生' and nvl(b.zgbm,'1')='事业单位'"+whereSql1+") bysqysbkssydw,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='本科生' and  nvl(b.zgbm,'1')='企业'"+whereSql1+") bysqysbksqydw,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='本科生' and nvl(b.zgbm,'1') not in ('已升学','事业单位','企业') "+whereSql1+") bysqysbksqt,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='专科生' and nvl(b.zgbm,'1')='已升学'"+whereSql1+") bysqyszksysx,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='专科生' and nvl(b.zgbm,'1')='事业单位'"+whereSql1+") bysqyszkssydw,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='专科生' and nvl(b.zgbm,'1')='企业'"+whereSql1+") bysqyszksqy,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xslb='专科生' and nvl(b.zgbm,'1') not in ('已升学','事业单位','企业')"+whereSql1+") bysqyszksqt,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh"+whereSql1+") gwxqshj,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xzdm='4'"+whereSql1+") gwxqshjbks,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xzdm<'4'"+whereSql1+") gwxqshjzks,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xzdm='4'"+whereSql1+") gwxqshjbksqntq,"
			+ "(select count(a.xsxh) from jygl_xsjbxxb a,jygl_jyxy b where a.xsxh=b.xsxh and a.xzdm<'4'"+whereSql1+") gwxqshjzksqntq,"
			+ "(select nvl(sum(zpcs),0) dwsl from zphxxfbb where zplx='大型招聘会'"+whereSql4+") dxzphcs,"
			+ "(select nvl(sum(zpcs),0) dwsl from zphxxfbb where zplx='单位宣讲会'"+whereSql4+") dwxjhcs,"
			+ "(select nvl(sum(dwsl),0) dwsl from zphxxfbb where zplx='大型招聘会'"+whereSql4+") dxzphdwsl,"
			+ "(select nvl(sum(gwsl),0) gwsl from zphxxfbb where zplx='大型招聘会'"+whereSql4+") dxzphgwsl,"
			+ "(select nvl(sum(dwsl),0) dwsl from zphxxfbb where zplx='单位宣讲会'"+whereSql4+") dwsjhdwsl,"
			+ "(select nvl(sum(gwsl),0) gwsl from zphxxfbb where zplx='单位宣讲会'"+whereSql4+") dwxjhgwsl,"
			+ "(select case when zpnum='1' then '上升' when zpnum='0' then '持平' else '下降' end from (select distinct sign(nvl((select sum(dwsl) from zphxxfbb where zplx='大型招聘会'"+whereSql4+"),0)-nvl((select sum(dwsl) from zphxxfbb where zplx='大型招聘会'"+whereSql5+"),0)) zpnum from  zphxxfbb)) zphxjxb1," 
			+ "(select case when zpnum='1' then '上升' when zpnum='0' then '持平' else '下降' end from (select distinct sign(nvl((select sum(gwsl) from zphxxfbb where zplx='大型招聘会'"+whereSql4+"),0)-nvl((select sum(gwsl) from zphxxfbb where zplx='大型招聘会'"+whereSql5+"),0)) zpnum from  zphxxfbb)) zphxjxb2,"
			+ "(select case when zpnum='1' then '上升' when zpnum='0' then '持平' else '下降' end from (select distinct sign(nvl((select sum(dwsl) from zphxxfbb where zplx='单位宣讲会'"+whereSql4+"),0)-nvl((select sum(dwsl) from zphxxfbb where zplx='单位宣讲会'"+whereSql5+"),0)) zpnum from  zphxxfbb)) zphxjxb3,"
			+ "(select case when zpnum='1' then '上升' when zpnum='0' then '持平' else '下降' end from (select distinct sign(nvl((select sum(gwsl) from zphxxfbb where zplx='单位宣讲会'"+whereSql4+"),0)-nvl((select sum(gwsl) from zphxxfbb where zplx='单位宣讲会'"+whereSql5+"),0)) zpnum from  zphxxfbb)  ) zphxjxb4"
			+ " from jygl_xsjbxxb";
		
		ArrayList<HashMap<String, String>> list = dao.getArrayList(
				sql, values != null ? values.toArray(new String[0])
				: new String[] {}, opCol);
//		sqlsb.append(sql).append(whereSql);
		// ArrayList<String[]> list = dao.rsToVator(sql, values != null ? values
		// .toArray(new String[0]) : new String[] {}, opCol);
//		ArrayList<HashMap<String, String>> list = dao.getArrayList(sqlsb
//				.toString(), values != null ? values.toArray(new String[0])
//				: new String[] {}, opCol);
		return list;
	}

	public HashMap<String, String> getjyyxdchjListtj(JyglModel model,
			CommanForm dataSearchForm, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);

		String[] opCol = new String[] { "ky", "szyf", "xbjh", "zzcy", "zgzs",
				"gjj", "bjs", "dfss", "wd", "bj" };
		StringBuffer sqlsb = new StringBuffer();
		// sql = "select * from jyyxdxb a where 1=1 ";
		sql = "select distinct "
				+ "(select nvl(count(ky),0) from jyyxdxb a where ky='是' "
				+ whereSql
				+ ") ky,"
				+ "(select nvl(count(bkgwy),0) from jyyxdxb a where bkgwy='国家级' "
				+ whereSql
				+ ") gjj,"
				+ "(select nvl(count(bkgwy),0) from jyyxdxb a where bkgwy='北京市' "
				+ whereSql
				+ ") bjs,"
				+ "(select nvl(count(bkgwy),0) from jyyxdxb a where bkgwy='地方省市' "
				+ whereSql
				+ ") dfss,"
				+ "(select nvl(count(szyf),0) from jyyxdxb a where szyf='是' "
				+ whereSql
				+ ") szyf,"
				+ "(select nvl(count(xbjh),0) from jyyxdxb a where xbjh='是' "
				+ whereSql
				+ ") xbjh,"
				+ "(select nvl(count(zgz),0) from jyyxdxb a where zgz='外地' "
				+ whereSql
				+ ") wd,"
				+ "(select nvl(count(zgz),0) from jyyxdxb a where zgz='北京' "
				+ whereSql
				+ ") bj,"
				+ "(select nvl(count(zzcy),0) from jyyxdxb a where zzcy='是' "
				+ whereSql
				+ ") zzcy,"
				+ "(select nvl(count(zgzs),0) from jyyxdxb a where zgzs='是' "
				+ whereSql + ") zgzs" + " from jyyxdxb a where 1=1 ";
		sqlsb.append(sql).append(whereSql);
		// ArrayList<String[]> list = dao.rsToVator(sql, values != null ? values
		// .toArray(new String[0]) : new String[] {}, opCol);
		if (StringUtils.isNotNull(model.getBynd())) {
			String sql1 = " and exists(select xh from xsxxb b where (nj+xz)='"
					+ model.getBynd() + "' and a.xh=b.xh)";
			sqlsb.append(sql1);
		}
		HashMap<String, String> list = dao.getMap(sqlsb.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, opCol);
		// getArrayList(sqlsb.toString(),
		// values != null ? values.toArray(new String[0])
		// : new String[] {}, opCol);
		return list;
	}

	public ArrayList<HashMap<String, String>> getjykndzyzyListtj(
			JyglModel model, CommanForm dataSearchForm,
			HttpServletRequest request) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		if ((!StringUtils.isNull(model.getXymc()))) {
			whereSql.append(" and xymc = '" + DealString.toGBK(model.getXymc()) + "'");
		}
		String[] opCol = new String[] { "zymc" };
//		StringBuffer sqlsb = new StringBuffer();
		// sql = "select * from jyyxdxb a where 1=1 ";
		sql = "select zymc,count(xsxh) num from jygl_jyxy where xzdm='4' "+whereSql+" group by zymc order by num";
		// HashMap<String, String> list = dao.getMap(sql,new String[]{}, opCol);
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sql,
				new String[] {}, opCol);
		return list; 
	}

	public ArrayList<String[]> getBysbmList(JyglModel model,
			CommanForm dataSearchForm, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);

		String[] opCol = new String[] { "id", "xm", "xb", "xydm",
				"zydm", "syd", "bkqx", "lxfs", "bz" };

		sql = "select id,rownum r,xymc xydm,zymc zydm,xh,bmxm, xm,xb,(select sydmc from syddmb b "
				+ "where a.syd=b.syddm) syd,bkqx,lxfs,bz from (select * from (select rownum r,a.*,b.xymc,"
				+ "b.zymc from bysbmb a,(select distinct xydm,xymc,zydm,zymc from view_njxyzybj) b "
				+ "where a.zydm=b.zydm) a where 1=1 "
				+ whereSql.toString()
				+ ") a where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ " and r> "
				+ dataSearchForm.getPages().getStart();

		ArrayList<String[]> list = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);

		return list;
	}

	public ArrayList<String[]> getJyjzqkbsList(JyglModel model,
			CommanForm dataSearchForm, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);

		String[] opCol = new String[] { "id", "gzjcs", "zywt", "jyxsfx", "lxr",
				"lxdh", "tbsm" };

		sql = "select * from (select * from (select id,rownum r,gzjcs,zywt,jyxsfx,lxr,lxdh,tbsm from jyjzqkbsb) a where 1=1 "
				+ whereSql.toString()
				+ ") a where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ " and r> "
				+ dataSearchForm.getPages().getStart();

		ArrayList<String[]> list = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);

		return list;
	}

	public ArrayList<String[]> getzhhcsList(JyglModel model,
			CommanForm dataSearchForm, HttpServletRequest request)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);

		String[] opCol = new String[] { "id", "xydm", "zplx", "zpcs", "dwsl",
				"gwsl", "zpsj" };

		sql = "select id,xymc xydm,zplx,zpcs,dwsl,gwsl,zpsj from (select * from (select rownum r,a.*,b.xymc"
				+ " from zphxxfbb a,(select distinct xydm,xymc from view_njxyzybj) b "
				+ "where a.xydm=b.xydm) a where 1=1 "
				+ whereSql.toString()
				+ ") a where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ " and r> "
				+ dataSearchForm.getPages().getStart();

		ArrayList<String[]> list = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);

		return list;
	}

	public void getExpList(JyglModel model, CommanForm dataSearchForm,
			HttpServletResponse response) throws Exception {
		StringBuffer whereSql = getWhereSql(model);

		response.reset();
		response.setContentType("application/vnd.ms-excel");

		sql = "select id,rownum r,xymc xydm,zymc zydm,xh,bmxm, xm,xb,(select sydmc from syddmb b "
				+ "where a.syd=b.syddm) syd,bkqx,lxfs,bz from (select * from (select rownum r,a.*,b.xymc,"
				+ "b.zymc from bysbmb a,(select distinct xydm,xymc,zydm,zymc from view_njxyzybj) b "
				+ "where a.zydm=b.zydm) a where 1=1 "
				+ whereSql.toString()
				+ ") a where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ " and r> "
				+ dataSearchForm.getPages().getStart();

		ServletOutputStream os = response.getOutputStream();

		DAO dao = DAO.getInstance();
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
			String[] ColumnName = new String[] { "id", "xh", "bmxm", "xm",
					"xb", "xydm", "zydm", "syd", "bkqx", "lxfs", "bz" };
			String[] ColumnNameCN = new String[] { "主键", "学号", "报名项目", "姓名",
					"性别", Base.YXPZXY_KEY+"名称", "专业名称", "生源地", "报名类别", "联系方式", "备注" };
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}

	/**
	 * 院系及专业介绍add
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getViewList(String pk)
			throws Exception {

		String[] colList = new String[] { "id", "xydm", "xymc", "xyjbqk",
				"zydm", "zymc", "rs1", "pycc", "xz", "xw", "pymb", "zykc",
				"jyqj" };
		String sql = "select * from yxjzyjsb a,(select distinct xydm,xymc,zydm,zymc from view_njxyzybj) b where a.zydm=b.zydm and a.id=?";
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sql,
				new String[] { pk }, colList);
		return list;
	}

	public ArrayList<HashMap<String, String>> getjytjViewList(String pk)
			throws Exception {

		String[] colList = new String[] { "xsxh", "name", "xb", "id", "csrq",
				"zzmm", "sydq", "lxdh", "sjhm", "txdz", "yzbm", "byxx", "xlmc",
				"zymc", "xz", "bysj", "jlqk", "shsj1", "shsj2", "shsj3",
				"wyyz", "jb", "jsjsp", "tcnl", "pyfs", "jyfw", "yxyj",
				"jybmyj", "lxbm", "bmlxr", "bmlxdh","bz","sczp"};
		String sql = "select * from bysjytjb a where a.xsxh=?";
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sql,
				new String[] { pk }, colList);
		return list;
	}

	public ArrayList<HashMap<String, String>> getBysbmViewList(String pk)
			throws Exception {

		String[] colList = new String[] { "id", "xh", "bmxm", "xm", "xb",
				"xydm", "zydm", "syd", "bkqx", "lxfs", "bz" };
		String sql = "select id,xh,bmxm,xm,xb,b.xymc xydm,b.zymc zydm,"
				+ "(select sydmc from syddmb b where a.syd=b.syddm) syd,bkqx,lxfs,bz from bysbmb a,"
				+ "(select distinct xydm,xymc,zydm,zymc from view_njxyzybj) b where a.zydm=b.zydm and a.id=?";
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sql,
				new String[] { pk }, colList);
		return list;
	}

	public ArrayList<HashMap<String, String>> getJyjzqkViewList(String pk)
			throws Exception {

		String[] colList = new String[] { "id", "gzjcs", "zywt", "jyxsfx",
				"lxr", "lxdh", "tbsm" };
		String sql = "select id,gzjcs,zywt,jyxsfx,lxr,lxdh,tbsm from jyjzqkbsb where id=?";
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sql,
				new String[] { pk }, colList);
		return list;
	}

	/**
	 * 院系及专业介绍删除
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public boolean getDelList(String pk, HttpServletRequest request)
			throws Exception {

		boolean bool = StandardOperation.delete("yxjzyjsb", "id", pk, request);
		return bool;
	}

	public boolean getDelBysbmList(String pk, HttpServletRequest request)
			throws Exception {

		boolean bool = StandardOperation.delete("bysbmb", "id", pk, request);
		return bool;
	}

	/**
	 * 院系及专业介绍批量删除
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getAllDelList(String pks, HttpServletRequest request)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from yxjzyjsb where id = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}

	public String getAllbysjytjDelList(String pks, HttpServletRequest request)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from bysjytjb where xsxh = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}

	public String getAlljyyxdcList(String pks, HttpServletRequest request)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from jyyxdxb where xh = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}

	public String getAllDelBysbmList(String pks, HttpServletRequest request)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from bysbmb where id = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}

	public String getAllDeljyjzqkbsList(String pks, HttpServletRequest request)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from jyjzqkbsb where id = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}

	public String getAllDelzphcsList(String pks, HttpServletRequest request)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from zphxxfbb where id = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}

	/**
	 * 院系及专业介绍学校审核
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public HashMap<String, String> getAuditingList(String pk,
			HttpServletRequest request) throws Exception {

		String auditing = request.getParameter("shenhe");
		String doType = request.getParameter("doType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		boolean bool = false;
		String sql = "";
		HashMap<String, String> list = new HashMap<String, String>();
		if ("kssh".equals(doType)) {
			String shType = DealString.toGBK(request.getParameter("xxsh"));
			String shsj = "";
			String xxsh = "";
			String shr = "";
			if (!"未审核".equals(shType)) {
				shsj = dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddHH24mmss') time from dual",
								new String[] {}, "time");
				shr = userName;
			}
			xxsh = shType;
			sql = "update yxjzyjsb set xxsh='" + xxsh + "',shr='" + shr
					+ "',shsj='" + shsj + "' where id = '" + pk + "'";

			// bool = StandardOperation.update("yxjzyjsb", sql, request);
			bool = StandardOperation.update("yxjzyjsb", new String[] { "xxsh",
					"shr", "shsj" }, new String[] { xxsh, shr, shsj }, "id",
					pk, request);
			if (bool) {
				request.setAttribute("ifsh", "yes");
			} else {
				request.setAttribute("ifsh", "no");
			}
		}
		if ("auditing".equals(auditing)) {
			String[] colList = new String[] { "id", "xydm", "xymc", "xyjbqk",
					"zydm", "zymc", "rs1", "pycc", "xz", "xw", "pymb", "zykc",
					"jyqj", "xxsh", "shr", "shsj" };
			sql = "select * from yxjzyjsb a,(select distinct xydm,xymc,zydm,zymc from view_njxyzybj) b where a.zydm=b.zydm and a.id=?";
			list = dao.getMap(sql, new String[] { pk }, colList);
			if (list.get("shsj") != null) {
				String sj = list.get("shsj").toString();
				String shsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				shsj = sjyear + "年" + sjmon + "月" + sjday + "日" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				list.put("shsj", shsj);
			}
		}

		return list;
	}

	/**
	 * 院系及专业介绍批量审核
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getAllAuditingList(String pks, HttpServletRequest request)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		String xxsh = request.getParameter("shenhe");
		if ("pass".equals(xxsh)) {
			xxsh = "已通过√";
		} else if ("nopass".equals(xxsh)) {
			xxsh = "未通过X";
		} else {
			xxsh = "未审核";
		}
		String shr = (String) request.getSession().getAttribute("userName");
		for (int i = 0; i < keys.length; i++) {
			if (StringUtils.isNotNull(keys[i])) {
				String pk = DealString.toGBK(keys[i]);// 得到主键
				String shsj = dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddHH24mmss') time from dual",
								new String[] {}, "time");
				sql = "update yxjzyjsb set xxsh='" + xxsh + "',shr='" + shr
						+ "',shsj='" + shsj + "' where id = '" + pk + "'";
				// 把主键组合成sql语句
				sb.append(sql);
				sb.append("!!#!!");
			}
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据审核失败;\n";
			}
		}
		return whichpk;
	}

	/**
	 * Method 毕业生基础信息统计
	 * 
	 * @param String
	 *            [] keys
	 * @param String
	 *            [] values
	 * @return String
	 */
	public ArrayList<HashMap<String, String>> getBysjcxxtj(String xydm,
			String zydm, String nd) {
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql = "select a.sfzh sfzh,xh,a.xm,a.xb, (select mzmc from mzdmb b where b.mzdm=a.mz) mz," +
				" (select zzmmmc from zzmmdmb b where b.zzmmdm=a.zzmm) zzmm,a.csrq,a.xz," +
				" (select xlmc from xldmb c where b.xldm=c.xldm) xldm," +
//				"(select distinct sydmc from syddmb c where b.sydqdm=c.syddm) syd,pyfsmc pyfs,sjhm,sfdk  from view_xsxxb a,jygl_xsjbxxb b where a.xh=b.xsxh and "
				"a.syd syd,pyfs pyfs,sjhm,nvl(sfdk,'不是') sfdk  from view_xsxxb a,jygl_xsjbxxb b where a.xh=b.xsxh and "
				+ "a.xydm=? and a.zydm = ? and b.bynd=? order by a.xh";
		String[] inputValue = {xydm,zydm,nd};
		String[] outputValue = { "sfzh", "xh", "xm", "xb", "mz", "zzmm",
				"csrq", "xz", "xldm", "syd", "pyfs", "sjhm", "sfdk" };
		// dao.rsToVator(sql, inputValue, outputValue);
		rs = dao.getArrayList(sql, inputValue, outputValue);
//		System.out.println(sql);
		return rs;
	}

	/**
	 * 导出就业意向调查统计
	 * 
	 * @param wwb
	 * @return void
	 * @throws Exception
	 * */
	public void printJyyxdc(WritableWorkbook wwb, CommanForm myform,
			ArrayList<HashMap<String, String>> list,
			HashMap<String, String> maprs) throws Exception {
//		XsJyglDao dao = new XsJyglDao();
		// List list = dao.getXjspList_db(myform.getBynd());
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, myform.getBynd() + "年"
					+ DealString.toGBK(myform.getZymc()) + "专业毕业生就业意向调查表",
					wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			int num = 5;

			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> rs = (HashMap<String, String>) list
						.get(i);

				ws.addCell(new Label(0, 5 + i, String.valueOf(rs.get("xh")),
						wcfTytle));
				ws.addCell(new Label(1, 5 + i, String.valueOf(rs.get("xm")),
						wcfTytle));
				ws.addCell(new Label(2, 5 + i, String.valueOf(rs.get("xb1")),
						wcfTytle));
				ws.addCell(new Label(3, 5 + i, String.valueOf(rs.get("ky")),
						wcfTytle));
				ws.addCell(new Label(4, 5 + i, String.valueOf(rs.get("gjj")),
						wcfTytle));
				ws.addCell(new Label(5, 5 + i, String.valueOf(rs.get("bjs")),
						wcfTytle));
				ws.addCell(new Label(6, 5 + i, String.valueOf(rs.get("dfss")),
						wcfTytle));
				ws.addCell(new Label(7, 5 + i, String.valueOf(rs.get("szyf")),
						wcfTytle));
				ws.addCell(new Label(8, 5 + i, String.valueOf(rs.get("xbjh")),
						wcfTytle));
				ws.addCell(new Label(9, 5 + i, String.valueOf(rs.get("wd")),
						wcfTytle));
				ws.addCell(new Label(10, 5 + i, String.valueOf(rs.get("bj")),
						wcfTytle));
				ws.addCell(new Label(11, 5 + i, String.valueOf(rs.get("zzcy")),
						wcfTytle));
				ws.addCell(new Label(12, 5 + i, String.valueOf(rs.get("zgzs")),
						wcfTytle));
				num += 1;
			}

			WritableFont wf = new WritableFont(WritableFont.TIMES); // 构造字体格式
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			wf.setBoldStyle(WritableFont.BOLD); // 设置字体格式(非粗体)
			wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // 设置字体格式(无下划线)
			wf.setPointSize(10); // 设置字体格式(大小)
			wcf.setFont(wf); // 设置指定字体格式的单元格格式
			wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);

			ws.mergeCells(0, num, 2, num);
			ws.addCell(new Label(0, num, "合计", wcf));
			ws.addCell(new Label(1, num, "", wcf));
			ws.addCell(new Label(2, num, "", wcf));
			ws.addCell(new Label(3, num, String.valueOf(maprs.get("ky")), wcf));
			ws.addCell(new Label(4, num,
							String.valueOf(maprs.get("gjj")), wcf));
			ws.addCell(new Label(5, num,
							String.valueOf(maprs.get("bjs")), wcf));
			ws.addCell(new Label(6, num, String
							.valueOf(maprs.get("dfss")), wcf));
			ws.addCell(new Label(7, num, String
							.valueOf(maprs.get("szyf")), wcf));
			ws.addCell(new Label(8, num, String
							.valueOf(maprs.get("xbjh")), wcf));
			ws.addCell(new Label(9, num, String.valueOf(maprs.get("wd")), wcf));
			ws.addCell(new Label(10, num,
							String.valueOf(maprs.get("bj")), wcf));
			ws.addCell(new Label(11, num, String.valueOf(maprs.get("zzcy")),
					wcf));
			ws.addCell(new Label(12, num, String.valueOf(maprs.get("zgzs")),
					wcf));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
