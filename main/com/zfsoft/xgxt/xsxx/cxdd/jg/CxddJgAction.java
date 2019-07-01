/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:22:49 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.jg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.cxdd.comm.CommForm;
import com.zfsoft.xgxt.xsxx.cxdd.comm.CommUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-28 ����05:22:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxddJgAction extends SuperAction<CxddJgForm, CxddJgService> {
	private final String CXDD ="cxdd";
	private CxddJgService service = new  CxddJgService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "xsxx_cxdd_pyjg.do";
	public ActionForward getPyjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
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
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXDD);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnlist", Base.getXnndList());
		request.setAttribute("xqlist", Base.getXqList());
		request.setAttribute("cxdjlist", service.getCxdjList(null));
		String path = "cxdd_pyjg.do?method=add";
		request.setAttribute("path", path);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		return mapping.findForward("add");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
		CxddJgForm myForm = service.getModel(model);
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXDD);
		request.setAttribute("jbxxList", jbxxList);
//		request.setAttribute("xnlist", Base.getXnndList());
//		request.setAttribute("xqlist", Base.getXqList());
		for (HashMap<String, String> hashMap : Base.getXqList()) {
			if(myForm.getXq().equalsIgnoreCase(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("cxdjlist", service.getCxdjList(null));
		String path = "cxdd_pyjg.do?method=edit";
		request.setAttribute("path", path);
		return mapping.findForward("edit");
	}
	
	public ActionForward saveCxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
		boolean flag = true;
		CommForm commform = new CommForm();
		CommUtil util = new CommUtil();
		commform.setXh(model.getXh());
		commform.setXq(model.getXq());
		commform.setXn(model.getXn());
		if("save".equalsIgnoreCase(model.getType())){
			commform.setType("jg");
		}else{
			commform.setType("jgedit");
			commform.setId(model.getXsid());
		}
		
		flag = util.isNotExists(commform);
		if(flag){
			if("save".equalsIgnoreCase(model.getType())){
			     model.setBjdm(service.getbjdm(model.getXh()));
			     flag = service.runInsert(model);
			}else{
		         flag = service.runUpdate(model);
			}
		}else{
			String message = MessageUtil.getText(MessageKey.XSXX_CXDD_REPEAT,model.getXh());;
			response.getWriter().print(getJsonMessage(message));
			  return null;
		}
		
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//ɾ��
	public ActionForward delCxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	
	//����
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxddJgForm model = (CxddJgForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		
		//��Ϊѧ�����ƺ������סԭ��ֻ�ܻ�ȡ����ֵ���������ѭ������setֵ

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	//�鿴
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
		model = service.getModel(model);
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXDD);
		request.setAttribute("jbxxList", jbxxList);
		for (HashMap<String, String> hashMap : Base.getXqList()) {
			if(model.getXq().equalsIgnoreCase(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		request.setAttribute("cxdj", service.getCxdjList(model.getPj()).get(0));
		request.setAttribute("jg", model);
		String path = "cxdd_pyjg.do?method=view";
		request.setAttribute("path", path);
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 * @����: ���سɼ����浥
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����04:21:36
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
	public ActionForward exportCjbgd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CxddJgForm myForm = (CxddJgForm) form;
		File wordFile = getWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getWord(CxddJgForm myForm,HttpServletRequest request) throws Exception{
		File file = null;
		CxddJgForm model = service.getModel(myForm);
		HashMap<String, String> xsxxjbxx = service.getXsjbxx(model.getXh());
		Map<String,Object> data = new HashMap<String,Object>();
		data.putAll(xsxxjbxx);
		String bjrs = service.getBjrs(xsxxjbxx.get("bjdm"));
		data.put("bjrs",bjrs);
		String xnxqStr = service.getXnxqStr(model.getXn(), model.getXq());
		String title = xnxqStr + "  "+xsxxjbxx.get("bjmc");
		data.put("title",title);
		List<HashMap<String, String>> cjList = service.getCjList(model);
		/**
		 * �ɼ�����д������γ̰���ɼ�
		 */
		HashMap<String,String> cjMap = new HashMap<String, String>();
		for (int i = 1; i < 11; i++) {
			cjMap.put("kcmc"+i,"");
			cjMap.put("cj"+i,"");
		}
		for (int i = 1;  i< cjList.size()+1; i++) {
			cjMap.put("kcmc"+i,cjList.get(i-1).get("kcmc"));
			cjMap.put("cj"+i,cjList.get(i-1).get("cj"));
		}
		data.putAll(cjMap);
		data.putAll(service.getKxCsszb());
		data.putAll(service.getXfsj(model.getXh()));
		data.put("py", HtmlUtil.xmlZy(model.getPy()));
		List<HashMap<String, String>> pjjglist = service.getPjjgList(model);
		List<HashMap<String, String>> wjcflist = service.getWjcfList(model);
		data.put("pjjglist",pjjglist);
		data.put("wjcflist",wjcflist);
		//data.put("pjcj", service.getZcfsList(model, "ƽ���Ļ��ɼ�"));
		//�޸�Ϊȫ���ɼ���ӻ�ȡƽ���ɼ�������ҽҩ��
		data.put("pjcj", service.getPjcjAndPm(model));
		data.put("dyxf", service.getZcfsList(model, "����ѧ��"));
		//�޸�Ϊƽ����0.6+����ѧ��*0.4=�ۺϷ���������ҽҩ��
		data.put("zhcj", service.getZcfsTotal(model, "����ѧ��"));
		//data.put("zhcj", service.getZcfsList(model, "�۲��ܷ�"));
		data.put("bzr", new XsxxDao().getBzrxxByXh(model.getXh()));
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"xsxx/xscjbgd.xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"xsxx","xscjbgd.xml",FreeMarkerUtil.getFileName(xsxxjbxx.get("xm")+"_�ɼ����浥"));
		}catch (Exception e) {
			file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"xsxx","xscjbgd.xml", FreeMarkerUtil.getFileName(xsxxjbxx.get("xm")+"_�ɼ����浥"));
		}
		return file;
	}
	
	/**
	 * 
	 * @����: ���سɼ����浥�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����04:24:43
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
	public ActionForward getCjbgdZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm myForm = (CxddJgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setXsid(values[i]);
				File file = getWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * ��ѧ��Ϣ����
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-9 ����02:47:11
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
	public ActionForward kxxxSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.setAttribute("data", service.getKxCsszb());
		return mapping.findForward("kxxxsz");
	}
	
	/**
	 * 
	 * @����: ���濪ѧ��Ϣ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-9 ����02:51:31
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
	public ActionForward saveKxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CxddJgForm myForm = (CxddJgForm) form;
		CxddJgService tranService = TransactionControlProxy.newProxy(new CxddJgService());
		boolean rs = tranService.saveKxxx(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS :MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
