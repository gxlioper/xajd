package xgxt.pjpy.hhgxy.zhszcp;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.pjpy.hhgxy.PjpyHhgxyActionForm;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyHhgxyZhszcpAction extends CommonAction {

	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	
	private PjpyHhgxyZhszcpService service = PjpyHhgxyZhszcpService
			.getInstance();

	/**
	 * 德育操行分维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dycxfWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyHhgxyActionForm hForm = (PjpyHhgxyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			hForm.setXydm(xydm);
		} else {
			xydm = hForm.getXydm();
		}
		zydm = hForm.getZydm();
		nj = hForm.getNj();
		hForm.setXm(DealString.toGBK(hForm.getXm()));
		String act = request.getParameter("act");
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String, String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		if ("qry".equalsIgnoreCase(act)) {//查询操作
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, hForm);
			titList = service.getDycxQryTitle();
			rsList = service.queryDycxf(model);
		} else if ("del".equalsIgnoreCase(act)) {//删除操作
			String rs = service.deleteDycxf(hForm.getCbv());
			if (StringUtils.isNull(rs)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failinfo", "操作完成,其中第" + rs + "条数据删除失败!");
			}
		}
		appendQryResult(request, titList, rsList);
		appendTableXx(request, "view_dychfb_hhgxy", "dychfb_hhgxy");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("dycxpage");
	}
	
	/**
	 * 德育分修改显示信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dycxfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyHhgxyActionForm hForm = (PjpyHhgxyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, hForm);
			boolean bFlag = service.updateDycxf(model, request);//保存数据
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		} else if ("view".equalsIgnoreCase(act)) {
			request.setAttribute("view", "yes");
		}
		request.setAttribute("cxList", service.getCxList());
		HashMap<String, String> rs = service.viewDycxf(pkValue);
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("dycxview");
	}
	
	public ActionForward dycxfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("");
	}
	
	//页面参数值获取
	public List<HashMap<String,String>>  getParams(HttpServletRequest request){
		Enumeration paramsEnum = request.getParameterNames();
		List<HashMap<String,String>> valueList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String[]> map_values = new HashMap<String, String[]>();
		int valueListSize = -1;
		while(paramsEnum.hasMoreElements()){
			String param = (String)paramsEnum.nextElement();
			if(param.startsWith("_")){
				String[] paramValue = request.getParameterValues(param);
				map_values.put(param.replace("_", ""), paramValue);
				valueListSize = paramValue.length;
			}
		}
		if(valueListSize > -1){
			for(int i=0;i<valueListSize;i++){
				valueList.add(new HashMap<String,String>());
			}
			Set<String> keySet = map_values.keySet();
			for(String key : keySet){
				String[] values = map_values.get(key);
				for(int i = 0;i<valueList.size();i++){
					HashMap<String,String> m = valueList.get(i);
					m.put(key, DealString.toGBK(values[i]));
				}
			}
		}
		return valueList;
	}
}
