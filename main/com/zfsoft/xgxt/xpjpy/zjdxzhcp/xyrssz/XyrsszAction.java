/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-18 ����09:29:50 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.xyrssz;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz.RsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq.XmsqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ѧԺ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-7-18 ����09:29:50 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyrsszAction extends SuperAction{
	private static final String url = "xpjpy_zhcp_xyrssz.do";
	
	/**
	 * @����: ѧԺ�������ò�ѯ�б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-7-18 ����02:00:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getXyrsszList (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
 		throws Exception {
		
		XyrsszForm model = (XyrsszForm)form;
		XyrsszService service = new XyrsszService();
		
		User user = getUser(request);
		
		/*��ѯ��������*/
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		XmwhService xmwhService = new XmwhService();
		/*��ȡ����������Ϣ*/
		HashMap<String, String> csszMap = xmwhService.getCsszMap();
		request.setAttribute("pjzq", csszMap.get("xn"));
		/*��ȡ��Ŀ����*/
		List<HashMap<String, String>> xmlxList = xmwhService.getXmlx();
		request.setAttribute("xmlxList", xmlxList);
		/*��ȡ��Ŀ����*/
		List<HashMap<String, String>> xmxzList = xmwhService.getXmxz();
		request.setAttribute("xmxzList", xmxzList);
		/*����path*/
		String path = "xpjpy_zhcp_xyrssz.do";
		request.setAttribute("path", path);
		/*����ϵͳ��ǰʱ��*/
		String dateFormat = "yyyy-MM-dd HH:mm";
		request.setAttribute("currDate", xmwhService.getCurrTime(dateFormat));
		
		/*���ѧԺ�������ý�ѧ������ʾ*/
		List<HashMap<String,String>> jxjList = service.getJxjze(model, user);
		request.setAttribute("jxjjeMap", jxjList.get(0));
		
		/*����ҳ�����Ȩ�޼���ͷ*/
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xyrsszList");
	}
	
	/**
	 * @����: �������û�����ѯ��ʽ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-19 ����09:58:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xyrszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XyrsszForm model = (XyrsszForm)form;
		XyrsszService service = new XyrsszService();
		
		User user = getUser(request);
		
		/*��ȡ��Ŀ���룬������Ŀ����������*/
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		
		RsszService rsszService = new RsszService();
		String rsfpnj = xmwhService.getRsfpnj(xmdm);
		/*���������꼶*/
		request.setAttribute("rsfpnj", rsfpnj);
		/*�õ����а���ѧ�����꼶*/
		List<String>  njList = rsszService.getNj();
		request.setAttribute("njList", njList);
		request.setAttribute("njArrList", njList);
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		/*���ݲ�ѯ*/
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getRsszList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		/*�Ƿ���ѧ��������Ŀ*/
		XmsqService xmsqservice = new XmsqService();
		boolean flag = xmsqservice.isExistsFlowData(xmdm);
		request.setAttribute("spzt", flag);
		/*����path*/
		String path = "xpjpy_zhcp_xyrssz.do";
		request.setAttribute("path", path);
		/*��ò�������*/
		request.setAttribute("rsjsfs", rsszService.getCsz("rsjsfs"));
		/*��Ŀ����Ŀ����*/
		request.setAttribute("xmje", request.getParameter("xmje"));
		request.setAttribute("xmdm", xmdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xyrsszCx");
	}
	
	/**
	 * @����: ѧԺ�������ã�ѧԺ��ʦ�޸��޸�����������  ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-7-20 ����11:52:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description="������������-��������-��Ŀ����-��������-����XMDM��{xmdm}")
	public ActionForward xmwhRsszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XyrsszForm myForm = (XyrsszForm)form;
		XyrsszService service = new XyrsszService();
		
		User user = getUser(request);
		if(SAVE.equalsIgnoreCase(myForm.getType())){
			String messageKey = null;
			
			messageKey = service.setZzrs(myForm,user);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		
		/*��������˺�ʱ��*/
		myForm.setZd1(user.getUserName());
		/*��ȷ��ʱ����*/
		DateFormat date = DateFormat.getDateTimeInstance();
		myForm.setZd2(date+"");
		
		XyrsszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}
	
	/**
	 * @����: ajax��֤�������޺ͽ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-7-20 ����04:51:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward rsszCheckAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XyrsszForm myForm = (XyrsszForm)form;
		User user = getUser(request);
		XyrsszService service = new XyrsszService();
		List<HashMap<String, String>> resultList = service.getJxjze(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	public ActionForward xmwhRsszYszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyrsszForm model = (XyrsszForm)form;
		XyrsszService service = new XyrsszService();
		/*��Ŀ�����õ�������*/
		int yszrs = service.getYszrs(model);
		String zme = "";
		if(model.getXmdm() != null){
			XmwhService xmwhService = new XmwhService();
			HashMap<String, String> hashMap = xmwhService.getDataById(model.getXmdm());
			if (hashMap != null) {
				zme = hashMap.get("rsfpz");
			}
		}
		response.setContentType("text/html;charset=gbk");
		Map<String, String> map = new HashMap<String, String>();
		map.put("yszrs", yszrs + "");
		map.put("zme", zme);
		response.getWriter().print(JSONObject.fromMap(map));
		return null;
	}
}
