package xsgzgl.xljk.tsxswh;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.szdw.bjlh.fdyzp.BjlhFdyzpForm;
import xgxt.szdw.bjlh.fdyzp.BjlhFdyzpService;
import xgxt.utils.FormModleCommon;
public class TsxswhAction extends BasicExtendAction{
	/**
	 * 特殊学生维护查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxswhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "xljk_zjzy_tsxswh.do");
		TsxswhService service = new TsxswhService();
		TsxswhForm myForm = (TsxswhForm) form;
		String doType = request.getParameter("doType");
		//删除
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.deleteTsxsXX(myForm.getCheckVal());
			//boolean flag = service.deleteTsxsXX(request.getParameter("pkValue").split("!!!SplitOne!!!"));
			if (flag) {
				request.setAttribute("message", "删除成功！");
			} else {
				request.setAttribute("message", "删除失败！");
			}
		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("tsxswh"));
		request.setAttribute("rs", service.getTableList(myForm, request));
		// write和titile
		setWriteAbleAndTitle(request, "xljk_zjzy_tsxswh.do");

		
		request.setAttribute("realTable", "XG_XLJK_ZJZYY_TSXSXXB"); // 导入表
		request.setAttribute("tableName", "view_xljk_zjzyy_tsxsxxb"); // 导出视图

		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tsxswhManage");
	}
	/**
	 * 特殊学生维护编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxswhEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxswhService service = new TsxswhService();
		TsxswhForm myForm = (TsxswhForm) form;
		String doType = request.getParameter("doType");
		HashMap<String,String> rs = new HashMap<String, String>();
		rs.put("xn", Base.currXn);
		//保存
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveTsxsXX(myForm,request);
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
			rs = service.getOneTsxsXX(pkValue.split("!!!SplitOne!!!"));
		}else if("add".equalsIgnoreCase(doType)){
			String act = request.getParameter("act");
			if(act!=null&&"hx".equalsIgnoreCase(act)){
				rs.put("xn", myForm.getXn());
				rs.put("xq", myForm.getXq());
				rs.put("xh", myForm.getXh());
				rs.putAll(service.getXsxx(myForm.getXh()));
				rs.put("sbsj", myForm.getSbsj());
				rs.put("zywt", myForm.getZywt());
				rs.put("brth", myForm.getBrth());
				rs.put("lxjs", myForm.getLxjs());
				rs.put("bz", myForm.getBz());
				rs.putAll(service.getOneTsxsXX(new String[]{myForm.getXn(),myForm.getXq(),myForm.getXh()}));
			}
			
		}
		request.setAttribute("rs", rs);
		// write和titile
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		setWriteAbleAndTitle(request, "xljk_zjzy_tsxswh.do");
		request.setAttribute("doType", doType);
		return mapping.findForward("tsxswhEdit");
	}
}
