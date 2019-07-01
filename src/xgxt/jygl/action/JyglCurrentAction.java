package xgxt.jygl.action;

/*
 * 创建日期 2006-9-16
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.jygl.model.JyglModel;
import xgxt.jygl.service.ZgldgxxyService;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;

/**
 * @author bat_zzj
 */

public class JyglCurrentAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm chkUser = (CommanForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
//			String userType = "";
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
//			userType = session.getAttribute("userOnLine").toString();
//			String xxdm = session.getAttribute("xxdm").toString();
			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();
			if (myAct.equalsIgnoreCase("byssytj")) {
				myActFwd = byssytj(mapping, form, request, response); // 中国劳动关系学院毕业生生源统计
			} else if (myAct.equalsIgnoreCase("yxjzyjs")) {
				myActFwd = yxjzyjs(mapping, form, request, response); // 中国劳动关系学院院系及专业介绍
			} else if (myAct.equals("bysjcxxtj")) {
				myActFwd = bysjcxxtj(mapping, form, request, response);// 毕业生基础信息统计
			} else if (myAct.equals("bysbmtj")) {
				myActFwd = bysbmtj(mapping, form, request, response);// 毕业生基础信息统计
			} else if (myAct.equals("zphcs")) {
				if(Base.xxdm.equals(Globals.XXDM_JHZYJSXY)){
					return new ActionForward("/jhzyzphcs.do?method=jhzyzphcs", false);
				}else{
				myActFwd = zphcs(mapping, form, request, response);// 毕业生基础信息统计
				}
			} else if (myAct.equals("jyyxdc")) {
				myActFwd = jyyxdc(mapping, form, request, response);// 毕业生基础信息统计
			} else if (myAct.equals("bysjytjb")) {
				myActFwd = bysjytjb(mapping, form, request, response);// 毕业生基础信息统计
			} else if (myAct.equals("bysjyjzqk")) {
				myActFwd = bysjyjzqk(mapping, form, request, response);// 毕业生基础信息统计
			} else if (myAct.equals("jyjzjkbs")) {
				myActFwd = jyjzjkbs(mapping, form, request, response);// 毕业生基础信息统计
			}
			
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
		}
		return new ActionForward("/login.jsp", false);
	}

//	private boolean isNull(String str) {
//		return ((str == null) || str.equalsIgnoreCase("") || str
//				.equalsIgnoreCase("all"));
//	}

	// 中国劳动关系学院毕业生生源统计
	private ActionForward byssytj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm acfm = (CommanForm)form;
		String tjxm = request.getParameter("tjxm");
		String bynd = request.getParameter("nd");
		String type = request.getParameter("type");
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		ZgldgxxyService service = new ZgldgxxyService();
		String xydm = acfm.getXydm();
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		String[] keys = null;
		@SuppressWarnings("unused")
		String[] values = null;
		String[] keys1 = null;
		String[] values1 = null;
		@SuppressWarnings("unused")
		String[] keys2 = null;
		@SuppressWarnings("unused")
		String[] values2 = null;
		@SuppressWarnings("unused")
		String zydm = request.getParameter("zydm");
		if(Base.xxdm.equals(Globals.XXDM_JHZYJSXY)){
			return new ActionForward("/jhzyjysb.do?method=byssytj", false);//浙江金华职业
		}	
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(acfm.getXydm())) {
			
			whereSql.append(" and zydm in(select distinct zydm from xsxxb b where xydm='" + acfm.getXydm() + "')" );
		}
		if (!StringUtils.isNull(bynd)) {
			whereSql.append(" and bynd='"+bynd+"'");
		}
		StringBuffer whereSql4 = new StringBuffer();
		if (!StringUtils.isNull(acfm.getXydm())) {
			whereSql4.append(" and zydm in(select distinct zydm from xsxxb b where xydm='" + acfm.getXydm() + "')" );
		}
		if (!StringUtils.isNull(bynd)) {
			whereSql4.append(" and bynd='"+bynd+"'");
		}
		StringBuffer whereSql2 = new StringBuffer();
		if (!StringUtils.isNull(acfm.getXydm())) {
			whereSql2.append(" and zydm in(select distinct zydm from xsxxb b where b.xydm='" + acfm.getXydm() + "')" );
		}
		if (!StringUtils.isNull(bynd)) {
			whereSql2.append(" and bynd='"+bynd+"'");
		}
		StringBuffer whereSql1 = new StringBuffer();
		if (!StringUtils.isNull(acfm.getXydm())) {
			whereSql1.append(" and b.zydm in(select distinct zydm from xsxxb b where a.xydm='" + acfm.getXydm() + "')" );
		}
		if (!StringUtils.isNull(bynd)) {
			whereSql1.append(" and bynd='"+bynd+"'");
		}
		HashMap<String, String> backrs = service.map_back(acfm);
		request.setAttribute("backrs", backrs);
		if (!Base.isNull(tjxm)) {
				if ("tj".equals(type)) {
					keys1 = new String[] { "zymc", "heji" };
					values1 = new String[] { "专业", "合计" };
				
					DAO dao = DAO.getInstance();
					String[] colList = new String[] { "zymc", "hj" };
					String sql = "select zymc,count(*) hj from " +
							"(select xh,a.xydm,a.zydm,a.zymc,b.sydqdm,b.bynd from view_xsxxb a,jygl_xsjbxxb b where a.xh=b.xsxh) a where 1=1" +
							whereSql2.toString()+" group by zymc";
					ArrayList<HashMap<String, String>> rszy = dao.getArrayList(
							sql, new String[] {}, colList);

					keys = new String[] { "zymc", "hj", "xb", "bj", "tj",
							"hlj", "jl", "ll", "sxty", "sd", "hb", "hn",
							"sxxa", "gs", "nm", "xj", "sh", "jx", "js", "zj",
							"ah", "fj", "hbwh", "hncs", "sc", "cq", "yn", "gd",
							"gx", "gz", "hnd" };
					values = new String[] { "专业名称", "合计", "性别", "北京", "天津",
							"吉林", "辽宁", "山西", "山东", "河北", "河南", "西安", "甘肃",
							"内蒙", "上海", "江西", "江苏", "浙江", "安徽", "福建", "湖北",
							"湖南", "湖南", "四川", "重庆", "云南", "广东", "广西", "贵州",
							"海南" };
					String njsql = "select distinct zydm from view_njxyzybj where xydm=?";
					String xydm1 = acfm.getXydm();
					String[] zylt = dao.getRs(njsql, new String[]{xydm1}, "zydm");
					ArrayList<HashMap<String, String>> rssydn = new ArrayList<HashMap<String, String>>();
					
					if(StringUtils.isArrayNotNull(zylt)){
					for(int i = 0;i<zylt.length;i++){
						
						String[] xsxb = {"1","2"};
						
						for(String xsxb1 : xsxb){
							StringBuffer whereSql12 = new StringBuffer();
							if (!StringUtils.isNull(acfm.getXydm())) {
								whereSql12.append(" and zydm='"+zylt[i]+"'");
							}
							if (!StringUtils.isNull(bynd)) {
								whereSql12.append(" and bynd='"+bynd+"'");
								}
							whereSql12.append(" and xbdm='"+xsxb1+"'");
						
					//sql = "select (select distinct zymc from view_njxyzybj where zydm='"+zylt[i]+"') zymc,(select count(xsxh) from jygl_xsjbxxb where 1=1 "+whereSql12+") hj," 
					sql = "select (select distinct zymc from jygl_xsjbxxb where 1=1 "+whereSql12+") zymc,(select count(xsxh) from jygl_xsjbxxb where sydqdm not in('630000','540000') "+whereSql12+") hj,"		
							+"(case xbdm when '"+xsxb1+"' then '男' when '"+xsxb1+"' then '女' else '' end ) xb,"
							+ "nvl((select count(xsxh) from jygl_xsjbxxb where sydqdm='110000' "+whereSql12.toString()+"),0) bj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='120000' "+whereSql12.toString()+") tj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='230000' "+whereSql12.toString()+") hlj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='220000' "+whereSql12.toString()+") jl,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='210000' "+whereSql12.toString()+") ll,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='140000' "+whereSql12.toString()+") sxty,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='370000' "+whereSql12.toString()+") sd,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='130000' "+whereSql12.toString()+") hb,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='410000' "+whereSql12.toString()+") hn,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='610000' "+whereSql12.toString()+") sxxa,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='620000' "+whereSql12.toString()+") gs,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='150000' "+whereSql12.toString()+") nm,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='650000' "+whereSql12.toString()+") xj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='310000' "+whereSql12.toString()+") sh,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='360000' "+whereSql12.toString()+") jx,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='320000' "+whereSql12.toString()+") js,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='330000' "+whereSql12.toString()+") zj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='340000' "+whereSql12.toString()+") ah,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='350000' "+whereSql12.toString()+") fj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='420000' "+whereSql12.toString()+") hbwh,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='430000' "+whereSql12.toString()+") hncs,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='510000' "+whereSql12.toString()+") sc,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='500000' "+whereSql12.toString()+") cq,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='530000' "+whereSql12.toString()+") yn,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='440000' "+whereSql12.toString()+") gd,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='450000' "+whereSql12.toString()+") gx,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='520000' "+whereSql12.toString()+") gz,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='460000' "+whereSql12.toString()+") hnd "
							+ "from jygl_xsjbxxb where zydm='"+zylt[i]+"'";
//					ArrayList<HashMap<String, String>> rssydn = dao
//							.getArrayList(sql, new String[] {}, keys);
					HashMap<String, String> map = dao.getMap(sql, new String[]{}, keys);
					if("1".equals(xsxb1)){
						map.put("xb", "男");
					}else{
						map.put("xb", "女");
					}
					rssydn.add(map);
					}}
					}
					keys = new String[] { "bkhj", "hj", "xb", "bj", "tj",
							"hlj", "jl", "ll", "sxty", "sd", "hb", "hn",
							"sxxa", "gs", "nm", "xj", "sh", "jx", "js", "zj",
							"ah", "fj", "hbwh", "hncs", "sc", "cq", "yn", "gd",
							"gx", "gz", "hnd" };
					values = new String[] { "专业名称", "合计", "性别", "北京", "天津",
							"吉林", "辽宁", "山西", "山东", "河北", "河南", "西安", "甘肃",
							"内蒙", "上海", "江西", "江苏", "浙江", "安徽", "福建", "湖北",
							"湖南", "湖南", "四川", "重庆", "云南", "广东", "广西", "贵州",
							"海南" };
					sql = "select '本科合计' bkhj,(select count(*) from jygl_xsjbxxb where sydqdm not in('630000','540000') and xslb='本科生' "+whereSql.toString()+") hj,'' xb,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='110000' and xslb='本科生' "+whereSql.toString()+") bj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='120000' and xslb='本科生' "+whereSql.toString()+") tj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='230000' and xslb='本科生' "+whereSql.toString()+") hlj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='220000' and xslb='本科生' "+whereSql.toString()+") jl,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='210000' and xslb='本科生' "+whereSql.toString()+") ll,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='140000' and xslb='本科生' "+whereSql.toString()+") sxty,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='370000' and xslb='本科生' "+whereSql.toString()+") sd,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='130000' and xslb='本科生' "+whereSql.toString()+") hb,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='410000' and xslb='本科生' "+whereSql.toString()+") hn,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='610000' and xslb='本科生' "+whereSql.toString()+") sxxa,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='620000' and xslb='本科生' "+whereSql.toString()+") gs,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='150000' and xslb='本科生' "+whereSql.toString()+") nm,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='650000' and xslb='本科生' "+whereSql.toString()+") xj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='310000' and xslb='本科生' "+whereSql.toString()+") sh,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='360000' and xslb='本科生' "+whereSql.toString()+") jx,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='320000' and xslb='本科生' "+whereSql.toString()+") js,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='330000' and xslb='本科生' "+whereSql.toString()+") zj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='340000' and xslb='本科生' "+whereSql.toString()+") ah,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='350000' and xslb='本科生' "+whereSql.toString()+") fj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='420000' and xslb='本科生' "+whereSql.toString()+") hbwh,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='430000' and xslb='本科生' "+whereSql.toString()+") hncs,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='510000' and xslb='本科生' "+whereSql.toString()+") sc,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='500000' and xslb='本科生' "+whereSql.toString()+") cq,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='530000' and xslb='本科生' "+whereSql.toString()+") yn,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='440000' and xslb='本科生' "+whereSql.toString()+") gd,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='450000' and xslb='本科生' "+whereSql.toString()+") gx,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='520000' and xslb='本科生' "+whereSql.toString()+") gz,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='460000' and xslb='本科生' "+whereSql.toString()+") hnd "
							+ "from jygl_xsjbxxb where 1=1 and rownum<2 ";
					ArrayList<HashMap<String, String>> rssydv = dao
							.getArrayList(sql, new String[] {}, keys);

					values = new String[] { "专业名称", "合计", "性别", "北京", "天津",
							"吉林", "辽宁", "山西", "山东", "河北", "河南", "西安", "甘肃",
							"内蒙", "上海", "江西", "江苏", "浙江", "安徽", "福建", "湖北",
							"湖南", "湖南", "四川", "重庆", "云南", "广东", "广西", "贵州",
							"海南" };
					sql = "select '专科合计' bkhj,(select count(*) from jygl_xsjbxxb where sydqdm not in('630000','540000') and xslb='专科生'  "+whereSql.toString()+") hj,'' xb,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='110000' and xslb='专科生' "+whereSql.toString()+") bj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='120000' and xslb='专科生' "+whereSql.toString()+") tj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='230000' and xslb='专科生' "+whereSql.toString()+") hlj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='220000' and xslb='专科生' "+whereSql.toString()+") jl,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='210000' and xslb='专科生' "+whereSql.toString()+") ll,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='140000' and xslb='专科生' "+whereSql.toString()+") sxty,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='370000' and xslb='专科生' "+whereSql.toString()+") sd,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='130000' and xslb='专科生' "+whereSql.toString()+") hb,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='410000' and xslb='专科生' "+whereSql.toString()+") hn,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='610000' and xslb='专科生' "+whereSql.toString()+") sxxa,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='620000' and xslb='专科生' "+whereSql.toString()+") gs,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='150000' and xslb='专科生' "+whereSql.toString()+") nm,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='650000' and xslb='专科生' "+whereSql.toString()+") xj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='310000' and xslb='专科生' "+whereSql.toString()+") sh,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='360000' and xslb='专科生' "+whereSql.toString()+") jx,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='320000' and xslb='专科生' "+whereSql.toString()+") js,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='330000' and xslb='专科生' "+whereSql.toString()+") zj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='340000' and xslb='专科生' "+whereSql.toString()+") ah,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='350000' and xslb='专科生' "+whereSql.toString()+") fj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='420000' and xslb='专科生' "+whereSql.toString()+") hbwh,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='430000' and xslb='专科生' "+whereSql.toString()+") hncs,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='510000' and xslb='专科生' "+whereSql.toString()+") sc,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='500000' and xslb='专科生' "+whereSql.toString()+") cq,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='530000' and xslb='专科生' "+whereSql.toString()+") yn,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='440000' and xslb='专科生' "+whereSql.toString()+") gd,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='450000' and xslb='专科生' "+whereSql.toString()+") gx,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='520000' and xslb='专科生' "+whereSql.toString()+") gz,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='460000' and xslb='专科生' "+whereSql.toString()+") hnd "
							+ "from jygl_xsjbxxb where 1=1 and rownum<2 ";
					ArrayList<HashMap<String, String>> rssydv1 = dao
							.getArrayList(sql, new String[] {}, keys);

					values = new String[] { "专业名称", "合计", "性别", "北京", "天津",
							"吉林", "辽宁", "山西", "山东", "河北", "河南", "西安", "甘肃",
							"内蒙", "上海", "江西", "江苏", "浙江", "安徽", "福建", "湖北",
							"湖南", "湖南", "四川", "重庆", "云南", "广东", "广西", "贵州",
							"海南" };
					sql = "select '合计' bkhj,(select count(xsxh) from jygl_xsjbxxb where sydqdm not in('630000','540000')  "+whereSql.toString()+") hj,'' xb,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='110000'  "+whereSql.toString()+") bj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='120000'  "+whereSql.toString()+") tj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='230000'  "+whereSql.toString()+") hlj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='220000'  "+whereSql.toString()+") jl,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='210000'  "+whereSql.toString()+") ll,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='140000'  "+whereSql.toString()+") sxty,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='370000'  "+whereSql.toString()+") sd,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='130000'  "+whereSql.toString()+") hb,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='410000'  "+whereSql.toString()+") hn,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='610000'  "+whereSql.toString()+") sxxa,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='620000'  "+whereSql.toString()+") gs,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='150000'  "+whereSql.toString()+") nm,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='650000'  "+whereSql.toString()+") xj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='310000'  "+whereSql.toString()+") sh,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='360000'  "+whereSql.toString()+") jx,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='320000'  "+whereSql.toString()+") js,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='330000'  "+whereSql.toString()+") zj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='340000'  "+whereSql.toString()+") ah,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='350000'  "+whereSql.toString()+") fj,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='420000'  "+whereSql.toString()+") hbwh,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='430000'  "+whereSql.toString()+") hncs,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='510000'  "+whereSql.toString()+") sc,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='500000'  "+whereSql.toString()+") cq,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='530000'  "+whereSql.toString()+") yn,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='440000'  "+whereSql.toString()+") gd,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='450000'  "+whereSql.toString()+") gx,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='520000'  "+whereSql.toString()+") gz,"
							+ "(select count(xsxh) from jygl_xsjbxxb where sydqdm='460000'  "+whereSql.toString()+") hnd "
							+ "from jygl_xsjbxxb where 1=1 and rownum<2 ";
					
					ArrayList<HashMap<String, String>> rssydv2 = dao
							.getArrayList(sql, new String[] {}, keys);
					request.setAttribute("rszy", rszy);
					request.setAttribute("rssydn", rssydn);
					request.setAttribute("rssyvn", rssydv);
					request.setAttribute("rssydv1", rssydv1);
					request.setAttribute("rssydv2", rssydv2);
					request.setAttribute("rszy", rszy);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_xjtjb.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					@SuppressWarnings("unused")
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					return mapping.findForward("");
				}
			request.setAttribute("tjxm", tjxm);
			request.setAttribute("rs", rs);
			request
					.setAttribute("tabtop", service
							.getTabTopMap(keys1, values1));
		}
		request.setAttribute("ndList", Base.getXnndList());
		return mapping.findForward("success");
	}

	// 中国劳动关系学院院系级专业介绍
	private ActionForward yxjzyjs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm myform = (CommanForm) form;
		String realTable = "yxjzyjsb";
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		int count = 0;
		String userType = (String) request.getSession().getAttribute("userType");
		request.setAttribute("usertype", userType);
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		rs1.put("xydm", xydm);
		rs1.put("zydm", zydm);
		request.setAttribute("realTable", realTable);
		DAO dao = DAO.getInstance();

		if("xy".equals(userType) || "stu".equals(userType)){
			String bmmc = (String) request.getSession().getAttribute("bmmc");
			HashMap<String, String> stumap = new HashMap<String, String>();
			String srusql = "select distinct b.xydm,b.zydm from view_njxyzybj b where xymc='"+bmmc+"'";
			stumap = dao.getMap(srusql, new String[]{}, new String[]{"xydm","zydm"});
			xydm = stumap.get("xydm");
			rs1.put("xydm", xydm);
			request.setAttribute("bmxxmc", bmmc);
 		}
		
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		ZgldgxxyService service = new ZgldgxxyService();
		JyglModel jygl = new JyglModel();
		if ("xx".equals(userType) || userType == "xx"
				|| "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType) || userType == "xy") {
			request.setAttribute("who", "xy");
		} else {
			request.setAttribute("who", "");
		}
		if ("add".equals(act)) {
			// service.yxjzyjs_toGBK(myform);
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			if("xy".equals(userType) || "stu".equals(userType)){
				String bmmc = (String) request.getSession().getAttribute("bmmc");
				HashMap<String, String> stumap = new HashMap<String, String>();
				String srusql = "select distinct b.xydm,b.zydm from view_njxyzybj b where xymc='"+bmmc+"'";
				stumap = dao.getMap(srusql, new String[]{}, new String[]{"xydm","zydm"});
				xydm = stumap.get("xydm");
				rs1.put("xydm", xydm);
				request.setAttribute("bmxxmc", bmmc);
	 		}
			request.setAttribute("rs1", rs1);
			request.setAttribute("rs", rs);
			if ("add".equals(doType)) {
				boolean judge = service.yxjzyjs_save(jygl, request);
				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
			return mapping.findForward("add");
		}
		if ("query".equals(act)) {
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			rs = service.getQueryList(jygl, myform, request);
			count = service.queryListNum(jygl, request);
			topTr = service.queryTitle();
			myform.getPages().setMaxRecord(
					Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
		}
		if ("view".equals(act)) {
			String pk = request.getParameter("pkValue");
			rs2 = service.getViewList(pk);
			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");
			return mapping.findForward("view");
		}
		if ("update".equals(act)) {

			String pk = request.getParameter("pkValue");
			rs2 = service.getViewList(pk);
			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");

			if ("update".equals(doType)) {
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				request.setAttribute("rs1", rs1);
				request.setAttribute("rs", rs);
				if ("update".equals(doType)) {
					boolean judge = service.yxjzyjs_modify(jygl, pk, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}
			}
			request.setAttribute("rs1", rs2);
			request.setAttribute("rs", rs);
			return mapping.findForward("update");
		}
		// 删除
		if ("del".equals(act)) {
			String pk = request.getParameter("pkValue");
			boolean bool = service.getDelList(pk, request);
			if (bool) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		// 批量删除
		if ("alldel".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllDelList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("whichpk", whichpk);
				request.setAttribute("delall", whichpk);
			}
		}
		// 单个审核
		if ("Auditing".equals(act)) {
			String pk = request.getParameter("pkValue");
			HashMap<String, String> map = service.getAuditingList(pk, request);
			request.setAttribute("rs1", map);
			return mapping.findForward("shenhe");
		}
		// 批量审核
		if ("allAuditing".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllAuditingList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("allpass", "ok");
			} else {
				request.setAttribute("allpass", "no");
			}
		}
		if("xy".equals(userType) || "stu".equals(userType)){
			String bmmc = (String) request.getSession().getAttribute("bmmc");
			HashMap<String, String> stumap = new HashMap<String, String>();
			String srusql = "select distinct b.xydm,b.zydm from view_njxyzybj b where xymc='"+bmmc+"'";
			stumap = dao.getMap(srusql, new String[]{}, new String[]{"xydm","zydm"});
			rs1.put("xydm", stumap.get("xydm"));
			request.setAttribute("bmxxmc", bmmc);
			xydm = stumap.get("xydm");
 		}
		request.setAttribute("who", userType);
		request.setAttribute("rsNum", count);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}

	// 中国劳动关系毕业生基础信息
	private ActionForward bysjcxxtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String realTable = "yxjzyjsb";
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String nd = request.getParameter("nd");
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		rs1.put("xydm", xydm);
		rs1.put("zydm", zydm);
		request.setAttribute("realTable", realTable);
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		ZgldgxxyService service = new ZgldgxxyService();
		String tjxm = request.getParameter("tjxm");
		String[] keys = null;
		String[] values = null;
		if ("tjxm".equals(tjxm)) {
//			String sql = " select sfzh,xh,xm,xb,(select mzmc from mzdmb a where a.mzdm=b.mz)mz,(select zzmmmc from zzmmdmb a where a.zzmmdm=b.zzmm)zzmm,csrq,xz,(select xlmc from xldmb a where a.xldm=b.xldm) xldm,syd,pyfs,sjhm,sfdk from view_xsxxb b where "
			String sql = " select sfzh,xh,xm,xb,(select mzmc from mzdmb a where a.mzdm=b.mz)mz,(select zzmmmc from zzmmdmb a where a.zzmmdm=b.zzmm)zzmm,csrq,xz,xlmc xldm,syd,pyfs,sjhm,sfdk from view_xsxxb b where "		
				+ "xydm='"
					+ xydm
					+ "' and zydm ='"
					+ zydm
					+ "' and xz+nj='" + nd + "' order by xh";
			response.reset();
			response.setContentType("application/vnd.ms-excel");	
			
			DAO dao = DAO.getInstance();
			Vector<Object> vec = new Vector<Object>();
			WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet ws = wwb.createSheet("数据导出", 0);
			try {
				String ColumnName[] = dao.getColumnName(sql);
				String ColumnNameCN[] = dao.getColumnNameCN(ColumnName,"xsxxb".toUpperCase());
				for (int m = 0; m < ColumnNameCN.length; m++) {
					if("是否贷款".equals(ColumnNameCN[m])){
						ws.addCell(new Label(m, 0,"是否贫困生"));
					}else{
						ws.addCell(new Label(m, 0, ColumnNameCN[m]));
					}
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
			
			return mapping.findForward("expdata");
		} else {
			keys = new String[] { "shzh", "xh", "xm", "xb", "mzmc", "zzmm",
					"csrq", "xz", "xl", "syszd", "pyfs", "sjhm", "sfpks" };
			values = new String[] { "身份证号", "学号", "姓名", "性别", "民族", "政治面貌",
					"出生日期", "学制", "学历", "生源所在地", "培养方式", "手机", "是否贫困生" };

			rs2 = service.getBysjcxxtj(xydm, zydm, nd);
		}
		//
		// }
		// }
		request.setAttribute("rs", rs2);
		request.setAttribute("tabtop", service.getTabTopMap(keys, values));
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("rs1", rs1);
		return mapping.findForward("success");
	}

	// 中国劳动关系学院院报名
	private ActionForward bysbmtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm myform = (CommanForm) form;
		String realTable = "yxjzyjsb";
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		int count = 0;
//		HttpSession sessopm = request.getSession();
		String userType = (String) request.getSession()
				.getAttribute("userType");
		request.setAttribute("usertype", userType);
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		HashMap<String, String> rs3 = new HashMap<String, String>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		DAO dao = DAO.getInstance();
		rs1.put("xydm", xydm);
		rs1.put("zydm", zydm);
		request.setAttribute("realTable", realTable);
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("sydList", stuDao.getSsList());
		ZgldgxxyService service = new ZgldgxxyService();
		JyglModel jygl = new JyglModel();

		if ("xx".equals(userType) || userType == "xx"
				|| "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType) || userType == "xy") {
			request.setAttribute("who", "xy");
		} else {
			request.setAttribute("who", "");
		}
		if ("add".equals(act)) {
			// service.yxjzyjs_toGBK(myform);
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			
			
			String userName = (String) request.getSession().getAttribute("userName");
			String srusql1 = "select * from jygl_xsjbxxb where xsxh='"+userName+"'";
			HashMap<String, String> stumap1 = dao.getMap(srusql1, new String[]{}, new String[]{"xsxh","name",});
			rs1.put("xh", stumap1.get("xsxh"));
			rs1.put("xm", stumap1.get("name"));
			HashMap<String, String> stumap2 = dao.getMap(
					"select distinct b.xydm,b.zydm,b.xymc,b.zymc,b.xb,b.xm from view_xsjbxx b where b.xh='"+userName+"'", new String[]{}, 
							new String[]{"xydm","zydm","xymc","zymc","xm","xb"});
			rs1.put("zymc", stumap2.get("zymc"));
			rs1.put("xymc", stumap2.get("xymc"));
			rs1.put("zydm", stumap2.get("zydm"));
			rs1.put("xydm", stumap2.get("xydm"));
			rs1.put("xm", stumap2.get("xm"));
			rs1.put("xb",stumap2.get("xb"));
			rs1.put("xh", userName);
				
			if ("add".equals(doType)) {
//				String xxdm = (String) request.getSession().getAttribute("xxdm");
				String xsxh = jygl.getXh();
				boolean bool = false;
					String[] str = dao.getOneRs("select sfsh from jygl_xsjbxxb where xsxh=?", 
							new String[]{xsxh}, new String[]{"sfsh"});
					if(str != null){
						if("已通过√".equals(str[0])){
							bool = true;
						}
				}
				if(bool){
				boolean judge = service.bysbm_save(jygl, request);
				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
				}else{
					request.setAttribute("mysh", "yes");
				}
			}
			request.setAttribute("rs1", rs1);
			request.setAttribute("rs", rs);
			return mapping.findForward("add");
		}
		if ("query".equals(act)) {
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			rs = service.getBysbmList(jygl, myform, request);
			count = service.bysbmListNum(jygl, request);
			String bkxm = DealString.toGBK(myform.getBmxm());
			topTr = service.bysbmTitle(bkxm);
			myform.getPages().setMaxRecord(
					Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
		}
		if ("view".equals(act)) {
			String pk = request.getParameter("pkValue");
			rs2 = service.getBysbmViewList(pk);
			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");
			return mapping.findForward("view");
		}
		if ("update".equals(act)) {
			String pk = request.getParameter("pkValue");
			if ("update".equals(doType)) {
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				request.setAttribute("rs1", rs1);
				request.setAttribute("rs", rs);
				if ("update".equals(doType)) {
					boolean judge = service.bysbm_modify(jygl, pk, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}
			}
			String[] colList = new String[] { "id", "xh", "bmxm", "xm", "xb",
					"xydm", "zydm", "syd", "bkqx", "lxfs", "bz" };
			String sql = "select id,xh,bmxm,xm,xb,xydm,zydm,syd,bkqx,lxfs,bz from bysbmb where id=?";
			rs2 = dao.getArrayList(sql, new String[] { pk }, colList);

			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");
			request.setAttribute("rs1", rs2);
			request.setAttribute("rs", rs);
			return mapping.findForward("update");
		}
		// 删除
		if ("del".equals(act)) {
			String pk = request.getParameter("pkValue");
			boolean bool = service.getDelBysbmList(pk, request);
			if (bool) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		// 批量删除
		if ("alldel".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllDelBysbmList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("whichpk", whichpk);
				request.setAttribute("delall", whichpk);
			}
		}
		// 单个审核
		if ("Auditing".equals(act)) {
			String pk = request.getParameter("pkValue");
			HashMap<String, String> map = service.getAuditingList(pk, request);
			request.setAttribute("rs1", map);
			return mapping.findForward("shenhe");
		}
		// 批量审核
		if ("allAuditing".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllAuditingList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("allpass", "ok");
			} else {
				request.setAttribute("allpass", "no");
			}
		}
		// 导出
		if ("expdata".equals(act)) {
			BeanUtils.copyProperties(jygl, myform);
			service.getExpList(jygl, myform, response);
		}
		if("stu".equals(userType)){
			String userName = (String) request.getSession().getAttribute("userName");
			HashMap<String, String> stumap = new HashMap<String, String>();
			String srusql = "select distinct b.xydm,b.zydm,a.xb from view_xsjbxx a,view_njxyzybj b where a.zymc=b.zymc and xh='"+userName+"'";
			stumap = dao.getMap(srusql, new String[]{}, new String[]{"xydm","zydm","xb"});
			
			String srusql1 = "select distinct b.xymc,b.zymc from view_xsjbxx a,view_njxyzybj b where a.zymc=b.zymc and xh='"+userName+"'";
			HashMap<String, String> stumap1 = dao.getMap(srusql1, new String[]{}, new String[]{"xymc","zymc"});
			if(stumap1 != null){
				request.setAttribute("xymcdt", stumap1.get("xymc"));
				request.setAttribute("zymcdt", stumap1.get("zymc"));
			}
			
			
			rs1.put("xh", userName);
			rs1.put("xb", stumap.get("xb"));
			rs1.put("xydm", stumap.get("xydm"));
			rs1.put("zydm", stumap.get("zydm"));
			srusql = "select xm from xsxxb where xh='"+userName+"'";
			stumap = dao.getMap(srusql, new String[]{}, new String[]{"xm"});
			rs1.put("xm", stumap.get("xm"));
		}
		if("xy".equals(userType)){
			HttpSession sessioin = request.getSession();
			String bmmc = (String) sessioin.getAttribute("bmmc");
			String xysql = "select distinct b.xydm from view_njxyzybj b where b.xymc='"+bmmc+"'";
			rs3 = dao.getMap(xysql, new String[]{}, new String[]{"xydm"});
			request.setAttribute("xydmdt", bmmc);
			if(rs1 != null){
				xydm = rs3.get("xydm");
			}
			rs1 = service.map_back(myform);
			request.setAttribute("xyList", commen_util.get_xyList());
			request.setAttribute("zyList", commen_util.get_zyList(xydm));
			request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		}
		
		request.setAttribute("who", userType);
		request.setAttribute("rsNum", count);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}

	// 中国劳动关系学院院报名
	private ActionForward zphcs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm myform = (CommanForm) form;
		String realTable = "yxjzyjsb";
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		int count = 0;
		String userType = (String) request.getSession()
				.getAttribute("userType");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		rs1.put("xydm", xydm);
		rs1.put("zydm", zydm);
		request.setAttribute("realTable", realTable);
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("sydList", stuDao.getSsList());
		ZgldgxxyService service = new ZgldgxxyService();
		JyglModel jygl = new JyglModel();
		request.setAttribute("ndList", Base.getXnndList());

		if ("xx".equals(userType) || userType == "xx"
				|| "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType) || userType == "xy") {
			request.setAttribute("who", "xy");
		} else {
			request.setAttribute("who", "");
		}
		if ("add".equals(act)) {
			// service.yxjzyjs_toGBK(myform);
			rs1 = service.map_back(myform);
			// BeanUtils.copyProperties(jygl, myform);
			request.setAttribute("rs1", rs1);
			request.setAttribute("rs", rs);

			if ("add".equals(doType)) {
				boolean bool = StandardOperation.insert("zphxxfbb ",
						new String[] { "xydm", "zplx", "zpcs", "dwsl", "gwsl",
								"zpsj" }, new String[] {
								DealString.toGBK(myform.getXydm()),
								DealString.toGBK(myform.getZplx()),
								DealString.toGBK(myform.getZpcs()),
								DealString.toGBK(myform.getDwsl()),
								DealString.toGBK(myform.getGwsl()),
								DealString.toGBK(myform.getZpsj()), }, request);
				if (bool) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
			return mapping.findForward("add");
		}
		if ("query".equals(act)) {
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			rs = service.getzhhcsList(jygl, myform, request);
			count = service.zphcsListNum(jygl, request);
			topTr = service.zphcsTitle();
			myform.getPages().setMaxRecord(
					Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
		}
		if ("update".equals(act)) {
			String pk = request.getParameter("pkValue");
			if ("update".equals(doType)) {
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				request.setAttribute("rs1", rs1);
				request.setAttribute("rs", rs);
				if ("update".equals(doType)) {
					boolean judge = service.zphcs_modify(jygl, pk, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}
			}
			DAO dao = DAO.getInstance();
			String[] colList = new String[] { "id", "xydm", "zplx", "zpcs",
					"dwsl", "gwsl", "zpsj" };
			String sql = "select id,xydm,zplx,zpcs,dwsl,gwsl,zpsj from zphxxfbb where id=?";
			rs2 = dao.getArrayList(sql, new String[] { pk }, colList);

			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");
			request.setAttribute("rs1", rs2);
			request.setAttribute("rs", rs);
			return mapping.findForward("update");
		}
		// 批量删除
		if ("alldel".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllDelzphcsList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("whichpk", whichpk);
				request.setAttribute("delall", whichpk);
			}
		}
		// 导出
		if ("expdata".equals(act)) {
			BeanUtils.copyProperties(jygl, myform);
			service.getExpList(jygl, myform, response);
		}
		request.setAttribute("who", userType);
		request.setAttribute("rsNum", count);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}

	// 中国劳动关系学院就业意向调查
	private ActionForward bysjyjzqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm myform = (CommanForm) form;
		String realTable = "yxjzyjsb";
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		int count = 0;
		String userType = (String) request.getSession()
				.getAttribute("userType");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rsmap = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		HashMap<String, String> hjrs = new HashMap<String, String>();
		HashMap<String, String> toptr = new HashMap<String, String>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		rs1.put("xydm", xydm);
		rs1.put("zydm", zydm);
		request.setAttribute("realTable", realTable);
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		ZgldgxxyService service = new ZgldgxxyService();
		JyglModel jygl = new JyglModel();
		String tj = request.getParameter("tj");
		if ("xx".equals(userType) || userType == "xx"
				|| "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType) || userType == "xy") {
			request.setAttribute("who", "xy");
		} else {
			request.setAttribute("who", "");
		}
		if ("add".equals(act)) {
			// service.yxjzyjs_toGBK(myform);
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			request.setAttribute("rs1", rs1);
			request.setAttribute("rs", rs);
			boolean bool = false;
			String pk = myform.getXh();
			DAO dao = DAO.getInstance();
			String[] colList = new String[] { "xm" };
			String sql = "select xm from jyyxdxb where xh=?";
			rs2 = dao.getArrayList(sql, new String[] { pk }, colList);
			if (rs2 != null) {
				if (rs2.size() < 1) {
					bool = true;
				}
			}
			if (bool) {
				if ("add".equals(doType)) {
					boolean judge = service.addJyyxdx(jygl, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}
			} else {
				request.setAttribute("yjsave", "yjsave");
			}
			return mapping.findForward("add");
		}
		if ("query".equals(act)) {
			if ("tj".equals(tj)) {
				request.setCharacterEncoding("GBK");
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				rsmap = service.getjyjzqkbsbListtj(jygl, myform, request);
				ArrayList<HashMap<String, String>> hjrs1 = service.getjykndzyzyListtj(jygl, myform, request);
				// count = service.jyyxdcListNum(jygl, request);
				request.setAttribute("hjrs1", hjrs1);
				request.setAttribute("hjrs2", hjrs1);
				request.setAttribute("hjrs3", hjrs1);
				
				String[] opCol = new String[] {"zymc"};
				DAO dao = DAO.getInstance();
				String sql = "select zymc,count(xsxh) num from jygl_jyxy where xzdm='3' and xymc = '" + DealString.toGBK(myform.getXymc()) + "' group by zymc order by num";
				ArrayList<HashMap<String, String>> list = dao.getArrayList(sql,new String[]{}, opCol);
				request.setAttribute("hjrs4", list);
				request.setAttribute("hjrs5", list);
				request.setAttribute("hjrs6", list);
				
				toptr = service.getTopTr();
			} else {
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				rs = service.getjyyxdcList(jygl, myform, request);
				count = service.jyyxdcListNum(jygl, request);
				topTr = service.jyyxTitle();
				myform.getPages().setMaxRecord(
						Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
			}
		}
		request.setAttribute("who", userType);
		request.setAttribute("rsNum", count);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("toptr", toptr);
		request.setAttribute("rsmap", rsmap);
		request.setAttribute("hjrs", hjrs);
		if ("tj".equals(tj)) {
			return mapping.findForward("successtj");
		} else {
			return mapping.findForward("success");
		}
	}

	// 中国劳动关系学院就业而推荐
	private ActionForward bysjytjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm myform = (CommanForm) form;
		String realTable = "bysjytjb";
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		int count = 0;
		String userType = (String) request.getSession().getAttribute("userType");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		rs1.put("xydm", xydm);
		rs1.put("zydm", zydm);
		request.setAttribute("realTable", realTable);
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		ZgldgxxyService service = new ZgldgxxyService();
		FormModleCommon.setNjXyZyBjList(request);
		JyglModel jygl = new JyglModel();
		if ("xx".equals(userType) || userType == "xx"
				|| "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType) || userType == "xy") {
			request.setAttribute("who", "xy");
		} else {
			request.setAttribute("who", "");
		}
		if ("add".equals(act)) {
			// service.yxjzyjs_toGBK(myform);
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			
			HttpSession session = request.getSession();
			String usertype = request.getSession().getAttribute("userType").toString();
			String username = request.getSession().getAttribute("userName").toString();
			if("stu".equals(usertype)){
				DAO dao = DAO.getInstance();
				String[] colList = new String[] {"xsxh","name","xb","id","zymc","xz","xlmc","sydq"};
				String sql = "select xsxh,name,(select distinct b.sydq from dmk_sydq b where a.sydqdm=b.sydqdm) sydq,(case xbdm when '1' then '男' when '2' then '女' else '' end) xb," 
					+"id,zymc,xzdm xz,(select xlmc from xldmb b where a.xldm=b.xldm) xlmc "
					+"from jygl_xsjbxxb a where xsxh=?";
				rs1 = dao.getMap(sql, new String[] { username }, colList);
				rs1.put("byxx", session.getAttribute("xxmc").toString());
			}
			
			request.setAttribute("rs1", rs1);
			request.setAttribute("rs", rs);
			if ("add".equals(doType)) {
				boolean judge = service.bysjytj_save(jygl, request);
				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
			return mapping.findForward("add");
		}
		if ("query".equals(act)) {
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			rs = service.getJybjbList(jygl, myform, request);
			count = service.querybysjytjNum(jygl, request);
			topTr = service.querybysjytjTitle();
			myform.getPages().setMaxRecord(
					Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
		}
		if ("view".equals(act)) {
			String pk = request.getParameter("pkValue");
			rs2 = service.getjytjViewList(pk);
			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");
			return mapping.findForward("view");
		}
		if ("update".equals(act)) {

			String pk = request.getParameter("pkValue");

			if ("update".equals(doType)) {
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				request.setAttribute("rs1", rs1);
				request.setAttribute("rs", rs);
				if ("update".equals(doType)) {
					boolean judge = service.bysjytj_modify(jygl, pk, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}
			}
			DAO dao = DAO.getInstance();
			String[] colList = new String[] { "xsxh", "name", "xb", "id",
					"csrq", "zzmm", "sydq", "lxdh", "sjhm", "txdz", "yzbm",
					"byxx", "xlmc", "zymc", "xz", "bysj", "jlqk", "shsj1",
					"shsj2", "shsj3", "wyyz", "jb", "jsjsp", "tcnl", "pyfs",
					"jyfw", "yxyj", "jybmyj", "lxbm", "bmlxr", "bmlxdh","bz","sczp"};
			String sql = "select * from bysjytjb where xsxh=?";
			rs2 = dao.getArrayList(sql, new String[] { pk }, colList);
			request.setAttribute("rs1", rs2);
			request.setAttribute("rs", rs);
			return mapping.findForward("update");
		}
		// 删除
		if ("del".equals(act)) {
			String pk = request.getParameter("pkValue");
			boolean bool = service.getDelList(pk, request);
			if (bool) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		// 批量删除
		if ("alldel".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllbysjytjDelList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("whichpk", whichpk);
				request.setAttribute("delall", whichpk);
			}
		}
		// 单个审核
		if ("Auditing".equals(act)) {
			String pk = request.getParameter("pkValue");
			
			if ("shenhe".equals(doType)) {
				boolean judge = service.bysjytj_modify(jygl, pk, request);
				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
			DAO dao = DAO.getInstance();
			String[] colList = new String[] { "xsxh", "name", "xb", "id",
					"csrq", "zzmm", "sydq", "lxdh", "sjhm", "txdz", "yzbm",
					"byxx", "xlmc", "zymc", "xz", "bysj", "jlqk", "shsj1",
					"shsj2", "shsj3", "wyyz", "jb", "jsjsp", "tcnl", "pyfs",
					"jyfw", "yxyj", "jybmyj", "lxbm", "bmlxr", "bmlxdh","bz"};
			String sql = "select * from bysjytjb where xsxh=?";
			rs2 = dao.getArrayList(sql, new String[] { pk }, colList);
			
			request.setAttribute("rs1", rs2);
			return mapping.findForward("successshenhe");
		}
		// 批量审核
		if ("allAuditing".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllAuditingList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("allpass", "ok");
			} else {
				request.setAttribute("allpass", "no");
			}
		}
		// 照片上传
		if ("sczp".equals(act)) {
			String xsxh = request.getParameter("pkxsxh");
			request.setAttribute("pkxsxh", xsxh);
			if("upload".equals(doType)){
				
					String dir = servlet.getServletContext().getRealPath("/jygl/zgldgx/picture");
//					String dir2 = request.getContextPath();
					File f = new File(dir);
					if (!f.exists()) {
						f.mkdir();
					}
					
					CommanForm hff = (CommanForm) form;
					FormFile file = hff.getFile();
					if (file == null) {
						return mapping.findForward("false");
					}
					Timestamp date = new Timestamp(System.currentTimeMillis());
					String dateStr = date.toString().substring(0, 19);
					dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
					int size = file.getFileSize();
					String fName=file.getFileName();
					InputStream in = file.getInputStream();
					String filePath = dir + "/" + dateStr+fName;
					String savepath = "/jygl/zgldgx/picture/" + dateStr + fName;
					
					DAO dao = DAO.getInstance();
					String sxsq = "select sczp from bysjytjb where xsxh='"+xsxh+"'";
					String[] rst = dao.getArray(sxsq, new String[]{},"sczp");
					if(StringUtils.isArrayNotNull(rst)){
						String dirpath = servlet.getServletContext().getRealPath(rst[0]);
						new File(dirpath).delete();
					}
					
					boolean judge = false;
//					judge = StandardOperation.insert("bysjytjb", new String[] { "sczp", },
//							new String[]{filePath}, request);
					String sql = "update bysjytjb set sczp='"+savepath+"' where xsxh='"+xsxh+"'";
					judge = StandardOperation.update("bysjytjb", sql, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "mo");
					}
					
					OutputStream out = new FileOutputStream(filePath);
					int bytesRead = 0;
					byte[] buffer = new byte[size];
					while ((bytesRead = in.read(buffer, 0, size)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					out.close();
					in.close();
					
					request.setAttribute("dataImported", "success");

	
				
			}
			return mapping.findForward("sczp");
		}
		if("admin".equals(userType) || "xx".equals(userType)){
			userType = "xx";
		}
//		HttpSession session = request.getSession();
		String usertype = request.getSession().getAttribute("userType").toString();
		String username = request.getSession().getAttribute("userName").toString();
		String userNameReal = request.getSession().getAttribute("userNameReal").toString();
		if("stu".equals(usertype)){
			rs1.put("xsxh", username);
			rs1.put("name", userNameReal);
		}
		request.setAttribute("who", userType);
		request.setAttribute("rsNum", count);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}

	// 中国劳动关系学院就业意向调查
	private ActionForward jyyxdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm myform = (CommanForm) form;
		String realTable = "yxjzyjsb";
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		int count = 0;
		String userType = (String) request.getSession().getAttribute("userType");
		String userName = (String) request.getSession().getAttribute("userName");
		request.setAttribute("usertype", userType);
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rsmap = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		HashMap<String, String> hjrs = new HashMap<String, String>();
		HashMap<String, String> toptr = new HashMap<String, String>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		rs1.put("xydm", xydm);
		rs1.put("zydm", zydm);
		
		request.setAttribute("realTable", realTable);
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		ZgldgxxyService service = new ZgldgxxyService();
		JyglModel jygl = new JyglModel();
		String tj = request.getParameter("tj");
		if ("xx".equals(userType) || userType == "xx"
				|| "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType) || userType == "xy") {
			request.setAttribute("who", "xy");
		} else {
			request.setAttribute("who", "");
		}
		if ("add".equals(act)) {
			// service.yxjzyjs_toGBK(myform);
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			request.setAttribute("rs1", rs1);
			request.setAttribute("rs", rs);
			if("stu".equals(userType)){
				HashMap<String, String> stumap = new HashMap<String, String>();
				String srusql = "select distinct b.xydm,b.xymc,b.zydm,b.zymc from xsxxb a,view_njxyzybj b where a.zymc=b.zymc and xh='"+userName+"'";
				stumap = dao.getMap(srusql, new String[]{}, new String[]{"xydm","zydm","xymc","zymc"});
				rs1.put("xh", userName);
				rs1.put("xydm", stumap.get("xydm"));
				rs1.put("zydm", stumap.get("zydm"));
				rs1.put("xymc", stumap.get("xymc"));
				rs1.put("zymc", stumap.get("zymc"));
				srusql = "select xm from xsxxb where xh='"+userName+"'";
				stumap = dao.getMap(srusql, new String[]{}, new String[]{"xm"});
				rs1.put("xm", stumap.get("xm"));
			}
			boolean bool = false;
			String pk = myform.getXh();
			String[] colList = new String[] { "xm" };
			String sql = "select xm from jyyxdxb where xh=?";
			rs2 = dao.getArrayList(sql, new String[] { pk }, colList);
			if (rs2 != null) {
				if (rs2.size() < 1) {
					bool = true;
				}
			}
			if (bool) {
				if ("add".equals(doType)) {
					boolean judge = service.addJyyxdx(jygl, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}
			} else {
				request.setAttribute("yjsave", "yjsave");
			}
			return mapping.findForward("add");
		}
		if ("query".equals(act)) {
			if ("tj".equals(tj)) {
				request.setCharacterEncoding("GBK");
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				rsmap = service.getjyyxdcListtj(jygl, myform, request);
				hjrs = service.getjyyxdchjListtj(jygl, myform, request);
				// count = service.jyyxdcListNum(jygl, request);
				toptr = service.getTopTr();
			} else {
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				rs = service.getjyyxdcList(jygl, myform, request);
				count = service.jyyxdcListNum(jygl, request);
				topTr = service.jyyxTitle();
				myform.getPages().setMaxRecord(
						Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
			}
		}
		if ("view".equals(act)) {
			String pk = request.getParameter("pkValue");
			rs2 = service.getViewList(pk);
			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");
			return mapping.findForward("view");
		}
		if ("update".equals(act)) {

			String pk = request.getParameter("pkValue");
			rs2 = service.getViewList(pk);
			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");

			if ("update".equals(doType)) {
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				request.setAttribute("rs1", rs1);
				request.setAttribute("rs", rs);
				if ("update".equals(doType)) {
					boolean judge = service.yxjzyjs_modify(jygl, pk, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}
			}
			request.setAttribute("rs1", rs2);
			request.setAttribute("rs", rs);
			return mapping.findForward("update");
		}
		// 删除
		if ("del".equals(act)) {
			String pk = request.getParameter("pkValue");
			boolean bool = service.getDelList(pk, request);
			if (bool) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		// 批量删除
		if ("alldel".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAlljyyxdcList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("whichpk", whichpk);
				request.setAttribute("delall", whichpk);
			}
		}
		// 单个审核
		if ("Auditing".equals(act)) {
			String pk = request.getParameter("pkValue");
			HashMap<String, String> map = service.getAuditingList(pk, request);
			request.setAttribute("rs1", map);
			return mapping.findForward("shenhe");
		}
		// 批量审核
		if ("allAuditing".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllAuditingList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("allpass", "ok");
			} else {
				request.setAttribute("allpass", "no");
			}
		}
		// 导出就业意向调查统计
		if ("expjyyx".equals(act)) {
			String modelPath = servlet.getServletContext().getRealPath("")
					+ "/print/ggldgx_bysjyyxdcb.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			BeanUtils.copyProperties(jygl, myform);
			rsmap = service.getjyyxdcListtj(jygl, myform, request);
			hjrs = service.getjyyxdchjListtj(jygl, myform, request);
			service.printByxj(wwb, myform, rsmap, hjrs);
			return mapping.findForward("");
		}
		
		if("stu".equals(userType)){
			String srusql = "select distinct b.xydm,b.zydm from xsxxb a,view_njxyzybj b where a.zymc=b.zymc and xh='"+userName+"'";
			rs1 = dao.getMap(srusql, new String[]{}, new String[]{"xydm","zydm"});
			String srusql1 = "select distinct b.xymc,b.zymc from xsxxb a,view_njxyzybj b where a.zymc=b.zymc and xh='"+userName+"'";
			HashMap<String, String> rss = dao.getMap(srusql1, new String[]{}, new String[]{"xymc","zymc"});
			if(rss!=null){
				request.setAttribute("xydmdt", rss.get("xymc"));
				request.setAttribute("zydmdt", rss.get("zymc"));
			}
			request.setAttribute("stuname", (String)request.getSession().getAttribute("userName"));
		}
		if("xy".equals(userType)){
			HttpSession sessioin = request.getSession();
			String bmmc = (String) sessioin.getAttribute("bmmc");
			String xysql = "select distinct b.xydm from view_njxyzybj b where b.xymc='"+bmmc+"'";
			rs1 = dao.getMap(xysql, new String[]{}, new String[]{"xydm"});
			request.setAttribute("xydmdt", bmmc);
			if(rs1 != null){
				xydm = rs1.get("xydm");
			}
			request.setAttribute("xyList", commen_util.get_xyList());
			request.setAttribute("zyList", commen_util.get_zyList(xydm));
			request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		}
		
		
		request.setAttribute("who", userType);
		request.setAttribute("rsNum", count);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("toptr", toptr);
		request.setAttribute("rsmap", rsmap);
		request.setAttribute("hjrs", hjrs);
		if ("tj".equals(tj)) {
			return mapping.findForward("successtj");
		} else {
			return mapping.findForward("success");
		}
	}

	// 中国劳动关系学院就业情况报送
	private ActionForward jyjzjkbs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm myform = (CommanForm) form;
		String realTable = "yxjzyjsb";
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		int count = 0;
		String userType = (String) request.getSession()
				.getAttribute("userType");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		rs1.put("xydm", xydm);
		rs1.put("zydm", zydm);
		request.setAttribute("realTable", realTable);
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("sydList", stuDao.getSsList());
		ZgldgxxyService service = new ZgldgxxyService();
		JyglModel jygl = new JyglModel();

		if ("xx".equals(userType) || userType == "xx"
				|| "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType) || userType == "xy") {
			request.setAttribute("who", "xy");
		} else {
			request.setAttribute("who", "");
		}
		if ("add".equals(act)) {
			// service.yxjzyjs_toGBK(myform);
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			request.setAttribute("rs1", rs1);
			request.setAttribute("rs", rs);
			if ("add".equals(doType)) {
				boolean judge = service.Jyjzqkbs_save(jygl, request);
				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
			return mapping.findForward("add");
		}
		if ("query".equals(act)) {
			rs1 = service.map_back(myform);
			BeanUtils.copyProperties(jygl, myform);
			rs = service.getJyjzqkbsList(jygl, myform, request);
			count = service.jyqkbsListNum(jygl, request);
			topTr = service.jyjzqkTitle();
			myform.getPages().setMaxRecord(
					Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
		}
		if ("view".equals(act)) {
			String pk = request.getParameter("pkValue");
			rs2 = service.getJyjzqkViewList(pk);
			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");
			return mapping.findForward("view");
		}
		if ("update".equals(act)) {
			String pk = request.getParameter("pkValue");
			if ("update".equals(doType)) {
				rs1 = service.map_back(myform);
				BeanUtils.copyProperties(jygl, myform);
				request.setAttribute("rs1", rs1);
				request.setAttribute("rs", rs);
				if ("update".equals(doType)) {
					boolean judge = service.jyjzqk_modify(jygl, pk, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}
			}
			DAO dao = DAO.getInstance();
			String[] colList = new String[] { "id", "gzjcs", "zywt", "jyxsfx",
					"lxr", "lxdh", "tbsm" };
			String sql = "select id,gzjcs,zywt,jyxsfx,lxr,lxdh,tbsm from jyjzqkbsb where id=?";
			rs2 = dao.getArrayList(sql, new String[] { pk }, colList);

			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");
			request.setAttribute("rs1", rs2);
			request.setAttribute("rs", rs);
			return mapping.findForward("update");
		}
		// 批量删除
		if ("alldel".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllDelJyjzqkList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("whichpk", whichpk);
				request.setAttribute("delall", whichpk);
			}
		}
		request.setAttribute("who", userType);
		request.setAttribute("rsNum", count);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}
}
