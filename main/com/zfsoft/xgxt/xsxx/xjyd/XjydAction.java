/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-19 ����02:36:56 
 */  
package com.zfsoft.xgxt.xsxx.xjyd;

import java.util.ArrayList;
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

import xgxt.action.Base;
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
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ���춯
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-11-28 ����09:40:48 
 * @�汾�� V5.12.20
 */
public class XjydAction extends SuperAction {
	
	private static final String thlj = "xjyd.do?method=xjydlbShpzList";
	
	private static final String mkdm = "xjydshlb.do";
	
	private static final String url = "xjyd_xjlbgl.do";
	
	/**
	 * 
	 * @����:ѧ���춯����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����10:06:52
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
	public ActionForward xjydlbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydForm model = (XjydForm) form;
		XjydService service = new XjydService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getXjlbPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xjyd_xjlbgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xjydlbList");
	}

	/**
	 * 
	 * @����:ѧ���춯�����������б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����10:06:52
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
	public ActionForward xjydlbShpzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydForm model = (XjydForm) form;
		XjydService service = new XjydService();
		
		if (QUERY.equals(model.getType())){

			List<HashMap<String,String>> resultList = service.getXjlbShpzPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xjyd_xjlbshpz.do";
		request.setAttribute("path", path);
		
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xjydlbShpzList");
	}
	
	/**
	 * ����ѧ���춯���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-�춯������ά��-����XJLBDM:{xjlbdm},XJLBMC:{xjlbmc},SFYXJ:{sfyxj},SFZX:{sfzx}")
	public ActionForward xjydlbAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydForm model = (XjydForm) form;
		XjydService service = new XjydService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			
				boolean lbdmExists = service.lbdmExists(model.getXjlbdm());
			
				
				boolean lbmcExist = service.lbmcExist(model.getXjlbmc());
				
				//��֤�����Ƿ��Ѵ���
				if (lbdmExists){	
					
					String message=MessageUtil.getText(MessageKey.XJYD_XMLBMC_EXIST,model.getXjlbdm());
					response.getWriter().print(getJsonMessage(message));
					return null;
				}else if(lbmcExist){	//��֤�����Ƿ��ѱ�ʹ��
					String message=MessageUtil.getText(MessageKey.XJYD_XMLBMC_EXIST,model.getXjlbmc());
					response.getWriter().print(getJsonMessage(message));
					return null;
				
				}
				
				boolean result = service.saveXjydlb(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			
			return null;
			
		}
		
		return mapping.findForward("xjydlbAdd");
	}
	
	
	/**
	 * ����ѧ���춯����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-�춯����������-����XJLBDM:{xjlbdm},SHLCID:{shlcid}")
	public ActionForward xjydlbShpzAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydForm model = (XjydForm) form;
		XjydService service = new XjydService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean result = service.saveXjydlbShpz(model);
			//boolean result = true;
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		//��ȡѧ����Ϣ�����
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xsxx");
		request.setAttribute("shlcList", shlc);
		
		//ѧ�����LIST
		List<HashMap<String,String>> xjlbList = service.getXjlbList("2","");
		request.setAttribute("xjlbList", xjlbList);

		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		FormModleCommon.commonRequestSet(request);
		this.saveToken(request);
		return mapping.findForward("xjydlbShpzAdd");
	}
	
	/**
	 * �޸�ѧ���춯���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-�춯������ά��-�޸�XJLBDM:{xjlbdm},XJLBMC:{xjlbmc},SFYXJ:{sfyxj},SFZX:{sfzx}")
	public ActionForward xjydlbUpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydForm myForm = (XjydForm) form;
		XjydService service = new XjydService();
		

		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			
			XjydForm myOldForm = service.getModel(myForm.getXjlbdmold());
			boolean lbmcExist = service.lbmcExist(myForm.getXjlbmc());
			boolean lbdmExists = service.lbdmExists(myForm.getXjlbdm());
			
			//�жϴ����Ƿ��ѱ�ʹ��
			if(service.lbdmExist(myForm.getXjlbdmold())){
				
				String message=MessageUtil.getText(MessageKey.XYJD_UPDATE_DMEXIST,myOldForm.getXjlbdm()+":"+myOldForm.getXjlbmc());
				response.getWriter().print(getJsonMessage(message));
			//��֤�����Ƿ��Ѵ���
			}else if (!myForm.getXjlbdm().equals(myOldForm.getXjlbdm())&&lbdmExists){
				
				String message=MessageUtil.getText(MessageKey.XJYD_XMLBMC_EXIST,myForm.getXjlbdm());
				response.getWriter().print(getJsonMessage(message));
				
			//��֤�����Ƿ��Ѵ���
			}else if (!myForm.getXjlbmc().equals(myOldForm.getXjlbmc())&&lbmcExist){
				
				String message=MessageUtil.getText(MessageKey.XJYD_XMLBMC_EXIST,myForm.getXjlbmc());
				response.getWriter().print(getJsonMessage(message));
				
			}else{
				
				boolean result = service.updXjydlb(myForm);
				
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
			
		}
		
		
		XjydForm model = service.getModel(myForm);

		model.setXjlbdmold(model.getXjlbdm());
		
		List<HashMap<String,String>> xjList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("xjdm", "0");
		map.put("xjmc", "��ѧ��");
		xjList.add(map);map = new HashMap<String,String>();
		map.put("xjdm", "1");
		map.put("xjmc", "��ѧ��");
		xjList.add(map);

		List<HashMap<String,String>> zxList = new ArrayList<HashMap<String,String>>();
		map = new HashMap<String,String>();
		map.put("zxdm", "0");
		map.put("zxmc", "��У");
		zxList.add(map);
		map = new HashMap<String,String>();
		map.put("zxdm", "1");
		map.put("zxmc", "����У");
		zxList.add(map);
		
		request.setAttribute("xjList", xjList);
		request.setAttribute("zxList", zxList);	
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydlbUpd");
	}

	/**
	 * �޸�ѧ���춯����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-�춯����������-�޸�XJLBDM:{xjlbdm},SHLCID:{shlcid}")
	public ActionForward xjydlbShpzUpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydForm myForm = (XjydForm) form;
		XjydService service = new XjydService();

		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			
			boolean result = service.updXjydlbShpz(myForm);
			//boolean result = true;
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		XjydForm model = service.getModelShpz(myForm);

		XtwhShlcService shlcService = new XtwhShlcService();
		//��ȡѧ����Ϣ�����
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xsxx");
		request.setAttribute("shlcList", shlc);
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydlbShpzUpd");
	}
	
	
	/**
	 * ɾ��ѧ���춯���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-�춯������ά��-ɾ��VALUES:{values}")
	public ActionForward xjydlbDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");
		XjydService service = new XjydService();
		String [] value = values.split(",");

		if (!StringUtil.isNull(values)){
			
			for(int i=0; i<value.length; i++){
				//�����类ʹ�ã����ش�false
				if(service.lbdmExist(value[i])){
					response.getWriter().print(
					getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
					return null;
				}
				
			}
			
			int num =  service.delXjydlb(values);
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	

	/**
	 * ɾ��ѧ���춯����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-�춯����������-ɾ��VALUES:{values}")
	public ActionForward xjydlbShpzDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");
		XjydService service = new XjydService();		

		if (!StringUtil.isNull(values)){
			
			int num =  service.delXjydlbShpz(values);
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * ��ȡѧ���춯���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydlbGet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");
		XjydForm model = new XjydForm();
		model.setXjlbdm(values);
		XjydService service = new XjydService();		

		if (!StringUtil.isNull(values)){
			
			XjydForm modelGet = service.getModel(model);
			if(modelGet!=null){
				Map<String,String> map = new HashMap<String, String>();
				map.put("xjlbmc", modelGet.getXjlbmc());
				map.put("sfyxjmc", modelGet.getSfyxjmc());
				map.put("sfzxmc", modelGet.getSfzxmc());
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
			}
		} else {
			return null;
		}
		return null;
	}
	
	/**
	 * ��ȡѧ���춯���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydlbShpzGet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");
		XjydForm model = new XjydForm();
		model.setXjlbdm(values);
		XjydService service = new XjydService();		

		if (!StringUtil.isNull(values)){
			
			XjydForm modelGet = service.getModelShpz(model);
			if(modelGet!=null){
				Map<String,String> map = new HashMap<String, String>();
				map.put("xjlbmc", modelGet.getXjlbmc());
				map.put("sfyxjmc", modelGet.getSfyxjmc());
				map.put("sfzxmc", modelGet.getSfzxmc());
				map.put("sftjbj", modelGet.getSftjbj());
				map.put("lrqzsj", modelGet.getLrqzsj());
				if("10511".equals(Base.xxdm)) {
					map.put("xzsfkq", modelGet.getXzsfkq());
					map.put("xxsfkq", modelGet.getXxsfkq());
				}
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
			}
		} else {
			return null;
		}
		return null;
	}
}
