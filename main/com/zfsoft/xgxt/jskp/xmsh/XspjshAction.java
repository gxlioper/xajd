/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-16 ����07:34:34 
 */  
package com.zfsoft.xgxt.jskp.xmsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ�����۹���ģ��
 * @�๦������: ѧ���������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-16 ����07:34:34 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XspjshAction extends SuperAction<XspjshForm,XspjshService>{
	private final String url = "xspj_xspj_xspjsh.do";
	private final String XSPJ = "xspj";
	private XspjshService service = new XspjshService();
	
	
	/**
	 * @����: ��ѯ�б�����json����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-17 ����09:03:16
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
	@SystemLog(description = "����ѧϰ����-��Ŀ���")
	public ActionForward getXspjshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			/*���ɸ߼���ѯ����*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xspjshList");
	}
	
	/**
	 * @����: �������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-17 ����03:22:03
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
	public ActionForward xspjshSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		if(!StringUtil.isNull(model.getSqid())){
			/*ѧ���������*/
			HashMap<String, String> xspjshInfo = service.getXspjshInfo(model);
			/*����ѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xspjshInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("rs", StringUtils.formatData(xspjshInfo));
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSPJ);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		/*ȡ���״̬���е�����һ����¼�ķ���*/
		HashMap<String, String> shxxLevel = service.getLevelXxBySqid(model);
		request.setAttribute("shxxLevel", shxxLevel);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("xspjshSingle");
	}
	
	/**
	 * @����: ������˱���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-18 ����11:49:16
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
	@SystemAuth(url = "xspj_xspj_xspjsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������--��Ŀ���-��˱���-GUID:{sqid}")
	public ActionForward xspjshSingleSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XspjshForm model = (XspjshForm)form;
		XspjshService serviceOne = TransactionControlProxy.newProxy(new XspjshService());
		
		User user = getUser(request);
		boolean result = serviceOne.xspjshSingleSave(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: �������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-18 ����04:24:04
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
	public ActionForward xspjshBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			XspjshService serviceOne = TransactionControlProxy.newProxy(new XspjshService());
			String message = serviceOne.xspjshBatchSave(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		HashMap<String, String> shxhList = service.getShxhForId(request.getParameter("id"));
		request.setAttribute("shxhList", shxhList);
		return mapping.findForward("xspjshBatch");
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-18 ����05:24:23
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
	@SystemLog(description="����ѧ������--��Ŀ���-����-����ID:{sqid}")
	public ActionForward xspjshRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		//���һ������
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: �޹�ѡ�������ҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-22 ����05:03:14
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
	@SystemLog(description="����ѧ������-��Ŀ���-�޹�ѡ�������")
	public ActionForward xspjshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		request.setAttribute("len", request.getParameter("len"));
		request.setAttribute("path", "xspj_xspj_xspjsh.do");
		return mapping.findForward("xspjshPlsh");
	}
	
	/**
	 * @����: ������˱���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-22 ����08:24:04
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
	@SystemLog(description="����ѧ������-��Ŀ���-�޹�ѡ������˱���")
	public ActionForward xspjshPlshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		User user = getUser(request);
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		List<HashMap<String, String>> resultList = service.xspjPlshxx(model,user);
		XspjshService serviceOne = TransactionControlProxy.newProxy(new XspjshService());
		String message = serviceOne.xspjshPlshSave(model, user,resultList);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
}
