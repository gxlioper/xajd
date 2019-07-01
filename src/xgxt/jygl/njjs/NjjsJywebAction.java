package xgxt.jygl.njjs;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.jygl.comman.JyWEBService;
import xgxt.jygl.comman.JyWebCheckSessionAciton;
import xgxt.jygl.comman.JyglForm;
import xgxt.utils.Fdypd;

/***
 * 南京技师就业网个性化需求
 */
public class NjjsJywebAction extends JyWebCheckSessionAciton {

	
	/**
	 * 学生报名审核管理
	 */
	public ActionForward xsbmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "xg_jyweb_njjs_xsbmshb";
		String viewName = "xg_view_jyweb_njjs_xsbmshb";
		String[] colList = new String[]{"pkValue","xh","xm","xymc","dwmc","gwmc","bzrsh","xysh","xxsh"};
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("jyweb_userName");
		String userType = (String) session.getAttribute("jyweb_userType");
		
		if ("xy".equals(userType)){
			if (Fdypd.isBzr(userName,"")){
				request.setAttribute("annexTerm", " and exists (select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='"+userName+"')");
			} else {
				request.setAttribute("annexTerm", " and xydm=(select szbm from yhb where yhm='"+userName+"')");
			}
		}
		
		
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		request.setAttribute("nowTime", service.getNow());
		request.setAttribute("topTr", service.getColumn(viewName,colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("xsbmshManage");
	}

	
	
	/**
	 * 学生上报审核
	 * @throws Exception
	 */
	public ActionForward xsbmAuditing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();

		String tableName = "xg_jyweb_njjs_xsbmshb";
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("jyweb_userName");
		String userType = (String) session.getAttribute("jyweb_userType");

		HashMap<String, String> valueMap = new HashMap<String, String>();
		if ("admin".equals(userType)){
			valueMap.put("xxsh", myForm.getSave_shzt());
			valueMap.put("xxshr", userName);
			valueMap.put("xxshsj", service.getNow());
			//valueMap.put("xxshyj", myForm.getSave_shyj());

		} else if ("xy".equals(userType)){
			boolean sfdb = Fdypd.isBzr(userName,"");
			
			if (sfdb){
				valueMap.put("bzrsh", myForm.getSave_shzt());
				valueMap.put("bzrshr", userName);
				valueMap.put("bzrshsj", service.getNow());
				//valueMap.put("bzrshyj", myForm.getSave_shyj());
			} else {
				valueMap.put("xysh", myForm.getSave_shzt());
				valueMap.put("xyshr", userName);
				valueMap.put("bzrshsj", service.getNow());
				//valueMap.put("xyshyj", myForm.getSave_shyj());
			}
		}
		
		auditingBatchOperation(request, getValueArrayMap(request,
				PRIFIX_PRIMARY_KEY), valueMap, tableName);

		return xsbmshManage(mapping, form, request, response);
	}

	
	
	
	
	
}
