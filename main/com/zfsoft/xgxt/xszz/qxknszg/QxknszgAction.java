/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-20 ����06:31:00 
 */  
package com.zfsoft.xgxt.xszz.qxknszg;

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
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;



/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: TODO(ȡ���������ʸ�) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-4-21 ����08:35:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class QxknszgAction extends SuperAction<QxknszgForm, QxknszgService> {
	
	
	//private static final String QXKNSZG = "qxknszg";
	//private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	//private BdpzService bdpzService = new BdpzService();
	//private List<HashMap<String,String>> jbxxList = null;	
	private static final String url = "xszz_qxknszg_cx.do";
	
	/**
	 * 
	 * @����:TODO(ȡ���������ʸ�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-27 ����09:21:41
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
	@SystemLog(description="ѧ������(��)-�������϶�-ȡ���������ʸ���תҳ��")
	public ActionForward qxKnszgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QxknszgForm model = (QxknszgForm) form;
		QxknszgService service = new QxknszgService();
		
		if (QUERY.equals(model.getType())){			
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;			
		}	
		
		String path = "xszz_qxknszg_cx.do";		
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qxKnszgManage");
		
	}
	
	/**
	 * 
	 * @����:TODO(ѧ������(��)-�������϶�-ȡ���������ʸ�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-25 ����03:34:13
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
	@SystemLog(description="ѧ������(��)-�������϶�-ȡ���������ʸ�-ȡ��")
	public ActionForward cancelKnsrdzg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
			QxknszgForm model = (QxknszgForm) form;
			QxknszgService service = new QxknszgService();
			User user = getUser(request);				
			
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
				boolean isExist = false;				
				String qxyy = request.getParameter("qxyy");
				qxyy = java.net.URLDecoder.decode(qxyy,"UTF-8");
				model.setQxyy(qxyy);
				if (!isExist) {	
					
					boolean result = service.cancelKnsrdzg(model,user);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;	
					
				} else {				
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}
			}			
			request.setAttribute("countNum", model.getCountNum()); 													
			return mapping.findForward("cancelKnsrdzg");			
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-27 ����09:37:43
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
	@SystemLog(description="ѧ������(��)-�������϶�-ȡ���������ʸ�-��ȡҪȡ��������������")
	public ActionForward getCountNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QxknszgForm model = (QxknszgForm) form;
		QxknszgService service = new QxknszgService();		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);				
		User user = getUser(request);
		String countNum =  service.getCountNum(model,user);		
		response.getWriter().print(countNum);		
		return null;
		
	}
	
	
	

}
