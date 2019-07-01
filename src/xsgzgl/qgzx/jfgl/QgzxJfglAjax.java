package xsgzgl.qgzx.jfgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.message.MessageKey;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * 勤工助学-勤工经费管理-经费信息管理
 * 
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJfglAjax extends BasicAction {
	/**
	 * 经费信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_jfgl_jfxxgl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getJfxx(myForm);
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
	 * 经费信息增加保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// 传输乱码问题
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbsj(service.unicode2Gbk(myForm.getHbsj()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		myForm.setBz(service.unicode2Gbk(myForm.getBz()));
		String message = service.jfxxBc(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @描述：月份初始化
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月9日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// 传输乱码问题
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbsj(service.unicode2Gbk(myForm.getHbsj()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		myForm.setBz(service.unicode2Gbk(myForm.getBz()));
		String message = service.jfxxInit(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述: 获取年度用人单位部门
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-22 上午11:05:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward getBM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd = request.getParameter("nd");
		List<HashMap<String, String>> list=null;
		if(StringUtils.isNull(nd)){
			list = service.getBms(xn,xq);
		}else{
			list=service.getBm(xn,nd);
		}
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
	public ActionForward getGwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		String xn = request.getParameter("xn");
		String yrdwdm=request.getParameter("yrdwdm");
		String xq = request.getParameter("xq");
		HashMap<String, String> map = service.getGwxx(xn,yrdwdm,xq);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	
	public String getxnForNd(String nd){
		String xn="";
		 List<HashMap<String, String>> xnL=Base.getXnndList();
		 for(HashMap<String, String> hm:xnL){
			 if(StringUtils.isNotNull(hm.get("nd"))&&nd.equals(hm.get("nd"))){
				 xn=hm.get("xn");
			 }
		 }
		return xn;
	}
	/**
	 * 经费信息修改保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// 传输乱码问题
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbsj(service.unicode2Gbk(myForm.getHbsj()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		myForm.setBz(service.unicode2Gbk(myForm.getBz()));
		String message = service.jfxxXg(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 验证增加的保存信息是否正确
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm model = (QgzxJfglForm) form;
		// 传输乱码问题
		model.setBm(service.unicode2Gbk(model.getBm()));
		model.setHbsj(service.unicode2Gbk(model.getHbsj()));
		String message = service.checkBcInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 验证修改的保存信息是否正确
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXgBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// 传输乱码问题
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		String message = service.checkXgBcInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
}
