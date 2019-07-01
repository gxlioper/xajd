package xsgzgl.gygl.cssz;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;

import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_��������action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class CsszAction extends BasicExtendAction{
	
	/**
	 * ʱ�俪�ز�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public ActionForward sjszManage(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszService service = new CsszService();
		
		// ����
		String doType = request.getParameter("doType");
		
		CsszForm myForm = (CsszForm)form;
		myForm.getPrimarykey_cbv();
		CsszModel model = new CsszModel();
		
		BeanUtils.copyProperties(model, myForm);
		
		if("xg".equalsIgnoreCase(doType)){
			String message = service.saveKqzt(model) ? "�����ɹ���" : "����ʧ�ܣ�"; 
			/*String message = flag ? "�޸ĳɹ�!":"�޸�ʧ��!";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);*/
			
			request.setAttribute("message", message);
			//return null;
		}
		
		List<String[]> rs = service.querySjsz(model);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		setWriteAbleAndTitle(request, "gyglnew_cssz_sjsz.do");
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("sjsz"));
		
		return mapping.findForward("sjszManage");
	}
	
	/**
	 * 
	 * @����:�޸����õ�����
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-22 ����01:26:11
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
	@SystemLog(description="���ʹ�Ԣ����-��������-����ʱ������-�޸�IDLIST:{idList}")
	public ActionForward qySjsz(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszService service = new CsszService();
		
		// ����
		String doType = request.getParameter("doType");
		String idList = request.getParameter("idList");
		CsszForm myForm = (CsszForm)form;
		idList.substring(0, idList.length()-1);
		request.setAttribute("idList", idList);
		myForm.getPrimarykey_cbv();
		CsszModel model = new CsszModel();
		BeanUtils.copyProperties(model, myForm);
		if("xg".equalsIgnoreCase(doType)){
			model.setPrimarykey_cbv(idList.split(","));
			String message = service.saveKqzt(model) ? "�����ɹ���" : "����ʧ�ܣ�"; 
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
		}
		
		
	
		return mapping.findForward("qySjsz");
	}
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@SystemLog(description="���ʹ�Ԣ����-��������-����ʱ������-����")
	public ActionForward czcsszManage(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm myForm = (CsszForm)form;
		CsszService service = new CsszService();
		String doType=request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			boolean flag=service.saveSjfw(myForm);
			request.setAttribute("result", flag);
		}
		HashMap<String, String> rs=new HashMap<String, String>();
		rs=service.getSjfw();
		request.setAttribute("rs", rs);
		setWriteAbleAndTitle(request, "gyglnew_cssz_czcssz.do");
		return mapping.findForward("czcsszManage");
	}
}
