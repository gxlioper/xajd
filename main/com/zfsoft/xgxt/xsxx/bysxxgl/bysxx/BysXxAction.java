/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-7 ����10:14:36 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.bysxx;

import java.io.File;
import java.util.ArrayList;
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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.xsxx.bysxxgl.cssz.BysXxCsSzDao;
import com.zfsoft.xgxt.xsxx.bysxxgl.cssz.BysXxCsSzService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.GrjlModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.GzjlModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.HjqkModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.JtcyxxModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.PxxxModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XlshjlModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ����Ϣ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ�����[����:1104]
 * @ʱ�䣺 2014-7-7 ����10:14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BysXxAction extends SuperAction {
	
	private static final String url = "xsxx_new_bysxx_xxgl.do";
	
	/**
	 * 
	 * @����:TODO��ҵ����Ϣ�鿴
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-7 ����10:32:21
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
	public ActionForward cxBysXxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxService bysxxService = new BysXxService();
		CommService cs = new CommService();
		BysXxForm myForm = (BysXxForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("xsxx_new_bysxx_xxgl.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = bysxxService.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_new_bysxx_xxgl.do";
		request.setAttribute("path", path);
//		SearchModel searchModel = new SearchModel();
//		searchModel.setSearch_tj_nd(new String[]{new BysXxCsSzDao().getCssz().get("bynd")});
//		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxBysXxList");
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-14 ����12:57:01
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
	@SuppressWarnings("unchecked")
	public ActionForward bysXxCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		String gndm = "xsxx_query";
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
		String path = "xsxx_new_bysxx_xxgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bysXxCk");

	}

	/**
	 * 
	 * @����:TODO��ѯѧ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-7 ����01:18:08
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
	public ActionForward showStudents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm myForm = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();

		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = bysxxService.getXsxxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_bysxx_xxgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		this.saveToken(request);
		return mapping.findForward("showStudents");
	}

	/**
	 * 
	 * @����:��ҵ����Ϣ���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-7 ����03:09:37
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
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��ҵ����Ϣ-����xh:{values}")
	public ActionForward bysXxAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm myForm = (BysXxForm) form;
		User user = getUser(request);
		String selected = request.getParameter("selected");
		BysXxService bysxxService = new BysXxService();
		// ȫѡ�����
		if ("all".equals(selected)) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xsxx_bysxx_xxgl.do?method=showStudents");
			myForm.setSearchModel(searchModel);
			String bynd = new BysXxCsSzService().getCssz().get("bynd");
			boolean result = bysxxService.runInsertSelect(myForm, user, bynd);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}else {
			String values = request.getParameter("values");
			String bynd = new BysXxCsSzService().getCssz().get("bynd");
			myForm.setBynd(bynd);
		    String messageKey = bysxxService.save(myForm, values);
		    response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
	}

	/**
	 * 
	 * @����:��ҵ����Ϣ�޸�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����09:36:32
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
	public ActionForward bysXxXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		String bynd = new BysXxCsSzService().getCssz().get("bynd");
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
		request.setAttribute("xh", model.getXh());
		request.setAttribute("bynd", bynd);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("bysXxXg");
	}

	/**
	 * 
	 * @����:��ҵ����Ϣ�޸ı���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-28 ����10:44:44
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
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��ҵ����Ϣ-�޸�XH:{xh}")
	public ActionForward bysXxXgBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		String gndm = "xsxx_update";
		List<String> srcList = new FlszService().getSrcListByGndm(gndm);
		User user = getUser(request);
		model.setUser(user);
		CommService commService = new CommService();
		HashMap<String, String> valueMap = commService.getValueMap(request, service.getColumnByTable("xsxxb"));
		HashMap<String, String> xsfzxxValueMap = commService.getValueMap(request, service.getColumnByTable("xsfzxxb"));

		valueMap.put("jtdz", xsfzxxValueMap.get("jtszd"));
		HashMap<String, String> bysxxValueMap = commService.getValueMap(request, service.getColumnByTable("XG_BYSXX_BYSXXB"));

		boolean result = bysxxService.updateRecord(model, valueMap, xsfzxxValueMap);
		boolean bysResult = bysxxService.updateBysXx(model, bysxxValueMap);

		if (srcList.contains(Constants.ZDYBD_JTCYXX)) {
			String jtcyxxJson = request.getParameter(Constants.ZDYBD_JTCYXX);
			List jtcyxxList = JsonUtil.jsonToList(jtcyxxJson, JtcyxxModel.class);
			result = service.updateJtcyxx(model.getXh(), jtcyxxList);// ��ͥ��Ա��Ϣ����
		}

		/**
		 * ����ѧ����ᾭ����Ϣ����
		 */
		if (srcList.contains(Constants.ZDYBD_XLSHJLXX)) {

			String xlshjlxxJson = request.getParameter(Constants.ZDYBD_XLSHJLXX);
			List xlshjlxxList = JsonUtil.jsonToList(xlshjlxxJson, XlshjlModel.class);
			result = service.updateXlshjlxx(model.getXh(), xlshjlxxList); // ѧ��ѧ����ᾭ����Ϣ����

		}

		// ��ѵ��Ϣ����
		if (srcList.contains("pxxxList")) {
			String pxxxJson = request.getParameter("pxxxList");
			List pxxxList = JsonUtil.jsonToList(pxxxJson, PxxxModel.class);
			result = service.updatePxxx(model.getXh(), pxxxList);
		}

		// ���������
		if (srcList.contains("rxqhjqkList")) {
			String hjqkJson = request.getParameter("rxqhjqkList");
			List hjqkList = JsonUtil.jsonToList(hjqkJson, HjqkModel.class);
			result = service.updateHjqk(model.getXh(), hjqkList);
		}
		
		// ����ʦ����ѧ
		if(Base.xxdm.equalsIgnoreCase("10511")) {
			// ���˼���
			if (srcList.contains("grjlList")) {
				String grjlJson = request.getParameter("grjlList");
				List grjlList = JsonUtil.jsonToList(grjlJson, GrjlModel.class);
				result = service.updateXsGrjl(model.getXh(), grjlList);
			}
			// ��������
			if (srcList.contains("gzjlList")) {
				String gzjlJson = request.getParameter("gzjlList");
				List gzjlList = JsonUtil.jsonToList(gzjlJson, GzjlModel.class);
				result = service.updateXsGzjl(model.getXh(), gzjlList);
			}
		}
		
		if (result && bysResult) {
			result = true;
		} else {
			result = false;
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:���ر�ҵ���ǼǱ�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����03:21:48
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
	public ActionForward printByjdb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm myForm = (BysXxForm) form;
		File wordFile = getDjbWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	/**
	 * 
	 * @����:���ر�ҵ���ǼǱ�(���)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����01:55:29
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
	public ActionForward printByjdbZip(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm myForm = (BysXxForm) form;
		String value = request.getParameter("xh");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				myForm.setXh(values[i]);
				File file = getDjbWord(myForm, request);
				files.add(file);
			}

			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-28 ����10:55:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getDjbWord(BysXxForm myForm, HttpServletRequest request) throws Exception {
		BysXxService service = new BysXxService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String, Object> data = new HashMap<String, Object>();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = service.getBysXx(xh);// ѧ��������Ϣ
		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList(xh);
		List<HashMap<String, String>> grjlList = xsxxglService.getXsGzjlList(xh);
		/*���˼����͹��������ϲ���*/
		List<HashMap<String, String>> grjlShjlList = xsxxglService.getGrjlShjlList(xh);
		if("10511".equals(Base.xxdm)){
			// ========== ��ͥ��Ա < ===========
			int jtcyxxBlankSize = 1 - jtcyxxList.size();
			for (int i = 0; i < jtcyxxBlankSize; i++) {
				jtcyxxList.add(new HashMap<String, String>());
			}
			// ========== ��ͥ��Ա < ===========
			// ========== �� �� ѧ �� �� �� �� �� �� < ===========
			int xlshjlBlankSize = 16 - grjlShjlList.size();
			for (int i = 0; i < xlshjlBlankSize; i++) {
				grjlShjlList.add(new HashMap<String, String>());
			}
			// ========== �� �� ѧ �� �� �� �� �� �� > ===========
			// ==========��ѯ�����Ϣ���������ʱ�併�� begin=======
			HashMap<String, String> sqMap = service.getSqXx(xh);// ������Ϣ
//			Map<String, String> shMap0 = new HashMap<String, String>();
//			Map<String, String> shMap1 = new HashMap<String, String>();
			ShlcDao splcdao = new ShlcDao();
			List<HashMap<String , String>> shyjList = splcdao.getShyjListByYwid(sqMap.get("sqid"));
			for (int i = 0; i < shyjList.size(); i++) {
				data.put("shyj"+(i), HtmlUtil.xmlZy(shyjList.get(i).get("shyj")));
				data.put("shrmc"+(i), shyjList.get(i).get("shrmc"));
				data.put("shsj"+(i), DateTranCnDate.fomartDateToCn(shyjList.get(i).get("shsj"),FomartDateType.day));
			}

			data.put("txsj", DateTranCnDate.fomartDateToCn(sqMap.get("xgsj"),FomartDateType.day));
//			if(shyjList.size() >= 1){
//				shMap0 = shyjList.get(0);
//			}
//			if(shyjList.size() >= 2){
//				shMap1 = shyjList.get(1);
//			}
//			data.put("shyj0", HtmlUtil.xmlZy(shMap0.get("shyj")));
//			data.put("shyj1", HtmlUtil.xmlZy(shMap1.get("shyj")));
			// ==========��ѯ�����Ϣ���������ʱ�併�� end=======
		}
		HashMap<String, String> zpMap = service.getZpMap(myForm, request, "zp");
		data.putAll(HtmlUtil.formatXmlMap(xsxxMap));
		data.put("xxmc", Base.xxmc);
		data.put("jtcyxxList", jtcyxxList);
		data.put("xlshjlList", grjlList);
		
		/*���˼����͹��������ϲ���*/
		data.put("grjlShjlList", grjlShjlList);
		
		data.putAll(zpMap);
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//xsxx", "bysdjb_"+Base.xxdm+".xml", xh + "-" + xsxxMap.get("xm"));
		return file;

	}
	
	/**
	 * 
	 * @����: ȡ��׼��ҵ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-19 ����07:08:35
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
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��ҵ����Ϣ-ȡ��׼��ҵ��xh:{values}")
	public ActionForward bysXxDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BysXxService service = new BysXxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;		
	}
	

}
