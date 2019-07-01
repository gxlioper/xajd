/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-8 ����04:27:04 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxForm;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxService;
import com.zfsoft.xgxt.xsxx.bysxxgl.cssz.BysXxCsSzService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XxxgService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-8 ����04:27:04
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BysXxXgSqAction extends SuperAction {
	
	private static final String url = "xsxx_new_bysxx_xxxgsq.do";
	
	/**
	 * 
	 * @����:��ҵ����Ϣ�޸�����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����04:40:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	@SuppressWarnings("unchecked")
	public ActionForward bysXxXgSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		model.setUser(user);
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			if (srcList.contains("jtcyxxList")) {
				// ��ͥ��Ա��Ϣ
				List<HashMap<String, String>> jtcyxxList = service.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			if (srcList.contains("xlshjlxxList")) {
				// ѧ����ᾭ����Ϣ
				List<HashMap<String, String>> xlshjlxxList = service.getXlshjlList(model.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);
			}
			// ��ѵ��Ϣ
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service.getPxxxList(model.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}
			// �����
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service.getHjqkList(model.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// ����ʦ����ѧ
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				// ���˼���
				if (srcList.contains("grjlList")) {
					List<HashMap<String, String>> grjlList = service
					.getXsGrjlList(model.getXh());
					xsxxMap.put("grjlList", grjlList);
				}
				// ��������
				if (srcList.contains("gzjlList")) {
					List<HashMap<String, String>> gzjlList = service
					.getXsGzjlList(model.getXh());
					xsxxMap.put("gzjlList", gzjlList);
				}
				
			}
			
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String isStu = null;
		if ("stu".equals(user.getUserType())) {
			isStu = "isStu";
		}
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		String zpsfcz = xsxxtyglService.checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("xh", model.getXh());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("isStu", isStu);
		return mapping.findForward("xxXgSq");

	}

	/**
	 * 
	 * @����:��ҵ����Ϣ�޸����뱣��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-28 ����11:42:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸�����-����XGZDJSON:{xgzdJson}")
	public ActionForward bysXxXgSqBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxXgSqService xxxgsqService = new BysXxXgSqService();
		User user = getUser(request);
		String xgzdJson = request.getParameter("xgzdJson");
		model.setUser(user);
		BysXxXgSqForm xgsqForm = new BysXxXgSqForm();
		xgsqForm.setXh(model.getXh());
		xgsqForm.setXgzd(xgzdJson);
		boolean result = xxxgsqService.saveXgSq(xgsqForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

	/**
	 * 
	 * @����:��Ϣ�޸������ύ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-28 ����11:09:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸�����-�ύXGZDJSON:{xgzdJson}")
	public ActionForward bysXxXgSqTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		CommService commService = new CommService();
		BysXxXgSqService xxxgsqService = new BysXxXgSqService();
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		String xgzdJson = request.getParameter("xgzdJson");
		model.setUser(user);
		HashMap<String, String> bysxxValueMap = commService.getValueMap(request, service.getColumnByTable("XG_BYSXX_BYSXXB"));
		BysXxXgSqForm xgsqForm = new BysXxXgSqForm();
		xgsqForm.setXh(model.getXh());
		xgsqForm.setXgzd(xgzdJson);
		boolean result = xxxgsqService.tjXgSq(xgsqForm, model, bysxxValueMap);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:TODO�����޸�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-11 ����05:04:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("unchecked")
	public ActionForward SqXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		model.setUser(user);
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			if (srcList.contains("jtcyxxList")) {
				// ��ͥ��Ա��Ϣ
				List<HashMap<String, String>> jtcyxxList = service.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			if (srcList.contains("xlshjlxxList")) {
				// ѧ����ᾭ����Ϣ
				List<HashMap<String, String>> xlshjlxxList = service.getXlshjlList(model.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);
			}
			// ��ѵ��Ϣ
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service.getPxxxList(model.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}
			// �����
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service.getHjqkList(model.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// ����ʦ����ѧ
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				// ���˼���
				if (srcList.contains("grjlList")) {
					List<HashMap<String, String>> grjlList = service
					.getXsGrjlList(model.getXh());
					xsxxMap.put("grjlList", grjlList);
				}
				// ��������
				if (srcList.contains("gzjlList")) {
					List<HashMap<String, String>> gzjlList = service
					.getXsGzjlList(model.getXh());
					xsxxMap.put("gzjlList", gzjlList);
				}
				
			}
			
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String isStu = null;
		if ("stu".equals(user.getUserType())) {
			isStu = "isStu";
		}
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		String zpsfcz = xsxxtyglService.checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("xh", model.getXh());
		request.setAttribute("sqid", sqid);
		request.setAttribute("splc", splc);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("isStu", isStu);
		return mapping.findForward("sqXg");

	}

	/**
	 * 
	 * @����:�����޸ı���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-28 ����11:55:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸�����-����XGZDJSON:{xgzdJson}")
	public ActionForward SqXgBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxXgSqService xxxgsqService = new BysXxXgSqService();

		User user = getUser(request);
		String xgzdJson = request.getParameter("xgzdJson");
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		model.setUser(user);

		BysXxXgSqForm xgsqForm = new BysXxXgSqForm();
		xgsqForm.setXh(model.getXh());
		xgsqForm.setXgzd(xgzdJson);
		xgsqForm.setSqid(sqid);
		xgsqForm.setSplc(splc);

		boolean result = xxxgsqService.sqXgSave(xgsqForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

	/**
	 * 
	 * @����:�����޸��ύ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-28 ����11:56:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸�����-�ύXGZDJSON:{xgzdJson}")
	public ActionForward SqXgTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;

		CommService commService = new CommService();
		BysXxXgSqService xxxgsqService = new BysXxXgSqService();
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		String xgzdJson = request.getParameter("xgzdJson");
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		model.setUser(user);
		HashMap<String, String> bysxxValueMap = commService.getValueMap(request, service.getColumnByTable("XG_BYSXX_BYSXXB"));
		BysXxXgSqForm xgsqForm = new BysXxXgSqForm();
		xgsqForm.setXh(model.getXh());
		xgsqForm.setXgzd(xgzdJson);
		xgsqForm.setSqid(sqid);
		xgsqForm.setSplc(splc);

		boolean result = xxxgsqService.sqXgTj(xgsqForm, model, bysxxValueMap);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public ActionForward showXgXx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		String gndm = "xsxx_update";
		List<String> srcList = new FlszService().getSrcListByGndm(gndm);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			if (srcList.contains("jtcyxxList")) {
				// ��ͥ��Ա��Ϣ
				List<HashMap<String, String>> jtcyxxList = service.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}

			if (srcList.contains("xlshjlxxList")) {
				// ѧ����ᾭ����Ϣ
				List<HashMap<String, String>> xlshjlxxList = service.getXlshjlList(model.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);

			}

			// ��ѵ��Ϣ
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service.getPxxxList(model.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}

			// �����
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service.getHjqkList(model.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// ����ʦ����ѧ
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				// ���˼���
				if (srcList.contains("grjlList")) {
					List<HashMap<String, String>> grjlList = service
					.getXsGrjlList(model.getXh());
					xsxxMap.put("grjlList", grjlList);
				}
				// ��������
				if (srcList.contains("gzjlList")) {
					List<HashMap<String, String>> gzjlList = service
					.getXsGzjlList(model.getXh());
					xsxxMap.put("gzjlList", gzjlList);
				}
				
			}
			
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("rs", StringUtils.formatData(xsxxMap));
		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", model.getXh());
		request.setAttribute("sqid", request.getParameter("sqid"));
		String path = "xsxx_new_bysxx_xxxgsq";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("showXgXx");

	}

	/**
	 * 
	 * @����:��ҵ���б���ʾ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����10:49:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward showBysXx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm myForm = (BysXxForm) form;
		BysXxXgSqService bysxxService = new BysXxXgSqService();

		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = bysxxService.getBysXxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_bysxx_xxxgsq.do?method=showBysXx";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showBysXx");
	}

	/**
	 * 
	 * @����:�����޸���Ϣ�鿴
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����07:38:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward XgSqCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxXgSqService service = new BysXxXgSqService();
		BysXxService bysxxService = new BysXxService();
		User user = getUser(request);
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			if (bysxxService.getBysXx(user.getUserName()).size() <= 0) {
				String msg = "��ģ��������ҵ���û����ʣ���ȷ�ϣ�";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = null;
			if ("stu".equalsIgnoreCase(user.getUserType())) {
				model.setXh(user.getUserName());
				resultList = service.getXgSqPageListByStu(model, user);

			} else {
				resultList = service.getXgSqPageList(model, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_new_bysxx_xxxgsq.do";
		request.setAttribute("path", path);
		HashMap<String, String> csszMap = new BysXxCsSzService().getCssz();
		String kfxg = "n".equalsIgnoreCase(csszMap.get("kgzt")) ? "n" : "y";// �޸�״̬����
		request.setAttribute("kfxg", kfxg);
		request.setAttribute("usertype", user.getUserType());
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("xh", user.getUserName());
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgsqCk");

	}

	/**
	 * 
	 * @����:ɾ����ҵ����Ϣ�޸�����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����08:14:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸�����-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgSqService service = new BysXxXgSqService();
		String values = request.getParameter("values");
		// ɾ���޸�����
		int result = service.runDelete(values.split(","));
		String messageKey = null;
		if (result > 0) {
			// ɾ���޸��ֶ���Ϣ
			boolean results = service.xgZdDel(values.split(","));
			messageKey = results ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		} else {
			messageKey = MessageKey.SYS_DEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;

	}

	/**
	 * 
	 * @����:�ύ��ҵ����Ϣ�޸�����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����09:34:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸�����-�ύSQID:{sqid}")
	public ActionForward submitRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		String xh = request.getParameter("xh");

		BysXxXgSqService service = new BysXxXgSqService();
		boolean result = service.submitRecord(sqid, splc, xh);
		if (result) {
			// ����ҵ��״̬Ϊ'�����'
			BysXxXgSqForm model = new BysXxXgSqForm();
			model.setSqid(sqid);
			model.setShjg(Constants.YW_SHZ);
			result = service.updateXgSq(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����10:20:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸�����-����VALUES:{values}")
	public ActionForward xgsqCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgSqService service = new BysXxXgSqService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values, lcid);
		if (result) {

			// ����ҵ��״̬Ϊ'δ�ύ'
			BysXxXgSqForm model = new BysXxXgSqForm();
			model.setSqid(values);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(values)) > 0) {
				model.setShjg(Constants.YW_YTH);
			} else {
				model.setShjg(Constants.YW_WTJ);
			}
			result = service.updateXgSq(model);

		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:TODO��ȡ�޸��ֶμ�ֵ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-11 ����03:29:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward getXgzdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XxxgService service = new XxxgService();
		String sqid = request.getParameter("sqid");
		List<HashMap<String, String>> xgzdList = service.getXgzdList(sqid);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(xgzdList));
		return null;
	}

}
