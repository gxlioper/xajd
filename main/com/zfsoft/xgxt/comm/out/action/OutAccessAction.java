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
 * ������������棬��ת
 * 
 */
public class OutAccessAction extends SuperAction {

	private static final long serialVersionUID = 1L;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String toPage = request.getParameter("toPage");//��ת��ҳ��
		String gnbh = request.getParameter("gnbh");//���ܱ�ţ��ɵ����������ṩ����ͳ�Ʋ�ѯtjcx��ͳ�Ʊ���tjbb
		String gnmk = request.getParameter("gnmk");//����ģ�飬�磺ѧ����Ϣ��xsxx
		String drmkdm = request.getParameter("drmkdm");//����ģ����루���õ���ģ���ʱ����ʹ�õĲ���������ģ�鲻ʹ�øò�����
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
			afterE = URLEncoder.encode(afterE,"UTF-8");//ת��
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	toPage += "?param=" + afterE;
		response.sendRedirect(toPage);

		return null;
	}

}