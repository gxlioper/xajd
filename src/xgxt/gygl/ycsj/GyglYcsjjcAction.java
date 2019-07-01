package xgxt.gygl.ycsj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.base.Excel2Oracle;
import xgxt.gygl.GyglCommForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * ��Ԣ����-�쳣���ݼ����
 * @author ³��
 * @version 1.0
 */
public class GyglYcsjjcAction extends BasicAction{

	
	/**
	 * �쳣���ݼ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ycsjjcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		request.setAttribute("path", "gygl_zsjg_ycsj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ycsjjcManage");
	}
	
	
	/**
	 * �쳣���ݲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryYcsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglCommForm model = (GyglCommForm) form;
		GyglYcsjjcService service = new GyglYcsjjcService();
		
		if (StringUtils.isNotNull(model.getYclx())){
			HashMap<String,Object> result = service.getYcsjByType(model);
			
			if (null != result){
				request.setAttribute("tableName", result.get("tableName"));//�쳣���������ڵı�
				request.setAttribute("pkKey", result.get("pkKey"));//�쳣���ݴ��ڱ������
				request.setAttribute("prompt", result.get("prompt"));//
				request.setAttribute("rs", result.get("data"));
				request.setAttribute("topTr", result.get("topTr"));
			}
		}
		
		request.setAttribute("path", "gygl_zsjg_ycsj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("queryYcsj");
	}
	
	
	
	/**
	 * �쳣����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delYcsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String tableName = request.getParameter("tableName");
		String pkKey = request.getParameter("pkKey");
		String[] pkValues = request.getParameterValues("pkValue");
		GyglYcsjjcService service = new GyglYcsjjcService();
		
		request.setAttribute("message", service.delYcsj(tableName, pkKey, pkValues) ? DEL_SUCCESS : DEL_FAIL);
		
		return queryYcsj(mapping, form, request, response);
	}
	
	
	
	/**
	 * �쳣���ݵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expYcsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyglCommForm model = (GyglCommForm) form;
		GyglYcsjjcService service = new GyglYcsjjcService();
		
		if (StringUtils.isNotNull(model.getYclx())){
			model.getPages().setPageSize(Integer.MAX_VALUE);
			HashMap<String,Object> result = service.getYcsjByType(model);
			String[] topTr = (String[]) result.get("topTr");
			List<String[]> data = (List<String[]>) result.get("data");
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(data,topTr,topTr, response.getOutputStream());
		}
		
		
		return null;
	}
	
}
