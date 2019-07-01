/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-19 ����02:36:56 
 */
package com.zfsoft.xgxt.xsxx.xsgl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.misc.BASE64Decoder;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchForm;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ����Ϣ
 * @���ߣ� Penghui.Qu
 * @ʱ�䣺 2013-4-19 ����02:36:56
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XsxxAction extends SuperAction {
	
	

	/**
	 * �߼���ѯģʽ(��У��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		//�ڹ���ѧ��ѯѧ���ʸ���е�ѧ��
		if ("qgzx_wdgwsq.do?method=QgzxStuSq".equals(gotoPath)
				&& "yes".equals(new QgzxCsszService().getCssz().get(
						"sfxyzgsc"))) {
			return mapping.findForward("showQgzxStudents");
		}else if("mrgzkhKhsq.do?method=addKhsq".equals(gotoPath)
				|| "mrgzkhKhjg.do?method=addKhjg".equals(gotoPath)){
			return  mapping.findForward("showGzkhStudents");
		}else if("rcsw_cxda.do?method=add".equals(gotoPath)){
			request.setAttribute("fzrow", request.getParameter("fzrow"));
			return mapping.findForward("cxdashowStudents");
		}else if("jxkqjg.do?method=add".equals(gotoPath)||"jxqjjg.do?method=add".equals(gotoPath)){
			return mapping.findForward("showJxStudents");
		}else if("dzzgxsq.do?method=zcSq".equals(gotoPath)||"dtjs_xxjg.do?method=xxjgAdd".equals(gotoPath)){
			return mapping.findForward("showStudentsdzzgxzc");
		}else {
			return mapping.findForward("showStudents");
		}
		
	}
	
	/**
	 * @����:ѡ��ѧ���б�ҳ�棬ѡ��ѧ�����б����е��ֶΣ��ֲ�ˢ�µ�ѧ����Ϣ���������̨��ѯ���Ӷ�ҳ�治ˢ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��20�� ����1:53:57
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
	public ActionForward showStudentsNotF5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
        return mapping.findForward("showStudentsNotF5");	
	}
	
	/**
	 * @����:ѡ��ѧ���б�ҳ�棬ѡ��ѧ�������ѧ���첽��ѯѧ����Ϣ���ֲ�ˢ�µ�ѧ����Ϣ���Ӷ�ҳ�治ˢ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��20�� ����1:55:42
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
	public ActionForward showStudentsAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		String path = "xsxx_xsgl.do?method=showStudentsAjax";
		String isAll = request.getParameter("isAll");
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = null;
			if("true".equalsIgnoreCase(isAll)){
				resultList = service.getPageList(model);
			}else{
				resultList = service.getPageList(model, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("isAll", isAll);
		request.setAttribute("path", path);
        return mapping.findForward("showStudentsAjax");	
	}
	
	
	/**
	 * ѧ�������������룬������������Ա��
	 */
	
	public ActionForward showStudentsKnsrdsqBjpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsKnsrdsqBjpy(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsKnsrdsqBjpy");
	}

	/**
	 * �߼���ѯģʽ(ȫ��ѧ��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudentsAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageListAll(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudentsAll";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsAll");
	}

	/**
	 * 
	 * @����:ѡ��ѧ��������ʱ���ݴ棩NEW
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����11:28:31
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward selectStudentsNew(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		// KEY
		String xzxsKey = model.getXzxsKey();
		if (StringUtils.isNull(xzxsKey)) {
			xzxsKey = new SimpleDateFormat("yyyyMMddHHmmssSSSS")
					.format(new Date());
			model.setXzxsKey(xzxsKey);
		}
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service
					.getSelectStudentsList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		boolean result = false;
		if (SAVE.equals(model.getType())) {
			String selected = request.getParameter("selected");

			// ȫѡ�����
			if ("all".equals(selected)) {

				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);

				User user = getUser(request);
				result = service.runInsertSelect(model, user, xzxsKey);

			} else {
				String values = request.getParameter("values");
				if (StringUtils.isNotNull(values)) {
					result = service.runInsertSelect(values, xzxsKey);
				}
			}

			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", MessageUtil.getText(messageKey));
			map.put("xzxsKey", xzxsKey);
			int counts = service.getSelectedCount(xzxsKey);
			map.put("yxzxss", counts + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}

		if (DEL.equals(model.getType())) {
			String selected = request.getParameter("selected");

			// ȫѡ�����
			if ("all".equals(selected)) {

				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);

				User user = getUser(request);
				result = service.runDelSelect(model, user, xzxsKey);

			} else {
				String values = request.getParameter("values");
				if (StringUtils.isNotNull(values)) {
					result = service.runDelSelect(values, xzxsKey);
				}
			}

			String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
					: MessageKey.SYS_DEL_FAIL;
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", MessageUtil.getText(messageKey));
			map.put("xzxsKey", xzxsKey);
			int counts = service.getSelectedCount(xzxsKey);
			map.put("yxzxss", counts + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		int counts = service.getSelectedCount(xzxsKey);
		String path = "xsxx_xsgl.do?method=selectStudentsNew";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("yxzxss", counts);
		request.setAttribute("xzxsKey", xzxsKey);

		return mapping.findForward("selectStudentsNew");
	}

	/**
	 * �߼���ѯģʽ(��У��) ������¼���� ���ݴ�ѧ
	 * 
	 * @author zhangxiaobin
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudentsForXlfdlr(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service
					.getPageListForXlfdlr(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForXlfdlr");
	}

	
	/**
	 * �߼���ѯģʽ(��У��) �ҽ���ʦ��
	 * 
	 * @author zhangxiaobin
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudentsForJjlsk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service
					.getPageListForJjlsk(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForJjlsk");
	}
	
	/**
	 * �߼���ѯģʽ(��У��) ��Ϣ�ϱ�������� ���ݴ�ѧ
	 * 
	 * @author zhangxiaobin
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudentsForXxsbjggl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		String sblx = request.getParameter("sblx");
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service
					.getPageListForXxsbjggl(model, user, sblx);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("sblx", sblx);
		return mapping.findForward("showStudentsForXxsbjggl");
	}

	/**
	 * 
	 * @����:��ù�Ԣ�������ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-15 ����02:23:45
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
	
	public ActionForward showStudentsForGygl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);

			GyglNewService gyglNewService = new GyglNewService();
			String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // ��Ԣ����Ա���ݷ�Χ��������
			
			//����¥�������������
			String lddm = request.getParameter("lddm");
			model.setSsld(lddm);
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service
					.getPageListForGygl(model, user, searchTjByGyfdy);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForGygl");
	}
	
	/**
	 * 
	 * @����:��ù�Ԣ�������ѧ���������춯��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-14 ����02:34:54
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
	
	public ActionForward showStudentsForGyglSsyd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			GyglNewService gyglNewService = new GyglNewService();
			String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // ��Ԣ����Ա���ݷ�Χ��������
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service
			.getPageListForGyglSsyd(model, user, searchTjByGyfdy);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForGyglSsyd");
	}

	/**
	 * @����:��õ�����Ϣ���ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-15 ����02:23:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @deprecated
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	
	public ActionForward showStudentsForDtgl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service
					.getPageListForDtgl(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
	}

	/**
	 * 
	 * @����:������-����W����Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-25 ����08:53:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	
	public ActionForward showStudentsForTsxs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);

			// ��ѯ
			List<HashMap<String, String>> resultList = service
					.getPageListForTsxs(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		// String path = "xsxx_xsgl.do?method=showStudentsForTsxs";
		String path = "xlzx_tsxs_tsxs.do";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForTsxs");
	}

	/**
	 * 
	 * @����:����ѧ����Ƭ��ʾ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-20 ����09:58:54
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
	
	public ActionForward showPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (null == model.getXh() || "".equals(model.getXh())) {
			ServletContext application = request.getSession()
					.getServletContext();
			InputStream is = new FileInputStream(new File(application
					.getRealPath("/images/type_pic.gif")));
			FileUtil.outputFileStream(is, response.getOutputStream());
		} else {
			if("10335".equals(Base.xxdm)){
				//String zjdxZpurl = "http://api.zju.edu.cn:8094/message/openapi/api.do?apiKey=91bd53f871464366f92c6964f98d5aa9&appName=getGrzp&returnType=xml&xgh=" + model.getXh();
				//2016��12��13�� ����������ܽ��������ʸ�ΪIP��ַ����ѧ����Ƭ    Meng.Wei
				//String yhm = getUser(request).getUserName();
				//User user = getUser(request);
				String zjdxZpurl = "";
				//if("stu".equals(user.getUserType())){
				//	zjdxZpurl = "http://10.10.7.173:8094/message/openapi/api.do?apiKey=91bd53f871464366f92c6964f98d5aa9&appName=getGrzp&returnType=xml&xgh=" + yhm;
				//}else{
				zjdxZpurl = "http://10.10.7.173:8094/message/openapi/api.do?apiKey=91bd53f871464366f92c6964f98d5aa9&appName=getGrzp&returnType=xml&xgh=" + model.getXh();
				//}
				URL localURL = new URL(zjdxZpurl);
		        URLConnection connection = localURL.openConnection();
		        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
		        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		        InputStream inputStream = null;
		        InputStreamReader inputStreamReader = null;
		        BufferedReader reader = null;
		        StringBuffer resultBuffer = new StringBuffer();
		        String tempLine = null;
		        if (httpURLConnection.getResponseCode() >= 300) {
		            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
		        }
		        try {
		            inputStream = httpURLConnection.getInputStream();
		            inputStreamReader = new InputStreamReader(inputStream);
		            reader = new BufferedReader(inputStreamReader);
		            while ((tempLine = reader.readLine()) != null) {
		                resultBuffer.append(tempLine);
		            }
		        } finally {
		            if (reader != null) {
		                reader.close();
		            }
		            if (inputStreamReader != null) {
		                inputStreamReader.close();
		            }
		            if (inputStream != null) {
		                inputStream.close();
		            }
		        }	
				
		        String[] zps = org.apache.commons.lang.StringUtils.substringsBetween(resultBuffer.toString(), "<ZP>", "</ZP>");

		        
		        byte[] bs = new BASE64Decoder().decodeBuffer(zps[0]);
				
				response.setContentType("image/jpeg");
				
				response.getOutputStream().write(bs);
				
				response.flushBuffer();

				
			}else{
				InputStream is = service.getPhotoStream(model.getXh());

				if (is == null) {
					ServletContext application = request.getSession()
							.getServletContext();
					is = new FileInputStream(new File(application
							.getRealPath("/images/type_pic.gif")));
				}

				FileUtil.outputFileStream(is, response.getOutputStream());
			}
		}
		return null;
	}

	/**
	 * 
	 * @����:�߿���Ƭ��ʾ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-2 ����10:51:13
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
	
	public ActionForward showGkPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (null == model.getXh() || "".equals(model.getXh())) {
			ServletContext application = request.getSession()
					.getServletContext();
			InputStream is = new FileInputStream(new File(application
					.getRealPath("/images/type_pic.gif")));
			FileUtil.outputFileStream(is, response.getOutputStream());
		} else {
			InputStream is = service.getGkPhotoStream(model.getXh());

			if (is == null) {
				ServletContext application = request.getSession()
						.getServletContext();
				is = new FileInputStream(new File(application
						.getRealPath("/images/type_pic.gif")));
			}

			FileUtil.outputFileStream(is, response.getOutputStream());
		}
		return null;
	}

	/**
	 * 
	 * @����:�ж�ѧ���Ƿ���ڣ���������У����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-30 ����05:28:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean checkStuExists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		// �ж�ѧ���Ƿ����
		boolean resultList = service.getCheckStuExists(model.getXh());

		return resultList;
	}
	
	/**
	 * ��ʾ��ס��ѧ��������Ƽ���
	 */
	
	public ActionForward showStudentsForZzdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForZzdgl(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForZzdgl");
	}
	
	/**
	 * У����Ϣ������ݹ��ˣ�
	 */
	public ActionForward showStudentsForXnwxjkhk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForXnwxjkhk(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForXnwxjkhk");
	}
	
	/**
	 * 
	 * @����: ��ƽ������ѧ�𻹿���Ϣ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-3 ����04:50:18
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
	public ActionForward showStudentsForHkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForHkxx(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForHkxx");
	}
	
	/**
	 * @����: ������ѧ���У԰�ش���Ѵ�����Ա����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-8-22 ����02:17:50
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
	public ActionForward showStudentsForXydHkwh(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForXydHkwh(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForXydHkwh");
	}
	
	/**
	 * @����: ��Դ���Ѵ���Ա����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-8-22 ����02:22:08
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
	public ActionForward showStudentsForSydHkwh(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForSydHkwh(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForSydHkwh");
	}
	
	/**
	 * 
	 * @����: ��ҵ������Ա��Ϣ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-12 ����10:14:06
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
	public ActionForward showStudentsForByHkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForByHkxx(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		
		return mapping.findForward("showStudentsForByHkxx");	
	}
	
	/** 
	 * @����:����ѧ�����Ϻ�Ϸ��ѧԺ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-23 ����05:15:25
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
	public ActionForward showStudentsForTsxsByTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxForm model = (XsxxForm) form;
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String lxdm = request.getParameter("lxdm");
		XsxxService service = new XsxxService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForTsxsByTy(model, xn, xq, lxdm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "pj_tsxs.do";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("lxdm", lxdm);
		return mapping.findForward("showStudentsForTsxsByTy");
	}
	/**
	 * @����: �㽭��ѧ�³�𷢷Ų�ѯ����ѧ����������ͨ�÷���,ps:��Ȼ�ܲ�ѯ������ѧ����
	 * 		    ����ֻ��ͨ��ѧ��ȥģ����ѯ����Ϊ�߼���ѯ���������������Ǵ����û�Ȩ�޿��ƣ��޸������������Ƚ��鷳���˴�������ͣ���Ҫ�о��ɿ�����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-20 ����04:38:17
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
	public ActionForward showStudentsCjffAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsCjffAdd(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsCjffAdd");
	}
	
	/**
	 * 
	 * @����: ѧ��ѡ������ѯ[����֯��ϵת��]
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-8 ����05:59:32
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
	public ActionForward showStudentsFordzzgxzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.showStudentsFordzzgxzc(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	/**
	 * @����:��ѯѧ����ϸ������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��20�� ����3:06:38
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
	public ActionForward getXsjbxxMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			JSONObject data = JSONObject.fromMap(xsjbxx);
			response.getWriter().print(data);
		}
		return null;
	}
	
	/** 
	 * @����:�����Ƽ���ѧ��λ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-20 ����09:38:19
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
	public ActionForward showStudentsForXiAnKjGwSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForXiAnKjGwSq(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;		
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForXiAnKjGwSq");
	}
	
	/** 
	 * @����:���һ���(���칤�̴�ѧ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-23 ����10:47:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward showStudentsForQshr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		String cwh = request.getParameter("cwh");
		request.setAttribute("lddm", lddm);
		request.setAttribute("qsh", qsh);
		request.setAttribute("cwh", cwh);
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForQshr(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;		
		}
		//String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		//request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForQshr");
	}
	
	/**
	 * @description	�� TODO
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-16 ����10:40:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showStudentsForTgb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudentsForTgb(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		String gotoPath = request.getParameter("goto");
		request.setAttribute("gotoPath", gotoPath);
        return mapping.findForward("showStudentsForTgb");	
	}

	/**
	 * ������ʾ��ѡѧ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward selectDy(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String hdid = request.getParameter("hdid");
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = new User();
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		HdxxService hdxxService = new HdxxService();
		HdxxForm hdxxForm = new HdxxForm();
		hdxxForm.setHdid(hdid);
		HashMap<String,String> data = hdxxService.getHdxx(hdxxForm);
		request.setAttribute("data", data);
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=selectDy";
		request.setAttribute("path", path);
		request.setAttribute("hdid",hdid);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("selectDy");
	}

	/**
	 * ��ѡ��Ա��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hasSelectDy(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String hdid = request.getParameter("hdid");
		String xhStr = request.getParameter("xhs");
		String[] xhs ;
		if(StringUtil.isNull(xhStr)){
			xhs = new String[]{};
		}else{
			xhs = xhStr.split(",");
		}
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = new User();
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model, xhs);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;


	}
}
