package com.zfsoft.xgxt.comm.out.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DBEncrypt;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;

/**
 * 接入第三方界面，跳转
 * 
 */
public class OutAccessAction extends SuperAction {

	private static final long serialVersionUID = 1L;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String toPage = request.getParameter("toPage");//跳转到页面
		String gnbh = request.getParameter("gnbh");//功能编号，由第三方功能提供，如统计查询tjcx，统计报表tjbb
		String gnmk = request.getParameter("gnmk");//功能模块，如：学生信息：xsxx
		String drmkdm = request.getParameter("drmkdm");//导入模块代码（调用导入模块的时候需使用的参数，其他模块不使用该参数）
		String kzszid = request.getParameter("kzszid");
		String path = "out_access.do";
		path += "?gnbh="+gnbh+"&gnmk="+gnmk+"&drmkdm="+drmkdm+"&toPage="+ toPage;
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		String menu = (String) request.getAttribute("title");

		String yhm =user.getUserName();//
		
		String sqlQx = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		sqlQx = java.net.URLEncoder.encode(sqlQx, "GBK");
		String param = "yhm=" + yhm + "&gnbh=" + gnbh + "&gnmk=" + gnmk + "&drmkdm=" + drmkdm +"&menu=" + menu + "&timestamp=" + new Date().getTime();
		param += "&sqlQx=" + sqlQx;
		if(kzszid != null){
			param += "&kzszid=" + kzszid;
		}
		DBEncrypt p = new DBEncrypt();
    	String afterE = p.eCode(param);
		try {
			afterE = URLEncoder.encode(afterE,"UTF-8");//转码
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	toPage += "?param=" + afterE;
		response.sendRedirect(toPage);

		return null;
	}

}