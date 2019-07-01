package xgxt.xljk.xlyt.action;

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

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.DataManModel;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

public class XlytAction extends Action {
	String writeAble = "";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		CommanForm chkUser = (CommanForm) form;
			// 判断用户读写权
			writeAble = Base.getWriteAble(request);
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			ActionForward myActFwd = dataSearch(mapping, form, request, response);
			DAO dao = DAO.getInstance();
			if (dao.getXxdm().equalsIgnoreCase(Globals.XXDM_HZZY)) {
				if (request.getSession().getAttribute("userName").toString().equalsIgnoreCase("zf01")) {
					request.setAttribute("writeAble", "yes");
				} else {
					request.setAttribute("writeAble", writeAble);
				}
			} else {
				request.setAttribute("writeAble", writeAble);
			}
		return myActFwd;
	}
	@SuppressWarnings("unchecked")
	private ActionForward dataSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = " ";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String tips = "";// 位置提示信息
		String tableName = request.getParameter("tableName");// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String dataType = request.getParameter("act");
		String xh = request.getParameter("xh");
		xh = xh != null && !"".equalsIgnoreCase(xh) ? DealString.toGBK(xh) : xh;
		String xm = DealString.toGBK(dataSearchForm.getXm());
		String nd = dataSearchForm.getNd();
		String nf = dataSearchForm.getNf();
		String yf = dataSearchForm.getYf();
		String xjzt = DealString.toGBK(dataSearchForm.getXjzt());
		String nj = dataSearchForm.getNj();
		String xn = dataSearchForm.getXn();
		String xq = dataSearchForm.getXq();
		String xymc = DealString.toGBK(dataSearchForm.getXydm());
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String hth = dataSearchForm.getHth();
		String bzffny = dataSearchForm.getBzffny();
		String sfzh = dataSearchForm.getSfzh();
		String xmdm = dataSearchForm.getXmdm();
		String kh = dataSearchForm.getKh();
		String xxsh = DealString.toGBK(dataSearchForm.getXxsh());
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		String userOnline = session.getAttribute("userOnLine").toString();
		String userType2 = session.getAttribute("userType").toString();   //直接获得用户类型，此方式获取的类型为两类：1、"stu"学生2、"teacher"教师
		String pkValue = request.getParameter("pkValue");
		String ndVar = request.getParameter("ndList");// for 综合素质测评中的年度
		String dxq = request.getParameter("dxq");
		String dxq1 = request.getParameter("writeAble");
		String xxmc = StandardOperation.getXxmc();	
		String xxdm = StandardOperation.getXxdm();
		String isFdy = session.getAttribute("isFdy").toString();
		//学生访问权限控制开始
		if (dataType!=null && (dataType.equalsIgnoreCase("dormInfo")
				||dataType.equalsIgnoreCase("usingInfo")
				||dataType.equalsIgnoreCase("dailyNote")
				||dataType.equalsIgnoreCase("weeklyCollect"))) {
			if(userType2.equalsIgnoreCase("stu")){//学生无操作权限
				request.setAttribute("errMsg", "学生无权访问该页面！");
				return new ActionForward("/errMsg.do", false);
			}  
		}
		//学生访问权限控制结束

		if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)&& dataType!=null 
				&& dataType.equalsIgnoreCase("studentPaperAgain") ){//重庆科技学院,云南艺术	
			return mapping.findForward("cqkj");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			//上海工程技术大学
			if(dataType!=null && "insureInfo".equalsIgnoreCase(dataType)){
				return new ActionForward("/shgc_tbxxwh.do?method=tbxxwhQue");
			}
		}
		String isBzr="";
		dataSearchForm.setXm(xm);
		if (!("".equalsIgnoreCase(dxq) || dxq == null)) {
			writeAble = dxq;
		} else {
			if (!("".equalsIgnoreCase(dxq1) || dxq1 == null)) {
				writeAble = dxq1;
			}
		}
		if (pkValue == null) {
			pkValue = "";
		}
		request.setAttribute("pkValue", pkValue);
		String jxhjlx = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sql = "select xydel from xtszb where rownum=1";
			String xydel = dao.getOneRs(sql, new String[] {}, "xydel");
			if ("".equalsIgnoreCase(xydel) || xydel == null
					|| "0".equalsIgnoreCase(xydel)) {
				request.setAttribute("xydel", "no");
			}
		}

		if (null == dataType) {
			dataType = request.getParameter("dataType");
		}

		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			request.setAttribute("showhzy", "showhzy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
			request.setAttribute("showzszy", "showzszy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {
			request.setAttribute("showszxx", "showszxx");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			request.setAttribute("isSHGC", "is");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			request.setAttribute("showbjlh", "showbjlh");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
			request.setAttribute("showZjsyzy", "showZjsyzy");
			if ("student".equalsIgnoreCase(userOnline)) {
				request.setAttribute("showStu", "showStu");
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			//这个地方只是综合素质测评才有打印.别的模块没有打印，而且只是江苏信息单独的
			request.setAttribute("showjsxx", "showjsxx");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			request.setAttribute("showzgdzdx", "showzgdzdx");
		}
		boolean isHTXX = false;
		//是否是心理普查
		boolean isXLPC = false;
		//长沙民政职业技术学院特殊群体学生情况登记表
		boolean isTSXSDJ = false;
		//长沙民政帮教小组人员
		boolean isBJXZRY = false;
		//长沙民政特殊躯体跟进
		boolean isTSQTGJ = false;
		//长沙民政特殊群体申请撤销
		boolean isTSQTSQCX = false;
		//长沙民政心理健康教育情况统计
		boolean isXLJKTJ = false;
		//是否是心理普查
		boolean isZXQK = false;
		//是否是心理健康兼职教师
		boolean isJZJS = false;
		//大学生心理健康教育活动
		boolean isXLJKHD = false;
		//大学生健康教育中心心理咨询预约登记表
		boolean isXLZXYY = false;
		//双周动态信息
		boolean isSZDTXX = false;
		boolean isXSJBXX = false;
		boolean isXSZZQK = false;
		boolean isZXDK_XSXXCX = false;
		boolean isFSBZ = false;
		// int rightNd = Integer.parseInt(Base.currNd);
		List<HashMap<String, String>> ndList = Base.getXnndList();
		if (userType.equalsIgnoreCase("xx")) {
			request.setAttribute("isXX", "is");
		} else {
			request.setAttribute("isXX", "no");
		}
		if ("trainPrise".equalsIgnoreCase(dataType)) {
			jxhjlx = dataSearchForm.getGrhj();
			if ("bjhj".equalsIgnoreCase(jxhjlx)) {
				xh = "";
			} else if ("yxhj".equalsIgnoreCase(jxhjlx)) {
				xh = "";
				zy = "";
				bj = "";
			}
		}
		if (xh == null) {
			xh = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (zy == null) {
			zy = "";
		}		
		if (dataType == null) {
			dataType = "";
		}
		if (userType.equalsIgnoreCase("xy")
				&& !dataType.equalsIgnoreCase("dormInfo")
				&& !dataType.equalsIgnoreCase("trainTime")) {
			xymc = userDep;
			dataSearchForm.setXydm(xymc);			
		}
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			xymc = Fdypd.fdybjck(userName,xymc);
			dataSearchForm.setXydm(xymc);
		}
		String tt = (String) request.getSession().getAttribute("userType");
		if ((xh != null) && !(xh.equalsIgnoreCase(""))) {
			if(dataType == "xlytqk" || "xlytqk".equalsIgnoreCase(dataType)){
				querry.append(" and a.xh = '");
				querry.append(xh);
				querry.append("' ");
			}else if(!("schoolBadgeAgain".equalsIgnoreCase(dataType) || "oneCardAgain".equalsIgnoreCase(dataType) || "studentPaperAgain".equalsIgnoreCase(dataType) ||"trainCheapAgain".equalsIgnoreCase(dataType))){
				querry.append(" and xh = '");
				querry.append(xh);
				querry.append("' ");
			}else if(tt.equals("stu")){
				querry.append(" and xh = '");
				querry.append(xh);
				querry.append("' ");
			}
		}
		if ((xm == null) || xm.equalsIgnoreCase("")) {
		} else {
			querry.append("and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		String csjg1 = DealString.toGBK(request.getParameter("csjg"));
		if("超标".equals(csjg1) && Globals.XXDM_ZGKYDX.equals(xxdm)){
			csjg1 = "0002";
		}
		if("良好".equals(csjg1) && Globals.XXDM_ZGKYDX.equals(xxdm)){
			csjg1 = "0001";
		}
		String csnj1 = DealString.toGBK(request.getParameter("csnj"));
		if(!"".equals(csjg1)){
			querry.append("and csjg like '%");
			querry.append(csjg1);
			querry.append("%' ");
		}
		if(!"".equals(csnj1)){
			querry.append("and csnj like '%");
			querry.append(csnj1);
			querry.append("%' ");
		}
		if(Globals.XXDM_ZJLG.equals(xxdm) && "view_xytbgxxsxx".equals(tableName)){
			String xlcslb = request.getParameter("xlcslb");
			String xlwtlx = request.getParameter("xlwtlx");
			String sfkns = request.getParameter("sfkns");
			String sfdq = request.getParameter("sfdq");
			if(StringUtils.isNotNull(xlcslb)){
				querry.append("and xlcslb like '%");
				querry.append(xlcslb);
				querry.append("%' ");
			}
			if(StringUtils.isNotNull(xlwtlx)){
				querry.append("and xlwtlx like '%");
				querry.append(xlwtlx);
				querry.append("%' ");
			}
			if(StringUtils.isNotNull(sfkns)){
				querry.append("and sfkns like '%");
				querry.append(sfkns);
				querry.append("%' ");
			}
			if(StringUtils.isNotNull(sfdq)){
				querry.append("and sfdq like '%");
				querry.append(sfdq);
				querry.append("%' ");
			}
		}

		if("xlytqk".equals(dataType)){
			String cxdtsj = DealString.toGBK(request.getParameter("dtsj"));
			if ((cxdtsj == null) || cxdtsj.equalsIgnoreCase("")) {
			} else {
				querry.append("and a.dtsj like '");
				querry.append(cxdtsj);
				querry.append("%' ");
			}
		}

		if ((nj == null) || nj.equalsIgnoreCase("")) {
			
		}else{
			querry.append(" and nj = '");
			querry.append(nj);
			querry.append("' ");
		}
		if (!StringUtils.isNull(kh)) {
			querry.append(" and kh = '");
			querry.append(kh);
			querry.append("' ");
		}
		if (!StringUtils.isNull(xxsh)) {
			querry.append(" and xxsh = '");
			querry.append(xxsh);
			querry.append("' ");
		}
		if ((xn == null) || xn.equalsIgnoreCase("")) {

		} else {
			querry.append(" and xn = '");
			querry.append(xn);
			querry.append("' ");
		}
		if ((xq == null) || xq.equalsIgnoreCase("")) {

		} else {
			querry.append(" and xq = '");
			querry.append(xq);
			querry.append("' ");
		}
		if ((sfzh == null) || "".equalsIgnoreCase(sfzh)) {

		} else if (!(dataType.equalsIgnoreCase("discipInfo"))) {
			querry.append(" and sfzh like '%");
			querry.append(sfzh);
			querry.append("%' ");
		}
		if ((zy == null) || zy.equalsIgnoreCase("")) {

		} else {
			querry.append(" and zydm = '");
			querry.append(zy);
			querry.append("' ");
		}
		if ((bj == null) || bj.equalsIgnoreCase("")||bj.equalsIgnoreCase("null")) {

		} else {
			querry.append(" and bjdm = '");
			querry.append(bj);
			querry.append("' ");
		}
		if ((xmdm == null) || xmdm.equalsIgnoreCase("")) {

		} else {
			if (tableName.equalsIgnoreCase("view_dwjlsq")) {
				querry.append("and jlxmdm = '");
				querry.append(xmdm);
				querry.append("' ");
			}
		}
		if ((hth == null) || ("".equalsIgnoreCase(hth))) {

		} else {
			querry.append(" and hth = '");
			querry.append(hth);
			querry.append("'");
		}
		if ((bzffny == null) || ("".equalsIgnoreCase(bzffny))) {

		} else {
			querry.append(" and bzffny = '");
			querry.append(bzffny);
			querry.append("'");
		}
		if ((xymc == null) || xymc.equalsIgnoreCase("")||xymc.equalsIgnoreCase("null")||"szdtxx".equals(dataType)
				||"xljkjyhd".equals(dataType)||"jzjsxx".equals(dataType)) {

		} else {
			querry.append(" and xydm = '");
			querry.append(xymc);
			querry.append("' ");
		}
		if ((xjzt == null) || xjzt.equalsIgnoreCase("")) {

		} else {
			querry.append(" and xjzt = '");
			querry.append(xjzt);
			querry.append("' ");
		}
		if (((nd == null) || nd.equalsIgnoreCase(" "))
				|| ((ndVar == null) || ("".equals(ndVar.trim())))) {

		} else if ((nd != null && !nd.trim().equals(""))
				|| ((ndVar != null) && !("".equals(ndVar.trim())))) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (tableName != null && tableName.equalsIgnoreCase("view_xshsxf")) {
			if ((nf == null) || nf.equalsIgnoreCase(" ")) {

			} else if (nf != null && !nf.trim().equals("")) {
				querry.append(" and nf='");
				querry.append(nf);
				querry.append("' ");
			}
			if ((yf == null) || yf.equalsIgnoreCase(" ")) {

			} else if (yf != null && !yf.trim().equals("")) {
				querry.append(" and yf='");
				querry.append(yf);
				querry.append("' ");
			}
		}

		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			//辅导员登录
			querry.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}	

		if(dataType.equalsIgnoreCase("xlytqk")){
			isXLPC = true;
			realTable = "xlytqkb";
			pk = "yt_id";
			tips = "心理健康 - 数据维护 - 心里约谈情况";
			tableName = "view_xlyt";
			colList = new String[] {"主键","xh","xm","xb","xymc","bjmc",
					 "ssbh","lxdzxx","sjhm"};
		} else {
			dataSearchForm.setErrMsg("S");
			return mapping.findForward("false");
		}
		String xsccdm = request.getParameter("xsccdm");
		if(xsccdm!=null&&!xsccdm.equalsIgnoreCase("")) {
			querry.append("and xsccdm = '");
			querry.append(xsccdm);
			querry.append("' ");
		}
		if (isFSBZ) {
			StringBuffer tempSb = new StringBuffer("");
			tempSb.append("select (case nvl(a.xxsh,'未审核') when '已通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,");
			tempSb.append("a.* from (select ");
			tempSb.append(pk);
			tempSb.append(" 主键,rownum 行号,a.* from ");
			tempSb.append(tableName);
			tempSb.append(" a");
			tempSb.append(querry.toString());
			tempSb.append(") a");
			sql = tempSb.toString();
		} else {
			StringBuffer tempSb = new StringBuffer("");
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)&& realTable.equalsIgnoreCase("lpxxb")){				
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" 主键,rownum r,a.* from ");
				tempSb.append("(select xh,nd,max(xm)xm,max(xymc)xymc,max(bjmc)bjmc,bxgsdm,max(bxgsmc)bxgsmc,max(slrq)slrq,max(slrq)slsj,max(dzsj)dzsj,sum(lpje)lpje,sum(hffy)hffy,trunc(sum(lpje)/sum(hffy)*100 ,2)||'%' 理赔率 from view_lpxx group by xh,nd,bxgsdm)");
				tempSb.append(" a");
				tempSb.append(querry);
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			} else if (Globals.XXDM_YCWSZYJSXY.equalsIgnoreCase(xxdm) && "zhszcp".equalsIgnoreCase(realTable)) {
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" 主键,rownum r,a.nd,a.xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,a.xh,a.xm,a.bjmc,a.dcj,a.zcj,a.tcj,a.zpf from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				tempSb.append(querry);
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY) && realTable.equalsIgnoreCase("xsrychb")) {
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" 主键,rownum r,a.nd,a.xn,a.xq,a.xh,a.xm,a.bjmc,a.rychmc,a.cjmc,a.zhpfmc,a.fdyqm,a.xyqm,(case when a.zysj is null then '' when length(ltrim(a.zysj,' '))>=5 then substr(ltrim(a.zysj,' '),0,5) else ltrim(a.zysj) end) zysj,a.xysh,a.xxsh from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				tempSb.append(querry + " )");

			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY) && realTable.equalsIgnoreCase("xsjxjb")) {
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" 主键,rownum r,a.* from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				tempSb.append(querry + " )");

			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) && realTable.equalsIgnoreCase("xlytqkb")) {
				dataSearchForm.getPages().setPageSize(100);
				tempSb.append("select * from (select ");
				tempSb.append("a."+pk);
				tempSb.append(" 主键,rownum r,a.xm,a.xb,a.xymc,a.bjmc,a.ssbh,x.* from ");
				tempSb.append(tableName);
				tempSb.append(" a,");
				tempSb.append(" xlytqkb x");
				tempSb.append(querry+"and a.yt_id=x.yt_id");
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) && realTable.equalsIgnoreCase("xlcsjgb")) {
				//dataSearchForm.getPages().setPageSize(100);
//				int dd= dataSearchForm.getPages().getPageSize();
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" 主键,rownum r,a.* from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				tempSb.append(querry);
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			}else{
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" 主键,rownum r,a.* from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				tempSb.append(querry);
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			}
			sql = tempSb.toString();

			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
				tempSb = new StringBuffer("");
				if (dataType.equalsIgnoreCase("amendsMainten")) {
					tempSb.append("select ");
					tempSb.append(pk);
					tempSb.append(" 主键,a.* from ");
					tempSb.append(tableName);
					tempSb.append(" a");
					tempSb.append(querry);
					tempSb.append(" and SFXFZRX='0'");
					sql = tempSb.toString();
				}
				if (dataType.equalsIgnoreCase("amendsMaintenBySch")) {
					tempSb.append("select ");
					tempSb.append(pk);
					tempSb.append(" 主键,a.* from ");
					tempSb.append(tableName);
					tempSb.append(" a");
					tempSb.append(querry);
					tempSb.append(" and SFXFZRX='1'");
					sql = tempSb.toString();
				}
			}
		}

		System.out.println(sql);
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			if (Globals.XXDM_SHGC.equalsIgnoreCase(xxdm) ) {
				if (dataType.equalsIgnoreCase("discipInfo") || dataType.equalsIgnoreCase("discipInfoHis")) {
					colList = new String[] { "主键", "xh", "xm", "xn", "nd", "xqmc",
							"cflbmc", "cfsj", "cfwh", "cfyymc" };
				}
			} 
			DataManModel dataManModel = getSearchModel(session, dao, colList,
					tableName);
			colListCN = dataManModel.getColListCN(tableName);
			topTr = dataManModel.getTopTr(tableName);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)
					&& "trainTime".equalsIgnoreCase(dataType)) {
				String zdz = DealString.toGBK(request.getParameter("zdz"));
				String qdz = DealString.toGBK(request.getParameter("qdz"));
				colList = new String[] { "主键", "cc", "dqzt",  "zdz",
						"qdz", "kcsj", "ddsj", "pj" };
				sql = "select "
					+ pk
					+ " 主键, cc,dqzt, zdzstr,qdzstr,(substr(zdzstr,1,(select instr(zdzstr,'!',1,1)-1 from dual))) zdz, "
					+ "(substr(zdzstr,(select instr(zdzstr,'!',1,1)+1 from dual),(select instr(zdzstr,'@',1,1)"
					+ " from dual)-(select instr(zdzstr,'!',1,1)+1 from dual))) ddsj ,"
					+ "(substr(zdzstr,(select instr(zdzstr,'@',1,1)+1 from dual),length(zdzstr)-(select instr(zdzstr,'@',1,1) from dual))) pj,"
					+ "(substr(qdzstr,1,(select instr(qdzstr,'!',1,1)-1 from dual))) qdz,"
					+ "(substr(qdzstr,(select instr(qdzstr,'!',1,1)+1 from dual),(select instr(qdzstr,'@',1,1) from dual)-(select instr(qdzstr,'!',1,1)+1 from dual))) kcsj"
					+ " from(select cc,dqzt,yxsj,(select substr(substr(tkz,instr(tkz,',"
					+ zdz
					+ "',1,1)+1,length(tkz)-instr(tkz,',"
					+ zdz
					+ "',1,1)),1,"
					+ "case when instr(substr(tkz,instr(tkz,',"
					+ zdz
					+ "',1,1)+1,length(tkz)-instr(tkz,',"
					+ zdz
					+ "',1,1)),',',1,1)=0"
					+ " then length(substr(tkz,instr(tkz,',"
					+ zdz
					+ "',1,1)+1,length(tkz)-instr(tkz,',"
					+ zdz
					+ "',1,1)))"
					+ "else instr( substr(tkz, instr(tkz,',"
					+ zdz
					+ "',1,1)+1, length(tkz)-instr(tkz,',"
					+ zdz
					+ "',1,1)),',',1,1)-1 end )"
					+ " from dual) zdzstr,(select substr(substr(tkz,instr(tkz,',"
					+ qdz + "',1,1)+1,length(tkz)-instr(tkz,'," + qdz
					+ "',1,1)),1,instr(substr(tkz,instr(tkz,'," + qdz
					+ "',1,1)+1,length(tkz)-instr(tkz,'," + qdz
					+ "',1,1)),',',1,1)-1) from dual) qdzstr"
					+ " from view_hccc " + querry + ")";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
			}
			if (Globals.XXDM_SHGC.equalsIgnoreCase(xxdm) ) {
				if (dataType.equalsIgnoreCase("discipInfo") || dataType.equalsIgnoreCase("discipInfoHis")) {
					colList = new String[] { "主键", "xh", "xm", "xn", "nd", "xqmc",
							"cflbmc", "cfsj", "cfwh", "cfyymc" };
				}
			} 
			rs = dao.rsToVator(sql, new String[] {}, colList);
			sql = "select count(*) cout from " + tableName + " a "+querry;
			dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao .getOneRs(sql, new String[] {}, "cout")));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		if (isHTXX) {
			request.setAttribute("isHTXX", "is");
		} else {
			request.setAttribute("isHTXX", "no");
		}
		if (isZXDK_XSXXCX) {
			request.setAttribute("isXSXXCX", "is");
		} else {
			request.setAttribute("isXSXXCX", "no");
		}
		if (isFSBZ) {
			request.setAttribute("isFSBZ", "is");
		} else {
			request.setAttribute("isFSBZ", "no");
		}
		if (realTable.equalsIgnoreCase("xshsxfb")) {
			request.setAttribute("yfList", dao.getYfList());
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			request.setAttribute("showbjlh", "showbjlh");
		}
		// TODO 分页

		xymc = xymc == null ? "" : xymc;
		zy = zy == null ? "" : zy;
		nj = nj == null ? "" : nj;
		String bjKey = xymc + "!!" + zy + "!!" + nj;

		bzffny = bzffny == null ? "" : bzffny;
		HashMap<String, String> rs1 = new HashMap<String, String>();
		rs1.put("xjzt", xjzt);
		rs1.put("bzffny", bzffny);
		request.setAttribute("rs1", rs1);
		// List<HashMap<String, String>> temp = (Base.getBjMap()).get(bjKey);

		xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				new String[] { "xxmc" })[0];
		request.setAttribute("xxmc", xxmc);// 取学校名称信息
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表		
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
//		request.setAttribute(arg0, arg1)
		FormModleCommon.setNdXnXqList(request);
		List zyList = (Base.getZyMap()).get(xymc);
		List bjList = (Base.getBjMap()).get(bjKey);


		if (isFdy.equalsIgnoreCase("true")) {

			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				request.setAttribute("iscsmz", "fdy");
			}
			String fdyName = session.getAttribute("userName").toString();

			fdyName = !StringUtils.isNull(fdyName) ? fdyName : "";
			//辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList (fdyName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList (fdyName));// 发送班级列表	
			zyList = Fdypd.getFdyZyList (fdyName);
		}

		String csjg;
		String csnj;
		Map cxmap = new HashMap();
		if("xlcsjgb".equals(realTable)){
			csjg = DealString.toGBK(request.getParameter("csjg"));
			csnj = DealString.toGBK(request.getParameter("csnj"));
			if(csjg != null || !"".equals(csjg)){
				cxmap.put("csjg", csjg);
			}
			if(csnj != null || !"".equals(csnj)){
				cxmap.put("csnj", csnj);
			}
		}
		request.setAttribute("cxrs", cxmap);

		request.setAttribute("zyList", zyList);// 发送专业列表		
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("ndList", ndList);// 获得5年内年度列表
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			//辅导员登录
			bjList = Fdypd.getFdybjList (userName);
		}	
		request.setAttribute("bjList", bjList);// 发送班级列表



		if(dataType.equalsIgnoreCase("discipInfo")
				&&xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){//南昌大学科学技术学院
			String tag = dao.returntag("select * from fdyxxb where zgh=? ", new String[]{userName});
			if ("notempty".equalsIgnoreCase(tag)) {//班主任编班
				String mySql = "select a.bjdm, bjmc from fdybjb b,view_njxyzybj a where a.bjdm=b.bjdm  and  b.zgh=?";
				request.setAttribute("bjList", dao.getList(mySql,new String[]{userName},new String[]{"bjdm","bjmc"}));// 发送班级列表
				isBzr = "yes";
			}else{
				isBzr = "no";
			}
		}
		
		
		request.setAttribute("isBzr", isBzr);
		String xsbbcx = request.getParameter("bbcx");
		if (isHTXX) {
			return new ActionForward("/sjcz/data_search_htxx.jsp", false);
		}if((realTable == "xszbbb" || realTable == "yktbbb" || realTable == "xhbbb" || realTable == "hcyhkbbb") && "bbcx".equals(xsbbcx)) {
			return new ActionForward("/sjcz/xszbbcx.jsp", false);
		}if(dataType.equalsIgnoreCase("xsbbcx")) {
			return new ActionForward("/sjcz/xszbbcx.jsp", false);
		}else if(isXLJKTJ){
			return new ActionForward("/sjcz/xljkjyqktj.jsp", false);
		}else if(isTSQTSQCX){
			return new ActionForward("/sjcz/tsqtxssqcx.jsp", false);
		}else if(isTSQTGJ){
			return new ActionForward("/sjcz/tsqtgj.jsp", false);
		}else if(isBJXZRY){
			return new ActionForward("/sjcz/bjxzry.jsp", false);
		}else if(isXLZXYY){
			return new ActionForward("/sjcz/xlzxyydj.jsp", false);
		}else if(isTSXSDJ){
			return new ActionForward("/sjcz/tsqtxsdj.jsp", false);
		}else if(isSZDTXX){
			return new ActionForward("/sjcz/szdtxx.jsp", false);
		}else if(isXSJBXX){
			return new ActionForward("/sjcz/gzxsjbqk.jsp", false);
		}else if(isXSZZQK){
			return new ActionForward("/sjcz/gzqk.jsp", false);
		}else if(isXLJKHD){
			return new ActionForward("/sjcz/xljkjyhd.jsp", false);
		}else if(isXLPC){
			return new ActionForward("/sjcz/xqpcyt.jsp", false);
		}else if(isJZJS){
			return new ActionForward("/sjcz/jzjsxx.jsp", false);
		}else if(isZXQK){ 
			return new ActionForward("/sjcz/xlzxqk.jsp", false);
		}else if (isZXDK_XSXXCX) {
			return new ActionForward("/sjcz/data_search_xsxxcx.jsp", false);
		} else if (isFSBZ) {
			return new ActionForward("/sjcz/data_search_fsbz.jsp", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)&&(dataType.equalsIgnoreCase("active")||dataType.equalsIgnoreCase("prepare"))) {
			return new ActionForward("/dtjs/jhzy/data_search_jhzy.jsp", false);
		}  else {
			return mapping.findForward("success");
		}
	}

	private DataManModel getSearchModel(HttpSession session, DAO dao, String[] colList, String tableName) {
		// TODO 自动生成方法存根
		DataManModel dataManModel = (DataManModel) session
		.getAttribute("dataManModel");
		if (dataManModel == null) {
			dataManModel = new DataManModel();
			dataManModel.setColListCN(dao, tableName, colList);
			dataManModel.setTopTr(dao, tableName, colList);
			session.setAttribute("dataManModel", dataManModel);
		} else if (!(dataManModel.containTable(tableName))) {
			dataManModel.setColListCN(dao, tableName, colList);
			dataManModel.setTopTr(dao, tableName, colList);
			session.setAttribute("dataManModel", dataManModel);
		}
		return dataManModel;
	}
}
