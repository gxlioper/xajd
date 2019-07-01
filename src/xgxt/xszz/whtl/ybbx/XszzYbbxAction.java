package xgxt.xszz.whtl.ybbx;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Configuration;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import xgxt.pjpy.tjzy.bcpj.PjpyBcpjForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;

import com.zfsoft.basic.BasicAction;

public class XszzYbbxAction extends BasicAction{
	
	XszzYbbxService service = new XszzYbbxService();
	/**
	 * 查询模型 以高级查询为查询条件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ybbxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user=getUser(request);
		
		XszzYbbxForm myForm=(XszzYbbxForm)form;
		
		XsxxglService stuService = new XsxxglService();
		
		String doType=request.getParameter("doType");
		
		String xh=request.getParameter("xh");
		
		String pkValue=request.getParameter("pkValue");
		
		String have=request.getParameter("have");
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			
			xh=user.getUserName();
			pkValue=Base.currXn+"!!@@!!"+xh;
			
			if(!service.countXh(xh)){
				
				String msg = "本功能仅允许获得大学生城镇报销的学生申请，请确认！";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
			
		}
		
		HashMap<String,String>ybbxSqInfo=new HashMap<String,String>();
		
		

		if(!Base.isNull(xh)){
			
			// 学生基本信息
			stuInfo.putAll(stuService.selectStuinfo(xh));
			
			pkValue=Base.currXn+"!!@@!!"+xh;
			
			myForm.setPkValue(pkValue);
			
			stuInfo.putAll(service.getYbbxSqbInfo(myForm));
			
		}
		

		
		if(!Base.isNull(pkValue)){
			
			myForm.setPkValue(pkValue);
			
			ybbxSqInfo=service.getYbbxSqbInfo(myForm);

			stuInfo.putAll(ybbxSqInfo);
			
			xh=ybbxSqInfo.get("xh");
			
			stuInfo.putAll(stuService.selectStuinfo(xh));
		}
		

		if(Base.isNull(doType)){
		
			doType="add";
		}
		
		
		
		if(ybbxSqInfo!=null && ybbxSqInfo.size()>0){
			String xysh=ybbxSqInfo.get("xysh");
			if(("未审核").equalsIgnoreCase(xysh) && (!"add".equalsIgnoreCase(doType)|| !"save".equalsIgnoreCase(doType))){
				have="modi";
			}else{
				have="sh";
			}
		}
		request.setAttribute("have", have);
		request.setAttribute("rs", stuInfo);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "xszz_ybbx_sq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ybbxDetail");
	}
	
	/**
	 * 综合测评_基本设置_综测基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ybbxcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		XszzYbbxInit init=new XszzYbbxInit();
		
		User user=getUser(request);
		
		XszzYbbxForm myForm=(XszzYbbxForm)form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		//查询的结果集
		model.setColList(new String[]{"pkValue","xh","xm", "nj",  "bjmc","xysh","xxsh"});
		//排序字段
		model.setOrderBy(new String[]{"bjdm","xh"});
		//功能模块
		model.setGnmk("xszz");
		//模块路径
		model.setPath("xszz_ybbx_cx.do");
		
		init.initYbbxcx(rForm, model, request,user);
		//结果列表
	
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("tableName", "xg_view_xszz_ybbxxx");
		
		//request.setAttribute("tableName", TABLENAME);
		// =================== end ===================

		return mapping.findForward("ybbxcxManage");
	
	}
	
	/**
	 * 综合测评_基本设置_综测基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ybbxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		XszzYbbxInit init=new XszzYbbxInit();
		
		User user=getUser(request);
		
		XszzYbbxForm myForm=(XszzYbbxForm)form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		//查询的结果集
		model.setColList(new String[]{"pkValue","xh","xm", "nj",  "bjmc","xysh","xxsh"});
		//排序字段
		model.setOrderBy(new String[]{"bjdm","xh"});
		//功能模块
		model.setGnmk("xszz");
		//模块路径
		model.setPath("xszz_ybbx_sh.do");
		
		init.initYbbxsh(rForm, model, request,user);
		//结果列表
	
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.commonRequestSet(request);
		
		//request.setAttribute("tableName", TABLENAME);
		// =================== end ===================

		return mapping.findForward("ybbxshManage");
	
	}
}
