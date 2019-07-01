package xgxt.action.zjcm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.Arrays2;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.QueryReturnData;

public class ZjcmAction extends DispatchAction {
	
	/**
	 * 学生网上请假申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsqjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmService myServie = new ZjcmService();
		ZjcmForm myForm = (ZjcmForm)form;
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");
		String xh = DealString.toGBK(request.getParameter("xh"));
		if(userType.equalsIgnoreCase("stu")){
			xh = userName; 
		}
		HashMap<String,String> stuRs = myServie.getXxInfo(xh);	
		List<HashMap<String, String>> qjlxList = myServie.getQjlxForWebService();
		request.setAttribute("rs", stuRs);
		request.setAttribute("path", "zjcm_wsqjsq.do");
		request.setAttribute("qjlxList", qjlxList);
		FormModleCommon.commonRequestSet(request);
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("wsqjsq");
	}
	
	/**
	 * 学生网上请假申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsqjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmService myServie = new ZjcmService();
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		BeanUtils.copyProperties(myModel, myForm);
		boolean update = myServie.wsqjsqSave(myModel);
		if(update){
			request.setAttribute("done", "true");
		}else{
			request.setAttribute("done", "false");
		}
		return wsqjsq(mapping,form,request,response);
	}
	
	/**
	 * 学生网上请假审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsqjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		ZjcmService myServie = new ZjcmService();
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("uesrName");
		BeanUtils.copyProperties(myModel, myForm);
		QueryReturnData queryReturnData = null;
		if(request.getParameter("go")!=null&&request.getParameter("go").equalsIgnoreCase("go")){
				queryReturnData = myServie.getQjxx(myModel);
				request.setAttribute("topTr", queryReturnData.getTopTr());
				request.setAttribute("rs", queryReturnData.getRs());
		}
		request.setAttribute("path", "zjcm_wsqjsh.do");
		FormModleCommon.commonRequestSet(request);
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			//辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
		}
		return mapping.findForward("wsqjsh");
	}
	
	
	/**
	 * 学生网上请假结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsqjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		ZjcmService myServie = new ZjcmService();
		BeanUtils.copyProperties(myModel, myForm);
		QueryReturnData queryReturnData = null;
		if(request.getParameter("go")!=null&&request.getParameter("go").equalsIgnoreCase("go")){
				queryReturnData = myServie.getQjxx(myModel);
				request.setAttribute("topTr", queryReturnData.getTopTr());
				request.setAttribute("rs", queryReturnData.getRs());
		}
		request.setAttribute("path", "zjcm_wsqjjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wsqjjg");
	}
	
	/**
	 * 单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsqjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmService myServie = new ZjcmService();
		String pk = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String,String> rs = myServie.getWsqjshOne(pk);	
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);
		List<HashMap<String, String>> qjlxList = myServie.getQjlxForWebService();
		request.setAttribute("qjlxList", qjlxList);
		return mapping.findForward("wsqjshOne");
	}
	
	/**
	 * 单个审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsqjshOneSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		ZjcmService myServie = new ZjcmService();
		BeanUtils.copyProperties(myModel, myForm);
		String pk = request.getParameter("pk");
		boolean updata = myServie.wsqjshSave(pk,myModel);	
		if(updata){
			request.setAttribute("updata", "true");
		}else{
			request.setAttribute("updata", "false");
		}
		return mapping.findForward("wsqjshOne");
	}
	
	
	/**
	 * 网上请假类型设置
	 */
	public ActionForward wsqjlxsz(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjcmService myServie = new ZjcmService();	
		List<HashMap<String, String>> qjlxList = myServie.getQjlxForWebService();
		request.setAttribute("rs", qjlxList);
		request.setAttribute("path", "zjcm_wsqjlxwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qjlxsz");
	}
	
	/**
	 * 网上请假类型增加修改分
	 */
	public ActionForward wsqjlxUpdata(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		String pk = request.getParameter("pk");
		ZjcmService myServie = new ZjcmService();
		BeanUtils.copyProperties(myModel, myForm);
		boolean updata = myServie.wsqjlxSave(pk,myModel);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
		if(updata){
			request.setAttribute("updata", "true");
		}else{
			request.setAttribute("updata", "false");
		}
		return wsqjlxsz(mapping,form,request,response);
	}
	
	/**
	 * 网上请假类型删除
	 */
	public ActionForward wsqjlxDelete(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pk = request.getParameter("pk");
		ZjcmService myServie = new ZjcmService();
		boolean updata = myServie.wsqjlxDel(pk);	
		if(updata){
			request.setAttribute("updata", "true");
		}else{
			request.setAttribute("updata", "false");
		}
		return wsqjlxsz(mapping,form,request,response);
	}
	/**
	 * 学生网上留言结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wslycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		ZjcmService myServie = new ZjcmService();
		BeanUtils.copyProperties(myModel, myForm);
		QueryReturnData queryReturnData = null;
		if(request.getParameter("go")!=null&&request.getParameter("go").equalsIgnoreCase("go")){
				queryReturnData = myServie.getLyxx(myModel);
				request.setAttribute("topTr", queryReturnData.getTopTr());
				request.setAttribute("rs", queryReturnData.getRs());
		}
		request.setAttribute("path", "zjcm_wsly.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wslyjg");
	}
	
	/**
	 * 教师值班数据维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jszbcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		ZjcmService myServie = new ZjcmService();
		BeanUtils.copyProperties(myModel, myForm);
		QueryReturnData queryReturnData = null;
		if(request.getParameter("go")!=null&&request.getParameter("go").equalsIgnoreCase("go")){
				queryReturnData = myServie.getJszbxx(myModel);
				request.setAttribute("topTr", queryReturnData.getTopTr());
				request.setAttribute("rs", queryReturnData.getRs());
		}
		request.setAttribute("path", "zjcm_jszb.do");
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jrzb");
	}
	
	/**
	 * 教师值班单个显示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jszbOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FormModleCommon.requestSetList(new String[] { "bm", "fdy" }, request);
		return mapping.findForward("jszbOne");
	}
	
	/**
	 * 教师值班单个修改保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jszbOneSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmForm myForm = (ZjcmForm) form;
		ZjcmModel myModel = new ZjcmModel();
		ZjcmService myServie = new ZjcmService();
		BeanUtils.copyProperties(myModel, myForm);
		boolean updata = myServie.jszbSave(myModel);
		if (updata) {
			request.setAttribute("updata", "true");
		} else {
			request.setAttribute("updata", "false");
		}
		return mapping.findForward("jszbOne");
	}
	
	/**
	 * 学生网上留言
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmService myServie = new ZjcmService();
		ZjcmForm myForm = (ZjcmForm)form;
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");
		String xh = DealString.toGBK(request.getParameter("xh"));
		if(userType.equalsIgnoreCase("stu")){
			xh = userName; 
		}
		HashMap<String,String> stuRs = myServie.getXxInfo(xh);	
		request.setAttribute("rs", stuRs);
		request.setAttribute("path", "zjcm_wsly.do");
		FormModleCommon.commonRequestSet(request);
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("wsly");
	}
	
	/**
	 * 学生网上留言申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wslySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmService myServie = new ZjcmService();
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		BeanUtils.copyProperties(myModel, myForm);
		boolean update = myServie.wslySave(myModel);
		if(update){
			request.setAttribute("done", "true");
		}else{
			request.setAttribute("done", "false");
		}
		return wsly(mapping,form,request,response);
	}
	
	/**
	 * 学生网上留言回复
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wslyhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		ZjcmService myServie = new ZjcmService();
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("uesrName");
		BeanUtils.copyProperties(myModel, myForm);
		QueryReturnData queryReturnData = null;
		if(request.getParameter("go")!=null&&request.getParameter("go").equalsIgnoreCase("go")){
				queryReturnData = myServie.getWslyhf(myModel);
				request.setAttribute("topTr", queryReturnData.getTopTr());
				request.setAttribute("rs", queryReturnData.getRs());
		}
		request.setAttribute("path", "zjcm_wslyhf.do");
		FormModleCommon.commonRequestSet(request);
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			//辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
		}
		return mapping.findForward("wslyhf");
	}
	
	/**
	 * 网上留言单个回复
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wslyhfOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmService myServie = new ZjcmService();
		String pk = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String,String> rs = myServie.getWslyhfOne(pk);	
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);
		FormModleCommon.requestSetList(new String[] { "bm"}, request);
		return mapping.findForward("wslyhfOne");
	}
	
	/**
	 * 网上留言单个回复保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wslyhfOneSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmService myServie = new ZjcmService();
		ZjcmForm myForm = (ZjcmForm)form;
		String pk = request.getParameter("pk");
		ZjcmModel myModel = new ZjcmModel();
		BeanUtils.copyProperties(myModel, myForm);
		boolean update = myServie.wslyhfSave(myModel,pk);
		if(update){
			request.setAttribute("updata", "true");
		}else{
			request.setAttribute("updata", "false");
		}
		return mapping.findForward("wslyhfOne");
	}
	
	/**
	 * 服务网上显示值班信息
	 */
	public ActionForward jrzbFww(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmService myServie = new ZjcmService();
		String day = request.getParameter("day");
		if(day==null||day.equalsIgnoreCase("")){
			day = "today";
		}
		List<HashMap<String, String>> jszbList = myServie.getJszbFww(day);
		request.setAttribute("rs", jszbList);
		request.setAttribute("day", day);
		return mapping.findForward("jrzbfww");
	}
	
	/**
	 * 
	 */
	public ActionForward lytjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmService myServie = new ZjcmService();
		String [][] result = myServie.getLytjxx();
		request.setAttribute("rs1", result[0]);
		request.setAttribute("rs2", result[1]);
		request.setAttribute("rs3", result[2]);
		request.setAttribute("rs4", result[3]);
		request.setAttribute("rs5", result[4]);
		request.setAttribute("rs6", result[5]);
		request.setAttribute("rs7", result[6]);
		request.setAttribute("rs2Sum", Arrays2.strArrToSum(result[1]));
		request.setAttribute("rs3Sum", Arrays2.strArrToSum(result[2]));
		request.setAttribute("rs4Sum", Arrays2.strArrToSum(result[3]));
		request.setAttribute("rs5Sum", Arrays2.strArrToSum(result[4]));
		request.setAttribute("rs6Sum", Arrays2.strArrToSum(result[5]));
		request.setAttribute("rs7Sum", Arrays2.strArrToSum(result[6]));
		return mapping.findForward("lytjfww");
	}
	
	/**
	 * 学生网上留言服务网查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wslyFww(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lylxdm = request.getParameter("lylxdm");
		ZjcmModel myModel = new ZjcmModel();
		myModel.setLylxdm(lylxdm);
		ZjcmService myServie = new ZjcmService();
		QueryReturnData queryReturnData = null;
		queryReturnData = myServie.getWslyhfForFww(lylxdm);
		request.setAttribute("topTr", queryReturnData.getTopTr());
		request.setAttribute("rs", queryReturnData.getRs());
		return mapping.findForward("wslyhfFww");
	}
	
	/**
	 * 学生网上留言服务网查看更多信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wslyFwwMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjcmForm myForm = (ZjcmForm)form;
		ZjcmModel myModel = new ZjcmModel();
		ZjcmService myServie = new ZjcmService();
		BeanUtils.copyProperties(myModel, myForm);
		String lylxdm = request.getParameter("lylxdm");
		if(lylxdm==null||lylxdm.equalsIgnoreCase("")){
			myModel.setLylxdm("1");
		}else{
			myModel.setLylxdm(lylxdm);
		}
		QueryReturnData queryReturnData = null;
		queryReturnData = myServie.wslyFwwMore(myModel);
		request.setAttribute("topTr", queryReturnData.getTopTr());
		request.setAttribute("rs", queryReturnData.getRs());
		request.setAttribute("rsNum", queryReturnData.getRs().size());
		return mapping.findForward("wslyFwwMore");
	}
}

