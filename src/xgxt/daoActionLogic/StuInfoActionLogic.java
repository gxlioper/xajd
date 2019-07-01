package xgxt.daoActionLogic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.form.CommanForm;
import xgxt.form.ShgcForm;
import xgxt.jygl.dao.DwxzDao;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.dao.StudentInfoDao;
import xgxt.studentInfo.dao.StudentInfoLogicDAO;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.ArchiveService;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.studentInfo.zgkd.XsxxZgkdService;
import xgxt.utils.CheckPower;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.model.stu_info_model;
import xgxt.xljk.lrh_Util.util.stu_info_util;

import common.Globals;

public class StuInfoActionLogic {
	/***
	 * 加载页面参数
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		XsxxZgkdService service = new XsxxZgkdService();
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		String writeAble = Base.getWriteAble(request);
		
		request.setAttribute("mzList", service.getMzList());
		request.setAttribute("zzmmList", service.getZzmmList());
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		
		request.setAttribute("userType", userType);//用户类型		
	}
	
	/**
	 * 历届学生转档信息管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * */
	public ActionForward stuArchives(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// to-do 要提供能把当前学生转成毕业学生
		ShgcForm myForm = (ShgcForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		ArchiveService service = new ArchiveService();
		
		String doType = (request.getParameter("doType"));
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userSpecType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String xxdm = StandardOperation.getXxdm();
		String go = request.getParameter("go");

		String bjdm = myForm.getBjdm();
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = DealString.toGBK(myForm.getNj());
		String xm = DealString.toGBK(myForm.getXm());
		String xh = DealString.toGBK(myForm.getXh());
		String sfzh = DealString.toGBK(myForm.getSfzh());//身份证号
		String dwxz = DealString.toGBK(myForm.getDwxz());//身份证号
		
		//南通职业 机要号（类似学生档案编号)、转档单位地址、转档单位名称、转移时间、转档原因。
		String jyh =  DealString.toGBK(myForm.getJyh());//机要号
		String zddwdz = DealString.toGBK(myForm.getZddwdz());//转档单位地址
		String zddwmc = DealString.toGBK(myForm.getZddwmc());//转档单位名称
		String kssj = DealString.toGBK(myForm.getKssj());//转档单位名称
		String jssj = DealString.toGBK(myForm.getJssj());//转档单位名称
		String zdyy = DealString.toGBK(myForm.getZdyy());//转档单位名称
		
		String realTable = "stu_history_lab";
		String tableName="view_stu_his_archives";
		
		if (userSpecType.equalsIgnoreCase("xy")&& (StringUtils.isNull(xydm))) {
			xydm = userDep;
			myForm.setXydm(userDep);
		}
		
		if ("add".equalsIgnoreCase(doType)) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {// 安徽建筑工业学院
				List<HashMap<String, String>> dkjlList = new ArrayList<HashMap<String, String>>();
				String[] dkjlArr = { "无", "有" };
				for (int i = 0; i < dkjlArr.length; i++) {
					HashMap<String, String> temmap = new HashMap<String, String>();
					temmap.put("dkjl", dkjlArr[i]);
					dkjlList.add(temmap);
				}
				request.setAttribute("dkjlList", dkjlList);
				request.setAttribute("isAHJZGYXY", "is");
			}
			
			request.setAttribute("rs", service.queryLjxsdaxxOne(myForm));//查询信息 			
			request.setAttribute("oper", request.getParameter("type"));
			appendProperties(request, xydm, zydm, nj);//加载页面属性
			return new ActionForward("/shgc/stu_info/stu_archives.jsp");
		}
		if ("savaArchive".equalsIgnoreCase(doType)) {
			// to-do 保存历届学生档案信息
			boolean result = service.saveHistoryArchive(myForm, request);//保存历届学生档案信息
			
			request.setAttribute("result", result);
			request.setAttribute("oper", "oper");
			request.setAttribute("rs", service.queryLjxsdaxxOne(myForm));//查询信息 			
			appendProperties(request, xydm, zydm, nj);
			return mapping.findForward("page");
		}
		if ("del".equalsIgnoreCase(doType)) {
			//  to-do 删除历届学生档案信息
			boolean flag = service.delHistoryArchive(request);
			request.setAttribute("result", flag);
		}		
			
		if(userOnLine.equalsIgnoreCase("student")){
			xh = userName;
			go = "go";
		}
		//数据查询
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		//获取表头
		String[] title_en = new String[] {};
		String[] title_cn = new String[] {};
		String sql = "";
		StringBuffer query = new StringBuffer(" where 1=1");
		query.append(xh == null || xh.trim().length() < 1 ? "" : " and a.xh like '%" + DealString.replaceImmitStr(xh.trim()) + "%'");
		query.append(xm == null || xm.trim().length() < 1 ? "" : " and exists(select b.nj from view_xsjbxx b where a.xh=b.xh and b.xm like '%" + DealString.replaceImmitStr(xm.trim()) + "%')");
		query.append(nj == null || nj.trim().length() < 1 ? "" : " and exists(select b.nj from view_xsjbxx b where a.xh=b.xh and b.nj = '" + nj.trim() + "')");
		query.append(bjdm == null || bjdm.trim().length() < 1 ? "" : " and exists(select b.xh from view_xsjbxx b where a.xh=b.xh and b.bjdm= '" + bjdm.trim() + "')");
		query.append(zydm == null || zydm.trim().length() < 1 ? "" : " and exists(select b.xh from view_xsjbxx b where a.xh=b.xh and b.zydm = '" + zydm.trim() + "')");
		query.append(xydm == null || xydm.trim().length() < 1 ? "" : " and exists(select 1 from view_xsjbxx b where a.xh=b.xh and b.xydm = '" + xydm.trim() + "')");
		query.append(jyh == null || jyh.trim().length() < 1 ? "" : " and jyh like '" + jyh.trim() + "'");
		query.append(zddwdz == null || zddwdz.trim().length() < 1 ? "" : " and zddwdz like '%" + zddwdz.trim() + "%'");
		query.append(zddwmc == null || zddwmc.trim().length() < 1 ? "" : " and zddwmc like '%" + zddwmc.trim() + "%'");
		query.append(kssj == null || kssj.trim().length() < 1 ? "" : " and zysj >= to_char(to_date('"+kssj.trim()+"','yyyy-mm-dd'),'yyyy-mm-dd')");
		query.append(jssj == null || jssj.trim().length() < 1 ? "" : " and zysj <= to_char(to_date('"+jssj.trim()+"','yyyy-mm-dd'),'yyyy-mm-dd')");
		query.append(zdyy == null || zdyy.trim().length() < 1 ? "" : " and zdyy like '%" + zdyy.trim() + "%'");
		query.append(dwxz == null || dwxz.trim().length() < 1 ? "" : " and dwxz = '" + dwxz.trim() + "')");
		query.append(sfzh == null || sfzh.trim().length() < 1 ? "" : " and exists(select 1 from view_xsjbxx b where a.xh=b.xh and b.sfzh like '%" + DealString.replaceImmitStr(sfzh.trim()) + "%')");
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
			sql = "select rownum r, a.xh,a.zddwmc,a.zddwdz,a.zysj,a.zdyy,"
					+ "(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.nj from view_xsjbxx b where a.xh=b.xh)nj,(select b.bynd from jygl_xsjbxxb b where a.xh = b.xsxh) bynd,"
					+ "(select b.xymc from view_xsjbxx b where a.xh=b.xh)xymc,(select b.zymc from view_xsjbxx b where a.xh=b.xh)zymc,"
					+ "(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,(select b.id from jygl_xsjbxxb b where a.xh = b.xsxh) sfzh"
					+ " from stu_history_lab a " + query.toString();
			title_en = new String[] { "r", "xh", "xm", "sfzh", "nj","bynd", "bjmc",
					"zymc", "xymc", "zddwdz", "zddwmc", "zysj", "zdyy" };
			title_cn = dao.getColumnNameCN(title_en, "stu_history_lab");
		} else {
//			sql = "select rownum r, a.xh,a.zddwmc,a.zddwdz,a.zysj,a.zdyy,a.dwxz,"
//					+ "(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.nj from view_xsjbxx b where a.xh=b.xh)nj,"
//					+ "(select b.xymc from view_xsjbxx b where a.xh=b.xh)xymc,(select b.zymc from view_xsjbxx b where a.xh=b.xh)zymc,"
//					+ "(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,(select b.sfzh from view_xsjbxx b where a.xh=b.xh)sfzh"
//					+ " from stu_history_lab a " + query.toString();
			
			sql = "select rownum r,a.* from (select a.xh,a.zddwmc,a.zddwdz,a.zysj,a.zdyy,a.dwxz,b.xm,b.nj,"
					+ "b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.sfzh "
					+ " from stu_history_lab a,view_xsjbxx b where a.xh = b.xh) a "
					+ query.toString();
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XYSFXY)) {
				title_en = new String[] { "r", "xh", "xm", "sfzh", "nj", "xymc",
						"zymc",  "bjmc", "zddwmc","dwxz", "zysj"};
			}else{
				title_en = new String[] { "r", "xh", "xm", "sfzh", "nj",
						"xymc", "zymc", "bjmc", "zddwmc", "zysj"};
			}
			title_cn = dao.getColumnNameCN(title_en, "stu_history_lab");
		}
		
		
		for (int i = 0; i < title_en.length; i++) {
			HashMap<String, String> temmap = new HashMap<String, String>();
			temmap.put("cn", title_cn[i]);// 将title_en[i]修改为cn
			topTr.add(temmap);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
			HashMap<String, String> temmap = new HashMap<String, String>();
			temmap.put("cn", "毕业年份");// 
			topTr.remove(4);
			topTr.add(4,temmap);
		}
		
		if (go != null&& go.equalsIgnoreCase("go")) {
			StringBuffer sb = new StringBuffer();
			sb.append("select * from(select * from(");
			
			// 高级查询
			SearchService searchService = new SearchService();
			String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
			
			String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
			String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
			
			sql += searchTj;
			sql += searchTjByUser;
			
			sb.append(sql);
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
				String bynf = myForm.getBynf();
				sfzh = myForm.getSfzh();
				String qury = " where 1=1";
				if (bynf != null && !"".equalsIgnoreCase(bynf)) {
					qury += " and bynd='" + bynf.trim() + "'";
				}
				if (sfzh != null && !"".equalsIgnoreCase(sfzh)) {
					qury += " and sfzh='" + sfzh.trim() + "'";
				}
				qury += " and r<=";
				sb.append(") "+qury);
			} else {
				sb.append(") where r<=");
			}
			sb.append(myForm.getPages().getStart()+ myForm.getPages().getPageSize());
			sb.append(") where r>");
			sb.append(myForm.getPages().getStart());
			String querry = sb.toString();

			
			rs = dao.rsToVator(querry, inputV,title_en);
			
			for (int i = 0; i < rs.size(); i++) {
				int str = rs.get(i).length;
				if (rs.get(i)[str - 2].equalsIgnoreCase("1")) {
					rs.get(i)[str - 2] = "在校";
				}
				if (rs.get(i)[str - 2].equalsIgnoreCase("0")) {
					rs.get(i)[str - 2] = "不在校";
				}
			}
			
			
			myForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from (" + sql.toString()+ ")", inputV, "count")));
		
			request.setAttribute("searchTj", myForm.getSearchModel());
		}
		//=========2011.1.26 lr=======
		if(rs == null || rs.size()<1){//无记录时，初始化总页数和总记录数
			myForm.getPages().setMaxPage(0);
			myForm.getPages().setMaxRecord(0);
		}
		//=========end=======
		request.setAttribute("rsNum",rs != null ? rs.size() : 0);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());		
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		//加载单位性质列表
		DwxzDao dwxzDAO = new DwxzDao();
		appendProperties(request, xydm, zydm, nj);//加载页面属性					
		request.setAttribute("maList", dao.getMzList());
		return mapping.findForward("success");
	}

	/**
	 * @author Lirong
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward stuArchivesApply(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		ShgcForm arApply = (ShgcForm) form;
		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String tableName = request.getParameter("tableName");
		String userDep = session.getAttribute("userDep").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		String writeAble = Base.getWriteAble(request);
		writeAble = CheckPower.checkUsrPageAccessPower(userOnLine, userName,"stu_archives_apply.do") ? "yes" : "no";
		
		String sqly = DealString.toGBK(arApply.getSqly());
		String lxfs = DealString.toGBK(arApply.getLxfs());
		String hkssqx = DealString.toGBK(arApply.getHkssqx());
		String hkssjd = DealString.toGBK(arApply.getHkssjd());
		String hkxxdz = DealString.toGBK(arApply.getHkxxdz());
		String zddwmc = DealString.toGBK(arApply.getZddwmc());
		String zddwdz = DealString.toGBK(arApply.getZddwdz());
		String zddwyb = DealString.toGBK(arApply.getZddwyb());

		arApply.setXb("");
		arApply.setLxfs(lxfs);
		arApply.setSqly(sqly);
		arApply.setHkssqx(hkssqx);
		arApply.setHkssjd(hkssjd);
		arApply.setHkxxdz(hkxxdz);
		arApply.setZddwdz(zddwdz);
		arApply.setZddwmc(zddwmc);
		arApply.setZddwyb(zddwyb);
		
		String nd = request.getParameter("nd");
		String xydm = arApply.getXydm();
		String zydm = arApply.getZydm();
		String bjdm = arApply.getBjdm();
		String xh = userOnLine.equalsIgnoreCase("teacher") ? request.getParameter("xh") : session.getAttribute("userName").toString();
		
		String sql = "select xymc,zymc,bjmc,nj,xm,xb,to_char(sysdate,'yyyy-mm-dd') sqrq from view_xsjbxx where xh=?";
		map = dao.getMap(sql, new String[]{xh}, new String[]{"xymc","zymc","bjmc","xm","xb","nj","sqrq"});
		String xymc = map.get("xymc");
		String zymc = map.get("zymc");
		String bjmc = map.get("bjmc");
		String xm = map.get("xm");
		String nj = map.get("nj");
		String sqrq = map.get("sqrq");
		String xb = map.get("xb");
		
		arApply.setXymc(xymc);
		arApply.setZymc(zymc);
		arApply.setBjmc(bjmc);
		arApply.setXm(xm);
		arApply.setXb(xb);
		arApply.setNj(nj);
		arApply.setXb(xb);
		boolean flag = false;
		
		
		request.setAttribute("path", "stu_archives_apply.do");
	    FormModleCommon.commonRequestSet(request);
		//判断是否是历届生
	    String xxdmpd=Base.xxdm+"#";
	    if(!Globals.XXDM_XCXY.equalsIgnoreCase(xxdmpd)){
			if(xh!=null && !xh.equalsIgnoreCase("")){
				StuInfoDAO stuDao = new StuInfoDAO();
				if(stuDao.getGraduateNum(xh)<2 || stuDao.getGraduateNum(xh)>2000){
					request.setAttribute("isLjs", "no");
					request.setAttribute("rs", arApply);
					if(doType != null && !doType.equalsIgnoreCase("auditing")){
					return mapping.findForward("success");
				}
				}
			}
	    }
		
		if (doType != null && doType.equalsIgnoreCase("save")) {
			tableName = "stu_archives_apply";
			sql = "select dqnd from xtszb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {},new String[] { "dqnd" });
			nd = tmp[0];
			String[] values = { xh, sqrq, sqly, lxfs, hkssqx, hkssjd, hkxxdz,
					zddwmc, zddwdz, zddwyb, nd};
			String[] columns = { "xh", "sqrq", "sqly", "lxfs", "hkssqx",
					"hkssjd", "hkxxdz", "zddwmc", "zddwdz", "zddwyb", "nd"};
			flag = StandardOperation.delete(tableName, "xh", xh.trim(), request);
			if (flag) {
				flag = StandardOperation.insert(tableName, columns, values,	request);
			}
			request.setAttribute("result", flag);
		}
		if (doType != null && doType.equalsIgnoreCase("auditing")) {
			writeAble = CheckPower.checkUsrPageAccessPower(userOnLine,userName, "archives_apply_query.do?act=auditing") ? "yes": "no";
			tableName = "stu_archives_apply";
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			xh = request.getParameter("xh");
			nd = request.getParameter("nd");
			if (userType.equalsIgnoreCase("xx")) {
				flag = StandardOperation.update(tableName, new String[] { "xxsh", "bz" }, new String[] { yesNo, bz }, "xh||nd", xh.trim() + nd.trim(), request);
			} else if (userType.equalsIgnoreCase("xy")) {
				flag = StandardOperation.update(tableName, new String[] { "xysh", "bz" }, new String[] { yesNo, bz }, "xh||nd", xh.trim() + nd.trim(), request);
			}
			map.put("yesNo", yesNo);
			map.put("bz", bz);
			
			request.setAttribute("result", flag);
			request.setAttribute("rs", map);
			request.setAttribute("writeAble", writeAble);
			return new ActionForward("/shgc/stu_info/stu_archives_sh.jsp");
		}	
		
		request.setAttribute("xh", xh);
		request.setAttribute("xyList", dao.getXyHistoryList());
		request.setAttribute("zyList", dao.getZyHistoryList(xydm));
		request.setAttribute("bjList", dao.getBjHistoryList(xydm, zydm, nj));
		request.setAttribute("writeAble", writeAble);		
		request.setAttribute("rs", arApply);
		return mapping.findForward("success");
	}

	public static ActionForward ArchivesApplyManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		ShgcForm arApply = (ShgcForm) form;
		HttpSession session = request.getSession();
		Vector<Object> rs = new Vector<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String tableName = "stu_archives_apply";
		String realTable = "stu_archives_apply";
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String writeAble = Base.getWriteAble(request);
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String xxdm = StandardOperation.getXxdm();
		String go = request.getParameter("go");
		request.setAttribute("xxdm", xxdm);

		String xydm = arApply.getXydm();
		String zydm = arApply.getZydm();
		String bjdm = arApply.getBjdm();
		String tips = "";
		String nj = "";
		String xh = arApply.getXh();
		String xm = DealString.toGBK(arApply.getXm());
		String nd = arApply.getNd();
		String act = request.getParameter("act");
		String rsNum = "";
		String sql = "";
		String[] colListCN = new String[] {};
		String[] colList = new String[] {};
		
		 if(userOnLine.equalsIgnoreCase("student")){
			 xh = userName;
			 go = "go";
		 } 
		 if(userType!=null && userType.equalsIgnoreCase("xy")){
				xydm = userDep;
				arApply.setXydm(xydm);
			}
		 
		StringBuffer querry = new StringBuffer("");
		querry.append(" where 1=1");
		querry.append((nj == null || nj.trim().length() < 1) ? "" : " and exists(select 1 from view_xsjbxx b where a.xh=b.xh and b.nj='"+ nj.trim() + "')");
		querry.append(xh == null || xh.trim().length() < 1 ? "" : " and xh like '%" + xh.trim() + "%'");
		querry.append(xm == null || xm.trim().length() < 1 ? "" : " and exists(select 1 from view_xsjbxx b where a.xh=b.xh and  b.xm like '%" + xm.trim() + "%')");
		querry.append((xydm == null || xydm.trim().length() < 1) ? "" : " and exists (select 1 from view_xsjbxx b where a.xh=b.xh and b.xydm='" + xydm + "')");
		querry.append((zydm == null || zydm.trim().length() < 1) ? "" : " and exists (select 1 from view_xsjbxx b where a.xh=b.xh and b.zydm='" + zydm + "')");
		querry.append((bjdm == null || bjdm.trim().length() < 1) ? "" : " and exists (select 1 from view_xsjbxx b where a.xh=b.xh and b.bjdm='" + bjdm + "')");
		querry.append((nd == null || nd.trim().length() < 1) ? "" : " and a.nd='" + nd + "'");		
		
		if (doType != null && doType.equalsIgnoreCase("view")) {// 申请修改			
			xh = request.getParameter("xh").trim();
			nd = request.getParameter("nd").trim();
			sql = "select rownum r, a.xh,a.nd,a.sqrq,a.lxfs,a.hkssqx,a.hkssjd,a.hkxxdz,a.zddwmc,a.zddwdz,a.zddwyb,a.sqly,a.dazcsj,a.xysh,a.xxsh,a.bz," + 
            "(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb," + 
            "(select b.nj from view_xsjbxx b where a.xh=b.xh)nj,(select b.xz from view_xsjbxx b where a.xh=b.xh)xz," + 
            "(select b.xydm from view_xsjbxx b where a.xh=b.xh)xydm,(select b.zydm from view_xsjbxx b where a.xh=b.xh)zydm," + 
            "(select b.bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,(select b.xymc from view_xsjbxx b where a.xh=b.xh)xymc," + 
            "(select b.zymc from view_xsjbxx b where a.xh=b.xh)zymc,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc" + 
            " from stu_archives_apply a where a.xh=? and a.nd=?"; 
			colList = new String[] { "xh", "xm", "xb", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "zddwmc", "zddwdz", "zddwyb", "lxfs", "hkssqx", "hkssjd",
					"hkxxdz", "sqrq", "sqly" };
			map = dao.getMap(sql, new String[] { xh, nd }, colList);
			if (map != null && map.size() > 0) {
				arApply.setXh(map.get("xh"));
				arApply.setXm(map.get("xm"));
				arApply.setXydm(map.get("xydm"));
				arApply.setZydm(map.get("zydm"));
				arApply.setBjdm(map.get("bjdm"));
				arApply.setZddwmc(map.get("zddwmc"));
				arApply.setZddwdz(map.get("zddwdz"));
				arApply.setZddwyb(map.get("zddwyb"));
				arApply.setLxfs(map.get("lxfs"));
				arApply.setHkssqx(map.get("hkssqx"));
				arApply.setHkssjd(map.get("hkssjd"));
				arApply.setHkxxdz(map.get("hkxxdz"));
				arApply.setSqrq(map.get("sqrq"));
				arApply.setSqly(map.get("sqly"));
				arApply.setXymc(map.get("xymc"));
				arApply.setZymc(map.get("zymc"));
				arApply.setBjmc(map.get("bjmc"));
				arApply.setNj(map.get("nj"));
				arApply.setXb(map.get("xb"));
			}
			//判断是否是毕业两年以上的历届生
			if(xh!=null && !xh.equalsIgnoreCase("")){
				StuInfoDAO stuDao = new StuInfoDAO();
				stuDao.getGraduateNum(xh);
//				int s = stuDao.getGraduateNum(xh);
				if(stuDao.getGraduateNum(xh)<2 || stuDao.getGraduateNum(xh)>2000){
					request.setAttribute("isLjs", "no");
				}
			}
			xydm = xydm == null ? "" : xydm;
			zydm = zydm == null ? "" : zydm;
			nj = nj == null ? "" : nj;
			String bjStr = xydm + "!!" + zydm + "!!" + nj;
			request.setAttribute("rs", arApply);
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", Base.getZyMap().get(xydm));
			request.setAttribute("bjList", Base.getBjMap().get(bjStr));
			request.setAttribute("writeAble", writeAble);
			return new ActionForward("/shgc/stu_info/stu_archives_apply.jsp");
		}
		if (doType != null && doType.equalsIgnoreCase("viewAuditing")) {// 审核
			xh = request.getParameter("pkValue").trim();
			//nd = request.getParameter("nd").trim();
			if (userType != null && userType.equalsIgnoreCase("xy")) {
				sql = "select rownum r, a.xh,a.nd,a.sqrq,a.lxfs,a.hkssqx,a.hkssjd,a.hkxxdz,a.zddwmc,a.zddwdz,a.zddwyb,a.sqly,a.dazcsj,a.xysh yesNo,a.bz," + 
	            "(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb," + 
	            "(select b.nj from view_xsjbxx b where a.xh=b.xh)nj,(select b.xz from view_xsjbxx b where a.xh=b.xh)xz," + 
	            "(select b.xydm from view_xsjbxx b where a.xh=b.xh)xydm,(select b.zydm from view_xsjbxx b where a.xh=b.xh)zydm," + 
	            "(select b.bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,(select b.xymc from view_xsjbxx b where a.xh=b.xh)xymc," + 
	            "(select b.zymc from view_xsjbxx b where a.xh=b.xh)zymc,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc" + 
	            " from stu_archives_apply a where a.xh=?";
			} else if (userType != null && userType.equalsIgnoreCase("xx")) {
				sql = "select rownum r, a.xh,a.nd,a.sqrq,a.lxfs,a.hkssqx,a.hkssjd,a.hkxxdz,a.zddwmc,a.zddwdz,a.zddwyb,a.sqly,a.dazcsj,a.xxsh yesNo,a.bz," + 
	            "(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb," + 
	            "(select b.nj from view_xsjbxx b where a.xh=b.xh)nj,(select b.xz from view_xsjbxx b where a.xh=b.xh)xz," + 
	            "(select b.xydm from view_xsjbxx b where a.xh=b.xh)xydm,(select b.zydm from view_xsjbxx b where a.xh=b.xh)zydm," + 
	            "(select b.bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,(select b.xymc from view_xsjbxx b where a.xh=b.xh)xymc," + 
	            "(select b.zymc from view_xsjbxx b where a.xh=b.xh)zymc,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc" + 
	            " from stu_archives_apply a where a.xh=?";
			}
			colList = new String[] { "xh", "xm", "nj", "xymc", "zymc", "bjmc", "nd",
					"sqrq", "sqly", "hkssqx", "hkssjd", "hkxxdz", "zddwdz",
					"zddwmc", "yesNo", "bz" };
			map = dao.getMap(sql, new String[] { xh }, colList);
			request.setAttribute("rs", map);
			request.setAttribute("path", "archives_apply_query.do?act=auditing");
			FormModleCommon.commonRequestSet(request);
			return new ActionForward("/shgc/stu_info/stu_archives_sh.jsp");
		}
		if (doType != null && doType.equalsIgnoreCase("del")) {
			boolean flag = false;
			String pkValue = request.getParameter("pkValue");
			String[] val = pkValue.split("!!SplitOneSplit!!");
			if (val.length == 1) {
				for (int i = 0; i < val.length; i++) {
					flag = StandardOperation.delete(realTable, "xh||nd",
							val[i], request);
				}
			} else {
				for (int i = 1; i < val.length; i++) {
					flag = StandardOperation.delete(realTable, "xh||nd",
							val[i], request);
				}
			}
			arApply.setXh("");
			request.setAttribute("result", flag);
		}
		
		if ("plsh".equals(doType)){
			String shjg = request.getParameter("shjg");
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			String shzd = "xy".equals(userType) ? "xysh" : "xxsh";
			if (null != pkValues && pkValues.length > 0){
				
				StringBuilder shSql = new StringBuilder();
				shSql.append("update stu_archives_apply set ")
				     .append(shzd)
				     .append("='")
				     .append(shjg)
				     .append("' where xh in (");
				
				for (int i = 0 ; i < pkValues.length ; i++){
					shSql.append("'")
						 .append(pkValues[i])
						 .append("'");
					
					if (i==pkValues.length-1){
						shSql.append(")");
					} else {
						shSql.append(",");
					}
				}
				boolean result = dao.runUpdate(shSql.toString(), new String[]{});
				request.setAttribute("message", result ? "操作成功!" : "操作失败!");
			}
			//act="query";
		}
		
		
		
		if ("query".equalsIgnoreCase(act)) {
			tips = "学生信息-转档管理 - 结果查询";
			tableName = "stu_archives_apply";
			realTable = "stu_archives_apply";
			colList = new String[] { "xh", "nd", "xm", "xymc", "bjmc",
					"zddwmc", "zddwdz", "xysh", "xxsh" };

		} else if ("auditing".equalsIgnoreCase(act)) {
			tips = "学生信息-转档管理 - 申请审核";
			tableName = "stu_archives_apply";
			realTable = "stu_archives_auditing";
			sql = "select dqnd from xtszb order by dqnd desc";
			colList = new String[] { "r", "xh", "nd", "xm", "xymc", "zymc",
					"bjmc", "zddwmc", "zddwdz", "xysh", "xxsh" };
			nd = dao.getOneRs(sql, new String[] {}, "dqnd");
			request.setAttribute("nd", nd);
			request.setAttribute("sh", "yes");
		}

		sql = "select rownum r,a.* from (select a.xh,a.nd,a.sqrq,a.lxfs,a.hkssqx,a.hkssjd,a.hkxxdz,a.zddwmc,a.zddwdz,a.zddwyb,a.sqly,a.dazcsj,a.xysh,a.xxsh,a.bz," + 
              "(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb," + 
              "(select b.nj from view_xsjbxx b where a.xh=b.xh)nj,(select b.xz from view_xsjbxx b where a.xh=b.xh)xz," + 
              "(select b.xydm from view_xsjbxx b where a.xh=b.xh)xydm,(select b.zydm from view_xsjbxx b where a.xh=b.xh)zydm," + 
              "(select b.bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,(select b.xymc from view_xsjbxx b where a.xh=b.xh)xymc," + 
              "(select b.zymc from view_xsjbxx b where a.xh=b.xh)zymc,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc" + 
              " from stu_archives_apply a" + querry.toString() +" ) a ";
		if (realTable != null && (realTable.equalsIgnoreCase("stu_archives_auditing"))) {
			//转档审核
			colList = new String[] { "r", "xh", "nd", "xm", "xymc", "zymc", "bjmc", "zddwmc", "zddwdz", "xysh", "xxsh", "bgcolor" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select rownum r,rownum 行号,a.* from (select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.xh,a.nd,a.sqrq,a.lxfs,a.hkssqx,a.hkssjd,a.hkxxdz,a.zddwmc,a.zddwdz,a.zddwyb,a.sqly,a.dazcsj,a.xysh,a.xxsh,a.bz," + 
		              "(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb," + 
		              "(select b.nj from view_xsjbxx b where a.xh=b.xh)nj,(select b.xz from view_xsjbxx b where a.xh=b.xh)xz," + 
		              "(select b.xydm from view_xsjbxx b where a.xh=b.xh)xydm,(select b.zydm from view_xsjbxx b where a.xh=b.xh)zydm," + 
		              "(select b.bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,(select b.xymc from view_xsjbxx b where a.xh=b.xh)xymc," + 
		              "(select b.zymc from view_xsjbxx b where a.xh=b.xh)zymc,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc" + 
		              " from stu_archives_apply a" + querry.toString()+" ) a ";
			} else if (userType.equalsIgnoreCase("xy")) {
				sql = "select rownum r,rownum 行号,a.* from (select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.xh,a.nd,a.sqrq,a.lxfs,a.hkssqx,a.hkssjd,a.hkxxdz,a.zddwmc,a.zddwdz,a.zddwyb,a.sqly,a.dazcsj,a.xysh,a.xxsh,a.bz," + 
	              "(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb," + 
	              "(select b.nj from view_xsjbxx b where a.xh=b.xh)nj,(select b.xz from view_xsjbxx b where a.xh=b.xh)xz," + 
	              "(select b.xydm from view_xsjbxx b where a.xh=b.xh)xydm,(select b.zydm from view_xsjbxx b where a.xh=b.xh)zydm," + 
	              "(select b.bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,(select b.xymc from view_xsjbxx b where a.xh=b.xh)xymc," + 
	              "(select b.zymc from view_xsjbxx b where a.xh=b.xh)zymc,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc" + 
	              " from stu_archives_apply a" + querry.toString()+" ) a ";
			}
		}

		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		
		
		if ((go != null)&& go.equalsIgnoreCase("go")) {
			SearchService searchService = new SearchService();
			
			String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
			String searchTj = SearchService.getSearchTj(arApply.getSearchModel());
			String[] inputV = SearchService.getTjInput(arApply.getSearchModel());
			
			sql+=" where 1 = 1 ";
			sql+=searchTj;
			sql+=searchTjByUser;
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from(select * from(");
			sb.append(sql);
			//sb.append(searchTj);
			sb.append(") where r<=");
			sb.append(arApply.getPages().getStart() + arApply.getPages().getPageSize());
			sb.append(") where r>");
			sb.append(arApply.getPages().getStart());
			rs.addAll(dao.rsToVator(sb.toString(), inputV, colList));
			
			request.setAttribute("searchTj", arApply.getSearchModel());
			arApply.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from("+ sql + ")", inputV, "count")));
		}

		
		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
		String bjStr = xydm + "!!" + zydm +"!!" + nj;
		
		//=========2011.1.26 lr=======
		if(rs == null || rs.size()<1){//无记录时，初始化总页数和总记录数
			arApply.getPages().setMaxPage(0);
			arApply.getPages().setMaxRecord(0);
		}
		//=========end=======
		request.setAttribute("searchModel", arApply.getSearchModel());
		request.setAttribute("nd",nd);
		request.setAttribute("path", "stuArchives.do?method=distributeExaPage");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("pageSize", arApply.getPages().getPageSize());
		request.setAttribute("rs", rs);
		request.setAttribute("act", act);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs == null ? 0 : rs.size());
		request.setAttribute("tips", tips);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("bjList", Base.getBjMap().get(bjStr));
		request.setAttribute("ndList", Base.getXnndList());
		return mapping.findForward("success");
	}

	/**
	 * @author Lirong
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public static ActionForward StuMessageConf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StudentInfoService service = new StudentInfoService();
		XsxxglService xsxxglService = new XsxxglService();
		boolean flag = false;
		StudentInfoForm conf = (StudentInfoForm) form;
		String doType = request.getParameter("doType");
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = Base.getWriteAble(request);
		StringBuffer sb = new StringBuffer();
		String[] colList = null;
		String sql_2 = "";
		String page = service.getReturnPage(xxdm);//获取返回的页面
		
		colList = new String[] { "nd", "xn", "xq", "kssj", "kssjH", "kssjM", 
				"kssjS", "jssj", "jssjH", "jssjM", "jssjS","bzrkssj", 
				"bzrkssjH", "bzrkssjM", "bzrkssjS", "bzrjssj","bzrjssjH", 
				"bzrjssjM", "bzrjssjS","isSz","cjbgdffsj","kxsj","bdsj",
				"bkkssj","bkjssj","xzf","havexsqx","xsxgxxsh", "dzxxqdm",
				"xsxgxxsfsh"};
		sb.append("select nd,xn,xq,substr(kssj,1,10) kssj,substr(kssj,11,2) kssjH,substr(kssj,13,2) kssjM");
		sb.append(",substr(kssj,13,2) kssjS,substr(jssj,1,10) jssj,substr(jssj,11,2) jssjH,substr(jssj,13,2) jssjM,substr(jssj,15,2) jssjS");
		sb.append(",substr(bzrkssj,1,10) bzrkssj,substr(bzrkssj,11,2) bzrkssjH,substr(bzrkssj,13,2) bzrkssjM");
		sb.append(",substr(bzrkssj,13,2) bzrkssjS,substr(bzrjssj,1,10) bzrjssj,substr(bzrjssj,11,2) bzrjssjH,substr(bzrjssj,13,2) bzrjssjM");
		sb.append(",substr(bzrjssj,15,2) bzrjssjS");
		sb.append(",isSz,cjbgdffsj,kxsj,bdsj,bkkssj,bkjssj,xzf,havexsqx,xsxgxxsh,dzxxqdm,xsxgxxsfsh from xsxxxgsjb");
		sql_2 = sb.toString();

		if ("save".equalsIgnoreCase(doType)) {//保存设置信息
			flag = xsxxglService.saveStuConfig(conf);
			request.setAttribute("oper", "save");
			request.setAttribute("result", flag);
		}
		map = dao.getMap(sql_2, new String[] {}, colList);

		request.setAttribute("rs", map);
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("edition", Base.getEdition());
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward(page);
	}
	
	
	
	// 通讯录信息
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public static ActionForward AddressBookInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StudentInfoForm address = (StudentInfoForm) form;
		boolean flag = false;
		HttpSession session = request.getSession();
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String writeAble = Base.getWriteAble(request);

		ArrayList<String[]> rs = new ArrayList<String[]>();
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String forStr = "success";
		String sql = "";
		String tableName = "view_bzrtxl";
		String realTable = "bzrtxlb";
		String pk = "";
		String pkValue = "";

		String xm = DealString.toGBK(address.getXm());
		String xydm = address.getXydm();
		String nj = address.getNj();
		String zydm = address.getZydm();
		String bjdm = address.getBjdm();
		String sjhm = address.getSjhm();
		String dwdh = address.getDwdh();
		String qtdh = address.getDwdh();
		String jtdh = address.getJtdh();
		String email = address.getEmail();
		String bz = DealString.toGBK(address.getBz());
		String xh = "";

		address.setXm(xm);
		address.setBz(bz);

		stu_info_model stu_mod = new stu_info_model();
		stu_info_util stu_util = new stu_info_util();
		if ("teacher".equalsIgnoreCase(userOnline)) {
			if ("admin".equalsIgnoreCase(userType)
					|| "xx".equalsIgnoreCase(userType)) {
				writeAble = "yes";
			} else if ("xy".equalsIgnoreCase(userType)) {
				xydm = userDep;
				address.setXydm(xydm);
				writeAble = "yes";
			}
		} else {
			xh = session.getAttribute("userName").toString();
			stu_mod = stu_util.stu_find_byxh(xh);
			xydm = stu_mod.getXYDM();
			bjdm = stu_mod.getBJDM();
			nj = stu_mod.getNJ();
			address.setXydm(xydm);
			address.setNj(nj);
			address.setBjdm(bjdm);
			writeAble = "no";
		}

		StringBuffer query = new StringBuffer("");
		query.append(xm == null || xm.trim().length() < 1 ? "" : " and xm like '%" + xm.trim() + "%'");
		query.append(bjdm == null || bjdm.trim().length() < 1 ? "" : " and bjdm= '" + bjdm.trim() + "'");
		query.append(nj == null || nj.trim().length() < 1 ? "" : " and nj= '" + nj.trim() + "'");
		query.append(xydm == null || xydm.trim().length() < 1 ? "" : " and xydm= '" + xydm.trim() + "'");
		query.append(zydm == null || zydm.trim().length() < 1 ? "" : " and zydm= '" + zydm.trim() + "'");
		if ("xy".equalsIgnoreCase(userType)) {
			query.append(" and exists (select bjdm from view_njxyzybj b where xydm='" + xydm + "' and a.bjdm=b.bjdm)");
		}
		// 模糊查询
		if (request.getParameter("go") != null
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] title_en = { "r", "xm", "bjmc", "sjhm", "dwdh", "jtdh","qtdh", "email"};
			String[] title_cn = dao.getColumnNameCN(title_en, "view_bzrtxl");
			for (int i = 0; i < title_en.length; i++) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("cn", title_cn[i]);
				topTr.add(temmap);
			}

			StringBuffer sb = new StringBuffer();
			sb.append("select * from (select * from(");
			sb.append("select rownum r, a.* from ");
			sb.append(tableName);
			sb.append(" a where 1=1 ");
			sb.append(query);
			sb.append(") where r<=");
			sb.append(address.getPages().getStart() + address.getPages().getPageSize());
			sb.append(") where r>");
			sb.append(address.getPages().getStart());
			rs = dao.rsToVator(sb.toString(), new String[] {}, title_en);

			if (rs != null) {
				int rsNum = rs.size();
				request.setAttribute("rsNum", rsNum);
			}
		}

		address.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from(select * from " + tableName
								+ " where 1=1 " + query.toString() + ")", new String[] {}, "count")));
		// show modify pages
		if (doType != null && doType.equalsIgnoreCase("view")) {
			forStr = "view";
			String bjmc = DealString.toGBK(request.getParameter("bjdm"));
			bjdm = dao.getOneRs("select bjdm from view_njxyzybj where bjmc=?",new String[] { bjmc }, "bjdm");
			String[] outValue = { "xydm","zydm","bjdm","nj", "xm", "sjhm", "dwdh", "jtdh", "qtdh","email", "bz" };

			sql = "select a.* from " + tableName + " a where bjdm=?";
			map = dao.getMap(sql, new String[] { bjdm }, outValue);
			if (map.size() < 1) {
				request.setAttribute("rs", address);
			} else {
				request.setAttribute("rs", map);
			}
			request.setAttribute("writeAble", writeAble);
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			return mapping.findForward(forStr);
		}
		// 保存信息
		if (doType != null && doType.equalsIgnoreCase("save")) {
			forStr = "view";
			String[] columns = { "bjdm", "xm", "sjhm", "dwdh", "jtdh", "qtdh", "email", "bz" };
			String[] values = { bjdm, xm, sjhm, dwdh, jtdh, qtdh, email,bz };
			sql = "select bjdm from " + realTable + " where bjdm=? and xm=?";
			List list = dao.getList(sql, new String[] { bjdm ,xm}, new String[] { "bjdm" });
			if (list != null && list.size() > 0) {
				flag = StandardOperation.update(realTable, columns, values, "bjdm||xm", bjdm+xm, request);
			} else {
				flag = StandardOperation.insert(realTable, columns, values, request);
			}
			request.setAttribute("rs", address);
			request.setAttribute("result", flag);
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			request.setAttribute("writeAble", writeAble);
			return mapping.findForward(forStr);
		}
		// 删除信息
		if (doType != null && doType.equalsIgnoreCase("del")) {
			String bjmc = DealString.toGBK(request.getParameter("bjdm"));
			xm = DealString.toGBK(request.getParameter("xm"));
			bjdm = dao.getOneRs("select bjdm from view_njxyzybj where bjmc=?", new String[] { bjmc }, "bjdm");
			flag = StandardOperation.delete(realTable, "bjdm||xm", bjdm+xm, request);
			request.setAttribute("result", flag);
		}
		if ("stu".equalsIgnoreCase(userType)) {
			request.setAttribute("writeAble", "no");
		} else {
			request.setAttribute("writeAble", "yes");
		}

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tips", "通讯录 - 班主任通讯录");
		request.setAttribute("realTable", realTable);
		request.setAttribute("pk", pk);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("tableName", tableName);
		request.setAttribute("userType", userType);
		request.setAttribute("act", "act");
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward(forStr);
	}

	/**
	 * @author Lirong
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward stuCgcjInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		boolean flag = false;
		StudentInfoForm applyForm = (StudentInfoForm) form;
		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = (String) session.getAttribute("userType");
		String doType = request.getParameter("doType");
		String userOnLine = (String) session.getAttribute("userOnLine");
		String userName = (String) session.getAttribute("userName");
		String realTable = request.getParameter("realTable");
		String writeAble = CheckPower.checkUsrPageAccessPower(userOnLine,userName, "stu_cgcj.do?act=stu_cgcj_sq") ? "yes" : "no";

		String xh = userOnLine.equalsIgnoreCase("teacher") ? request.getParameter("xh") : session.getAttribute("userName").toString();
		String nd = request.getParameter("nd");
		String sqrq = dao.getOneRs("select substr(to_char(sysdate,'yyy-mm-dd'),0,10) str from dual ",new String[] {}, "str");
		String lxdh = applyForm.getLxdh();
		String zzmm = DealString.toGBK(applyForm.getZzmm());
		String gj = DealString.toGBK(applyForm.getGj());
		String cgyy = DealString.toGBK(applyForm.getCgyy());
		String jtzz = DealString.toGBK(applyForm.getJtzz());
		String jjdbqk = DealString.toGBK(applyForm.getJjdbqk());
		String sql = "";

		sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		if (doType != null && doType.equalsIgnoreCase("view")) {
			xh = xh.trim();
			nd = nd.trim();
			sql = "select * from view_cgsqxx where xh='" + xh + "' and nd='" + nd + "'";
			colList = dao.getColumnName("select * from view_cgsqxx where 1=2");
			rs = dao.getOneRs(sql, new String[] {}, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
			}
		}
		if (doType != null && doType.equalsIgnoreCase("save")) {
			realTable = "xscgsqb";
			nd = request.getParameter("nd");
			sql = "select dqnd from xtszb where rownum=1";
			sqrq = request.getParameter("sqrq");
			if(sqrq ==null || sqrq.equalsIgnoreCase("")){
				sqrq = dao.getOneRs("select substr(to_char(sysdate,'yyyy-mm-dd'),0,10) time from dual",new String[] {}, "time"); 
			}
			if(nd == null || "".equalsIgnoreCase(nd)){
				String[] tmp = dao.getOneRs(sql, new String[] {},new String[] { "dqnd" });
				nd = tmp[0];
			}
			String[] values = { xh, lxdh, zzmm, gj, cgyy, jtzz, jjdbqk, sqrq,nd };
			String[] columns = { "xh", "lxdh", "zzmm", "gj", "cgyy", "jtzz","jjdbqk", "sqrq", "nd" };
			flag = StandardOperation.delete(realTable, "xh||nd", xh.trim()+sqrq.trim(), request);
			if (flag) {
				flag = StandardOperation.insert(realTable, columns, values,request);
			}
			request.setAttribute("result", flag);
		}
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map);
		request.setAttribute("userType", userType);
		request.setAttribute("zzmmList", dao.getZzmmList());
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("success");
	}

	/**
	 * @author Lirong
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward stuTxInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StudentInfoForm txForm = (StudentInfoForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		HashMap<String, String> map = new HashMap<String, String>();
		boolean flag = false;

		String writeAble = CheckPower.checkUsrPageAccessPower(userOnLine,userName, "stu_cgcj.do?act=stu_tx_sq") ? "yes" : "no";
		String xh = userOnLine.equalsIgnoreCase("teacher") ? request.getParameter("xh") : session.getAttribute("userName").toString();
		String nd = request.getParameter("nd");
		String tableName = "";
		String sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");

		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		if (doType != null && doType.equalsIgnoreCase("view")) {
			xh = xh.trim();
			nd = nd.trim();
			sql = "select * from view_txsqxx where xh='" + xh + "' and nd='"+ nd + "'";
			colList = dao.getColumnName("select * from view_txsqxx where 1=2");
			rs = dao.getOneRs(sql, new String[] {}, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
			}
		}
		if (doType != null && doType.equalsIgnoreCase("save")) {
			String sqrq = request.getParameter("sqrq");
			if(sqrq == null || "".equalsIgnoreCase("sqrq")){
				sqrq = dao.getOneRs("select substr(to_char(sysdate,'yyyy-mm-dd'),0,10) time from dual",new String[] {}, "time");
			}
			String sqly = DealString.toGBK(txForm.getSqly());
			String sjhm = txForm.getSjhm();
			String qtdh = txForm.getQtdh();
			tableName = "stu_txsqb";

			sql = "select dqnd from xtszb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] { "dqnd" });
			nd = tmp[0];
			String[] values = { xh, sqrq, sqly, sjhm, qtdh, nd };
			String[] columns = { "xh", "sqrq", "sqly", "sjhm", "qtdh", "nd" };
			flag = StandardOperation.delete(tableName, "xh||nd", xh.trim() + nd.trim(), request);
			if (flag) {
				flag = StandardOperation.insert(tableName, columns, values,request);
			}
			request.setAttribute("result", flag);
		}
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map);
		request.setAttribute("userType", userType);
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("txsq");
	}

	/**
	 * @author Lirong
	 * @return
	 * @throws Exception
	 */

	public static ActionForward ManagerInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentInfoForm stuForm = (StudentInfoForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;

		String tableName = request.getParameter("tableName");
		String doType = request.getParameter("doType");
		String realTable = request.getParameter("realTable");
		String writeAble = CheckPower.checkUsrPower(session.getAttribute(
				"userName").toString(), "abroad_query.do?act=cgsq_query") ? "yes"
				: "no";
		String dataType = request.getParameter("act");
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);

		String sql = "";
		String tips = "";
		String rsNum = "0";
		String pk = "";

		String nj = stuForm.getNj();
		String xh = stuForm.getXh();
		String xm = DealString.toGBK(stuForm.getXm());
		String xydm = stuForm.getXydm();
		String zydm = stuForm.getZydm();
		String bjdm = stuForm.getBjdm();
		String nd = request.getParameter("nd");

		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.equalsIgnoreCase(""))) {
			xydm = userDep;
			stuForm.setXydm(xydm);
		}

		StringBuffer querry = new StringBuffer("");
		querry.append(" where 1=1");
		querry.append((nj == null || nj.trim().length() < 1) ? "" : " and nj='"
				+ nj.trim() + "'");
		querry.append(xh == null || xh.trim().length() < 1 ? ""
				: " and xh like '%" + xh.trim() + "%'");
		querry.append(xm == null || xm.trim().length() < 1 ? ""
				: " and xm like '%" + xm.trim() + "%'");
		querry.append((xydm == null || xydm.trim().length() < 1) ? ""
				: " and xydm='" + xydm + "'");
		querry.append((zydm == null || zydm.trim().length() < 1) ? ""
				: " and zydm='" + zydm + "'");
		querry.append((bjdm == null || bjdm.trim().length() < 1) ? ""
				: " and bjdm='" + bjdm + "'");
		querry.append((nd == null || nd.trim().length() < 1) ? "" : " and nd='"
				+ nd + "'");

		if (dataType.equalsIgnoreCase("cgsq_query")) {// 出国申请查询
			realTable = "xscgsqb";
			pk = "xh-sqrq";
			tips = "因私出国管理 - 出国申请查询 ";
			tableName = "view_cgsqxx";
			colList = new String[] { "r", "nd", "xh", "xm", "xymc", "zymc",
					"bjmc", "nj", "xxsh", "xysh", "sqrq" };
			querry.append(" order by sqrq desc");
		}
		if (dataType.equalsIgnoreCase("txsq_query")) {// 退学申请查询
			realTable = "stu_txsqb";
			pk = "xh-sqrq";
			tips = "学生退学申请 - 退学申请查询 ";
			tableName = "view_txsqxx";
			colList = new String[] { "r", "nd", "xh", "xm", "xymc", "zymc",
					"bjmc", "nj", "xxsh", "xysh", "sqrq" };
			querry.append(" order by sqrq desc");
		}
		if (dataType.equalsIgnoreCase("cgsq_sh")) {// 出国申请审核
			realTable = "xscgsqsh";
			pk = "xh-nd";
			tips = "因私出国管理-因私出国审核";
			tableName = "view_cgsqxx";
			writeAble = CheckPower.checkUsrPower(session.getAttribute(
			"userName").toString(), "abroad_query.do?act=cgsq_sh") ? "yes"
			: "no";
			
			//userType = "xy";
			colList = new String[] { "r", "nd", "xh", "xm", "xymc", "zymc",
					"bjmc", "nj", "xxsh", "xysh", "sqrq" };
			if (userType.equalsIgnoreCase("xx")) {
				querry.append(" and xysh='通过'");
			}
			querry.append(" order by nd desc");

			if (doType != null && doType.equalsIgnoreCase("save")) {
				boolean result = false;
				String yesNo = DealString.toGBK(request.getParameter("yesNo"));
				realTable = "xscgsqb";
				String xxyj = DealString.toGBK(stuForm.getXxyj());
				String xyyj = DealString.toGBK(stuForm.getXyyj());
				String xscyj = DealString.toGBK(stuForm.getXscyj());
				xh = request.getParameter("xh");
				nd = request.getParameter("nd");
				if (userType.equalsIgnoreCase("xy")) {
					result = StandardOperation.update(realTable, new String[] {
							"xysh", "xxyj", "xyyj", "xscyj" }, new String[] {
							yesNo, xxyj, xyyj, xscyj }, "xh||nd", xh.trim()
							+ nd.trim(), request);
				} else if (userType.equalsIgnoreCase("xx")) {
					result = StandardOperation.update(realTable, new String[] {
							"xxsh", "xxyj", "xyyj", "xscyj" }, new String[] {
							yesNo, xxyj, xyyj, xscyj }, "xh||nd", xh.trim()
							+ nd.trim(), request);
				}
				request.setAttribute("rs", new StudentInfoForm());
				request.setAttribute("result", result);
				request.setAttribute("writeAble", writeAble);
				return new ActionForward("/shgc/stu_info/cgsqshb.jsp");
			}
			if (doType != null && doType.equalsIgnoreCase("view")) {
				xh = request.getParameter("xh").trim();
				nd = request.getParameter("nd").trim();
				HashMap<String, String> map = new HashMap<String, String>();
				sql = "select * from view_cgsqxx where xh='" + xh
						+ "' and nd='" + nd + "'";
				colList = dao
						.getColumnName("select * from view_cgsqxx where 1=2");
				String[] rs_cg = dao.getOneRs(sql, new String[] {}, colList);
				if (rs_cg != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), rs_cg[i]);
					}

				}
				request.setAttribute("rs", map);
				request.setAttribute("userType", userType);
				request.setAttribute("writeAble", writeAble);
				return new ActionForward("/shgc/stu_info/cgsqshb.jsp");
			}
			request.setAttribute("sh", "yes");
		}
		if (dataType.equalsIgnoreCase("txsq_sh")) {// 退学申请审核
			writeAble = CheckPower.checkUsrPower(session.getAttribute(
			"userName").toString(), "abroad_query.do?act=txsq_sh") ? "yes"
			: "no";
			realTable = "stu_txsqsh";
			pk = "xh-nd";
			tips = "学生退学管理-退学申请审核";
			tableName = "view_txsqxx";
			colList = new String[] { "r", "nd", "xh", "xm", "xymc", "zymc",
					"bjmc", "nj", "xxsh", "xysh", "sqrq" };
			if (userType.equalsIgnoreCase("xx")) {
				querry.append(" and xysh='通过'");
			}
			querry.append(" order by sqrq desc");
			if (doType != null && doType.equalsIgnoreCase("save")) {
				boolean result = false;
				String yesNo = DealString.toGBK(request.getParameter("yesNo"));
				String xxshyj = DealString.toGBK(stuForm.getXxyj());
				String jbbmyj = DealString.toGBK(stuForm.getJbbmyj());
				String jzyj = DealString.toGBK(stuForm.getXscyj());
				xh = request.getParameter("xh");
				nd = request.getParameter("nd");
				if (userType.equalsIgnoreCase("xy")) {
					sql = "update stu_txsqb set xysh=?,jbbmyj=?,jzyj=? where xh=? and nd=?";
					result = dao.runUpdate(sql, new String[] { yesNo, jbbmyj, jzyj, xh, nd });
				} else if (userType.equalsIgnoreCase("xx")) {
					sql = "update stu_txsqb set xxsh=?,xxshyj=?,jzyj=?where xh=? and nd=?";
					result = dao.runUpdate(sql, new String[] { yesNo, xxshyj, jzyj, xh, nd });
				}
				
				request.setAttribute("rs", new StudentInfoForm());
				request.setAttribute("result", result);
				request.setAttribute("writeAble", writeAble);
				return new ActionForward("/shgc/stu_info/txsqshb.jsp");
			}
			if (doType != null && doType.equalsIgnoreCase("view")) {
				xh = request.getParameter("xh").trim();
				nd = request.getParameter("nd").trim();
				HashMap<String, String> map = new HashMap<String, String>();
				sql = "select * from view_txsqxx where xh='" + xh
						+ "' and nd='" + nd + "'";
				colList = dao.getColumnName("select * from view_txsqxx where 1=2");
				String[] rs_cg = dao.getOneRs(sql, new String[] {}, colList);
				if (rs_cg != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), rs_cg[i]);
					}

				}
				map.put("xxyj", map.get("xxshyj"));
				map.put("xyyj", map.get("jbbmyj"));
				request.setAttribute("userType", userType);
				request.setAttribute("rs", map);
				request.setAttribute("writeAble", writeAble);
				return new ActionForward("/shgc/stu_info/txsqshb.jsp");
			}
			request.setAttribute("sh", "yes");
		}
		sql = " select rownum r,a.* from " + tableName + " a"
				+ querry.toString();
		if (realTable != null
				&& (realTable.equalsIgnoreCase("xscgsqsh") || realTable
						.equalsIgnoreCase("stu_txsqsh"))) {
			colList = new String[] { "r", "nd", "xh", "xm", "xymc", "zymc",
					"bjmc", "nj", "xxsh", "xysh", "sqrq", "bgcolor" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select rownum r,rownum 行号,(case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from "
						+ tableName + " a " + querry.toString();
			} else if (userType.equalsIgnoreCase("xy")) {
				sql = "select rownum r,rownum 行号,(case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from "
						+ tableName + " a " + querry.toString();
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			StringBuffer sb = new StringBuffer();
			sb.append("select * from (select * from(");
			sb.append(sql);
			sb.append(") where r<=");
			sb.append(stuForm.getPages().getStart()
					+ stuForm.getPages().getPageSize());
			sb.append(") where r>");
			sb.append(stuForm.getPages().getStart());
			rs.addAll(dao.rsToVator(sb.toString(), new String[] {}, colList));
		}
		stuForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs("select count(*) count from("
						+ sql + ")", new String[] {}, "count")));
		if (doType != null && doType.equalsIgnoreCase("del")) {
			nd = request.getParameter("nd");
			StandardOperation.delete(realTable, "xh||nd",
					xh.trim() + nd.trim(), request);
			;
			xh = "";
		}

		xydm = (xydm == null ? "" : xydm);
		zydm = (zydm == null ? "" : zydm);
		nj = (nj == null ? "" : nj);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;

		//=========2011.1.26 lr=======
		if(rs == null || rs.size()<1){//无记录时，初始化总页数和总记录数
			stuForm.getPages().setMaxPage(0);
			stuForm.getPages().setMaxRecord(0);
		}
		//=========end=======
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs == null ? 0 : rs.size());// 发送记录数
		request.setAttribute("pageSize", stuForm.getPages().getPageSize());
		request.setAttribute("userType", userType);
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("nd", dao.getOneRs(
				"select dqnd from xtszb order by dqnd desc", new String[] {},
				"dqnd"));
		return mapping.findForward("success");
	}

	// 出国学生在读证明申请
	public static ActionForward AttendSchoolApply(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentInfoForm stuForm = (StudentInfoForm) form;
		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		String nd = Base.currNd;
		String doType = request.getParameter("doType");
		String xxdm = StandardOperation.getXxdm();
		String sh = request.getParameter("sh");
		sh = "no".equalsIgnoreCase(sh) ? "no" : "yes";
		
		String writeAble = CheckPower.checkUsrPageAccessPower(userOnLine, userName, "attend_school_prove.do") ? "yes" : "no";
		String xh = userOnLine.equalsIgnoreCase("teacher") ? request .getParameter("xh") : session.getAttribute("userName").toString();
		String sql = "select * from view_xsjbxx where xh=?";

		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		
		if (doType != null && doType.equalsIgnoreCase("save")) {
			boolean flag = false;
			int size = 0;
			nd = Base.currNd;
			xh = stuForm.getXh().trim();
			sql = "select substr(to_char(sysdate,'yyyy-mm-dd'),0,10) time from dual";
			
			String lxfs = DealString.toGBK(stuForm.getLxfs());
			String blfs = DealString.toGBK(stuForm.getBlfs());
			String sqly = DealString.toGBK(stuForm.getSqly());
			String sqrq = dao.getOneRs(sql, new String[] {}, "time");
			String zmlx = DealString.toGBK(stuForm.getZmlx());			
			
			sql = "select count(*) num from stu_zdzmsq where xh=? and nd=?";
			size = Integer.parseInt(dao.getOneRs(sql,new String[] { xh, nd }, "num"));
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				sql = "select count(*) num from stu_zdzmsq where xh=? and nd=? and zmlx=?";
				size = Integer.parseInt(dao.getOneRs(sql,new String[] { xh, nd,zmlx }, "num"));
			}
			if (size > 0) {//记录已经存在
				sql = "select count(*) num from stu_zdzmsq where xh=? and nd=? and (xxsh='通过' or xysh='通过') and zmlx=?";
				size = Integer.parseInt(dao.getOneRs(sql,new String[] { xh, nd ,zmlx}, "num"));
				if(size>0){
					request.setAttribute("msg", "本年度已经有申请的项目通过审核，不能再修改！");
				}else{
					sql = "select count(*) num from stu_zdzmsq where xh=? and nd=? and zmlx=?";
					size = Integer.parseInt(dao.getOneRs(sql,new String[] { xh, nd ,zmlx}, "num"));
					if(size>0){
						if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
							if ("no".equalsIgnoreCase(sh)) {
								colList = new String[] { xh, nd, lxfs, blfs,
										sqly, sqrq, "通过", "通过", zmlx };
								flag = StandardOperation.update("stu_zdzmsq",
										new String[] { "xh", "nd", "lxfs",
												"blfs", "sqyy", "sqrq", "xxsh",
												"xysh", "zmlx" }, colList,
										"xh||nd||zmlx", xh.trim() + nd.trim()
												+ zmlx, request);
							} else {
								colList = new String[] { xh, nd, lxfs, blfs, sqly,
										sqrq, "未审核", "未审核", zmlx };
								flag = StandardOperation.update("stu_zdzmsq",
										new String[] { "xh", "nd", "lxfs", "blfs",
												"sqyy", "sqrq", "xxsh", "xysh",
												"zmlx" }, colList, "xh||nd||zmlx",
										xh.trim() + nd.trim() + zmlx, request);
							}
						} else {
							colList = new String[] { xh, nd, lxfs, blfs, sqly,
									sqrq, "未审核", "未审核", zmlx };
							flag = StandardOperation.update("stu_zdzmsq",
									new String[] { "xh", "nd", "lxfs", "blfs",
											"sqyy", "sqrq", "xxsh", "xysh",
											"zmlx" }, colList, "xh||nd||zmlx",
									xh.trim() + nd.trim() + zmlx, request);
						}
					}else{
						colList = new String[] { xh, nd, lxfs, blfs, sqly, sqrq, zmlx };
						flag = StandardOperation.insert("stu_zdzmsq", new String[] {
								"xh", "nd", "lxfs", "blfs", "sqyy", "sqrq", "zmlx" }, colList, request);
					}
					
				}
						
			} else {// 记录不存在
				if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
					if ("no".equalsIgnoreCase(sh)) {
						colList = new String[] { xh, nd, lxfs, blfs, sqly,
								sqrq, zmlx, "通过", "通过" };
						flag = StandardOperation
								.insert("stu_zdzmsq", new String[] { "xh",
										"nd", "lxfs", "blfs", "sqyy", "sqrq",
										"zmlx", "xxsh", "xysh" }, colList,
										request);
					} else {
						colList = new String[] { xh, nd, lxfs, blfs, sqly,
								sqrq, zmlx };
						flag = StandardOperation.insert("stu_zdzmsq",
								new String[] { "xh", "nd", "lxfs", "blfs",
										"sqyy", "sqrq", "zmlx" }, colList,
								request);
					}
				} else {
					colList = new String[] { xh, nd, lxfs, blfs, sqly, sqrq,
							zmlx };
					flag = StandardOperation.insert("stu_zdzmsq",
							new String[] { "xh", "nd", "lxfs", "blfs", "sqyy",
									"sqrq", "zmlx" }, colList, request);
				}
			}
			request.setAttribute("result", flag);
			sql = "select * from stu_zdzmsq where xh||nd||zmlx=?";
			map.putAll(dao.getMap(sql, new String[]{xh+nd+zmlx}, new String[]{"xh","nd","lxfs","blfs","gsyq","sqyy","sqrq","cgrq","ggrq","xxsh","xysh","xxshyj","xyshyj","sfcg","zmlx"}));
			map.put("sqly", map.get("sqyy"));
		}		
		request.setAttribute("sh", sh);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("rs", map);
		request.setAttribute("doType", request.getParameter("doType"));
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("typeList",dao.getTypeList(1));//在读证明类型
		return mapping.findForward("success");
	}

	// 出国学生在读证明申请查询，审核
	public static ActionForward ProveManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentInfoForm stuForm = (StudentInfoForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xxdm = StandardOperation.getXxdm();
		String needSh = request.getParameter("needSh");
		needSh = "no".equalsIgnoreCase(needSh) ? "no" : "yes";
		request.setAttribute("needSh", needSh);
		String writeAble = CheckPower.checkUsrPageAccessPower(userOnLine, userName, "prove_query.do?doType=auditing") ? "yes" : "no";

		String tableName = "view_zdzmsq";
		String doType = request.getParameter("doType");
		String sql = "";
		String realTable = "stu_zdzmsq";
		String tips = "";
		String rsNum = "";

		String[] colListCN = null;
		String[] colList = null;

		String nj = stuForm.getNj();
		String xh = DealString.toGBK(stuForm.getXh());
		String xm = DealString.toGBK(stuForm.getXm());
		String xydm = stuForm.getXydm();
		String zydm = stuForm.getZydm();
		String bjdm = stuForm.getBjdm();
		String zmlx = DealString.toGBK(stuForm.getZmlx());		
		String nd = request.getParameter("nd");
		
		stuForm.setXm(xm);
		stuForm.setZmlx(zmlx);
		stuForm.setXh(xh);
		
		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.equalsIgnoreCase(""))) {
			xydm = userDep;
			stuForm.setXydm(xydm);
		}
		StringBuffer querry = new StringBuffer("");
		querry.append(" where 1=1");
		querry.append((nj == null || nj.trim().length() < 1) ? "" : " and nj='" + nj.trim() + "'");
		querry.append(xh == null || xh.trim().length() < 1 ? "" : " and xh like '%" + xh.trim() + "%'");
		querry.append(xm == null || xm.trim().length() < 1 ? "" : " and xm like '%" + xm.trim() + "%'");
		querry.append((xydm == null || xydm.trim().length() < 1) ? "" : " and xydm='" + xydm + "'");
		querry.append((zydm == null || zydm.trim().length() < 1) ? "" : " and zydm='" + zydm + "'");
		querry.append((bjdm == null || bjdm.trim().length() < 1) ? "" : " and bjdm='" + bjdm + "'");
		querry.append((nd == null || nd.trim().length() < 1) ? "" : " and nd='" + nd + "'");
		querry.append((zmlx == null || zmlx.trim().length() < 1) ? "" : " and zmlx='" + zmlx + "'");

		sql = "select nd,xh,xm,xymc,bjmc,sqrq,xxsh,xysh from " + tableName + " where xh=? and xh||nd=?";
		rs = dao.rsToVator(sql, new String[] { xh, xh + nd }, new String[] {"nd", "xh", "xm", "xymc", "bjmc", "sqrq", "xxsh", "xysh" });
		rsNum = Integer.toString(map.size());

		tips = "学生信息 - 在读证明 - 申请查询";
		colList = new String[] { "r", "xh", "nd", "xm", "bjmc", "xymc", "sqrq", "blfs", "zmlx", "xysh", "xxsh" };
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			colList = new String[] { "r", "xh", "nd", "xm", "bjmc", "xymc", 
					"sqrq","blfs", "zmlx", "xxsh"};
		}
		if (doType != null && doType.equalsIgnoreCase("auditing")) {// 审核
			String type = request.getParameter("type");
			tips = "学生信息 - 在读证明 - 申请审核";
			realTable = "stu_zdzmsqsh";
			request.setAttribute("sh", "yes");
			if (type != null && type.equalsIgnoreCase("view")) {
				xh = request.getParameter("xh");
				colList = new String[] { "r", "xh", "xm", "xymc", "zymc",
						"bjmc", "xb", "blfs", "sqrq", "sqyy", "nd", "nj",
						"xxsh", "xysh", "xxshyj", "xyshyj", "zmlx" };
				StringBuffer sb = new StringBuffer();
				sb.append("select rownum r, xh,xm,xymc,zymc,bjmc,xb,blfs,sqrq,sqyy,zmlx,nd,nj,xxsh,xysh,xxshyj,xyshyj, zmlx from ");
				sb.append(tableName);
				sb.append(" where xh||nd=?");
				sql = sb.toString();
				
				map = dao.getMap(sql, new String[] { xh.trim() + nd.trim() }, colList);
				map.put("sqly", map.get("sqyy"));
				map.put("xxyj", map.get("xxshyj"));
				map.put("xyyj", map.get("xyshyj"));

				request.setAttribute("rs", map);
				request.setAttribute("userType", userType);
				request.setAttribute("writeAble", writeAble);
				request.setAttribute("typeList", dao.getTypeList(1));//在读证明类型列表
				return mapping.findForward("auditingOne");
			}
			if (type != null && type.equalsIgnoreCase("save")) {
				boolean flag = false;
				xh = request.getParameter("xh");
				zmlx= DealString.toGBK(request.getParameter("zmlx"));
				String yesNo = DealString.toGBK(request.getParameter("yesNo"));
				String xxyj = DealString.toGBK(request.getParameter("xxyj"));
				String xyyj = DealString.toGBK(request.getParameter("xyyj"));
				if (userType.equalsIgnoreCase("xx")) {
					flag = StandardOperation.update("stu_zdzmsq", new String[] {
							"xxsh", "xxshyj" }, new String[] { yesNo, xxyj },
							"xh||nd||zmlx", xh.trim() + nd.trim()+zmlx.trim(), request);
				} else if (userType.equalsIgnoreCase("xy") && !xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
					flag = StandardOperation.update("stu_zdzmsq", new String[] {
							"xysh", "xyshyj" }, new String[] { yesNo, xyyj },
							"xh||nd||zmlx", xh.trim() + nd.trim()+zmlx.trim(), request);
				}
				request.setAttribute("rs", new StudentInfoForm());
				request.setAttribute("result", flag);
				request.setAttribute("writeAble", writeAble);
				request.setAttribute("typeList", dao.getTypeList(1));//在读证明类型列表
				return mapping.findForward("auditingOne");
			}

		}

		if (doType != null && doType.equalsIgnoreCase("view")) {// 修改申请信息
			xh = request.getParameter("xh");
			nd = request.getParameter("nd");
			colList = new String[] { "xh", "xm", "xb", "zymc", "bjmc", "xymc", "lxfs", "sqyy", "sqrq", "blfs", "zmlx" };
			
			sql = "select * from " + tableName + " where xh=? and xh||nd=?";
			map = dao.getMap(sql, new String[] { xh.trim(), xh.trim() + nd.trim() }, colList);
			map.put("sqly", map.get("sqyy"));
			
			request.setAttribute("sh", "no");
			request.setAttribute("doType", doType);
			request.setAttribute("rs", map);
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("typeList", dao.getTypeList(1));//在读证明类型列表
			return mapping.findForward("view");//

		}

		if (doType != null && doType.equalsIgnoreCase("del")) {// 删除申请信息
			boolean flag = false;
			xh = request.getParameter("xh");
			nd = request.getParameter("nd");
			zmlx = DealString.toGBK(request.getParameter("zmlx"));
			String pkStr = xh.trim() + nd.trim()+zmlx.trim();
			flag = StandardOperation.delete(realTable, "xh||nd||zmlx", pkStr, request);
			request.setAttribute("result", flag);
			stuForm.setXh("");
		}

		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		sql = "select rownum r,a.* from " + tableName + " a "
				+ querry.toString();
		if (doType != null && doType.equalsIgnoreCase("auditing")) {
			colList = new String[] { "bgcolor","xh", "nd", "xm", "bjmc", "xymc", "sqrq","blfs", "zmlx", "xysh", "xxsh" };
			StringBuffer sb = new StringBuffer();
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
				//浙江机电职业技术学院
				sb.append("select rownum r, rownum 行号,(case when nvl(a.xxsh,'未审核')='通过' and nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from ");
				sb.append(tableName);
				sb.append(" a ");
				sb.append(querry);
				sql = sb.toString();// and xysh='通过'
				
				colList = new String[] {"bgcolor","xh", "nd", "xm", "bjmc", "xymc", "sqyy", "sqrq", "xxsh" };
				colListCN = dao.getColumnNameCN(colList, tableName);
				//topTr = dao.arrayToList(colList, colListCN);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				//上海工程
				if (userType.equalsIgnoreCase("xx")) {
					sb.append("select rownum r, rownum 行号,(case when nvl(a.xxsh,'未审核')='通过' and nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from ");
					sb.append(tableName);
					sb.append(" a ");
					sb.append(querry);
					sql = sb.toString();// and xysh='通过'
				} else if (userType.equalsIgnoreCase("xy")) {
					sb.append("select rownum r, rownum 行号,(case when nvl(a.xysh,'未审核')='通过' and nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from ");
					sb.append(tableName);
					sb.append(" a ");
					sb.append(querry);
					sql = sb.toString();
				}
			}else{
				if (userType.equalsIgnoreCase("xx")) {
					sb.append("select rownum r, rownum 行号,(case when nvl(a.xxsh,'未审核')='通过' and nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from ");
					sb.append(tableName);
					sb.append(" a ");
					sb.append(querry);
					sb.append("and xysh='通过'");
					sql = sb.toString();// and xysh='通过'
				} else if (userType.equalsIgnoreCase("xy")) {
					sb.append("select rownum r, rownum 行号,(case when nvl(a.xysh,'未审核')='通过' and nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from ");
					sb.append(tableName);
					sb.append(" a ");
					sb.append(querry);
					sql = sb.toString();
				}
			}
			topTr = dao.arrayToList(colList, colListCN);
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			StringBuffer sb = new StringBuffer();
			sb.append("select * from (select * from(");
			sb.append(sql);
			sb.append(") where r<=");
			sb.append(stuForm.getPages().getStart()
					+ stuForm.getPages().getPageSize());
			sb.append(") where r>");
			sb.append(stuForm.getPages().getStart());

			rs=dao.rsToVator(sb.toString(), new String[] {}, colList);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		stuForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from (" + sql + ")", new String[] {}, "count")));

		xydm = (xydm == null ? "" : xydm);
		zydm = (zydm == null ? "" : zydm);
		nj = (nj == null ? "" : nj);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;

		request.setAttribute("xxdm", xxdm);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
		request.setAttribute("rs", rs);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("typeList", dao.getTypeList(1));//在读证明类型列表
		request.setAttribute("userType", userType);
		request.setAttribute("nd", dao.getOneRs("select dqnd from xtszb order by dqnd desc", new String[] {},"dqnd"));
		return mapping.findForward("success");
	}

	// 出国学生在读证明打印
	public static ActionForward CertificatePrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentInfoForm stuForm = (StudentInfoForm) form;
		DAO dao = DAO.getInstance();
		Vector<String[]> rs = new Vector<String[]>();
		String writeAble = Base.getWriteAble(request);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xxdm = StandardOperation.getXxdm();
		String nj = stuForm.getNj();

		String xydm = stuForm.getXydm();
		String zydm = stuForm.getZydm();
		String bjdm = stuForm.getBjdm();
		String zmlx = DealString.toGBK(stuForm.getZmlx());
		String xh = DealString.toGBK(stuForm.getXh());
		
		stuForm.setZmlx(zmlx);
		stuForm.setXh(xh);

		String rsNum = "";
		String tips = "";
		tips = "学生信息 - 在读证明 - 证明打印";
		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.equalsIgnoreCase(""))) {
			xydm = userDep;
			stuForm.setXydm(xydm);
		}
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		query.append(xydm == null || xydm.length() < 1 ? "" : " and xydm='" + xydm + "' ");
		query.append(zydm == null || zydm.length() < 1 ? "" : " and zydm='" + zydm + "' ");
		query.append(bjdm == null || bjdm.length() < 1 ? "" : " and bjdm='" + bjdm + "' ");
		query.append(zmlx == null || zmlx.length() < 1 ? "" : " and zmlx='" + zmlx + "' ");
		query.append(xh == null || xh.length() < 1 ? "" : " and xh like '%" + xh + "%' ");
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//浙江机电
			query.append(" and xxsh='通过' ");
		}else if(!xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			query.append(" and xysh='通过' and xxsh='通过' ");
		}

		String sql = "select * from view_zdzmsq " + query.toString();
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "zmlx", "sqrq" };
		String[] colListCN = dao.getColumnNameCN(colList, "view_zdzmsq");
		List topTr = dao.arrayToList(colList, colListCN);
		if (request.getParameter("go") != null
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs != null && rs.size() > 0) {
				rsNum = String.valueOf(rs.size());
			}
		}

		xydm = (xydm == null ? "" : xydm);
		zydm = (zydm == null ? "" : zydm);
		nj = (nj == null ? "" : nj);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;

		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
		request.setAttribute("tips", tips);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("typeList", dao.getTypeList(1));//在读证明类型列表
		return mapping.findForward("success");
	}

	// 东北林业大学学生信息报表打印
	public static ActionForward StuinfoPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		String writeAble = Base.getWriteAble(request);
		String xh = request.getParameter("xh");
		xh = (xh==null || "".equalsIgnoreCase(xh) ? "" : xh.trim());
		String qslh = "";
		String qssh = "";
		String sql = "select * from view_xsxxb  where xh=?";
		String sql2 = "select * from view_xsjtxx where xh=?";

		String[] colList = dao
				.getColumnName("select * from view_xsxxb where 1=2");
		String[] colList2 = dao
				.getColumnName("select * from  view_xsjtxx where 1=2");
		map = dao.getMap(sql, new String[] { xh }, colList);
		// 寝室信息
		if (map.get("ssbh") != null && !map.get("ssbh").equalsIgnoreCase("")) {
			qslh = map.get("ssbh").substring(0, map.get("ssbh").indexOf("-"));
			qssh = map.get("ssbh").substring(map.get("ssbh").indexOf("-"),
					map.get("ssbh").length());
			map.put("qslh", qslh);
			map.put("qssh", qssh);
			rs.add(map);
		}
		map2 = dao.getMap(sql2, new String[] { xh }, colList2);
		map.putAll(map2);
		request.setAttribute("rs", map);
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("success");
	}

	public static ActionForward stuSchoolingInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		CommanForm schooling = (CommanForm) form;
		List<String[]> vector = new ArrayList<String[]>();
		String writeAble = Base.getWriteAble(request);
		String tableName = "view_xsxx_xsxfxx";
		String realTable = "xsxx_xsxfxxb";
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String xn = request.getParameter("xn");
		String bjdm = request.getParameter("bjdm");
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		schooling.setXm(xm);
		String querry = "";
		String sql = "";
		String rsNum = "";

		String[] colList = { "主键", "行号", "xh", "xm", "xn", "bjmc", "yjf",
				"sjf", "qf" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		String go = request.getParameter("go");
		if ("".equalsIgnoreCase(xh) || xh == null) {
			xh = "%";
		} else {
			xh += "%";
		}
		if ("".equalsIgnoreCase(xm) || xm == null) {
			xm = "%";
		} else {
			xm = "%" + xm + "%";
		}
		querry = StudentInfoLogicDAO
				.getQuerry(nj, xydm, zydm, xn, bjdm, xh, xm); // 获得查询条件
		if ("go".equalsIgnoreCase(go) && go != null) {
			StringBuffer sb = new StringBuffer();
			sb
					.append("select * from (select * from(select rownum r, xh||xn 主键,rownum 行号,xh,xm,xn,bjmc,yjf,sjf,qf from ");
			sb.append(tableName);
			sb.append(querry);
			sb.append(")where r<=");
			sb.append(schooling.getPages().getStart()
					+ schooling.getPages().getPageSize());
			sb.append(") where r>");
			sb.append(schooling.getPages().getStart());
			sql = sb.toString();

			vector.addAll(dao.rsToVator(sql, new String[] { xh, xm },
					new String[] { "r", "主键", "行号", "xh", "xm", "xn", "bjmc",
							"yjf", "sjf", "qf" }));
			rsNum = vector == null ? "0" : String.valueOf(vector.size());
		}

		schooling.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(
						"select count(*) count from (select * from "
								+ tableName + querry + ")", new String[] { xh,
								xm }, "count")));

		xydm = (xydm == null ? "" : xydm);
		zydm = (zydm == null ? "" : zydm);
		nj = (nj == null ? "" : nj);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;

		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userOper", "yes");
		request.setAttribute("writeAble", writeAble);
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBCSZYJSXY)){//宁波城市职业技术学院
			return mapping.findForward("xfxxwh");
		}
		return mapping.findForward("success");
	}

	public static ActionForward stuSchoolingInfoOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String writeAble = Base.getWriteAble(request);
		String tableName = "view_xsxx_xsxfxx";
		String realTable = "xsxx_xsxfxxb";
		String sql = "";
		String[] colList = { "xh", "xm", "xn", "xb", "xymc", "zymc", "nj",
				"bjmc", "yjf", "sjf", "qf" };
		HashMap<String, String> map = new HashMap<String, String>();
		boolean del = false;

		if ("add".equalsIgnoreCase(doType)) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], "");
			}
		} else if ("modi".equalsIgnoreCase(doType)) {
			sql = "select * from " + tableName + " where xh||xn=?";
			map = dao.getMap(sql, new String[] { pkValue }, colList);
		} else {
			del = StandardOperation.delete(realTable, "xh||xn", pkValue,
					request);
			if (del) {
				request.setAttribute("result", "ok");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/stuSchoolingInfo.do?go=go", false);
		}
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map);
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("success");
	}

	public static ActionForward stuSchoolingInfoSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String writeAble = Base.getWriteAble(request);
		String realTable = request.getParameter("tab");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String pk = "xh||xn";
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String yjf = request.getParameter("yjf");
		String sjf = request.getParameter("sjf");
		String qf = request.getParameter("qf");
		String[] colList = { "xh", "xn", "yjf", "sjf", "qf" };
		boolean insert = false;
		boolean del = false;

		if ("add".equalsIgnoreCase(doType)) {
			insert = StandardOperation.insert(realTable, colList, new String[] {
					xh, xn, yjf, sjf, qf }, request);
		} else {
			if ("".equalsIgnoreCase(pkValue) || pkValue == null) {
				del = StandardOperation.delete(realTable, "xh||xn", xh + xn,
						request);
			} else {
				del = StandardOperation.delete(realTable, pk, pkValue, request);
			}
			if (del) {
				insert = StandardOperation.insert(realTable, colList,
						new String[] { xh, xn, yjf, sjf, qf }, request);
			}
		}
		if (insert) {
			request.setAttribute("result", "ok");
		} else {
			request.setAttribute("result", "no");
		}
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("success");
	}

	//学生集体信息报表---云南艺术
	public static ActionForward stuGroupInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StudentInfoForm stuForm = (StudentInfoForm) form;
		HttpSession session = request.getSession();
		String writeAble = Base.getWriteAble(request);
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		request.setAttribute("userType", userType);
		String xxdm = StandardOperation.getXxdm();

		String[] colListCN = null;
		String[] colList = null;
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "";
		String tableName = "view_stugroup";
		String sql = "";

		String nj = stuForm.getNj();
		String xydm = stuForm.getXydm();
		String zydm = stuForm.getZydm();
		String bjdm = stuForm.getBjdm();
		String xjztm = DealString.toGBK(stuForm.getXjzt());
		String sfzx = stuForm.getSfzx();
		if (userType.equalsIgnoreCase("xy") && (xydm == null || xydm.equalsIgnoreCase(""))) {
			xydm = userDep;
			stuForm.setXydm(xydm);
		}

		StringBuffer querry = new StringBuffer("");
		querry.append(" where 1=1");
		querry.append((nj == null || nj.trim().length() < 1) ? "" : " and a.nj='"
				+ nj.trim() + "'");
		querry.append((xydm == null || xydm.trim().length() < 1) ? ""
				: " and a.xydm='" + xydm + "'");
		querry.append((zydm == null || zydm.trim().length() < 1) ? ""
				: " and a.zydm='" + zydm + "'");
		querry.append((bjdm == null || bjdm.trim().length() < 1) ? ""
				: " and a.bjdm='" + bjdm + "'");
		if(xjztm!=null && !"".equalsIgnoreCase(xjztm) && xjztm.equalsIgnoreCase("有学籍")){
			//有学籍
			querry.append(" and (a.xjztm='" + xjztm + "' or a.xjztm='有' or a.xjztm is null)");
		}
		if(xjztm!=null && !"".equalsIgnoreCase(xjztm) && xjztm.equalsIgnoreCase("无学籍")){
			//无学籍
			querry.append(" and (a.xjztm='" + xjztm + "' or a.xjztm='无')");
		}		
		if(sfzx!=null && sfzx.equalsIgnoreCase("1")){		
			//在校学生 
		}
		if(sfzx!=null && sfzx.equalsIgnoreCase("0")){
			//不在校
			querry.append(" and exists(select xh from (select xh,xm,decode(nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx),null,'在校',nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx)) sfzx from view_xsxxb f) f where f.sfzx='不在校' and a.xh=f.xh)");
		}	
		
		colList = new String[] { "xymc", "zymc", "bjmc", "zrs", "zsrs", "wzrs",
				"boyrs", "girlrs", "snrs", "swrs","dyrs","tyrs","qzrs","dkrs","qgzxrs","bgbrs", "bzxm", "bzlxdh", "bzrxm",
				"bzrlxdh", "fdyxm", "fdylxdh" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		sql = "select a.* from " + tableName + " a " + querry;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XBMZDX)){
			//西北民大
			StudentInfoService service = new StudentInfoService();
			sql = service.getSql(querry.toString(),"甘肃");
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		xydm = (xydm == null ? "" : xydm);
		zydm = (zydm == null ? "" : zydm);
		nj = (nj == null ? "" : nj);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;

		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("userType", userType);
		return mapping.findForward("success");
	}

	/**
	 * 出国学生在读证明连打
	 * 
	 */
	public static ActionForward CertificatePrintAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> rxMap = new HashMap<String, String>();
		HashMap<String, String> blMap = new HashMap<String, String>();
		HashMap<String, String> byMap = new HashMap<String, String>();

		String doType = request.getParameter("doType");
		String zmlx = DealString.toGBK(request.getParameter("zmlx"));//证明类型
		String page = "";
		
		doType = (doType == null || "".equalsIgnoreCase(doType)) ? "" : doType.trim();

		String mb = request.getParameter("mb");
		String xh = DealString.toGBK(request.getParameter("xh").trim());
		String rxrq = dao.getOneRs("select rxrq from view_xsxxb where xh=?",new String[] { xh }, "rxrq");
		String blrq = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd') str from dual",new String[] {}, "str");
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xz = dao.getOneRs("select xz from view_xsjbxx where xh=?",new String[] { xh }, "xz");
		String dqN = dao.getOneRs("select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqN from dual",new String[] {}, "dqN");
		String dqY = dao.getOneRs("select substr(to_char(sysdate,'yyyy-mm-dd'),6,2) dqY from dual",new String[] {}, "dqY");

		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?";
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc" };
		rs = dao.getMap(sql, new String[] { xh }, colList);
		String rxny = dao.getOneRs("select rxrq from view_xsxxb where xh=?",new String[] { xh }, "rxrq");

		String rxN = "";
		String rxY = "";
		String rxR = "";
		if (rxny == null || "".equalsIgnoreCase(rxny)) {
			request.setAttribute("message", "入学年月数据错误！");
			request.setAttribute("url", "");
			return mapping.findForward("false");
		}
		if(rxny.length()==8){
			rxN = rxny.substring(0, 4);
			rxY = rxny.substring(4, 6);
			rxR = rxny.substring(6, 8);
		} else if (rxny.length() == 10) {
			rxN = rxny.substring(0, 4);
			rxY = rxny.substring(5, 7);
			rxR = rxny.substring(8,10);
		} else {
			// dataSearchForm.setErrMsg("入学年月数据错误！");
			request.setAttribute("message", "入学年月数据错误,数据格式(yyyymmdd 或yyyy-mm-dd)！");
			request.setAttribute("url", "");
			return mapping.findForward("false");
		}
		
		if(xz == null || "".equalsIgnoreCase(xz)){
			request.setAttribute("message", "无学制信息！");
			request.setAttribute("url", "");
			return mapping.findForward("false");
		}

		rxMap.put("rxN", rxN);
		rxMap.put("rxY", rxY);
		rxMap.put("rxR", rxR);

		byMap.put("byN", rxN.equalsIgnoreCase("0000") ? "" : String.valueOf(Integer.parseInt(rxN) + Integer.parseInt(xz)));
		byMap.put("byY", rxY);
		byMap.put("byR", rxR);

		blMap.put("blN", blrq.substring(0, 4));
		blMap.put("blY", blrq.substring(5, blrq.lastIndexOf("-")));
		blMap.put("blR", blrq.substring(blrq.lastIndexOf("-") + 1, blrq.length()));		

		int n = Integer.parseInt(dqN) - Integer.parseInt(rxN);

		int y = Integer.parseInt(dqY) - Integer.parseInt(rxY);
//		if (y < 0 && n!=1) {			
//			nj = String.valueOf(n - 1);
//		} else if(n==0){
//			nj = String.valueOf(n+1);
//		}else {
//			nj = String.valueOf(n);
//		}
		
		if (y < 0) {			
			nj = String.valueOf(n);
		} else{
			nj = String.valueOf(n+1);
		}
		
		request.setAttribute("mb", mb);
		request.setAttribute("rs", rs);
		request.setAttribute("rxMap", rxMap);
		request.setAttribute("blMap", blMap);
		request.setAttribute("byMap", byMap);
		request.setAttribute("nj", nj);
		request.setAttribute("rxrq", rxrq);
		request.setAttribute("blrq", blrq);

		if (doType.equalsIgnoreCase("printone")) {
			if ("3".equalsIgnoreCase(mb)){
				return mapping.findForward("zdzm_en_one");
			}else{
				page = "zdzm_cn_one";
				if(zmlx != null && "在读证明（出国留学）学位版本".equalsIgnoreCase(zmlx)){
					page = "zdzm_cglx_xw";
				}else if(zmlx != null && "在读证明（出国留学）正常毕业版本".equalsIgnoreCase(zmlx)){
					page = "zdzm_cglx_zcby";
				}
				return mapping.findForward(page);
			}
		} else {
			if ("3".equalsIgnoreCase(mb)){
				return mapping.findForward("ywmb_all");
			}else{
				page = "zwmb_all";
				if(zmlx != null && "在读证明（出国留学）学位版本".equalsIgnoreCase(zmlx)){
					page = "zwmb_xw";
				}else if(zmlx != null && "在读证明（出国留学）正常毕业版本".equalsIgnoreCase(zmlx)){
					page = "zwmb_zcby";
				}
				return mapping.findForward(page);
			}
		}
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * @category 学生的学籍异动处理方法
	 */
	public static ActionForward stuStatusDifferentInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StuInfoDAO siDAO = new StuInfoDAO();
		boolean flag=false;
		List rs = null;
		
		StudentInfoForm sif = (StudentInfoForm) form;		
		HttpSession session = request.getSession();
		
		//是否执行查询
		String go = request.getParameter("go");
		String userType = session.getAttribute("userOnLine").toString();
		String userSpceType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String type = request.getParameter("type");
		String userName = session.getAttribute("userName").toString();
		String xxdm = StandardOperation.getXxdm();
		String writeAble = CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stu_status_different.do")?"yes":"no";
		
		String tableName = "view_xjydjbxx";
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		StudentInfoService service = new StudentInfoService();
		String ydhxydm = DealString.toGBK(sif.getYdhxydm());
		
		
		//学院用户只能查询本学院记录
		if(session.getAttribute("fdyQx").equals(true)){
			sif.setIsFdy("true");
		}else if (userSpceType.equalsIgnoreCase("xy")&& (ydhxydm == null || ydhxydm.equalsIgnoreCase(""))) {
			ydhxydm = userDep;
			sif.setYdhxydm(ydhxydm);
		}	
		
		String[] cols_en = {"r", "ydxh", "xh", "xm","ydqbjmc", "ydhxymc", "ydhzymc", "ydhbjmc","ydlbmc","ydrq" };
		//查询信息操作
		String[] cols_cn = dao.getColumnNameCN(cols_en, tableName);
		topTr = dao.arrayToList(cols_en, cols_cn);
		if ("go".equalsIgnoreCase(go)) {
			rs = service
					.getStatusDifferentInfo(userName, sif, cols_en, request);						
			// 异动提醒
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)|| xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				boolean different = false;
				dao.runProcuder("{call pro_stuInfoXjydb ()}", new String[] {});
				different = (Integer.parseInt(dao.getOneRs("select stuInfoydxxFun num from dual", new String[] {},"num")) > 0) ? true : false;
				if (different) {
					request.setAttribute("different", different);
				}
			}
			
			request.setAttribute("searchTj", sif.getSearchModel());
		}
		
		
		//增加页面跳转
		if ("add".equalsIgnoreCase(doType)) {
			StudentInfoDao stuDao = new StudentInfoDao();
			String ydxh = stuDao.getMaxYdxh();
			sif.setYdxh(ydxh);			
			request.setAttribute("rs", sif);
			
			String xy = sif.getYdhxydm()==null ? "" : sif.getYdhxydm();
			String zy = sif.getYdhzydm()==null ? "" : sif.getYdhzydm();
			String nj = sif.getYdhnj()==null ? "" : sif.getYdhnj();
			String bjKey = xy + "!!" + zy + "!!" + nj;			
			
			request.setAttribute("oper", "add");			
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", Base.getZyMap().get(ydhxydm));			
			//request.setAttribute("bjList", Base.getBjMap().get(bjKey));
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX) && siDAO.isFdy(userName)){
				//武汉理工大学辅导员登录			
				request.setAttribute("bjList", siDAO.getBjByFdy(userName,xy,zy));
			}else{			
				request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				StuInfoDAO stuDAO = new StuInfoDAO();
				List<HashMap<String, String>> xsztList = stuDAO.getXsztList("有学籍");
				request.setAttribute("xsztList", xsztList);
				request.setAttribute("xjzt", "有学籍");
			}
			request.setAttribute("ydlbList", dao.getYdlbList());
			request.setAttribute("writeAble", "yes");
			request.setAttribute("xjztList", siDAO.getXjztList());
			return new ActionForward("/shgc/stu_info/stu_info_modify.jsp");
		}
		//修改页面跳转
		if ("update".equalsIgnoreCase(doType)) {
			HashMap<String, String> map = new HashMap<String, String>();
			
			String xh = request.getParameter("xh").trim();
			String ydxh = request.getParameter("ydxh");

			map = service.getOneStatuDiffInfo(xh+ydxh);
			
			String xydm = map.get("ydhxydm");			
			String zydm = map.get("ydhzydm");
			String nj = map.get("ydhnj");
			xydm = xydm == null ? "" : xydm;
			zydm = zydm == null ? "" : zydm;			
			nj = nj == null ? "" : nj;
			
			String bjKey = xydm + "!!" + zydm + "!!" + nj;
						
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX) && siDAO.isFdy(userName)){
				//武汉理工大学辅导员登录			
				request.setAttribute("bjList", siDAO.getBjByFdy(userName,xydm,zydm));
			}else{			
				request.setAttribute("bjList", Base.getBjMap().get(bjKey));//发送班级列表
			}
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				String zt = map.get("xszt");
				String xjzt = "";
				if ("005".equalsIgnoreCase(zt)) {
					xjzt = "无学籍";
				} else {
					xjzt = "有学籍";
				}
				StuInfoDAO stuDAO = new StuInfoDAO();
				List<HashMap<String, String>> xsztList = stuDAO.getXsztList(xjzt);
				
				sif.setYdhxjztm(xjzt);
				sif.setXszt(zt);
				request.setAttribute("xsztList", xsztList);
				request.setAttribute("xjzt", xjzt);
			}
			
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", Base.getZyMap().get(xydm));
			request.setAttribute("oper", "update");
			request.setAttribute("rs", map);
			request.setAttribute("user", userType);
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("ydlbList", dao.getYdlbList());			
			request.setAttribute("xjztList", siDAO.getXjztList());
			request.setAttribute("userOper", request.getParameter("oper"));
			return new ActionForward("/shgc/stu_info/stu_info_modify.jsp");
		}
		//删除异动信息
		if ("del".equalsIgnoreCase(doType)) {			
			String xh = request.getParameter("pkValue");
			String ydxh = request.getParameter("ydxh");
			
			//sif.setXh("");		
			flag = service.delStatuDiffInfo(xh, ydxh, request);
			request.setAttribute("result", flag);
		}
		//操作
		if ("modify".equalsIgnoreCase(doType)) {			
			String ydqnj = request.getParameter("ydqnj");
			String ydqxymc = DealString.toGBK(request.getParameter("ydqxymc"));
			String ydqxydm = dao.getOneRs("select xydm from view_njxyzybj where xymc=?",new String[]{ydqxymc}, "xydm");				
			String ydqzymc = DealString.toGBK(request.getParameter("ydqzymc"));
			
			String ydqzydm = dao.getOneRs("select zydm from view_njxyzybj where zymc=?",new String[]{ydqzymc}, "zydm");
			String ydqbjmc = DealString.toGBK(request.getParameter("ydqbjmc"));
			String ydqbjdm = dao.getOneRs("select bjdm from view_njxyzybj where bjmc=?", new String[]{ydqbjmc}, "bjdm");
			String ydqxz = request.getParameter("ydqxz");
			String ydqxjztm = DealString.toGBK(request.getParameter("ydqxj"));
			String sfzx = DealString.toGBK(sif.getSfzx());
			
			String ydlbdm = sif.getYdlbdm();
			
//			String ydzt = dao.getOneRs("select xjzt from dm_ydlb where ydlbm=?", new String[]{ydlbdm}, "xjzt");
			String ydhxjztm = sif.getYdhxjztm();
			
			sif.setYdqnj(ydqnj);
			sif.setYdqxymc(ydqxymc);
			sif.setYdqxydm(ydqxydm);
			sif.setYdqzydm(ydqzydm);
			sif.setYdqbjdm(ydqbjdm);
			sif.setYdqxz(ydqxz);
			sif.setYdqxjztm(ydqxjztm);
			sif.setSfzx(sfzx);
			sif.setYdyy(DealString.toGBK(sif.getYdyy()));
			sif.setYdsm(DealString.toGBK(sif.getYdsm()));
			sif.setClwh(DealString.toGBK(sif.getClwh()));
			
			sif.setYdhxydm(sif.getXydm());
			sif.setYdhzydm(sif.getZydm());
			sif.setYdhbjdm(sif.getBjdm());
			sif.setYdhnj(sif.getNj());
			sif.setYdhxz(sif.getXz());
			sif.setYdhxjztm(sif.getYdhxjztm());
			sif.setSfzx(sfzx);
			sif.setYdhxjztm(ydhxjztm);
			sif.setYdjzrq(sif.getYdjzrq());
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//中国地质大学
				String xszt = sif.getXszt();
				if("003".equalsIgnoreCase(xszt)||"005".equalsIgnoreCase(xszt)){
					sfzx="在校";
				}else{
					sfzx="不在校";
				}
				sif.setSfzx(sfzx);
				sif.setXszt(xszt);
			}
			
			if ("add".equalsIgnoreCase(type)) {
				String xh = sif.getXh();
				//增加学籍异动信息
				flag = service.addStatuStuInfo(sif, request);
				//如果学生不存在加入到学生信息中
				if(flag){
					if(!service.checkExists("view_xsjbxx", "xh", xh)){
						String xm = DealString.toGBK(sif.getXm());
						String ydhnj = sif.getYdhnj();
						String ydhzydm = sif.getYdhzydm();
						String ydhbjdm = sif.getYdhbjdm();
						ydhxydm = sif.getYdhxydm();
						String ydhxz = sif.getYdhxz();	
						flag = StandardOperation.insert("xsxxb", new String[]{"xh","xm","nj","xydm","zydm","bjdm","xz","xjztm"},
								new String[]{xh,xm,ydhnj,ydhxydm,ydhzydm,ydhbjdm,ydhxz,ydhxjztm}, request);
					}
				}
                //更新学生基本信息
				if (ydhxydm != null && !ydhxydm.equalsIgnoreCase("")) {
					flag = service.modStudentBaseInfo(xh, sif, request);
				}
				//更新是否在校信息
				sfzx = dao.getOneRs("select sfzx from dm_ydlb where ydlbm=?", new String[]{ydlbdm}, "sfzx");
				flag = StandardOperation.update("xsxxb", new String[]{"sfzx"}, new String[]{sfzx}, "xh", xh, request);
			}
			if ("update".equalsIgnoreCase(type)) {
				//更新学籍异动信息
				flag = service.modStatuStuInfo(sif, request);				
				//判断学生处是否修改学生信息
				String modiTag = dao.getOneRs("select modistuinfo from xtszb", new String[] {}, "modistuinfo");
				if("1".equalsIgnoreCase(modiTag)){
					//修改学生基本信息
					String xh = sif.getXh();
					flag = service.modStudentBaseInfo(xh, sif, request);
				} 
			}
			String xydm = "";
			String zydm = "";
			String nj = "";
			String bjKey = xydm + "!!" + zydm + "!!" + nj;
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				String xszt = sif.getXszt();
				String xjzt = "";
				if ("005".equalsIgnoreCase(xszt)) {
					xjzt = "无学籍";
				} else {
					xjzt = "有学籍";
				}
				StuInfoDAO stuDAO = new StuInfoDAO();
				List<HashMap<String, String>> xsztList = stuDAO.getXsztList(xjzt);
				request.setAttribute("xsztList", xsztList);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX) && siDAO.isFdy(userName)){
				//武汉理工大学辅导员权限			
				request.setAttribute("bjList", siDAO.getBjByFdy(userName,xydm,zydm));
			}else{			
				request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
			}			
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", Base.getZyMap().get(xydm));		
			request.setAttribute("ydlbList", dao.getYdlbList());
			request.setAttribute("result", flag);
			request.setAttribute("oper", "oper");
			request.setAttribute("rs", new StudentInfoForm());
			request.setAttribute("writeAble", writeAble);
			//request.setAttribute("xjztList", dao.getList("select zxdm,zxdmmc from dm_zju_xjzt order by zxdm", new String[]{}, new String[]{"zxdm","zxdmmc"}));
			request.setAttribute("xjztList", siDAO.getXjztList());
			return mapping.findForward("page");
		}
		if (sif != null) {
			request.setAttribute("rs", rs);
		} else {
			request.setAttribute("rs", sif);
		}
		if (userType.equalsIgnoreCase("student")) {
			String xh = session.getAttribute("userName").toString();
			String sql_user = "select rownum r,a.* from view_xjydjbxx a where xh='" + xh+ "'";
			rs = dao.rsToVator(sql_user, new String[] {}, cols_en);
			
			topTr = dao.arrayToList(cols_en, dao.getColumnNameCN(cols_en, "view_xjydjbxx"));
			request.setAttribute("topTr", topTr);
			if (rs != null) {
				int rsNum = rs.size();
				request.setAttribute("rsNum", rsNum);
			}
			request.setAttribute("rs", rs);
			request.setAttribute("title", "学生信息 - 学籍异动 - 学籍异动信息");
			request.setAttribute("user", "student");
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("xjztList", siDAO.getXjztList());
			return new ActionForward("/shgc/stu_info/stu_info.jsp");
		}
		
		String xydm = "";
		String zydm = "";
		String nj = "";
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX) && siDAO.isFdy(userName)){
			//武汉理工大学辅导员登录			
			request.setAttribute("bjList", siDAO.getBjByFdy(userName,xydm,zydm));
		}else{			
			request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		}
		//=========2011.1.26 lr=======
		if(rs == null || rs.size()<1){//无记录时，初始化总页数和总记录数
			sif.getPages().setMaxPage(0);
			sif.getPages().setMaxRecord(0);
		}
		//=========end=======
		//发送表头和记录数到页面
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum",  rs == null ? 0 : rs.size());
		request.setAttribute("pageSize", sif.getPages().getPageSize());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("title", "学生信息 - 学籍异动 - 学籍异动信息");
		request.setAttribute("ydlbList", dao.getYdlbList());
		request.setAttribute("xjztList", siDAO.getXjztList());
		return mapping.findForward("success");
	}
	
	
	/**
	 * 家庭信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public static ActionForward familyInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StuInfoDAO stuDao = new StuInfoDAO();
		boolean flag = false;
		CommanForm studefamily = (CommanForm) form;
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userOnLine").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String oper = request.getParameter("oper");
		String writeAble =Base.getWriteAble(request);
		// 查询学生家庭信息
		if (userType.equalsIgnoreCase("student")) {
			request.setAttribute("user", "student");
			request.setAttribute("oper","oper");
			return new ActionForward("/sjcz/stu_family_modify.jsp", false);///stu_family_detail.do?xh=
		}		
		request.setAttribute("user", "teacher");		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String rsNum = "0";// 返回的记录数
		String nj = studefamily.getNj();
		String xydm = studefamily.getXydm();
		String zydm = studefamily.getZydm();
		String bjdm = studefamily.getBjdm();
		String xh = DealString.toGBK(studefamily.getXh());
		String xm = DealString.toGBK(studefamily.getXm());
		String jjzk = DealString.toGBK(studefamily.getJjzk());
		String jtyb = DealString.toGBK(studefamily.getJtyb());
		String jtszd = DealString.toGBK(studefamily.getJtszd());
		String jtcy1_xm = DealString.toGBK(studefamily.getJtcy1_xm());
		String jtcy1_sfzh = DealString.toGBK(studefamily.getJtcy1_sfzh());
		String jtcy1_gx = DealString.toGBK(studefamily.getJtcy1_gx());
		String jtcy1_mz = DealString.toGBK(studefamily.getJtcy1_mz());
		String jtcy1_zw = DealString.toGBK(studefamily.getJtcy1_zw());
		String jtcy1_zy = DealString.toGBK(studefamily.getJtcy1_zy());
		String jtcy1_nl = DealString.toGBK(studefamily.getJtcy1_nl());
		String jtcy1_ch = DealString.toGBK(studefamily.getJtcy1_ch());
		String jtcy1_gzdz = DealString.toGBK(studefamily.getJtcy1_gzdz());
		String jtcy1_yzbm = DealString.toGBK(studefamily.getJtcy1_yzbm());
		String jtcy1_zzmm = DealString.toGBK(studefamily.getJtcy1_zzmm());		
		String jtcy1_lxdh1 = DealString.toGBK(studefamily.getJtcy1_lxdh1());
		String jtcy1_lxdh2 = DealString.toGBK(studefamily.getJtcy1_lxdh2());
		String jtcy1_lxdh3 = DealString.toGBK(studefamily.getJtcy1_lxdh3());
		String jtcy2_xm = DealString.toGBK(studefamily.getJtcy2_xm());
		String jtcy2_sfzh = DealString.toGBK(studefamily.getJtcy2_sfzh());
		String jtcy2_gx = DealString.toGBK(studefamily.getJtcy2_gx());
		String jtcy2_mz = DealString.toGBK(studefamily.getJtcy2_mz());
		String jtcy2_zw = DealString.toGBK(studefamily.getJtcy2_zw());
		String jtcy2_zy = DealString.toGBK(studefamily.getJtcy2_zy());
		String jtcy2_nl = DealString.toGBK(studefamily.getJtcy2_nl());
		String jtcy2_ch = DealString.toGBK(studefamily.getJtcy2_ch());
		String jtcy2_gzdz = DealString.toGBK(studefamily.getJtcy2_gzdz());
		String jtcy2_yzbm = DealString.toGBK(studefamily.getJtcy2_yzbm());
		String jtcy2_zzmm = DealString.toGBK(studefamily.getJtcy2_zzmm());
		String jtcy2_lxdh1 = DealString.toGBK(studefamily.getJtcy2_lxdh1());
		String jtcy2_lxdh2 = DealString.toGBK(studefamily.getJtcy2_lxdh2());
		String jtcy2_lxdh3 = DealString.toGBK(studefamily.getJtcy2_lxdh3());
		String jtcy3_xm = DealString.toGBK(studefamily.getJtcy3_xm());
		String jtcy3_sfzh = DealString.toGBK(studefamily.getJtcy3_sfzh());
		String jtcy3_gx = DealString.toGBK(studefamily.getJtcy3_gx());
		String jtcy3_mz = DealString.toGBK(studefamily.getJtcy3_mz());
		String jtcy3_zw = DealString.toGBK(studefamily.getJtcy3_zw());
		String jtcy3_zy = DealString.toGBK(studefamily.getJtcy3_zy());
		String jtcy3_nl = DealString.toGBK(studefamily.getJtcy3_nl());
		String jtcy3_ch = DealString.toGBK(studefamily.getJtcy3_ch());
		String jtcy3_gzdz = DealString.toGBK(studefamily.getJtcy3_gzdz());
		String jtcy3_yzbm = DealString.toGBK(studefamily.getJtcy3_yzbm());
		String jtcy3_zzmm = DealString.toGBK(studefamily.getJtcy3_zzmm());
		String jtcy3_lxdh1 = DealString.toGBK(studefamily.getJtcy3_lxdh1());
		String jtcy3_lxdh2 = DealString.toGBK(studefamily.getJtcy3_lxdh2());
		String jtcy3_lxdh3 = DealString.toGBK(studefamily.getJtcy3_lxdh3());
		String sfdb = DealString.toGBK(studefamily.getSfdb());
		String srly = DealString.toGBK(studefamily.getSrly());
		String jtzsr = DealString.toGBK(studefamily.getJtzsr());
		String jtrjsr = DealString.toGBK(studefamily.getJtrjsr());
		String jtcy1_jtdz = DealString.toGBK(studefamily.getJtcy1_jtdz());
		String jtcy2_jtdz = DealString.toGBK(studefamily.getJtcy2_jtdz());
		String jtcy3_jtdz = DealString.toGBK(studefamily.getJtcy3_jtdz());
		String lxdh1 = DealString.toGBK(studefamily.getLxdh1());
		String jtcy1_lxr = DealString.toGBK(studefamily.getJtcy1_lxr());
		String jtcy2_lxr = DealString.toGBK(studefamily.getJtcy2_lxr());
		String jtcy3_lxr = DealString.toGBK(studefamily.getJtcy3_lxr());
		String jtcy4_xm = DealString.toGBK(studefamily.getJtcy4_xm());
		String jtcy4_gx = DealString.toGBK(studefamily.getJtcy4_gx());
		String jtcy4_nl = DealString.toGBK(studefamily.getJtcy4_nl());
		String jtcy4_sfzh = DealString.toGBK(studefamily.getJtcy4_sfzh());
		String jtcy4_mz = DealString.toGBK(studefamily.getJtcy4_mz());
		String jtcy4_zzmm = DealString.toGBK(studefamily.getJtcy4_zzmm());
		String jtcy4_zy = DealString.toGBK(studefamily.getJtcy4_zy());
		String jtcy4_zw = DealString.toGBK(studefamily.getJtcy4_zw());
		String jtcy4_lxdh1 = DealString.toGBK(studefamily.getJtcy4_lxdh1());
		String jtcy4_lxdh2 = DealString.toGBK(studefamily.getJtcy4_lxdh2());
		String jtcy4_gzdz = DealString.toGBK(studefamily.getJtcy4_gzdz());
		String jtcy4_yzbm = DealString.toGBK(studefamily.getJtcy4_yzbm());
		
		String jtcy5_xm = DealString.toGBK(studefamily.getJtcy5_xm());
		String jtcy5_gx = DealString.toGBK(studefamily.getJtcy5_gx());
		String jtcy5_nl = DealString.toGBK(studefamily.getJtcy5_nl());
		String jtcy5_sfzh = DealString.toGBK(studefamily.getJtcy5_sfzh());
		String jtcy5_mz = DealString.toGBK(studefamily.getJtcy5_mz());
		String jtcy5_zzmm = DealString.toGBK(studefamily.getJtcy5_zzmm());
		String jtcy5_zy = DealString.toGBK(studefamily.getJtcy5_zy());
		String jtcy5_zw = DealString.toGBK(studefamily.getJtcy5_zw());
		String jtcy5_lxdh1 = DealString.toGBK(studefamily.getJtcy5_lxdh1());
		String jtcy5_lxdh2 = DealString.toGBK(studefamily.getJtcy5_lxdh2());
		String jtcy5_gzdz = DealString.toGBK(studefamily.getJtcy5_gzdz());
		String jtcy5_yzbm = DealString.toGBK(studefamily.getJtcy5_yzbm());
		String zyshgxxx1 = DealString.toGBK(studefamily.getZyshgxxx1());
		String zyshgxxx2 = DealString.toGBK(studefamily.getZyshgxxx2());
		String zyshgxxx3 = DealString.toGBK(studefamily.getZyshgxxx3());
		

		if ("xy".equalsIgnoreCase(dao.getUserType(userDep)) 
				&& !session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {//无辅导员只能查询所带班级限制
			xydm = userDep;
			studefamily.setXydm(xydm);
		}

		String tableName = "view_xsjtxx";
		String[] cols_en = { "xh", "xm", "nj", "bjmc", "lxdh1", "jtszd","jtcy1_xm", "jtcy2_xm", "jtcy3_xm" };
		String[] cols_cn = dao.getColumnNameCN(cols_en, tableName);

		List<HashMap<String, String>> title = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < cols_en.length; i++) {
			HashMap<String, String> temmap = new HashMap<String, String>();
			temmap.put("en", cols_en[i]);
			temmap.put("cn", cols_cn[i]);
			title.add(temmap);
		}
		StringBuffer sql = new StringBuffer("select rownum r ,a.* from ");
		sql.append(tableName);
		sql.append(" a ");
		sql.append(" where 1=1");
		sql.append((nj == null || nj.trim().length() < 1) ? "" : " and nj='"+ nj + "'");
		sql.append((xydm == null || xydm.trim().length() < 1) ? "": " and xydm='" + xydm + "'");
		sql.append((zydm == null || zydm.trim().length() < 1) ? "": " and zydm='" + zydm + "'");
		sql.append((bjdm == null || bjdm.trim().length() < 1) ? "": " and bjdm='" + bjdm + "'");
		sql.append((xh == null || xh.trim().length() < 1) ? "": " and xh like '%" + DealString.replaceImmitStr(xh) + "%'");
		sql.append((xm == null || xm.trim().length() < 1) ? "": " and xm like '%" + DealString.replaceImmitStr(xm) + "%'");		
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			//辅导员登录			
			sql.append(" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + DealString.replaceImmitStr(userName) + "')");
		}
		
		String go = request.getParameter("go");
		if ("del".equalsIgnoreCase(doType)) {
			boolean result = false;
			String delPk = request.getParameter("delPk");
			String[] xhStr = delPk.split("!!#!!");			
			for (int i = 0; i < xhStr.length; i++) {
				result = StandardOperation.delete("xsfzxxb", "xh", xhStr[i], request);				
			}			
			request.setAttribute("result", result);
			request.setAttribute("userOper", "yes");
			go = "go";
		}
		//高级查询 edit by luojw
		String searchTj = SearchService.getSearchTj(studefamily.getSearchModel());
		String[] inputV = SearchService.getTjInput(studefamily.getSearchModel());
		System.out.println("sql:"+sql+searchTj);
		System.out.println("where:"+searchTj);
		
		sql.append(searchTj);
		if ("go".equalsIgnoreCase(go)) {
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from (select * from (");
			sb.append(sql);
			//sb.append(searchTj);
			sb.append(")where r<=");
			sb.append(studefamily.getPages().getStart()+studefamily.getPages().getPageSize());
			sb.append(") where r>");
			sb.append(studefamily.getPages().getStart());
			String querry= sb.toString();
			rs = dao.rsToVator(querry, inputV, new String[] {"r","xh", "xm", "nj", "bjmc", "lxdh1", "jtszd","jtcy1_xm", "jtcy2_xm", "jtcy3_xm"});
		
			request.setAttribute("searchTj", studefamily.getSearchModel());
		}
		request.setAttribute("top", title);
		request.setAttribute("rsNum", rs == null ? 0 : rs.size());
		request.setAttribute("rs", rs);
		
		String querry = "select count(*) count from (" + sql.toString()+")";	
		
		studefamily.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(querry, inputV, "count")));
		if ("modify".equalsIgnoreCase(doType)) { // 信息修改
			String[] columns = null;
			String[] col_value = null;
			
			columns = new String[] { "xh", "jtszd", "jtcy1_xm", "jtcy1_zw","jtcy1_gx","jtcy1_sfzh", "jtcy1_zy", "jtcy1_mz", "jtcy1_zzmm",
					"jtcy1_gzdz", "jtcy1_nl", "jtcy1_lxdh1", "jtcy1_lxdh2","jtcy1_lxdh3", "JTCY1_YZBM", "jtcy2_xm", "jtcy2_zw",
					"jtcy2_gx","jtcy2_sfzh", "jtcy2_zy", "jtcy2_mz", "jtcy2_zzmm","jtcy2_gzdz", "jtcy2_nl", "jtcy2_lxdh1", "jtcy2_lxdh2",
					"jtcy2_lxdh3", "JTCY2_YZBM", "jtcy3_xm", "jtcy3_zw","jtcy3_gx","jtcy3_sfzh", "jtcy3_zy", "jtcy3_mz", "jtcy3_zzmm",
					"jtcy3_gzdz", "jtcy3_nl", "jtcy3_lxdh1", "jtcy3_lxdh2","jtcy3_lxdh3", "JTCY3_YZBM","jjzk","yb","sfdb","srly","jtzsr",
					"jtrjsr","jtcy1_jtdz","jtcy2_jtdz","jtcy3_jtdz" ,"lxdh1","jtcy1_lxr","jtcy2_lxr","jtcy3_lxr","jtcy4_xm","jtcy4_gx",
					"jtcy4_nl","jtcy4_sfzh","jtcy4_mz","jtcy4_zzmm","jtcy4_zy","jtcy4_zw","jtcy4_lxdh1","jtcy4_lxdh2","jtcy4_gzdz","jtcy4_yzbm",
					"jtcy5_xm","jtcy5_gx","jtcy5_nl","jtcy5_sfzh","jtcy5_mz","jtcy5_zzmm","jtcy5_zy","jtcy5_zw","jtcy5_lxdh1","jtcy5_lxdh2",
					"jtcy5_gzdz","jtcy5_yzbm","zyshgxxx1","zyshgxxx2","zyshgxxx3","jtcy1_zjxy","jtcy2_zjxy","jtcy3_zjxy"};
			col_value = new String[] { xh, jtszd, jtcy1_xm, jtcy1_zw,jtcy1_gx,jtcy1_sfzh, jtcy1_zy, jtcy1_mz, jtcy1_zzmm, jtcy1_gzdz,
					jtcy1_nl, jtcy1_lxdh1, jtcy1_lxdh2, jtcy1_lxdh3,jtcy1_yzbm, jtcy2_xm, jtcy2_zw, jtcy2_gx,jtcy2_sfzh, jtcy2_zy,
					jtcy2_mz, jtcy2_zzmm, jtcy2_gzdz, jtcy2_nl,jtcy2_lxdh1, jtcy2_lxdh2, jtcy2_lxdh3, jtcy2_yzbm,
					jtcy3_xm, jtcy3_zw, jtcy3_gx,jtcy3_sfzh, jtcy3_zy, jtcy3_mz,jtcy3_zzmm, jtcy3_gzdz, jtcy3_nl, jtcy3_lxdh1,
					jtcy3_lxdh2, jtcy3_lxdh3, jtcy3_yzbm,jjzk ,jtyb,sfdb,srly,jtzsr,jtrjsr,jtcy1_jtdz,jtcy2_jtdz,jtcy3_jtdz,lxdh1,
					jtcy1_lxr,jtcy2_lxr,jtcy3_lxr,jtcy4_xm,jtcy4_gx,jtcy4_nl,jtcy4_sfzh,jtcy4_mz,jtcy4_zzmm,jtcy4_zy,jtcy4_zw,
					jtcy4_lxdh1,jtcy4_lxdh2,jtcy4_gzdz,jtcy4_yzbm,jtcy5_xm,jtcy5_gx,jtcy5_nl,jtcy5_sfzh,jtcy5_mz,jtcy5_zzmm,
					jtcy5_zy,jtcy5_zw,jtcy5_lxdh1,jtcy5_lxdh2,jtcy5_gzdz,jtcy5_yzbm,zyshgxxx1,zyshgxxx2,zyshgxxx3,studefamily.getJtcy1_zjxy(),
					studefamily.getJtcy2_zjxy(),studefamily.getJtcy3_zjxy()};			
			
			List listJtxx=dao.getList("select xh from xsfzxxb where xh=?", new String[] {xh}, new String[] {"xh"});
			if(listJtxx.size()>0){
				flag=StandardOperation.update("xsfzxxb", columns, col_value, "xh", xh,request);
			}
			else {
				flag = StandardOperation.insert("xsfzxxb", columns, col_value,request);
			}
			if(flag){
				if(xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)){//广东女子职业
					flag = StandardOperation.update("xsxxb", new String[]{"jtdz","jtjjqk"}, new String[]{jtszd,jjzk}, "xh", xh, request);
				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)||xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){//西昌学院
					flag = StandardOperation.update("xsxxb", new String[]{"jtdz"}, new String[]{jtszd}, "xh", xh, request);
				}			
			}			
			xydm = (xydm == null ? "" : xydm);
			zydm = (zydm == null ? "" : zydm);
			nj = (nj == null ? "" : nj);
			String bjKey = xydm + "!!" + zydm + "!!" + nj;
			
			if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
				//武汉理工大学辅导员登录			
				request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			}else{			
				request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
			}
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", Base.getZyMap().get(xydm));
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("result", flag);
			request.setAttribute("oper", "oper");
			request.setAttribute("xxdm", xxdm);
			request.setAttribute("rs", new CommanForm());
			request.setAttribute("mzList", dao.getMzList());
			request.setAttribute("zzmmList", dao.getZzmmList());
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				//杭州职业
				return mapping.findForward("stu_hzzy_family");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
				//武汉理工大学
				return mapping.findForward("stu_whlgdx_family");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
				//浙江商业职业技术学院
				return mapping.findForward("stu_zjsyzy_family");
			}
			return mapping.findForward("stufamily");
		}
		
		if ("showAddpage".equalsIgnoreCase(doType)) {
			HashMap<String, String> map = new HashMap<String, String>();
			String types = request.getParameter("type");
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) || xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//中国矿业大学
				return new ActionForward("/xsxx_zgkd.do?method=showStuFamily",false);
			}
			if ("update".equalsIgnoreCase(types)) {
				String curr_xh = request.getParameter("xh");
				String[] columns = dao.getColumnName("select * from view_xsjtxx");
				String curr_sql = "select * from view_xsjtxx where xh='"+ curr_xh + "'";
				String[] temp = new String[]{};
				temp = dao.getOneRs(curr_sql, new String[] {}, columns);
				for (int i = 0; i < temp.length; i++) {
					map.put(columns[i].toLowerCase(), temp[i]);
				}	
				if(xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){
					//南昌科技学院
					if(stuDao.isFdy(userName)){
						if(!stuDao.stuIsOwnFdy(xh, userName)){
							request.setAttribute("cWrite", "no");
						}
					}
				}
				
				request.setAttribute("rs", map);
				request.setAttribute("oper", "update");
				request.setAttribute("userOper", request.getParameter("userOper"));
			}
			if ("add".equalsIgnoreCase(types)) {
				String change = request.getParameter("oper");
				if ("".equalsIgnoreCase(change)) {
					request.setAttribute("rs", new CommanForm());
					request.setAttribute("oper", oper);
				} else {
					request.setAttribute("rs", studefamily);
					request.setAttribute("oper", "add");
				}
			}
			
			xydm = (xydm == null ? "" : xydm);
			zydm = (zydm == null ? "" : zydm);
			nj = (nj == null ? "" : nj);
			String bjKey = xydm + "!!" + zydm + "!!" + nj;
			
			if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
				//武汉理工大学辅导员登录			
				request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			}else{			
				request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
			}
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", Base.getZyMap().get(xydm));		
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("xxdm", xxdm);
			request.setAttribute("mzList", dao.getMzList());
			request.setAttribute("zzmmList", dao.getZzmmList());	
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				return mapping.findForward("stu_hzzy_family");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
				//武汉理工大学
				return mapping.findForward("stu_whlgdx_family");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
				//浙江商业职业技术学院
				return mapping.findForward("stu_zjsyzy_family");
			}
			return mapping.findForward("stufamily");
		}
		
		xydm = (xydm == null ? "" : xydm);
		zydm = (zydm == null ? "" : zydm);
		nj = (nj == null ? "" : nj);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			//辅导员登录只能查询到所带班级学生信息			
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
		}else{			
			request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		}
		//=========2011.1.26 lr=======
		if(rs == null || rs.size()<1){//无记录时，初始化总页数和总记录数
			studefamily.getPages().setMaxPage(0);
			studefamily.getPages().setMaxRecord(0);
		}
		//=========end=======
		request.setAttribute("pageSize",studefamily.getPages().getPageSize());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("ownWriteAble", writeAble);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("userOper", "yes");
		request.setAttribute("title", "学生信息 - 学生家庭信息");
		request.setAttribute("path", "modi_stu_famil.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("success");
	}
	/**
	 * 学生转档申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public static ActionForward xsZdSq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShgcForm arApply = (ShgcForm) form;
		String zdlb = request.getParameter("zdlb");
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName")==null?"":
			session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType")==null?"":
			session.getAttribute("userType").toString();
		String sql = "";
		DAO dao = DAO.getInstance();
		String[] vals = null;
		boolean flag = false;
		String zdlbcn = "";
		if("sx".equals(zdlb)){
			zdlbcn = "升学";
		}else if("zx".equals(zdlb)){
			zdlbcn = "转学";
		}else if("tx".equals(zdlb)){
			zdlbcn = "退学";
		}
		if("save".equals(doType)){
			sql = "delete from stu_archives_apply where xh=? and xxsh='未审核'";
			flag = dao.runUpdate(sql, new String[]{xh});
			if(flag){
				if("sx".equals(zdlb)){
					sql = "insert into stu_archives_apply(nd,xh,xxmc,zddwdz,zddwmc,zddwyb,zdlb) values(?,?,?,?,?,?,?)";
					vals = new String[]{Base.currNd,xh,arApply.getXxmc(),arApply.getZddwdz(),arApply.getZddwmc(),arApply.getZddwyb(),"升学"};
				}else if("zx".equals(zdlb)){
					sql = "insert into stu_archives_apply(nd,xh,xxmc,wjh,zddwdz,zddwmc,zddwyb,zdlb) values(?,?,?,?,?,?,?,?)";
					vals = new String[]{Base.currNd,xh,arApply.getXxmc(),arApply.getWjh(),arApply.getZddwdz(),arApply.getZddwmc(),arApply.getZddwyb(),"转学"};
				}else{
					sql = "insert into stu_archives_apply(nd,xh,wjh,zddwdz,zddwmc,zddwyb,zdlb) values(?,?,?,?,?,?,?)";
					vals = new String[]{Base.currNd,xh,arApply.getWjh(),arApply.getZddwdz(),arApply.getZddwmc(),arApply.getZddwyb(),"退学"};
				}
			}	
			request.setAttribute("result",dao.runUpdate(sql, vals));		
		}
		if("stu".equals(userType)){
			xh = userName;
		}
		sql = "select a.xh,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,b.xxmc,b.zddwdz,b.zddwmc,b.zddwyb,b.wjh from " +
				"view_xsjbxx a left join stu_archives_apply b on a.xh=b.xh and b.zdlb=? where a.xh=?";
		request.setAttribute("zdlb",zdlb);
		request.setAttribute("doType",doType);
		request.setAttribute("rs", dao.getMapNotOut(sql, new String[]{zdlbcn,xh}));
		return mapping.findForward("xszdsq");
	}
	
	public static ActionForward xsZdShAndQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		ShgcForm arApply = (ShgcForm) form;
		ArchiveService  service = new ArchiveService();
		HttpSession session = request.getSession();
		String doType = request.getParameter("doType");
		String shjg = request.getParameter("shjg");
		String pkStr = request.getParameter("pkStr");
		String userType = session.getAttribute("userType")==null?"":
			session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName")==null?"":
			session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep")==null?"":
			session.getAttribute("userDep").toString();
		String act = request.getParameter("act");
		String zdlb = request.getParameter("zdlb");
		String zdlbcn = "";
		StringBuffer sb = new StringBuffer(" ");
		String sql = "";
		String zcbh = "";
		if("stu".equals(userType)){
			doType="query";
			arApply.setXh(userName);
		}
		if("sx".equals(zdlb)){
			zdlbcn = "升学";
		}else if("zx".equals(zdlb)){
			zdlbcn = "转学";
		}else if("tx".equals(zdlb)){
			zdlbcn = "退学";
		}else if("by".equals(zdlb)){
			zdlbcn = "毕业";
		}
		boolean flag = false;
		if("xy".equals(userType)){
			arApply.setXydm(userDep);
		}
//		if("auditing".equals(act)){
//			sb.append(" and b.zdlb<>'毕业' ");
//		}
		if("auditing".equals(doType)){
			if("xx".equals(userType) || "admin".equals(userType)){
				if("通过".equals(shjg)){
					zcbh = service.getZcbh();
				}
				String[] pkValues = pkStr.split("!@!");
				String[] sqls = new String[pkValues.length-1];
				for(int i=1;i<pkValues.length;i++){
					sqls[i-1]="update stu_archives_apply set xxsh='"+shjg+"',zcbh='"+zcbh+"' where xh='"+pkValues[i]+"'";
					if("通过".equals(shjg)){
						zcbh = Integer.valueOf(zcbh)+1+"";
					}
				}
				int[] num = dao.runBatch(sqls);
				flag = dao.checkBatch(num);
				request.setAttribute("result", flag);
				doType = "query";
			}		
		}
		if("delete".equals(doType)){
			sql = "delete from  stu_archives_apply  where instr(?,'!@!'||xh||'!@!')>0";
			flag = dao.runUpdate(sql, new String[]{pkStr});
			request.setAttribute("result", flag);
			doType = "query";
		}
		if("query".equals(doType)){
			sb.append(Base.isNull(zdlbcn)?"":" and b.zdlb='"+zdlbcn+"' ");
			sb.append(Base.isNull(arApply.getNd())?"":" and b.nd='"+arApply.getNd()+"' ");
			sb.append(Base.isNull(arApply.getXydm())?"":" and a.xydm='"+arApply.getXydm()+"' ");
			sb.append(Base.isNull(arApply.getZydm())?"":" and a.zydm='"+arApply.getZydm()+"' ");
			sb.append(Base.isNull(arApply.getBjdm())?"":" and a.bjdm='"+arApply.getBjdm()+"' ");
			sb.append(Base.isNull(arApply.getXxsh())?"":" and b.xxsh='"+arApply.getXxsh()+"' ");
			sb.append(Base.isNull(arApply.getXh())?"":" and a.xh like '%"+arApply.getXh().replace("'", "")+"%' ");
			sb.append(Base.isNull(arApply.getXm())?"":" and a.xm like '%"+arApply.getXm().replace("'", "")+"%' ");
			sql = "select count(*) count from view_xsjbxx a,stu_archives_apply b where a.xh=b.xh "+sb;
			arApply.getPages().setMaxRecord(Integer.valueOf(dao.getOneRs(sql, new String[]{}, "count")));
			sql = "select * from (select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,b.zddwmc,b.zddwdz,b.zdlb,b.xxsh,b.zcbh,rownum r " +
					"from view_xsjbxx a,stu_archives_apply b where a.xh=b.xh "+sb+" order by a.xydm,a.zydm,a.bjdm) where r<="
				+ (arApply.getPages().getStart() + arApply.getPages().getPageSize())+ " and r>"
				+ arApply.getPages().getStart();
			List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{});
			String[] en = new String[]{"xh","xm","xymc","zymc","bjmc","zddwmc","zddwdz","zdlb","xxsh","zcbh"};
			String[] cn = new String[]{"学号","姓名",Base.YXPZXY_KEY+"名称","专业名称","班级名称","转档单位名称","转档单位地址","转档类别","学校审核","转出编号"};
			request.setAttribute("rs", list);
			request.setAttribute("topTr", dao.arrayToList(en, cn));
		}
		request.setAttribute("act", act);
		request.setAttribute("zdlb", zdlb);
		request.setAttribute("tableName", "stu_archives_apply");
		request.setAttribute("realTable", "stu_archives_apply");
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(arApply.getXydm()));
		request.setAttribute("bjList", dao.getBjList(arApply.getXydm(), arApply.getZydm()));
		request.setAttribute("ndList", dao.getNdList());
		return mapping.findForward("xszdshAndQuery");
	}
	
	/**
	 *Method distributeExaPage 转档申请审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * **/
	@SuppressWarnings("deprecation")
	public static ActionForward archivesDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		ShgcForm arApply = (ShgcForm) form;
		HttpSession session = request.getSession();
		String doType = request.getParameter("doType");
		String pkStr = request.getParameter("pkStr");
		String userType = session.getAttribute("userType")==null?"":
			session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep")==null?"":
			session.getAttribute("userDep").toString();
		String zdlb = request.getParameter("zdlb");
		StringBuffer sb = new StringBuffer(" and xxsh='通过'" );
		HashMap<String,String> map = new HashMap<String,String>();
		String sql = "";
		boolean flag = false;
		if("xy".equals(userType)){
			arApply.setXydm(userDep);
		}
		if("delete".equals(doType)){
			sql = "delete from  stu_archives_apply  where instr(?,'!@!'||xh||'!@!')>0";
			flag = dao.runUpdate(sql, new String[]{pkStr});
			request.setAttribute("result", flag);
			doType = "query";
		}
		if("save".equals(doType)){
			String dir = "/upload/hz";
			String filePath = "";
			File f = new File(dir);
		    if (!f.exists()) {
			   f.mkdirs();
		    }
		    if(arApply.getHztp()!=null && arApply.getHztp().indexOf("/upload/hz")!=0){
		    	FormFile file = arApply.getUploadFile();
			    if (file != null) {
					int size = file.getFileSize();
					if (size < 10485760) {
						String fName = arApply.getXh() + file.getFileName();
						InputStream in = file.getInputStream();
						filePath = dir + "/" + fName;
						OutputStream out = new FileOutputStream(filePath);
						int bytesRead = 0;
						byte[] buffer = new byte[size];
						while ((bytesRead = in.read(buffer, 0, size)) != -1) {
							out.write(buffer, 0, bytesRead);
						}
						out.close();
						in.close();
					}
				}
		    }else{
		    	filePath = arApply.getHztp();
		    }
			sql = "update stu_archives_apply set zddwdz=?,zddwmc=?,zddwyb=?,xxmc=?,wjh=?, dazcsj=?,dazcfs=?,jyh=?,sfyhz=?,hztp=? where xh=?";
			flag = dao.runUpdate(sql, new String[]{arApply.getZddwdz(),arApply.getZddwmc(),arApply.getZddwyb(),arApply.getXxmc()==null?"":
					arApply.getXxmc(),arApply.getWjh()==null?"":arApply.getWjh(),arApply.getDazcsj(),arApply.getDazcfs(),
					arApply.getJyh(),arApply.getSfyhz(),filePath,arApply.getXh()});
			request.setAttribute("result", flag);
			doType = "update";
			pkStr = arApply.getXh();
		}
		if("update".equals(doType)||"view".equals(doType)){
			sql = "select a.xh,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,b.xxmc,b.zddwdz,b.zddwmc,b.zddwyb,b.wjh," +
					"b.dazcsj,b.dazcfs,b.jyh,b.sfyhz,b.hztp,b.zdlb from view_xsjbxx a,stu_archives_apply b where a.xh=b.xh and a.xh=?";
			map = dao.getMapNotOut(sql, new String[]{pkStr});
			request.setAttribute("rs", map);
			request.setAttribute("doType", doType);
			return mapping.findForward("editZdxx");
		}
		if("viewhztp".equals(doType)){
			byte b[] = new byte[500];
			String hztp = DealString.toGBK(request.getParameter("hztp"));
			String filename = hztp.substring(hztp.lastIndexOf("/")+1,hztp.length());
			File fileload = new File(request.getRealPath("").substring(0,2)+hztp);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ DealString.toUtf8String(filename));
			InputStream in = new FileInputStream(fileload);
			in = new BufferedInputStream(in);
			while ((in.read(b)) != -1) {
				response.getOutputStream().write(b);
			}
			return null;
		}
		
		//查询
		String[] en = new String[]{"xh","xm","xymc","zymc","bjmc","zddwmc","zddwdz","zdlb","xxsh","zcbh"};
		String[] cn = new String[]{"学号","姓名",Base.YXPZXY_KEY+"名称","专业名称","班级名称","转档单位名称","转档单位地址","转档类别","学校审核","转出编号"};
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();	
		if("query".equals(doType)){
			
			// 高级查询
			SearchService searchService = new SearchService();

			String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
			String searchTj = SearchService.getSearchTj(arApply.getSearchModel());
			String[] inputV = SearchService.getTjInput(arApply.getSearchModel());

			request.setAttribute("searchTj", arApply.getSearchModel());
			
			sb.append(Base.isNull(zdlb)?"":" and b.zdlb='"+zdlb+"' ");
			sb.append(Base.isNull(arApply.getNd())?"":" and b.nd='"+arApply.getNd()+"' ");
			sb.append(Base.isNull(arApply.getXydm())?"":" and a.xydm='"+arApply.getXydm()+"' ");
			sb.append(Base.isNull(arApply.getZydm())?"":" and a.zydm='"+arApply.getZydm()+"' ");
			sb.append(Base.isNull(arApply.getBjdm())?"":" and a.bjdm='"+arApply.getBjdm()+"' ");
			sb.append(Base.isNull(arApply.getXh())?"":" and a.xh like '%"+arApply.getXh().replace("'", "")+"%' ");
			sb.append(Base.isNull(arApply.getXm())?"":" and a.xm like '%"+arApply.getXm().replace("'", "")+"%' ");
			
			sql = "select count(*) count from (select a.xh,a.xm,a.xydm,a.zydm,a.bjdm,b.nd,b.zdlb "
					+ " from view_xsjbxx a,stu_archives_apply b where a.xh=b.xh "
					+ sb + ") where 1 = 1" + searchTj;
			arApply.getPages().setMaxRecord(
					Integer.valueOf(dao.getOneRs(sql, inputV, "count")));
			sql = "select * from (select * from (select b.nd,a.xh,a.xm,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,b.zddwmc,b.zddwdz,b.zdlb,b.xxsh,b.zcbh,rownum r "
					+ "from view_xsjbxx a,stu_archives_apply b where a.xh=b.xh "
					+ sb
					+ " order by a.xydm,a.zydm,a.bjdm) a where 1 = 1 "
					+ searchTj
					+ searchTjByUser
					+ ") where r<="
					+ (arApply.getPages().getStart() + arApply.getPages()
							.getPageSize())
					+ " and r>"
					+ arApply.getPages().getStart();
			list = dao.getListNotOut(sql, inputV);
			
		}
		//=========2011.1.26 lr=======
		if(list == null || list.size()<1){//无记录时，初始化总页数和总记录数
			arApply.getPages().setMaxPage(0);
			arApply.getPages().setMaxRecord(0);
		}
		//=========end=======
		if(arApply.getNd()==null){
			arApply.setNd(Base.currNd);
		}		
		request.setAttribute("path", "archives_deal.do");	
		request.setAttribute("rs", list);
		request.setAttribute("rsNum", list != null ? list.size() : 0);
		request.setAttribute("topTr", dao.arrayToList(en, cn));
		request.setAttribute("pageSize", arApply.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);//学院专业班级年级列表
		request.setAttribute("ndList", Base.getXnndList());
		return mapping.findForward("success");
	}
	
	/**
	 *Method distributeExaPage 转档申请审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * **/
	@SuppressWarnings("deprecation")
	public static ActionForward archivesPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xxdm = request.getSession().getAttribute("xxdm").toString();
		String doType = request.getParameter("doType");
		String pkStr = request.getParameter("pkStr");
		String zdlb = request.getParameter("zdlb");
		String wh = request.getParameter("wh");
		String xm = request.getParameter("djr");
		DAO dao = DAO.getInstance();
		String xxmc = dao.getOneRs("select xxmc from dmk_xx where xxdm=?", new String[] {xxdm}, "xxmc");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);

		String sql = "select a.xh,a.xm,b.zcbh,b.zddwmc,a.nj from view_xsjbxx a,stu_archives_apply b where a.xh=b.xh and xxsh='通过' and instr(?,'!@!'||a.xh||'!@!')>0";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pkStr});
		String nd = Base.currNd;
		HashMap<String,String> map = new HashMap<String,String>();
		String time = GetTime.getNowTime();
		if("dajdd".equals(doType)){
			WritableSheet ws = wwb.createSheet("档案寄递单", 0);
			for(int i=0;i<list.size();i++){
				map = list.get(i);
				ws.mergeCells(0, i*55+0, 8, i*55+1);
				
				ws.mergeCells(0, i*55+3, 8, i*55+3);
				mb.printToOneCell_multy(ws, "编号："+map.get("zcbh"), 0, i*55+3, 12, false,
						Alignment.RIGHT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+5, 8, i*55+5);
				if("转学".equals(zdlb) || "退学".equals(zdlb)){
					mb.printToOneCell_multy(ws,xxmc+"寄"+map.get("nj")+"级"+zdlb+"生档案存根单", 0, i*55+0, 16, true, Alignment.CENTRE,
							VerticalAlignment.CENTRE, true, 1000, Border.NONE);
					mb.printToOneCell_multy(ws, "    "+map.get("nj")+"级"+zdlb+"生：", 0, i*55+5, 12, false,
							Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
					mb.printToOneCell_multy(ws,xxmc+"寄"+map.get("nj")+"级"+zdlb+"生档案通知书", 0, i*55+15, 16, true, Alignment.CENTRE,
							VerticalAlignment.CENTRE, true, 1000, Border.NONE);
					mb.printToOneCell_multy(ws, "        现寄去"+map.get("nj")+"级"+zdlb+"生：", 0, i*55+22, 12, false,
							Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
					mb.printToOneCell_multy(ws,xxmc+"寄"+map.get("nj")+"级"+zdlb+"生档案回执", 0, i*55+36, 16, true, Alignment.CENTRE,
							VerticalAlignment.CENTRE, true, 1000, Border.NONE);
					mb.printToOneCell_multy(ws, "        已收到"+map.get("nj")+"级"+zdlb+"生：", 0, i*55+45, 12, false,
							Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
				}else{
					mb.printToOneCell_multy(ws,xxmc+"寄"+nd+"届毕业学生档案存根单", 0, i*55+0, 16, true, Alignment.CENTRE,
							VerticalAlignment.CENTRE, true, 1000, Border.NONE);
					mb.printToOneCell_multy(ws, "    "+nd+"届毕业生：", 0, i*55+5, 12, false,
							Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
					mb.printToOneCell_multy(ws,xxmc+"寄"+nd+"届毕业学生档案通知书", 0, i*55+15, 16, true, Alignment.CENTRE,
							VerticalAlignment.CENTRE, true, 1000, Border.NONE);
					mb.printToOneCell_multy(ws, "        现寄去"+nd+"届毕业生：", 0, i*55+22, 12, false,
							Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
					mb.printToOneCell_multy(ws,xxmc+"寄"+nd+"届毕业学生档案回执", 0, i*55+36, 16, true, Alignment.CENTRE,
							VerticalAlignment.CENTRE, true, 1000, Border.NONE);
					mb.printToOneCell_multy(ws, "        已收到"+nd+"届毕业生：", 0, i*55+45, 12, false,
							Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
				}
				
				ws.mergeCells(0, i*55+8, 8, i*55+8);
				mb.printToOneCell_multy(ws, "姓名："+map.get("xm")+
						"                    "+"学号："+map.get("xh"), 0, i*55+8, 12, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+10, 8, i*55+10);
				mb.printToOneCell_multy(ws, "    "+"寄往："+map.get("zddwmc")+
						"                                                     "+time, 0, i*55+10, 12, false,
						Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+15, 8, i*55+16);
				
				ws.mergeCells(0, i*55+18, 8, i*55+18);
				mb.printToOneCell_multy(ws, "编号："+map.get("zcbh")+"    ", 0, i*55+18, 12, false,
						Alignment.RIGHT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+20, 8, i*55+20);
				mb.printToOneCell_multy(ws, "    "+map.get("zddwmc")+"：", 0, i*55+20, 12, false,
						Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+22, 8, i*55+22);
				
				ws.mergeCells(0, i*55+25, 8, i*55+25);
				mb.printToOneCell_multy(ws, "姓名："+map.get("xm")+
						"                    "+"学号："+map.get("xh"), 0, i*55+25, 12, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+27, 8, i*55+27);
				mb.printToOneCell_multy(ws, "    计  壹  人学生档案，请查收，并请从速寄回“回执”，谢谢。", 0, i*55+27, 12, false,
						Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+29, 8, i*55+29);
				mb.printToOneCell_multy(ws, xxmc+"就业指导中心", 0, i*55+29, 12, false,
						Alignment.RIGHT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+31, 8, i*55+31);
				mb.printToOneCell_multy(ws, time, 0, i*55+31, 12, false,
						Alignment.RIGHT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+36, 8, i*55+37);
				
				ws.mergeCells(0, i*55+39, 8, i*55+39);
				mb.printToOneCell_multy(ws, "编号："+map.get("zcbh")+"    ", 0, i*55+39, 12, false,
						Alignment.RIGHT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+41, 8, i*55+41);
				mb.printToOneCell_multy(ws, "    "+xxmc+"学生工作部：", 0, i*55+41, 12, false,
						Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+45, 8, i*55+45);
				
				ws.mergeCells(0, i*55+48, 8, i*55+48);
				mb.printToOneCell_multy(ws, "姓名："+map.get("xm")+
						"                    "+"学号："+map.get("xh"), 0, i*55+48, 12, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+50, 8, i*55+50);
				mb.printToOneCell_multy(ws, "    计 壹 人学生档案。", 0, i*55+50, 12, false,
						Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+52, 8, i*55+52);
				mb.printToOneCell_multy(ws, "（单位盖章）", 0, i*55+52, 12, false,
						Alignment.RIGHT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*55+54, 8, i*55+54);
				mb.printToOneCell_multy(ws, "       年   月   日", 0, i*55+54, 12, false,
						Alignment.RIGHT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
			
			}		
		}else if ("dajdxf".equals(doType)){
			WritableSheet ws = wwb.createSheet("档案寄递信封", 0);
			int j=0;
			for(int i=0;i<list.size();){
				map = list.get(i);
				ws.mergeCells(0, i*3+j+0, 3,i*3+j+0);
				mb.printToOneCell_multy(ws, "档案代码："+map.get("zcbh"), 0, i*3+j+0, 12, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*3+j+2, 3,i*3+j+2);
				mb.printToOneCell_multy(ws, "  "+map.get("zddwmc"), 0, i*3+j+2, 14, true,
						Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				ws.mergeCells(0, i*3+j+4, 3,i*3+j+4);
				mb.printToOneCell_multy(ws, "  姓名："+map.get("xm")+"       "+"学号："+map.get("xh"), 0,i*3+j+4, 11, false,
						Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
						Border.NONE);
				if(i+1 <list.size()){
					map = list.get(i+1);
					ws.mergeCells(5, i*3+j+0, 8,i*3+j+0);
					mb.printToOneCell_multy(ws, "档案代码："+map.get("zcbh"), 5, i*3+j+0, 12, false,
							Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
					ws.mergeCells(5, i*3+j+2, 8,i*3+j+2);
					mb.printToOneCell_multy(ws, "  "+map.get("zddwmc"), 5, i*3+j+2, 14, true,
							Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
					ws.mergeCells(5, i*3+j+4, 8,i*3+j+4);
					mb.printToOneCell_multy(ws, "  姓名："+map.get("xm")+"       "+"学号："+map.get("xh"), 5, i*3+j+4, 11, false,
							Alignment.LEFT, VerticalAlignment.CENTRE, false, 300,
							Border.NONE);
				}
				i = i+2;
				if((i+2)%18==0){
					j++;
				}
			}
		}else if ("jyjdd".equals(doType)){
			int num = list.size();
			int pagesize = 0;
			String modelPath = request.getRealPath("")+"/print/jdda.xls";
			File file = new File(modelPath);
			WritableWorkbook wwb1 = ExcelMethods.getWritableWorkbook(file, response.getOutputStream());
			WritableSheet ws = wwb1.getSheet(0);
			WritableCell cell1 = ws.getWritableCell(0, 0);
			CellFormat cft1 = cell1.getCellFormat();
//			WritableCell cell2 = ws.getWritableCell(0, 1);
//			CellFormat cft2 = cell2.getCellFormat();
			WritableCell cell3 = ws.getWritableCell(0, 2);
			CellFormat cft3 = cell3.getCellFormat();
			WritableCell cell4 = ws.getWritableCell(1, 2);
			CellFormat cft4 = cell4.getCellFormat();
			
			ws.mergeCells(0, 0, 9,0);
			ws.addCell(new Label(0,0,"机要邮件交寄单",cft1));
			int j = 1;
			for(int i=0;i<list.size();i++){
				map = list.get(i);
				if(j==21){
					j = 1;
				}
				if(i%20==0){
					ws.mergeCells(0, 0+(i/20)*29, 9,0+(i/20)*29);
					ws.addCell(new Label(0,0+(i/20)*29,"机要邮件交寄单",cft1));
					ws.mergeCells(0, 1+(i/20)*29, 9,1+(i/20)*29);
					ws.addCell(new Label(0,1+(i/20)*29,"寄件单位:"+xxmc+"                    " +
							"                              寄递证号：6208  ",cft4));
					ws.mergeCells(0, 2+(i/20)*29, 9,2+(i/20)*29);
					ws.addCell(new Label(0,2+(i/20)*29,"发出日期:"+time+"            " +
							"                                第"+wh+"号  第 "+(i/20+1)+" 页  ",cft4));
					ws.mergeCells(0, 3+(i/20)*29, 0,4+(i/20)*29);
					ws.addCell(new Label(0,3+(i/20)*29,"格数",cft3));
					ws.mergeCells(1, 3+(i/20)*29, 1,4+(i/20)*29);
					ws.addCell(new Label(1,3+(i/20)*29,"收件单位名称",cft3));
					ws.mergeCells(2, 3+(i/20)*29, 2,4+(i/20)*29);
					ws.addCell(new Label(2,3+(i/20)*29,"单位自编号码",cft3));
					ws.mergeCells(3, 3+(i/20)*29, 5,3+(i/20)*29);
					ws.addCell(new Label(3,3+(i/20)*29,"件数",cft3));
					ws.addCell(new Label(3,4+(i/20)*29,"绝",cft3));
					ws.addCell(new Label(4,4+(i/20)*29,"机",cft3));
					ws.addCell(new Label(5,4+(i/20)*29,"秘",cft3));
					
					ws.mergeCells(6, 3+(i/20)*29, 6,4+(i/20)*29);
					ws.addCell(new Label(6,3+(i/20)*29,"形状",cft3));
					
					ws.mergeCells(7, 3+(i/20)*29, 7,4+(i/20)*29);
					ws.addCell(new Label(7,3+(i/20)*29,"机要通信编号",cft3));
					
					ws.mergeCells(8, 3+(i/20)*29, 9,3+(i/20)*29);
					ws.addCell(new Label(8,3+(i/20)*29,"备   注",cft3));
					ws.addCell(new Label(8,4+(i/20)*29,"学号",cft3));
					ws.addCell(new Label(9,4+(i/20)*29,"姓名",cft3));
					ws.mergeCells(0, 25+(i/20)*29, 4,25+(i/20)*29);
					if(num-i<=20){
						pagesize = num - i;
					}else{
						pagesize = 20;
					}
					ws.addCell(new Label(0,25+(i/20)*29,"本页合计　  "+pagesize+"　  件",cft4));
					ws.mergeCells(0, 26+(i/20)*29, 4,26+(i/20)*29);
					ws.addCell(new Label(0,26+(i/20)*29,"本号单合计    "+((num-1)/20+1)+"   页   "+num+"  件",cft4));
					ws.mergeCells(0, 27+(i/20)*29, 4,27+(i/20)*29);
					ws.addCell(new Label(0,27+(i/20)*29,"贴票文件资费￥:           元",cft4));
					ws.mergeCells(0, 28+(i/20)*29, 9,28+(i/20)*29);
					ws.addCell(new Label(0,28+(i/20)*29,"登记人："+xm+"                                      " +
							"         送件人："+xm,cft4));
					ws.mergeCells(5, 25+(i/20)*29, 9,27+(i/20)*29);
					ws.addCell(new Label(5,25+(i/20)*29,"接收人章        接收日戳",cft4));
				}
				ws.addCell(new Label(0, 5+i+(i/20)*10,j+"",cft3));
				ws.addCell(new Label(1, 5+i+(i/20)*10,map.get("zddwmc"),cft3));
				ws.addCell(new Label(2, 5+i+(i/20)*10,map.get("zcbh"),cft3));
				ws.addCell(new Label(4, 5+i+(i/20)*10,"1",cft3));
				ws.addCell(new Label(6, 5+i+(i/20)*10,"信",cft3));
				ws.addCell(new Label(8, 5+i+(i/20)*10,map.get("xh"),cft3));
				ws.addCell(new Label(9, 5+i+(i/20)*10,map.get("xm"),cft3));
				j++;			
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb1);
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return mapping.findForward("");
	}
}
