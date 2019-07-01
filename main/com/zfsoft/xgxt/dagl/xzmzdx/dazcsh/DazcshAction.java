/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����03:01:13 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsh;

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
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszForm;
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-���
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-5-14 ����03:28:27 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DazcshAction extends SuperAction<DazcshForm,DazcshService>{
	private final String url = "xsxx_dagl_dazcsh.do";
	private final static String DAZC = "dazc";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*ѧ��������Ϣ��ʾ����*/
		jbxxList = bdpzService.getJbxxpz(DAZC);
	}
	private DazcshService service = new DazcshService();
	
	/**
	 * @����: ��ѯ�б�����Json����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����05:03:15
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
	@SystemLog(description = "����ѧ����Ϣ-��������-����ת�����-��ѯҳ��")
	public ActionForward getDazcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcshForm model = (DazcshForm)form;
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
		return mapping.findForward("dazcshList");
	}
	
	/**
	 * @����: ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����05:43:06
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
	@SystemLog(description = "����ѧ����Ϣ-��������-����ת�����-�������")
	public ActionForward dazcshSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcshForm model = (DazcshForm)form;
		if(!StringUtil.isNull(model.getSqid())){
			/*ѧ���������*/
			HashMap<String, String> xspjshInfo = service.getDazcshInfo(model);
			/*����ѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xspjshInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("rs", StringUtils.formatData(xspjshInfo));
		}
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		
		/*����������Ϣ*/
		DazccsszForm dazccsszForm = new DazccsszService().getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("dazcshSingle");
	}
	
	/**
	 * @����: ���浥�����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����06:54:32
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
	@SystemLog(description="����ѧ����Ϣ-��������-����ת�����-������˱���-SQID:{sqid}")
	public ActionForward dazcshSingleSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		DazcshForm model = (DazcshForm)form;
		DazcshService serviceOne = TransactionControlProxy.newProxy(new DazcshService());
		
		User user = getUser(request);
		boolean result = serviceOne.dazcshSingleSave(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����05:31:28
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
	@SystemLog(description="����ѧ����Ϣ-��������-����ת�����-����ID:{sqid}")
	public ActionForward dazcshRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcshForm model = (DazcshForm)form;
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
}
