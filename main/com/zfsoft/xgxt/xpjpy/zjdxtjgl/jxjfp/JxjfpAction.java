/**
 * @����: ѧ����Ʒ(1)��
 * @���ڣ� 2018-7-24 ����04:09:18 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.jxjfp;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���������Ź���ģ��
 * @�๦������: ��ѧ���������һ����
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2018-7-24 ����04:08:47 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JxjfpAction extends SuperAction{
	private static final String url = "xpjpy_tjgl_jxjfp.do";
	private JxjfpService service = new JxjfpService();
	
	/**
	 * @����: ��ѧ���������һ�����ѯҳ��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-24 ����04:25:34
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
	@SystemLog(description = "��������������-ͳ�ƹ���-��ѧ���������һ����-��ѯҳ��")
	public ActionForward getJxjfpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		
		/*����������Ϣ*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		request.setAttribute("cssz", csszModel);
		
		/*Ĭ�ϸ߼���ѯ����*/
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		request.setAttribute("searchTj", searchModel);
		model.setSearchModel(searchModel);
		model.setXn(searchModel.getSearch_tj_xn()[0]);
		
		/*��ͳ����Ŀ*/
		List<HashMap<String, String>> pjxmList = service.getPjxmList(model);
		request.setAttribute("pjxmList", pjxmList);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxjfpList");
	}
	
	/**
	 * @����: ��ѯ�б���Json����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-24 ����07:23:32
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
	@SystemLog(description = "��������������-ͳ�ƹ���-��ѧ���������һ����-��ѯҳ��Json����")
	public ActionForward getJxjfpListData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		model.setXn(searchModel.getSearch_tj_xn()[0]);//ѧ��
		User user = getUser(request);
		/*��ѯ������JSON����*/
		List<HashMap<String, String>> resultList = service.getJxjmefpList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����: ���ͳ����Ŀ
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-25 ����10:07:04
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
	public ActionForward initPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		/*ѧ��*/
		String xn = request.getParameter("xn");
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszForm csszModel = csszService.getCsszModel();
			xn = csszModel.getXn();
		}
		model.setXn(xn);
		List<HashMap<String, String>> pjxmList = service.getPjxmList(model);
		JSONArray dataList = JSONArray.fromObject(pjxmList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����: ��ѧ�����һ�������ݵ���
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-25 ����03:14:38
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
	@SystemLog(description = "��������������-ͳ�ƹ���-��ѧ���������һ����-���ݵ���")
	public ActionForward jxjfpExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszForm csszModel = csszService.getCsszModel();
			xn = csszModel.getXn();
		}
		model.setXn(xn);
		
		User user = getUser(request);
		
		File file = service.getJxjfpFile(model,user);
		/*�����ļ�*/
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����: ���Ż��ܵ���(�͸�ԭ��)
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-25 ����05:25:07
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
	@SystemLog(description = "��������������-ͳ�ƹ���-��ѧ���������һ����-���Ż��ܵ���")
	public ActionForward ffhzExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszForm csszModel = csszService.getCsszModel();
			xn = csszModel.getXn();
		}
		model.setXn(xn);
		
		User user = getUser(request);
		
		File file = service.ffhzExport(model,user);
		/*�����ļ�*/
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����: ���ҽ�ѧ����ܵ���
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-11 ����12:38:11
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
	@SystemLog(description = "��������������-ͳ�ƹ���-��ѧ���������һ����-���ҽ�ѧ����ܵ���")
	public ActionForward gjjxjhzExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszForm csszModel = csszService.getCsszModel();
			xn = csszModel.getXn();
		}
		model.setXn(xn);
		response.setContentType("application/vnd.ms-excel");
		User user = getUser(request);
		service.exportGjjxjhz(model,response.getOutputStream(),user);
		return null;
	}
}
