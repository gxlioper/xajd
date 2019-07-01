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
	 * ѧ���ϱ�����
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
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request); // �����꼶ѧԺרҵ�༶List
		setWriteAbleAndTitle(request, "jygl_xssbgl.do"); // ����title��writeAble
		request.setAttribute("rs", service.xssbQuery(model));
		request.setAttribute("topTr", service.getTopTr("xssb", new RequestForm()));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		
		request.setAttribute("user", user);
		return mapping.findForward("xssbManage");
	}
	
	/**
	 * ѧ���ϱ�
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
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			NjjsJyglModel model = new NjjsJyglModel();
			BeanUtils.copyProperties(model, form);
			
			String message = service.saveXssb(model) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getStuInfo(pkValue); // ѧ����Ϣ
		
		request.setAttribute("shenList", service.getShenList());
		request.setAttribute("shiList", service.getShiList(""));
		request.setAttribute("rs", rs);
		request.setAttribute("xlList", service.getXlList()); // ��ȡѧ��List
		request.setAttribute("pyccList", service.getPyccList()); // ���ܵȼ�List
		request.setAttribute("pyfsList", service.getPyfsList());
		
		return mapping.findForward("xssbUpdate");
	}
	
	/**
	 * ѧ���ϱ��޸�
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
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			NjjsJyglModel model = new NjjsJyglModel();
			BeanUtils.copyProperties(model, form);
			
			String message = service.updateXssb(model) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getBysInfo(pkValue); // ѧ����Ϣ
		
		String shi = rs.get("sydq");
		if(StringUtils.isNotNull(shi) && shi.length()>2){
			request.setAttribute("shen", shi.substring(0,2)+"0000");
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		request.setAttribute("shenList", service.getShenList());
		request.setAttribute("shiList", service.getShiList(""));
		request.setAttribute("pyfsList", service.getPyfsList());
		request.setAttribute("xlList", service.getXlList()); // ��ȡѧ��List
		request.setAttribute("pyccList", service.getPyccList()); // ���ܵȼ�List
		return mapping.findForward("xssbModi");
	}
	
	/**
	 * ��ҵȥ�����
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
			myForm.setSfsb("��");
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
			String message = service.delByqx(pks) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�";
			request.setAttribute("message", message);
		}else if("export".equalsIgnoreCase(doType)){
			// ��ҵȥ�򵼳�
			response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
			response.setContentType("application/vnd.ms-excel");
			
			service.byqxdc(model, response.getOutputStream());
			return null;
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request); // �����꼶ѧԺרҵ�༶List
		
		setWriteAbleAndTitle(request, "jygl_byqxgl.do"); // ����title��writeAble
		request.setAttribute("rs", service.bysQuery(model));
		request.setAttribute("topTr", service.getTopTr("byqx", new RequestForm()));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("byqxList", service.getByqxList());
		request.setAttribute("byndList", service.getByndList());
		
		request.setAttribute("user", user);
		return mapping.findForward("byqxManage");
	}
	
	/**
	 * �ϱ���ѯ
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
		
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			String[] pks = request.getParameterValues("primary_cbv");
			String message = service.delSbxs(pks) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request); // �����꼶ѧԺרҵ�༶List
		
		setWriteAbleAndTitle(request, "jygl_xssbcx.do"); // ����title��writeAble
		request.setAttribute("rs", service.sbxsQuery(model));
		request.setAttribute("topTr", service.getTopTr("sbxs", new RequestForm()));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("byndList", service.getByndList());
		
		request.setAttribute("user", user);
		return mapping.findForward("sbcxManage");
	}
	
	/**
	 * ��ҵȥ��¼��
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
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			NjjsJyglModel model = new NjjsJyglModel();
			BeanUtils.copyProperties(model, form);
			
			String message = service.saveByqx(model) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getBysInfo(pkValue); // ��ҵ����Ϣ
		
		request.setAttribute("doType", doType);
		request.setAttribute("byqxList", service.getByqxList());
		request.setAttribute("rs", rs);
		
		return mapping.findForward("byqxUpdate");
	}
	
	/**
	 * ѧ��ʵϰ����
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
			String message = service.delXssx(pks) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request); // �����꼶ѧԺרҵ�༶List
		
		setWriteAbleAndTitle(request, "jygl_xssxgl.do"); // ����title��writeAble
		request.setAttribute("rs", service.xssxQuery(model));
		request.setAttribute("topTr", service.getTopTr("xssx", new RequestForm()));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		
		request.setAttribute("user", user);
		return mapping.findForward("xssxManage");
	}
	
	/**
	 * ��ҵȥ��¼��
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
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			NjjsJyglModel model = new NjjsJyglModel();
			BeanUtils.copyProperties(model, form);
			
			String message = ""; 
			if("no".equalsIgnoreCase(sfcz)){
				message = service.saveXssx(model) ? "����ɹ���" : "����ʧ�ܣ�";
			}else{
				message = service.updateXssx(model) ? "����ɹ���" : "����ʧ�ܣ�";
			}
			if("����ɹ���".equals(message)){//�����ͥסַ�͵绰��Ϣ
				String jtdz=request.getParameter("jtdz");
				String lxdh=request.getParameter("lxfs");
				service.saveXsJtdzAndLxdh(model.getXh(), jtdz, lxdh);
			}
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getXssxInfo(pkValue); // ��ҵ����Ϣ
		rs.put("lxfs", rs.get("lxfs")!=null&&rs.get("lxfs").length()<2?null:rs.get("lxfs"));//����һ����ϵ��ʽ
		
		request.setAttribute("sfcz", sfcz);
		request.setAttribute("doType", doType);
		request.setAttribute("byqxList", service.getByqxList());
		request.setAttribute("rs", rs);
		
		return mapping.findForward("xssxUpdate");
	}
	
	/**
	 * ��ҵȥ�����
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
		// �����ѯ
		request.setAttribute("rs", service.byqxhzQuery(model));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		
		setWriteAbleAndTitle(request, "jygl_byqxhz.do");
		return mapping.findForward("byqxQuery");
	}
	
	/**
	 * ѧ��ʵϰ����
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
	 * ������
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
	 * ������
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
