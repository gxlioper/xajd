/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2014-10-8 ����11:40:22
 */  
package com.zfsoft.xgxt.szdw.thjl.thlx;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼������-̸����¼ά��-̸������
 * @�๦������: 
 * @���ߣ� ��ˮ��[����:1150]
 * @ʱ�䣺 2014-10-8 ����11:40:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwThlxAction extends SuperAction{
	
	private static final String url = "szdw_thjl_thlx.do";

	/**
	 * 
	 * @����:��ѯ̸�����ͣ���ת��
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@SystemAuth(url = url)
	public ActionForward queryThlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��ȡ����Ȩ��
		request.setAttribute("path", "szdw_thjl_thlx.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryThlx");
	}
	
	/**
	 * 
	 * @����:��ѯ̸������
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@SystemAuth(url = url)
	public ActionForward queryThlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwThlxForm model  = (SzdwThlxForm) form;
		SzdwThlxService service = new SzdwThlxService();
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	/**
	 * @����:����������б�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 9:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	//@SystemAuth(url = url)
	public ActionForward queryKhwtAction(ActionMapping mapping, ActionForm form,
										 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwThlxForm model  = (SzdwThlxForm) form;
		SzdwThlxService service = new SzdwThlxService();
		List<HashMap<String,String>> resultList = service.getKhwtPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * @����:���������б�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 9:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	//@SystemAuth(url = url)
	public ActionForward queryWtmsAction(ActionMapping mapping, ActionForm form,
										 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwThlxForm model  = (SzdwThlxForm) form;
		SzdwThlxService service = new SzdwThlxService();
		List<HashMap<String,String>> resultList = service.getWtmsPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	/**
	 * @����:�ṩ�����б�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 9:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	//@SystemAuth(url = url)
	public ActionForward queryTgbzAction(ActionMapping mapping, ActionForm form,
										 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThlxForm model  = (SzdwThlxForm) form;
		SzdwThlxService service = new SzdwThlxService();
		List<HashMap<String,String>> resultList = service.getTgbzPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * 
	 * @����:���̸�����ͣ���ת��
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addThlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���ݴ�ѧ
		if("10351".equals(Base.xxdm)){
			SzdwThlxForm model  = (SzdwThlxForm) form;
			SzdwThlxService service = new SzdwThlxService();
			request.setAttribute("type",model.getType());
			if(!"thlx".equals(model.getType()) && !"tgbz".equals(model.getType())){
				List<HashMap<String,String>> thlxList = service.getAllThlxList();
				request.setAttribute("thlxList",thlxList);
			}
		}
		return mapping.findForward("addThlx");
	}
	
	/**
	 * 
	 * @����:���̸�����ͱ������
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
//	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addThlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwThlxForm model  = (SzdwThlxForm) form;
		SzdwThlxService service = new SzdwThlxService();
		boolean isExist = false;
		boolean isSuccess = false;
		if("10351".equals(Base.xxdm)){
			//̸������
			if("thlx".equals(model.getType())){
					isSuccess = service.addThlx(model);
			}
			//���������
			if("khwt".equals(model.getType())){
					isSuccess = service.addKhwt(model);
			}
			//��������
			if("wtms".equals(model.getType())){
					isSuccess = service.addWtms(model);
			}
			//�ṩ����
			if("tgbz".equals(model.getType())){
					isSuccess = service.addTgbz(model);
			}
			String message = isSuccess ? "����ɹ���" : "����������Ѿ����ڣ�";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}else{
			//�����Ƿ����
			isExist=service.thlxIsExist(model);
			if(!isExist){
				isSuccess = service.runInsert(model);
				String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));

			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_DM_REPEAT));
			}
		}
		return null;

	}
	
	/**
	 * 
	 * @����:�޸�̸�����ͣ���ת��
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateThlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThlxForm model  = (SzdwThlxForm) form;
		request.setAttribute("type",model.getType());
		SzdwThlxService service = new SzdwThlxService();
		if("10351".equals(Base.xxdm)){
			request.setAttribute("type",model.getType());
			if(!"thlx".equals(model.getType())){
				/*List<HashMap<String,String>> thlxList = service.getAllThlxList();
				request.setAttribute("thlxList",thlxList);*/
				HashMap<String,String> map = new HashMap<String, String>();
				if("khwt".equals(model.getType())){
					map = service.getKhwtInfo(model);
				}
				if("wtms".equals(model.getType())){
					map = service.getWtmsInfo(model);
				}
				if("tgbz".equals(model.getType())){
					map = service.getTgbzInfo(model);
				}
				BeanUtils.copyProperties(model, StringUtils.formatData(map));
				request.setAttribute("rs",map);
			}else{
				SzdwThlxForm dataModel = service.getModel(model.getLxdm());
				request.setAttribute("rs",dataModel);
				BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
			}
		}else{
			SzdwThlxForm dataModel = service.getModel(model.getLxdm());
			BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
			request.setAttribute("wttg",model.getWttg());
		}

		
		return mapping.findForward("updateThlx");
	}
	
	/**
	 * 
	 * @����:�޸�̸�����ͱ������
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateThlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThlxForm model  = (SzdwThlxForm) form;
		SzdwThlxService service = new SzdwThlxService();
		boolean isSuccess = false;
		boolean isExit = false;//�ж��ظ�
		if("10351".equals(Base.xxdm)){
			//̸������
			if("thlx".equals(model.getType())){
				isExit = service.isExit(model,"XG_SZDW_THJL_THLX");
				if(isExit)
					isSuccess = service.runUpdate(model);
			}
			//���������
			if("khwt".equals(model.getType())){
				isExit = service.isExit(model,"XG_SZDW_THJL_KHWTLX");
				if(isExit)
				isSuccess = service.updateKhwt(model);
			}
			//��������
			if("wtms".equals(model.getType())){
				isExit = service.isExit(model,"XG_SZDW_THJL_KHLX");
				if(isExit)
				isSuccess = service.updateWtms(model);
			}
			//�ṩ����
			if("tgbz".equals(model.getType())){
				isExit = service.isExit(model,"XG_SZDW_THJL_TGBZ");
				if(isExit)
				isSuccess = service.updateTgbz(model);
			}
			String message = isSuccess ? "����ɹ���" : "�����Ѿ����ڣ�";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}else{
			isSuccess = service.runUpdate(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ��̸������
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteThlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThlxService service = new SzdwThlxService();
		int isSuccess = 0;
		String lxdms = request.getParameter("lxdms"); //��ɾ�������ʹ���
		String type = request.getParameter("type"); //��ɾ�������ʹ���
		if("10351".equals(Base.xxdm) && !"thlx".equals(type)){
			//���������
			if("khwt".equals(type)){
				isSuccess = service.delete(lxdms.split(","),"XG_SZDW_THJL_KHWTLX");
			}
			//��������
			if("wtms".equals(type)){
				isSuccess = service.delete(lxdms.split(","),"XG_SZDW_THJL_KHWT");
			}
			//�ṩ����
			if("tgbz".equals(type)){
				isSuccess = service.delete(lxdms.split(","),"XG_SZDW_THJL_TGBZ");
			}

		}else{
			isSuccess = service.runDelete(lxdms.split(","));
		}
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}

	/**
	 * @����:��������̸�����ͻ�ȡ���������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 16:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward getKhwtListByThlx(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThlxForm model  = (SzdwThlxForm) form;
		SzdwThlxService service = new SzdwThlxService();
		HashMap<String,String> data = service.getKhwtListByThlx( model.getSsthlx());
		Object r = StringUtils.formatData(data);
		response.getWriter().print(JSONObject.fromObject(r));
		return null;
	}

	/**
	 * @����:������������������ȡ��������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 16:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward getWtmsListByKhwt(ActionMapping mapping, ActionForm form,
										   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThlxForm model  = (SzdwThlxForm) form;
		SzdwThlxService service = new SzdwThlxService();
		List<HashMap<String,String>> infoList = service.getWtmsListByKhwt( model.getSskhwt());
		JSONArray dataList = JSONArray.fromObject(infoList);
		response.getWriter().print(dataList);
		return null;
	}

	/**
	 * @����:��ȡ�����ṩ����
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 16:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward getAllTgbz(ActionMapping mapping, ActionForm form,
										   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThlxService service = new SzdwThlxService();
		List<HashMap<String,String>> infoList = service.getAllTgbz();
		JSONArray dataList = JSONArray.fromObject(infoList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * @����:��ȡ���а������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 16:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward getAllBzjg(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThlxService service = new SzdwThlxService();
		List<HashMap<String,String>> infoList = service.getAllBzjg();
		JSONArray dataList = JSONArray.fromObject(infoList);
		response.getWriter().print(dataList);
		return null;
	}

}
