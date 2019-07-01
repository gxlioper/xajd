package xgxt.jygl.action;

/*
 * 创建日期 2006-9-16
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.ZjgxJyxxForm;
import xgxt.jygl.service.JyglService;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * @author bat_zzj
 */

public class ZjgsjyxxAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		ZjgxJyxxForm chfm = (ZjgxJyxxForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
//				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
			String userType = "";
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			userType = session.getAttribute("userOnLine").toString();

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();

			if (myAct.equalsIgnoreCase("jyxxtjwh")) {
				myActFwd = zjgsjyxxwh(mapping, form, request, response,userType);
			} else if (myAct.equals("cgjyxys")) {
				myActFwd = cgjyxys(mapping, form, request, response, userType);
			} else if (myAct.equals("jyxysbbsq")) {
				myActFwd = jyxysbbsq(mapping, form, request, response);
			} else if (myAct.equals("jyxysbbgl")) {
				myActFwd = jyxysbbgl(mapping, form, request, response);
			} 
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
//			chkUser.setErrMsg("数据连接中断，请重新登陆！");
		}
		return new ActionForward("/login.jsp", false);
	}

	private ActionForward zjgsjyxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

//		CommanForm chfm = (CommanForm) form;
		ZjgxJyxxForm chfm = (ZjgxJyxxForm) form;
		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "zjgsjyxytj"; // 查询信息源表（多为视图名）
		String rsNum = "0";// 返回的记录数
		String xxdm = dao.getXxdm();
		String xxmc = request.getSession().getAttribute("xxmc").toString();
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> mapsq = new HashMap<String, String>();
		String doType = request.getParameter("doType");

		HttpSession session = request.getSession();
		String act = request.getParameter("act");
		
		if ("add".equals(act)) {

			String xsxhao = request.getParameter("xsxh");
			String whichxxdm = StandardOperation.getXxdm();// 学校代码

			ArrayList<HashMap<String, String>> ListA = new ArrayList<HashMap<String, String>>();
			String[] dwxzdmA = { "10", "15", "20", "21", "22", "23", "25",
					"29", "31", "32", "33", "35", "39", "40", "55", "56" };
			String[] dwxzdmA1 = { "党政机关", "县及县以下党政机关、事业单位和社会团体组织*", "科研设计单位",
					"高等教育单位", "中等、初等教育单位", "医疗卫生单位", "艰苦行业事业单位*", "其他事业单位",
					"国有企业", "三资企业", "中小企业*", "艰苦行业企业*", "其他企业", "部队*",
					"农村建制村*", "城镇社区*" };
			for (int i = 0; i < 16; i++) {
				HashMap<String, String> mapA = new HashMap<String, String>();
				mapA.put("dwxzdm", dwxzdmA[i]);
				mapA.put("dwxz", dwxzdmA1[i]);
				ListA.add(mapA);
			}
			request.setAttribute("ListA", ListA);

			// b
			ArrayList<HashMap<String, String>> ListB = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> mapB = new HashMap<String, String>();
			mapB.put("dwxzdm", "80");
			ListB.add(mapB);
			request.setAttribute("ListB", ListB);

			// c
			ArrayList<HashMap<String, String>> ListC = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> mapC = new HashMap<String, String>();
			mapC.put("dwxzdm", "85");
			ListC.add(mapC);
			request.setAttribute("ListC", ListC);

			// d
			ArrayList<HashMap<String, String>> ListD = new ArrayList<HashMap<String, String>>();
			String[] dwxzdmD = { "75", "76", "77" };
			String[] dwxzdmD1 = { "自主创业*", "自由职业", "其他灵活就业" };
			for (int d = 0; d < 3; d++) {
				HashMap<String, String> mapD = new HashMap<String, String>();
				mapD.put("dwxzdm", dwxzdmD[d]);
				mapD.put("dwxz", dwxzdmD1[d]);
				ListD.add(mapD);
			}
			request.setAttribute("ListD", ListD);
			// e
			ArrayList<HashMap<String, String>> ListE = new ArrayList<HashMap<String, String>>();
			String[] dwxzdmE = { "50", "51" };
			for (int d = 0; d < 2; d++) {
				HashMap<String, String> mapE = new HashMap<String, String>();
				mapE.put("dwxzdm", dwxzdmE[d]);
				ListE.add(mapE);
			}
			request.setAttribute("ListE", "ListE");
			// F
			ArrayList<HashMap<String, String>> ListF = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> mapF = new HashMap<String, String>();
			mapF.put("dwxzdm", "70");
			ListF.add(mapF);
			request.setAttribute("ListF", ListF);

			// G
			ArrayList<HashMap<String, String>> ListG = new ArrayList<HashMap<String, String>>();
			String[] dwxzdmG = { "71", "72" };
			for (int g = 0; g < 2; g++) {
				HashMap<String, String> mapG = new HashMap<String, String>();
				mapG.put("dwxzdm", dwxzdmG[g]);
				ListG.add(mapG);
			}
			request.setAttribute("ListG", "ListG");
			// H
			ArrayList<HashMap<String, String>> ListH = new ArrayList<HashMap<String, String>>();
			String[] dwxzdmH = { "70", "71", "72" };
			for (int h = 0; h < 3; h++) {
				HashMap<String, String> mapH = new HashMap<String, String>();
				mapH.put("dwxzdm", dwxzdmH[h]);
				ListH.add(mapH);
			}
			request.setAttribute("ListH", "ListH");
			// I
			ArrayList<HashMap<String, String>> ListI = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> mapI = new HashMap<String, String>();
			mapI.put("dwxzdm", "");
			ListI.add(mapI);
			request.setAttribute("ListI", ListI);
			// J
			ArrayList<HashMap<String, String>> ListJ = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> mapJ = new HashMap<String, String>();
			mapJ.put("zgbm", "升学");
			ListJ.add(mapJ);
			request.setAttribute("ListJ", ListJ);
			// K
			ArrayList<HashMap<String, String>> ListK = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> mapK = new HashMap<String, String>();
			mapK.put("zgbm", "出国、退学、延长");
			ListK.add(mapK);
			request.setAttribute("ListK", ListK);
			// L
			sql = "select zgbmdm,zgbm from dmk_zgbmL";
			List ListL = dao.getList(sql, new String[] {}, new String[] {
					"zgbmdm", "zgbm" });
			request.setAttribute("ListL", ListL);
			// M
			ArrayList<HashMap<String, String>> ListM = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> mapM = new HashMap<String, String>();
			mapM.put("zgbm", "");
			ListM.add(mapM);
			request.setAttribute("ListM", ListM);

			// 控制进入哪个logic标签
			String byqxdm = request.getParameter("byqxdm");
			JyglService service = new JyglService();
			HashMap<String, String> flagmap = new HashMap<String, String>();
			if (!Base.isNull(byqxdm)) {
				flagmap = service.getRedFlag(byqxdm);
			}
			request.setAttribute("redflag", flagmap);
			if ("first".equals(act)) {
				request.setAttribute("whichlist", "H");
				request.setAttribute("whichzgbmlist", "X");
				request.setAttribute("danweiname", "X");
			}
			if ("01".equals(byqxdm) || "13".equals(byqxdm)
					|| "15".equals(byqxdm)) {
				request.setAttribute("whichlist", "A");
			}
			if ("02".equals(byqxdm) || "03".equals(byqxdm)
					|| "12".equals(byqxdm)) {
				request.setAttribute("whichlist", "B");
			}
			if ("04".equals(byqxdm)) {
				request.setAttribute("whichlist", "C");
				request.setAttribute("chuguo", "N");
			}
			if ("14".equals(byqxdm)) {
				request.setAttribute("whichlist", "D");
			}
			if ("17".equals(byqxdm)) {
				request.setAttribute("whichlist", "E");
			}
			if ("06".equals(byqxdm)) {
				request.setAttribute("whichlist", "F");
			}
			if ("07".equals(byqxdm)) {
				request.setAttribute("whichlist", "G");
			}
			if ("16".equals(byqxdm)
					|| (null == byqxdm && (!"first".equals(act)))
					|| ("".equals(byqxdm) && (!"first".equals(act)))) {
				request.setAttribute("whichlist", "H");
			}
			if ("05".equals(byqxdm) || "08".equals(byqxdm)
					|| "11".equals(byqxdm)) {
				request.setAttribute("whichlist", "I");
			}

			if ("02".equals(byqxdm) || "03".equals(byqxdm)
					|| "12".equals(byqxdm)) {
				request.setAttribute("whichzgbmlist", "J");
			}
			if ("04".equals(byqxdm) || "05".equals(byqxdm)
					|| "08".equals(byqxdm) || "11".equals(byqxdm)) {
				request.setAttribute("whichzgbmlist", "K");
			}
			if ("01".equals(byqxdm) || "13".equals(byqxdm)
					|| "14".equals(byqxdm) || "15".equals(byqxdm)
					|| "17".equals(byqxdm)) {
				request.setAttribute("whichzgbmlist", "L");
			}
			if ("06".equals(byqxdm) || "07".equals(byqxdm)
					|| "16".equals(byqxdm) || "".equals(byqxdm)
					|| "09".equals(byqxdm)) {
				request.setAttribute("whichzgbmlist", "M");
			}
			// 和单位名称有关的下选项
			if ("01".equals(byqxdm) || "02".equals(byqxdm)
					|| "03".equals(byqxdm) || "12".equals(byqxdm)
					|| "13".equals(byqxdm) || "14".equals(byqxdm)
					|| "15".equals(byqxdm) || "17".equals(byqxdm)) {
				request.setAttribute("danweiname", "X");
			} else if ("04".equals(byqxdm)) {
				request.setAttribute("danweiname", "Y");
			} else {
				request.setAttribute("danweiname", "O");
			}

			// 把第一部分的信息传递进来
			if (userType.equalsIgnoreCase("student")) {
				act = "go";
			}
			if ("go".equalsIgnoreCase(act) || "go".equals(doType)) {
				// 把刷新以前的值传回页面
				String xxdjh = DealString.toGBK(request.getParameter("xxdjh"));
				String zzjgdm = DealString
						.toGBK(request.getParameter("zzjgdm"));
				String dwmc = DealString.toGBK(request.getParameter("dwmc"));
				String zzmmdm = DealString
						.toGBK(request.getParameter("zzmmdm"));
				String lxdz = DealString.toGBK(request.getParameter("lxdz"));
				String yb = DealString.toGBK(request.getParameter("yb"));
				String dh = DealString.toGBK(request.getParameter("dh"));
				String jzzhlbbzwdm = DealString.toGBK(request
						.getParameter("jzzhlbbzwdm"));
				String memo = DealString.toGBK(request.getParameter("memo"));
				String sydzgbm = DealString.toGBK(request
						.getParameter("sydzgbm"));

				// 修改
				String dwxzdm2 = DealString.toGBK(request
						.getParameter("dwxzdm2"));
				String dajsd = DealString.toGBK(request.getParameter("dajsd"));
				String dwdz = DealString.toGBK(request.getParameter("dwdz"));
				String dajsddz = DealString.toGBK(request
						.getParameter("dajsddz"));
				String dwdh = DealString.toGBK(request.getParameter("dwdh"));
				String dajsdyb = DealString.toGBK(request
						.getParameter("dajsdyb"));
				String dwyb = DealString.toGBK(request.getParameter("dwyb"));
				String dqlx = DealString.toGBK(request.getParameter("dqlx"));
				String wyj = DealString.toGBK(request.getParameter("wyj"));
				String hyfl = DealString.toGBK(request.getParameter("hyfl"));
				String dynypjgz = DealString.toGBK(request
						.getParameter("dynypjgz"));
				String zydk = DealString.toGBK(request.getParameter("zydk"));
				String wjyyy = DealString.toGBK(request.getParameter("wjyyy"));
				String wjybz = DealString.toGBK(request.getParameter("wjybz"));
				String bz1 = DealString.toGBK(request.getParameter("bz1"));
				String bz2 = DealString.toGBK(request.getParameter("bz2"));
				String bz3 = DealString.toGBK(request.getParameter("bz3"));
				String bz4 = DealString.toGBK(request.getParameter("bz4"));
				String bz5 = DealString.toGBK(request.getParameter("bz5"));

				map.put("dwxzdm2", dwxzdm2);
				map.put("dajsd", dajsd);
				map.put("dwdz", dwdz);
				map.put("dajsddz", dajsddz);
				map.put("dwdh", dwdh);
				map.put("dajsdyb", dajsdyb);
				map.put("dwyb", dwyb);
				map.put("dqlx", dqlx);
				map.put("wyj", wyj);
				map.put("hyfl", hyfl);
				map.put("dynypjgz", dynypjgz);
				map.put("zydk", zydk);
				map.put("wjybz", wjybz);
				map.put("wjyyy", wjyyy);
				map.put("bz1", bz1);
				map.put("bz2", bz2);
				map.put("bz3", bz3);
				map.put("bz4", bz4);
				map.put("bz5", bz5);

				map.put("xxdjh", xxdjh);
				map.put("zzjgdm", zzjgdm);
				map.put("dwmc", dwmc);
				map.put("zzmmdm", zzmmdm);
				map.put("lxdz", lxdz);
				map.put("yb", yb);
				map.put("dh", dh);
				map.put("jzzhlbbzwdm", jzzhlbbzwdm);
				map.put("memo", memo);
				map.put("sydzgbm", sydzgbm);

				if ("go".equals(doType)) {
					xsxhao = request.getParameter("xsxh");

				}
				if (userType.equalsIgnoreCase("student")) {
					xsxhao = session.getAttribute("userName").toString();
				}
				sql = "select * from jygl_xsjbxxb where xsxh=?"; // 查询该学号的相关内容的sql语句
				colList = dao
						.getColumnName("select * from jygl_xsjbxxb where 1=2"); // 返回列名数组
				String[] rs3 = dao.getOneRs(sql, new String[] { xsxhao },
						colList); // 获得从毕业生基本信息表（视图）中查询的记录集
				if (rs != null && !("".equals(rs)) && StringUtils.isArrayNotNull(rs3)) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), rs3[i]); // 将记录循环放入map中
					}
				}

				String zydm = map.get("zydm");
				sql = "select jygl_zydm,jygl_zymc from DMK_ZYDMDZB where jwc_zydm=?";
				String[] dminfo = dao.getOneRs(sql, new String[] { zydm },
						new String[] { "jygl_zydm", "jygl_zymc" });
				if (null != dminfo) {
					map.put("zydm", dminfo[0]);
					map.put("zymc", dminfo[1]);
				}
				if (Globals.XXDM_ZGDZDX.equals(whichxxdm)) {
					// 转换培养性别代码
					if ("1".equals(map.get("xbdm"))) {
						map.put("xb", "男");
					} else {
						map.put("xb", "女");
					}
				}

				String sydqdm = map.get("sydqdm");
				sql = "select sydq from dmk_sydq where sydqdm=?";
				String[] sydqmc = dao.getOneRs(sql, new String[] { sydqdm },
						new String[] { "sydq" });
				if (sydqmc != null) {
					map.put("sydq", sydqmc[0]);
				}
				if (!Base.isNull(dwmc)) {
					map.put("dwmc", dwmc);
				}
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc1 = dao.getOneRs(sql, new String[] { xsxhao },
						new String[] { "xymc" });
				if (xymc1 != null) {
					map.put("xymc", xymc1[0]);
					map.put("dwdq", DealString.toGBK(request
							.getParameter("dwdq")));
				}

				if ("go".equals(request.getParameter("doType"))
						|| "go".equals(request.getParameter("act"))) {
					String dwdq = DealString
							.toGBK(request.getParameter("dwdq"));
					sql = "select * from dmk_dwdq where dwdq=?";
					String[] rs1 = dao.getOneRs(sql, new String[] { dwdq },
							new String[] { "dwdqdm" });
					map.put("dwdqdm", "310100");
					if (rs1 != null) {
						map.put("dwdqdm", rs1[0]);
					}
					if ("04".equals(request.getParameter("byqxdm"))) {
						map.put("dwdqdm", "990000");
					}
				}
				map.put("zgbm", DealString.toGBK(request.getParameter("zgbm")));

				if ("01".equals(byqxdm) || "13".equals(byqxdm)
						|| "14".equals(byqxdm) || "15".equals(byqxdm)
						|| "17".equals(byqxdm)) {
					String zgbm = DealString
							.toGBK(request.getParameter("zgbm"));
					sql = "select * from dmk_zgbm where zgbm=?";
					String[] rs2 = dao.getOneRs(sql, new String[] { zgbm },
							new String[] { "zgbmdm" });
					if (rs2 != null) {
						map.put("zgbmdm", rs2[0]);
					}
				}
			}
			if (Globals.XXDM_ZGDZDX.equals(whichxxdm)) {
				sql = "select pyfsdm,pyfs from dmk_bzpyfs";
				List pyfsList = dao.getList(sql, new String[] {}, new String[] {
						"pyfsdm", "pyfs" });
				request.setAttribute("pyfsList", pyfsList);

			}
			// 判断是否是上海本地学生 上海本地学生不用选择生源地主管部门
			if ("310000".equals(map.get("sydqdm"))) {
				request.setAttribute("sydqdmIsSh", "kong");
			} else {
				request.setAttribute("sydqdmIsSh", "notkong");
			}
			// 通用登陆后下拉框信息显示
			sql = "select zzmmdm,zzmm from dmk_zzmm"; // 政治面貌代码
			List zzmmdmList = dao.getList(sql, new String[] {}, new String[] {
					"zzmmdm", "zzmm" });
			request.setAttribute("zzmmdmList", zzmmdmList);
			if (userType.equals("teacher")) {
				sql = "select byqxdm,byqx from dmk_byqx";
			} else {
				sql = "select byqxdm,byqx from dmk_byqx where byqxdm not in('05','06','07','08','09','11')";
			}
			// 毕业去向代码
			List byqxdmList = dao.getList(sql, new String[] {}, new String[] {
					"byqxdm", "byqx" });
			request.setAttribute("byqxdmList", byqxdmList);

			sql = "select dwdqdm,dwdq from dmk_dwdq"; // 单位地区
			List dwdqList = dao.getList(sql, new String[] {}, new String[] {
					"dwdqdm", "dwdq" });
			request.setAttribute("dwdqList", dwdqList);

			sql = "select zgbmdm,zgbm from dmk_zgbm"; // 主管部门代码
			List zgbmList = dao.getList(sql, new String[] {}, new String[] {
					"zgbmdm", "zgbm" });
			request.setAttribute("zgbmList", zgbmList);

			sql = "select jzzhlbbzwdm,jzzhlbbzw from dmk_jzzhlbbzw"; // 居住证或蓝表标志位
			List jzzhlbbzwdmList = dao.getList(sql, new String[] {},
					new String[] { "jzzhlbbzwdm", "jzzhlbbzw" });
			request.setAttribute("jzzhlbbzwdmList", jzzhlbbzwdmList);

			sql = "select sydzgbmdm,sydzgbm from dmk_sydzgbm"; // 生源地主管单位名称
			List sydzgbmList = dao.getList(sql, new String[] {}, new String[] {
					"sydzgbmdm", "sydzgbm" });
			request.setAttribute("sydzgbmList", sydzgbmList);

			sql = "select dwxzdm,dwxz from dmk_dwxz"; // 单位性质代码
			List dwxzdmList = dao.getList(sql, new String[] {}, new String[] {
					"dwxzdm", "dwxz" });
			request.setAttribute("dwxzdmList", dwxzdmList);

			sql = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
			List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
					"dwxzdm", "dwxz" });
			request.setAttribute("dwxzdm2List", dwxzdm2List);

			sql = "select dqlxdm,dqlx from dmk_dqlx"; // 地区流向
			List dqlxdmList = dao.getList(sql, new String[] {}, new String[] {
					"dqlxdm", "dqlx" });
			request.setAttribute("dqlxdmList", dqlxdmList);

			sql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
			List hyflList = dao.getList(sql, new String[] {}, new String[] {
					"hyfldm", "hyfl" });
			request.setAttribute("hyflList", hyflList);

			map.put("byqxdm", byqxdm);
			if ("save".equals(doType)) {
				map.put("byqxdm", "");
			}
			if(Globals.XXDM_ZJLG.equals(xxdm)){
				sql = "select xh,xm,xydm yxdm,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) yxmc,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) szyx,bjmc szbj,zydm,zymc,bjdm,bjmc,sfzh,xb,sfzh sfzhm,xb xbdm,xldm,(select xwdm from xwdmb b where a.xwmc=b.xwmc) xwdm,syd syszddm,(select sydmc from syddmb b where a.syd=b.syddm) syszd,mz mzdm,zzmm zzmmdm,xz,rxrq rxsj,pyfs,zslb,dxhwp dxhwpdw,bysj,wydj,sjhm,qqhm,lxdz,lxdh from view_xsxxb a where a.xh=?"; 
//				colList = dao.getColumnName("select * from view_xsxxb where 1=2"); 
				colList = new String[]{"xh","xm","yxdm","yxmc","szyx","szbj","zydm","zymc","dxhwpdw","rxsj","bysj","wydj","sjhm","qqhm","lxdh","lxdz"};
				String[] rs3 = dao.getOneRs(sql, new String[] { xsxhao },colList); 
				if ((colList != null || "".equals(colList)) && StringUtils.isNotNull(xsxhao)) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), rs3[i]);
					}
				}
			}
			map.put("stuExists", "yes");
			map.put("userType", userType);
			map.put("yxdm", xxdm);
			map.put("yxmc", xxmc);
			request.setAttribute("rs", map);
			// String dd = map.get("dwmc").toString();
//			String xxdm2 = dao.getXxdm();
			if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
				StuInfoDAO stuDao = new StuInfoDAO();
				request.setAttribute("shiList", stuDao.getShiList("").get(
						"shiList"));
				request.setAttribute("xianList", stuDao.getShiList("").get(
						"xianList"));
				request.setAttribute("ssList", stuDao.getSsList());
			}
			request.setAttribute("xyList", Base.getXyList());// 发送学院列表
			return mapping.findForward("successadd");
		}
		if ("save".equals(act)) {
			boolean save = false;
			String xh = DealString.toGBK(request.getParameter("xh"));
			String xm = DealString.toGBK(request.getParameter("xm"));
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String zydm = DealString.toGBK(request.getParameter("zydm"));
			String szbj = DealString.toGBK(request.getParameter("szbj"));
			String szyx = DealString.toGBK(request.getParameter("szyx"));
			String yxmc = DealString.toGBK(request.getParameter("yxmc"));
			String yxdm = DealString.toGBK(request.getParameter("yxdm"));
			String sfzhm = DealString.toGBK(request.getParameter("sfzhm"));
			String xbdm1 = DealString.toGBK(request.getParameter("xbdm"));
			String xldm = DealString.toGBK(request.getParameter("xldm"));
			String xwdm = DealString.toGBK(request.getParameter("xwdm"));
			String syszddm = DealString.toGBK(request.getParameter("syszddm"));
			String syszd = DealString.toGBK(request.getParameter("syszd"));
			String mzdm = DealString.toGBK(request.getParameter("mzdm"));
			String zzmmdm = DealString.toGBK(request.getParameter("zzmmdm"));
			String sfzz = DealString.toGBK(request.getParameter("sfzz"));
			String sfsf = DealString.toGBK(request.getParameter("sfsf"));
			String sfdl = DealString.toGBK(request.getParameter("sfdl"));
			String xz = DealString.toGBK(request.getParameter("xz"));
			String zslb = DealString.toGBK(request.getParameter("zslb"));
			String pyfsdm = DealString.toGBK(request.getParameter("pyfsdm"));
			String dxhwpdw = DealString.toGBK(request.getParameter("dxhwpdw"));
			String rxsj = DealString.toGBK(request.getParameter("rxsj"));
			String bysj = DealString.toGBK(request.getParameter("bysj"));
			String byzsbh = DealString.toGBK(request.getParameter("byzsbh"));
			String zxwyyzdm = DealString
					.toGBK(request.getParameter("zxwyyzdm"));
			String wydj = DealString.toGBK(request.getParameter("wydj"));
			String jsjdj = DealString.toGBK(request.getParameter("jsjdj"));
			String sjhm = DealString.toGBK(request.getParameter("sjhm"));
			String qqhm = DealString.toGBK(request.getParameter("qqhm"));
			String dzxx = DealString.toGBK(request.getParameter("dzxx"));
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			String lxdz = DealString.toGBK(request.getParameter("lxdz"));
			String shzw = DealString.toGBK(request.getParameter("shzw"));
			String yzbm = DealString.toGBK(request.getParameter("yzbm"));
			String jypx = DealString.toGBK(request.getParameter("jypx"));
			String xmsj = DealString.toGBK(request.getParameter("xmsj"));
			String zgzs = DealString.toGBK(request.getParameter("zgzs"));
			String jljn = DealString.toGBK(request.getParameter("jljn"));
			String sybz1 = DealString.toGBK(request.getParameter("sybz1"));
			String sybz2 = DealString.toGBK(request.getParameter("sybz2"));
			String sybz3 = DealString.toGBK(request.getParameter("sybz3"));
			String qysj = DealString.toGBK(request.getParameter("qysj"));
			String zyyx = DealString.toGBK(request.getParameter("zyyx"));
			HashMap<String, String> maphz = new HashMap<String, String>();

			maphz.put("xh", xh);
			maphz.put("xm", xm);
			maphz.put("zymc", zymc);
			maphz.put("zydm", zydm);
			maphz.put("szbj", szbj);
			maphz.put("szyx", szyx);
			maphz.put("yxmc", yxmc);
			maphz.put("yxdm", yxdm);
			maphz.put("sfzhm", sfzhm);
			maphz.put("xbdm", xbdm1);
			maphz.put("xldm", xldm);
			maphz.put("xwdm", xwdm);
			maphz.put("syszddm", syszddm);
			maphz.put("syszd", syszd);
			maphz.put("mzdm", mzdm);
			maphz.put("zzmmdm", zzmmdm);
			maphz.put("sfzz", sfzz);
			maphz.put("sfsf", sfsf);
			maphz.put("sfdl", sfdl);
			maphz.put("xz", xz);
			maphz.put("zslb", zslb);
			maphz.put("pyfsdm", pyfsdm);
			maphz.put("dxhwpdw", dxhwpdw);
			maphz.put("rxsj", rxsj);
			maphz.put("bysj", bysj);
			maphz.put("byzsbh", byzsbh);
			maphz.put("zxwyyzdm", zxwyyzdm);
			maphz.put("wydj", wydj);
			maphz.put("jsjdj", jsjdj);
			maphz.put("sjhm", sjhm);
			maphz.put("qqhm", qqhm);
			maphz.put("dzxx", dzxx);
			maphz.put("lxdh", lxdh);
			maphz.put("lxdz", lxdz);
			maphz.put("shzw", shzw);
			maphz.put("yzbm", yzbm);
			maphz.put("jypx", jypx);
			maphz.put("xmsj", xmsj);
			maphz.put("zgzs", zgzs);
			maphz.put("jljn", jljn);
			maphz.put("sybz1", sybz1);
			maphz.put("sybz2", sybz2);
			maphz.put("sybz3", sybz3);
			maphz.put("qysj", qysj);

//			String tjsj = (dao
//					.getOneRs(
//							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 51提交时间
//							// //
//							// 取至数据库临时表
//							new String[] {}, new String[] { "sdate" }))[0];

			String[] iszc = dao.getRs("select xm from zjgsjyxytj where xh=?",
					new String[] { xh }, new String("xm"));

			if (iszc.length > 0) {
				request.setAttribute("iszc", "iszc");
			} else {
				save = StandardOperation.insert("zjgsjyxytj", new String[] {
						"xh", "xm", "zymc", "zydm", "szbj", "szyx", "yxmc",
						"yxdm", "sfzhm", "xbdm", "xldm", "xwdm", "syszddm",
						"syszd", "mzdm", "zzmmdm", "sfzz", "sfsf", "sfdl",
						"xz", "zslb", "pyfsdm", "dxhwpdw", "rxsj", "bysj",
						"byzsbh", "zxwyyzdm", "wydj", "jsjdj", "sjhm", "qqhm",
						"dzxx", "lxdh", "lxdz", "shzw", "yzbm", "jypx", "xmsj",
						"zgzs", "jljn", "sybz1", "sybz2", "sybz3", "qysj","zyyx" },
						new String[] { xh, xm, zymc, zydm, szbj, szyx, yxmc,
								yxdm, sfzhm, xbdm1, xldm, xwdm, syszddm, syszd,
								mzdm, zzmmdm, sfzz, sfsf, sfdl, xz, zslb,
								pyfsdm, dxhwpdw, rxsj, bysj, byzsbh, zxwyyzdm,
								wydj, jsjdj, sjhm, qqhm, dzxx, lxdh, lxdz,
								shzw, yzbm, jypx, xmsj, zgzs, jljn, sybz1,
								sybz2, sybz3, qysj,zyyx}, request);
				if (save) {
					request.setAttribute("save", "ok");

				} else {
					request.setAttribute("save", "no");
				}
			}
			request.setAttribute("xyList", Base.getXyList());// 发送学院列表
			request.setAttribute("rs", maphz);
			return mapping.findForward("successadd");
		}

		// 把上次提交的值传回去

		if ("go".equals(act)) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			String xm = DealString.toGBK(request.getParameter("xm"));
			xymc = DealString.toGBK(request.getParameter("xymc"));
			
			mapsq.put("xh", xh);
			mapsq.put("xm", xm);
			mapsq.put("xymc", xymc);

			if (xh == null) {
				xh = "";
			}
			if (xm == null) {
				xm = "";
			}
			if (xymc == null) {
				xymc = "";
			}

			if (xh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xh ='" + xh + "' ";
			}
			if (xm.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xm ='" + xm + "' ";
			}
			if (xymc.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and szyx ='" + xymc + "' ";
			}

			request.setAttribute("rsq", mapsq);

			sql = "select count(*) count from zjgsjyxytj a " + querry;

			chfm.getPages().setMaxRecord(
					Integer.parseInt(dao
							.getOneRs(sql, new String[] {}, "count")));
			sql = "select * from (select ROWID rid,rownum r,a.*  from (SELECT * FROM zjgsjyxytj a "
					+ querry
					+ " order by qysj desc) a ) a where a.r<="
					+ (chfm.getPages().getStart() + chfm.getPages().getPageSize())
					+ " and a.r>="
					+ chfm.getPages().getStart() + " order by qysj desc";
			
			colList = new String[] { "rid", "xh", "xm", "szyx", "zymc", "szbj",
					"byzsbh", "qysj" };
			if ("go".equals(act)) {
				// rs = dao.getArrayList2(sql, new String[] {}, colList);
				ArrayList<String[]> rt = dao.rsToVator(sql, new String[] {},
						colList);
				if (rt != null) {
					rs.addAll(rt);
				}
			}
			sql = "select count(*) from zjgsjyxytj " + querry;
			int rsNuminfo = dao.getOneRsint(sql);
			rsNum = String.valueOf(rsNuminfo);
			colListCN = dao.getColumnNameCN(colList, "zjgsjyxytj");
			List<HashMap<String, String>> topTr = dao.arrayToList(colList,
					colListCN);
			
			request.setAttribute("xyList", Base.getXyList());// 发送学院列表
			request.setAttribute("rs", rs);// 发送数据集
			request.setAttribute("topTr", topTr);// 发送表头
			request.setAttribute("rsNum", rsNum);// 发送记录数
			return mapping.findForward("success");

		}

		// 删除数据
		if ("del".equals(act)) {
			boolean judge = false;
			String pk1 = request.getParameter("pk");
			sql = "delete from zjgsjyxytj where xh=?";
			judge = dao.runUpdate(sql, new String[] { pk1 });
			if (judge) {
				request.setAttribute("delete", "ok");

			} else {
				request.setAttribute("delete", "no");
			}
			request.setAttribute("xyList", Base.getXyList());// 发送学院列表
			request.setAttribute("rsq", mapsq);
			return mapping.findForward("success");
		}

		if ("saveupdate".equals(act)) {
			String pk1 = request.getParameter("pk");
//			boolean save = false;
			String xm = DealString.toGBK(request.getParameter("xm"));
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String zydm = DealString.toGBK(request.getParameter("zydm"));
			String szbj = DealString.toGBK(request.getParameter("szbj"));
			String szyx = DealString.toGBK(request.getParameter("szyx"));
			String yxmc = DealString.toGBK(request.getParameter("yxmc"));
			String yxdm = DealString.toGBK(request.getParameter("yxdm"));
			String sfzhm = DealString.toGBK(request.getParameter("sfzhm"));
			String xbdm1 = DealString.toGBK(request.getParameter("xbdm"));
			String xldm = DealString.toGBK(request.getParameter("xldm"));
			String xwdm = DealString.toGBK(request.getParameter("xwdm"));
			String syszddm = DealString.toGBK(request.getParameter("syszddm"));
			String syszd = DealString.toGBK(request.getParameter("syszd"));
			String mzdm = DealString.toGBK(request.getParameter("mzdm"));
			String zzmmdm = DealString.toGBK(request.getParameter("zzmmdm"));
			String sfzz = DealString.toGBK(request.getParameter("sfzz"));
			String sfsf = DealString.toGBK(request.getParameter("sfsf"));
			String sfdl = DealString.toGBK(request.getParameter("sfdl"));
			String xz = DealString.toGBK(request.getParameter("xz"));
			String zslb = DealString.toGBK(request.getParameter("zslb"));
			String pyfsdm = DealString.toGBK(request.getParameter("pyfsdm"));
			String dxhwpdw = DealString.toGBK(request.getParameter("dxhwpdw"));
			String rxsj = DealString.toGBK(request.getParameter("rxsj"));
			String bysj = DealString.toGBK(request.getParameter("bysj"));
			String byzsbh = DealString.toGBK(request.getParameter("byzsbh"));
			String zxwyyzdm = DealString
					.toGBK(request.getParameter("zxwyyzdm"));
			String wydj = DealString.toGBK(request.getParameter("wydj"));
			String jsjdj = DealString.toGBK(request.getParameter("jsjdj"));
			String sjhm = DealString.toGBK(request.getParameter("sjhm"));
			String qqhm = DealString.toGBK(request.getParameter("qqhm"));
			String dzxx = DealString.toGBK(request.getParameter("dzxx"));
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			String lxdz = DealString.toGBK(request.getParameter("lxdz"));
			String shzw = DealString.toGBK(request.getParameter("shzw"));
			String yzbm = DealString.toGBK(request.getParameter("yzbm"));
			String jypx = DealString.toGBK(request.getParameter("jypx"));
			String xmsj = DealString.toGBK(request.getParameter("xmsj"));
			String zgzs = DealString.toGBK(request.getParameter("zgzs"));
			String jljn = DealString.toGBK(request.getParameter("jljn"));
			String sybz1 = DealString.toGBK(request.getParameter("sybz1"));
			String sybz2 = DealString.toGBK(request.getParameter("sybz2"));
			String sybz3 = DealString.toGBK(request.getParameter("sybz3"));
			String qysj = DealString.toGBK(request.getParameter("qysj"));
			HashMap<String, String> maphz = new HashMap<String, String>();

			maphz.put("xh", pk1);
			maphz.put("xm", xm);
			maphz.put("zymc", zymc);
			maphz.put("zydm", zydm);
			maphz.put("szbj", szbj);
			maphz.put("szyx", szyx);
			maphz.put("yxmc", yxmc);
			maphz.put("yxdm", yxdm);
			maphz.put("sfzhm", sfzhm);
			maphz.put("xbdm", xbdm1);
			maphz.put("xldm", xldm);
			maphz.put("xwdm", xwdm);
			maphz.put("syszddm", syszddm);
			maphz.put("syszd", syszd);
			maphz.put("mzdm", mzdm);
			maphz.put("zzmmdm", zzmmdm);
			maphz.put("sfzz", sfzz);
			maphz.put("sfsf", sfsf);
			maphz.put("sfdl", sfdl);
			maphz.put("xz", xz);
			maphz.put("zslb", zslb);
			maphz.put("pyfsdm", pyfsdm);
			maphz.put("dxhwpdw", dxhwpdw);
			maphz.put("rxsj", rxsj);
			maphz.put("bysj", bysj);
			maphz.put("byzsbh", byzsbh);
			maphz.put("zxwyyzdm", zxwyyzdm);
			maphz.put("wydj", wydj);
			maphz.put("jsjdj", jsjdj);
			maphz.put("sjhm", sjhm);
			maphz.put("qqhm", qqhm);
			maphz.put("dzxx", dzxx);
			maphz.put("lxdh", lxdh);
			maphz.put("lxdz", lxdz);
			maphz.put("shzw", shzw);
			maphz.put("yzbm", yzbm);
			maphz.put("jypx", jypx);
			maphz.put("xmsj", xmsj);
			maphz.put("zgzs", zgzs);
			maphz.put("jljn", jljn);
			maphz.put("sybz1", sybz1);
			maphz.put("sybz2", sybz2);
			maphz.put("sybz3", sybz3);
			maphz.put("qysj", qysj);
			boolean judge = false;
			String[] columns = { "zymc", "zydm", "szbj", "szyx", "yxmc",
					"yxdm", "sfzhm", "xbdm", "xldm", "xwdm", "syszddm",
					"syszd", "mzdm", "zzmmdm", "sfzz", "sfsf", "sfdl", "xz",
					"zslb", "pyfsdm", "dxhwpdw", "rxsj", "bysj", "byzsbh",
					"zxwyyzdm", "wydj", "jsjdj", "sjhm", "qqhm", "dzxx",
					"lxdh", "lxdz", "shzw", "yzbm", "jypx", "xmsj", "zgzs",
					"jljn", "sybz1", "sybz2", "sybz3" };
			String[] values = { zymc, zydm, szbj, szyx, yxmc, yxdm, sfzhm,
					xbdm1, xldm, xwdm, syszddm, syszd, mzdm, zzmmdm, sfzz,
					sfsf, sfdl, xz, zslb, pyfsdm, dxhwpdw, rxsj, bysj, byzsbh,
					zxwyyzdm, wydj, jsjdj, sjhm, qqhm, dzxx, lxdh, lxdz, shzw,
					yzbm, jypx, xmsj, zgzs, jljn, sybz1, sybz2, sybz3 };
			judge = StandardOperation.update(tableName, columns, values, "xh",
					pk1, request);
			if (judge) {
				request.setAttribute("saveupdate", "ok");
			} else {
				request.setAttribute("saveupdate", "no");
			}
			request.setAttribute("rs", maphz);
			return mapping.findForward("success1");
		}
		if ("update".equals(act)) {
			String pk1 = request.getParameter("pk");

			colList = new String[] { "xh", "xm", "zymc", "zydm", "szbj",
					"szyx", "yxmc", "yxdm", "sfzhm", "xbdm", "xldm", "xwdm",
					"syszddm", "syszd", "mzdm", "zzmmdm", "sfzz", "sfsf",
					"sfdl", "xz", "zslb", "pyfsdm", "dxhwpdw", "rxsj", "bysj",
					"byzsbh", "zxwyyzdm", "wydj", "jsjdj", "sjhm", "qqhm",
					"dzxx", "lxdh", "lxdz", "shzw", "yzbm", "jypx", "xmsj",
					"zgzs", "jljn", "sybz1", "sybz2", "sybz3", "qysj","zyyx" };
			sql = "select * from zjgsjyxytj where xh='" + pk1 + "'";

			map = dao.getRSArray(sql, colList);
			request.setAttribute("xyList", Base.getXyList());// 发送学院列表
			request.setAttribute("rs", map);
			return mapping.findForward("success1");
		}
		if ("view".equals(act)) {
			String pk1 = request.getParameter("pk");

			colList = new String[] { "xh", "xm", "zymc", "zydm", "szbj",
					"szyx", "yxmc", "yxdm", "sfzhm", "xbdm", "xldm", "xwdm",
					"syszddm", "syszd", "mzdm", "zzmmdm", "sfzz", "sfsf",
					"sfdl", "xz", "zslb", "pyfsdm", "dxhwpdw", "rxsj", "bysj",
					"byzsbh", "zxwyyzdm", "wydj", "jsjdj", "sjhm", "qqhm",
					"dzxx", "lxdh", "lxdz", "shzw", "yzbm", "jypx", "xmsj",
					"zgzs", "jljn", "sybz1", "sybz2", "sybz3", "qysj","zyyx" };
			sql = "select * from zjgsjyxytj where xh='" + pk1 + "'";

			map = dao.getRSArray(sql, colList);
			request.setAttribute("rs", map);
			return mapping.findForward("success2");
		}
		if("ondbview".equals(act)){
			String xh = request.getParameter("xh");
			sql = "select * from jygl_xsjbxxb where xsxh=?"; // 查询该学号的相关内容的sql语句
			colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2"); // 返回列名数组
			String[] rs1 = dao.getOneRs(sql, new String[] { xh }, colList); // 获得从毕业生基本信息表（视图）中查询的记录集
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs1[i]); // 将记录循环放入map中
				}
			}
		}
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("rsq", mapsq);
		request.setAttribute("rsNum", rsNum); // 发送记录数
		request.setAttribute("rs1", rs); // 发送数据集
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward cgjyxys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

//		 CommanForm chfm = (CommanForm) form;
		ZjgxJyxxForm chfm = (ZjgxJyxxForm) form;
		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "cgxysb"; // 查询信息源表（多为视图名）
		String rsNum = "0";// 返回的记录数
//		String xxdm = dao.getXxdm();
		String act = request.getParameter("act"); // 接收数据类型
		String pk = ""; // 主键
		HashMap<String, String> map = new HashMap<String, String>();
//		HashMap<String, String> rsq = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		List<HashMap<String, String>> topTr = null;

//		HashMap<String, String> maphz = new HashMap<String, String>();
		HashMap<String, String> mapsq = new HashMap<String, String>();
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		String zgxysyy = DealString.toGBK(request.getParameter("zgxysyy"));
		String xyjcyy = DealString.toGBK(request.getParameter("xyjcyy"));
		if ("go".equals(act)) {

			mapsq.put("xm", xm);
			request.setAttribute("rsp", mapsq);
			if (xm == null) {
				xm = "";
			}
			if (xh == null) {
				xh = "";
			}
			if (zgxysyy == null) {
				zgxysyy = "";
			}
			if (xyjcyy == null) {
				xyjcyy = "";
			}

			if (xm.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xm ='" + xm + "' ";
			}
			if (xh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xh ='" + xh + "' ";
			}
			if (zgxysyy.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and zgxysyy ='" + zgxysyy + "' ";
			}
			if (xyjcyy.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xyjcyy ='" + xyjcyy + "' ";
			}

			map.put("xm", xm);
			map.put("xh", xh);
			map.put("xyjcyy", xyjcyy);
			map.put("zgxysyy", zgxysyy);

			sql = "select count(*) count from cgxysb a " + querry;

			chfm.getPages().setMaxRecord(
					Integer.parseInt(dao
							.getOneRs(sql, new String[] {}, "count")));

			sql = "select * from (select ROWID rid,rownum r,a.*  from (SELECT * FROM cgxysb a "
					+ querry
					+ " order by xh desc) a ) a where a.r>"
					+ chfm.getPages().getStart()
					+ " and a.r<="
					+ (chfm.getPages().getStart() + chfm.getPages()
							.getPageSize()) + " order by xh desc";

			colList = new String[] { "rid", "xh", "xm", "zymc", "jtdz",
					"zgxysyy", "xxysbh", "yqydw", "dwxz", "jygw", "xyjcyy" };
			if ("query".equals(doType)) {
				// rs = dao.getArrayList2(sql, new String[] {}, colList);
				ArrayList<String[]> rt = dao.rsToVator(sql, new String[] {},
						colList);
				if (rt != null) {
					rs.addAll(rt);
				}
			}
			// sql = "select count(*) from zjgsjyxytj " + querry;
			// int rsNuminfo = dao.getOneRsint(sql);
			// rsNum = String.valueOf(rsNuminfo);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			colListCN = dao.getColumnNameCN(colList, "cgxysb");
			topTr = dao.arrayToList(colList, colListCN);

			request.setAttribute("rsq", map);
			request.setAttribute("rs", rs);// 发送数据集
			request.setAttribute("topTr", topTr);// 发送表头
			request.setAttribute("rsNum", rsNum);// 发送记录数
			chfm.setXm(xm);
			chfm.setXh(xh);
			return mapping.findForward("success");

		}
		if ("add".equals(act)) {
			xh = DealString.toGBK(request.getParameter("xh"));
			xm = DealString.toGBK(request.getParameter("xm"));
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String jtdz = DealString.toGBK(request.getParameter("jtdz"));
			zgxysyy = DealString.toGBK(request.getParameter("zgxysyy"));
			String xxysbh = DealString.toGBK(request.getParameter("xxysbh"));
			String yqydw = DealString.toGBK(request.getParameter("yqydw"));
			String dwxz = DealString.toGBK(request.getParameter("dwxz"));
			String jygw = DealString.toGBK(request.getParameter("jygw"));
			xyjcyy = DealString.toGBK(request.getParameter("xyjcyy"));

			map.put("xh", xh);
			map.put("xm", xm);
			map.put("zymc", zymc);
			map.put("jtdz", jtdz);
			map.put("zgxysyy", zgxysyy);
			map.put("xxysbh", xxysbh);
			map.put("yqydw", yqydw);
			map.put("dwxz", dwxz);
			map.put("jygw", jygw);
			map.put("xyjcyy", xyjcyy);

			if ("save".equals(doType)) {

				boolean judge = false;
				judge = StandardOperation.insert(tableName, new String[] {
						"xh", "xm", "zymc", "jtdz", "zgxysyy", "xxysbh",
						"yqydw", "dwxz", "jygw", "xyjcyy" }, new String[] { xh,
						xm, zymc, jtdz, zgxysyy, xxysbh, yqydw, dwxz, jygw,
						xyjcyy }, request);
				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
			request.setAttribute("rs", map);
			return mapping.findForward("successadd");
		}

		if ("update".equals(act)) {
			String pk1 = request.getParameter("pk");
			pk = pk1.replaceAll(" ", "+");

			colList = new String[] { "rid", "xh", "xm", "zymc", "jtdz",
					"zgxysyy", "xxysbh", "yqydw", "dwxz", "jygw", "xyjcyy" };
			sql = "SELECT ROWID rid,a.* from cgxysb a where rowid='" + pk + "'";
			map = dao.getRSArray(sql, colList);
			request.setAttribute("rs", map);
			return mapping.findForward("success1");
		}
		if ("saveupdate".equals(act)) {
			xh = DealString.toGBK(request.getParameter("xh"));
			xm = DealString.toGBK(request.getParameter("xm"));
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String jtdz = DealString.toGBK(request.getParameter("jtdz"));
			zgxysyy = DealString.toGBK(request.getParameter("zgxysyy"));
			String xxysbh = DealString.toGBK(request.getParameter("xxysbh"));
			String yqydw = DealString.toGBK(request.getParameter("yqydw"));
			String dwxz = DealString.toGBK(request.getParameter("dwxz"));
			String jygw = DealString.toGBK(request.getParameter("jygw"));
			xyjcyy = DealString.toGBK(request.getParameter("xyjcyy"));
			String pk1 = request.getParameter("rid");
			if (pk1 != null) {
				pk = pk1.replaceAll(" ", "+");
			}
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("zymc", zymc);
			map.put("jtdz", jtdz);
			map.put("zgxysyy", zgxysyy);
			map.put("xxysbh", xxysbh);
			map.put("yqydw", yqydw);
			map.put("dwxz", dwxz);
			map.put("jygw", jygw);
			map.put("xyjcyy", xyjcyy);

			boolean judge = false;
			String[] columns = { "zymc", "jtdz", "zgxysyy", "xxysbh", "yqydw",
					"dwxz", "jygw", "xyjcyy" };
			String[] values = { zymc, jtdz, zgxysyy, xxysbh, yqydw, dwxz, jygw,
					xyjcyy };
			judge = StandardOperation.update(tableName, columns, values,
					"rowid", pk, request);
			if (judge) {
				request.setAttribute("saveupdate", "ok");
			} else {
				request.setAttribute("saveupdate", "no");
			}
			request.setAttribute("rs", map);
			return mapping.findForward("success1");
		}

		// 删除
		if ("del".equals(act)) {
			boolean judge = false;
			String pk1 = request.getParameter("pk");
			if (pk1 != null) {
				pk = pk1.replaceAll(" ", "+");
			}
			sql = "delete from " + tableName + " where rowid='" + pk + "'";
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		if ("view".equals(act)) {
			String pk1 = request.getParameter("pk");
			pk = pk1.replaceAll(" ", "+");

			colList = new String[] { "rid", "xh", "xm", "zymc", "jtdz",
					"zgxysyy", "xxysbh", "yqydw", "dwxz", "jygw", "xyjcyy" };
			sql = "SELECT ROWID rid,a.* from cgxysb a where rowid='" + pk + "'";
			map = dao.getRSArray(sql, colList);
			request.setAttribute("rs", map);
			return mapping.findForward("success2");
		}

		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rsq", map);
		return mapping.findForward("success");
	}
	
	private ActionForward jyxysbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZjgxJyxxForm myform = (ZjgxJyxxForm) form;
		DAO dao = DAO.getInstance();
		
		String sql = "";
		boolean result = false;
		
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String act = request.getParameter("act");
		
		HashMap<String, String> map = new HashMap<String, String>();
		if (!"stu".equals(userType)) {
			//request.setAttribute("errMsg", "该页面只能有学生填写！！");
			//return mapping.findForward("false");
		}
		if ("add".equals(act)) {
			sql = "delete from sjxy_xysbbb where xh='"+userName+"'";
			dao.runUpdate(sql, new String[]{});
			String[] colList = {"xh","bbyy","bbsm","byny"};
			String[] insertString = FormModleCommon.modelToStrings(colList, myform);
			result = StandardOperation.insertNoLog("sjxy_xysbbb", colList, insertString);
			if (result) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}
		String pk = request.getParameter("pk");
		String[] outputValue = null;
		outputValue = new String[]{"xh","xm","xb","nj","xz","xymc","zymc","bjmc","byny"};
		sql = "select * from view_xsxxb where xh = ?";
		map = dao.getMap(sql, new String[]{userName}, outputValue);
		if("update".equals(act)){
			sql = "select * from view_sjxy_xysbbb where xh = ?";
			HashMap<String, String> rsmap = dao.getMap(sql, new String[]{pk}, new String[]{"bbyy","bbsm"});
			myform.setBbyy(rsmap.get("bbyy"));
			myform.setBbsm(rsmap.get("bbsm"));
		}
		if("view".equals(act)){
			sql = "select * from view_sjxy_xysbbb where xh = ?";
			HashMap<String, String> rsmap = dao.getMap(sql, new String[]{pk},dao.getColumnName("select * from view_sjxy_xysbbb"));
			request.setAttribute("rs", rsmap);
			return mapping.findForward("jyxysbbsqview");
		}
		if("xxsh".equals(act)){
			String acttype = request.getParameter("acttype");
			String xysbh = request.getParameter("xysbh");
			String xh = request.getParameter("XH");
			if ("add".equals(acttype)) {
				sql = "update jygl_jyxy set xysbh='"+xysbh+"'where xsxh='"+xh+"'";
				result = dao.runUpdate(sql, new String[]{});
				if (result) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
				pk = StringUtils.isNull(pk)?xh:pk;
			}
			sql = "select * from view_sjxy_xysbbb where xh = ?";
			HashMap<String, String> rsmap = dao.getMap(sql, new String[]{pk},dao.getColumnName("select * from view_sjxy_xysbbb"));
			request.setAttribute("rs", rsmap);
			return mapping.findForward("jyxysbbxysbh");
		}
		if ("exp".equals(act)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData("select r,xh,xm,xb,nj,xz,xymc,zymc,bjmc,bbyy,bbsm,sqrq,byny from view_sjxy_xysbbb", "view_sjxy_xysbbb", response.getOutputStream());
			return mapping.findForward("");
		}
		
		request.setAttribute("rs", map);
		return mapping.findForward("jyxysbbsq");
	}
	
	private ActionForward jyxysbbgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZjgxJyxxForm chfm = (ZjgxJyxxForm) form;
		StringBuffer querry = new StringBuffer();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String act = request.getParameter("act");
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		DAO dao = DAO.getInstance();
		
		String sql = "";
		String[] colList = {"xh","xm","xb","nj","xz","xymc","zymc","bjmc","bbyy","byny","xysh"};
		
		if("del".equals(act)){
			StringBuffer sb = new StringBuffer();
			String pks = request.getParameter("pkVStr");
			String[] keys = pks.split("!!");
			String[] pksql = new String[] {};
			for (int i = 0; i < keys.length; i++) {
				String pk = DealString.toGBK(keys[i]);// 得到主键
				sql = "delete from sjxy_xysbbb where xh = '" + pk + "'";
				sb.append(sql);
				sb.append("!!");
			}
			pksql = sb.toString().split("!!");
			int[] judge = dao.runBatch(pksql);
			String whichpk = "";
			for (int i = 0; i < judge.length; i++) {
				if (judge[i] < 0) {
					whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
				}
			}
			act = "go";
		}
		if("xysh".equals(act)){
			StringBuffer sb = new StringBuffer();
			String pks = request.getParameter("pkVStr");
			String shlx = request.getParameter("shlx");
			if("tg".equals(shlx)){
				shlx = "通过";
			}else{
				shlx = "不通过";
			}
			String[] keys = pks.split("!!");
			String[] pksql = new String[] {};
			for (int i = 0; i < keys.length; i++) {
				String pk = DealString.toGBK(keys[i]);// 得到主键
				sql = "update sjxy_xysbbb set xysh='"+shlx+"' where xh = '"+pk+"'";
				sb.append(sql);
				sb.append("!!");
			}
			pksql = sb.toString().split("!!");
			int[] judge = dao.runBatch(pksql);
			String whichpk = "";
			for (int i = 0; i < judge.length; i++) {
				if (judge[i] < 0) {
					whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
				}
			}
			act = "go";
		}
		if ("stu".equals(userType)) {
			chfm.setXh(userName);
			chfm.setXm(request.getSession().getAttribute("userNameReal").toString());
		}
		if ("xy".equals(userType)) {
			chfm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		if ("go".equals(act)) {
			sql = "select * from view_sjxy_xysbbb";
			querry = FormModleCommon.makeQuery(new String[]{"byny","xh","nj","xydm","zydm","bjdm","xysh"}, chfm);
			String xm = chfm.getXm().trim();
			querry.append(StringUtils.isNotNull(xm)?" and xm like '%"+xm+"%'": "");
			rs = CommonQueryDAO.commonQuery(sql, querry.toString(), new String[]{}, colList, chfm);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", dao.arrayToList(colList, dao.getColumnNameCN(colList, "view_sjxy_xysbbb")));
		}
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		if("admin".equals(userType) || "xx".equals(userType)){
			request.setAttribute("iswho", "xx");
		}else if("xy".equals(userType)){
			request.setAttribute("iswho", "xy");
		}else{
			request.setAttribute("iswho", "stu");
		}
		
		request.setAttribute("writeAble",(Base.chkUPower(userName,"jyxysbbgl.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("jyxysbbcx");
	}
}
