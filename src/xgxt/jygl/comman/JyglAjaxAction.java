package xgxt.jygl.comman;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.String.StringUtils;

/**
 * <p>��ҵ����</p>
 * <p>
 * 	����Ajax������<br>
 * </p>
 * @author Administrator
 */
public class JyglAjaxAction extends DispatchAction {

	
	/**
	 * �����û���λ�����ȡ���˵�λ��ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getYrdwInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String yrdwdm = request.getParameter("yrdwdm");
		JyglService service = new JyglService();
		
		HashMap<String, String> map = 	service.getYrdw(yrdwdm);
		
		JSONObject yrdwInfo = JSONObject.fromObject(map); //תΪjson��ʽ
		response.setContentType("text/html;charset=gbk"); //ajax���󷵻�����ת�룬�������������
		response.getWriter().print(yrdwInfo);
		return null;
	}
	
	
	
	/**
	 * �������ܵ�λ�����б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZgdwOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zgdwmc = request.getParameter("zgdw");
		JyglService service = new JyglService();
		
		if (StringUtils.isNotNull(zgdwmc)){
			zgdwmc = URLDecoder.decode(zgdwmc, "UTF-8");//new String(zgdwmc.getBytes("GBK"),"UTF-8");
		}
		
		List<HashMap<String, String>> map = service.getZgdwOption(zgdwmc);
		JSONArray zgdwList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(zgdwList);
		return null;
	}
	
	
	/**
	 * �����������������б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getLsbmOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zgdwmc = request.getParameter("lsbm");
		
		if (StringUtils.isNotNull(zgdwmc)){
			zgdwmc = URLDecoder.decode(zgdwmc, "UTF-8");//new String(zgdwmc.getBytes("GBK"),"UTF-8");
		}
		
		JyglService service = new JyglService();
		
		
		List<HashMap<String, String>> map = service.getLsbmOption(zgdwmc);
		JSONArray zgdwList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(zgdwList);
		return null;
	}
	
	
	
	
	/**
	 * ��ҵ��ȷ����Ϣ
	 */
	public ActionForward getBysxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xh = request.getParameter("xh");
		JyglService service = new JyglService();
		
		HashMap<String,String> map = service.getBysxx(xh);
		JSONObject bysxx = JSONObject.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bysxx);
		return null;
	}
}
