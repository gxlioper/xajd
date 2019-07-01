package com.zfsoft.xgxt.dtjs.dxbmgl.pxjg;

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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @������������У��ѵ���action
 * @author������ ��1346��
 * @date��2017-11-1 ����03:50:47 
 */
public class DxpxjgAction extends SuperAction<DxpxjgForm, DxpxjgService> {
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz("dtxxXsxxpz");
	}
	/** 
	 * @������������ѯҳ��
	 * @author������ ��1346��
	 * @date��2017-11-9 ����10:35:53 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxpxjgCx(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxjgService service = new DxpxjgService();
		CommService cs = new CommService();
		DxpxjgForm myForm = (DxpxjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("dtjs_dxbmgl_dxpxjgCx.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "dtjs_dxbmgl_dxpxjgCx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dxpxjgCx");
	}
	
	/** 
	 * @������������ת�������ҳ��  �� ����
	 * @author������ ��1346��
	 * @date��2017-11-9 ����02:04:50 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxpxjgZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxpxjgService service = new DxpxjgService();
		DxpxjgForm myForm = (DxpxjgForm)form;
		User user = getUser(request);
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (!StringUtil.isNull(myForm.getPxid())) {
			HashMap<String, String> pxMap=service.getXspxByxhpxid(myForm.getXh(), myForm.getPxid());
			request.setAttribute("dxpxjgForm", pxMap);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			myForm.setFbr(user.getRealName());
			boolean result = service.save(myForm);
			response.getWriter().print(result);
			return null;
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "dtjs_dxbmgl_dxpxjgZj.do";
		request.setAttribute("path", path);
		return mapping.findForward("dxpxjgZj");
	}
	/** 
	 * @�������������ֺ�  ����
	 * @author������ ��1346��
	 * @date��2017-11-9 ����02:06:12 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxpxjgXg(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxjgService service = new DxpxjgService();
		DxpxjgForm myForm = (DxpxjgForm)form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.updatePf(myForm);
			response.getWriter().print(result);
			return null;
		}
		HashMap<String, String> pxMap=service.getXspx(myForm.getJgid());
		request.setAttribute("dxpxjgForm", pxMap);
		return mapping.findForward("dxpxjgXg");
	}
	/** 
	 * @������������ת�鿴
	 * @author������ ��1346��
	 * @date��2017-11-9 ����02:08:24 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxpxjgCk(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxjgService service = new DxpxjgService();
		DxpxjgForm myForm = (DxpxjgForm)form;
		HashMap<String, String> pxMap=service.getXspx(myForm.getJgid());
		request.setAttribute("dxpxjgForm", pxMap);
		return mapping.findForward("dxpxjgCk");
	}
	/** 
	 * @������������ѯ��ѵ��Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-11 ����11:48:15 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxpxxzCx(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxjgService service = new DxpxjgService();
		CommService cs = new CommService();
		DxpxjgForm myForm = (DxpxjgForm) form;
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("dtjs_dxbmgl_dxpxxzCx.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageListOfPx(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "dtjs_dxbmgl_dxpxxzCx.do";
		request.setAttribute("path", path);
		String gotoPath = "dtjs_dxbmgl_dxpxjgZj.do?xh="+myForm.getXh();
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("xh", myForm.getXh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dxpxxzCx");
	}
	/** 
	 * @��������������ɾ�����������
	 * @author������ ��1346��
	 * @date��2017-11-11 ����05:00:37 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxpxjgSc(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxjgService service = new DxpxjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int mess = service.del(values.split(","));
			response.getWriter().print(mess);
		} else {
			response.getWriter().print("0");
		}
		return null;
	}
}
