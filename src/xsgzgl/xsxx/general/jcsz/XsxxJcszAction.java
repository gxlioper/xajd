package xsgzgl.xsxx.general.jcsz;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

public class XsxxJcszAction extends BasicAction {
	
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	/**
	 * 参数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问学生信息-基础设置-参数设置-SHLID:{shlid}")
	public ActionForward csSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		XsxxJcszForm model = (XsxxJcszForm) form;
		XsxxJcszService service = new XsxxJcszService();
		if ("save".equals(doType)) {
			boolean result=service.jcSz(model);
			request.setAttribute("result", result);//保存增加结果
		}
		HashMap<String, String> map = service.getOnesCssz();
		if(null!=map&&map.size()!=0){
			model.setKgzt(map.get("kgzt"));
			model.setShlid(map.get("shlid"));
			model.setSqkssj(map.get("sqkssj"));
			model.setSqjssj(map.get("sqjssj"));
			model.setShkg(map.get("shkg"));
			model.setShkssj(map.get("shkssj"));
			model.setShjssj(map.get("shjssj"));
			model.setSqcs(map.get("sqcs"));
		} else {
			model.setSqcs("0");
		}
		HashMap<String, String> rs=service.splCx();
		if(rs!=null && !rs.isEmpty()){
			request.setAttribute("rs", rs);
		}
		// 以下为公共配置项
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xsxx"));
		request.setAttribute("doType", doType);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "general_xsxx_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
}
