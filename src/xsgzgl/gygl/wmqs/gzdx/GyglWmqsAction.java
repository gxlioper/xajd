package xsgzgl.gygl.wmqs.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * 公寓管理-文明寝室评比             贵州大学个性功能
 * 2012-03-21
 */
public class GyglWmqsAction extends BasicExtendAction{
	
	/**
	 * 文明寝室个数维护
	 * @return
	 */
	@SystemLog(description="访问公寓管理-文明寝室-文明寝室个数维护-{doType}维护XYDMS:{xydms}")
	public ActionForward qsgswhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);// 用户对象
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		GyglWmqsService service = new GyglWmqsService();
		RequestForm rForm = new RequestForm();
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//保存设置的文明寝室个数
			boolean b=service.saveWmqsgs(myForm);
			if(b){
				request.setAttribute("back", "操作成功！");
			}else{
				request.setAttribute("back", "操作失败！");
			}
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] colList = new String[]{"学院","寝室数","文明百分比个数","文明寝室数"};

		// =============== 执行查询操作 ===========
		
//		myForm.getSearchModel().setSearch_tj_nj(new String[]{Base.currNd});
		
		rsArrList = service.getWmqsgswhInfoList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		
		request.setAttribute("path", "gzdx_gygl_wmqs_qsgswh.do");
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================
		
		//判断必须由学生进入该界面
		if(rsArrList.size() == 0){
			request.setAttribute("yhInfo", "目前公寓寝室的寝室长未维护，无法通过寝室长所属学院设置文明寝室个数！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		return mapping.findForward("qsgswhManage");
	}
	/**
	 * 文明寝室申请
	 * @return
	 */
	@SystemLog(description="访问公寓管理-文明寝室-文明寝室申请-保存LDDM:{lddm}")
	public ActionForward qssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//判断必须由学生进入该界面
		if(!"stu".equals((String)request.getSession().getAttribute("userType"))){
			request.setAttribute("yhInfo", "该申请只有寝室长申请操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		GyglWmqsService service = new GyglWmqsService();
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//保存申请信息
			myForm.setSqr(getUser(request).getRealName());
			boolean b=service.saveWmqssqxx(myForm);
			if(b){
				request.setAttribute("back", "操作成功！");
			}else{
				request.setAttribute("back", "操作失败！");
			}
		}
		myForm.setSqr(getUser(request).getUserName());
//		myForm.setSqr("3063029018");
		HashMap<String,String> rs=service.getWmqssqxx(myForm,request);//获取文明寝室申请信息
		request.setAttribute("rs", rs);
		if(rs==null||rs.isEmpty()){
			request.setAttribute("yhInfo", "该申请只有寝室长申请操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		return mapping.findForward("qssqManage");
	}
	/**
	 * 文明寝室审核
	 * @return
	 */
	@SystemLog(description="访问公寓管理-文明寝室-文明寝室审核-保存PK:{primarykey_checkVal}")
	public ActionForward qsshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.setAttribute("path", "gzdx_gygl_wmqs_qssh.do");
		
		//判断必须由学生进入该界面
		if("stu".equals((String)request.getSession().getAttribute("userType"))){
			request.setAttribute("yhInfo", "学生无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		//确定用户类型
		User user = getUser(request);
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		/*boolean isFdy=false;
		boolean isBzr=false;
		if(session.getAttribute("isFdy")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("isFdy").toString());
		}
		if(session.getAttribute("isBzr")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("isBzr").toString());
		}
		if(isFdy||isBzr){
			myForm.setUserType("fdy");
			myForm.setSqr(getUser(request).getUserName());//临时存放用户名，用于权限控制
			request.setAttribute("shtype", "fdy");
		}else{
			myForm.setUserType("xx");
			request.setAttribute("shtype", "xx");
		}
		*/
		GyglWmqsService service = new GyglWmqsService();
		
		String doType = request.getParameter("doType");
		
		//审核操作
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveWmqsshxx(myForm,request,user) ? "操作成功！" : "操作失败！" ;
			if(request.getAttribute("message")==null){
				request.setAttribute("message", message);
			}
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// 结果集显示字段
		String[] cn = new String[]{"楼栋","寝室","寝室人数","卫生平均分","申请人","辅导员审核状态","学校审核状态"};
//		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","bxnr","wxsj","jjcd","clzt","mycd"};

		// =============== 执行查询操作 ===========
		
		rsArrList = service.getWmqsshList(myForm,request,user);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(cn));
		
		FormModleCommon.commonRequestSet(request);	//显示title
		return mapping.findForward("qsshManage");
	}
	/**
	 * 文明寝室管理
	 * @return
	 */
	@SystemLog(description="访问公寓管理-文明寝室-文明寝室管理-删除PK:{primarykey_checkVal}")
	public ActionForward qsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.setAttribute("path", "gzdx_gygl_wmqs_qsgl.do");
		//判断必须由学生进入该界面
		if("stu".equals((String)request.getSession().getAttribute("userType"))){
			request.setAttribute("yhInfo", "学生无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		GyglWmqsService service = new GyglWmqsService();
		
		String doType = request.getParameter("doType");
		
		// 删除操作
		if("save".equalsIgnoreCase(doType)){
			myForm.setUserType("xx");
			String message = service.saveWmqssqxx(myForm) ? "操作成功！" : "操作失败！" ;
			request.setAttribute("message", message);
		}else if("del".equals(doType)){
			String message = service.delWmqsshxx(myForm) ? "操作成功！" : "操作失败！" ;
			request.setAttribute("message", message);
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// 结果集显示字段
		String[] cn = new String[]{"楼栋","寝室","寝室人数","卫生平均分","申请人","辅导员审核状态","学校审核状态"};
//		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","bxnr","wxsj","jjcd","clzt","mycd"};

		// =============== 执行查询操作 ===========
		request.setAttribute("path", "gzdx_gygl_wmqs_qsgl.do");
		rsArrList = service.getWmqsglList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(cn));
		
		FormModleCommon.commonRequestSet(request);	//显示title
		request.setAttribute("tableName", "view_xg_gygl_new_gzdx_wmqssq");
		return mapping.findForward("qsglManage");
	}
	
	/**
	 * 文明寝室修改
	 * @return
	 */
	@SystemLog(description="访问公寓管理-文明寝室-文明寝室管理-修改PK:{lddm}")
	public ActionForward wmqsglModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		myForm.setUserType("teacher");
		GyglWmqsService service = new GyglWmqsService();
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){
			myForm.setSqr(getUser(request).getRealName());
			String message = service.saveWmqssqxx(myForm) ? "操作成功！" : "操作失败！" ;
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getWmqssqxx(myForm,request);
		request.setAttribute("rs", rs);
		request.setAttribute("ldList", service.getLdxxList(request));
		request.setAttribute("doType", doType);
		return mapping.findForward("wmqsglModi");
	}
	
	/**
	 * 获取文明寝室信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWmqsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		myForm.setUserType("teacher");
		GyglWmqsService service = new GyglWmqsService();

		Map<String, String> map = service.getWmqssqxx(myForm,request);
		List<HashMap<String,String>> xsList=(List<HashMap<String,String>>)request.getAttribute("xsList");
		if(xsList!=null&&xsList.size()>0){
			StringBuffer xsHtml=new StringBuffer();
			xsHtml.append("<table width='100%'>");
			xsHtml.append("<tr>");
			xsHtml.append("<th>学号</th>");
			xsHtml.append("<th>姓名</th>");
			xsHtml.append("<th>学院</th>");
			xsHtml.append("<th>专业</th>");
			xsHtml.append("<th>班级</th>");
			xsHtml.append("</tr>");
			HashMap<String,String> r;
			for(int i=0;i<xsList.size();i++){
				r=xsList.get(i);
				xsHtml.append("<tr><td>");
				xsHtml.append(r.get("xh"));
				xsHtml.append("</td><td>");
				xsHtml.append(r.get("xm"));
				xsHtml.append("</td><td>");
				xsHtml.append(r.get("xymc"));
				xsHtml.append("</td><td>");
				xsHtml.append(r.get("zymc"));
				xsHtml.append("</td><td>");
				xsHtml.append(r.get("bjmc"));
				xsHtml.append("</td></tr>");
			}
			xsHtml.append("</table>");
			map.put("xsHtml", xsHtml.toString());
		}
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 获取文明寝室百分比
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWmqsbfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm=Base.chgNull(request.getParameter("csdm"), "", 0);
		GyglWmqsService service = new GyglWmqsService();

		List<HashMap<String,String>> list=service.getWmqsbfb(new String[]{csdm});
		if(list!=null&&list.size()>0){
			String json = JSONObject.fromObject(list.get(0)).toString();	
			
			response.setCharacterEncoding("gbk");
			response.getWriter().write(json);
		}
		return null;
	}
	
	/**
	 * 保存文明寝室百分比
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-文明寝室-文明寝室个数维护-保存文明寝室百分比")
	public ActionForward saveWmqsbfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csz=request.getParameter("csz");
		GyglWmqsService service = new GyglWmqsService();
		boolean b = service.saveWmqsbfb(csz);
//		String json = JSONObject.fromObject(map).toString();	
		String msg;
		if(b){
			msg="修改成功！";
		}else{
			msg="修改失败！";
		}
		response.setCharacterEncoding("gbk");
		response.getWriter().write(msg);
		return null;
	}
	
}
