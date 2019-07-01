package xgxt.gygl.tjfx;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.gygl.jbsz.GyglJbszService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_统计分析-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */

public class GyglTjfxAction extends BasicAction {

	/**
	 * 公寓管理_统计分析_精简统计_寝室床位统计
	 * 
	 * @author zhanghui
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjfxQscwtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTjfxService service = new GyglTjfxService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglTjfxForm myForm = (GyglTjfxForm) form;
		String doType= request.getParameter("doType");
		String searchType = "cwtj";		

		MessageResources message = getResources(request);
		
		//判断数据库中是否已有设置
		String sfsz = jbszModel.getSfsz();
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else {
			// 存在校区
			String czxq = jbszModel.getCzxq();
			// 存在园区
			String czyq = jbszModel.getCzyq();
			//分配对象
			String fpdx = jbszModel.getFpdx();
			if(!"njxy".equalsIgnoreCase(fpdx) && !"xy".equalsIgnoreCase(fpdx)){
				request.setAttribute("msg", "分配对象与统计对象不一致，本统计仅针对"+Base.YXPZXY_KEY+"统计！");
				return mapping.findForward("tjfxQscwtj");
			}else if("njxy".equalsIgnoreCase(fpdx)){
				searchType = "cwtj_nj";
			}
			int i= 5;
			if("是".equalsIgnoreCase(czxq)){
				i++;
			}
			if("是".equalsIgnoreCase(czyq)){
				i++;
			}
			request.setAttribute("ldnum", i);
			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			
			
		}


		String path = myForm.getSearchModel().getPath() + "&searchType="+searchType;
		myForm.getSearchModel().setPath(path);								
		//=============== 执行查询操作 ===========
		//是否查询操作
		boolean search = !"go".equalsIgnoreCase(request.getParameter("go")) ? false: true;
		List<String[]> rs= new ArrayList<String[]>();
		List<String[]> zj= new ArrayList<String[]>();
		List<HashMap<String,String>> xylist= new ArrayList<HashMap<String,String>>();
		if (search) {
			//结果集
			rs = service.getQscwtjList(myForm,request);
			zj = service.getQscwtjZjList(myForm,request);
			xylist = service.getXylist(myForm,request);
			request.setAttribute("searchTj", myForm.getSearchModel());				
		}
		

		if("exp".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.printQscwtj(myForm,message,request,response);
			return null;
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("zjrs", zj);
		request.setAttribute("xylist",xylist);
		
		request.setAttribute("searchType",searchType);		
		request.setAttribute("path", "gygl_tjfx_jjtj.do");
		
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tjfxQscwtj");
	}

	
	/**
	 * 公寓管理_统计分析_精简统计_寝室人员统计
	 * 
	 * @author zhanghui
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward tjfxQsrytj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTjfxService service = new GyglTjfxService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglTjfxForm myForm = (GyglTjfxForm) form;
		String doType= request.getParameter("doType");
		String searchType = "rytj";		

		MessageResources message = getResources(request);
		
		//判断数据库中是否已有设置
		String sfsz = jbszModel.getSfsz();
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else {
			// 存在校区
			String czxq = jbszModel.getCzxq();
			// 存在园区
			String czyq = jbszModel.getCzyq();
			int i= 1;
			if("是".equalsIgnoreCase(czxq)){
				i++;
			}
			if("是".equalsIgnoreCase(czyq)){
				i++;
			}
			request.setAttribute("ldnum", i);
			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);			
			
		}


		String path = myForm.getSearchModel().getPath() + "&searchType="+searchType;
		myForm.getSearchModel().setPath(path);								
		//=============== 执行查询操作 ===========
		//是否查询操作
		boolean search = !"go".equalsIgnoreCase(request.getParameter("go")) ? false: true;
		List<String[]> rs= new ArrayList<String[]>();
		List<String[]> zj= new ArrayList<String[]>();
		int maxcs = service.getMaxCs(myForm);
		if (search) {
			//结果集
			rs = service.getQsrytjList(myForm,request);
			zj = service.getQsrytjZjList(myForm,request);
			request.setAttribute("searchTj", myForm.getSearchModel());				
		}
		

		if("exp".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.printQsrytj(myForm,message,request,response);
			return null;
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("zjrs", zj);
		request.setAttribute("csnum", maxcs);
		
		request.setAttribute("searchType",searchType);		
		request.setAttribute("path", "gygl_tjfx_jjtj.do");
		
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tjfxQsrytj");
	}
	
	
	// =======================================以下BY qlj=================================================
	/**
	 * 公寓管理_统计分析_精简统计
	 * 宿舍年级分布情况统计
	 *  Method tjfxNjfbtj
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward tjfxNjfbtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTjfxService service = new GyglTjfxService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglTjfxForm myForm = (GyglTjfxForm) form;
		String doType=request.getParameter("doType");
		
		//判断数据库中是否已有设置
		String sfsz = jbszModel.getSfsz();
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		myForm.setCzxq(jbszModel.getCzxq());
		myForm.setCzyq(jbszModel.getCzyq());
								
		//=============== 执行查询操作 ===========
		//是否查询操作
		if("query".equalsIgnoreCase(doType)){	
			myForm.getSearchModel().setPath("gygl_tjfx_jjtj.do&searchType=ssnjfb");
			request.setAttribute("tjxxList", service.getNjfbtjBg(myForm, request));
		}
		
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("searchType", "ssnjfb");
		request.setAttribute("path", "gygl_tjfx_jjtj.do");
		request.setAttribute("searchTj", myForm.getSearchModel());
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tjfxNjfbtj");
	}
	
	/** 
	 * Method printNjfbtj
	 * 宿舍年级分布情况统计导出表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * 
	 */
	public ActionForward printNjfbtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GyglTjfxService service=new GyglTjfxService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglTjfxForm myForm = (GyglTjfxForm) form;
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/gygl/gygl_ssnjfbtj.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		myForm.setCzxq(jbszModel.getCzxq());
		myForm.setCzyq(jbszModel.getCzyq());
		
		myForm.getSearchModel().setPath("gygl_tjfx_jjtj.do&searchType=ssnjfb");
		service.printNjfbtj(myForm, request, wwb);
		return null;
	}
}
