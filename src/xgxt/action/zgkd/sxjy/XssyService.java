package xgxt.action.zgkd.sxjy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.dao.common.CommonDAO;
import xgxt.szdw.server.common.CommonService;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.GetTime;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;

public class XssyService extends CommonService {

	/**
	 * <p>
	 * Title: 学工管理系统
	 * </p>
	 * <p>
	 * Description: 学生信息管理思想教育-中国矿大-学生生涯service类
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2008
	 * </p>
	 * <p>
	 * Company: zfsoft
	 * </p>
	 * 
	 * @author 鲁宁
	 * @version 1.0
	 */

	CommonDAO dao = new CommonDAO();

	DAO basicDao = DAO.getInstance();

	XssyDAO mydao = XssyDAO.getInstance();

	public HashMap<String, String> getSjszList() {  
		// 获得设置时间
		String tableName = "sxjy_jskd_xssysjszb";
		String[] colList = new String[] { "xsdjqsrq", "xsdjjsrq", "xgmbqsrq",
				"xgmbjsrq" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "1",
				"1");
		return rs;
	}

	public boolean saveSyxgsj(XssyModel myModel, HttpServletRequest request)
			throws Exception {
		// 保存设置时间
		String tableName = "sxjy_jskd_xssysjszb";
		String[] colList = new String[] { "xsdjqsrq", "xsdjjsrq", "xgmbqsrq",
				"xgmbjsrq" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, "1", "1",
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return inserted;
	}

	public HashMap<String, String> getXssysj(String xh, String xn,String view) {
		// 得到学生生涯学生信息
		HashMap<String, String> rs = new HashMap<String, String>();
		String nj = getNjForXh(xh);
		// 判断该生是否是几年级生
		String tmp = xn.substring(5, 9);
		String jnjs = ((Integer) (Integer.parseInt(tmp) - Integer.parseInt(nj)))
				.toString();
		HashMap<String, String> rsTmp = getSjszList();
		String sfxk = "no";
		boolean sfmbxg = false;
		if (Integer.parseInt(jnjs) < 5 && Integer.parseInt(jnjs) > 1) {
			// 表明还没有毕业
			sfmbxg = true;
		}
		// 判断是否在申请时间内
		int nowTime = Integer.parseInt(GetTime.getNowTime2());
		if(!"view".equals(view)){
		if (rsTmp != null) {
				if (jnjs.equalsIgnoreCase("1")) {
					if (StringUtils.isNull(rsTmp.get("xsdjqsrq"))
							&& StringUtils.isNull(rsTmp.get("xsdjjsrq"))) {
						sfxk = "yes";
					} else if (StringUtils.isNull(rsTmp.get("xsdjqsrq"))
							&& !StringUtils.isNull(rsTmp.get("xsdjqsrq"))) {
						int xsdjjsrq = Integer.parseInt(rsTmp.get("xsdjjsrq")
								.replace("-", ""));
						if (nowTime <= xsdjjsrq) {
							sfxk = "yes";
						}
					} else if (!StringUtils.isNull(rsTmp.get("xsdjqsrq"))
							&& StringUtils.isNull(rsTmp.get("xsdjqsrq"))) {
						int xsdjqsrq = Integer.parseInt(rsTmp.get("xsdjqsrq")
								.replace("-", ""));
						if (xsdjqsrq <= nowTime) {
							sfxk = "yes";
						}
					} else {
						int xsdjjsrq = Integer.parseInt(rsTmp.get("xsdjjsrq")
								.replace("-", ""));
						int xsdjqsrq = Integer.parseInt(rsTmp.get("xsdjqsrq")
								.replace("-", ""));
						if (xsdjqsrq <= nowTime && nowTime <= xsdjjsrq) {
							sfxk = "yes";
						}
					}
				} else if (sfmbxg) {
					if (StringUtils.isNull(rsTmp.get("xgmbqsrq"))
							&& StringUtils.isNull(rsTmp.get("xgmbjsrq"))) {
						sfxk = "yes";
					} else if (StringUtils.isNull(rsTmp.get("xgmbqsrq"))
							&& !StringUtils.isNull(rsTmp.get("xgmbjsrq"))) {
						int xgmbjsrq = Integer.parseInt(rsTmp.get("xgmbjsrq")
								.replace("-", ""));// 结束时间
						if (nowTime <= xgmbjsrq) {
							sfxk = "yes";
						}
					} else if (!StringUtils.isNull(rsTmp.get("xgmbqsrq"))
							&& StringUtils.isNull(rsTmp.get("xgmbjsrq"))) {
						int xgmbqsrq = Integer.parseInt(rsTmp.get("xgmbqsrq")
								.replace("-", "")); // 起始时间
						if (xgmbqsrq <= nowTime) {
							sfxk = "yes";
						}
					} else {
						int xgmbqsrq = Integer.parseInt(rsTmp.get("xgmbqsrq")
								.replace("-", ""));
						int xgmbjsrq = Integer.parseInt(rsTmp.get("xgmbjsrq")
								.replace("-", ""));
						if (xgmbqsrq <= nowTime && nowTime <= xgmbjsrq) {
							sfxk = "yes";
						}
					}
				}
			} else {
				// rsTmp == null
				sfxk = "yes";
			}
		}else{
			sfxk = "yes";
		}
		if (sfxk.equalsIgnoreCase("no")) {
			String errMassage = "不在申请时间内或你已不需要填写";
			rs.put("errMassage", errMassage);
			return rs;
		}
		String tableName = "view_jskd_xssyxxb";
		String[] colList = new String[] { "xh", "lydq", "grjl", "zwfx",
				"zwfzgh", "ndmbonexn", "ndmbtwoxn", "ndmbthreexn",
				"ndmbfourxn", "zdyj", "lrrq", "yjhfrq", "mbtztwoxn",
				"mbtzthreexn", "mbtzfourxn", "mbtzrqtwoxn", "mbtzrqthreexn",
				"mbtzrqfourxn", "mbwcqkonexn", "mbwcqktwoxn", "mbwcqkthreexn",
				"zdyjonexn", "zdyjtwoxn", "zdyjthreexn", "yjhfrqonexn",
				"yjhfrqtwoxn", "yjhfrqthreexn", "bzronexn", "bzrtwoxn",
				"bzrthreexn", "bzrfourxn", "hjqk", "xxqk", "xjysjl", "shsjqk",
				"gzjl", "grtc", "rxnf", "xm", "xb", "csrq", "zzmmmc", "mzmc",
				"bjmc", "jg","zyfx","zymb" };
		rs = dao.sxjyQueryOne(tableName, colList, "xh", xh);
		// 存入几年级生的变量
		rs.put("jnjs", jnjs);
		if (null == rs.get("xh") || rs.get("xh").equalsIgnoreCase("")) {
			colList = new String[] { "xh", "xm", "xb", "csrq", "bjmc",
					"zzmmmc", "mzmc", "jg" };
			rs = dao.sxjyQueryOne3("view_xsxxb", colList, "xh", xh, rs, "");
		}
		return rs;
	}

	public HashMap<String, String> getXssysjAll(String xh) {
		// 得到学生生涯学生信息
		HashMap<String, String> rs = new HashMap<String, String>();
		String tableName = "view_jskd_xssyxxb";
		String[] colList = new String[] { "xh", "lydq", "grjl", "zwfx",
				"zwfzgh", "ndmbonexn", "ndmbtwoxn", "ndmbthreexn",
				"ndmbfourxn", "zdyj", "lrrq", "yjhfrq", "mbtztwoxn",
				"mbtzthreexn", "mbtzfourxn", "mbtzrqtwoxn", "mbtzrqthreexn",
				"mbtzrqfourxn", "mbwcqkonexn", "mbwcqktwoxn", "mbwcqkthreexn",
				"zdyjonexn", "zdyjtwoxn", "zdyjthreexn", "yjhfrqonexn",
				"yjhfrqtwoxn", "yjhfrqthreexn", "bzronexn", "bzrtwoxn",
				"bzrthreexn", "bzrfourxn", "hjqk", "xxqk", "xjysjl", "shsjqk",
				"gzjl", "grtc", "rxnf", "xm", "xb", "csrq", "zzmmmc", "mzmc",
				"bjmc", "jg","zyfx","zymb" };
		rs = dao.sxjyQueryOne(tableName, colList, "xh", xh);
		return rs;
	}

	public boolean saveXssysj(String xh, String jnjs, XssyModel myModel,
			String userName, HttpServletRequest request) throws Exception {
		// 得到学生生涯学生信息

		String tableName = "sxjy_jskd_xssyxxb";
		String nowTime = GetTime.getNowTime();
		boolean inserted = false;
		if (jnjs.equalsIgnoreCase("1")) {
			myModel.setXh(xh);
			String[] colList = new String[] { "xh", "lydq", "grjl", "zwfx",
					"zwfzgh", "ndmbonexn", "ndmbtwoxn", "ndmbthreexn",
					"ndmbfourxn", "zdyj","zyfx","zymb" };
			// 学号 来源地区 自我分析 自我发展规划
			String[] inputList = ModelToStrings
					.modelToStrings(colList, myModel);
			inserted = StandardOperation.delete(tableName, "xh", xh, request);
			if (inserted) {
				inserted = StandardOperation.insert(tableName, colList,
						inputList, request);
			}
		} else if (jnjs.equalsIgnoreCase("2")) {
			String[] columns = new String[] { "mbwcqkonexn", "mbtztwoxn",
					"mbtzrqtwoxn","zyfx","zymb" };
			String[] values = ModelToStrings.modelToStrings(columns, myModel);
			values[2] = nowTime;
			// 先看看有没有数据

			inserted = StandardOperation.update(tableName, columns, values,
					"xh", xh, request);

		} else if (jnjs.equalsIgnoreCase("3")) {
			String[] columns = new String[] { "mbwcqktwoxn", "mbtzthreexn",
					"mbtzrqthreexn","zyfx","zymb"  };
			String[] values = ModelToStrings.modelToStrings(columns, myModel);
			values[2] = nowTime;
			inserted = StandardOperation.update(tableName, columns, values,
					"xh", xh, request);
		} else if (jnjs.equalsIgnoreCase("4")) {
			String[] columns = new String[] { "mbwcqkthreexn", "mbtzfourxn",
					"mbtzrqfourxn","zyfx","zymb"  };
			String[] values = ModelToStrings.modelToStrings(columns, myModel);
			values[2] = nowTime;
			inserted = StandardOperation.update(tableName, columns, values,
					"xh", xh, request);
		} else if (jnjs.equalsIgnoreCase("js1")) {
			String[] columns = new String[] { "zdyj", "bzronexn", "yjhfrq" };
			String[] values = ModelToStrings.modelToStrings(columns, myModel);
			values[2] = nowTime;
			values[1] = userName;
			inserted = StandardOperation.update(tableName, columns, values,
					"xh", xh, request);
		} else if (jnjs.equalsIgnoreCase("js2")) {
			String[] columns = new String[] { "zdyjonexn", "bzrtwoxn",
					"yjhfrqonexn" };
			String[] values = ModelToStrings.modelToStrings(columns, myModel);
			values[2] = nowTime;
			values[1] = userName;
			inserted = StandardOperation.update(tableName, columns, values,
					"xh", xh, request);
		} else if (jnjs.equalsIgnoreCase("js3")) {
			String[] columns = new String[] { "zdyjtwoxn", "bzrthreexn",
					"yjhfrqtwoxn" };
			String[] values = ModelToStrings.modelToStrings(columns, myModel);
			values[2] = nowTime;
			values[1] = userName;
			inserted = StandardOperation.update(tableName, columns, values,
					"xh", xh, request);
		} else if (jnjs.equalsIgnoreCase("js4")) {
			String[] columns = new String[] { "zdyjthreexn", "bzrfourxn",
					"yjhfrqthreexn" };
			String[] values = ModelToStrings.modelToStrings(columns, myModel);
			values[2] = nowTime;
			values[1] = userName;
			inserted = StandardOperation.update(tableName, columns, values,
					"xh", xh, request);
		}
		return inserted;
	}

	private String getNjForXh(String xh) {
		// 通过学号得到年级
		String nj = dao.sxjyQueryOne2("view_xsjbxx", new String[] { "nj" },
				"xh", xh)[0];
		return nj;
	}

	public ArrayList<String[]> getXssyList(XssyModel myModel) {
		// 得到已填写生涯学生列表
		String tableName = "view_jskd_xssyxxb";
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		// String filter = myModel.getFilter();
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, "", "", ""));

		String[] colList = new String[] { "xh", "xh", "xm", "xb", "xymc",
				"zymc", "bjmc", "lrrq" };
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(),
				new String[] {}, colList, "");
		return rs;
	}

	public List getXsxyTopTr() {
		// 得到学生生涯列表表头
		DAO dao = DAO.getInstance();
		String tableName = "view_jskd_xssyxxb";
		String[] colList = new String[] { "xh", "xh", "xm", "xb", "xymc",
				"zymc", "bjmc", "lrrq" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}
	
	public List getDyxxTopTr() {
		// 得到党团信息列表表头
		DAO dao = DAO.getInstance();
		String tableName = "view_zgkd_dyxx";
		String[] colList = new String[] { "xh","xh","xm","xymc",
				"zymc", "bjmc", "xzzt" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}

	/**
	 * 判断学生是否填写过学生生涯设计的内容
	 * 
	 * @param xh
	 * @return
	 */
	public boolean isStuAlreadyWriteSj(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select xh from sxjy_jskd_xssyxxb where xh=?";
		return dao.getOneRs(sql, new String[] { xh }, new String[] { "xh" }) == null ? false
				: true;
	}

	/**
	 * 返回条件列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getFilterCondi() {
		return mydao.getFilterCondi();
	}

	public ArrayList<String[]> getFilterList(XssyModel myModel) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String filter = myModel.getFilter();
		String tableName = "view_jskd_xssyxxb";
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, "", "", ""));
		String[] colList = new String[] { "xh", "xh", "xm", "xb", "xymc",
				"zymc", "bjmc", "lrrq" };
		if (filter.equals("0")) { // 已填
			return dao.sxjyQuery(tableName, query.toString(), new String[] {},
					colList, "");
		} else if (filter.equals("1")) { // 全部
			StringBuffer sb1 = new StringBuffer();
			sb1
					.append("select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,b.LRRQ from");
			sb1.append("(select xh,xm,xb,xymc,zymc,bjmc from view_xsjbxx ");
			sb1.append(SearchUtils.makeQueryCondition(xydm, zydm, bjdm, "", xh,
					xm, nj, "", "", ""));
			sb1.append(") a left join  view_jskd_xssyxxb b on a.xh = b.xh");
			// System.out.println(sb1.toString());
			return dao.sxjyQuery("", "", new String[] {}, colList, sb1
					.toString());
		} else if (filter.equals("2")) { // 未填
			StringBuffer sb2 = new StringBuffer();
			sb2
					.append("select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,b.LRRQ from");
			sb2
					.append("(select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc from view_xsjbxx a,");
			sb2.append("(select xh from view_xsjbxx ");
			sb2.append(SearchUtils.makeQueryCondition(xydm, zydm, bjdm, "", xh,
					xm, nj, "", "", ""));
			sb2.append(" minus ");
			sb2.append("select xh from view_jskd_xssyxxb ");
			sb2.append(SearchUtils.makeQueryCondition(xydm, zydm, bjdm, "", xh,
					xm, nj, "", "", ""));
			sb2
					.append(") b where a.XH = b.xh) a left join  view_jskd_xssyxxb b on a.xh = b.xh");
			// System.out.println(sb2.toString());
			return dao.sxjyQuery("", "", new String[] {}, colList, sb2
					.toString());
		} else if (filter.equals("3")) { // 已回复
			ArrayList<String[]> tempList = new ArrayList<String[]>();
			StringBuffer sb3 = new StringBuffer();
			sb3
					.append("select xh,nj,ZDYJ,ZDYJONEXN,ZDYJTWOXN,ZDYJTHREEXN,xm,xb,xymc,zymc,bjmc,lrrq from view_jskd_xssyxxb ");
			sb3.append(SearchUtils.makeQueryCondition(xydm, zydm, bjdm, "", xh,
					xm, nj, "", "", ""));
			// System.out.println(sb3.toString());
			String[] outputArray = new String[] { "xh", "nj", "ZDYJ",
					"ZDYJONEXN", "ZDYJTWOXN", "ZDYJTHREEXN", "xm", "xb",
					"xymc", "zymc", "bjmc", "lrrq" };
			rs = dao.sxjyQuery("", "", new String[] {}, outputArray, sb3
					.toString()); // 进一步删
			String currXn = Base.currXn;
			String tmp = currXn.substring(5, 9);
			for (String[] strArr : rs) { // 年级
				String jnjs = ((Integer) (Integer.parseInt(tmp) - Integer
						.parseInt(strArr[1]))).toString();
				if ((jnjs.equals("1") && !StringUtils.isNull(strArr[2]))
						|| (jnjs.equals("2") && !StringUtils.isNull(strArr[3]))
						|| (jnjs.equals("3") && !StringUtils.isNull(strArr[4]))
						|| (jnjs.equals("4") && !StringUtils.isNull(strArr[5]))) {
					String[] toArray = new String[colList.length];
					toArray[0] = strArr[0];
					toArray[1] = strArr[0];
					System.arraycopy(strArr, 6, toArray, 2, colList.length - 2);
					tempList.add(toArray);
				}
			}
			return tempList;
		} else if (filter.equals("4")) { // 未回复
			ArrayList<String[]> tempList = new ArrayList<String[]>();
			StringBuffer sb4 = new StringBuffer();
			sb4
					.append("select xh,nj,ZDYJ,ZDYJONEXN,ZDYJTWOXN,ZDYJTHREEXN,xm,xb,xymc,zymc,bjmc,lrrq from view_jskd_xssyxxb ");
			sb4.append(SearchUtils.makeQueryCondition(xydm, zydm, bjdm, "", xh,
					xm, nj, "", "", ""));
			String[] outputArray = new String[] { "xh", "nj", "ZDYJ",
					"ZDYJONEXN", "ZDYJTWOXN", "ZDYJTHREEXN", "xm", "xb",
					"xymc", "zymc", "bjmc", "lrrq" };
			rs = dao.sxjyQuery("", "", new String[] {}, outputArray, sb4
					.toString()); // 进一步删
			String currXn = Base.currXn;
			String tmp = currXn.substring(5, 9);
			for (String[] strArr : rs) { // 年级
				String jnjs = ((Integer) (Integer.parseInt(tmp) - Integer
						.parseInt(strArr[1]))).toString();
				if ((jnjs.equals("1") && StringUtils.isNull(strArr[2]))
						|| (jnjs.equals("2") && StringUtils.isNull(strArr[3]))
						|| (jnjs.equals("3") && StringUtils.isNull(strArr[4]))
						|| (jnjs.equals("4") && StringUtils.isNull(strArr[5]))) {
					String[] toArray = new String[colList.length];
					toArray[0] = strArr[0];
					toArray[1] = strArr[0];
					System.arraycopy(strArr, 6, toArray, 2, colList.length - 2);
					tempList.add(toArray);
				}
			}
			return tempList;
		}
		return rs;
	}
	
	public ArrayList<String[]> getDyxxList(XssyModel myModel,XssyForm dataSearchForm) {
		//	     武汉理工 马克思主义理论学习研究会分会基本信息
//		String tableName     = "view_zgkd_dyxx";
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xzzt         = DealString.toGBK(myModel.getXzzt());
		String xydm         = DealString.toGBK(myModel.getXydm());
		String xm           = DealString.toGBK(myModel.getXm());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		String xh         = DealString.toGBK(myModel.getXh());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"",xh,xm,nj,"","",""));
		if(xzzt != null && !("".equalsIgnoreCase(xzzt.trim()))){
			query.append(" and xzzt like '%");
			query.append(xzzt);
			query.append("%' ");
		}
		String[] colList = new String[] { "xh","xh","xm","xymc","zymc", "bjmc", "xzzt" };
		String sql = "select * from (select * from (select distinct xh,rownum r,xm,xymc,zymc, bjmc, xzzt from view_zgkd_dyxx "
			+ query.toString()
			+ " ) where r<="
			+ (dataSearchForm.getPages().getStart() + dataSearchForm
					.getPages().getPageSize())
			+ ") where r>"
			+ dataSearchForm.getPages().getStart();
		
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from view_zgkd_dyxx " + query.toString();
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));
		return rs;
	}
	
	public ArrayList<String[]> getByDyxxList(XssyModel myModel,XssyForm dataSearchForm) {
//		String tableName = "view_zgkd_dyxx";
		// TODO
		ArrayList<String[]> rs = new ArrayList<String[]>();
		DAO dao = DAO.getInstance();
		String xzzt = DealString.toGBK(myModel.getXzzt());
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, "", "", ""));
		if (xzzt != null && !("".equalsIgnoreCase(xzzt.trim()))) {
			query.append(" and xzzt like '%");
			query.append(xzzt);
			query.append("%' ");
		}
		String[] colList = new String[] { "xh", "xh", "xm", "xymc", "zymc",
				"bjmc", "xzzt" };

		String sql = "select * from (select * from (select rownum r,a.* from view_zgkd_dyxx a, bysdasqb b, (select a.* from bysdasqb a"
				+ " right join (select b.xh, round(to_number(sysdate - to_date(b.bysj, 'YYYY-MM-DD'))) tt"
				+ " from bysdasqb b) b on a.xh = b.xh where b.tt > 730) c ";
		sql += query;
		sql+="and a.xh = b.xh and c.xh = b.xh";
		sql+= " ) where r<="
		+ (dataSearchForm.getPages().getStart() + dataSearchForm
				.getPages().getPageSize())
		+ ") where r>"
		+ dataSearchForm.getPages().getStart();

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from view_zgkd_dyxx " + query.toString();
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));
		return rs;
	}
	
	public HashMap<String, String> getDyxxOne(String pk,String xh) {
		//   党员信息
		String tableName = "view_zgkd_dyxx";
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "rdsqsj", "qdwjjfzsj",
				"fzdxsj", "rdsj", "zzsj", "sfaqzz", "yjysjcsj", "dyzzgxzrsj",
				"txsj", "bz", "xzzt", "zzgxszdzb", "jrdzblx", "zzqk", "tsdfjn",
				"jg", "sfzh", "mzmc", "csrq" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		colList = new String []{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jg","mzmc","sfzh","csrq"};
		if(!xh.equalsIgnoreCase("")) {
			rs = dao.sxjyQueryOne3("view_xsxxb", colList, "xh", xh, rs, "");
		}
		return rs;
	}

	public Object getXzztList() {
		return mydao.getXzztList();
	}

	public boolean updataDyxx(XssyModel myModel, String pk, HttpServletRequest request) throws Exception {
		//	   更新党员信息
		String tableName       = "zgkd_dyxxb";
		String pkComment       = "xh";
		String[] colList = new String[] { "xh", "xzzt", "rdsqsj", "qdwjjfzsj",
				"fzdxsj", "rdsj", "zzsj", "sfaqzz", "yjysjcsj", "dyzzgxzrsj",
				"bz", "txsj", "zzgxszdzb", "jrdzblx", "zzqk", "tsdfjn" }; 
		myModel.setTxsj(((Integer)(Integer.parseInt(myModel.getRdsj())+10000)).toString());
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public boolean deleteDyxxOne(String pk, HttpServletRequest request) throws Exception {
		// 党员信息删除
		String tableName       = "zgkd_dyxxb";
		String pkComment       = "xh";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}

	public ArrayList<String[]> getYbdyzzList() {
		//	  应转正预备党员信息
		String tableName = "view_zgkd_dyxx";
		String[] colList = new String[] { "xh","xh","xymc","zymc", "bjmc", "xzzt","rdsqsj","rdsj","txsj"};
		String query     = " where xzzt = '预备党员' and txsj < to_char(sysdate,'yyyymmdd')";
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query, new String []{}, colList, "");
		return rs;
	}
	
	public List getYbdyzzTorList() {
		//	  应转正预备党员信息
		DAO dao = DAO.getInstance();
		String tableName = "view_zgkd_dyxx";
		String[] colList = new String[] { "xh","xh","xymc","zymc", "bjmc", "xzzt","rdsqsj","rdsj","txsj"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}

	public boolean updataYbdyzz(XssyModel myModel, String pk, HttpServletRequest request) throws Exception {
		// 预备党员转正
		String tableName       = "zgkd_dyxxb";
		String pkComment       = "xh";
		String [] colList      = new String []{"zzsj","bz","txsj","xzzt"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.update(tableName, colList, inputList, pkComment, pk, request);
		return inserted;
	}
	
	/**
	 * @describe 获得表头
	 * @author luo
	 */
	public List getRdsqTopTr() {
		DAO dao = DAO.getInstance();
		String tableName = "view_zgkd_rdsq";
		String[] colList = new String[] { "主键","xh", "xm", "nj",
				"bjmc", "djsqsj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		return topTr;
	}
	
	/**
	 * @describe 入党申请查询
	 * @author luo
	 */
	public ArrayList<String[]> getRdsqList(XssyModel myModel,
			XssyForm dataSearchForm) {

		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		String dqzt = myModel.getDqzt();

		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, "", "", ""));

		String[] colList = new String[] { "主键","xh", "xm", "nj",
				"bjmc", "djsqsj" };
		StringBuffer tempSb = new StringBuffer("");
		String pk = "xh";
		String nowTime = dao.getOneRs(
				"select to_char(SYSDATE,'YYYYMMDD') nowTime from dual",
				new String[] {}, "nowTime");
		if (dqzt != null && !"".equals(dqzt)) {
			if ("fzdx".equals(dqzt)) {
				query.append(" and fzdxsj <" + nowTime);
			}
			if ("rdjjfz".equals(dqzt)) {
				query.append(" and (fzdxsj >" + nowTime +" or fzdxsj is null)");
				query.append(" and rdjjfzsj <" + nowTime);
			}
		}
		
		tempSb.append("select * from (select ");
		tempSb.append(pk);
		tempSb.append(" 主键,rownum r,a.* from ");
		tempSb.append("view_zgkd_rdsq");
		tempSb.append(" a");
		tempSb.append(query);
		tempSb.append(" ) where r<=");
		tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
		tempSb.append(" and r>");
		tempSb.append(dataSearchForm.getPages().getStart());

		String sql = tempSb.toString();
		
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from view_zgkd_rdsq " + query;
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}
	
	/**
	 * 事务处理批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delRdsq(String[] keys) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]).trim();// 得到主键
			sql = "delete from zgkd_rdsqb where xh = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
}
