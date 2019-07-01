/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-10 ����05:21:45 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bxh;

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
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: ����ѧ��action
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-10 ����05:21:45
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglBxhAction extends SuperAction {
	
	private static final String url = "fbglbxhbase.do";
	
	/**
	 * 
	 * @����: �б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����03:54:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service
					.getPageListForBxh(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglbxhbase.do");
		//��ѧ��״̬
		request.setAttribute("xhzt",FbglBxhService._BXH_WBXH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bxhlb");
	}
	/**
	 * 
	 * @����: ����б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����03:53:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward jglist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
	
		FbglBbglForm myForm = (FbglBbglForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel sm=cs.getSearchModel(request);
			sm.setPath("fbglbxhjgbase.do");
			myForm.setSearchModel(sm);
			myForm.setXhzt(FbglBxhService._BXH_YBXH);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service
					.getPageListForBxh(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglbxhbase.do");
		//��ѧ��״̬
		request.setAttribute("xhzt",FbglBxhService._BXH_YBXH);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "fbglbxhjgbase.do");
		return mapping.findForward("bxhjglb");
	}
	/**
	 * 
	 * @����: ����ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����03:41:05
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-��ѧ��-����ѧ��PK:{pk}")
	public ActionForward scxh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String pk = request.getParameter("pk");
		String bxhzt = request.getParameter("bxhzt");
		if (SAVE.equals(myForm.getType())) {
			String barKey = request.getParameter("barkey");
			String pzgzid = request.getParameter("pzgzid");
			String msg= service.scxh(pk, pzgzid, barKey, bxhzt);
			response.getWriter().print(getJsonMessageByKey(msg));
			return null;
		}
		// ѧ������
		request.setAttribute("xszs", service.getXszs(pk));
		// �ѱ�ѧ��
		request.setAttribute("ybxh", service.getYbxhXs(pk));
		// δ��ѧ��
		int wbxh = service.getWbxhXs(pk);
		request.setAttribute("wbxh", wbxh);
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		//��ѧ�Ź���
		request.setAttribute("pzgzList", fgts.getYqyTjnrList(Config._TJGZ_GZDM_BXHGZ));
		request.setAttribute("pk", pk);
		//��ѧ��״̬
		request.setAttribute("bxhzt", bxhzt);
		return mapping.findForward("scxh");
	}
	/**
	 * 
	 * @����: ���ҳ������ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����03:42:17
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-��ѧ��-���ҳ������ѧ��PK:{pk}")
	public ActionForward jgscxh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String pk = request.getParameter("pk");
		String bxhzt = request.getParameter("bxhzt");
		if (SAVE.equals(myForm.getType())) {
			String barKey = request.getParameter("barkey");
			String pzgzid = request.getParameter("pzgzid");
			response.getWriter().print(service.scxh(pk, pzgzid, barKey, bxhzt));
			return null;
		}
		
		FbglXsxxService fxs=new FbglXsxxService();
		//ѧ������
		request.setAttribute("xszs",fxs.getXsxxForNjKsh(pk, FbglXsxxService._XSXX_LXCX_ALL).size());
		//�ѱ�ѧ��
		request.setAttribute("ybxh", fxs.getXsxxForNjKsh(pk, FbglXsxxService._XSXX_LXCX_YBXH).size());
		//δ��ѧ��
		request.setAttribute("wbxh",fxs.getXsxxForNjKsh(pk, FbglXsxxService._XSXX_LXCX_WBXH).size());
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		//��ѧ�Ź���
		request.setAttribute("pzgzList", fgts.getYqyTjnrList(Config._TJGZ_GZDM_BXHGZ));
		request.setAttribute("pk", pk);
		request.setAttribute("bxhzt", bxhzt);
		return mapping.findForward("scxh");
	}
	/**
	 * @����: ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-8 ����03:32:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-�ְ����-��ѧ��-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
		String values = request.getParameter("values");
		String type = request.getParameter("type");
		Map<String, Object> map=null;
		if (!StringUtil.isNull(values)) {
			 map = service.deleteXh(values.split(","), type);
		} else {
			map=service.deleteAllXh();
		}
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
}
