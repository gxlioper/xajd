package xgxt.qgzx.action;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.MessageInfo;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.ShgcForm;
import xgxt.qgzx.bjlhdx.BjlhQgzxService;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.service.QgzxGwtzService;
import xgxt.qgzx.service.QgzxService;
import xgxt.qgzx.service.XsgwglService;
import xgxt.qgzx.xbemy.XbemyQgzxDAO;
import xgxt.qgzx.zgdzdx.QgzxCjffService;
import xgxt.qgzx.zgdzdx.QgzxZgdzdxService;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.ToolClass;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import common.Globals;

public class QgzxOperactionAction {

	private static boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	public static ActionForward stuWorkPageInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 初始化页面，返回查询信息
		ShgcForm checkForm = (ShgcForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String xxdm = StandardOperation.getXxdm();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		String querry = " where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj ";// sql条件
		String tips = "";// 位置提示信息
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String dataType = request.getParameter("act");
		String nj = checkForm.getNj();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userTP = session.getAttribute("userType").toString();
		sql = "select xn,nd,xq from gwsqsjb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "xn", "nd",
				"xq" });
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xq = request.getParameter("xq");
		checkForm.setXn(xn);
		checkForm.setNd(nd);
		checkForm.setXq(xq);
		checkForm.setXmdm(DealString.toGBK(checkForm.getXmdm()));
		String bmdm = request.getParameter("xydm");
		String gwdm = checkForm.getXmdm();
		String gwxz = request.getParameter("gwxz");
		String yrdwdm = checkForm.getYrdwdm();
		String doType = request.getParameter("doType");
		String page = "";
		QgzxService service = new QgzxService();

		if ("gzjl".equalsIgnoreCase(doType)) {
			tips = "勤工助学 - 考核 - 工作记录";
			page = "/qgzx/qgzx_xsgzjl.jsp";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				// 长沙民政
				tips = "学生义工 - 考核 - 工作记录";
			}
		} else if ("xskh".equalsIgnoreCase(doType)) {
			tips = "勤工助学 - 考核 - 学生考核";
			page = "/qgzx/qgzx_xsgzkh.jsp";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				// 长沙民政
				tips = "学生义工 - 考核 - 学生考核";
			}
		} else {
			return mapping.findForward("false");
		}

		realTable = "xsgwxxb";
		pk = "a.xh||a.gwdm||a.sqsj";
		tableName = "view_xsgwxx";
		sql = "select distinct gwdm from view_gwxx";
		if (!userTP.equals("admin") || "xy".equalsIgnoreCase(userType)) {
			sql += " where xydm='" + userDep + "'";
			querry += " and b.xydm='" + userDep + "'";
			checkForm.setXydm(userDep);
		}
		List gwList = dao
				.getList(sql, new String[] {}, new String[] { "gwdm" });
		String sql1 = "select gwxzdm,gwxzmc from gwxzdmb";
		List gwxzList = dao.getList(sql1, new String[] {}, new String[] {
				"gwxzdm", "gwxzmc" });
		if (isNull(xn) || "".equalsIgnoreCase(xn)) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.xn = '" + xn + "' ";
		}
		if ("".equalsIgnoreCase(nd) || isNull(nd)) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.nd = '" + nd + "' ";
		}
		if ("".equalsIgnoreCase(xq) || isNull(xq)) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.xq = '" + xq + "' ";
		}
		if (isNull(nj)) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.nj = '" + nj + "' ";
		}
		if (isNull(gwdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.gwdm = '" + gwdm + "' ";
		}
		if (isNull(bmdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.xydm = '" + bmdm + "' ";
		}
		if (isNull(yrdwdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.yrdwdm = '" + yrdwdm + "' ";
		}

		querry += "and xyyj='通过' and xxyj='通过' ";
		if ("".equalsIgnoreCase(gwxz) || gwxz == null) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.gwxz= '" + gwxz + "' ";
		}

		colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd", "gwdm",
				"xq", "xh", "xm", "bjmc", "sqsj", "xyyj", "xxyj", "gzbx" };
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			// 上海工程
			colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd", "gwdm",
					"xq", "xh", "xm", "bjmc", "sqsj", "xyyj", "xxyj" };
		}
		sql = "select rownum 行号,(case nvl(a.xyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " 主键,a.* from "
				+ tableName
				+ " a,view_gwxx b" + querry + " order by xyyj desc) a";

		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("njList", dao.getNjList());// 发送年级列表
		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		request.setAttribute("xiaoquList", dao.getXiaoQuList());// 发送校区列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(""));// 发送专业列表
		request.setAttribute("gwList", gwList);// 发送专业列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		request.setAttribute("yrdwList", service.getYrdwList());
		return new ActionForward(page, false);
	}

	public static ActionForward addStuWorkLog(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 查看、修改单个记录
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		String doType = request.getParameter("doType");
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String pk = "xh||gwdm||sqsj";
		String sql = "";
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String time = GetTime.getXskhrq();
		sql = "select to_char(sysdate,'yyyymm') nowTime,to_char(sysdate,'dd') nowdata from dual";
		String timeTmp[] = dao.getOneRs(sql, new String[] {}, new String[] {
				"nowTime", "nowdata" });
		// 判断当前日期来决定可填写的日期
		String data = timeTmp[1];
		Integer dataTmp = Integer.parseInt(data);
		data = dataTmp.toString();
		if (act.equalsIgnoreCase("save")) {
			String hours = request.getParameter("day" + data);
			String xh = request.getParameter("xh");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
				// 中国美术学院
				String shours = request.getParameter("day" + data + "_ksxs");
				String sminute = request.getParameter("day" + data + "_ksfz");
				String ehours = request.getParameter("day" + data + "_jsxs");
				String eminute = request.getParameter("day" + data + "_jsfz");

				shours = shours == null || "".equalsIgnoreCase(shours) ? "0"
						: shours;
				sminute = sminute == null || "".equalsIgnoreCase(sminute) ? "0"
						: sminute;
				ehours = ehours == null || "".equalsIgnoreCase(ehours) ? "0"
						: ehours;
				eminute = eminute == null || "".equalsIgnoreCase(eminute) ? "0"
						: eminute;
				hours = shours + "-" + sminute + ":" + ehours + "-" + eminute;
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				// 云南艺术
				String gztd = DealString.toGBK(request.getParameter("gztd"));
				String gzzl = DealString.toGBK(request.getParameter("gzzl"));
				sql = "update xskhyb set day" + data + " = " + hours + ",day"
						+ data + "gztd='" + gztd + "', day" + data + "gzzl='"
						+ gzzl + "' where xh='" + xh + "' and time='" + time
						+ "'";
				dao.runUpdate(sql, new String[] {});
				dao.writeLog(sql, new String[] {}, null, "更新", request);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_LFSFXY)) {
				// 廊坊师范学院
				boolean flag = false;
				String gwmc = DealString.toGBK(request.getParameter("gwmc"));
				String ksH = request.getParameter("kshour");
				String ksM = request.getParameter("ksminute");
				String jsH = request.getParameter("jshour");
				String jsM = request.getParameter("jsminute");
				String gznr = DealString.toGBK(request.getParameter("gznr"));
				String gzkssj = ksH + "点" + ksM + "分";
				String gzjssj = jsH + "点" + jsM + "分";
				sql = "update xskhyb set day" + data + " = '" + hours
						+ "' where xh = ? and time = ?";
				flag = dao.runUpdate(sql, new String[] { xh, time });
				if (flag) {
					sql = "select count(*) num from xsrkhxxb where xh=? and time=? and day=?";
					int num = Integer.parseInt(dao.getOneRs(sql, new String[] {
							xh, time, data }, "num"));
					if (num > 0) {
						String[] inputCol = new String[] { "gzqssj", "gzjssj",
								"gznr", "gwmc" };
						String[] inputValue = { gzkssj, gzjssj, gznr, gwmc };
						flag = StandardOperation.update("xsrkhxxb", inputCol,
								inputValue, "xh||time||day", xh.trim()
										+ time.trim() + data.trim(), request);
					} else {
						String[] inputCol = new String[] { "xh", "time", "day",
								"gzqssj", "gzjssj", "gznr", "gwmc" };
						String[] inputValue = { xh, time, data, gzkssj, gzjssj,
								gznr, gwmc };
						flag = StandardOperation.insert("xsrkhxxb", inputCol,
								inputValue, request);
					}
				}

			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				// 上海工程 || 宁波理工
				// boolean flag = false;
				String gzsj = request.getParameter("gzsj");
				String gzqk = DealString.toGBK(request.getParameter("xsgzqk"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String gwdm = DealString.toGBK(request.getParameter("gwdm"));
				String yf = request.getParameter("yf");
				String sbsj = request.getParameter("sbsj");
				sbsj = xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) ? DealString
						.toGBK(request.getParameter("gwsbsj")) : sbsj;
				sql = "select count(*) num from xsgzkhb where xh = ? and gwdm=? and sbsj=? and yf=?";
				int num = Integer.parseInt(dao.getOneRs(sql, new String[] { xh,
						gwdm, sbsj, yf }, "num"));
				if (num > 0) {
					// update
					StandardOperation.update("xsgzkhb", new String[] { "gzsj",
							"gzqk", "bz" }, new String[] { gzsj, gzqk, bz },
							"xh||gwdm||sbsj||yf", xh.trim() + gwdm.trim()
									+ sbsj.trim() + yf.trim(), request);
				} else {
					// insert
					StandardOperation
							.insert("xsgzkhb", new String[] { "xh", "sbsj",
									"gwdm", "gzsj", "gzqk", "yf", "bz" },
									new String[] { xh, sbsj, gwdm, gzsj, gzqk,
											yf, bz }, request);
				}

			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合大学
				for (int i = 1; i < 32; i++) {
					String tmpValue = request.getParameter("day" + i + "O");
					String value = request.getParameter("day" + i);
					String nd = request.getParameter("nd");
					String yf = request.getParameter("yf");
					nd = nd == null ? "" : nd;
					yf = yf == null ? "0" : yf;

					yf = String.valueOf(Integer.parseInt(yf));
					time = nd + yf;
					if (!value.equalsIgnoreCase(tmpValue)) {
						// 修改
						StandardOperation.update("xskhyb", new String[] { "day"
								+ i }, new String[] { value }, "xh||time", xh
								+ time, request);
					}
				}
			} else {
				sql = "update xskhyb set day" + data + " = '" + hours
						+ "' where xh = '" + xh + "' and time = '" + time + "'";
				StandardOperation.update("xskhyb", sql, request);
				// dao.runUpdate(sql, new String[] { xh, time });
				// dao.writeLog(sql, new String[] { hours }, null, "更新",
				// request);
			}
		} else if (act.equalsIgnoreCase("tj")) {
			// 提交用于保存月工作时间和工资，用与酬金审核处的填写详单
			String xh = request.getParameter("xh");
			String ysj = request.getParameter("ysj");
			String yje = request.getParameter("yje");
			sql = "update xskhyb set sftj = 'yes',ysj = '" + ysj + "',yje = '"
					+ yje + "' where xh = ? and time = ?";
			dao.runUpdate(sql, new String[] { xh, time });
			// 保存时间
			String hours = request.getParameter("day" + data);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合大学
				for (int i = 1; i < 32; i++) {
					String tmpValue = request.getParameter("day" + i + "O");
					String value = request.getParameter("day" + i);
					if (!value.equalsIgnoreCase(tmpValue)) {
						// 修改
						StandardOperation.update("xskhyb", new String[] { "day"
								+ i }, new String[] { value }, "xh||time", xh
								+ time, request);
					}
				}
			} else {
				sql = "update xskhyb set day" + data + " = '" + hours
						+ "' where xh = '" + xh + "' and time = '" + time + "'";
				StandardOperation.update("xskhyb", sql, request);
			}
		}
		if ((act == null) || !act.equalsIgnoreCase("save")) {
			sql = "select "
					+ pk
					+ ",xh,nd,xm,xn,xb,gwdm,gwsbsj,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,xsgzqk,gzbx,yrdwdm,"
					+ "(select distinct yrdwmc from yrdwdmb b where b.yrdwdm=a.yrdwdm)yrdwmc,(select spbcbz from view_gwxx b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj)spbcbz from view_xsgwxx a where "
					+ pk + "='" + pkVal + "'";
			String[] colList = new String[] { pk, "xh", "nd", "xm", "xn", "xb",
					"gwdm", "gwsbsj", "nj", "sqsj", "xymc", "sfpks", "zymc",
					"lxdh", "bjmc", "yesNo", "xsgzqk", "gzbx", "yrdwmc",
					"spbcbz", "yrdwdm" };
			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], rs[i]);
			}
			String xh = map.get("xh");

			request.setAttribute("day" + data, "on");
			colList = new String[] { "pk", "day1", "day2", "day3", "day4",
					"day5", "day5", "day6", "day7", "day8", "day9", "day10",
					"day11", "day12", "day13", "day14", "day15", "day16",
					"day17", "day18", "day19", "day20", "day21", "day22",
					"day23", "day24", "day25", "day26", "day27", "day28",
					"day29", "day30", "day31" };
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合大学可任意修改每天的记录信息
				for (int i = 1; i < colList.length; i++) {
					request.setAttribute(colList[i], "on");
				}
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				// 云南艺术
				String gztd = "day" + data + "gztd";
				String gzzl = "day" + data + "gzzl";
				colList = new String[] { "pk", "day1", "day2", "day3", "day4",
						"day5", "day5", "day6", "day7", "day8", "day9",
						"day10", "day11", "day12", "day13", "day14", "day15",
						"day16", "day17", "day18", "day19", "day20", "day21",
						"day22", "day23", "day24", "day25", "day26", "day27",
						"day28", "day29", "day30", "day31", "gztd", "gzzl" };
				sql = "select a.xh||a.time pk,a." + gztd + " gztd, a." + gzzl
						+ " gzzl,"
						+ "a.* from xskhyb a where a.xh = ? and a.time = ? ";
			} else {
				sql = "select a.xh||a.time pk,a.* from xskhyb a where a.xh = ? and a.time = ? ";
			}
			rs = dao.getOneRs(sql, new String[] { xh, time }, colList);
			if (null == rs) {
				String sqlTmp = "insert into xskhyb (xh,time,sftj) values ('"
						+ xh + "','" + time + "','no')";
				dao.runUpdate(sqlTmp, new String[] {});
				rs = dao.getOneRs(sql, new String[] { xh, time }, colList);
			}
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], rs[i]);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
				// 中国美术学院
				map = service.getDetailsDaytime(map, pkVal);
			}

			String yf = dao.getOneRs(
					"select to_char(sysdate,'mm') month from dual",
					new String[] {}, "month");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				yf = dao
						.getOneRs(
								"select to_char(sysdate,'yyyy')||to_char(to_number(to_char(sysdate,'mm'))) month from dual",
								new String[] {}, "month");
			}
			sql = "select gzsj,gzqk,bz from xsgzkhb where xh||gwdm||sbsj=? and yf=?";
			map.put("yf", yf);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				// 上海工程 || 宁波理工
				HashMap<String, String> tmpMap = new HashMap<String, String>();
				tmpMap = dao.getMap(sql, new String[] { pkVal, yf },
						new String[] { "gzsj", "gzqk", "bz" });

				map.put("gzsj", tmpMap.get("gzsj"));
				map.put("gzqk", tmpMap.get("gzqk"));
				map.put("xsgzqk", tmpMap.get("gzqk"));
				map.put("bz", tmpMap.get("bz"));
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_LFSFXY)) {
				// 廊坊师范
				// String gwmc = DealString.toGBK(request.getParameter("gwmc"));
				sql = "select gzqssj,gzjssj,gznr from xsrkhxxb where xh=? and time=? and day=?";
				String[] outValue = { "gzqssj", "gzjssj", "gznr" };
				rs = dao.getOneRs(sql, new String[] { xh, time, data },
						outValue);
				if (rs != null && rs.length > 0) {
					for (int m = 0; m < outValue.length; m++) {
						map.put(outValue[m], rs[m]);
					}
				}
			}
			List sjList = null;
			ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
			String[] gwsqsj = dao.getOneRs("select mxsbc,mtzgxs from gwsqsjb",
					new String[] {}, new String[] { "mxsbc", "mtzgxs" });
			if (gwsqsj != null && gwsqsj.length != 0) {
				double i = 0;
				if (gwsqsj[1] != null) {
					i = Float.parseFloat(gwsqsj[1]);
				}
				while (i >= 0.5) {
					HashMap<String, String> sj = new HashMap<String, String>();
					sj.put("sj", String.valueOf(i));
					arraylist.add(sj);
					i = i - 0.5;
				}
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
				// 中国美术学院
				ArrayList<HashMap<String, String>> hlist = new ArrayList<HashMap<String, String>>();// 小时列表
				ArrayList<HashMap<String, String>> mlist = new ArrayList<HashMap<String, String>>();// 分钟列表
				for (int i = 0; i < 24; i++) {
					// 小时列表
					HashMap<String, String> hm = new HashMap<String, String>();
					hm.put("xs", String.valueOf(i));
					hlist.add(hm);
				}
				for (int i = 0; i < 60; i++) {
					// 分钟列表
					HashMap<String, String> hm = new HashMap<String, String>();
					hm.put("fz", String.valueOf(i));
					mlist.add(hm);
				}
				request.setAttribute("xsList", hlist);
				request.setAttribute("fzList", mlist);
			}
			sjList = arraylist;
			String[] tmp = dao.getOneRs(
					"select mxsbc,myzgxs from gwsqsjb where rownum=1",
					new String[] {}, new String[] { "mxsbc", "myzgxs" });
			String mxsbc = tmp[0];
			String myzgxs = tmp[1];
			mxsbc = mxsbc == null ? "" : mxsbc;
			myzgxs = myzgxs == null ? "" : myzgxs;

			request.setAttribute("mxsbc", mxsbc);
			request.setAttribute("myzgxs", myzgxs);
			request.setAttribute("sjList", sjList);
			request.setAttribute("rs", map);
			request.setAttribute("userType", userType);
			request.setAttribute("chkList", dao.getChkList(3));
			if ("xskh".equalsIgnoreCase(doType)) {
				request.setAttribute("xskh", "xskh");
				request.setAttribute("doType", "xskh");
			} else {
				request.setAttribute("gzjl", "gzjl");
				request.setAttribute("doType", "gzjl");
			}
			request.setAttribute("xxdm", xxdm);

			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				// 宁波理工学院
				request
						.setAttribute(
								"yf",
								dao
										.getOneRs(
												"select to_char(sysdate,'yyyy')||to_char(to_number(to_char(sysdate,'mm'))) nowmonth from dual",
												new String[] {}, "nowmonth"));
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				// 上海工程技术大学
				request.setAttribute("yf", dao.getOneRs(
						"select to_char(sysdate,'mm') nowmonth from dual",
						new String[] {}, "nowmonth"));
				if (!service.checkShsbsj(map.get("yrdwdm"))) {
					HashMap<String, String> timeMap = service
							.getKhsbsjByYrdw(map.get("yrdwdm"));
					request.setAttribute("allow", "上报时间：" + timeMap.get("kssj")
							+ "-" + timeMap.get("jssj"));
				}
				return mapping.findForward("shgcgzjl");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
				// 中国美术学院
				return mapping.findForward("zgmsxyGzjl");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				request.setAttribute("ndList", Base.getXnndList());
				request.setAttribute("yfList", dao.getYfList());
			}
			return mapping.findForward("success");
		}
		if ("xskh".equalsIgnoreCase(doType)) {
			// 学生考核
			String gzbx = DealString.toGBK(request.getParameter("gzbx"));
			StandardOperation.update("xsgwxxb", new String[] { "gzbx" },
					new String[] { gzbx }, pk, pkVal, request);
		} else {
			// 工作记录
			String xsgzqk = DealString.toGBK(request.getParameter("xsgzqk"));
			StandardOperation.update("xsgwxxb", new String[] { "xsgzqk" },
					new String[] { xsgzqk }, pk, pkVal, request);
		}
		return null;
	}

	// ///////学生卡号修改申请
	public static ActionForward StuKhModiApply(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		// String xxdm = StandardOperation.getXxdm();
		String sql = "";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		ShgcForm applyForm = (ShgcForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		String kh = "";
		String[] colList = null;
		colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc" };
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}

		if (xh != null && !xh.equalsIgnoreCase("")) {
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?";
			// if(Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm) ||
			// xxdm.equals(Globals.XXDM_NBLGXY)){
			// 中国美术学院 || 宁波理工
			kh = dao.getOneRs("select kh from view_xsxxb where xh=?",
					new String[] { xh }, "kh");
			// }else{
			// kh = dao.getOneRs("select max(kh) kh from view_xsgwxx where
			// xh=?",
			// new String[] { xh }, "kh");
			// }
			map = dao.getMap(sql, new String[] { xh }, colList);
			map.put("xgqkh", kh);
			map.put("xghkh", "");
			map.put("lxdh", "");
			map.put("sqyy", "");
			map.put("bz", "");
		} else {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], "");
			}
			map.put("xgqkh", "");
			map.put("xghkh", "");
			map.put("lxdh", "");
			map.put("sqyy", "");
			map.put("bz", "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ///////学生卡号修改审核
	public static ActionForward StuKhModiCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ShgcForm comm = (ShgcForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String sql = "";
		String realTable = "view_khxgsqb";
		String tips = "勤工助学 - 审核 - 卡号更改审核";
		String go = request.getParameter("go");
		String xydm = request.getParameter("xydm");
		String nj = request.getParameter("nj");
		String sfsh = DealString.toGBK(request.getParameter("sfsh"));

		if (userType != null && userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
			comm.setXydm(xydm);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			// 长沙民政
			tips = "学生义工 - 审核 - 卡号更改审核";
		}

		if ("".equalsIgnoreCase(xydm) || xydm == null) {
			xydm = "%";
		}
		if ("".equalsIgnoreCase(nj) || nj == null) {
			nj = "%";
		}
		if ("".equalsIgnoreCase(sfsh) || sfsh == null) {
			sfsh = "%";
		}

		if ("go".equalsIgnoreCase(go)) {
			String[] colList = { "bgcolor", "主键", "行号", "xh", "xm", "nj",
					"xymc", "zymc", "bjmc", "xgqkh", "xghkh", "xglx", "sfsh" };
			sql = "select (case nvl(sfsh,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
					+ "rownum 行号,xh 主键,xh,xm,nj,xymc,zymc,bjmc,xgqkh,xghkh,xglx,sfsh from "
					+ realTable
					+ " where nj like ? and xydm like ? and sfsh like ?";
			String[] colListCN = dao.getColumnNameCN(colList, realTable);
			Vector<Object> rs = new Vector<Object>();
			List topTr = dao.arrayToList(colList, colListCN);
			rs.addAll(dao.rsToVator(sql, new String[] { nj, xydm, sfsh },
					colList));
			String rsNum = "";
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			request.setAttribute("topTr", topTr);
			request.setAttribute("rsNum", rsNum);
			request.setAttribute("rs", rs);
		}

		setList(request, dao, tips);
		return mapping.findForward("success");
	}

	// /////////////设置列表
	private static void setList(HttpServletRequest request, DAO dao, String tips) {
		request.setAttribute("tips", tips);
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("chkList", dao.getChkList(3));
	}

	// ///////学生卡号申请保存
	public static ActionForward StuKhApplySave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String tableName = "khxgsqb";
		String xh = "";
		boolean Ok = false;
		String userType = request.getSession().getAttribute("userOnLine")
				.toString();
		if ("student".equalsIgnoreCase(userType)) {
			xh = request.getSession().getAttribute("userName").toString();
		} else {
			xh = DealString.toGBK(request.getParameter("xh"));
		}
		String xglx = DealString.toGBK(request.getParameter("xglx"));
		String xgqkh = DealString.toGBK(request.getParameter("xgqkh"));
		String xghkh = DealString.toGBK(request.getParameter("xghkh"));
		String lxdh = DealString.toGBK(request.getParameter("lxdh"));
		String sqyy = DealString.toGBK(request.getParameter("sqyy"));
		String bz = DealString.toGBK(request.getParameter("bz"));

		String tag = dao.returntag(
				"select * from " + tableName + " where xh=?",
				new String[] { xh });

		if ("empty".equalsIgnoreCase(tag)) {
			Ok = StandardOperation.insert(tableName, new String[] { "xh",
					"xglx", "xgqkh", "xghkh", "lxdh", "xgyy", "bz", "sfsh" },
					new String[] { xh, xglx, xgqkh, xghkh, lxdh, sqyy, bz,
							"未审核" }, request);
		} else {
			Ok = StandardOperation.update(tableName, new String[] { "xglx",
					"xgqkh", "xghkh", "lxdh", "xgyy", "bz", "sfsh" },
					new String[] { xglx, xgqkh, xghkh, lxdh, sqyy, bz, "未审核" },
					"xh", xh, request);
		}
		if (Ok) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("success");
	}

	// ///////////////卡号修改单个学生审核
	public static ActionForward sutKhModiOneCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("pkvalue");
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "xglx", "zymc",
				"bjmc", "xgqkh", "xghkh", "lxdh", "sqyy", "bz", "sfsh" };
		sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xglx,xgqkh,xghkh,lxdh,xgyy sqyy,bz,sfsh from view_khxgsqb where xh=?";
		map = dao.getMap(sql, new String[] { xh }, colList);
		request.setAttribute("rs", map);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("xh", xh);
		return mapping.findForward("success");
	}

	// ////////////卡号修改单个学生审核保存
	public static ActionForward sutKhModiOneCheckSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DAO dao = DAO.getInstance();
		StudentInfoService service = new StudentInfoService();
		String xh = request.getParameter("xh");
		String xghkh = request.getParameter("xghkh");
		String sfsh = DealString.toGBK(request.getParameter("sfsh"));
		String tableName = "khxgsqb";
		// String xxdm = StandardOperation.getXxdm();
		boolean Ok = StandardOperation.update(tableName,
				new String[] { "sfsh" }, new String[] { sfsh }, "xh", xh,
				request);
		if ("通过".equalsIgnoreCase(sfsh) && Ok) {
			StandardOperation.update("xsgwxxb", new String[] { "kh" },
					new String[] { xghkh }, "xh", xh, request);
			// 上海工程技术大学：将卡号保存在xsxxb中和资助共用
			// if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
			// ||xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)
			// ||xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)||xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)
			// ) {
			// boolean flag = true;
			// String sql = "select count(*) num from xsxxb where xh=?";
			// int num = Integer.parseInt(dao.getOneRs(sql,
			// new String[] { xh }, "num"));
			// if (num < 1) {
			// sql = "insert into
			// xsxxb(xh,xm,xb,xy,zymc,bjmc,bjdm,zydm,xydm,nj,xz) (select
			// xh,xm,xb,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,xz from view_xsjbxx
			// where xh=?)";
			// flag = dao.insert(sql, new String[] { xh });
			// dao.writeLog(sql, null, null, "插入:", request);
			// }
			// if (flag) {
			// StandardOperation.update("xsxxb", new String[] { "kh" },
			// new String[] { xghkh }, "xh", xh, request);
			// }
			// }
			service.modiStuinfo(xh, new String[] { "kh" },
					new String[] { xghkh }, request);// 修改学生信息中的卡号

		}
		request.setAttribute("result", "view");
		return new ActionForward("/khModiCheckOne.do?pkvalue=" + xh, false);
	}

	public static ActionForward gwsbReportPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String currXn = dao.getConf(0);
		String currXq = dao.getConf(1);
		String xyrs = request.getParameter("xyrs");
		String gwdm = DealString.toGBK(request.getParameter("gwdm"));
		String yrdwdm = request.getParameter("yrdwdm");
		String gznr = DealString.toGBK(request.getParameter("gznr"));
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String xxdm = StandardOperation.getXxdm();
		String xxmc = StandardOperation.getXxmc();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			sql = "select a.lxr,b.bmmc xymc from yrdwdmb a,zxbz_xxbmdm b where a.xydm=b.bmdm and a.yrdwdm=?";
			String[] colV = dao.getOneRs(sql, new String[] { yrdwdm },
					new String[] { "lxr", "xymc" });
			map.put("xn", currXn);
			map.put("xq", currXq);
			map.put("xyrs", xyrs);
			map.put("gwdm", gwdm);
			map.put("xymc", colV[1]);
			map.put("lxr", colV[0]);
			request.setAttribute("rs", map);
			return new ActionForward("/qgzx/hzzy/print/hzzy_sqqgzxgwb.jsp");
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {
			sql = "select lxr,yrdwmc,lxdh from yrdwdmb where yrdwdm=?";
			String[] colList = { "yrdwmc", "fzr", "lxdh", "gznr", "xyrs" };
			String[] yrdwxx = dao.getOneRs(sql, new String[] { yrdwdm },
					new String[] { "yrdwmc", "lxr", "lxdh" });
			map.put(colList[0], yrdwxx[0]);
			map.put(colList[1], yrdwxx[1]);
			map.put(colList[2], yrdwxx[2]);
			map.put(colList[3], gznr);
			map.put(colList[4], xyrs);
			request.setAttribute("xxmc", xxmc);
			request.setAttribute("rs", map);
			return mapping.findForward("success_zzsf");
		}
		return mapping.findForward("success");
	}

	public static ActionForward gwsqReportPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String xxmc = StandardOperation.getXxmc();
		String sql = "";
		String xh = request.getParameter("xh");
		String userType = request.getSession().getAttribute("userOnLine")
				.toString();
		if ("student".equalsIgnoreCase(userType)) {
			xh = request.getSession().getAttribute("userName").toString();
		}
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdm"));
		HashMap<String, String> map = new HashMap<String, String>();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {
			String[] colList = { "xh", "xm", "xymc", "zymc", "nj", "lxdh",
					"gwdm", "xb", "sfpks" };
			sql = "select xh,xm,xymc,zymc,nj,lxdh,gwdm,xb,sfpks from view_xsgwxx where xh=? and gwdm||'-'||gwsbsj =?";
			map = dao.getMap(sql, new String[] { xh, gwdmgwsbsj }, colList);
			request.setAttribute("xxmc", xxmc);
			request.setAttribute("rs", map);
			return mapping.findForward("success_zzsf");
		}
		return mapping.findForward("success");
	}

	public static ActionForward xskhPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String xh = request.getParameter("xh");
		List sjList = null;
		ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
		String[] colList = { "xm", "yrdwmc", "xymc", "bjmc", "kh", "lxdh",
				"xsgzqk", "gzbx" };
		String sql = "select a.xm,c.yrdwmc,a.xymc,a.bjmc,a.kh,a.lxdh,a.xsgzqk,a.gzbx from view_xsgwxx a,view_gwxx b,yrdwdmb c where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and b.sqdw=c.yrdwdm and a.xh=?";
		HashMap<String, String> map = dao.getMap(sql, new String[] { xh },
				colList);
		String[] gwsqsj = dao.getOneRs("select mxsbc,mtzgxs from gwsqsjb",
				new String[] {}, new String[] { "mxsbc", "mtzgxs" });
		if (gwsqsj != null) {
			double i = Float.parseFloat(gwsqsj[1]);
			while (i >= 0.5) {
				HashMap<String, String> sj = new HashMap<String, String>();
				sj.put("sj", String.valueOf(i));
				arraylist.add(sj);
				i = i - 0.5;
			}
		}
		sql = "select to_char(sysdate,'yyyymm') nowTime,to_char(sysdate,'dd') nowdata from dual";
		String timeTmp[] = dao.getOneRs(sql, new String[] {}, new String[] {
				"nowTime", "nowdata" });
		String time = timeTmp[0];
		colList = new String[] { "pk", "day1", "day2", "day3", "day4", "day5",
				"day5", "day6", "day7", "day8", "day9", "day10", "day11",
				"day12", "day13", "day14", "day15", "day16", "day17", "day18",
				"day19", "day20", "day21", "day22", "day23", "day24", "day25",
				"day26", "day27", "day28", "day29", "day30", "day31" };
		sql = "select a.xh||a.time pk,a.* from xskhyb a where a.xh = ? and a.time = ? ";
		String[] rs = dao.getOneRs(sql, new String[] { xh, time }, colList);
		if (null == rs) {
			String sqlTmp = "insert into xskhyb (xh,time,sftj) values ('" + xh
					+ "','" + time + "','no')";
			dao.runUpdate(sqlTmp, new String[] {});
			rs = dao.getOneRs(sql, new String[] { xh, time }, colList);
		}
		for (int i = 0; i < colList.length; i++) {
			map.put(colList[i], rs[i]);
		}
		sjList = arraylist;
		request.setAttribute("mxsbc", gwsqsj[0]);
		request.setAttribute("mtzgxs", gwsqsj[1]);
		request.setAttribute("rs", map);
		request.setAttribute("sjList", sjList);
		return mapping.findForward("success");
	}

	/**
	 * 云南艺术勤工助学申请
	 */
	public static ActionForward YnysGwsqReportPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = "";
		String userOnLine = session.getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
		String tableName = request.getParameter("tableName");
		String gwValue = DealString.toGBK(request.getParameter("gwValue"));
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));

		String sql = "select xxmc from xtszb";
		String xxmc = dao.getOneRs(sql, new String[] {},
				new String[] { "xxmc" })[0];
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("xxmc", xxmc);

		if (doType == null || doType.equals(null)) {
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String currentTime = date.toString().substring(0, 19).replaceAll(
					"-", "").replaceAll(" ", "").replaceAll(":", "");
			QgzxForm applyForm = (QgzxForm) form;
			HashMap<String, String> map = new HashMap<String, String>();
			String xh = applyForm.getXh();
			if (userOnLine.equalsIgnoreCase("student")) {
				xh = session.getAttribute("userName").toString();
			}
			// 上海工程
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				request.setAttribute("showshgc", "showshgc");
				String zzmm = dao
						.getOneRs(
								"select b.zzmmmc zzmm from (select * from bks_xsqtxx where xh =?) a left join zzmmdmb b on a.zzmmm = b.zzmmdm",
								new String[] { xh }, "zzmm");
				map.put("zzmm", zzmm);
			}
			// 云南艺术
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				String[] infoList = { "zzmmmc", "mzmc", "ssbh", "jtdz", "jtyb",
						"jtdh" };
				sql = "select ssbh,lxdzxx,(select zzmmmc from view_stu_details a where a.xh=b.xh)zzmmmc,(select mzmc from view_stu_details a where a.xh=b.xh) mzmc,(select jtszd from view_xsfzxx a where a.xh=b.xh)jtdz,(select yb from view_xsfzxx a where a.xh=b.xh)jtyb,(select lxdh1 from view_xsfzxx a where a.xh=b.xh)jtdh from view_xsjbxx b where b.xh=?";
				String[] infoVList = dao.getOneRs(sql, new String[] { xh },
						infoList);
				if (infoVList != null && infoVList.length > 0) {
					for (int i = 0; i < infoList.length; i++) {
						map.put(infoList[i], infoVList[i]);
					}
				}
			}

			if (!("".equalsIgnoreCase(xh) || xh == null)) {
				if (!dao.isKns(xh)) {
					request.setAttribute("IsKns", "no");
				} else {
					request.setAttribute("IsKns", "yes");
				}
			}
			// TODO 学生勤工助学
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
				// 中北大学
				if (!("".equalsIgnoreCase(xh) || xh == null)) {
					sql = "select * from view_knsxx where  xxsh='通过' and xh=?";
					String tag = dao.returntag(sql, new String[] { xh });
					if ("empty".equalsIgnoreCase(tag)) {
						request.setAttribute("IsKns", "no");
						return mapping.findForward("success");
					}
				}
			}

			String[] sj = { "早自修（7:30—8:20）", "第1-2节（8:30—10:05）",
					"第3-4节（10:25—12:00）", "午休（12:00—13:45）",
					"第5-6节（13:50—15:25）", "第7-8节（15:30—17:05）",
					"晚自修（17:50—20:15）" };
			String[] xq = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };

			if (xh != null && !xh.equalsIgnoreCase("")) {
				List<HashMap> kxList = new ArrayList<HashMap>();
				sql = "select kxbz from xsqgzxsjb where xh = ?  order by xq,sj ";
				String[] kxbz = dao.getOneRs(sql, new String[] { xh },
						new String[] { "kxbz" });
				if (kxbz != null && kxbz.length > 48) {
					String[] kx = new String[7];
					int j = 0;
					for (int i = 0; i < 7; i++) {
						kx[i] = kxbz[i].substring(j, j + 7);
						j += 7;
						char[] a = kx[i].toCharArray();
						HashMap<String, String> map2 = new HashMap<String, String>();
						for (int p = 0; p < 7; p++) {
							map2.put(xq[p], String.valueOf(a[p]));
						}
						map2.put("sj", sj[i]);
						map2.put("sjIndex", String.valueOf(i));
						kxList.add(map2);
					}
					request.setAttribute("kxList", kxList);
				} else {
					request.setAttribute("kxbz", "no");
				}
			}

			sql = "select * from view_xsjbxx where xh=?";
			String[] colList = dao
					.getColumnName("select * from view_xsjbxx where 1=2");
			String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
			}
			sql = "select * from gwsqsjb where kssj<" + currentTime
					+ " and jssj>" + currentTime;
			String tag = dao.returntag(sql, new String[] {});
			if ("empty".equalsIgnoreCase(tag)) {
				request.setAttribute("sqsjFlag", "1");
				map.put("xn", "");
				map.put("nd", "");
				map.put("xq", "");
			} else {
				sql = "select xn,nd,xq from gwsqsjb where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
						"xn", "nd", "xq" });
				map.put("xn", tmp[0]);
				map.put("nd", tmp[1]);
				map.put("xq", tmp[2]);
			}
			sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where SHJG='通过'";
			List gwList = dao.getList(sql, new String[] {}, new String[] {
					"gwdm", "gwdmgwsbsj" });
			request.setAttribute("gwList", gwList);
			sql = "select * from kcjsjdmb";
			List kysjList = dao.getList(sql, new String[] {}, new String[] {
					"kcjsjdm", "kcjsjmc" });
			request.setAttribute("kysjList", kysjList);
			if (gwValue != null && !gwValue.equalsIgnoreCase("")) {
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where gwdm||gwsbsj =?";
				String[] tmp = dao.getOneRs(sql, new String[] { gwValue },
						new String[] { "gwdm", "gwdmgwsbsj" });
				map.put("gwdm", tmp[0]);
				map.put("xmdm", tmp[1]);
				if (map.get("gwdm") != null)
					map.put("gwdm", map.get("gwdm").toString());
			}
			map.put("stuExists", "yes");
			map.put("userType", userType);
			request.setAttribute("do", "no");// 用于判断是不是进行修改操作，no表示不是修改操作
			request.setAttribute("rs", map);
			request.setAttribute("chkList", dao.getChkList(2));
			// request.setAttribute("IsKns", "yes");
			return mapping.findForward("success");
		} else {
			// 云南艺术
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				StringBuffer sb = new StringBuffer();
				sb
						.append("select a.*,(select zzmmmc from view_stu_details b where a.xh=b.xh)zzmmmc,");
				sb
						.append("(select mzmc from view_stu_details b where a.xh=b.xh)mzmc,(select jtszd from view_xsfzxx b where a.xh=b.xh) jtdz");
				sb
						.append(",(select yb from view_xsfzxx b where a.xh=b.xh)jtyb,(select lxdh1 from view_xsfzxx b where a.xh=b.xh)jtdh from ");
				sb.append(tableName);
				sb.append(" a ");
				sql = sb.toString();
			} else {
				sql = "select * from " + tableName;
			}
			String[] colList = dao.getColumnName(sql);
			String[] outValue = new String[colList.length];
			HashMap<String, String> map = new HashMap<String, String>();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				request.setAttribute("IsKns", "yes");
				// 云南艺术
				StringBuffer sb = new StringBuffer();
				sb
						.append("select a.*,(select zzmmmc from view_stu_details b where a.xh=b.xh)zzmmmc,");
				sb
						.append("(select mzmc from view_stu_details b where a.xh=b.xh)mzmc,(select jtszd from view_xsfzxx b where a.xh=b.xh) jtdz");
				sb
						.append(",(select yb from view_xsfzxx b where a.xh=b.xh)jtyb,(select lxdh1 from view_xsfzxx b where a.xh=b.xh)jtdh from ");
				sb.append(tableName);
				sb.append(" a where xh||gwdm||sqsj = ? ");
				sql = sb.toString();
			} else {
				sql = "select a.* from " + tableName
						+ " a where xh||gwdm||sqsj = ?";
			}
			outValue = dao.getOneRs(sql, new String[] { pkValue }, colList);
			for (int i = 0; i < outValue.length; i++) {
				if (outValue[i] == null || outValue[i].equals(null)) {
					map.put(colList[i].toLowerCase(), "");
				} else {
					map.put(colList[i].toLowerCase(), outValue[i].toString());
				}
			}
			// gwList
			sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb";
			List gwList = dao.getList(sql, new String[] {}, new String[] {
					"gwdm", "gwdmgwsbsj" });
			// kysjList
			sql = "select * from kcjsjdmb";
			List kysjList = dao.getList(sql, new String[] {}, new String[] {
					"kcjsjdm", "kcjsjmc" });

			request.setAttribute("kysjList", kysjList);
			request.setAttribute("gwList", gwList);
			request.setAttribute("do", "yes");// 修改操作
			request.setAttribute("rs", map);
			return mapping.findForward("success");
		}
	}

	public static ActionForward YnysWorkAdjust(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		QgzxService service = new QgzxService();
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String tableName = "view_qgzx_gwtz";
		String realTable = "qgzx_gwtzb";
		String pk = "xn||xq||xh||tzqgw||tzsj";
		QgzxForm qgzxform = (QgzxForm) form;
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);

		StringBuffer querry = new StringBuffer();
		List<HashMap<String, String>> gwList = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		List<HashMap<String, String>> topTr = null;
		String tips = "勤工助学 - 审核 - 岗位调整";
		String[] colList = { "主键", "行号", "xn", "xqmc", "xh", "xm", "bjmc",
				"tzqgw", "tzhgw", "tzsj" };
		String[] CNcolList = { "主键", "行号", "学年", "学期", "学号", "姓名", "班级",
				"调整前岗位", "调整后岗位", "调整时间" };

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			// 广州大学
			colList = new String[] { "主键", "行号", "xn", "xqmc", "xh", "xm",
					"bjmc", "tzqgw", "tzhgw", "tzsj", "shjg" };
			CNcolList = new String[] { "主键", "行号", "学年", "学期", "学号", "姓名",
					"班级", "调整前岗位", "调整后岗位", "调整时间", "学校审核" };
		}
		topTr = dao.arrayToList(colList, CNcolList);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			// 长沙民政
			tips = "学生义工 - 审核 - 岗位调整";
		}
		if ("xy".equalsIgnoreCase(userType)) {
			qgzxform.setXydm(userDep);
			sql = "select distinct gwdm,gwdm from view_gwxx where xydm=?";
			gwList = dao.getList(sql, new String[] { userDep }, new String[] {
					"gwdm", "gwdm" });
		} else {
			sql = "select distinct gwdm,gwdm from view_gwxx";
			gwList = dao.getList(sql, new String[] {}, new String[] { "gwdm",
					"gwdm" });
		}

		querry = ToolClass.getGWTZBQuery(qgzxform);
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			sql = "select "
					+ pk
					+ " 主键,rownum 行号,rownum r ,a.*,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from "
					+ tableName + " a " + querry.toString();
			vector.addAll(dao.rsToVator("select * from ("
					+ sql
					+ ") where r>"
					+ qgzxform.getPages().getStart()
					+ " and r<="
					+ (qgzxform.getPages().getStart() + qgzxform.getPages()
							.getPageSize()), new String[] {}, colList));
			rsNum = String.valueOf(vector != null ? vector.size() : "");
			qgzxform.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) num from("
							+ sql + ")", new String[] {}, "num")));
		}

		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", qgzxform.getXn());
		paramter.put("nd", qgzxform.getNd());
		paramter.put("xq", qgzxform.getXq());
		paramter.put("yrdwdm", qgzxform.getYrdwdm());
		paramter.put("gwxzdm", qgzxform.getGwxz());

		request.setAttribute("gwList", service.getGwmcxxList(paramter, "no"));// 所有审核通过岗位列表
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);
		// request.setAttribute("gwList", gwList);
		request.setAttribute("gwxzList", service.getGwxzList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("success");
	}

	public static ActionForward YnysWorkAdjustInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
		String sql = "";
		String realTable = "qgzx_gwtzb";
		String tableName = "view_qgzx_gwtz";
		String pk = "xn||xq||xh||tzqgw||tzsj";
		String xxdm = StandardOperation.getXxdm();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		// List gwList = null;
		List gwsbsjList = null;
		List qgwList = null;
		List qgwsbsjList = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String[] colList = { "xh", "xm", "nj", "xymc", "zymc", "bjmc", "tzyy",
				"tzsj", "tzqgzxn", "tzqgznd", "tzqgzxq", "tzqgw", "tzqgwsbsj",
				"tzqkcjqgzxsj", "tzhgzxn", "tzhgznd", "tzhgzxq", "tzhgw",
				"tzhgwsbsj", "tzhkcjqgzxsj", "tzqgzxqmc" };
		boolean del = false;
		if ("xy".equalsIgnoreCase(userType)) {
			// 异动后岗位信息列表
			// sql = "select distinct gwdm,gwdm from view_gwxx where xydm=? and
			// shjg='通过' and gzjsrq>to_char(sysdate,'yyyymmdd')";
			// gwList = dao.getList(sql, new String[] { userDep }, new String[]
			// {
			// "gwdm", "gwdm" });
			sql = "select distinct gwdm,gwdm from view_gwxx where xydm=? and shjg='通过'";
			qgwList = dao.getList(sql, new String[] { userDep }, new String[] {
					"gwdm", "gwdm" });

			sql = "select distinct gwsbsj,gwsbsj from view_gwxx where xydm=?";
			gwsbsjList = dao.getList(sql, new String[] { userDep },
					new String[] { "gwsbsj", "gwsbsj" });
			qgwsbsjList = gwsbsjList;
		} else {
			// 异动后岗位信息列表
			// sql = "select distinct gwdm,gwdm from view_gwxx where shjg='通过'
			// and gzjsrq>to_char(sysdate,'yyyymmdd')";
			// gwList = dao.getList(sql, new String[] {}, new String[] { "gwdm",
			// "gwdm" });

			sql = "select distinct gwdm,gwdm from view_gwxx where shjg='通过'";
			qgwList = dao.getList(sql, new String[] {}, new String[] { "gwdm",
					"gwdm" });

			// 异动后岗位申报时间信息列表
			// sql = "select distinct
			// gwsbsj,substr(gwsbsj,1,4)||'-'||substr(gwsbsj,5,2)||'-'||substr(gwsbsj,7,2)||'
			// '||"
			// +
			// "substr(gwsbsj,9,2)||':'||substr(gwsbsj,11,2)||':'||substr(gwsbsj,13,2)
			// labgwsbsj from view_gwxx where shjg='通过' and
			// gzjsrq>to_char(sysdate,'yyyymmdd')";
			// gwsbsjList = dao.getList(sql, new String[] {}, new String[] {
			// "gwsbsj", "labgwsbsj" });
			sql = "select distinct gwsbsj,substr(gwsbsj,1,4)||'-'||substr(gwsbsj,5,2)||'-'||substr(gwsbsj,7,2)||' '||"
					+ "substr(gwsbsj,9,2)||':'||substr(gwsbsj,11,2)||':'||substr(gwsbsj,13,2) labgwsbsj from view_gwxx where shjg='通过'";
			qgwsbsjList = dao.getList(sql, new String[] {}, new String[] {
					"gwsbsj", "labgwsbsj" });
			;
		}

		if ("add".equalsIgnoreCase(doType)) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], "");
			}
		} else if ("modi".equalsIgnoreCase(doType)) {
			sql = "select a.*,(select xqmc from xqdzb b where a.tzqgzxq=b.xqdm)tzqgzxqmc from "
					+ tableName + " a where " + pk + "=?";
			map = dao.getMap(sql, new String[] { pkValue }, colList);
		} else if ("del".equalsIgnoreCase(doType)) {
			sql = "delete " + realTable + " where " + pk + "=?";
			del = dao.runUpdate(sql, new String[] { pkValue });
			if (del) {
				request.setAttribute("result", "ok");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/qgzx_work_adjust.do");
		}
		request.setAttribute("writeAble", "yes");
		request.setAttribute("gwList", qgwList);
		request.setAttribute("gwsbsjList", qgwsbsjList);
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("qgwList", qgwList);
		request.setAttribute("qgwsbsjList", qgwsbsjList);
		request.setAttribute("qxnList", dao.getXnndList());
		request.setAttribute("qxqList", dao.getXqList());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", map);
		// 长沙民政 liang 把doType和xxdm保存到request中
		request.setAttribute("xxdm", xxdm);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			request.setAttribute("doType", doType);
		}

		return mapping.findForward("success");
	}

	/**
	 * 岗位调整保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public static ActionForward YnysWorkAdjustSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxForm model = (QgzxForm) form;
		QgzxGwtzService service = new QgzxGwtzService();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		boolean result = false;
		model.setOldXh(request.getParameter("oldStudent"));
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setNd(Base.currNd);
		model.setPk("xn||xq||xh||tzqgw||tzsj");
		model.setUserType(userType);

		String changeStudent = request.getParameter("changeStudent");
		if (changeStudent != null && !changeStudent.equalsIgnoreCase("")) {
			result = service.gwtzGwth(model, request);// 岗位调整替换岗位
		} else {
			// 岗位调整调换岗位
			result = service.gwtz(model, request);
		}
		request.setAttribute("result", result ? "ok" : "no");
		return mapping.findForward("success");
	}

	/**
	 * 岗位调整审核查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public static ActionForward workAdjustAudi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		HttpSession session = request.getSession();
		QgzxForm qgzxform = (QgzxForm) form;
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);

		StringBuffer querry = new StringBuffer();
		String sql = "";
		String tableName = "view_qgzx_gwtz";
		String pk = "xn||xq||xh||tzqgw||tzsj";

		String[] colList = { "主键", "xn", "xqmc", "xh", "xm", "bjmc", "tzqgw",
				"tzhgw", "tzsj", "shjg" };
		String[] CNcolList = { "主键", "学年", "学期", "学号", "姓名", "班级", "调整前岗位",
				"调整后岗位", "调整时间", "学校审核" };
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				CNcolList);

		// 查询
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			querry = ToolClass.getGWTZBQuery(qgzxform);// 获取查询条件
			sql = "select "
					+ pk
					+ " 主键,rownum 行号,rownum r ,a.*,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from "
					+ tableName + " a " + querry.toString();
			List<String[]> rs = dao.rsToVator("select * from ("
					+ sql
					+ ") where r>"
					+ qgzxform.getPages().getStart()
					+ " and r<="
					+ (qgzxform.getPages().getStart() + qgzxform.getPages()
							.getPageSize()), new String[] {}, colList);
			qgzxform.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) num from("
							+ sql + ")", new String[] {}, "num")));
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}

		request.setAttribute("topTr", topTr);
		request.setAttribute("userType", userType);
		request.setAttribute("gwList", service.getGwmcList(userName));
		request.setAttribute("gwxzList", service.getGwxzList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xqList", dao.getXqList());
		return mapping.findForward("success");
	}

	/**
	 * 保存勤工助学批量审核信息
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 */
	public static ActionForward saveAdjustBatchAudi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxForm model = (QgzxForm) form;
		QgzxGwtzService service = new QgzxGwtzService();
		String shjg = request.getParameter("shjg");

		model.setShjg(shjg);
		boolean result = service.adjustBatchAudi(model);

		request.setAttribute("result", result == true ? "ok" : "no");
		return new ActionForward("/qgzx_work_adjustAudi.do?go=go");
	}

	/**
	 * 显示勤工助学调整信息
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 */
	public static ActionForward showAdjustDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		QgzxGwtzService service = new QgzxGwtzService();
		String pkValue = request.getParameter("pkValue");
		String pk = "xn||xq||xh||tzqgw||tzsj";

		request.setAttribute("rs", service.queryGwtzxxxx(pk, pkValue));
		return mapping.findForward("success");
	}

	public static ActionForward JsxxGwsqSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		ActionForward newFwd = new ActionForward();
		String userType = session.getAttribute("userOnLine").toString();
		QgzxForm from = (QgzxForm) form;

		String pkValue = request.getParameter("pkValue");

		String sql = "";
		// 保存数据
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = from.getXh();
		}
		// 岗位申请
		sql = "select xn,nd,xq from gwsqsjb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] { "xn",
				"nd", "xq" });
		String xn = tmp[0];
		String nd = tmp[1];
		String xq = tmp[2];
		boolean del = true;
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdm"));
		if ("".equalsIgnoreCase(gwdmgwsbsj) || gwdmgwsbsj == null) {
			gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdmmodi"));
		}

		String sfpks = "";
		if (dao.isKns(xh)) {
			sfpks = "是";
		} else {
			sfpks = "否";
		}

		String gwdm = gwdmgwsbsj.split("-")[0];
		String gwsbsj = gwdmgwsbsj.split("-")[1];
		String xssq = DealString.toGBK(request.getParameter("xssq").trim());
		String bz = DealString.toGBK(request.getParameter("bz").trim());
		String kcjqgzxsj = DealString.toGBK(request.getParameter("kcjqgzxsj"));
		String lxdh = DealString.toGBK(request.getParameter("lxdh"));
		String xxqk = DealString.toGBK(request.getParameter("xxqk").trim());
		String bkhywbjgkc = DealString
				.toGBK(request.getParameter("bkhywbjgkc"));
		String btzw = DealString.toGBK(request.getParameter("btzw"));
		String jtmntg = DealString.toGBK(request.getParameter("jtmntg"));
		String hjmnsqfy = DealString.toGBK(request.getParameter("hjmnsqfy"));
		String qjxfs = DealString.toGBK(request.getParameter("qjxfs"));
		String yhdkzljje = DealString.toGBK(request.getParameter("yhdkzljje"));
		String mqqgzxqk = DealString.toGBK(request.getParameter("mqqgzxqk")
				.trim());
		String yhtc = DealString.toGBK(request.getParameter("yhtc"));
		String jg = DealString.toGBK(request.getParameter("jg"));
		String cxdd = DealString.toGBK(request.getParameter("cxdd"));
		String sfyytytp = DealString.toGBK(request.getParameter("sfyytytp"));
		String sfgr = DealString.toGBK(request.getParameter("sfgr"));
		String sflszn = DealString.toGBK(request.getParameter("sflszn"));
		String sfwsrh = DealString.toGBK(request.getParameter("sfwsrh"));
		String sfzbh = DealString.toGBK(request.getParameter("sfzbh"));
		String sfdbh = DealString.toGBK(request.getParameter("sfdbh"));
		String sffmsxg = DealString.toGBK(request.getParameter("sffmsxg"));
		String sfcnh = DealString.toGBK(request.getParameter("sfcnh"));
		String sfdsr = DealString.toGBK(request.getParameter("sfdsr"));
		String jtcy1_xm = DealString.toGBK(request.getParameter("jtcy1_xm"));
		String jtcy1_cw = DealString.toGBK(request.getParameter("jtcy1_cw"));
		String jtcy1_nl = DealString.toGBK(request.getParameter("jtcy1_nl"));
		String jtcy1_jkzk = DealString
				.toGBK(request.getParameter("jtcy1_jkzk"));
		String jtcy1_gzdwjzw = DealString.toGBK(request
				.getParameter("jtcy1_gzdwjzw"));
		String jtcy1_nsr = DealString.toGBK(request.getParameter("jtcy1_nsr"));
		String jtcy2_xm = DealString.toGBK(request.getParameter("jtcy2_xm"));
		String jtcy2_cw = DealString.toGBK(request.getParameter("jtcy2_cw"));
		String jtcy2_nl = DealString.toGBK(request.getParameter("jtcy2_nl"));
		String jtcy2_jkzk = DealString
				.toGBK(request.getParameter("jtcy2_jkzk"));
		String jtcy2_gzdwjzw = DealString.toGBK(request
				.getParameter("jtcy2_gzdwjzw"));
		String jtcy2_nsr = DealString.toGBK(request.getParameter("jtcy2_nsr"));
		String jtcy3_xm = DealString.toGBK(request.getParameter("jtcy3_xm"));
		String jtcy3_cw = DealString.toGBK(request.getParameter("jtcy3_cw"));
		String jtcy3_nl = DealString.toGBK(request.getParameter("jtcy3_nl"));
		String jtcy3_jkzk = DealString
				.toGBK(request.getParameter("jtcy3_jkzk"));
		String jtcy3_gzdwjzw = DealString.toGBK(request
				.getParameter("jtcy3_gzdwjzw"));
		String jtcy3_nsr = DealString.toGBK(request.getParameter("jtcy3_nsr"));
		String jtcy4_xm = DealString.toGBK(request.getParameter("jtcy4_xm"));
		String jtcy4_cw = DealString.toGBK(request.getParameter("jtcy4_cw"));
		String jtcy4_nl = DealString.toGBK(request.getParameter("jtcy4_nl"));
		String jtcy4_jkzk = DealString
				.toGBK(request.getParameter("jtcy4_jkzk"));
		String jtcy4_gzdwjzw = DealString.toGBK(request
				.getParameter("jtcy4_gzdwjzw"));
		String jtcy4_nsr = DealString.toGBK(request.getParameter("jtcy4_nsr"));
		String jtcy5_xm = DealString.toGBK(request.getParameter("jtcy5_xm"));
		String jtcy5_cw = DealString.toGBK(request.getParameter("jtcy5_cw"));
		String jtcy5_nl = DealString.toGBK(request.getParameter("jtcy5_nl"));
		String jtcy5_jkzk = DealString
				.toGBK(request.getParameter("jtcy5_jkzk"));
		String jtcy5_gzdwjzw = DealString.toGBK(request
				.getParameter("jtcy5_gzdwjzw"));
		String jtcy5_nsr = DealString.toGBK(request.getParameter("jtcy5_nsr"));
		String mnyjngzfy = DealString.toGBK(request.getParameter("mnyjngzfy"));
		String jtjjknqk = DealString.toGBK(request.getParameter("jtjjknqk")
				.trim());
		boolean result = false;
		if (del) {
			del = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj", xh
					+ gwdm + gwsbsj, request);
			if (del) {
				result = StandardOperation.insert("xsgwxxb", new String[] {
						"mnyjngzfy", "xh", "gwdm", "xssq", "bz", "kcjqgzxsj",
						"lxdh", "xn", "nd", "xq", "gwsbsj", "sfpks", "xxqk",
						"bkhywbjgkc", "btzw", "jtmntg", "hjmnsqfy", "qjxfs",
						"yhdkzljje", "mqqgzxqk", "yhtc", "jg", "cxdd",
						"sfyytytp", "sfgr", "sflszn", "sfwsrh", "sfzbh",
						"sfdbh", "sffmsxg", "sfcnh", "sfdsr", "jtcy1_xm",
						"jtcy1_cw", "jtcy1_nl", "jtcy1_jkzk", "jtcy1_gzdwjzw",
						"jtcy1_nsr", "jtcy2_xm", "jtcy2_cw", "jtcy2_nl",
						"jtcy2_jkzk", "jtcy2_gzdwjzw", "jtcy2_nsr", "jtcy3_xm",
						"jtcy3_cw", "jtcy3_nl", "jtcy3_jkzk", "jtcy3_gzdwjzw",
						"jtcy3_nsr", "jtcy4_xm", "jtcy4_cw", "jtcy4_nl",
						"jtcy4_jkzk", "jtcy4_gzdwjzw", "jtcy4_nsr", "jtcy5_xm",
						"jtcy5_cw", "jtcy5_nl", "jtcy5_jkzk", "jtcy5_gzdwjzw",
						"jtcy5_nsr", "jtjjknqk" }, new String[] { mnyjngzfy,
						xh, gwdm, xssq, bz, kcjqgzxsj, lxdh, xn, nd, xq,
						gwsbsj, sfpks, xxqk, bkhywbjgkc, btzw, jtmntg,
						hjmnsqfy, qjxfs, yhdkzljje, mqqgzxqk, yhtc, jg, cxdd,
						sfyytytp, sfgr, sflszn, sfwsrh, sfzbh, sfdbh, sffmsxg,
						sfcnh, sfdsr, jtcy1_xm, jtcy1_cw, jtcy1_nl, jtcy1_jkzk,
						jtcy1_gzdwjzw, jtcy1_nsr, jtcy2_xm, jtcy2_cw, jtcy2_nl,
						jtcy2_jkzk, jtcy2_gzdwjzw, jtcy2_nsr, jtcy3_xm,
						jtcy3_cw, jtcy3_nl, jtcy3_jkzk, jtcy3_gzdwjzw,
						jtcy3_nsr, jtcy4_xm, jtcy4_cw, jtcy4_nl, jtcy4_jkzk,
						jtcy4_gzdwjzw, jtcy4_nsr, jtcy5_xm, jtcy5_cw, jtcy5_nl,
						jtcy5_jkzk, jtcy5_gzdwjzw, jtcy5_nsr, jtjjknqk },
						request);
			}
			if (result) {
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		newFwd = new ActionForward("/post_stu_apply.do", false);
		request.setAttribute("dataSaved", "ok");
		return newFwd;
	}

	public static ActionForward JsxxReportPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		// String realTable = request.getParameter("realTable");
		String userType;

		// String userDep;

		String sUName;

		// String logMsg;
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userDep = session.getAttribute("userDep").toString();
		// String tableName = request.getParameter("tableName");
		String gwValue = request.getParameter("gwValue");
		// String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String sql = "select xxmc from xtszb";
		String xxmc = dao.getOneRs(sql, new String[] {},
				new String[] { "xxmc" })[0];
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("xxmc", xxmc);
		QgzxForm applyForm = (QgzxForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();

		String[] sj = { "早自修（7:30—8:20）", "第1-2节（8:30—10:05）",
				"第3-4节（10:25—12:00）", "午休（12:00—13:45）", "第5-6节（13:50—15:25）",
				"第7-8节（15:30—17:05）", "晚自修（17:50—20:15）" };
		String[] xq = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };

		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
		}
		if (xh != null && !xh.equalsIgnoreCase("")) {
			List<HashMap> kxList = new ArrayList<HashMap>();
			sql = "select kxbz from xsqgzxsjb where xh = ?  order by xq,sj ";
			String kxbz = dao.getOneRs(sql, new String[] { xh }, "kxbz");
			if (kxbz != null && !kxbz.equalsIgnoreCase("")) {
				String[] kx = new String[7];
				int j = 0;
				for (int i = 0; i < 7; i++) {
					kx[i] = kxbz.substring(j, j + 7);
					j += 7;
					char[] a = kx[i].toCharArray();
					HashMap<String, String> map2 = new HashMap<String, String>();
					for (int p = 0; p < 7; p++) {
						map2.put(xq[p], String.valueOf(a[p]));
					}
					map2.put("sj", sj[i]);
					map2.put("sjIndex", String.valueOf(i));
					kxList.add(map2);
				}
				request.setAttribute("kxList", kxList);
			} else {
				request.setAttribute("kxbz", "no");
			}
		}

		String nd = dao.getOneRs("select dqnd nd from xtszb", new String[] {},
				new String[] { "nd" })[0];

		if (dao.isKns(xh)) {
			String ssbh = dao.getOneRs(
					"select ssbh from view_xsjbxx where xh=?",
					new String[] { xh }, "ssbh");
			map.put("ssbh", ssbh);
			sql = "select zzqk_sfsqxwqgzx,zzqk_sfsbnczxdk,zzqk_lnyhnczxdhje,zzqk_sfsbgxzxdk,zzqk_lnyhgxzxdhje,zzqk_sfsbbxzxdk,zzqk_lnyhbxzxdhje,zzqk_lnjzxjqk,sqsj,xysh,xyshyj,xxsh,xxshyj,jtcy2_gzdwjzw,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_gzdwjzw,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_gzdwjzw,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_gzdwjzw,jtcy5_nsr,jtcy5_jkzk,jtjjknqk,mzbm_yzbm,mzbm_lxdh,mzbm_lxr,jfqk_jttg,jfqk_qtqytg,jfqk_hjtg,jfqk_yjfy,jfqk_mysffy,jfqk_qnhjfy,jfqk_bxnjttgfybzs,jfqk_ljqf,jfqk_ywhjjh,zzqk_sfsqxnqgzx,zzqk_xnqgzxyapgw,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,zzmm,jtdz,jtdh,xsdh,rxqhk,sfgr,sfcj,sfdq,sfjszn sflszn,sfssmz,sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,rxcj,sxqpm,pjcj,cxdd,jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_gzdwjzw,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_cw,jtcy2_nl from view_jsxx_knsxx where xh=? and nd=? and xxsh in ('一般困难','特别困难')";
			String[] colList = dao
					.getColumnName("select zzqk_sfsqxwqgzx,zzqk_sfsbnczxdk,zzqk_lnyhnczxdhje,zzqk_sfsbgxzxdk,zzqk_lnyhgxzxdhje,zzqk_sfsbbxzxdk,zzqk_lnyhbxzxdhje,zzqk_lnjzxjqk,sqsj,xysh,xyshyj,xxsh,xxshyj,jtcy2_gzdwjzw,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_gzdwjzw,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_gzdwjzw,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_gzdwjzw,jtcy5_nsr,jtcy5_jkzk,jtjjknqk,mzbm_yzbm,mzbm_lxdh,mzbm_lxr,jfqk_jttg,jfqk_qtqytg,jfqk_hjtg,jfqk_yjfy,jfqk_mysffy,jfqk_qnhjfy,jfqk_bxnjttgfybzs,jfqk_ljqf,jfqk_ywhjjh,zzqk_sfsqxnqgzx,zzqk_xnqgzxyapgw,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,zzmm,jtdz,jtdh,xsdh,rxqhk,sfgr,sfcj,sfdq,sfjszn sflszn,sfssmz,sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,rxcj,sxqpm,pjcj,cxdd,jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_gzdwjzw,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_cw,jtcy2_nl from view_jsxx_knsxx where 1=2");
			String[] rs = dao.getOneRs(sql, new String[] { xh, nd }, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
			}
			request.setAttribute("IsKns", "yes");
		} else {
			String zzmmmc = dao
					.getOneRs(
							"select b.zzmmmc from (select * from bks_xsqtxx where xh =?) a left join zzmmdmb b on a.zzmmm = b.zzmmdm",
							new String[] { xh }, "zzmmmc");
			map.put("zzmmmc", zzmmmc);
			sql = "select * from view_xsjbxx where xh=?";
			String[] colList = dao
					.getColumnName("select * from view_xsjbxx where 1=2");
			String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
			}
			request.setAttribute("IsKns", "no");
		}

		String sqsj = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sqsj from dual",
				new String[] {}, "sqsj");
		map.put("sqsj", sqsj);
		sql = "select * from gwsqsjb where kssj<to_char(sysdate,'yyyymmddhh24miss') and jssj>to_char(sysdate,'yyyymmddhh24miss')";

		String tag = dao.returntag(sql, new String[] {});
		if ("empty".equalsIgnoreCase(tag)) {
			request.setAttribute("sqsjFlag", "1");
			map.put("xn", "");
			map.put("nd", "");
			map.put("xq", "");
		} else {
			sql = "select xn,nd,xq from gwsqsjb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
					"xn", "nd", "xq" });
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("xq", tmp[2]);
		}
		sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where SHJG='通过' and gzjsrq>to_char(sysdate,'yyyymmdd')";
		List gwList = dao.getList(sql, new String[] {}, new String[] { "gwdm",
				"gwdmgwsbsj" });
		request.setAttribute("gwList", gwList);
		if (gwValue != null && !gwValue.equalsIgnoreCase("")) {
			sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where gwdm||gwsbsj =?";
			String[] tmp = dao.getOneRs(sql, new String[] { gwValue },
					new String[] { "gwdm", "gwdmgwsbsj" });
			map.put("gwdm", tmp[0]);
			map.put("xmdm", tmp[1]);
			if (map.get("gwdm") != null)
				map.put("gwdm", map.get("gwdm").toString());
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("do", "no");// 用于判断是不是进行修改操作，no表示不是修改操作
		request.setAttribute("rs", map);
		request.setAttribute("chkList", dao.getChkList(2));
		return mapping.findForward("success");
	}

	public static ActionForward JsxxGwsqPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String cxdd = DealString.toGBK(request.getParameter("cxdd").toString());
		String jg = DealString.toGBK(request.getParameter("jg").toString());
		String sfyytytp = DealString.toGBK(request.getParameter("sfyytytp")
				.toString());
		String sfgr = DealString.toGBK(request.getParameter("sfgr").toString());
		String sflszn = DealString.toGBK(request.getParameter("sflszn")
				.toString());
		String mnyjngzfy = DealString.toGBK(request.getParameter("mnyjngzfy")
				.toString());
		String sfwsrh = DealString.toGBK(request.getParameter("sfwsrh")
				.toString());
		String sfzbh = DealString.toGBK(request.getParameter("sfzbh")
				.toString());
		String sfdbh = DealString.toGBK(request.getParameter("sfdbh")
				.toString());
		String sffmsxg = DealString.toGBK(request.getParameter("sffmsxg")
				.toString());
		String sfcnh = DealString.toGBK(request.getParameter("sfcnh")
				.toString());
		String sfdsr = DealString.toGBK(request.getParameter("sfdsr")
				.toString());
		String jtcy1_xm = DealString.toGBK(request.getParameter("jtcy1_xm")
				.toString());
		String jtcy1_cw = DealString.toGBK(request.getParameter("jtcy1_cw")
				.toString());
		String jtcy1_nl = DealString.toGBK(request.getParameter("jtcy1_nl")
				.toString());
		String jtcy1_jkzk = DealString.toGBK(request.getParameter("jtcy1_jkzk")
				.toString());
		String jtcy1_gzdwjzw = DealString.toGBK(request.getParameter(
				"jtcy1_gzdwjzw").toString());
		String jtcy1_nsr = DealString.toGBK(request.getParameter("jtcy1_nsr")
				.toString());
		String jtcy2_xm = DealString.toGBK(request.getParameter("jtcy2_xm")
				.toString());
		String jtcy2_cw = DealString.toGBK(request.getParameter("jtcy2_cw")
				.toString());
		String jtcy2_nl = DealString.toGBK(request.getParameter("jtcy2_nl")
				.toString());
		String jtcy2_jkzk = DealString.toGBK(request.getParameter("jtcy2_jkzk")
				.toString());
		String jtcy2_gzdwjzw = DealString.toGBK(request.getParameter(
				"jtcy2_gzdwjzw").toString());
		String jtcy2_nsr = DealString.toGBK(request.getParameter("jtcy2_nsr")
				.toString());
		String jtcy3_xm = DealString.toGBK(request.getParameter("jtcy3_xm")
				.toString());
		String jtcy3_cw = DealString.toGBK(request.getParameter("jtcy3_cw")
				.toString());
		String jtcy3_nl = DealString.toGBK(request.getParameter("jtcy3_nl")
				.toString());
		String jtcy3_jkzk = DealString.toGBK(request.getParameter("jtcy3_jkzk")
				.toString());
		String jtcy3_gzdwjzw = DealString.toGBK(request.getParameter(
				"jtcy3_gzdwjzw").toString());
		String jtcy3_nsr = DealString.toGBK(request.getParameter("jtcy3_nsr")
				.toString());
		String jtcy4_xm = DealString.toGBK(request.getParameter("jtcy4_xm")
				.toString());
		String jtcy4_cw = DealString.toGBK(request.getParameter("jtcy4_cw")
				.toString());
		String jtcy4_nl = DealString.toGBK(request.getParameter("jtcy4_nl")
				.toString());
		String jtcy4_jkzk = DealString.toGBK(request.getParameter("jtcy4_jkzk")
				.toString());
		String jtcy4_gzdwjzw = DealString.toGBK(request.getParameter(
				"jtcy4_gzdwjzw").toString());
		String jtcy4_nsr = DealString.toGBK(request.getParameter("jtcy4_nsr")
				.toString());
		String jtcy5_xm = DealString.toGBK(request.getParameter("jtcy5_xm")
				.toString());
		String jtcy5_cw = DealString.toGBK(request.getParameter("jtcy5_cw")
				.toString());
		String jtcy5_nl = DealString.toGBK(request.getParameter("jtcy5_nl")
				.toString());
		String jtcy5_jkzk = DealString.toGBK(request.getParameter("jtcy5_jkzk")
				.toString());
		String jtcy5_gzdwjzw = DealString.toGBK(request.getParameter(
				"jtcy5_gzdwjzw").toString());
		String jtcy5_nsr = DealString.toGBK(request.getParameter("jtcy5_nsr")
				.toString());
		String jtjjknqk = DealString.toGBK(request.getParameter("jtjjknqk")
				.toString().trim());
		String btzw = DealString.toGBK(request.getParameter("btzw").toString());
		String jtmntg = DealString.toGBK(request.getParameter("jtmntg")
				.toString());
		String hjmnsqfy = DealString.toGBK(request.getParameter("hjmnsqfy")
				.toString());
		String qjxfs = DealString.toGBK(request.getParameter("qjxfs")
				.toString());
		String yhdkzljje = DealString.toGBK(request.getParameter("yhdkzljje")
				.toString());
		String mqqgzxqk = DealString.toGBK(request.getParameter("mqqgzxqk")
				.toString().trim());
		String yhtc = DealString.toGBK(request.getParameter("yhtc").toString());
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String gwdm = "";
		String gwTemp = request.getParameter("gw");
		if ((null == gwTemp) || ("".equalsIgnoreCase(gwTemp))) {
			gwdm = "无";
		} else {
			gwdm = DealString.toGBK(gwTemp);
		}
		String sqsj = DealString.toGBK(request.getParameter("sqsj").toString());
		String xssq = DealString.toGBK(request.getParameter("xssq").toString()
				.trim());
		String bz = DealString.toGBK(request.getParameter("bz").toString()
				.trim());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String xxqk = DealString.toGBK(request.getParameter("xxqk").toString());
		String bkhywbjgkc = DealString.toGBK(request.getParameter("bkhywbjgkc")
				.toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String zzmm = DealString.toGBK(request.getParameter("zzmm").toString());
		if ((null == sqsj) || ("".equalsIgnoreCase(sqsj))) {
			sqsj = (dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
					new String[] {}, new String[] { "sdate" }))[0];
		}
		String sqsj_year = sqsj.substring(0, 4);
		String sqsj_mon = sqsj.substring(5, 7);
		String sqsj_day = sqsj.substring(8);

		String[] outValue = new String[] { cxdd, jg, sfyytytp, sfgr, sflszn,
				mnyjngzfy, sfwsrh, sfzbh, sfdbh, sffmsxg, sfcnh, sfdsr,
				jtcy1_xm, jtcy1_cw, jtcy1_nl, jtcy1_jkzk, jtcy1_gzdwjzw,
				jtcy1_nsr, jtcy2_xm, jtcy2_cw, jtcy2_nl, jtcy2_jkzk,
				jtcy2_gzdwjzw, jtcy2_nsr, jtcy3_xm, jtcy3_cw, jtcy3_nl,
				jtcy3_jkzk, jtcy3_gzdwjzw, jtcy3_nsr, jtcy4_xm, jtcy4_cw,
				jtcy4_nl, jtcy4_jkzk, jtcy4_gzdwjzw, jtcy4_nsr, jtcy5_xm,
				jtcy5_cw, jtcy5_nl, jtcy5_jkzk, jtcy5_gzdwjzw, jtcy5_nsr,
				jtjjknqk, btzw, jtmntg, hjmnsqfy, qjxfs, yhdkzljje, mqqgzxqk,
				yhtc, xh, gwdm, sqsj, xssq, bz, lxdh, xxqk, bkhywbjgkc, xm, xb,
				xymc, zymc, bjmc, zzmm, sqsj_year, sqsj_mon, sqsj_day };
		String[] outString = new String[] { "cxdd", "jg", "sfyytytp", "sfgr",
				"sflszn", "mnyjngzfy", "sfwsrh", "sfzbh", "sfdbh", "sffmsxg",
				"sfcnh", "sfdsr", "jtcy1_xm", "jtcy1_cw", "jtcy1_nl",
				"jtcy1_jkzk", "jtcy1_gzdwjzw", "jtcy1_nsr", "jtcy2_xm",
				"jtcy2_cw", "jtcy2_nl", "jtcy2_jkzk", "jtcy2_gzdwjzw",
				"jtcy2_nsr", "jtcy3_xm", "jtcy3_cw", "jtcy3_nl", "jtcy3_jkzk",
				"jtcy3_gzdwjzw", "jtcy3_nsr", "jtcy4_xm", "jtcy4_cw",
				"jtcy4_nl", "jtcy4_jkzk", "jtcy4_gzdwjzw", "jtcy4_nsr",
				"jtcy5_xm", "jtcy5_cw", "jtcy5_nl", "jtcy5_jkzk",
				"jtcy5_gzdwjzw", "jtcy5_nsr", "jtjjknqk", "btzw", "jtmntg",
				"hjmnsqfy", "qjxfs", "yhdkzljje", "mqqgzxqk", "yhtc", "xh",
				"gwdm", "sqsj", "xssq", "bz", "lxdh", "xxqk", "bkhywbjgkc",
				"xm", "xb", "xymc", "zymc", "bjmc", "zzmm", "sqsj_year",
				"sqsj_mon", "sqsj_day" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	public static ActionForward BjlhPostStuChkBatch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BjlhQgzxService bjlhService = new BjlhQgzxService();
		String pk = "xh||gwdm||sqsj";
		String[] gwxx = request.getParameterValues("pkV");
		String[] column = { "xyyj" };
		String mes = "";
		boolean flag = false;

		if (gwxx == null) {
			request.setAttribute("result", "no");
		} else {
			for (int i = 0; i < gwxx.length; i++) {
				String message = bjlhService.checkGwrs(DealString
						.toGBK(gwxx[i]), "xyyj");// 用人单位审核人数超限判断
				if (message != null && !"".equalsIgnoreCase(message)) {// 用人单位人数超限不能通过
					mes += message + "\n";
				} else {// 录用
					flag = StandardOperation.update("xsgwxxb", column,
							new String[] { "通过" }, pk, DealString
									.toGBK(gwxx[i]), request);
				}
			}
			request.setAttribute("result", flag);
			request.setAttribute("mes", mes);
			request.setAttribute("showbjlh", "showbjlh");
		}
		return new ActionForward("/post_stu_check.do", false);
	}

	public static ActionForward BjlhJfhzSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		String sql = "";
		QgzxForm dataSearchForm = (QgzxForm) form;
		String tableName = "view_jfhb";
		String go = request.getParameter("go");
		String tips = "勤工助学 - 经费管理 - 经费记录查询";
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		String nd = request.getParameter("nd");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String yrdwdm = request.getParameter("yrdwdm");
		querry = getQuerry(querry, nd, xn, xq, yrdwdm);
		String xxdm = StandardOperation.getXxdm();

		Vector<Object> vector = new Vector<Object>();
		String[] colList = { "序号", "xn", "xq", "yrdwmc", "xydm", "总划拨金额",
				"总增拨金额", "总金额" };
		String[] CNcolList = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, CNcolList);
		String rsNum = "";
		sql = "select yrdwdm,yrdwmc from yrdwdmb";
		List yrdwList = dao.getList(sql, new String[] {}, new String[] {
				"yrdwdm", "yrdwmc" });
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			// 长沙民政
			tips = "学生义工 - 经费管理 - 经费记录查询";
		}
		if ("go".equalsIgnoreCase(go)) {
			sql = "select * from (select rownum 序号,a.xn,a.xq,a.nd,a.yrdwdm,a.yrdwmc,a.xydm,a.hbje 总划拨金额,b.zbje 总增拨金额,nvl(a.hbje,0)+nvl(b.zbje,0) 总金额 from "
					+ "(select xn,xq,nd,yrdwdm,yrdwmc,xydm,sum(hbje) hbje from view_jfhb where hblb='常规' group by xn,xq,nd,yrdwdm,yrdwmc,xydm) a,"
					+ "(select xn,xq,nd,yrdwdm,yrdwmc,xydm,sum(hbje) zbje from view_jfhb where hblb='增拨' group by xn,xq,nd,yrdwdm,yrdwmc,xydm) b "
					+ "where a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and a.yrdwmc=b.yrdwmc and a.xydm=b.xydm)"
					+ querry.toString();
			vector.addAll(dao.rsToVator(sql, new String[] {}, colList));
			rsNum = vector == null ? "0" : String.valueOf(vector.size());
		}

		request.setAttribute("yrdwList", yrdwList);
		dataSearchForm.setCxfs("汇总");
		setListInfo(request, "", "", "");
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tips", tips);
		return mapping.findForward("success");
	}

	public static ActionForward workConf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 设置岗位申请时间
		CommanForm priseForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String sql = "";// sql语句
		String xn = "";
		String nd = "";
		String xq = "";
		String kssj = "";
		String jssj = "";
		// 中国地质大学
		String xwkssj = "";
		String xwjssj = "";
		String myzgsjfs = "";
		String shkssj = "";
		String shjssj = "";
		String ffsjjg = "";
		String sbts = "";
		// end中国地质大学
		String knsbl = "";
		String page = "success";
		// 北京联合
		String mxsbc = "";
		String mtzgxs = "";
		String myzgxs = "";
		// 中国地质大学
		String myzgbc = "";// 每月最高报酬
		// 浙江机电
		String cjffsj = "";// 酬金发放时间
		String jfglkg = request.getParameter("jfglkg");
		String bkkmsxz = request.getParameter("bkkmsxz");

		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("save")) {
			xn = request.getParameter("xn");
			nd = request.getParameter("nd");
			xq = request.getParameter("xq");
			kssj = request.getParameter("kssqsj");
			jssj = request.getParameter("jssqsj");
			knsbl = request.getParameter("knsbl");
			mxsbc = request.getParameter("mxsbc");
			mtzgxs = request.getParameter("mtzgxs");
			myzgxs = request.getParameter("myzgxs");
			myzgbc = request.getParameter("myzgbc");
			xwkssj = request.getParameter("xwkssqsj");
			xwjssj = request.getParameter("xwjssqsj");
			myzgsjfs = request.getParameter("myzgsjfs");
			shkssj = request.getParameter("shkssj");
			shjssj = request.getParameter("shjssj");
			ffsjjg = request.getParameter("ffsjjg");
			cjffsj = request.getParameter("cjffsj");

			if (Base.isNull(cjffsj)) {
				SimpleDateFormat f = new SimpleDateFormat("yyyyMM");
				String time = f.format(new Date());
				cjffsj = time;
			}
			sbts = Base.chgNull(request.getParameter("sbts"), "", 1);

			boolean ok = false;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合大学
				sql = "select xn from gwsqsjb where rownum =1";
				String[] userTmp = dao.getOneRs(sql, new String[] {},
						new String[] { "xn" });
				if (null == userTmp) {
					ok = StandardOperation.insert("gwsqsjb", new String[] {
							"xn", "nd", "xq", "kssj", "jssj", "knsbl", "mxsbc",
							"mtzgxs", "myzgxs", "cjffsj", "jfglkg" },
							new String[] { xn, xn.substring(0, 4), xq, kssj,
									jssj, knsbl, mxsbc, mtzgxs, myzgxs, cjffsj,
									jfglkg }, request);
				} else {
					ok = StandardOperation.update("gwsqsjb", new String[] {
							"xn", "nd", "xq", "kssj", "jssj", "knsbl", "mxsbc",
							"mtzgxs", "myzgxs", "cjffsj", "jfglkg" },
							new String[] { xn, xn.substring(0, 4), xq, kssj,
									jssj, knsbl, mxsbc, mtzgxs, myzgxs, cjffsj,
									jfglkg }, "1", "1", request);
				}
			} else {
				sql = "select xn from gwsqsjb where rownum =1";
				String[] userTmp = dao.getOneRs(sql, new String[] {},
						new String[] { "xn" });
				if (null == userTmp || userTmp.length == 0) {
					ok = StandardOperation.insert("gwsqsjb", new String[] {
							"xn", "nd", "xq", "kssj", "jssj", "knsbl", "mxsbc",
							"mtzgxs", "myzgxs", "myzgbc", "xwkssj", "xwjssj",
							"myzgsjfs", "shkssj", "shjssj", "ffsjjg", "cjffsj",
							"sbts", "jfglkg", "bkkmsxz" }, new String[] { xn,
							nd, xq, kssj, jssj, knsbl, mxsbc, mtzgxs, myzgxs,
							myzgbc, xwkssj, xwjssj, myzgsjfs, shkssj, shjssj,
							ffsjjg, cjffsj, sbts, jfglkg, bkkmsxz }, request);
				} else {
					ok = StandardOperation.update("gwsqsjb", new String[] {
							"xn", "nd", "xq", "kssj", "jssj", "knsbl", "mxsbc",
							"mtzgxs", "myzgxs", "myzgbc", "xwkssj", "xwjssj",
							"myzgsjfs", "shkssj", "shjssj", "ffsjjg", "cjffsj",
							"sbts", "jfglkg", "bkkmsxz" }, new String[] { xn,
							nd, xq, kssj, jssj, knsbl, mxsbc, mtzgxs, myzgxs,
							myzgbc, xwkssj, xwjssj, myzgsjfs, shkssj, shjssj,
							ffsjjg, cjffsj, sbts, jfglkg, bkkmsxz }, "1", "1",
							request);
				}
			}
			if (ok) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		sql = "select xn,nd,xq, strtodatetime(substr(kssj,1,8)) kssj1,"
				+ "substr(kssj,9,2) kssj2,"
				+ "substr(kssj,11,2) kssj3,"
				+ "substr(kssj,13,2) kssj4,"
				+ "strtodatetime(substr(jssj,1,8)) jssj1,"
				+ "substr(jssj,9,2) jssj2,"
				+ "substr(jssj,11,2) jssj3,"
				+ "substr(jssj,13,2) jssj4,knsbl,myzgxs,myzgbc,myzgsjfs,jfglkg,"
				+ "mtzgxs,cjffsj,bkkmsxz from gwsqsjb " + "where rownum=1";
		String[] rst = { "xn", "nd", "xq", "kssj1", "kssj2", "kssj3", "kssj4",
				"jssj1", "jssj2", "jssj3", "jssj4", "knsbl", "myzgxs",
				"myzgbc", "myzgsjfs", "mtzgxs", "cjffsj", "mtzgxs", "jfglkg",
				"bkkmsxz" };
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			// 北京联合大学
			sql = "select xn,xq, strtodatetime(substr(kssj,1,8)) kssj1,"
					+ "substr(kssj,9,2) kssj2,"
					+ "substr(kssj,11,2) kssj3,"
					+ "substr(kssj,13,2) kssj4,"
					+ "strtodatetime(substr(jssj,1,8)) jssj1,"
					+ "substr(jssj,9,2) jssj2,"
					+ "substr(jssj,11,2) jssj3,"
					+ "substr(jssj,13,2) jssj4,knsbl,mxsbc,mtzgxs,myzgxs,nd,cjffsj,jfglkg from gwsqsjb "
					+ "where rownum=1";
			rst = new String[] { "xn", "xq", "kssj1", "kssj2", "kssj3",
					"kssj4", "jssj1", "jssj2", "jssj3", "jssj4", "knsbl",
					"mxsbc", "mtzgxs", "myzgxs", "nd", "cjffsj", "jfglkg" };
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
			// 中国地质大学
			sql = "select xn,nd,xq,ffsjjg,mtzgxs,strtodatetime(substr(kssj,1,8)) kssj1,"
					+ "substr(kssj,9,2) kssj2,"
					+ "substr(kssj,11,2) kssj3,"
					+ "substr(kssj,13,2) kssj4,"
					+ "strtodatetime(substr(jssj,1,8)) jssj1,"
					+ "substr(jssj,9,2) jssj2,"
					+ "substr(jssj,11,2) jssj3,"
					+ "substr(jssj,13,2) jssj4,knsbl,myzgxs,myzgbc,"
					+ "strtodatetime(substr(xwkssj,1,8)) xwkssj1,substr(xwkssj,9,2) xwkssj2,"
					+ "substr(xwkssj,11,2) xwkssj3,substr(xwkssj,13,2) xwkssj4,"
					+ "strtodatetime(substr(xwjssj,1,8)) xwjssj1,substr(xwjssj,9,2) xwjssj2,"
					+ "substr(xwjssj,11,2) xwjssj3,substr(xwjssj,13,2) xwjssj4, "
					+ "strtodatetime(substr(shkssj,1,8)) shkssj1,"
					+ "substr(shkssj,9,2) shkssj2,substr(shkssj,11,2) shkssj3,"
					+ "substr(shkssj,13,2) shkssj4,"
					+ "strtodatetime(substr(shjssj,1,8)) shjssj1,"
					+ "substr(shjssj,9,2) shjssj2, substr(shjssj,11,2) shjssj3,"
					+ "substr(shjssj,13,2) shjssj4,cjffsj,sbts from gwsqsjb "
					+ "where rownum=1";
			rst = new String[] { "xn", "nd", "xq", "kssj1", "kssj2", "kssj3",
					"kssj4", "jssj1", "jssj2", "jssj3", "jssj4", "knsbl",
					"myzgxs", "myzgbc", "xwkssj1", "xwkssj2", "cjffsj",
					"xwkssj3", "xwkssj4", "xwjssj1", "xwjssj2", "xwjssj3",
					"xwjssj4", "shkssj1", "shkssj2", "shkssj3", "shkssj4",
					"shjssj1", "shjssj2", "shjssj3", "shjssj4", "ffsjjg",
					"mtzgxs", "sbts" };
		}
		HashMap<String, String> sj = dao.getMap(sql, new String[] {}, rst);
		for (int i = 0; i < rst.length; i++) {
			String column = sj.get(rst[i]);
			request.setAttribute(rst[i], Base.isNull(column) ? "" : column);
		}

		priseForm.setXn(sj.get("xn"));
		priseForm.setXq(sj.get("xq"));
		priseForm.setKnsbl(sj.get("knsbl"));
		priseForm.setMxsbc(sj.get("mxsbc"));
		priseForm.setMtzgxs(sj.get("mtzgxs"));
		priseForm.setMyzgxs(sj.get("myzgxs"));
		priseForm.setMyzgbc(sj.get("myzgbc"));
		priseForm.setNd(sj.get("nd"));
		priseForm.setCjffsj(sj.get("cjffsj"));
		priseForm.setFfsjjg(sj.get("ffsjjg"));
		priseForm.setSbts(sj.get("sbts"));
		priseForm.setJfglkg(sj.get("jfglkg"));
		priseForm.setBkkmsxz(sj.get("bkkmsxz"));

		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
			page = "zgdzdxWorkConf";
		}
		request.setAttribute("xnndList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward(page);
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public static ActionForward workerApply(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		CommanForm work = (CommanForm) form;
		XsxxglService xsxxglService = new XsxxglService();
		QgzxService service = new QgzxService();
		XsgwglService xsgwglService = new XsgwglService();
		QgzxZgdzdxService dzdxService = new QgzxZgdzdxService();
		DAO dao = DAO.getInstance();
		request.setAttribute("path", "post_stu_apply.do");
		FormModleCommon.commonRequestSet(request);
		// 需要增加打印报表的特殊判断
		String url = getJspUrl(request,
				"/sqb/print/qgzx/" + Base.xxdm + ".jsp", "");
		if (!StringUtils.isNull(url)) {
			request.setAttribute("jspurl", url);
		}

		HashMap<String, String> map = new HashMap<String, String>();
		// 操作类型
		String doType = request.getParameter("doType");
		// 表名称
		String tableName = request.getParameter("tableName");
		// 岗位主键
		String gwValue = request.getParameter("gwValue");
		// 主键
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		// 用户类型
		String userType = session.getAttribute("userType").toString();
		// 学校代码
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		// 查询语句
		String sql = "";
		// 学号
		String xh = DealString.toGBK(request.getParameter("xh"));
		// 岗位代码||'-'||岗位时间
		String gwdm = request.getParameter("xmdm");
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX) && doType != null
				&& doType.equalsIgnoreCase("modi")) {
			// 湖南工业大学
			return new ActionForward("/xsqgzx.do?method=getXsgwInfo&pkValue="
					+ pkValue);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {
			// 浙江传媒学院
			return new ActionForward("/qgzxZjcm.do?method=gwsq");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)) {
			// 西昌学院
			if (doType != null && doType.equalsIgnoreCase("modi")) {
				return new ActionForward("/qgzxXcxy.do?method=modiXsgwxx");
			} else {
				return new ActionForward("/qgzxXcxy.do?method=xssqgw");
			}
		}
		if (userType.equalsIgnoreCase("stu")) {
			xh = session.getAttribute("userName").toString();
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
			// 西北二民院
			XbemyQgzxDAO xbDao = new XbemyQgzxDAO();
			// 002 自定义项目学生申请代码
			List<HashMap<String, String>> filedList = xbDao.getFiledInfo("002");
			request.setAttribute("filedList", filedList);
			request.setAttribute("rsNum", filedList.size());
		}
		if (StringUtils.isNull(doType)) {
			if (StringUtils.isNotNull(xh)) {
				boolean isKns = dao.isKns(xh);
				// 判断是否是贫困生
				request.setAttribute("IsKns", isKns == true ? "yes" : "no");
				map.put("sfpks", (isKns == true) ? "是" : "否");
				// 判断是否是黑名单成员
				if (dzdxService.isHmdMember(xh)) {
					request.setAttribute("hmdMember", "true");
				}
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {
				// 漳州师范 查询
				request.setAttribute("showshgc", "showshgc");
				request.setAttribute("zdy", "zdy");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				// 云南艺术
				return new ActionForward("/qgzx_ynys_qgzxApply.do", false);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
				// 江苏信息
				return new ActionForward("/qgzx_jsxx_gwsq.do", false);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)) {
				// 湖南工业大学
				return new ActionForward("/xsqgzx.do?method=stationApp", false);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				// 重庆科技
				xsgwglService.freeTimeTableCqkj(xh, request);
			}
			if (service.checkSqdg()) {// 可申请多个岗位
				xsgwglService.freeTimeTableZgdzdx(xh, request);
			}
			// 查询学生基本信息
			map.putAll(xsxxglService.selectStuinfo(xh));
			map.putAll(xsxxglService.getStuJtxx(xh));
			// 查询岗位申请相关信息
			String xmdm = request.getAttribute("gwdmgwsbsj") != null ? request
					.getAttribute("gwdmgwsbsj").toString() : "";
			if (StringUtils.isNotNull(xmdm)) {
				QgzxForm model = new QgzxForm();
				model.setPk("xh||gwdm||'-'||gwsbsj");
				model.setPkValue(xh + xmdm);
				map.putAll(xsgwglService.getStuGwxx(model));
			}

			// 判断是否在设置的允许申请时间范围内 tag 为empty不在申请时间内
			String tag = xsgwglService.checkTime();

			if ("empty".equalsIgnoreCase(tag)
					&& !xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				request.setAttribute("sqsjFlag", "1");
				map.put("xn", "");
				map.put("nd", "");
				map.put("xq", "");
				map.put("xqmc", "");
			} else {
				sql = "select xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from gwsqsjb a where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
						"xn", "nd", "xq", "xqmc" });
				map.put("xn", tmp[0]);
				map.put("nd", tmp[1]);
				map.put("xq", tmp[2]);
				map.put("xqmc", tmp[3]);
			}

			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合
				String type = request.getParameter("type");
				if ("".equalsIgnoreCase(xh) || xh == null) {
					if (type != null && type.equalsIgnoreCase("modi")) {
						sql = "select a.*,b.bmmc from view_gwxx a,zxbz_xxbmdm b where a.xydm=b.bmdm and a.shjg='通过'";
						List gwList = dao.getList(sql, new String[] {},
								new String[] { "gwdm", "sqdw", "gznr", "gzsj",
										"bmmc", "gwxzmc" });
						request.setAttribute("gwList", gwList);
						request.setAttribute("showmodi", "showmodi");
					} else {
						sql = "select a.gwdm gwmc,a.gwdm||'-'||a.sqdw gwdm,a.gwxzmc,a.gznr,a.gzsj,a.lxdh,a.yrdwmc sqdw,b.bmmc from view_gwxx a,zxbz_xxbmdm b where a.xydm=b.bmdm and a.shjg='通过'";
						List gwList = dao.getList(sql, new String[] {},
								new String[] { "gwmc", "gwdm", "sqdw", "gznr",
										"gzsj", "bmmc", "gwxzmc" });
						request.setAttribute("gwList", gwList);
					}
				} else {
					sql = "select xn,nd,xq from gwsqsjb where rownum=1";
					String[] XnNdXq = dao.getOneRs(sql, new String[] {},
							new String[] { "xn", "nd", "xq" });
					if (type != null && type.equalsIgnoreCase("modi")) {
						sql = "select a.*,b.bmmc from view_gwxx a,zxbz_xxbmdm b where a.xydm=b.bmdm and a.shjg='通过' and not exists (select * from view_xsgwxx b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and b.xxyj='通过' and b.xh=? and "
								+ " xn=? and nd=? and xq=?)";
						List gwList = dao.getList(sql, new String[] { xh,
								XnNdXq[0], XnNdXq[1], XnNdXq[2] },
								new String[] { "gwdm", "sqdw", "gznr", "gzsj",
										"bmmc", "gwxzmc" });

						sql = "select lxdh,xssq from view_xsgwxx where xh=? and xn=? and nd=? and xq=?";
						map.putAll(dao.getMap(sql, new String[] { xh,
								XnNdXq[0], XnNdXq[1], XnNdXq[2] },
								new String[] { "lxdh", "xssq" }));

						request.setAttribute("gwList", gwList);
						request.setAttribute("showmodi", "showmodi");
					} else {
						sql = "select a.gwdm gwmc,a.gwdm||'-'||a.sqdw gwdm,a.gwxzmc,a.gznr,a.gzsj,a.lxdh,a.yrdwmc sqdw,b.bmmc from view_gwxx a,zxbz_xxbmdm b where a.xydm=b.bmdm and shjg='通过' and"
								+ " not exists (select * from view_xsgwxx b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and b.xxyj='通过' and b.xh=? and "
								+ " xn=? and nd=? and xq=?)";
						List gwList = dao.getList(sql, new String[] { xh,
								XnNdXq[0], XnNdXq[1], XnNdXq[2] },
								new String[] { "gwmc", "gwdm", "sqdw", "gznr",
										"gzsj", "bmmc", "gwxzmc" });
						request.setAttribute("gwList", gwList);
					}
				}
			} else {
				// 获取岗位名称列表
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where SHJG='通过' and gzjsrq>=to_char(sysdate,'yyyymmdd')";
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
					// 重庆科技学院
					String sqdw = request.getParameter("sqdw");
					work.setSqdw(sqdw);
					sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where SHJG='通过' and sqdw='"
							+ sqdw + "'";
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)
						&& !Base.isNull(xh)) {
					// 浙江机电只能选择本系和各部门发布的岗位信息
					String xydm = dao.getOneRs(
							"select distinct xydm from view_xsjbxx where xh=?",
							new String[] { xh }, "xydm");
					sql += " and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and not exists(select 1 from (select * from view_njxyzybj where xydm<>'"
							+ xydm + "')c where b.xydm=c.xydm ))";
				}
				if (Globals.XXDM_GZDX.equals(xxdm)) {
					// 广州大学
					sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where SHJG='通过' and gzjsrq>=to_char(sysdate,'yyyymmdd') and gwxz<>(select gwxzdm from gwxzdmb where gwxzmc='临时岗位')";
				}
				List<HashMap<String, String>> gwList = dao.getList(sql,
						new String[] {}, new String[] { "gwdm", "gwdmgwsbsj" });
				request.setAttribute("gwList", gwList);
			}

			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				// 可参加时间代码表
				sql = "select * from kcjsjdmb";
				List kysjList = dao.getList(sql, new String[] {}, new String[] {
						"kcjsjdm", "kcjsjmc" });
				request.setAttribute("kysjList", kysjList);
			}

			if (gwValue != null && !gwValue.equalsIgnoreCase("")) {
				// 获取岗位名称
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where gwdm||gwsbsj =?";
				String[] tmp = dao.getOneRs(sql, new String[] { DealString
						.toGBK(gwValue) },
						new String[] { "gwdm", "gwdmgwsbsj" });
				if (tmp != null) {
					map.put("gwdm", tmp[0] == null ? "" : tmp[0]);
					map.put("xmdm", tmp[1] == null ? "" : tmp[1]);
					if (map.get("gwdm") != null) {
						map.put("gwdm", map.get("gwdm").toString());
					}
				}
				work.setXmdm(tmp[1]);
			}

			if (!StringUtil.isNull(work.getXmdm())) {
				// 获取岗位名称
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where gwdm||'-'||gwsbsj =?";
				String[] tmp = dao.getOneRs(sql, new String[] { DealString
						.toGBK(work.getXmdm()) }, new String[] { "gwdm",
						"gwdmgwsbsj" });
				if (tmp != null) {
					map.put("gwdm", tmp[0] == null ? "" : tmp[0]);
					map.put("xmdm", tmp[1] == null ? "" : tmp[1]);
					if (map.get("gwdm") != null) {
						map.put("gwdm", map.get("gwdm").toString());
					}
				}
				work.setXmdm(tmp[1]);
			}

			if (xh != null && !"".equalsIgnoreCase(xh)) {
				if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
					if (StringUtils.isNotNull(gwdm)) {
						sql = "select gwdm,gwsbsj,lxdh,kcjqgzxsj,yjshf,sfdbh,sfgr,sfyfdx,sfdq,sffcfp,yhtc,xssq,bz from xsgwxxb where xh||gwdm||'-'||gwsbsj = ?";
						map.putAll(dao.getMap(sql, new String[] { xh + gwdm },
								new String[] { "gwdm", "gwsbsj", "lxdh",
										"kcjqgzxsj", "yjshf", "sfdbh", "sfgr",
										"sfyfdx", "sfdq", "sffcfp", "yhtc",
										"xssq", "bz" }));
						map.put("xmdm", map.get("gwdm") + "-"
								+ map.get("gwsbsj"));
					}
				}
			}
			map.put("stuExists", "yes");

			// 中国地质大学
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
					&& StringUtils.isNotNull(xh)) {// 中国地质大学判断学生是否通过学院推荐
				sql = "select * from qgzxsqb where xh=?";
				map.putAll(dao.getMapNotOut(sql, new String[] { xh }));
				sql = "select count(*)num from xsgwxxb where xh=? and xn=? and nd=? and xq=? and xxyj='通过'";
				String result = dao.getOneRs(sql, new String[] { xh,
						xsgwglService.getQgzxConfig().get("xn"),
						xsgwglService.getQgzxConfig().get("nd"),
						xsgwglService.getQgzxConfig().get("xq") }, "num");
				result = result == null || "".equalsIgnoreCase(result) ? "0"
						: result;
				if (Integer.parseInt(result) > 0) {
					request.setAttribute("gwExists", "yes");
				}

			} else if (service.checkSqdg() && StringUtils.isNotNull(xh)) {
				// 可申请多个岗位
				map.putAll(xsxxglService.getStuJtxx(xh));
				// request.setAttribute("gwExists", "yes");
			}

			request.setAttribute("do", "no");// 用于判断是不是进行修改操作，no表示不是修改操作
			request.setAttribute("rs", map);
			request.setAttribute("chkList", dao.getChkList(2));
			request.setAttribute("path", "post_stu_apply.do");
			FormModleCommon.commonRequestSet(request);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合大学
				return new ActionForward("/bjlhdx/qgzx/gwsq.jsp");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {
				// 广东白云学院
				return mapping.findForward("qgzx_gdbyxy");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				// 西北二民院
				return new ActionForward("/qgzx/xbemy/gwsq.jsp");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				// 重庆科技学院
				request.setAttribute("sqdwList", service.getYrdwList());
				return new ActionForward("/qgzx/cqkjxy/qgzx_cqkjxy_gwxxb.jsp",
						false);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// 武汉理工大学
				request.setAttribute("yrdwList", service.getYrdwList());
				return new ActionForward("/qgzx/whlgdx/qgzx_whlgdx_gwsq.jsp");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)) {
				// 浙江经济
				return new ActionForward("/qgzx/zjjjzyjsxy/gwsq.jsp", false);
			}
			request.setAttribute("zzmmList", dao.getZzmmList());
			if (service.checkSqdg()) {// 申请多个岗位
				// 中国地质大学 浙江科技学院 闽江学院
				return mapping.findForward("zgdzdx");
			}
			return mapping.findForward("success");
		} else {
			// 修改时查询信息

			// 学生申请岗位的相关信息
			String[] colList = dao
					.getColumnName("select a.*,a.gwdm||'-'||gwsbsj xmdm,(select b.jtjjknqk from xsgwxxb b where a.xh=b.xh and a.gwdm||a.gwsbsj = b.gwdm||b.gwsbsj)jtjjknqk,(select xqmc from xqdzb b where a.xq=b.xqdm)xueqmc from "
							+ tableName + " a");
			String[] outValue = new String[colList.length];
			sql = "select a.*,a.gwdm||'-'||gwsbsj xmdm,(select b.jtjjknqk from xsgwxxb b where a.xh=b.xh and a.gwdm||a.gwsbsj = b.gwdm||b.gwsbsj)jtjjknqk,(select xqmc from xqdzb b where a.xq=b.xqdm)xueqmc from "
					+ tableName + " a where xh||gwdm||sqsj = ?";
			outValue = dao.getOneRs(sql, new String[] { pkValue }, colList);
			for (int i = 0; i < outValue.length; i++) {
				if (outValue[i] == null || outValue[i].equals(null)) {
					map.put(colList[i].toLowerCase(), "");
				} else {
					map.put(colList[i].toLowerCase(), outValue[i].toString());
				}
			}
			if (!Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// 非海南大学
				// 岗位的负责人、联系电话、申请单位信息
				sql = "select a.fzr,a.lxdh,a.gzsj,a.gznr,(select b.yrdwmc from yrdwdmb b where a.sqdw=b.yrdwdm) sqdw from view_gwxx a where a.gwdm=(select gwdm from "
						+ tableName + " where xh||gwdm||sqsj = ?)";
				colList = new String[] { "fzr", "lxdh", "sqdw", "gzsj", "gznr" };
				if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)) {
					colList = new String[] { "fzr", "sqdw", "gzsj", "gznr" };
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
					// 浙江机电职业技术学院
					sql = "select (select lxr from yrdwdmb b where a.sqdw=b.yrdwdm)fzr,(select lxdh from yrdwdmb b where a.sqdw=b.yrdwdm)gwlxdh,a.gzsj,a.gznr,(select b.yrdwmc from yrdwdmb b where a.sqdw=b.yrdwdm) sqdw from view_gwxx a where a.gwdm=(select gwdm from "
							+ tableName + " where xh||gwdm||sqsj = ?)";
					colList = new String[] { "fzr", "gwlxdh", "sqdw", "gzsj",
							"gznr" };
				}
				map.putAll(dao.getMap(sql, new String[] { pkValue }, colList));
			}
			xh = map.get("xh");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				map.put("zzmmdm", map.get("zzmmm"));
			}
			// 学生是否是困难生
			if (dao.isKns(xh)) {
				request.setAttribute("sfpks", "是");
				map.put("sfpks", "是");
			} else {
				String lxdh = map.get("lxdh");
				map.putAll(xsxxglService.selectStuinfo(xh));
				map.put("xqmc", map.get("xueqmc"));
				if (StringUtils.isNotNull(lxdh)) {
					map.put("lxdh", lxdh);
				}
				request.setAttribute("sfpks", "否");
				map.put("sfpks", "否");
			}
			// 审核通过的岗位信息列表
			sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where SHJG='通过'";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				// 重庆科技学院
				String sqdw = request.getParameter("sqdw");
				work.setSqdw(sqdw);
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where SHJG='通过' and sqdw='"
						+ sqdw + "'";
			}
			map = service.addOtherInfo(map, "modi");
			map.putAll(xsxxglService.getStuJtxx(xh));// 家庭信息查询
			map.putAll(xsxxglService.selectStuinfo(xh));
			map.put("gwdmgwsbsj1", map.get("xmdm"));
			List gwList = dao.getList(sql, new String[] {}, new String[] {
					"gwdm", "gwdmgwsbsj" });
			request.setAttribute("gwList", gwList);
			request.setAttribute("do", "yes");// 修改操作
			request.setAttribute("rs", map);
			request.setAttribute("doType", doType);
			request.setAttribute("zzmmList", dao.getZzmmList());
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合
				return new ActionForward("/bjlhdx/qgzx/gwsq.jsp");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
				// 中北大学
				return new ActionForward("/qgzx/zbdx_qgzx/gwsq.jsp", false);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				// 云南艺术
				request.setAttribute("IsKns", "yes");
				return new ActionForward("/qgzx_ynys_qgzxApply.do", false);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {
				// 广东白云学院
				request.setAttribute("dotype", doType);
				return mapping.findForward("qgzx_gdbyxy");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				// 西北二民院
				return new ActionForward("/qgzx/xbemy/gwsq.jsp");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				// 重庆科技
				String[] sj = { "早自修（7:30—8:20）", "第1-2节（8:30—10:05）",
						"第3-4节（10:25—12:00）", "午休（12:00—13:45）",
						"第5-6节（13:50—15:25）", "第7-8节（15:30—17:05）",
						"晚自修（17:50—20:15）" };
				String[] xq = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };

				// if (xh != null && !xh.equalsIgnoreCase("")) {
				List<HashMap> kxList = new ArrayList<HashMap>();
				sql = "select kxbz from xsqgzxsjb where xh = ?  order by xq,sj ";
				String[] kxbz = dao.getRs(sql, new String[] { xh }, "kxbz");
				if (kxbz != null && kxbz.length != 0) {
					String[] kx = new String[7];
					int j = 0;
					for (int i = 0; i < 7; i++) {
						for (int x = 0; x < 7; x++) {
							kx[x] = kxbz[x + j];
						}
						j += 7;
						HashMap<String, String> map2 = new HashMap<String, String>();
						for (int p = 0; p < 7; p++) {
							map2.put(xq[p], String.valueOf(kx[p]));
						}
						map2.put("sj", sj[i]);
						map2.put("sjIndex", String.valueOf(i));
						kxList.add(map2);
					}
					request.setAttribute("kxList", kxList);

				} else {
					for (int i = 0; i < 7; i++) {
						HashMap<String, String> map2 = new HashMap<String, String>();
						map2.put("sj", sj[i]);
						map2.put("sjIndex", String.valueOf(i));
						kxList.add(map2);
					}
					request.setAttribute("whkxList", kxList);
					request.setAttribute("kxbz", "no");
				}
				request.setAttribute("sqdwList", service.getYrdwList());
				return new ActionForward("/qgzx/cqkjxy/qgzx_cqkjxy_gwxxb.jsp",
						false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
					|| Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)) {
				// 中国地质大学 浙江科技
				xsgwglService.freeTimeTableZgdzdx(xh, request);
				return new ActionForward("/qgzx/zgdzdx/qgzxgwsq.jsp", false);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// 武汉理工大学
				request.setAttribute("yrdwList", service.getYrdwList());
				return new ActionForward("/qgzx/whlgdx/qgzx_whlgdx_gwsq.jsp");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)) {
				// 浙江经济
				return new ActionForward("/qgzx/zjjjzyjsxy/gwsq.jsp", false);
			}

			// 需要增加打印报表的特殊判断
			String jspurl = getJspUrl(request, "/sqb/print/qgzx/" + xxdm
					+ ".jsp", "");
			if (!StringUtils.isNull(url)) {
				request.setAttribute("jspurl", jspurl);
			}

			return mapping.findForward("success");
		}
	}

	/**
	 * getJspUrl 方法
	 * <p>
	 * 方法说明: 检测个性化页面是否存在，若存在获取个性化页面，若不存在则获取通用跳转页面
	 * </p>
	 * 
	 * @param request
	 * @param gxhJsp
	 *            个性化页面路径，例：/jsp/print/bdd/12317.jsp
	 * @param tyJsp
	 *            通用页面路径，例：/jsp/print/bdd/default.jsp
	 * @return String
	 * @author hhy
	 * @date 2011-6-23
	 */
	public static String getJspUrl(HttpServletRequest request, String gxhJsp,
			String tyJsp) {
		File f = new File(request.getRealPath(gxhJsp));
		if (f.exists()) {
			return gxhJsp;
		} else {
			return tyJsp;
		}
	}

	public static ActionForward workPaySearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String writeAble) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		CommanForm applyForm = (CommanForm) form;
		Vector<Object> rs = new Vector<Object>();
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userOnLine").toString();
		String userTP = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String sql = "";

		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		if (userTP != null && userTP.equalsIgnoreCase("xy")) {
			applyForm.setXydm(userDep);
		}

		sql = "select xn,xq,nd from gwsqsjb where rownum=1";
		String[] gwsqXnXqNd = dao.getOneRs(sql, new String[] {}, new String[] {
				"xn", "xq", "nd" });

		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合
				HashMap<String, String> map = new HashMap<String, String>();
				sql = "select distinct xh,xm,xb,nj,xymc,zymc,bjmc,lxdh from view_xsgwxx where xh=?";
				map = dao.getMap(sql, new String[] { xh }, new String[] { "xh",
						"xm", "xb", "nj", "xymc", "zymc", "bjmc", "lxdh" });
				map.put("xn", gwsqXnXqNd[0]);
				map.put("xq", gwsqXnXqNd[1]);
				map.put("nd", gwsqXnXqNd[2]);
				colList = new String[] { "行号", "xn", "xqmc", "yf", "yrdwmc",
						"gwxzmc", "gwdm", "gzsj", "cjje" };
				request.setAttribute("map", map);
				request.setAttribute("showbjlh", "showbjlh");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// 武汉理工大学
				colList = new String[] { "行号", "nd", "yf", "xqmc", "xh", "xm",
						"bjmc", "gwdm", "cjje", "ffsj", "gzsj", "xxsh" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				// 中国地质大学
				colList = new String[] { "行号", "nd", "yf", "xqmc", "xh", "xm",
						"bjmc", "gwdm", "cjje", "ffsj", "gzsj", "fflx", "xxsh" };
			} else {
				colList = new String[] { "行号", "nd", "yf", "xqmc", "xh", "xm",
						"bjmc", "gwdm", "cjje", "ffsj", "gzsj" };
			}
			String tag = dao.returntag(
					"select * from xscjffb where xh=? and KHQK is not null",
					new String[] { xh });
			colListCN = dao.getColumnNameCN(colList, "view_xscjff");
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select rownum 行号, a.* from view_xscjff a where xh=? order by ffsj desc";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// 武汉理工大学
				sql = "select rownum 行号, a.* from view_xscjff a where xh=? and xxsh='通过' order by ffsj desc";
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				// 中国地质大学
				sql = "select rownum 行号,nd,yf,xqmc,xh,xm,bjmc,gwdm,cjje,ffsj,gzsj,decode(fflx,'','常规',fflx)fflx,xxsh from view_xscjff where xh=? order by ffsj desc";
			}
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
			request.setAttribute("xnList", Base.getXnndList());
			if ("notempty".equalsIgnoreCase(tag)) {
				request.setAttribute("KhError", "showerror");
			}
		} else {
			request.setAttribute("xnList", Base.getXnndList());
			return new ActionForward(
					"/work_pay_search_t.do?act=work_pay&writeAble=" + writeAble,
					false);
		}

		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("success");
	}

	/**
	 * 教师用户查询学生酬金发放信息
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return throws Exception
	 */
	public static ActionForward workPaySearchT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 酬金发放查询
		CommanForm dataSearchForm = (CommanForm) form;
		QgzxService service = new QgzxService();
		QgzxCjffService cjffService = new QgzxCjffService();
		HttpSession session = request.getSession();

		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		List<String[]> rs = new ArrayList<String[]>();
		String tableName = "view_xscjff";// 查询信息源（多为视图名）
		String realTable = "xscjffb";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String dataType = request.getParameter("act");
		String xxdm = StandardOperation.getXxdm();
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();

		dataSearchForm.setUserName(userName);
		dataSearchForm.setTableName(tableName);

		if ("xy".equalsIgnoreCase(userType) && !service.isYrdwUser(userName)) {
			dataSearchForm.setXydm(userDep);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
			// 中国地质大学
			String yf = dataSearchForm.getYf();
			yf = StringUtils.isNull(yf) ? "0" : yf;
			dataSearchForm.setYf(Integer.parseInt(yf) + "");
		}

		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = cjffService.queryXscjffb(dataSearchForm);

			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", cjffService
					.getXscjffTorTr(dataSearchForm));// 发送表头
		}

		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			request.setAttribute("showbjlh", "showbjlh");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {
			realTable = "zzsf_xscjffb";
		}

		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", dataSearchForm.getXn());
		paramter.put("nd", dataSearchForm.getNd());
		paramter.put("xq", dataSearchForm.getXq());
		paramter.put("yrdwdm", dataSearchForm.getYrdwdm());

		request.setAttribute("gwList", service.getGwmcxxList(paramter, "no"));// 所有审核通过岗位列表
		// request.setAttribute("gwList", service.getGwmcList(userName));//岗位列表
		request.setAttribute("yrdwList", service.getYrdwList(userName));// 用人单位列表
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", dataType);// 发送数据查询类型
		setListInfo(request, nj, xy, zy);
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("path", "work_pay_search.do");
		return mapping.findForward("success");
	}

	public static ActionForward workPaySave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward newFwd = new ActionForward();
		QgzxDao qgzxDao = new QgzxDao();
		int count = Integer.parseInt(request.getParameter("count"));
		String gwdm = DealString.toGBK(request.getParameter("gwdm"));
		String sqsj = request.getParameter("gwsbsj");
		String doflag = request.getParameter("doflag");
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xq = request.getParameter("xq");
		String yf = request.getParameter("yf");
		String xxdm = StandardOperation.getXxdm();
		String xh = "";
		String cjje = "";
		String gzsj = "";
		String bz = "";
		String khqk = "";
		boolean tmp = false;
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
			// 中国地质大学 学校用户可设置用人单位提交的每个学生每月的最大工作时间（小时）和最大薪金
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap = qgzxDao.getSqsjInfo();
			String myzgxs = tmpMap.get("myzgxs");
			String myzgbc = tmpMap.get("myzgbc");
			myzgxs = myzgxs == null || "".equalsIgnoreCase(myzgxs) ? "0"
					: myzgxs;
			myzgbc = myzgbc == null || "".equalsIgnoreCase(myzgbc) ? "0"
					: myzgbc;

			for (int i = 0; i < count; i++) {
				cjje = DealString.toGBK(request.getParameter("cjje" + i));
				gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
				cjje = cjje == null || "".equalsIgnoreCase(cjje) ? "0" : cjje;
				gzsj = gzsj == null || "".equalsIgnoreCase(gzsj) ? "0" : gzsj;
				xh = DealString.toGBK(request.getParameter("xh" + i));

				if (Integer.parseInt(cjje) > Integer.parseInt(myzgbc)) {
					// 每月最高酬金数不要做限制，只需要添加提示语。
					request.setAttribute("mes", xh + "酬金金额超出了设定的最高酬金金额！");
				}
				if (Integer.parseInt(gzsj) > Integer.parseInt(myzgxs)) {
					request.setAttribute("mes", xh
							+ "工作时间超出了设定的最高工作时间,所有酬金发放失败！");
					request.setAttribute("workInfo", null);
					request.setAttribute("result", tmp);
					// return new ActionForward("/sjcz/work_pay_details.jsp",
					// false);
					return new ActionForward(
							"/qgzxcjff.do?method=txxd&pkValue=" + (gwdm + sqsj),
							false);
				}

			}
		}
		for (int i = 0; i < count; i++) {
			xh = request.getParameter("xh" + i);
			cjje = DealString.toGBK(request.getParameter("cjje" + i));
			gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
			bz = DealString.toGBK(request.getParameter("bz" + i));
			khqk = DealString.toGBK(request.getParameter("khqk" + i));
			String gzpj = request.getParameter("gzpj" + i);
			String yhkh = DealString.toGBK(request.getParameter("yhkh" + i));
			String yhmc = DealString.toGBK(request.getParameter("yhmc" + i));
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				// 上海工程技术大学
				if (doflag.equals("add")) {
					tmp = StandardOperation.insert("xscjffb", new String[] {
							"xh", "gwdm", "cjje", "sqsj", "gzsj", "bz", "xn",
							"nd", "xq", "yf", "khqk" }, new String[] { xh,
							gwdm, cjje, sqsj, gzsj, bz, xn, nd, xq, yf, khqk },
							request);
				} else if (doflag.equals("modi")) {
					tmp = StandardOperation.update("xscjffb", new String[] {
							"cjje", "gzsj", "bz" }, new String[] { cjje, gzsj,
							bz }, new String[] { "xh", "gwdm", "sqsj", "nd",
							"yf", "khqk" }, new String[] { xh, gwdm, sqsj, nd,
							yf, khqk }, request);
				}
			} else {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
					// 浙江机电
					String cjffsj = qgzxDao.getOneRs(
							"select cjffsj from gwsqsjb", new String[] {},
							"cjffsj");

					if (cjffsj != null && cjffsj.length() == 6) {
						nd = cjffsj.substring(0, 4);
						yf = cjffsj.substring(4, 6);
					} else {
						nd = Base.currNd;
						yf = GetTime.getSystemTime().substring(5, 7);
					}
				}
				boolean flag = qgzxDao.isExist("xscjffb",
						"xh||gwdm||sqsj||nd||yf", xh + gwdm + sqsj + nd + yf);
				if (flag) {
					// 记录存在进行修改操作
					tmp = StandardOperation.update("xscjffb", new String[] {
							"cjje", "gzsj", "bz", "gzpj", "yhkh", "yhmc" },
							new String[] { cjje, gzsj, bz, gzpj, yhkh, yhmc },
							new String[] { "xh", "gwdm", "sqsj", "nd", "yf" },
							new String[] { xh, gwdm, sqsj, nd, yf }, request);
				} else {
					// 记录不存在进行增加操作
					tmp = StandardOperation.insert("xscjffb", new String[] {
							"xh", "gwdm", "cjje", "sqsj", "gzsj", "bz", "xn",
							"nd", "xq", "yf", "gzpj", "yhkh", "yhmc" },
							new String[] { xh, gwdm, cjje, sqsj, gzsj, bz, xn,
									nd, xq, yf, gzpj, yhkh, yhmc }, request);
				}
			}

		}
		request.setAttribute("workInfo", null);
		request.setAttribute("result", tmp);
		// newFwd = new ActionForward("/sjcz/work_pay_details.jsp", false);
		newFwd = new ActionForward("/qgzxcjff.do?method=txxd&pkValue="
				+ (gwdm + sqsj), false);
		return newFwd;
	}

	public static ActionForward workOutlayTransfer(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String type) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		boolean doflag = false;
		String[] tmp = new String[1];
		String hbje = "";
		String pk = "";
		String pkValue = "";
		String dotype = request.getParameter("dotype");
		String tag = "";
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		String gwxzdm = request.getParameter("gwxzdm");
		sql = "select gwxzdm,gwxzmc from gwxzdmb";
		List gwxzList = dao.getList(sql, new String[] {}, new String[] {
				"gwxzdm", "gwxzmc" });
		request.setAttribute("gwxzList", gwxzList);

		if (type.equals("transfer") || type.equals("add")) {
			sql = "select * from gwsqsjb where rownum=1";
			tag = dao.returntag(sql, new String[] {});
			if ("empty".equalsIgnoreCase(tag)) {
				request.setAttribute("sqsjFlag", "1");
				tmp[0] = "";
				map.put("xn", "");
				map.put("nd", "");
				map.put("xq", "");
			} else {
				sql = "select xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from gwsqsjb a where rownum=1";
				tmp = dao.getOneRs(sql, new String[] {}, new String[] { "xn",
						"nd", "xq", "xqmc" });
				map.put("xn", tmp[0]);
				map.put("nd", tmp[1]);
				map.put("xq", tmp[2]);
				map.put("xqmc", tmp[3]);
			}
			sql = "select * from jfhbb where nd='"
					+ (tmp != null && tmp.length > 1 ? tmp[1] : "")
					+ "' and hblb='常规'";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				sql = "select * from jfhbb where xn='" + tmp[0]
						+ "' and hblb='常规'";
			}
			String lrh_flag1 = (tmp != null && tmp.length > 1 ? tmp[1] : "");
			tag = dao.returntag(sql, new String[] {});
			if (type.equals("transfer")) {
				if ("empty".equalsIgnoreCase(tag)) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
						sql = "select a.*,b.gws,b.syrs from view_yrdwdmb a,(select sqdw,count(*) gws,sum(nvl(sqsyrs,0)) syrs from gwxxb a where shjg='通过' and xn=? and xueqi=? group by sqdw) b where a.yrdwdm=b.sqdw";
					} else {
						sql = "select * from yrdwdmb";
					}
				} else {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
						sql = "select * from yrdwdmb where yrdwmc like '%学院' or  yrdwmc like '%学园' "
								+ "or yrdwmc like '%学生资助管理中心'";
					} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
						sql = " select a.* from yrdwdmb a where a.yrdwdm not in(select b.yrdwdm from jfhbb b where b.nd='"
								+ lrh_flag1 + "' and b.hblb='常规' )";
					} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
						sql = "select a.*,b.gws,b.syrs from view_yrdwdmb a,(select sqdw,count(*) gws,sum(nvl(sqsyrs,0)) syrs from gwxxb a where shjg='通过' and xn=? and xueqi=? group by sqdw) b where a.yrdwdm=b.sqdw";
					} else {
						sql = "select * from yrdwdmb";
					}
				}
			} else {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
					sql = "select a.*,b.gws,b.syrs from view_yrdwdmb a,(select sqdw,count(*) gws,sum(nvl(sqsyrs,0)) syrs from gwxxb a where shjg='通过' and xn=? and xueqi=? group by sqdw) b where a.yrdwdm=b.sqdw";
				} else {
					sql = "select * from yrdwdmb";
				}
			}
			List yrdwList = null;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				yrdwList = dao.getList(sql, new String[] { tmp[0], tmp[2] },
						new String[] { "yrdwdm", "yrdwmc", "xydm", "gws",
								"syrs" });
			} else {
				yrdwList = dao.getList(sql, new String[] {}, new String[] {
						"yrdwdm", "yrdwmc" });
			}
			request.setAttribute("yrdwList", yrdwList);

			String yrdwdm = dao.listToString(yrdwList,
					new String[] { "yrdwdm" });
			map.put("yrdwdm", yrdwdm);
			request.setAttribute("rs", map);
			yrdwdm = request.getParameter("yrdwdm");
			if (dotype != null && dotype.equals("save")) {
				String tmp1[] = yrdwdm.split("!!SplitSignOne!!");
				if (null != tmp1) {
					for (int i = 0; i < tmp1.length; i++) {
						if (null != tmp1[i] && "" != tmp1[i]) {
							hbje = request.getParameter(tmp1[i]);
							gwxzdm = request.getParameter("gwxz" + i);
							if (hbje != null && !hbje.equalsIgnoreCase("")) {
								if (tmp != null && tmp.length > 2) {
									sql = "insert into jfhbb(xn,nd,xq,yrdwdm,hbje,hblb,gwxzdm) values(?,?,?,?,?,?,?)";
									if (xxdm
											.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
										doflag = dao.runUpdate(sql,
												new String[] { tmp[0],
														tmp[0].substring(0, 4),
														tmp[2], tmp1[i], hbje,
														"常规", gwxzdm });
									} else {
										doflag = dao.runUpdate(sql,
												new String[] { tmp[0], tmp[1],
														tmp[2], tmp1[i], hbje,
														"常规", gwxzdm });
									}
									dao.writeLog(sql, new String[] { tmp[0],
											tmp[0].substring(0, 4), tmp[2],
											tmp1[i], hbje, "常规", gwxzdm },
											null, "增加记录：jfhbb-经费划拨表", request);
								}
							}
						}
					}
				}
				if (doflag == true)
					request.setAttribute("ok", "ok");
				else
					request.setAttribute("ok", "no");
			} else if (dotype != null && dotype.equals("add")) {
				hbje = request.getParameter("hbje");
				String bz = DealString.toGBK(request.getParameter("bz"));
				doflag = StandardOperation.insert("jfhbb", new String[] { "xn",
						"nd", "xq", "yrdwdm", "hbje", "hblb", "bz", "gwxzdm" },
						new String[] { tmp[0], tmp[1], tmp[2], yrdwdm, hbje,
								"增拨", bz, gwxzdm }, request);
				if (doflag == true)
					request.setAttribute("ok", "ok");
				else
					request.setAttribute("ok", "no");
			}
		} else {
			sql = "select * from yrdwdmb";
			List yrdwList = dao.getList(sql, new String[] {}, new String[] {
					"yrdwdm", "yrdwmc" });
			request.setAttribute("yrdwList", yrdwList);
			pk = request.getParameter("pk");
			pkValue = DealString.toGBK(request.getParameter("pkValue"));
			sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from jfhbb a "
					+ " where " + pk + "='" + pkValue + "'";
			tmp = dao.getOneRs(sql, new String[] {}, new String[] { "xn", "nd",
					"xq", "yrdwdm", "hbje", "hblb", "gwxzdm", "bz", "xqmc" });
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("xq", tmp[2]);
			map.put("xqmc", tmp[8]);
			map.put("yrdwdm", tmp[3]);
			map.put("hbje", tmp[4]);
			map.put("hblb", tmp[5]);
			map.put("gwxzdm", tmp[6]);
			map.put("bz", tmp[7]);
			request.setAttribute("rs", map);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			request.setAttribute("showbjlh", "showbjlh");
		}
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	public static ActionForward auditingControl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();

		QgzxService service = new QgzxService();
		List<String[]> rs = new ArrayList<String[]>();
		String doType = request.getParameter("doType");

		String[] outputValue = new String[] { "pk", "gwdm", "gwsbsj",
				"xyyrdwsh" };
		String[] colnumCN = dao.getColumnNameCN(outputValue, "gwxxb");
		List<HashMap<String, String>> topTr = dao.arrayToList(outputValue,
				colnumCN);

		CommanForm myForm = (CommanForm) form;
		String gwdm = request.getParameter("gwdm");
		String kssj = request.getParameter("kssj");
		String jssj = request.getParameter("jssj");
		String xyyrdwsh = request.getParameter("xyyrdwsh");

		String message = "";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveYrdwshkz(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		StringBuilder query = new StringBuilder();
		String sql = "select distinct gwdm||'-'||gwsbsj pk, gwdm,gwsbsj, ";
		sql+=" case when xyyrdwsh is null then '是' else xyyrdwsh end xyyrdwsh from gwxxb where 1=1  ";
		if (!Base.isNull(gwdm)) {

			query.append(" and gwdm like '%" + gwdm + "%'");
		}
		if (!Base.isNull(kssj)) {
			kssj = kssj.replace("-", "");
			kssj = kssj.replace(" ", "");
			kssj = kssj.replace(":", "");
			query.append(" and gwsbsj >= '" + kssj + "'");
		}
		
		if (!Base.isNull(jssj)) {
			jssj = jssj.replace("-", "");
			jssj = jssj.replace(" ", "");
			jssj = jssj.replace(":", "");
			query.append(" and gwsbsj <= '" + jssj + "'");
		}
		
		if (!Base.isNull(xyyrdwsh)) {

			query.append(" and xyyrdwsh='" + xyyrdwsh + "' ");
		}
		sql += query;

		rs = dao.rsToVator(sql, new String[] {}, outputValue);

		String[] xyyrdwshArr = request.getParameterValues("xyyrdwsh_hid");
		myForm.setXyyrdwsh_hid(xyyrdwshArr);

		request.setAttribute("path", "post_stu_zhztkz.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		return mapping.findForward("success");
	}

	public static ActionForward viewReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxDao qgzxDao = new QgzxDao();
		Vector<Object> rs = new Vector<Object>();
		Vector<Object> rs1 = new Vector<Object>();
		String[] colList = null;
		String name = request.getParameter("name");
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String sql = "";
		String whereSql = "";
		String[] tmp = null;
		String tag = "";
		String xxdm = StandardOperation.getXxdm();
		String nd = "";
		String yf = "";
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			// 江苏信息
			request.setAttribute("isJSXX", "is");
		} else {
			request.setAttribute("isJSXX", "no");
		}
		if (name != null && name.equals("work_payput_sum")) {
			sql = "select to_char(sysdate,'yyyy-mm-dd') time from dual";
			tmp = dao.getOneRs(sql, new String[] {}, new String[] { "time" });
			yf = tmp[0].substring(5, 7);
			request.setAttribute("yf", yf);

			sql = "select * from gwsqsjb where rownum=1";
			tag = dao.returntag(sql, new String[] {});
			if ("empty".equalsIgnoreCase(tag) || "".equalsIgnoreCase(tag)
					|| tag == null) {
				request.setAttribute("nd", "");
			} else {
				sql = "select nd ,cjffsj from gwsqsjb where rownum=1";
				HashMap<String, String> conf = dao.getMap(sql, new String[] {},
						new String[] { "nd", "cjffsj" });
				nd = conf.get("nd");
				String cjffsj = conf.get("cjffsj");
				if (cjffsj != null && !"".equalsIgnoreCase(cjffsj)) {
					request.setAttribute("nd", cjffsj.substring(0, 4));
					request.setAttribute("yf", cjffsj.substring(4, 6));
				} else {
					request.setAttribute("nd", nd);
				}
			}

			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				// 浙江机电
				String cjffsj = dao.getOneRs("select cjffsj from gwsqsjb",
						new String[] {}, "cjffsj");
				if (cjffsj != null && cjffsj.length() == 6) {
					nd = cjffsj.substring(0, 4);
					yf = cjffsj.substring(4, 6);
				} else {
					nd = Base.currNd;
					yf = GetTime.getSystemTime().substring(5, 7);
				}
				request.setAttribute("nd", nd);
				request.setAttribute("yf", yf);
			}

			if (qgzxDao.isYrdwUser(userName)) {
				// 用人单位查询
				whereSql += " and a.sqdw=(select distinct yrdwdm from yrdwdmb where dlm='"
						+ userName + "')";
			}

			// 各用人单位汇总
			colList = new String[] { "rownum", "yrdwmc", "ygrc", "ffje" };
			sql = "select rownum,a.* from (select b.yrdwmc,count(*) ygrc,sum(a.cjje) ffje,max(a.sqdw)sqdw from view_xscjff a,yrdwdmb b where a.sqdw=b.yrdwdm and b.dwlb='校内' and b.ssxq is not null and a.nd=? and a.yf=? group by rollup(b.yrdwmc)) a where a.yrdwmc is not null";//
			rs.addAll(dao.rsToVator(sql + whereSql, new String[] { nd, yf },
					colList));
			request.setAttribute("rs", rs);

			// 各学院汇总
			colList = new String[] { "xymc", "ygrc", "ffje" };
			sql = "select a.xymc ,a.ygrc,a.ffje from (select b.xydm,b.xymc ,count(*) ygrc,sum(a.cjje) ffje from view_xscjff a,view_yrdwdmb b,dm_zju_xq c where a.sqdw=b.yrdwdm and c.xqmc=b.ssxq and b.dwlb='校内' and b.ssxq is not null and a.nd=? and a.yf=? group by rollup(b.xymc)) a where a.xymc is not null";//
			rs1.addAll(dao.rsToVator(sql, new String[] {
					(String) request.getAttribute("nd"),
					(String) request.getAttribute("yf") }, colList));
			request.setAttribute("rs1", rs1);

			// 总金额和总人数
			sql = "select sum(a.ygrc) ygzrc,sum(a.ffje) ffzje from (select b.yrdwmc,count(*) ygrc,sum(a.cjje) ffje from view_xscjff a,yrdwdmb b where a.sqdw=b.yrdwdm and b.dwlb='校内' and b.ssxq is null and a.nd=? and a.yf=? group by b.yrdwmc union select c.xqmc yrdwmc,count(*) ygrc,sum(a.cjje) ffje from view_xscjff a,yrdwdmb b,dm_zju_xq c where a.sqdw=b.yrdwdm and c.dm=b.ssxq and b.dwlb='校内' and b.ssxq is not null and a.nd=? and a.yf=? group by c.xqmc) a";
			tmp = dao.getOneRs(sql, new String[] {
					(String) request.getAttribute("nd"),
					(String) request.getAttribute("yf"),
					(String) request.getAttribute("nd"),
					(String) request.getAttribute("yf") }, new String[] {
					"ygzrc", "ffzje" });
			request.setAttribute("ygzrc", tmp[0]);
			request.setAttribute("ffzje", tmp[1]);
		} else if (name != null && name.equals("work_payput_details")) {
			sql = "select * from gwsqsjb where rownum=1";
			tag = dao.returntag(sql, new String[] {});
			if ("empty".equalsIgnoreCase(tag) || tag == null
					|| "".equalsIgnoreCase(tag)) {
				request.setAttribute("nd", "");
			} else {
				sql = "select to_char(sysdate,'yyyy-mm-dd') time from dual";
				tmp = dao.getOneRs(sql, new String[] {},
						new String[] { "time" });
				request.setAttribute("yf", tmp[0].substring(5, 7));

				sql = "select nd ,cjffsj from gwsqsjb where rownum=1";
				HashMap<String, String> conf = dao.getMap(sql, new String[] {},
						new String[] { "nd", "cjffsj" });
				nd = conf.get("nd");
				String cjffsj = conf.get("cjffsj");
				if (cjffsj != null && !"".equalsIgnoreCase(cjffsj)) {
					request.setAttribute("nd", cjffsj.substring(0, 4));
					request.setAttribute("yf", cjffsj.substring(4, 6));
				} else {
					request.setAttribute("nd", nd);
				}
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				// 浙江机电
				String cjffsj = dao.getOneRs("select cjffsj from gwsqsjb",
						new String[] {}, "cjffsj");
				if (cjffsj != null && cjffsj.length() == 6) {
					nd = cjffsj.substring(0, 4);
					yf = cjffsj.substring(4, 6);
				} else {
					nd = Base.currNd;
					yf = GetTime.getSystemTime().substring(5, 7);
				}
				request.setAttribute("nd", nd);
				request.setAttribute("yf", yf);
			}

			colList = new String[] { "xh", "xm", "xymc", "gwdm", "yrdwmc",
					"cjje", "bz" };
			sql = "select a.xh,a.xm,a.xymc,a.gwdm,c.yrdwmc,a.cjje,a.bz,a.sqdw from view_xscjff a,gwxxb b,yrdwdmb c where a.gwdm=b.gwdm and a.sqsj=b.gwsbsj and a.sqdw=c.yrdwdm and b.gwxz=? and a.nd=? and a.yf=?";

			if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// 武汉理工大学
				colList = new String[] { "xh", "xm", "xymc", "gwdm", "yrdwmc",
						"cjje", "考核等级", "bz" };
				sql = "select a.xh,a.xm,a.xymc,a.gwdm,c.yrdwmc,a.cjje,a.bz,(select gzbx from view_xsgwxx d where a.xh=d.xh)考核等级 from view_xscjff a,gwxxb b,yrdwdmb c where a.gwdm=b.gwdm and a.sqsj=b.gwsbsj and a.sqdw=c.yrdwdm and b.gwxz=? and a.nd=? and a.yf=?";
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "xh", "xm", "xymc", "gwdm", "yrdwmc",
						"cjje", "yhkh", "yhmc", "bz" };
				sql = "select a.xh,a.xm,a.xymc,a.gwdm,c.yrdwmc,a.cjje,a.bz,a.sqdw,a.yhkh,a.yhmc from view_xscjff a,gwxxb b,yrdwdmb c where a.gwdm=b.gwdm and a.sqsj=b.gwsbsj and a.sqdw=c.yrdwdm and b.gwxz=? and a.nd=? and a.yf=?";
			}

			if (qgzxDao.isYrdwUser(userName)) {
				// 用人单位查询
				sql += " and exists (select 1 from yrdwdmb d where a.sqdw=d.yrdwdm and d.dlm='"
						+ userName + "')";
			}
			rs.addAll(dao.rsToVator(sql, new String[] { "001",
					(String) request.getAttribute("nd"),
					(String) request.getAttribute("yf") }, colList));
			request.setAttribute("rs", rs);

			rs1.addAll(dao.rsToVator(sql, new String[] { "002",
					(String) request.getAttribute("nd"),
					(String) request.getAttribute("yf") }, colList));
			request.setAttribute("rs1", rs1);
		}
		// dao.closeStmt();
		// dao.closeConnection();
		request.setAttribute("name", name);
		return mapping.findForward("success");
	}

	/**
	 * @param querry
	 *            初始查询条件
	 * @param nd
	 *            年度
	 * @param xn
	 *            学年
	 * @param xq
	 *            学期
	 * @param yrdwdm
	 *            用人单位代码
	 * @return 查询条件
	 */
	private static StringBuffer getQuerry(StringBuffer querry, String nd,
			String xn, String xq, String yrdwdm) {
		if ("".equalsIgnoreCase(nd) || nd == "") {
			querry.append(" and 1=1 ");
		} else {
			if (Check_Input_Value.check2(nd)) {
				querry.append(" and nd = '");
				querry.append(nd);
				querry.append("' ");
			}
		}
		if ("".equalsIgnoreCase(xn) || xn == "") {
			querry.append(" and 1=1 ");
		} else {
			if (Check_Input_Value.check2(xn)) {
				querry.append(" and xn = '");
				querry.append(xn);
				querry.append("' ");
			}
		}
		if ("".equalsIgnoreCase(xq) || xq == "") {
			querry.append(" and 1=1 ");
		} else {
			if (Check_Input_Value.check2(xq)) {
				querry.append(" and xq = '");
				querry.append(xq);
				querry.append("' ");
			}
		}
		if ("".equalsIgnoreCase(yrdwdm) || yrdwdm == "") {
			querry.append(" and 1=1 ");
		} else {
			if (Check_Input_Value.check2(yrdwdm)) {
				querry.append(" and yrdwdm = '");
				querry.append(yrdwdm);
				querry.append("' ");
			}
		}
		return querry;
	}

	/**
	 * @param request
	 * @param myDao
	 *            基本dao
	 * @param nj
	 *            年级
	 * @param xydm
	 *            学院代码
	 * @param zydm
	 *            专业代码 设置基础列表信息
	 */
	private static void setListInfo(HttpServletRequest request, String nj,
			String xydm, String zydm) {
		nj = nj == null ? "" : nj;
		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		String bjStr = xydm + "!!" + zydm + "!!" + nj;

		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("bjList", Base.getBjMap().get(bjStr));
	}
}
