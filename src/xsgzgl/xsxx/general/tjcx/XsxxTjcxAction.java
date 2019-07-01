package xsgzgl.xsxx.general.tjcx;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;

public class XsxxTjcxAction extends BasicAction {

	private static final String NJ_TJCX = "njTjcx";
	private static final String XY_TJCX = "xyTjcx";
	private static final String ZY_TJCX = "zyTjcx";
	private static final String BJ_TJCX = "bjTjcx";
	private static final String XS_TJCX = "xsTjcx";
	private static final String NJXY_TJCX = "njxyTjcx";
	private static final String XYZY_TJCX = "xyzyTjcx";
	private static final String ZYBJ_TJCX = "zybjTjcx";
	private static final String BJXS_TJCX = "bjxsTjcx";
	
	/**
	 * 按年级
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjcxByNj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxTjcxService service = new XsxxTjcxService(); 
		User user = getUser(request);
		List<HashMap<String,String>> rsList = service.getTjcxByNj(user);
		
		request.setAttribute("rsList", rsList);
		return mapping.findForward(NJ_TJCX);
	}
	
	
	
	/**
	 * 按年级学院统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjcxByNjXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		XsxxTjcxService service = new XsxxTjcxService(); 
		XsxxTjcxForm model = (XsxxTjcxForm) form;
		String nj = model.getNj();
		
		List<HashMap<String,String>> rsList = service.getTjcxByNjXy(nj,user);
		Map<String,String> zyzsMap = service.getZyzsByXy(nj,user);
		Map<String,String> bjzsMap = service.getBjzsByXy(nj,user);
		
		
		request.setAttribute("zyzsMap", zyzsMap);
		request.setAttribute("bjzsMap", bjzsMap);
		request.setAttribute("rsList", rsList);
		
		//FormModleCommon.setNjXyZyBjList(request);
		
		List<HashMap<String,String>> njList = service.getNjList(user);
		List<HashMap<String,String>> xyList = service.getXyList(user);
		List<HashMap<String,String>> zyList = new ArrayList<HashMap<String,String>>();
		
		if (!StringUtil.isNull(model.getXydm())){
			String[] xyArr = model.getXydm().split("!!@!!");
			for (String xydm : xyArr){
				zyList.addAll(Base.getZyMap().get(xydm));
			}
		} else {
			zyList.addAll(Base.getZyMap().get(""));
		}
		
		request.setAttribute("njList", njList);
		request.setAttribute("xyList", xyList);
		request.setAttribute("zyList", zyList);
		
		return mapping.findForward(NJXY_TJCX);
	}
	
	
	
	/**
	 * 按专业统计查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjcxByZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxTjcxService service = new XsxxTjcxService(); 
		XsxxTjcxForm model = (XsxxTjcxForm) form;
		User user = getUser(request);
		
		List<HashMap<String,String>> rsList = service.getTjcxByZy(model,user);
		Map<String,String> bjzsMap = service.getBjzsByZy(model,user);
		
		request.setAttribute("bjzsMap", bjzsMap);
		request.setAttribute("rsList", rsList);
		
		//FormModleCommon.setNjXyZyBjList(request);
		List<HashMap<String,String>> njList = service.getNjList(user);
		List<HashMap<String,String>> xyList = service.getXyList(user);
		List<HashMap<String,String>> zyList = new ArrayList<HashMap<String,String>>();
		
		if (!StringUtil.isNull(model.getXydm())){
			String[] xyArr = model.getXydm().split("!!@!!");
			for (String xydm : xyArr){
				zyList.addAll(Base.getZyMap().get(xydm));
			}
		} else {
			zyList.addAll(Base.getZyMap().get(""));
		}
		
		request.setAttribute("njList", njList);
		request.setAttribute("xyList", xyList);
		request.setAttribute("zyList", zyList);
		return mapping.findForward(ZY_TJCX);
	}
	
	
	
	/**
	 * 按班级统计 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjcxByBj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxTjcxService service = new XsxxTjcxService(); 
		XsxxTjcxForm model = (XsxxTjcxForm) form;
		User user = getUser(request);
		List<HashMap<String,String>> rsList = service.getTjcxByBj(model);
		
		request.setAttribute("rsList", rsList);
		
		List<HashMap<String,String>> njList = service.getNjList(user);
		List<HashMap<String,String>> xyList = service.getXyList(user);
		List<HashMap<String,String>> zyList = new ArrayList<HashMap<String,String>>();
		
		if (!StringUtil.isNull(model.getXydm())){
			String[] xyArr = model.getXydm().split("!!@!!");
			for (String xydm : xyArr){
				zyList.addAll(Base.getZyMap().get(xydm));
			}
		} else {
			zyList.addAll(Base.getZyMap().get(""));
		}
		
		request.setAttribute("njList", njList);
		request.setAttribute("xyList", xyList);
		request.setAttribute("zyList", zyList);
		
		return mapping.findForward(BJ_TJCX);
	}
	
	
	/**
	 * 按班级统计学生
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjxsByBjdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxTjcxService service = new XsxxTjcxService(); 
		XsxxTjcxForm model = (XsxxTjcxForm) form;
		request.setAttribute("rsList", service.tjxsByBjdm(model));
		return mapping.findForward(XS_TJCX);
	}
	
	
	
	
	
	/**
	 * 按学院
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjcxByXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxTjcxService service = new XsxxTjcxService(); 
		User user = getUser(request);
		List<HashMap<String,String>> rsList = service.getTjcxByXy(user);
		Map<String,String> zyzsMap = service.getZyzsByXy(user);
		Map<String,String> bjzsMap = service.getBjzsByXy(user);
		
		request.setAttribute("zyzsMap", zyzsMap);
		request.setAttribute("bjzsMap", bjzsMap);
		request.setAttribute("rsList", rsList);
		return mapping.findForward(XY_TJCX);
	}
	
	/**
	 * 点击学院统计专业
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjcxByXyZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxTjcxService service = new XsxxTjcxService(); 
		XsxxTjcxForm model = (XsxxTjcxForm) form;
		User user = getUser(request);
		List<HashMap<String,String>> rsList = service.getTjcxByZy(model,user);
		Map<String,String> bjzsMap = service.getBjzsByZy(model,user);
		
		request.setAttribute("bjzsMap", bjzsMap);
		request.setAttribute("rsList", rsList);
		
		//FormModleCommon.setNjXyZyBjList(request);
		List<HashMap<String,String>> njList = service.getNjList(user);
		List<HashMap<String,String>> xyList = service.getXyList(user);
		List<HashMap<String,String>> zyList = new ArrayList<HashMap<String,String>>();
		
		if (!StringUtil.isNull(model.getXydm())){
			String[] xyArr = model.getXydm().split("!!@!!");
			for (String xydm : xyArr){
				zyList.addAll(Base.getZyMap().get(xydm));
			}
		} else {
			zyList.addAll(Base.getZyMap().get(""));
		}
		
		request.setAttribute("njList", njList);
		request.setAttribute("xyList", xyList);
		request.setAttribute("zyList", zyList);
		return mapping.findForward(XYZY_TJCX);
	}
	
	/**
	 * 点击专业按班级统计 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjcxByXyZyBj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxTjcxService service = new XsxxTjcxService(); 
		XsxxTjcxForm model = (XsxxTjcxForm) form;
		User user = getUser(request);
		List<HashMap<String,String>> rsList = service.getTjcxByBj(model);
		
		request.setAttribute("rsList", rsList);
		
		List<HashMap<String,String>> xyList = service.getXyList(user);
		List<HashMap<String,String>> zyList = new ArrayList<HashMap<String,String>>();
		
		if (!StringUtil.isNull(model.getXydm())){
			String[] xyArr = model.getXydm().split("!!@!!");
			for (String xydm : xyArr){
				zyList.addAll(Base.getZyMap().get(xydm));
			}
		} else {
			zyList.addAll(Base.getZyMap().get(""));
		}
		
		request.setAttribute("xyList", xyList);
		request.setAttribute("zyList", zyList);
		
		return mapping.findForward(ZYBJ_TJCX);
	}
	
	/**
	 * 按班级统计学生
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjxsByXyZyBjXs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxTjcxService service = new XsxxTjcxService(); 
		XsxxTjcxForm model = (XsxxTjcxForm) form;
		request.setAttribute("rsList", service.tjxsByBjdm(model));
		return mapping.findForward(BJXS_TJCX);
	}
	
	
	/**
	 * 按结果导出
	 */
	public ActionForward dcsjByType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type");
		User user = getUser(request);
		XsxxTjcxService service = new XsxxTjcxService();
		XsxxTjcxForm myForm = (XsxxTjcxForm) form;
		response.reset();
		response.resetBuffer();
		OutputStream ops = null;
		ops = response.getOutputStream();
		String fileName   =   new   String("tjjg.xls".getBytes("GBK"),"iso8859-1");  
	 	response.reset();
		response.setContentType("application/vnd.ms-excel"); 
		response.addHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
		service.getDcsjList(myForm, ops,type,user);
		return null;
	}
	
	
}
