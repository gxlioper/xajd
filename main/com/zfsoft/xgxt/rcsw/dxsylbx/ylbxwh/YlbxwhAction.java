/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-6 ����10:51:30
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxwh;

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
 * @ģ������: ҽ�Ʊ���ά������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-6 ����10:50:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxwhAction extends SuperAction {
	
	private static final String url = "rcsw_dxsylbx_ylbxwh.do";
	
	/**
	 * 
	 * @����:TODO(����ȫ�����Ա���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����01:53:13
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
	public ActionForward czqebzlxListManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhForm model = (YlbxwhForm) form;
		YlbxwhService service = new YlbxwhService();
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getCzqebzlxPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_dxsylbx_ylbxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("czqebzlxListManage");
		
	}
	
	/**
	 * 
	 * @����:TODO(�α�״��ά���б�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����02:11:13
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
	public ActionForward cbzklxListManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhForm model = (YlbxwhForm) form;
		YlbxwhService service = new YlbxwhService();
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getCbzklxPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_dxsylbx_ylbxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cbzklxListManage");
		
	}
	
	/**
	 * 
	 * @����:TODO(���Ӳ���ȫ�����Ա��� )
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ���ά��-��������-����CZQEBZMC:{czqebzmc}")
	public ActionForward addCzqebzlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhForm model = (YlbxwhForm) form;
		YlbxwhService service = new YlbxwhService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveCzqebzlxInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String czqebzdm = service.changeCzqebzlxdm();
		model.setCzqebzdm(czqebzdm);
		request.setAttribute("model", model);
		return mapping.findForward("addCzqebzlx");
		
	}
	
	/**
	 * 
	 * @����:TODO(�޸Ĳ���ȫ�����Ա��� )
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ���ά��-��������-�޸�CZQEBZDM:{czqebzdm},CZQEBZMC:{czqebzmc}")
	public ActionForward updateCzqebzlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhService service = new YlbxwhService();
		YlbxwhForm myForm = (YlbxwhForm) form;
		YlbxwhForm model = service.getCzqebzlxForm(myForm,myForm.getCzqebzdm());
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveCzqebzlxInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("updateCzqebzlx");
		
	}
	
	
	
	/**
	 * 
	 * @����:TODO(ɾ��ȫ�����Ա���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����05:44:04
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ���ά��-��������-ɾ��VALUES:{values}")
	public ActionForward delCzqebzlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhService service = new YlbxwhService();	
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteCzqebzlxwhInfo(values.split(","));
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
	
	/**
	 * 
	 * @����:TODO(���Ӳα�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����06:03:28
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ���ά��-�α�״������-����CBZKMC:{cbzkmc}")
	public ActionForward addCbzklx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhForm model = (YlbxwhForm) form;
		YlbxwhService service = new YlbxwhService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveCbzklxInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String cbzkdm = service.changeCbzklxdm();
		model.setCbzkdm(cbzkdm);
		request.setAttribute("model", model);
		return mapping.findForward("addCbzklx");
		
	}
	

	/**
	 * 
	 * @����:TODO(�޸Ĳα�״������ )
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-7 ����11:01:39
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ���ά��-�α�״������-�޸�CBZKDM:{cbzkdm},CBZKMC:{cbzkmc}")
	public ActionForward updateCbzklx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhService service = new YlbxwhService();
		YlbxwhForm myForm = (YlbxwhForm) form;
		YlbxwhForm model = service.getCbzklxForm(myForm,myForm.getCbzkdm());
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveCbzklxInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("updateCbzklx");
		
	}
	
	
	/**
	 * 
	 * @����:TODO(ɾ���α�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����05:44:04
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ���ά��-�α�״������-ɾ��VALUES:{values}")
	public ActionForward delCbzklx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhService service = new YlbxwhService();	
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delCbzklxwhInfo(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
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
