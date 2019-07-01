/*
 * 创建日期 2007-6-15 ls_zzj
 *
 */
package xgxt.sxjy.action;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.CLOB;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SxjyForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.RowidToPk;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import common.Globals;


public class SxPlanAction extends Action {

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {	
		ActionForward myActFwd = null;
		String myAct = mapping.getParameter();

		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			if ("showLists".equalsIgnoreCase(myAct)) {
				// 列表显示
				myActFwd = showLists(mapping, form, request, response);
			}else if("delPlans".equalsIgnoreCase(myAct)) {
				// 删除计划
				myActFwd = deletePlans(mapping, form, request, response);
			}else if("savePlans".equalsIgnoreCase(myAct)) {
				// 保存计划
				myActFwd = savePlans(mapping, form, request, response);
			}else if("showOneClob".equalsIgnoreCase(myAct)) {
				// 显示单个文档
				myActFwd = showOneClob(mapping, form, request, response);
			}else if("checkList".equalsIgnoreCase(myAct)) {
				// 显示查询(clob)通用模块
				myActFwd = checkList(mapping, form, request, response);
			}else if("checkdis".equalsIgnoreCase(myAct)) {
				// 显示查询通用模块
				myActFwd = checkdis(mapping, form, request, response);
			}else if("saveWindows".equalsIgnoreCase(myAct)) {
				// 显示保存通用模块
				myActFwd = saveWindows(mapping, form, request, response);
			}else if("updataWindows".equalsIgnoreCase(myAct)) {
				// 显示修改增加页面通用模块
				myActFwd = updataWindows(mapping, form, request, response);
			}else if("delOneRow".equalsIgnoreCase(myAct)) {
				// 显示删除通用模块
				myActFwd = delOneRow(mapping, form, request, response);
			}else if("reportDis".equalsIgnoreCase(myAct)) {
				// 显示报表通用模块
				myActFwd = reportDis(mapping, form, request, response);
			}else if("testPaperUpdata".equalsIgnoreCase(myAct)) {
				// 显示试卷更新页面
				myActFwd = testPaperUpdata(mapping, form, request, response);
			}else if("getTestQuestions".equalsIgnoreCase(myAct)) {
				// 得到试卷试题
				myActFwd = getTestQuestionsList(mapping, form, request, response);
			}else if("questionChoose".equalsIgnoreCase(myAct)) {
				// 试卷选择试题
				myActFwd = questionChoose(mapping, form, request, response);
			}else if("delOneJyChooseQuestion".equalsIgnoreCase(myAct)) {
				// 试卷删除试题
				myActFwd = delOneJyChooseQuestion(mapping, form, request, response);
			}else if("testPaperFillIn".equalsIgnoreCase(myAct)) {
				// 试卷填充
				myActFwd = testPaperFillIn(mapping, form, request, response);
			}else if("testPaperFillInSave".equalsIgnoreCase(myAct)) {
				// 试卷填充保存
				myActFwd = testPaperFillInSave(mapping, form, request, response);
			}else if("testPaperResultStatisticList".equalsIgnoreCase(myAct)) {
				// 试卷填充结果统计
				myActFwd = testPaperResultStatisticList(mapping, form, request, response);
			}else if("testPaperResultStatisticOne".equalsIgnoreCase(myAct)) {
				// 试卷填充结果统计显示
				myActFwd = testPaperResultStatisticOne(mapping, form, request, response);
			}else if("classStudentStatistic".equalsIgnoreCase(myAct)) {
				// 班级学生人数统计
				myActFwd = classStudentStatistic(mapping, form, request, response);
			}else if("get_stu_info".equalsIgnoreCase(myAct)) {
				// 试卷填充结果统计显示
				myActFwd = get_stu_info(mapping, form, request, response);
			}else if("export".equalsIgnoreCase(myAct)){
				
				myActFwd = bjxsgbdwExportData(mapping, form, request, response);
			}
			return myActFwd;
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward get_stu_info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String dotype = DealString.toGBK(request.getParameter("act"));
		String pk = DealString.toGBK(request.getParameter("pk"));
		String url = "";
		if(dotype.equalsIgnoreCase("bjzw")){
			url = "/xgxt/classDuty_Cadre_updata.do?act=bjzw&pk="+pk;
		}if(dotype.equalsIgnoreCase("tfsjjl")){
			url = "/xgxt/tfsj_note_updata.do?act=tfsjjl&pk="+pk;
		}if(dotype.equalsIgnoreCase("fdyxxth")){
			url = "/xgxt/counsellorAndStuSpeak_note_updata.do?act=fdyxxth&pk="+pk;
		}if(dotype.equalsIgnoreCase("xshzw")){
			url = "/xgxt/xshgb_updata.do?act=xshzw&pk="+pk;
		}if(dotype.equalsIgnoreCase("xyXshzw")){
			url = "/xgxt/xyXshgb_updata.do?act=xyXshzw&pk="+pk;
		}if(dotype.equalsIgnoreCase("xljkXshzw")){
			url = "/xgxt/xljkXshgb_updata.do?act=xljkXshzw&pk="+pk;
		}
		request.setAttribute("url", url);
		return new ActionForward("/xljk_stu_info.do?act=stu_util&doType=stu_info");
	}


	private ActionForward classStudentStatistic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//在全部或者指定的范围内查询你要查询的内容在学生中的比例
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String xxdm     = session.getAttribute("xxdm").toString();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String moduleName = "";
		String grade = "";
		String manCount = "0";
		String womanCount = "0";
		String sex = null;
		String manScale = 	"0%";
		String womanScale = "0%";
		String dybl       = "0%";
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xy = DealString.toGBK(request.getParameter("xy"));
		String zy = DealString.toGBK(request.getParameter("zy"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("xy") && (xy == null || xy.equalsIgnoreCase(""))){
			xy = userDep;
			
		}

		StringBuffer sqlBuffer = new StringBuffer("select count(*) sum,max(xb) xb from ");
		sqlBuffer.append("view_xsjbxx b where (b.nj = ? or ? = ' ') and (b.xydm = ? or ? = ' ') ");
		if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
			sqlBuffer.append("and (b.zydm = ? or ? = ' ') and (b.bjdm = ? or ? = ' ') and b.xjztm = '有学籍' group by b.xb order by b.xb");
		}else{
			sqlBuffer.append("and (b.zydm = ? or ? = ' ') and (b.bjdm = ? or ? = ' ') group by b.xb order by b.xb");
		}
		sql =sqlBuffer.toString();
		ArrayList vector = dao.rsToVator(sql, new String[] {nj,nj+" ",xy,xy+" ",zy,zy+" ",bj,bj +" "},new String[]{"sum","xb"});
		if(vector!=null){
			for(int i = 0 ;i < vector.size();i++){
				sex =  ((String [])vector.get(i))[1]; 
				if (sex.equalsIgnoreCase("男")){
					manCount = ((String [])vector.get(i))[0]; 
				}else if (sex.equalsIgnoreCase("女")){
					womanCount = ((String [])vector.get(i))[0]; 
				}
			}
		}
		int manSum =  Integer.parseInt(manCount);
		int womanSum = Integer.parseInt(womanCount);
		int stuSum = manSum+womanSum;
		String stuCount = ((Integer)stuSum).toString();
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xy,zy,bj,"","","",nj,"","",""));
		sql = "select count(*) num from view_dyxx a ";
		if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
			query.append(" and exists (select xh from view_xsjbxx where a.xh = xh and xjztm = '有学籍') ");
		}
		int dyrs = Integer.parseInt(dao.getOneRs(sql+query.toString(), new String []{}, "num"));
		//得到总数后统计比例
		if(stuSum!=0){
			dybl     = ((float)((dyrs*10000)/stuSum))/100 +"%";
			manScale = ((float)((manSum*10000)/stuSum))/100 + "%";
			womanScale = ((float)((womanSum*10000)/stuSum))/100 + "%";
		}

		//得到页面上显示的所查模块和查询年级的内容
		if(!bj.equalsIgnoreCase("")){
			sql = "select distinct bjmc from view_njxyzybj where bjdm = ? order by bjmc";
			String[] tmp = dao.getOneRs(sql, new String[] {bj},new String[]{"bjmc"});
			moduleName = tmp[0]; 
		}else if(!zy.equalsIgnoreCase("")){
			sql = "select distinct zymc from view_njxyzybj where zydm = ? order by zymc";
			String[] tmp = dao.getOneRs(sql, new String[] {zy},new String[]{"zymc"});
			moduleName = tmp[0]; 
		}else if(!xy.equalsIgnoreCase("")){
			sql = "select distinct xymc from view_njxyzybj where xydm = ? order by xymc";
			String[] tmp = dao.getOneRs(sql, new String[] {xy},new String[]{"xymc"});
			moduleName = tmp[0]; 
		}else{
			moduleName = "全校范围"; 
		}
		if(!nj.equalsIgnoreCase("")){
			grade = nj; 
		}else{
			grade = "没有指定";
		}

		map.put("moduleName", moduleName);
		map.put("grade", grade);
		map.put("manCount", manCount);
		map.put("womanCount", womanCount); 
		map.put("stuCount", stuCount); 
		map.put("manScale", manScale); 
		map.put("womanScale", womanScale); 
		map.put("dyrs",((Integer)dyrs).toString()); 
		map.put("dybl", dybl); 
		request.setAttribute("nj", nj);
		request.setAttribute("bjdm", bj);
		request.setAttribute("zydm", zy);
		request.setAttribute("xydm", xy);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", map);
		request.setAttribute("path", "show_classStudentSumStatistic_list.do");
		FormModleCommon.commonRequestSet(request);
		return new ActionForward("/sxjy/statistic/ClassStudentStatistic.jsp");
	}


	private ActionForward testPaperResultStatisticList(ActionMapping mapping,ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		SxjyDAO dao = new SxjyDAO();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		request.setAttribute("ResearchTestPageList", dao.getResearchTestPageList());
		request.setAttribute("userType", userType);
		return mapping.findForward("success");
	}

	private ActionForward testPaperResultStatisticOne(ActionMapping mapping,ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		String sql = "";
		DAO dao = DAO.getInstance();
		String pk = RowidToPk.rowidToPK(request.getParameter("testPage"));
		HashMap<String, String> map = new HashMap<String, String>();

		String xh = DealString.toGBK(request.getParameter("xh"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xy = DealString.toGBK(request.getParameter("xy"));
		String zy = DealString.toGBK(request.getParameter("zy"));
		String bj = DealString.toGBK(request.getParameter("bj"));


		sql = "select dywjdm,dywjmc,puber,lrrq,bz from sxjy_dywjb where dywjdm = ?";
		String[] tmp = dao.getOneRs(sql, new String[]{pk}, new String[]{"dywjdm","dywjmc","puber","lrrq","bz"});
		map.put("dywjdm", tmp[0]);
		map.put("dywjmc", tmp[1]);
		map.put("puber", tmp[2]);
		map.put("lrrq", tmp[3]);
		map.put("bz", tmp[4]);
		request.setAttribute("dywjxx", map);

		sql = "select count(1) sum from sxjy_dywjxtb where dywjdm = ?";
		String sum = dao.getOneRs(sql, new String[]{pk}, new String[]{"sum"})[0];
		request.setAttribute("sum", sum);
		sql = "select rownum xh,a.dywjdm,b.wtdm,b.wtmc from sxjy_dywjxtb a left join sxjy_dytkb b on a.wtdm = b.wtdm where a.dywjdm = ? ";
		List stList = dao.getList(sql, new String[] { pk }, new String[] {
				"xh", "dywjdm", "wtdm", "wtmc"});
		request.setAttribute("stList", stList);

		sql = "select count(*) sum,a.wtdm,a.damc,b.danr from sxjy_dytktkdab a left join sxjy_dytkdab b on a.wtdm = b.wtdm and a.damc = b.damc where xh in (select xh from view_xsjbxx where " +
		"(xh = ? or ? = ' ')and (nj = ? or ? = ' ') and (xydm = ? or ? = ' ') and (zydm = ? or ? = ' ') and " +
		"(bjdm = ? or ? = ' ')) and (dywjdm = ?) group by a.wtdm,a.damc,b.danr";
		List resultList = dao.getList(sql, new String[] {xh,xh+" ",nj,nj+" ",xy,xy+" ",zy,zy+" ",bj,bj +" ",tmp[0]},new String[]{"sum","wtdm","damc","danr"});
		String resultStr = dao.listToString(resultList, new String[] { "sum","wtdm","damc","danr"});
		request.setAttribute("resultStr", resultStr);
		return mapping.findForward("success");
	}


	private ActionForward testPaperFillInSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean del = false;
		DAO dao = DAO.getInstance();
		String sql = "";
		HttpSession session = request.getSession();
		String xh = session.getAttribute("userName").toString();
		String stStr = DealString.toGBK(request.getParameter("stStr"));
		String dywjxx = request.getParameter("dywjxx");
		String selectedStr[] = stStr.split("!!SplitSignOne!!");
		String wtdm = "";
		String damc = "";
		int len = selectedStr.length;
		sql = "delete from sxjy_dytktkdab where xh = ? and dywjdm = ?";
		del = dao.runUpdate(sql, new String[]{ xh,dywjxx});
		if(del){
			for (int i = 0; i < len; i++) {
				wtdm = selectedStr[i].split("!!SplitSignTwo!!")[0];
				if(selectedStr[i].split("!!SplitSignTwo!!").length!=2){
					damc = "未选";
				}else{
					damc = selectedStr[i].split("!!SplitSignTwo!!")[1];
				}
				sql = "insert into sxjy_dytktkdab (xh,dywjdm,wtdm,damc) values (?,?,?,?)";
				dao.runUpdate(sql, new String[]{ xh,dywjxx,wtdm,damc });
			}
		}
		return null;
	}


	private ActionForward testPaperFillIn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//调研问卷显示
		SxjyDAO dao = new SxjyDAO();
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if(userType.equalsIgnoreCase("stu")){
			sql = "select dywjdm,dywjmc,puber,lrrq,bz from sxjy_dywjb where dywjdm = ?";
			String[] tmp = dao.getOneRs(sql, new String[]{pk}, new String[]{"dywjdm","dywjmc","puber","lrrq","bz"});
			map.put("dywjdm", tmp[0]);
			map.put("dywjmc", tmp[1]);
			map.put("puber", tmp[2]);
			map.put("lrrq", tmp[3]);
			map.put("bz", tmp[4]);
			request.setAttribute("dywjxx", map);

			sql = "select count(1) sum from sxjy_dywjxtb where dywjdm = ?";
			String sum = dao.getOneRs(sql, new String[]{pk}, new String[]{"sum"})[0];
			request.setAttribute("sum", sum);
			sql = "select rownum xh,a.dywjdm,b.wtdm,b.wtmc from sxjy_dywjxtb a left join sxjy_dytkb b on a.wtdm = b.wtdm where a.dywjdm = ? ";
			List stList = dao.getList(sql, new String[] { pk }, new String[] {
					"xh", "dywjdm", "wtdm", "wtmc"});
			request.setAttribute("stList", stList);

			sql = "select dywjdm,wtdm,wtmc,damc,danr from view_xxdyzkwj where dywjdm=? order by damc";
			List daList = dao.getList(sql, new String[] { pk }, new String[] {
					"wtdm", "wtmc", "damc", "danr"});
			String stStr = dao.listToString(daList, new String[] { "wtdm", "wtmc", "damc", "danr" });
			request.setAttribute("stStr", stStr);

		}
		return mapping.findForward("success");
	}


	private ActionForward delOneJyChooseQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SxjyDAO dao = new SxjyDAO();
		String realTable = request.getParameter("realTable");
		String pkSubsidiary = request.getParameter("pkSubsidiary");
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		String sql = "";
		boolean ok = false;
		if(realTable.equalsIgnoreCase("sxjy_dywjxtb")){
			sql = "delete from sxjy_dywjxtb where dywjdm = ? and wtdm = ?";
			ok = dao.runUpdate(sql, new String[]{pkSubsidiary,pk});
		}
		if(ok==true){
			if(realTable.equalsIgnoreCase("sxjy_dywjxtb")){
				return  new ActionForward("/research_testPaper_updata.do?pk="+pkSubsidiary);
			}else{
				return mapping.findForward("false");
			}
		}else{
			return mapping.findForward("false");
		}   
	}


	private ActionForward questionChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SxjyDAO dao = new SxjyDAO();
		SxjyForm saveWindowsForm = (SxjyForm) form;
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		String sql="";
		boolean del = false;
		String type =request.getParameter("type");
		String pkSubsidiary =request.getParameter("pkSubsidiary");
		if(type.equalsIgnoreCase("jystyz")){
			sql = "delete from sxjy_dywjxtb where wtdm = ? and dywjdm = ?";
			del = dao.runUpdate(sql, new String[] { pk,pkSubsidiary });
			if(del){
				sql = "insert into sxjy_dywjxtb(dywjdm,wtdm) values (?,?)";
				dao.runUpdate(sql, new String[] {pkSubsidiary,pk});
			} else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}
		return null;
	}


	private ActionForward getTestQuestionsList(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		SxjyDAO dao = new SxjyDAO();
		String[] colList = null;
		String sql="";
		Vector<Object> rs = new Vector<Object>(); 
		String[] colListCN = null;
		String[] inputList =null;
		SxjyForm myForm = (SxjyForm) form;
		String month = myForm.getMonth();
		String year = myForm.getYear();
		String time = GetTime.getTime(year, month);
		myForm.setMonth(month);
		myForm.setYear(year);
		String pk = request.getParameter("pk");
		colList = new String[] { "wtdm","wtmc","lrrq","bz"};
		colListCN = dao.getColumnNameCN(colList, "sxjy_dytkb");
		inputList = new String[] {time};
		sql = "select wtdm,wtmc,lrrq,bz from sxjy_dytkb where lrrq like ? order by wtdm";
		rs.addAll(dao.rsToVator(sql, inputList, colList));
		List topTr = dao.arrayToList(colList, colListCN);
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("yearList", GetTime.getYearList(5));
		request.setAttribute("chkList", dao.getChkList(6));
		request.setAttribute("pk", pk);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}


	private ActionForward testPaperUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SxjyDAO dao = new SxjyDAO();
		boolean disabled = true; 
		String[] colList = null;
		String sql="";
		Vector<Object> rs = new Vector<Object>(); 
		String[] record = null;
		String[] colListCN = null;
		String[] inputList =null;
		SxjyForm checkListForm = (SxjyForm) form;
		String pk = request.getParameter("pk");
		colList =  new String[] { "pk","dywjmc","bz","xsbj"};
		sql = "select dywjdm pk,dywjmc,bz,xsbj"
			+" from sxjy_dywjb where dywjdm = ? order by dywjdm";
		String rec = dao.getStringToSplit(sql, new String[] {pk}, colList);
		String[] tmp = rec.split("!!SplitSignOne!!");
		if (tmp.length != 2) {
			record = new String[colList.length + 1];
		} else {
			record = tmp[1].split("!!SplitSignTwo!!");
		}
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < colList.length; i++) {
			map.put(colList[i].toLowerCase(), record[i + 1]);
		}
		colList = new String[] { "wtdm","wtmc","lrrq","bz"};
		colListCN = dao.getColumnNameCN(colList, "sxjy_dytkb");
		inputList = new String[] {pk};
		sql = "select a.wtdm,b.wtmc,b.lrrq,b.bz from sxjy_dywjxtb a left join sxjy_dytkb b on a.wtdm = b.wtdm where (a.dywjdm = ?)";
		rs.addAll(dao.rsToVator(sql, inputList, colList));
		List topTr = dao.arrayToList(colList, colListCN);
		checkListForm.setXsbj(map.get("xsbj"));
		sql = "select count(*) count from sxjy_dytktkdab where dywjdm = ?";
		String[] tmp1 = dao.getOneRs(sql, new String[] {pk}, new String[] {"count"});
		String count = tmp1[0];
		if(!count.equalsIgnoreCase("0")){
			request.setAttribute("notAmend", true);
		}
		request.setAttribute("type", "shbk");
		request.setAttribute("disabled", disabled);
		request.setAttribute("ynList", dao.getChkList(2));
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rs", map);// 发送数据集
		request.setAttribute("rs2", rs);// 发送数据集2
		return mapping.findForward("success");
	}


	private ActionForward reportDis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		SxjyDAO dao = new SxjyDAO();
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		String sql="";
		String[] colList = null;
		String[] record = null;
		ActionForward forward = null;
		String type =request.getParameter("type");
		HashMap<String, String> map = new HashMap<String, String>();
		if(type.equalsIgnoreCase("hdzjhb")) {
			colList =  new String[] { "pk","xymc", "place", "joinstudent", "compere","kssj","jssj","lrrq","motif","format","cont","effect","reflect","attitude"};
			sql = "select a.rowid pk,c.xymc,a.place,a.joinstudent,a.compere,a.kssj,a.jssj,a.lrrq,a.motif,a.format,a.cont,a.effect,a.reflect,a.attitude"
				+" from sxjy_hdzjhbb a left join (select distinct xydm,xymc from view_njxyzybj order by xydm) c on a.xydm = c.xydm where a.rowid = ? order by c.xymc";
			forward = new ActionForward("/sxjy/Jyhd_zjhb_report.jsp");
		}else if(type.equalsIgnoreCase("zzxxnj")) {
			colList =  new String[] { "pk","xymc","bjmc", "place", "studentcount", "compere","xxsj","lrrq","motif","cont","attitude"};
			sql = "select a.rowid pk,c.xymc,c.bjmc,a.place,a.studentcount,a.compere,a.xxsj,a.lrrq,a.motif,a.cont,a.attitude"
				+" from sxjy_zzxxtljlb a left join (select xydm,xymc,bjdm,bjmc from view_njxyzybj order by bjdm) c on a.bjdm = c.bjdm where a.rowid = ? order by c.xymc";
			forward = new ActionForward("/sxjy/Zzxx_note_report.jsp");
		}else if(type.equalsIgnoreCase("xfjpjl")) {
			colList =  new String[] { "pk","xymc","place","studentcount", "compere","kssj","jssj","lrrq","motif","format","scope","cont","effect"};
			sql = "select a.rowid pk,c.xymc,a.place,a.studentcount,a.compere,a.kssj,a.jssj,a.lrrq,a.motif,a.format,a.scope,a.cont,a.effect"
				+" from sxjy_xfjpjlb a left join (select distinct xydm,xymc from view_njxyzybj order by xydm) c on a.xydm = c.xydm where a.rowid = ? order by c.xymc";
			forward = new ActionForward("/sxjy/Xfjs_xfjp_report.jsp");
		}else if(type.equalsIgnoreCase("swCheck")) {
			colList =  new String[] { "pk","xymc","place", "joinpersonnel","kssj","jssj","lrrq","motif","problem","checknote","adoptstep"};
			sql = "select a.rowid pk,c.xymc,a.place,a.joinpersonnel,a.kssj,a.jssj,a.lrrq,a.motif,a.problem,a.checknote,a.adoptstep"
				+" from sxjy_xfjcjlb a left join (select distinct xydm,xymc from view_njxyzybj order by xydm) c on a.xydm = c.xydm where a.rowid = ? order by c.xymc";
			forward = new ActionForward("/sxjy/StudyWind_check_report.jsp");
		}else if(type.equalsIgnoreCase("shbkjl")) {
			colList =  new String[] { "pk","xymc","bksj", "format","dxmc","joinperson","money","cont","reflect","effect","lrrq"};
			sql = "select a.pk,c.xymc,a.bksj,a.format,a.dxmc,a.joinperson,a.money,a.cont,a.reflect,a.effect,a.lrrq"
				+" from view_sxjy_shbkhdjl a left join (select distinct xydm,xymc from view_njxyzybj order by xydm) c on a.xydm = c.xydm where a.pk = ? order by c.xymc";
			forward = new ActionForward("/sxjy/PenuryHelp_note_report.jsp");
		}else if(type.equalsIgnoreCase("ysjyhdjl")) {
			colList =  new String[] { "pk","xymc", "place", "joinstudent", "compere","kssj","jssj","lrrq","motif","format","cont","reflect","attitude"};
			sql = "select a.rowid pk,c.xymc,a.place,a.joinstudent,a.compere,a.kssj,a.jssj,a.lrrq,a.motif,a.format,a.cont,a.reflect,a.attitude"
				+" from sxjy_ysjyhdjlb a left join (select distinct xydm,xymc from view_njxyzybj order by xydm) c on a.xydm = c.xydm where a.rowid = ? order by c.xymc";
			forward = new ActionForward("/sxjy/ArtEducate_note_report.jsp");
		}else if(type.equalsIgnoreCase("ztjzqksb")) {
			colList =  new String[] { "pk","xymc", "place", "joinstudent", "compere","kssj","jssj","lrrq","motif","format","cont","effect","reflect","attitude"};
			sql = "select a.rowid pk,c.xymc,a.place,a.joinstudent,a.compere,a.kssj,a.jssj,a.lrrq,a.motif,a.format,a.cont,a.effect,a.reflect,a.attitude"
				+" from sxjy_ztjzqksbb a left join (select distinct xydm,xymc from view_njxyzybj order by xydm) c on a.xydm = c.xydm where a.rowid = ? order by c.xymc";
			forward = new ActionForward("/sxjy/SpecialChair_note_report.jsp");
		}else if(type.equalsIgnoreCase("aqfzjyqk")) {
			colList =  new String[] { "pk","xymc", "place", "joinstudent", "compere","kssj","jssj","lrrq","motif","format","cont","effect","reflect","attitude"};
			sql = "select a.rowid pk,c.xymc,a.place,a.joinstudent,a.compere,a.kssj,a.jssj,a.lrrq,a.motif,a.format,a.cont,a.effect,a.reflect,a.attitude"
				+" from sxjy_aqfzjyqkb a left join (select distinct xydm,xymc from view_njxyzybj order by xydm) c on a.xydm = c.xydm where a.rowid = ? order by c.xymc";
			forward = new ActionForward("/sxjy/aqfzjyqk_report.jsp");
		}else if(type.equalsIgnoreCase("tfsjjl")) {
			colList =  new String[] { "pk","xh","xm","xydm","xymc","bjmc","bjdm","zydm","zymc","nj","lxdh","linkman","lrrq","fssj","report","cont","disposal"};
			sql = "select pk,xh,xm,xydm,xymc,bjmc,bjdm,zydm,zymc,nj,lxdh,linkman,lrrq,fssj,report,cont,disposal "
				+" from view_sxjy_xstfsj  where pk = ? order by lrrq";
			forward = new ActionForward("/sxjy/Xfjs_tfsj_report.jsp");
		}else if(type.equalsIgnoreCase("fdyxxth")) {
			colList =  new String[] { "pk","xh","lrrq","place","tutorshipperson","joinstudent","knowside","acceptside","fssj","kssj","jssj","cause",
					"format","cont","commoncognition","tracknote","xm","bjdm","xydm","xymc","bjmc","nj","zydm","zymc","count"};
			sql = "select pk,xh,lrrq,place,tutorshipperson,joinstudent,knowside,acceptside,fssj,kssj,jssj,cause,"+
			"contactformat format,cont,commoncognition,tracknote,xm,bjdm,xydm,xymc,bjmc,nj,zydm,zymc,count"
			+" from view_fdyxxthjl where pk = ?";
			forward = new ActionForward("/sxjy/CounsellorAndStuSpeak_note_report.jsp");
		}else if(type.equalsIgnoreCase("jsgtjl")) {
			colList =  new String[] { "count","pk","zgh","lrrq","bjdm","bjmc","place","fssj","kssj","jssj","format","cont","commoncognition","bz","xm","xb","xydm","xymc"};
			sql = "select count,pk,zgh,lrrq,bjdm,bjmc,place,fssj,kssj,jssj,contactformat format,cont,commoncognition,bz,xm,xb,bmdm xydm,xymc" 
			+" from view_jsgtjlb where pk = ?";
			forward = new ActionForward("/sxjy/CounsellorCommunicate_note_report.jsp");
		}else if(type.equalsIgnoreCase("fdydj1")) {
			colList =  new String[] { "zgh","xm","bdjzgh","ksn","ksy","ksr","jsn","jsy","jsr","jl1","jl2","jl3","jl4","xydm","xymc","bdjxm"};
			sql = "select zgh,xm,bdjzgh,substr(kssj,0,4) ksn,substr(kssj,5,2) ksy,substr(kssj,7,2) ksr,substr(jssj,0,4) jsn,substr(jssj,5,2) jsy,substr(jssj,7,2) jsr,jl1,jl2,jl3,jl4,xydm,xymc,bdjxm" 
			+" from view_fdydjjl where zgh||bdjzgh = ?";
			forward = new ActionForward("/sxjy/fdy/counsellorLeadTeach_Report.jsp");
		}else if(type.equalsIgnoreCase("fdydj2")) {
			colList =  new String[] { "zgh","xm","bdjzgh","ksn","ksy","ksr","jsn","jsy","jsr","jl1","jl2","jl3","jl4","xydm","xymc","bdjxm"};
			sql = "select zgh,xm,bdjzgh,substr(kssj,0,4) ksn,substr(kssj,5,2) ksy,substr(kssj,7,2) ksr,substr(jssj,0,4) jsn,substr(jssj,5,2) jsy,substr(jssj,7,2) jsr,jl1,jl2,jl3,jl4,xydm,xymc,bdjxm" 
			+" from view_fdydjjl where zgh||bdjzgh = ?";
			forward = new ActionForward("/sxjy/fdy/counsellorLeadTeach_Report2.jsp");
		}else if(type.equalsIgnoreCase("bgbxb")) {
			colList =  new String[] { "xh","xm","nj","xb","bjdm","bjmc","xq","xn","nd","jpzz","jpbm","jpzw","xymc","xydm","tc","rzjl","zydm","zymc","jljl","zwtj","lxdh","zzmmmc"};
			map = dao.sxjyQueryOne("view_xsgbxbxx", colList, "xh",pk);
			request.setAttribute("rs", map);
			return new ActionForward("/sxjy/fdy/bgbxb_Report.jsp");
		}else if(type.equalsIgnoreCase("stsh")) {
			colList = new String[] { "stdm","stmc","yhm","clsj","zsn1st","zsm1st","zsn2st","zsm2st","zsn3st","zsm3st","xh","lxfs","grjl","lbdm","lbmc","hdnr","zgh","xshyj","ytwyj","shzt","xymc","lsxm","zzmmmc","xm","xb","bmmc","bjmc","zwmc","zydm","zymc"};
			map = dao.sxjyQueryOne("view_stsqdj", colList, "stdm",pk);
			request.setAttribute("rs", map);
			return new ActionForward("/shgz/stshReport.jsp");
		}else if(type.equalsIgnoreCase("sthdsh")){
			pk = DealString.toGBK(pk);
			colList = new String[] { "pk","yhm","lxfs","shzt","stmc","lbmc","xymc","bjmc","xm","lsxm","stdm","hdmc","hdsj","hdnr","hdjfys","ddlsyj","xtzjyj","lrsj","cjrs","shzt","hddd","xn","nd","xqdm"};
			map = dao.sxjyQueryOne("view_gdnz_sthd", colList, "pk",pk);
			request.setAttribute("rs", map);
			return new ActionForward("/shgz/sthdReport.jsp");
		}else if(type.equalsIgnoreCase("sthdjfsh")){
			pk = DealString.toGBK(pk);
			colList = new String[] { "pk","hdmc","xn","xqdm","xqmc","nd","stdm","lxfs","stmc","xm","xymc","jfyt1","je1","jfyt2","je2","jfyt3","je3","jfyt4","je4","jfyt5","je5","jfyt6","je6","lrsj","shzt","yfje","jsr","bxqk","ytwyj","stdm"};
			map = dao.sxjyQueryOne("view_gdnz_sthdjf", colList, "pk",pk);
			request.setAttribute("rs", map);
			return new ActionForward("/shgz/sthdjfReport.jsp");
		}else if(type.equalsIgnoreCase("fdypx")){
			pk = DealString.toGBK(pk);
			colList = new String[] { "pk","zgh","xn","xm","pxqj||pxmc","lrrq","xxyj","bz","bmdm","xb","lxdh","bmmc","zwmc","zyzz","zzmm","xl","sfmc","mzmc","csrq","zwrzsj","jsrzsj","sjdw","bgdh","cz","txdz","grhjqk","gzjl","zc","lxgzsj","xsgzyjfx","jtzz","yddh","dzyx","fblw","kyjl","xw","yzbm"};
			map = dao.sxjyQueryOne("view_fdyPx", colList, "pk",pk);
			HttpSession session = request.getSession();
			String xxmc = (String) session.getAttribute("xxmc");
			map.put("xxmc", xxmc);
			String[] pxList = dao.getFdypxjlList(map.get("zgh"), map.get("lrrq"));
			request.setAttribute("pxList", pxList);
			request.setAttribute("rs", map);
			return new ActionForward("/sxjy/fdy/fdypxReport.jsp");
		}
		String rec = dao.getStringToSplit(sql, new String[] {pk}, colList);
		String[] tmp = rec.split("!!SplitSignOne!!");
		if (tmp.length != 2) {
			record = new String[colList.length + 1];
		} else {
			record = tmp[1].split("!!SplitSignTwo!!");
		}
		
		for (int i = 0; i < colList.length; i++) {
			map.put(colList[i].toLowerCase(), record[i + 1]);
		}
		request.setAttribute("rs", map);
		return forward;
	}


	private ActionForward delOneRow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SxjyDAO dao = new SxjyDAO();
		String realTable = request.getParameter("realTable");
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		String sql = "";
		boolean ok = false;
		boolean del = false;
		if(realTable.equalsIgnoreCase("sxjy_shbkxxgl")){
			sql = "delete from "+realTable+" where dxdm = ?";
		}else if(realTable.equalsIgnoreCase("sxjy_dytkb")){
			sql = "delete from sxjy_dytkdab where wtdm = ?";
			del = dao.runUpdate(sql, new String[]{pk});
			if(del){
				sql = "delete from "+realTable+" where wtdm = ?";
			}
		}else if(realTable.equalsIgnoreCase("sxjy_dywjb")){
			sql = "delete from sxjy_dytktkdab where dywjdm = ?";
			del = dao.runUpdate(sql, new String[]{pk});
			if (del) {
				del = false;
				sql = "delete from sxjy_dywjxtb where dywjdm = ?";
				del = dao.runUpdate(sql, new String[]{pk});
			}
			if (del) {
				sql = "delete from "+realTable+" where dywjdm  = ?";
			}
		}else if(realTable.equalsIgnoreCase("sxjy_bjgbzlb")){
			sql = "delete from sxjy_bjgbxxb where bjgbdm = ?";
			del = dao.runUpdate(sql, new String[]{pk});
			if (del) {
				sql = "delete from "+realTable+" where bjgbdm  = ?";
			}
		}else if(realTable.equalsIgnoreCase("sxjy_dytkdab")){
			sql = "delete from sxjy_dytktkdab where  WTDM = (select wtdm from sxjy_dytkdab where rowid = ?) and DAMC = (select damc from sxjy_dytkdab where rowid = ?);";
			del = dao.runUpdate(sql, new String[]{pk,pk});
			if (del) {
				sql = "delete from "+realTable+" where rowid = ?";
			}
		}
		else{
			sql = "delete from "+realTable+" where rowid = ?";
			pk=pk.replace(' ', '+');
		}
		ok = dao.runUpdate(sql, new String[]{pk});
		if(ok==true){
			if(realTable.equalsIgnoreCase("sxjy_dytkdab")){
				String pkSubsidiary = request.getParameter("pkSubsidiary");
				return  new ActionForward("/research_question_updata.do?act=dyst&pk="+pkSubsidiary);
			}else{
				request.setAttribute("ok", "ok");
				return checkdis(mapping, form, request, response);//调用显示模块显示查询结果
			}
		}else{
			return mapping.findForward("false");
		}   
	}


	private ActionForward updataWindows(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		SxjyDAO dao = new SxjyDAO();
		boolean disabled = true; 
		String[] colList = null;
		String sql="";
		String xy = "";
		String[] record = null;
		SxjyForm checkListForm = (SxjyForm) form;
		String dataType = request.getParameter("act");
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if(request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= "";
			}
		}
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		if(dataType.equalsIgnoreCase("hdzjhb")){
			//活动总结汇报
			colList =  new String[] { "pk","xydm", "place", "joinstudent", "compere","kssj","jssj","lrrq","motif","format","cont","effect","reflect","attitude","jyPlotdm"};
			sql = "select rowid pk,xydm,place,joinstudent,compere,kssj,jssj,lrrq,motif,format,cont,effect,reflect,attitude,chdm jyPlotdm"
				+" from sxjy_hdzjhbb where rowid = ?";
		}else if (dataType.equalsIgnoreCase("zzxxnj")){
			//政治学习记录
			colList =  new String[] { "pk","xydm", "place", "studentcount", "compere","xxsj","lrrq","motif","cont","attitude","xxstuffdm"};
			sql = "select rowid pk,xydm,place,studentcount,compere,xxsj,lrrq,motif,cont,attitude,cldm xxstuffdm"
				+" from sxjy_zzxxtljlb where rowid = ?";
		}else if (dataType.equalsIgnoreCase("xfjpjl")){
			//学风讲评记录
			colList =  new String[] { "pk","xydm", "place", "studentcount", "compere","kssj","jssj","lrrq","motif","format","scope","cont","effect"};
			sql = "select rowid pk,xydm,place,studentcount,compere,kssj,jssj,lrrq,motif,format,scope,cont,effect"
				+" from sxjy_xfjpjlb where rowid = ?";
		}else if (dataType.equalsIgnoreCase("tfsjjl")){
			//突发事件记录
			colList =  new String[] { "pk","xydm", "xymc", "xh","xm","fssj","lrrq","report","linkman","lxdh","disposal","cont","bjmc"};
			sql = "select pk,xydm, xymc, xh,xm,fssj,lrrq,report,linkman,lxdh,disposal,cont,bjmc"
				+" from view_sxjy_xstfsj where pk = ?";
		}else if (dataType.equalsIgnoreCase("swCheck")){
			//学风检查
			colList =  new String[] { "pk","xydm", "place", "joinpersonnel","kssj","jssj","lrrq","motif","problem","checknote","adoptstep"};
			sql = "select rowid pk,xydm,place,joinpersonnel,kssj,jssj,lrrq,motif,problem,checknote,adoptstep"
				+" from sxjy_xfjcjlb where rowid = ?";
		}else if (dataType.equalsIgnoreCase("shbk")){
			//社会帮困
			colList =  new String[] { "pk","xydm", "dxmc", "format","jdsj","lrrq","bz"};
			sql = "select dxdm pk,xydm,dxmc,format,jdsj,lrrq,bz"
				+" from sxjy_shbkxxgl where dxdm = ?";
		}else if (dataType.equalsIgnoreCase("shbkjl")){
			//社会帮困记录
			colList =  new String[] { "pk","xydm","dxdm","dxmc","joinperson","format","bksj","money","cont","effect","reflect","lrrq","bz","dxmc"};
			sql =  "select pk,xydm,dxdm,dxmc,joinperson,format,bksj,money,cont,effect,reflect,lrrq,bz,dxmc"  
				+" from view_sxjy_shbkhdjl where pk = ?";
		}else if (dataType.equalsIgnoreCase("ysjyhdjl")){
			//艺术教育活动记录管理
			colList =  new String[] { "pk","xydm", "place", "joinstudent", "compere","kssj","jssj","lrrq","motif","format","cont","reflect","attitude"};
			sql = "select rowid pk,xydm,place,joinstudent,compere,kssj,jssj,lrrq,motif,format,cont,reflect,attitude"
				+" from sxjy_ysjyhdjlb where rowid = ?";
		}else if (dataType.equalsIgnoreCase("dyst")){
			//调研题库更新
			colList =  new String[] { "pk","wtmc","wtda","bz"};
			sql = "select wtdm pk,wtmc,wtda,bz from sxjy_dytkb where wtdm = ?";
		}else if (dataType.equalsIgnoreCase("jystda")){
			//调研试题答案更新
			colList =  new String[] { "pk","damc","danr","xsbj"};
			sql = "select rowid pk,damc,danr,xsbj from sxjy_dytkdab where rowid = ?";
			String pkSubsidiary = request.getParameter("pkSubsidiary");
			request.setAttribute("pk", pkSubsidiary);
			request.setAttribute("ynList", dao.getChkList(2));
		}else if(dataType.equalsIgnoreCase("ztjzqksb")){
			//专题讲座情况上报
			colList =  new String[] { "pk","xydm", "place", "joinstudent", "compere","kssj","jssj","lrrq","motif","format","cont","effect","reflect","attitude"};
			sql = "select rowid pk,xydm,place,joinstudent,compere,kssj,jssj,lrrq,motif,format,cont,effect,reflect,attitude"
				+" from sxjy_ztjzqksbb where rowid = ?";
		}else if(dataType.equalsIgnoreCase("aqfzjyqk")){
			//安全法制教育情况
			colList =  new String[] { "pk","xydm", "place", "joinstudent", "compere","kssj","jssj","lrrq","motif","format","cont","effect","reflect","attitude"};
			sql = "select rowid pk,xydm,place,joinstudent,compere,kssj,jssj,lrrq,motif,format,cont,effect,reflect,attitude"
				+" from sxjy_aqfzjyqkb where rowid = ?";
		}else if(dataType.equalsIgnoreCase("fdyxxth")){
			//与学生谈话情况
			colList =  new String[] { "pk","xh","lrrq","place","tutorshipperson","joinstudent","knowside","acceptside","fssj","kssj","jssj","cause",
					"format","cont","commoncognition","tracknote","xm","bjdm","xydm","xymc","bjmc","nj","zydm","zymc"};
			sql = "select pk,xh,lrrq,place,tutorshipperson,joinstudent,knowside,acceptside,fssj,kssj,jssj,cause,"+
			"contactformat format,cont,commoncognition,tracknote,xm,bjdm,xydm,xymc,bjmc,nj,zydm,zymc"
			+" from view_fdyxxthjl where pk = ?";
		}else if(dataType.equalsIgnoreCase("jsgtjl")){
			//与教师谈话情况
			colList =  new String[] { "count","pk","zgh","lrrq","bjdm","bjmc","place","fssj","kssj","jssj","format","cont","commoncognition","bz","xm","xb","xydm"};
			sql = "select count,pk,zgh,lrrq,bjdm,bjmc,place,fssj,kssj,jssj,contactformat format,cont,commoncognition,bz,xm,xb,bmdm xydm" 
			+" from view_jsgtjlb where pk = ?";
		}else if(dataType.equalsIgnoreCase("bjzw")){
			//班级职务
			String doType=request.getParameter("doType");
			colList =  new String[] { "pk","bjgbdm","bjgbmc","jbdm","xh","xm","xb","xydm","xymc","bjdm","bjmc","lxfs","lrrq","bz","kssj","jssj"};
			sql = "select pk,bjgbdm,bjgbmc,gbzljb jbdm,xh,xm,xb,xydm,xymc,bjdm,bjmc,lxfs,lrrq,bz,kssj,jssj from view_bjgbxx where pk = ?";
			request.setAttribute("doType", doType);
		}else if(dataType.equalsIgnoreCase("bjgbsz")){
			//班级职务设置
			colList =  new String[] { "bjgbdm","bjgbmc","zr","bz" };
			sql = "select bjgbdm,bjgbmc,zr,bz from sxjy_bjgbzlb where bjgbdm = ?";
		}else if(dataType.equalsIgnoreCase("dsyjx")){
			//导师意见箱
			colList =  new String[] { "pk","zxm","gdsyj","title","puber","xydm" };
			sql = "select pk,zgh zxm,gdsyj,title,puber,xydm from view_szdw_dsyjx where pk = ?";
		}else if(dataType.equalsIgnoreCase("xshzw")){
			//学生会职务
			colList =  new String[] { "pk","xshgbdm","xshgbmc","xh","xm","xb","xydm","xymc","bjdm","bjmc","lxfs","lrrq","bz","kssj","jssj","zymc"};
			sql = "select pk,xshgbdm,xshgbmc,xh,xm,xb,xydm,xymc,bjdm,bjmc,lxfs,lrrq,bz,kssj,jssj,zymc from view_xshgbxx where pk = ?";
		}else if(dataType.equalsIgnoreCase("xyXshzw")){
			//学生会职务
			colList =  new String[] { "pk","xshgbdm","xshgbmc","xh","xm","xb","xydm","xymc","bjdm","bjmc","lxfs","lrrq","bz","kssj","jssj","zymc"};
			sql = "select pk,xshgbdm,xshgbmc,xh,xm,xb,xydm,xymc,bjdm,bjmc,lxfs,lrrq,bz,kssj,jssj,zymc from view_xyxshgbxx where pk = ?";
		}else if(dataType.equalsIgnoreCase("xljkXshzw")){
			//学生会职务
			colList =  new String[] { "pk","xshgbdm","xshgbmc","xh","xm","xb","xydm","xymc","bjdm","bjmc","lxfs","lrrq","bz","kssj","jssj","zymc"};
			sql = "select pk,xshgbdm,xshgbmc,xh,xm,xb,xydm,xymc,bjdm,bjmc,lxfs,lrrq,bz,kssj,jssj,zymc from view_xljkxshgbxx where pk = ?";
		}
		String rec = dao.getStringToSplit(sql, new String[] {pk}, colList);
		String[] tmp = rec.split("!!SplitSignOne!!");
		if (tmp.length != 2) {
			record = new String[colList.length + 1];
		} else {
			record = tmp[1].split("!!SplitSignTwo!!");
		}
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < colList.length; i++) {
			map.put(colList[i].toLowerCase(), record[i + 1]);
		}
		if(userType.equalsIgnoreCase("xy") && (xy == null || xy.equalsIgnoreCase(""))){
			xy = userDep;
			checkListForm.setXydm(xy);
			map.put("xydm", userDep);
			disabled = false;
		}else{
			checkListForm.setXydm(map.get("xydm"));
		}
		
		//各别判断增加需要的参数
		if(dataType.equalsIgnoreCase("hdzjhb")){
			checkListForm.setFormat(map.get("format"));
			checkListForm.setJyPlotdm(map.get("jyplotdm"));
			request.setAttribute("jyPlotList", dao.getJyPlotList(userDep));
			request.setAttribute("chkList", dao.getChkList(7));
		}else if (dataType.equalsIgnoreCase("zzxxnj")){
			checkListForm.setXxstuffdm(map.get("xxstuffdm"));
			request.setAttribute("xxstuffList", dao.getXxstuffList());
		}else if (dataType.equalsIgnoreCase("shbkjl")){
			checkListForm.setDxdm(map.get("dxdm"));
			request.setAttribute("xxstuffList", dao.getXxstuffList());
			request.setAttribute("penuryHelpList", dao.getPenuryHelpObjectList(userDep));
		}else if (dataType.equalsIgnoreCase("ysjyhdjl")){
			checkListForm.setFormat(map.get("format"));
			request.setAttribute("chkList", dao.getChkList(7));
		}else if (dataType.equalsIgnoreCase("dyst")){
			colList =  new String[] { "pk","damc","danr","xsbj"};
			sql = "select rowid pk,damc,danr,xsbj from sxjy_dytkdab where wtdm = ? order by damc";
			checkListForm.setFormat(map.get("format"));
			Vector<Object> rs = new Vector<Object>(); 
			String [] colListCN = dao.getColumnNameCN(colList, "sxjy_dytkdab");
			List topTr = dao.arrayToList(colList, colListCN);
			rs.addAll(dao.rsToVator(sql, new String[]{pk}, colList));
			request.setAttribute("topTr", topTr);// 发送表头
			request.setAttribute("rs2", rs);// 发送数据集
		}else if(dataType.equalsIgnoreCase("ztjzqksb")||dataType.equalsIgnoreCase("aqfzjyqk")){
			String format = map.get("format");
			if(format==null){
				format = "讲座";
			}
			checkListForm.setFormat(format);
			request.setAttribute("chkList", dao.getChkList(7));
		}else if(dataType.equalsIgnoreCase("fdyxxth")){
			checkListForm.setTutorshipperson(map.get("tutorshipperson"));
			request.setAttribute("tutorshippersonList", dao.getTutorshippersonList(userDep));
			checkListForm.setFormat(map.get("format"));
			request.setAttribute("chkList", dao.getChkList(8));
		}else if(dataType.equalsIgnoreCase("bjzw")){
			checkListForm.setBjgb(map.get("bjgbdm"));
			request.setAttribute("bjgbList", dao.getClassDutyList());
			request.setAttribute("bjzwTypeList", dao.getClassDutyTypeList());
		}else if(dataType.equalsIgnoreCase("xshzw")){
			checkListForm.setBjgb(map.get("xshgbdm"));
			request.setAttribute("xshgbList", dao.getXshGbList());
		}else if(dataType.equalsIgnoreCase("xyXshzw")){
			checkListForm.setBjgb(map.get("xshgbdm"));
			request.setAttribute("xshgbList", dao.getXyXshGbList());
		}else if(dataType.equalsIgnoreCase("xljkXshzw")){
			checkListForm.setBjgb(map.get("xshgbdm"));
			request.setAttribute("xshgbList", dao.getXljkXshGbList());
		}else if(dataType.equalsIgnoreCase("jsgtjl")){
			checkListForm.setTutorshipperson(map.get("zgh"));
			if(map.get("xydm")!=null){
				request.setAttribute("xydm", map.get("xydm"));
			}
			else{
				request.setAttribute("xydm", "");	
			}
			if(map.get("bjdm")!=null){
				request.setAttribute("bjdm", map.get("bjdm"));
			}else{
				request.setAttribute("bjdm", "");	
			}
			request.setAttribute("userType", userType);
			request.setAttribute("tutorshippersonList", dao.getTutorshippersonList(userDep));
			checkListForm.setFormat(map.get("format"));
			request.setAttribute("chkList", dao.getChkList(8));
		}else if(dataType.equalsIgnoreCase("dsyjx")){
			if(!(("updata").equalsIgnoreCase(request.getParameter("doType")))){
			checkListForm.setXydm(userDep);
			}
			request.setAttribute("zgList", dao.getFdyList(userDep));
			String title= DealString.toGBK(request.getParameter("title"));
			String gdsyj= DealString.toGBK(request.getParameter("gdsyj"));
			if(!title.equalsIgnoreCase("")){
			map.put("title", title);
			}
			if(!gdsyj.equalsIgnoreCase("")){
			map.put("gdsyj", gdsyj);
			}
		}
		
		//取学生信息后的覆盖和增加map里的值
		
		String xh= DealString.toGBK(request.getParameter("xh"));	
		//String xh= DealString.toGBK(request.getParameter("stu_id"));	
		if(!xh.equalsIgnoreCase("")){
			colList = new String[]{ "xh","xm","xb","nj","xymc","zymc","bjmc","bjdm","xydm" };
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,bjdm,xydm from view_xsjbxx where xh = ?";
			tmp = dao.getOneRs(sql, new String[] {xh},colList); 
			map.put("xh", tmp[0]);
			map.put("xm", tmp[1]);
			map.put("xb", tmp[2]);
			map.put("nj", tmp[3]);
			map.put("xymc", tmp[4]);
			map.put("zymc", tmp[5]);
			map.put("bjmc", tmp[6]);
			map.put("bjdm", tmp[7]);
			map.put("xydm", tmp[8]);
		}
		//时间判断
		if(dataType.equalsIgnoreCase("hdzjhb")||dataType.equalsIgnoreCase("zzxxnj")||dataType.equalsIgnoreCase("xfjpjl")){
			String xzsj = dao.sxjyQueryOne2("sxjyhz_sjszb", new String []{dataType}, "1", "1")[0];
			String dqsj = GetTime.getNowTime2();
			if(Integer.parseInt(dqsj)<=Integer.parseInt(xzsj)){
				request.setAttribute("sfkxg", "yes");
			}else{
				request.setAttribute("sfkxg", "no");
			}
		}
		
		//		读写权判断(是否有增加权限)
		String modiAble =request.getParameter("modiAble");
		if(null!=modiAble){
				request.setAttribute("modiAble", modiAble);
		}else{
			request.setAttribute("modiAble", "yes");
		}
		
		request.setAttribute("operation", request.getParameter("operation"));
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("disabled", disabled);
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}


	private ActionForward saveWindows(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SxjyDAO dao = new SxjyDAO();
		SxjyForm saveWindowsForm = (SxjyForm) form;
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		String sql="";
		boolean del = false;
		String type =request.getParameter("type");
		HttpSession session = request.getSession();
		String xydm = DealString.toGBK(request.getParameter("xydm"));	
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String puber = session.getAttribute("userName").toString();
		if(userType.equalsIgnoreCase("xy")){
			xydm = userDep;		
		}
		if(type.equalsIgnoreCase("hdzjhb")) {
			sql = "delete from sxjy_hdzjhbb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String place = DealString.toGBK(request.getParameter("place"));
				String kssj = DealString.toGBK(request.getParameter("kssj"));
				String jssj = DealString.toGBK(request.getParameter("jssj"));
				String compere = DealString.toGBK(request.getParameter("compere"));
				String joinstudent = DealString.toGBK(request.getParameter("joinstudent"));
				String format = DealString.toGBK(request.getParameter("format"));
				String jyPlotdm = DealString.toGBK(request.getParameter("jyPlotdm"));
				String motif = DealString.toGBK(request.getParameter("motif"));
				String cont = DealString.toGBK(request.getParameter("cont"));
				String effect = DealString.toGBK(request.getParameter("effect"));
				String reflect = DealString.toGBK(request.getParameter("reflect"));
				String attitude = DealString.toGBK(request.getParameter("attitude"));
				sql = "insert into sxjy_hdzjhbb(xydm,place,kssj,jssj,compere,joinstudent,"
					+ "format,chdm,motif,cont,effect,reflect,attitude)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xydm,place,kssj,jssj,compere,joinstudent,format,jyPlotdm,motif,cont,effect,reflect,attitude });
			} else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("zzxxnj")) {
			sql = "delete from sxjy_zzxxtljlb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String place = DealString.toGBK(request.getParameter("place"));
				String xxsj = DealString.toGBK(request.getParameter("xxsj"));
				String compere = DealString.toGBK(request.getParameter("compere"));
				String studentcount = DealString.toGBK(request.getParameter("studentcount"));
				String xxStuffdm = DealString.toGBK(request.getParameter("xxstuffdm"));
				String motif = DealString.toGBK(request.getParameter("motif"));
				String cont = DealString.toGBK(request.getParameter("cont"));
				String attitude = DealString.toGBK(request.getParameter("attitude"));
				sql = "insert into sxjy_zzxxtljlb(xydm,place,xxsj,compere,studentcount,"
					+ "cldm,motif,cont,attitude)"
					+ "values (?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xydm,place,xxsj,compere,studentcount,xxStuffdm,motif,cont,attitude });
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("xfjpjl")) {
			sql = "delete from sxjy_xfjpjlb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String place = DealString.toGBK(request.getParameter("place"));
				String kssj = DealString.toGBK(request.getParameter("kssj"));
				String jssj = DealString.toGBK(request.getParameter("jssj"));
				String compere = DealString.toGBK(request.getParameter("compere"));
				String studentcount = DealString.toGBK(request.getParameter("studentcount"));
				String format = DealString.toGBK(request.getParameter("format"));
				String scope = DealString.toGBK(request.getParameter("scope"));
				String motif = DealString.toGBK(request.getParameter("motif"));
				String cont = DealString.toGBK(request.getParameter("cont"));
				String effect = DealString.toGBK(request.getParameter("effect"));
				sql = "insert into sxjy_xfjpjlb(xydm,place,kssj,jssj,compere,studentcount,"
					+ "format,scope,motif,cont,effect)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] {xydm,place,kssj,jssj,compere,studentcount,format,scope,motif,cont,effect});
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("swCheck")) {
			sql = "delete from sxjy_xfjcjlb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String place = DealString.toGBK(request.getParameter("place"));
				String kssj = DealString.toGBK(request.getParameter("kssj"));
				String jssj = DealString.toGBK(request.getParameter("jssj"));
				String joinpersonnel = DealString.toGBK(request.getParameter("joinpersonnel"));
				String problem = DealString.toGBK(request.getParameter("problem"));
				String motif = DealString.toGBK(request.getParameter("motif"));
				String checknote = DealString.toGBK(request.getParameter("checknote"));
				String adoptstep = DealString.toGBK(request.getParameter("adoptstep"));
				sql = "insert into sxjy_xfjcjlb(xydm,place,kssj,jssj,joinpersonnel,"
					+ "problem,motif,checknote,adoptstep)"
					+ "values (?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] {xydm,place,kssj,jssj,joinpersonnel,problem,motif,checknote,adoptstep});
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("shbk")) {
			sql = "delete from sxjy_shbkxxgl where dxdm = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String jdsj = DealString.toGBK(request.getParameter("jdsj"));
				String format = DealString.toGBK(request.getParameter("format"));
				String dxmc = DealString.toGBK(request.getParameter("dxmc"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				sql = "insert into sxjy_shbkxxgl(dxdm,xydm,jdsj,format,dxmc,bz)"
					+ "values (COMMUNITY_HELP_ID.nextval,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] {xydm,jdsj,format,dxmc,bz});
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("shbkjl")) {
			sql = "delete from sxjy_shbkhdjl where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String bksj = DealString.toGBK(request.getParameter("bksj"));
				String format = DealString.toGBK(request.getParameter("format"));
				String dxdm = DealString.toGBK(request.getParameter("dxdm"));
				String joinperson = DealString.toGBK(request.getParameter("joinperson"));
				String money = DealString.toGBK(request.getParameter("money"));
				String cont = DealString.toGBK(request.getParameter("cont"));
				String reflect = DealString.toGBK(request.getParameter("reflect"));
				String effect = DealString.toGBK(request.getParameter("effect"));
				sql = "insert into sxjy_shbkhdjl(xydm,bksj,format,dxdm,joinperson,money,cont,reflect,effect)"
					+ "values (?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] {xydm,bksj ,format,dxdm,joinperson,money,cont,reflect,effect});
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("ysjyhdjl")) {
			sql = "delete from sxjy_ysjyhdjlb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String place = DealString.toGBK(request.getParameter("place"));
				String kssj = DealString.toGBK(request.getParameter("kssj"));
				String jssj = DealString.toGBK(request.getParameter("jssj"));
				String compere = DealString.toGBK(request.getParameter("compere"));
				String joinstudent = DealString.toGBK(request.getParameter("joinstudent"));
				String format = DealString.toGBK(request.getParameter("format"));
				String motif = DealString.toGBK(request.getParameter("motif"));
				String cont = DealString.toGBK(request.getParameter("cont"));
				String reflect = DealString.toGBK(request.getParameter("reflect"));
				String attitude = DealString.toGBK(request.getParameter("attitude"));
				sql = "insert into sxjy_ysjyhdjlb(xydm,place,kssj,jssj,compere,joinstudent,"
					+ "format,motif,cont,reflect,attitude)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xydm,place,kssj,jssj,compere,joinstudent,format,motif,cont,reflect,attitude });
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("dywj")) {
			sql = "delete from sxjy_dywjb where dywjdm = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String dywjmc = DealString.toGBK(request.getParameter("dywjmc"));
				String xsbj = DealString.toGBK(request.getParameter("xsbj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				if(pk.equalsIgnoreCase("")){
					sql = "insert into sxjy_dywjb(dywjdm,dywjmc,puber,xsbj,bz)"
						+ "values (RESEARCH_TESTPAER_ID.nextval,?,?,?,?)";
					dao.runUpdate(sql, new String[] { dywjmc,puber,xsbj,bz });
				}else{
					sql = "insert into sxjy_dywjb(dywjdm,dywjmc,puber,xsbj,bz)"
						+ "values (?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {pk,dywjmc,puber,xsbj,bz });
				}
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("dyst")) {
			sql = "delete from sxjy_dytkb where wtdm = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String wtmc = DealString.toGBK(request.getParameter("wtmc"));
				String wtda = DealString.toGBK(request.getParameter("wtda"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				if(pk.equalsIgnoreCase("")){
					sql = "insert into sxjy_dytkb(wtdm,wtmc,puber,wtda,bz)"
						+ "values (RESEARCH_QUESTIONS_ID.nextval,?,?,?,?)";
					dao.runUpdate(sql, new String[] { wtmc,puber,wtda,bz });
				}else{
					sql = "insert into sxjy_dytkb(wtdm,wtmc,puber,wtda,bz)"
						+ "values (?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {pk,wtmc,puber,wtda,bz });
				}
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("jystda")) {
			sql = "delete from sxjy_dytkdab where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String damc = DealString.toGBK(request.getParameter("damc"));
				String danr = DealString.toGBK(request.getParameter("danr"));
				String xsbj = DealString.toGBK(request.getParameter("xsbj"));
				String wtdm = DealString.toGBK(request.getParameter("wtdm"));
				sql = "insert into sxjy_dytkdab(damc,danr,xsbj,wtdm)"
					+ "values (?,?,?,?)";
				dao.runUpdate(sql, new String[] { damc,danr,xsbj,wtdm});
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("bjgbsz")) {
			sql = "delete from sxjy_bjgbzlb where bjgbdm = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String bjgbmc = DealString.toGBK(request.getParameter("bjgbmc"));
				String zr = DealString.toGBK(request.getParameter("zr"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				if(pk.equalsIgnoreCase("")){
					sql = "insert into sxjy_bjgbzlb(bjgbdm,bjgbmc,zr,bz)"
						+ "values (S_STUGBZW_ID.nextval,?,?,?)";
					dao.runUpdate(sql, new String[] { bjgbmc,zr,bz });
				}else{
					sql = "insert into sxjy_bjgbzlb(bjgbdm,bjgbmc,zr,bz)"
						+ "values (?,?,?,?)";
					dao.runUpdate(sql, new String[] {pk,bjgbmc,zr,bz });
				}
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("bjzw")) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			String bjgb = DealString.toGBK(request.getParameter("bjgb"));
			String lxfs = DealString.toGBK(request.getParameter("lxfs"));
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String kssj = DealString.toGBK(request.getParameter("kssj"));
			String jssj = DealString.toGBK(request.getParameter("jssj"));
			sql = "delete from sxjy_bjgbxxb where rowid=?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				sql = "insert into sxjy_bjgbxxb(xh,bjgbdm,bjdm,lxfs,bz,kssj,jssj)"
					+ "values (?,?,?,?,?,?,?)";
				Boolean update = dao.runUpdate(sql, new String[] { xh,bjgb,bjdm,lxfs,bz,kssj,jssj});
				if(update){
					request.setAttribute("inserted", "ok");
					return mapping.findForward("success");
				}
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("tfsjjl")) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			String fssj = DealString.toGBK(request.getParameter("fssj"));
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			String linkman = DealString.toGBK(request.getParameter("linkman"));
			String cont = DealString.toGBK(request.getParameter("cont"));
			String report = DealString.toGBK(request.getParameter("report"));
			String disposal = DealString.toGBK(request.getParameter("disposal"));
			sql = "delete from sxjy_tfsjjlb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				sql = "insert into sxjy_tfsjjlb(xh,fssj,lxdh,linkman,cont,report,disposal)"
					+ "values (?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xh,fssj,lxdh,linkman,cont,report,disposal });
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("fdyxxth")) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			String place = DealString.toGBK(request.getParameter("place"));
			String fssj = DealString.toGBK(request.getParameter("fssj"));
			String kssj = DealString.toGBK(request.getParameter("kssj"));
			String jssj = DealString.toGBK(request.getParameter("jssj"));
			String format = DealString.toGBK(request.getParameter("format"));
			String knowside = DealString.toGBK(request.getParameter("knowside"));
			String cause = DealString.toGBK(request.getParameter("cause"));
			String cont = DealString.toGBK(request.getParameter("cont"));
			String commoncognition = DealString.toGBK(request.getParameter("commoncognition"));
			String tracknote = DealString.toGBK(request.getParameter("tracknote"));
			String tutorshipperson = DealString.toGBK(request.getParameter("tutorshipperson"));
			sql = "delete from sxjy_fdyxxthjlb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				sql = "insert into sxjy_fdyxxthjlb(xh,place,tutorshipperson,fssj,kssj,jssj,contactformat,knowside,cause,cont,commoncognition,tracknote)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xh,place,tutorshipperson,fssj,kssj,jssj,format,knowside,cause,cont,commoncognition,tracknote });
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("jsgtjl")) {
			String zgh = DealString.toGBK(request.getParameter("tutorshipperson"));
			String place = DealString.toGBK(request.getParameter("place"));
			String fssj = DealString.toGBK(request.getParameter("fssj"));
			String kssj = DealString.toGBK(request.getParameter("kssj"));
			String jssj = DealString.toGBK(request.getParameter("jssj"));
			String format = DealString.toGBK(request.getParameter("format"));
			String cont = DealString.toGBK(request.getParameter("cont"));
			String bjdm = DealString.toGBK(request.getParameter("bj"));
			String commoncognition = DealString.toGBK(request.getParameter("commoncognition"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			sql = "delete from sxjy_jsgtjlb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				sql = "insert into sxjy_jsgtjlb(zgh,place,fssj,kssj,jssj,contactformat,cont,commoncognition,bz,bjdm)"
					+ "values (?,?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { zgh,place,fssj,kssj,jssj,format,cont,commoncognition,bz,bjdm });
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("ztjzqksb")) {
			sql = "delete from sxjy_ztjzqksbb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String place = DealString.toGBK(request.getParameter("place"));
				String kssj = DealString.toGBK(request.getParameter("kssj"));
				String jssj = DealString.toGBK(request.getParameter("jssj"));
				String compere = DealString.toGBK(request.getParameter("compere"));
				String joinstudent = DealString.toGBK(request.getParameter("joinstudent"));
				String format = DealString.toGBK(request.getParameter("format"));
				String motif = DealString.toGBK(request.getParameter("motif"));
				String cont = DealString.toGBK(request.getParameter("cont"));
				String effect = DealString.toGBK(request.getParameter("effect"));
				String reflect = DealString.toGBK(request.getParameter("reflect"));
				String attitude = DealString.toGBK(request.getParameter("attitude"));
				sql = "insert into sxjy_ztjzqksbb(xydm,place,kssj,jssj,compere,joinstudent,"
					+ "format,motif,cont,effect,reflect,attitude)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xydm,place,kssj,jssj,compere,joinstudent,format,motif,cont,effect,reflect,attitude });
			} else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("aqfzjyqk")) {
			sql = "delete from sxjy_aqfzjyqkb where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String place = DealString.toGBK(request.getParameter("place"));
				String kssj = DealString.toGBK(request.getParameter("kssj"));
				String jssj = DealString.toGBK(request.getParameter("jssj"));
				String compere = DealString.toGBK(request.getParameter("compere"));
				String joinstudent = DealString.toGBK(request.getParameter("joinstudent"));
				String format = DealString.toGBK(request.getParameter("format"));
				String motif = DealString.toGBK(request.getParameter("motif"));
				String cont = DealString.toGBK(request.getParameter("cont"));
				String effect = DealString.toGBK(request.getParameter("effect"));
				String reflect = DealString.toGBK(request.getParameter("reflect"));
				String attitude = DealString.toGBK(request.getParameter("attitude"));
				sql = "insert into sxjy_aqfzjyqkb(xydm,place,kssj,jssj,compere,joinstudent,"
					+ "format,motif,cont,effect,reflect,attitude)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xydm,place,kssj,jssj,compere,joinstudent,format,motif,cont,effect,reflect,attitude });
			} else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("dsyjx")) {
			sql = "delete from szdw_dsyjx where rowid = ?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				String zgh = DealString.toGBK(request.getParameter("zxm"));
				String title = DealString.toGBK(request.getParameter("title"));
				String gdsyj = DealString.toGBK(request.getParameter("gdsyj"));
				sql = "insert into szdw_dsyjx(zgh,gdsyj,title,puber)"
					+ "values (?,?,?,?)";
				dao.runUpdate(sql, new String[] { zgh,gdsyj,title,puber });
			} else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("xshzw")) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			String xshgbdm = DealString.toGBK(request.getParameter("bjgb"));
			String lxfs = DealString.toGBK(request.getParameter("lxfs"));
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String kssj = DealString.toGBK(request.getParameter("kssj"));
			String jssj = DealString.toGBK(request.getParameter("jssj"));
			sql = "delete from sxjy_xshgbxxb where rowid=?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				sql = "insert into sxjy_xshgbxxb(xh,xshgbdm,bjdm,lxfs,bz,kssj,jssj)"
					+ "values (?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xh,xshgbdm,bjdm,lxfs,bz,kssj,jssj});
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("xyXshzw")) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			String xshgbdm = DealString.toGBK(request.getParameter("bjgb"));
			String lxfs = DealString.toGBK(request.getParameter("lxfs"));
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String kssj = DealString.toGBK(request.getParameter("kssj"));
			String jssj = DealString.toGBK(request.getParameter("jssj"));
			sql = "delete from sxjy_xyxshgbxxb where rowid=?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				sql = "insert into sxjy_xyxshgbxxb(xh,xshgbdm,bjdm,lxfs,bz,kssj,jssj)"
					+ "values (?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] {xh,xshgbdm,bjdm,lxfs,bz,kssj,jssj});
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else if(type.equalsIgnoreCase("xljkXshzw")) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			String xshgbdm = DealString.toGBK(request.getParameter("bjgb"));
			String lxfs = DealString.toGBK(request.getParameter("lxfs"));
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String kssj = DealString.toGBK(request.getParameter("kssj"));
			String jssj = DealString.toGBK(request.getParameter("jssj"));
			sql = "delete from sxjy_xljkxshgbxxb where rowid=?";
			del = dao.runUpdate(sql, new String[] { pk });
			if(del){
				sql = "insert into sxjy_xljkxshgbxxb(xh,xshgbdm,bjdm,lxfs,bz,kssj,jssj)"
					+ "values (?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xh,xshgbdm,bjdm,lxfs,bz,kssj,jssj});
			}else {
				saveWindowsForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}else {
			saveWindowsForm.setErrMsg("sdf");
			return mapping.findForward("false");
		}

		return null;
	}


	private ActionForward checkdis(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SxjyDAO dao = new SxjyDAO();
		boolean disabled = true;
		Vector<Object> rs = new Vector<Object>(); 
		String[] colList = null;
		String[] colListCN = null;
		String[] inputList =null;
		String writeAble = "yes";
		ActionForward forward = null;
		SxjyForm checkListForm = (SxjyForm) form;
		String xy = checkListForm.getXydm();
		if (xy==null){
			xy = "";
		}
		if (xy.equalsIgnoreCase("")){
			xy = DealString.toGBK(request.getParameter("xy"));
		}
		if (xy==null){
			xy = "";
		}
		String dataType = request.getParameter("act");
		if (dataType==null){
			dataType = "";
		}
		String month = checkListForm.getMonth();
		String year = checkListForm.getYear();
		//String time = GetTime.getTime(year, month);
		String time = StringUtil.isNull(request.getParameter("data")) ? "%" : request.getParameter("data");
		checkListForm.setMonth(month);
		checkListForm.setYear(year);

		String xh = DealString.toGBK(checkListForm.getXh());
		String nj = DealString.toGBK(checkListForm.getNj());
		
		String zy = DealString.toGBK(checkListForm.getZy());
		if (zy==null){
			zy = "";
		}
		if (zy.equalsIgnoreCase("")){
			zy = DealString.toGBK(request.getParameter("zy"));
		}
		if (zy==null){
			zy = "";
		}
		
		String bj = DealString.toGBK(checkListForm.getBj());
		checkListForm.setXy(xy);

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= "";
			}
			writeAble = "no";
		}
		String sql="";
		sql = "select to_char(sysdate,'yyyy') nowYear from dual";
		String nowYear = dao.getOneRs(sql, new String[]{}, new String[]{"nowYear"})[0];
		if(userType.equalsIgnoreCase("xy") && (xy == null || xy.equalsIgnoreCase(""))){
			xy = userDep;
			checkListForm.setXydm(xy);
			disabled = false;
		}
		String stuQuery = "where (xydm = ? or ? = ' ') and (bjdm = ? or ? = ' ')" +
		" and (xh like '%'||?||'%' or ? = ' ') and (nj = ? or ? = ' ') and (zydm = ? or ? = ' ') and (lrrq like ?)";
		
		
		year = StringUtils.isNull(year) ? "%" : year;
		month = StringUtils.isNull(month) ? "%" : month;
		
		String data = year+"年_"+month+"月"+"%"+"日";
		
		String [] stuInputList = new String[] {xy,xy+" ",bj,bj +" ",xh,xh+" ",nj,nj+" ",zy,zy+" ",time};
		String [] stuInputList1 = new String[] {xy,xy+" ",bj,bj +" ",xh,xh+" ",nj,nj+" ",zy,zy+" ",data};
		String [] writeableArray=null;
		
		if(dataType.equalsIgnoreCase("hdzjhb")){
			//活动总结汇报'
			String jyhddm = request.getParameter("jyPlotdm");
			inputList = new String[] {userDep,userDep+" ", time, jyhddm,jyhddm+" "};
			colList = new String[] { "pk","motif", "compere", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_hdzjhbb");
			sql = "select rowid pk,motif,compere,lrrq from sxjy_hdzjhbb where (xydm = ? or ? = ' ') and (lrrq like ?) and (chdm = ? or ? = ' ')";
			rs.addAll(dao.rsToVator(sql, inputList, colList));

			request.setAttribute("jyPlotList", dao.getJyPlotList(userDep));
			request.setAttribute("type", "hdzjhb");
			forward = new ActionForward("/sxjy/Jyhd_zjhb.jsp");
		}else if(dataType.equalsIgnoreCase("zzxxnj")){
			//政治学习记录
			colList = new String[] { "pk","motif", "compere", "lrrq"};

			colListCN = dao.getColumnNameCN(colList, "sxjy_hdzjhbb");
			String xxstuffdm = request.getParameter("xxstuffdm");
			inputList = new String[] {userDep,userDep+" ", time, xxstuffdm,xxstuffdm+" "};
			sql = "select a.rowid pk,a.motif,a.compere,a.lrrq from sxjy_zzxxtljlb a left join view_njxyzybj b on a.bjdm = b.bjdm where (a.xydm = "
				+"? or ? = ' ') and (a.lrrq like ?) and (a.cldm = ? or ? = ' ')";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			if(userType.equalsIgnoreCase("xy")){
				request.setAttribute("zyList", (Base.getZyMap()).get(userDep));
			}
			request.setAttribute("xxstuffList", dao.getXxstuffList());
			request.setAttribute("type", "zzxxnj");
			forward = new ActionForward("/sxjy/Zzxx_note.jsp");
		}else if(dataType.equalsIgnoreCase("xfjpjl")){
			//学风讲评记录
			colList = new String[] { "pk","motif", "compere", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_xfjpjlb");
			inputList = new String[] {userDep,userDep+" ", time};
			sql = "select rowid pk,motif,compere,lrrq from sxjy_xfjpjlb where (xydm = ? or ? = ' ') and (lrrq like ?)";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "hdzjhb");
			forward = new ActionForward("/sxjy/Xfjs_xfjp.jsp");
		}else if(dataType.equalsIgnoreCase("tfsjjl")){
			//突发事件处理记录
			colList = new String[] { "pk","xm", "xymc", "bjmc","lrrq"};
			colListCN = dao.getColumnNameCN(colList, "view_sxjy_xstfsj");
			
			sql = "select pk,xm,xymc,bjmc,lrrq from view_sxjy_xstfsj "+stuQuery;
			rs.addAll(dao.rsToVator(sql, stuInputList, colList));
			request.setAttribute("xydm", xy);
			request.setAttribute("type", "tfsjjl");
			forward = new ActionForward("/sxjy/Xfjs_tfsj.jsp");
		}else if(dataType.equalsIgnoreCase("swCheck")){
			//学风检查
			colList = new String[] { "pk","motif", "place", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_xfjcjlb");
			inputList = new String[] {userDep,userDep+" ", time};
			sql = "select rowid pk,motif,place,lrrq from sxjy_xfjcjlb where (xydm = ? or ? = ' ') and (lrrq like ?)";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "swCheck");
			forward = new ActionForward("/sxjy/StudyWind_check.jsp");
		}else if(dataType.equalsIgnoreCase("shbk")){
			//社会帮困活动
			colList = new String[] { "pk","dxmc", "jdsj", "format"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_shbkxxgl");
			inputList = new String[] {userDep,userDep+" ", time};
			sql = "select dxdm pk,dxmc,jdsj,format from sxjy_shbkxxgl where (xydm = ? or ? = ' ') and (lrrq like ?)";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "shbk");
			forward = new ActionForward("/sxjy/PenuryHelp_info.jsp");
		}else if(dataType.equalsIgnoreCase("shbkjl")){
			//社会帮困活动记录管理
			colList = new String[] { "pk","dxmc", "bksj", "money"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_shbkhdjl");
			inputList = new String[] {userDep,userDep+" ", time};
			sql = "select pk,dxmc,bksj,money from view_sxjy_shbkhdjl where (xydm = ? or ? = ' ') and (lrrq like ?)";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "shbkjl");
			request.setAttribute("penuryHelpList", dao.getPenuryHelpObjectList(userDep));
			forward = new ActionForward("/sxjy/PenuryHelp_note.jsp");
		}else if(dataType.equalsIgnoreCase("ysjyhdjl")){
			//艺术教育活动记录管理
			colList = new String[] { "pk","motif", "compere", "lrrq" };
			colListCN = dao.getColumnNameCN(colList, "sxjy_ysjyhdjlb");
			inputList = new String[] {userDep,userDep+" ", time};
			sql = "select rowid pk,motif,compere,lrrq from sxjy_ysjyhdjlb where (xydm = ? or ? = ' ') and (lrrq like ?)";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "ysjyhdjl");
			forward = new ActionForward("/sxjy/ArtEducate_note.jsp");
		}else if(dataType.equalsIgnoreCase("dywj")){
			//调研问卷
			colList = new String[] { "pk","dywjmc", "lrrq", "xsbj" };
			colListCN = dao.getColumnNameCN(colList, "sxjy_dywjb");
			inputList = new String[] {time};
			sql = "select dywjdm pk,dywjmc,lrrq,xsbj from sxjy_dywjb where lrrq like ?";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "dywj");
			request.setAttribute("testPageList", dao. getResearchTestPageList());
			forward = new ActionForward("/sxjy/Research_testPaper.jsp");
		}else if(dataType.equalsIgnoreCase("dyst")){
			//调研问卷试题维护
			colList = new String[] { "pk","wtmc","wtda","lrrq" };
			colListCN = dao.getColumnNameCN(colList, "sxjy_dytkb");
			inputList = new String[] {time};
			sql = "select wtdm pk,wtmc,wtda,lrrq from sxjy_dytkb where lrrq like ?";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "dyst");
			forward = new ActionForward("/sxjy/Research_questionDepot.jsp");
		}else if(dataType.equalsIgnoreCase("dywjlr")){
			//调研问卷录入选择问卷
			colList = new String[] { "pk","dywjmc","puber","lrrq" };
			colListCN = dao.getColumnNameCN(colList, "sxjy_dywjb");
			inputList = new String[] {time};
			sql = "select dywjdm  pk,dywjmc,puber,lrrq from sxjy_dywjb where lrrq like ? and xsbj = '是'";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "dyst");
			forward = new ActionForward("/sxjy/Research_testPaper_choose.jsp");
		}else if(dataType.equalsIgnoreCase("ztjzqksb")){
			//专题讲座情况上报
			inputList = new String[] {userDep,userDep+" ", time};
			colList = new String[] { "pk","motif", "compere", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_ztjzqksbb");
			sql = "select rowid pk,motif,compere,lrrq from sxjy_ztjzqksbb where (xydm = ? or ? = ' ') and (lrrq like ?)";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "ztjzqksb");
			forward = new ActionForward("/sxjy/SpecialChair_note.jsp");
		}else if(dataType.equalsIgnoreCase("aqfzjyqk")){
			//专题讲座情况上报
			inputList = new String[] {userDep,userDep+" ", time};
			colList = new String[] { "pk","motif", "compere", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_aqfzjyqkb");
			sql = "select rowid pk,motif,compere,lrrq from sxjy_aqfzjyqkb where (xydm = ? or ? = ' ') and (lrrq like ?)";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("type", "aqfzjyqk");
			forward = new ActionForward("/sxjy/aqfzjyqk.jsp");
		}else if(dataType.equalsIgnoreCase("fdyxxth")){
			//学生谈话记录
			colList = new String[] { "pk","xh","xm","tutorshipperson","count", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "view_fdyxxthjl");
			sql = "select pk,xh,xm,tutorshipperson,count,lrrq from view_fdyxxthjl " + stuQuery;
			rs.addAll(dao.rsToVator(sql, stuInputList1, colList));
			request.setAttribute("xydm", xy);
			request.setAttribute("type", "fdyxxth");
			
			if("xy".equalsIgnoreCase(userType)){
				checkListForm.setXy(userDep);
			}
			request.setAttribute("path", "show_counsellorAndStuSpeak_note.do?act=fdyxxth&part=N020711");
			FormModleCommon.commonRequestSet(request);
			forward = new ActionForward("/sxjy/CounsellorAndStuSpeak_note.jsp");
		}else if(dataType.equalsIgnoreCase("bjzw")){
			//班级学生干部队伍
			String isFdy=session.getAttribute("isFdy").toString();
			String fdyQx = session.getAttribute("fdyQx") != null ? String.valueOf(session.getAttribute("fdyQx")) : "";
			if("true".equalsIgnoreCase(isFdy)){
				isFdy=fdyQx;
			}
			String userName=session.getAttribute("userName").toString();
			String zwdm = DealString.toGBK(request.getParameter("zwdm"));
			String fzpd = "";
			String xm =  DealString.toGBK(request.getParameter("xm"));
			String jbdm = DealString.toGBK(checkListForm.getJbdm());
			if(request.getParameter("go")==null) {
				fzpd= " and 1 = 2";
			}
			stuQuery = "where (xydm = ? or ? = ' ') and (bjdm = ? or ? = ' ')" +
			"  and (nj = ? or ? = ' ') and (zydm = ? or ? = ' ') and (bjgbdm = ? or ? = ' ') and (gbzljb = ? or ? = ' ')";
			if(xh!=null && !xh.equalsIgnoreCase("")){
				stuQuery+= " and xh like '%";
				stuQuery+= xh;
				stuQuery+= "%'";
			}
			
			if(xm!=null&&!xm.equalsIgnoreCase("")) {
				stuQuery+= " and xm like '%";
				stuQuery+= xm;
				stuQuery+= "%'";
			}
			
			if("true".equalsIgnoreCase(isFdy)){
				stuQuery+= " and exists(select 1 from fdybjb a where a.bjdm=b.bjdm and a.zgh='"+userName+"' ) ";
			}
			
			String xxdm = StandardOperation.getXxdm();
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
				String rzqk = checkListForm.getRzqk();
				if(!Base.isNull(rzqk)){
					if("yes".equalsIgnoreCase(rzqk)){
						stuQuery+=" and ((kssj <= to_char(sysdate, 'yyyymmdd') and jssj >= to_char(sysdate, 'yyyymmdd')) or jssj is null)";
					}else{
						stuQuery+=" and (kssj > to_char(sysdate, 'yyyymmdd') or jssj < to_char(sysdate, 'yyyymmdd'))";
					}
				}
			}
			stuInputList = new String[] {xy,xy+" ",bj,bj +" ",nj,nj+" ",zy,zy+" ",zwdm,zwdm+" ",jbdm,jbdm+" "};
		
			colList = new String[] { "pk","bjgbmc","gbjbmc","xh","xm","nj","xymc","zymc","bjmc","lxfs"};
			colListCN = dao.getColumnNameCN(colList, "view_bjgbxx");
			colListCN[0]="";
			sql = "select rownum r,a.* from(" ;
			sql+=" select pk,bjgbmc,gbjbmc,xh,xm,nj,xymc,zymc,bjmc,lxfs from view_bjgbxx b "+stuQuery+fzpd;
			sql+=" )a ";
			List<String[]>rsList=CommonQueryDAO.commonQuery(sql, "", stuInputList, colList, checkListForm);
			//rs.addAll(dao.rsToVator(sql, stuInputList, colList));
			//System.out.println(sql);
			request.setAttribute("bjzwList", dao.getClassDutyList());
			request.setAttribute("bjzwTypeList", dao.getClassDutyTypeList());
			request.setAttribute("nj", nj);
			request.setAttribute("xydm", xy);
			request.setAttribute("zydm", zy);// 发送数据集
			request.setAttribute("bjdm", bj);// 发送数据集
			request.setAttribute("type", "bjrsgb");
			checkListForm.setXm(xm);
			checkListForm.setXh(xh);
			request.setAttribute("rsList", rsList);
			request.setAttribute("path", "show_classStudentCadre_list.do?act=bjzw");
			writeableArray=FormModleCommon.getWriteAbleAndTitleTwo(request);
			forward = new ActionForward("/szdw/ClassStudentCadre.jsp");
		}else if(dataType.equalsIgnoreCase("xshzw")){
			//盐城师范,金华职业  学生会干部队伍
			String zwdm = DealString.toGBK(request.getParameter("zwdm"));
			String fzpd = "";
			String xm =  DealString.toGBK(request.getParameter("xm"));
			if(request.getParameter("go")==null) {
				fzpd= " and 1 = 2";
			}
			if(xm!=null&&!xm.equalsIgnoreCase("")) {
				stuQuery+= " and xm like '%";
				stuQuery+= xm;
				stuQuery+= "%'";
			}
			stuQuery = "where (xydm = ? or ? = ' ') and (bjdm = ? or ? = ' ')" +
			" and (xh = ? or ? = ' ') and (nj = ? or ? = ' ') and (zydm = ? or ? = ' ') and (xshgbdm = ? or ? = ' ')";
			stuInputList = new String[] {xy,xy+" ",bj,bj +" ",xh,xh+" ",nj,nj+" ",zy,zy+" ",zwdm,zwdm+" "};
			colList = new String[] { "pk","xshgbmc","xm","bjmc","lxfs"};
			colListCN = dao.getColumnNameCN(colList, "view_xshgbxx");
			sql = "select pk,xshgbmc,xm,bjmc,lxfs from view_xshgbxx "+stuQuery+fzpd;
			rs.addAll(dao.rsToVator(sql, stuInputList, colList));
			request.setAttribute("bjzwList", dao.getXshGbList());
			request.setAttribute("xydm", xy);
			request.setAttribute("zydm", zy);// 发送数据集
			request.setAttribute("bjdm", bj);// 发送数据集
			String bjKey = xy + "!!" + zy + "!!" + nj;
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(xy));
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
			request.setAttribute("type", "bjrsgb");
			checkListForm.setXm(xm);
			checkListForm.setXh(xh);
			forward = new ActionForward("/szdw/jsyc/xshgb.jsp");
		}else if(dataType.equalsIgnoreCase("xyXshzw")){
			//金华职业  学院学生会干部队伍
			String zwdm = DealString.toGBK(request.getParameter("zwdm"));
			String fzpd = "";
			String xm =  DealString.toGBK(request.getParameter("xm"));
			if(request.getParameter("go")==null) {
				fzpd= " and 1 = 2";
			}
			if(xm!=null&&!xm.equalsIgnoreCase("")) {
				stuQuery+= " and xm like '%";
				stuQuery+= xm;
				stuQuery+= "%'";
			}
			stuQuery = "where (xydm = ? or ? = ' ') and (bjdm = ? or ? = ' ')" +
			" and (xh = ? or ? = ' ') and (nj = ? or ? = ' ') and (zydm = ? or ? = ' ') and (xshgbdm = ? or ? = ' ')";
			stuInputList = new String[] {xy,xy+" ",bj,bj +" ",xh,xh+" ",nj,nj+" ",zy,zy+" ",zwdm,zwdm+" "};
			colList = new String[] { "pk","xshgbmc","xm","bjmc","lxfs"};
			colListCN = dao.getColumnNameCN(colList, "view_xyxshgbxx");
			sql = "select pk,xshgbmc,xm,bjmc,lxfs from view_xyxshgbxx "+stuQuery+fzpd;
			rs.addAll(dao.rsToVator(sql, stuInputList, colList));
			request.setAttribute("bjzwList", dao.getXyXshGbList());
			request.setAttribute("xydm", xy);
			request.setAttribute("zydm", zy);// 发送数据集
			request.setAttribute("bjdm", bj);// 发送数据集
			String bjKey = xy + "!!" + zy + "!!" + nj;
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(xy));
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
			request.setAttribute("type", "bjrsgb");
			checkListForm.setXm(xm);
			checkListForm.setXh(xh);
			forward = new ActionForward("/szdw/jhzy/xyXshgb.jsp");
		}else if(dataType.equalsIgnoreCase("xljkXshzw")){
			//金华职业 心里健康学生会干部队伍
			String zwdm = DealString.toGBK(request.getParameter("zwdm"));
			String fzpd = "";
			String xm =  DealString.toGBK(request.getParameter("xm"));
			if(request.getParameter("go")==null) {
				fzpd= " and 1 = 2";
			}
			if(xm!=null&&!xm.equalsIgnoreCase("")) {
				stuQuery+= " and xm like '%";
				stuQuery+= xm;
				stuQuery+= "%'";
			}
			stuQuery = "where (xydm = ? or ? = ' ') and (bjdm = ? or ? = ' ')" +
			" and (xh = ? or ? = ' ') and (nj = ? or ? = ' ') and (zydm = ? or ? = ' ') and (xshgbdm = ? or ? = ' ')";
			stuInputList = new String[] {xy,xy+" ",bj,bj +" ",xh,xh+" ",nj,nj+" ",zy,zy+" ",zwdm,zwdm+" "};
			colList = new String[] { "pk","xshgbmc","xm","bjmc","lxfs"};
			colListCN = dao.getColumnNameCN(colList, "view_xljkxshgbxx");
			sql = "select pk,xshgbmc,xm,bjmc,lxfs from view_xljkxshgbxx "+stuQuery+fzpd;
			rs.addAll(dao.rsToVator(sql, stuInputList, colList));
			request.setAttribute("bjzwList", dao.getXljkXshGbList());
			request.setAttribute("xydm", xy);
			request.setAttribute("zydm", zy);// 发送数据集
			request.setAttribute("bjdm", bj);// 发送数据集
			String bjKey = xy + "!!" + zy + "!!" + nj;
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(xy));
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
			request.setAttribute("type", "bjrsgb");
			checkListForm.setXm(xm);
			checkListForm.setXh(xh);
			forward = new ActionForward("/szdw/jhzy/xljkXshgb.jsp");
		}else if(dataType.equalsIgnoreCase("bjgbsz")){
			//学生干部设置
			colList = new String[] { "bjgbdm","bjgbmc","zr"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_bjgbzlb");
			sql = "select bjgbdm,bjgbmc,zr from sxjy_bjgbzlb";
			rs.addAll(dao.rsToVator(sql, new String[]{}, colList));
			request.setAttribute("type", "bjgbsz");
			request.setAttribute("path", "show_classDutyConfig_list.do?act=bjgbsz&part=N170304");
			forward = new ActionForward("/szdw/ClassDutyConfig.jsp");
		}else if(dataType.equalsIgnoreCase("jsgtjl")){
			//与教师沟通记录
			String tutorshipperson = request.getParameter("tutorshipperson");
			colList = new String[] { "pk","xm","bjmc","place","count", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "view_jsgtjlb");
			inputList = new String[] {userDep,userDep+" ", data,tutorshipperson,tutorshipperson+" "};
			sql = "select  pk,xm,bjmc,place,count,lrrq from view_jsgtjlb where (bmdm = ? or ? = ' ') and (lrrq like ?) and (zgh = ? or ? = ' ')";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			request.setAttribute("tutorshippersonList", dao.getTutorshippersonList(userDep));
			request.setAttribute("type", "jsgtjl");
			request.setAttribute("path", "show_counsellorCommunicate_note.do?act=jsgtjl&part=N021110");
			String[] writeAbleAndTitle = FormModleCommon.getWriteAbleAndTitle(request);
			request.setAttribute("writeAble", writeAbleAndTitle[0]);
			forward = new ActionForward("/sxjy/CounsellorCommunicate_note.jsp");
		}else if(dataType.equalsIgnoreCase("dsyjx")){
			//导师意见箱
			String zgh = request.getParameter("zxm");
			String go = request.getParameter("go");
			
			colList = new String[] { "pk","xm","title","puber","lrrq" };
			colListCN = dao.getColumnNameCN(colList, "view_szdw_dsyjx");
			if ((go != null) && go.equalsIgnoreCase("go")) {
			inputList = new String[] {userDep,userDep+" ",zgh,zgh+" ",time};
			sql = "select pk,xm,title,puber,lrrq from view_szdw_dsyjx where (xydm = ? or ? = ' ') and (zgh = ? or ? = ' ') and lrrq like ? ";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			}
			checkListForm.setXydm(xy);
			request.setAttribute("zgList", dao.getFdyList(userDep));
			request.setAttribute("type", "dsyjx");
			forward = new ActionForward("/szdw/TeacherAttitudeBox.jsp");
		}
		if(dataType.equalsIgnoreCase("hdzjhb")||dataType.equalsIgnoreCase("zzxxnj")||dataType.equalsIgnoreCase("xfjpjl")){
			String []sxsj=dao.sxjyQueryOne2("sxjyhz_sjszb", new String []{dataType}, "1", "1");
			String xzsj = (sxsj == null || sxsj.length == 0 ) ?  "0" : (sxsj[0] != null ? sxsj[0] : "0" ) ;
			String dqsj = GetTime.getNowTime2();
			if(Integer.parseInt(dqsj)<=Integer.parseInt(xzsj)){
				request.setAttribute("sfkxg", "yes");
			}else{
				request.setAttribute("sfkxg", "no");
			}
		}
		//读写权判断(是否有增加权限)
		String part=request.getParameter("part");
		String puber = session.getAttribute("userName").toString();
		sql = "select dxq from yhqxb where gnmkdm = ? and yhm = ?";
		String[] tmp1 = dao.getOneRs(sql, new String[] {part,puber}, new String[] {"dxq"});
		String tmp2=request.getParameter("modiAble");
		if(tmp2==null||tmp2.equalsIgnoreCase("")) {
			if(null!=tmp1){
				if(tmp1[0].equals("1")){
					request.setAttribute("modiAble", "yes");
				}else{
					request.setAttribute("modiAble", "no");
				}
			}
		}
			else {
				request.setAttribute("modiAble", tmp2);
			}
		List topTr = dao.arrayToList(colList, colListCN);
		
		String[] writeAbleAndTitle = FormModleCommon.getWriteAbleAndTitle(request);
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("yearList", GetTime.getYearList(5));
		request.setAttribute("chkList", dao.getChkList(6));
		request.setAttribute("nowYear", nowYear);
		//该读写权是与用户类型相关的；
		
		FormModleCommon.commonRequestSet(request);
		
		if(writeableArray==null || writeableArray.length==0){
			request.setAttribute("writeAble", writeAbleAndTitle[0]);
		}else{
			String title=writeableArray[1];
			writeAble=writeableArray[0];
			request.setAttribute("title", title);
			request.setAttribute("writeAble", writeAble);
		}
		request.setAttribute("disabled", disabled);
		request.setAttribute("xydm", xy);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs.size());// 发送记录数
		request.setAttribute("tableName", "view_bjgbxx");
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return forward;
	}


	private ActionForward checkList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		SxjyDAO dao = new SxjyDAO();
		boolean disabled = true;
		Vector<Object> rs = new Vector<Object>(); 
		String[] colList = null;
		String[] inputList = null;
		String[] colListCN = null;
		String writeAble = "yes";
		SxjyForm checkListForm = (SxjyForm) form;
		String xy = checkListForm.getXydm();
		String dataType = request.getParameter("act");
		if (dataType==null){
			dataType = "";
		}
		String month = checkListForm.getMonth();
		String year = checkListForm.getYear();
		String time = GetTime.getTime2(year, month);
		checkListForm.setMonth(month);
		checkListForm.setYear(year);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= " ";
			}
			writeAble = "no";
		}
		String sql="";
		sql = "select to_char(sysdate,'yyyy') nowYear from dual";
		String nowYear = dao.getOneRs(sql, new String[]{}, new String[]{"nowYear"})[0];
		if(userType.equalsIgnoreCase("xy") && (xy == null || xy.equalsIgnoreCase(""))){
			xy = userDep;
			checkListForm.setXydm(xy);
			disabled = false;
		}

		request.setAttribute("updataOFF", "false");

		if(dataType.equalsIgnoreCase("xyjh")){
			//学院计划
			colList = new String[] { "xyjhdm", "xyjhmc", "puber", "lrrq"};

			colListCN = dao.getColumnNameCN(colList, "sxjy_xygzjh");
			String xxPlan =  request.getParameter("xxplandm");
			inputList = new String [] {userDep,userDep+ " ",time,xxPlan,xxPlan + " "}; 
			sql = "select xyjhdm,xyjhmc,puber,lrrq from sxjy_xygzjh where (xydm = ? or ? = ' ') and (lrrq like ?) and"+
			" (xxjhdm= ? or ? = ' ') order by lrrq desc";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			String plansId = request.getParameter("plansId");
			if (!isNull(plansId)) {
				sql = "select xyjhmc,jhnr,xxjhdm from sxjy_xygzjh where xyjhdm=?";
				CLOB clob = dao.getOneClob(sql, new String[] { plansId }, "jhnr");
				String plansTit = (dao.getOneRs(sql, new String[] { plansId },
						new String[] { "xyjhmc" }))[0];
				String xxplanId = (dao.getOneRs(sql, new String[] { plansId },
						new String[] { "xxjhdm" }))[0];
				checkListForm.setSaveXxplandm(xxplanId);
				request.setAttribute("planscont", clob.getSubString(1L, (int) clob.length()));
				request.setAttribute("planstit", plansTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("plansId", plansId);
			} else {
				request.setAttribute("planscont", "");
				request.setAttribute("planstit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("plansId", "");
			}
			request.setAttribute("xxPlanList", dao.getXxPlanList());
			request.setAttribute("type", "xy");

		}else if(dataType.equalsIgnoreCase("xyzj")){
			//学院总结
			colList = new String[] { "zjdm", "zjmc", "puber", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_jhzj");
			String xyjhdm =  request.getParameter("xyplandm");
			inputList = new String [] {userDep,userDep+ " ",time,xyjhdm,xyjhdm + " "}; 
			sql = "select zjdm,zjmc,puber,lrrq from sxjy_jhzj where (xydm = ? or ? = ' ') and (lrrq like ?) and"+
			" (xyjhdm= ? or ? = ' ') order by lrrq desc";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			String summarizesId = request.getParameter("summarizesId");
			if (!isNull(summarizesId)) {
				sql = "select zjmc,zjnr,xyjhdm from sxjy_jhzj where zjdm=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { summarizesId }, "zjnr");
				String summarizessTit = (dao.getOneRs(sql, new String[] { summarizesId },
						new String[] { "zjmc" }))[0];
				String xyjhId = (dao.getOneRs(sql, new String[] { summarizesId },
						new String[] { "xyjhdm" }))[0];
				checkListForm.setSaveXyjhdm(xyjhId);
				request.setAttribute("summarizesscont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("summarizesstit", summarizessTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("summarizesId", summarizesId);
			} else {
				request.setAttribute("summarizesscont", "");
				request.setAttribute("summarizesstit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("summarizesId", "");
			}
			request.setAttribute("xyPlanList", dao.getXyPlanList(userDep));
			request.setAttribute("type", "xyzj");
		}else if(dataType.equalsIgnoreCase("hdch")){
			//活动策划
			colList = new String[] { "chdm", "chmc", "puber", "lrrq","nd","yf"};

			colListCN = dao.getColumnNameCN(colList, "sxjy_hdchb");
			String errMessage =DealString.toGBK(request.getParameter("errMessage"));
			if(errMessage!=null){
				if(!errMessage.equalsIgnoreCase("")){
					request.setAttribute("errMessage", errMessage);
				}
			}
			String jyztdm =  request.getParameter("jyMotifdm");
			inputList = new String [] {userDep,userDep+ " ",year,year+ " ",month,month+ " ",jyztdm,jyztdm + " "}; 
			sql = "select chdm,chmc,puber,lrrq,nd,yf from sxjy_hdchb where (xydm = ? or ? = ' ') and (nd = ? or ? = ' ') and (yf = ? or ? = ' ') and "+
			" (ztdm= ? or ? = ' ') order by lrrq desc";
			rs.addAll(dao.rsToVator(sql, inputList, colList));

			String plotId = request.getParameter("plotId");
			if (!isNull(plotId)) {
				sql = "select chmc,chnr,ztdm,nd,yf from sxjy_hdchb where chdm=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { plotId }, "chnr");
				String [] temp = dao.getOneRs(sql, new String[] { plotId },
						new String[] { "chmc","ztdm","nd","yf" });
				String plotsTit = temp[0];
				String jyztId = temp[1];
				String fbnd = temp[2];
				String fbyf = temp[3];
				checkListForm.setSaveJyMotifdm(jyztId);
				request.setAttribute("plotscont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("plotstit", plotsTit);
				checkListForm.setFbnd(fbnd);
				checkListForm.setFbyf(fbyf);
				request.setAttribute("isModi", "yes");
				request.setAttribute("plotId", plotId);
			} else {
				request.setAttribute("plotscont", "");
				request.setAttribute("plotstit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("plotId", "");
				request.setAttribute("fbnd", Base.currNd);
				request.setAttribute("fbyf", "");
			}
			request.setAttribute("jyMotifList", dao.getJyMotifList());
			request.setAttribute("type", "hdch");
		}else if(dataType.equalsIgnoreCase("hdzj")){
			//活动总结
			colList = new String[] { "zjdm", "zjmc", "puber", "lrrq","nd","yf"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_hdzjb");
			String jyhddm = request.getParameter("jyPlotdm");
			String errMessage = request.getParameter("errMessage");
			if(errMessage!=null){
				if(!errMessage.equalsIgnoreCase("")){
					request.setAttribute("errMessage", errMessage);
				}
			}
			inputList = new String [] {userDep,userDep+ " ",year,year+ " ",month,month+ " ",jyhddm,jyhddm + " "}; 
			sql = "select zjdm,zjmc,puber,lrrq,nd,yf from sxjy_hdzjb where (xydm = ? or ? = ' ') and (nd = ? or ? = ' ') and (yf = ? or ? = ' ') and "+
			" (chdm= ? or ? = ' ') order by lrrq desc";
			rs.addAll(dao.rsToVator(sql, inputList, colList));

			String summarizesId = request.getParameter("summarizesId");
			if (!isNull(summarizesId)) {
				sql = "select zjmc,zjnr,chdm,nd,yf from sxjy_hdzjb where zjdm=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { summarizesId }, "zjnr");
				String [] temp = dao.getOneRs(sql, new String[] { summarizesId },
						new String[] { "zjmc","chdm","nd","yf" });
				String summarizessTit = temp[0];
				String hdchId = temp[1];
				String fbnd = temp[2];
				String fbyf = temp[3];
				checkListForm.setSaveHdchdm(hdchId);
				request.setAttribute("summarizesscont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("summarizesstit", summarizessTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("summarizesId", summarizesId);
				checkListForm.setFbnd(fbnd);
				checkListForm.setFbyf(fbyf);
			} else {
				request.setAttribute("summarizesscont", "");
				request.setAttribute("summarizesstit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("summarizesId", "");
				request.setAttribute("fbnd", Base.currNd);
				request.setAttribute("fbyf", "");
			}
			request.setAttribute("jyPlotList", dao.getJyPlotList(userDep));
			request.setAttribute("type", "hdzj");
		}else if(dataType.equalsIgnoreCase("dyzj")){
			//思想调研总结报告
			colList = new String[] { "pk", "zjbgmc", "puber", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_dyzjbgb");
			inputList = new String [] {userDep,userDep+ " ",time}; 
			sql = "select rowid pk,zjbgmc,puber,lrrq from sxjy_dyzjbgb where (xydm = ? or ? = ' ') and (lrrq like ?) order by lrrq desc";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			String summarizesId = request.getParameter("summarizesId");
			if (!isNull(summarizesId)) {
				sql = "select zjbgmc,zjbgnr from sxjy_dyzjbgb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { summarizesId }, "zjbgnr");
				String summarizessTit = (dao.getOneRs(sql, new String[] { summarizesId },
						new String[] { "zjbgmc" }))[0];
				request.setAttribute("summarizesscont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("summarizesstit", summarizessTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("summarizesId", summarizesId);
			} else {
				request.setAttribute("summarizesscont", "");
				request.setAttribute("summarizesstit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("summarizesId", "");
			}
			request.setAttribute("type", "dyzj");
		}else if(dataType.equalsIgnoreCase("bjfc")){
			//班级风采
			userDep =  request.getParameter("xydm");
			if(userDep==null){
				userDep="";
			}
			if(!userDep.equalsIgnoreCase(session.getAttribute("userDep").toString())){
				request.setAttribute("updataOFF", "true");
			}
			colList = new String[] { "pk", "xxmc", "puber", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_bjfcxxb");
			inputList = new String [] {userDep,userDep+ " ",time}; 
			sql = "select rowid pk,xxmc,puber,lrrq from sxjy_bjfcxxb where (xydm = ? or ? = ' ') and (lrrq like ?) order by lrrq desc";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			String documentId = request.getParameter("documentId");
			if (!isNull(documentId)) {
				sql = "select xxmc,xxnr from sxjy_bjfcxxb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "xxnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "xxmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("path", "show_classElegant_list.do?act=bjfc&part=N170303");
			request.setAttribute("type", "bjfc");
		}else if(dataType.equalsIgnoreCase("fdygzjl")){
			//辅导员工作交流
			userDep =  request.getParameter("xydm");
			if(userDep==null){
				userDep="";
			}
			if(!userDep.equalsIgnoreCase(session.getAttribute("userDep").toString())){
				request.setAttribute("updataOFF", "true");
			}
			colList = new String[] { "pk", "xxmc", "puber", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_gzjlxxb");
			inputList = new String [] {userDep,userDep+ " ",time}; 
			sql = "select rowid pk,xxmc,puber,lrrq from sxjy_gzjlxxb where (xydm = ? or ? = ' ') and (lrrq like ?) order by lrrq desc";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			String documentId = request.getParameter("documentId");
			if (!isNull(documentId)) {
				sql = "select xxmc,xxnr from sxjy_gzjlxxb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "xxnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "xxmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "fdygzjl");
		}else if(dataType.equalsIgnoreCase("yxdsfc")){
			//优秀导师风采
			userDep =  request.getParameter("xydm");
			if(userDep==null){
				userDep="";
			}
			if(!userDep.equalsIgnoreCase(session.getAttribute("userDep").toString())){
				request.setAttribute("updataOFF", "true");
			}
			colList = new String[] { "pk", "xxmc", "puber", "lrrq"};
			colListCN = dao.getColumnNameCN(colList, "sxjy_fdyfcxxb");
			inputList = new String [] {userDep,userDep+ " ",time}; 
			sql = "select rowid pk,xxmc,puber,lrrq from sxjy_fdyfcxxb where (xydm = ? or ? = ' ') and (lrrq like ?) order by lrrq desc";
			rs.addAll(dao.rsToVator(sql, inputList, colList));
			String documentId = request.getParameter("documentId");
			if (!isNull(documentId)) {
				sql = "select xxmc,xxnr from sxjy_fdyfcxxb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "xxnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "xxmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "yxdsfc");
		}else if(dataType.equalsIgnoreCase("fdypx")){
			//辅导员培训小结上报
            //			读写权判断(读为只能看到自己发的信息，写则是全部）
			String part=request.getParameter("part");
			String puber = session.getAttribute("userName").toString();
			sql = "select dxq from yhqxb where gnmkdm = ? and yhm = ?";
			String[] tmp1 = dao.getOneRs(sql, new String[] {part,puber}, new String[] {"dxq"});
			String tmp2=request.getParameter("modiAble");
			boolean sfxfd = true;
			if(tmp2==null||tmp2.equalsIgnoreCase("")) {
				if(null!=tmp1){
					if(tmp1[0].equals("1")){
						sfxfd = false;
						request.setAttribute("modiAble", "yes");
					}else{
						request.setAttribute("modiAble", "no");
					}
				}
			}else if (tmp2.equalsIgnoreCase("yes")){
				sfxfd = false;
				request.setAttribute("modiAble", "yes");
			}else{
				request.setAttribute("modiAble", "no");
			}
			String colListSql= "";
			if(sfxfd){
				inputList = new String []{};
				colListSql = "select a.rowid pk,pxxxmc,lrrq,puber from sxjy_fdypxxxb a where PUBER = '"+puber+"' order by lrrq desc";
			}else{	
				inputList = new String [] {userDep,userDep+ " "}; 
					colListSql = "select a.rowid pk,pxxxmc,lrrq,puber from sxjy_fdypxxxb a left join yhb b on a.PUBER = b.yhm where b.szbm = ? or ? = ' ' order by lrrq desc";
			}
			
			if(!userDep.equalsIgnoreCase(session.getAttribute("userDep").toString())){
				request.setAttribute("updataOFF", "true");
			}
			String tableName = "sxjy_fdypxxxb";
			
			colList = new String[] {"pk", "pxxxmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select pxxxmc,pxxxnr from sxjy_fdypxxxb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "pxxxnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "pxxxmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "fdypx");
			colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);
			rs.addAll(dao.rsToVator(colListSql, inputList, colList));
			request.setAttribute("topTr", topTr);// 发送表头
		}
		if(dataType.equalsIgnoreCase("xyjh")||dataType.equalsIgnoreCase("hdch")||dataType.equalsIgnoreCase("xyzj")||dataType.equalsIgnoreCase("hdzj")){
			//取得限制时间
			String xzsj = dao.sxjyQueryOne2("sxjyhz_sjszb", new String []{dataType}, "1", "1")[0];
				String dqsj = GetTime.getNowTime2();
				if(Integer.parseInt(dqsj)<=Integer.parseInt(xzsj)){
					request.setAttribute("sfkxg", "yes");
				}else{
					request.setAttribute("sfkxg", "no");
				}
		}
		List topTr = dao.arrayToList(colList, colListCN);
		request.setAttribute("rsNum",rs.size());// 发送记录数
		request.setAttribute("yearList", GetTime.getYearList(3));
		request.setAttribute("chkList", dao.getChkList(6));
		request.setAttribute("nowYear", nowYear);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("disabled", disabled);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		return mapping.findForward("success");
	}


	private ActionForward showOneClob(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		SxjyDAO dao = new SxjyDAO();
		String type = request.getParameter("type");
		String sql = "";
		String[] tit = null;
		String ID = RowidToPk.rowidToPK(request.getParameter("ID"));
		tit = new String[] { "id", "title", 
				"pubtime", "puber", "cont" };
		if(type.equalsIgnoreCase("xx")){
			sql = "select xxjhdm id,jhnr cont,xxjhmc title,puber,lrrq pubtime from "
				+ "sxjy_xxgzjh where xxjhdm=?";
		}else if(type.equalsIgnoreCase("xyjh")){
			sql = "select xyjhdm id,jhnr cont,xyjhmc title,puber,lrrq pubtime from "
				+ "sxjy_xygzjh where xyjhdm=?";
		}else if(type.equalsIgnoreCase("xyzj")){
			sql = "select zjdm id,zjnr cont,zjmc title,puber,lrrq pubtime from "
				+ "sxjy_jhzj where zjdm =?";
		}else if(type.equalsIgnoreCase("jyzt")){
			sql = "select ztdm id,ztnr cont,ztmc title,puber,lrrq pubtime from "
				+ "sxjy_yjyzj where ztdm =?";
		}else if(type.equalsIgnoreCase("hdch")){
			sql = "select chdm id,chnr cont,chmc title,puber,lrrq pubtime from "
				+ "sxjy_hdchb where chdm =?";
		}else if(type.equalsIgnoreCase("hdzj")){
			sql = "select zjdm id,zjnr cont,zjmc title,puber,lrrq pubtime from "
				+ "sxjy_hdzjb where zjdm =?";
		}else if(type.equalsIgnoreCase("zzxxcl")){
			sql = "select cldm id,clnr cont,clmc title,puber,lrrq pubtime from "
				+ "sxjy_zzxxcl where cldm =?";
		}else if(type.equalsIgnoreCase("szkhwj")){
			sql = "select rowid id,wdnr cont,khwdmc title,puber,lrrq pubtime from "
				+ "sxjy_xsszkhwdb where rowid =?";
		}else if(type.equalsIgnoreCase("khtz")){
			sql = "select rowid id,khtznr cont,khtzmc title,puber,lrrq pubtime from "
				+ "sxjy_khtz where rowid =?";
		}else if(type.equalsIgnoreCase("ysjyxx")){
			sql = "select rowid id,ysxxnr cont,ysxxmc title,puber,lrrq pubtime from "
				+ "sxjy_ysjyxxglb where rowid =?";
		}else if(type.equalsIgnoreCase("dyjh")){
			sql = "select rowid id,dyjhnr cont,dyjhmc title,puber,lrrq pubtime from "
				+ "sxjy_dyjhb where rowid =?";
		}else if(type.equalsIgnoreCase("dyzj")){
			sql = "select rowid id,zjbgnr cont,zjbgmc title,puber,lrrq pubtime from "
				+ "sxjy_dyzjbgb where rowid =?";
		}else if(type.equalsIgnoreCase("aqjytz")){
			sql = "select rowid id,tznr cont,tzmc title,puber,lrrq pubtime from "
				+ "sxjy_aqjytz where rowid =?";
		}else if(type.equalsIgnoreCase("xsjyhdtz")){
			sql = "select rowid id,tznr cont,tzmc title,puber,lrrq pubtime from "
				+ "sxjy_xsjyhdtzb where rowid =?";
		}else if(type.equalsIgnoreCase("fdypx")){
			sql = "select rowid id,pxxxnr cont,pxxxmc title,puber,lrrq pubtime from "
				+ "sxjy_fdypxxxb where rowid =?";
		}else if(type.equalsIgnoreCase("fdykhpy")){
			sql = "select rowid id,xxnr cont,xxmc title,puber,lrrq pubtime from "
				+ "sxjy_fdykhpyb where rowid =?";
		}else if(type.equalsIgnoreCase("fdyzbaptz")){
			sql = "select rowid id,xxnr cont,xxmc title,puber,lrrq pubtime from "
				+ "sxjy_fdyzbaptzb where rowid =?";
		}else if(type.equalsIgnoreCase("fdyzdxx")){
			sql = "select rowid id,xxnr cont,xxmc title,puber,lrrq pubtime from "
				+ "sxjy_szzdxxb where rowid =?";
		}else if(type.equalsIgnoreCase("bjfc")){
			sql = "select rowid id,xxnr cont,xxmc title,puber,lrrq pubtime from "
				+ "sxjy_bjfcxxb where rowid =?";
		}else if(type.equalsIgnoreCase("fdygzjl")){
			sql = "select rowid id,xxnr cont,xxmc title,puber,lrrq pubtime from "
				+ "sxjy_gzjlxxb where rowid =?";
		}else if(type.equalsIgnoreCase("yxdsfc")){
			sql = "select rowid id,xxnr cont,xxmc title,puber,lrrq pubtime from "
				+ "sxjy_fdyfcxxb where rowid =?";
		}else if(type.equalsIgnoreCase("bgbzjjh")){
			ID = DealString.toGBK(request.getParameter("ID"));
			sql = "select xh||jhzldm||lrrq||jhbt id,jhbgnr cont,jhbt title,xh puber,lrrq pubtime from "
				+ "xsgbgzjhb where xh||jhzldm||lrrq||jhbt =?";
		}else if(type.equalsIgnoreCase("fdyzp")){
			ID = DealString.toGBK(request.getParameter("ID"));
			sql = "select a.zgh||a.xn||a.nd||a.xq id,a.jhnr cont,a.xjmc title,b.xm puber,a.lrrq pubtime from "
				+ "sxjy_fdyzpb a left join view_fdyxx b on a.zgh = b.zgh where a.zgh||a.xn||a.nd||a.xq =?";
		}
		String[] rs = dao.getOneRs(sql, new String[] { ID }, tit);
		rs = (rs == null) ? new String[tit.length] : rs;
		for (int i = 0; i < tit.length; i++) {
			request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
		}
		CLOB clob = dao.getOneClob(sql, new String[] { ID }, "cont");
		request.setAttribute("cont", clob.getSubString(1L, (int) clob
				.length()));
		return mapping.findForward("success");
	}

	private ActionForward deletePlans(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sql = "";//sql语句
		String ID = "";
		String type = request.getParameter("type");
		SxjyDAO dao = new SxjyDAO();
		ActionForward forward = new ActionForward();
		if(type.equalsIgnoreCase("xx")){
			//学校计划
			ID =request.getParameter("plansId");
			sql = " delete from sxjy_xxgzjh where xxjhdm = ? ";
			forward = new ActionForward("show_xxjh_list.do?act=xxjh&part=N020401", true);
		}else if(type.equalsIgnoreCase("xy")){
			//学院计划
			ID =request.getParameter("plansId");
			sql = " delete from sxjy_xygzjh where xyjhdm = ? ";	
			forward = new ActionForward("show_xyjh_list.do?act=xyjh&part=N020403", true);
		}else if(type.equalsIgnoreCase("xyzj")){
			//学院总结
			ID =request.getParameter("summarizesId");
			sql = " delete from sxjy_jhzj where zjdm = ? ";	
			forward = new ActionForward("show_xyzj_list.do?act=xyzj&part=N020404", true);
		}else if(type.equalsIgnoreCase("jyzt")){
			//教育主题
			ID =request.getParameter("motifId");
			sql = " delete from sxjy_yjyzj where ztdm = ? ";	
			forward = new ActionForward("show_jyzt_list.do?act=jyzt&part=N020406", true);
		}else if(type.equalsIgnoreCase("hdch")){
			//教育主题
			ID =request.getParameter("plotId");
			sql = " delete from sxjy_hdchb where chdm = ? ";	
			forward = new ActionForward("show_hdch_list.do?act=hdch&part=N020407", true);
		}else if(type.equalsIgnoreCase("hdzj")){
			//教育总结
			ID =request.getParameter("summarizesId");
			sql = " delete from sxjy_hdzjb where zjdm = ? ";	
			forward = new ActionForward("show_hdzj_list.do?act=hdzj&part=N020408", true);
		}else if(type.equalsIgnoreCase("zzxxcl")){
			//政治学习材料
			ID =request.getParameter("stuffId");
			sql = " delete from sxjy_zzxxcl where cldm = ? ";	
			forward = new ActionForward("show_zzxxcl_list.do?act=zzxxcl&part=N020410", true);
		}else if(type.equalsIgnoreCase("szkhwj")){
			//思政考核文档
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_xsszkhwdb where rowid = ? ";	
			forward = new ActionForward("show_szkhwj_list.do?act=szkhwj&part=N020412", true);
		}else if(type.equalsIgnoreCase("khtz")){
			//考核办法及教育活动通知
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_khtz where rowid = ? ";	
			forward = new ActionForward("show_assessAndInform_list.do?act=khtz&part=N020704", true);
		}else if(type.equalsIgnoreCase("ysjyxx")){
			//艺术教育信息管理
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_ysjyxxglb where rowid = ? ";	
			forward = new ActionForward("show_artEducateInfo_list.do?act=ysjyxx&part=N021001", true);
		}else if(type.equalsIgnoreCase("dyjh")){
			//调研计划
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_dyjhb where rowid = ? ";	
			forward = new ActionForward("show_dyjh_list.do?act=dyjh&part=N020901", true);
		}else if(type.equalsIgnoreCase("dyzj")){
			//调研总结
			ID =request.getParameter("summarizesId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_dyzjbgb where rowid = ? ";	
			forward = new ActionForward("show_sxdyzj_list.do?act=dyzj&part=N020905", true);
		}else if(type.equalsIgnoreCase("aqjytz")){
			//安全教育通知
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_aqjytz where rowid = ? ";	
			forward = new ActionForward("show_safetyEducationInform_list.do?act=aqjytz&part=N020711", true);
		}else if(type.equalsIgnoreCase("xsjyhdtz")){
			//新生教育活动通知
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_xsjyhdtzb where rowid = ? ";	
			forward = new ActionForward("show_sfreshmanEducationPloy_list.do?act=xsjyhdtz&part=N020710", true);
		}else if(type.equalsIgnoreCase("fdypx")){
			//辅导员培训
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_fdypxxxb where rowid = ? ";	
			forward = new ActionForward("show_counsellorFosterInfo_list.do?act=fdypx&part=N170107", true);
		}else if(type.equalsIgnoreCase("fdykhpy")){
			//辅导员考核评优
			ID= request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_fdykhpyb where rowid = ? ";	
			forward = new ActionForward("show_counsellorAssessInfo_list.do?act=fdykhpy&part=N170205", true);
		}else if(type.equalsIgnoreCase("fdyzbaptz")){
			//辅导员值班安排通知
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_fdyzbaptzb where rowid = ? ";	
			forward = new ActionForward("show_counsellorOnDuty_list.do?act=fdyzbaptz&part=N021112", true);
		}else if(type.equalsIgnoreCase("fdyzdxx")){
			//思政制度
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_szzdxxb where rowid = ? ";	
			forward = new ActionForward("show_counsellorSystem_list.do?act=fdyzdxx&part=N170202", true);
		}else if(type.equalsIgnoreCase("bjfc")){
			//班级风采
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_bjfcxxb where rowid = ? ";	
			forward = new ActionForward("show_classElegant_list.do?act=bjfc&part=N170303", true);
		}else if(type.equalsIgnoreCase("fdygzjl")){
			//辅导员工作交流
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_gzjlxxb where rowid = ? ";	
			forward = new ActionForward("show_workIntercourse_list.do?act=fdygzjl&part=N021113", true);
		}else if(type.equalsIgnoreCase("yxdsfc")){
			//优秀导师风采
			ID =request.getParameter("documentId");
			ID=RowidToPk.rowidToPK(ID);
			sql = " delete from sxjy_fdyfcxxb where rowid = ? ";	
			forward = new ActionForward("show_excellenceTeacherElegant_list.do?act=yxdsfc&part=N170401", true);
		}
		boolean ok = dao.runUpdate(sql, new String[]{ ID });
		if(ok==true){
			return forward;
		}else{
			return mapping.findForward("false");
		}   
	}

	private ActionForward savePlans(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SxjyDAO dao = new SxjyDAO();
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		StringBuffer sqlBuffer;
		String clobSql="";
		String sql="";
		String type =request.getParameter("type");
		//String ssmk = msgForm.getXmdm();
		String title = DealString.toGBK(request.getParameter("title"));
		String sContent = DealString.toGBK(request.getParameter("content1"));
		String puber = session.getAttribute("userName").toString();
		String isModi = request.getParameter("isModi");
		title = isNull(title) ? "无标题" : title;
		if(type.equalsIgnoreCase("xx")){
			//学校计划
			String plansId = request.getParameter("plansId");
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sql = "update sxjy_xxgzjh set xxjhmc='"+title+"',jhnr='null',puber='"+puber+"' where xxjhdm='"+plansId+"'";
				clobSql="select jhnr from sxjy_xxgzjh where xxjhdm='"+plansId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "jhnr");
			} else {
				sql="insert into sxjy_xxgzjh(xxjhdm,xxjhmc,jhnr,puber) values(S_PLANS_ID.nextval,'"+title+"','null','"+puber+"')";
				clobSql="select jhnr from sxjy_xxgzjh where jhnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "jhnr");
			}
			return new ActionForward("show_xxjh_list.do?act=xxjh&part=N020401", true);
		}else if (type.equalsIgnoreCase("xy")){
			//学院计划
			String plansId = request.getParameter("plansId");
			String xxplandm = request.getParameter("saveXxplandm");
			if(xxplandm==null){
				xxplandm="";
			}
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sql = "update sxjy_xygzjh set xyjhmc='"+title+"',jhnr='null',puber='"+puber+"',xxjhdm='"+xxplandm+"' where xyjhdm='"+plansId+"'";
				clobSql="select jhnr from sxjy_xygzjh where xyjhdm='"+plansId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "jhnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_xygzjh(xyjhdm,xyjhmc,jhnr,puber,xydm,xxjhdm) values(XY_PLANS_ID.nextval,'");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("','");
				sqlBuffer.append(userDep);
				sqlBuffer.append("','");
				sqlBuffer.append(xxplandm);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select jhnr from sxjy_xygzjh where jhnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "jhnr");
			}
			return new ActionForward("show_xyjh_list.do?act=xyjh&part=N020403", true);
		}else if (type.equalsIgnoreCase("xyzj")){
			//学院计划总结
			String summarizesId = request.getParameter("summarizesId");
			String xyjhdm = request.getParameter("saveXyjhdm");
			if(xyjhdm==null){
				xyjhdm="";
			}
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sql = "update sxjy_jhzj set zjmc='"+title+"',zjnr='null',puber='"+puber+"',xyjhdm='"+xyjhdm+"' where zjdm='"+summarizesId+"'";
				clobSql="select zjnr from sxjy_jhzj where zjdm='"+summarizesId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "zjnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_jhzj(zjdm,zjmc,zjnr,puber,xydm,xyjhdm) values(XY_SUMMARIZES_ID.nextval,'");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("','");
				sqlBuffer.append(userDep);
				sqlBuffer.append("','");
				sqlBuffer.append(xyjhdm);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select zjnr from sxjy_jhzj where zjnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "zjnr");
			}
			return new ActionForward("show_xyzj_list.do?act=xyzj&part=N020404", true);
		}else if (type.equalsIgnoreCase("jyzt")){
			//教育主题
			String motifId = request.getParameter("motifId");
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sql = "update sxjy_yjyzj set ztmc='"+title+"',ztnr='null',puber='"+puber+"' where ztdm='"+motifId+"'";
				clobSql="select ztnr from sxjy_yjyzj where ztdm='"+motifId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "ztnr");
			} else {
				sql="insert into sxjy_yjyzj(ztdm,ztmc,ztnr,puber) values(XY_MOTIF_ID.nextval,'"+title+"','null','"+puber+"')";
				clobSql="select ztnr from sxjy_yjyzj where ztnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "ztnr");
			}
			return new ActionForward("show_jyzt_list.do?act=jyzt&part=N020406", true);
		}else if (type.equalsIgnoreCase("hdch")){
			//教育活动策划
			String plotId = request.getParameter("plotId");
			String jyztdm = request.getParameter("saveJyMotifdm");
			String fbnd = request.getParameter("fbnd");
			String fbyf = request.getParameter("fbyf");
			String errMessage = "";
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_hdchb set chmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',chnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("',ztdm = '");
				sqlBuffer.append(jyztdm);
				sqlBuffer.append("',nd = '");
				sqlBuffer.append(fbnd);
				sqlBuffer.append("',yf = '");
				sqlBuffer.append(fbyf);
				sqlBuffer.append("' where chdm='");
				sqlBuffer.append(plotId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select chnr from sxjy_hdchb where chdm='"+plotId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "chnr");
			} else {
				sql = "select count(*) num from sxjy_hdchb where xydm = ? and nd = ? and yf = ? ";
				String num = dao.getOneRs(sql, new String []{userDep,fbnd,fbyf}, new String []{"num"})[0];
				if(num.equalsIgnoreCase("0")){
				sqlBuffer = new StringBuffer("insert into sxjy_hdchb(chdm,chmc,chnr,puber,xydm,ztdm,nd,yf) values(JY_PLOT_ID.nextval,'");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("','");
				sqlBuffer.append(userDep);
				sqlBuffer.append("','");
				sqlBuffer.append(jyztdm);
				sqlBuffer.append("','");
				sqlBuffer.append(fbnd);
				sqlBuffer.append("','");
				sqlBuffer.append(fbyf);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select chnr from sxjy_hdchb where chnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "chnr");
				}else{
					errMessage = "yes";
				}
			}
			return new ActionForward("show_hdch_list.do?act=hdch&part=N020407&errMessage="+errMessage, true);
		}else if (type.equalsIgnoreCase("hdzj")){
			//教育活动总结
			String summarizesId = request.getParameter("summarizesId");
			String hdchdm = request.getParameter("saveHdchdm");
			String fbnd = request.getParameter("fbnd");
			String fbyf = request.getParameter("fbyf");
			String errMessage = "";
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_hdzjb set zjmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',zjnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("',nd = '");
				sqlBuffer.append(fbnd);
				sqlBuffer.append("',yf = '");
				sqlBuffer.append(fbyf);
				sqlBuffer.append("',chdm = '");
				sqlBuffer.append(hdchdm);
				sqlBuffer.append("' where zjdm='");
				sqlBuffer.append(summarizesId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select zjnr from sxjy_hdzjb where zjdm='"+summarizesId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "zjnr");
			} else {
				sql = "select count(*) num from sxjy_hdzjb where xydm = ? and nd = ? and yf = ? ";
				String num = dao.getOneRs(sql, new String []{userDep,fbnd,fbyf}, new String []{"num"})[0];
				if(num.equalsIgnoreCase("0")){
				sqlBuffer = new StringBuffer("insert into sxjy_hdzjb(zjdm,zjmc,zjnr,puber,xydm,chdm,nd,yf) values(JY_SUMMARIZES_ID.nextval,'");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("','");
				sqlBuffer.append(userDep);
				sqlBuffer.append("','");
				sqlBuffer.append(hdchdm);
				sqlBuffer.append("','");
				sqlBuffer.append(fbnd);
				sqlBuffer.append("','");
				sqlBuffer.append(fbyf);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select zjnr from sxjy_hdzjb where zjnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "zjnr");
				}else{
					errMessage = "yes";
				}
			}
			return new ActionForward("show_hdzj_list.do?act=hdzj&part=N020408&errMessage="+errMessage, true);
		}else if (type.equalsIgnoreCase("szkhwj")){
			//思政考核文档
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_xsszkhwdb set khwdmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',wdnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select wdnr from sxjy_xsszkhwdb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "wdnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_xsszkhwdb(khwdmc,wdnr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select wdnr from sxjy_xsszkhwdb where wdnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "wdnr");
			}
			return new ActionForward("show_szkhwj_list.do?act=szkhwj&part=N020412", true);
		}else if (type.equalsIgnoreCase("zzxxcl")){
			//政治学习材料
			String stuffId = request.getParameter("stuffId");
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sql = "update sxjy_zzxxcl set clmc='"+title+"',clnr='null',puber='"+puber+"' where cldm='"+stuffId+"'";
				clobSql="select clnr from sxjy_zzxxcl where cldm='"+stuffId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "clnr");
			} else {
				sql="insert into sxjy_zzxxcl(cldm,clmc,clnr,puber) values(JY_STUFF_ID.nextval,'"+title+"','null','"+puber+"')";
				clobSql="select clnr from sxjy_zzxxcl where clnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "clnr");
			}
			return new ActionForward("show_zzxxcl_list.do?act=zzxxcl&part=N020410", true);
		}else if (type.equalsIgnoreCase("khtz")){
			//考核办法及教育通知表
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_khtz set khtzmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',khtznr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select khtznr from sxjy_khtz where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "khtznr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_khtz(khtzmc,khtznr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select khtznr from sxjy_khtz where khtznr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "khtznr");
			}
			return new ActionForward("show_assessAndInform_list.do?act=khtz&part=N020704", true);
		}else if (type.equalsIgnoreCase("ysjyxx")){
			//艺术教育信息管理表
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_ysjyxxglb set ysxxmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',ysxxnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select ysxxnr from sxjy_ysjyxxglb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "ysxxnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_ysjyxxglb(ysxxmc,ysxxnr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select ysxxnr from sxjy_ysjyxxglb where ysxxnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "ysxxnr");
			}
			return new ActionForward("show_artEducateInfo_list.do?act=ysjyxx&part=N021001", true);
		}else if (type.equalsIgnoreCase("dyjh")){
			//调研计划表
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_dyjhb set dyjhmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',dyjhnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select dyjhnr from sxjy_dyjhb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "dyjhnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_dyjhb(dyjhmc,dyjhnr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select dyjhnr from sxjy_dyjhb where dyjhnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "dyjhnr");
			}
			return new ActionForward("show_dyjh_list.do?act=dyjh&part=N020901", true);
		}else if (type.equalsIgnoreCase("dyzj")){
			//调研总结报告
			String summarizesId = RowidToPk.rowidToPK(request.getParameter("summarizesId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_dyzjbgb set zjbgmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',zjbgnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(summarizesId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select zjbgnr from sxjy_dyzjbgb where rowid='"+summarizesId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "zjbgnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_dyzjbgb(zjbgmc,zjbgnr,xydm,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(userDep);
				sqlBuffer.append("','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select zjbgnr from sxjy_dyzjbgb where zjbgnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "zjbgnr");
			}
			return new ActionForward("show_sxdyzj_list.do?act=dyzj&part=N020905", true);
		}else if (type.equalsIgnoreCase("aqjytz")){
			//安全教育通知表
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_aqjytz set tzmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',tznr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select tznr from sxjy_aqjytz where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "tznr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_aqjytz(tzmc,tznr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select tznr from sxjy_aqjytz where tznr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "tznr");
			}
			return new ActionForward("show_safetyEducationInform_list.do?act=aqjytz&part=N020711", true);
		}else if (type.equalsIgnoreCase("xsjyhdtz")){
			//新生教育活动通知
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_xsjyhdtzb set tzmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',tznr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select tznr from sxjy_xsjyhdtzb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "tznr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_xsjyhdtzb(tzmc,tznr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select tznr from sxjy_xsjyhdtzb where tznr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "tznr");
			}
			return new ActionForward("show_sfreshmanEducationPloy_list.do?act=xsjyhdtz&part=N020710", true);
		}else if (type.equalsIgnoreCase("fdypx")){
			//辅导员培训
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_fdypxxxb set pxxxmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',pxxxnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select pxxxnr from sxjy_fdypxxxb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "pxxxnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_fdypxxxb(pxxxmc,pxxxnr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select pxxxnr from sxjy_fdypxxxb where pxxxnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "pxxxnr");
			}
			return new ActionForward("show_counsellorFosterInfo_list.do?act=fdypx&part=N170107", true);
		}else if (type.equalsIgnoreCase("fdykhpy")){
			//辅导员考核评优办法
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_fdykhpyb set xxmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',xxnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_fdykhpyb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_fdykhpyb(xxmc,xxnr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_fdykhpyb where xxnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			}
			return new ActionForward("show_counsellorAssessInfo_list.do?act=fdykhpy&part=N170205", true);
		}else if (type.equalsIgnoreCase("fdyzdxx")){
			//思政制度信息
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_szzdxxb set xxmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',xxnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_szzdxxb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_szzdxxb(xxmc,xxnr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_szzdxxb where xxnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			}
			return new ActionForward("show_counsellorSystem_list.do?act=fdyzdxx&part=N170202", true);
		}else if (type.equalsIgnoreCase("fdyzbaptz")){
			//辅导员值班安排通知
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_fdyzbaptzb set xxmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',xxnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_fdyzbaptzb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_fdyzbaptzb(xxmc,xxnr,puber) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_fdyzbaptzb where xxnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			}
			return new ActionForward("show_counsellorOnDuty_list.do?act=fdyzbaptz&part=N021112", true);
		}else if (type.equalsIgnoreCase("bjfc")){
			//班级风采信息
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_bjfcxxb set xxmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',xxnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_bjfcxxb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_bjfcxxb(xxmc,xxnr,puber,xydm) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("','");
				sqlBuffer.append(userDep);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_bjfcxxb where xxnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			}
			return new ActionForward("show_classElegant_list.do?act=bjfc&part=N170303", true);
		}else if (type.equalsIgnoreCase("fdygzjl")){
			//工作交流信息
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_gzjlxxb set xxmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',xxnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_gzjlxxb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_gzjlxxb(xxmc,xxnr,puber,xydm) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("','");
				sqlBuffer.append(userDep);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_gzjlxxb where xxnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			}
			return new ActionForward("show_workIntercourse_list.do?act=fdygzjl&part=N021113", true);
		}else if (type.equalsIgnoreCase("yxdsfc")){
			//优秀导师风采信息
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update sxjy_fdyfcxxb set xxmc='");
				sqlBuffer.append(title);
				sqlBuffer.append("',xxnr='null',puber='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where rowid='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_fdyfcxxb where rowid='"+documentId+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			} else {
				sqlBuffer = new StringBuffer("insert into sxjy_fdyfcxxb(xxmc,xxnr,puber,xydm) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("','");
				sqlBuffer.append(userDep);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select xxnr from sxjy_fdyfcxxb where xxnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "xxnr");
			}
			return new ActionForward("show_excellenceTeacherElegant_list.do?act=yxdsfc&part=N170401", true);
		}else if (type.equalsIgnoreCase("xsgbzjfb")){
			//学生干部总结计划发布
			String documentId = DealString.toGBK(request.getParameter("plansId"));
			String jhtype = request.getParameter("jhtype");
			request.setAttribute("type", jhtype);
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sqlBuffer = new StringBuffer("update xsgbgzjhb set jhbt='");
				sqlBuffer.append(title);
				sqlBuffer.append("',xh='");
				sqlBuffer.append(puber);
				sqlBuffer.append("' where xh||jhzldm||lrrq||jhbt='");
				sqlBuffer.append(documentId);
				sqlBuffer.append("'");
				sql =sqlBuffer.toString();
				clobSql="select jhbgnr from xsgbgzjhb where xh||jhzldm||lrrq||jhbt='"+documentId+"' for update";
				dao.updataClobs(sql, clobSql, sContent, "jhbgnr");
			} else {
				sqlBuffer = new StringBuffer("insert into xsgbgzjhb(jhbt,jhbgnr,xh,jhzldm) values('");
				sqlBuffer.append(title);
				sqlBuffer.append("','null','");
				sqlBuffer.append(puber);
				sqlBuffer.append("','");
				sqlBuffer.append(jhtype);
				sqlBuffer.append("')");
				sql =sqlBuffer.toString();
				clobSql="select jhbgnr from xsgbgzjhb where jhbgnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "jhbgnr");
			}
			return new ActionForward("/stuCadre.do?method=xsgbgzjh&type="+jhtype, true);
		}
		return null;
	}

	private ActionForward showLists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String dataType = request.getParameter("act");
		HttpSession session = request.getSession();
//		String puber = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
//		String part=request.getParameter("part");//部门
		String tableName = "";// 数据源表
		String[] colList = null;//输入字段
		String[] colListCN = null;
		String sql = "";//sql语句
		String colListSql = "";//sql语句
		SxjyDAO dao = new SxjyDAO();
		if(userType.equalsIgnoreCase("xy")) {
		}else{
			request.setAttribute("writeAble", "1");
		}
		if (dataType.equalsIgnoreCase("xxjh")) {
			//学校计划
			tableName = "sxjy_xxgzjh";
			colListSql = "select xxjhdm 主键,xxjhmc,lrrq,puber from sxjy_xxgzjh order by lrrq desc";
			colList = new String[] {"主键", "xxjhmc", "lrrq", "puber"};
			String plansId = request.getParameter("plansId");
			if (!isNull(plansId)) {
				sql = "select xxjhmc,jhnr from sxjy_xxgzjh where xxjhdm = ? ";
				CLOB clob = dao.getOneClob(sql, new String[] { plansId }, "jhnr");
				String plansTit = (dao.getOneRs(sql, new String[] { plansId },new String[] { "xxjhmc" }))[0];
				request.setAttribute("planscont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("planstit", plansTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("plansId", plansId);
			} else {
				request.setAttribute("planscont", "");
				request.setAttribute("planstit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("plansId", "");
			}
			request.setAttribute("type", "xx");
		}else if (dataType.equalsIgnoreCase("jyzt")){
			//教育活动主题
			tableName = "sxjy_yjyzj";
			colListSql = "select ztdm 主键,ztmc,lrrq,puber from sxjy_yjyzj order by lrrq desc";
			colList = new String[] {"主键", "ztmc", "lrrq", "puber"};
			String motifId = request.getParameter("motifId");
			if (!isNull(motifId)) {
				sql = "select ztmc,ztnr from sxjy_yjyzj where ztdm=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { motifId }, "ztnr");
				String motifTit = (dao.getOneRs(sql, new String[] { motifId },
						new String[] { "ztmc" }))[0];
				request.setAttribute("motifcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("motiftit", motifTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("motifId", motifId);
			} else {
				request.setAttribute("motifcont", "");
				request.setAttribute("motiftit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("motifId", "");
			}
			request.setAttribute("type", "jyzt");
		}else if(dataType.equalsIgnoreCase("zzxxcl")){
			//政治学习材料
			tableName = "sxjy_zzxxcl";
			colListSql = "select cldm 主键,clmc,lrrq,puber from sxjy_zzxxcl order by lrrq desc";
			colList = new String[] {"主键", "clmc", "lrrq", "puber"};
			String stuffId = request.getParameter("stuffId");
			if (!isNull(stuffId)) {
				sql = "select clmc,clnr from sxjy_zzxxcl where cldm=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { stuffId }, "clnr");
				String stuffTit = (dao.getOneRs(sql, new String[] { stuffId },
						new String[] { "clmc" }))[0];
				request.setAttribute("stuffcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("stufftit", stuffTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("stuffId", stuffId);
			} else {
				request.setAttribute("stuffcont", "");
				request.setAttribute("stufftit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("stuffId", "");
			}
			request.setAttribute("type", "zzxxcl");
		}else if(dataType.equalsIgnoreCase("szkhwj")){
			//学生思政考核文档
			tableName = "sxjy_xsszkhwdb";
			colListSql = "select rowid 主键,khwdmc,lrrq,puber from sxjy_xsszkhwdb order by lrrq desc";
			colList = new String[] {"主键", "khwdmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select khwdmc,wdnr from sxjy_xsszkhwdb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "wdnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "khwdmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "szkhwj");
		}else if(dataType.equalsIgnoreCase("khtz")){
			//考核办法及教育通知
			tableName = "sxjy_khtz";
			colListSql = "select rowid 主键,khtzmc,lrrq,puber from sxjy_khtz order by lrrq desc";
			colList = new String[] {"主键", "khtzmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select khtzmc,khtznr from sxjy_khtz where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "khtznr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "khtzmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "khtz");
		}else if(dataType.equalsIgnoreCase("ysjyxx")){
			//艺术教育信息
			tableName = "sxjy_ysjyxxglb";
			colListSql = "select rowid 主键,ysxxmc,lrrq,puber from sxjy_ysjyxxglb order by lrrq desc";
			colList = new String[] {"主键", "ysxxmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select ysxxmc,ysxxnr from sxjy_ysjyxxglb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "ysxxnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "ysxxmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "ysjyxx");
		}else if(dataType.equalsIgnoreCase("dyjh")){
			//调研计划
			tableName = "sxjy_dyjhb";
			colListSql = "select rowid 主键,dyjhmc,lrrq,puber from sxjy_dyjhb order by lrrq desc";
			colList = new String[] {"主键", "dyjhmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select dyjhmc,dyjhnr from sxjy_dyjhb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "dyjhnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "dyjhmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "dyjh");
		}else if(dataType.equalsIgnoreCase("aqjytz")){
			//安全教育通知
			tableName = "sxjy_aqjytz";
			colListSql = "select rowid 主键,tzmc,lrrq,puber from sxjy_aqjytz order by lrrq desc";
			colList = new String[] {"主键", "tzmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select tzmc,tznr from sxjy_aqjytz where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "tznr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "tzmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "aqjytz");
		}else if(dataType.equalsIgnoreCase("xsjyhdtz")){
			//新生教育活动通知
			tableName = "sxjy_xsjyhdtzb";
			colListSql = "select rowid 主键,tzmc,lrrq,puber from sxjy_xsjyhdtzb order by lrrq desc";
			colList = new String[] {"主键", "tzmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select tzmc,tznr from sxjy_xsjyhdtzb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "tznr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "tzmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "xsjyhdtz");
		}else if(dataType.equalsIgnoreCase("fdykhpy")){
			//辅导员考核评优信息
			tableName = "sxjy_fdykhpyb";
			colListSql = "select rowid 主键,xxmc,lrrq,puber from sxjy_fdykhpyb order by lrrq desc";
			colList = new String[] {"主键", "xxmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select xxmc,xxnr from sxjy_fdykhpyb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "xxnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "xxmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "fdykhpy");
		}else if(dataType.equalsIgnoreCase("fdyzbaptz")){
			//辅导员值班安排通知信息
			tableName = "sxjy_fdyzbaptzb";
			colListSql = "select rowid 主键,xxmc,lrrq,puber from sxjy_fdyzbaptzb order by lrrq desc";
			colList = new String[] {"主键", "xxmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select xxmc,xxnr from sxjy_fdyzbaptzb where rowid=?";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "xxnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "xxmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "fdyzbaptz");
		}else if(dataType.equalsIgnoreCase("fdyzdxx")){
			//思政制度信息
			tableName = "sxjy_szzdxxb";
			colListSql = "select rowid 主键,xxmc,lrrq,puber from sxjy_szzdxxb order by lrrq desc";
			colList = new String[] {"主键", "xxmc", "lrrq", "puber"};
			String documentId = RowidToPk.rowidToPK(request.getParameter("documentId"));
			if (!isNull(documentId)) {
				sql = "select xxmc,xxnr from sxjy_szzdxxb where rowid=? order by lrrq desc";
				CLOB clob = dao
				.getOneClob(sql, new String[] { documentId }, "xxnr");
				String documentTit = (dao.getOneRs(sql, new String[] { documentId },
						new String[] { "xxmc" }))[0];
				request.setAttribute("documentcont", clob.getSubString(1L, (int) clob
						.length()));
				request.setAttribute("documenttit", documentTit);
				request.setAttribute("isModi", "yes");
				request.setAttribute("documentId", documentId);
			} else {
				request.setAttribute("documentcont", "");
				request.setAttribute("documenttit", "");
				request.setAttribute("isModi", "no");
				request.setAttribute("documentId", "");
			}
			request.setAttribute("type", "fdyzdxx");
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		
		List rs = dao.getList(colListSql, new String[] {}, colList);
		
		request.setAttribute("path", "show_counsellorSystem_list.do?act=fdyzdxx&part=N170202");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rs", rs);// 发送数据集
		return mapping.findForward("success");
	}
	
	/**
	 * 班级学生干部队伍自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward bjxsgbdwExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxjyDAO dao = new SxjyDAO();
		String[] colList = null;
		String[] colListCN = null;
		SxjyForm checkListForm = (SxjyForm) form;
		String xy = checkListForm.getXydm();
		if (xy==null){
			xy = "";
		}
		if (xy.equalsIgnoreCase("")){
			xy = DealString.toGBK(request.getParameter("xy"));
		}
		if (xy==null){
			xy = "";
		}
		String dataType = request.getParameter("act");
		if (dataType==null){
			dataType = "";
		}

		String xh = DealString.toGBK(checkListForm.getXh());
		String nj = DealString.toGBK(checkListForm.getNj());
		
		String zy = DealString.toGBK(checkListForm.getZy());
		if (zy==null){
			zy = "";
		}
		if (zy.equalsIgnoreCase("")){
			zy = DealString.toGBK(request.getParameter("zy"));
		}
		if (zy==null){
			zy = "";
		}
		
		String bj = DealString.toGBK(checkListForm.getBj());
		checkListForm.setXy(xy);
		HttpSession session = request.getSession();
		String sql="";

		String stuQuery ="";
		String [] stuInputList = new String[] {};
		
		//班级学生干部队伍
		String isFdy=session.getAttribute("isFdy").toString();
		String userName=session.getAttribute("userName").toString();
		String zwdm = DealString.toGBK(request.getParameter("zwdm"));
		String fzpd = "";
		String xm =  DealString.toGBK(request.getParameter("xm"));
		String jbdm = DealString.toGBK(checkListForm.getJbdm());
		if(request.getParameter("go")==null) {
			fzpd= " and 1 = 2";
		}
		stuQuery = "where (xydm = ? or ? = ' ') and (bjdm = ? or ? = ' ')" +
		"  and (nj = ? or ? = ' ') and (zydm = ? or ? = ' ') and (bjgbdm = ? or ? = ' ') and (gbzljb = ? or ? = ' ')";
		if(xh!=null && !xh.equalsIgnoreCase("")){
			stuQuery+= " and xh like '%";
			stuQuery+= xh;
			stuQuery+= "%'";
		}
		
		if(xm!=null&&!xm.equalsIgnoreCase("")) {
			stuQuery+= " and xm like '%";
			stuQuery+= xm;
			stuQuery+= "%'";
		}
		
		if("true".equalsIgnoreCase(isFdy)){
			stuQuery+= " and exists(select 1 from fdybjb a where a.bjdm=b.bjdm and a.zgh='"+userName+"' ) ";
		}
		
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
			String rzqk = checkListForm.getRzqk();
			if(!Base.isNull(rzqk)){
				if("yes".equalsIgnoreCase(rzqk)){
					stuQuery+=" and ((kssj <= to_char(sysdate, 'yyyymmdd') and jssj >= to_char(sysdate, 'yyyymmdd')) or jssj is null)";
				}else{
					stuQuery+=" and (kssj > to_char(sysdate, 'yyyymmdd') or jssj < to_char(sysdate, 'yyyymmdd'))";
				}
			}
		}
		stuInputList = new String[] {xy,xy+" ",bj,bj +" ",nj,nj+" ",zy,zy+" ",zwdm,zwdm+" ",jbdm,jbdm+" "};
	
		colList = new String[] { "pk","bjgbmc","gbjbmc","xh","xm","nj","xymc","zymc","bjmc","lxfs"};
		colListCN = dao.getColumnNameCN(colList, "view_bjgbxx");
		colListCN[0]="";
		sql = "select rownum r,a.* from(" ;
		sql+=" select pk,bjgbmc,gbjbmc,xh,xm,nj,xymc,zymc,bjmc,lxfs from view_bjgbxx b "+stuQuery+fzpd;
		sql+=" )a ";
		// 结果集
		List<HashMap<String,String>> resultList = CommonQueryDAO.commonQueryforExportList(sql, "", stuInputList, colList, checkListForm);
		//导出功能代码 List<HashMap<String, String>> commonQueryforExportList
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = checkListForm.getExportModel();
		exportModel.setZgh(userName);//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);		
		return null;
		
	}
}