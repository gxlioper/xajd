package xsgzgl.pjpy.general.bjry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import com.zfsoft.basic.BasicAction;

public class BjryglAjax extends BasicAction {
	
	/**
	 * 班级荣誉查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("pjpy_bjry_bjrygl.do");
		request.setAttribute("path", "pjpy_bjry_bjrygl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getBjryglCx(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 获取班级名称
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("pjpy_bjry_bjrygl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr2();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getBjmc(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 获取班级信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		String bjdm = request.getParameter("bjdm");
		//获取由班级代码到班级信息结果集
		List<HashMap<String, String>> bjxxRs = service.getBjxx(myForm,bjdm);
		JSONArray jsonArr = JSONArray.fromArray(bjxxRs.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/**
	 * 班级荣誉修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglXgBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		String message = "";
		boolean flag = false;
		User user = getUser(request);
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String bjdm = request.getParameter("bjdm");
		String hdsj = request.getParameter("hdsj");
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String username = user.getUserName();
		myForm.setXn(xn);
		myForm.setXq(xq);
		myForm.setBjdm(bjdm);
		myForm.setHdsj(hdsj);
		myForm.setBz(bz);

		flag = service.bjryglXgBc(myForm, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 班级荣誉增加保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglZjBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		String message = "";
		boolean flag = false;
		User user = getUser(request);
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String bjdm = request.getParameter("bjdm");
		String rydm = service.unicode2Gbk(request.getParameter("rydm"));
		String hdsj = request.getParameter("hdsj");
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String guid = xn+"!@"+(xq.equals("")?"no":xq)+"!@"+bjdm;
		String username = user.getUserName();
		myForm.setXn(xn);
		//如果学年为空，则不设置学年字段
		if(!xq.equals("")){
			myForm.setXq(xq);
		}
		myForm.setBjdm(bjdm);
		myForm.setRydm(rydm);
		myForm.setHdsj(hdsj);
		myForm.setBz(bz);
		myForm.setGuid(guid);
		HashMap<String, String> bjryglxx = service.getBjryglMap(myForm);
		//判断有没有在该周期评过
		if(bjryglxx.isEmpty()){
			flag = service.bjryglZjBc(myForm, username);
			if (Base.isNull(message)) {
				message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
			}
		}else{
			message = "该班级在该周期已经获得荣誉，不能重复添加！";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}