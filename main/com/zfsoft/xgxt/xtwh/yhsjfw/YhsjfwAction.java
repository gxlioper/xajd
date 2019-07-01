/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xtwh.yhsjfw;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀ���ά��
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class YhsjfwAction extends SuperAction {
	private String messageKey;

	private static final String url = "xtwh_yhsjfw.do?method=yhsjfwCx";
	
	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward yhsjfwCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YhModel myForm = (YhModel) form;
		YhsjfwService service = new YhsjfwService();

		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		List<HashMap<String, String>> yjbmList = service.getYjbmList();
		request.setAttribute("yjbmList", yjbmList);
		List<HashMap<String, String>> yhzForSzdwList = service
				.getYhzForSzdwList();
		request.setAttribute("yhzForSzdwList", yhzForSzdwList);
		String path = "xtwh_yhsjfw.do?method=yhsjfwCx";
		request.setAttribute("path", path);

		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("yhsjfwCx");
	}
	
	/**
	 * 
	 * @����:�û����ݷ�Χ��Ȩ
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-26 ����02:37:18
	 * @�޸ļ�¼: 
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
	public ActionForward yhsjfwSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhModel myForm = (YhModel) form;
		YhsjfwService service = new YhsjfwService();
		String ids = request.getParameter("ids");
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			List<HashMap<String, String>> njxyzyList = service.getNjxyzyList();
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("njxyzyList", njxyzyList);
			map.put("ids", ids);
			if(ids != null && ids.indexOf(",") == -1){
				HashMap<String, String> dataMap = service.getDataById(ids);
				map.putAll(dataMap);
			}
			JSONObject json = JSONObject.fromMap(map);
			response.getWriter().print(json);
			return null;
		}else if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String sjfwdmSubmit = request.getParameter("sjfwdmSubmit");
			String sjfwmcSubmit = request.getParameter("sjfwmcSubmit");
			YhsjfwModel yhsjfwModel = new YhsjfwModel();
			yhsjfwModel.setIds(ids);
			yhsjfwModel.setSjfwdm(sjfwdmSubmit);
			yhsjfwModel.setSjfwmc(sjfwmcSubmit);
			boolean result = service.runDeal(yhsjfwModel);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		request.setAttribute("ids", ids);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("yhsjfwSq");
	}


}
