/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-24 ����08:32:46 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjjg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import org.springframework.util.ResourceUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xnxj.jcsz.JcszService;
import com.zfsoft.xgxt.xsxx.xnxj.xjtx.XnxjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ��С����
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-24 ����08:32:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnxjJgAction extends SuperAction {

	private static final String XSXXXNXJ = "xsxxxnxj";
	private static BdpzService bdpzService = new BdpzService();
	private static List<HashMap<String,String>> jbxxList = null;
	
	private DAO dao = DAO.getInstance();
	
	
	static{
		jbxxList = bdpzService.getJbxxpz(XSXXXNXJ);
	}
	
	private static final String url = "xsxx_xnxj_xjjg.do";
	
	/**
	 * 
	 * @����: ��ѯѧ��С����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-24 ����11:28:29
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
	public ActionForward viewXnxjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnxjJgForm model  = (XnxjJgForm) form;
		XnxjJgService service = new XnxjJgService();
		
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		JcszService jcszService = new JcszService();
		HashMap<String , String> jcszMap =jcszService.getOneKgzt();
		searchModel.setSearch_tj_xn(new String[]{jcszMap.get("xjxn")});
		request.setAttribute("searchTj", searchModel);
		
		JcszService jzszService = new JcszService();			
		HashMap<String , String> jcszModel = jzszService.getOneKgzt();
		request.setAttribute("jcszModel", jcszModel);
		
		String path = "xsxx_xnxj_xjjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewXnxjJgList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xnxjjdadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjJgForm model  = (XnxjJgForm) form;
		XnxjJgService service = new XnxjJgService();
		User user = getUser(request);
		
		String xh = model.getXh();
		
		if(!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxx(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			
		}

		List<HashMap<String , String>> xnList = dao.getXnndList(); //ѧ���б�
		
		request.setAttribute("xnList", xnList);
		
		String path = "xsxx_xnxj_xjjggl.do?method=xnxjjdadd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnxjjgModel", model);
		return mapping.findForward("toAdd");
	}
	
	@SystemAuth(url = url)
	public ActionForward xnxjjdck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjJgForm model  = (XnxjJgForm) form;
		XnxjJgService service = new XnxjJgService();
		
		String xh = model.getXh();
		String xn = model.getXn();
		
		if(!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxx(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			List<HashMap<String , String>> xnxjList = service.getXnxjList(xh , xn);
			request.setAttribute("xnxjList", xnxjList);
		}
		String path = "xsxx_xnxj_xjjggl.do?method=xnxjjdadd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("toCk2");
	}
	
	@SystemAuth(url = url)
	public ActionForward xnxjjdckAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjJgForm model  = (XnxjJgForm) form;
		XnxjJgService service = new XnxjJgService();
		
		String xh = model.getXh();
		
		if(!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxx(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			List<HashMap<String , String>> xnxjList = service.getAllXnxjList(xh);
			request.setAttribute("xnxjList", xnxjList);
		}
		String path = "xsxx_xnxj_xjjggl.do?method=xnxjjdadd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("toCk");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xnxjjgupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjJgForm myform  = (XnxjJgForm) form;
		XnxjJgService service = new XnxjJgService();
		User user = getUser(request);
		
		if(!StringUtil.isNull(myform.getId())){
			XnxjJgForm model = service.getModel(myform.getId());
			
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxx(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			
			if(model != null){
				BeanUtils.copyProperties(myform, StringUtils.formatData(model));
			}
		}

		List<HashMap<String , String>> xnList = dao.getXnndList(); //ѧ���б�
		
		request.setAttribute("xnList", xnList);
		
		String path = "xsxx_xnxj_xjjggl.do?method=xnxjjgupdate";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("toUpdate");
	}
	/**
	 * 
	 * @����:����ѧ��С����д
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-24 ����01:18:49
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С����-����")
	public ActionForward doXnxjjgtx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjJgForm myform  = (XnxjJgForm) form;
		XnxjJgService service = new XnxjJgService();
		
		String xh = myform.getXh();
		String xn = myform.getXn();
		
		XnxjJgForm model = service.getModel(xh, xn); //���ж��Ƿ����ѧ��С������
		
		if(model != null){
			String messageKey = "����ʧ�ܣ���ѧ����ѧ��ѧ��С���Ѵ���,��ȷ�ϣ�";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		
		myform.setSjly(Constants.SJLY_JGK);
		myform.setTxsj(DateUtils.getCurrTime());
		
		StringUtils.formatData(myform);
		
		boolean isSuccess = service.runInsert(myform);
		
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
		
	}
	
	/**
	 * 
	 * @����:�޸�ѧ��С��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-24 ����02:07:48
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С����-�޸�PK:{id}")
	public ActionForward doXnxjjgxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjJgForm myform  = (XnxjJgForm) form;
		XnxjJgService service = new XnxjJgService();
		
		String id = myform.getId();

		XnxjJgForm model = service.getModel(id);
		
		if(model.getSjly().equals(Constants.SJLY_SPL)){
			String messageKey = "��ѧ��С�����������������������޸ģ�";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		
		model.setXjnr(myform.getXjnr());
		
		StringUtils.formatData(model);
		
		boolean isSuccess = service.runUpdate(model);
		
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
		
	}
	
	/**
	 * 
	 * @����:ɾ��ѧ��С��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-24 ����02:11:47
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С����-ɾ��PK:{values}")
	public ActionForward doXnxjjgsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjJgService service = new XnxjJgService();
		
		String ids = request.getParameter("values");

		if(StringUtils.isNotNull(ids)){
			int number = service.runDelete(ids.split(","));
			
			boolean isSuccess = (number >= 1) ? true : false;
			
			String messageKey = isSuccess ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
			
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			
		}
		
		return null;
		
	}
	
	/**
	 * 
	 * @����:
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-25 ����11:09:02
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
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XnxjJgForm myform  = (XnxjJgForm) form;
		
		String id = request.getParameter("id");
		
		if(id != null && id.split(",").length == 1){
			File file=printForWord(id);
			FileUtil.outputWord(response, file);
		}else{
			List<File> files = new ArrayList<File>();
			for(String gid:id.split(",")){
				File file=printForWord(gid);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @����:����word
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-24 ����06:15:28
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
	public ActionForward downloadWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnxjJgForm myform  = (XnxjJgForm) form;
		
		String id = myform.getId();
		File wordFile = printForWord(id);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-24 ����06:01:18
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnxjJgForm model  = (XnxjJgForm) form;
		XnxjJgService service = new XnxjJgService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	private  File printForWord(String guid) throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();
		
		XnxjJgService xnxjJgService = new XnxjJgService();
		
		XsxxService xsxxService = new XsxxService();
		
		XnxjJgForm xnxjJgModel = xnxjJgService.getModel(guid);
		
		HashMap<String , String> xnxjInfo = xnxjJgService.getXnxjJgInfo(guid);
		xnxjInfo.put("txny", DateTranCnDate.fomartDateToCn(xnxjInfo.get("txsj"),FomartDateType.day));
		String xnxj = xnxjInfo.get("xjnr");
		String xnxjBegin = "";
		String xnxjEnd = "";
		if(xnxj.length()>1000){
			xnxjBegin= xnxj.substring(0, 1000);
			xnxjEnd = xnxj.substring(1000,xnxj.length());
		}else{
			xnxjBegin=xnxj;
		}
		xnxjInfo.put("xjnrBegin", HtmlUtil.xmlZy(xnxjBegin));
		xnxjInfo.put("xjnrEnd", HtmlUtil.xmlZy(xnxjEnd));
		
		data.putAll(xnxjInfo);
		
		HashMap<String , String> xsxx = new HashMap<String, String>();
		
		if(xnxjJgModel != null){
			
			xsxx = xsxxService.getXsjbxx(xnxjJgModel.getXh());
			
		}

		data.putAll(xsxx);
		XnxjService xnxjService = new XnxjService();
		// =======��ѯ���������������ʱ������ beign======
		HashMap<String , String> xnxjShyjMap0 = new HashMap<String , String>();
		HashMap<String , String> xnxjShyjMap1 = new HashMap<String , String>();
		List<HashMap<String , String>> xnxjShyjList = xnxjService.getXnxjShyjList(xnxjInfo.get("sqid"));
		if(xnxjShyjList.size() >= 1){
			xnxjShyjMap0 = xnxjShyjList.get(0);
			xnxjShyjMap0.put("shny", DateTranCnDate.fomartDateToCn(xnxjShyjMap0.get("shsj"),FomartDateType.day));
		}
		data.put("xnxjShyjMap0", HtmlUtil.formatXmlMap(xnxjShyjMap0));
		if(xnxjShyjList.size() >= 2){
			xnxjShyjMap1 = xnxjShyjList.get(1);
			xnxjShyjMap1.put("shny", DateTranCnDate.fomartDateToCn(xnxjShyjMap1.get("shsj"),FomartDateType.day));
		}
		data.put("xnxjShyjMap1", HtmlUtil.formatXmlMap(xnxjShyjMap1));
		// =======��ѯ���������������ʱ������ end======
		data.put("xxmc", Base.xxmc);
		
		return getWord(data);
	}
	
	
	private File getWord(Map<String, Object> data) throws FileNotFoundException {
		String templatePath = Constants.TEP_DIR+"xsxx/xnxj" + ".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xsxx", "xnxj"+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}

		return wordFile;
	
	}

	
}
