package xgxt.jygl.njjs;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;


public class NjjsJyglAction extends BasicExtendAction{
	/**
	 * 学生上报管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);		
		
		NjjsJyglForm myForm = (NjjsJyglForm) form;
		NjjsJyglService service = new NjjsJyglService();
		
		if("xy".equalsIgnoreCase(user.getUserStatus())){
			myForm.setXydm(user.getUserDep());
		}
		
		NjjsJyglModel model = new NjjsJyglModel();
		model.setUserName(user.getUserName());
		model.setUserType(user.getUserStatus());
		BeanUtils.copyProperties(model, myForm);
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request); // 设置年级学院专业班级List
		setWriteAbleAndTitle(request, "jygl_xssbgl.do"); // 设置title和writeAble
		request.setAttribute("rs", service.xssbQuery(model));
		request.setAttribute("topTr", service.getTopTr("xssb", new RequestForm()));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		
		request.setAttribute("user", user);
		return mapping.findForward("xssbManage");
	}
	
	/**
	 * 学生上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		NjjsJyglService service = new NjjsJyglService();
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			NjjsJyglModel model = new NjjsJyglModel();
			BeanUtils.copyProperties(model, form);
			
			String message = service.saveXssb(model) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getStuInfo(pkValue); // 学生信息
		
		request.setAttribute("shenList", service.getShenList());
		request.setAttribute("shiList", service.getShiList(""));
		request.setAttribute("rs", rs);
		request.setAttribute("xlList", service.getXlList()); // 获取学历List
		request.setAttribute("pyccList", service.getPyccList()); // 技能等级List
		request.setAttribute("pyfsList", service.getPyfsList());
		
		return mapping.findForward("xssbUpdate");
	}
	
	/**
	 * 学生上报修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssbModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		NjjsJyglService service = new NjjsJyglService();
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			NjjsJyglModel model = new NjjsJyglModel();
			BeanUtils.copyProperties(model, form);
			
			String message = service.updateXssb(model) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getBysInfo(pkValue); // 学生信息
		
		String shi = rs.get("sydq");
		if(StringUtils.isNotNull(shi) && shi.length()>2){
			request.setAttribute("shen", shi.substring(0,2)+"0000");
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		request.setAttribute("shenList", service.getShenList());
		request.setAttribute("shiList", service.getShiList(""));
		request.setAttribute("pyfsList", service.getPyfsList());
		request.setAttribute("xlList", service.getXlList()); // 获取学历List
		request.setAttribute("pyccList", service.getPyccList()); // 技能等级List
		return mapping.findForward("xssbModi");
	}
	
	/**
	 * 毕业去向管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byqxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);		
		String doType = request.getParameter("doType");
		
		NjjsJyglForm myForm = (NjjsJyglForm) form;
		
		if(StringUtils.isNull(myForm.getSfsb())){
			myForm.setSfsb("是");
		}
		
		if("xy".equalsIgnoreCase(user.getUserStatus())){
			myForm.setXydm(user.getUserDep());
		}
		
		NjjsJyglService service = new NjjsJyglService();
		
		NjjsJyglModel model = new NjjsJyglModel();
		model.setUserName(user.getUserName());
		model.setUserType(user.getUserStatus());
		
		BeanUtils.copyProperties(model, myForm);
		
		if("del".equalsIgnoreCase(doType)){
			String[] pks = request.getParameterValues("primary_cbv");
			String message = service.delByqx(pks) ? "删除成功！" : "删除失败！";
			request.setAttribute("message", message);
		}else if("export".equalsIgnoreCase(doType)){
			// 毕业去向导出
			response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
			response.setContentType("application/vnd.ms-excel");
			
			service.byqxdc(model, response.getOutputStream());
			return null;
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request); // 设置年级学院专业班级List
		
		setWriteAbleAndTitle(request, "jygl_byqxgl.do"); // 设置title和writeAble
		request.setAttribute("rs", service.bysQuery(model));
		request.setAttribute("topTr", service.getTopTr("byqx", new RequestForm()));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("byqxList", service.getByqxList());
		request.setAttribute("byndList", service.getByndList());
		
		request.setAttribute("user", user);
		return mapping.findForward("byqxManage");
	}
	
	/**
	 * 上报查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sbcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);		
		String doType = request.getParameter("doType");
		
		NjjsJyglForm myForm = (NjjsJyglForm) form;
		NjjsJyglService service = new NjjsJyglService();
		
		if("xy".equalsIgnoreCase(user.getUserStatus())){
			myForm.setXydm(user.getUserDep());
		}
		
		NjjsJyglModel model = new NjjsJyglModel();
		model.setUserName(user.getUserName());
		model.setUserType(user.getUserStatus());
		BeanUtils.copyProperties(model, myForm);
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String[] pks = request.getParameterValues("primary_cbv");
			String message = service.delSbxs(pks) ? "删除成功！" : "删除失败！";
			request.setAttribute("message", message);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request); // 设置年级学院专业班级List
		
		setWriteAbleAndTitle(request, "jygl_xssbcx.do"); // 设置title和writeAble
		request.setAttribute("rs", service.sbxsQuery(model));
		request.setAttribute("topTr", service.getTopTr("sbxs", new RequestForm()));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("byndList", service.getByndList());
		
		request.setAttribute("user", user);
		return mapping.findForward("sbcxManage");
	}
	
	/**
	 * 毕业去向录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byqxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		NjjsJyglService service = new NjjsJyglService();
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			NjjsJyglModel model = new NjjsJyglModel();
			BeanUtils.copyProperties(model, form);
			
			String message = service.saveByqx(model) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getBysInfo(pkValue); // 毕业生信息
		
		request.setAttribute("doType", doType);
		request.setAttribute("byqxList", service.getByqxList());
		request.setAttribute("rs", rs);
		
		return mapping.findForward("byqxUpdate");
	}
	
	/**
	 * 学生实习管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);		
		String doType = request.getParameter("doType");
		
		NjjsJyglForm myForm = (NjjsJyglForm) form;
		if("xy".equalsIgnoreCase(user.getUserStatus())){
			myForm.setXydm(user.getUserDep());
		}
		NjjsJyglService service = new NjjsJyglService();
		
		NjjsJyglModel model = new NjjsJyglModel();
		model.setUserName(user.getUserName());
		model.setUserType(user.getUserStatus());
		BeanUtils.copyProperties(model, myForm);
		
		if("del".equalsIgnoreCase(doType)){
			String[] pks = request.getParameterValues("primary_cbv");
			String message = service.delXssx(pks) ? "删除成功！" : "删除失败！";
			request.setAttribute("message", message);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request); // 设置年级学院专业班级List
		
		setWriteAbleAndTitle(request, "jygl_xssxgl.do"); // 设置title和writeAble
		request.setAttribute("rs", service.xssxQuery(model));
		request.setAttribute("topTr", service.getTopTr("xssx", new RequestForm()));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		
		request.setAttribute("user", user);
		return mapping.findForward("xssxManage");
	}
	
	/**
	 * 毕业去向录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String sfcz = request.getParameter("sfcz");
		
		NjjsJyglService service = new NjjsJyglService();
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			NjjsJyglModel model = new NjjsJyglModel();
			BeanUtils.copyProperties(model, form);
			
			String message = ""; 
			if("no".equalsIgnoreCase(sfcz)){
				message = service.saveXssx(model) ? "保存成功！" : "保存失败！";
			}else{
				message = service.updateXssx(model) ? "保存成功！" : "保存失败！";
			}
			if("保存成功！".equals(message)){//保存家庭住址和电话信息
				String jtdz=request.getParameter("jtdz");
				String lxdh=request.getParameter("lxfs");
				service.saveXsJtdzAndLxdh(model.getXh(), jtdz, lxdh);
			}
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getXssxInfo(pkValue); // 毕业生信息
		rs.put("lxfs", rs.get("lxfs")!=null&&rs.get("lxfs").length()<2?null:rs.get("lxfs"));//处理一下联系方式
		
		request.setAttribute("sfcz", sfcz);
		request.setAttribute("doType", doType);
		request.setAttribute("byqxList", service.getByqxList());
		request.setAttribute("rs", rs);
		
		return mapping.findForward("xssxUpdate");
	}
	
	/**
	 * 毕业去向汇总
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byqxhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		NjjsJyglService service = new NjjsJyglService();
		NjjsJyglForm model = (NjjsJyglForm) form;
		
		request.setAttribute("topTr", service.getTopTr("byqx", new RequestForm()));
		// 结果查询
		request.setAttribute("rs", service.byqxhzQuery(model));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		
		setWriteAbleAndTitle(request, "jygl_byqxhz.do");
		return mapping.findForward("byqxQuery");
	}
	
	/**
	 * 学生实习导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		NjjsJyglForm myForm = (NjjsJyglForm) form;
		NjjsJyglService service = new NjjsJyglService();
		
		NjjsJyglModel model = new NjjsJyglModel();
		BeanUtils.copyProperties(model, myForm);
		response.setContentType("application/vnd.ms-excel");
		OutputStream os = response.getOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		service.xssxExp(model, wwb);
		return null;
	}
	
	/**
	 * 加载市
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadShi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String shen = request.getParameter("shen");
		
		NjjsJyglService service = new NjjsJyglService();
		List<HashMap<String, String>> list = service.getShiList(shen);
		
		String json = JSONArray.fromObject(list).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		
		return null;
	}
	
	/**
	 * 加载县
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadXian(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String shen = request.getParameter("shen");
		String shi = request.getParameter("shi");

		NjjsJyglService service = new NjjsJyglService();
		List<HashMap<String, String>> list = service.getXianList(shen,shi);

		String json = JSONArray.fromObject(list).toString();

		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);

		return null;
	}
}
