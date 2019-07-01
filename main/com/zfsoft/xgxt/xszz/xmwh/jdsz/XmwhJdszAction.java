/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.jdsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhDao;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��-�������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhJdszAction extends SuperAction {
	private XmwhJdszService service = new XmwhJdszService();
	private String messageKey;
	
	private static final String url = "xszz_xmwh.do?method=xmwhCx";
	
	/**
	 * 
	 * @����:������ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xmwhJdszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhJdszForm myForm = (XmwhJdszForm) form;
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service.getByXmdm(xmdm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//�Ƿ���ѧ��������Ŀ
		XszzSqshService xszzSqshService = new XszzSqshService();
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		request.setAttribute("spzt", flag);
		
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhJdszCx");
	}

	/**
	 * 
	 * @����:������÷���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-�������-�޸ı���XMDM:{xmdm}")
	public ActionForward xmwhJdszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhJdszForm myForm = (XmwhJdszForm) form;
		String xmdm = request.getParameter("xmdm");
		String xmdms = request.getParameter("xmdms");

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = false;
			try {
				result = service.jdsz(xmdm,xmdms);
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
			} catch (Exception e) {
				e.printStackTrace();// //�쳣��ӡ����������////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XmwhJdszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * 
	 * @����:��ѯ������Ŀ����������ǰ��Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-24 ����02:20:21
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
	public ActionForward xmwhJdszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		// ��ȡ������������Ŀ�б�
		try {
			List<HashMap<String, String>> resultList = xmwhService
					.getOthers(xmdm);
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(resultList));
		} catch (Exception e) {
			// TODO �Զ����� catch ��........�쳣����
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @����:������Ŀ���룬��ȡ�����õĲ��ɼ����Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-24 ����10:14:13
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public ActionForward getKjdxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		List<HashMap<String, String>> resultList = service.getKjdxm(xmdm);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", resultList);
		
		XmwhDao xmwhDao = new XmwhDao();
		HashMap<String, String> data = xmwhDao.getDataById(xmdm);
		String jdkg = "";
		if(data != null){
			jdkg = data.get("jdkg");
		}
		map.put("jdkg", jdkg);		
		JSONObject dataMap = JSONObject.fromMap(map);
		
		response.getWriter().print(dataMap);
		return null;
	}
}
