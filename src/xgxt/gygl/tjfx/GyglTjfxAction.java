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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_ͳ�Ʒ���-action��
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
	 * ��Ԣ����_ͳ�Ʒ���_����ͳ��_���Ҵ�λͳ��
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
		
		//�ж����ݿ����Ƿ���������
		String sfsz = jbszModel.getSfsz();
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else {
			// ����У��
			String czxq = jbszModel.getCzxq();
			// ����԰��
			String czyq = jbszModel.getCzyq();
			//�������
			String fpdx = jbszModel.getFpdx();
			if(!"njxy".equalsIgnoreCase(fpdx) && !"xy".equalsIgnoreCase(fpdx)){
				request.setAttribute("msg", "���������ͳ�ƶ���һ�£���ͳ�ƽ����"+Base.YXPZXY_KEY+"ͳ�ƣ�");
				return mapping.findForward("tjfxQscwtj");
			}else if("njxy".equalsIgnoreCase(fpdx)){
				searchType = "cwtj_nj";
			}
			int i= 5;
			if("��".equalsIgnoreCase(czxq)){
				i++;
			}
			if("��".equalsIgnoreCase(czyq)){
				i++;
			}
			request.setAttribute("ldnum", i);
			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			
			
		}


		String path = myForm.getSearchModel().getPath() + "&searchType="+searchType;
		myForm.getSearchModel().setPath(path);								
		//=============== ִ�в�ѯ���� ===========
		//�Ƿ��ѯ����
		boolean search = !"go".equalsIgnoreCase(request.getParameter("go")) ? false: true;
		List<String[]> rs= new ArrayList<String[]>();
		List<String[]> zj= new ArrayList<String[]>();
		List<HashMap<String,String>> xylist= new ArrayList<HashMap<String,String>>();
		if (search) {
			//�����
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
	 * ��Ԣ����_ͳ�Ʒ���_����ͳ��_������Աͳ��
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
		
		//�ж����ݿ����Ƿ���������
		String sfsz = jbszModel.getSfsz();
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else {
			// ����У��
			String czxq = jbszModel.getCzxq();
			// ����԰��
			String czyq = jbszModel.getCzyq();
			int i= 1;
			if("��".equalsIgnoreCase(czxq)){
				i++;
			}
			if("��".equalsIgnoreCase(czyq)){
				i++;
			}
			request.setAttribute("ldnum", i);
			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);			
			
		}


		String path = myForm.getSearchModel().getPath() + "&searchType="+searchType;
		myForm.getSearchModel().setPath(path);								
		//=============== ִ�в�ѯ���� ===========
		//�Ƿ��ѯ����
		boolean search = !"go".equalsIgnoreCase(request.getParameter("go")) ? false: true;
		List<String[]> rs= new ArrayList<String[]>();
		List<String[]> zj= new ArrayList<String[]>();
		int maxcs = service.getMaxCs(myForm);
		if (search) {
			//�����
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
	
	
	// =======================================����BY qlj=================================================
	/**
	 * ��Ԣ����_ͳ�Ʒ���_����ͳ��
	 * �����꼶�ֲ����ͳ��
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
		
		//�ж����ݿ����Ƿ���������
		String sfsz = jbszModel.getSfsz();
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		myForm.setCzxq(jbszModel.getCzxq());
		myForm.setCzyq(jbszModel.getCzyq());
								
		//=============== ִ�в�ѯ���� ===========
		//�Ƿ��ѯ����
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
	 * �����꼶�ֲ����ͳ�Ƶ�����
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
