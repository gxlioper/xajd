package xgxt.pjpy.xcxy;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyXcxyAction extends CommonAction {

	private PjpyXcxyService service = PjpyXcxyService.getInstance();
	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	/**
	 * 学生操作行信息维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stuCxfxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		PjpyXcxyActionForm pjpyForm = (PjpyXcxyActionForm) form;
		String act = pjpyForm.getAct();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			pjpyForm.setXydm(xydm);
		}
		zydm = pjpyForm.getZydm();
		nj = pjpyForm.getNj();
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if (QUERY.equalsIgnoreCase(act)) {//查询数据
			PjpyXcxyModel model = new PjpyXcxyModel();
			BeanUtils.copyProperties(model, pjpyForm);
			topTr = service.queryCxfTitle(pjpyForm.getFs());
			rs = service.queryCxfResult(model, pjpyForm.getFs(), request, pjpyForm);
			int count = service.queryCxfNum(model, pjpyForm.getFs(), request);
			pjpyForm.getPages().setMaxRecord(count);
		} else if (DELETE.equalsIgnoreCase(act)) {//删除数据
			String flag = service.deleteCxf(pjpyForm.getCbv());
			if (StringUtils.isNull(flag)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failinfo", "操作完成,其中第" + flag + "条数据删除失败!");
			}
		}
		appendQryResult(request, topTr, rs);
		pjpyForm.setXh(DealString.toGBK(pjpyForm.getXh()));
		appendProperties(request, xydm, zydm, nj);
		appendFdybjList(request);
		return mapping.findForward("stucxfwh");
	}
	
	/**
	 * 操行分增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xn     = request.getParameter("xn");
		String xq     = request.getParameter("xq");
		String act = request.getParameter("act");
		List<HashMap<String,String>> valueList = null;
		
		if (SAVE.equalsIgnoreCase(act)) {
			List<HashMap<String,String>> param = getParams(request);
			valueList = param;
			boolean flag = service.cxfAddSave(param, xn, xq);
			if (flag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		request.setAttribute("rs", valueList);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		return mapping.findForward("cxfadd");
	}
	
	/**
	 * 操行分修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxfUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXcxyActionForm pjpyForm = (PjpyXcxyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = service.viewCxf(pkValue);
		String act = request.getParameter("act");
		if (SAVE.equalsIgnoreCase(act)) {//保存数据
			PjpyXcxyModel model = new PjpyXcxyModel();
			BeanUtils.copyProperties(model, pjpyForm);
			boolean flag = service.updateCxf(pkValue, model, request);
			if (flag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		pjpyForm.setXn(rs.get("xn"));
		pjpyForm.setXq(rs.get("xq"));
		pjpyForm.setCxxm(rs.get("cxxm"));
		pjpyForm.setCxfs(rs.get("cxfs"));
		pjpyForm.setCxlb(rs.get("cxlb"));
		request.setAttribute("act", act);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		return mapping.findForward("cxfview");
	}
	
	
	//页面参数值获取
	public List<HashMap<String,String>>  getParams(HttpServletRequest request){
		Enumeration paramsEnum = request.getParameterNames();
		List<HashMap<String,String>> valueList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String[]> map_values = new HashMap<String, String[]>();
		int valueListSize = -1;
		while(paramsEnum.hasMoreElements()){
			String param = (String)paramsEnum.nextElement();
			if(param.startsWith("_")){
				String[] paramValue = request.getParameterValues(param);
				map_values.put(param.replace("_", ""), paramValue);
				valueListSize = paramValue.length;
			}
		}
		if(valueListSize > -1){
			for(int i=0;i<valueListSize;i++){
				valueList.add(new HashMap<String,String>());
			}
			Set<String> keySet = map_values.keySet();
			for(String key : keySet){
				String[] values = map_values.get(key);
				for(int i = 0;i<valueList.size();i++){
					HashMap<String,String> m = valueList.get(i);
					m.put(key, DealString.toGBK(values[i]));
				}
			}
		}
		return valueList;
	}
	
	/**
	 * 评奖成绩信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjcjXxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXcxyActionForm pjpyForm = (PjpyXcxyActionForm) form;
		String act = pjpyForm.getAct();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			pjpyForm.setXydm(xydm);
		}
		zydm = pjpyForm.getZydm();
		nj = pjpyForm.getNj();
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if (QUERY.equalsIgnoreCase(act)) {//查询数据
			PjpyXcxyModel model = new PjpyXcxyModel();
			BeanUtils.copyProperties(model, pjpyForm);
			int count = service.queryPjcjResultNum(model);
			rs = service.queryPjcjResult(model, pjpyForm);
			topTr = service.queryPjcjTitle();
			pjpyForm.getPages().setMaxRecord(count);
		} else if (AUTOACCOUNT.equalsIgnoreCase(act)) {//自动计算成绩
			boolean flag = service.autoAccount(pjpyForm.getXydm(),pjpyForm.getXn(), pjpyForm.getXq());
			if (flag) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
			}
		}
		appendQryResult(request, topTr, rs);
		pjpyForm.setXh(DealString.toGBK(pjpyForm.getXh()));
		appendProperties(request, xydm, zydm, nj);
		appendFdybjList(request);
		return mapping.findForward("pjcjxxwh");
	}

	/**
	 * 奖学金单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXcxyActionForm pjpyForm = (PjpyXcxyActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String pkVal = request.getParameter("pkVal");
		if (StringUtils.isNull(pkVal)) {
			pkVal = request.getParameter("pkValue");
		}
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			PjpyXcxyModel model = new PjpyXcxyModel();
			BeanUtils.copyProperties(model, pjpyForm);
			boolean flag = service.updateJxjxx(pkVal, userType, request, model);
			if (flag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		HashMap<String, String> rs = service.queryJxjshxx(pkVal, userType);
		pjpyForm.setYesNo(rs.get("yesno"));
		pjpyForm.setYj(rs.get("yj"));
		request.setAttribute("rs", rs);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jxjshone");
	}
	
		
	/**
	 * 奖学金发放表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjffb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		PjpyXcxyModel model = new PjpyXcxyModel();
//		model.setXn(Base.getJxjsqxn());
//		model.setNd(Base.getJxjsqnd());
//		model.setJxjdm(request.getParameter("jxjdm"));
//		String modelPath = "";
//		modelPath = servlet.getServletContext().getRealPath("")+"/print/xcxy_jxjffb.xls";
//		response.reset();
//		response.setContentType("application/vnd.ms-excel");
//		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
//		service.jxjfpbPrint(wwb, model);
		String tjblx = DealString.toGBK(request.getParameter("tjblx"));
		if(tjblx.equalsIgnoreCase("myzyjxjmx")){
			
		}else if(tjblx.equalsIgnoreCase("myzyjxjhz")) {
			
		}else if(tjblx.equalsIgnoreCase("yxxsjxjffhz")) {
			
		}else if(tjblx.equalsIgnoreCase("yxxsjxjff")) {
			
		}else if(tjblx.equalsIgnoreCase("ndzyjxjff")) {
			
		}else if(tjblx.equalsIgnoreCase("yxjxjffhz")) {
			
		}
		return mapping.findForward("");
	}
	
	/**
	 * 优秀学生奖学金发放表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxxsjxjffb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXcxyModel model = new PjpyXcxyModel();
		String xydm = request.getParameter("xydm");
		model.setXydm(xydm);
		model.setXn(Base.getJxjsqxn());
		model.setXq(Base.getJxjsqxq());
		String modelPath = "";
		//统计报表类型
		String tjbbType = request.getParameter("tjblx");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if(tjbbType!=null&&tjbbType.equalsIgnoreCase("yxxsjxjffhz")){
			modelPath = servlet.getServletContext().getRealPath("")+"/print/xcxy/xcxy_yxxsjxjffhz.xls";
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.yxxsjxjffhzPrint(wwb, model);
		}else if(tjbbType!=null&&tjbbType.equalsIgnoreCase("yxxsjxjff")){
			modelPath = servlet.getServletContext().getRealPath("")+"/print/xcxy/yxxsjxjff.xls";
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.yxxsjxjffPrint(wwb, model);
		}else if(tjbbType!=null&&tjbbType.equalsIgnoreCase("ndzyjxjff")){
			modelPath = servlet.getServletContext().getRealPath("")+"/print/xcxy/ndzyjxjff.xls";
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.ndzyjxjffPrint(wwb, model);
		}else if(tjbbType!=null&&tjbbType.equalsIgnoreCase("yxjxjffhz")){
			modelPath = servlet.getServletContext().getRealPath("")+"/print/xcxy/yxjxjffhz.xls";
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.yxjxjffhzPrint(wwb, model);
		}else if(tjbbType!=null&&tjbbType.equalsIgnoreCase("myzyjxjmx")){
			modelPath = servlet.getServletContext().getRealPath("")+"/print/xcxy/myzyjxjmx.xls";
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.myzyjxjmxPrint(wwb, model);
		}else if(tjbbType!=null&&tjbbType.equalsIgnoreCase("myzyjxjhz")){
			modelPath = servlet.getServletContext().getRealPath("")+"/print/xcxy/myzyjxjhz.xls";
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.myzyjxjhzPrint(wwb, model);
		}else{
			modelPath = servlet.getServletContext().getRealPath("")+"/print/xcxy_yxxsjxjffb.xls";
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.yxxsjxjffbPrint(wwb, model);
		}
		return mapping.findForward("");
	}
	
	
	
	public ActionForward tjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//奖学金条件设置
		PjpyXcxyActionForm pjpyForm = (PjpyXcxyActionForm) form;
		String jxjdm = DealString.toGBK(pjpyForm.getJxjdm());
		HashMap<String, String> rs = service.getTjsz(jxjdm);
		request.setAttribute("rs", rs);
		appendJxjList(request);
		return mapping.findForward("tjsz");
	}
	
	public ActionForward tjszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//奖学金条件设置
		PjpyXcxyActionForm pjpyForm = (PjpyXcxyActionForm) form;
		PjpyXcxyModel model = new PjpyXcxyModel();
		BeanUtils.copyProperties(model, pjpyForm);
		boolean update = service.updateTjsz(model,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return tjsz(mapping,form,request,response);
	}
}
