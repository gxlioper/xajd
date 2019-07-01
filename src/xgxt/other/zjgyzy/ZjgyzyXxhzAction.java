package xgxt.other.zjgyzy;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.jqlx.GyglJqlxForm;
import xsgzgl.xsxx.gdjs.XsxxGdjsService;
import xsgzgl.xsxx.xsxxhz.XsxxCommService;
import xsgzgl.xsxx.xsxxhz.XsxxXxhzbService;
import xsgzgl.xsxx.xsxxhz.zjkj.XsxxZjkjService;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-23 ����09:57:34</p>
 */
public class ZjgyzyXxhzAction extends BasicAction{
	
	ZjgyzyXxhzService service =new ZjgyzyXxhzService();
	/**
	 * ѧ��������Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsrsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// ------------------��ȡtitle����дȨ begin-----------------------
		request.setAttribute("path", "xsrsPrint.do");
		
		FormModleCommon.commonRequestSet(request);
		// ------------------��ȡtitle����дȨ end-------------------------

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		return mapping.findForward("xsrsManage");
	
	}
	
	/**
	 * ѧ��ס����Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		 ------------------��ȡtitle����дȨ begin-----------------------
		request.setAttribute("path", "xszsPrint.do");
		
		FormModleCommon.commonRequestSet(request);
		// ------------------��ȡtitle����дȨ end-------------------------

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		return mapping.findForward("xszsManage");
	
	}
	

	/**
	 * ѧ��������Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdaManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		 ------------------��ȡtitle����дȨ begin-----------------------
		request.setAttribute("path", "xsdaPrint.do");
		
		FormModleCommon.commonRequestSet(request);
		// ------------------��ȡtitle����дȨ end-------------------------

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		return mapping.findForward("xsdaManage");
	
	}
	
	/**
	 * ����Ա��Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// ------------------��ȡtitle����дȨ begin-----------------------
		request.setAttribute("path", "fdyPrint.do");
		
		FormModleCommon.commonRequestSet(request);
		// ------------------��ȡtitle����дȨ end-------------------------

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		return mapping.findForward("fdyManage");
	
	}
	
	/**
	 * ����Ա������Ƹ��һ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward prqkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		 ------------------��ȡtitle����дȨ begin-----------------------
		request.setAttribute("path", "prqkPrint.do");
		
		FormModleCommon.commonRequestSet(request);
		// ------------------��ȡtitle����дȨ end-------------------------

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		return mapping.findForward("prqkManage");
	
	}
	
	/**
	 * Υ�ʹ���һ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		 ------------------��ȡtitle����дȨ begin-----------------------
		request.setAttribute("path", "wjcfPrint.do");
		
		FormModleCommon.commonRequestSet(request);
		// ------------------��ȡtitle����дȨ end-------------------------

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		List<HashMap<String, String>> xnList = Base.getXnndList();//��ѯ������ѧ��
		request.setAttribute("xnList", xnList);
		List<HashMap<String, String>> xqList = Base.getXqList();//��ѯ������ѧ��
		request.setAttribute("xqList", xqList);
		request.setAttribute("currXn", Base.currXn);
		request.setAttribute("currXq", Base.currXq);
		
		return mapping.findForward("wjcfManage");
	
	}
	
	/**
	 * ѧ����Ϣexcel���
	 * @param myForm
	 * @param map
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printXsxxExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm=(ZjgyzyXxhzForm)form;
		
		BasicModel model=new BasicModel();
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		
		service.printXsrs( myForm, model,wwb);
		
		return null;
	}
	
	/**
	 * ѧ��ס��excel���
	 * @param myForm
	 * @param map
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printXszsExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm=(ZjgyzyXxhzForm)form;
		
		BasicModel model=new BasicModel();
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		
		service.printXszs( myForm, model,wwb);
		
		return null;
	}
	
	/**
	 * ѧ������excel���
	 * @param myForm
	 * @param map
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printXsdaExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm=(ZjgyzyXxhzForm)form;
		
		BasicModel model=new BasicModel();
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		
		service.printXsda( myForm, model,wwb);
		
		return null;
	}
	
	/**
	 * ����Ա��Ϣexcel���
	 * @param myForm
	 * @param map
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printFdyExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm=(ZjgyzyXxhzForm)form;
		
		BasicModel model=new BasicModel();
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		
		service.printFdy( myForm, model,wwb);
		
		return null;
	}
	
	/**
	 * ѧ����Ϣexcel���
	 * @param myForm
	 * @param map
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printPrqkExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm=(ZjgyzyXxhzForm)form;
		
		BasicModel model=new BasicModel();
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		
		service.printPrqk( myForm, model,wwb);
		
		return null;
	}
	
	/**
	 * ѧ����Ϣexcel���
	 * @param myForm
	 * @param map
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printWjcfExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm=(ZjgyzyXxhzForm)form;
		
		BasicModel model=new BasicModel();
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		
		service.printWjcf( myForm, model,wwb);
		
		return null;
	}
	
}
