package xsgzgl.pjpy.general.xmsz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyXmszInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmsyInterface;
import xsgzgl.pjpy.general.xmsz.xmsy.XmszXmsyModel;

import com.zfsoft.basic.BasicAction;

public class PjpyXmszAction extends BasicAction{
	

	/**
	 * 查询评奖设置（我的评奖统计）结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkShxz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommService commService=new CommService();
		
		PjpyGeneralForm myForm=PjpyGeneralForm.getJbszModel();
		
		PjpyXmszModel model=new PjpyXmszModel();
		
		User user=getUser(request);
		
		HttpSession session=request.getSession();
		//学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyXmszInterface service = myService.getPjpyXmszService(myForm);
		 
		// 将request中的值封装成model
		commService.getModelValue(model, request);
		String message=service.getXmshzg(model, user);

		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * 显示字段修改Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showXmsyDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CommService commService = new CommService();
		
		PjpyGeneralForm myForm=PjpyGeneralForm.getJbszModel();
		
		XmszXmsyModel model=new XmszXmsyModel();
		
		HttpSession session=request.getSession();
		//学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszXmsyInterface service = myService.getXmszXmsyService(myForm);

		
		//将学号与项目封装入model
		commService.getModelValue(model, request);

		// ==================构建前台页面========================
		service.showXmsyDiv(model,response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 显示字段修改Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXmsy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommService commService = new CommService();
		
		User user=getUser(request);
		
		PjpyGeneralForm myForm=PjpyGeneralForm.getJbszModel();
		
		XmszXmsyModel model=new XmszXmsyModel();
		
		HttpSession session=request.getSession();
		//学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszXmsyInterface service = myService.getXmszXmsyService(myForm);

		//将学号与项目封装入model
		commService.getModelValue(model, request);
		
		boolean flag=service.saveXmsy(model, user);
		
		String message=flag?"顺延成功":"操作失败，请联系相关人员";
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
}
