package xsgzgl.gygl.gyjldmgl;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

public class GyjldmglAction extends BasicAction{
	/**
	 * ��Ԣ���ɴ������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��Ԣ���ɴ���ά��-���ɴ���-{doType}ά��JLDLDM:{jldldm},JLDLMC:{jldlmc}")
	public ActionForward gyjldlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// ����
		request.setAttribute("path", "gyglnew_gyjldmgl_gyjldmgl.do");
		String doType = request.getParameter("doType");
		GyjldmglForm myForm = (GyjldmglForm) form;
		GyjldmglService service = new GyjldmglService();
		
		if (!Base.isNull(doType)) {
			String message = "��������";
			if ("add".equals(doType)) {// ����
				message = service.saveJldlInfo(myForm, "add");
				//String message = flag ? "�޸ĳɹ�!":"�޸�ʧ��!";
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;

				
			} else if ("update".equals(doType)) {// �޸�
				message = service.saveJldlInfo(myForm, "update");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("del".equalsIgnoreCase(doType)) {// ɾ��
				message = service.deleteJldlInfo(myForm);
			} 
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getJldlList(myForm);

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("jldl"));
		FormModleCommon.commonRequestSet(request);
		/*�㽭���θ��Ի�����*/
		if(Base.xxdm.equals("12867")){
			return mapping.findForward("zjlydmwh");
		}
		return mapping.findForward("gyjldlManage");
	}
	/**
	 * 
	 * @����:���ӹ�Ԣ����������ά��������
	 * @���ߣ�dlq[���ţ�995]
	 * @���ڣ�2013-8-26 ����10:50:15
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
	public ActionForward gyjldlZjxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// ����
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		if(UPDATE.equalsIgnoreCase(doType)){
			String jldldm = URLDecoder.decode(request.getParameter("jldldm"),"utf-8");
			String jldlmc = URLDecoder.decode(request.getParameter("jldlmc"),"utf-8");
			request.setAttribute("jldldm", jldldm);
			request.setAttribute("jldlmc", jldlmc);
		}

		return mapping.findForward("gyjldlZj");
	}
	
	/**
	 * ��Ԣ����������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��Ԣ���ɴ���ά��-�������-{doType}ά��JLLBDM:{jllbdm},JLLBMC:{jllbmc},JLLBKF:{jllbkf},JLDLDM:{jldldm}")
	public ActionForward gyjllbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// ����
		request.setAttribute("path", "gyglnew_gyjldmgl_gyjldmgl.do");
		String doType = request.getParameter("doType");
		GyjldmglForm myForm = (GyjldmglForm) form;
		GyjldmglService service = new GyjldmglService();

		if (!Base.isNull(doType)) {
			String message = "��������";
			if ("add".equals(doType)) {// ����
				message = service.saveJllbInfo(myForm, "add");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("update".equals(doType)) {// �޸�
				message = service.saveJllbInfo(myForm, "update");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("del".equalsIgnoreCase(doType)) {// ɾ��
				message = service.deleteJllbInfo(myForm);
			} 
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getJllbList(myForm);

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("jllb"));
		request.setAttribute("jldlList", service.getJllbListMap(myForm));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gyjllbManage");
	}
	/**
	 * 
	 * @����:���ӹ�Ԣ����������ά��������
	 * @���ߣ�dlq[���ţ�995]
	 * @���ڣ�2013-8-26 ����10:50:15
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
	public ActionForward gyjllbZjxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjldmglForm myForm = (GyjldmglForm) form;
		GyjldmglService service = new GyjldmglService();
		// ����
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		if(UPDATE.equalsIgnoreCase(doType)){
			String jllbdm = request.getParameter("jllbdm");
			String jllbmc = request.getParameter("jllbmc");
			String jldldm = request.getParameter("jldldm");
			String jllbkf = request.getParameter("jllbkf");
			request.setAttribute("jllbdm", jllbdm);
			request.setAttribute("jllbmc", jllbmc);
			request.setAttribute("jldldm", jldldm);
			request.setAttribute("jllbkf", jllbkf);
			
		}
		request.setAttribute("topTr", service.getTopTr("jllb"));
		request.setAttribute("jldlList", service.getJllbListMap(myForm));
		
		return mapping.findForward("gyjllbZj");
	}
	
	/**
	 * ��Ԣ����������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��Ԣ���ɴ���ά��-�������-{doType}ά��CFLBDM:{cflbdm},CFLBMC:{cflbmc}")
	public ActionForward gyjlcfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// ����
		request.setAttribute("path", "gyglnew_gyjldmgl_gyjldmgl.do");
		String doType = request.getParameter("doType");
		GyjldmglForm myForm = (GyjldmglForm) form;
		GyjldmglService service = new GyjldmglService();

		if (!Base.isNull(doType)) {
			String message = "��������";
			if ("add".equals(doType)) {// ����
				message = service.saveCflbInfo(myForm, "add");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("update".equals(doType)) {// �޸�
				message = service.saveCflbInfo(myForm, "update");
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			} else if ("del".equalsIgnoreCase(doType)) {// ɾ��
				message = service.saveCflbInfo(myForm,"delete");
			} 
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getCflbList(myForm);

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("cflb"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gyjlcfManage");
	}
	/**
	 * 
	 * @����:���ӹ�Ԣ����������ά��������
	 * @���ߣ�dlq[���ţ�995]
	 * @���ڣ�2013-8-26 ����10:50:15
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
	public ActionForward gyjlcfZJxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		if(UPDATE.equalsIgnoreCase(doType)){
			String cflbdm = request.getParameter("cflbdm");
			String cflbmc = request.getParameter("cflbmc");
			request.setAttribute("cflbdm", cflbdm);
			request.setAttribute("cflbmc", cflbmc);
		}
		
		return mapping.findForward("gyjlcfZj");
	}
	
	/*�㽭���θ��Ի�start*/
	/**
	 * 
	 * @����:�㽭���θ��Ի���ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-4-28 ����08:55:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward gyglDmwhcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GyjldmglForm modle = (GyjldmglForm)form;
		User user = getUser(request);
		List<HashMap<String, String>> resultlist = new ZjlyGyglDao().getPageList(modle, user);
		JSONArray datalist = JSONArray.fromObject(resultlist);
		response.getWriter().print(datalist);
		return null;
	}
	
	public ActionForward addZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		return mapping.findForward("add");
	}
	
	public ActionForward updateZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		GyjldmglForm myForm = (GyjldmglForm) form;
		ZjlyGyglDao dao = new ZjlyGyglDao(); 
		GyjldmglForm model = dao.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		request.setAttribute("dm", model.getGyjllbdldm());
		request.setAttribute("jkf", model.getLb().equals("jf") ? "�ӷ���":"�۷���");
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @����:�鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-4-28 ����02:48:27
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
	public ActionForward ckZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		GyjldmglForm myForm = (GyjldmglForm) form;
		ZjlyGyglDao dao = new ZjlyGyglDao(); 
		GyjldmglForm model = dao.getModel(myForm);
		request.setAttribute("jg", model);
		request.setAttribute("jkf", model.getLb().equals("jf") ? "�ӷ���":"�۷���");
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-4-28 ����02:47:47
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
	public ActionForward saveZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		GyjldmglForm modle = (GyjldmglForm)form;
	    ZjlyGyglDao dao = new ZjlyGyglDao(); 
	    boolean flag = dao.checkIsExists(modle);
	    String message = "";
	    HashMap<String, String> map = new HashMap<String, String>();
		if(modle.getType().equals("add")){
			if(flag){
				message = "����������Ѵ��ڣ���ȷ�ϣ�";
			    response.getWriter().print(getJsonMessage(message));
			    return null;
			}
			 flag = dao.runInsert(modle);
			 message = flag ? "����ɹ���" :"����ʧ�ܣ�";
			 response.getWriter().print(getJsonMessage(message));
			 return null;
		}else{
			if(flag){
				message = "����������Ѵ��ڣ���ȷ�ϣ�";
			    response.getWriter().print(getJsonMessage(message));
			    return null;
			}
			 flag = dao.runUpdate(modle);
			 message = flag ? "����ɹ���" :"����ʧ�ܣ�";
			 response.getWriter().print(getJsonMessage(message));
			 return null;
			
		}
	}
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-4-28 ����02:47:16
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
	public ActionForward delZjlydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		//���id
		String values = request.getParameter("values");
		String message = "";
		ZjlyGyglDao dao = new ZjlyGyglDao(); 
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(dao.checkIsUsingNow(ids)){
				message = "��ɾ������Ŀ���ڱ�ʹ�ã�";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = dao.runDelete(ids);
			boolean result = num > 0;
			 message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	protected JSONObject getJsonMessage(String message){
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}
	/*�㽭���θ��Ի�end*/
}
