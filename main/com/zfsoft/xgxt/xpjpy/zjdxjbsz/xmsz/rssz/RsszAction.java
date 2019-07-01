/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-1 ����09:25:00 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz;

import java.net.URLDecoder;
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
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq.XmsqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-��Ŀ����-��������
 * @�๦������: ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-6-1 ����09:25:00 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RsszAction extends SuperAction{
	private static final String url = "xpjpy_jbsz_xmsz.do";
	
	
	/**
	 * @����: ������ѯ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����11:12:52
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
	public ActionForward xmwhRsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszForm model = (RsszForm)form;
		User user = getUser(request);
		RsszService service = new RsszService();
		/*��ȡҳ���xmdm*/
		String xmdm = request.getParameter("xmdm");
		/*��ȡ������Ŀ�� ���������꼶 (����)*/
		XmwhService xmwhService = new XmwhService();
		String rsfpnj = xmwhService.getRsfpnj(xmdm);
		request.setAttribute("rsfpnj", rsfpnj);
		/*�õ����а���ѧ�����꼶*/
		List<String> njList = service.getNj();
		request.setAttribute("njList", njList);
		request.setAttribute("njArrList", njList);
		/*�꼶ѧԺרҵ�༶*/
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		/*��ѯ*/
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getRsszList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*�����Ŀ����*/
		String xmmc = request.getParameter("xmmc");
		xmmc = URLDecoder.decode(xmmc,"utf-8");
		request.setAttribute("xmmc", xmmc);
		/*�Ƿ���ѧ��������Ŀ*/
		XmsqService xmsqservice = new XmsqService();
		boolean flag = xmsqservice.isExistsFlowData(xmdm);
		request.setAttribute("spzt", flag);
		/*����path*/
		String path = "xpjpy_jbsz_xmsz.do";
		request.setAttribute("path", path);
		/*��ò�������*/
		request.setAttribute("rsjsfs", service.getCsz("rsjsfs"));
		/*��Ŀ����Ŀ����*/
		request.setAttribute("xmje", request.getParameter("xmje"));
		request.setAttribute("xmdm", xmdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszCx");
	}
	
	/**
	 * @����: ��ѯ��Ŀ����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-2 ����09:31:49
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
	public ActionForward xmwhRsszYszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszForm model = (RsszForm)form;
		RsszService service = new RsszService();
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
	
	/**
	 * @����: �������÷���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����09:02:51
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-��������-��������-����XMDM��{xmdm}")
	public ActionForward xmwhRsszBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszForm myForm = (RsszForm)form;
		RsszService service = new RsszService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String json = request.getParameter("json");
			List<RsszForm> list = JsonUtil.jsonToList(json,
					RsszForm.class);
			String messageKey;
			String fpfs = request.getParameter("fpfs");
			String zme = null;
			if (fpfs != null && fpfs.equals("zme")) {//�����ʽ
				zme = request.getParameter("zme");
			}
			String rsfpnj = request.getParameter("rsfpnj");//���������꼶
			messageKey = service.fpsz(myForm, list, zme,rsfpnj,user);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		RsszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}
	
	/**
	 * @����: ajax��֤�������޺ͽ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����05:28:44
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
		RsszForm myForm = (RsszForm)form;
		User user = getUser(request);
		RsszService service = new RsszService();
		List<HashMap<String, String>> resultList = service.getJxjze(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
}
