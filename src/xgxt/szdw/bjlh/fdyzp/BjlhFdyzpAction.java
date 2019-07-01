package xgxt.szdw.bjlh.fdyzp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

public class BjlhFdyzpAction extends BasicExtendAction {

	public ActionForward fdyzpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		//判断必须由校级用户进入该界面
		if(!"xx".equals(user.getUserType()) && !"admin".equals(user.getUserType())){
			request.setAttribute("yhInfo", "非校级用户无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		request.setAttribute("path", "bjlh_fdykh_fdyzp.do");
		BjlhFdyzpService service = new BjlhFdyzpService();
		BjlhFdyzpForm myForm = (BjlhFdyzpForm) form;
		String doType = request.getParameter("doType");
		String[] checkVal = request.getParameterValues("checkVal");
		String pkValue = request.getParameter("pkValue");
		Map<String, String> rs1 = new HashMap<String, String>();
		//删除
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.deleteAllById(pkValue);
			if (flag) {
				request.setAttribute("message", "删除成功！");
			} else {
				request.setAttribute("message", "删除失败！");
			}
		}
		//启用 
		else if ("qy".equalsIgnoreCase(doType)) {
			boolean flag = service.changeSfqy(pkValue,"是");
			if (flag) {
				request.setAttribute("message", "启用成功！");
			} else {
				request.setAttribute("message", "启用失败！");
			}
		} 
		//停用
		else if ("ty".equalsIgnoreCase(doType)) {
			boolean flag = service.changeSfqy(pkValue,"否");
			if (flag) {
				request.setAttribute("message", "停用成功！");
			} else {
				request.setAttribute("message", "停用失败！");
			}
		} 
		//复制
		else if ("copy".equalsIgnoreCase(doType)){
			boolean flag = service.copyFdyzpById(myForm,pkValue);
			if (flag) {
				request.setAttribute("message", "复制成功！");
			} else {
				request.setAttribute("message", "复制失败！");
			}
		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("fdyzp"));
		request.setAttribute("rs", service.getTableList(myForm, request));
		request.setAttribute("rs1", rs1);
		// write和titile
		setWriteAbleAndTitle(request, "bjlh_fdykh_fdyzp.do");

		
		request.setAttribute("realTable", "xg_gygl_new_cwxxb"); // 导入表
		request.setAttribute("tableName", "xg_gygl_new_cwxxb"); // 导出视图

		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("addxnList", service.getAddXnList());
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("manage");
	}

	public ActionForward fdyzpEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdyzpService service = new BjlhFdyzpService();
		BjlhFdyzpForm myForm = (BjlhFdyzpForm) form;
		String doType = request.getParameter("doType");
		HashMap<String,String> rs = new HashMap<String, String>();
		//保存
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveFdyzpbAndFdyzpxmb(myForm);
			if (flag) {
				request.setAttribute("message", "保存成功！");
			} else {
				request.setAttribute("message", "保存失败！");
			}
		} 
		//修改或者查看数据初始化
		else if ("modi".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pkValue = request.getParameter("pkValue");
			rs = service.getFdyzpbById(pkValue);	
			request.setAttribute("xmList", service.getXmListByZpbId(pkValue));
			myForm.setZpbid(pkValue);
		}
		
		request.setAttribute("addxnList", service.getAddXnList());
		request.setAttribute("rs", rs);
		// write和titile
		setWriteAbleAndTitle(request, "bjlh_fdykh_fdyzp.do");
		request.setAttribute("doType", doType);
		return mapping.findForward("edit");
	}

	public ActionForward fdyzpFdylr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdyzpService service = new BjlhFdyzpService();
		BjlhFdyzpForm myForm = (BjlhFdyzpForm) form;
		String doType = request.getParameter("doType");
		HashMap<String, String> mrsz = service.getMrsz();
		String dqsj = service.getDate();
		
		User user = getUser(request);
		//判断必须由辅导员进入该界面
		if("false".equals(user.getFdyQx())){
			request.setAttribute("yhInfo", "非辅导员无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(mrsz == null ||mrsz.isEmpty()){
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(mrsz.get("zpbid") == null || "".equalsIgnoreCase(mrsz.get("zpbid"))){
				request.setAttribute("yhInfo", "暂无自评表需填写，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
			
			//判断 当前学年是否启用 的自评表
			if("0".equalsIgnoreCase(mrsz.get("khsfqd"))
					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj")) 
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))){
				request.setAttribute("yhInfo", "非辅导员考核时间段，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}			
		}		
		
		
		//保存
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveFdyzpxxb(myForm,"否");
			if(flag){
				request.setAttribute("message", "保存成功！");
			}else{
				request.setAttribute("message", "保存失败！");
			}
		//提交
		}else if("tj".equalsIgnoreCase(doType)){
			boolean flag = service.saveFdyzpxxb(myForm,"是");
			if(flag){
				request.setAttribute("message", "提交成功！");
			}else{
				request.setAttribute("message", "提交失败！");
			}
		}
		
		HashMap<String,String> rs = service.getFdyxx(user.getUserName(),mrsz.get("zpbid"));
		List<HashMap<String,String>> xmList = service.getFdyzpxxb(mrsz.get("zpbid"),user.getUserName());
				
		rs.put("sftj", xmList.get(0).get("sftj"));
		request.setAttribute("rs", rs);
		
		request.setAttribute("xmList", xmList);
		// write和titile
		setWriteAbleAndTitle(request, "bjlh_fdykh_fdyzplr.do");
		
		request.setAttribute("path", "bjlh_fdykh_fdyzplr.do");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		return mapping.findForward("fdylr");
	}
	
	//辅导员自评查询
	public ActionForward fdyzpQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		//判断必须由学院或学校或辅导员用户进入该界面
		if("stu".equals(user.getUserType()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "班主任及学生用户无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		BjlhFdyzpService service = new BjlhFdyzpService();
		BjlhFdyzpForm myForm = (BjlhFdyzpForm) form;
		HashMap<String, String> mrsz = service.getMrsz();
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_fdyzpcx.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
			myForm.getSearchModel().setSearch_tj_sf(new String[]{"是"});
		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("fdyzpcx"));
		request.setAttribute("rs", service.getQueryTableList(myForm, request));
		// write和titile
		setWriteAbleAndTitle(request, "bjlh_fdykh_fdyzpcx.do");
		
		return mapping.findForward("query");
	}
	
	public ActionForward fdyzpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BjlhFdyzpService service = new BjlhFdyzpService();
		
		String zpbid=request.getParameter("zpbid");
		String yhm= request.getParameter("yhm");
		
		request.setAttribute("rs", service.getFdyxx(yhm,zpbid));
		request.setAttribute("xmList", service.getFdyzpxxb(zpbid,yhm));
		
		return mapping.findForward("view");
	}
	
	//辅导员自评查询
	public ActionForward fdyzpPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdyzpService service = new BjlhFdyzpService();
		String zpbid=request.getParameter("zpbid");
		String yhm= request.getParameter("yhm");
		
		request.setAttribute("rs", service.getFdyxx(yhm,zpbid));
		request.setAttribute("xmList", service.getFdyzpxxb(zpbid,yhm));
		
		return mapping.findForward("print");
	}
	
}
