/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-10 ����05:28:11 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.rzkh;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-6-10 ����05:28:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RzkhAction extends SuperAction<rzkhjgForm,RzkhService> {
	private final String RZKHJG ="rzkhjg";
	private RzkhService service = new  RzkhService();
	private RzkhDao dao = new RzkhDao();
	private DAO Dao = DAO.getInstance();
	
	private static final String url = "szdw_xsgb_rzkhjg.do?method=rzkhjgList";
	
	@SystemAuth(url = url)
	public ActionForward rzkhjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		 rzkhjgForm model = (rzkhjgForm) form;
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
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "szdw_xsgb_rzkhjg.do?method=rzkhjgList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}
	
	/**
	 * ѧ���ɲ����˽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    rzkhjgForm model = (rzkhjgForm) form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RZKHJG);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Dao.getXnndList());
		request.setAttribute("xqList", Dao.getXqList());
		request.setAttribute("zwmclist", dao.getZwxxList());
		String path = "szdw_xsgb_rzkhjg.do?method=add";
		request.setAttribute("path", path);
		//������Ϣ����
		return mapping.findForward("add");
	}
	
	/**
	 * ��ְ���˽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ���ְ���˽��-���ӻ��޸�XH:{xh},KHJGID:{khjgid},ZWMC:{zwmc},RZSJ:{rzsj},XN:{xn},XQ:{xq},GRZP:{grzp},ZGDWYJ:{zgdwyj}")
	public ActionForward saveKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		rzkhjgForm model = (rzkhjgForm) form;
		boolean result = false;
		String message=null;
	  //  User user = getUser(request);
		if(model.getType().equals("save")){
			// �жϵ�ǰѧ���Ƿ���У��ס�޽��
			boolean isExist = service.checkExistForSave(model);
			if (isExist) {
				message = MessageUtil.getText(MessageKey.SZSW_XSGBGL_KHJG_ADD,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * ɾ�����˽��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ���ְ���˽��-ɾ��KHJGID:{values}")
	public ActionForward delKhjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			XyzsglForm myForm = new XyzsglForm();
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
	
	/**
	 * ѧ���ɲ����˽���鿴
	 */
	@SystemAuth(url = url)
	public ActionForward KhjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		rzkhjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RZKHJG);
		HashMap<String, String> zwmc = service.getZwmc(model.getZwmc());
//		HashMap<String, String> xyjzyy = service.getXyZsyy(model);
		request.setAttribute("jbxxList", StringUtils.formatData(jbxxList));
		request.setAttribute("khjg", model);
	    String rzsj = model.getRzsj();
//	    rzsj = rzsj.substring(0, 4)+"-"+rzsj.substring(4, 6)+"-"+rzsj.substring(6, 8);
	    request.setAttribute("rzsj",rzsj);
	    request.setAttribute("zwmc", zwmc);
	    String xqmc = service.getxqdz(model.getXq()).get("xqmc");
	    request.setAttribute("xqmc", xqmc);
		return mapping.findForward("view");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		rzkhjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RZKHJG);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("khjg", StringUtils.formatData(model));
		request.setAttribute("xnList", Dao.getXnndList());
		request.setAttribute("xqList", Dao.getXqList());
		request.setAttribute("zwmclist", dao.getZwxxList());
		String path = "szdw_xsgb_rzkhjg.do?method=updateKhjg";
		request.setAttribute("path", path);
		return mapping.findForward("update");
	}
	
	/**
	 * ѧ���ɲ����˽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		rzkhjgForm model = (rzkhjgForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		

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
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getGbrzkhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		File wordFile = getKhjgWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getKhjgWord(rzkhjgForm myForm,HttpServletRequest request) throws Exception{
		String xh = myForm.getXh();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> KhjgMap = null;
		myForm  = service.getModel(myForm.getKhjgid());
		KhjgMap = service.getKhjgxxMap(myForm, user);
		data.put("qdyjs", HtmlUtil.xmlZy(KhjgMap.get("qdyj")));
		data.put("xsgzcyjs",HtmlUtil.xmlZy(KhjgMap.get("xsgzcyj")));
		data.put("ddyjs", HtmlUtil.xmlZy(KhjgMap.get("ddyj")));
		data.put("szdwyjs", HtmlUtil.xmlZy(KhjgMap.get("szdwyj")));
		data.put("grszs", HtmlUtil.xmlZy(KhjgMap.get("grsz")));
		data.putAll(KhjgMap);
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//szdw","szdw_xsgb_rzkhjg_11483.xml",myForm.getXn()+myForm.getXq() +"-"+myForm.getXh()+"-"+KhjgMap.get("xm"));
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getKhjgkhbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setKhjgid(values[i]);
				File file = getKhjgWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	//���˻��ܱ���
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getKhhzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		myForm = service.getModel(myForm);
		File wordFile = getKhhzWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	//��ȡ����word
	private File getKhhzWord(rzkhjgForm myForm,HttpServletRequest request) throws Exception{
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> Khjglist = null;
		Khjglist = service.getKhjgxxList(myForm);
		HashMap<String, String>  KhjgMap = service.getKhjgxxMap(myForm, user);
		data.put("Khjglist", Khjglist);
		data.putAll(KhjgMap);
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//szdw","szdw_xsgb_rzkhhz_11483.xml","�㽭����ѧԺѧ���ɲ����˻��ܱ�"+myForm.getXh());
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getKhhzbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setKhjgid(values[i]);
				myForm = service.getModel(myForm);
				File file = getKhhzWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
}
