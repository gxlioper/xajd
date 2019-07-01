/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-16 ����09:54:23 
 */  
package com.zfsoft.xgxt.szdw.sdkj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�cmj 
 * @ʱ�䣺 2013-5-16 ����09:54:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwSdkjZdjskhAction extends SuperAction {
	
	private static final String url = "szdw_sdkj_zdjskh.do";
	
	/**
	 * �鿴����ͳ��
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cmj
	 * @���ڣ�2013-5-23 ����10:12:00
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
	public ActionForward zdjskh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdlskhForm zdlskhForm=(ZdlskhForm)form;
		SzdwSdkjZdjskhService service=new SzdwSdkjZdjskhService();
		request.setAttribute("nfList", service.getndList());
		request.setAttribute("yfList", Base.getYfList());
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("nf", Base.currNd);
		request.setAttribute("map", map);
		request.setAttribute("realTable", "zdlskhb");
		if (QUERY.equals(zdlskhForm.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(zdlskhForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "szdw_sdkj_zdjskh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jskhlr");
	}
	/**
	 * �鿴�·ݽ�ʦ¼����ϸ��¼
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cmj
	 * @���ڣ�2013-5-23 ����10:12:22
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
	public ActionForward ckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdlskhForm zdlskhForm=(ZdlskhForm)form;
		SzdwSdkjZdjskhService service=new SzdwSdkjZdjskhService();
		HashMap<String, String> map=new HashMap<String, String>();
		
		request.setAttribute("nfList", service.getndList());
		request.setAttribute("yfList", Base.getYfList());
		
		request.setAttribute("tableName", "zdlskhb");
		
		String nf=request.getParameter("nf");
		String yf=request.getParameter("yf");
		if(!"".equalsIgnoreCase(nf)){
			map.put("nf", nf);
		}
		if(!"".equalsIgnoreCase(yf)){
			map.put("yf", yf);
		}
		request.setAttribute("map", map);
		if (QUERY.equals(zdlskhForm.getType())){
			List<HashMap<String,String>> resultList = service.getPageListxx(zdlskhForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "szdw_sdkj_zdjskh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jskhck");
	}
	/**
	 * ɾ����ʦ���˼�¼
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cmj
	 * @���ڣ�2013-5-23 ����10:13:02
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
	public ActionForward delKhjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwSdkjZdjskhService service=new SzdwSdkjZdjskhService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	

}
