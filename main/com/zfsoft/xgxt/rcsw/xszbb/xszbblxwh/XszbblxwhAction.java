/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:12:54 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh;

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
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�������ģ��
 * @�๦������: TODO(ѧ��֤��������ά��) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-16 ����05:17:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbblxwhAction extends SuperAction {

	private static final String url = "rcsw_xszbb_bblxwh.do";
	
	/**
	 * 
	 * @����:TODO(ѧ��֤��������List)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����08:35:07
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
	public ActionForward xszbblxwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbblxwhForm model = (XszbblxwhForm) form;
		XszbblxwhService service = new XszbblxwhService();
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getBblxPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_xszbb_bblxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszbblxwhManage");
	}
	
	
	/**
	 * 
	 * @����:TODO(���� ѧ��֤�������� )
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����11:31:39
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
	@SystemLog(description="�����ճ�����-֤������-֤����������ά��-����XSZBBLMC:{xszbblxmc},SHLC:{shlc}")
	public ActionForward addXszbblx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbblxwhForm model = (XszbblxwhForm) form;
		XszbblxwhService service = new XszbblxwhService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveBblxInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		
		String xszbblxdm = service.changeXszbblxdm();
		model.setXszbblxdm(xszbblxdm);
		request.setAttribute("model", model);
		return mapping.findForward("addXszbblx");
	}
	
	/**
	 * 
	 * @����:TODO(�޸�ѧ��֤�������� )
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����11:31:09
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
	@SystemLog(description="�����ճ�����-֤������-֤����������ά��-�޸�XSZBBLXDM:{xszbblxdm},XSZBBLMC:{xszbblxmc},SHLC:{shlc}")
	public ActionForward updateXszbblx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbblxwhService service = new XszbblxwhService();
		XszbblxwhForm myForm = (XszbblxwhForm) form;
		XszbblxwhForm model = service.getXszbblxwhForm(myForm,myForm.getXszbblxdm());
	
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveBblxInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("updateXszbblx");
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����11:32:05
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
	@SystemLog(description="�����ճ�����-֤������-֤����������ά��-ɾ��VALUES:{values}")
	public ActionForward delXszbblx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbblxwhService service = new XszbblxwhService();	
		String values = request.getParameter("values");
	
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteXszbblxwhInfo(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
}
