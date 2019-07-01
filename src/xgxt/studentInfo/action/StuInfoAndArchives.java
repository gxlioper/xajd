package xgxt.studentInfo.action;

import java.sql.SQLException;
import java.util.ArrayList;
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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.daoActionLogic.StuInfoActionLogic;
import xgxt.form.ShgcForm;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.ArchiveService;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.studentInfo.zgkd.XsxxZgkdService;
import xgxt.utils.CheckPower;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import common.Globals;

public class StuInfoAndArchives extends Action {
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String actParameter = mapping.getParameter();
		ActionForward acForward = new ActionForward();
		StuInfoActionLogic stuAction = new StuInfoActionLogic();
		try {
			String act=request.getParameter("act");
			String xxdm = StandardOperation.getXxdm();
		  	int i = Base.chkTimeOut(request.getSession());
		  	if (i <= 2) {
		  	  request.setAttribute("errMsg", "登陆超时，请重新登陆！");
		  	  return new ActionForward("/login.jsp", false);
		  	  }
			if("stu_archives_history".equalsIgnoreCase(actParameter)){//历届学生档案
				if("ljs_archive".equalsIgnoreCase(act)){
					 acForward=stuHistoryArchives(mapping, form, request, response);//历届学生档案查询
				}else{
					acForward = stuAction.stuArchives(mapping,form,request,response);//历届学生档案维护
				}
			}else if("stu_archives".equalsIgnoreCase(actParameter)){//在校生档案
				acForward=stuArchives(mapping, form, request, response);
			}else if("archivesApply".equalsIgnoreCase(actParameter)){//历届学生转档申请
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SJXY)) {
					acForward=StuInfoActionLogic.xsZdSq(mapping, form, request, response);
				}else{
					acForward=StuInfoActionLogic.stuArchivesApply(mapping, form, request, response);
				}						
			}else if("archivesApplyData".equalsIgnoreCase(actParameter)){//历届学生档案管理查询
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SJXY)) {
					acForward=StuInfoActionLogic.xsZdShAndQuery(mapping, form, request, response);
				}else{
					acForward=StuInfoActionLogic.ArchivesApplyManage(mapping, form, request, response);
				}
				
			}else if("archivesDeal".equalsIgnoreCase(actParameter)){//历届学生档案管理查询
				acForward=StuInfoActionLogic.archivesDeal(mapping, form, request, response);
			}else if("archivesPrint".equalsIgnoreCase(actParameter)){//历届学生档案管理查询
				acForward=StuInfoActionLogic.archivesPrint(mapping, form, request, response);
			}
			FormModleCommon.commonRequestSet(request);
			return acForward;
		}catch(Exception ex){
			ex.printStackTrace();		
			return mapping.findForward("failure");
		}
	}
	
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
		//String writeAble = Base.getWriteAble(request);
		
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
	 * 历届生档案管理
	 * */ 
	public static ActionForward stuHistoryArchives(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		DAO dao = DAO.getInstance();
		XsxxglService xsxxglService = new XsxxglService();
		StudentInfoService service = new StudentInfoService();
		ShgcForm historystu = (ShgcForm) form;
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userSpecType = session.getAttribute("userType").toString();		
		String writeAble = Base.getWriteAble(request); 
		writeAble=CheckPower.checkUsrPageAccessPower(userOnLine, session.getAttribute("userName").toString(), "stu_archives_history.do")?"yes":"no";
		String xydm = historystu.getXydm();
		String zydm = historystu.getZydm();
		String nj = historystu.getNj();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String result = "result";
		String sql = "";
		
		sql = "select * from view_stu_his_archives a where a.xh=?";
		if (userOnLine.equalsIgnoreCase("student")) {
			String userName = session.getAttribute("userName").toString();
			xh = userName;
			result = dao.getOneRs(sql, new String[] {userName}, "xh");
		}
		if (result.equals("") || result == null) {
			request.setAttribute("msg", "本页面只有历届生可以访问");
		} else {
			String[] columns = {"xh","xm","xb","nj","xydm","xymc","zymc","bjmc",
					            "xz","jg","lxdh","mzmc","sfzh","csrq","bynf","daly",
					            "dasfzx","dkjl","dxyysp","gkcj","gkyycj","hzh","jsjsp",
					            "snbjpm","zddwdz","zddwmc","zddwyb","zdfs","zdyy","zysj",
					            "zzmm","jyh", "daclr", "daclrlxfs","dwxz","xl","bz",
					            "zlsfqq","sfydycl","jyh","dabh","sjr","sjjgmc","sjsj","daqx"};
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
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {// 中国美术学院
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xz,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.zddwmc,a.zddwdz," +
					  "a.zysj,a.dasfzx,a.gkyycj,a.dxyysp,a.dkjl,a.snbjpm,a.gkcj,a.jsjsp,a.zdfs," +
					  "(select b.bynd from jygl_xsjbxxb b where a.xh = b.xsxh) bynf,a.hzh,a.zdyy,a.zddwyb,a.zdfs," +
					  "a.daly,(select b.lxdh from stu_history_lab b where a.xh = b.xh) lxdh," +
					  "(select b.id from jygl_xsjbxxb b where a.xh = b.xsxh) sfzh,a.csrq,a.mz,a.mzmc,a.jg " +
					  "from view_stu_his_archives a where a.xh=?";
			}
			map.putAll(dao.getMap(sql, new String[]{xh}, columns));
			if(map == null || map.size()<=0){//无转档记录
				map.putAll(service.getStuInfo(xh));
			}
			String lxdh = map.get("lxdh");//修改,查看  取档案表电话
			map.putAll(xsxxglService.selectStuinfo(xh));
			map.put("lxdh", lxdh);
		}
		request.setAttribute("njList", dao.getNjHistoryList());
		request.setAttribute("xyList", dao.getXyHistoryList());
		request.setAttribute("zyList", dao.getZyHistoryList(xydm));
		request.setAttribute("bjList", dao.getBjHistoryList(xydm, zydm, nj));
		request.setAttribute("rs", map);
		request.setAttribute("oper", doType);
		request.setAttribute("user", userOnLine);
		request.setAttribute("add", "update");
		request.setAttribute("userType", userSpecType);
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("view", request.getParameter("view"));
		return new ActionForward("/shgc/stu_info/stu_archives.jsp");
	}
	
	/**
	 * 在校生档案管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * */ 
	public ActionForward stuArchives(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		ArchiveService service = new ArchiveService();
		boolean flag = false;
		ShgcForm archForm = (ShgcForm) form;
		
		String doType = request.getParameter("doType");		 
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userSpecType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();		
		
		String bjdm = archForm.getBjdm();
		String xydm = archForm.getXydm();		
		String zydm = archForm.getZydm();
		String nj = DealString.toGBK(archForm.getNj());
		String xm = DealString.toGBK(archForm.getXm());
		String xh = archForm.getXh();
		String go = request.getParameter("go");
		request.setAttribute("dadjList", service.dadjCf(xh));
		if(userSpecType.equalsIgnoreCase("xy") && (xydm==null || xydm.equalsIgnoreCase(""))){
			xydm=userDep;
			archForm.setXydm(xydm);
		}
		
		
		if ("add".equalsIgnoreCase(doType)) {
			archForm.setXb("");
			request.setAttribute("rs", archForm);
			request.setAttribute("oper", request.getParameter("type"));
			appendProperties(request, xydm, zydm, nj);
			
			if(StringUtils.isNotNull(xh)){
				String sql = "select * from view_xsxxb where xh=?";
				Map<String, String> rs = dao.getMapNotOut(sql, new String[]{xh});
				request.setAttribute("rs", rs);
			}
			
			return new ActionForward("/shgc/stu_info/stu_archives_info.jsp");
		}
		if ("update".equalsIgnoreCase(doType)) {
			HashMap<String, String> map = new HashMap<String, String>();
			xh = request.getParameter("xh");
			
			String sql = "select * from view_xsjbxx where xh='" + xh + "'";
			String result = dao.getOneRs(sql, new String[] {}, "xh");
			request.setAttribute("dadj", service.dadjCf(xh));
			if (result.equals("") && result == null) {
				request.setAttribute("message", "本页面只有在校学生可以访问");
			} else {
				sql = "select * from view_stu_archives where xh=?";
				String[] columns = { "xh", "xm", "xb", "sfzh", "lxdh", "nj",
									"bjdm", "bjmc", "zydm", "zymc", "xydm", "xymc",
									"zddwmc", "zddwdz", "zysj", "gzdasfzx", "zdyy",
									"zddwyb", "jsr", "jsnr", "glr", "dabcwz", "dah", 
									"sfydycl", "bz", "daly","zdqk","xl","zlsfqq",
									"dayjr","jyh","xndabh","djr","jssj","sjr"};
				String rec = dao.getStringToSplit(sql, new String[] { xh },columns);
				String[] tmp = rec.split("!!SplitSignOne!!");
				String[] recode = null;
				recode = (tmp.length != 2) ? new String[columns.length + 1]: tmp[1].split("!!SplitSignTwo!!");
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i].toLowerCase(), recode[i + 1]);
				}
				
				map.putAll(dao.getMap("select xz,mzmc mz,csrq,zzmmmc,byny from view_xsxxb where xh=?", new String[]{xh}, new String[]{"mz", "csrq", "zzmmmc","xz","byny"}));
				map.put("zdsj", map.get("zysj"));
			}
			request.setAttribute("rs", map);
			request.setAttribute("oper", doType);
			request.setAttribute("user", userOnLine);
			request.setAttribute("view", request.getParameter("view"));
			appendProperties(request, xydm, zydm, nj);			
			return new ActionForward("/shgc/stu_info/stu_archives_info.jsp");
		}
		if ("saveArchives".equalsIgnoreCase(doType)) {
			
			flag = service.saveArchive(archForm,request);//加载页面属性
			request.setAttribute("result", flag);
			request.setAttribute("rs", new ShgcForm());
			request.setAttribute("oper", "oper");
			appendProperties(request, xydm, zydm, nj);
			return mapping.findForward("page");
		}
		if ("del".equalsIgnoreCase(doType)) {
			String[] val = request.getParameterValues("pk");
			for(int i=0 ;i<val.length; i++){
				flag = StandardOperation.delete("stu_archives", "xh", val[i],request);;
			}
			archForm.setXh("");
			request.setAttribute("result", flag);
		}
		if(userOnLine.equalsIgnoreCase("student")){
			xh = userName;
			go = "go";
		}
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String[] title_en = {"r", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "zddwdz", "zddwmc", "zysj", "zdyy" };
		if(Globals.XXDM_FJGCXY.equalsIgnoreCase(Base.xxdm)){
			title_en = new String[]{"r", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "zddwmc","zddwdz", "jyh","zysj", "zdyy" };
		}
		if (Base.xxdm.equalsIgnoreCase(Globals.XXDM_XYSFXY)) {
			title_en = new String []{"r", "xh", "xm", "nj", "bjmc", "zymc", "xymc" };
		}
		String[] title_cn = dao.getColumnNameCN(title_en,"stu_archives");
		for (int i = 0; i < title_en.length; i++) {
			HashMap<String, String> temmap = new HashMap<String, String>();
			temmap.put("cn", title_cn[i]);
			topTr.add(temmap);
		}
		if (go != null&& go.equalsIgnoreCase("go")) {
			
			//模糊查询
			StringBuffer sql = new StringBuffer("select rownum  r,a.* from view_stu_archives a where 1=1 ");
			sql.append(xh == null || xh.trim().length() < 1 ? "": " and xh like '%" + DealString.replaceImmitStr(xh.trim()) + "%'");
			sql.append(xm == null || xm.trim().length() < 1 ? "": " and xm like '%" + DealString.replaceImmitStr(xm.trim()) + "%'");
			sql.append(nj == null || nj.trim().length() < 1 ? "": " and nj = '" + nj.trim() + "'");
			sql.append(bjdm == null || bjdm.trim().length() < 1 ? "": " and bjdm= '" + bjdm + "'");
			sql.append(zydm == null || zydm.trim().length() < 1 ? "": " and zydm = '" + zydm + "'");
			sql.append(xydm == null || xydm.trim().length() < 1 ? "": " and xydm = '" + xydm + "'");
			if(StringUtils.isNotNull(archForm.getZddwmc())){
				sql.append(" and zddwmc like '%");
				sql.append(DealString.replaceImmitStr(archForm.getZddwmc()));
				sql.append("%' ");
			}
			if(StringUtils.isNotNull(archForm.getJyh())){
				sql.append(" and jyh like '%");
				sql.append(DealString.replaceImmitStr(archForm.getJyh()));
				sql.append("%' ");
			}
				
			//高级查询
			SearchService searchService = new SearchService();		
			
			String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");

			String searchTj = SearchService.getSearchTj(archForm.getSearchModel());
			String[] inputV = SearchService.getTjInput(archForm.getSearchModel());
			sql.append(searchTj);
			sql.append(searchTjByUser);
			
			request.setAttribute("searchTj", archForm.getSearchModel());
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from (select * from (");
			sb.append(sql);
			sb.append(") where r<=");
			sb.append(archForm.getPages().getStart()+archForm.getPages().getPageSize());
			sb.append(") where r>");
			sb.append(archForm.getPages().getStart());
			rs = dao.rsToVator(sb.toString(),inputV, title_en);
				
			archForm.setXm(xm);
			request.setAttribute("uer", "teacher");
			archForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from ("+sql.toString()+")", inputV, "count")));
		}
		
		//=========2011.1.26 lr=======
		if(rs == null || rs.size()<1){//无记录时，初始化总页数和总记录数
			archForm.getPages().setMaxPage(0);
			archForm.getPages().setMaxRecord(0);
		}
		//=========end=======
		List zlList = new ArrayList<HashMap<String,String>>();
		String sql = "select distinct gdzldm,zlbmc gdzlmc from stu_gdzlb";
		zlList = dao.getList(sql, new String[] {}, new String[] {
				"gdzldm", "gdzlmc" });
		request.setAttribute("zlList", zlList);
		request.setAttribute("pageSize", archForm.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs == null ? 0 : rs.size());		
		appendProperties(request, xydm, zydm, nj);//加载页面属性
		request.setAttribute("view", request.getParameter("view"));
		return mapping.findForward("success");
	}
}