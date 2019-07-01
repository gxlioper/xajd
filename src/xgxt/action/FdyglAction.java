/*
 * 创建日期 2007-1-22  bat_zzj
 *
 */
package xgxt.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import Ice.SyscallException;

import common.Globals;

import xgxt.DAO.*;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.FdyglForm;

import xgxt.szdw.dao.common.CommonDAO;
import xgxt.szdw.server.common.CommonService;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.RowidToPk;

/** 辅导员管理 */
public class FdyglAction extends Action {

	//DAO dao = DAO.getInstance();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userType;

		String userDep;

		String sUName;

//		String logMsg;

		String writeAble;
		
		String sql;
		String inputSQL[];
		String outputSQL[];
		String tmp[];
		String yhz;//用户组
		
		HttpSession session = request.getSession();
		try {
			/** 在线检测 */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			boolean isStu = true;
			userType = session.getAttribute("userType").toString();
			isStu = (userType.equalsIgnoreCase("stu"));
			sUName = session.getAttribute("userName").toString();
			userDep = session.getAttribute("userDep").toString();

			/** 获取用户组 */
			DAO dao = DAO.getInstance();
			sql = "select zdm from yhb where yhm=?";
			inputSQL = new String[] { sUName };
			outputSQL = new String[] { "zdm" };
			tmp = dao.getOneRs(sql, inputSQL, outputSQL);
			if (tmp == null) {
				yhz = "6727";
			} else {
				yhz = tmp[0];
			}
			String zdm = yhz;
			request.setAttribute("zdm", zdm);
			ActionForward myActFwd = null;
			String act = mapping.getParameter();
			String power;
			int p = -1;

			if (act.equalsIgnoreCase("setCpdx")) {
				/** 设置参评对象 */
				power = "setCpdx.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myActFwd = setCpdx(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("setPjConf")) {
				/** 设置评价参数 */
				power = "setPjConf.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myActFwd = setPjConf(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("setPjzb")) {
				/** 设置评价指标 */
				power = "setPjzb.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)){
					myActFwd = setPjzbForXy(mapping, form, request, response, p);
				}else{
					myActFwd = setPjzb(mapping, form, request, response, p);
				}
			} else if (act.equalsIgnoreCase("setPfbz")) {
				/** 设置评分标准 */
				power = "setPjConf.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myActFwd = setPfbz(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("setFdyBj")) {
				/** 设置辅导员班级对照表 */
				power = "setFdyBj.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myActFwd = setFdyBj(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("pj")) {
				/** 学院相关评价 */
				if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)){
					myActFwd = pjForXy(mapping, form, request, response, p);
				}else{
					myActFwd = pj(mapping, form, request, response, p);
				}
			} else if (act.equalsIgnoreCase("fdypjjg")) {
				/** 评价结果 */
				power = "sxjy_jspj.do";
				p = Base.chkUPower(sUName, power, isStu);
				myActFwd = pjjg(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("fdypjjgDc")) {
				/** 评价结果导出 */
				myActFwd = fdyXxPjjgDc(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("fdypjZfDc")) {
				/** 评价结果导出 */
				myActFwd = fdypjZfDc(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("sxjy_fdypjDel")) {
				/** 评价数据删除 */
				power = "sxjy_jspj.do";
				p = Base.chkUPower(sUName, power, isStu);
				myActFwd = sxjy_fdypjDel(mapping, form, request, response);
			}else if (act.equalsIgnoreCase("fdypjcj")) {
				/** 评价 */
				power = "szdwfdypjcj.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myActFwd = pjcj(mapping, form, request, response, p);
			}else if(act.equalsIgnoreCase("szdw_xzcd")){   //云南艺术学院思政队伍信息维护操作类型选择菜单							
				myActFwd = szdwXzcd(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("szdw_xxQuery")){//云南艺术学院思政队伍信息维护信息查询公用模块					
				power = "fdyxx.do";
				p = Base.chkUPower(sUName, power, isStu);
				if(p == -1){
					request.setAttribute("errMsg", "无权访问该页面!");
					return new ActionForward("/errMsg.do",false);
				}
				myActFwd = szdwxxQuery(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("gzxxOne")){    //云南艺术学院思政人员工作信息操作	
				myActFwd = gzxxOne(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("jcxxOne")){    //云南艺术学院人员奖惩信息操作
				myActFwd = jcxxOne(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("ywpxxxOne")){  //云南艺术学院人员业务培训学习信息操作
				myActFwd = ywpxxxOne(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("wjstwh")){//调查问卷试题维护
				power = "wjstwh.do";
				p = Base.chkUPower(sUName,power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myActFwd = wjstwh(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("wjstwh_make")){				
				myActFwd = wjstwh_make(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("wjstxxwh")){
			    myActFwd = wjstxxwh(mapping,form,request,response);	
			}else if(act.equalsIgnoreCase("wjstxxwh_make")){
				myActFwd = wjstxxwh_make(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("wj_view")){
				myActFwd = wj_view(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("fdywork_research")){				
				myActFwd = fdywork_research(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("xscppTj")){		
				//思政考核-广州大学-学生测评票统计导出
				myActFwd = xscppTj(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("szdw_xstpxx")){		
				//思政考核-广州大学-学生投票信息
				myActFwd = szdw_xstpxx(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("fdywork_result")){
				p = Base.chkUPower(sUName,"fdywork_result.do", isStu);
				if(p == -1){
					request.setAttribute("errMsg","无权访问该页面!");
					return new ActionForward("/errMsg.do&",false);
				}
				myActFwd = fdywork_result(mapping,form,request,response);
			}
			writeAble = (p == 1) ? "yes" : "no";
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("ok", request.getParameter("ok"));
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "出现故障，" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
	}

	/**
	 * 思政队伍思政考核学生投票信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	private ActionForward szdw_xstpxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		
		FdyglForm myForm  = (FdyglForm)form; 
		
		if(userType.equalsIgnoreCase("xy")){
			myForm.setXydm(userDep);
		}
		
		CommonService myService = new CommonService();
		if(("go").equalsIgnoreCase(request.getParameter("go"))){
			ArrayList<String[]> rs = myService.getXstpxx(myForm);
			List topTr = myService.getXstpxxTop();
		
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		if(myForm.getXn()==null){
			myForm.setXn(Base.currXn);
		}
		if(myForm.getXq()==null){
			myForm.setXq(Base.currXq);
		}
		if(myForm.getNd()==null){
			myForm.setNd(Base.currNd);
		}
		FormModleCommon.requestSetList(new String[]{"xn","xq"},request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.commonRequestSet(request);
		return  mapping.findForward("success");
	}

	private ActionForward sxjy_fdypjDel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 辅导员删除
		boolean flag = false;
		flag=StandardOperation.delete("xspfb_lsb", "1", "1",request);
		if(flag) {
			flag=StandardOperation.delete("xspfb", "1", "1",request);
		}
		if(flag) {
			flag=StandardOperation.delete("fdypjjlb", "1", "1",request);
		}
		return pjjg(mapping, form, request, response, 1);
	}

	private ActionForward fdypjZfDc(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		// 评价总分导出
		boolean flag = false;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		DAO dao = DAO.getInstance();
		flag=StandardOperation.delete("fdykhzfdclsb", "1", "1",request);
		if(flag) {
			String sql = "insert into fdykhzfdclsb select sum(e.pjfs),e.jszgh,(select xm from fdyxxb where zgh = e.jszgh) xm,e.khqzdm," +
					"(select qzmc from szdw_fdykhqzb where e.khqzdm = qzdm) qzmc from (select round(sum(a.pjfs * b.pfbl " +
					" * c.fz) / (count(distinct a.xh)),2) pjfs,b.qtmc,a.jszgh,b.khqzdm from xspfb a,(select b.pfbl, c.yhm, c.zdm, " +
					"b.qtmc, b.khqzdm from yhzb a, pjqtb b, yhb c where a.zdm = b.qtdm and c.zdm = a.zdm) b, pjzbb c where a.xh " +
					"= b.yhm and c.xh = a.pjzbxh and a.xh != a.jszgh group by b.zdm, b.qtmc, a.jszgh, b.khqzdm) e group by jszgh, " +
					"khqzdm";
			flag = dao.runUpdate(sql, new String [] {});
		}
		if(flag) {
			String sql = "insert into fdykhzfdclsb select sum(e.pjfs),e.jszgh,(select xm from fdyxxb where zgh = e.jszgh) xm,e.khqzdm,"+
					"(select qzmc from szdw_fdykhqzb where e.khqzdm = qzdm) qzmc " +
					" from (select round(sum(a.pjfs*b.pfbl*c.fz)/(count(distinct a.xh)),2) pjfs,b.qtmc,a.jszgh,b.khqzdm from xspfb a," +
					"(select pfbl, qtmc,khqzdm from pjqtb where qtdm='6727') b,pjzbb c,view_xsjbxx d where c.xh=a.pjzbxh and a.xh=d.xh" +
					" group by qtmc,a.jszgh,b.khqzdm) e group by jszgh,khqzdm";
			flag = dao.runUpdate(sql, new String [] {});
		}
		if(flag) {
			String sql = "insert into fdykhzfdclsb select sum(e.pjfs),e.jszgh,(select xm from fdyxxb where zgh = e.jszgh) xm,e.khqzdm,"+
					"(select qzmc from szdw_fdykhqzb where e.khqzdm = qzdm) qzmc " +
					" from (select round(sum(a.pjfs*b.pfbl*c.fz)/(count(distinct a.xh)),2) pjfs, b.qtmc,a.jszgh,b.khqzdm from xspfb a," +
					"(select b.pfbl,b.qtmc,b.khqzdm from  pjqtb b where b.qtdm ='999999') b,pjzbb c where a.xh = a.jszgh" +
					" and c.xh=a.pjzbxh group by b.qtmc,a.jszgh, b.khqzdm) e group by jszgh,khqzdm";
			flag = dao.runUpdate(sql, new String [] {});
		}
		if(flag) {
			String sql = "";// sql语句
			StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
			List<Object> rs = new ArrayList<Object>();
			String bmdm = DealString.toGBK(request.getParameter("xydm"));
			String nj   = DealString.toGBK(request.getParameter("nj"));
			if (!isNull(bmdm)) {
				querry.append(" and bmdm='");
				querry.append(bmdm);
				querry.append("' ");
			}
			if (!isNull(nj)&&!nj.equalsIgnoreCase("no")) {
				querry.append(" and fgnj='");
				querry.append(nj);
				querry.append("' ");
			}else if(nj.equalsIgnoreCase("no")){
				querry.append(" and (fgnj is null or fgnj = '') ");
			}
			sql = "select * from view_fdykhzfdcls " + querry.toString()+" order by pjfs desc";
			
			String[] colList = dao.getColumnName("select * from view_fdykhzfdcls where 1=2");// 获得列名数组
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			
			//取详细信息的sql
			List<Object> rsAll = new ArrayList<Object>();
			sql = "select jszgh,sum(pjfs) pjfs,qzmc from fdykhzfdclsb a group by a.jszgh,qzmc";
			rsAll.addAll(dao.rsToVator(sql, new String[] {}, new String []{"jszgh","pjfs","qzmc"}));
			CommonDAO myDAO = new CommonDAO(); 
			String [] khqzs= myDAO.getKhqzs();
			int lengTmp = khqzs.length;
			String[] colListCN = new String [lengTmp+5];
			colListCN [0] = "教师职工号";
			colListCN [1] = "姓名";
			colListCN [2] = "部门名称";
			colListCN [lengTmp+3] = "评价总分";
			colListCN [lengTmp+4] = "名次";
			for(int i = 0;i<lengTmp;i++){
				colListCN [3+i] = khqzs[i];
			}

			try {
				for (int m = 0; m < colListCN.length; m++) {
					ws.addCell(new Label(m, 0, colListCN[m]));
				}
				for (int i = 0; i < rs.size(); i++) {
					String[] tmp = (String[]) rs.toArray()[i];
					String jszgh = tmp[0];
					int t = i+1;
					ws.addCell(new Label(0, t, jszgh));
					ws.addCell(new Label(1, t, tmp[1]));
					ws.addCell(new Label(2, t, tmp[4]));
					ws.addCell(new Label(lengTmp+3, t, tmp[2]));
					ws.addCell(new Label(lengTmp+4, t, ((Integer)(t)).toString()));
					for(int j = 0;j<rsAll.size();j++){
						String[] tmpAll = (String[]) rsAll.toArray()[j];
						if(jszgh.equalsIgnoreCase(tmpAll[0])){
							for(int k = 0;k<khqzs.length;k++){
								if(khqzs[k].equalsIgnoreCase(tmpAll[2]))
								ws.addCell(new Label(k+3, i + 1, tmpAll[1]));
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("数据导出失败!");
			} finally {
				wwb.write();
				wwb.close();
			}
		}
		return mapping.findForward("success");
	}
	
	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase(""));
	}

	private ActionForward pjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int p)
	throws Exception {
		FdyglForm szkhForm = (FdyglForm) form;
		DAO dao = DAO.getInstance();
		SxjyDAO sxjyDao = new SxjyDAO();
		String go = request.getParameter("go");
		String bmdm = szkhForm.getXydm();
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		String pk = szkhForm.getZxm();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		
		String userType = dao.getUserType(userDep);
		FdyglForm comform = (FdyglForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			comform.setXydm(userDep);
			bmdm = userDep;
		}
		pk = isNull(pk) ? " " : pk;
		querry.append(isNull(bmdm)? " and 1=1 ":" and bmdm='" + bmdm + "' ");
		if ((go != null) && go.equalsIgnoreCase("go")) {
			Vector<String[]> rs = new Vector<String[]>();
			rs.addAll(sxjyDao.getJspjFs(pk));
			rs.addAll(sxjyDao.getJspjForstuFs(pk));
			rs.addAll(sxjyDao.getJspjForZp(pk));
			Double count = 0.00;
			for(int i=0;i<rs.size();i++){
				count = count+Double.parseDouble(rs.get(i)[1]);
			}
			request.setAttribute("count", new java.text.DecimalFormat("#,##0.00").format(count));
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", String.valueOf(rs.size()));
		}
		
		String sql = "select * from view_fdyxx where zgh=? ";
		String[] inputSQL = new String[] { pk };
		String[] outputSQL = new String[] { "zgh", "xm", "xb", "lxdh", "zwmc", "zyzz",
		"bmmc" };
		HashMap<String, String> fdyInfo = dao.getMap(sql, inputSQL, outputSQL);
		if (fdyInfo == null) {
			fdyInfo = new HashMap<String, String>();
		}
		String fgnj = szkhForm.getNj();
		
		request.setAttribute("fdyInfo", fdyInfo);		    
		szkhForm.setXydm(bmdm);
		if(Fdypd.isSzdw(userName)){
			szkhForm.setZxm(userName);
			request.setAttribute("zxm", userName);
		}
		
		request.setAttribute("path", "sxjy_jspj.do");
		request.setAttribute("title", FormModleCommon.getWriteAbleAndTitle(request)[1]);
		
		request.setAttribute("bmList", dao.getXyList());
		request.setAttribute("bmxyList",getBmxyList(userDep));
		request.setAttribute("writeAble", request.getParameter("writeAble"));
		request.setAttribute("userType", userType);
		request.setAttribute("cpzList", sxjyDao.getCpzList());
		request.setAttribute("zgList", sxjyDao.getFdyList(bmdm,fgnj));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("tableName", "view_fdyxx");
		return mapping.findForward("success");
	}

	private ActionForward pjcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int p) {

		FdyglForm szkhForm = (FdyglForm) form;
		String xydm = szkhForm.getXydm();
		xydm = Base.chgNull(xydm, "%", 0);
		return null;
	}

	/** 设置参评对象 */
	private ActionForward setCpdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {

		String sUName;
		String logMsg;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		sUName = session.getAttribute("userName").toString();
		FdyglForm szkhForm = (FdyglForm) form;
		String xydm = szkhForm.getXydm();
		xydm = Base.chgNull(xydm, "%", 0);
 
		String act = request.getParameter("act");
		String sql;
		if(userType.equalsIgnoreCase("xy")){
			xydm = userDep;
		}
		if (!Base.isNull(act)) {
			if (power == 0) {
				request.setAttribute("errMsg", "无权做此操作！");
				return new ActionForward("/errMsg.do", false);
			}
			boolean ok = false;
			if (!act.equalsIgnoreCase("all") & !act.equalsIgnoreCase("none")) {
				ok = dao.runProcuder("{call chgFdySfCp(?)}",
						new String[] { act });
				logMsg = "更新参评对象。";
			} else if (act.equalsIgnoreCase("all")) {
				sql = "insert into fdypfb(xn,nd,xq,zgh) select ?,?,?,zgh from fdyxxb "
					+ "where zgh not in(select zgh from fdypfb where xn=? and "
					+ "nd=? and xq=?) and bmdm like ?";
				ok = dao.runUpdate(sql, new String[] { Base.currXn,
						Base.currNd, Base.currXq, Base.currXn, Base.currNd,
						Base.currXq, xydm });
				logMsg = "设置所有辅导员可参评。";
			} else {
				sql = "delete fdypfb where xn=? and nd=? and xq=? and zgh "
					+ "in(select zgh from fdyxxb where bmdm like ?)";
				ok = dao.runUpdate(sql, new String[] { Base.currXn,
						Base.currNd, Base.currXq, xydm });
				logMsg = "设置所有辅导员不可参评。";
			}
			if (ok) {
				Base.log(request, logMsg, sUName);
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
			sql = "select zgh,xm,xb,zwmc,bmmc,lxdh,(case when exists(select *"
				+ " from view_fdypf b where a.zgh=b.zgh and xn=? and nd=? and"
				+ " xq=?) then '参评' else '不参评' end ) color from "
				+ "view_fdyxx a where bmdm like ? order by color desc ,bmmc,xm";
			
		String[] outSql = new String[] { "zgh", "xm", "xb", "zwmc", "bmmc",
				"color", "lxdh" };
		String[] inSql = new String[] { Base.currXn, Base.currNd, Base.currXq,
				xydm };
		List rs = dao.getList(sql, inSql, outSql);
		szkhForm.setXydm(xydm);
		szkhForm.setXn(Base.currXn);
		szkhForm.setNd(Base.currNd);
		szkhForm.setXq(Base.currXq);
		request.setAttribute("rs", rs);
		request.setAttribute("bmList", dao.getXyList());
		request.setAttribute("bmxyList",getBmxyList(userDep));
		request.setAttribute("rsNum", Integer.toString(rs.size()));
		request.setAttribute("bcpColor", "#000000");
		return mapping.findForward("success");
	}

	/** 设置评价参数 */
	private ActionForward setPjConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {

		String sUName;
		String logMsg;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		sUName = session.getAttribute("userName").toString();

		FdyglForm pjForm = (FdyglForm) form;
		String act = request.getParameter("act");
		String sql;
		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapTmp;
		String[] tmp;

		tmp = new String[] {};
		mapTmp = new HashMap<String, Object>();
		sql = "delete from pjqtb where qtdm not in(select zdm from yhzb) and qtdm !='999999'";
		mapTmp.put("sqlTxt", sql);
		mapTmp.put("sqlVal", tmp);
		sqlToExe.add(mapTmp);

		mapTmp = new HashMap<String, Object>();
		sql = "insert into pjqtb(qtdm,qtmc) select zdm,zmc from yhzb where zdm not in(select qtdm from pjqtb)";
		mapTmp.put("sqlTxt", sql);
		mapTmp.put("sqlVal", tmp);
		sqlToExe.add(mapTmp);

		dao.runUpdate(sqlToExe);

		if (!Base.isNull(act) && act.equalsIgnoreCase("save")) {
			if (power == 0) {
				request.setAttribute("errMsg", "无权做此操作！");
				return new ActionForward("/errMsg.do", false);
			}

			boolean ok = false;
			sqlToExe = new Vector<HashMap<String, Object>>();

			String[] qtdm = pjForm.getQtdm();
			String[] sfcp = pjForm.getSfcp();
			String[] pfbl = pjForm.getPfbl();
			String[] pfbz = pjForm.getPfbz();
			String[] khqzdm = pjForm.getKhqzdm();
			String sfkpj = request.getParameter("sfkpj");
			sfkpj = (Base.isNull(sfkpj)) ? "否" : DealString.toGBK(sfkpj);

			if ((qtdm == null) || (sfcp == null) || (pfbl == null)
					|| (pfbz == null) || (qtdm.length != sfcp.length)
					|| (qtdm.length != pfbl.length)
					|| (qtdm.length != pfbz.length)) {
				request.setAttribute("ok", "no");
			} else {
				for (int i = 0; i < qtdm.length; i++) {
					mapTmp = new HashMap<String, Object>();
					sql = "update pjqtb set sfcp=?,pfbl=?,pfbz=?,khqzdm=? where qtdm=?";
					tmp = new String[] { DealString.toGBK(sfcp[i]), pfbl[i], pfbz[i],khqzdm[i],
							qtdm[i] };
					mapTmp.put("sqlTxt", sql);
					mapTmp.put("sqlVal", tmp);
					sqlToExe.add(mapTmp);
				}
				mapTmp = new HashMap<String, Object>();
				sql = "update xtszb set sfkpj=?";
				tmp = new String[] { sfkpj };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
				ok = dao.runUpdate(sqlToExe);
				logMsg = "更新了思政考核参评对象。";

				if (ok) {
					Base.log(request, logMsg, sUName);
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			}
		}

		sql = "select qtdm,(case(a.qtdm) when '999999' then a.qtmc else b.zmc end) qtmc,sfcp,pfbl,pfbz,khqzdm from pjqtb a left join yhzb b on a.qtdm = b.zdm  order by qtdm";
		String[] in = new String[] {};
		List cpqtList = dao.getList(sql, in, new String[] { "qtdm", "qtmc",
				"sfcp", "pfbl", "pfbz","khqzdm" });
		String sfkpj = dao.getConf(5);
		sql = "select distinct bzbh bzdm,bzmc from pfbzb";
		List pfbzList = dao.getList(sql, in, new String[] { "bzdm", "bzmc" });

		request.setAttribute("pfbzList", pfbzList);
		request.setAttribute("sfkpj", sfkpj);
		request.setAttribute("yesNoList", dao.getChkList(2));
		request.setAttribute("cpqtList", cpqtList);
		CommonDAO myDAO = new CommonDAO(); 
		request.setAttribute("khqzList", myDAO.getKhqzList());
		return mapping.findForward("success");
	}

	/** 设置评价指标 */
	private ActionForward setPjzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
//		String userType;

//		String userDep;

		String sUName;

		String logMsg;

//		String writeAble;

		HttpSession session = request.getSession();
//		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
//		userDep = session.getAttribute("userDep").toString();

		FdyglForm fdyForm = (FdyglForm) form;
		String[] tmp;
		String mxdx = fdyForm.getMxdx();
		String query = "";
		if(!Base.isNull(mxdx)) {
			query = " and mxdx = '";
			query +=mxdx;
			query +="' "; 
		}
		String[] getPara = { "xh","stlbmc","pjzb", "fz", "mxdx","scojzb"};
		String sql = "select xh,(select stlbmc from szdw_fdykhstdlb where stlbdm = a.stlbdm) stlbmc,pjzb,fz,scojzb,b.qtmc mxdx from pjzbb a,pjqtb b where a.mxdx=b.qtdm "+query+" order by mxdx,xh";
		List rslist = dao.getList(sql, new String[] {}, getPara);
		String act = request.getParameter("act");
		sql = "select qtmc,qtdm from pjqtb";
		List mxdxList = dao.getList(sql, new String[] {}, new String[] {
				"qtdm", "qtmc" });
		request.setAttribute("mxdxList", mxdxList);
		if (!Base.isNull(act)) {
			if (power == 0) {
				request.setAttribute("errMsg", "无权做此操作！");
				return new ActionForward("/errMsg.do", false);
			}

			boolean ok = false;

			if (act.equalsIgnoreCase("add")) {
				fdyForm.setFz(""); 
				fdyForm.setPjh("");
				fdyForm.setPjnr("");
				fdyForm.setMxdx("");
				fdyForm.setJtzb("");
				fdyForm.setStlbdm("");
				fdyForm.setScojzb("");
				request.setAttribute("doType", "add");
				CommonDAO myDAO = new CommonDAO(); 
				request.setAttribute("stlblist", myDAO.getStlbList());
				return new ActionForward("/fdygl/pjzbOne.jsp", false);
			} else if (act.equalsIgnoreCase("modi")) {
				String zbh = request.getParameter("id");
				sql = "select xh pjh,pjzb pjnr,fz,mxdx,jtzb,stlbdm,scojzb from pjzbb where xh=?";
				String[] oneInfo = new String[] { "pjh", "pjnr", "fz", "mxdx","jtzb","stlbdm","scojzb" };
				String[] res = dao.getOneRs(sql, new String[] { zbh }, oneInfo);
				if (res == null) {
					res = new String[5];
				}

				fdyForm.setPjh(res[0]);
				fdyForm.setPjnr(res[1]);
				fdyForm.setFz(res[2]);
				fdyForm.setMxdx(res[3]);
				fdyForm.setJtzb(res[4]);
				fdyForm.setStlbdm(res[5]);
				fdyForm.setScojzb(res[6]);
				request.setAttribute("operation", request.getParameter("operation"));
				request.setAttribute("doType", "modi");
				CommonDAO myDAO = new CommonDAO(); 
				request.setAttribute("stlblist", myDAO.getStlbList());
				return new ActionForward("/fdygl/pjzbOne.jsp", false);
			} else if (act.equalsIgnoreCase("save")) {
				String fz = fdyForm.getFz();
				String pjh = fdyForm.getPjh();
				String pjnr = fdyForm.getPjnr();
				String stlbdm = fdyForm.getStlbdm();
				String scojzb = DealString.toGBK(fdyForm.getScojzb());
				String jtzb = DealString.toGBK(fdyForm.getJtzb());
				String doType = request.getParameter("doType");
				if (!Base.isNull(pjnr)) {
					pjnr = DealString.toGBK(pjnr);
				}

				if (!Base.isNull(doType) && doType.equalsIgnoreCase("add")) {
					sql = "insert into pjzbb(xh,pjzb,mxdx,fz,jtzb,stlbdm,scojzb) values(?,?,?,?,?,?,?)";
					tmp = new String[] { pjh, pjnr, mxdx, fz,jtzb,stlbdm,scojzb };
					logMsg = "添加思政考核评价指标，指标号为：" + pjh;
				} else {
					sql = "update pjzbb set pjzb=?,mxdx=?,fz=?,jtzb=?,stlbdm=?,scojzb=? where xh=?";
					tmp = new String[] { pjnr, mxdx, fz, jtzb,stlbdm,scojzb,pjh};
					logMsg = "修改思政考核评价指标，指标号为：" + pjh;
				}

				ok = dao.runUpdate(sql, tmp);
				String dest = "/setPjzb.do?act=modi&id=" + pjh;
				if (ok) {
					Base.log(request, logMsg, sUName);
					dest += "&ok=ok";
				} else {
					request.setAttribute("ok", "no");
					dest += "&ok=no";
				}
				CommonDAO myDAO = new CommonDAO(); 
				request.setAttribute("stlblist", myDAO.getStlbList());
				return new ActionForward(dest,
						false);
			} else if (act.equalsIgnoreCase("del")) {
				String pjh = fdyForm.getPjh();
				sql = "delete pjzbb where xh=?";
				tmp = new String[] { pjh };
				logMsg = "删除思政考核评价指标，指标号为：" + pjh;

				ok = dao.runUpdate(sql, tmp);
				String dest = null;
				if (ok) {
					Base.log(request, logMsg, sUName);
					dest = "/setPjzb.do?ok=ok";
				} else {
					dest = "/setPjzb.do?ok=no";
				}
				return new ActionForward(dest, true);
			}
		}
		request.setAttribute("rslist", rslist);
		request.setAttribute("rsNum", Integer.toString(rslist.size()));
		return mapping.findForward("success");
	}

	/** 设置评分标准 */
	private ActionForward setPfbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
//		String userType;

//		String userDep;

		String sUName;

		String logMsg;

//		String writeAble;

		HttpSession session = request.getSession();
//		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
//		userDep = session.getAttribute("userDep").toString();

		FdyglForm fdyForm = (FdyglForm) form;
		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapTmp;
		String[] tmp;
		String sql;
		String bzbh = fdyForm.getBzbh();
		bzbh = Base.chgNull(bzbh, "%", 0);

		sql = "select distinct bzbh,bzmc from pfbzb order by bzbh";
		tmp = new String[] { "bzbh", "bzmc" };
		List bzList = dao.getList(sql, new String[] {}, tmp);
		String act = request.getParameter("act");

		if (!Base.isNull(act)) {
			if (power == 0) {
				request.setAttribute("errMsg", "无权做此操作！");
				return new ActionForward("/errMsg.do", false);
			}

			boolean ok = false;

			if (act.equalsIgnoreCase("add")) {
				fdyForm.setBzbh("");
				fdyForm.setBzmc("");
				fdyForm.setPfxm("");
				fdyForm.setQz("");
				fdyForm.setXssx("");
				request.setAttribute("bzList", bzList);
				request.setAttribute("doType", "add");
				return new ActionForward("/fdygl/pfbzOne.jsp", false);
			} else if (act.equalsIgnoreCase("modi")) {
				String bh = request.getParameter("bzid");
				String xm = request.getParameter("pfxm");
				xm = DealString.toGBK(xm);
				sql = "select bzbh,bzmc,pfbz,qz,xssx from pfbzb where bzbh=? and pfbz=?";
				String[] oneInfo = new String[] { "bzbh", "bzmc", "pfbz",
						"qz", "xssx" };
				String[] res = dao.getOneRs(sql, new String[] { bh, xm },
						oneInfo);
				if (res == null) {
					res = new String[5];
				}

				fdyForm.setBzbh(res[0]);
				fdyForm.setBzmc(res[1]);
				fdyForm.setPfxm(res[2]);
				fdyForm.setQz(res[3]);
				fdyForm.setXssx(res[4]);
				String operation = request.getParameter("operation");
				request.setAttribute("operation", operation);
				request.setAttribute("bzList", bzList);
				request.setAttribute("doType", "modi");
				return new ActionForward("/fdygl/pfbzOne.jsp", false);
			} else if (act.equalsIgnoreCase("save")) {
				String bzmc = fdyForm.getBzmc();
				String pfxm = fdyForm.getPfxm();
				String qz = fdyForm.getQz();
				String xssx = fdyForm.getXssx();
				String oldPfxm = request.getParameter("oldPfxm");
				if (!Base.isNull(bzmc)) {
					bzmc = DealString.toGBK(bzmc);
				}
				if (!Base.isNull(pfxm)) {
					pfxm = DealString.toGBK(pfxm);
				}
				if (!Base.isNull(oldPfxm)) {
					oldPfxm = DealString.toGBK(oldPfxm);
				}

				mapTmp = new HashMap<String, Object>();
				sql = "delete pfbzb where bzbh=? and pfbz=?";
				tmp = new String[] { bzbh, oldPfxm };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);

				mapTmp = new HashMap<String, Object>();
				sql = "insert into pfbzb(bzbh,bzmc,pfbz,qz,xssx) values(?,?,?,?,?)";
				tmp = new String[] { bzbh, bzmc, pfxm, qz, xssx };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);

				logMsg = "添加思政考核评分标准，标准编号为：" + bzbh + "，评分项目为：" + pfxm;

				ok = dao.runUpdate(sqlToExe);
				String dest = "/setPfbz.do?act=modi&bzid=" + bzbh+ "&pfxm=" + pfxm;
				
				if (ok) {
					Base.log(request, logMsg, sUName);
					dest += "&ok=ok";
				} else {
					dest += "&ok=no";
				}

				return new ActionForward(dest, false);
			} else if (act.equalsIgnoreCase("del")) {
				String bh = request.getParameter("bzid");
				String xm = request.getParameter("pfxm");
				xm = DealString.toGBK(xm);
				sql = "delete pfbzb where bzbh=? and pfbz=?";
				tmp = new String[] { bh, xm };
				logMsg = "删除思政考核评价指标，指标号为：" + bh;

				ok = dao.runUpdate(sql, tmp);
				String dest = null;
				if (ok) {
					Base.log(request, logMsg, sUName);
					dest = "/setPfbz.do?ok=ok";
				} else {
					dest = "/setPfbz.do?ok=no";
				}
				return new ActionForward(dest, true);
			}
		}

		tmp = new String[] { "bzbh", "bzmc", "pfbz", "qz", "xssx" };
		sql = "select bzbh,bzmc,pfbz,qz,xssx from pfbzb where bzbh like ? order by bzbh,xssx";
		List rsList = dao.getList(sql, new String[] { bzbh }, tmp);
		
		request.setAttribute("doType", request.getParameter("doType"));
		request.setAttribute("bzList", bzList);
		request.setAttribute("rslist", rsList);
		request.setAttribute("rsNum", Integer.toString(rsList.size()));
		return mapping.findForward("success");
	}

	/** 设置辅导员班级对照表 */
	private ActionForward setFdyBj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
		String userType;

		String userDep;

		String sUName;

		String logMsg;

//		String writeAble;
		
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		userDep = session.getAttribute("userDep").toString();
		String tableName = "";
		String viewName = "";
		
		FdyglForm fdyForm = (FdyglForm) form;
		String szdwbb = fdyForm.getSzdwbb();
		if(szdwbb==null||szdwbb.equalsIgnoreCase("")){
			szdwbb="fdy";
		}
		if(szdwbb.equalsIgnoreCase("fdy")){
			tableName = "fdybjb";
			viewName = "view_bjfdyxgxx";
		}else{
			tableName = "bzrbbb";
			viewName = "view_bjBzrxgxx";
		}
		request.setAttribute("qx", szdwbb);
		fdyForm.setSzdwbb(szdwbb);
		
		String sql="";;
		String sql2="";
		String sql3="";
		String inputSQL[];
		String outputSQL[];
		String bmdm = fdyForm.getXydm();
		String jsbmdm = fdyForm.getBmdm();
		String zgh = fdyForm.getZgh();
		String nj = fdyForm.getNj();
		String zydm = fdyForm.getZydm();
		String fdyxm = fdyForm.getFdyxm();
		String cxZgh = fdyForm.getCxZgh();
		String cxXm=fdyForm.getCxXm();
		String act = request.getParameter("act");
		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapTmp;
		String[] tmp;
		bmdm = Base.chgNull(bmdm, "%", 0);
		nj = Base.chgNull(nj, "%", 0);
		zydm = Base.chgNull(zydm, "%", 0);
		zgh = Base.chgNull(zgh, "", 0);
		fdyxm = Base.chgNull(fdyxm, "", 0);

		userDep = request.getSession().getAttribute("userDep")
				.toString();
		userType = dao.getUserType(userDep);
		if ("xy".equalsIgnoreCase(userType)) {
			fdyForm.setXydm(userDep);
			bmdm = userDep;
		}

		/** 保存 */
		if (!Base.isNull(act) && act.equalsIgnoreCase("save")) {
			if (power == 0) {
				request.setAttribute("errMsg", "无权做此操作！");
				return new ActionForward("/errMsg.do", false);
			}

			boolean ok = false;

			String[] bjdm = fdyForm.getBjdm();
			if (bjdm == null || Base.isNull(zgh)) {
				sql = "delete from "+tableName+" where zgh=?";
				ok = dao.runUpdate(sql, new String[] { zgh });
				logMsg = "清空了代码为" + zgh + "的辅导员名下的班级";
			} else {
				mapTmp = new HashMap<String, Object>();
				sql = "delete from "+tableName+" where zgh=?";
				tmp = new String[] { zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
				logMsg = "维护辅导员班级对照表，辅导员代码为" + zgh + "名下的班级有";
				for (int i = 0; i < bjdm.length; i++) {
					mapTmp = new HashMap<String, Object>();
					sql = "insert into "+tableName+" (zgh,bjdm) values(?,?)";
					tmp = new String[] { zgh, bjdm[i] };
					mapTmp.put("sqlTxt", sql);
					mapTmp.put("sqlVal", tmp);
					sqlToExe.add(mapTmp);
					logMsg += bjdm[i] + ",";
				}
				ok = dao.runUpdate(sqlToExe);
			}

			if (ok) {
				Base.log(request, logMsg, sUName);
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		String queryTmp = " where 1=1 ";
		String queryTmp2 = "";
		if(jsbmdm!=null&&!jsbmdm.equalsIgnoreCase("")){
			queryTmp += " and bmdm = '"+jsbmdm+"'";
		}
		if(cxZgh!=null&&!cxZgh.equalsIgnoreCase("")){
			queryTmp += " and zgh like '%"+cxZgh+"%'";
		}
		if(cxXm!=null&&!cxXm.equalsIgnoreCase("")){
			queryTmp += " and xm like  '%"+cxXm+"%'";
		}
		if(bmdm!=null&&!bmdm.equalsIgnoreCase("")){
			queryTmp2 += " and '"+bmdm+"' in (b.bmfdm,b.bmdm) ";
		}
		
		//浙江理工过滤为职务为辅导员的才能编班
		String xxdm = Base.xxdm;
		if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){
			if(szdwbb.equalsIgnoreCase("fdy")){
				queryTmp += " and zwmc like '%辅导员%' ";
				queryTmp2 += " and zwmc like '%辅导员%'" ;
			}else{
				queryTmp += " and zwmc like '%班主任%' ";
				queryTmp2 += " and zwmc like '%班主任%' ";
			}
		}
		if("fdy".equalsIgnoreCase(szdwbb)){
			sql = "select zgh,xm,xb from view_fdyxx "+queryTmp+" and sfbb='是' order by zgh";
			sql3 = "select zgh,xm,xb from view_fdyxx "+queryTmp+" and sfbb='否' order by zgh";			
		}else{
			sql="select zgh,xm,xb from view_fdyxx a "+queryTmp+" and exists(select 1 from bzrbbb b where a.zgh=b.zgh) order by zgh";
			sql3="select zgh,xm,xb from view_fdyxx a "+queryTmp+" and not exists(select 1 from bzrbbb b where a.zgh=b.zgh) order by zgh";
		}
		sql2 = "select zgh,xm,xb from view_fdyxx a,zxbz_xxbmdm b where a.bmdm=b.bmdm "+queryTmp2+" order by a.zgh";
		inputSQL = new String[] {};
		outputSQL = new String[] { "zgh", "xm", "xb" };
		List dbFdyList = dao.getList(sql, inputSQL, outputSQL);
		List wdbFdyList = dao.getList(sql3, inputSQL, outputSQL);
		List fdyListxy = dao.getList(sql2, inputSQL, outputSQL);
		if(Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)||Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)){
			sql = "select distinct bjdm,bjmc from view_njxyzybj where nj like ? and"
				+ " xydm like ? and zydm like ? and bjdm not in(select bjdm from "
				+tableName+" where zgh = '"+zgh+"')";
			sql2 = "select distinct bjdm,bjmc from view_njxyzybj where nj like ? and"
				+ " xydm=? and zydm like ? and bjdm not in(select bjdm from "
				+tableName+" where zgh = '"+zgh+"')";
		}else {
			sql = "select distinct bjdm,bjmc from view_njxyzybj_all where nj like ? and"
				+ " xydm like ? and zydm like ? and bjdm not in(select bjdm from "
				+tableName+")";
			sql2 = "select distinct bjdm,bjmc from view_njxyzybj_all where nj like ? and"
				+ " xydm=? and zydm like ? and bjdm not in(select bjdm from "
				+tableName+")";
		}
		inputSQL = new String[] { nj, bmdm, zydm };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List bjList = dao.getList(sql, inputSQL, outputSQL);
		List bjListxy = dao.getList(sql2, inputSQL, outputSQL);
		sql = "select distinct bjdm,bjmc||'  ('||ltrim(xm,' ')||')' bjmc from "+viewName+" where nj like ? and"
			+ " xydm like ? and zydm like ? and bjdm in(select bjdm from "+tableName+" where zgh != '"+zgh+"')";
		List yyzBjList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select  bjdm,bjmc from view_njxyzybj a where exists (select 1 from "+tableName+" b where zgh=? and a.bjdm=b.bjdm )";
		inputSQL = new String[] { zgh };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List fdyBjList = dao.getList(sql, inputSQL, outputSQL);
		
		sql = "select * from view_fdyxx where zgh=?";
		inputSQL = new String[] { zgh};
		outputSQL = new String[] { "zgh", "xm", "xb", "lxdh", "zwmc", "zyzz",
				"bmmc" };
		HashMap<String, String> fdyInfo = dao.getMap(sql, inputSQL, outputSQL);
		if (fdyInfo == null) {
			fdyInfo = new HashMap<String, String>();
		}
		fdyForm.setFdyxm(fdyxm);
		sql = "select distinct bmdm,bmmc from ZXBZ_XXBMDM ";
		System.out.println("xxdm:"+xxdm);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			sql += "where bmlb = '5' ";
		}
		sql2 = "select distinct bmdm,bmmc from ZXBZ_XXBMDM where bmfdm=?";
		List<HashMap<String, String>> partList = dao.getList(sql, new String[]{},new String[]{"bmdm", "bmmc"});
		//该学院的部门
		List<HashMap<String, String>> partxyList = dao.getList(sql2, new String[]{userDep},new String[]{"bmdm", "bmmc"});
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			request.setAttribute("bmList", dao.getBmList());//fdyglstruts.xml配置文件中
			//该学院的部门
			request.setAttribute("bmxyList", getBmxyList(userDep));//fdyglstruts.xml配置文件中
		}else{
			System.out.println("sql:"+sql);
			request.setAttribute("bmList", partList);
			request.setAttribute("bmxyList", partxyList);
		}
		
		request.setAttribute("path", "setFdyBj.do");
		
		request.setAttribute("title", FormModleCommon.getWriteAbleAndTitle(request)[1]);
		request.setAttribute("dbFdyList", dbFdyList);
		request.setAttribute("wdbFdyList", wdbFdyList);
		request.setAttribute("fdyListxy", fdyListxy);
		request.setAttribute("bjList", bjList);
		request.setAttribute("bjListxy", bjListxy);
		request.setAttribute("yyzBjList", yyzBjList);
		request.setAttribute("fdyBjList", fdyBjList);
		request.setAttribute("fdyInfo", fdyInfo);
		CommonService service = new CommonService();
		request.setAttribute("fpfs", service.getFpfs(szdwbb));
		request.setAttribute("njList",  Base.getNjList());// 发送年级列表
		request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		if(bmdm.equalsIgnoreCase("%")){
			bmdm = "";
		}
		request.setAttribute("zyList", (Base.getZyMap()).get(bmdm));// 发送专业列表
		return mapping.findForward("success");
	}

	/** 评价 */
	private ActionForward pj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
		String userType;

		String userDep;

		String sUName;

		String logMsg;


		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		userDep = session.getAttribute("userDep").toString();
		String xxdm = session.getAttribute("xxdm").toString();

		FdyglForm fdyForm = (FdyglForm) form;
		String act = request.getParameter("act");
		String doType = request.getParameter("do");
		String sql;
		String inputSQL[];
		String outputSQL[];
		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapTmp;
		String tmp[];
		String[] pjh = fdyForm.getPfbz();
		String[] pj = fdyForm.getPjfs();
		String bmdm = fdyForm.getXydm();
		if(userType.equalsIgnoreCase("xy")){
			bmdm = userDep;
		}
		String zgh = fdyForm.getZgh();
		String yhz;
		String pfbz;
		bmdm = Base.chgNull(bmdm, "%", 0);

		/** 判断评价是否开放 */
		sql = "select sfkpj from xtszb where sfkpj='否'";
		inputSQL = new String[] {};
		outputSQL = new String[] { "sfkpj" };
		tmp = dao.getOneRs(sql, inputSQL, outputSQL);
		if (tmp != null) {
			request.setAttribute("sfcp", "time");
			return mapping.findForward("success");
		}

		/** 保存 */
		if (!Base.isNull(act) && act.equalsIgnoreCase("save") && pjh != null
				&& pj != null && pjh.length == pj.length) {
			boolean ok = false;
			sql = "update xspfb_lsb set pjfs=? where xn=? and nd=? and xq=? "
				+ "and xh=? and jszgh=? and pjzbxh=?";
			for (int i = 0; i < pj.length; i++) {
				mapTmp = new HashMap<String, Object>();
				tmp = new String[] { Base.chgNull(pj[i], "", 0), Base.currXn,
						Base.currNd, Base.currXq, sUName, zgh, pjh[i] };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
			}
			logMsg = "保存了对辅导员（" + zgh + "）的评价。";
			ok = dao.runUpdate(sqlToExe);
			/** 提交 */
			if (!Base.isNull(doType) && doType.equalsIgnoreCase("submit")) {
				mapTmp = new HashMap<String, Object>();
				sql = "delete xspfb where xn=? and nd=? and xq=? "
					+ "and xh=? and jszgh=? ";
				tmp = new String[] { Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);

				mapTmp = new HashMap<String, Object>();
				sql = "insert into xspfb select * from xspfb_lsb where xn=?"
					+ " and nd=? and xq=? and xh=? and jszgh=?";
				tmp = new String[] { Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);

				mapTmp = new HashMap<String, Object>();
				sql = "delete fdypjjlb where xn=? and nd=? and xq=? and xh=? and jszgh=?";
				tmp = new String[] { Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);

				mapTmp = new HashMap<String, Object>();
				sql = "insert into fdypjjlb(xn,nd,xq,xh,jszgh) values(?,?,?,?,?)";
				tmp = new String[] { Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);

				logMsg = "提交了对辅导员（" + zgh + "）的评价。";
				ok = dao.runUpdate(sqlToExe);
			}
			if (ok) {
				Base.log(request, logMsg, sUName);
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		/** 获取用户组 */
		sql = "select zdm from yhb where yhm=?";
		inputSQL = new String[] { sUName };
		outputSQL = new String[] { "zdm" };
		tmp = dao.getOneRs(sql, inputSQL, outputSQL);
		if (tmp == null) {
			yhz = "6727";
		} else {
			yhz = tmp[0];
		}
		String zyhz = yhz;
		if(sUName.equalsIgnoreCase(zgh)) {
			yhz ="999999";
			request.setAttribute("zwpj", "true");
		}
		/** 判断用户是否参评，若参评，获取评分标准 */
		sql = "select pfbz,khqzdm from pjqtb where sfcp='是' and qtdm=?";
		inputSQL = new String[] { yhz };
		outputSQL = new String[] { "pfbz","khqzdm" };
		tmp = dao.getOneRs(sql, inputSQL, outputSQL);
		if (tmp == null) {
			request.setAttribute("sfcp", "no");
			return mapping.findForward("success");
		}
		pfbz = tmp[0];
		String khqzdm = tmp[1];
		if(khqzdm == null){
			if (tmp != null) {
				request.setAttribute("sfcp", "out");
				return mapping.findForward("success");
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)){
			if(yhz.equalsIgnoreCase("999999")){
				sql = "select pfbz,khqzdm from pjqtb where sfcp='是' and qtdm=?";
				inputSQL = new String[] { zyhz };
				outputSQL = new String[] { "pfbz","khqzdm" };
				khqzdm = dao.getOneRs(sql, inputSQL, outputSQL)[1];
			}
			if(khqzdm!=null&&khqzdm.equalsIgnoreCase("002")){
				sql = "select fgnj from view_fdyxx where zgh = ?";
				String sjnj = dao.getOneRs(sql, new String []{sUName}, "fgnj");
				if(sjnj!=null&&!sjnj.equalsIgnoreCase("")){
					fdyForm.setNj(sjnj);
					request.setAttribute("sjnj", sjnj);
				}else{
					fdyForm.setNj("no");
					request.setAttribute("sjnj", "no");
				}
			}
		}
		/** 获取评价对象列表 */
		String fgnj = fdyForm.getNj();
		if (userType.equalsIgnoreCase("stu")) {
			sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
				+ " where xn=? and nd=? and xq=? ) and (zgh in(select"
				+ " zgh from fdybjb c where bjdm in(select bjdm from view_xsjbxx d "
				+ "where xh=?)) or zgh in (select"
				+ " zgh from bzrbbb c where bjdm in(select bjdm from view_xsjbxx d "
				+ "where xh=?)))";
			inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					sUName,sUName };
		} else if (userType.equalsIgnoreCase("xy")) {
			if(fgnj==null||fgnj.equalsIgnoreCase("")){		
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ?";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					bmdm };
			}else if(fgnj.equalsIgnoreCase("no")){
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ? and (fgnj is null or fgnj = '')";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					bmdm};
			}else{
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ? and fgnj = ?";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					bmdm,fgnj};
			}
		} else {
			if(fgnj==null||fgnj.equalsIgnoreCase("")){		
			sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
				+ " where xn=? and nd=? and xq=?) and bmdm like ?";
			inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					bmdm };
			}else if(fgnj.equalsIgnoreCase("no")){
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ? and (fgnj is null or fgnj = '')";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
						bmdm };
			}else{
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ? and fgnj = ?";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
						bmdm,fgnj };
			}
		}
		outputSQL = new String[] { "zgh", "xm" };
		List fdyList = dao.getList(sql, inputSQL, outputSQL);
		/** 获取评分标准列表 */
		sql = "select pfbz,qz from pfbzb where bzbh=? order by xssx";
		inputSQL = new String[] { pfbz };
		outputSQL = new String[] { "pfbz", "qz"};
		List pfbzList = dao.getList(sql, inputSQL, outputSQL);

		if (!Base.isNull(zgh)) {
			
			sql = "select count(*) num from pjzbb where mxdx=?";
			inputSQL = new String[] { yhz };
			outputSQL = new String[] { "num" };
			tmp = dao.getOneRs(sql, inputSQL, outputSQL);

			int totNum = 0;
			totNum = Integer.parseInt(tmp[0]);

			sql = "select count(*) num from xspfb_lsb where xn=? and nd=? and xq=? and xh=? and jszgh=?";
			inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					sUName, zgh };
			outputSQL = new String[] { "num" };
			tmp = dao.getOneRs(sql, inputSQL, outputSQL);

			if (Integer.parseInt(tmp[0]) != totNum) {
				mapTmp = new HashMap<String, Object>();
				sql = "insert into xspfb_lsb(xh,pjzbxh,jszgh,xn,nd,xq) select ?,xh,?,?,?,? from pjzbb"
					+ " where xh not in(select pjzbxh from xspfb_lsb where xn=? and nd=? "
					+ "and xq=? and xh=? and jszgh=?) and mxdx=? ";
				tmp = new String[] { sUName, zgh, Base.currXn, Base.currNd,
						Base.currXq, Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh, yhz };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);

				mapTmp = new HashMap<String, Object>();
				sql = "delete xspfb_lsb where pjzbxh not in(select xh from pjzbb where mxdx=?) and xn=? and nd=?"
					+ " and xq=? and xh=? and jszgh=?";
				tmp = new String[] { yhz, Base.currXn, Base.currNd,
						Base.currXq, sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);

				dao.runUpdate(sqlToExe);
			}

			/** 获取评价指标列表 */
			sql = "select a.pjzbxh,a.pjfs,a.py,a.bz,b.pjzb,b.fz,b.jtzb jtbz,b.scojzb,(select stlbmc from szdw_fdykhstdlb where b.stlbdm = stlbdm ) stlbmc from xspfb_lsb a,pjzbb b where a.xh=?"
				+ " and jszgh=? and to_char(a.pjzbxh)=b.xh order by a.pjzbxh";
			inputSQL = new String[] { sUName, zgh };
			outputSQL = new String[] { "pjzbxh", "pjfs", "pjzb", "fz","jtbz","scojzb","stlbmc" };
			List pjzbList = dao.getList(sql, inputSQL, outputSQL);
			request.setAttribute("pjzbList", pjzbList);
		}

		sql = "select count(*) num from fdypjjlb where xn=? and nd=? and xq=? and xh=? and jszgh=?";
		inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
				sUName, zgh };
		outputSQL = new String[] { "num" };
		tmp = dao.getOneRs(sql, inputSQL, outputSQL);
		if (Integer.parseInt(tmp[0]) == 0) {
			request.setAttribute("sfpj", "no");
		} else {
			request.setAttribute("sfpj", "yes");
		}

		fdyForm.setXn(Base.currXn);
		fdyForm.setNd(Base.currNd);
		fdyForm.setXq(Base.currXq);
		fdyForm.setXydm(bmdm);
		request.setAttribute("sfcp", "yes");
		request.setAttribute("pfbzList", pfbzList);
		request.setAttribute("fdyList", fdyList);
		request.setAttribute("njList", Base.getNjList());
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)){
			sql = "select distinct bmdm xydm,bmmc xymc from ZXBZ_XXBMDM ";			
			List<HashMap<String, String>> partList = dao.getList(sql, new String[]{},new String[]{"xydm", "xymc"});
			
			request.setAttribute("xyList", partList);
		}else {
			request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		}
		return mapping.findForward("success");
	}
	/** 云南艺术学院 操作类型选择菜单*/
	private ActionForward szdwXzcd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = request.getParameter("writeAble");
		List<HashMap<String, String>> SzdwTypeList = new ArrayList<HashMap<String,String>>();	        
		String [] Typemc = {"个人信息","工作信息","奖惩信息","业务培训学习信息"};
		String [] Typelj = {"fdyxx.do?act=persInfo","szdw_xxQuery.do?tableName=view_szdw_gzxx",
				"szdw_xxQuery.do?tableName=view_szdw_jcxx","szdw_xxQuery.do?tableName=view_szdw_ywpxxx"};	   					
		for(int i = 0;i < Typemc.length;i++){
			HashMap<String, String> temmap = new HashMap<String, String>();
			temmap.put("typelj", Typelj[i]);
			temmap.put("typemc",Typemc[i]);
			SzdwTypeList.add(temmap);
		}		
		request.setAttribute("SzdwTypeList", SzdwTypeList);
		request.setAttribute("rs", map);
		request.setAttribute("writeAble",writeAble);
		return mapping.findForward("success");
	}
	/** 云南艺术学院 思政工作人员信息查询公用模块*/
	private ActionForward szdwxxQuery(ActionMapping mapping,ActionForm form,
			 HttpServletRequest request,HttpServletResponse response){
		FdyglForm fdyglform = (FdyglForm) form;
		DAO dao = DAO.getInstance();
		SxjyDAO sxjyDao = new SxjyDAO();
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		String go = request.getParameter("go");
		String bmdm = fdyglform.getXydm();
		String zgh = fdyglform.getZxm();
		String writeAble = request.getParameter("writeAble");
		String tableName = request.getParameter("tableName");
		String realTable ="" ;//request.getParameter("realTable");
		String userDep = request.getSession().getAttribute("userDep").toString(); 
		String userType = dao.getUserType(userDep);
		String[] colList = null;
		String[] colListCN = null;
		List topTr = null;
		String sql="";
		String tips="";
		FdyglForm comform = (FdyglForm) form;
		if("view_szdw_gzxx".equalsIgnoreCase(tableName)){  
			tips = " 工作信息"; 
		}else if("view_szdw_jcxx".equalsIgnoreCase(tableName)){
			tips = " 奖惩信息";
		}else if("view_szdw_ywpxxx".equalsIgnoreCase(tableName)){
			tips = " 业务培训学习信息";		    	 
		}
		if ("xy".equalsIgnoreCase(userType)) {
			comform.setXydm(userDep);
			bmdm = userDep;
		}
		querry.append(isNull(zgh)?" and 1=1":" and zgh = '"+zgh+"'");
		querry.append(isNull(bmdm)?" and 1=1":" and bmdm = '"+bmdm+"'");
		if ((go != null) && go.equalsIgnoreCase("go")){	
			if("view_szdw_gzxx".equalsIgnoreCase(tableName)){//工作信息
				colList = new String[]{"gzjldm","xn","xq","rq","zwmc","zyzz","gzzt","pj"};
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				sql = "select gzjldm,xn,xq,rq,zwmc,zyzz,gzzt,pj from "+ tableName;
			}else if("view_szdw_jcxx".equalsIgnoreCase(tableName)){//奖惩信息
				colList = new String[]{"jcjldm","jcwjh","jcwjm","jclb","jcsj"};
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				sql = "select jcjldm,jcwjh,jcwjm,jclb,jcsj from "+ tableName;
			}else if("view_szdw_ywpxxx".equalsIgnoreCase(tableName)){//业务培训信息
				colList = new String[]{"ywpxjldm","pxxm","ywpxwj","ywpxspr","pxcjry","pxsj","pxdd","pxf","qtf","fykzbm","总结"};
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				sql = "select ywpxjldm,pxxm,ywpxwj,ywpxspr,pxcjry,pxsj,pxdd,pxf,qtf,fykzbm,(case when  xxzj is null then  '无' else '有' end) 总结 from "+ tableName;
			}
			sql += querry;
			Vector<String[]> rs =new Vector<String[]>();
			rs.addAll(dao.rsToVator(sql, new String[]{}, colList));
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum",String.valueOf(rs.size()));
			request.setAttribute("topTr", topTr);
		}  
		fdyglform.setXydm(bmdm);
		String zgxm ="";
		if(!isNull(zgh)){
			sql="select xm from fdyxxb where zgh = ?";
			zgxm= dao.getOneRs(sql,  new String[]{zgh}, "xm");		          	      		          
		}
		request.setAttribute("zgxm", zgxm);
		request.setAttribute("userType", userType);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("bmList",  Base.getXyList());// 发送学院列表
		request.setAttribute("bmxyList",getBmxyList(userDep));
		request.setAttribute("zgList", sxjyDao.getFdyList(bmdm));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("writeAble",writeAble);
		request.setAttribute("tips",tips);
		return mapping.findForward("success");
	}
	/**云南艺术学院 思政工作人员信息数据操作*/
	private ActionForward gzxxOne(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{	
		FdyglForm fdyglform = (FdyglForm) form;
		SxjyDAO sxjyDao = new SxjyDAO();		 
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		String zgh = fdyglform.getZxm();
		String writeAble = request.getParameter("writeAble");
		String userDep = request.getSession().getAttribute("userDep").toString(); 
		String userType = dao.getUserType(userDep);
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String tips = " 信息添加  ";
		if(doType!=null){
			if("save".equalsIgnoreCase(doType)){
				//zgh = request.getParameter("zgh");
				String gzzt = DealString.toGBK(request.getParameter("gzzt"));
				String xn = request.getParameter("xn");
				String xq = request.getParameter("xq");
				String rq = request.getParameter("rq");
				String nr = DealString.toGBK(request.getParameter("nr"));
				String pj = DealString.toGBK(request.getParameter("pj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean res = false;
				if("".equalsIgnoreCase(pk)||pk==null){//添加
					sql = "insert into szdw_gzxx(gzjldm,zgh,xn,xq,rq,gzzt,nr,pj,bz) values(S_SZDWGZXX_ID.nextval,?,?,?,?,?,?,?,?)";				       
					try{
						res = dao.runUpdate(sql, new String[] { zgh,xn,xq,rq,gzzt,nr,pj,bz});				   
					}catch (Exception e){
						res = false;
					}
					if (res) {
						dao.writeLog(sql, new String[]{zgh,xn,xq,rq,gzzt,nr,pj,bz}, new String[]{}, "增加数据",request);	
						request.setAttribute("done", "ok");
					} else {
						request.setAttribute("done", "no");
					} 
				}else{//修改
					sql = "update szdw_gzxx set xn=?,xq=?,rq=?,gzzt=?,nr=?,pj=?,bz=? where gzjldm=?";					     
					try{
						res = dao.runUpdate(sql, new String[]{xn,xq,rq,gzzt,nr,pj,bz,pk});					    
					}catch(Exception e){
						res = false;
					}
					if (res) {
						dao.writeLog(sql, new String[]{zgh,xn,xq,rq,gzzt,nr,pj,bz}, new String[]{}, "修改数据",request);	
						request.setAttribute("done", "ok");
					} else {
						request.setAttribute("done", "no");
					} 
				}
			}		   
			if("modi".equalsIgnoreCase(doType)){//显示修改
				tips = " 信息修改  ";
				sql = "select zgh,xn,xq,rq,gzzt,nr,pj,bz from szdw_gzxx where gzjldm =?";
				String[] colList = new String[] { "zgh", "xn", "xq", "rq", "gzzt", "nr","pj","bz"};
				String[] colV =  dao.getOneRs(sql, new String[]{pk}, colList);
				if (colV == null){
					colV = new String[colList.length];
				}
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i], isNull(colV[i]) ? "" : colV[i]);
				}
			}
			if("del".equalsIgnoreCase(doType)){
				boolean res = false; 
				String[] oldVals =dao.getOneRs("select ZGH,XN,XQ,RQ,GZZT,NR,PJ,BZ from szdw_gzxx where gzjldm=?", new String[] {pk}, new String[] {"ZGH","XN","XQ","RQ","GZZT","NR","PJ","BZ"});				
				sql = "delete from szdw_gzxx where gzjldm=?";
				try{
					res = dao.runUpdate(sql, new String[] {pk});				   
				}catch (Exception e){
					res = false;
				}
				if (res) {
					dao.writeLog(sql, new String[]{}, oldVals, "删除数据",request);	
					request.setAttribute("del", "ok");
				} else {
					request.setAttribute("del", "no");
				}
				return new ActionForward("/szdw_xxQuery.do?go=go&tableName=view_szdw_gzxx",false);   
			}
		}
		sql = "select zgh, xm,(case(xb) when '1' then '男' when '2' then '女' end) xb, bmdm, zw, zyzz,zzmm,xl," +
		"csrq,zc,lxgzsj from fdyxxb where zgh = ?";
		String[] colList = new String[] { "zgh", "xm", "xb", "bmdm", "zw", "zyzz","zzmm","xl","csrq","zc","lxgzsj"};
		String[] colV = dao.getOneRs(sql, new String[] { zgh }, colList);
		if (colV == null) {
			colV = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			map.put(colList[i], isNull(colV[i]) ? "" : colV[i]);
		}
		if("xy".equalsIgnoreCase(userType)){
			map.put("bmdm", userDep);
		}
		String[] bjList = sxjyDao.getFdyBjList(zgh);
		String[] zyList = sxjyDao.getZyBjList(zgh);
		request.setAttribute("fdybjList", bjList);
		request.setAttribute("fdyzyList", zyList);
		request.setAttribute("xbList", dao.getSexList());
		request.setAttribute("doType", doType);	
		request.setAttribute("rs", map);
		sql = "select zwdm,zwmc from zwb";
		List zwList = dao.getList(sql, new String[] {}, new String[] { "zwdm",
		"zwmc" });
		request.setAttribute("bmList",  Base.getXyList());// 发送学院列表
		request.setAttribute("bmxyList",getBmxyList(userDep));
		request.setAttribute("zwList", zwList);
		request.setAttribute("JsZzmmList", sxjyDao.getJsZzmmList());
		request.setAttribute("JsXlList", sxjyDao.getJsXlList());
		request.setAttribute("sfList", sxjyDao.getSfList());
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pk);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		request.setAttribute("tips", tips);
		return mapping.findForward("success");	   
	}
	/**云南艺术学院 思政队伍奖惩信息数据操作*/
	private ActionForward jcxxOne(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		FdyglForm fdyglform =  (FdyglForm) form;
		SxjyDAO sxjyDao=new SxjyDAO();
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		String zgh = fdyglform.getZxm();
		String writeAble = request.getParameter("writeAble");
		String userDep = request.getSession().getAttribute("userDep").toString(); 
		String userType = dao.getUserType(userDep);
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String tips = " 信息添加  ";
		if(doType!=null){
			if ("save".equalsIgnoreCase(doType)){
				String jcwjh = DealString.toGBK(request.getParameter("jcwjh"));
				String jcwjm = DealString.toGBK(request.getParameter("jcwjm"));
				String jclb = DealString.toGBK(request.getParameter("jclb"));
				String jcsj = DealString.toGBK(request.getParameter("jcsj"));
				String jcyy = DealString.toGBK(request.getParameter("jcyy"));
				String jcsy = DealString.toGBK(request.getParameter("jcsy"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean res = false;
				if("".equalsIgnoreCase(pk)||pk==null){//添加
					sql="insert into szdw_jcxx(jcjldm,jcwjh,jcwjm,jclb,jcsj,jcyy,jcsy,bz,zgh) values(S_SZDWJCXX_ID.nextval,?,?,?,?,?,?,?,?)";
					try{
						res=dao.runUpdate(sql, new String[]{jcwjh,jcwjm,jclb,jcsj,jcyy,jcsy,bz,zgh});
					}catch(Exception e){
						res=false;
					}
					if(res){
						dao.writeLog(sql, new String[]{jcwjh,jcwjm,jclb,jcsj,jcyy,jcsy,bz,zgh}, new String[]{}, "增加数据",request);	
						request.setAttribute("done", "ok");
					}else{
						request.setAttribute("done", "no");
					}
				}else{//修改
					sql="update szdw_jcxx set jcwjh=?,jcwjm=?,jclb=?,jcsj=?,jcyy=?,jcsy=?,bz=? where jcjldm=? ";
					dao.writeLog(sql, new String[]{jcwjh,jcwjm,jclb,jcsj,jcyy,jcsy,bz,zgh}, new String[]{}, "修改数据",request);					
					try{
						res=dao.runUpdate(sql, new String[]{jcwjh,jcwjm,jclb,jcsj,jcyy,jcsy,bz,pk});
					}catch(Exception e){
						res=false;
					}
					if(res){
						request.setAttribute("done", "ok");
					}else{
						request.setAttribute("done", "no");
					}
				}

			}
			if("modi".equalsIgnoreCase(doType)){//显示修改
				tips = " 信息修改  ";
				sql="select jcwjh,jcwjm,jclb,jcsj,jcyy,jcsy,bz from szdw_jcxx where jcjldm =? ";
				String[] colList = new String[]{"jcwjh","jcwjm","jclb","jcsj","jcyy","jcsy","bz"};
				String[] colV = dao.getOneRs(sql, new String[]{pk}, colList);
				if(colV==null){
					colV=new String[colList.length];
				}
				for(int i=0;i<colV.length;i++){
					map.put(colList[i], isNull(colV[i]) ? "" : colV[i]);
				}

			}
			if("del".equalsIgnoreCase(doType)){//删除
				boolean res = false;
				sql = "delete from szdw_jcxx where jcjldm = ?";
				try{
					res = dao.runUpdate(sql, new String[]{pk});
				}catch(Exception e){
					res = false;
				}
				if(res){
					res = true;
				}else{
					res = false;
				}
				return new ActionForward("/szdw_xxQuery.do?go=go&tableName=view_szdw_jcxx",false);
			}

		}
		sql = "select  zgh, xm,(case(xb) when '1' then '男' when '2' then '女' end) xb, bmdm, zw, zyzz,zzmm," +
		"xl,csrq,zc,lxgzsj,gzjl,xsgzyjfx,grhjqk,fblw,kyjl from fdyxxb where zgh =?";
		String[] colList = new String[]{"zgh","xm","xb","bmdm","zw","zyzz","zzmm","xl","csrq","zc","lxgzsj","gzjl","xsgzyjfx","grhjqk",
				"fblw","kyjl"};
		String[] colV = dao.getOneRs(sql, new String[]{zgh}, colList);
		if(colV == null){
			colV = new String[colList.length];	
		}
		for(int i=0;i<colList.length;i++){
			map.put(colList[i],isNull(colV[i])? "" : colV[i]);
		}
		if("xy".equalsIgnoreCase(userType)){
			map.put("bmdm", userDep);
		}

		List <HashMap<String,String>> jctypeList = new ArrayList<HashMap<String,String>>();//奖惩类别列表
		String[] jctype = new String[]{"奖励","惩罚"};
		for(int i=0;i<jctype.length;i++){
			HashMap<String,String> temMap = new HashMap<String,String>();
			temMap.put("jclb", jctype[i]);
			jctypeList.add(temMap);
		}
		request.setAttribute("jclbList", jctypeList);
		String[] bjList = sxjyDao.getFdyBjList(zgh);
		String[] zyList = sxjyDao.getZyBjList(zgh);
		request.setAttribute("fdybjList", bjList);
		request.setAttribute("fdyzyList", zyList);
		request.setAttribute("xbList", dao.getSexList());
		request.setAttribute("doType", doType);	
		request.setAttribute("rs", map);
		sql = "select zwdm,zwmc from zwb";
		List zwList = dao.getList(sql, new String[] {}, new String[] { "zwdm",
		"zwmc" });
		request.setAttribute("bmList",  Base.getXyList());// 发送学院列表
		request.setAttribute("bmxyList",getBmxyList(userDep));
		request.setAttribute("zwList", zwList);
		request.setAttribute("JsZzmmList", sxjyDao.getJsZzmmList());
		request.setAttribute("JsXlList", sxjyDao.getJsXlList());
		request.setAttribute("sfList", sxjyDao.getSfList());
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pk);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		request.setAttribute("tips", tips);
		return mapping.findForward("success");	
	}
	/**云南艺术学院 思政工作人员业务培训信息数据操作*/
	private ActionForward ywpxxxOne(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{	
		FdyglForm fdyglform = (FdyglForm) form;
		SxjyDAO sxjyDao = new SxjyDAO();
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String pk = RowidToPk.rowidToPK(request.getParameter("pk"));
		String zgh = fdyglform.getZxm();
		String writeAble = request.getParameter("writeAble");
		String userDep = request.getSession().getAttribute("userDep").toString(); 
		String userType = dao.getUserType(userDep);
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String tips = " 信息添加  ";
		if(doType!=null){
			if("save".equalsIgnoreCase(doType)){
				String pxxm = DealString.toGBK(request.getParameter("pxxm"));
				String pxdd = DealString.toGBK(request.getParameter("pxdd"));
				String pxsj = request.getParameter("pxsj");
				String pxnr = DealString.toGBK(request.getParameter("pxnr"));
				String pxcjry = DealString.toGBK(request.getParameter("pxcjry"));
				String xxzj = DealString.toGBK(request.getParameter("xxzj"));
				String ywpxwj = DealString.toGBK(request.getParameter("ywpxwj"));
				String ywpxspr = DealString.toGBK(request.getParameter("ywpxspr"));
				String pxf = DealString.toGBK(request.getParameter("pxf"));
				String qtf = DealString.toGBK(request.getParameter("qtf"));
				String fykzbm = DealString.toGBK(request.getParameter("fykzbm"));
				boolean res = false;
				if("".equalsIgnoreCase(pk)||pk==null){//添加
					sql ="insert into szdw_ywpxxx(ywpxjldm,pxxm,pxsj,pxdd,pxnr,pxcjry,xxzj,zgh,ywpxwj,ywpxspr,pxf,qtf,fykzbm) values(S_SZDWYWPX_ID.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";				       
					try{
						res=dao.runUpdate(sql, new String[]{pxxm,pxsj,pxdd,pxnr,pxcjry,xxzj,zgh,ywpxwj,ywpxspr,pxf,qtf,fykzbm});
					}catch(Exception e){
						res=false;
					}
					if(res){
						dao.writeLog(sql, new String[]{pxxm,pxsj,pxdd,pxnr,pxcjry,xxzj,zgh,ywpxwj,ywpxspr,pxf,qtf,fykzbm}, new String[]{}, "增加数据",request);							
						request.setAttribute("done", "ok");
					}else{
						request.setAttribute("done", "no");
					}
				}else{//修改
					sql = "update szdw_ywpxxx set pxxm=?,pxsj=?,pxdd=?,pxnr=?,pxcjry=?,xxzj=?,zgh=?,ywpxwj=?,ywpxspr=?,pxf=?,qtf=?,fykzbm=? where ywpxjldm=?";					     
					try{
						dao.writeLog(sql, new String[]{pxxm,pxsj,pxdd,pxnr,pxcjry,xxzj,zgh,ywpxwj,ywpxspr,pxf,qtf,fykzbm}, new String[]{}, "修改数据",request);													
						res = dao.runUpdate(sql, new String[]{pxxm,pxsj,pxdd,pxnr,pxcjry,xxzj,zgh,ywpxwj,ywpxspr,pxf,qtf,fykzbm,pk});				    
					}catch(Exception e){
						res = false;
					}
					if (res) {
						request.setAttribute("done", "ok");
					} else {
						request.setAttribute("done", "no");
					} 
				}
			}		   
			if("modi".equalsIgnoreCase(doType)){//显示修改
				tips = " 信息修改  ";
				sql = "select pxxm,pxsj,pxdd,pxnr,pxcjry,xxzj,ywpxwj,ywpxspr,pxf,qtf,fykzbm from szdw_ywpxxx where ywpxjldm =?";
				String[] colList = new String[] { "pxxm", "pxsj", "pxdd", "pxnr", "pxcjry", "xxzj","ywpxwj","ywpxspr","pxf","qtf","fykzbm"};
				String[] colV =  dao.getOneRs(sql, new String[]{pk}, colList);
				if (colV == null){
					colV = new String[colList.length];
				}
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i], isNull(colV[i]) ? "" : colV[i]);
				}
			}
			if("del".equalsIgnoreCase(doType)){
				boolean res = false; 
				sql = "delete from szdw_ywpxxx where ywpxjldm=?";
				try{
					res = dao.runUpdate(sql, new String[] {pk});				   
				}catch (Exception e){
					res = false;
				}
				if (res) {
					request.setAttribute("del", "ok");
				} else {
					request.setAttribute("del", "no");
				}
				return new ActionForward("/szdw_xxQuery.do?go=go&tableName=view_szdw_ywpxxx",false);   
			}
		}
		sql = "select zgh, xm,(case(xb) when '1' then '男' when '2' then '女' end) xb, bmdm, zw, zyzz,zzmm,xl," +
		"csrq,zc,lxgzsj from fdyxxb where zgh = ?";
		String[] colList = new String[] { "zgh", "xm", "xb", "bmdm", "zw", "zyzz","zzmm","xl","csrq","zc","lxgzsj"};
		String[] colV = dao.getOneRs(sql, new String[] { zgh }, colList);
		if (colV == null) {
			colV = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			map.put(colList[i], isNull(colV[i]) ? "" : colV[i]);
		}
		if("xy".equalsIgnoreCase(userType)){
			map.put("bmdm", userDep);
		}
		String[] bjList = sxjyDao.getFdyBjList(zgh);
		String[] zyList = sxjyDao.getZyBjList(zgh);
		request.setAttribute("fdybjList", bjList);
		request.setAttribute("fdyzyList", zyList);
		request.setAttribute("xbList", dao.getSexList());
		request.setAttribute("doType", doType);	
		request.setAttribute("rs", map);
		sql = "select zwdm,zwmc from zwb";
		List zwList = dao.getList(sql, new String[] {}, new String[] { "zwdm",
		"zwmc" });
		request.setAttribute("bmList", dao.getXyList());
		request.setAttribute("bmxyList",getBmxyList(userDep));
		request.setAttribute("zwList", zwList);
		request.setAttribute("JsZzmmList", sxjyDao.getJsZzmmList());
		request.setAttribute("JsXlList", sxjyDao.getJsXlList());
		request.setAttribute("sfList", sxjyDao.getSfList());
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pk);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		request.setAttribute("tips", tips);
		return mapping.findForward("success");	   
	}
    /**调查问卷试题维护*/
	public ActionForward wjstwh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		FdyglForm myForm = (FdyglForm) form;
		DAO dao = DAO.getInstance();
		String act = request.getParameter("dcAct");
		String sql = "";
		String realTable = "fdygz_dcwjstb";
		String[] colList = null;
		String[] colListCN = null;
		List topTr = null;
		Vector<String[]> rs = new Vector<String[]>();	
		String sfkf = "0";//调查问卷是否开放
		if(act!=null&&act.equalsIgnoreCase("save")){//调查问卷是否开放
		    sfkf = request.getParameter("sfkf");
			boolean done = false;
			sql = "delete from fdygz_dcwjkfb";			
			done = dao.runUpdate(sql,new String[]{});
			if(done){
				sql = "insert into fdygz_dcwjkfb(sfkf) values (?)";
				done = dao.runUpdate(sql, new String[]{sfkf});
			}
			if(done){
           		request.setAttribute("done","yes");
       			String logMsg = "在表'fdygz_dcwjkfb'修改记录";
      			dao.writeLog(sql,new String[]{sfkf}, new String[]{}, logMsg,request);	
           	}else{
           		request.setAttribute("done", "no");
           	}
		}
		
		sql = "select  sfkf from fdygz_dcwjkfb";
		sfkf = dao.getOneRs(sql, new String[]{},"sfkf");
		if(sfkf==null||sfkf.equalsIgnoreCase("")){
			request.setAttribute("sfkf", "0");
		}else {
			request.setAttribute("sfkf", sfkf);
		}
		colList = new String[]{"id","stmc","bz"};
		sql = "select * from "+realTable+" where xn=? and xq=? order by id asc";
		colListCN = dao.getColumnNameCN(colList, realTable);
		topTr = dao.arrayToList(colList, colListCN);
		rs.addAll(dao.rsToVator(sql, new String[]{Base.currXn,Base.currXq}, colList));
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum",String.valueOf(rs.size()));
		request.setAttribute("topTr", topTr);
		myForm.setXn(Base.currXn);
		myForm.setNd(Base.currNd);
		myForm.setXq(Base.currXq);		
		request.setAttribute("realTable", realTable);
		return mapping.findForward("success");
	}
   /**调查问卷问题添加、修改、删除*/
	public ActionForward wjstwh_make(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		HashMap<String,String> map = new HashMap<String,String>();
		DAO dao = DAO.getInstance();
		FdyglForm myForm = (FdyglForm) form;
		String doType = request.getParameter("doType");
		String sql = "";
		String realTable = "fdygz_dcwjstb";		
		int idIndex = 0;
		boolean done = false;
		sql = "select count(*) cout from "+realTable+" where 1=1 and xn=? and xq=?";
		String cout = dao.getOneRs(sql, new String[]{Base.currXn,Base.currXq}, "cout");
		idIndex = Integer.parseInt(cout)+1; //问题列号
		map.put("idIndex", String.valueOf(idIndex));
        if(doType.equalsIgnoreCase("save")){//保存
           	String id = request.getParameter("idIndex").trim();
           	String stmc = DealString.toGBK(request.getParameter("stmc"));
           	String bz = DealString.toGBK(request.getParameter("bz"));
           	sql = "delete from "+realTable+" where id =? and xn=? and xq=? ";
           	done = dao.runUpdate(sql,new String[]{id,Base.currXn,Base.currXq});
           	if(done){
           		sql = "insert into "+realTable+"(id,stmc,bz,xn,xq)values(?,?,?,?,?)";
                done = dao.runUpdate(sql,new String[]{id,stmc,bz,Base.currXn,Base.currXq});          	
           	}
           	if(done){
           		request.setAttribute("done","yes");
       			String logMsg = "在表'"+realTable+"'中增加或修改了id为"+id+"的记录";    			
     			dao.writeLog(sql,null, null, logMsg,request);	
           	}else{
           		request.setAttribute("done", "no");
           	}
        }else if(doType.equalsIgnoreCase("modi")){	//修改
        	String id = request.getParameter("id").trim();
        	sql = "select stmc,bz from "+realTable+" where id= ? and xn=? and xq=?";
        	map = dao.getMap(sql, new String[]{id,Base.currXn,Base.currXq}, new String[]{"stmc","bz"});
        	map.put("idIndex", id);
        }else if(doType.equalsIgnoreCase("view")){//查看详细信息 
        	return new ActionForward("/wjstxxwh.do?id="+request.getParameter("id"),false);
        }else if(doType.equalsIgnoreCase("del")){//删除
        	String id = request.getParameter("id").trim();
        	sql = "delete from "+realTable+" where id =? and xn=? and xq=? ";
           	done = dao.runUpdate(sql,new String[]{id,Base.currXn,Base.currXq});
           	if(done){
           		sql = "delete from fdygz_dcwjstxxb where stid =? and xn=? and xq=? ";
               	done = dao.runUpdate(sql,new String[]{id,Base.currXn,Base.currXq});
               	sql = "update "+realTable+" set id = id-1 where id > ?";
               	dao.runUpdate(sql,new String[]{id});
               	sql = "update fdygz_dcwjstxxb set stid = stid-1 where stid > ?";
               	dao.runUpdate(sql,new String[]{id});
       			String logMsg = "在表'"+realTable+"'中删除了id为"+id+"的记录";
     			dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);	
           		request.setAttribute("done","yes");
           	}else{
           		request.setAttribute("done", "no");
           	}
           	return new ActionForward("/wjstwh.do",false);
        }
		request.setAttribute("rs", map);
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("doType", doType);
        return mapping.findForward("success");
	}
	/**调查问卷问题选项维护*/
	public ActionForward wjstxxwh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		FdyglForm myForm = (FdyglForm) form;
		DAO dao = DAO.getInstance();
		String id = request.getParameter("id").trim();
		String realTable = "fdygz_dcwjstxxb";
		String doType = DealString.toString(request.getParameter("doType"));
		String sql = "";
		String stmc = dao.getOneRs("select stmc from fdygz_dcwjstb where id= ? and xn=? and xq=? ",new String[]{id,Base.currXn,Base.currXq},"stmc");//问题名称
				
		sql = "select * from "+realTable+" where stid= ? and xn=? and xq=? order by xxl asc";
		String[] colList = null;
		String[] colListCN = null;
		List topTr = null;
		Vector<String[]> rs = new Vector<String[]>();
		colList = new String[]{"xxl","xxnr","xxfz"};
		colListCN = dao.getColumnNameCN(colList,realTable);
		topTr = dao.arrayToList(colList, colListCN);
		rs.addAll(dao.rsToVator(sql, new String[]{id,Base.currXn,Base.currXq}, colList));
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum",String.valueOf(rs.size()));
		request.setAttribute("topTr", topTr);
		myForm.setXn(Base.currXn);
		myForm.setNd(Base.currNd);
		myForm.setXq(Base.currXq);
		request.setAttribute("id", id);
		request.setAttribute("stmc", stmc);
		request.setAttribute("realTable", realTable);
		if(doType.equalsIgnoreCase("view")){
			return new ActionForward("/fdygl/fdygzdc/wjstxx_view.jsp",false);
		}
		return mapping.findForward("success");
	}
   /**调查问卷问题选项增加、修改、删除*/
	public ActionForward wjstxxwh_make(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		FdyglForm myForm = (FdyglForm) form;
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		String realTable = "fdygz_dcwjstxxb";
		String sql = "";
		boolean done = false;
		HashMap<String,String> map = new HashMap<String,String>();
		if(doType.equalsIgnoreCase("save")){
			String xxl = request.getParameter("xxl");
			String xxnr = DealString.toGBK(request.getParameter("xxnr"));
			String xxfz = DealString.toGBK(request.getParameter("xxfz"));
			sql = "delete from "+realTable+" where stid =? and xxl=? and xn=? and xq=? ";
           	done = dao.runUpdate(sql,new String[]{id,xxl,Base.currXn,Base.currXq});
           	if(done){
           		sql = "insert into "+realTable+"(stid,xxl,xxnr,xxfz,xn,xq) values(?,?,?,?,?,?)";
                done = dao.runUpdate(sql,new String[]{id,xxl,xxnr,xxfz,Base.currXn,Base.currXq});          	
           	}
           	if(done){
           		request.setAttribute("done","yes");
       			String logMsg = "在表'"+realTable+"'中增加或修改了id为"+id+",xxl为"+xxl+"的记录";
     			dao.writeLog(sql,new String[]{id,xxl,xxnr,Base.currXn,Base.currXq}, new String[]{}, logMsg,request);	
           	}else{
           		request.setAttribute("done", "no");
           	}
			
		}else if(doType.equalsIgnoreCase("modi")){
			String xxl = request.getParameter("xxid");
			sql = "select xxnr,xxfz from "+realTable+" where stid=? and xxl=? and xn=? and xq=?";
			map = dao.getMap(sql, new String[]{id,xxl,Base.currXn,Base.currXq}, new String[]{"xxnr","xxfz"});
        	map.put("xxl", xxl);
		}else if(doType.equalsIgnoreCase("del")){
			String xxl = request.getParameter("xxid");
        	sql = "delete from "+realTable+" where stid =? and xxl=? and xn=? and xq=? ";
           	done = dao.runUpdate(sql,new String[]{id,xxl,Base.currXn,Base.currXq});
           	if(done){
       			String logMsg = "在表'"+realTable+"'中删除了id为"+id+",xxl为"+xxl+"的记录";
     			dao.writeLog(sql,new String[]{id,xxl,Base.currXn,Base.currXq}, new String[]{}, logMsg,request);	
           		request.setAttribute("done","yes");
           	}else{
           		request.setAttribute("done", "no");
           	}
           	return new ActionForward("/wjstxxwh.do?id="+id,false);
		}
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("id", id);
		request.setAttribute("rs", map);
		request.setAttribute("doType", doType);
		return mapping.findForward("success");
	}
	/**调查问卷预览*/
	public ActionForward wj_view(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
 		HttpSession session = request.getSession();
 		DAO dao = DAO.getInstance();
 		FdyglForm myForm = (FdyglForm)form;
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		String sql = "";
		String xh = "";
		String[] st = null;
		String[] st2 = null;
		boolean done = false;
		String userType=session.getAttribute("userOnLine").toString();
		if(userType.equalsIgnoreCase("student")){
			xh = session.getAttribute("userName").toString();
			sql = "select * from view_xsjbxx where xh=?";
			String[] colList = dao
				.getColumnName("select * from view_xsjbxx where 1=2");
			String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
			if (rs != null) {
			    for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			    }
			}
			
			request.setAttribute("rs", map);			
		}
		
		/**调查结果保存*/
		if(act!=null&&act.equalsIgnoreCase("save")){			
			String str = request.getParameter("strV");
			String zgh = request.getParameter("zgh");
			xh = session.getAttribute("userName").toString();
 		    sql = str;	
            st = sql.split("!!SplitSignOne!!");	 
            for(int i=0;i<st.length;i++){
            	st2=st[i].split("!!SplitSignTwo!!");           	
            	sql = "delete from fdygz_dcjgb where stid=? and xxl=? and xh=? and xn=? and xq=? and zgh=?";
            	done = dao.runUpdate(sql, new String[]{st2[0],st2[1],xh,Base.currXn,Base.currXq,zgh});
            	if(done){
            		String xxCout = dao.getOneRs("select count(*) xxCout from fdygz_dcwjstxxb where stid=? ", new String[]{st2[0]},"xxCout");//对应试题选项数目
            		sql = "insert into fdygz_dcjgb(stid,xxl,xh,xn,xq,xxs,zgh) values(?,?,?,?,?,?,?)";
            		done = dao.runUpdate(sql,  new String[]{st2[0],st2[1],xh,Base.currXn,Base.currXq,xxCout,zgh});
            	}
            	if(done){
            		request.setAttribute("done", "yes");
            	}else{
            		request.setAttribute("done", "no");
            	}
            }
		}

		sql = "select id,stmc from fdygz_dcwjstb where xn=? and xq=? order by id asc";
		List stList = dao.getList(sql,new String[]{Base.currXn,Base.currXq},new String[]{"id","stmc"});
		sql = "select stid,xxl,xxnr from fdygz_dcwjstxxb where xn=? and xq=? order by stid asc,xxl asc ";
		List xxList = dao.getList(sql, new String[]{Base.currXn,Base.currXq}, new String[]{"stid","xxl","xxnr"});
		String xxStr = dao.listToString(xxList, new String[]{"stid","xxl","xxnr"});
		request.setAttribute("stList", stList);
		request.setAttribute("xxStr", xxStr);
		request.setAttribute("stLen",stList.size());
		request.setAttribute("xh||xn||xq",xh+Base.currXn+Base.currXq);
		myForm.setXn(Base.currXn);
		myForm.setNd(Base.currNd);
		myForm.setXq(Base.currXq);
		/**学生填写调查问卷页面*/
		if(act!=null&&(act.equalsIgnoreCase("answer")||act.equalsIgnoreCase("save"))){
			String zgh = request.getParameter("zgh");
			String fdyxm = dao.getOneRs("select xm from fdyxxb where zgh=?",new String[]{zgh}, "xm");
			request.setAttribute("zgh", zgh);
			request.setAttribute("fdyxm", fdyxm);
			request.setAttribute("userType", userType);
			return new ActionForward("/fdygl/fdygzdc/wj_tx.jsp",false);
		}
		/**问卷预览页面*/
		return mapping.findForward("success");
	}
	/**辅导员工作调查*/
	public ActionForward fdywork_research(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		FdyglForm myForm = (FdyglForm) form;
		SxjyDAO sxjyDao = new SxjyDAO();
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		String bmdm = myForm.getXydm();
		String inputSQL[];
		String outputSQL[];
		String userType = session.getAttribute("userType").toString();
		String sUName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		sql = "select  sfkf from fdygz_dcwjkfb";//调查问卷是否开放
		String sfkf = dao.getOneRs(sql, new String[]{},"sfkf");
		if(sfkf==null||sfkf.equalsIgnoreCase("")){
			request.setAttribute("sfkf", "0");
		}else {
			request.setAttribute("sfkf", sfkf);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			bmdm = userDep;
		}
		/** 获取辅导员列表 */
		if (userType.equalsIgnoreCase("stu")) {
			sql = "select zgh,xm from view_fdyxx a where zgh in(select"
				+ " zgh from fdybjb c where bjdm in(select bjdm from view_xsjbxx d "
				+ "where xh=?))";
			inputSQL = new String[] { sUName };
		} else if (userType.equalsIgnoreCase("xy")) {
			bmdm = userDep;
			sql = "select zgh,xm from view_fdyxx a where bmdm=?";
			inputSQL = new String[] { bmdm };
		} else {
			sql = "select zgh,xm from view_fdyxx a where bmdm like ?";
			inputSQL = new String[] { bmdm };
		}
		outputSQL = new String[] { "zgh", "xm" };
		List fdyList = dao.getList(sql, inputSQL, outputSQL);
		
		myForm.setXydm(bmdm);
		request.setAttribute("bmList",  Base.getXyList());// 发送学院列表
		request.setAttribute("bmxyList",getBmxyList(userDep));
		request.setAttribute("zgList", sxjyDao.getFdyList(bmdm));
		myForm.setXn(Base.currXn);
		myForm.setNd(Base.currNd);
		myForm.setXq(Base.currXq);
		request.setAttribute("fdyList", fdyList);
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("success");
	}
	/**调查结果*/
	public ActionForward fdywork_result(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception{
		FdyglForm myForm = (FdyglForm) form;
		SxjyDAO sxjyDao = new SxjyDAO();
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		String go = request.getParameter("go");
		String bmdm = myForm.getXydm();
		String zgh = myForm.getZgh();
		String inputSQL[];
		String outputSQL[];
		
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		Vector<Object> rs = new Vector<Object>();	
		String allCount = "";//调查总人数
		List stList = null;//问题列表
		
		List<HashMap<String, String>> xxList = new ArrayList<HashMap<String,String>>();
//		List<HashMap<String, String>> xxListZH = new ArrayList<HashMap<String,String>>();
		//获取辅导员信息
		sql = "select zgh,xm,xb,bmmc from view_fdyxx where zgh = ?";
		String[] colList = new String[]{"zgh","xm","xb","bmmc"};
		String[] fdyxx = dao.getOneRs(sql,new String[]{zgh},colList);
		if(fdyxx!=null){
			for(int i=0;i<colList.length;i++){
			request.setAttribute(colList[i].toLowerCase(), (fdyxx[i]==null)?"":fdyxx[i]);
			}
		}
		if ((go != null) && go.equalsIgnoreCase("go")){			
				//参与调查人员总数		    
				sql = "select count(distinct xh) count from fdygz_dcjgb where xn=? and xq=? and zgh=? ";			
				allCount = dao.getOneRs(sql, new String[]{Base.currXn,Base.currXq,zgh}, "count");
				request.setAttribute("allCount", allCount);	
				//算出老师平均分
				sql = "select nvl(sum(nvl(xxfz,0)),0) sum from fdygz_dcjgb a left join fdygz_dcwjstxxb b on a.xn=b.xn and a.xq =  b.xq and a.stid = b.stid and a.xxl = b.xxl where a.xn=? and a.xq=? and a.zgh = ?";
				String sum  = dao.getOneRs(sql, new String[]{Base.currXn,Base.currXq,zgh}, "sum");
				String pjf = ((Float)(Float.parseFloat(sum)/Float.parseFloat(allCount))).toString();
				request.setAttribute("pjf",pjf);	
				//是否存在该辅导员的调查结果信息
				sql = "select count(*) count from fdygz_dcjgb where zgh=? and xn=? and xq=?";
				String exist = dao.getOneRs(sql, new String[]{zgh,Base.currXn,Base.currXq}, "count");
				if(!exist.equalsIgnoreCase("0")){
			
					//问卷试题数目									
					sql = "select count(distinct id) stNum from fdygz_dcwjstb where xn = ? and xq = ? order by id";
					String stNum = dao.getOneRs(sql, new String[]{Base.currXn,Base.currXq}, "stNum");

					for(int i=1;i<=Integer.parseInt(stNum);i++){

						HashMap<String, Object> map = new HashMap<String, Object>();
						//问卷试题列ID及该试题的选项数
						sql = " select a.id,(select count(distinct xxl) from fdygz_dcwjstxxb b where b.xn=a.xn and b.xq=a.xq and b.stid=a.id ) xxcount from " +
						"fdygz_dcwjstb a where a.xn=? and a.xq=? and id=?";
						stList = dao.getList(sql, new String[]{Base.currXn,Base.currXq,String.valueOf(i)},new String[]{"id","xxcount"});
						map.put("stList", stList);

						//试题列、选项列、人数、比例
						sql = "select a.id,a.xn,a.xq,a.xxl,a.zgh,a.sumc,a.numcount,round(to_number((a.sumc/a.numcount) * 100),2) numbl from ( "+
						"select a.xxl,a.id,a.xn,a.xq,a.zgh,(select count(xxl)from fdygz_dcjgb b where b.stid=a.id and b.xxl=a.xxl and b.zgh=?) sumc,"+
						"(select count(distinct b.xh) count from fdygz_dcjgb b where b.xn=a.xn and b.xq=a.xq and b.zgh=?)numcount from"+
						"(select distinct a.xxl,a.id,a.xn,a.xq,c.zgh from "+
						"(select a.id,a.xn,a.xq,b.xxl from "+
						"fdygz_dcwjstb a,fdygz_dcwjstxxb b where a.id=b.stid and a.xn=b.xn and "+
						"a.xq=b.xq  )a left join fdygz_dcjgb c on a.xn=c.xn and a.xq=c.xq and "+
						"a.id=c.stid  )a "+
						")a  where (a.zgh=? or a.zgh is null) and "+
						" xn=? and xq=? and id=?  order by a.id,a.xxl ";																
						xxList = dao.getList(sql, new String[]{zgh,zgh,zgh,Base.currXn,Base.currXq,String.valueOf(i)}, new String[]{"id","xxl","sumc","numbl"});	
						map.put("xxList", xxList);
						rs.add(map);
				}
//				HashMap<String, Object> map = new HashMap<String, Object>();
//				sql = "select 0 id,a.xxl,a.sumc,round(to_number((a.sumc/b.numcount) * 100),2) numbl from (select a.xxl,count(a.XXL) sumc from fdygz_dcjgb a where a.zgh=? and a.xn=? and a.xq=? group by a.xxl) a,(select count(distinct xh) numcount from fdygz_dcjgb  where zgh=? and xn=? and xq=?) b";
//				xxListZH = dao.getList(sql, new String[]{zgh,Base.currXn,Base.currXq,zgh,Base.currXn,Base.currXq}, new String[]{"id","xxl","sumc","numbl"});
//				map.put("xxList", xxListZH);
//				rs.add(map);
			}
		}
				
		if (userType.equalsIgnoreCase("xy")) {
			bmdm = userDep;
			sql = "select zgh,xm from view_fdyxx a where bmdm=?";
			inputSQL = new String[] { bmdm };
		} else {
			sql = "select zgh,xm from view_fdyxx a where bmdm like ?";
			inputSQL = new String[] { bmdm };
		}
		outputSQL = new String[] { "zgh", "xm" };
		List fdyList = dao.getList(sql, inputSQL, outputSQL);
		myForm.setXydm(bmdm);
		request.setAttribute("bmList", Base.getXyList());
		request.setAttribute("bmxyList",getBmxyList(userDep));
		request.setAttribute("zgList", sxjyDao.getFdyList(bmdm));
		myForm.setXn(Base.currXn);
		myForm.setNd(Base.currNd);
		myForm.setXq(Base.currXq);
		request.setAttribute("rs", rs);
		request.setAttribute("fdyList", fdyList);
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("success");
	}
	
	public ActionForward fdyXxPjjgDc(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		List<Object> rs = new ArrayList<Object>();
		String zdm = DealString.toGBK(request.getParameter("zdm"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zxm = DealString.toGBK(request.getParameter("zxm"));
		if(!isNull(zdm)){
		if(zdm.equalsIgnoreCase("999999")) {
			querry.append(" and jszgh = xh ");
		}else{
			querry.append(" and zdm='");
			querry.append(zdm);
			querry.append("' and jszgh != xh ");
		}
		}
		if (!isNull(xydm)) {
			querry.append(" and bmdm='");
			querry.append(xydm);
			querry.append("' ");
		}
		if (!isNull(zxm)) {
			querry.append(" and jszgh='");
			querry.append(zxm);
			querry.append("' ");
		}
		sql = "select jszgh,xm,bmdm,bmmc,pjzbxh,pjfs,pjzb,zdm,zmc from view_xspfdc " + querry.toString()+" order by jszgh";
		String[] colList = dao.getColumnName("select jszgh,xm,bmdm,bmmc,pjzbxh,pjfs,pjzb,zdm,zmc from view_xspfdc where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_xspfdc");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("success");
	}
	
	public ActionForward xscppTj(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		CommonService commonService = new CommonService();
		//查出学生组参评对象的具体答题列表
		
		List<HashMap<String, String>> cpdxList = commonService.getCpdxList();
		
		//开始拼接sql语句并执行
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		commonService.getPjjgForGzListPrint(cpdxList,response.getOutputStream());
		return null;
	}
	
	/**
	 * 设置评价标准(按学院)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param power
	 * @return
	 * @throws Exception
	 */
	private ActionForward setPjzbForXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();

		String sUName;
		
		String userDep;
		
		String userType;

		String logMsg;

		HttpSession session = request.getSession();
		
		sUName = session.getAttribute("userName").toString();
		userDep = session.getAttribute("userDep").toString();
		userType = session.getAttribute("userType").toString();
		
		FdyglForm fdyForm = (FdyglForm) form;
		
		if(userType.equalsIgnoreCase("xy")){
			fdyForm.setXydm(userDep);
		}
		
		
		
		String[] tmp;
		String mxdx = fdyForm.getMxdx();
		String xydm = fdyForm.getXydm();
		String query = "";
		if(!Base.isNull(mxdx)) {
			query = " and mxdx = '";
			query +=mxdx;
			query +="' "; 
		}
		if(!Base.isNull(xydm)) {
			query = " and xydm = '";
			query +=xydm;
			query +="' "; 
		}
		String[] getPara = { "xh","stlbmc","xymc","pjzb", "fz", "mxdx","scojzb"};
		String sql = "select xh,(select stlbmc from szdw_fdykhstdlb where stlbdm = a.stlbdm) stlbmc,(select bmmc from zxbz_xxbmdm where a.xydm = bmdm) xymc,pjzb,fz,scojzb,b.qtmc mxdx from pjzbb a,pjqtb b where a.mxdx=b.qtdm "+query+" order by mxdx,xh";
		List rslist = dao.getList(sql, new String[] {}, getPara);
		String act = request.getParameter("act");
		sql = "select qtmc,qtdm from pjqtb";
		List mxdxList = dao.getList(sql, new String[] {}, new String[] {
				"qtdm", "qtmc" });
		request.setAttribute("mxdxList", mxdxList);
		request.setAttribute("xyList", dao.getXyList());
		if (!Base.isNull(act)) {
			if (power == 0) {
				request.setAttribute("errMsg", "无权做此操作！");
				return new ActionForward("/errMsg.do", false);
			}
			
			boolean ok = false;

			if (act.equalsIgnoreCase("add")) {
				sql = "select max(to_number(xh))+1 num from pjzbb";
				String pjh = dao.getOneRs(sql,new String[]{},"num");
				fdyForm.setPjh(pjh); 
				fdyForm.setXh(""); 
				fdyForm.setFz(""); 
				fdyForm.setPjnr("");
				fdyForm.setMxdx("");
				fdyForm.setJtzb("");
				fdyForm.setStlbdm("");
				fdyForm.setScojzb("");
				request.setAttribute("doType", "add");
				CommonDAO myDAO = new CommonDAO(); 
				request.setAttribute("stlblist", myDAO.getStlbList());
				return new ActionForward("/fdygl/pjzbXyOne.jsp", false);
			} else if (act.equalsIgnoreCase("modi")) {
				String zbh = request.getParameter("id");
				sql = "select xh pjh,pjzb pjnr,fz,mxdx,jtzb,stlbdm,scojzb,xydm from pjzbb where xh=?";
				String[] oneInfo = new String[] { "pjh", "pjnr", "fz", "mxdx","jtzb","stlbdm","scojzb","xydm" };
				String[] res = dao.getOneRs(sql, new String[] { zbh }, oneInfo);
				if (res == null) {
					res = new String[5];
				}

				fdyForm.setPjh(res[0]);
				fdyForm.setPjnr(res[1]);
				fdyForm.setFz(res[2]);
				fdyForm.setMxdx(res[3]);
				fdyForm.setJtzb(res[4]);
				fdyForm.setStlbdm(res[5]);
				fdyForm.setScojzb(res[6]);
				fdyForm.setXydm(res[7]);
				request.setAttribute("doType", "modi");
				CommonDAO myDAO = new CommonDAO(); 
				request.setAttribute("stlblist", myDAO.getStlbList());
				return new ActionForward("/fdygl/pjzbXyOne.jsp", false);
			} else if (act.equalsIgnoreCase("save")) {
				String fz = fdyForm.getFz();
				String pjh = fdyForm.getPjh();
				String pjnr = fdyForm.getPjnr();
				String stlbdm = fdyForm.getStlbdm();
				String scojzb = DealString.toGBK(fdyForm.getScojzb());
				String jtzb = DealString.toGBK(fdyForm.getJtzb());
				String doType = request.getParameter("doType");
				if (!Base.isNull(pjnr)) {
					pjnr = DealString.toGBK(pjnr);
				}

				if (!Base.isNull(doType) && doType.equalsIgnoreCase("add")) {
					sql = "insert into pjzbb(xh,pjzb,mxdx,fz,jtzb,stlbdm,scojzb,xydm) values(?,?,?,?,?,?,?,?)";
					tmp = new String[] { pjh, pjnr, mxdx, fz,jtzb,stlbdm,scojzb,xydm };
					logMsg = "添加思政考核评价指标，指标号为：" + pjh;
				} else {
					sql = "update pjzbb set pjzb=?,mxdx=?,fz=?,jtzb=?,stlbdm=?,scojzb=?,xydm=? where xh=?";
					tmp = new String[] { pjnr, mxdx, fz, jtzb,stlbdm,scojzb,xydm,pjh};
					logMsg = "修改思政考核评价指标，指标号为：" + pjh;
				}

				ok = dao.runUpdate(sql, tmp);
				if (ok) {
					Base.log(request, logMsg, sUName);
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
				CommonDAO myDAO = new CommonDAO(); 
				request.setAttribute("stlblist", myDAO.getStlbList());
				return new ActionForward("/setPjzb.do?act=modi&id=" + pjh,
						false);
			} else if (act.equalsIgnoreCase("del")) {
				String pjh = fdyForm.getPjh();
				sql = "delete pjzbb where xh=? ";
				tmp = new String[] { pjh };
				logMsg = "删除思政考核评价指标，指标号为：" + pjh;

				ok = dao.runUpdate(sql, tmp);
				if (ok) {
					Base.log(request, logMsg, sUName);
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
				return new ActionForward("/setPjzb.do", true);
			}
		}
		request.setAttribute("rslist", rslist);
		request.setAttribute("rsNum", Integer.toString(rslist.size()));
		return mapping.findForward("xyPjzb");
	}

	/** 评价 */
	private ActionForward pjForXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
		String userType;
	
		String userDep;
	
		String sUName;
	
		String logMsg;
	
	
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		userDep = session.getAttribute("userDep").toString();
		String xxdm = session.getAttribute("xxdm").toString();
	
		FdyglForm fdyForm = (FdyglForm) form;
		String act = request.getParameter("act");
		String doType = request.getParameter("do");
		String sql;
		String inputSQL[];
		String outputSQL[];
		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapTmp;
		String tmp[];
		String[] pjh = fdyForm.getPfbz();
		String[] pj = fdyForm.getPjfs();
		String bmdm = fdyForm.getXydm();
		if(userType.equalsIgnoreCase("xy")){
			bmdm = userDep;
		}
		String zgh = fdyForm.getZgh();
		String yhz;
		String pfbz;
		bmdm = Base.chgNull(bmdm, "%", 0);
	
		/** 判断评价是否开放 */
		sql = "select sfkpj from xtszb where sfkpj='否'";
		inputSQL = new String[] {};
		outputSQL = new String[] { "sfkpj" };
		tmp = dao.getOneRs(sql, inputSQL, outputSQL);
		if (tmp != null) {
			request.setAttribute("sfcp", "time");
			return mapping.findForward("success");
		}
	
		/** 保存 */
		if (!Base.isNull(act) && act.equalsIgnoreCase("save") && pjh != null
				&& pj != null && pjh.length == pj.length) {
			boolean ok = false;
			sql = "update xspfb_lsb set pjfs=? where xn=? and nd=? and xq=? "
				+ "and xh=? and jszgh=? and pjzbxh=?";
			for (int i = 0; i < pj.length; i++) {
				mapTmp = new HashMap<String, Object>();
				tmp = new String[] { Base.chgNull(pj[i], "", 0), Base.currXn,
						Base.currNd, Base.currXq, sUName, zgh, pjh[i] };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
			}
			logMsg = "保存了对辅导员（" + zgh + "）的评价。";
			ok = dao.runUpdate(sqlToExe);
			/** 提交 */
			if (!Base.isNull(doType) && doType.equalsIgnoreCase("submit")) {
				mapTmp = new HashMap<String, Object>();
				sql = "delete xspfb where xn=? and nd=? and xq=? "
					+ "and xh=? and jszgh=? ";
				tmp = new String[] { Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
	
				mapTmp = new HashMap<String, Object>();
				sql = "insert into xspfb select * from xspfb_lsb where xn=?"
					+ " and nd=? and xq=? and xh=? and jszgh=?";
				tmp = new String[] { Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
	
				mapTmp = new HashMap<String, Object>();
				sql = "delete fdypjjlb where xn=? and nd=? and xq=? and xh=? and jszgh=?";
				tmp = new String[] { Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
	
				mapTmp = new HashMap<String, Object>();
				sql = "insert into fdypjjlb(xn,nd,xq,xh,jszgh) values(?,?,?,?,?)";
				tmp = new String[] { Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
	
				logMsg = "提交了对辅导员（" + zgh + "）的评价。";
				ok = dao.runUpdate(sqlToExe);
			}
			if (ok) {
				Base.log(request, logMsg, sUName);
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
	
		/** 获取用户组 */
		sql = "select zdm from yhb where yhm=?";
		inputSQL = new String[] { sUName };
		outputSQL = new String[] { "zdm" };
		tmp = dao.getOneRs(sql, inputSQL, outputSQL);
		if (tmp == null) {
			yhz = "6727";
		} else {
			yhz = tmp[0];
		}
		String zyhz = yhz;
		if(sUName.equalsIgnoreCase(zgh)) {
			yhz ="999999";
			request.setAttribute("zwpj", "true");
		}
		/** 判断用户是否参评，若参评，获取评分标准 */
		sql = "select pfbz,khqzdm from pjqtb where sfcp='是' and qtdm=?";
		inputSQL = new String[] { yhz };
		outputSQL = new String[] { "pfbz","khqzdm" };
		tmp = dao.getOneRs(sql, inputSQL, outputSQL);
		if (tmp == null) {
			request.setAttribute("sfcp", "no");
			return mapping.findForward("success");
		}
		pfbz = tmp[0];
		String khqzdm = tmp[1];
		if(khqzdm == null){
			if (tmp != null) {
				request.setAttribute("sfcp", "out");
				return mapping.findForward("success");
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)){
			if(yhz.equalsIgnoreCase("999999")){
				sql = "select pfbz,khqzdm from pjqtb where sfcp='是' and qtdm=?";
				inputSQL = new String[] { zyhz };
				outputSQL = new String[] { "pfbz","khqzdm" };
				khqzdm = dao.getOneRs(sql, inputSQL, outputSQL)[1];
			}
			if(khqzdm!=null&&khqzdm.equalsIgnoreCase("002")){
				sql = "select fgnj from view_fdyxx where zgh = ?";
				String sjnj = dao.getOneRs(sql, new String []{sUName}, "fgnj");
				if(sjnj!=null&&!sjnj.equalsIgnoreCase("")){
					fdyForm.setNj(sjnj);
					request.setAttribute("sjnj", sjnj);
				}else{
					fdyForm.setNj("no");
					request.setAttribute("sjnj", "no");
				}
			}
		}
		/** 获取评价对象列表 */
		String fgnj = fdyForm.getNj();
		if (userType.equalsIgnoreCase("stu")) {
			sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
				+ " where xn=? and nd=? and xq=? ) and (zgh in(select"
				+ " zgh from fdybjb c where bjdm in(select bjdm from view_xsjbxx d "
				+ "where xh=?)) or zgh in (select"
				+ " zgh from bzrbbb c where bjdm in(select bjdm from view_xsjbxx d "
				+ "where xh=?)))";
			inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					sUName,sUName };
		} else if (userType.equalsIgnoreCase("xy")) {
			if(fgnj==null||fgnj.equalsIgnoreCase("")){		
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ?";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					bmdm };
			}else if(fgnj.equalsIgnoreCase("no")){
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ? and (fgnj is null or fgnj = '')";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					bmdm};
			}else{
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ? and fgnj = ?";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					bmdm,fgnj};
			}
		} else {
			if(fgnj==null||fgnj.equalsIgnoreCase("")){		
			sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
				+ " where xn=? and nd=? and xq=?) and bmdm like ?";
			inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					bmdm };
			}else if(fgnj.equalsIgnoreCase("no")){
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ? and (fgnj is null or fgnj = '')";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
						bmdm };
			}else{
				sql = "select zgh,xm from view_fdyxx a where zgh in(select zgh from fdypfb b"
					+ " where xn=? and nd=? and xq=?) and bmdm like ? and fgnj = ?";
				inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
						bmdm,fgnj };
			}
		}
		outputSQL = new String[] { "zgh", "xm" };
		List fdyList = dao.getList(sql, inputSQL, outputSQL);
	
		/** 获取评分标准列表 */
		sql = "select pfbz,qz from pfbzb where bzbh=? order by xssx";
		inputSQL = new String[] { pfbz };
		outputSQL = new String[] { "pfbz", "qz"};
		List pfbzList = dao.getList(sql, inputSQL, outputSQL);
	
		if (!Base.isNull(zgh)) {
			//获取被评价人学院代码
			CommonService service = new CommonService();
			String zprXydm = service.getBmdmForZgh(zgh); 
			/** 初始化评价数据 */
			sql = "select count(*) num from pjzbb where mxdx=? and  xydm = ?";
			inputSQL = new String[] { yhz,zprXydm};
			outputSQL = new String[] { "num" };
			tmp = dao.getOneRs(sql, inputSQL, outputSQL);
	
			int totNum = 0;
			totNum = Integer.parseInt(tmp[0]);
	
			sql = "select count(*) num from xspfb_lsb where xn=? and nd=? and xq=? and xh=? and jszgh=?";
			inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
					sUName, zgh };
			outputSQL = new String[] { "num" };
			tmp = dao.getOneRs(sql, inputSQL, outputSQL);
	
			if (Integer.parseInt(tmp[0]) != totNum) {
				mapTmp = new HashMap<String, Object>();
				sql = "insert into xspfb_lsb(xh,pjzbxh,jszgh,xn,nd,xq) select ?,xh,?,?,?,? from pjzbb"
					+ " where xh not in(select pjzbxh from xspfb_lsb where xn=? and nd=? "
					+ "and xq=? and xh=? and jszgh=?) and mxdx=? and xydm = ?";
				tmp = new String[] { sUName, zgh, Base.currXn, Base.currNd,
						Base.currXq, Base.currXn, Base.currNd, Base.currXq,
						sUName, zgh, yhz,zprXydm };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
	
				mapTmp = new HashMap<String, Object>();
				sql = "delete xspfb_lsb where pjzbxh not in(select xh from pjzbb where mxdx=? and xydm = ?) and xn=? and nd=?"
					+ " and xq=? and xh=? and jszgh=?";
				tmp = new String[] { yhz,zprXydm,Base.currXn, Base.currNd,
						Base.currXq, sUName, zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
	
				dao.runUpdate(sqlToExe);
			}
	
			/** 获取评价指标列表 */
			sql = "select a.pjzbxh,a.pjfs,a.py,a.bz,b.pjzb,b.fz,b.jtzb jtbz,b.scojzb,(select stlbmc from szdw_fdykhstdlb where b.stlbdm = stlbdm ) stlbmc from xspfb_lsb a,pjzbb b where a.xh=?"
				+ " and jszgh=? and a.pjzbxh=b.xh order by a.pjzbxh";
			inputSQL = new String[] { sUName, zgh };
			outputSQL = new String[] { "pjzbxh", "pjfs", "pjzb", "fz","jtbz","scojzb","stlbmc" };
			List pjzbList = dao.getList(sql, inputSQL, outputSQL);
			request.setAttribute("pjzbList", pjzbList);
		}
	
		sql = "select count(*) num from fdypjjlb where xn=? and nd=? and xq=? and xh=? and jszgh=?";
		inputSQL = new String[] { Base.currXn, Base.currNd, Base.currXq,
				sUName, zgh };
		outputSQL = new String[] { "num" };
		tmp = dao.getOneRs(sql, inputSQL, outputSQL);
		if (Integer.parseInt(tmp[0]) == 0) {
			request.setAttribute("sfpj", "no");
		} else {
			request.setAttribute("sfpj", "yes");
		}
	
		fdyForm.setXn(Base.currXn);
		fdyForm.setNd(Base.currNd);
		fdyForm.setXq(Base.currXq);
		fdyForm.setXydm(bmdm);
		request.setAttribute("sfcp", "yes");
		request.setAttribute("pfbzList", pfbzList);
		request.setAttribute("fdyList", fdyList);
		request.setAttribute("njList", Base.getNjList());
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)){
			sql = "select distinct bmdm xydm,bmmc xymc from ZXBZ_XXBMDM ";			
			List<HashMap<String, String>> partList = dao.getList(sql, new String[]{},new String[]{"xydm", "xymc"});
			
			request.setAttribute("xyList", partList);
		}else {
			request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		}
		return mapping.findForward("success");
	}
		
	/**
	 * //获取某个学院的部门
	 * @param userdept
	 * @return
	 * @author honglin
	 */
	public List<HashMap<String, String>> getBmxyList(String userdept) {
		// 获得部门代码，名称列表
		DAO dao = DAO.getInstance();
		String sql = "select bmdm,bmmc from zxbz_xxbmdm　where bmjb = '1' and bmdm=?";
		return dao.getList(sql, new String[] {userdept}, new String[] { "bmdm", "bmmc" });
	}
}
