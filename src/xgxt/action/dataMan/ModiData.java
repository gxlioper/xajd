package xgxt.action.dataMan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.dtjs.zjcm.server.DtjszjcmService;
import xgxt.form.CommanForm;
import xgxt.qgzx.service.QgzxService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.New_Random_ID;
import xgxt.utils.ToolClass;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.xsgygl.ynys.qsz.YnysQszService;

import common.Globals;

public class ModiData extends Action {

	public ActionForward modiData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String writeAble) throws Exception {
		// 单个数据查询、修改、添加
		ActionForward newFwd = new ActionForward();
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		XsxxglService servcie = new XsxxglService();		
		DAO dao = DAO.getInstance();
		String pk1 = request.getParameter("pk");
		String pk = Base.isNull(pk1) ? "" : pk1.replaceAll("-", "||");
		// String pk = pk1.replaceAll("-", "||");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String[] pkValueA = pkValue.split("!!SplitOneSplit!!");
		String realTable = request.getParameter("realTable");
		System.out.println("realTable==" + realTable);
		String tableName = request.getParameter("tableName");
		System.out.println("tableName==" + tableName);
		String doType = request.getParameter("doType");
		String from = request.getParameter("from");
		boolean ok = false;
		String[] dqndq = dao.getOneRs("select dqxn,dqxq,dqnd from xtszb",
				new String[] {}, new String[] { "dqxn", "dqxq", "dqnd" });
		String sql = "";
		String xxmc = StandardOperation.getXxmc();
		String xxdm = StandardOperation.getXxdm();
		String dxq = request.getParameter("dxq");
		String dxq1 = request.getParameter("writeAble");
		String xh = request.getParameter("xh");
		request.setAttribute("sendxh", xh);
		String userOnLine = session.getAttribute("userOnLine").toString();
		if(userOnLine.equalsIgnoreCase("student")){
			Map<String, String> rs = new HashMap<String, String>();
			String userName = session.getAttribute("userName").toString();
			BasicExtendService basicExtendService = new  BasicExtendService();
			System.out.println("------------------student 登录");
			rs=basicExtendService.getStuInfo(userName, new String[]{"xh","xm","nj"});
			request.setAttribute("rs", rs);
		}
		if (!("".equalsIgnoreCase(dxq) || dxq == null)) {
			writeAble = dxq;
		} else {
			if (!("".equalsIgnoreCase(dxq1) || dxq1 == null)) {
				writeAble = dxq1;
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			request.setAttribute("showhzy", "showhzy");
		}
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			request.setAttribute("shownblg", "shownblg");
		}*/ //lyl
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
			request.setAttribute("showzszy", "showzszy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			request.setAttribute("showjsxx", "showjsxx");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			request.setAttribute("showbjlh", "showbjlh");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			request.setAttribute("showhzy", "showhzy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
			request.setAttribute("showGdnz", "showGdnz");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			request.setAttribute("showCsmzzyjsxy", "showCsmzzyjsxy");
		}
		/*
		 * if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
		 * request.setAttribute("showXbemy", "showXbemy"); }
		 */

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			dataSearchForm.setErrMsg("sdf");
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("del")) {
			newFwd = delData(request, writeAble, dao, pk, pkValue, pkValueA,
					realTable, from, xxdm);
		} else if (doType.equalsIgnoreCase("save")) {
			newFwd = saveData(mapping, request, dataSearchForm, dao, pk,
					pkValue, realTable, ok, xxmc, xxdm);
		} else {
			// TODO 生成页面

			if ("view".equalsIgnoreCase(doType)
					&& "view_wjcf".equalsIgnoreCase(tableName)) {
				request.setAttribute("wjcfWritable", "no");
			}

			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)
					&& tableName.equalsIgnoreCase("view_xsgwxx")) {
				// 重庆科技
				
				xh = (xh == null || xh.equalsIgnoreCase("")) ? "" : xh.trim();

				String[] sj = { "早自修（7:30—8:20）", "第1-2节（8:30—10:05）",
						"第3-4节（10:25—12:00）", "午休（12:00—13:45）",
						"第5-6节（13:50—15:25）", "第7-8节（15:30—17:05）",
						"晚自修（17:50—20:15）" };
				String[] xq = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };

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
			}
			if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)
					&& realTable.equalsIgnoreCase("xsgwxxb")) {
				// 查询广州大学学生岗位详细信息
				return new ActionForward("/qgzxLogic.do?method=showGzdxXsgwxxb");
			}
			if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)
					&& realTable.equalsIgnoreCase("xsgwxxb")) {
				// 查询中国地质大学学生岗位详细信息
				return new ActionForward(
						"/qgzxLogic.do?method=showZgdzdxXsgwxxb");
			}
			// ********2010.4.7 qph begin*********//
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)
					&& realTable.equalsIgnoreCase("xlytqkb")) {
				sql = "select a.xm,a.xb,a.xymc,a.xydm,a.bjmc,a.ssbh,x.* from "
						+ tableName + " a";
			} else {
				sql = "select * from " + tableName + " t";
			}
			// ********2010.4.7 qph end*********//
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)
					&& realTable.equalsIgnoreCase("xlytqkb")) {
				sql += (",xlytqkb x");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)
					&& tableName.equalsIgnoreCase("view_xsgwxx")) {
				sql = "select a.*,(select zzmmmc from view_stu_details b where a.xh=b.xh) zzmmmc,(select mzmc from view_stu_details b where a.xh=b.xh) mzmc,(select jtszd jtdz from view_xsfzxx b where a.xh=b.xh) jtdz,(select yb from view_xsfzxx b where a.xh=b.xh)jtyb,(select lxdh1 from view_xsfzxx b where a.xh=b.xh) jtdh from "
						+ tableName + " a ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
					&& realTable.equalsIgnoreCase("lpxxb")) {
				sql = "select xh,max(xm)xm,max(xb)xb,max(nj)nj,max(zymc)zymc,max(xymc)xymc,max(bjmc)bjmc,nd,xn,xq,max(bxgsmc),bxgsdm,max(slrq)slrq,max(dzsj)dzsj,sum(lpje)lpje,sum(hffy)hffy from "
						+ tableName;
				sql += " where " + pk + "='" + pkValue + "'";
				sql += " group by xh,nd,xn,xq,bxgsdm";
			}
			String[] record = null;
			String[] colList = dao.getColumnName(sql);

			if (!(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) && realTable
					.equalsIgnoreCase("lpxxb"))) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)
						&& realTable.equalsIgnoreCase("xlytqkb")) {
					sql += (" where a." + pk + "='" + pkValue + "'");
				} else {
					sql += (" where t." + pk + "='" + pkValue + "'");
				}
			}
			if ((xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) && realTable
					.equalsIgnoreCase("xlytqkb"))) {
				sql += (" and a.yt_id=x.yt_id ");
			}
			String rec = dao.getStringToSplit(sql, new String[] {}, colList);
			if (rec.equalsIgnoreCase("Error")) {
				record = new String[colList.length];
			} else {
				String[] tmp = rec.split("!!SplitSignOne!!");
				if (tmp.length != 2) {
					if ((realTable.equalsIgnoreCase("sxhbb")
							|| tableName.equalsIgnoreCase("view_gdnz_lpxx") || realTable
							.equalsIgnoreCase("lpxxb"))
							&& tmp.length > 2) {
						record = tmp[1].split("!!SplitSignTwo!!");
					} else {
						record = new String[colList.length + 1];
					}
				} else {
					record = tmp[1].split("!!SplitSignTwo!!");
				}
			}
			if (record.length != colList.length + 1) {
				record = new String[colList.length + 1];
			}
			HashMap<String, String> map = new HashMap<String, String>();

			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(),
						Base.isNull(record[i + 1]) ? "" : record[i + 1].trim());
			}
//			if (Globals.XXDM_ZJLG.equals(xxdm)) {
//xh已经有值了,不用再取了吧.音响其他模块取学号 2010.12.21 qlj
//				xh = map.get("xh");
				// System.out.println(xh);
				if (StringUtils.isNull(xh)) {
					map.put("sfkns", "");
				} else {
					map.put("sfkns", DAO.getInstance().isKns(xh) ? "是" : "否");
					map.put("sfpks", DAO.getInstance().isKns(xh) ? "是" : "否");
				}
//			}
			// 党团建设学生层次列表
			if (realTable.equalsIgnoreCase("dyxxb")
					|| realTable.equalsIgnoreCase("ybdyxxb")
					|| realTable.equalsIgnoreCase("rdjjfzxxb")) {
				String mySql = "select xsccdm, xsccmc from dtjs_xsccb";
				List<HashMap<String, String>> temp = dao.getList(mySql,
						new String[] {}, new String[] { "xsccdm", "xsccmc" });
				request.setAttribute("xsccList", temp);// 发送dtjs_xsccb列表
				request.setAttribute("xxdm", xxdm);// 发送dtjs_xsccb列表
			}
			if (realTable.equalsIgnoreCase("xspxxxb")) {
				sql = "select pxxmdm xmdm,pxxmmc xmmc from typxxmdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"xmdm", "xmmc" });
				request.setAttribute("xmdmList", xmdmList);
			} else if (realTable.equalsIgnoreCase("rdjjfzxxb")
					|| realTable.equalsIgnoreCase("ybdyxxb")
					|| realTable.equalsIgnoreCase("xxb")
					|| realTable.equalsIgnoreCase("jxjgb")) {
				request.setAttribute("ynList", dao.getChkList(2));
			} else if (realTable.equalsIgnoreCase("knsshhdxxb")) {
				sql = "select shhdxzdm,shhdxzmc from shhdxzdmb";
				List shhdxzList = dao.getList(sql, new String[] {},
						new String[] { "shhdxzdm", "shhdxzmc" });
				request.setAttribute("shhdxzList", shhdxzList);
			} else if (realTable.equalsIgnoreCase("jszhkpb")) {
				request.setAttribute("scoreList", dao.getScore(5));
			} else if (realTable.equalsIgnoreCase("xsjxhjb")) {

				sql = "select jxdm,jxmc from jxjxdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"jxdm", "jxmc" });
				request.setAttribute("jxList", xmdmList);
			} else if (realTable.equalsIgnoreCase("zhszcp")) {
				request.setAttribute("scoreList", dao.getScore(5));
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {// 广东白云学院
					request.setAttribute("isgdby", "yes");
				}
				/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {// 宁波理工
					String[] cjAndPm = dao
							.getOneRs(
									"select zhszcpzf,zhpm,kcjqpfj,jpfpm,dcj,dcjpm from (select xh,xn,xq,nd,zhszcpzf, (dense_rank() over (partition by bjdm order by to_number(zhszcpzf) desc nulls last)) zhpm,kcjqpfj, (dense_rank() over (partition by zydm order by to_number(kcjqpfj) desc nulls last)) jpfpm,trunc((to_number(ddcj)+to_number(xwcj)+to_number(shqcj)),2) dcj,(dense_rank() over (partition by bjdm order by to_number(trunc((to_number(ddcj)+to_number(xwcj)+to_number(shqcj)),2)) desc nulls last)) dcjpm from view_zhszcp) where xn||xh=?",
									new String[] { pkValue }, new String[] {
											"zhszcpzf", "zhpm", "dcj", "dcjpm",
											"kcjqpfj", "jpfpm" });
					if (cjAndPm != null && cjAndPm.length == 6) {
						request.setAttribute("zhszcpzf", cjAndPm[0]);
						request.setAttribute("zhpm", cjAndPm[1]);
						request.setAttribute("dcj", cjAndPm[2]);
						request.setAttribute("dcjpm", cjAndPm[3]);
						request.setAttribute("kcjqpfj", cjAndPm[4]);
						request.setAttribute("jpfpm", cjAndPm[5]);
					}

					request.setAttribute("shownbng", "yes");
				}*/ //lyl
				if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
					String[] tmpList = new String[] { "zgdy", "zgybdy", "xjbj",
							"wmss", "xsgb1", "xsgb2", "xsgb3", "xsgb4", "wysp",
							"jsjsp", "xjjxj1", "xjjxj2", "xjjxj3", "gjjl1",
							"gjjl2", "gjjl3", "sjjl1", "sjjl2", "sjjl3",
							"xjgr1", "xjgr2", "zf" };
					String[] pfList = dao
							.getOneRs(
									"select * from sjdxspfxzb where xh=(select xh from view_zhszcp where xn||xh=?)",
									new String[] { pkValue }, tmpList);
					String[] xqS = dao
							.getOneRs(
									"select xh,xz,nj,((select to_number(a.dqnd) from xtszb a)-to_number(nj))*2 zxq,(select b.dqxq from xtszb b) dqxq from view_zhszcp where xn||xh = ?",
									new String[] { pkValue }, new String[] {
											"zxq", "dqxq" });
					if (xqS != null && xqS.length == 2) {
						int iXq = 0;
						if (!StringUtils.isNull(xqS[1])
								&& StringUtils.isEqual(xqS[1], "02")) {
							iXq = StringUtils.isNull(xqS[0]) ? 0 : (Integer
									.parseInt(xqS[0]) + 1);
						}
						if (!StringUtils.isNull(xqS[1])
								&& StringUtils.isEqual(xqS[1], "01")) {
							iXq = StringUtils.isNull(xqS[0]) ? 0 : Integer
									.parseInt(xqS[0]);
						}
						map.put("zxq", iXq + "");// 获取学生累计学期数
					}
					if (pfList != null && pfList.length == 22) {
						for (int i = 0; i < tmpList.length; i++) {
							map.put(tmpList[i], pfList[i]);
						}
					}
				}
			} else if (realTable.equalsIgnoreCase("nsepxxb")) {
				sql = "select  nsepxmdm,nsepxmmc from nsepxmdmb";
				List nsepList = dao.getList(sql, new String[] {}, new String[] {
						"nsepxmdm", "nsepxmmc" });
				sql = "select nsepjbdm,nsepjbmc from nsepjbdmb";
				List nsepjbList = dao.getList(sql, new String[] {},
						new String[] { "nsepjbdm", "nsepjbmc" });
				request.setAttribute("nsepjbList", nsepjbList);
				request.setAttribute("nsepList", nsepList);
			} else if (realTable.equalsIgnoreCase("xlcsjgb")) {
				String csxmdm = request.getParameter("csxmdm");
				
				String xm = DealString.toGBK(request.getParameter("xm"));
				String xb = DealString.toGBK(request.getParameter("xb"));
				String nj = request.getParameter("nj");
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String bjmc = DealString.toGBK(request.getParameter("bjmc"));
				sql = "select xlcsxmdm,xlcsxmmc from xlcsxmdmb";
				List csxmList = dao.getList(sql, new String[] {}, new String[] {
						"xlcsxmdm", "xlcsxmmc" });
				SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
				String csnj = simp.format(new Date());
				request.setAttribute("csxmList", csxmList);
				request.setAttribute("csnj", csnj);
				if ((csxmdm != null) && !csxmdm.equals("")) {
					sql = "select xlcsjgdm,xlcsjgmc from xlcsjgdmb where xlcsxmdm='"
							+ csxmdm + "'";
					List csjgList = dao.getList(sql, new String[] {},
							new String[] { "xlcsjgdm", "xlcsjgmc" });
					request.setAttribute("csjgList", csjgList);
					map.put("xh", xh);
					map.put("csxmdm", csxmdm);
					map.put("xm", xm);
					map.put("xb", xb);
					map.put("nj", nj);
					map.put("xymc", xymc);
					map.put("zymc", zymc);
					map.put("bjmc", bjmc);
				} else if ((map.get("csxmdm") != null)
						&& !map.get("csxmdm").equals("")) {
					sql = "select xlcsjgdm,xlcsjgmc from xlcsjgdmb where xlcsxmdm='"
							+ map.get("csxmdm") + "'";
					List csjgList = dao.getList(sql, new String[] {},
							new String[] { "xlcsjgdm", "xlcsjgmc" });
					request.setAttribute("csjgList", csjgList);
				}
			} else if (realTable.equalsIgnoreCase("zxdk_htxx")) {
			} else if (realTable.equalsIgnoreCase("xytbgxxsxxb")) {
				sql = "select tbgxxslbdm,tbgxxslbmc from tbgxxslbdmb";
				List tbgxxslbdmList = dao.getList(sql, new String[] {},
						new String[] { "tbgxxslbdm", "tbgxxslbmc" });
				
				sql = "select lydm,lymc from xg_xljk_tsxslydmb";
				List tsxslyList = dao.getList(sql, new String[] {},
						new String[] { "lydm", "lymc" });
				
				request.setAttribute("tbgxxslbdmList", tbgxxslbdmList);
				request.setAttribute("tsxslyList", tsxslyList);
			} else if (realTable.equalsIgnoreCase("wjcfb")
					|| realTable.equalsIgnoreCase("wjcflsb")) {
				List cflbList = null;
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					request.setAttribute("isCSMZ", "yes");
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
					sql = "select cflbdm,cflbmc from cflbdmb where cflbmc = ? or cflbmc = ? or cflbmc = ? or cflbmc = ? or cflbmc = ?";
					cflbList = dao.getList(sql, new String[] { "警告", "严重警告",
							"记过", "留校察看", "开除学籍" }, new String[] { "cflbdm",
							"cflbmc" });
				} else {
					sql = "select cflbdm,cflbmc from cflbdmb";
					cflbList = dao.getList(sql, new String[] {}, new String[] {
							"cflbdm", "cflbmc" });
				}
				request.setAttribute("cflbList", cflbList);
				sql = "select cfyydm,cfyymc from cfyydmb";
				List cfyyList = dao.getList(sql, new String[] {}, new String[] {
						"cfyydm", "cfyymc" });

				request.setAttribute("cfyyList", cfyyList);
				request.setAttribute("njList", dao.getNjList());
				String clwh = StandardOperation.getWjcfClwh(xxdm);
				if ("".equalsIgnoreCase(clwh) || clwh == null) {
					request.setAttribute("clwh", "");
				} else {
					request.setAttribute("clwh", clwh);
				}
				if (session == null) {
					request.setAttribute("errMsg", "未知错误,请联系系统管理员!");
					return mapping.findForward("false");
				}
				String userType = session.getAttribute("userType").toString();
				if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					request.setAttribute("writeAble", "yes");
				} else {
					request.setAttribute("writeAble", "no");
				}
				
				if(realTable.equalsIgnoreCase("wjcfb") 
					&&("通过".equalsIgnoreCase(map.get("xysh"))
						|| "通过".equalsIgnoreCase(map.get("xxsh"))
						|| "已确认".equalsIgnoreCase(map.get("xsqr")))){
					request.setAttribute("writeAble", "no");
				}else {
					request.setAttribute("writeAble", "yes");
				}

				// 以下学校对于留校察看的才显示解除时间,文号
				if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)
						&& "留校察看".equalsIgnoreCase(map.get("cflbmc"))) {
					request.setAttribute("zjlgLxck", "yes");
				} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)
						&& "留校察看".equalsIgnoreCase(map.get("cflbmc"))) {
					request.setAttribute("xmlgLxck", "yes");
				} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)
						&& "留校察看".equalsIgnoreCase(map.get("cflbmc"))) {
					request.setAttribute("lxck", "yes");
				}

			} else if (realTable.equalsIgnoreCase("xsbxb")) {
				sql = "select bxgsdm,bxgsmc from bxgsdmb";
				List tbgsdmList = dao.getList(sql, new String[] {},
						new String[] { "bxgsdm", "bxgsmc" });
				request.setAttribute("tbgsdmList", tbgsdmList);
				sql = "select bxxzdm,bxxzmc from bxxzb";
				List tbxzdmList = dao.getList(sql, new String[] {},
						new String[] { "bxxzdm", "bxxzmc" });
				request.setAttribute("tbxzdmList", tbxzdmList);
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
					List bxdcList = dao
							.getList(
									"select dcdm,dcmc from gdnzzy_bxdcdmb order by dcdm",
									new String[] {}, new String[] { "dcdm",
											"dcmc" });
					request.setAttribute("bxdcList", bxdcList);
				}
			}

			else if (realTable.equalsIgnoreCase("xsjxjb")) {
				String userType = "";
				if (session == null) {
					request.setAttribute("errMsg", "sadfsdf");
					return mapping.findForward("false");
				}
				userType = session.getAttribute("userOnLine").toString();

				String jxjdm = request.getParameter("xmdm");
				if (jxjdm != null) {
					sql = "select jxjlb,jlje from jxjdmb where jxjdm=?";
					String[] jxjInfo = dao.getOneRs(sql,
							new String[] { jxjdm }, new String[] { "jxjlb",
									"jlje" });
					if (jxjInfo != null) {
						map.put("jxjlb", jxjInfo[0]);
						map.put("jlje", jxjInfo[1]);
					}
				}
				sql = "select * from jxjdmb";
				List jxjList = dao.getList(sql, new String[] {}, new String[] {
						"jxjdm", "jxjmc" });
				request.setAttribute("jxjList", jxjList);
				sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
						"jxjsqxn", "jxjsqnd" });
				map.put("xn", tmp[0]);
				map.put("nd", tmp[1]);
				map.put("xmdm", jxjdm);
				map.put("stuExists", "yes");
				map.put("userType", userType);
				request.setAttribute("oldjxjdm", map.get("jxjdm"));
			} else if (realTable.equalsIgnoreCase("xshsxfb")) {
				request.setAttribute("yfList", dao.getYfList());
			} else if (realTable.equalsIgnoreCase("zxdk_xsjbxx")) {
				String kh = dao.getOneRs(
						"select kh from view_stu_details where xh=?",
						new String[] { pkValue }, "kh");
				map.put("kh", kh);
			} else if (realTable.equalsIgnoreCase("xsrychb")) {
				HashMap<String, String> stuxx = dao
						.getMapNotOut(
								"select zysj,brjl,hjqk,drzw,byzx,(select b.wysp from xsfzxxb b where a.xh=b.xh) wysp,(select b.lxdh from view_xsxxb b where a.xh=b.xh) lxdh," +
								"(select b.jtszd from xsfzxxb b where a.xh=b.xh) jtszd,(select b.sydqmc from view_xsxxb b where a.xh=b.xh) syd from xsrychxxb a where xh=?",
								new String[] { map.get("xh") });
				map.putAll(stuxx);
				String userType = "";
				if (session == null) {
					request.setAttribute("errMsg", "sadfsdf");
					return mapping.findForward("false");
				}
				userType = session.getAttribute("userOnLine").toString();

				sql = "select * from rychdmb";
				List rychList = dao.getList(sql, new String[] {}, new String[] {
						"rychdm", "rychmc" });
				request.setAttribute("rychList", rychList);
				sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
						"jxjsqxn", "jxjsqnd" });
				map.put("xn", tmp[0]);
				map.put("nd", tmp[1]);
				map.put("stuExists", "yes");
				map.put("userType", userType);
				dataSearchForm.setXmdm(map.get("rychdm"));
			} else if (realTable.equalsIgnoreCase("gwxxb")) {// 勤工助学酬金发放填写详单
				//浙江机电，浙江传媒，需要显示银行信息
				String needYh = "";
				if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)
						|| Globals.XXDM_ZJJDZYJSXY.equalsIgnoreCase(xxdm)) {
					needYh = "yes";
					request.setAttribute("needYh", needYh);
				}
				String cjffsj = dao.getOneRs("select cjffsj from gwsqsjb",
						new String[] {}, "cjffsj");
				// 获取酬金发放年度和月份
				sql = "select xn ,nd, xq, jfglkg from gwsqsjb where rownum=1";
				map.putAll(dao.getMap(sql, new String[] {}, new String[] {
						"xn", "nd", "xq", "jfglkg" }));

				String[] tmp = null;
				sql = "select to_char(sysdate,'yyyy-mm-dd') time from dual";
				tmp = dao.getOneRs(sql, new String[] {},
						new String[] { "time" });
				map.put("yf", tmp[0].substring(5, 7));
				if (cjffsj != null && !"".equalsIgnoreCase(cjffsj)
						&& cjffsj.length() > 5) {
					map.put("nd", cjffsj.substring(0, 4));
					map.put("yf", cjffsj.substring(4, 6));
				}

				sql = "select sqdw,gznr,gwxzmc,decode(jcfs,'h','元/小时','d','元/天','w','元/周','m','元/月','n','元/志愿服务') jcfs,spbcbz,decode(jcfs,'h','小时','d','天','w','周','m','月') gzsjdw, gwsbsj, gwdm, yrdwmc, gwxz,xymc from view_gwxx where "
						+ pk + "='" + pkValue + "'";
				tmp = dao.getOneRs(sql, new String[] {}, new String[] { "sqdw",
						"gznr", "gwxzmc", "jcfs", "spbcbz", "gzsjdw", "gwsbsj",
						"gwdm", "yrdwmc", "gwxz", "xymc" });
				map.put("sqdw", tmp[0]);
				map.put("gznr", tmp[1]);
				map.put("gwxzmc", tmp[2]);
				map.put("jcfs", tmp[3]);
				map.put("jybcbz", tmp[4]);
				map.put("gzsjdw", tmp[5]);
				map.put("gwsbsj", tmp[6]);
				map.put("gwdm", tmp[7]);
				map.put("yrdwmc", tmp[8]);
				map.put("gwxz", tmp[9]);
				map.put("xymc", tmp[10]);
				// 剩余经费=划拨金额+增拨金额-发放金额(划拨金额+增拨金额=hbje)
				sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=? and (gwxzdm=? or gwxzdm is null) ),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and (gwxzmc=? or gwxzmc is null)),0) syjf from dual";
				tmp = dao.getOneRs(sql, new String[] { map.get("nd"),
						map.get("sqdw"), map.get("gwxz"), map.get("nd"),
						map.get("sqdw"), map.get("gwxzmc") },
						new String[] { "syjf" });
				if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
					sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=?),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and yrdwmc=?),0) syjf from dual";
					tmp = dao.getOneRs(sql, new String[] { map.get("nd"),
							map.get("sqdw"), map.get("nd"), map.get("sqdw"),
							map.get("yrdwmc") }, new String[] { "syjf" });
				}

				map.put("syjf", tmp[0]);
				if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
					// 北京联合
					String jcbz = dao.getOneRs(
							"select mxsbc from gwsqsjb where rownum=1",
							new String[] {}, "mxsbc");
					map.put("jybcbz", jcbz);
					request.setAttribute("bjlh", "yes");
				}
				request.setAttribute("workInfo", map);

				// sql = "select * from xscjffb a,xsgwxxb b where a.xh=b.xh and
				// a.gwdm=b.gwdm and a.sqsj=b.gwsbsj and b.gwdm||b.gwsbsj='"
				// + pkValue
				// + "' and a.nd='"
				// + map.get("nd")
				// + "' and a.yf='" + map.get("yf") + "'";

				// List li_lrh = dao.getListNotOut(sql, new String[] {});
				// if (null != li_lrh && 0 != li_lrh.size()) {
				// request.setAttribute("doflag", "modi");
				// } else {
				// request.setAttribute("doflag", "add");
				// }
				if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
					// 北京联合
					// 查询该岗位在岗的需要发放酬金的学生
					sql = "select a.*,b.bz,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh,d.yje cjje,d.ysj gzsj  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
							+ pk
							+ "='"
							+ pkValue
							+ "') and xyyj='通过' and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
							+ " and b.nd='"
							+ map.get("nd")
							+ "' and b.yf='"
							+ map.get("yf")
							+ "' left join khxgsqb c on c.xh=a.xh　left join (select xh,ysj,yje,time from xskhyb where sftj = 'yes') d on a.xh = d.xh and d.time = '"
							+ map.get("nd") + map.get("yf") + "' ";

				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
					// 中国地质大学
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
							+ pk
							+ "='"
							+ pkValue
							+ "') and xyyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
							+ " and b.nd='"
							+ map.get("nd")
							+ "' and b.yf='"
							+ map.get("yf")
							+ "' left join khxgsqb c on c.xh=b.xh";
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
					// 浙江机电职业技术学院
					String cjffY = "";
					String cjffM = "";
					if (cjffsj != null && cjffsj.length() == 6) {
						cjffY = cjffsj.substring(0, 4);
						cjffM = cjffsj.substring(4, 6);
					} else {
						cjffY = Base.currNd;
						cjffM = GetTime.getSystemTime().substring(5, 7);
					}
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,"
							+ "(select v.yhmc from view_xsxxb v where a.xh = v.xh) yhmc,"
							+ "(select v.yhkh from view_xsxxb v where a.xh = v.xh) yhkh,"
							+ "(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  "
							+ "from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from "
							+ "view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
							+ pk
							+ "='"
							+ pkValue
							+ "') and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
							+ " and b.nd='" + cjffY + "' and b.yf='" + cjffM
							+ "' left join khxgsqb c on c.xh=b.xh";
					request.setAttribute("cjffY", cjffY);
					request.setAttribute("cjffM", cjffM);
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
					// 宁波理工学院
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
							+ pk
							+ "='"
							+ pkValue
							+ "') and xyyj='通过' and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
							+ " and b.nd='"
							+ map.get("nd")
							+ "' and b.yf='"
							+ map.get("yf")
							+ "' left join khxgsqb c on c.xh=b.xh";
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
					// 浙江理工大学
					sql = "select a.*,decode(b.gzsj,'',(select ygzsj from view_qgzx_xsgzkhb c where a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj and a.xh=c.xh and c.nd='"
							+ map.get("nd")
							+ "' and c.yf='"
							+ map.get("yf")
							+ "'),b.gzsj)gzsj,decode(b.cjje,'',(select ffcjje from view_qgzx_xsgzkhb c where a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj and a.xh=c.xh and c.nd='"
							+ map.get("nd")
							+ "' and c.yf='"
							+ map.get("yf")
							+ "'),b.cjje)cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
							+ pk
							+ "='"
							+ pkValue
							+ "') and xyyj='通过' and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
							+ " and b.nd='"
							+ map.get("nd")
							+ "' and b.yf='"
							+ map.get("yf")
							+ "' left join khxgsqb c on c.xh=b.xh";
				} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
					// 广州大学
					sql = "select a.*,b.gzsj,(select ygzsj from view_qgzx_xsgzkhb c where c.nd='"
							+ map.get("nd")
							+ "' and c.yf='"
							+ map.get("yf")
							+ "' and a.xh=c.xh and a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) khgzsj,"
							+ "b.cjje,(select ffcjje from view_qgzx_xsgzkhb c where c.nd='"
							+ map.get("nd")
							+ "' and c.yf='"
							+ map.get("yf")
							+ "' and a.xh=c.xh and a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) khcjje,b.bz,b.khqk,b.xxsh,b.gzpj ,(select kh from view_xsxxb c where a.xh=c.xh) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
							+ pk
							+ "='"
							+ pkValue
							+ "') and sfyx='有效') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
							+ " and b.nd='"
							+ map.get("nd")
							+ "' and b.yf='"
							+ map.get("yf") + "'";
				} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					// 浙江传媒学院
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,nvl(b.yhkh,(select yhkh from xsxxb c where a.xh=c.xh)) kh,nvl(b.yhmc,(select yhmc from view_xsxxb c where a.xh=c.xh))yhmc  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
							+ pk
							+ "='"
							+ pkValue
							+ "') and xyyj='通过' and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
							+ " and b.nd='"
							+ map.get("nd")
							+ "' and b.yf='"
							+ map.get("yf") + "'";
				} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
					// 常州信息职业技术学院 离职的学生不发放酬金
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where (lzsj is null or (lzsj is not null and to_number(substr(lzsj,1,4)||substr(lzsj,6,2)||substr(lzsj,9,2))>to_char(sysdate,'yyyymmdd'))) and gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
							+ pk
							+ "='"
							+ pkValue
							+ "') and xyyj='通过' and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
							+ " and b.nd='"
							+ map.get("nd")
							+ "' and b.yf='"
							+ map.get("yf")
							+ "' left join khxgsqb c on c.xh=b.xh";
				} else {
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
							+ pk
							+ "='"
							+ pkValue
							+ "') and xyyj='通过' and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
							+ " and b.nd='"
							+ map.get("nd")
							+ "' and b.yf='"
							+ map.get("yf")
							+ "' left join khxgsqb c on c.xh=b.xh";
				}
				System.out.println(sql);
				ArrayList<HashMap<String, String>> ffList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> li_lrh2 = dao.getListNotOut(sql,
						new String[] {});
				for (int i = 0; i < li_lrh2.size(); i++) {
					HashMap<String, String> map1 = new HashMap<String, String>();
					map1 = li_lrh2.get(i);
					ffList.add(map1);
				}

				// 岗位调整后，发放学生在调整前的岗位工资
				QgzxService service = new QgzxService();
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
					// 浙江理工
					ffList.addAll(service.queryTzqxsgw(ffList, pkValue, map
							.get("nd"), map.get("yf")));
				} else {
					ffList.addAll(service.queryTzqxsgw(ffList, pk, pkValue));
				}

				request.setAttribute("count", Integer.toString(ffList.size()));
				request.setAttribute("ffList", ffList);// 岗位下的所有学生信息列表
				return new ActionForward("/sjcz/work_pay_details.jsp", false);
			} else if (realTable.equalsIgnoreCase("sjb")) {
				request.setAttribute("ynList", dao.getChkList(2));
				if (request.getParameter("buttonxsbj").equalsIgnoreCase(
						"viewOnly")) {
					realTable = "sjb_view";
				}
			} else if (realTable.equalsIgnoreCase("stb")) {
				sql = "select stlxdm,stlxmc from stlxdmb";
				List stlxList = dao.getList(sql, new String[] {}, new String[] {
						"stlxdm", "stlxmc" });
				request.setAttribute("stlxList", stlxList);
				sql = "select stndjbdm,stndjbmc from stndjbdmb";
				List stndjbList = dao.getList(sql, new String[] {},
						new String[] { "stndjbdm", "stndjbmc" });
				request.setAttribute("stndjbList", stndjbList);
				request.setAttribute("ynList", dao.getChkList(2));
			} else if (realTable.equalsIgnoreCase("sjstb")) {
				String sjlsh = request.getParameter("sjlsh");
				if (sjlsh != null) {
					sql = "select sjlsh,sjm from sjb where sjlsh=?";
					String[] sjInfo = dao.getOneRs(sql, new String[] { sjlsh },
							new String[] { "sjlsh", "sjm" });
					if (sjInfo != null) {
						map.put("sjlsh", sjInfo[0]);
						map.put("sjm", sjInfo[1]);
					}
				}
				sql = "select a.stlsh,a.stlsh||'------------'||rownum stlshstxh from (select * from sjstb a where sjlsh=? order by stxh asc) a";
				List stList = dao.getList(sql, new String[] { sjlsh },
						new String[] { "stlsh", "stlshstxh" });
				request.setAttribute("stList", stList);
			} else if (realTable.equalsIgnoreCase("xsbzxxb")) {
				sql = "select bzlxdm,bzlxmc from bzlxdmb";
				List bzlxList = dao.getList(sql, new String[] {}, new String[] {
						"bzlxdm", "bzlxmc" });
				request.setAttribute("bzlxList", bzlxList);
			} else if (realTable.equalsIgnoreCase("xsgbxxb")) {
				sql = "select rzbmdm,rzbmmc from rzbmdmb";
				List rzbmList = dao.getList(sql, new String[] {}, new String[] {
						"rzbmdm", "rzbmmc" });
				request.setAttribute("rzbmList", rzbmList);
				request.setAttribute("chkList", dao.getChkList(3));
			} else if (realTable.equalsIgnoreCase("sthdxxb")) {
				sql = "select stdm,stmc from stdmb";
				List stList = dao.getList(sql, new String[] {}, new String[] {
						"stdm", "stmc" });
				request.setAttribute("stList", stList);
			} else if (realTable.equalsIgnoreCase("ssxxb")) {
				String lddm = map.get("lddm");
				String userName = session.getAttribute("userName").toString();
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
					userName = StringUtils.isNull(userName) ? "" : userName;
					request.setAttribute("showdzdx", "yes");
					request.setAttribute("fpbjList", gyglDao.getQsLxList(2));
					sql = "select lddm,(select yqmc from yqdmb b where a.yqdm=b.yqdm)||ldmc ldmc from sslddmb a order by lddm";
				} else {
					sql = "select lddm,ldmc from sslddmb order by lddm";
					request.setAttribute("fpbjList", gyglDao.getQsLxList(1));
				}
				List ldList = dao.getList(sql, new String[] {}, new String[] {
						"lddm", "ldmc" });
				List csList = GyglShareDAO.getLcList2(lddm);
				// 公寓辅导员判断加载负责楼栋列表begin
				// List listTem = gyglDao.getLddmxXx(userName,xqdm,yqdm);
				if (gyglDao.isGyFdy(userName)) {
					gyglDao.reLoadLb(request);
				} else {
					request.setAttribute("ldList", ldList);
					request.setAttribute("csList", csList);// 楼层列表
				}
				// 公寓辅导员判断end

			} else if (realTable.equalsIgnoreCase("xszsxxb")) {
				YnysQszService qszService=new YnysQszService();
				String sslx = DealString.toGBK(dataSearchForm.getSslx());// 宿舍类型
				String ssbh = dataSearchForm.getSsbh();
				if(Base.isNull(ssbh)){
					ssbh=request.getParameter("ssbh");
					System.out.println(ssbh);
				}
				xh = dataSearchForm.getXh();
				String xb = "";
				String xydm = "";
				String nj = "";
				String bjdm = "";
				String cwh = "";
				// String[] xsRzIf = null;
				String userDep = session.getAttribute("userDep").toString();
				String userType = dao.getUserType(userDep);
				String userName = session.getAttribute("userName").toString();
				StringBuffer sqlT = new StringBuffer();
				// if(Base.isNull(sslx)){
				// sslx = "yfpss";//已划分学院宿舍
				// }
				//寝室长
				request.setAttribute("qsz", qszService.getQsz(xh));
				if ("xy".equalsIgnoreCase(userType)) {
					sslx = "yfpss";
				}
				StringBuffer querry = new StringBuffer();
				StringBuffer querryT = new StringBuffer();
				boolean isSss = GyglShareDAO.isSssAdmin(userName);// 研究生用户识别
				String tabname = "";

				if (xh != null && !xh.equalsIgnoreCase("")) {
					if (isSss) {
						tabname = "sss_xxb";
					} else {
						tabname = "view_xsjbxx";
					}
					sql = " select xh,xm,xb,nj,xymc,zymc,bjmc,xydm,bjdm from "
							+ tabname + " where xh = ? ";
					String[] outPut = { "xh", "xm", "xb", "nj", "xymc", "zymc",
							"bjmc", "xydm", "bjdm" };
					String[] outValue = dao.getOneRs(sql, new String[] { xh },
							outPut);
					for (int i = 0; i < outPut.length; i++) {
						map.put(outPut[i], outValue[i]);
					}
				}
				xh = map.get("xh");
				xb = map.get("xb");// 性别
				xydm = map.get("xydm");// 学院代码
				nj = map.get("nj");// 年级
				bjdm = map.get("bjdm");// 班级代码

				List<HashMap<String, String>> cwhList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> ssList = new ArrayList<HashMap<String, String>>();
				List temCwhList = null;

				if (!Base.isNull(doType)
						&& (doType.equalsIgnoreCase("modi") || doType
								.equalsIgnoreCase("view"))) {
					if (!Base.isNull(pkValue)) {// 修改、查看学生住宿信息单个页面
						sql = " select ssbh from xszsxxb where " + pk + "=?";
						if(Base.isNull(ssbh)){
						ssbh = dao.getOneRs(sql, new String[] { pkValue },
								"ssbh");
						}
						if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {// 中国地质大学(分本科、硕士、博士)
							if (isSss) {
								sql = " select count(a.xydm) cout from ssfpb a,view_ssszsxx b where a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj and b.xh=? ";
							} else {
								sql = " select count(a.xydm) cout from ssfpb a,view_xszsxx b where a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj and b.xh=? ";
							}
						} else {
							sql = " select count(a.xydm) cout from ssfpb a,view_xszsxx b where a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj and b.xh=? ";
						}
						if (dao.getOneRs(sql, new String[] { pkValue }, "cout")
								.equalsIgnoreCase("0")) {// 判断该生所住宿舍是否是未划分学院宿舍
							sslx = "wfpss";
						} else {
							sslx = "yfpss";
						}
					}
				}
				// 生成宿舍列表begin
				querry.append((Base.isNull(xb)) ? "" : " and (xbxd = '" + xb
						+ "' or xbxd='混合') ");// 查询宿舍信息条件
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {// 中国地质大学
					if (isSss) {
						if (sslx.equalsIgnoreCase("wfpss")) {// 未划分学院（空闲）宿舍
							sql = " select distinct ssbh dm,yqmc||ldmc||qsh mc from view_ssxx  a where (fpbj = '硕士' or fpbj = '博士') and not exists (select * from ssfpb b where a.ssbh=b.ssbh ) "
									+ querry + " order by ssbh ";
						} else {// 已划分学院宿舍
							sql = "select distinct a.ssbh dm,b.yqmc||b.ldmc||b.qsh mc from ssfpb a,view_ssxx b where (fpbj = '硕士' or fpbj = '博士') and a.ssbh=b.ssbh "
									+ querry + " order by a.ssbh ";
						}
						request.setAttribute("xslx", "sss");
					} else {
						if (sslx.equalsIgnoreCase("wfpss")) {// 未划分学院（空闲）宿舍
							sql = " select distinct ssbh dm,yqmc||ldmc||qsh mc from view_ssxx  a where fpbj = '一般' and not exists (select * from ssfpb b where a.ssbh=b.ssbh ) "
									+ querry + " order by ssbh ";
						} else {// 已划分学院宿舍
							querry.append((Base.isNull(nj)) ? "  "
									: " and nj='" + nj + "' ");
							querry.append((Base.isNull(xydm)) ? "  "
									: " and xydm='" + xydm + "' ");
							sql = "select distinct a.ssbh dm,b.yqmc||b.ldmc||b.qsh mc from ssfpb a,view_ssxx b where fpbj='一般' and a.ssbh=b.ssbh "
									+ querry + " order by a.ssbh ";
						}
					}
				} else {
					if (sslx.equalsIgnoreCase("wfpss")) {// 未划分学院（空闲）宿舍
						// if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//杭州职业技术学院
						// sql = "select distinct a.ssbh dm,a.ldmc||a.qsh mc
						// from
						// view_ssxx a where fpbj='一般' and not exists( "
						// +"select b.ssbh ,b.cout from (select ssbh
						// ,count(*)cout
						// from ssfpb b group by ssbh )b where a.ssbh=b.ssbh"
						// +" and a.cws=b.cout ) "+querry+" order by a.ssbh ";
						// }else{
						sql = " select distinct a.ssbh dm,a.ldmc||'/'||a.qsh mc from view_ssxx a where fpbj='一般' and not exists ("
								+ " select b.ssbh,b.cwssum from (select ssbh,sum(cws) cwssum from ssfpb group by ssbh )b where a.ssbh=b.ssbh and a.cws=b.cwssum "
								+ " ) " + querry + " order by a.ssbh";
						// }
					} else {// 已划分学院宿舍
						if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// 杭州职业技术学院
							querry.append((Base.isNull(bjdm)) ? " and 1=2 "
									: " and bjdm='" + bjdm + "' ");
							sql = "select distinct a.ssbh dm,a.ldmc||a.qsh mc from view_ssxx a,ssfpb b where a.ssbh=b.ssbh and a.fpbj='一般' "
									+ querry;
						} else {
							querry.append((Base.isNull(nj)) ? "" : " and nj='"
									+ nj + "' ");
							querry.append((Base.isNull(xydm) ? " and 1=2 "
									: " and xydm='" + xydm + "' "));
							sql = " select distinct a.ssbh dm,a.ldmc||a.qsh mc from view_ssxx a,ssfpb b where a.ssbh=b.ssbh and fpbj = '一般' "
									+ querry + " order by a.ssbh ";
						}
						
						if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
							//TODO 住宿纪律
							qszService.getZsjlList(request,xh);
							//卫生检查
							qszService.getWsjcList(request,xh);
							request.setAttribute("showzjcmxy", "showzjcmxy");
						}
					}
				}
				ssList = dao.getList(sql, new String[] {}, new String[] { "dm",
						"mc" });
				request.setAttribute("ssList", ssList);
				// //生成宿舍列表end

				// 生成床位列表begin
				if (sslx.equalsIgnoreCase("yfpss")) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {// 中国地质大学(分本科、硕士、博士)
						querryT.append((Base.isNull(ssbh)) ? " and 1=2 "
								: " and a.ssbh='" + ssbh + "' ");
						//辽宁机电职业技术学院 床位号存在中文，个性化修改
						String sb1 = "";
						if("12898".equals(Base.xxdm)){
							sb1 = "a.cwh";
						}else{
							sb1 = "to_number(a.cwh)";
						}
						sqlT
								.append("select a.cwh dm,a.cwh mc from cwxxb a,ssfpb b  where a.ssbh=b.ssbh and a.ssbh||a.cwh not in (select distinct ssbh||cwh from (select ssbh,cwh from xszsxxb union  select ssbh,cwh from wxs_xszsxxb) b where a.ssbh=b.ssbh) "
										+ querryT
										+ " order by "+sb1+" ");
						cwhList = dao.getList(sqlT.toString(), new String[] {},
								new String[] { "dm", "mc" });
					} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// 杭州职业技术学院
						querryT.append((Base.isNull(bjdm)) ? " and 1=1 "
								: " and a.bjdm='" + bjdm + "' ");
						querryT.append((Base.isNull(ssbh)) ? " and 1=2 "
								: " and a.ssbh='" + ssbh + "' ");
						querryT.append(" order by dm ");
						sqlT
								.append(" select cwh dm,cwh mc from ssfpb a,view_ssxx b where ");
						sqlT
								.append(" a.ssbh=b.ssbh and not exists (select distinct ssbh,cwh from(select ssbh,cwh from xszsxxb union select ssbh, cwh from wxs_xszsxxb) b where a.ssbh=b.ssbh and a.cwh=b.cwh) "
										+ querryT);
						cwhList = dao.getList(sqlT.toString(), new String[] {},
								new String[] { "dm", "mc" });
					} else {
						querryT.append(Base.isNull(xydm) ? " and 1=2 "
								: "  and a.xydm='" + xydm + "'");
						querryT.append(Base.isNull(nj) ? " and 1=2 "
								: "  and a.nj='" + nj + "'");
						querryT.append((Base.isNull(ssbh)) ? " and 1=2 "
								: " and a.ssbh='" + ssbh + "' ");
						sqlT
								.append(" select dm,mc from(select c.cwh dm,c.cwh mc,");
						sqlT
								.append(" to_number(b.cws) - to_number((select count(ssbh) from xszsxxb b where a.ssbh = b.ssbh)) leaveCws,");
						sqlT
								.append(" (select count(ssbh)from view_xszsxx b where a.ssbh = b.ssbh and a.nj=b.nj and a.xydm=b.xydm)rzs,");
						//辽宁机电职业技术学院 床位号存在中文，个性化修改
						String sb1 = "";
						if("12898".equals(Base.xxdm)){
							sb1 = "c.cwh";
						}else{
							sb1 = "to_number(c.cwh)";
						}
						sqlT
								.append(" row_number() over( partition by a.ssbh order by a.ssbh,"+sb1+") px,a.cws fps  from ssfpb a,view_ssxx b,");
						sqlT
								.append(" cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh");
						sqlT.append(querryT.toString());
						sqlT
								.append(" and a.ssbh||c.cwh not in (select distinct ssbh||cwh from xszsxxb) and a.ssbh || c.cwh not in ( select distinct ssbh || cwh from wxs_xszsxxb) ");
						sqlT.append(" )a where px <=fps-rzs ");
						cwhList = dao.getList(sqlT.toString(), new String[] {},
								new String[] { "dm", "mc" });
					}
				} else if (sslx.equalsIgnoreCase("wfpss")) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {// 中国地质大学(分本科、硕士、博士)
						querryT.append((Base.isNull(ssbh)) ? " and 1=2 "
								: " and a.ssbh='" + ssbh + "' ");
						//辽宁机电职业技术学院 床位号存在中文，个性化修改
						String sb2 = "";
						if("12898".equals(Base.xxdm)){
							sb2 = "a.cwh";
						}else{
							sb2 = "to_number(a.cwh)";
						}
						sqlT
								.append(" select a.cwh dm,a.cwh mc from cwxxb a  where a.ssbh||a.cwh  not in ( select distinct ssbh||cwh from (select ssbh,cwh from xszsxxb union select ssbh,cwh from wxs_xszsxxb ) b where a.ssbh=b.ssbh) "
										+ querryT
										+ " order by "+sb2+" ");
						cwhList = dao.getList(sqlT.toString(), new String[] {},
								new String[] { "dm", "mc" });
					} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// 杭州职业技术学院

						querryT.append((Base.isNull(ssbh)) ? " and 1=2 "
								: " and a.ssbh='" + ssbh + "' ");
						querryT.append(" order by dm ");

						sqlT
								.append(" select cwh dm,cwh mc from cwxxb a where  not exists (select distinct ssbh ,cwh from ( select distinct ssbh,cwh from view_xszsxx ");
						sqlT
								.append(" union all select distinct ssbh, cwh from wxs_xszsxxb) b where a.ssbh=b.ssbh and a.cwh=b.cwh  )"
										+ querryT);
						cwhList = dao.getList(sqlT.toString(), new String[] {},
								new String[] { "dm", "mc" });

						// sqlT = new StringBuffer();
						// sqlT.append(" select distinct cwh dm,cwh mc from
						// xszsxxb a where xh=? ");
						// temCwhList = dao.getList(sqlT.toString(), new
						// String[] {pkValue}, new String[] {"dm", "mc" });
						// cwhList.addAll(temCwhList);
					} else {
						//辽宁机电职业技术学院 床位号存在中文，个性化修改
						String sb = "";
						if("12898".equals(Base.xxdm)){
							sb = "cwh";
						}else{
							sb = "to_number(cwh)";
						}
						sqlT
								.append(" select cwh dm,cwh mc from cwxxb a where ssbh=? and not exists(select distinct ssbh,cwh from(select ssbh,cwh from xszsxxb union select ssbh,cwh from wxs_xszsxxb) b where a.ssbh||a.cwh=b.ssbh||b.cwh) order by "+sb+" ");
						System.out.println(sqlT);
						cwhList = dao.getList(sqlT.toString(),
								new String[] { ssbh }, new String[] { "dm",
										"mc" });
						// querryT.append(Base.isNull(xydm)?" and 1=1 ":" and
						// a.xydm='" + xydm + "'");
						// querryT.append(Base.isNull(nj)?" and 1=1 ":" and
						// a.nj='" + nj + "'");
						// querryT.append((Base.isNull(ssbh))?" and 1=2 ":" and
						// a.ssbh='"+ssbh+"' ");
						// querryT.append(" order by dm");
						sqlT = new StringBuffer();
						// sqlT.append("select dm,mc from (select c.cwh dm,c.cwh
						// mc, ");
						// sqlT.append("a.ssbh,a.nj,a.xydm from ssfpb a,
						// view_ssxx b, cwxxb c where a.ssbh = b.ssbh and a.ssbh
						// = c.ssbh and a.cws=b.cws ");
						// sqlT.append("and a.ssbh||c.cwh not in (select
						// distinct ssbh||cwh from xszsxxb) union select a.cwh
						// en, ");
						// sqlT.append("a.cwh cn,a.ssbh ,a.nj,a.xydm from (
						// select a.ssbh,cwh,xydm,nj from ( ");
						// sqlT.append("select a.ssbh, rank() over ( partition
						// by a.ssbh,xydm,nj order by to_number(a.cwh) )
						// px,a.cwh,cws,countcwh,xydm,nj from ( ");
						// sqlT.append("select ssbh,cws,cwh,countcwh,xydm,nj
						// from (select distinct a.ssbh, c.cwh,
						// a.cws,countcwh,d.xydm,d.nj from ssfpb a,cwxxb c, ");
						// sqlT.append("(select ssbh,countcwh,xydm,nj from
						// (select distinct a.ssbh,count(b.cwh)
						// countcwh,a.xydm,a.nj from (select a.ssbh,a.xydm,a.nj
						// ");
						// sqlT.append("from ssfpb a,view_ssxx b where a.cws <>
						// b.cws and a.ssbh=b.ssbh ) a left join (select
						// distinct a.ssbh,a.cwh,b.nj,b.xydm ");
						// sqlT.append("from xszsxxb a,view_xsjbxx b where
						// a.xh=b.xh ) b on a.ssbh=b.ssbh and a.xydm=b.xydm and
						// a.nj=b.nj group by a.ssbh,a.xydm,a.nj ");
						// sqlT.append(")) d where a.ssbh = c.ssbh and a.ssbh =
						// d.ssbh and a.ssbh || c.cwh not in (select ssbh || cwh
						// from xszsxxb) and d.countcwh<>a.cws ");
						// sqlT.append("and a.xydm=d.xydm and a.nj=d.nj )) a )a
						// where px between 1 and a.cws-a.countcwh ) a,view_ssxx
						// b where a.ssbh = b.ssbh )a, ");
						// sqlT.append("view_ssxx b,ssfpb c where a.ssbh =
						// b.ssbh and a.ssbh = c.ssbh and c.xydm=a.xydm and
						// c.nj=a.nj ");
						// sqlT.append(querryT);
						sqlT
								.append("select cwh dm,cwh mc,ssbh from (select "+sb+" cwh,ssbh from cwxxb c where exists( select 1 from view_xszsxx d where c.ssbh=d.ssbh and c.cwh=d.cwh )   order by cwh ) a ");
						sqlT
								.append("where ssbh = ? and rownum <=( select nvl(sum(cws),0) from ssfpb b where b.ssbh=a.ssbh)");
						temCwhList = dao.getList(sqlT.toString(),
								new String[] { ssbh }, new String[] { "dm",
										"mc" });
						cwhList.removeAll(temCwhList);
					}
				}
				request.setAttribute("cwhList", cwhList);// 床位号列表
				// 生成床位列表end

				sql = " select cwh from xszsxxb where ssbh = ? and " + pk
						+ "=?";
				cwh = dao.getOneRs(sql, new String[] { ssbh, pkValue }, "cwh");
				if (!Base.isNull(cwh)) {
					HashMap<String, String> mymap = new HashMap<String, String>();
					mymap.put("dm", cwh);
					mymap.put("mc", cwh);
					cwhList.add(mymap);
				}

				map.put("sslx", sslx);// 宿舍类型
				map.put("ssbh", ssbh);// 宿舍编号
				map.put("sfbz", dao.getOneRs(
						"select sfbz from view_ssxx where ssbh=? ",
						new String[] { ssbh }, "sfbz"));
				if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)
						|| Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {
					sql = "select nvl(b.lz,'暂无')lz, nvl(a.cz,'暂无')cz from (select (select xm||'/'||xh||'/'||bjmc from view_xsjbxx d where d.xh=b.cz and rownum=1) cz,";
					sql += "c.lddm from czxxb b,ssxxb c where b.lddm=c.lddm and sfzz='是' and c.ssbh=? and rownum=1) a ";
					sql += "left join (select (select xm||'/'||xh||'/'||bjmc from view_xsjbxx d where d.xh=b.lz and rownum=1) lz,c.lddm from ";
					sql += "lzxxb b,ssxxb c where b.lddm=c.lddm and sfzz='是' and c.ssbh=? and rownum=1)b on a.lddm=b.lddm";
					request.setAttribute("rsLzCz", dao.getMap(sql,
							new String[] { ssbh, ssbh }, new String[] { "lz",
									"cz" }));
					request.setAttribute("rslxr", dao.getMap(
							"select xm||'/'||xh||'/'||bjmc lxr from view_zrqfzrxx where fzssbh like '%"
									+ ssbh + "%' and sfzz='是' and rownum=1 ",
							new String[] {}, new String[] { "lxr" }));
					request.setAttribute("showlczrr", "showlczrr");
					sql = " select max(ltrim(sys_connect_by_path(xm||'('||'电话：'||(select lxdh  from gygl_lzxxb b where a.yhm=b.yhm and rownum=1 )||')','、'),'、')) cy from (";
					sql += "	(select lddm,yhm,xm, row_number() over (partition by lddm order by lddm,yhm,xm) pno,row_number() over (partition by lddm order by lddm,yhm,xm)-1 sno";
					sql += "	from gygl_lzxxb where xn=? and xq=? and lddm=(select lddm from view_ssxx b where b.ssbh=? and rownum=1 )group by lddm,yhm,xm)a";
					sql += " ) start with pno=1 connect by prior pno=sno and prior lddm=lddm  ";
					request.setAttribute("gyfdyxx", dao.getMap(sql,
							new String[] { Base.currXn, Base.currXq, ssbh },
							new String[] { "cy" }));
				}
				if (Globals.XXDM_XCXY.equals(xxdm)) {// 西昌学院
					String xsnj = map.get("nj");
					sql = "select rzsj from njrzsj where nj=?";
					String rzsj = dao.getOneRs(sql, new String[] { xsnj },
							"rzsj");
					request.setAttribute("rzsj", rzsj != null ? rzsj : "");
				}
				request.setAttribute("doType", doType);
				request.setAttribute("userType", userType);

				if (Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)
						&& "view".equalsIgnoreCase(doType)) {// 金华职业技术学院
					sql = "select * from (select distinct xn,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,xq,wsdj,jcrq from jhzy_qswsjcb a where ssbh=?) order by xn,xq";
					request.setAttribute("rsOtherInfo", "info");
					request.setAttribute("rsWsjc", dao.rsToVator(sql,
							new String[] { ssbh }, new String[] { "xn", "xqmc",
									"wsdj", "jcrq" }));
					sql = "select xn,xqmc,dj from  view_xjqsxx where fdysh='通过'and xysh='通过' and xxsh='通过' and ssbh=? order by xn,xq";
					request.setAttribute("rsXjwmqs", dao.rsToVator(sql,
							new String[] { ssbh }, new String[] { "xn", "xqmc",
									"dj" }));
					sql = "select xn,xqmc,wjlbmc,yjfs,wjsj,ldmc||qsh zz from view_zsjlxx where xh=? order by xn,xq,wjsj";
					request.setAttribute("rsZsjl", dao.rsToVator(sql,
							new String[] { xh }, new String[] { "xn", "xqmc",
									"wjlbmc", "yjfs", "wjsj", "zz" }));
					sql = "select xm from jhzy_gyfdyb where ssbh=? and xn=? and xq=? ";
					request.setAttribute("rsGyFdy", dao.getMap(sql,
							new String[] { ssbh, Base.currXn, Base.currXq },
							new String[] { "xm" }));
					sql = "select (select lz||'/'||xm from view_lzxx a,ssxxb b where a.lddm=b.lddm and b.ssbh=? and sfzz='是'and rownum=1 )lz, ";
					sql += "(select cz||'/'||xm from view_czxx a,ssxxb b where a.lddm=b.lddm and a.lc=b.cs and b.ssbh=?  and sfzz='是'and rownum=1)cz ,";
					sql += "(select qsz||'/'||xm from view_qszxx where ssbh=? and sfzz='是'and rownum=1)qsz from dual";
					request.setAttribute("rsLcqsz", dao.getMap(sql,
							new String[] { ssbh, ssbh, ssbh }, new String[] {
									"lz", "cz", "qsz" }));

				}
				// map.put("picture",
				// PictureUtil.getPicPath(request.getSession().getServletContext().getRealPath("").toString(),new
				// String[]{xh},null));

			} else if (realTable.equalsIgnoreCase("mrzbjlb")
					|| realTable.equalsIgnoreCase("yzzbhzb")) {
				sql = "select lddm,ldmc from sslddmb order by  lddm ";
				List ldList = dao.getList(sql, new String[] {}, new String[] {
						"lddm", "ldmc" });
				request.setAttribute("ldList", ldList);
				sql = "select zbrydm,zbrymc from zbrydmb";
				List zbryList = dao.getList(sql, new String[] {}, new String[] {
						"zbrydm", "zbrymc" });
				request.setAttribute("zbryList", zbryList);
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
					request.setAttribute("syzy", true);
					sql = "select dm,xqmc from dm_zju_xq";
					List xiaoqquList = dao.getList(sql, new String[] {},
							new String[] { "dm", "xqmc" });
					request.setAttribute("xiaoqquList", xiaoqquList);
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					List zbrLxList = dao.getChkList(16);// 值班人员类型
					request.setAttribute("zbrLxList", zbrLxList);
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)) {// 浙江经济职业技术学院
					request.setAttribute("showZjjj", "showZjjj");
					request.setAttribute("bmList", dao.getBmList());
				}
			} else if (realTable.equalsIgnoreCase("xszffb")
					|| realTable.equalsIgnoreCase("hcyhkffb")
					|| realTable.equalsIgnoreCase("xhffb")
					|| realTable.equalsIgnoreCase("xszbbb")
					|| realTable.equalsIgnoreCase("hcyhkbbb")
					|| realTable.equalsIgnoreCase("yktbbb")
					|| realTable.equalsIgnoreCase("xhbbb")
					|| realTable.equalsIgnoreCase("hcyhkczb")) {
				sql = "select jbrgh,jbrxm from jbrxxb order by jbrgh"; // 经办人信息表
				List jbrList = dao.getList(sql, new String[] {}, new String[] {
						"jbrgh", "jbrxm" });
				request.setAttribute("jbrList", jbrList);
				request.setAttribute("ynList", dao.getChkList(2));
				request.setAttribute("sqsjnyr", GetTime.getTimeByFormat("yyyymmdd"));
				if (!"xhffb".equalsIgnoreCase(realTable)
						&& !"xhbbb".equalsIgnoreCase(realTable)) {
					map.put("xn", dqndq[0]);// xn
					map.put("xq", dqndq[1]);// xq
					map.put("nd", dqndq[2]);// nd
				}
					
			} else if (realTable.equalsIgnoreCase("hcccb")) {
				sql = "select czdm,czmc from czdmb";
				List czList = dao.getList(sql, new String[] {}, new String[] {
						"czdm", "czmc" });
				request.setAttribute("czList", czList);
			} else if (realTable.equalsIgnoreCase("bks_xsszjbxx")) {
				map = new HashMap<String, String>();
				xh = request.getParameter("pkValue");
				sql = "select * from view_xslxfszsxx where xh=?";
				String[] list = new String[] { "xh", "xm", "xymc", "zymc",
						"bjmc", "lxdh1", "sjhm", "email", "ssbh", "cwh" };
				String[] xsxx = dao.getOneRs(sql, new String[] { xh }, list);
				for (int i = 0; i < xsxx.length; i++) {
					if (!(xsxx[i] == null)) {
						map.put(list[i].toLowerCase(), xsxx[i].toString());
					} else
						map.put(list[i].toLowerCase(), "");
				}
				String userType = session.getAttribute("userType").toString();

				request.setAttribute("rs", map);
				request.setAttribute("userType", userType);
				request.setAttribute("tableName", tableName);
				request.setAttribute("realTable", realTable);
				request.setAttribute("tips", "学生信息 - 学生信息维护 - 单个学生信息维护");

			} else if (realTable.equalsIgnoreCase("qcccb")) {
				sql = "select czdm,czmc from qczdmb";
				List czList = dao.getList(sql, new String[] {}, new String[] {
						"czdm", "czmc" });
				request.setAttribute("czList", czList);
			} else if (realTable.equalsIgnoreCase("xszxbzb")) {

				String userType = session.getAttribute("userType").toString();
				request.setAttribute("userType", userType);
				request.setAttribute("chkList", dao.getChkList(3));

				sql = "select * from view_zxbzxx ";
				String[] outString = dao.getColumnName(sql + " where 1=2 ");
				String[] outValue = dao.getOneRs(sql + " where " + pk + " =? ",
						new String[] { pkValue }, outString);

				if (outValue != null) {
					for (int i = 0; i < outString.length; i++) {
						if (outValue[i] != null) {
							map.put(outString[i].toLowerCase(), outValue[i]);
						} else {
							map.put(outString[i].toLowerCase(), "");
						}
					}
				}

			} else if (realTable.equalsIgnoreCase("xsgwxxb")) {
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb";
				List gwList = dao.getList(sql, new String[] {}, new String[] {
						"gwdm", "gwdmgwsbsj" });
				request.setAttribute("gwList", gwList);
			} else if (realTable.equalsIgnoreCase("lstdb")) {
			} else if (realTable.equalsIgnoreCase("kkqkb")) {
				if (doType == "add") {
					map.put("xn", dqndq[0]);// xn
					map.put("xq", dqndq[1]);// xq
					map.put("nd", dqndq[2]);// nd
				}
				List kcList = null;
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)) {
					String xn = dataSearchForm.getXn();
					String xq = dataSearchForm.getXq();
					String nd = dataSearchForm.getNd();
					
					String cg = DealString.toGBK(request.getParameter("chag"));
					String bjdm = dao.getOneRs(
							"select bjdm from view_xsjbxx where xh=?",
							new String[] { xh }, "bjdm");
					sql = "select * from view_xsjbxx where xh=?";
					colList = dao
							.getColumnName("select * from view_xsjbxx where 1=2");
					String[] rs = dao.getOneRs(sql, new String[] { xh },
							colList);
					if (rs != null) {
						for (int i = 0; i < colList.length; i++) {
							map.put(colList[i].toLowerCase(), rs[i]);
						}
					}
					if (cg.equalsIgnoreCase("true")) {
						map.put("xn", xn);// xn
						map.put("xq", xq);// xq
						map.put("nd", nd);// nd
					}
					dataSearchForm.setXh(xh);
					kcList = dao.getKcList("gzcj_kcxx", bjdm, xn, xq);
					request.setAttribute("isGZCJXY", "yes");
				} else {
					sql = "select distinct kcdm,kcmc from bks_kcdm";
					kcList = dao.getList(sql, new String[] {}, new String[] {
							"kcdm", "kcmc" });
				}
				request.setAttribute("kcList", kcList);
			} else if (realTable.equalsIgnoreCase("xfcjb")) {
				List<HashMap<String, String>> qflxList = new ArrayList<HashMap<String, String>>();
				qflxList = dao.getList("select qflxdm,qflxmc from qflxdmb",
						new String[] {}, new String[] { "qflxdm", "qflxmc" });
				request.setAttribute("qflxList", qflxList);

				/*
				 * map.put("xn", dqndq[0]);// xn map.put("xq", dqndq[1]);// xq
				 * map.put("nd", dqndq[2]);// nd
				 */

				if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)) {// 广州城建
					
					sql = "select * from view_xfcjxx where xh=?";
					List xfcjList = dao.getList(sql, new String[] { xh },
							new String[] { "xn", "je", "qflxmc", "bz" });

					sql = "select hjje,hjqx from view_xfhjxx where xh=?";
					List xfhjList = dao.getList(sql, new String[] { xh },
							new String[] { "hjje", "hjqx" });

					request.setAttribute("xfcjList", xfcjList);
					request.setAttribute("xfhjList", xfhjList);
				}
			} else if (realTable.equalsIgnoreCase("gywxglb")) {

				String lddm = request.getParameter("lddm");
				StringBuffer cxtj = new StringBuffer();
				String userType = session.getAttribute("userType").toString();
				// String userName =
				// session.getAttribute("userName").toString();
				if (doType.equalsIgnoreCase("add")) {// 添加时默认系统设置年月
					map.put("xn", Base.currXn);
					map.put("xq", Base.currXq);
				}
				// 公寓辅导员判断begin
				// String lddmStr = gyglDao.getLddmxXx(userName);
				// String isGyFdy = "no";
				// if(!Base.isNull(lddmStr)){
				// lddm = lddmStr;
				// //comForm.setLddm(lddm);
				// isGyFdy = "yes";
				// map.put("lddm", lddm);
				// }
				// request.setAttribute("isGyFdy","");
				// 公寓辅导员判断end

				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {
					request.setAttribute("isGDBYXY", "yes");
				}
				if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
					request.setAttribute("isNBZYJSXY", "isNBZYJSXY");
				}
				cxtj.append(" where 1=1");
				if ("".equalsIgnoreCase(lddm) || lddm == null) {
					cxtj.append(" and 1=1");
				} else {
					if (Check_Input_Value.check2(lddm)) {
						cxtj.append(" and lddm='");
						cxtj.append(lddm);
						cxtj.append("'");
					}
				}
				// sql = "select lddm,ldmc from sslddmb order by
				//  lddm ";
				// List ldList = dao.getList(sql, new String[] {}, new String[]
				// {
				// "lddm", "ldmc" });
				// request.setAttribute("ldList", ldList);
				sql = "select rydm,rymc from gywxryb where rydm <> '000' ";
				List ryList = dao.getList(sql, new String[] {}, new String[] {
						"rydm", "rymc" });
				request.setAttribute("ryList", ryList);
				sql = "select bmdm,bmmc from gywsjcbmb";
				List bmList = dao.getList(sql, new String[] {}, new String[] {
						"bmdm", "bmmc" });
				request.setAttribute("bmList", bmList);
				// sql = "select distinct qsh from ssxxb " + cxtj;
				// List ssList = dao.getList(sql, new String[] {},
				// new String[] { "qsh" });
				// request.setAttribute("ssList", ssList);
				gyglDao.getLdLcQshList(request);
				request.setAttribute("userType", userType);
				request.setAttribute("xxdm", xxdm);
				sql = "select dm,mc from wxnrdmb order by dm ";
				List wxnrList = dao.getList(sql, new String[] {}, new String[] {
						"dm", "mc" });
				request.setAttribute("wxnrList", wxnrList);
			} else if (realTable.equalsIgnoreCase("gywsjcb")) {
				if (Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)
						&& "add".equalsIgnoreCase(doType)) {// 武汉理工大学华夏学院
					return new ActionForward(
							"/whlghxxy_Gygl.do?method=wsjcInput", false);
				}
				String clin = "卫生检查";
				String lddm = request.getParameter("lddm");
				StringBuffer cxtj = new StringBuffer();
				if (doType.equalsIgnoreCase("add")) {// 添加时默认系统设置年月
					map.put("xn", Base.currXn);
					map.put("xq", Base.currXq);
				}
				String userType = session.getAttribute("userType").toString();
				// String userName =
				// session.getAttribute("userName").toString();
				// 公寓辅导员判断begin
				// String lddmStr = gyglDao.getLddmxXx(userName);
				// String isGyFdy = "no";
				// if(!Base.isNull(lddmStr)){
				// lddm = lddmStr;
				// //comForm.setLddm(lddm);
				// isGyFdy = "yes";
				// map.put("lddm", lddm);
				// }
				// request.setAttribute("isGyFdy","");
				// 公寓辅导员判断end

				cxtj.append(" where 1=1");
				if ("".equalsIgnoreCase(lddm) || lddm == null) {
					cxtj.append(" and 1=1");
				} else {
					if (Check_Input_Value.check2(lddm)) {
						cxtj.append(" and lddm='");
						cxtj.append(lddm);
						cxtj.append("'");
					}
				}
				// sql = "select lddm,ldmc from sslddmb order by
				//  lddm ";
				// List ldList = dao.getList(sql, new String[] {}, new String[]
				// {
				// "lddm", "ldmc" });
				sql = "select bmdm,bmmc from gywsjcbmb";
				List bmList = dao.getList(sql, new String[] {}, new String[] {
						"bmdm", "bmmc" });

				List djList = dao.getScore(5);
				// request.setAttribute("ldList", ldList);
				if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// 浙江传媒学院
					djList = dao.getScore(6);
				} else if (Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)) {
					djList = dao.getList(
							"select * from nwwsdjdmb order by nwwsdjfs desc",
							new String[] {}, new String[] { "nwwsdjdm",
									"nwwsdjmc", "nwwsdjfs" });
					request.setAttribute("zsList", gyglDao.dao_zsList());
				} else if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
					djList = dao.getScore(7);
				} else if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// 中国地大
					djList = dao.getWhList("zgdd_wsjcdj", "dm", "mc", "", "",
							"");
					djList.remove(0);
				} else if (Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {// 淮海工学院
					// TODO
					sql = "select xqzs from xtszb";
					String zs = dao.getOneRs(sql, new String[] {}, "xqzs");
					List<HashMap<String, String>> zsList = new ArrayList<HashMap<String, String>>();
					List<HashMap<String, String>> wsjcdjList = new ArrayList<HashMap<String, String>>();
					if (zs != null && !"".equalsIgnoreCase(zs)) {
						int zsV = Integer.parseInt(zs);
						for (int i = 1; i <= zsV; i++) {
							HashMap<String, String> zsMap = new HashMap<String, String>();
							zsMap.put("dm", String.valueOf(i));
							zsMap.put("mc", "第" + String.valueOf(i) + "周");
							zsList.add(zsMap);
						}
					}
					wsjcdjList = dao
							.getList(
									"select wsjcdj dj,wsjccj cj from hhgxy_wsjcdj order by dj",
									new String[] {},
									new String[] { "cj", "dj" });

					request.setAttribute("zsList", zsList);
					request.setAttribute("wsjcdjList", wsjcdjList);
				}
				request.setAttribute("bmList", bmList);
				request.setAttribute("djList", djList);
				// sql = "select distinct qsh from ssxxb " + cxtj;
				// List ssList = dao.getList(sql, new String[] {},
				// new String[] { "qsh" });
				// request.setAttribute("ssList", ssList);
				request.setAttribute("userType", userType);
				request.setAttribute("xxdm", xxdm);
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)) {
						clin = "卫生寝室信息";
					}
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
						clin = "卫生寝室";
					}
					request.setAttribute("noshowbm", "noshowbm");
				}
				request.setAttribute("clin", clin);
				gyglDao.getLdLcQshList(request);
			} else if (realTable.equalsIgnoreCase("zsjlb")) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)) {
					return new ActionForward("/gygl_gzcj_zsjl.do", false);
				}
				if (doType.equalsIgnoreCase("add")) {// 信息添加时默认系统设置年月
					map.put("xn", Base.currXn);
					map.put("xq", Base.currXq);
				}
				String userDep = session.getAttribute("userDep").toString();
				String userType = dao.getUserType(userDep);
				// String userName =
				// session.getAttribute("userName").toString();
				String lddm = request.getParameter("lddm");
				StringBuffer cxtj = new StringBuffer();
				String[] ldQshTem = null;// 存储学生所住楼栋寝室号
				// 住宿纪律信息添加时，获得所选学号
				String[] colListV = new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc" };
				String[] rsV = dao
						.getOneRs(
								"select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",
								new String[] { xh }, colListV);
				if (rsV != null) {
					for (int i = 0; i < colListV.length; i++) {
						map.put(colListV[i], rsV[i]);
					}
					// 默认学生所住寝室
					ldQshTem = dao
							.getOneRs(
									"select lddm, qsh ,cwh,ssbh,ldmc from view_xszsxx where xh=?",
									new String[] { xh }, new String[] { "lddm",
											"qsh", "cwh", "ssbh", "ldmc" });
					if (ldQshTem != null) {
						map.put("lddm", (Base.isNull(ldQshTem[0]) ? ""
								: ldQshTem[0]));
						map.put("qsh", (Base.isNull(ldQshTem[1]) ? ""
								: ldQshTem[1]));
						map.put("cwh", (Base.isNull(ldQshTem[2]) ? ""
								: ldQshTem[2]));
						map.put("ssbh", (Base.isNull(ldQshTem[3]) ? ""
								: ldQshTem[3]));
						map.put("ldmc", (Base.isNull(ldQshTem[4]) ? ""
								: ldQshTem[4]));
					}
				}
				cxtj.append(" where 1=1");
				if ("".equalsIgnoreCase(lddm) || lddm == null) {
					cxtj.append(" and 1=1");
				} else {
					if (Check_Input_Value.check2(lddm)) {
						cxtj.append(" and lddm='");
						cxtj.append(lddm);
						cxtj.append("'");
					}
				}
				sql = "select lddm,ldmc from sslddmb order by lddm";
				List ldList = dao.getList(sql, new String[] {}, new String[] {
						"lddm", "ldmc" });
				sql = "select wjlbdm,wjlbmc from gygl_zswjlbdmb";
				List wjlbList = dao.getList(sql, new String[] {}, new String[] {
						"wjlbdm", "wjlbmc" });
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
					sql = "select cfdjfs,cfdjmc from hhgxy_cfdj";
					List cfdjList = dao.getList(sql, new String[] {},
							new String[] { "cfdjfs", "cfdjmc" });
					request.setAttribute("cfdjList", cfdjList);
					map.put("fs", map.get("yjfs"));
				}

				request.setAttribute("wjlbList", wjlbList);
				session.setAttribute("ldList", ldList);
				sql = "select distinct qsh from ssxxb " + cxtj;
				List ssList = dao.getList(sql, new String[] {},
						new String[] { "qsh" });
				request.setAttribute("ssList", ssList);
				request.setAttribute("userType", userType);
				request.setAttribute("xxdm", xxdm);
				if (!Base.isNull(xh)) {
					String stuzsinfo = dao.returntag(
							"select * from view_xszsxx where xh=?",
							new String[] { xh });
					if ("empty".equalsIgnoreCase(stuzsinfo)) {
						request.setAttribute("message", "notIn");// 该学生还未住宿
					}
				}

			} else if (realTable.equalsIgnoreCase("xsgzpxxxb")) {
				sql = "select pxxmdm xmdm,pxxmmc xmmc from xsgbpxxmdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"xmdm", "xmmc" });
				request.setAttribute("xmdmList", xmdmList);
			} else if (realTable.equalsIgnoreCase("nsepxxb")) {
				List<HashMap<String, String>> nsepList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> nsepjbList = new ArrayList<HashMap<String, String>>();
				nsepjbList = dao.getList(
						"select nsepjbdm,nsepjbmc from nsepjbdmb",
						new String[] {},
						new String[] { "nsepjbdm", "nsepjbmc" });
				nsepList = dao.getList(
						"select nsepxmdm,nsepxmmc from nsepxmdmb",
						new String[] {},
						new String[] { "nsepxmdm", "nsepxmmc" });
				request.setAttribute("nsepjbList", nsepjbList);
				request.setAttribute("nsepList", nsepList);
			} else if (realTable.equalsIgnoreCase("ssydxxb")) {

				// List<HashMap<String, String>> ldList = new
				// ArrayList<HashMap<String, String>>();
				// List<HashMap<String, String>> lcList = new
				// ArrayList<HashMap<String, String>>();
				// List<HashMap<String, String>> ssList = new
				// ArrayList<HashMap<String, String>>();
				// List<HashMap<String, String>> cwhList = new
				// ArrayList<HashMap<String, String>>();
				// GetDropDownList getList = new GetDropDownList();
				String lddm = request.getParameter("lddm");
				String qsh = DealString.toGBK(request.getParameter("qsh"));
				String lc = request.getParameter("lc");
				String cwh = request.getParameter("cwh");
				@SuppressWarnings("unused")
				String ssbh = dao
						.getOneRs(
								"select ssbh from ssxxb where lddm=? and qsh=? and rownum=1 ",
								new String[] { lddm, qsh }, "ssbh");
				if (!Base.isNull(pkValue)) {
					sql = "select lddm,cs,ydhssbh ssbh,qsh,cwh from view_ssydxx where "
							+ pk + "=? and rownum=1";
					String[] ssInfo = dao.getOneRs(sql,
							new String[] { pkValue }, new String[] { "lddm",
									"cs", "ssbh", "qsh", "cwh" });
					if (ssInfo != null) {
						lddm = Base.isNull(ssInfo[0]) ? "" : ssInfo[0];
						lc = Base.isNull(ssInfo[1]) ? "" : ssInfo[1];
						ssbh = Base.isNull(ssInfo[2]) ? "" : ssInfo[2];
						qsh = Base.isNull(ssInfo[3]) ? "" : ssInfo[3];
						cwh = Base.isNull(ssInfo[4]) ? "" : ssInfo[4];
					}
				}

				/**
				 * sql = "select lddm,ldmc from sslddmb order by
				 *  lddm "; ldList = dao.getList(sql, new String[] {},
				 * new String[] { "lddm", "ldmc" }); ssList =
				 * getList.GetQshList(lddm); lcList=getList.GetLcList(lddm);
				 * ssList=getList.GetQshList2(lddm,lc);
				 * 
				 * cwhList = getList.GetCwhList(ssbh);
				 */

				// StringBuffer cxtj = new StringBuffer();
				// cxtj.append(" where 1=1");
				// if ("".equalsIgnoreCase(lddm) || lddm == null) {
				// cxtj.append(" and 1=1");
				// } else {
				// if (Check_Input_Value.check2(lddm)) {
				// cxtj.append(" and lddm='");
				// cxtj.append(lddm);
				// cxtj.append("'");
				// }
				// }
				// sql = "select distinct qsh from ssxxb " + cxtj;
				// List ssList = dao.getList(sql, new String[] {},
				// new String[] { "qsh" });
				// if (lddm != null && !lddm.equalsIgnoreCase("") && qsh != null
				// && !qsh.equalsIgnoreCase("")) {
				// sql = " select cws from ssxxb where ssbh = ? ";
				// String cws = dao.getOneRs(sql, new String[] {ssbh }, "cws");
				// for (int i = 1; i <= Integer.parseInt(cws); i++) {
				// HashMap<String, String> mapt = new HashMap<String, String>();
				// mapt.put("cwh", String.valueOf(i));
				// cwhList.add(mapt);
				// }
				// sql = " select cwh from xszsxxb where ssbh = ? ";
				// List sycwhList = dao.getList(sql, new String[] { ssbh },
				// new String[] { "cwh" });
				// cwhList.removeAll(sycwhList);
				// } else {
				// sql = "select distinct ydhssbh from view_ssydxx where "
				// + pk + "=?";
				// String ssbh = dao.getOneRs(sql, new String[] { pkValue },
				// "ydhssbh");
				// sql = "select distinct cwh from cwxxb a where ssbh like ? and
				// not exists (select * from xszsxxb b where a.ssbh=b.ssbh and
				// a.cwh=b.cwh) order by cwh";
				// cwhList = dao.getList(sql, new String[] { ssbh },
				// new String[] { "cwh" });
				// sql = "select distinct cwh from view_ssydxx where " + pk
				// + "=?";
				// String cwh = dao.getOneRs(sql, new String[] { pkValue },
				// "cwh");
				// HashMap<String, String> mapt = new HashMap<String, String>();
				// mapt.put("cwh", cwh);
				// cwhList.add(mapt);
				// }
				map.put("lddm", lddm);
				map.put("lc", lc);
				map.put("qsh", qsh);
				map.put("cwh", cwh);

				/**
				 * request.setAttribute("ldList",ldList);
				 * request.setAttribute("ssList",ssList);
				 * request.setAttribute("lcList",lcList);
				 * request.setAttribute("cwhList",cwhList);
				 */
				gyglDao.getLdLcQshList(request);
				if (doType.equalsIgnoreCase("add")) {// 添加时 默认设置中学年学期年度
					map.put("xn", Base.currXn);
					map.put("xq", Base.currXq);
					map.put("nd", Base.currNd);
				}
				if (Base.isNull(lddm)) {
					
					List<HashMap<String, String>> qshList = new ArrayList<HashMap<String, String>>();
					HashMap<String, String> qsMap = new HashMap<String, String>();
					qsMap.put("dm", "");
					qsMap.put("mc", "----请选择----");
					qshList.add(qsMap);
					request.setAttribute("qshList", qshList);
				}
			} else if (realTable.equalsIgnoreCase("bjjxhjb")) {
				String nj = dataSearchForm.getNj();
				String xy = dataSearchForm.getXydm();
				String zy = dataSearchForm.getZydm();
				if (xy == null) {
					xy = "";
				}
				if (zy == null) {
					zy = "";
				}
				sql = "select jxdm,jxmc from jxjxdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"jxdm", "jxmc" });
				request.setAttribute("jxList", xmdmList);
				request.setAttribute("xyList", dao.getXyList());
				request.setAttribute("zyList", dao.getZyList(xy));
				request.setAttribute("bjList", dao.getBjList(xy, zy, nj));
				request.setAttribute("urlV", request.getParameter("urlV")
						.replaceAll("!!-!!", "&"));
			} else if (realTable.equalsIgnoreCase("yxjxhjb")) {
				sql = "select jxdm,jxmc from jxjxdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"jxdm", "jxmc" });
				request.setAttribute("jxList", xmdmList);
				request.setAttribute("xyList", dao.getXyList());
			} else if (realTable.equalsIgnoreCase("wmqspbb")) {
				String clin = "文明寝室";
				String lddm = request.getParameter("lddm");
				StringBuffer cxtj = new StringBuffer();
				String userType = request.getSession().getAttribute("userType")
						.toString();
				String fzld = "";
				String userName = request.getSession().getAttribute("userName")
						.toString();
				if (doType.equalsIgnoreCase("add")) {// 添加时默认系统设置年月
					map.put("xn", Base.currXn);
					map.put("xq", Base.currXq);
				}
				// 公寓辅导员判断begin
				// String lddmStr = gyglDao.getLddmxXx(userName);
				// String isGyFdy = "no";
				// if(!Base.isNull(lddmStr)){
				// lddm = lddmStr;
				// isGyFdy = "yes";
				// map.put("lddm", lddm);
				// }
				// request.setAttribute("isGyFdy","");
				// 公寓辅导员判断end

				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					request.setAttribute("showhzy", "showhzy");
					// 值班人员登录，默认其值班楼栋
					sql = "select szlddm from zbrydmb where zbrydm = ?";
					fzld = dao.getOneRs(sql, new String[] { userName },
							"szlddm");
					if (!("".equalsIgnoreCase(fzld) || fzld == null)) {
						lddm = fzld;
						map.put("lddm", fzld);
						userType = "xy";
					}
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)
						|| Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {// 杭州职业技术学院、浙江经济职业技术学院,淮海工学院
					request.setAttribute("showlb", "showlb");
				}

				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)) {
					clin = "星级文明寝室";
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
					request.setAttribute("showzgdzdx", "showzgdzdx");
				}
				cxtj.append(" where 1=1");
				if ("".equalsIgnoreCase(lddm) || lddm == null) {
					cxtj.append(" and 1=1");
				} else {
					if (Check_Input_Value.check2(lddm)) {
						cxtj.append(" and lddm='");
						cxtj.append(lddm);
						cxtj.append("'");
					}
				}
				sql = "select lbdm,lbmc from qslbdmb";
				List qslbList = dao.getList(sql, new String[] {}, new String[] {
						"lbdm", "lbmc" });
				request.setAttribute("qslbList", qslbList);
				// sql = "select lddm,ldmc from sslddmb order by
				//  lddm ";
				// List ldList = dao.getList(sql, new String[] {}, new String[]
				// {
				// "lddm", "ldmc" });
				request.setAttribute("userType", userType);
				// request.setAttribute("ldList", ldList);
				sql = "select distinct qsh from ssxxb " + cxtj;
				// List ssList = dao.getList(sql, new String[] {},
				// new String[] { "qsh" });
				// request.setAttribute("ssList", ssList);
				request.setAttribute("xxdm", xxdm);
				request.setAttribute("userType", userType);
				request.setAttribute("clin", clin);
				gyglDao.getLdLcQshList(request);
			} else if (realTable.equalsIgnoreCase("zyzfwdjb")) {
				
				if (!Base.isNull(xh)) {
					map = CommonQueryDAO.getStuInfo(xh);
				}
				if (map.get("xn") == null) {
					map.put("xn", Base.currXn);
				}
				if (map.get("nd") == null) {
					map.put("nd", Base.currNd);
				}
				if (map.get("xq") == null) {
					map.put("xq", Base.currXq);
				}
				String cjzyzfwsj = CommonQueryDAO.dao_getInfo(
						"zyzfwdjb",
						null,
						" where xh='" + map.get("xh")
								+ "' and rownum=1 and cjzyzfwsj is not null ")
						.get("cjzyzfwsj");
				map.put("cjzyzfwsj", Base.isNull(cjzyzfwsj) ? "" : cjzyzfwsj);
			} else if (realTable.equalsIgnoreCase("lpxxb")) {
				sql = "select bxgsdm,bxgsmc from bxgsdmb";
				List bxgsList = dao.getList(sql, new String[] {}, new String[] {
						"bxgsdm", "bxgsmc" });
				sql = "select bxxzdm,bxxzmc from bxxzb where 1=1";

				List bxxzList = dao.getList(sql, new String[] {}, new String[] {
						"bxxzdm", "bxxzmc" });
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
					if (doType.equalsIgnoreCase("modi")
							|| doType.equalsIgnoreCase("view")) {
						sql = "select bxxzdm,hffy,lpje from view_lpxx where xh||bxgsdm||nd||slrq=?";
						List bxfyList = dao.getList(sql,
								new String[] { pkValue }, new String[] {
										"bxxzdm", "hffy", "lpje" });
						request.setAttribute("bxfyList", bxfyList);
						if (bxfyList != null) {
							request.setAttribute("bxfyNum", bxfyList.size());
						}
					}
					if (bxxzList != null) {
						request.setAttribute("bxxzNum", bxxzList.size());
					}
				}
				request.setAttribute("bxgsList", bxgsList);
				request.setAttribute("bxxzList", bxxzList);
			} else if (realTable.equalsIgnoreCase("gdnz_lpxxb_bx")
					|| realTable.equalsIgnoreCase("gdnz_lpxxb_sh")) {
				// 广东女子理赔信息
				sql = "select bxgsdm,bxgsmc from bxgsdmb";
				List bxgsList = dao.getList(sql, new String[] {}, new String[] {
						"bxgsdm", "bxgsmc" });
				sql = "select dcdm,dcmc from gdnzzy_bxdcdmb";
				List bxdcList = dao.getList(sql, new String[] {}, new String[] {
						"dcdm", "dcmc" });
				request.setAttribute("bxdcList", bxdcList);
				if (realTable.equalsIgnoreCase("gdnz_lpxxb_sh")) {
					request.setAttribute("showXfzrx", "showXfzrx");
				}
				request.setAttribute("bxgsList", bxgsList);
			}
			List xlzxjsxm = null;
			if (realTable.equalsIgnoreCase("xlzxqkb")) {
				sql = "SELECT zxxxm FROM xljk_zxsxxb";
				xlzxjsxm = dao.getList(sql, new String[] {},
						new String[] { "zxxxm" });
			}
			String xsdy = request.getParameter("xsdy");
			String xsjbxx = request.getParameter("xsjbxx");
			String a = request.getQueryString();
			String b = request.getRequestURL().toString();
			b = b.substring(b.lastIndexOf('/'), b.length());
			b = b + "?" + a;
			request.setAttribute("url", b);
			request.setAttribute("pkValue", pkValue);// 发送表主键
			request.setAttribute("doType", doType);
			request.setAttribute("njList", Base.getNjList());// 发送年级列表
			request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
			request.setAttribute("xqList", Base.getXqList());// 发送学期列表
			request.setAttribute("xyList", Base.getXyList());// 发送学院列表
			request.setAttribute("xlzxjsxm", xlzxjsxm);// 发送咨询师姓名列表

			if(StringUtils.isNotNull(xh)){
				map.putAll(servcie.getStuJtxx(xh));
			}
			request.setAttribute("rs", map);
			// if ((xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY) ||
			// xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX))
			// && ((realTable.equalsIgnoreCase("ybdyxxb")) ||
			// (realTable.equalsIgnoreCase("dyxxb")))) {
			// newFwd = new ActionForward("/sxjy/szxxdj/" + realTable + ".jsp",
			// false);
			// } else
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)
					&& realTable.equalsIgnoreCase("zhszcp")) {
				newFwd = new ActionForward("/added/syzy_zhszcp.jsp", false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
					&& tableName.equalsIgnoreCase("view_gdnz_lpxx")) {
				newFwd = new ActionForward("/qtsj/gdnzzy/gdnz_lpxxb.jsp");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)
					&& realTable.equalsIgnoreCase("xfcjb")) {// 广州城建学费催交
				newFwd = new ActionForward("/rcsw/gzcj/xfcjb.jsp");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)
					&& realTable.equalsIgnoreCase("xfhjb")) {// 广州城建学费缓交
				newFwd = new ActionForward("/rcsw/gzcj/xfhjb.jsp");
			}
			// ==========begin 骆嘉伟 2009/4/8 ==================
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)
					&& realTable.equalsIgnoreCase("zgkd_rdsqb")) {// 中国矿大入党申请
				newFwd = new ActionForward("/dtjs/zgkd/zgkd_rdsqb.jsp");
			}
			// ==========end 骆嘉伟 2009/4/8 ==================
			else {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
					request.setAttribute("showXbemy", "showXbemy");
				} else if (StringUtils.isEqual(xxdm, Globals.XXDM_AHJZGYXY)) {
					request.setAttribute("isAHJG", "yes");
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)
						&& realTable.equalsIgnoreCase("wjcfb")) {
					request.setAttribute("isCSMZ", "yes");
				}
				if ("xsdy".equals(xsdy)) {
					realTable = "xlytqkxsb";
				}
				if ("xsjbxx".equals(xsjbxx)) {
					realTable = "gzxsjbxsyyqkb";
				}
				if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
					if (realTable.equalsIgnoreCase("xsrychb")) {
						String rychmc = map.get("rychmc");
						if (!StringUtils.isNull(map.get("rychdm"))) {
							HashMap<String, String> rychMap = dao
									.getMapNotOut(
											"select byjyqx,mzpyqksm,jcqk,(select b.brjl from xsrychxxb b where a.xh=b.xh) brjl,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk,(select b.zysj from xsrychxxb b where a.xh=b.xh) zysj,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw,b.zzmmmc,b.mzmc,b.syd,b.jtdz from xsrychb a left join view_xsxxb b on a.xh=b.xh where a.xh=? and a.xn=? and a.xq=? and a.rychdm=?",
											new String[] { map.get("xh"),
													map.get("xn"),
													map.get("xq"),
													map.get("rychdm") });
							map.put("byjyqx", rychMap.get("byjyqx"));
							map.put("mzpyqksm", rychMap.get("mzpyqksm"));
							map.put("jcqk", rychMap.get("jcqk"));
							map.put("drzw", rychMap.get("drzw"));
							map.put("zzmmmc", rychMap.get("zzmmmc"));
							map.put("mzmc", rychMap.get("mzmc"));
							map.put("syd", rychMap.get("syd"));
							map.put("jtdz", rychMap.get("jtdz"));
							map.put("brjl", rychMap.get("brjl"));
							map.put("hjqk", rychMap.get("hjqk"));
							map.put("zysj", rychMap.get("zysj"));

						}
						if (rychmc.contains("院优毕业生")) {
							request.setAttribute("info", "yes");
							request.setAttribute("yybys", "yes");
						}
						if (rychmc.contains("省优毕业生")) {
							request.setAttribute("sybys", "yes");
							request.setAttribute("info", "yes");
						}
					}
				}
				
				newFwd = new ActionForward("/sjcz/" + realTable + ".jsp", false);
			}

		}
		return newFwd;
	}

	private ActionForward saveData(ActionMapping mapping,
			HttpServletRequest request, CommanForm dataSearchForm, DAO dao,
			String pk, String pkValue, String realTable, boolean ok,
			String xxmc, String xxdm) throws Exception, FileNotFoundException,
			IOException, SQLException {
		ActionForward newFwd;
		String sql;
		// 保存数据
		String xh = dataSearchForm.getXh();
		String nd = dataSearchForm.getNd();
		String xn = dataSearchForm.getXn();
		String xq = dataSearchForm.getXq();
		// ------------2010/6/25 edit by luojw-----------------------------
		String url = request.getParameter("url");
		newFwd = Base.isNull(url) ? null : new ActionForward(url, false);

		boolean isSuccess = false;

		if (realTable.equalsIgnoreCase("xspxxxb")) {
			// 培训信息
			String pxxmdm = request.getParameter("pxxmdm");
			String pxjg = DealString.toGBK(request.getParameter("pxjg"));
			String pxkssj = request.getParameter("pxkssj").replaceAll("-", "");
			String pxjssj = request.getParameter("pxjssj").replaceAll("-", "");
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from xspxxxb where xn=? and xq=? and xh=? and pxxmdm=?";
				isSuccess = dao.runUpdate(sql, new String[] { xn, xq, xh,
						pxxmdm });
			} else {
				sql = "delete from xspxxxb where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into xspxxxb(nd,xn,xq,xh,pxxmdm,pxjg,pxkssj,pxjssj,bz) values(?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						pxxmdm, pxjg, pxkssj, pxjssj, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("rdjjfzxxb")) {
			// 入党积极分子
			String pxkssj = request.getParameter("kssj").replaceAll("-", "");
			String bz = DealString.toGBK(request.getParameter("bz"));
			String tjdw = DealString.toGBK(request.getParameter("tjdw"));
			String rzqk = DealString.toGBK(request.getParameter("rzqk"));
			String lxr2 = DealString.toGBK(request.getParameter("lxr2"));
			String pysj = DealString.toGBK(request.getParameter("pysj"));
			String lxr1 = DealString.toGBK(request.getParameter("lxr1"));
			String pycs = DealString.toGBK(request.getParameter("pycs"));
			String ssbx1 = DealString.toGBK(request.getParameter("ssbx1"));
			String ssbx2 = DealString.toGBK(request.getParameter("ssbx2"));
			String ssbx3 = DealString.toGBK(request.getParameter("ssbx3"));
			String ssbx4 = DealString.toGBK(request.getParameter("ssbx4"));
			String xsccdm = DealString.toGBK(request.getParameter("xsccdm"));
			String lwjjfzsj = DealString
					.toGBK(request.getParameter("lwjjfzsj"));
			String lwjhfzdxdsj = DealString.toGBK(request
					.getParameter("lwjhfzdxdsj"));
			String zbdhtggrrdsj = DealString.toGBK(request
					.getParameter("zbdhtggrrdsj"));
			String pyr = DealString.toGBK(request.getParameter("pyr"));
			String fzlx = DealString.toGBK(request.getParameter("fzlx"));
			String zbqdsj = DealString.toGBK(request.getParameter("zbqdsj"));
			String sfpydy = DealString.toGBK(request.getParameter("sfpydy"));
			String thcs = DealString.toGBK(request.getParameter("thcs"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable
						+ " where xn=? and xq=? and xh=?";
				isSuccess = dao.runUpdate(sql, new String[] { xn, xq, xh });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,kssj,bz,tjdw,rzqk,lxr1,lxr2,xsccdm) values(?,?,?,?,?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq,
							xh, pxkssj, bz, tjdw, rzqk, lxr1, lxr2, xsccdm });
				} else {
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,lxr1,kssj,bz,pysj,pycs,zbqdsj,sfpydy,thcs,xsccdm,ssbx1,ssbx2,ssbx3,ssbx4,lwjjfzsj,lwjhfzdxdsj,zbdhtggrrdsj,pyr,fzlx) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq,
							xh, lxr1, pxkssj, bz, pysj, pycs, zbqdsj, sfpydy,
							thcs, xsccdm, ssbx1, ssbx2, ssbx3, ssbx4, lwjjfzsj,
							lwjhfzdxdsj, zbdhtggrrdsj, pyr, fzlx });
				}
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			// =============== begin 浙江传媒学院 骆嘉伟 2009/3/9 =============
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				String fzsj = request.getParameter("fzsj");
				DtjszjcmService service = new DtjszjcmService();
				if (!service.addFzdx(nd, xn, xq, xh, fzsj, bz, request)) {
					dataSearchForm.setErrMsg("sdf");

				}
			}
			// =============== end 浙江传媒学院 骆嘉伟 2009/3/9 ============
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("jszhkpb")) {
			// 纪实综合考评
			String sxpdpj = DealString.toGBK(request.getParameter("sxpdpj"));
			String zssppj = DealString.toGBK(request.getParameter("zssppj"));
			String xynlpj = DealString.toGBK(request.getParameter("xynlpj"));
			String nlpj = DealString.toGBK(request.getParameter("nlpj"));
			String szpj = DealString.toGBK(request.getParameter("szpj"));
			String xf = request.getParameter("xf");
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable
						+ " where xn=? and xq=? and xh=?";
				isSuccess = dao.runUpdate(sql, new String[] { xn, xq, xh });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into " + realTable
						+ "(nd,xn,xq,xh,sxpdpj,zssppj,xynlpj,nlpj,szpj,xf,bz)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						sxpdpj, zssppj, xynlpj, nlpj, szpj, xf, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xsjxhjb")) {
			// 军训获奖
			String hjsj = request.getParameter("hjsj").replaceAll("-", "");
			String jxdm = request.getParameter("jxdm");
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = StandardOperation.delete(realTable, new String[] {
						"hjsj", "xh" }, new String[] { hjsj, xh }, request);
			} else {
				isSuccess = StandardOperation.delete(realTable, pk, pkValue,
						request);
			}
			if (isSuccess) {
				isSuccess = StandardOperation.insert(realTable, new String[] {
						"nd", "xn", "xq", "xh", "hjsj", "jxdm", "bz" },
						new String[] { nd, xn, xq, xh, hjsj, jxdm, bz },
						request);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("zhszcp")) {
			// 综合素质测评信息
			String zpf = request.getParameter("zpf");
			BigDecimal b= new BigDecimal(zpf);
			zpf = b.setScale(1,BigDecimal.ROUND_HALF_UP).toString();
			String dcj = DealString.toGBK(request.getParameter("dcj"));
			String xydykpf = DealString.toGBK(request.getParameter("xydykpf"));
			String gydykpf = DealString.toGBK(request.getParameter("gydykpf"));
			String zcj = DealString.toGBK(request.getParameter("zcj"));
			String tcj = DealString.toGBK(request.getParameter("tcj"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String jnjf = DealString.toGBK(request.getParameter("jnjf"));
			String gzxxcx = DealString.toGBK(request.getParameter("gzxxcx"));
			String cpzf = DealString.toGBK(request.getParameter("cpzf"));
			String znszcj = DealString.toGBK(request.getParameter("znszcj"));
			String zhszcpzf = DealString.toGBK(request.getParameter("cpzf"));
			String cxcj = DealString.toGBK(request.getParameter("cxcj"));// 广东白云操行成绩
			cxcj = !StringUtils.isNull(cxcj) ? cxcj : "";
			String kcjqpfj = request.getParameter("kcjqpfj");
			kcjqpfj = StringUtils.isNull(kcjqpfj) ? "0" : kcjqpfj;
			String ddcj = request.getParameter("ddcj");
			ddcj = StringUtils.isNull(ddcj) ? "0" : ddcj;
			String xwcj = request.getParameter("xwcj");
			xwcj = StringUtils.isNull(xwcj) ? "0" : xwcj;
			String shqcj = request.getParameter("shqcj");
			shqcj = StringUtils.isNull(shqcj) ? "0" : shqcj;
			float dyzf = 0;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {// 安徽建工评分明细
				zhszcpzf = DealString.toGBK(request.getParameter("zhszcpzf"));
				// String zxq = request.getParameter("zxq");
				String zgdy = request.getParameter("zgdy");
				String zgybdy = request.getParameter("zgybdy");
				String xjbj = request.getParameter("xjbj");
				String wmss = request.getParameter("wmss");
				String xsgb1 = request.getParameter("xsgb1");
				String xsgb2 = request.getParameter("xsgb2");
				String xsgb3 = request.getParameter("xsgb3");
				String xsgb4 = request.getParameter("xsgb4");
				String wysp = request.getParameter("wysp");
				String jsjsp = request.getParameter("jsjsp");
				String xjjxj1 = request.getParameter("xjjxj1");
				String xjjxj2 = request.getParameter("xjjxj2");
				String xjjxj3 = request.getParameter("xjjxj3");
				String gjjl1 = request.getParameter("gjjl1");
				String gjjl2 = request.getParameter("gjjl2");
				String gjjl3 = request.getParameter("gjjl3");
				String sjjl1 = request.getParameter("sjjl1");
				String sjjl2 = request.getParameter("sjjl2");
				String sjjl3 = request.getParameter("sjjl3");
				String xjgr1 = request.getParameter("xjgr1");
				String xjgr2 = request.getParameter("xjgr2");
				String zf = request.getParameter("zf");
				isSuccess = dao.runUpdate("delete from sjdxspfxzb where xh=?",
						new String[] { xh });
				dao
						.runUpdate(
								"insert into sjdxspfxzb (xh,zgdy,zgybdy,xjbj,wmss,xsgb1,xsgb2,xsgb3,xsgb4,wysp,jsjsp,xjjxj1,xjjxj2,xjjxj3,gjjl1,gjjl2,gjjl3,sjjl1,sjjl2,sjjl3,xjgr1,xjgr2,zf) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
								new String[] { xh, zgdy, zgybdy, xjbj, wmss,
										xsgb1, xsgb2, xsgb3, xsgb4, wysp,
										jsjsp, xjjxj1, xjjxj2, xjjxj3, gjjl1,
										gjjl2, gjjl3, sjjl1, sjjl2, sjjl3,
										xjgr1, xjgr2, zf });
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				/*
				 * if (!StringUtils.isNull(dcj) && !StringUtils.isNull(kcjqpfj)) {
				 * int tmp = (int)((Float.parseFloat(ddcj)*10/100 +
				 * Float.parseFloat(xwcj)*10/100 + Float.parseFloat(shqcj)*5/100 +
				 * Float.parseFloat(kcjqpfj)*75/100)*10); zhszcpzf = tmp/10.0 +
				 * ""; } else { zhszcpzf = ""; }
				 */
				dyzf = Float.parseFloat(ddcj) + Float.parseFloat(xwcj)
						+ Float.parseFloat(shqcj);
				zhszcpzf = (Float.parseFloat(kcjqpfj) * 75 / 100 + dyzf) + "";
				zhszcpzf = dao
						.getOneRs("select trunc(" + zhszcpzf
								+ ",2) zhszcpzf from dual", new String[] {},
								"zhszcpzf");
			}

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from zhszcp where xn=? and xh=? and nd=? and xq = ?";
				isSuccess = dao.runUpdate(sql, new String[] { xn, xh, nd, xq });
			} else {
				sql = "delete from zhszcp where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
					sql = "insert into zhszcp(nd,xn,xq,xh,dcj,zcj,tcj,bz,znszcj,jnjf,zhszcpzf,kcjqpfj,ddcj,xwcj,shqcj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq,
							xh, dcj, zcj, tcj, bz, znszcj, jnjf, zhszcpzf,
							kcjqpfj, ddcj, xwcj, shqcj });
				} else {*/
					ToolClass.getZhszcp(dao, xxmc, xh, nd, xn, xq, dcj,
							xydykpf, gydykpf, zcj, tcj, bz, jnjf, gzxxcx, cpzf,
							znszcj, zhszcpzf, cxcj, zpf);
				/*}*/
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("knssqb")) {
			// 困难生信息
			String lxdh = request.getParameter("lxdh");
			String sqyy = DealString.toGBK(request.getParameter("sqyy"));
			String cxsj = request.getParameter("cxsj");
			String bz = DealString.toGBK(request.getParameter("bz"));
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));
			String yjfsbj = request.getParameter("yjfsbj");
			String shsj = dao.dateToStr(request.getParameter("shsj"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from knssqb where xn=? and xh=? and xq=?";
				isSuccess = dao.runUpdate(sql, new String[] { xn, xh, xq });
			} else {
				sql = "delete from knssqb where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into knssqb(nd,xn,xq,xh,lxdh,sqyy,cxsj,bz,xysh,xxsh,yjfsbj,shsj) values(?,?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						lxdh, sqyy, cxsj, bz, xysh, xxsh, yjfsbj, shsj });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("zxdk_htxx")) {
			// 综合素质测评信息
			String hth = DealString.toGBK(dataSearchForm.getHth());
			String htjbjrjg = DealString.toGBK(dataSearchForm.getHtjbjrjg());
			String htfzjgmc = DealString.toGBK(dataSearchForm.getHtfzjgmc());
			String htpzrq = DealString.toGBK(dataSearchForm.getHtpzrq());
			String htzje = DealString.toGBK(dataSearchForm.getHtzje());
			String htjby = DealString.toGBK(dataSearchForm.getHtjby());
			String htpzhz = DealString.toGBK(dataSearchForm.getHtpzhz());
			String hthkksrq = DealString.toGBK(dataSearchForm.getHthkksrq());
			String hthkjzrq = DealString.toGBK(dataSearchForm.getHthkjzrq());
			String htzqly = DealString.toGBK(dataSearchForm.getHtzqly());
			String htzqsj = DealString.toGBK(dataSearchForm.getHtzqsj());
			String htdkfs = DealString.toGBK(dataSearchForm.getHtdkfs());
			String hthkfs = DealString.toGBK(dataSearchForm.getHthkfs());
			String hthkjzmc = DealString.toGBK(dataSearchForm.getHthkjzmc());
			String hthkjzzh = DealString.toGBK(dataSearchForm.getHthkjzzh());
			String httqyhbxje = DealString
					.toGBK(dataSearchForm.getHttqyhbxje());
			String httqjzsj = DealString.toGBK(dataSearchForm.getHttqbz());
			String httqbz = DealString.toGBK(dataSearchForm.getHttqbz());
			String dkll = DealString.toGBK(dataSearchForm.getDkll());
			String dklb = DealString.toGBK(dataSearchForm.getDklb());
			String bz = DealString.toGBK(dataSearchForm.getBz());
			String sfzh = DealString.toGBK(dataSearchForm.getSfzh());
			if ((null == sfzh) || ("".equalsIgnoreCase(sfzh))) {
				sfzh = dao.getOneRs(
						"select sfzh from bks_xsjbxx where xh=? and rownum=1",
						new String[] { xh }, new String[] { "sfzh" })[0];
			}
			String xm = dao.getOneRs(
					"select xm from bks_xsjbxx where xh=? and rownum=1",
					new String[] { xh }, new String[] { "xm" })[0];
			pk = xh + hth;

			boolean full = false;
			sql = "select xh,hth1,hth2,hth3,hth4,replace(NVL(dkze,'0'),' ','0') dkze,replace(NVL(ht1_zje,'0'),' ','0') ht1_zje,"
					+ "replace(NVL(ht2_zje,'0'),' ','0') ht2_zje,replace(NVL(ht3_zje,'0'),' ','0') ht3_zje,replace(NVL(ht4_zje,'0'),' ','0') ht4_zje from zxdk_xsjbxx where xh=? and rownum=1";
			String[] xsxx = dao
					.getOneRs(sql, new String[] { xh }, new String[] { "xh",
							"hth1", "hth2", "hth3", "hth4", "dkze", "ht1_zje",
							"ht2_zje", "ht3_zje", "ht4_zje" });
			if (xsxx == null) {
				request.setAttribute("isNULL", "is");
			} else {
				int tempje = 0;
				if (hth.equalsIgnoreCase(xsxx[1])
						|| (" ".equalsIgnoreCase(xsxx[1])) || (xsxx[1] == null)) {
					sql = "update zxdk_xsjbxx set hth1=?,ht1_jbjrjg=?,ht1_fzjgmc=?,ht1_pzrq=?,ht1_zje=?,"
							+ "ht1_jby=?,ht1_pzhz=?,ht1_hkksrq=?,ht1_hkjzrq=?,ht1_zqly=?,ht1_zqsj=?,"
							+ "ht1_dkfs=?,ht1_hkfs=?,ht1_hkjzmc=?,ht1_hkjzzh=?,ht1_tqyhbxje=?,ht1_tqjzsj=?,"
							+ "ht1_tqbz=?,dkze=? where xh=?";
					tempje = Integer.parseInt(xsxx[5])
							- Integer.parseInt(xsxx[6])
							+ Integer.parseInt(htzje);
					isSuccess = dao.runUpdate(sql, new String[] { hth,
							htjbjrjg, htfzjgmc, htpzrq, htzje, htjby, htpzhz,
							hthkksrq, hthkjzrq, htzqly, htzqsj, htdkfs, hthkfs,
							hthkjzmc, hthkjzzh, httqyhbxje, httqjzsj, httqbz,
							String.valueOf(tempje), xh });
				} else if (hth.equalsIgnoreCase(xsxx[2])
						|| (" ".equalsIgnoreCase(xsxx[2])) || (xsxx[2] == null)) {
					sql = "update zxdk_xsjbxx set hth2=?,ht2_jbjrjg=?,ht2_fzjgmc=?,ht2_pzrq=?,ht2_zje=?,"
							+ "ht2_jby=?,ht2_pzhz=?,ht2_hkksrq=?,ht2_hkjzrq=?,ht2_zqly=?,ht2_zqsj=?,"
							+ "ht2_dkfs=?,ht2_hkfs=?,ht2_hkjzmc=?,ht2_hkjzzh=?,ht2_tqyhbxje=?,ht2_tqjzsj=?,"
							+ "ht2_tqbz=?,dkze=? where xh=?";
					tempje = Integer.parseInt(xsxx[5])
							- Integer.parseInt(xsxx[7])
							+ Integer.parseInt(htzje);
					isSuccess = dao.runUpdate(sql, new String[] { hth,
							htjbjrjg, htfzjgmc, htpzrq, htzje, htjby, htpzhz,
							hthkksrq, hthkjzrq, htzqly, htzqsj, htdkfs, hthkfs,
							hthkjzmc, hthkjzzh, httqyhbxje, httqjzsj, httqbz,
							String.valueOf(tempje), xh });
				} else if (hth.equalsIgnoreCase(xsxx[3])
						|| (" ".equalsIgnoreCase(xsxx[3])) || (xsxx[3] == null)) {
					sql = "update zxdk_xsjbxx set hth3=?,ht3_jbjrjg=?,ht3_fzjgmc=?,ht3_pzrq=?,ht3_zje=?,"
							+ "ht3_jby=?,ht3_pzhz=?,ht3_hkksrq=?,ht3_hkjzrq=?,ht3_zqly=?,ht3_zqsj=?,"
							+ "ht3_dkfs=?,ht3_hkfs=?,ht3_hkjzmc=?,ht3_hkjzzh=?,ht3_tqyhbxje=?,ht3_tqjzsj=?,"
							+ "ht3_tqbz=?,dkze=? where xh=?";
					tempje = Integer.parseInt(xsxx[5])
							- Integer.parseInt(xsxx[8])
							+ Integer.parseInt(htzje);
					isSuccess = dao.runUpdate(sql, new String[] { hth,
							htjbjrjg, htfzjgmc, htpzrq, htzje, htjby, htpzhz,
							hthkksrq, hthkjzrq, htzqly, htzqsj, htdkfs, hthkfs,
							hthkjzmc, hthkjzzh, httqyhbxje, httqjzsj, httqbz,
							String.valueOf(tempje), xh });
				} else if (hth.equalsIgnoreCase(xsxx[4])
						|| " ".equalsIgnoreCase(xsxx[4]) || (xsxx[4] == null)) {
					sql = "update zxdk_xsjbxx set hth4=?,ht4_jbjrjg=?,ht4_fzjgmc=?,ht4_pzrq=?,ht4_zje=?,"
							+ "ht4_jby=?,ht4_pzhz=?,ht4_hkksrq=?,ht4_hkjzrq=?,ht4_zqly=?,ht4_zqsj=?,"
							+ "ht4_dkfs=?,ht4_hkfs=?,ht4_hkjzmc=?,ht4_hkjzzh=?,ht4_tqyhbxje=?,ht4_tqjzsj=?,"
							+ "ht4_tqbz=?,dkze=? where xh=?";
					tempje = Integer.parseInt(xsxx[5])
							- Integer.parseInt(xsxx[9])
							+ Integer.parseInt(htzje);
					isSuccess = dao.runUpdate(sql, new String[] { hth,
							htjbjrjg, htfzjgmc, htpzrq, htzje, htjby, htpzhz,
							hthkksrq, hthkjzrq, htzqly, htzqsj, htdkfs, hthkfs,
							hthkjzmc, hthkjzzh, httqyhbxje, httqjzsj, httqbz,
							String.valueOf(tempje), xh });
				} else {
					full = true;
					request.setAttribute("isFULL", "is");
				}

				if (!full) {
					sql = "delete from zxdk_htxx where xh||hth = '" + pk + "'";
					isSuccess = dao.runUpdate(sql, new String[] {});
					if (isSuccess) {
						sql = "insert into zxdk_htxx(xxmc,xh,xm,sfzh,hth,htjbjrjg,htfzjgmc,"
								+ "htpzrq,htzje,htjby,htpzhz,hthkksrq,hthkjzrq,htzqly,htzqsj,"
								+ "htdkfs,hthkfs,hthkjzmc,hthkjzzh,httqyhbxje,httqjzsj,httqbz,"
								+ "dkll,dklb,bz) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						isSuccess = dao.runUpdate(sql, new String[] { xxmc, xh,
								xm, sfzh, hth, htjbjrjg, htfzjgmc, htpzrq,
								htzje, htjby, htpzhz, hthkksrq, hthkjzrq,
								htzqly, htzqsj, htdkfs, hthkfs, hthkjzmc,
								hthkjzzh, httqyhbxje, httqjzsj, httqbz, dkll,
								dklb, bz });
					} else {
						dataSearchForm.setErrMsg("sdf");

					}
				}
			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);

			if (ok) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
			request.setAttribute("isHTXX", "is");
			ActionForward forward = new ActionForward(
					"/modiData.do?realTable=zxdk_htxx&doType=add&tableName=zxdk_htxx&pk=xh||hth&from=htxx&pkValue= ",
					false);
			return forward;

		} else if (realTable.equalsIgnoreCase("zxdk_xsjbxx")) {
			// 综合素质测评信息
			String ksh = DealString.toGBK(dataSearchForm.getKsh());
			String xm = dao.getOneRs(
					"select xm from bks_xsjbxx where xh=? and rownum=1",
					new String[] { xh }, new String[] { "xm" })[0];
			String xb = DealString.toGBK(dataSearchForm.getXb());
			String sfzh = DealString.toGBK(dataSearchForm.getSfzh());
			String mzmc = DealString.toGBK(dataSearchForm.getMzmc());
			String xymc = DealString.toGBK(dataSearchForm.getXymc());
			String xmc = DealString.toGBK(dataSearchForm.getXmc());
			String rxny = DealString.toGBK(dataSearchForm.getRxny());
			String csrq = DealString.toGBK(dataSearchForm.getCsrq());
			String xz = DealString.toGBK(dataSearchForm.getXz());
			String sedh = DealString.toGBK(dataSearchForm.getSedh());
			String jtsr = DealString.toGBK(dataSearchForm.getJtsr());
			String fqsfzh = DealString.toGBK(dataSearchForm.getFqsfzh());
			String mqsfzh = DealString.toGBK(dataSearchForm.getMqsfzh());
			String jtzz = DealString.toGBK(dataSearchForm.getJtzz());
			String byny = DealString.toGBK(dataSearchForm.getByny());
			String dkze = DealString.toGBK(dataSearchForm.getDkze());
			String xfdks = DealString.toGBK(dataSearchForm.getXfdks());
			String shfdks = DealString.toGBK(dataSearchForm.getShfdks());
			String zsfdks = DealString.toGBK(dataSearchForm.getZsfdks());
			String yhdkje = DealString.toGBK(dataSearchForm.getYhdkje());
			String yzbm = DealString.toGBK(dataSearchForm.getYzbm());
			String fqxm = DealString.toGBK(dataSearchForm.getFqxm());
			String fqgzdw = DealString.toGBK(dataSearchForm.getFqgzdw());
			String fqlxdh = DealString.toGBK(dataSearchForm.getFqlxdh());
			String mqxm = DealString.toGBK(dataSearchForm.getMqxm());
			String mqgzdw = DealString.toGBK(dataSearchForm.getMqgzdw());
			String mqlxdh = DealString.toGBK(dataSearchForm.getMqlxdh());
			String scbyqx = DealString.toGBK(dataSearchForm.getScbyqx());
			String yxlxfs = DealString.toGBK(dataSearchForm.getYxlxfs());
			String dqgzdw = DealString.toGBK(dataSearchForm.getDqgzdw());
			String dqgzdwdz = DealString.toGBK(dataSearchForm.getDqgzdwdz());
			String dqgzdwyb = DealString.toGBK(dataSearchForm.getDqgzdwyb());
			String dqgzdwdh = DealString.toGBK(dataSearchForm.getDqgzdwdh());
			String hth1 = DealString.toGBK(dataSearchForm.getHth1());
			String ht1_jbjrjg = DealString
					.toGBK(dataSearchForm.getHt1_jbjrjg());
			String ht1_fzjgmc = DealString
					.toGBK(dataSearchForm.getHt1_fzjgmc());
			String ht1_pzrq = DealString.toGBK(dataSearchForm.getHt1_pzrq());
			String ht1_zje = DealString.toGBK(dataSearchForm.getHt1_zje());
			String ht1_jby = DealString.toGBK(dataSearchForm.getHt1_jby());
			String ht1_pzhz = DealString.toGBK(dataSearchForm.getHt1_pzhz());
			String ht1_hkksrq = DealString
					.toGBK(dataSearchForm.getHt1_hkksrq());
			String ht1_hkjzrq = DealString
					.toGBK(dataSearchForm.getHt1_hkjzrq());
			String ht1_zqly = DealString.toGBK(dataSearchForm.getHt1_zqly());
			String ht1_zqsj = DealString.toGBK(dataSearchForm.getHt1_zqsj());
			String ht1_dkfs = DealString.toGBK(dataSearchForm.getHt1_dkfs());
			String ht1_hkfs = DealString.toGBK(dataSearchForm.getHt1_hkfs());
			String ht1_hkjzmc = DealString
					.toGBK(dataSearchForm.getHt1_hkjzmc());
			String ht1_hkjzzh = DealString
					.toGBK(dataSearchForm.getHt1_hkjzzh());
			String ht1_tqyhbxje = DealString.toGBK(dataSearchForm
					.getHt1_tqyhbxje());
			String ht1_tqjzsj = DealString
					.toGBK(dataSearchForm.getHt1_tqjzsj());
			String ht1_tqbz = DealString.toGBK(dataSearchForm.getHt1_tqbz());
			String hth2 = DealString.toGBK(dataSearchForm.getHth2());
			String ht2_jbjrjg = DealString
					.toGBK(dataSearchForm.getHt2_jbjrjg());
			String ht2_fzjgmc = DealString
					.toGBK(dataSearchForm.getHt2_fzjgmc());
			String ht2_pzrq = DealString.toGBK(dataSearchForm.getHt2_pzrq());
			String ht2_zje = DealString.toGBK(dataSearchForm.getHt2_zje());
			String ht2_jby = DealString.toGBK(dataSearchForm.getHt2_jby());
			String ht2_pzhz = DealString.toGBK(dataSearchForm.getHt2_pzhz());
			String ht2_hkksrq = DealString
					.toGBK(dataSearchForm.getHt2_hkksrq());
			String ht2_hkjzrq = DealString
					.toGBK(dataSearchForm.getHt2_hkjzrq());
			String ht2_zqly = DealString.toGBK(dataSearchForm.getHt2_zqly());
			String ht2_zqsj = DealString.toGBK(dataSearchForm.getHt2_zqsj());
			String ht2_dkfs = DealString.toGBK(dataSearchForm.getHt2_dkfs());
			String ht2_hkfs = DealString.toGBK(dataSearchForm.getHt2_hkfs());
			String ht2_hkjzmc = DealString
					.toGBK(dataSearchForm.getHt2_hkjzmc());
			String ht2_hkjzzh = DealString
					.toGBK(dataSearchForm.getHt2_hkjzzh());
			String ht2_tqyhbxje = DealString.toGBK(dataSearchForm
					.getHt2_tqyhbxje());
			String ht2_tqjzsj = DealString
					.toGBK(dataSearchForm.getHt2_tqjzsj());
			String ht2_tqbz = DealString.toGBK(dataSearchForm.getHt2_tqbz());
			String hth3 = DealString.toGBK(dataSearchForm.getHth3());
			String ht3_jbjrjg = DealString
					.toGBK(dataSearchForm.getHt3_jbjrjg());
			String ht3_fzjgmc = DealString
					.toGBK(dataSearchForm.getHt3_fzjgmc());
			String ht3_pzrq = DealString.toGBK(dataSearchForm.getHt3_pzrq());
			String ht3_zje = DealString.toGBK(dataSearchForm.getHt3_zje());
			String ht3_jby = DealString.toGBK(dataSearchForm.getHt3_jby());
			String ht3_pzhz = DealString.toGBK(dataSearchForm.getHt3_pzhz());
			String ht3_hkksrq = DealString
					.toGBK(dataSearchForm.getHt3_hkksrq());
			String ht3_hkjzrq = DealString
					.toGBK(dataSearchForm.getHt3_hkjzrq());
			String ht3_zqly = DealString.toGBK(dataSearchForm.getHt3_zqly());
			String ht3_zqsj = DealString.toGBK(dataSearchForm.getHt3_zqsj());
			String ht3_dkfs = DealString.toGBK(dataSearchForm.getHt3_dkfs());
			String ht3_hkfs = DealString.toGBK(dataSearchForm.getHt3_hkfs());
			String ht3_hkjzmc = DealString
					.toGBK(dataSearchForm.getHt3_hkjzmc());
			String ht3_hkjzzh = DealString
					.toGBK(dataSearchForm.getHt3_hkjzzh());
			String ht3_tqyhbxje = DealString.toGBK(dataSearchForm
					.getHt3_tqyhbxje());
			String ht3_tqjzsj = DealString
					.toGBK(dataSearchForm.getHt3_tqjzsj());
			String ht3_tqbz = DealString.toGBK(dataSearchForm.getHt3_tqbz());
			String hth4 = DealString.toGBK(dataSearchForm.getHth4());
			String ht4_jbjrjg = DealString
					.toGBK(dataSearchForm.getHt4_jbjrjg());
			String ht4_fzjgmc = DealString
					.toGBK(dataSearchForm.getHt4_fzjgmc());
			String ht4_pzrq = DealString.toGBK(dataSearchForm.getHt4_pzrq());
			String ht4_zje = DealString.toGBK(dataSearchForm.getHt4_zje());
			String ht4_jby = DealString.toGBK(dataSearchForm.getHt4_jby());
			String ht4_pzhz = DealString.toGBK(dataSearchForm.getHt4_pzhz());
			String ht4_hkksrq = DealString
					.toGBK(dataSearchForm.getHt4_hkksrq());
			String ht4_hkjzrq = DealString
					.toGBK(dataSearchForm.getHt4_hkjzrq());
			String ht4_zqly = DealString.toGBK(dataSearchForm.getHt4_zqly());
			String ht4_zqsj = DealString.toGBK(dataSearchForm.getHt4_zqsj());
			String ht4_dkfs = DealString.toGBK(dataSearchForm.getHt4_dkfs());
			String ht4_hkfs = DealString.toGBK(dataSearchForm.getHt4_hkfs());
			String ht4_hkjzmc = DealString
					.toGBK(dataSearchForm.getHt4_hkjzmc());
			String ht4_hkjzzh = DealString
					.toGBK(dataSearchForm.getHt4_hkjzzh());
			String ht4_tqyhbxje = DealString.toGBK(dataSearchForm
					.getHt4_tqyhbxje());
			String ht4_tqjzsj = DealString
					.toGBK(dataSearchForm.getHt4_tqjzsj());
			String ht4_tqbz = DealString.toGBK(dataSearchForm.getHt4_tqbz());
			String bz = DealString.toGBK(dataSearchForm.getBz());
			String xslx = DealString.toGBK(dataSearchForm.getXslx());
			String lxyy = DealString.toGBK(dataSearchForm.getLxyy());
			pk = xh;

			sql = "delete from zxdk_xsjbxx where xh = '" + pk + "'";
			isSuccess = dao.runUpdate(sql, new String[] {});
			if (isSuccess) {
				sql = "insert into ZXDK_XSJBXX (ksh,xh,xm,xb,sfzh,mzmc,xymc,xmc,"
						+ "rxny,byny,dkze,xfdks,shfdks,zsfdks,yhdkje,hth1,ht1_jbjrjg,"
						+ "ht1_fzjgmc,ht1_pzrq,ht1_zje,ht1_jby,ht1_pzhz,ht1_hkksrq,"
						+ "ht1_hkjzrq,ht1_zqly,ht1_zqsj,ht1_dkfs,ht1_hkfs,ht1_hkjzmc,"
						+ "ht1_hkjzzh,ht1_tqyhbxje,ht1_tqjzsj,ht1_tqbz,jtzz,yzbm,fqxm,"
						+ "fqgzdw,fqlxdh,mqxm,mqgzdw,mqlxdh,scbyqx,yxlxfs,dqgzdw,dqgzdwdz,"
						+ "dqgzdwyb,dqgzdwdh,hth2,ht2_jbjrjg,ht2_fzjgmc,ht2_pzrq,ht2_zje,"
						+ "ht2_jby,ht2_pzhz,ht2_hkksrq,ht2_hkjzrq,ht2_zqly,ht2_zqsj,ht2_dkfs,"
						+ "ht2_hkfs,ht2_hkjzmc,ht2_hkjzzh,ht2_tqyhbxje,ht2_tqjzsj,ht2_tqbz,"
						+ "hth3,ht3_jbjrjg,ht3_fzjgmc,ht3_pzrq,ht3_zje,ht3_jby,ht3_pzhz,"
						+ "ht3_hkksrq,ht3_hkjzrq,ht3_zqly,ht3_zqsj,ht3_dkfs,ht3_hkfs,"
						+ "ht3_hkjzmc,ht3_hkjzzh,ht3_tqyhbxje,ht3_tqjzsj,ht3_tqbz,hth4,"
						+ "ht4_jbjrjg,ht4_fzjgmc,ht4_pzrq,ht4_zje,ht4_jby,ht4_pzhz,ht4_hkksrq,"
						+ "ht4_hkjzrq,ht4_zqly,ht4_zqsj,ht4_dkfs,ht4_hkfs,ht4_hkjzmc,ht4_hkjzzh,"
						+ "ht4_tqyhbxje,ht4_tqjzsj,ht4_tqbz,bz,xslx,xxmc,csrq,xz,fqsfzh,mqsfzh,sedh,jtsr,nd,lxyy ) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { ksh, xh, xm, xb,
						sfzh, mzmc, xymc, xmc, rxny, byny, dkze, xfdks, shfdks,
						zsfdks, yhdkje, hth1, ht1_jbjrjg, ht1_fzjgmc, ht1_pzrq,
						ht1_zje, ht1_jby, ht1_pzhz, ht1_hkksrq, ht1_hkjzrq,
						ht1_zqly, ht1_zqsj, ht1_dkfs, ht1_hkfs, ht1_hkjzmc,
						ht1_hkjzzh, ht1_tqyhbxje, ht1_tqjzsj, ht1_tqbz, jtzz,
						yzbm, fqxm, fqgzdw, fqlxdh, mqxm, mqgzdw, mqlxdh,
						scbyqx, yxlxfs, dqgzdw, dqgzdwdz, dqgzdwyb, dqgzdwdh,
						hth2, ht2_jbjrjg, ht2_fzjgmc, ht2_pzrq, ht2_zje,
						ht2_jby, ht2_pzhz, ht2_hkksrq, ht2_hkjzrq, ht2_zqly,
						ht2_zqsj, ht2_dkfs, ht2_hkfs, ht2_hkjzmc, ht2_hkjzzh,
						ht2_tqyhbxje, ht2_tqjzsj, ht2_tqbz, hth3, ht3_jbjrjg,
						ht3_fzjgmc, ht3_pzrq, ht3_zje, ht3_jby, ht3_pzhz,
						ht3_hkksrq, ht3_hkjzrq, ht3_zqly, ht3_zqsj, ht3_dkfs,
						ht3_hkfs, ht3_hkjzmc, ht3_hkjzzh, ht3_tqyhbxje,
						ht3_tqjzsj, ht3_tqbz, hth4, ht4_jbjrjg, ht4_fzjgmc,
						ht4_pzrq, ht4_zje, ht4_jby, ht4_pzhz, ht4_hkksrq,
						ht4_hkjzrq, ht4_zqly, ht4_zqsj, ht4_dkfs, ht4_hkfs,
						ht4_hkjzmc, ht4_hkjzzh, ht4_tqyhbxje, ht4_tqjzsj,
						ht4_tqbz, bz, xslx, xxmc, csrq, xz, fqsfzh, mqsfzh,
						sedh, jtsr, nd, lxyy });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xxzxdksqb")) {
			// 学校助学金信息
			String dkrq = request.getParameter("dkrq");
			String sqje = request.getParameter("sqje");
			String dkje = request.getParameter("dkje");
			String shf = request.getParameter("shf");
			String xf = request.getParameter("xf");
			String hth = DealString.toGBK(request.getParameter("hth"));
			String hkqx = dao.dateToStr(request.getParameter("hkqx"));
			String cxsj = request.getParameter("cxsj");
			String bz = DealString.toGBK(request.getParameter("bz"));
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));
			String yjfsbj = request.getParameter("yjfsbj");
			String shsj = dao.dateToStr(request.getParameter("shsj"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = true;
			} else {
				sql = "delete from xxzxdksqb where " + pk + "='" + pkValue
						+ "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into xxzxdksqb(dkls,nd,xn,xq,xh,dkrq,sqje,dkje,shf,xf,hth,hkqx,cxsj,bz,xysh,xxsh,yjfsbj,shsj) values(xxzxdksqb_sequence.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						dkrq, sqje, dkje, shf, xf, hth, hkqx, cxsj, bz, xysh,
						xxsh, yjfsbj, shsj });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("gjzxdksqb")) {
			// 国家助学贷款信息
			String sqdkze = request.getParameter("sqdkze");
			String sqdkxf = request.getParameter("sqdkxf");
			String sqdkshf = request.getParameter("sqdkshf");
			String sqdkzsf = request.getParameter("sqdkzsf");
			String dqdwyb = request.getParameter("dqdwyb");
			String fqsfzh = request.getParameter("fqsfzh");
			String mqsfzh = request.getParameter("mqsfzh");
			String qysfzh = request.getParameter("qysfzh");
			String dkje1 = request.getParameter("dkje1");
			String dkje2 = request.getParameter("dkje2");
			String dkje3 = request.getParameter("dkje3");
			String yjfsbj = request.getParameter("yjfsbj");
			String ffdkze = request.getParameter("ffdkze");
			String ffdkxf = request.getParameter("ffdkxf");
			String ffdkshf = request.getParameter("ffdkshf");
			String ffdkzsf = request.getParameter("ffdkzsf");
			String sqly = DealString.toGBK(request.getParameter("sqly"));
			String scbyqx = DealString.toGBK(request.getParameter("scbyqx"));
			String yxlxfs = DealString.toGBK(request.getParameter("yxlxfs"));
			String dqgzdw = DealString.toGBK(request.getParameter("dqgzdw"));
			String dqdwdz = DealString.toGBK(request.getParameter("dqdwdz"));
			String dqdwlxfs = DealString
					.toGBK(request.getParameter("dqdwlxfs"));
			String fqxm = DealString.toGBK(request.getParameter("fqxm"));
			String fqgzdw = DealString.toGBK(request.getParameter("fqgzdw"));
			String fqlxfs = DealString.toGBK(request.getParameter("fqlxfs"));
			String mqxm = DealString.toGBK(request.getParameter("mqxm"));
			String mqgzdw = DealString.toGBK(request.getParameter("mqgzdw"));
			String mqlxfs = DealString.toGBK(request.getParameter("mqlxfs"));
			String qyxm = DealString.toGBK(request.getParameter("qyxm"));
			String qygzdw = DealString.toGBK(request.getParameter("qygzdw"));
			String qygx = DealString.toGBK(request.getParameter("qygx"));
			String qyzz = DealString.toGBK(request.getParameter("qyzz"));
			String qylxdh = DealString.toGBK(request.getParameter("qylxdh"));
			String hth1 = DealString.toGBK(request.getParameter("hth1"));
			String htjbjrjg1 = DealString.toGBK(request
					.getParameter("htjbjrjg1"));
			String fzjgmc1 = DealString.toGBK(request.getParameter("fzjgmc1"));
			String hth2 = DealString.toGBK(request.getParameter("hth2"));
			String htjbjrjg2 = DealString.toGBK(request
					.getParameter("htjbjrjg2"));
			String fzjgmc2 = DealString.toGBK(request.getParameter("fzjgmc2"));
			String hth3 = DealString.toGBK(request.getParameter("hth3"));
			String htjbjrjg3 = DealString.toGBK(request
					.getParameter("htjbjrjg3"));
			String fzjgmc3 = DealString.toGBK(request.getParameter("fzjgmc3"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String hknf1 = dao.dateToStr(request.getParameter("hknf1"));
			String hknf2 = dao.dateToStr(request.getParameter("hknf2"));
			String hknf3 = dao.dateToStr(request.getParameter("hknf3"));
			String ffsj = dao.dateToStr(request.getParameter("ffsj"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = true;
			} else {
				sql = "delete from gjzxdksqb where " + pk + "='" + pkValue
						+ "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into gjzxdksqb(dkls,xn,nd,xq,xh,sqly,sqdkze,sqdkxf,sqdkshf,sqdkzsf,scbyqx,yxlxfs,dqgzdw,dqdwdz,dqdwyb,dqdwlxfs,fqxm,fqsfzh,fqgzdw,fqlxfs,mqxm,mqsfzh,mqgzdw,mqlxfs,qyxm,qysfzh,qygzdw,qygx,qyzz,qylxdh,hth1,htjbjrjg1,fzjgmc1,dkje1,hknf1,hth2,htjbjrjg2,fzjgmc2,dkje2,hknf2,hth3,htjbjrjg3,fzjgmc3,dkje3,hknf3,yjfsbj,bz,ffdkze,ffdkxf,ffdkshf,ffdkzsf,ffsj,xysh,xxsh ) values(gjzxdksqb_sequence.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xn, nd, xq, xh,
						sqly, sqdkze, sqdkxf, sqdkshf, sqdkzsf, scbyqx, yxlxfs,
						dqgzdw, dqdwdz, dqdwyb, dqdwlxfs, fqxm, fqsfzh, fqgzdw,
						fqlxfs, mqxm, mqsfzh, mqgzdw, mqlxfs, qyxm, qysfzh,
						qygzdw, qygx, qyzz, qylxdh, hth1, htjbjrjg1, fzjgmc1,
						dkje1, hknf1, hth2, htjbjrjg2, fzjgmc2, dkje2, hknf2,
						hth3, htjbjrjg3, fzjgmc3, dkje3, hknf3, yjfsbj, bz,
						ffdkze, ffdkxf, ffdkshf, ffdkzsf, ffsj, xysh, xxsh });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("nsepxxb")) {
			// NSEP信息
			String nsepxmmc = DealString
					.toGBK(request.getParameter("nsepxmmc"));
			String jx = DealString.toGBK(request.getParameter("jx"));
			String zdls = DealString.toGBK(request.getParameter("zdls"));
			String fzr = DealString.toGBK(request.getParameter("fzr"));
			String sj = dao.dateToStr(request.getParameter("sj"));
			String dektxf = dataSearchForm.getDektxf();
			String nsepjbdm = request.getParameter("nsepjbdm");

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from nsepxxb where xn=? and xh=? and nsepxmmc=?";
				isSuccess = dao.runUpdate(sql,
						new String[] { xn, xh, nsepxmmc });
			} else {
				sql = "delete from nsepxxb where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into nsepxxb(nd,xn,xq,xh,nsepxmmc,jx,zdls,fzr,sj,dektxf,nsepjbdm) values(?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						nsepxmmc, jx, zdls, fzr, sj, dektxf, nsepjbdm });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("knsshhdxxb")) {
			// 社会活动信息
			String hdxz = request.getParameter("hdxz");
			String hdxm = DealString.toGBK(request.getParameter("hdxm"));
			String hdnr = DealString.toGBK(request.getParameter("hdnr"));
			String sj = dao.dateToStr(request.getParameter("sj"));
			String khjg = DealString.toGBK(request.getParameter("khjg"));
			String xjts = request.getParameter("xjts");
			String shry = request.getParameter("shry");

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from knsshhdxxb where xh=? and sj=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh, sj });
			} else {
				sql = "delete from knsshhdxxb where " + pk + "='" + pkValue
						+ "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					sql = "insert into knsshhdxxb(xh,hdxz,hdxm,hdnr,sj,khjg) values(?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { xh, hdxz,
							hdxm, hdnr, sj, khjg });
				} else {
					sql = "insert into knsshhdxxb(xh,hdxz,hdxm,hdnr,sj,xjts,shry) values(?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { xh, hdxz,
							hdxm, hdnr, sj, xjts, shry });
				}
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xlytqkb")) {
			// 心理普查约谈与干预情况
			String lxdzxx = DealString.toGBK(request.getParameter("lxdzxx"));
			String sjhm = DealString.toGBK(request.getParameter("sjhm"));
			String chf = DealString.toGBK(request.getParameter("chf"));
			String cbqk = DealString.toGBK(request.getParameter("cbqk"));
			String ytqk = DealString.toGBK(request.getParameter("ytqk"));
			String cbsb = DealString.toGBK(request.getParameter("cbsb"));
			String gzjb = DealString.toGBK(request.getParameter("gzjb"));
			String ytjy = DealString.toGBK(request.getParameter("ytjy"));
			String ytid = DealString.toGBK(request.getParameter("yt_id"));
			String dtsj = request.getParameter("dtsj");

			if ((xh != null) || !(xh.equalsIgnoreCase(""))) {
				sql = "delete from xlytqkb a where " + pk + "='" + ytid + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into xlytqkb(yt_id,xh,chf,cbqk,ytqk,cbsb,gzjb,ytjy,lxdzxx,sjhm,dtsj) values(?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { ytid, xh, chf,
						cbqk, ytqk, cbsb, gzjb, ytjy, lxdzxx, sjhm, dtsj });
			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xlzxqkb")) {
			// 心理咨询情况
			String zxsj = request.getParameter("zxsj");
			String zxid = request.getParameter("pkValue");
			String zxls = DealString.toGBK(request.getParameter("zxls"));
			// String xm = DealString.toGBK(request.getParameter("xm"));
			String lfjl = DealString.toGBK(request.getParameter("lfjl"));
			String wtcl = DealString.toGBK(request.getParameter("wtcl"));

			if ((zxid != null) || !("".equalsIgnoreCase(zxid))) {
				sql = "delete from xlzxqkb a where " + pk + "='" + zxid + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into xlzxqkb(xh,zxsj,zxls,lfjl,wtcl) values(?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xh, zxsj, zxls,
						lfjl, wtcl });
			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("jzjsdjb")) {
			// 兼职教师聘任登记
			String jzid = request.getParameter("jzid");
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String csny = DealString.toGBK(request.getParameter("csny"));
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String bgsdh = DealString.toGBK(request.getParameter("bgsdh"));
			String sjhm = DealString.toGBK(request.getParameter("sjhm"));
			String qtlxdh = DealString.toGBK(request.getParameter("qtlxdh"));
			String zwzc = DealString.toGBK(request.getParameter("zwzc"));
			String dzyx = DealString.toGBK(request.getParameter("dzyx"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String gzjl = DealString.toGBK(request.getParameter("gzjl"));

			if ((jzid != null) || !("".equalsIgnoreCase("jzid"))) {
				sql = "delete from jzjsdjb a where " + pk + "='" + jzid + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into jzjsdjb(xm,xb,csny,xymc,bgsdh,sjhm,qtlxdh,zwzc,dzyx,gzjl,bz) values(?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xm, xb, csny,
						xymc, bgsdh, sjhm, qtlxdh, zwzc, dzyx, gzjl, bz });
			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xlcsjgb")) {
			// 心理测试信息
			String csxmdm = DealString.toGBK(request.getParameter("csxmdm"));
			if ("中国大学生心理健康量表".equals(csxmdm)
					&& Globals.XXDM_ZGKYDX.equals(xxdm)) {
				csxmdm = "001";
			}
			String csjg = DealString.toGBK(request.getParameter("csjg"));
			String fsbj = DealString.toGBK(request.getParameter("fsbj"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String cssj = dao.dateToStr(request.getParameter("cssj"));
			@SuppressWarnings("unused")
			String sylb = DealString.toGBK(request.getParameter("sylb"));
			String csnj = DealString.toGBK(request.getParameter("csnj"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from xlcsjgb where xh=? and cssj=? and csxmdm=?";
				isSuccess = dao.runUpdate(sql,
						new String[] { xh, cssj, csxmdm });
			} else {
				sql = "delete from xlcsjgb where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				if (!Base.isNull(xn) && xn.length() > 3) {
					nd = xn.substring(0, 4);
				} else {
					nd = "";
				}

				sql = "insert into xlcsjgb(xn,xq,xh,csxmdm,csjg,bz,fsbj,cssj,csnj,nd) values(?,?,?,?,?,?,?,?,?,?)";
				if (cssj == null || "".equals(cssj)) {
					String nowtime = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') sdate from dual",
									// 发布时间25
									// 取至数据库临时表
									new String[] {}, new String[] { "sdate" }))[0];
					cssj = nowtime.substring(0, 10);
				}
				isSuccess = dao.runUpdate(sql, new String[] { xn, xq, xh,
						csxmdm, csjg, bz, fsbj, cssj, csnj, nd });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("gzxsjbqkb")) {
			// 重点关注学生基本情况表
			String id = request.getParameter("id");
			@SuppressWarnings("unused")
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			@SuppressWarnings("unused")
			String xzb = DealString.toGBK(request.getParameter("xzb"));
			@SuppressWarnings("unused")
			String xm = DealString.toGBK(request.getParameter("xm"));
			@SuppressWarnings("unused")
			String xb = DealString.toGBK(request.getParameter("xb"));
			String cbsb = DealString.toGBK(request.getParameter("cbsb"));
			String gzjb = DealString.toGBK(request.getParameter("gzjb"));
			String zyzzjbx = DealString.toGBK(request.getParameter("zyzzjbx"));
			String yyfx = DealString.toGBK(request.getParameter("yyfx"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from gzxsjbqkb where id=?";
				isSuccess = dao.runUpdate(sql, new String[] { id });
			} else {
				sql = "delete from gzxsjbqkb where " + pk + "='" + pkValue
						+ "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into gzxsjbqkb(xh,cbsb,gzjb,zyzzjbx,yyfx,bz) values(?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xh, cbsb, gzjb,
						zyzzjbx, yyfx, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("gzqkb")) {
			// 重点关注学生跟踪情况表
			@SuppressWarnings("unused")
			String id = request.getParameter("id");
			@SuppressWarnings("unused")
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String sfzdgz = DealString.toGBK(request.getParameter("sfzdgz"));
			@SuppressWarnings("unused")
			String xzb = DealString.toGBK(request.getParameter("xzb"));
			@SuppressWarnings("unused")
			String xm = DealString.toGBK(request.getParameter("xm"));
			@SuppressWarnings("unused")
			String xb = DealString.toGBK(request.getParameter("xb"));
			String jtbx = DealString.toGBK(request.getParameter("jtbx"));
			String lxr = DealString.toGBK(request.getParameter("lxr"));
			String tbsj = DealString.toGBK(request.getParameter("tbsj"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from gzqkb where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			} else {
				sql = "delete from gzqkb where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into gzqkb(xh,jtbx,lxr,tbsj,sfzdgz) values(?,?,?,?,?)";
				dao
						.runUpdate(sql, new String[] { xh, jtbx, lxr, tbsj,
								sfzdgz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xlzxyydjb")) {
			// 大学大学生健康教育中心心理咨询预约登记表
			@SuppressWarnings("unused")
			String id = request.getParameter("id");
			String gjxx = DealString.toGBK(request.getParameter("gjxx"));
			String ssld = DealString.toGBK(request.getParameter("ssld"));
			String dhhm = DealString.toGBK(request.getParameter("dhhm"));
			String dzyx = DealString.toGBK(request.getParameter("dzyx"));
			String sfzx = DealString.toGBK(request.getParameter("sfzx"));
			String sczxxx = DealString.toGBK(request.getParameter("sczxxx"));
			String sfxlcs = DealString.toGBK(request.getParameter("sfxlcs"));
			String xlxsyy = DealString.toGBK(request.getParameter("xlxsyy"));
			String zxyy = DealString.toGBK(request.getParameter("zxyy"));
			String qtzxxx = DealString.toGBK(request.getParameter("qtzxxx"));
			String zxmd = DealString.toGBK(request.getParameter("zxmd"));
			String qtwtxx = DealString.toGBK(request.getParameter("qtwtxx"));
			String ddnxbz = DealString.toGBK(request.getParameter("ddnxbz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from xlzxyydjb where " + pk + "='" + pkValue
						+ "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			} else {
				sql = "delete from xlzxyydjb where " + pk + "='" + pkValue
						+ "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into xlzxyydjb(xh,gjxx,ssld,dhhm,dzyx,sfzx,sczxxx,sfxlcs,xlxsyy,zxyy,qtzxxx,zxmd,qtwtxx,ddnxbz) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xh, gjxx, ssld,
						dhhm, dzyx, sfzx, sczxxx, sfxlcs, xlxsyy, zxyy, qtzxxx,
						zxmd, qtwtxx, ddnxbz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("szdtxxb")) {
			// 双周动态信息
			@SuppressWarnings("unused")
			String id = request.getParameter("id");
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String xldt = DealString.toGBK(request.getParameter("xldt"));
			String dtsj = DealString.toGBK(request.getParameter("dtsj"));
			String jkjygz = DealString.toGBK(request.getParameter("jkjygz"));
			String zdgzqk = DealString.toGBK(request.getParameter("zdgzqk"));
			String gtjcl = DealString.toGBK(request.getParameter("gtjcl"));
			String qt = DealString.toGBK(request.getParameter("qt"));
			String xn1 = request.getParameter("xn");
			String xq1 = request.getParameter("xq");
			String zc = DealString.toGBK(request.getParameter("zc"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from szdtxxb where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			} else {
				sql = "delete from szdtxxb where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into szdtxxb(xymc,dtsj,xldt,jkjygz,zdgzqk,gtjcl,qt,xn,xq,zc) values(?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xymc, dtsj, xldt,
						jkjygz, zdgzqk, gtjcl, qt, xn1, xq1, zc });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("bjxzryb")) {
			// 帮教小组人员
			String id = DealString.toGBK(request.getParameter("id"));
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String nn = DealString.toGBK(request.getParameter("nn"));
			String zzmm = DealString.toGBK(request.getParameter("zzmm"));
			String zwzc = DealString.toGBK(request.getParameter("zwzc"));
			String xrgz = DealString.toGBK(request.getParameter("xrgz"));
			String xm1 = DealString.toGBK(request.getParameter("xm1"));
			String xb1 = DealString.toGBK(request.getParameter("xb1"));
			String nn1 = DealString.toGBK(request.getParameter("nn1"));
			String zzmm1 = DealString.toGBK(request.getParameter("zzmm1"));
			String zwzc1 = DealString.toGBK(request.getParameter("zwzc1"));
			String xrgz1 = DealString.toGBK(request.getParameter("xrgz1"));
			String xm2 = DealString.toGBK(request.getParameter("xm2"));
			String xb2 = DealString.toGBK(request.getParameter("xb2"));
			String nn2 = DealString.toGBK(request.getParameter("nn2"));
			String zzmm2 = DealString.toGBK(request.getParameter("zzmm2"));
			String zwzc2 = DealString.toGBK(request.getParameter("zwzc2"));
			String xrgz2 = DealString.toGBK(request.getParameter("xrgz2"));
			String xm3 = DealString.toGBK(request.getParameter("xm3"));
			String xb3 = DealString.toGBK(request.getParameter("xb3"));
			String nn3 = DealString.toGBK(request.getParameter("nn3"));
			String zzmm3 = DealString.toGBK(request.getParameter("zzmm3"));
			String zwzc3 = DealString.toGBK(request.getParameter("zwzc3"));
			String xrgz3 = DealString.toGBK(request.getParameter("xrgz3"));
			String xm4 = DealString.toGBK(request.getParameter("xm4"));
			String xb4 = DealString.toGBK(request.getParameter("xb4"));
			String nn4 = DealString.toGBK(request.getParameter("nn4"));
			String zzmm4 = DealString.toGBK(request.getParameter("zzmm4"));
			String zwzc4 = DealString.toGBK(request.getParameter("zwzc4"));
			String xrgz4 = DealString.toGBK(request.getParameter("xrgz4"));
			String zybjsl = DealString.toGBK(request.getParameter("zybjsl"));
			String fdysh = DealString.toGBK(request.getParameter("fdysh"));
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));
			String fdysj = DealString.toGBK(request.getParameter("fdysj"));
			String xyshsj = DealString.toGBK(request.getParameter("xyshsj"));
			String xxshsj = DealString.toGBK(request.getParameter("xxshsj"));

			if ((id != null)) {
				sql = "delete from bjxzryb where id ='" + id + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into bjxzryb(id,xm,xb,nn,zzmm,zwzc,xrgz,xm1,xb1,nn1,zzmm1,zwzc1,xrgz1,xm2,xb2,nn2,zzmm2,zwzc2,xrgz2,xm3,xb3,nn3,zzmm3,zwzc3,xrgz3,xm4,xb4,nn4,zzmm4,zwzc4,xrgz4,zybjsl,fdysh,xysh,xxsh,fdysj,xyshsj,xxshsj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql,
						new String[] { id, xm, xb, nn, zzmm, zwzc, xrgz, xm1,
								xb1, nn1, zzmm1, zwzc1, xrgz1, xm2, xb2, nn2,
								zzmm2, zwzc2, xrgz2, xm3, xb3, nn3, zzmm3,
								zwzc3, xrgz3, xm4, xb4, nn4, zzmm4, zwzc4,
								xrgz4, zybjsl, fdysh, xysh, xxsh, fdysj,
								xyshsj, xxshsj });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("tsqtgjb")) {
			// 长沙民政职业技术学院特殊群体跟进表
			String id = DealString.toGBK(request.getParameter("pkValue"));
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String csny = DealString.toGBK(request.getParameter("csny"));
			String bjmc = DealString.toGBK(request.getParameter("bjmc"));
			String qsh = DealString.toGBK(request.getParameter("qsh"));
			String xsdh = DealString.toGBK(request.getParameter("xsdh"));
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String jzxm = DealString.toGBK(request.getParameter("jzxm"));
			String jzsj = DealString.toGBK(request.getParameter("jzsj"));
			String bybjqk = DealString.toGBK(request.getParameter("bybjqk"));
			String xsmqqk = DealString.toGBK(request.getParameter("xsmqqk"));
			String fdysh = DealString.toGBK(request.getParameter("fdysh"));
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));

			if ((id != null)) {
				sql = "delete from tsqtgjb where id ='" + id + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into tsqtgjb(xh,xm,xb,csny,bjmc,qsh,xsdh,xymc,jzxm,jzsj,bybjqk,xsmqqk,fdysh,xysh,xxsh) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xh, xm, xb, csny,
						bjmc, qsh, xsdh, xymc, jzxm, jzsj, bybjqk, xsmqqk,
						fdysh, xysh, xxsh });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("tsqtxssqcxb")) {
			// 长沙民政职业技术学院重点特殊群体学生申请撤销表
			String tsid = DealString.toGBK(request.getParameter("pkValue"));
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String csny = DealString.toGBK(request.getParameter("csny"));
			String bjmc = DealString.toGBK(request.getParameter("bjmc"));
			String qsh = DealString.toGBK(request.getParameter("qsh"));
			String xsdh = DealString.toGBK(request.getParameter("xsdh"));
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String jzxm = DealString.toGBK(request.getParameter("jzxm"));
			String jzsj = DealString.toGBK(request.getParameter("jzsj"));
			String sqcxyy = DealString.toGBK(request.getParameter("sqcxyy"));
			String fdysh = DealString.toGBK(request.getParameter("fdysh"));
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));

			if ((tsid != null)) {
				sql = "delete from tsqtxssqcxb where tsid ='" + tsid + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into tsqtxssqcxb(xh,xm,xb,csny,bjmc,qsh,xsdh,xymc,jzxm,jzsj,sqcxyy,fdysh,xysh,xxsh) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xh, xm, xb, csny,
						bjmc, qsh, xsdh, xymc, jzxm, jzsj, sqcxyy, fdysh, xysh,
						xxsh });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xljkjyqktjb")) {
			// 长沙民政职业技术学院心理健康教育情况统计表
			String zgid = request.getParameter("pkValue");
			String xm = DealString.toGBK(request.getParameter("xm"));
			String qs = DealString.toGBK(request.getParameter("qs"));
			String js = DealString.toGBK(request.getParameter("js"));
			String hdcs = DealString.toGBK(request.getParameter("hdcs"));
			String xlwys = DealString.toGBK(request.getParameter("xlwys"));
			String zsrs = DealString.toGBK(request.getParameter("zsrs"));
			String zsnt = DealString.toGBK(request.getParameter("zsnt"));
			String zcrs = request.getParameter("zcrs");
			String jshzs = request.getParameter("jshzs");
			String wjgys = DealString.toGBK(request.getParameter("wjgys"));

			if ((zgid != null)) {
				sql = "delete from xljkjyqktjb where zgid ='" + zgid + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into xljkjyqktjb(xm,qs,js,hdcs,xlwys,zsrs,zsnt,zcrs,jshzs,wjgys) values(?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xm, qs, js, hdcs,
						xlwys, zsrs, zsnt, zcrs, jshzs, wjgys });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xljkjyhdb")) {
			// 大学生心理健康教育活动表
			String zt = DealString.toGBK(request.getParameter("zt"));
			String xy = DealString.toGBK(request.getParameter("xy"));
			String jyxs = DealString.toGBK(request.getParameter("jyxs"));
			String dd = DealString.toGBK(request.getParameter("dd"));
			String hdrq = DealString.toGBK(request.getParameter("hdrq"));
			String kssjs = DealString.toGBK(request.getParameter("kssjs"));
			String kssjf = DealString.toGBK(request.getParameter("kssjf"));
			String jssjs = DealString.toGBK(request.getParameter("jssjs"));
			String jssjf = DealString.toGBK(request.getParameter("jssjf"));
			String zcr = DealString.toGBK(request.getParameter("zcr"));
			String cyxs = DealString.toGBK(request.getParameter("cyxs"));
			String cyrs = DealString.toGBK(request.getParameter("cyrs"));
			String hdjl = DealString.toGBK(request.getParameter("hdjl"));
			String hdxg = DealString.toGBK(request.getParameter("hdxg"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				// sql = "delete from xljkjyhdb where id?";
				// isSuccess=dao.runUpdate(sql, new String[] {pkValue});
				isSuccess = true;
			} else {
				sql = "delete from xljkjyhdb where " + pk + "='" + pkValue
						+ "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into xljkjyhdb(zt,xy,jyxs,dd,hdrq,kssjs,kssjf,jssjs,jssjf,zcr,cyxs,cyrs,hdjl,hdxg) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { zt, xy, jyxs, dd,
						hdrq, kssjs, kssjf, jssjs, jssjf, zcr, cyxs, cyrs,
						hdjl, hdxg });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xytbgxxsxxb")) {
			// 特别关心学生信息
			String tbgxxslbdm = request.getParameter("tbgxxslbdm");
			String xytbgxcs = DealString
					.toGBK(request.getParameter("xytbgxcs"));
			String zxqjzysj = DealString
					.toGBK(request.getParameter("zxqjzysj"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from xytbgxxsxxb where xh=? and tbgxxslbdm=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh, tbgxxslbdm });
			} else {
				sql = "delete from xytbgxxsxxb where " + pk + "='" + pkValue
						+ "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				if (Globals.XXDM_CSMZZYJSXY.equals(xxdm)) {
					String ssbh = request.getParameter("ssbh");
					String dhhm = request.getParameter("dhhm");
					String csrq = request.getParameter("csrq");
					String jzdh = request.getParameter("jzdh");
					String lydm = request.getParameter("lydm");
					String jzxm = DealString
							.toGBK(request.getParameter("jzxm"));
					sql = "insert into xytbgxxsxxb(xh,tbgxxslbdm,xytbgxcs,zxqjzysj,ssbh,dhhm,csrq,jzdh,jzxm,lydm) values(?,?,?,?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { xh,
							tbgxxslbdm, xytbgxcs, zxqjzysj, ssbh, dhhm, csrq,
							jzdh, jzxm,lydm });
				} else {
					String xlcslb = request.getParameter("xlcslb");
					String xlwtlx = request.getParameter("xlwtlx");
					String sfkns = request.getParameter("sfkns");
					String sfdq = request.getParameter("sfdq");
					String lydm = request.getParameter("lydm");
					sql = "insert into xytbgxxsxxb(xh,tbgxxslbdm,xytbgxcs,zxqjzysj,xlcslb,xlwtlx,sfkns,sfdq,lydm) values(?,?,?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { xh,
							tbgxxslbdm, xytbgxcs, zxqjzysj, xlcslb, xlwtlx,
							sfkns, sfdq,lydm });
				}
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("wjcfb")) {
			// 处分信息
			String cflb = request.getParameter("cflb");
			String cfyy = request.getParameter("cfyy");
			String ggcflbdm = request.getParameter("ggcflbdm");
			String cfsj = dao.dateToStr(request.getParameter("cfsj"));
			String cxsj = dao.dateToStr(request.getParameter("cxsj"));
			String cfwh = request.getParameter("cfwh").trim();
			String cxwh = request.getParameter("cxwh").trim();
			String ssjg = request.getParameter("ssjg");
			String bz = request.getParameter("bz");
			String kf = request.getParameter("kf");
			String wjsj = request.getParameter("wjsj");
			String sfjw = request.getParameter("sfjw");
			String lswjjl = request.getParameter("lswjjl");
			String cfnx = request.getParameter("cfnx");
			String filePath = "";

			String ycflb = request.getParameter("ycflb");

			FormFile file = dataSearchForm.getUploadFile();
			if (file == null || file.getFileSize() <= 0) {
				filePath = dao
						.getOneRs(
								"select fjsclj from wjcfb where xh=? and cfwh=? and cfsj=?",
								new String[] { xh, cfwh, cfsj }, "fjsclj");
			}
			// String cxjg = DealString.toGBK(request.getParameter("cxjg"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from wjcfb where xh=? and cfwh=? and cfsj=?";
				// isSuccess=dao.runUpdate(sql, new String[] { xh, cfwh,
				// cfsj
				// });
				isSuccess = StandardOperation.delete("wjcfb", "xh||cfwh||cfsj",
						xh + cfwh + cfsj, request);
			} else {
				sql = "delete from wjcfb where " + pk + "='" + pkValue + "'";
				// isSuccess=dao.runUpdate(sql, new String[] {});
				isSuccess = StandardOperation.delete("wjcfb", pk, pkValue,
						request);
			}
			if (isSuccess) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
					sql = "insert into wjcfb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,xxsh,wjsj) values(?,?,?,?,?,?,?,?,?,?,?,?,'通过',?)";
					isSuccess = dao.runUpdate(sql, new String[] { xh, xn, nd,
							xq, cflb, cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz,
							wjsj });
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {// 广东白云学院违纪处分增加
					sql = "insert into wjcfb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,xxsh,xysh,xndsh) values(?,?,?,?,?,?,?,?,?,?,?,?,'通过','通过','通过')";
					isSuccess = dao.runUpdate(sql, new String[] { xh, xn, nd,
							xq, cflb, cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz });
				} else if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
					isSuccess = StandardOperation.insert("wjcfb", new String[] {
							"xh", "xn", "nd", "xq", "cflb", "cfyy", "cfsj",
							"cxsj", "cfwh", "cxwh", "ssjg", "bz", "xxsh",
							"ggcflbdm", "kf", "xysh" }, new String[] { xh, xn,
							nd, xq, cflb, cfyy, cfsj, cxsj, cfwh, cxwh, ssjg,
							bz, "通过", ggcflbdm, kf, "通过" }, request);
				} else {

					if (!Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
						// 上传处分文件 begin
						String dir = request.getRealPath("/") + "upload";
						File f = new File(dir);
						if (!f.exists()) {
							f.mkdir();
						}
						String message = "";
						String dateStr = "";
						Timestamp date = new Timestamp(System
								.currentTimeMillis());
						dateStr += date.toString().substring(0, 19);
						dateStr = dateStr.replaceAll("-", "").replaceAll(" ",
								"").replaceAll(":", "");

						if (file == null) {
							message = "附件上传失败！";
							request.setAttribute("message", message);
							request.setAttribute("inserted", "no");

						} else {
							int size = file.getFileSize();
							if (size > 0) {// 有文件上传
								String fName = dateStr + file.getFileName();
								InputStream in = file.getInputStream();
								filePath = dir + "/" + fName;
								OutputStream out = new FileOutputStream(
										filePath);
								int bytesRead = 0;
								byte[] buffer = new byte[size];
								while ((bytesRead = in.read(buffer, 0, size)) != -1) {
									out.write(buffer, 0, bytesRead);
								}
								out.close();
								in.close();
							}
						}
						// end
					}
					sql = "insert into wjcfb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,xxsh,kf) values(?,?,?,?,?,?,?,?,?,?,?,?,'通过',?)";
					// isSuccess=dao.runUpdate(sql, new String[] { xh, xn, nd,
					// xq,
					// cflb,
					// cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz });
					boolean result = isSuccess = StandardOperation.insert(
							"wjcfb", new String[] { "xh", "xn", "nd", "xq",
									"cflb", "cfyy", "cfsj", "cxsj", "cfwh",
									"cxwh", "ssjg", "bz", "xxsh", "ggcflbdm",
									"kf", "fjsclj", "ycflb", "sfjw", "lswjjl",
									"wjsj", "cfnx" }, new String[] { xh, xn,
									nd, xq, cflb, cfyy, cfsj, cxsj, cfwh, cxwh,
									ssjg, bz, "通过", ggcflbdm, kf, filePath,
									ycflb, sfjw, lswjjl, wjsj, cfnx }, request);
					if (result) {
						dao
								.runUpdate(
										"update wjcf_xsssb set cflbmc=(select cflbmc from cflbdmb where cflbdm=?), cfyymc=(select cfyymc from cfyydmb where cfyydm=?),ggcflbdm=? where xh=? and cfwh=? and cfsj=?",
										new String[] { ycflb, cfyy, cflb, xh,
												cfwh, cfsj });
					}
				}
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("wjcflsb")) {
			// 历史处分信息
			String cflb = request.getParameter("cflb");
			String cfyy = request.getParameter("cfyy");
			String cfsj = dao.dateToStr(request.getParameter("cfsj"));
			String cxsj = dao.dateToStr(request.getParameter("cxsj"));
			String cfwh = DealString.toGBK(request.getParameter("cfwh"));
			String cxwh = DealString.toGBK(request.getParameter("cxwh"));
			String ssjg = DealString.toGBK(request.getParameter("ssjg"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String lswjjl = request.getParameter("lswjjl");
			String ycflb = request.getParameter("ycflb");
			String sfjw = request.getParameter("sfjw");
			// String xm = DealString.toGBK(request.getParameter("xm"));
			// String nj = request.getParameter("nj");
			// String xb = DealString.toGBK(request.getParameter("xb"));
			// String xymc = DealString.toGBK(request.getParameter("xymc"));
			// String zymc = DealString.toGBK(request.getParameter("zymc"));
			// String bjmc = DealString.toGBK(request.getParameter("bjmc"));

			String sbsj = DateUtils.getTime();

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = true;
			} else {
				sql = "delete from wjcflsb where " + pk + "='" + pkValue + "'";
				// isSuccess=dao.runUpdate(sql, new String[] {});
				isSuccess = StandardOperation.delete("wjcflsb", pk, pkValue,
						request);
			}
			if (isSuccess) {
				// sql = "insert into
				// wjcflsb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,xm,nj,xb,xymc,zymc,bjmc)
				// values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				// isSuccess=dao.runUpdate(sql, new String[] { xh, xn, nd, xq,
				// cflb,
				// cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz, xm, nj, xb,
				// xymc, zymc, bjmc });
				sql = "insert into wjcflsb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,sbsj) values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				// isSuccess=dao.runUpdate(sql, new String[] { xh, xn, nd, xq,
				// cflb,
				// cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz });
				StandardOperation
						.insert("wjcflsb", new String[] { "xh", "xn", "nd",
								"xq", "cflb", "cfyy", "cfsj", "cxsj", "cfwh",
								"cxwh", "ssjg", "bz", "sbsj", "ycflb", "sfjw",
								"lswjjl" }, new String[] { xh, xn, nd, xq,
								cflb, cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz,
								sbsj, ycflb, sfjw, lswjjl }, request);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xsbxb")) {
			// 学生保险信息
			String tbgsdm = request.getParameter("tbgsdm");
			String tbxzdm = request.getParameter("tbxzdm");
			String tbbj = DealString.toGBK(request.getParameter("tbbj"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String tbsj = dao.dateToStr(request.getParameter("tbsj"));
			String tuibsj = dao.dateToStr(request.getParameter("tuibsj"));
			String bxpzh = request.getParameter("bxpzh");
			String bxnx = DealString.toGBK(request.getParameter("bxnx"))+"年";
			String bf = request.getParameter("bf");
			String bxdc = request.getParameter("bxdc");
			String jfbj = DealString.toGBK(request.getParameter("jfbj"));
			String sqsj = request.getParameter("sqsj");
			sqsj = Base.isNull(sqsj) ? dao.getNowTime("YYYYMMDD") : sqsj;
			// String page = request.getParameter("page");
			boolean result = false;
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				result = isSuccess = StandardOperation.delete("xsbxb",
						"xh||tbgsdm||tbxzdm||nd", xh.trim() + tbgsdm.trim()
								+ tbxzdm.trim() + nd.trim(), request);
			} else {
				result = StandardOperation
						.delete("xsbxb", pk, pkValue, request);
			}
			if (result) {
				result = isSuccess = StandardOperation.insert("xsbxb",
						new String[] { "xh", "tbgsdm", "tbxzdm", "tbbj", "bz",
								"tbsj", "tuibsj", "bxpzh", "bxnx", "bf", "nd",
								"jfbj", "xxsh", "bxdc", "sqsj" }, new String[] {
								xh, tbgsdm, tbxzdm, tbbj, bz, tbsj, tuibsj,
								bxpzh, bxnx, bf, nd, jfbj, "通过", bxdc, sqsj },
						request);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
				List bxdcList = dao.getList(
						"select dcdm,dcmc from gdnzzy_bxdcdmb order by dcdm",
						new String[] {}, new String[] { "dcdm", "dcmc" });
				request.setAttribute("bxdcList", bxdcList);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {// 广东女子
				request.setAttribute("showGdnz", "showGdnz");
			}

			request.setAttribute("result", result);

			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xshsxfb")) {
			// 学生伙食消费信息
			String nf = request.getParameter("nf");
			String yf = request.getParameter("yf");
			String xfje = request.getParameter("xfje");
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = StandardOperation.delete("xshsxfb", "xh||nf||yf",
						xh.trim() + nf.trim() + yf.trim(), request);
			} else {
				isSuccess = StandardOperation.delete("xshsxfb", pk, pkValue,
						request);
			}
			if (isSuccess) {
				isSuccess = StandardOperation.insert("xshsxfb", new String[] {
						"xh", "nf", "yf", "xfje", "bz" }, new String[] { xh,
						nf, yf, xfje, bz }, request);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("sjb")) {
			// 试卷维护
			String sjm = DealString.toGBK(request.getParameter("sjm"));
			String sjxd = request.getParameter("sjxd");
			String sjxsbj = DealString.toGBK(request.getParameter("sjxsbj"));
			String sjsm = DealString.toGBK(request.getParameter("sjsm"));
			boolean update = false;
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "insert into sjb(sjlsh,sjm,sjsm,sjxd,sjxsbj) values(sequence_sjlsh.nextval,?,?,?,?)";
				update = isSuccess = dao.runUpdate(sql, new String[] { sjm,
						sjsm, sjxd, sjxsbj });
			} else {
				sql = "update sjb set sjm=?,sjsm=?,sjxd=?,sjxsbj=? where " + pk
						+ "='" + pkValue + "'";
				update = isSuccess = dao.runUpdate(sql, new String[] { sjm,
						sjsm, sjxd, sjxsbj });
			}
			if (!update) {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("stb")) {
			// 题库维护
			String stlxdm = request.getParameter("stlxdm");
			String stndjbdm = request.getParameter("stndjbdm");
			String stjffs = request.getParameter("stjffs");
			String stfz = request.getParameter("stfz");
			String stda = DealString.toGBK(request.getParameter("stda"));
			String stxsbj = DealString.toGBK(request.getParameter("stxsbj"));
			String stnr = DealString.toGBK(request.getParameter("stnr"));
			String stdajs = DealString.toGBK(request.getParameter("stdajs"));
			if (stjffs != null && stjffs.equalsIgnoreCase("0")) {
				stda = "按选像得分";
			}
			boolean update = false;
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "insert into stb(stlsh,stnr,stlxdm,stndjbdm,stjffs,stfz,stda,stdajs,stxsbj) values(sequence_stlsh.nextval,?,?,?,?,?,?,?,?)";
				update = isSuccess = dao.runUpdate(sql, new String[] { stnr,
						stlxdm, stndjbdm, stjffs, stfz, stda, stdajs, stxsbj });
			} else {
				sql = "update stb set stnr=?,stlxdm=?,stndjbdm=?,stjffs=?,stfz=?,stda=?,stdajs=?,stxsbj=? where "
						+ pk + "='" + pkValue + "'";
				update = isSuccess = dao.runUpdate(sql, new String[] { stnr,
						stlxdm, stndjbdm, stjffs, stfz, stda, stdajs, stxsbj });
			}
			if (!update) {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xxb")) {
			// 选项维护
			String stlsh = request.getParameter("stlsh");
			String xxxh = DealString.toGBK(request.getParameter("xxxh"));
			String xxnr = DealString.toGBK(request.getParameter("xxnr"));
			String xxfz = request.getParameter("xxfz");
			String xxxsbj = DealString.toGBK(request.getParameter("xxxsbj"));
			boolean update = false;
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "insert into xxb(xxlsh,stlsh,xxxh,xxnr,xxfz,xxxsbj) values(sequence_xxlsh.nextval,?,?,?,?,?)";
				update = isSuccess = dao.runUpdate(sql, new String[] { stlsh,
						xxxh, xxnr, xxfz, xxxsbj });
			} else {
				sql = "update xxb set stlsh=?,xxxh=?,xxnr=?,xxfz=?,xxxsbj=? where "
						+ pk + "='" + pkValue + "'";
				update = isSuccess = dao.runUpdate(sql, new String[] { stlsh,
						xxxh, xxnr, xxfz, xxxsbj });
			}
			if (!update) {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("sjstb")) {
			// 组卷维护
			New_Random_ID newId = new New_Random_ID();
			String sjlsh = request.getParameter("sjlsh");
			String yxstlbStr = DealString.toGBK(request
					.getParameter("yxstlbStr"));
			String yxstlb[] = yxstlbStr.split("-");
			boolean update = false;
			sql = "delete from sjstb where sjlsh=?";
			update = isSuccess = dao.runUpdate(sql, new String[] { sjlsh });
			for (int i = 0; i < yxstlb.length; i++) {
				sql = "insert into sjstb(SJLSH,STLSH,STXH,XN_ID) values (?,?,?,?)";
				update = isSuccess = dao.runUpdate(sql, new String[] { sjlsh,
						yxstlb[i], Integer.toString(i + 1),
						newId.new_xnid("sjstb") });
			}
			if (!update) {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("rdsqb")) {
			// 入党申请信息
			String djsqsj = dao.dateToStr(request.getParameter("djsqsj"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable
						+ " where xn=? and xq=? and xh=?";
				isSuccess = dao.runUpdate(sql, new String[] { xn, xq, xh });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into " + realTable
						+ "(nd,xn,xq,xh,djsqsj,bz) values(?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						djsqsj, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			// =============== begin 浙江传媒学院 骆嘉伟 2009/3/4 =============
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				DtjszjcmService service = new DtjszjcmService();
				if (!service.addRdjjfz(xn, xq, xh, nd, djsqsj, pkValue, pk,
						request)) {
					dataSearchForm.setErrMsg("sdf");

				}
			}
			// =============== end 浙江传媒学院 骆嘉伟 2009/3/4 ============
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("zgkd_rdsqb")) {
			// =============== begin 中国矿业大学 骆嘉伟 2009/4/9 =============
			// 入党申请信息
			String djsqsj = dao.dateToStr(request.getParameter("djsqsj"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable + " where xh=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			String rdjjfzsj = dataSearchForm.getRdjjfzsj();
			String rdjjfzpxbh = dataSearchForm.getRdjjfzpxbh();
			String fzdxsj = dataSearchForm.getFzdxsj();
			String fzdxpxbh = dataSearchForm.getFzdxpxbh();
			if (isSuccess) {
				sql = "insert into "
						+ realTable
						+ "(xh,djsqsj,bz,rdjjfzsj,rdjjfzpxbh,fzdxsj,fzdxpxbh) values(?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xh, djsqsj, bz,
						rdjjfzsj, rdjjfzpxbh, fzdxsj, fzdxpxbh });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			// =============== end 中国矿业大学 骆嘉伟 2009/4/9 =============

			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("sxhbb")) {
			// 思想汇报信息
			String djsj = dao.dateToStr(request.getParameter("djsj"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			if (xn == null) {
				xn = " ";
			}
			if (xq == null) {
				xq = " ";
			}
			if (nd == null) {
				nd = " ";
			}

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable + " where xh=? and djsj=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh, djsj });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into " + realTable
						+ "(nd,xn,xq,xh,djsj,bz) values(?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						djsj, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xsbzxxb")) {
			// 学生补助信息
			String bzsj = dao.dateToStr(request.getParameter("bzsj"));
			String bzlxdm = request.getParameter("bzlxdm");
			String bzed = request.getParameter("bzed");
			String bzyy = DealString.toGBK(request.getParameter("bzyy"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable
						+ " where xn=? and xq=? and xh=? and bzlxdm=?";
				isSuccess = dao.runUpdate(sql, new String[] { xn, xq, xh,
						bzlxdm });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,bzsj,bzlxdm,bzed,bzyy,bz) values(?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						bzsj, bzlxdm, bzed, bzyy, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("jxjgb")) {
			// 军训结果信息
			String sfhg = DealString.toGBK(request.getParameter("sfhg"));
			String sfjjfz = DealString.toGBK(request.getParameter("sfjjfz"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String bxsfhg = DealString.toGBK(request.getParameter("bxsfhg"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = StandardOperation.delete(realTable, new String[] {
						"xn", "xq", "xh" }, new String[] { xn, xq, xh },
						request);
			} else {
				isSuccess = StandardOperation.delete(realTable, pk, pkValue,
						request);
			}
			if (isSuccess) {
				StandardOperation
						.insert(realTable, new String[] { "nd", "xn", "xq",
								"xh", "sfhg", "sfjjfz", "bz", "bxsfhg" },
								new String[] { nd, xn, xq, xh, sfhg, sfjjfz,
										bz, bxsfhg }, request);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xsgbxxb")) {
			// 学生干部信息
			String rzbmdm = request.getParameter("rzbmdm");
			String drzw = DealString.toGBK(request.getParameter("drzw"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String khjg = DealString.toGBK(request.getParameter("khjg"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from "
						+ realTable
						+ " where xn=? and xq=? and xh=? and rzbmdm=? and drzw=?";
				isSuccess = dao.runUpdate(sql, new String[] { xn, xq, xh,
						rzbmdm, drzw });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,rzbmdm,drzw,bz,khjg) values(?,?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq,
							xh, rzbmdm, drzw, bz, khjg });
				} else {
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,rzbmdm,drzw,bz) values(?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq,
							xh, rzbmdm, drzw, bz });
				}
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("zyzfwdjb")) {
			// 志愿者服务信息
			String fwl = DealString.toGBK(request.getParameter("fwl"));
			String fwsj = dao.dateToStr(request.getParameter("fwsj"));
			String fwnr = DealString.toGBK(request.getParameter("fwnr"));
			String khjg = DealString.toGBK(request.getParameter("khjg"));
			String zyzbh = DealString.toGBK(request.getParameter("zyzbh"));
			String drzw = DealString.toGBK(request.getParameter("drzw"));
			String cjzyzfwsj = request.getParameter("cjzyzfwsj");
			String[] delSql = new String[1];
			String[] intSql = new String[1];
			String[] uptSql = new String[1];
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable + " where xh='" + xh
						+ "' and fwsj='" + fwsj + "'";
				delSql[0] = sql;
				// isSuccess = isSuccess=dao.runUpdate(sql, new String[] { xh,
				// fwsj
				// });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				delSql[0] = sql;
				// isSuccess = isSuccess=dao.runUpdate(sql, new String[] {});
			}
			// if (isSuccess) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,fwnr,fwsj,fwl,khjg,zyzbh,drzw) values('"
						+ nd + "','" + xn + "','" + xq + "','" + xh + "','"
						+ fwnr + "','" + fwsj + "','" + fwl + "','" + khjg
						+ "','" + zyzbh + "','" + drzw + "')";
				intSql[0] = sql;
				// isSuccess = isSuccess=dao.runUpdate(sql, new String[] { nd,
				// xn,
				// xq, xh,
				// fwnr,
				// fwsj, fwl, khjg, zyzbh, drzw });
				// isSuccess = isSuccess=dao.runUpdate(" update zyzfwdjb set
				// cjzyzfwsj=?
				// ",new String[]{cjzyzfwsj});
			} else {
				sql = "insert into " + realTable
						+ "(nd,xn,xq,xh,fwnr,fwsj,fwl,zyzbh,drzw) values('"
						+ nd + "','" + xn + "','" + xq + "','" + xh + "','"
						+ fwnr + "','" + fwsj + "','" + fwl + "','" + zyzbh
						+ "','" + drzw + "')";
				// isSuccess = isSuccess=dao.runUpdate(sql, new String[] { nd,
				// xn,
				// xq, xh,
				// fwnr,
				// fwsj, fwl, zyzbh, drzw });
				intSql[0] = sql;
			}
			// } else {
			// dataSearchForm.setErrMsg("sdf");
			// 
			// }
			uptSql[0] = "update zyzfwdjb set cjzyzfwsj='" + cjzyzfwsj
					+ "' where xh='" + xh + "' ";
			String[] exesql = dao.unionArray(delSql, intSql);
			exesql = dao.unionArray(exesql, uptSql);
			int[] array = null;
			array = dao.runBatch(exesql);
			isSuccess = dao.checkBatch(array);
			if (!isSuccess) {

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("sthdxxb")) {
			// 社团活动信息
			String stdm = request.getParameter("stdm");
			String jrsj = dao.dateToStr(request.getParameter("jrsj"));
			String tcsj = dao.dateToStr(request.getParameter("tcsj"));
			String hydj = DealString.toGBK(request.getParameter("hydj"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String rzqk = DealString.toGBK(request.getParameter("rzqk"));
			String khjg = DealString.toGBK(request.getParameter("khjg"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable
						+ " where xh=? and stdm=? and jrsj=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh, stdm, jrsj });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,stdm,jrsj,tcsj,bz,rzqk,khjg) values(?,?,?,?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq,
							xh, stdm, jrsj, tcsj, bz, rzqk, khjg });
				} else {
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,stdm,jrsj,tcsj,hydj,bz) values(?,?,?,?,?,?,?,?,?)";
					isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq,
							xh, stdm, jrsj, tcsj, hydj, bz });
				}
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("ssxxb")) {
			// 房源信息
			String ssbh = DealString.toGBK(request.getParameter("ssbh"));
			String lddm = DealString.toGBK(request.getParameter("lddm"));
			String qsh = DealString.toGBK(request.getParameter("qsh"));
			String qsdh = DealString.toGBK(request.getParameter("qsdh"));
			String cws = DealString.toGBK(request.getParameter("cws"));
			String fpbj = DealString.toGBK(request.getParameter("fpbj"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String shq = DealString.toGBK(request.getParameter("shq"));
			String dxdh = DealString.toGBK(request.getParameter("dxdh"));
			String ttdh = DealString.toGBK(request.getParameter("ttdh"));
			String sfbz = DealString.toGBK(request.getParameter("sfbz"));
			String wxport = DealString.toGBK(request.getParameter("wxport"));
			String cs = DealString.toGBK(request.getParameter("cs"));
			String sffqfj = DealString.toGBK(request.getParameter("sffqfj"));
			String xlcws = DealString.toGBK(request.getParameter("xlcws"));
			// xlcws = Base.isNull(xlcws)?"1":xlcws;

			// String[] sqlArr = new String[4];

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = StandardOperation.delete(realTable, "ssbh", ssbh,
						request);
			} else {
				isSuccess = StandardOperation.delete(realTable, pk, pkValue,
						request);
			}
			if (isSuccess) {
				// if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				if (!Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
					isSuccess = StandardOperation.insert(realTable,
							new String[] { "ssbh", "lddm", "qsh", "qsdh",
									"cws", "fpbj", "bz", "shq", "dxdh", "ttdh",
									"wxport", "cs", "sfbz", "sffqfj" },
							new String[] { ssbh, lddm, qsh, qsdh, cws, fpbj,
									bz, shq, dxdh, ttdh, wxport, cs, sfbz,
									sffqfj }, request);
				} else {
					isSuccess = StandardOperation
							.insert(realTable, new String[] { "ssbh", "lddm",
									"qsh", "qsdh", "cws", "fpbj", "bz", "shq",
									"dxdh", "ttdh", "wxport", "cs", "sfbz",
									"sffqfj", "xlcws" }, new String[] { ssbh,
									lddm, qsh, qsdh, cws, fpbj, bz, shq, dxdh,
									ttdh, wxport, cs, sfbz, sffqfj, xlcws },
									request);
				}
				request.setAttribute("isSuccess", isSuccess);
				request.setAttribute("done", isSuccess);
				request.setAttribute("result", isSuccess);
				// }
				// else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				// isSuccess=StandardOperation.insert(realTable, new String[] {
				// "ssbh", "lddm", "qsh", "qsdh", "cws", "fpbj",
				// "bz" , "cs","sfbz"}, new String[] { ssbh, lddm, qsh,
				// qsdh,
				// cws, fpbj, bz, cs,sfbz }, request);
				// } else {
				// isSuccess=StandardOperation.insert(realTable, new String[] {
				// "ssbh", "lddm", "qsh", "qsdh", "cws", "fpbj",
				// "bz","cs","sfbz" }, new String[] { ssbh, lddm, qsh, qsdh,
				// cws, fpbj, bz,cs,sfbz }, request);
				// }
				// 更新床位数据
				String[] sqls = new String[Integer.parseInt(cws) + 1];
				sqls[0] = "delete from cwxxb where ssbh='" + ssbh + "' ";
				for (int i = 1; i <= Integer.parseInt(cws); i++) {
					sql = "insert into cwxxb (ssbh,cwh) values('" + ssbh
							+ "','" + i + "')";
					sqls[i] = sql;
				}
				dao.runBatch(sqls);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xszsxxb")) {
			// 住宿信息维护
			String ssbh = DealString.toGBK(request.getParameter("ssbh"));
			String rzrq = dao.dateToStr(request.getParameter("rzrq"));
			String jzrq = dao.dateToStr(request.getParameter("jzrq"));
			String cwh = DealString.toGBK(request.getParameter("cwh"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String zsf = DealString.toGBK(request.getParameter("zsf"));
			String dsjssf = DealString.toGBK(request.getParameter("dsjssf"));
			String gyqk = DealString.toGBK(request.getParameter("gyqk"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = StandardOperation.delete(realTable, "xh", xh,
						request);
			} else {
				isSuccess = StandardOperation.delete(realTable, pk, pkValue,
						request);
			}
			if (isSuccess) {
				isSuccess = StandardOperation.insert(realTable, new String[] {
						"xh", "ssbh", "rzrq", "jzrq", "cwh", "bz", "zsf",
						"dsjssf", "gyqk" }, new String[] { xh, ssbh, rzrq,
						jzrq, cwh, bz, zsf, dsjssf, gyqk }, request);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("mrzbjlb")) {
			// 每日值班记录
			String sj = request.getParameter("sj");
			String lddm = request.getParameter("lddm");
			String zbrydm = DealString.toGBK(request.getParameter("zbrydm"));
			String zbrydm1 = request.getParameter("zbrydm1");
			String tq = DealString.toGBK(request.getParameter("tq"));
			String zbjl = DealString.toGBK(request.getParameter("zbjl"));
			String tfsjjcl = DealString.toGBK(request.getParameter("tfsjjcl"));
			String zw = DealString.toGBK(request.getParameter("zw"));
			String bmdm = request.getParameter("bmdm");
			String xqdm = dataSearchForm.getXqdm();
			String zbrlx = DealString.toGBK(request.getParameter("zbrlx"));
			String dgsj = DealString.toGBK(request.getParameter("dgsj"));
			String lgsj = DealString.toGBK(request.getParameter("lgsj"));

			xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
					"xxmc");
			boolean result = false;
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				result = isSuccess = StandardOperation.delete(realTable,
						new String[] { "sj", "zbrydm" }, new String[] { sj,
								zbrydm }, request);
			} else {
				result = isSuccess = StandardOperation.delete(realTable, pk,
						pkValue, request);
			}
			if (result) {
				result = isSuccess = StandardOperation.insert(realTable,
						new String[] { "sj", "lddm", "zbrydm", "zbrydm1", "tq",
								"zbjl", "tfsjjcl", "xqdm", "zbrlx", "dgsj",
								"lgsj", "zw", "bmdm" }, new String[] { sj,
								lddm, zbrydm, zbrydm1, tq, zbjl, tfsjjcl, xqdm,
								zbrlx, dgsj, lgsj, zw, bmdm }, request);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("result", result);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("yzzbhzb")) {
			// 一周值班汇总
			String qssj = request.getParameter("qssj");
			String jssj = request.getParameter("jssj");
			String lddm = request.getParameter("lddm");
			String zyzbjlhz = DealString
					.toGBK(request.getParameter("zyzbjlhz"));
			String xgbmclqk = DealString
					.toGBK(request.getParameter("xgbmclqk"));
			String yldps = DealString.toGBK(request.getParameter("yldps"));
			String hzr = DealString.toGBK(dataSearchForm.getHzr());
			String xqdm = dataSearchForm.getXqdm();
			boolean result = false;
			xxmc = dao.getOneRs("select xxmc from xtszb ", new String[] {},
					"xxmc");
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
					result = isSuccess = StandardOperation.delete(realTable,
							new String[] { "qssj", "jssj", "hzr" },
							new String[] { qssj, jssj, hzr }, request);
				} else {
					result = isSuccess = StandardOperation.delete(realTable,
							new String[] { "qssj", "jssj", "lddm" },
							new String[] { qssj, jssj, lddm }, request);
				}
			} else {
				result = isSuccess = StandardOperation.delete(realTable, pk,
						pkValue, request);
			}
			if (result) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
					result = isSuccess = StandardOperation.insert(realTable,
							new String[] { "qssj", "jssj", "hzr", "zyzbjlhz",
									"xgbmclqk", "yldps", "xqdm" },
							new String[] { qssj, jssj, hzr, zyzbjlhz, xgbmclqk,
									yldps, xqdm }, request);
				} else {
					result = isSuccess = StandardOperation.insert(realTable,
							new String[] { "qssj", "jssj", "lddm", "zyzbjlhz",
									"xgbmclqk", "yldps" }, new String[] { qssj,
									jssj, lddm, zyzbjlhz, xgbmclqk, yldps },
							request);
				}

			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("result", result);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xszffb")
				|| realTable.equalsIgnoreCase("xhffb")) {
			// 学生证发放维护和校徽维护
			String ffsj = dao.dateToStr(request.getParameter("ffsj"));
			String jbr = DealString.toGBK(request.getParameter("jbr"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable + " where xh=? and ffsj=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh, ffsj });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into " + realTable
						+ "(nd,xn,xq,xh,ffsj,jbr,bz) values(?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						ffsj, jbr, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("hcyhkffb")) {
			// 火车优惠卡发放维护
			String ffsj = dao.dateToStr(request.getParameter("ffsj"));
			String jbr = DealString.toGBK(request.getParameter("jbr"));
			String jtdz = DealString.toGBK(request.getParameter("jtdz"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable + " where xh=? and ffsj=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh, ffsj });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,ffsj,jbr,jtdz,bz) values(?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						ffsj, jbr, jtdz, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xszbbb")
				|| realTable.equalsIgnoreCase("hcyhkbbb")
				|| realTable.equalsIgnoreCase("yktbbb")
				|| realTable.equalsIgnoreCase("xhbbb")) {
			// 学生证补办,火车优惠卡补办,一卡通补办,校徽补办维护
			// String sqsj = dao.dateToStr(dao.dateToStr(request
			// .getParameter("sqsj")));
			String jbr = DealString.toGBK(request.getParameter("jbr"));
			String bbsj = dao.dateToStr(request.getParameter("bbsj"));
			String sflq = DealString.toGBK(request.getParameter("sflq"));
			String bbyy = DealString.toGBK(request.getParameter("bbyy"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String sfzh = DealString.toGBK(request.getParameter("sfzh"));
			String syd = DealString.toGBK(request.getParameter("syd"));
			// String xz = DealString.toGBK(request.getParameter("xz"));
			// String csrq = DealString.toGBK(request.getParameter("csrq"));
			// String hczm = DealString.toGBK(request.getParameter("hczm"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = true;
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}

			if (isSuccess) {
				String sqllx = "SELECT * FROM xszbbb WHERE xh||sqsj=?";
				List xhsqsj = dao.getList(sqllx, new String[] { xh + bbsj },
						new String[] { "xh", "sqsj" });
				if (xhsqsj.size() > 0) {
					request.setAttribute("tgcg", "tgcg");
				} else {
					request.setAttribute("tgcg", "tgcg1");
					if (xxdm == "10690" || "10690".equalsIgnoreCase(xxdm)) {
						if (realTable.equalsIgnoreCase("xszbbb")) {
							String fdysh = "";
							String xxsh = "";
							if (!(pkValue.equalsIgnoreCase(""))) {
								fdysh = "通过";
								xxsh = "通过";
							} else {
								fdysh = "通过";
								xxsh = "未审核";
							}
							sql = "insert into "
									+ realTable
									+ "(nd,xn,xq,xh,bbsj,jbr,sflq,bbyy,bz,sfzh,syd,fdysh,xxsh) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
							isSuccess = dao.runUpdate(sql, new String[] { nd,
									xn, xq, xh, bbsj, jbr, sflq, bbyy, bz,
									sfzh, syd, fdysh, xxsh });
						} else {
							String fdysh = "";
							String xxsh = "";
							if (!(pkValue.equalsIgnoreCase(""))) {
								fdysh = "通过";
								xxsh = "通过";
							} else {
								fdysh = "通过";
								xxsh = "未审核";
							}
							sql = "insert into "
									+ realTable
									+ "(nd,xn,xq,xh,bbsj,jbr,sflq,bbyy,bz,fdysh,xxsh) values(?,?,?,?,?,?,?,?,?,?,?)";
							isSuccess = dao.runUpdate(sql, new String[] { nd,
									xn, xq, xh, bbsj, jbr, sflq, bbyy, bz,
									fdysh, xxsh });
						}
					} else {
						if (realTable.equalsIgnoreCase("xszbbb")) {
							sql = "insert into "
									+ realTable
									+ "(nd,xn,xq,xh,bbsj,jbr,sflq,bbyy,bz,sfzh,syd) values(?,?,?,?,?,?,?,?,?,?,?)";
							isSuccess = dao.runUpdate(sql, new String[] { nd,
									xn, xq, xh, bbsj, jbr, sflq, bbyy, bz,
									sfzh, syd });
						} else {
							sql = "insert into "
									+ realTable
									+ "(nd,xn,xq,xh,bbsj,jbr,sflq,bbyy,bz) values(?,?,?,?,?,?,?,?,?)";
							isSuccess = dao.runUpdate(sql, new String[] { nd,
									xn, xq, xh, bbsj, jbr, sflq, bbyy, bz });
						}
					}
				}
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("hcyhkczb")) {
			// 火车优惠卡冲值维护
			String jbr = DealString.toGBK(request.getParameter("jbr"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String czsj = request.getParameter("czsj");
			String czje = request.getParameter("czje");

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				isSuccess = true;
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,jbr,bz,czsj,czje) values(?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						jbr, bz, czsj, czje });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xssztzb")) {
			// 学生素质拓展维护
			String sxzzddsy = DealString
					.toGBK(request.getParameter("sxzzddsy"));
			String shsjzyfw = DealString
					.toGBK(request.getParameter("shsjzyfw"));
			String xskjcxcy = DealString
					.toGBK(request.getParameter("xskjcxcy"));
			String whyssxfz = DealString
					.toGBK(request.getParameter("whyssxfz"));
			String sthdshgz = DealString
					.toGBK(request.getParameter("sthdshgz"));
			String jnpxqt = DealString.toGBK(request.getParameter("jnpxqt"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable
						+ " where xh=? and xn=? and xq=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh, xn, xq });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,sxzzddsy,shsjzyfw,xskjcxcy,whyssxfz,sthdshgz,jnpxqt,bz) values(?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
						sxzzddsy, shsjzyfw, xskjcxcy, whyssxfz, sthdshgz,
						jnpxqt, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("hcccb")) {
			// 车次信息维护
			String cc = DealString.toGBK(request.getParameter("cc"));
			String qdz = request.getParameter("qdz");
			String zdz = request.getParameter("zdz");
			String kcsj = request.getParameter("kcsj");
			String ddsj = request.getParameter("ddsj");
			String yxsj = request.getParameter("yxsj");
			String pj = request.getParameter("pj");
			String tkz = DealString.toGBK(request.getParameter("tkz"));
			String dqzt = DealString.toGBK(request.getParameter("dqzt"));
			boolean result = false;
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable + " where cc=?";
				result = isSuccess = dao.runUpdate(sql, new String[] { cc });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				result = isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (result) {
				sql = "insert into "
						+ realTable
						+ "(cc,qdz,zdz,kcsj,ddsj,yxsj,pj,tkz,dqzt) values(?,?,?,?,?,?,?,?,?)";
				result = isSuccess = dao.runUpdate(sql, new String[] { cc, qdz,
						zdz, kcsj, ddsj, yxsj, pj, tkz, dqzt });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("result", result);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("bks_xsszjbxx")) {
			// 学生信息维护
			// WebServiceClientForXmlgxy ws = new
			// WebServiceClientForXmlgxy();
			String lxdh1 = request.getParameter("lxdh1");
			// String lxdh2 = request.getParameter("lxdh2");
			String sjhm = request.getParameter("sjhm");
			String email = request.getParameter("email");
			String ssbh = request.getParameter("ssbh");
			String cwh = request.getParameter("cwh");
			xh = request.getParameter("xh");
			boolean flag = false;
			if (pkValue != null && !pkValue.trim().equalsIgnoreCase("")) {
				sql = "select xh from xsfzxxb where xh=?";
				String str = dao.getOneRs(sql, new String[] { xh }, "xh");
				if (str.equalsIgnoreCase("")) {
					flag = isSuccess = StandardOperation.insert("xsfzxxb",
							new String[] { "xh", "lxdh1", "sjhm", "email" },
							new String[] { xh, lxdh1, sjhm, email }, request);

				} else {
					flag = isSuccess = StandardOperation.update("xsfzxxb",
							new String[] { "lxdh1", "sjhm", "email" },
							new String[] { lxdh1, sjhm, email }, "xh", xh,
							request);

				}
				sql = "select xh from xsxxb where xh=?";
				str = dao.getOneRs(sql, new String[] { xh }, "xh");
				if (str.equalsIgnoreCase("")) {
					flag = StandardOperation
							.update(
									"xsxxb",
									"insert into xsxxb(xh,xm,xb,nj,xydm,xy,zydm,zymc,bjdm,bjmc)"
											+ "(select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_xsjbxx where xh='"
											+ xh + "')", request);

				}
				flag = isSuccess = StandardOperation.update("xsxxb",
						new String[] { "lxdh", "sjhm", "dzyx" }, new String[] {
								lxdh1, sjhm, email }, "xh", xh, request);

				if (!ssbh.equalsIgnoreCase("")) {
					sql = "select xh from xszsxxb where xh=?";
					str = dao.getOneRs(sql, new String[] { xh }, "xh");
					if (str.equalsIgnoreCase("")) {
						flag = isSuccess = StandardOperation.insert("xszsxxb",
								new String[] { "xh", "ssbh", "cwh" },
								new String[] { xh, ssbh, cwh }, request);
					} else {
						flag = isSuccess = StandardOperation.update("xszsxxb",
								new String[] { "ssbh", "cwh" }, new String[] {
										ssbh, cwh }, "xh", xh, request);
					}
				}
				request.setAttribute("result", flag);
			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("qcccb")) {
			// 车次信息维护
			String cc = DealString.toGBK(request.getParameter("cc"));
			String qdz = request.getParameter("qdz");
			String zdz = request.getParameter("zdz");
			String kcsj = request.getParameter("kcsj");
			String ddsj = request.getParameter("ddsj");
			String yxsj = request.getParameter("yxsj");
			String pj = request.getParameter("pj");
			String tkz = DealString.toGBK(request.getParameter("tkz"));
			String dqzt = DealString.toGBK(request.getParameter("dqzt"));

			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from " + realTable + " where cc=?";
				isSuccess = dao.runUpdate(sql, new String[] { cc });
			} else {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into "
						+ realTable
						+ "(cc,qdz,zdz,kcsj,ddsj,yxsj,pj,tkz,dqzt) values(?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { cc, qdz, zdz,
						kcsj, ddsj, yxsj, pj, tkz, dqzt });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xszxbzb")) {

			String bysj = DealString.toGBK(request.getParameter("bysj"));
			String jtdz = DealString.toGBK(request.getParameter("jtdz"));
			String yzbm = DealString.toGBK(request.getParameter("yzbm"));
			String jtcy1_xm = DealString
					.toGBK(request.getParameter("jtcy1_xm"));
			String jtcy1_gx = DealString
					.toGBK(request.getParameter("jtcy1_gx"));
			String jtcy1_gzdw = DealString.toGBK(request
					.getParameter("jtcy1_gzdw"));
			String jtcy1_ysr = DealString.toGBK(request
					.getParameter("jtcy1_ysr"));
			String jtcy2_xm = DealString
					.toGBK(request.getParameter("jtcy2_xm"));
			String jtcy2_gx = DealString
					.toGBK(request.getParameter("jtcy2_gx"));
			String jtcy2_gzdw = DealString.toGBK(request
					.getParameter("jtcy2_gzdw"));
			String jtcy2_ysr = DealString.toGBK(request
					.getParameter("jtcy2_ysr"));
			String jtcy3_xm = DealString
					.toGBK(request.getParameter("jtcy3_xm"));
			String jtcy3_gx = DealString
					.toGBK(request.getParameter("jtcy3_gx"));
			String jtcy3_gzdw = DealString.toGBK(request
					.getParameter("jtcy3_gzdw"));
			String jtcy3_ysr = DealString.toGBK(request
					.getParameter("jtcy3_ysr"));
			String jtcy4_xm = DealString
					.toGBK(request.getParameter("jtcy4_xm"));
			String jtcy4_gx = DealString
					.toGBK(request.getParameter("jtcy4_gx"));
			String jtcy4_gzdw = DealString.toGBK(request
					.getParameter("jtcy4_gzdw"));
			String jtcy4_ysr = DealString.toGBK(request
					.getParameter("jtcy4_ysr"));
			String jtcy5_xm = DealString
					.toGBK(request.getParameter("jtcy5_xm"));
			String jtcy5_gx = DealString
					.toGBK(request.getParameter("jtcy5_gx"));
			String jtcy5_gzdw = DealString.toGBK(request
					.getParameter("jtcy5_gzdw"));
			String jtcy5_ysr = DealString.toGBK(request
					.getParameter("jtcy5_ysr"));
			String jttgje = DealString.toGBK(request.getParameter("jttgje"));
			String zxje = DealString.toGBK(request.getParameter("zxje"));
			String jxje = DealString.toGBK(request.getParameter("jxje"));
			String qgzxje = DealString.toGBK(request.getParameter("qgzxje"));
			String xnwxdkje = DealString
					.toGBK(request.getParameter("xnwxdkje"));
			String qtsrje = DealString.toGBK(request.getParameter("qtsrje"));
			String zxdkje = DealString.toGBK(request.getParameter("zxdkje"));
			String zxdksj = DealString.toGBK(request.getParameter("zxdksj"));
			String yffzxdkje = DealString.toGBK(request
					.getParameter("yffzxdkje"));
			String yffzxdksj = DealString.toGBK(request
					.getParameter("yffzxdksj"));
			String sqzzly = DealString.toGBK(request.getParameter("sqzzly"));
			String zzff1 = DealString.toGBK(request.getParameter("zzff1"));
			String zzff1qsje = DealString.toGBK(request
					.getParameter("zzff1qsje"));
			String zzff1jsje = DealString.toGBK(request
					.getParameter("zzff1jsje"));
			String xxsh = DealString.toGBK(request.getParameter("yesNo"));

			if (xxsh == null || xxsh.equalsIgnoreCase("")) {
				xxsh = "未审核";
			}

			// String bzdm = dao.getOneRs("select knbzdm from knbzdmb where
			// knbzmc like '专项%'",
			// new String[]{}, new String[]{"knbzdm"})[0];
			String bzdm = "1";
			String bzmc = "专项补助";

			sql = "delete from " + realTable
					+ " where xh=? and nd=? and bzdm=? ";
			isSuccess = dao.runUpdate(sql, new String[] { xh, nd, bzdm });

			sql = "insert into xszxbzb(bzdm,bzmc,xn,nd,xh,bysj,jtdz,yzbm,"
					+ "jtcy1_xm,jtcy1_gx,jtcy1_gzdw,jtcy1_ysr,"
					+ "jtcy2_xm,jtcy2_gx,jtcy2_gzdw,jtcy2_ysr,"
					+ "jtcy3_xm,jtcy3_gx,jtcy3_gzdw,jtcy3_ysr,"
					+ "jtcy4_xm,jtcy4_gx,jtcy4_gzdw,jtcy4_ysr,"
					+ "jtcy5_xm,jtcy5_gx,jtcy5_gzdw,jtcy5_ysr,"
					+ "jttgje,zxje,jxje,qgzxje,xnwxdkje,qtsrje,zxdkje,zxdksj,yffzxdkje,yffzxdksj,"
					+ "sqzzly,zzff1,zzff1qsje,zzff1jsje,xq,xxsh) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			String[] input = new String[] { bzdm, bzmc, xn, nd, xh, bysj, jtdz,
					yzbm, jtcy1_xm, jtcy1_gx, jtcy1_gzdw, jtcy1_ysr, jtcy2_xm,
					jtcy2_gx, jtcy2_gzdw, jtcy2_ysr, jtcy3_xm, jtcy3_gx,
					jtcy3_gzdw, jtcy3_ysr, jtcy4_xm, jtcy4_gx, jtcy4_gzdw,
					jtcy4_ysr, jtcy5_xm, jtcy5_gx, jtcy5_gzdw, jtcy5_ysr,
					jttgje, zxje, jxje, qgzxje, xnwxdkje, qtsrje, zxdkje,
					zxdksj, yffzxdkje, yffzxdksj, sqzzly, zzff1, zzff1qsje,
					zzff1jsje, xq, xxsh };

			isSuccess = dao.runUpdate(sql, input);

			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("lstdb")) {
			String jtdz = DealString.toGBK(request.getParameter("jtdz"));
			String je = DealString.toGBK(request.getParameter("je"));
			sql = "delete from " + realTable + " where xh=? and nd=? ";
			isSuccess = dao.runUpdate(sql, new String[] { xh, nd });
			sql = "insert into " + realTable + " (xh,jtdz,je,nd,xn,xq) "
					+ " values(?,?,?,?,?,?)";
			String[] input = new String[] { xh, jtdz, je, nd, xn, xq };
			isSuccess = dao.runUpdate(sql, input);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xfcjb")) {
			String je = DealString.toGBK(request.getParameter("je"));
			String qflxdm = dataSearchForm.getQflxdm();
			String bz = DealString.toGBK(dataSearchForm.getBz());

			sql = "delete from "
					+ realTable
					+ " where xh = ? and nd = ? and xn = ? and xq = ? and qflxdm = ?";
			isSuccess = dao.runUpdate(sql, new String[] { xh, nd, xn, xq,
					qflxdm });
			sql = "insert into " + realTable + " (xh,je,nd,xn,xq,qflxdm,bz) "
					+ " values(?,?,?,?,?,?,?)";
			String[] input = new String[] { xh, je, nd, xn, xq, qflxdm, bz };
			isSuccess = dao.runUpdate(sql, input);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xfhjb")) {
			String hjje = DealString.toGBK(request.getParameter("hjje"));
			String hjqx = DealString.toGBK(request.getParameter("hjqx"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			sql = "delete from " + realTable + " where xh=? and nd=? ";
			isSuccess = dao.runUpdate(sql, new String[] { xh, nd });
			sql = "insert into " + realTable + " (xh,hjje,nd,xn,xq,hjqx,bz) "
					+ " values(?,?,?,?,?,?,?)";
			String[] input = new String[] { xh, hjje, nd, xn, xq, hjqx, bz };
			isSuccess = dao.runUpdate(sql, input);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("gywxglb")) {

			String lddm = DealString.toGBK(dataSearchForm.getLddm());
			// String userName =
			// session.getAttribute("userName").toString();
			// String fzld = gyglDao.getLddmxXx( userName);
			// 公寓辅导员判断begin
			// String lddmStr = gyglDao.getLddmxXx(userName);
			// String isGyFdy = "no";
			// if(!Base.isNull(lddmStr)){
			// lddm = lddmStr;
			// //comForm.setLddm(lddm);
			// isGyFdy = "yes";
			// }
			// request.setAttribute("isGyFdy","");
			// 公寓辅导员判断end

			String qsh = DealString.toGBK(dataSearchForm.getQsh());
			String bxsj = DealString.toGBK(dataSearchForm.getBxsj());
			String wxsj = DealString.toGBK(dataSearchForm.getWxsj());
			String wxry = DealString.toGBK(dataSearchForm.getRydm());
			String wxnr = DealString.toGBK(request.getParameter("wxnr"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String wxbm = DealString.toGBK(request.getParameter("bmdm"));
			String bxbm = DealString.toGBK(request.getParameter("bxbm"));
			String bxr = DealString.toGBK(request.getParameter("bxr"));
			String ssbh = dao.getOneRs(
					"select ssbh from ssxxb where lddm=? and qsh=?",
					new String[] { lddm, qsh }, "ssbh");

			sql = " select ssbh from ssxxb where ssbh = ? ";
			String tmp = dao.getOneRs(sql, new String[] { ssbh }, "ssbh");

			boolean result = false;
			// ==============2010/7/28 edit by
			// luojw=============================
			if (tmp != null && !tmp.equalsIgnoreCase("")) {
				result = isSuccess = StandardOperation.delete(realTable,
						new String[] { "ssbh", "bxsj" }, new String[] { ssbh,
								bxsj }, request);
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					if (wxsj.equalsIgnoreCase("")
							|| wxsj.equalsIgnoreCase("未维修") || wxsj == null) {
						result = isSuccess = StandardOperation
								.insert(realTable, new String[] { "xn", "xq",
										"ssbh", "bxsj", "wxnr", "bz" },
										new String[] { xn, xq, ssbh, bxsj,
												wxnr, bz }, request);
					} else {
						result = isSuccess = StandardOperation.insert(
								realTable, new String[] { "xn", "xq", "ssbh",
										"bxsj", "wxsj", "wxnr", "wxry", "bz",
										"wxbm" }, new String[] { xn, xq, ssbh,
										bxsj, wxsj, wxnr, wxry, bz, wxbm },
								request);
					}
				} else {
					if (wxsj.equalsIgnoreCase("")
							|| wxsj.equalsIgnoreCase("未维修") || wxsj == null) {
						result = isSuccess = StandardOperation.insert(
								realTable, new String[] { "xn", "xq", "ssbh",
										"bxsj", "wxnr", "bz", "bxbm", "bxr" },
								new String[] { xn, xq, ssbh, bxsj, wxnr, bz,
										bxbm, bxr }, request);
					} else {
						result = isSuccess = StandardOperation.insert(
								realTable, new String[] { "xn", "xq", "ssbh",
										"bxsj", "wxsj", "wxnr", "wxry", "bz",
										"bxbm", "bxr" }, new String[] { xn, xq,
										ssbh, bxsj, wxsj, wxnr, wxry, bz, bxbm,
										bxr }, request);
					}
				}
			}
			request.setAttribute("result", result);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("gywsjcb")) {// TODO
			String lddm = DealString.toGBK(dataSearchForm.getLddm());
			// 公寓辅导员判断begin
			// String userName =
			// session.getAttribute("userName").toString();
			// String lddmStr = gyglDao.getLddmxXx(userName);
			// String isGyFdy = "no";
			// if(!Base.isNull(lddmStr)){
			// lddm = lddmStr;
			// //comForm.setLddm(lddm);
			// isGyFdy = "yes";
			// }
			// request.setAttribute("isGyFdy","");
			// 公寓辅导员判断end

			// ==================begin 2009/5/25 =================
			String qsh = DealString.toGBK(dataSearchForm.getQsh());
			String jcsj = DealString.toGBK(dataSearchForm.getJcsj());
			String jcbm = DealString.toGBK(dataSearchForm.getJcbm());
			String fs = DealString.toGBK(request.getParameter("fs"));
			String dj = DealString.toGBK(request.getParameter("dj"));
			String zs = DealString.toGBK(request.getParameter("zs"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String ssbh = dao.getOneRs(
					"select ssbh from ssxxb where lddm=? and qsh=?",
					new String[] { lddm, qsh }, "ssbh");

			if (zs != null && zs.length() != 1 && zs.length() != 2) {
				zs = zs.replace("第", "").replace("周", "");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
				dj = DealString.toGBK(request.getParameter("fs"));
				fs = dj;
				dj = dao.getOneRs(
						"select wsjcdj from hhgxy_wsjcdj where wsjccj = ?",
						new String[] { dj }, "wsjcdj");
			}
			sql = " select ssbh from ssxxb where ssbh = ? ";
			String tmp = dao.getOneRs(sql, new String[] { ssbh }, "ssbh");
			if (tmp != null && !tmp.equalsIgnoreCase("")) {
				if (!Base.isNull(jcsj)) {
					// -------------2010/6/25 edit by
					// luojw------------------------
					// if (xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)) {
					isSuccess = StandardOperation.delete(realTable,
							new String[] { "xn", "xq", "ssbh", "jcsj" },
							new String[] { xn, xq, ssbh, jcsj }, request);
				} else {
					isSuccess = StandardOperation.delete(realTable,
							new String[] { "xn", "xq", "ssbh", "zs" },
							new String[] { xn, xq, ssbh, zs }, request);
				}
				if (isSuccess) {
					if (!Base.isNull(jcsj)) {
						isSuccess = StandardOperation.insert(realTable,
								new String[] { "xn", "xq", "jcbm", "fs", "bz",
										"dj", "ssbh", "zs", "jcsj" },
								new String[] { xn, xq, jcbm, fs, bz, dj, ssbh,
										zs, jcsj }, request);
					} else {
						isSuccess = StandardOperation.insert(realTable,
								new String[] { "xn", "xq", "jcbm", "fs", "bz",
										"dj", "ssbh", "zs" }, new String[] {
										xn, xq, jcbm, fs, bz, dj, ssbh, zs },
								request);
					}
				}
			}
			// ==================end 2009/5/25 骆嘉伟 =================
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("kccjcpb")) {
			String cpf = DealString.toGBK(request.getParameter("cpf"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			sql = " delete from " + realTable
					+ " where xh = ? and xn = ? and xq = ? ";
			isSuccess = dao.runUpdate(sql, new String[] { xh, xn, xq });
			sql = " insert into " + realTable
					+ " (xh,xn,xq,cpf,bz) values (?,?,?,?,?)";
			isSuccess = dao
					.runUpdate(sql, new String[] { xh, xn, xq, cpf, bz });
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("zsjlb")) {

			// String lddm = DealString.toGBK(request.getParameter("lddm"));
			// String qsh = DealString.toGBK(request.getParameter("qsh"));
			String wjsj = DealString.toGBK(dataSearchForm.getWjsj());
			String wjsy = DealString.toGBK(request.getParameter("wjsy"));
			String cljg = DealString.toGBK(request.getParameter("cljg"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String ch = DealString.toGBK(request.getParameter("cwh"));
			String wjlbdm = request.getParameter("wjlbdm");
			String ssbh = request.getParameter("ssbh");
			String saveType = request.getParameter("saveType");
			String yjfs = request.getParameter("fs");
			String xsxx = DealString.toGBK(request.getParameter("xsxx"));
			sql = " select ssbh from ssxxb where ssbh = ? ";
			String tmp = dao.getOneRs(sql, new String[] { ssbh }, "ssbh");
			if (tmp != null && !tmp.equalsIgnoreCase("")) {
				if (!Base.isNull(saveType) && "more".equalsIgnoreCase(saveType)) {
					String[] rzStu = dao.getRs(
							" select xh from xszsxxb where ssbh=? ",
							new String[] { ssbh }, "xh");
					String[] inserSql = new String[rzStu.length];
					String[] delSql = new String[rzStu.length];

					// if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
					String brsql = "delete from " + realTable
							+ " where xh=? and wjsj='" + wjsj + "'";
					isSuccess = dao.runUpdate(brsql, new String[] { xh });
					brsql = "insert into "
							+ realTable
							+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm,yjfs) "
							+ "values('" + xh + "','" + xn + "','" + xq + "','"
							+ ssbh + "','" + wjsy + "','" + cljg + "','" + wjsj
							+ "','" + bz + "','" + ch + "','" + wjlbdm + "','"
							+ yjfs + "')";
					isSuccess = dao.runUpdate(brsql, new String[] {});

					rzStu = new String[0];
					if (!"".equalsIgnoreCase(xsxx)) {
						String[] xx = xsxx.split("!!SplitSignOne!!");
						if (xx.length > 0) {
							rzStu = new String[xx.length];
							for (int i = 0; i < xx.length; i++) {
								String[] xx1 = xx[i].split(":");
								String xhV = xx1[1].replace("姓名", "").trim();
								rzStu[i] = xhV;
							}
						}
					}
					// }
					for (int i = 0; i < rzStu.length; i++) {
						delSql[i] = "delete from " + realTable + " where xh='"
								+ rzStu[i] + "' and wjsj='" + wjsj + "'";
						// if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
						inserSql[i] = "insert into "
								+ realTable
								+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm,yjfs) "
								+ "values('" + rzStu[i] + "','" + xn + "','"
								+ xq + "','" + ssbh + "','" + wjsy + "','"
								+ cljg + "','" + wjsj + "','" + bz + "','" + ch
								+ "','" + wjlbdm + "','" + yjfs + "')";

						String[] exesql = new String[2];
						exesql[0] = "delete from " + realTable + " where xh='"
								+ xh + "' and wjsj='" + wjsj + "' ";

						exesql[1] = "insert into "
								+ realTable
								+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm,yjfs) "
								+ "values('" + xh + "','" + xn + "','" + xq
								+ "','" + ssbh + "','" + wjsy + "','" + cljg
								+ "','" + wjsj + "','" + bz + "','" + ch
								+ "','" + wjlbdm + "','" + yjfs + "')";

						int[] array = null;
						array = dao.runBatch(exesql);
						isSuccess = dao.checkBatch(array);
						// } else {
						// inserSql[i] = "insert into "
						// + realTable
						// + "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm) "
						// + "values('" + rzStu[i] + "','" + xn
						// + "','" + xq + "','" + ssbh + "','"
						// + wjsy + "','" + cljg + "','" + wjsj
						// + "','" + bz + "','" + ch + "','"
						// + wjlbdm + "')";
						// }
					}
					String[] exesql = dao.unionArray(delSql, inserSql);
					int[] array = null;
					array = dao.runBatch(exesql);
					if (array != null && array.length > 0) {
						isSuccess = dao.checkBatch(array);
					}
				} else {
					String[] exesql = new String[2];
					exesql[0] = "delete from " + realTable + " where xh='" + xh
							+ "' and wjsj='" + wjsj + "' ";
					// if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
					exesql[1] = "insert into "
							+ realTable
							+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm,yjfs) "
							+ "values('" + xh + "','" + xn + "','" + xq + "','"
							+ ssbh + "','" + wjsy + "','" + cljg + "','" + wjsj
							+ "','" + bz + "','" + ch + "','" + wjlbdm + "','"
							+ yjfs + "')";
					// } else {
					// exesql[1] = "insert into "
					// + realTable
					// + "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm) "
					// + "values('" + xh + "','" + xn + "','" + xq
					// + "','" + ssbh + "','" + wjsy + "','"
					// + cljg + "','" + wjsj + "','" + bz + "','"
					// + ch + "','" + wjlbdm + "')";
					// }
					int[] array = null;
					array = dao.runBatch(exesql);
					isSuccess = dao.checkBatch(array);
				}
			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("xsgzpxxxb")) {
			String pxxm = DealString.toGBK(request.getParameter("pxxmdm"));
			String pxjg = DealString.toGBK(request.getParameter("pxjg"));
			String pxkssj = DealString.toGBK(request.getParameter("pxkssj"));
			String pxjssj = DealString.toGBK(request.getParameter("pxjssj"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || (pkValue == "")) {
				sql = "delete xsgzpxxxb where xh=? and xn=? and xq=? and nd=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh, xn, xq, nd });
			} else {
				sql = "delete xsgzpxxxb where " + pk + "= '" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into "
						+ realTable
						+ "(xh,xn,xq,nd,pxxmdm,pxjg,pxkssj,pxjssj,bz) values (?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xh, xn, xq, nd,
						pxxm, pxjg, pxkssj, pxjssj, bz });
			} else {
				dataSearchForm.setErrMsg("sdf");

			}

			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("ssydxxb")) {
			String lddm = DealString.toGBK(dataSearchForm.getLddm());
			String qsh = DealString.toGBK(dataSearchForm.getQsh());
			String ydsj = DealString.toGBK(request.getParameter("ydsj"));
			String ydly = DealString.toGBK(request.getParameter("ydly"));
			String ydqssbh = DealString.toGBK(dataSearchForm.getYdqssbh());
			String ydqcwh = DealString.toGBK(dataSearchForm.getYdqcwh());
			String ydqrzsj = DealString.toGBK(dataSearchForm.getYdqrzsj());
			String ydhcwh = DealString.toGBK(request.getParameter("cwh"));
			String sfjh = request.getParameter("sfjh");// 是否床位交换标志
			String ydhssbh = dao.getOneRs(
					"select ssbh from ssxxb where lddm=? and qsh=?",
					new String[] { lddm, qsh }, "ssbh");
			String bz = DealString.toGBK(request.getParameter("bz"));
			boolean result = false;

			String xh1 = null;
			String rzrq2 = null;
			if (sfjh != null && sfjh.equals("yes")) {
				sql = "select xh,rzrq from xszsxxb where ssbh=? and cwh=?";
				String[] info = dao.getOneRs(sql, new String[] { ydhssbh,
						ydhcwh }, new String[] { "xh", "rzrq" });
				xh1 = info[0];
				rzrq2 = info[1];
			}
			if ((pkValue == null) || (pkValue == "")) {
				sql = "delete ssydxxb where xh=? and ydsj=?";
				result = isSuccess = dao.runUpdate(sql,
						new String[] { xh, ydsj });
				if (xh1 != null && !xh1.equals("")) {
					sql = "delete ssydxxb where xh=? and ydsj=?";
					result = isSuccess = dao.runUpdate(sql, new String[] { xh1,
							ydsj });
				}
			} else {
				sql = "delete ssydxxb where " + pk + "= '" + pkValue + "'";
				result = isSuccess = dao.runUpdate(sql, new String[] {});
				if (xh1 != null && !xh1.equals("")) {
					sql = "delete ssydxxb where " + pk + "= ?";
					result = isSuccess = dao.runUpdate(sql, new String[] { xh1
							+ "||" + ydsj });
				}
			}
			if (result) {
				sql = "insert into "
						+ realTable
						+ "(xh,xn,xq,ydqssbh,ydhssbh,ydly,ydsj,bz,ydqcwh,ydhcwh,ydqrzsj,ydhrzsj) values (?,?,?,?,?,?,?,?,?,?,?,?)";
				String upzsxxsql = "update xszsxxb set ssbh=?,cwh=?,rzrq=? where xh=?";
				result = isSuccess = dao.runUpdate(upzsxxsql, new String[] {
						ydhssbh, ydhcwh, ydsj, xh });
				result = isSuccess = dao.runUpdate(sql, new String[] { xh, xn,
						xq, ydqssbh, ydhssbh, ydly, ydsj, bz, ydqcwh, ydhcwh,
						ydqrzsj, ydsj });
				String count = dao
						.getOneRs(
								"select count(xh)cout from xszslsxxb where  xh||ssbh||cwh||rzrq||tfrq=? ",
								new String[] { xh + ydqssbh + ydqcwh + ydqrzsj
										+ ydsj }, "cout");
				if (Integer.parseInt(count) == 0) {
					sql = "insert into xszslsxxb(xh,ssbh,bz,cwh,rzrq,tfrq,sfbz) values (?,?,?,?,?,?,?)";
					result = isSuccess = dao.runUpdate(sql, new String[] {
							xh,
							ydqssbh,
							bz,
							ydqcwh,
							ydqrzsj,
							ydsj,
							dao.getOneRs(
									"select sfbz from ssxxb where ssbh=? ",
									new String[] { ydqssbh }, "sfbz") });
				}
				if (xh1 != null && !xh1.equals("")) {
					sql = "insert into "
							+ realTable
							+ "(xh,xn,xq,ydqssbh,ydhssbh,ydly,ydsj,bz,ydqcwh,ydhcwh,ydqrzsj,ydhrzsj) values (?,?,?,?,?,?,?,?,?,?,?,?)";
					upzsxxsql = "update xszsxxb set ssbh=?,cwh=?,rzrq=? where xh=?";
					result = isSuccess = dao.runUpdate(upzsxxsql, new String[] {
							ydqssbh, ydqcwh, ydsj, xh1 });
					result = isSuccess = dao.runUpdate(sql, new String[] { xh1,
							xn, xq, ydhssbh, ydqssbh, ydly, ydsj, bz, ydhcwh,
							ydqcwh, ydqrzsj, ydsj });
					String count1 = dao
							.getOneRs(
									"select count(xh)cout from xszslsxxb where  xh||ssbh||cwh||rzrq||tfrq=? ",
									new String[] { xh1 + ydhssbh + ydhcwh
											+ rzrq2 + ydsj }, "cout");
					if (Integer.parseInt(count1) == 0) {
						sql = "insert into xszslsxxb(xh,ssbh,bz,cwh,rzrq,tfrq,sfbz) values (?,?,?,?,?,?,?)";
						result = isSuccess = dao.runUpdate(sql, new String[] {
								xh1,
								ydhssbh,
								bz,
								ydhcwh,
								rzrq2,
								ydsj,
								dao.getOneRs(
										"select sfbz from ssxxb where ssbh=? ",
										new String[] { ydqssbh }, "sfbz") });
					}
				}
			}
			request.setAttribute("result", result);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
			
			return newFwd = new ActionForward("/sjcz/ssydxxb.jsp", false);
			
		} else if (realTable.equalsIgnoreCase("bjjxhjb")) {
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));
			String hjsj = request.getParameter("hjsj").replaceAll("-", "");
			String jxdm = DealString.toGBK(request.getParameter("jxdm"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || (pkValue == "")) {
				isSuccess = StandardOperation.delete(realTable, new String[] {
						"bjdm", "hjsj" }, new String[] { bjdm, hjsj }, request);
			} else {
				isSuccess = StandardOperation.delete(realTable, pk, pkValue,
						request);
			}
			if (isSuccess) {
				isSuccess = StandardOperation.insert(realTable, new String[] {
						"bjdm", "hjsj", "jxdm", "nd", "xn", "xq", "bz" },
						new String[] { bjdm, hjsj, jxdm, nd, xn, xq, bz },
						request);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			dataSearchForm.setBjdm(bjdm);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("yxjxhjb")) {
			String xydm = DealString.toGBK(request.getParameter("xydm"));
			String hjsj = request.getParameter("hjsj").replaceAll("-", "");
			String jxdm = DealString.toGBK(request.getParameter("jxdm"));
			String bz = DealString.toGBK(request.getParameter("bz"));

			if ((pkValue == null) || pkValue == "") {
				isSuccess = StandardOperation.delete(realTable, new String[] {
						"xydm", "hjsj" }, new String[] { xydm, hjsj }, request);
			} else {
				isSuccess = StandardOperation.delete(realTable, pk, pkValue,
						request);
			}
			if (isSuccess) {
				isSuccess = StandardOperation.insert(realTable, new String[] {
						"xydm", "hjsj", "jxdm", "nd", "xn", "xq", "bz" },
						new String[] { xydm, hjsj, jxdm, nd, xn, xq, bz },
						request);
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("wmqspbb")) {
			String lddm = dataSearchForm.getLddm();
			String qsh = DealString.toGBK(dataSearchForm.getQsh());
			String pysj = request.getParameter("pysj");
			String bz = DealString.toGBK(request.getParameter("bz"));
			String fzld = "";
			String qslb = dataSearchForm.getQslb();
			String qsjje = "0";
			String xxfw = DealString.toGBK(request.getParameter("xxfw"));

			if (qslb != null && !"".equalsIgnoreCase(qslb)) {// 获得寝室奖金额
				qsjje = dao.getOneRs(
						"select qsjje from qslbdmb where lbdm = ?",
						new String[] { qslb }, "qsjje");
			}
			// String userDep = session.getAttribute("userDep").toString();
			// String userType =
			// session.getAttribute("userType").toString();
			String userName = request.getSession().getAttribute("userName")
					.toString();
			// 公寓辅导员判断begin
			// String lddmStr = gyglDao.getLddmxXx(userName);
			// String isGyFdy = "no";
			// if(!Base.isNull(lddmStr)){
			// lddm = lddmStr;
			// isGyFdy = "yes";
			// }
			// request.setAttribute("isGyFdy","");
			// 公寓辅导员判断end

			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)) {
				// 值班人员登录，默认其值班楼栋
				sql = "select szlddm from zbrydmb where zbrydm = ?";
				fzld = dao.getOneRs(sql, new String[] { userName }, "szlddm");
				if (!("".equalsIgnoreCase(fzld) || fzld == null)) {
					lddm = fzld;
				}
			}
			String ssbh = dao.getOneRs(
					"select ssbh from ssxxb where lddm=? and qsh=?",
					new String[] { lddm, qsh }, "ssbh");

			sql = " select ssbh from ssxxb where ssbh = ? ";
			String tmp = dao.getOneRs(sql, new String[] { ssbh }, "ssbh");
			if (tmp != null && !tmp.equalsIgnoreCase("")) {
				isSuccess = StandardOperation.delete(realTable, new String[] {
						"ssbh", "xn", "xq" }, new String[] { ssbh, xn, xq },
						request);
				if (isSuccess) {
					isSuccess = isSuccess = StandardOperation.insert(realTable,
							new String[] { "xn", "xq", "bz", "pysj", "ssbh",
									"qslb", "qsjje", "xxfw" },
							new String[] { xn, xq, bz, pysj, ssbh, qslb, qsjje,
									xxfw }, request);
					if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
						sql = "{call AUTOQSPYJF(?,?,?)}";
						dao.runProcuder(sql, new String[] { xn, xq, ssbh });
					}
				}
			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("lpxxb")) {
			String bxgsdm = request.getParameter("bxgsdm");
			String lpje = request.getParameter("lpje");
			String slrq = request.getParameter("slrq");
			String dzsj = DealString.toGBK(request.getParameter("dzsj"));
			String lpyy = DealString.toGBK(request.getParameter("lpyy"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String hffy = DealString.toGBK(request.getParameter("hffy"));
			String bxxzdm = request.getParameter("bxxzdm");

			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				// 上海工程技术大学
				isSuccess = StandardOperation.delete("lpxxb", "xh||nd", xh
						.trim()
						+ nd.trim(), request);
			} else {
				if ((pkValue == null) || (pkValue == "")) {
					isSuccess = StandardOperation.delete("lpxxb",
							"xh||bxgsdm||nd||slrq", xh.trim() + bxgsdm.trim()
									+ nd.trim() + slrq.trim(), request);
				} else {
					isSuccess = StandardOperation.delete("lpxxb", pk, pkValue,
							request);
				}
			}
			if (isSuccess) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
					String xxshyj = DealString.toGBK(request
							.getParameter("xxshyj"));
					// isSuccess=StandardOperation.insert(realTable, new
					// String[] {
					// "xh",
					// "bxgsdm", "nd", "xn", "xq", "slrq", "dzsj", "lpje",
					// "lpyy", "bz" ,"hffy","bxxzdm","xxshyj"}, new String[]
					// { xh, bxgsdm, nd, xn,
					// xq, slrq, dzsj, lpje, lpyy, bz ,hffy,bxxzdm,xxshyj},
					// request);
					// 将花费信息保存
					int num = Integer.parseInt(request.getParameter("bxxzNum"));
					for (int i = 0; i < num; i++) {
						hffy = request.getParameter("hffy" + i);
						bxxzdm = request.getParameter("bxxzdm" + i);
						lpje = request.getParameter("lpje" + i);
						if (bxxzdm != null && !"".equalsIgnoreCase(bxxzdm)) {
							isSuccess = StandardOperation.insert(realTable,
									new String[] { "xh", "bxgsdm", "nd", "xn",
											"xq", "slrq", "dzsj", "lpje",
											"lpyy", "bz", "hffy", "bxxzdm",
											"xxshyj" }, new String[] { xh,
											bxgsdm, nd, xn, xq, slrq, dzsj,
											lpje, lpyy, bz, hffy, bxxzdm,
											xxshyj }, request);
						}
					}
				} else {
					isSuccess = StandardOperation.insert(realTable,
							new String[] { "xh", "bxgsdm", "nd", "xn", "xq",
									"slrq", "dzsj", "lpje", "lpyy", "bz",
									"bxxzdm", "hffy" }, new String[] { xh,
									bxgsdm, nd, xn, xq, slrq, dzsj, lpje, lpyy,
									bz, bxxzdm, hffy }, request);
				}
			} else {
				dataSearchForm.setErrMsg("sdf");

			}
			List bxxzList = dao.getList(
					"select distinct bxxzdm,bxxzmc from bxxzb",
					new String[] {}, new String[] { "bxxzdm", "bxxzmc" });
			request.setAttribute("bxxzList", bxxzList);
			request.setAttribute("result", isSuccess);
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
		} else if (realTable.equalsIgnoreCase("kkqkb")) {
			String kkrq = request.getParameter("kkrq");
			String kkkc = request.getParameter("kkkc");
			String kkjs = request.getParameter("kkjs");
			String wzx = DealString.toGBK(request.getParameter("wzx"));
			String skqk = DealString.toGBK(request.getParameter("skqk"));
			String zdl = DealString.toGBK(request.getParameter("zdl"));
			String cljg = DealString.toGBK(request.getParameter("cljg"));
			if ((pkValue == null) || (pkValue == "")) {
				sql = "delete from kkqkb where xh=? and kkrq=? and kkkc=?";
				isSuccess = dao.runUpdate(sql, new String[] { xh, kkrq, kkkc });
			} else {
				sql = "delete from kkqkb where " + pk + "='" + pkValue + "'";
				isSuccess = dao.runUpdate(sql, new String[] {});
			}
			if (isSuccess) {
				sql = "insert into "
						+ realTable
						+ "(xh,xn,xq,nd,kkrq,kkkc,kkjs,cljg,zdl,skqk,wzx) values (?,?,?,?,?,?,?,?,?,?,?)";
				isSuccess = dao.runUpdate(sql, new String[] { xh, xn, xq, nd,
						kkrq, kkkc, kkjs, cljg, zdl, skqk, wzx });
			} else {
				System.out.println("22222");
				dataSearchForm.setErrMsg("sdf");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		} else if (realTable.equalsIgnoreCase("gdnz_lpxxb_bx")
				|| realTable.equalsIgnoreCase("gdnz_lpxxb_sh")) {// 广东女子职业保险理赔信息
			String bxgsdm = request.getParameter("bxgsdm");
			String bxnx = request.getParameter("bxqx");
			String bxdc = request.getParameter("bxdc");
			String bf = request.getParameter("bf");
			String jzyy = DealString.toGBK(request.getParameter("jzyy"));
			String lpje = request.getParameter("lpje");
			String zykssj = request.getParameter("zykssj");
			String zyjzsj = request.getParameter("zyjzsj");
			String spsj = request.getParameter("spsj");
			String spje = request.getParameter("spje");
			String sqlpsj = request.getParameter("sqlpsj");
			String clqd = DealString.toGBK(request.getParameter("clqd"));
			String bpyy = DealString.toGBK(request.getParameter("bpyy"));
			String sqlpyy = DealString.toGBK(request.getParameter("sqlpyy"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String bdhm = DealString.toGBK(request.getParameter("bdh"));
			String sfxfzrx = realTable.equalsIgnoreCase("gdnz_lpxxb_sh") ? "1"
					: "0";

			if ((pkValue == null) || (pkValue == "")) {
				isSuccess = StandardOperation.delete("gdnz_lpxxb",
						"xh||bxgsdm||nd||sfxfzrx", xh.trim() + bxgsdm.trim()
								+ nd.trim() + sfxfzrx.trim(), request);
			} else {
				isSuccess = StandardOperation.delete("gdnz_lpxxb", pk, pkValue,
						request);
			}
			if (isSuccess) {
				isSuccess = StandardOperation.insert("gdnz_lpxxb",
						new String[] { "xh", "bxgsdm", "nd", "xn", "xq",
								"sfxfzrx", "bxnx", "bxdc", "bf", "sqlpsj",
								"sqlpyy", "jzyy", "zykssj", "zyjzsj", "clqd",
								"spsj", "spje", "lpje", "bpyy", "bz", "bdhm" },
						new String[] { xh, bxgsdm, nd, xn, xq, sfxfzrx, bxnx,
								bxdc, bf, sqlpsj, sqlpyy, jzyy, zykssj, zyjzsj,
								clqd, spsj, spje, lpje, bpyy, bz, bdhm },
						request);
			} else {
				dataSearchForm.setErrMsg("操作失败！");

			}
			request.setAttribute("isSuccess", isSuccess);
			request.setAttribute("done", isSuccess);
			request.setAttribute("result", isSuccess);
		}
		// -----------------2010/6/25 edit by luojw----------------------
		// newFwd = null;
		return newFwd;
	}

	private ActionForward delData(HttpServletRequest request, String writeAble,
			DAO dao, String pk, String pkValue, String[] pkValueA,
			String realTable, String from, String xxdm) throws Exception {
		ActionForward newFwd;
		String sql;
		// 删除数据
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			if (pkValueA != null) {
				for (int i = 0; i < pkValueA.length; i++) {
					String tempPk = pkValueA[i];
					String[] temp = dao
							.getOneRs(
									"select xh,hth from zxdk_htxx where xh||hth=? and rownum=1",
									new String[] { tempPk }, new String[] {
											"xh", "hth" });
					String xh = "";
					String hth = "";
					if (temp != null) {
						xh = temp[0];
						hth = temp[1];
					}
					sql = "select xh,hth1,hth2,hth3,hth4,replace(NVL(dkze,'0'),' ','0') dkze,replace(NVL(ht1_zje,'0'),' ','0') ht1_zje,"
							+ "replace(NVL(ht2_zje,'0'),' ','0') ht2_zje,replace(NVL(ht3_zje,'0'),' ','0') ht3_zje,replace(NVL(ht4_zje,'0'),' ','0') ht4_zje from zxdk_xsjbxx where xh=? and rownum=1";
					String[] xsxx = dao.getOneRs(sql, new String[] { xh },
							new String[] { "xh", "hth1", "hth2", "hth3",
									"hth4", "dkze", "ht1_zje", "ht2_zje",
									"ht3_zje", "ht4_zje" });
					int tempje = 0;
					if (xsxx == null) {
					} else {
						if (hth.equalsIgnoreCase(xsxx[1])) {
							tempje = Integer.parseInt(xsxx[5])
									- Integer.parseInt(xsxx[6]);
							sql = "update zxdk_xsjbxx set hth1=?,ht1_jbjrjg=?,ht1_fzjgmc=?,ht1_pzrq=?,ht1_zje=?,"
									+ "ht1_jby=?,ht1_pzhz=?,ht1_hkksrq=?,ht1_hkjzrq=?,ht1_zqly=?,ht1_zqsj=?,"
									+ "ht1_dkfs=?,ht1_hkfs=?,ht1_hkjzmc=?,ht1_hkjzzh=?,ht1_tqyhbxje=?,ht1_tqjzsj=?,"
									+ "ht1_tqbz=?,dkze=? where xh=?";
							dao.runUpdate(sql, new String[] { "", "", "", "",
									"", "", "", "", "", "", "", "", "", "", "",
									"", "", "", String.valueOf(tempje), xh });
						} else if (hth.equalsIgnoreCase(xsxx[2])) {
							tempje = Integer.parseInt(xsxx[5])
									- Integer.parseInt(xsxx[7]);
							sql = "update zxdk_xsjbxx set hth2=?,ht2_jbjrjg=?,ht2_fzjgmc=?,ht2_pzrq=?,ht2_zje=?,"
									+ "ht2_jby=?,ht2_pzhz=?,ht2_hkksrq=?,ht2_hkjzrq=?,ht2_zqly=?,ht2_zqsj=?,"
									+ "ht2_dkfs=?,ht2_hkfs=?,ht2_hkjzmc=?,ht2_hkjzzh=?,ht2_tqyhbxje=?,ht2_tqjzsj=?,"
									+ "ht2_tqbz=?,dkze=? where xh=?";
							dao.runUpdate(sql, new String[] { "", "", "", "",
									"", "", "", "", "", "", "", "", "", "", "",
									"", "", "", String.valueOf(tempje), xh });
						} else if (hth.equalsIgnoreCase(xsxx[3])) {
							tempje = Integer.parseInt(xsxx[5])
									- Integer.parseInt(xsxx[8]);
							sql = "update zxdk_xsjbxx set hth3=?,ht3_jbjrjg=?,ht3_fzjgmc=?,ht3_pzrq=?,ht3_zje=?,"
									+ "ht3_jby=?,ht3_pzhz=?,ht3_hkksrq=?,ht3_hkjzrq=?,ht3_zqly=?,ht3_zqsj=?,"
									+ "ht3_dkfs=?,ht3_hkfs=?,ht3_hkjzmc=?,ht3_hkjzzh=?,ht3_tqyhbxje=?,ht3_tqjzsj=?,"
									+ "ht3_tqbz=?,dkze=? where xh=?";
							dao.runUpdate(sql, new String[] { "", "", "", "",
									"", "", "", "", "", "", "", "", "", "", "",
									"", "", "", String.valueOf(tempje), xh });
						} else if (hth.equalsIgnoreCase(xsxx[4])) {
							tempje = Integer.parseInt(xsxx[5])
									- Integer.parseInt(xsxx[9]);
							sql = "update zxdk_xsjbxx set hth4=?,ht4_jbjrjg=?,ht4_fzjgmc=?,ht4_pzrq=?,ht4_zje=?,"
									+ "ht4_jby=?,ht4_pzhz=?,ht4_hkksrq=?,ht4_hkjzrq=?,ht4_zqly=?,ht4_zqsj=?,"
									+ "ht4_dkfs=?,ht4_hkfs=?,ht4_hkjzmc=?,ht4_hkjzzh=?,ht4_tqyhbxje=?,ht4_tqjzsj=?,"
									+ "ht4_tqbz=?,dkze=? where xh=?";
							dao.runUpdate(sql, new String[] { "", "", "", "",
									"", "", "", "", "", "", "", "", "", "", "",
									"", "", "", String.valueOf(tempje), xh });
						}
					}
				}
			}
		}
		String type = "";
		if (realTable.equalsIgnoreCase("gdnz_lpxxb")) {
			type = request.getParameter("type");
		}
		for (int i = 1; i < pkValueA.length; i++) {
			if (realTable.equalsIgnoreCase("gdnz_lpxxb")) {
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValueA[i] + "'";
				sql += " and sfxfzrx=" + type;
				dao.runUpdate(sql, new String[] {});
			}
			if (realTable.equalsIgnoreCase("xlytqkb")) {
				pk = "yt_id";
			}
			// =================2009/7/8 luojw ===================
			if (realTable.equalsIgnoreCase("gywsjcb")
					&& Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {
				pk = "xn||xq||jcsj||ssbh";
				pkValue = pkValueA[i].replace("第", "").replace("周", "");
			}
			StandardOperation.delete(realTable, pk, pkValueA[i], request);
			if (realTable.equalsIgnoreCase("gwxxb")) {
				StandardOperation.delete(
						"delete from xsgwxxb a where gwdm||gwsbsj='"
								+ pkValueA[i] + "'", "xsgwxxb", request);
			}
		}
		if (realTable.equalsIgnoreCase("dwjlxxb")) {
			newFwd = new ActionForward("/data_search2.do?go=go&act=" + from
					+ "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("zgkd_rdsqb")) {
			newFwd = new ActionForward("/zgkdDyxx.do?method=rdsq&go=go&act="
					+ from + "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("sjb")) {
			newFwd = new ActionForward("/test_paper_search.do?go=go&act="
					+ from + "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("stb")) {
			newFwd = new ActionForward("/test_question_search.do?go=go&act="
					+ from + "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("xxb")) {
			newFwd = new ActionForward("/test_option_search.do?go=go&act="
					+ from + "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("sjstb")) {
			newFwd = new ActionForward(
					"/test_paperQuestion_search.do?go=go&pkValue=&act=" + from
							+ "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("ssxxb")) {
			newFwd = new ActionForward("/ssxx_search.do?go=go&act=" + from
					+ "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("xszsxxb")) {
			newFwd = new ActionForward("/dorm_Using_Search.do?go=go&act="
					+ from + "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("hcccb")) {
			newFwd = new ActionForward("/train_time_search.do?go=go&act="
					+ from + "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("gwxxb")) {
			newFwd = new ActionForward("/data_search2.do?go=go&act=" + from
					+ "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("gywxglb")) {
			newFwd = new ActionForward("/gywx.do?go=go" + "&writeAble="
					+ writeAble, false);
		} else if (realTable.equalsIgnoreCase("gywsjcb")) {
			if (Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {
				StandardOperation.update("hhgxy_zrsgl", new String[] { "cj" },
						new String[] { "0" }, "xn||xq||ssbh||zs", pkValue,
						request);
			}
			newFwd = new ActionForward("/wsjc.do?go=go" + "&writeAble="
					+ writeAble, false);
		} else if (realTable.equalsIgnoreCase("zsjlb")) {
			newFwd = new ActionForward("/zsjl.do?go=go" + "&writeAble="
					+ writeAble, false);
		} else if (realTable.equalsIgnoreCase("mrzbjlb")) {
			newFwd = new ActionForward("/daily_note_search.do?go=go"
					+ "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("yzzbhzb")) {
			newFwd = new ActionForward("/weekly_collect_search.do?go=go"
					+ "&writeAble=" + writeAble, false);
		} else if (realTable.equalsIgnoreCase("ssydxxb")) {
			newFwd = new ActionForward("/ssyd.do?go=go" + "&writeAble="
					+ writeAble, false);
		} else if (realTable.equalsIgnoreCase("wmqspbb")) {
			newFwd = new ActionForward("/wmqspb.do?go=go" + "&writeAble="
					+ writeAble, false);
		} else if (realTable.equalsIgnoreCase("jfhbb")) {
			newFwd = new ActionForward(
					"/work_outlay_search.do?go=go&act=workOutlay"
							+ "&writeAble=" + writeAble, false);
		} else if (realTable.equals("xlytqkb")) {
			newFwd = new ActionForward("/xljk_ytqk.do?go=go&act=" + from
					+ "&writeAble=" + writeAble, false);
		} else {
			newFwd = new ActionForward("/data_search.do?go=go&act=" + from
					+ "&writeAble=" + writeAble, false);
		}
		return newFwd;
	}
}
