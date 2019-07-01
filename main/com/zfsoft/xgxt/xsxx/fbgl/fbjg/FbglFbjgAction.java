/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-24 ����10:42:10 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-24 ����10:42:10
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglFbjgAction extends SuperAction {
	
	private static final String url = "fbglfbjgbase.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService service = new FbglFbjgService();
		FbglXsxxForm myForm = (FbglXsxxForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglfbjgbase.do");
		request.setAttribute("tjzt", myForm.getTjzt());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbglfbjglb");
	}

	/**
	 * 
	 * @����: �ύ��ʽ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:36:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-�ְ����-�ְ�������-�ύ��ʽ��NJ:{nj}")
	public ActionForward tjzsk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService service = new FbglFbjgService();
		FbglXsxxForm myForm = (FbglXsxxForm) form;
		String nj = request.getParameter("nj");
		User user=getUser(request);
		if (SAVE.equals(myForm.getType())) {
			Map<String, String> map = service.tjzskForMessage(nj,user);
			response.getWriter().print(JSONObject.fromObject(map));
			return null;
		}
		List<HashMap<String, String>> list=service.getNjList();
		if(StringUtils.isNull(nj)&&null!=list&&list.size()>0){
			nj=list.get(0).get("nj");
		}
		//δ�ύ
		request.setAttribute("wtj", service.getXsxxSl(false, nj));
		//���ύ
		request.setAttribute("ytj", service.getXsxxSl(true, nj));
		request.setAttribute("njList", list);
		return mapping.findForward("fbglfbjgtj");
	}

	/**
	 * 
	 * @����: ������ʽ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:36:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-�ְ����-�ְ�������-�����ύNJ:{nj}")
	public ActionForward cxzsk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService service = new FbglFbjgService();
		FbglXsxxForm myForm = (FbglXsxxForm) form;
		String nj = request.getParameter("nj");
		User user=getUser(request);
		if (SAVE.equals(myForm.getType())) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "true");
			if (!service.cxzsk(nj,user)) {
				map.put("message", "false");
			}
			response.getWriter().print(JSONObject.fromObject(map));
			return null;
		}
		List<HashMap<String, String>> list=service.getYtjNjList();
		if(StringUtils.isNull(nj)&&null!=list&&list.size()>0){
			nj=list.get(0).get("nj");
		}
		//δ�ύ
		request.setAttribute("wtj", service.getXsxxSl(false, nj));
		//���ύ
		request.setAttribute("ytj", service.getXsxxSl(true, nj));
		request.setAttribute("njList", list);
		return mapping.findForward("fbglfbjgcx");
	}

	/**
	 * 
	 * @����: ���ش�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:36:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService ffs = new FbglFbjgService();
		ffs.download(request, response);
		return null;
	}
	/**
	 * 
	 * @����: ��ȡ�ύ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-14 ����05:17:49
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
	public ActionForward getTjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService service = new FbglFbjgService();
		String nj=request.getParameter("nj");
		Map<String,Integer> data=new HashMap<String, Integer>();
		data.put("wtj", service.getXsxxSl(false, nj));
		data.put("ytj", service.getXsxxSl(true, nj));
		response.getWriter().print(JSONObject.fromObject(data));
		return null;
	}

}
