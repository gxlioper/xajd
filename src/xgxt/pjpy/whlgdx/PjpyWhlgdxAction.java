/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.pjpy.whlgdx;

import java.io.File;
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
import xgxt.base.Excel2Oracle;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 武汉理工大学评奖评优Action
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-01</p>
 */
public class PjpyWhlgdxAction extends DispatchAction {
	
	/**
	 * 公用方法:在REQUEST中存放页面所要加载的属性
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		@SuppressWarnings("unused")
		String writeAble = Base.getWriteAble(request);
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
	 * 公用方法:在REQUEST中存放页面所要加载的属性
	 * @param request
	 * @param model
	 * @return void
	 */
	public void appendProperties(HttpServletRequest request,WhlgdxXjbjModel model){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		String xy = "";
		String zy = "";
		String njLocal = model.getNj();
		xy = xy==null ? "": (model.getXydm()==null ? "" :model.getXydm()); 
		zy = zy==null ? "": (model.getZydm()==null ? "" : model.getZydm()); 
		njLocal = model.getNj()==null ? "": model.getNj();
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		@SuppressWarnings("unused")
		String writeAble = Base.getWriteAble(request);
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xjbjlbList", dao.getXjbjlbList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("userType", userType);//用户类型
	}
	/** 
	 * Method 综合素质测评信息查询页面显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward zhszcpSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
			WhlgdxZhszcpModel zhszcpModel = new WhlgdxZhszcpModel();
			BeanUtils.copyProperties(zhszcpModel, pjpyForm);
			String xydm = zhszcpModel.getXydm();//学院代码
			String zydm = zhszcpModel.getZydm();//专业代码
			String nj = zhszcpModel.getNj();//年级
			@SuppressWarnings("unused")
			String writeAble = Base.getWriteAble(request);
			
			request.setAttribute("tableName", "view_zhszcp");//视图名
			request.setAttribute("realTable", "zhszcp");//表名
			appendProperties(request, xydm, zydm, nj);
		   return mapping.findForward("zhszcp_search");
	}
	
	/** 
	 * Method 综合素质测评信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward zhszcpSearchInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
			WhlgdxZhszcpModel zhszcpModel = new WhlgdxZhszcpModel();
			BeanUtils.copyProperties(zhszcpModel, pjpyForm);
			PjpyWhlgdxService service = new PjpyWhlgdxService();
			String xydm = zhszcpModel.getXydm();
			String zydm = zhszcpModel.getZydm();
			String nj = zhszcpModel.getNj();
			List<HashMap<String, String>> topList = service.getZhszcpTitle();//查询表头
			List<String[]> resList = service.getZhszcpRs(zhszcpModel);//查询结果
			
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
			request.setAttribute("tableName", "view_zhszcp");//视图名
			request.setAttribute("realTable", "zhszcp");//表名
			appendProperties(request, xydm, zydm, nj);
			return mapping.findForward("zhszcp_search");
	}
	
	/**
	 * 综合素质测评单个增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward zhszcpAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		String xh = request.getParameter("xh");
		String xydm = pjpyForm.getXydm();
		String zydm = pjpyForm.getZydm();
		String nj = pjpyForm.getNj();
		
		xh = !StringUtils.isNull(xh) ? xh : "";
		HashMap<String, String> resMap = service.getStuInfo(xh);//获取学生相关信息
		if (resMap == null) {
			resMap.put("stuExists", "no");//不存在该学生信息
		}else {
			resMap.put("stuExists", "yes");//存在该学生信息
		}
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcp_add");
	}
	
	/**
	 * 综合素质测评单个保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward zhszcpSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxForm whlgForm = (PjpyWhlgdxForm) form;
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		WhlgdxZhszcpModel zhszcpModel = new WhlgdxZhszcpModel();
		BeanUtils.copyProperties(zhszcpModel, whlgForm);
		String xh = request.getParameter("xh");
		xh = !StringUtils.isNull(xh) ? xh : "";
		
		String xydm = zhszcpModel.getXydm();
		String zydm = zhszcpModel.getZydm();
		String nj = zhszcpModel.getNj();
				
		HashMap<String, String> resMap = service.getStuInfo(xh);//获取学生相关信息
		if (resMap == null) {
			resMap.put("stuExists", "no");//不存在该学生信息
		}else {
			resMap.put("stuExists", "yes");//存在该学生信息
		}
		whlgForm.setXh(xh);		
		boolean bFlag = service.zhszcpSave(zhszcpModel, request);//数据保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcp_detail");
	}
	
	/**
	 * 综合素质测评批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward zhszcpDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxForm whlgForm = (PjpyWhlgdxForm) form;
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		String[] key = whlgForm.getCbv();
		String xydm = whlgForm.getXydm();
		String zydm = whlgForm.getZydm();
		String nj = whlgForm.getNj();
		String result = service.zhszcpDel(key, request);//信息批量删除
		
		request.setAttribute("result", result);
		request.setAttribute("tableName", "view_zhszcp");//视图名
		request.setAttribute("realTable", "zhszcp");//表名
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcp_search");
	}
	
	/**
	 * 综合素质测评单个信息修改信息显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward showZhcpInfo(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxForm whlgForm = (PjpyWhlgdxForm) form;
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		String pkValue = request.getParameter("pkValue");
		String type = request.getParameter("type");
		String xydm = whlgForm.getXydm();
		String zydm = whlgForm.getZydm();
		String nj = whlgForm.getNj();
		
		type = !StringUtils.isNull(type) ? type : "";
		HashMap<String, String> resMap = service.getZhszcpInfoByPk(pkValue);//获取相关信息
		if (StringUtils.isEqual(type, "modi")) {//修改显示
			request.setAttribute("updated", "yes");
		} else {//只显示
			request.setAttribute("updated", "no");
		}
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcp_detail");
	}
	
	/**
	 * 综合素质测评数据导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward zhszcpExp(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		WhlgdxZhszcpModel model = new WhlgdxZhszcpModel();
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xq = request.getParameter("xq");
		String xh = request.getParameter("xh");
		
		model.setXydm(xydm);
		model.setZydm(zydm);
		model.setBjdm(bjdm);
		model.setNj(nj);
		model.setXn(xn);
		model.setNd(nd);
		model.setXq(xq);
		model.setXh(xh);
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")+"/print/zhszcpwhlgdx.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.printZhszcp(wwb,model);//XLS报表输出
		return mapping.findForward("");	
	}
	
	/** 
	 * Method 先进班级信息查询页面显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward xjbjSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
			WhlgdxXjbjModel xjbjModel = new WhlgdxXjbjModel();
			BeanUtils.copyProperties(xjbjModel, pjpyForm);
			
			request.setAttribute("tableName", "view_pjpy_xjbj");//视图名
			request.setAttribute("realTable", "pjpy_xjbjandwmsqb");//表名
			appendProperties(request, xjbjModel);
		    return mapping.findForward("xjbj_search");
	}
	
	/** 
	 * Method 先进班级信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward xjbjSearchInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
			PjpyWhlgdxService service = new PjpyWhlgdxService();
			WhlgdxXjbjModel xjbjModel = new WhlgdxXjbjModel();
			BeanUtils.copyProperties(xjbjModel, pjpyForm);
			xjbjModel.setUserName(request.getSession().getAttribute("userName").toString());
			String isFdy = request.getSession().getAttribute("isFdy").toString();
			if(isFdy!=null && isFdy.equalsIgnoreCase("true")){
				xjbjModel.setFdy(true);
			}else{
				xjbjModel.setFdy(false);
			}
			
			List<HashMap<String, String>> topList = service.getXjbjTitle();//查询表头
			List<String[]> resList = service.getXjbjRs(xjbjModel);//查询结果
			
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
			request.setAttribute("tableName", "view_pjpy_xjbj");//视图名
			request.setAttribute("realTable", "pjpy_xjbjandwmsqb");//表名
			appendProperties(request, xjbjModel);
			return mapping.findForward("xjbj_search");
	}
	
	/**
	 * 先进班级单个增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjbjAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		WhlgdxXjbjModel xjbjModel = new WhlgdxXjbjModel();
		//BeanUtils.copyProperties(xjbjModel, pjpyForm);
		pjpyForm.setBj("");
		pjpyForm.setBjmc("");
		pjpyForm.setBjdm("");
		request.setAttribute("updated", "yes");
		request.setAttribute("rs", xjbjModel);
		appendProperties(request, xjbjModel);
		return mapping.findForward("xjbj_add");
	}
	
	/**
	 * 先进班级单个保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjbjSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxForm whlgForm = (PjpyWhlgdxForm) form;
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		WhlgdxXjbjModel xjbjModel = new WhlgdxXjbjModel();
		BeanUtils.copyProperties(xjbjModel, whlgForm);
						
		boolean bFlag = service.xjbjSave(xjbjModel, request);//数据保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		
		appendProperties(request,xjbjModel);
		return mapping.findForward("xjbj_detail");
	}
	
	/**
	 * 先进班级批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjbjDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxForm whlgForm = (PjpyWhlgdxForm) form;
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		WhlgdxXjbjModel xjbjModel = new WhlgdxXjbjModel();
		BeanUtils.copyProperties(xjbjModel, whlgForm);
		String[] key = whlgForm.getCbv();
		String result = service.xjbjDel(key, request);//信息批量删除
		
		request.setAttribute("result", result);
		request.setAttribute("tableName", "view_pjpy_xjbj");//视图名
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");//表名
		appendProperties(request,xjbjModel);
		return mapping.findForward("xjbj_search");
	}
	
	/**
	 * 先进班级单个信息修改信息显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward showXjbjInfo(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxForm whlgForm = (PjpyWhlgdxForm) form;
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		WhlgdxXjbjModel xjbjModel = new WhlgdxXjbjModel();
		BeanUtils.copyProperties(xjbjModel, whlgForm);
		String pkValue = request.getParameter("pkValue");
		String type = request.getParameter("type");
		
		type = !StringUtils.isNull(type) ? type : "";
		HashMap<String, String> resMap = service.getXjbjInfoByPk(pkValue);//获取相关信息
		if (StringUtils.isEqual(type, "modi")) {//修改显示
			request.setAttribute("updated", "yes");
		} else {//只显示
			request.setAttribute("updated", "no");
		}
		request.setAttribute("rs", resMap);
		appendProperties(request, xjbjModel);
		return mapping.findForward("xjbj_detail");
	}
	
	/**
	 *先进班级数据导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjbjExp(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		WhlgdxXjbjModel model = new WhlgdxXjbjModel();
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		
		model.setXydm(xydm);
		model.setZydm(zydm);
		model.setBjdm(bjdm);
		model.setNj(nj);
		model.setXn(xn);
		model.setXq(xq);
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")+"/print/xjbjwhlgdx.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.printXjbj(wwb,model);//XLS报表输出
		return mapping.findForward("");	
	}
	
	/**
	 *参数设置-->名额分配表 显示所选学院的已分配信息  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward viewFpb(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();		
		String xydm = request.getParameter("xydm");
		HashMap<String, String> ndxy = service.getNdAndXyxx(xydm);;
		
		String jxjje = service.getJxjjeOfXy(xydm,ndxy.get("jxjsqnd"));
		
		request.setAttribute("fpbList", service.getFpxxByXy(xydm,ndxy.get("jxjsqnd")));
		request.setAttribute("jxjje", jxjje);
		request.setAttribute("ndxy", ndxy);
		return mapping.findForward("viewFpb");
	}
	
	/**
	 *条件设置  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward tjssInit(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		String jxjdm = pjpyForm.getJxjdm();
		String jxjfl = DealString.toGBK(request.getParameter("jxjfl"));
		String tableName = pjpyForm.getXmdm();
		if(tableName!=null && !"".equalsIgnoreCase(tableName)){
//		if (!StringUtils.isNull(jxjdm)) {
			List<HashMap<String, String>> topList = service.getJxjsztjTitle();
			List<String[]> jxjsztjList = service.getJxjsztj(pjpyForm.getXn(), jxjdm, jxjfl, tableName);
			request.setAttribute("rs", jxjsztjList);
			request.setAttribute("topTr", topList);
		}
		pjpyForm.setZdm("");
		request.setAttribute("jxjflList", service.getJxjflList(tableName));//加载奖学金分类列表
		request.setAttribute("jxjList", service.getJxjList(jxjfl,tableName));//加载奖学金列表
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("tjssInit");
	}
	
	
	/**
	 * 显示条件设置增加页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward showTjszAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		 
		String jxjdm = request.getParameter("jxjdm");
		String xmdm = request.getParameter("xmdm");
		String jxjfl = request.getParameter("jxjfl");
		xmdm = xmdm==null ? "" : xmdm;
		jxjfl = jxjfl==null || "".equals(jxjfl) || jxjfl.equals("null") ? "" : DealString.toGBK(jxjfl);
		jxjdm = jxjdm==null ? "" : jxjdm;
		String jxjmc = "";
		String xmmc = "";
		if(xmdm!=null && !xmdm.equals("") && xmdm.equals("jxjdmb")){
			xmmc = "奖学金";
		}
		if(xmdm!=null && !xmdm.equals("") && xmdm.equals("rychdmb")){
			xmmc = "荣誉称号";
		}
		
		jxjmc = service.getJxjmc(xmdm,jxjdm);
		jxjmc = jxjmc==null || jxjmc.equals("")? "全部" : jxjmc;
		
		service.initForm(pjpyForm);
		
		request.setAttribute("jxjmc", jxjmc);
		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("jxjfl", jxjfl);
		return mapping.findForward("tjszAdd");
	}
	
	/**
	 * 条件设置增加操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * **/
	public ActionForward tjszAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		String xn = service.getNdAndXyxx("").get("jxjsqxn");
		pjpyForm.setXn(xn);
		String tableName = request.getParameter("xmdm");
		
		request.setAttribute("result", service.saveTjsz(pjpyForm,tableName));
		return this.showTjszAdd(mapping, form, request, response);
	}
	
	/**
	 * 条件设置删除操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward tjszDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String tableName = request.getParameter("tableName");
		
		request.setAttribute("result", service.deleteTjsz(tableName,pkValue,request));
		return this.tjssInit(mapping, form, request, response);
	}
	
	/**
	 * 奖学金申请页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward jxjApply(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		WhlgJxjModel model = new WhlgJxjModel();
		BeanUtils.copyProperties(pjpyForm, model);	
		HashMap<String, String> map = new HashMap<String, String>();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "jxjdmb";
		String jxjfl = "";
		String doType = request.getParameter("act");
		
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String nj = model.getNj();
		String xh = request.getParameter("xh");
		HashMap< String, String > ndxnxqMap = service.getNdAndXyxx(xydm);//奖学金申请学年年度等
		String xn = ndxnxqMap.get("jxjsqxn");
		String xq = ndxnxqMap.get("jxjsqxq");
		String nd = ndxnxqMap.get("jxjsqnd");
		
		if(doType!=null && doType.equals("modi")){
			String pk = request.getParameter("pk");
			String pkValue = request.getParameter("pkValue");
			map.putAll(service.getXsjxjInfo(pk,pkValue));
			xh = map.get("xh");	
			xn = map.get("xn");
			nd = map.get("nd");
			xq = map.get("xq");
		}
		
		if(userOnLine!=null && userOnLine.equals("student")){//学生用户登录是学号为用户名
			xh = userName;
		}
		
		map.putAll(service.getStuZhszcpxx(xh,xn,xq));//学生综合素质测评
		map.putAll(service.getStuInfo(xh));//学生基本信息
		map.put("xn", xn);
		map.put("nd", xq);		
		map.put("nd", nd);
		map.put("xq",ndxnxqMap.get("jxjsqxq"));
		map.put("stuExists", "yes");
		map.put("sfpks", service.isKns(xh) ? "是" : "否");//是否贫困生
		
		request.setAttribute("rs", map);
		request.setAttribute("doType", doType);
		request.setAttribute("jxjflList", service.getJxjflList(tableName));//奖学金分类列表
		request.setAttribute("jxjList", service.getJxjList(jxjfl, tableName));
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("jxjApply");
	}
	
	/**
	 * 申请奖学金保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward jxjsqSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		WhlgJxjModel model = new WhlgJxjModel();
		BeanUtils.copyProperties(model,pjpyForm);
		String tjFlag = request.getParameter("tjFlag");
		model.setTjFlag(tjFlag);
		
		request.setAttribute("result", service.saveJxjsq(model,request));
		
		return jxjApply(mapping, form, request, response);
	}
	
	/**
	 * 显示奖学金单个审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward showCheckPrise(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		String pkValue = request.getParameter("pkVal");
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		userType = userType!=null && userType.equals("admin") ? "xx" : userType;
		userType = isFdy!=null && isFdy.equals("true") ? "fdy" : userType;
		
		request.setAttribute("userType", userType);
		request.setAttribute("rs", service.getXsjxjCheckInfo(pkValue,userType));
		request.setAttribute("chkList", service.getChkList(3));
		return mapping.findForward("prise_check_one");
	}
	
	/**
	 * 奖学金审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward checkPriseSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		WhlgJxjModel model = new WhlgJxjModel();
		boolean flag = false;
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		userType = userType!=null && userType.equals("admin") ? "xx" : userType;
		userType = isFdy!=null && isFdy.equals("true") ? "fdy" : userType;
		String yesNo = request.getParameter("yesNo");
		String pkValue = request.getParameter("pkVal");
		String fdyyj = request.getParameter("fdyyj");
		String xxshyj = request.getParameter("xxyj");
		String xyshyj = request.getParameter("xyyj");
		
		model.setUserType(userType);
		model.setPkVal(pkValue);
		model.setYesNo(yesNo);
		model.setFdyyj(fdyyj);
		model.setXxshyj(xxshyj);
		model.setXyshyj(xyshyj);
		
		map = service.getXsjxjCheckInfo(pkValue, userType);
		model.setXh(map.get("xh"));
		model.setJxjdm(map.get("jxjdm"));
		model.setXn(map.get("xn"));
		model.setNd(map.get("nd"));
		model.setXq(map.get("xq"));
		model.setUserType(userType);
		
		String checkInfo  = service.getCheckResult(map.get("xh"), map.get("jxjdm"),"jxjdmb");//综合素质测评条件检测
		
		yesNo = DealString.toGBK(yesNo);
		if(yesNo!=null && yesNo.equals("通过")){
			int tgrs = service.checkPersonNumber(model);
			if(tgrs<=0){//人数限制检测超限
				request.setAttribute("tgres", "审核通过人数已达到或超过限制人数!超过人数：" + String.valueOf(tgrs).substring(1, String.valueOf(tgrs).length()));
			}else{
				if(checkInfo==null || checkInfo.equals("") || checkInfo.equals(" ")){//学生的综合素质测评条件符号要求
					flag = true;
				}else{
					request.setAttribute("tgres", checkInfo + "不符合规定，无法通过！");
				}
				if(flag){
					flag = service.saveCheckPrise(model,request);
				}
			}
		}else{
			flag = service.saveCheckPrise(model,request);
		}
		
		request.setAttribute("result", flag);		
		return showCheckPrise(mapping, form, request, response);
	}
	
	/**
	 * 荣誉称号申请页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward rychApply(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		WhlgJxjModel model = new WhlgJxjModel();
		BeanUtils.copyProperties(pjpyForm, model);	
		HashMap<String, String> map = new HashMap<String, String>();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "rychdmb";
		String rychfl = "";
		String doType = request.getParameter("act");
		
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String nj = model.getNj();
		String xh = request.getParameter("xh");
		HashMap< String, String > ndxnxqMap = service.getNdAndXyxx(xydm);//奖学金申请学年年度等
		String xn = ndxnxqMap.get("jxjsqxn");
		String xq = ndxnxqMap.get("jxjsqxq");
		String nd = ndxnxqMap.get("jxjsqnd");
		
		if(doType!=null && doType.equals("modi")){
			String pk = request.getParameter("pk");
			String pkValue = request.getParameter("pkValue");
			map.putAll(service.getXsjxjInfo(pk,pkValue));
			xh = map.get("xh");	
			xn = map.get("xn");
			nd = map.get("nd");
			xq = map.get("xq");
		}
		
		if(userOnLine!=null && userOnLine.equals("student")){//学生用户登录是学号为用户名
			xh = userName;
		}
		
		map.putAll(service.getStuZhszcpxx(xh,xn,xq));//学生综合素质测评
		map.putAll(service.getStuInfo(xh));//学生基本信息
		map.put("xn", xn);
		map.put("nd", xq);		
		map.put("nd", nd);
		map.put("xq", ndxnxqMap.get("jxjsqxq"));
		map.put("stuExists", "yes");
		
		request.setAttribute("rs", map);
		request.setAttribute("doType", doType);
		request.setAttribute("chkList", service.getChkList(2));
		request.setAttribute("jxjflList", service.getJxjflList(tableName));//奖学金分类列表
		request.setAttribute("rychList", service.getJxjList(rychfl, tableName));
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("rychApply");
	}
	
	/**
	 * 荣誉称号保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward rychsqSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		WhlgJxjModel model = new WhlgJxjModel();
		BeanUtils.copyProperties(model,pjpyForm);
		
		request.setAttribute("result", service.saveRychsq(model,request));
		
		return rychApply(mapping, form, request, response);
	}
	
	/**
	 * 显示荣誉称号审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward showCheckRych(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		userType = userType.equalsIgnoreCase("admin")&&userType!=null ? "xx" : userType;
		userType = isFdy!=null && isFdy.equalsIgnoreCase("true") ? "fdy" : userType;
		
		String pkValue = request.getParameter("pkVal");
		String pk = "xn||nd||xh||rychdm";
		
		request.setAttribute("rs", service.getXsrychInfo(pk,pkValue,userType));
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("userType", userType);
		return mapping.findForward("rychCheck");
	}
	
	/**
	 * 荣誉称号审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward checkRychSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		WhlgJxjModel model = new WhlgJxjModel();
		boolean flag = false;
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		userType = userType!=null && userType.equals("admin") ? "xx" : userType;
		userType = isFdy!=null && isFdy.equals("fdy") ? "fdy" : userType;
		
		String pk = "xn||nd||xh||rychdm";
		String yesNo = request.getParameter("yesNo");
		String pkValue = request.getParameter("pkVal");
		String fdyyj = request.getParameter("fdyyj");
		String xxshyj = request.getParameter("xxyj");
		String xyshyj = request.getParameter("xyyj");
		
		model.setUserType(userType);
		model.setPkVal(pkValue);
		model.setYesNo(yesNo);
		model.setFdyyj(fdyyj);
		model.setXxshyj(xxshyj);
		model.setXyshyj(xyshyj);
		
		map = service.getXsrychInfo(pk,pkValue, userType);
		model.setXh(map.get("xh"));
		model.setJxjdm(map.get("jxjdm"));
		model.setXn(map.get("xn"));
		model.setNd(map.get("nd"));
		model.setXq(map.get("xq"));
		model.setUserType(userType);
		
		String checkInfo  = service.getCheckResult(map.get("xh"), map.get("rychdm"),"rychdmb");//综合素质测评条件检测
		
		yesNo = DealString.toGBK(yesNo);
		if(yesNo!=null && yesNo.equals("通过")){
			if(checkInfo==null || checkInfo.equals("")){//学生的综合素质测评条件符号要求
				flag = true;
			}else{
				request.setAttribute("tgres", checkInfo + "不符合规定，无法通过！");
			}
			if(flag){
				flag = service.saveCheckRych(model,request);//保存审核结果
			}
		}else{
			flag = service.saveCheckRych(model,request);//保存审核结果
		}
		
		request.setAttribute("result", flag);		
		return showCheckPrise(mapping, form, request, response);
	}
	
	/**
	 * 学生荣誉称号批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward checkRychAll(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();		
		userType = userType.equalsIgnoreCase("admin") ? "xx" : userType;
		userType = isFdy!=null && isFdy.equalsIgnoreCase("true") ? "fdy" : userType;
		String[] pkValue = pjpyForm.getCbv();
		String yesNo = request.getParameter("yesNo");
		
		request.setAttribute("result", service.checkRychAll(pkValue,userType,yesNo,request));
		return new ActionForward("/credit_check.do");
	}
	
	/**
	 * 学生奖学金按分类导出数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward priseExport(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		WhlgJxjModel model = new WhlgJxjModel();
		BeanUtils.copyProperties(model, pjpyForm);
		model.setJxjdm(pjpyForm.getXmdm());
		String tabName = "view_xsjxjb";
		String sql = service.getPriseExpSql(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(sql, tabName, response.getOutputStream());		
		return mapping.findForward("");
	}
	
	
	/**
	 * 学生荣誉称号按分类导出数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward rychExport(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyWhlgdxService service = new PjpyWhlgdxService();
		PjpyWhlgdxForm pjpyForm = (PjpyWhlgdxForm) form;
		WhlgJxjModel model = new WhlgJxjModel();
		BeanUtils.copyProperties(model, pjpyForm);
		String tabName = "view_xsrychb";
		model.setRychdm(pjpyForm.getXmdm());
		
		String sql = service.getRychExpSql(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(sql, tabName, response.getOutputStream());		
		return mapping.findForward("");
	}
}