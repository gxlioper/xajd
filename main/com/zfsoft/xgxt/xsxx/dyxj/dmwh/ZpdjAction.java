/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-18 ����11:13:42 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dmwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
 * @�๦������: �����ȼ�
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-18 ����11:13:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZpdjAction extends SuperAction<ZpdjModel, ZpdjService> {
	
	private static final String url = "xsxx_dyxj_dmwh.do";
	
	/**����ά���б�*/
	@SystemAuth(url = url)
	public ActionForward zpdjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "xsxx_dyxj_dmwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zpdjList");
	}
	
	/**����ά���б�*/
	@SystemAuth(url = url)
	public ActionForward getZpdjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpdjService service = getService();
		ZpdjModel model = (ZpdjModel) form;
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-����С��-����ά��-����DJMC:{djmc}")
	public ActionForward zpdjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("zpdjAdd");
	}
	
	/**����ά���޸�*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-����С��-����ά��-�޸�DJDM:{djdm},DJMC:{djmc}")
	public ActionForward zpdjEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpdjService service = getService();
		ZpdjModel myForm = (ZpdjModel) form;
		
		ZpdjModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		return mapping.findForward("zpdjEdit");
	}
	
	
	/**����ά��ɾ��*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-����С��-����ά��-ɾ��PK:{ids}")
	public ActionForward zpdjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		ZpdjService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XYJD_DELETE_DMEXIST,"����");
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	
	/**���ȼ����Ʋ�ѯ�Ƿ����**/
	public ActionForward getCountByDjmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpdjModel myForm = (ZpdjModel) form;
		ZpdjService service = getService();
		
		String count = service.getCountByDjmc(myForm.getDjmc());
		response.getWriter().print(count);
		return null;
	}
}
