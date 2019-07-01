/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-11-17 下午02:41:56</p>
 */
package xgxt.xsgygl.zjcmxy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;



import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.dao.gyglDao;


// TODO: Auto-generated Javadoc
/**
 * The Class GyglZjcmxyAction.
 */
public class GyglZjcmxyAction extends DispatchAction {
	
	/**
	 *住宿纪律处理查询
	 */
	public ActionForward zsjlDispose(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GyglZjcmxyForm myForm = (GyglZjcmxyForm)form;
		GyglZjcmService service = new GyglZjcmService();
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String userType = session.getAttribute("userType").toString();
		String userDep  = session.getAttribute("userDep").toString();	
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		if(userOnLine.equalsIgnoreCase("student")){
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		userType = CommonQueryDAO.getUserType(userDep);
		String xydm = myForm.getXydm();
//		String lddm = myForm.getLddm();
		String xn   = myForm.getXn();
		String xq   = myForm.getXq();
		if(xn==null){
			myForm.setXn(Base.currXn);
		}
		if(xq==null){
			myForm.setXq(Base.currXq);
		}
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			myForm.setXh(DealString.toGBK(myForm.getXh().trim()));
			myForm.setXm(DealString.toGBK(myForm.getXm().trim()));
			myForm.setSfcf(DealString.toGBK(myForm.getSfcf()));
			GyglZsjlClModel model = new GyglZsjlClModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getZsjlTopTr();
			rs = service.serv_getZsjlClQue(model);
		}
		int rsNum = rs==null?0:rs.size();
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		gyglDao.getLdLcQshList(request);
		request.setAttribute("userType", userType);
		request.setAttribute("cljgList",service.ser_getCljgList());
		//读写权判断		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"zsjlDispose.do", userOnLine.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("zsjlDispose");
	}
	/**
	 *住宿纪律处理
	 */
	public ActionForward dispose(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GyglZjcmxyForm myForm = (GyglZjcmxyForm)form;
		GyglZjcmService service = new GyglZjcmService();
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		HashMap<String,String>map = new HashMap<String, String>();	
		String sfcf = DealString.toGBK(request.getParameter("sfcf"));
		String xycljg =  DealString.toGBK(request.getParameter("xycljg"));
		String dcqk   =  DealString.toGBK(request.getParameter("dcqk"));
		String[] pks  = myForm.getPks();
		String checkedValue = "";
		if("save".equalsIgnoreCase(doType)){
			boolean done = service.ser_disposeSave(sfcf, xycljg, dcqk, pks,pkValue);
			request.setAttribute("done",done);
		}
		checkedValue = service.getZsjlCfXsList(pkValue);
		map = CommonQueryDAO.dao_getInfo("view_zjcm_zsjlxx",null," where ssbh||wjsj||wjlbdm='"+pkValue+"'  order by sfcf ");
		request.setAttribute("cljgList",service.ser_getCljgList());
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("act",act);
		request.setAttribute("zsjlXsList",service.ser_getZsjlXsList(pkValue,""));
		request.setAttribute("checkedValue", checkedValue);
		return mapping.findForward("dispose");
	}
	/**
	 * 住宿纪律处理审核
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public ActionForward zsjlCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		GyglZjcmxyForm myForm = (GyglZjcmxyForm)form;
		GyglZjcmService service = new GyglZjcmService();
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String userType = session.getAttribute("userType").toString();
		String userDep  = session.getAttribute("userDep").toString();	
//		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		userType = CommonQueryDAO.getUserType(userDep);
		String xydm = myForm.getXydm();
//		String lddm = myForm.getLddm();
		String xn   = myForm.getXn();
		String xq   = myForm.getXq();
		if(userOnLine.equalsIgnoreCase("student")){
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		if(xn==null){
			myForm.setXn(Base.currXn);
		}
		if(xq==null){
			myForm.setXq(Base.currXq);
		}
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			myForm.setXh(DealString.toGBK(myForm.getXh().trim()));
			myForm.setXm(DealString.toGBK(myForm.getXm().trim()));
			myForm.setSfcf(DealString.toGBK(myForm.getSfcf()));
			myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
			GyglZsjlClModel model = new GyglZsjlClModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getZsjlChkTopTr();
			rs = service.serv_getZsjlChkQue(model);
		}
		int rsNum = rs==null?0:rs.size();
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		gyglDao.getLdLcQshList(request);
		request.setAttribute("userType", userType);
		request.setAttribute("cljgList",service.ser_getCljgList());
		request.setAttribute("chkList",DAO.getInstance().getChkList(3));
		return mapping.findForward("zsjlCheck");
	}
	/**
	 *住宿纪律处理审核
	 */
	public ActionForward doCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GyglZjcmService service = new GyglZjcmService();
		String pkValue = request.getParameter("pkValue");		
		String doType = request.getParameter("doType");
		HashMap<String,String>map = new HashMap<String, String>();	
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));
		String xxcljg =  DealString.toGBK(request.getParameter("xxcljg"));
		if("save".equalsIgnoreCase(doType)){
			boolean done = service.serv_doCheckSave(xxcljg, yesNo, pkValue);
			request.setAttribute("done",done);
		}
		map = CommonQueryDAO.dao_getInfo("view_zjcm_zsjlxx",null," where ssbh||wjsj||wjlbdm='"+pkValue+"' order by sfcf");
		map.put("yesNo",map.get("xxsh"));
		request.setAttribute("cljgList",service.ser_getCljgList());
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);		
		request.setAttribute("zsjlXsList",service.ser_getZsjlXsList(pkValue,"cf"));
		request.setAttribute("chkList",DAO.getInstance().getChkList(3));
		return mapping.findForward("doCheck");
	}
	public ActionForward plCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws  Exception{
		GyglZjcmService service = new GyglZjcmService();
		String pkValue = request.getParameter("pkVStr");
		String check   = request.getParameter("check");
		service.serv_plCheckSave(pkValue, check);
		return zsjlCheck(mapping, form, request, response);
	}

	/**
	 * Zsjl input.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public static ActionForward zsjlInput(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		GyglZjcmxyForm myForm = (GyglZjcmxyForm)form;
		GyglZjcmService service = new GyglZjcmService();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String tips = "公寓管理 - 信息维护 - 住宿纪律";
		String tableName = "view_zjcm_zsjlxx";
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		List<HashMap<String,String>> topTr = new ArrayList<HashMap<String,String>>();
		ArrayList<String[]>  rs = new ArrayList<String[]>();
		userType = CommonQueryDAO.getUserType(userDep);
		if(userType.equalsIgnoreCase("stu")){
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		String xydm = myForm.getXydm();
//		String lddm = myForm.getLddm();
		if(xn==null){
			myForm.setXn(Base.currXn);
		}
		if(xq==null){
			myForm.setXq(Base.currXq);
		}
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		int rsNum = 0;
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			zsjlModel model = new zsjlModel();
			FormModleCommon.formToGBK(myForm);
			BeanUtils.copyProperties(model, myForm);
			String[] colList = {"xn","xqmc","xh","xm","xb","nj","bjmc","ldmc","qsh","cwh","wjsj","wjlbmc","lrr","sfcf","xxsh"};
			topTr = service.getToptrTitle(tableName,colList);
			rs = service.ser_xlzxsQuery(model, tableName);
			if(rs != null && !("".equals(rs))){
				rsNum = rs.size();
			}
		}
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		gyglDao.getLdLcQshList(request);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		
		String writeAble = (Base.chkUPower(userName,"zsjlInput.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no";
		request.setAttribute("writeAble",writeAble);
		List<HashMap<String, String>>  wjlbList = service.ser_wjlbList();
		request.setAttribute("wjlbList", wjlbList);
		gyglDao.getLdLcQshList(request);
		gyglDao.getXyZyBjxx(request);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", "zjcm_zsjlb");// 发送数据源表名
		request.setAttribute("act", "view");// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("zsjl_manager");
	}
	
	/**
	 * Zsjl add.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward zsjlAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglZjcmxyForm zsjlForm = (GyglZjcmxyForm) form;
		zsjlModel model = new zsjlModel();
		GyglZjcmService service = new GyglZjcmService();
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		if("save".equals(act)){
			boolean result = false;
			BeanUtils.copyProperties(model, zsjlForm);
			FormModleCommon.formToGBK(zsjlForm);
			model.setLrr(userName);
			result = service.serv_saveXsInfo(model,request,pk);
			if (result) {
				pk = model.getXh()+model.getWjsj()+model.getWjlbdm();
				request.setAttribute("done", "ok");
			} else {
				request.setAttribute("done", "no");
			}
		}
		if (StringUtils.isNotNull(xh)) {
			map = service.serv_getXsInfo(xh);
		}
		if (StringUtils.isNotNull(pk)) {
			map = service.serv_getpkForXsInfo(pk);
			map.put("pk", pk);
		}
		request.setAttribute("doType", StringUtils.isNull(doType)?"":doType);
		map.put("xn", Base.currXn);
		map.put("xq", Base.currXq);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("ldList", service.ser_setLdlist());
		request.setAttribute("wjlbList", service.serv_setLdWjlb());
		request.setAttribute("rs", map);
		return mapping.findForward("zsjl_add");
	}
	
	/**
	 * Zsjldel.纪律管理 删除
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward zsjldel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglZjcmxyForm zsjlForm = (GyglZjcmxyForm) form;
		String pk = request.getParameter("pkValue");
		GyglZjcmService service = new GyglZjcmService();
		boolean result = service.ser_xlzxsdel(pk, request);
		if (result) {
			request.setAttribute("done", "ok");
		} else {
			request.setAttribute("done", "no");
		}
		return zsjlInput(mapping, zsjlForm, request, response);
	}
	/**
	 * 导出数据
	 */
	public ActionForward expData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglZjcmxyForm zsjlForm = (GyglZjcmxyForm) form;
		GyglZjcmService service = new GyglZjcmService();
		zsjlModel model = new zsjlModel();
		BeanUtils.copyProperties(model, zsjlForm);
		FormModleCommon.formToGBK(model);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dao_expData("view_zjcm_zsjlxx",response,model);
		return mapping.findForward("");
	}
	public ActionForward zsjlStat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglZjcmxyForm myForm = (GyglZjcmxyForm)form;
		GyglZjcmService service = new GyglZjcmService();
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String userType = session.getAttribute("userType").toString();
		String userDep  = session.getAttribute("userDep").toString();	
		String userOnLine = session.getAttribute("userOnLine").toString();
		if(userOnLine.equalsIgnoreCase("student")){
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		userType = CommonQueryDAO.getUserType(userDep);
		String xydm = myForm.getXydm();
//		String lddm = myForm.getLddm();
		String xn   = myForm.getXn();
		String xq   = myForm.getXq();
		if(xn==null){
			myForm.setXn(Base.currXn);
		}
		if(xq==null){
			myForm.setXq(Base.currXq);
		}
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			myForm.setXh(DealString.toGBK(myForm.getXh().trim()));
			myForm.setXm(DealString.toGBK(myForm.getXm().trim()));
			myForm.setSfcf(DealString.toGBK(myForm.getSfcf()));
			GyglZsjlClModel model = new GyglZsjlClModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getZsjlStatTopTr();
			rs = service.serv_zsjlStatQue(model);
		}
		int rsNum = rs==null?0:rs.size();
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		gyglDao.getLdLcQshList(request);
		request.setAttribute("userType", userType);
		request.setAttribute("cljgList",service.ser_getCljgList());
		request.setAttribute("wjlbList", service.serv_getWjlbList());
		return mapping.findForward("zsjlStat");
	}
	/**
	 * 导出数据
	 */
	public ActionForward expStatData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglZjcmxyForm myForm = (GyglZjcmxyForm) form;
		GyglZjcmService service = new GyglZjcmService();
		myForm.setXh(DealString.toGBK(myForm.getXh().trim()));
		myForm.setXm(DealString.toGBK(myForm.getXm().trim()));
		myForm.setSfcf(DealString.toGBK(myForm.getSfcf()));
		GyglZsjlClModel model = new GyglZsjlClModel();
		BeanUtils.copyProperties(model, myForm);
		FormModleCommon.formToGBK(model);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		try {
			service.ser_expStatData(wwb, model);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
		return mapping.findForward("");
	}
}
