/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.qgzx.shgc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.DealString;
import xgxt.qgzx.form.QgzxForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-03-16</p>
 */
public class QgzxShgcAction extends DispatchAction {
	
	/** 
	 * 考核上报时间设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward kssbsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QgzxForm model = (QgzxForm)form;
		QgzxShgcService service = new QgzxShgcService();
		List list = service.getYrdwkhsbsj(model);
		
		request.setAttribute("topTr", service.getKhsbsjToptr());
		request.setAttribute("rs", list);
		request.setAttribute("rsNum", list.size());
		request.setAttribute("yrdwList", service.getYrdwList());
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("khsbsj");
	}
	
	/** 
	 * 考核上报时间设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward khsbsjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pk = DealString.toGBK(request.getParameter("pkString"));
		QgzxShgcService service = new QgzxShgcService();
		
		request.setAttribute("pk", pk);		
		request.setAttribute("rs", service.getKhsbsjOfYrdw(pk));
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("khsbsjsz");
	}
	
	
	/** 
	 * 保存考核上报时间信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward saveKhsbsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxForm model = (QgzxForm) form;
		String pk = DealString.toGBK(request.getParameter("pkString"));
		QgzxShgcService service = new QgzxShgcService();
		
		model.setPk(pk);
		request.setAttribute("result", service.saveKhsbsjInfo(model, request));
		return khsbsjModi(mapping, form, request, response);
	}
	
	/** 
	 * 打印考核上报时间信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward printYrdwXskh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		QgzxForm model = (QgzxForm) form;
		QgzxShgcService service = new QgzxShgcService();
		model.setXmdm(DealString.toGBK(model.getXmdm()));
		String xydm = model.getXydm();
		String xmdm = model.getXmdm();
		String gwxz = model.getGwxz();
		String yrdw = model.getYrdwdm();
		
		request.setAttribute("rs", service.getXskhInfo(model));
		
		request.setAttribute("xn", model.getXn());
		request.setAttribute("nd", model.getNd());
		request.setAttribute("nj", model.getNj());
		request.setAttribute("xymc", service.getXmmc("view_njxyzybj","xydm","xymc",xydm));
		request.setAttribute("gwxz", service.getXmmc("gwxzdmb","gwxzdm","gwxzmc",gwxz));
		request.setAttribute("gwxz", service.getXmmc("yrdwdmb","yrdwdm","yrdwmc",yrdw));
		request.setAttribute("gwmc", xmdm);
		request.setAttribute("topTr", service.getPrintXskhTortr());
		return mapping.findForward("printYrdwXskh");
	}
	
	
}