package xgxt.action;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;
import common.Globals;
import common.GlobalsVariable;

import java.awt.*;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDropDownList;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.Arrays2;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.dao.GyglShareDAO;

public class StatisicalAction extends Action {
	// DAO dao = DAO.getInstance();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		CommanForm chkUser = (CommanForm) form;
		try {
			// int i = ChkTimeOut.chkTimeOut(mapping, form, request,
			// response);
			// if (i <= 2) {
			// chkUser.setErrMsg("登陆超时，请重新登陆！");
			// System.out.println(i);
			// return new ActionForward("/login.jsp", false);
			// }

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();
			if (myAct.equalsIgnoreCase("STATISICALINIT")) {
				myActFwd = statisicalInit(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("STATISICAL")) { // 数据操作通用模块－统计
				myActFwd = statisical(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("ANALYSE")) {
				myActFwd = analyse(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("PRISEANALYSEINIT")) {
				myActFwd = priseAnalyseInit(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("PRISEANALYSEDO")) {
				myActFwd = priseAnalyse(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("DATASTATISICAL")) {
				myActFwd = dataStatisical(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("DATAQUERY")) {
				myActFwd = dataQueryInit(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("DODATAQUERY")) {
				myActFwd = doDataQuery(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("DATAEXPORT")) {
				myActFwd = dataExport(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("DATAEXPORTFORQ")) {
				myActFwd = dataExportForQ(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("obtainEmploy_stati")){
				myActFwd = ahgcjyglBysxxSb(mapping, form, request, response); //浙江工商近三年签约情况对比统计
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	// 数据统计--通用模块
	private ActionForward statisicalInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward myActFwd = null;
		DAO dao = DAO.getInstance();
		CommanForm statisicalForm = (CommanForm) form;
		String mkName = request.getParameter("mkName");
		String tjxm = statisicalForm.getTjxm();
		tjxm = DealString.toGBK(tjxm);
		String jtxm = statisicalForm.getJtxm();
		String zxm = statisicalForm.getZxm();
		String conditionSqlText = DealString.toGBK(statisicalForm
				.getConditionSqlText());
		String conditionSqlValue = DealString.toGBK(statisicalForm
				.getConditionSqlValue());
		statisicalForm.setConditionSqlText(conditionSqlText);
		statisicalForm.setConditionSqlValue(conditionSqlValue);
		String sql = "";
		String cols = "";
		String[] getValue = null;
		sql = "select distinct tjxm from tjb where ssmk='" + mkName + "'";
		String[] inputSQLGroup = {};
		String[] outputSQLGroup = { "tjxm" };
		List tjxmList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
		request.setAttribute("tjxmList", tjxmList);
		if ((tjxm != null) && !tjxm.equals("")) {
			sql = "select distinct jtxmzdm,jtxmzdmcn from tjb where tjxm='"
				+ tjxm + "' and ssmk='" + mkName + "'";
			String[] inputSQLGroup1 = {};
			String[] outputSQLGroup1 = { "jtxmzdm", "jtxmzdmcn" };
			List jtxmList = dao.getList(sql, inputSQLGroup1, outputSQLGroup1);
			request.setAttribute("jtxmList", jtxmList);
			statisicalForm.setTjxm(tjxm);
			String[] inputSQLGroup7 = {};
			String[] outputSQLGroup7 = { "jtxmzdmTit", "jtxmzdmcnTit" };
			List<HashMap<String, String>> jtxmListTit = null;
			ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
			cols = dao.getOneRs(
					"select tjzd from tjzdxxb where ssmk=? and tjxm=?",
					new String[] { mkName, tjxm }, "tjzd");
			if (!("".equalsIgnoreCase(cols) || cols == null)) {
				String[] Vcols = cols.split("!!SplitSignOne!!");
				String linkVcols = "";
				for (int i = 0; i < Vcols.length; i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					linkVcols += "'";
					linkVcols += Vcols[i];
					if (i < Vcols.length - 1) {
						linkVcols += "',";
					} else {
						linkVcols += "'";
					}
					String[] tmp = Vcols[i].split("-");
					if (tmp.length != 1) {
						map.put("jtxmzdmTit", Vcols[i]);
						map.put("jtxmzdmcnTit", "√" + tmp[1]);
						arrayList.add(map);
					}
				}
				sql = "select * from (select distinct xsthzd||'-'||jtxmzdmcn jtxmzdmTit,'×'||jtxmzdmcn jtxmzdmcnTit from tjb where tjxm='"
					+ tjxm
					+ "' and ssmk='"
					+ mkName
					+ "' and zxmczbj='0' union select distinct xsthzd||'-'||zxmzdmcn jtxmzdmTit,'×'||zxmzdmcn jtxmzdmcnTit from tjb where tjxm='"
					+ tjxm
					+ "' and ssmk='"
					+ mkName
					+ "' and zxmczbj='1') where jtxmzdmTit not in ("
					+ linkVcols + ")";
				jtxmListTit = dao.getList(sql, inputSQLGroup7, outputSQLGroup7,arrayList);
			} else {
				sql = "select distinct xsthzd||'-'||jtxmzdmcn jtxmzdmTit,'×'||jtxmzdmcn jtxmzdmcnTit from tjb where tjxm='"
					+ tjxm
					+ "' and ssmk='"
					+ mkName
					+ "' and zxmczbj='0' union select distinct xsthzd||'-'||zxmzdmcn jtxmzdmTit,'×'||zxmzdmcn jtxmzdmcnTit from tjb where tjxm='"
					+ tjxm + "' and ssmk='" + mkName + "' and zxmczbj='1'";
				jtxmListTit = dao.getList(sql, inputSQLGroup7, outputSQLGroup7);
			}
			request.setAttribute("jtxmListTit", jtxmListTit);
			sql = "select distinct tjxmdyb from tjb where tjxm=? and ssmk=?";
			getValue = dao.getOneRs(sql, new String[] { tjxm, mkName },new String[] { "tjxmdyb" });
			if (getValue != null) {
				statisicalForm.setTjxmdyb(getValue[0]);
			}
		}
		if ((tjxm != null) && !tjxm.equals("") && (jtxm != null)
				&& !jtxm.equals("")) {
			sql = "select distinct zxmczbj from tjb where jtxmzdm=? and tjxm=? and ssmk=?";
			getValue = dao.getOneRs(sql, new String[] { jtxm, tjxm, mkName },
					new String[] { "zxmczbj" });
			if (getValue != null) {
				if (Integer.parseInt(getValue[0]) == 1) {
					sql = "select distinct zxmzdm,zxmzdmcn,tjtjzdm from tjb where jtxmzdm='"
						+ jtxm
						+ "' and tjxm='"
						+ tjxm
						+ "' and ssmk='"
						+ mkName + "'" + " order by TJTJZDM";
					String[] inputSQLGroup2 = {};
					String[] outputSQLGroup2 = { "zxmzdm", "zxmzdmcn" };
					List zxmList = dao.getList(sql, inputSQLGroup2,
							outputSQLGroup2);
					request.setAttribute("zxmList", zxmList);
				} else {
					sql = "select distinct jtxmbj,jtxmssb,tjtjzdm,tjtjzmc from tjb where jtxmzdm=? and tjxm=? and ssmk=?";
					getValue = dao.getOneRs(sql, new String[] { jtxm, tjxm,
							mkName }, new String[] { "jtxmbj", "jtxmssb",
							"tjtjzdm", "tjtjzmc" });
					if (getValue != null) {
						if (Integer.parseInt(getValue[0]) == 1) {
							sql = "select distinct " + getValue[2]
							                                    + " tjtjzdm," + getValue[3]
							                                                             + " tjtjzmc from " + getValue[1]
							                                                                                           + " order by tjtjzdm";
							String[] inputSQLGroup3 = {};
							String[] outputSQLGroup3 = { "tjtjzdm", "tjtjzmc" };
							List tjtjzList = dao.getList(sql, inputSQLGroup3,
									outputSQLGroup3);
							request.setAttribute("tjtjzList", tjtjzList);
						} else if (Integer.parseInt(getValue[0]) == 2) {
							sql = "select distinct tjtjzdm,tjtjzmc from tjb where jtxmzdm=? and tjxm=? and ssmk=? order by tjtjzdm";
							String[] inputSQLGroup4 = { jtxm, tjxm, mkName };
							String[] outputSQLGroup4 = { "tjtjzdm", "tjtjzmc" };
							List tjtjzList = dao.getList(sql, inputSQLGroup4,
									outputSQLGroup4);
							request.setAttribute("tjtjzList", tjtjzList);
						} else if (Integer.parseInt(getValue[0]) == 3) {
							ArrayList<HashMap<String, String>> tjtjzList = new ArrayList<HashMap<String, String>>();
							String xn = null;
							HashMap<String, String> map = new HashMap<String, String>();
							int currentNd = Integer.parseInt(DealString
									.getDateTime().substring(0, 4));
							// int currentNd = (new Date()).getYear() + 1900;
							for (int i = currentNd - 5; i < currentNd + 5; i++) {
								map = new HashMap<String, String>();
								xn = String.valueOf(i) + "-"
								+ String.valueOf(i + 1);
								map.put("tjtjzdm", xn);
								map.put("tjtjzmc", xn);
								tjtjzList.add(map);
							}
							request.setAttribute("tjtjzList", tjtjzList);
						} else if (Integer.parseInt(getValue[0]) == 4) {
							ArrayList<HashMap<String, String>> tjtjzList = new ArrayList<HashMap<String, String>>();
							String nd = null;
							HashMap<String, String> map = new HashMap<String, String>();
							int currentNd = Integer.parseInt(DealString.getDateTime().substring(0, 4));
							// int currentNd = (new Date()).getYear() + 1900;
							for (int i = currentNd - 5; i < currentNd + 5; i++) {
								map = new HashMap<String, String>();
								nd = String.valueOf(i);
								map.put("tjtjzdm", nd);
								map.put("tjtjzmc", nd);
								tjtjzList.add(map);
							}
							request.setAttribute("tjtjzList", tjtjzList);
						}
					}
				}
			}
		}
		if ((tjxm != null) && !tjxm.equals("") && (jtxm != null)
				&& !jtxm.equals("") && (zxm != null) && !zxm.equals("")) {
			sql = "select distinct zxmbj,zxmssb,tjtjzdm,tjtjzmc from tjb where zxmzdm=? and jtxmzdm=? and tjxm=? and ssmk=?";
			getValue = dao.getOneRs(sql,
					new String[] { zxm, jtxm, tjxm, mkName }, new String[] {
					"zxmbj", "zxmssb", "tjtjzdm", "tjtjzmc" });
			if (getValue != null) {
				if (Integer.parseInt(getValue[0]) == 1) {
					sql = "select distinct " + getValue[2] + " tjtjzdm,"
					+ getValue[3] + " tjtjzmc from " + getValue[1]
					                                            + " tjtjzdm";
					String[] inputSQLGroup5 = {};
					String[] outputSQLGroup5 = { "tjtjzdm", "tjtjzmc" };
					List tjtjzList = dao.getList(sql, inputSQLGroup5,
							outputSQLGroup5);
					request.setAttribute("tjtjzList", tjtjzList);
				} else if (Integer.parseInt(getValue[0]) == 2) {
					sql = "select distinct tjtjzdm,tjtjzmc from tjb where zxmzdm='"
						+ zxm
						+ "' and jtxmzdm='"
						+ jtxm
						+ "' and tjxm='"
						+ tjxm
						+ "' and ssmk='"
						+ mkName
						+ "' order by tjtjzdm";
					String[] inputSQLGroup6 = {};
					String[] outputSQLGroup6 = { "tjtjzdm", "tjtjzmc" };
					List tjtjzList = dao.getList(sql, inputSQLGroup6,
							outputSQLGroup6);
					request.setAttribute("tjtjzList", tjtjzList);
				}
			}
		}
		statisicalForm.setCols(cols);
		request.setAttribute("xmV", tjxm);
		request.setAttribute("mkV", mkName);
		String titName = request.getParameter("titName");
		if(titName!=null&&!titName.equalsIgnoreCase("")){
			request.setAttribute("title",titName);
		}else{
			FormModleCommon.commonRequestSet(request);
		}
		myActFwd = mapping.findForward("success");
		return myActFwd;
	}

	private ActionForward statisical(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward myActFwd = null;
		DAO dao = DAO.getInstance();
		CommanForm statisicalForm = (CommanForm) form;
		String conditionSqlValue = DealString.toGBK(statisicalForm
				.getConditionSqlValue());
		String cols = DealString.toGBK(statisicalForm.getCols());
		//2010.7.16 quph
		cols=cols.replace("	", "");
		//-----end------
		String ssmk = request.getParameter("mkV");
		String tjxm = DealString.toGBK(request.getParameter("xmV"));
		String tag = dao.returntag(
				"select * from tjzdxxb where ssmk=? and tjxm=?", new String[] {
						ssmk, tjxm });
		
		if ("empty".equalsIgnoreCase(tag)) {
			dao.runUpdate(" insert into tjzdxxb(ssmk,tjxm,tjzd)values(?,?,?)",
					new String[] { ssmk, tjxm, cols });
			// StandardOperation.insert("tjzdxxb", new
			// String[]{"ssmk","tjxm","tjzd"}, new String[]{ssmk,tjxm,cols},
			// request);
		} else {
			dao.runUpdate(" update tjzdxxb set tjzd=? where ssmk||tjxm=?",
					new String[] { cols, ssmk + tjxm });
			// StandardOperation.update("tjzdxxb", new String[]{"tjzd"}, new
			// String[]{cols}, "ssmk||tjxm", ssmk+tjxm, request);
		}
		String tjxmdyb = statisicalForm.getTjxmdyb();
		String dybj = request.getParameter("dybj");
		String strColName = "";
		String strColNameCN = "";
		String colsArray[] = cols.split("!!SplitSignOne!!");
		String conditionSqlArray[] = conditionSqlValue.replace(",", " ").split("!!SplitSignOne!!");
		String sql = "select ";
		String colsSql = "";
		String fmSql = "";
		String groupSql = " group by ";
		String orderSql = " order by ";
		int statementFlag = 0;
		int groupFlag = 0;
		for (int j = 0; j < conditionSqlArray.length; j++) {
			fmSql = fmSql + conditionSqlArray[j] + " ";
		}

		boolean rsExist = false;// 请求页面上选中显示字段“数量”时
		for (int i = 0; i < colsArray.length; i++) {
			if (colsArray[i].equals("rs")) {
				rsExist = true;
			}
		}
		if (!rsExist) {
			if (statementFlag == 1) {
				colsSql = colsSql + ",";
			}
			colsSql = colsSql + "count(*) rs ";
			if ((strColNameCN != null) && !strColNameCN.equals("")) {
				strColNameCN = strColNameCN + "!!SplitSignOne!!";
			}
			if ((strColName != null) && !strColName.equals("")) {
				strColName = strColName + "!!SplitSignOne!!";
			}
			statementFlag = 1;
		}

		for (int i = 0; i < colsArray.length; i++) {
			if (colsArray[i].equals("rs")) {// 请求页面上未选中显示字段“数量”时
				if (statementFlag == 1) {
					colsSql = colsSql + ",";
				}
				colsSql = colsSql + "count(*) rs ";
				if ((strColNameCN != null) && !strColNameCN.equals("")) {
					strColNameCN = strColNameCN + "!!SplitSignOne!!";
				}
				if ((strColName != null) && !strColName.equals("")) {
					strColName = strColName + "!!SplitSignOne!!";
				}
				strColNameCN = strColNameCN + "数量";
				strColName = strColName + "rs";
				statementFlag = 1;
			} else if (colsArray[i].equals("bl")) {
				if (statementFlag == 1) {
					colsSql = colsSql + ",";
				}
				colsSql = colsSql + "to_number(case (select count(*) from "
				+ tjxmdyb + " where ";
				colsSql = colsSql + fmSql;
				colsSql = colsSql
				+ ") when 0 then 0 else round((count(*)/(select count(*) from "
				+ tjxmdyb + " where " + fmSql;
				colsSql = colsSql + "))*100,2) end) bl ";
				if ((strColNameCN != null) && !strColNameCN.equals("")) {
					strColNameCN = strColNameCN + "!!SplitSignOne!!";
				}
				if ((strColName != null) && !strColName.equals("")) {
					strColName = strColName + "!!SplitSignOne!!";
				}
				strColNameCN = strColNameCN + "比例(%)";
				strColName = strColName + "bl";
				statementFlag = 1;
			} else {
				if (statementFlag == 1) {
					colsSql = colsSql + ",";
				}
				colsSql = colsSql + colsArray[i].split("-")[0];
				statementFlag = 1;
				if (groupFlag == 1) {
					groupSql = groupSql + ",";
					orderSql = orderSql + ",";
				}
				groupSql = groupSql + colsArray[i].split("-")[0];
				orderSql = orderSql + colsArray[i].split("-")[0];
				if ((strColNameCN != null) && !strColNameCN.equals("")) {
					strColNameCN = strColNameCN + "!!SplitSignOne!!";
				}
				if ((strColName != null) && !strColName.equals("")) {
					strColName = strColName + "!!SplitSignOne!!";
				}
				strColNameCN = strColNameCN + colsArray[i].split("-")[1];
				strColName = strColName + colsArray[i].split("-")[0];
				groupFlag = 1;
			}
		}
		if (groupFlag == 0) {
			groupSql = "";
			orderSql = "";
		}
		if (cols.equals("rs!!SplitSignOne!!bl")) {
			colsSql = " count(*) rs,'100%' bl ";
		}
		if (cols.equals("bl!!SplitSignOne!!rs")) {
			colsSql = " '100%' bl,count(*) rs ";
		}
		if (cols.equals("rs")) {
			colsSql = " count(*) rs ";
		}
		if (cols.equals("bl")) {
			colsSql = " '100%' bl ";
		}
		if ((dybj != null) && !dybj.equals("")) {
			sql = sql + colsSql + " from " + tjxmdyb + " a , view_dybj b "
			+ " where a.xh=b.xh1 and b.dybj=1 and " + fmSql + groupSql
			+ orderSql;
		} else {
			if (" view_zjlg_xsrychxx".equalsIgnoreCase(tjxmdyb) || " view_jxjzjlg".equalsIgnoreCase(tjxmdyb)) {
				fmSql += " and xxsh='通过' ";
			} 
			sql = sql + colsSql + " from " + tjxmdyb + " where " + fmSql
			+ groupSql + orderSql;
		}
		String ColName[] = strColName.split("!!SplitSignOne!!");
		String ColNameCN[] = strColNameCN.split("!!SplitSignOne!!");
		Vector<Object> rs = new Vector<Object>();
		System.out.println(sql);
		rs.addAll(dao.rsToVator(sql, new String[] {}, ColName));
		int rsNum = rs.size();
		List topTr = dao.arrayToList(ColName, ColNameCN);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", Integer.toString(rsNum));
		request.setAttribute("topTr", topTr);
		statisicalForm.setTjxm(DealString.toGBK(statisicalForm.getTjxm()));
		statisicalForm.setCols(DealString.toGBK(statisicalForm.getCols()));
		statisicalForm.setSql(sql);
		statisicalForm.setTableName(tjxmdyb);
		request.setAttribute("colNameCN", Arrays2.changeArrayToString(
				ColNameCN, "!!"));
		statisicalForm.setCheckVal(ColNameCN);
		myActFwd = mapping.findForward("success");
		return myActFwd;
	}

	private ActionForward analyse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward myActFwd = null;
		DAO dao = DAO.getInstance();
		CommanForm statisicalForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String sql = DealString.toGBK(statisicalForm.getSql());
		String tblx = DealString.toGBK(request.getParameter("tblx"));
		String yjxm = DealString.toGBK(request.getParameter("yjxm"));
		String tjxm = DealString.toGBK(statisicalForm.getTjxm());
		String[] fxxm = yjxm.split("-");
		sql = " select " + fxxm[0] + " ,sum(rs) rs from (" + sql;
		sql += " ) where " + fxxm[0] + " is not null group by " + fxxm[0]
		                                                               + " order by " + fxxm[0];
		ResultSet rs = dao.getRS(sql);
		JFreeChart chart = null;
		if (tblx.equals("bar")) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if ((yjxm != null) && !yjxm.equals("")) {
				while (rs != null && rs.next()) {
					dataset.addValue(Integer.parseInt(rs.getString("rs")), "",
							rs.getString(yjxm.split("-")[0]));
				}
			}
			chart = ChartFactory.createBarChart(tjxm + "分析",
					yjxm.split("-")[1], "人数", dataset,
					PlotOrientation.VERTICAL, true, false, false);
			chart.setBackgroundPaint(new Color(222, 222, 255));
			CategoryPlot plot = chart.getCategoryPlot();
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setLabelAngle(Math.PI / 2);
			CategoryAxis domainAxis = plot.getDomainAxis();
			plot.setRangeCrosshairValue(50.0, true);
			domainAxis.setLowerMargin(0.05);
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
			BarRenderer renderer = new BarRenderer();
			renderer.setSeriesPaint(0, new Color(0xffff));
			renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setItemLabelsVisible(true);
			ItemLabelPosition p = new ItemLabelPosition(
					org.jfree.chart.labels.ItemLabelAnchor.OUTSIDE12,
					TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT,
					-Math.PI / 2.0);
			renderer.setPositiveItemLabelPositionFallback(p);
			renderer.setMaximumBarWidth(0.1);
			plot.setRenderer(renderer);
			TextTitle title = chart.getTitle();
			title.setFont(new Font("汉真广标", Font.BOLD, 25));
			domainAxis.setLabelFont(new Font("汉真广标",Font.BOLD,14)); //水平底部标题 
			domainAxis.setTickLabelFont(new Font("汉真广标",Font.BOLD,12)); //垂直标题 
			rangeAxis.setLabelFont(new Font("汉真广标",Font.BOLD,15));
		} else if (tblx.equals("bar3D")) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if ((yjxm != null) && !yjxm.equals("")) {
				while (rs.next()) {
					dataset.addValue(Integer.parseInt(rs.getString("rs")), "",
							rs.getString(yjxm.split("-")[0]));
				}
			}
			chart = ChartFactory.createBarChart3D(tjxm + "分析",
					yjxm.split("-")[1], "人数", dataset,
					PlotOrientation.VERTICAL, true, false, false);
			chart.setBackgroundPaint(new Color(222, 222, 255));
			CategoryPlot plot = chart.getCategoryPlot();
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setLabelAngle(Math.PI / 2);
			CategoryAxis domainAxis = plot.getDomainAxis();
			plot.setRangeCrosshairValue(50.0, true);
			domainAxis.setLowerMargin(0.05);
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions
					.createUpRotationLabelPositions(Math.PI / 6.0));
			BarRenderer3D renderer = new BarRenderer3D();
			renderer.setSeriesPaint(0, new Color(162, 162, 162));
			renderer
			.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setItemLabelsVisible(true);
			ItemLabelPosition p = new ItemLabelPosition(
					org.jfree.chart.labels.ItemLabelAnchor.OUTSIDE12,
					TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT,
					-Math.PI / 2.0);
			renderer.setPositiveItemLabelPositionFallback(p);
			renderer.setMaximumBarWidth(0.1);
			plot.setRenderer(renderer);
			TextTitle title = chart.getTitle();
			title.setFont(new Font("汉真广标", Font.BOLD, 25));
			domainAxis.setLabelFont(new Font("汉真广标",Font.BOLD,14)); //水平底部标题 
			domainAxis.setTickLabelFont(new Font("汉真广标",Font.BOLD,12)); //垂直标题 
			rangeAxis.setLabelFont(new Font("汉真广标",Font.BOLD,15));
		} else if (tblx.equals("pie")) {
			DefaultPieDataset dataset = new DefaultPieDataset();
			if ((yjxm != null) && !yjxm.equals("")) {
				while (rs.next()) {
					dataset.setValue(rs.getString(yjxm.split("-")[0]), Integer
							.parseInt(rs.getString("rs")));
				}
			}
			chart = ChartFactory.createPieChart(tjxm + "分析", dataset, true,
					false, false);
//			chart   =   ChartFactory.createPieChart(combo.getText()   +   "调查结果",   //   chart  
//			//   title  
//			createPieDataset(),   //   data  
//			true,   //   include   legend  
//			true,   //   tooltips?  
//			false   //   URLs?  
//			);  
//			PiePlot   pie   =   (PiePlot)   (chart.getPlot());  


			chart.setBackgroundPaint(new Color(222, 222, 255));
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelGenerator(new   StandardPieSectionLabelGenerator("{0}={2}", 
					NumberFormat.getNumberInstance(),  
					new   DecimalFormat("0.0%")));  
			plot.setCircular(true);
			TextTitle title = chart.getTitle();
			title.setFont(new Font("汉真广标", Font.BOLD, 25));
		} else if (tblx.equals("pie3D")) {
			DefaultPieDataset dataset = new DefaultPieDataset();
			if ((yjxm != null) && !yjxm.equals("")) {
				while (rs.next()) {
					dataset.setValue(rs.getString(yjxm.split("-")[0]), Integer
							.parseInt(rs.getString("rs")));
				}
			}
			chart = ChartFactory.createPieChart3D(tjxm + "分析", dataset, true,
					false, false);
			chart.setBackgroundPaint(new Color(222, 222, 255));
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelGenerator(new   StandardPieSectionLabelGenerator("{0}={2}", 
					NumberFormat.getNumberInstance(),  
					new   DecimalFormat("0.0%")));
			plot.setCircular(false);
			plot.setForegroundAlpha(0.6f);
			plot.setBackgroundAlpha(0.9f);
			plot.setStartAngle(180);
			TextTitle title = chart.getTitle();
			title.setFont(new Font("汉真广标", Font.BOLD, 25));
		} else if (tblx.equals("line")) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if ((yjxm != null) && !yjxm.equals("")) {
				while (rs.next()) {
					dataset.addValue(Integer.parseInt(rs.getString("rs")), "",
							rs.getString(yjxm.split("-")[0]));
				}
			}
			chart = ChartFactory.createLineChart(tjxm + "分析",
					yjxm.split("-")[1], "人数", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			TextTitle title = chart.getTitle();
			title.setFont(new Font("汉真广标", Font.BOLD, 25));
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			GradientPaint bg = new GradientPaint(0, 50,
					new Color(248, 253, 255), 0, 250, new Color(205, 237, 252));
			plot.setBackgroundPaint(bg);
			plot.setDomainGridlinePaint(Color.BLACK);
			plot.setDomainGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.GRAY);
			chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0,
					1000, Color.blue));
			ValueAxis rangeAxis = plot.getRangeAxis();
			rangeAxis.setLabelAngle(Math.PI / 2);
			CategoryAxis domainAxis = plot.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions
					.createUpRotationLabelPositions(Math.PI / 6.0));
			title.setFont(new Font("汉真广标", Font.BOLD, 25));
			domainAxis.setLabelFont(new Font("汉真广标",Font.BOLD,14)); //水平底部标题 
			domainAxis.setTickLabelFont(new Font("汉真广标",Font.BOLD,12)); //垂直标题 
			rangeAxis.setLabelFont(new Font("汉真广标",Font.BOLD,15));
		} else if (tblx.equals("area")) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if ((yjxm != null) && !yjxm.equals("")) {
				while (rs.next()) {
					dataset.addValue(Integer.parseInt(rs.getString("rs")), "",
							rs.getString(yjxm.split("-")[0]));
				}
			}
			chart = ChartFactory.createAreaChart(tjxm + "分析",
					yjxm.split("-")[1], "人数", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			TextTitle title = chart.getTitle();
			title.setFont(new Font("汉真广标", Font.BOLD, 25));
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.WHITE);
			plot.setDomainGridlinePaint(Color.BLACK);
			plot.setDomainGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.GRAY);
			plot.setForegroundAlpha(0.9f);
			chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0,
					1000, Color.blue));
			ValueAxis rangeAxis = plot.getRangeAxis();
			rangeAxis.setLabelAngle(Math.PI / 2);
			CategoryAxis domainAxis = plot.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions
					.createUpRotationLabelPositions(Math.PI / 6.0));
			title.setFont(new Font("汉真广标", Font.BOLD, 25));
			domainAxis.setLabelFont(new Font("汉真广标",Font.BOLD,14)); //水平底部标题 
			domainAxis.setTickLabelFont(new Font("汉真广标",Font.BOLD,12)); //垂直标题 
			rangeAxis.setLabelFont(new Font("汉真广标",Font.BOLD,15));
		}
		// dao.closeStmt();
		 
		ChartRenderingInfo info = new ChartRenderingInfo(
				new org.jfree.chart.entity.StandardEntityCollection());
		String filename = ServletUtilities.saveChartAsPNG(chart, 950, 550,
				info, session);
		ServletUtilities.sendTempFile(filename, response);
		myActFwd = mapping.findForward("success");
		return myActFwd;
	}

	private ActionForward priseAnalyseInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward myActFwd = null;
		CommanForm priseForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String sql = "";
		// sql = "select xydm bmdmbmmc,xymc bmmc from view_njxyzybj";
		// String[] inputSQLGroup = {};
		// String[] outputSQLGroup = { "bmdmbmmc", "bmmc" };
		// List bmList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
		List bmList = dao.getXyList();
		request.setAttribute("bmList", bmList);
		if ((priseForm.getXy() != null) && !priseForm.getXy().equals("")) {
			// sql = "select distinct zydm zydmzymc,zymc from view_njxyzybj
			// where xydm='"
			// + priseForm.getXy() + "'";
			// String[] inputSQLGroup1 = {};
			// String[] outputSQLGroup1 = { "zydmzymc", "zymc" };
			// List zyList = dao.getList(sql, inputSQLGroup1,
			// outputSQLGroup1);
			List zyList = dao.getZyList(priseForm.getXy());
			request.setAttribute("zyList", zyList);
			// priseForm.setXy(dealString.toGBK(priseForm.getXy()));
		}
		List njList = dao.getNjList();
		List xqList = dao.getXqList();
		List xnndList = dao.getXnndList();
		request.setAttribute("xnndList", xnndList);
		request.setAttribute("njList", njList);
		// sql = "select * from xqdzb";
		// String[] inputSQLGroup4 = {};
		// String[] outputSQLGroup4 = { "xqdm", "xqmc" };
		// List xqList = dao.getList(sql, inputSQLGroup4, outputSQLGroup4);
		request.setAttribute("xqList", xqList);
		if ((priseForm.getTjxm() != null) && !priseForm.getTjxm().equals("")) {
			if (priseForm.getTjxm().equals("prise")) {
				sql = "select jxjdm jtxmdm,jxjmc jtxmmc from jxjdmb";
			}
			if (priseForm.getTjxm().equals("credit")) {
				sql = "select rychdm jtxmdm,rychmc jtxmmc from rychdmb";
			}
			if (priseForm.getTjxm().equals("trainPrise")) {
				sql = "select jxdm jtxmdm,jxmc jtxmmc from jxjxdmb";
			}
			String[] inputSQLGroup2 = {};
			String[] outputSQLGroup2 = { "jtxmdm", "jtxmmc" };
			List jtxmList = dao.getList(sql, inputSQLGroup2, outputSQLGroup2);
			request.setAttribute("jtxmList", jtxmList);
			priseForm.setZy(DealString.toGBK(priseForm.getZy()));
		}
		myActFwd = mapping.findForward("success");
		return myActFwd;
	}

	private ActionForward priseAnalyse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward myActFwd = null;
		CommanForm priseForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String sql = "";
		String colSql = "";
		String tableName = "";
		String conditionSql = "";
		String conditionSql1 = "";
		String xyZyCondition = "";
		String xyZyCondition1 = "";
		String xyZyCondition2 = "";
		String xy = DealString.toGBK(priseForm.getXy());
		String xydm = "";
		String xn = priseForm.getXn();
		String nd = priseForm.getNd();
		String xq = priseForm.getXq();
		String dqszj = priseForm.getDqszj();
		String tjxm = priseForm.getTjxm();
		String jtxm = priseForm.getJtxm();
		int tjdw = Integer.parseInt(priseForm.getTjdw());
		if ((xy != null) && !xy.equals("")) {
			xydm = xy.substring(0, 2);
			xyZyCondition = " and b.bmdm='" + xydm + "'";
			if (tjdw == 1) {
				xyZyCondition1 = " where a.bmdm='" + xydm + "'";
			} else if (tjdw == 2) {
				xyZyCondition2 = " and b.bmdm='" + xydm + "'";
			}
		}
		String zy = DealString.toGBK(priseForm.getZy());
		String zydm = "";
		String zymc = "";
		if ((xy != null) && !xy.equals("") && (zy != null) && !zy.equals("")
				&& (tjdw == 2)) {
			zydm = zy.substring(0, 4);
			zymc = zy.substring(4, zy.length());
			xyZyCondition = " and b.bmdm='" + xydm + "' and b.zymc='" + zymc
			+ "'";
			xyZyCondition1 = " where a.zydm='" + zydm + "'";
		}

		if (tjxm.equals("prise")) {
			tableName = "xsjxjb";
		} else if (tjxm.equals("credit")) {
			tableName = "xsrychb";
		} else if (tjxm.equals("trainPrise")) {
			tableName = "xsjxhjb";
		}

		if ((xn == null) || xn.equals("")) {
			colSql = colSql + "'全部' xn,";
		} else {
			colSql = colSql + "'" + xn + "' xn,";
			conditionSql = conditionSql + " and xn='" + xn + "' ";
			conditionSql1 = conditionSql1 + " and a.xn='" + xn + "' ";
		}
		if ((nd == null) || nd.equals("")) {
			colSql = colSql + "'全部' nd,";
		} else {
			colSql = colSql + "'" + nd + "' nd,";
			conditionSql = conditionSql + " and nd='" + nd + "' ";
			conditionSql1 = conditionSql1 + " and a.nd='" + nd + "' ";
		}
		if ((xq == null) || xq.equals("")) {
			colSql = colSql + "'全部' xq,";
		} else {
			String str = "select * from xqdzb where xqdm='" + xq + "'";
			ResultSet rsXq = dao.getRS(str);
			if (rsXq != null) {
				rsXq.next();
			}
			String xqmc = rsXq.getString("xqmc");
			colSql = colSql + "'" + xqmc + "' xq,";
			conditionSql = conditionSql + " and xq='" + xq + "' ";
			conditionSql1 = conditionSql1 + " and a.xq='" + xq + "' ";
			// 关闭连接
			dao.closeFromResultSet(rsXq);
		}
		if ((dqszj == null) || dqszj.equals("")) {
			colSql = colSql + "'全部' dqszj,";
		} else {
			colSql = colSql + "'" + dqszj + "' dqszj,";
			conditionSql1 = conditionSql1 + " and b.dqszj='" + dqszj + "' ";
		}
		if ((jtxm == null) || jtxm.equals("")) {
			colSql = colSql + "'全部' jtxm,";
		} else {
			String str1 = "";
			if (tjxm.equals("prise")) {
				conditionSql = conditionSql + " and jxjdm='" + jtxm + "' ";
				conditionSql1 = conditionSql1 + " and a.jxjdm='" + jtxm + "' ";
				str1 = "select * from jxjdmb where jxjdm='" + jtxm + "'";
			}
			if (tjxm.equals("credit")) {
				conditionSql = conditionSql + " and rychdm='" + jtxm + "' ";
				conditionSql1 = conditionSql1 + " and a.rychdm='" + jtxm + "' ";
				str1 = "select * from rychdmb where rychdm='" + jtxm + "'";
			}
			if (tjxm.equals("trainPrise")) {
				conditionSql = conditionSql + " and jxdm='" + jtxm + "' ";
				conditionSql1 = conditionSql1 + " and a.jxdm='" + jtxm + "' ";
				str1 = "select * from jxjxdmb jxdm='" + jtxm + "'";
			}
			ResultSet rsjtxm = dao.getRS(str1);
			if (rsjtxm != null) {
				rsjtxm.next();

			}
			// 关闭连接
			dao.closeFromResultSet(rsjtxm);
			colSql = colSql + "'" + rsjtxm.getString(2) + "' jtxm,";
		}
		if (tjdw == 0) {
			sql = "select rownum,a.* from(select a.bmmc xy ,'全部' zy,'全部' xzb,"
				+ colSql
				+ "count(b.xh) rs,(case (select count(*) from "
				+ tableName
				+ " where xxsh='已审核'"
				+ conditionSql
				+ ") when 0 then 0 else round(count(b.xh)/(select count(*) from "
				+ tableName
				+ " where xxsh='已审核'"
				+ conditionSql
				+ ")*100,2) end) bl from zxbz_xxbmdm a left join (select a.xh,b.bmdm from "
				+ tableName
				+ " a,view_xsjbxx b where a.xh=b.xh and a.xxsh='已审核'"
				+ conditionSql1
				+ ") b on a.bmdm=b.bmdm group by a.bmmc) a order by a.xy";
		} else if (tjdw == 1) {
			sql = "select rownum,a.* from(select b.bmmc xy,a.zymc zy,'全部' xzb,"
				+ colSql
				+ "a.rs,a.bl from (select a.bmdm,a.zymc,count(b.xh) rs,(case (select count(*) from "
				+ tableName
				+ " where xxsh='已审核'"
				+ conditionSql
				+ ") when 0 then 0 else round(count(b.xh)/(select count(*) from "
				+ tableName
				+ " where xxsh='已审核'"
				+ conditionSql
				+ ")*100,2) end) bl from bks_zydm a left join (select a.xh,b.zymc from "
				+ tableName
				+ " a,view_xsjbxx b where a.xh=b.xh and a.xxsh='已审核'"
				+ conditionSql1
				+ xyZyCondition
				+ ") b on a.zymc=b.zymc "
				+ xyZyCondition1
				+ " group by a.zymc,a.bmdm) a,zxbz_xxbmdm b where a.bmdm=b.BMDM) a order by a.zy";
		} else if (tjdw == 2) {
			sql = "select rownum,a.* from(select b.bmmc xy,a.zymc zy,a.bjdm ,a.bjmc xzb,"
				+ colSql
				+ "a.rs,a.bl from (select b.zymc,b.bmdm,a.bjdm,a.bjmc,a.rs,a.bl from (select a.bjdm,a.bjmc,a.zydm,count(b.xh) rs,(case (select count(*) from "
				+ tableName
				+ " where xxsh='已审核'"
				+ conditionSql
				+ ") when 0 then 0 else round(count(b.xh)/(select count(*) from "
				+ tableName
				+ " where xxsh='已审核'"
				+ conditionSql
				+ ")*100,2) end) bl from bks_bjdm a left join (select a.xh,b.xzb from xsjxjb a,view_xsjbxx b where a.xh=b.xh and a.xxsh='已审核'"
				+ conditionSql1
				+ xyZyCondition
				+ ") b on a.bjmc=b.xzb "
				+ xyZyCondition1
				+ " group by a.bjdm,a.bjmc,a.zydm) a,bks_zydm b where a.zydm=b.zydm) a,zxbz_xxbmdm b where a.bmdm=b.bmdm "
				+ xyZyCondition2 + ") a order by a.xzb";
		}
		String[] inputSQLGroup1 = {};
		String[] outputSQLGroup1 = { "rownum", "xy", "zy", "xzb", "dqszj",
				"xn", "nd", "xq", "jtxm", "rs", "bl" };
		List analyseList = dao.getList(sql, inputSQLGroup1, outputSQLGroup1);
		request.setAttribute("analyseList", analyseList);
		sql = "select concat(bmdm,bmmc) bmdmbmmc,bmmc from zxbz_xxbmdm";
		String[] inputSQLGroup = {};
		String[] outputSQLGroup = { "bmdmbmmc", "bmmc" };
		List bmList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
		request.setAttribute("bmList", bmList);
		List xnndList = dao.getXnndList();
		request.setAttribute("xnndList", xnndList);
		sql = "select * from xqdzb";
		String[] inputSQLGroup4 = {};
		String[] outputSQLGroup4 = { "xqdm", "xqmc" };
		List xqList = dao.getList(sql, inputSQLGroup4, outputSQLGroup4);
		request.setAttribute("xqList", xqList);
		priseForm.setXy(DealString.toGBK(priseForm.getXy()));
		myActFwd = mapping.findForward("success");
		return myActFwd;
	}

	private ActionForward dataStatisical(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward myActFwd = null;
		String mkName = request.getParameter("mkName");
		String titName = "";
		String xxdm = StandardOperation.getXxdm();
		
		//学生用户没有权限登录
		String uType = request.getSession().getAttribute("userType").toString();
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(uType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(uType)) {
			request.setAttribute("errMsg", "您无权访问该页面!");
			return new ActionForward("/errMsg.do");
		}
		
		if (mkName.equals("thought")) {
			titName = "思想教育 - 统计分析 - 统计分析";
		} else if (mkName.equals("prise")) {
			titName = "评奖评优 - 统计分析 - 统计分析";
		} else if (mkName.equals("comm")) {
			titName = "对外交流 - 统计分析 - 统计分析";
		} else if (mkName.equals("assis")) {
			titName = "学生资助 - 统计分析 - 统计分析";
		} else if (mkName.equals("work")) {
			titName = "勤工助学 - 统计分析 - 统计分析";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				// 长沙民政
				titName = "学生义工 - 统计分析 - 统计分析";
			}
		} else if (mkName.equals("health")) {
			titName = "心理健康 - 统计分析 - 统计分析";
		} else if (mkName.equals("train")) {
			titName = "军训管理 - 统计分析 - 统计分析";
		} else if (mkName.equals("discip")) {
			titName = "违纪处分 - 统计分析 - 统计分析";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
				titName = "违纪处理 - 统计分析 - 统计分析";
			}
		} else if (mkName.equals("insure")) {
			titName = "其他数据 - 保险信息 - 统计分析";
		} else if (mkName.equals("meal")) {
			titName = "其他数据 - 伙食消费信息 - 统计分析";
		} else if (mkName.equals("drom")) {
			titName = "公寓管理 - 学生住宿信息 - 统计分析";
		} else if (mkName.equals("hzjy")) {
			titName = "合作教育 - 合作教育统计分析 - 统计分析";
		} else if (mkName.equals("student")) {
			titName = "学生信息-统计分析-统计分析";
		} else if (mkName.equals("jygl")) {
			titName = "就业管理 - 统计分析 - 就业数据统计";
		} else if (mkName.equals("student2")) {
			titName = "学生信息-学籍异动-统计分析";
		} else if (mkName.equals("rcsw")) {
			titName = "日常事务-统计分析-统计分析";
		} else if (mkName.equals("xszz")) {
			titName = "学生组织干部-统计分析-统计分析";
		} 
		request.setAttribute("titName", titName);
		request.setAttribute("mkName", mkName);
		myActFwd = mapping.findForward("success");
		return new ActionForward("/statisicalInit.do?mkName="+mkName+"&titName="+titName);
	}

	@SuppressWarnings("unchecked")
	// 数据查询-通用模块
	private ActionForward dataQueryInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward myActFwd = null;
		String mkName = request.getParameter("mkName");
		CommanForm queryForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String cxbm = queryForm.getCxbm();
		String xxdm = StandardOperation.getXxdm();
			
		//学生用户没有权限登录
		String uType = request.getSession().getAttribute("userType").toString();
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(uType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(uType)) {
			request.setAttribute("errMsg", "您无权访问该页面!");
			return new ActionForward("/errMsg.do");
		}
		
		String titName = "";
		String sql = "";
		if (mkName.equals("thought")) {
			titName = "思想教育 - 统计分析 - 数据查询";
		} else if (mkName.equals("prise")) {
			titName = "评奖评优 - 统计分析 - 数据查询";
		} else if (mkName.equals("comm")) {
			titName = "对外交流 - 统计分析 - 数据查询";
		} else if (mkName.equals("assis")) {
			titName = "学生资助 - 统计分析 - 数据查询";
		} else if (mkName.equals("work")) {
			titName = "勤工助学 - 统计分析 - 数据查询";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				// 长沙民政
				titName = "学生义工 - 统计分析 - 数据查询";
			}
		} else if (mkName.equals("health")) {
			titName = "心理健康 - 统计分析 - 数据查询";
		} else if (mkName.equals("train")) {
			titName = "军训管理 - 统计分析 - 数据查询";
		} else if (mkName.equals("discip")) {
			titName = "违纪处分 - 统计分析 - 数据查询";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
				titName = "违纪处理 - 统计分析 - 数据查询";
			}
		} else if (mkName.equals("student")) {
			titName = "学生信息-统计分析-数据查询";
		} else if (mkName.equals("jygl")) {
			titName = "就业管理 - 统计分析 - 就业数据查询";
		} else if (mkName.equals("szdw")) {
			titName = "思政队伍 - 统计分析 - 数据查询";
		} else if (mkName.equals("assisNew")) {
			titName = "学生资助 - 数据查询 - 数据查询";
		} else if (mkName.equals("gjzxdk") || mkName.equals("gjzxdk_msxy")) {
			titName = "助学贷款 - 数据查询 - 数据查询";
		} else if (mkName.equals("jzxj")) {
			titName = "奖助学金 - 数据查询 - 数据查询";
		} else if (mkName.equals("xszz")) {
			titName = "学生组织干部 - 统计分析 - 数据查询";
		} else if (mkName.equalsIgnoreCase("dorm")){
			titName = "公寓管理 - 统计分析 - 数据查询";
		} else if (mkName.equalsIgnoreCase("xsh")) {
			titName = "社团及志愿者管理 - 统计分析 - 数据查询";
		}

		sql = "select xxmc from xtszb";
		String query = " and 1=1 ";
		// String xxmc = "";
		String tag = dao.returntag(sql, new String[] {});
		if ("notempty".equalsIgnoreCase(tag)) {
			// xxmc = dao.getOneRs(sql, new String[] {}, "xxmc");
		}
		// String xxdm = StandardOperation.getXxdm();
		query += Globals.NotJudgeWhichSchoolQuery("cxbm", xxdm);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
				&& "assis".equalsIgnoreCase(mkName)) {
			sql = "select lower(a.table_name)table_name,nvl(a.comments,a.table_name) comments from user_tab_comments a,(select distinct cxbm from cxb where ssmk='"
				+ mkName
				+ "' and xxmc = '"
				+ xxdm
				+ "') b where lower(a.table_name)=lower(b.cxbm)";
			String[] inputSQLGroup = {};
			String[] outputSQLGroup = { "table_name", "comments" };
			List tableList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
			request.setAttribute("tableList", tableList);
			if ((cxbm != null) && !cxbm.equals("")) {
				sql = "select a.column_name,nvl(a.comments,a.column_name) comments,decode(b.sxbj,'1','√'||a.comments,'×'||a.comments) titComments from user_col_comments a,cxb b where lower(a.table_name)=lower(b.cxbm) and lower(a.column_name)=lower(b.sxxm) and lower(a.table_name)=lower('"
					+ cxbm + "') and xxmc = '" + xxdm + "'";
				String[] inputSQLGroup1 = {};
				String[] outputSQLGroup1 = { "column_name", "comments",
				"titComments" };
				List colList = dao
				.getList(sql, inputSQLGroup1, outputSQLGroup1);
				request.setAttribute("colList", colList);
			}
		} else if ((Globals.JudgeWhichSchool(xxdm) && "assis"
				.equalsIgnoreCase(mkName))
				|| (Globals.NewJudgeWhichSchool(xxdm) && "assisNew"
						.equalsIgnoreCase(mkName))) {
			sql = "select lower(a.table_name)table_name,nvl(a.comments,a.table_name) comments from user_tab_comments a,((select distinct cxbm from cxb where ssmk='"
				+ mkName
				+ "' and XXMC is null "
				+ query
				+ ") union (select distinct cxbm from cxb where ssmk='"
				+ mkName
				+ "' and XXMC = '"
				+ xxdm
				+ "')) b where lower(a.table_name)=lower(b.cxbm)";
			String[] inputSQLGroup = {};
			String[] outputSQLGroup = { "table_name", "comments" };
			List tableList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
			request.setAttribute("tableList", tableList);
			if ((cxbm != null) && !cxbm.equals("")) {
				sql = "(select a.column_name,nvl(a.comments,a.column_name) comments,decode(b.sxbj,'1','√'||a.comments,'×'||a.comments) titComments from user_col_comments a,cxb b where lower(a.table_name)=lower(b.cxbm) and lower(a.column_name)=lower(b.sxxm) and b.xxmc is null "
					+ query
					+ " and lower(a.table_name)=lower('"
					+ cxbm
					+ "')) union (select a.column_name,nvl(a.comments,a.column_name) comments,decode(b.sxbj,'1','√'||a.comments,'×'||a.comments) titComments from user_col_comments a,cxb b where lower(a.table_name)=lower(b.cxbm) and lower(a.column_name)=lower(b.sxxm) and lower(a.table_name)=lower('"
					+ cxbm + "') and xxmc = '" + xxdm + "')";
				String[] inputSQLGroup1 = {};
				String[] outputSQLGroup1 = { "column_name", "comments",
				"titComments" };
				List colList = dao
				.getList(sql, inputSQLGroup1, outputSQLGroup1);
				request.setAttribute("colList", colList);
			}
		} else {
			sql = "select lower(a.table_name)table_name,nvl(a.comments,a.table_name) comments from user_tab_comments a,(select distinct cxbm from cxb where ssmk='"
				+ mkName
				+ "' and (XXMC is null or XXMC='"
				+ xxdm
				+ "')) b where lower(a.table_name)=lower(b.cxbm)";
			String[] inputSQLGroup = {};
			String[] outputSQLGroup = { "table_name", "comments" };
			List tableList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
			request.setAttribute("tableList", tableList);
			if ((cxbm != null) && !cxbm.equals("")) {
				sql = "select distinct a.column_name,nvl(a.comments,a.column_name) comments,"
					+ " decode(b.sxbj,'1','√'||a.comments,'×'||a.comments) titComments"
					+ " from user_col_comments a,cxb b where lower(a.table_name)=lower(b.cxbm) and"
					+ " lower(a.column_name)=lower(b.sxxm) and (b.xxmc is null"
					+ " or b.xxmc='"
					+ xxdm
					+ "')"
					+ " and lower(a.table_name)=lower('"
					+ cxbm + "')";
				String[] inputSQLGroup1 = {};
				String[] outputSQLGroup1 = { "column_name", "comments",
				"titComments" };
				List colList = dao
				.getList(sql, inputSQLGroup1, outputSQLGroup1);
				request.setAttribute("colList", colList);
				// 要判断是否是长沙民政 liang 当为用人单位表时，改为查询的表名为视图“(view_xsgwxx)”
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
						&& cxbm.equalsIgnoreCase("yrdwdmb")) {
					cxbm = "VIEW_XSGWXX";
					sql = "select a.column_name,nvl(a.comments,a.column_name) comments,"
						+ " decode(b.sxbj,'1','√'||a.comments,'×'||a.comments) titComments"
						+ " from user_col_comments a,cxb b where lower(a.table_name)=lower(b.cxbm) and"
						+ " lower(a.column_name)=lower(b.sxxm) and (b.xxmc is null"
						+ " or b.xxmc='"
						+ xxdm
						+ "')"
						+ " and lower(a.table_name)=lower('" + cxbm + "') ";
					// 这里要显示用人单位表的字段名称信息列表
					String[] outputValue = new String[] { "XN", "ND", "XQMC",
							"XH", "XM", "BJMC", "GWDM", "SQSJ", "SFPKS",
							"XYYJ", "XXYJ" };
					List<HashMap<String, String>> disColListPre = dao.getList(
							sql, inputSQLGroup1, outputSQLGroup1);
					List disColList = new ArrayList<HashMap<String, String>>();
					for (HashMap<String, String> map : disColListPre) {
						for (int i = 0; i < outputValue.length; i++) {
							if (map.get("column_name").toString()
									.equalsIgnoreCase(outputValue[i])) {
								disColList.add(map);
								break;
							}
						}
					}
					request.setAttribute("disColList", disColList);
					// 对于长沙民政学院 （在request中保存学校代码）
					request.setAttribute("xxdm", xxdm);
					request.setAttribute("yrdwdm", "1");
				}
			}
		}

		// 测试
		// if (1 == 2) {
		HashMap<String, String> rs = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// 如何获得年级
		String cols = DealString.toGBK(request.getParameter("cols"));

		if ("xb".equalsIgnoreCase(cols)) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("val", "男");
			list.add(map);
			map = new HashMap<String, String>();
			map.put("val", "女");
			list.add(map);
		}

		if ("xn".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getXnndList();
			String xn = "";
			for (HashMap<String, String> sT : list1) {
				xn = sT.get("xn");
				if (!"".equalsIgnoreCase(xn)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", xn);
					list.add(map);
				}
			}
		}

		if ("nd".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getXnndList();
			String nd = "";
			for (HashMap<String, String> sT : list1) {
				nd = sT.get("nd");
				if (!"".equalsIgnoreCase(nd)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", nd);
					list.add(map);
				}
			}
		}

		if ("xq".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getXqList();
			String xqmc = "";
			for (HashMap<String, String> sT : list1) {
				xqmc = sT.get("xqmc");
				if (!"".equalsIgnoreCase(xqmc)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", xqmc);
					list.add(map);
				}
			}
		}
		
		if ("xydm".equalsIgnoreCase(cols)
				|| "ydqxydm".equalsIgnoreCase(cols)
				|| "ydhxydm".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getXyList();
			String xydm = "";
			for (HashMap<String, String> sT : list1) {
				xydm = sT.get("xydm");
				if (!"".equalsIgnoreCase(xydm)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", xydm);
					list.add(map);
				}
			}
		}
		if ("xymc".equalsIgnoreCase(cols)
				|| "ydqxymc".equalsIgnoreCase(cols)
				|| "yhhxymc".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getXyList();
			String xymc = "";
			for (HashMap<String, String> sT : list1) {
				xymc = sT.get("xymc");
				if (!"".equalsIgnoreCase(xymc)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", xymc);
					list.add(map);
				}
			}
		}
		if ("zydm".equalsIgnoreCase(cols)
				|| "ydqzydm".equalsIgnoreCase(cols)
				|| "ydhzydm".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getZyList("");
			String zydm = "";
			for (HashMap<String, String> sT : list1) {
				zydm = sT.get("zydm");
				if (!"".equalsIgnoreCase(zydm)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", zydm);
					list.add(map);
				}
			}
		}
		if ("zymc".equalsIgnoreCase(cols)
				|| "ydqzymc".equalsIgnoreCase(cols)
				|| "ydhzymc".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getZyList("");
			String zymc = "";
			for (HashMap<String, String> sT : list1) {
				zymc = sT.get("zymc");
				if (!"".equalsIgnoreCase(zymc)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", zymc);
					list.add(map);
				}
			}
		}
		if ("bjdm".equalsIgnoreCase(cols)
				|| "ydqbjdm".equalsIgnoreCase(cols)
				|| "ydhbjdm".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getBjList("","");
			String bjdm = "";
			for (HashMap<String, String> sT : list1) {
				bjdm = sT.get("bjdm");
				if (!"".equalsIgnoreCase(bjdm)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", bjdm);
					list.add(map);
				}
			}
		}
		if ("bjmc".equalsIgnoreCase(cols) 
				|| "ydqbjmc".equalsIgnoreCase(cols)
				|| "ydhbjmc".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getBjList("","");
			String bjmc = "";
			for (HashMap<String, String> sT : list1) {
				bjmc = sT.get("bjmc");
				if (!"".equalsIgnoreCase(bjmc)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", bjmc);
					list.add(map);
				}
			}
		}
		if ("nj".equalsIgnoreCase(cols) 
				|| "ydqnj".equalsIgnoreCase(cols)
				|| "ydhnj".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getNjList();
			String nj = "";
			for (HashMap<String, String> sT : list1) {
				nj = sT.get("nj");
				if (!"".equalsIgnoreCase(nj)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", nj);
					list.add(map);
				}
			}
		}
		if ("lddm".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = GyglShareDAO.getLdList();
			String lddm = "";
			for (HashMap<String, String> sT : list1) {
				lddm = sT.get("dm");
				if (!"".equalsIgnoreCase(lddm)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", lddm);
					list.add(map);
				}
			}
		}
		if ("ldmc".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = GyglShareDAO.getLdList();
			String ldmc = "";
			for (HashMap<String, String> sT : list1) {
				ldmc = sT.get("mc");
				if (!"".equalsIgnoreCase(ldmc)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", ldmc);
					list.add(map);
				}
			}
		}
		if ("xqmc".equalsIgnoreCase(cols) && mkName.equalsIgnoreCase("dorm")) {
			List<HashMap<String, String>> list1 = GyglShareDAO.getXaioQList();
			String xqmc = "";
			for (HashMap<String, String> sT : list1) {
				xqmc = sT.get("mc");
				if (!"".equalsIgnoreCase(xqmc)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", xqmc);
					list.add(map);
				}
			}
		}
		if ("xqmc".equalsIgnoreCase(cols) && !mkName.equalsIgnoreCase("dorm")) {
			List<HashMap<String, String>> list1 = dao.getXqList();
			String xqmc = "";
			for (HashMap<String, String> sT : list1) {
				xqmc = sT.get("xqmc");
				if (!"".equalsIgnoreCase(xqmc)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", xqmc);
					list.add(map);
				}
			}
		}
		if ("mzmc".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getMzList();
			String mzmc = "";
			for (HashMap<String, String> sT : list1) {
				mzmc = sT.get("mzmc");
				if (!"".equalsIgnoreCase(mzmc)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", mzmc);
					list.add(map);
				}
			}
		}
		if ("zzmmmc".equalsIgnoreCase(cols)) {
			List<HashMap<String, String>> list1 = dao.getZzmmList();
			String zzmmmc = "";
			for (HashMap<String, String> sT : list1) {
				zzmmmc = sT.get("zzmmmc");
				if (!"".equalsIgnoreCase(zzmmmc)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("val", zzmmmc);
					list.add(map);
				}
			}
		}
		CommanForm statisicalForm = (CommanForm) form;
		String conditionSqlValue = DealString.toGBK(request
				.getParameter("sqlstring"));
		String conditionSqlText = DealString.toGBK(request
				.getParameter("sqlstringtext"));

		statisicalForm.setConditionSqlText(conditionSqlText);
		statisicalForm.setConditionSqlValue(conditionSqlValue);

		// sql = "select distinct nj from view_njxyzybj order by nj";
		// list=dao.getList(sql, new String[] {}, new String[] { "nj" });

		request.setAttribute("list", list);
		request.setAttribute("rs", rs);

		request.setAttribute("titName", titName);
		request.setAttribute("mkName", mkName);
		myActFwd = mapping.findForward("success");
		// dao.closeStmt();
		// dao.closeFromResultSet(rs);
		return myActFwd;
	}

	private ActionForward doDataQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward myActFwd = null;
		StudentInfoService service = new StudentInfoService();
		DAO dao = DAO.getInstance();
		String query = DealString.toGBK(request.getParameter("sql1"));
		String vCols = request.getParameter("cols1");
		String tabName = request.getParameter("cxbm");
		String titName = DealString.toGBK(request.getParameter("titName"));
		String sql = "";
		sql = "select * from " + tabName;
		
		if (tabName != null && tabName.equalsIgnoreCase("view_xsjtxx")) {
			// 学生家庭信息
			sql = service.getXsfzxxToExp();
		}
		String[] cName = dao.getColumnName(sql);
		String[] cNameCN = dao.getColumnNameCN(cName, tabName);
		
		if (tabName != null && tabName.equalsIgnoreCase("view_xsxxb")) {
			// 学生信息
			GetDropDownList util = new GetDropDownList();
			cName = new String[]{};
			cNameCN = new String[]{};
			List<HashMap<String, String>> list = util.getTableColForExp("view_xsxxb");
			for(HashMap<String, String> colMap : list){
				cName = StringUtils.joinStrArr(cName,new String[]{colMap.get("dm").toUpperCase()}) ;
				cNameCN = StringUtils.joinStrArr(cNameCN,new String[]{colMap.get("mc")}) ;
			}
		}
		
		String xxdm = StandardOperation.getXxdm();
		List<HashMap<String, String>> rs = null;
		int rowCount = 0;
		int pageCount = 0;
		int pageSize = 0;
		int currentPage = 1;
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize = 20;
		}
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		if (pageSize < 10) {
			pageSize = 20;
		}
		String strColName = "";
		String strColNameCN = "";
		String rsInfo = "";
		String columns = "";
		for (int i = 0; i < cName.length; i++) {
			if (i == cName.length - 1) {
				columns = columns + cName[i] + " ";
			} else {
				columns += cName[i] + ",";
			}
		}
		if ((request.getParameter("cxbm") != null)
				&& !request.getParameter("cxbm").equalsIgnoreCase("")
				&& (query != null) && !query.equalsIgnoreCase("")) {
			
			if ("view_zjlg_xsrychxx".equalsIgnoreCase(tabName) || "view_jxjzjlg".equalsIgnoreCase(tabName)) {
				query += " and xxsh='通过'";
			}

			dao.setPageSize(pageSize);
			rs = dao.getRS(tabName, query, columns, cName, currentPage);
			request.setAttribute("tbody", rs);
			rowCount = dao.getRsCount();
			pageCount = dao.getPageCount();
			pageSize = dao.getPageSize();

			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
					&& tabName.equalsIgnoreCase("YRDWDMB")) {
				// 长沙名政 liang 列出的是学生的信息列表
				String view_Name = "view_xsgwxx";
				List<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();
				String[] outputValue = new String[] { "XN", "ND", "XQMC", "XH",
						"XM", "BJMC", "GWDM", "SQSJ", "SFPKS", "XYYJ", "XXYJ" };
				for (int i = 0; i < rs.size(); i++) {
					String yrdwdm = rs.get(i).get("YRDWDM").toString();
					String tempSql = "select * from view_xsgwxx where YRDWDM=?";
					List<HashMap<String, String>> subStuList = dao.getList(
							tempSql, new String[] { yrdwdm }, outputValue);
					// 将时间变为中文的类型
					for (HashMap<String, String> map : subStuList) {
						String sTime = map.get("SQSJ");
						map.remove("SQSJ");
						map.put("SQSJ", dao.doForTime(sTime));
					}
					studentList.addAll(subStuList);
				}
				// 得到学生的相关信息的字段名字
				sql = "select * from view_xsgwxx";
				cName = outputValue;
				cNameCN = dao.getColumnNameCN(cName, view_Name);
				request.setAttribute("tbody", studentList);
				rowCount = studentList.size();
			}
			currentPage = dao.getCurrentPage();
			rsInfo = String.valueOf(rowCount) + "!!SplitSignOne!!"
			+ String.valueOf(pageCount) + "!!SplitSignOne!!"
			+ String.valueOf(pageSize) + "!!SplitSignOne!!"
			+ String.valueOf(currentPage);
		}
		for (int i = 0; i < cName.length; i++) {
			strColName += "!!SplitSignOne!!";
			strColName += cName[i];
			strColNameCN += "!!SplitSignOne!!";
			strColNameCN += cNameCN[i];
		}
		request.setAttribute("tabName", tabName);
		request.setAttribute("titName", titName);
		request.setAttribute("vCols", vCols);
		request.setAttribute("colName", strColName);
		request.setAttribute("colNameCN", strColNameCN);
		request.setAttribute("rsInfo", rsInfo);
 		request.setAttribute("sql", query);
		request.setAttribute("cols", vCols);
		myActFwd = mapping.findForward("success");
		return myActFwd;
	}

	/**
	 * 统计分析后数据导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return AcrionForward
	 */
	private ActionForward dataExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		CommanForm statisicalForm = (CommanForm) form;
		String sql = DealString.toGBK(statisicalForm.getSql());
//		String tableName = statisicalForm.getTableName();
		String colString = DealString.toGBK(request.getParameter("colNameCN"));
		String[] colNameCN = colString.split("!!");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		System.out.println(sql);
		Excel2Oracle.exportData(sql,colNameCN, response.getOutputStream()," ");
//		Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		return mapping.findForward("");
	}

	/**
	 * 数据查询后数据导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return AcrionForward
	 */
	private ActionForward dataExportForQ(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommanForm statisicalForm = (CommanForm) form;
		String tableName = statisicalForm.getCxbm();
		String colString = statisicalForm.getColName();
		String querry = DealString.toGBK(statisicalForm.getSql1());
		String[] colNameCN = colString.split("!!SplitSignOne!!");
		String sql = "";
		for (int i = 1; i < colNameCN.length; i++) {
			if (i == colNameCN.length - 1) {
				sql += colNameCN[i];
			} else {
				sql += colNameCN[i] + ",";
			}
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData("select " + sql + " from " + tableName
				+ " a where " + querry, tableName, response.getOutputStream());
		return mapping.findForward("");
	}
	
	public ActionForward ahgcjyglBysxxSb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
        String yx = request.getParameter("yx");
        if(Base.isNull(yx)){
        	yx = "one";
        }
        DAO dao = DAO.getInstance();       
        int nd = Integer.parseInt(Base.isNull(Base.currNd)?"1900":Base.currNd);
        String yxStr = "";
        //(最近三年度) 
        String nd1 = String.valueOf(nd-2);
        String nd2 = String.valueOf(nd-1);;
        String nd3 = String.valueOf(nd);;
        String[] columnKeys = {nd1,nd2,nd3};//统计年度(最近三年)   
		double[][] data = new double[][]{};
        //四月
		int nd14 = 0;//第一年度4月份
		int nd24 = 0;//第二年度4月份
		int nd34 = 0;//第三年度4月份
		//五月
		int nd15 = 0;//第一年度5月份
		int nd25 = 0;//第二年度5月份
		int nd35 = 0;//第三年度5月份
		//六月
		int nd16 = 0;//第一年度6月份
		int nd26 = 0;//第二年度6月份
		int nd36 = 0;//第三年度6月份
		int[] ndV = new int[]{nd14,nd15,nd16,nd24,nd25,nd26,nd34,nd35,nd36};
        StringBuffer sql = new StringBuffer();
        sql.append("select count(distinct xh)cout from (select xh,qysj ,substr(qysj,1,4)nd,( case when length(qysj)=10 then substr(qysj,6,2)  when length(qysj)=8 then substr(qysj,6,2) else substr(qysj,0,0)  end )yf, ");
        sql.append("( case when length(qysj)=10 then substr(qysj,9,2)  when length(qysj)=8 then substr(qysj,7,2) else substr(qysj,0,0)  end )rq from zjgsjyxytj)  ");
        int m = 0;//数组ndV下标
        String querry = "";
        if("one".equalsIgnoreCase(yx)){//上旬
        	for(int i=0;i<columnKeys.length;i++){//比例三年
        		for(int j=0;j<3;j++){//遍历三月(04、05、06)
        			String yf = "0"+(j+4);
        			querry = " where nd= '"+columnKeys[i]+"' and yf='"+yf+"' and rq <11 ";//三年三月个上旬查询条件
        			int tem = Integer.parseInt(dao.getOneRs(sql+querry,new String[]{},"cout"));
        			ndV[m] = tem;
        		    m++;
        		}
        	}
        	yxStr = "上旬";
        }else if("tow".equalsIgnoreCase(yx)){//中旬
        	for(int i=0;i<columnKeys.length;i++){//比例三年
        		for(int j=0;j<3;j++){//遍历三月(04、05、06)
        			String yf = "0"+(j+4);
        			querry = " where nd= '"+columnKeys[i]+"' and yf='"+yf+"' and rq between 11 and 20 ";//三年三月个上旬查询条件
        			int tem = Integer.parseInt(dao.getOneRs(sql+querry,new String[]{},"cout"));
        			ndV[m] = tem;
        		    m++;
        		}
        	}  
        	yxStr = "中旬";
        }else{//下旬
        	for(int i=0;i<columnKeys.length;i++){//比例三年
        		for(int j=0;j<3;j++){//遍历三月(04、05、06)
        			String yf = "0"+(j+4);
        			querry = " where nd= '"+columnKeys[i]+"' and yf='"+yf+"' and rq >20 ";//三年三月个上旬查询条件
        			int tem = Integer.parseInt(dao.getOneRs(sql+querry,new String[]{},"cout"));
        			ndV[m] = tem;
        		    m++;
        		}
        	}
        	yxStr = "下旬";
        }
        String clinText = "近三年4、5、6月份"+yxStr+" 学生签约情况统计";
		data = new double[][] {{ndV[0],ndV[1],ndV[2]},{ndV[3],ndV[4],ndV[5]},{ndV[6],ndV[7],ndV[8]}};
		String[] rowKeys = {"4月","5月","6月"};
		JFreeChart chart = null;

		CategoryDataset dataset = DatasetUtilities.createCategoryDataset( columnKeys,rowKeys, data);
		data = new double[][]{};

		chart = ChartFactory.createBarChart3D("统计柱状图",
				null,
				null,
				dataset,
				PlotOrientation.VERTICAL,
				true,false,false);

		chart.setBackgroundPaint(Color.WHITE);
		CategoryPlot plot = chart.getCategoryPlot();

		CategoryAxis domainAxis = plot.getDomainAxis();

		plot.setDomainAxis(domainAxis);
		ValueAxis rangeAxis = (ValueAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setLabelAngle(Math.PI / 2);

		plot.setRangeCrosshairValue(50.0, true);
		domainAxis.setLowerMargin(0.05);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 6.0));

		BarRenderer3D renderer = new BarRenderer3D();

		renderer.setSeriesPaint(0, Color.red);
		renderer.setSeriesPaint(1, Color.yellow);
		renderer.setSeriesPaint(2, Color.green);
		renderer.setWallPaint(Color.gray);		

		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setItemLabelsVisible(true);
		ItemLabelPosition p = new ItemLabelPosition(org.jfree.chart.labels.ItemLabelAnchor.OUTSIDE12,
				TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT,-Math.PI / 2.0);
		renderer.setPositiveItemLabelPositionFallback(p);
		renderer.setMaximumBarWidth(0.1);
		plot.setRenderer(renderer);
		ChartRenderingInfo info = new ChartRenderingInfo(
				new org.jfree.chart.entity.StandardEntityCollection());
		String filename = ServletUtilities.saveChartAsPNG(chart, 500,550,
				info, session);		
		request.setAttribute("filename", filename);
		request.setAttribute("nd14", ndV[0]);
		request.setAttribute("nd24", ndV[3]);
		request.setAttribute("nd34", ndV[6]);
		request.setAttribute("nd15", ndV[1]);
		request.setAttribute("nd25", ndV[4]);
		request.setAttribute("nd35", ndV[7]);
		request.setAttribute("nd16", ndV[2]);
		request.setAttribute("nd26", ndV[5]);
		request.setAttribute("nd36", ndV[8]);
		request.setAttribute("nd1", nd1);
		request.setAttribute("nd2", nd2);
		request.setAttribute("nd3", nd3);
		request.setAttribute("clinText", clinText);
		return mapping.findForward("success");
	}
}
