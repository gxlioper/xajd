
package xgxt.pjpy.csmz;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.WjcfActionUtils;
import xgxt.wjcf.wjcfutils.WjcfCommenBean;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政学院评奖评优Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-06</p>
 */
public class PjpyCsmzAction extends DispatchAction {

	/**
	 * 奖学金申请默认页面
	 * jxjsq ----- 奖学金申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String,String>>();
		PjpyCsmzService service = new PjpyCsmzService();
		xmList = service.getSqXmList(1);
		request.setAttribute("list", xmList);
		return mapping.findForward("jxjsqdefault");
	}
	
	/**
	 * 社会奖学金申请默认页面
	 * shjxjSq ---- 社会奖学金申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjSqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		PjpyCsmzService service = new PjpyCsmzService();
		String sPkVal = DealString.toGBK(request.getParameter("pkValue"));
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : request.getParameter("xh");//用户类型是学生则直接获取用户名
		HashMap<String, String> stuMap = service.getStuInfo(xh);//学生相关信息
		if (stuMap != null) {
			stuMap.put("stuExists", "yes");//学号非空
		} else {
			stuMap.put("stuExists", "no");//学号为空
		}//end if
		HashMap<String, String> resMap = service.getJxjInfo(stuMap);
		resMap.put("xn", Base.currXn);//当前学年
		resMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", sPkVal);
		return mapping.findForward("shjxjsq");
	}
	
	/**
	 * 奖学金申请默认页面
	 * jtjxjsq ----- 其它奖学金申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward jtjxjSqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String sql = "";
		String act = request.getParameter("act");
		String pk = request.getParameter("pk");
		String pkValue = request.getParameter("pkValue");
		PjpyCsmzActionForm applyForm = (PjpyCsmzActionForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		String jxjdm = request.getParameter("xmdm");
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userOnLine").toString();
		if (userType.equalsIgnoreCase("student")) {
	    	xh = session.getAttribute("userName").toString();
		} else {
			xh = request.getParameter("xh");
		}
	
		sql = "select * from view_xsjbxx where xh=?";
		PjpyCsmzDAO dao = new PjpyCsmzDAO();
		String[] colList = dao
		.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		if (jxjdm != null) {
			sql = "select jxjlb,jlje from jxjdmb where jxjdm=?";
			String[] jxjInfo = dao.getOneRs(sql, new String[] { jxjdm },
					new String[] { "jxjlb", "jlje" });
			if (jxjInfo != null) {
				map.put("jxjlb", jxjInfo[0]);
				map.put("jlje", jxjInfo[1]);
			}
		}
		sql = "select * from jxjdmb";	
		List jxjList = dao.getList(sql, new String[] {}, new String[] {	
				"jxjdm", "jxjmc" });
		request.setAttribute("jxjList", jxjList);
		List jxjListN = dao.getList(sql, new String[] {}, new String[] {	
				"jxjdm", "jxjmc" });
		request.setAttribute("jxjListN", jxjListN);
		sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
				"jxjsqxn", "jxjsqnd" });
		map.put("xn", tmp[0]);
		map.put("nd", tmp[1]);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		String[] qtxx = null;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			qtxx = new String[] { "xh", "jxj1", "shyg1", "ddj1",
					"tyhgbz1", "jxj2", "shyg2", "ddj2",
					"tyhgbz2", "jxj3", "shyg3", "ddj3",
					"tyhgbz3", "jxj4", "shyg4", "ddj4","tyhgbz4" };
		}else{
			qtxx = new String[] { "xh", "jxj1", "shyg1", "ddj1", "bxkpjcj1", "bjcjpx1", "zycjpx1",
					"tyhgbz1", "jxj2", "shyg2", "ddj2",  "bxkpjcj2", "bjcjpx2", "zycjpx2",
					"tyhgbz2", "jxj3", "shyg3", "ddj3",  "bxkpjcj3", "bjcjpx3", "zycjpx3",
					"tyhgbz3", "jxj4", "shyg4", "ddj4",   "bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4" };
		}
		sql = "select * from xsjxjxxb where xh=?";
		String[] qtxxfs = dao.getOneRs(sql, new String[] {xh}, qtxx);
		if( act != null && act.equalsIgnoreCase("modi")){
			String sql1 = "select * from view_xsjxjb where "+pk+"='"+pkValue+"'";
			String[] jxjxx = dao.getColumnName("select * from view_xsjxjb where 1=2");
			String[] jxjxxArr = dao.getOneRs(sql1, new String[]{}, jxjxx);
			for(int i=0;i<jxjxx.length;i++){
				if(jxjxxArr[i] != null){
					if(jxjxx[i].equalsIgnoreCase("jxjdm")){
						applyForm.setXmdm(jxjxxArr[i]);
					} else if(jxjxx[i].equalsIgnoreCase("DNSHJXJ")){
						applyForm.setZdm(jxjxxArr[i]);
					} else if (jxjxx[i].equalsIgnoreCase("KYCG")){
						System.out.println(jxjxx[i]);
					}
					map.put(jxjxx[i].toLowerCase(), jxjxxArr[i]);
				}
			}
		}
		//System.out.println(sql);
		if(qtxxfs == null){
			qtxxfs = new String[qtxx.length];
		}
		for(int i = 1;i < qtxx.length; i++){
			map.put(qtxx[i],qtxxfs[i]);
		}
		String[] tt = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?",new String[]{xh},new String[]{"sjhm","wysp"});
		String sjhm = "";
		String wysp = "";
		if(tt != null && tt.length == 2){
			sjhm = tt[0];
			wysp = tt[1];
		}
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		request.setAttribute("rs",map);
		return mapping.findForward("jtjxjsqdefault");
	}*/

	/**
	 * 保存社会奖学金
	 * shjxj ---- 社会奖学金 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShJxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		SaveShJxjModel shjxjModel = new SaveShJxjModel();//保存社会奖学金MODEL
		BeanUtils.copyProperties(shjxjModel, csmzForm);
		PjpyCsmzService service = new PjpyCsmzService();
		String xh = csmzForm.getXh();
//		String jxjdm = request.getParameter("jxjdm");
//		String xn = Base.currXn;
		boolean bJg = service.isSaveShJxj(shjxjModel, request);//保存社会奖学金信息
			if (bJg) {
				request.setAttribute("inserted", "ok");
			}else {
				request.setAttribute("inserted", "no");
			}
		
		HashMap<String, String> stuMap = service.getStuInfo(xh);//学生相关信息
		if (stuMap != null) {
			stuMap.put("stuExists", "yes");//学号非空
		} else {
			stuMap.put("stuExists", "no");//学号为空
		}//end if
		HashMap<String, String> resMap = service.getJxjInfo(stuMap);
		resMap.put("xn", Base.currXn);//当前学年
		resMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("rs", resMap);
		return mapping.findForward("shjxjsq");
	}
	
	/**
	 * 奖学金申请结果查询默认页面
	 * jxjsqqry ---- 奖学金申请查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqQryDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String,String>>();
		PjpyCsmzService service = new PjpyCsmzService();
		xmList = service.getSqXmList(1);//获取奖学金列表选择信息
		request.setAttribute("list", xmList);
		return mapping.findForward("jxjsqqrydefault");
	}
	
	/**
	 * 社会奖学金申请结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjQryDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		WjcfActionUtils commonAction = new WjcfActionUtils();//公用ACTION
		WjcfCommenBean commenBean = new WjcfCommenBean();
		commonAction.appendProperties(request, commenBean);//在REQUEST中存放页面加载的列表
		csmzForm.setXn(Base.currXn);
		request.setAttribute("cForm", csmzForm);
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "xsjxjb");
		return mapping.findForward("shjxjqrydefault");
	}
	
	/**
	 * 社会奖学金查询
	 * shjxjqry ---- 社会奖学金查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		QueryShJxjModel shjxjModel = new QueryShJxjModel();//社会奖学金查询MODEL
		BeanUtils.copyProperties(shjxjModel, csmzForm);
		PjpyCsmzService service = new PjpyCsmzService();
		List<HashMap<String, String>> topList = service.getShJxjTit(1, "", "");
		List<String[]> resList = service.getShJxjRes(shjxjModel);
		String xh = DealString.toGBK(csmzForm.getXh());
		csmzForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		WjcfActionUtils commonAction = new WjcfActionUtils();//公用ACTION
		WjcfCommenBean commenBean = new WjcfCommenBean();
		commonAction.appendProperties(request, commenBean);//在REQUEST中存放页面加载的列表
		csmzForm.setXn(Base.currXn);
		request.setAttribute("cForm", csmzForm);
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "xsjxjb");
		return mapping.findForward("shjxjqrydefault");
	}

	/**
	 * 社会奖学金修改
	 * shjxj ---- 社会奖学金 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		PjpyCsmzService service = new PjpyCsmzService();
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getShJxjByPkVal(pkValue);//通过主键获取学生奖学金信息
		resMap.put("nd", Base.currNd);
		resMap.put("xn", Base.currXn);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkVal", pkValue);
		return mapping.findForward("shjxjmodi");
	}
	
	/**
	 *  社会奖学金删除
	 *  shjxjdel ---- 社会奖学金删除 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		String[] cbv = csmzForm.getCbv();
		PjpyCsmzService service = new PjpyCsmzService();
		String sJg = service.delInfoByPk(cbv);
		if (!StringUtils.isNull(sJg)) {
			request.setAttribute("result", "yes");
		}else {
			request.setAttribute("result", sJg);
		}//end if
		
		WjcfActionUtils commonAction = new WjcfActionUtils();//公用ACTION
		WjcfCommenBean commenBean = new WjcfCommenBean();
		commonAction.appendProperties(request, commenBean);//在REQUEST中存放页面加载的列表
		csmzForm.setXn(Base.currXn);
		request.setAttribute("cForm", csmzForm);
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "xsjxjb");
		return mapping.findForward("shjxjqrydefault");
	}

	/**
	 * 奖学金审核默认页面
	 * jxjsh ---- 奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjShDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String,String>>();
		PjpyCsmzService service = new PjpyCsmzService();
		xmList = service.getSqXmList(1);
		request.setAttribute("list", xmList);
		return mapping.findForward("jxjshdefault");
	}
	
	/**
	 * 奖学金审核默认页面
	 * shjxjsh ---- 社会奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjShDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		PjpyCsmzService service = new PjpyCsmzService();
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
		String isFdy 		   = session.getAttribute("isFdy").toString();
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
		} 
		
		WjcfActionUtils commonAction = new WjcfActionUtils();//公用ACTION
		WjcfCommenBean commenBean = new WjcfCommenBean();
		commenBean.setXydm(xydm);
		commonAction.appendProperties(request, commenBean);//在REQUEST中存放页面加载的列表
		csmzForm.setXn(Base.currXn);
		csmzForm.setXydm(xydm);
		request.setAttribute("cForm", csmzForm);
		//辅导员审核时获取对应的专业与班级
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "true")) {
			String fdyName = session.getAttribute("userName").toString();
			request.setAttribute("bjList", service.fdyGetBjList(fdyName));
			request.setAttribute("zyList", service.fdyGetZyList(fdyName));
			request.setAttribute("iscsmz", "fdy");
		}
		return mapping.findForward("shjxjshdefault");
	}
	
	/**
	 * 奖学金审核查询(辅导员，学院，学校)
	 * shjxjshqry ---- 社会奖学金审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjShQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType       = session.getAttribute("userType").toString();//用户类型
		String sIsFdy          = session.getAttribute("isFdy").toString();//辅导员
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
		} 
		QueryShJxjModel shjxjModel = new QueryShJxjModel();//查询MODEL
		BeanUtils.copyProperties(shjxjModel, csmzForm);
		PjpyCsmzService service = new PjpyCsmzService();
		List<HashMap<String, String>> topList = service.getShJxjTit(2, sUserType, sIsFdy);
		List<String[]> resList = service.getShJxjRes2(shjxjModel, sUserType, sIsFdy);
		WjcfActionUtils commonAction = new WjcfActionUtils();//公用ACTION
		WjcfCommenBean commenBean = new WjcfCommenBean();
		commenBean.setXydm(xydm);
		commonAction.appendProperties(request, commenBean);//在REQUEST中存放页面加载的列表
		csmzForm.setXn(Base.currXn);
		request.setAttribute("cForm", csmzForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		//辅导员审核时获取对应的专业与班级
		if (!StringUtils.isNull(sIsFdy) && StringUtils.isEqual(sIsFdy, "true")) {
			String fdyName = session.getAttribute("userName").toString();
			request.setAttribute("bjList", service.fdyGetBjList(fdyName));
			request.setAttribute("zyList", service.fdyGetZyList(fdyName));
			request.setAttribute("iscsmz", "fdy");
		}
		csmzForm.setXydm(xydm);
		return mapping.findForward("shjxjshdefault");
	}
	
	/**
	 * 社会奖学金审核(辅导员，学院，学校)
	 * shjxjsh ---- 社会奖学金审核 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
		} 
		String sRes = request.getParameter("param1");//获得审核结果
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		String[] keys = csmzForm.getCbv();
		ShShJxjModel shjxjModel = new ShShJxjModel();//社会奖学金审核MDOEL
		BeanUtils.copyProperties(shjxjModel, csmzForm);
		shjxjModel.setShjg(sRes);
		shjxjModel.setIsFdy(sIsFdy);
		shjxjModel.setUserType(sUserType);
		PjpyCsmzService service = new PjpyCsmzService();
		service.shjxjSh(shjxjModel, keys, request);
		
		WjcfActionUtils commonAction = new WjcfActionUtils();//公用ACTION
		WjcfCommenBean commenBean = new WjcfCommenBean();
		commenBean.setXydm(xydm);
		commonAction.appendProperties(request, commenBean);//在REQUEST中存放页面加载的列表
		csmzForm.setXn(Base.currXn);
		request.setAttribute("cForm", csmzForm);
		request.setAttribute("result", "yes");
		return mapping.findForward("shjxjshdefault");
	}
	
	/**
	 * 社会奖学金单个审核(学院，学校，辅导员)
	 * shjxjshone ---- 社会奖学金单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjShOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
//		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
//		String xydm = "";
		//用户是学院时
//		if("xy".equalsIgnoreCase(sUserType)){
//			xydm = bmdm;
//		} 
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		PjpyCsmzService service = new PjpyCsmzService();
		HashMap<String, String> resMap = new HashMap<String, String>();
		List<HashMap<String, String>> chkList = service.getChkList(3);
		resMap = service.getShJxjByPkVal2(sPk, sUserType, sIsFdy);//通过主键获取学生奖学金信息
		resMap.put("nd", Base.currNd);
		resMap.put("xn", Base.currXn);
		csmzForm.setYesNo(resMap.get("shjg"));
		csmzForm.setShyj(resMap.get("shyj"));
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", sPk);
		request.setAttribute("chkList", chkList);
		return mapping.findForward("shjxjshone");
	}
	
	/**
	 * 社会奖学金单个审核(学院，学校，辅导员)
	 * shjxjPriseOne ---- 社会奖学金单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjxjPriseOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
//		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
//		String xydm = "";
		//用户是学院时
//		if("xy".equalsIgnoreCase(sUserType)){
//			xydm = bmdm;
//		} 
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		PjpyCsmzService service = new PjpyCsmzService();
		ShShJxjModel shjxjModel = new ShShJxjModel();
		BeanUtils.copyProperties(shjxjModel, csmzForm);
		shjxjModel.setIsFdy(sIsFdy);
		shjxjModel.setUserType(sUserType);
		boolean bFlag = service.saveshjxj(shjxjModel, sPk, request);//单个审核
		if (bFlag){
			request.setAttribute("result", "view");
		}else{
			request.setAttribute("result", "noview");
		}
		
		HashMap<String, String> resMap = new HashMap<String, String>();
		List<HashMap<String, String>> chkList = service.getChkList(3);
		resMap = service.getShJxjByPkVal2(sPk, sUserType, sIsFdy);//通过主键获取学生奖学金信息
		resMap.put("nd", Base.currNd);
		resMap.put("xn", Base.currXn);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", sPk);
		request.setAttribute("chkList", chkList);
		return mapping.findForward("shjxjshone");
	}
	
	/**
	 * 社会奖学金单个修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShJxj1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkVal  = DealString.toGBK(request.getParameter("pkVal"));
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		SaveShJxjModel shjxjModel = new SaveShJxjModel();//保存社会奖学金MODEL
		BeanUtils.copyProperties(shjxjModel, csmzForm);
		PjpyCsmzService service = new PjpyCsmzService();
		String xh = csmzForm.getXh();
		String jxjdm = request.getParameter("jxjdm");
		String xn = Base.currXn;
		shjxjModel.setJxjdm(jxjdm);
		boolean bJg = service.isSaveShJxj(shjxjModel, request);//保存社会奖学金信息
			if (bJg) {
				request.setAttribute("inserted", "ok");
			}else {
				request.setAttribute("inserted", "no");
			}
		
		HashMap<String, String> stuMap = service.getStuInfo(xh);//学生相关信息
		if (stuMap != null) {
			stuMap.put("stuExists", "yes");//学号非空
		} else {
			stuMap.put("stuExists", "no");//学号为空
		}//end if
		HashMap<String, String> resMap = service.getJxjInfo(stuMap);
		resMap.put("xn", xn);//当前学年
		resMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("rs", resMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("shjxjmodi");
	}
	
	/**
	 * 代码维护增加（奖学金，荣誉称号，军训奖）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpydmwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		PjpyCsmzService service = new PjpyCsmzService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		String bmdm = session.getAttribute("userDep").toString();//用户部门
		String xydm = "";//学院代码
		String jxjlb = "院级";
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			jxjlb = "系级";
		} else {
			xydm = service.getXgbdm();
		}
		int len = 10;
		if (!StringUtils.isNull(xydm)) {
			len = 10 - xydm.length();
		}
		String realTable = request.getParameter("realTable");
		//奖学金代码的增加
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "jxjdmb")) {
			request.setAttribute("jxjdmb", "yes");
		}
		//荣誉称号代码的增加
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "rychdmb")) {
			request.setAttribute("rychdmb", "yes");
		}
		//军训代码的增加
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "jxjxdmb")) {
			request.setAttribute("jxjxdmb", "yes");
		}
		csmzForm.setJxjlb(jxjlb);
		csmzForm.setRychlb(jxjlb);
		List<HashMap<String, String>> jxjlbList = service.getJxjLb();//奖学金类别
		String id = service.getAutoJxjId();
		if (!StringUtils.isNull(id)) {
			if (id.length() == 1) {
				id = "000" + id;
			} else if (id.length() == 2) {
				id = "00" + id;
			} else if (id.length() == 3) {
				id = "0" + id;
			} 
		}
		request.setAttribute("jxjlbList", jxjlbList);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xydm", xydm);
		request.setAttribute("writable", "yes");
		request.setAttribute("len", len);
		request.setAttribute("autoid", id);
		return mapping.findForward("dmwhadd");
	}
	
	/**
	 * 代码维护保存（奖学金，荣誉称号，军训奖）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpydmSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		PjpyCsmzService service = new PjpyCsmzService();
		String jxjlb = request.getParameter("jxjlb");
		String rychlb = request.getParameter("rychlb");
		String realTable = request.getParameter("realTable");
		String xydm = request.getParameter("xydm");
		csmzForm.setJxjdm(request.getParameter("jxjdm"));
		csmzForm.setXydm(xydm);
//		String pk = "";//主键
		String tableName = "";//表名
		String pkValue = "";//主键值
		JxjdmModel jxjdmModel = new JxjdmModel();//奖学金代码MODEL
		RychdmModel rychdmModel = new RychdmModel();//荣誉称号MODEL
		JxjxdmModel jxjxdmModel = new JxjxdmModel();//军训奖项MODEL
		//奖学金代码的增加
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "jxjdmb")) {
//			pk = "jxjdm";
			tableName = "jxjdmb";
			csmzForm.setJxjlb(jxjlb);
			BeanUtils.copyProperties(jxjdmModel, csmzForm);
			pkValue = jxjdmModel.getXydm() + jxjdmModel.getJxjdm();
			request.setAttribute("jxjdmb", "yes");
		}
		//荣誉称号代码的增加
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "rychdmb")) {
//			pk = "rychdm";
			tableName = "rychdmb";
			csmzForm.setRychlb(rychlb);
			BeanUtils.copyProperties(rychdmModel, csmzForm);
			pkValue = rychdmModel.getXydm() + rychdmModel.getRychdm();
			request.setAttribute("rychdmb", "yes");
		}
		//军训代码的增加
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "jxjxdmb")) {
//			pk = "jxdm";
			tableName = "jxjxdmb";
			BeanUtils.copyProperties(jxjxdmModel, csmzForm);
			pkValue = jxjxdmModel.getJxdm();
			request.setAttribute("jxjxdmb", "yes");
		}
		boolean bDel = service.dmDel(tableName, pkValue, request);
		if (bDel) {
			//代码保存
			boolean bIns = service.dmSave(tableName, jxjdmModel, rychdmModel, jxjxdmModel, request);
			if (bIns) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		List<HashMap<String, String>> jxjlbList = service.getJxjLb();//奖学金类别
		request.setAttribute("jxjlbList", jxjlbList);
		request.setAttribute("writable", "yes");
		request.setAttribute("realTable", realTable);
		csmzForm.setJxjmc(DealString.toGBK(csmzForm.getJxjmc()));
		csmzForm.setRychmc(DealString.toGBK(csmzForm.getRychmc()));
		csmzForm.setJxmc(DealString.toGBK(csmzForm.getJxmc()));
		return mapping.findForward("dmwhadd");
	}
	
	/**
	 * 代码删除（奖学金，荣誉称号，军训奖）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpydmwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzService service = new PjpyCsmzService();
		String realTable = request.getParameter("realTable");
		String pkValue = request.getParameter("pkValue");
		String xydm = "";
		String dm = "";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		String bmdm = session.getAttribute("userDep").toString();//用户部门
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
		} else {
			xydm = service.getXgbdm();
		}
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "jxjdmb")) {
			dm = "jxjdm";
		} if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "rychdmb")) {
			dm = "rychdm";
		}
		//判断用户是否有权操作
		boolean bWritable = service.chkUserWritable(xydm, dm, pkValue, realTable);
		if (bWritable) {
			service.dmDel(realTable, pkValue, request);//单个删除
		} 
		//军训奖项不用判断
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "jxjxdmb")) {
			service.dmDel(realTable, pkValue, request);//单个删除
		}
		return new ActionForward("/code_man.do?act=prise", false);
	}
	
	/**
	 * 代码全部删除（奖学金，荣誉称号，军训奖）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpydmAllDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzService service = new PjpyCsmzService();
		String realTable = request.getParameter("realTable");
		service.dmAllDel(realTable, request);
		return new ActionForward("/code_man.do?act=prise", false);
	}
	
	/**
	 * 代码单个修改显示详细信息（奖学金，荣誉称号，军训奖）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpydmModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		PjpyCsmzService service = new PjpyCsmzService();
		String realTable = request.getParameter("realTable");//表名
		realTable = !StringUtils.isNull(realTable) ? realTable : "";
		String pkValue = request.getParameter("pkValue");//主键
		String xydm = "";//学院代码
		String dm = "";//主键
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		String bmdm = session.getAttribute("userDep").toString();//用户部门
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
		} else {
			xydm = service.getXgbdm();
		}
		if (StringUtils.isEqual(realTable, "jxjdmb")) {
			dm = "jxjdm";
		} 
		if (StringUtils.isEqual(realTable, "rychdmb")) {
			dm = "rychdm";
		}
		//判断用户是否有权操作
		boolean bWritable = service.chkUserWritable(xydm, dm, pkValue, realTable);
		if (bWritable) {
			request.setAttribute("iswritable", "yes");
		} else {
			request.setAttribute("iswritable", "no");
		}
		//军训奖项不用判断
		if (StringUtils.isEqual(realTable, "jxjxdmb")) {
			request.setAttribute("iswritable", "yes");
		}
		HashMap<String, String> resMap = service.getDmInfo(pkValue, realTable);//获取代码信息
		if (resMap != null) {//加载页面信息
			if (StringUtils.isEqual(realTable, "jxjdmb")) {
				csmzForm.setJxjdm(resMap.get("jxjdm"));
				csmzForm.setJxjmc(resMap.get("jxjmc"));
				csmzForm.setJxjjb(resMap.get("jxjjb"));
				csmzForm.setJxjlb(resMap.get("jxjlb"));
				csmzForm.setSzjdbz(resMap.get("szjdbz"));
				csmzForm.setJlje(resMap.get("jlje"));
				csmzForm.setXydm(resMap.get("xydm"));
				csmzForm.setSztzxfbz(resMap.get("sztzxfbz"));
				request.setAttribute("jxjdmb", "yes");
			}
			if (StringUtils.isEqual(realTable, "rychdmb")) {
				csmzForm.setRychdm(resMap.get("rychdm"));
				csmzForm.setRychmc(resMap.get("rychmc"));
				csmzForm.setRychlb(resMap.get("rychlb"));
				csmzForm.setXydm(resMap.get("xydm"));
				request.setAttribute("rychdmb", "yes");
			}
			if (StringUtils.isEqual(realTable, "jxjxdmb")) {
				csmzForm.setJxdm(resMap.get("jxdm"));
				csmzForm.setJxmc(resMap.get("jxmc"));
				request.setAttribute("jxjxdmb", "yes");
			}
		}
		List<HashMap<String, String>> jxjlbList = service.getJxjLb();//奖学金类别
		request.setAttribute("jxjlbList", jxjlbList);
		request.setAttribute("writable", "yes");
		request.setAttribute("realTable", realTable);
		request.setAttribute("xydm", csmzForm.getXydm());
		return mapping.findForward("pjpydmmodi");
	}
	
	/**
	 * 单个修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dmModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzActionForm csmzForm = (PjpyCsmzActionForm) form;
		PjpyCsmzService service = new PjpyCsmzService();
		String jxjlb = request.getParameter("jxjlb");
		String rychlb = request.getParameter("rychlb");
		csmzForm.setXydm(request.getParameter("xydm"));
		String realTable = request.getParameter("realTable");
		String xydm = "";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		String bmdm = session.getAttribute("userDep").toString();//用户部门
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
		} else {
			xydm = service.getXgbdm();
		}
		String pk = "";//主键
		String tableName = "";//表名
		String pkValue = "";//主键值
		JxjdmModel jxjdmModel = new JxjdmModel();//奖学金代码MODEL
		RychdmModel rychdmModel = new RychdmModel();//荣誉称号MODEL
		JxjxdmModel jxjxdmModel = new JxjxdmModel();//军训奖项MODEL
		//奖学金代码的增加
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "jxjdmb")) {
			pk = "jxjdm";
			tableName = "jxjdmb";
			csmzForm.setJxjlb(jxjlb);
			BeanUtils.copyProperties(jxjdmModel, csmzForm);
			pkValue = jxjdmModel.getJxjdm();
			request.setAttribute("jxjdmb", "yes");
		}
		//荣誉称号代码的增加
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "rychdmb")) {
			pk = "rychdm";
			tableName = "rychdmb";
			csmzForm.setRychlb(rychlb);
			BeanUtils.copyProperties(rychdmModel, csmzForm);
			pkValue = rychdmModel.getRychdm();
			request.setAttribute("rychdmb", "yes");
		}
		//军训代码的增加
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "jxjxdmb")) {
			pk = "jxdm";
			tableName = "jxjxdmb";
			BeanUtils.copyProperties(jxjxdmModel, csmzForm);
			pkValue = jxjxdmModel.getJxdm();
			request.setAttribute("jxjxdmb", "yes");
		}
		//判断用户是否有权操作
		boolean isWritable = service.chkUserWritable(xydm, pk, pkValue, tableName);
		if (isWritable) {
			boolean bDel = service.dmDel(tableName, pkValue, request);
			if (bDel) {
				//代码保存
				boolean bIns = service.dmSave1(tableName, jxjdmModel, rychdmModel, jxjxdmModel, request);
				if (bIns) {
					request.setAttribute("inserted", "yes");
				} else {
					request.setAttribute("inserted", "no");
				}
				request.setAttribute("iswritable", "yes");
			}
		} else {//军训奖项不用判断
			if (!StringUtils.isNull(realTable) && StringUtils.isEqual(realTable, "jxjxdmb")) {
				boolean bDel = service.dmDel(tableName, pkValue, request);
				if (bDel) {
					//代码保存
					boolean bIns = service.dmSave1(tableName, jxjdmModel, rychdmModel, jxjxdmModel, request);
					if (bIns) {
						request.setAttribute("inserted", "yes");
					} else {
						request.setAttribute("inserted", "no");
					}
					request.setAttribute("iswritable", "yes");
				}
			} else {
				request.setAttribute("inserted", "nowirtable");//无权操作
			}
		}
		List<HashMap<String, String>> jxjlbList = service.getJxjLb();//奖学金类别
		request.setAttribute("jxjlbList", jxjlbList);
		request.setAttribute("writable", "yes");
		request.setAttribute("realTable", realTable);
		csmzForm.setJxjmc(DealString.toGBK(csmzForm.getJxjmc()));
		csmzForm.setRychmc(DealString.toGBK(csmzForm.getRychmc()));
		csmzForm.setJxmc(DealString.toGBK(csmzForm.getJxmc()));
		return mapping.findForward("pjpydmmodi");
	}
	
	/**
	 * 奖学金申请报表输出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjbbExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzService service = new PjpyCsmzService();
		String xh = request.getParameter("xh");
		String jxjdm = request.getParameter("jxjdm");
		String modelPath = "";//数据表格模板
		modelPath = servlet.getServletContext().getRealPath("")+"/print/csmzjxjsq.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.jxjPrint(wwb, xh, jxjdm);//奖学金数据导出
		return mapping.findForward("");
	}

	/**
	 * 学生修改奖学金申请信息页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stuXgjxjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzService service = new PjpyCsmzService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rsMap = service.viewJxjxx(pkValue);//获取奖学金信息
		request.setAttribute("rs", rsMap);
		return mapping.findForward("stuxgjxjxx");
	}
	
	public ActionForward stujxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyCsmzService service = new PjpyCsmzService();
		String pkValue = request.getParameter("pkValue");
		@SuppressWarnings("unused")
		boolean bFlag = service.stujxjDel(pkValue, request);
		return mapping.findForward("stujxjdel");
	}
	
	public ActionForward sturychDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCsmzService service = new PjpyCsmzService();
		String pkValue = request.getParameter("pkValue");
		@SuppressWarnings("unused")
		boolean bFlag = service.sturychDel(pkValue, request);
		return mapping.findForward("sturychdel");
	}
	
}
