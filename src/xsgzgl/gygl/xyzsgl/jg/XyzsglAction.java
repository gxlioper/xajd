/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-18 ����07:36:02 
 */  
package xsgzgl.gygl.xyzsgl.jg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-5-18 ����07:36:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyzsglAction extends SuperAction<XyzsglForm, XyzsglService> {
	private final String XYZSJG ="xyzsjg";
	private XyzsglService service = new  XyzsglService();
	
	private static final String url = "gygl_xyzsjg.do";
	
	/**
	 * У��ס�޽����ʼ�������Լ���ѯ
	 */
	@SystemAuth(url = url)
	public ActionForward getXyzsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XyzsglForm model = (XyzsglForm) form;
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
		request.setAttribute("searchTj", searchModel);
		String path = "gygl_xyzsjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXyzsList");
	}
	
	/**
	 * У��ס�޽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XyzsglForm model = (XyzsglForm) form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSJG);
		request.setAttribute("jbxxList", jbxxList);
		String path = "gygl_xyzsjggl.do?method=add";
		request.setAttribute("path", path);
		List<HashMap<String, String>> zwjzyy = service.getZyjzxx(model);
		request.setAttribute("zwjzyy", zwjzyy);
		List<HashMap<String, String>> xl = service.getXl(model);
		request.setAttribute("xl", xl);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xxdm", Base.xxdm);
		//������Ϣ����
		return mapping.findForward("add");
	}
	
	/**
	 * ס�޽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-���ù���-У��ס�޽��-ά������{type}-XH:{xh},SQBH:{sqbh},SQSJSTART:{sqsjstart},SQSJEND:{sqsjend},XL:{xl},LXDH:{lxdh},PARENTSLXDY:{parentslxdy},XXDZ:{xxdz},ZWJZYY:{zwjzyy}")
	public ActionForward saveZsjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsglForm model = (XyzsglForm) form;
		
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		if(model.getType().equals("save")){
			// �жϵ�ǰѧ���Ƿ���У��ס�޽��
			boolean isExist = service.checkExistForSave(model);
			if (isExist) {
				message = MessageUtil.getText(MessageKey.GYGL_XYZS_REPEAT,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.saveZsjg(model, user);
		}else if(model.getType().equals("update")){
			result = service.saveZsjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * У��ס�޽���鿴
	 */
	@SystemAuth(url = url)
	public ActionForward ZsjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsglForm myForm = (XyzsglForm) form;
		XyzsglForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSJG);
		HashMap<String, String> xl = service.getXlCk(model);
		HashMap<String, String> xyjzyy = service.getXyZsyy(model);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", StringUtils.formatData(model));
		request.setAttribute("xlxx", StringUtils.formatData(xl));
		request.setAttribute("xyjzyy", StringUtils.formatData(xyjzyy));
		return mapping.findForward("view");
	}
	
	/**
	 * У��ס�޽���޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editZsjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsglForm myForm = (XyzsglForm) form;
		XyzsglForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSJG);
		List<HashMap<String, String>> zwjzyy = service.getZyjzxx(model);
		request.setAttribute("zwjzyy", zwjzyy);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", StringUtils.formatData(model));
		List<HashMap<String, String>> xl = service.getXl(model);
		request.setAttribute("xl", xl);
		String path = "gygl_xyzsjggl.do?method=editZsjg";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("editZsjg");
	}
	
	/**
	 * ס�޽��ɾ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-���ù���-У��ס�޽��-ɾ��SQBH:{values}")
	public ActionForward delZsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
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
	
	/**
	 * ס�޽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyzsglForm model = (XyzsglForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		
		//��Ϊѧ�����ƺ������סԭ��ֻ�ܻ�ȡ����ֵ���������ѭ������setֵ
		for (HashMap<String, String> hashMap : resultList) {
		  String sqbh = hashMap.get("sqbh");
		  model.setSqbh(sqbh);
		  HashMap<String, String> zwjzMap= service.getXyZsyy(model);
		  hashMap.put("zwjzyy",zwjzMap.get("mc"));
		  HashMap<String, String> xlMap = service.getXlCk(model);
		  hashMap.put("xl",xlMap.get("xlmc"));
		}

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
	/**
	 * 
	 *ס�޽���������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getXyzsjgsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyzsglForm myForm = (XyzsglForm) form;
		File wordFile = getZsjgWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getZsjgWord(XyzsglForm myForm,HttpServletRequest request) throws Exception{

		String xh = myForm.getXh();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> XyzsjgMap = null;
		if(request.getParameter("flag").equals("sq")){
			 XyzsSqDao xyzsSqDao = new XyzsSqDao();
			 XyzsSqForm xyzsSqForm = xyzsSqDao.getModel(myForm.getSqbh());
			 XyzsjgMap = service.getXyzsxxMap1(myForm, user);
			 data.put("xyzsjg", xyzsSqForm);
		}else{
			myForm  = service.getModel(myForm.getSqbh());
			XyzsjgMap = service.getXyzsxxMap(myForm, user);
			data.put("xyzsjg", myForm);
		}
//		data.put("xyzsjg", myForm);
		data.putAll(XyzsjgMap);
		
		//��������
		List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(XyzsjgMap.get("sqbh"));
		List<HashMap<String,String>> gwList = ShlcUtil.getSpbzBySplc(XyzsjgMap.get("splc"));
		for(int i=0;i<infoList.size();i++){
			HashMap<String,String>lcMap=infoList.get(i);
			if(i<gwList.size()){
				lcMap.putAll(gwList.get(i));
			}
		}
		data.put("splcList",infoList);
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"gygl/gygl_xyzsjgsqb_"+Base.xxdm+".xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"gygl","gygl_xyzsjgsqb_"+Base.xxdm+".xml",FreeMarkerUtil.getFileName(XyzsjgMap.get("xm")+"_У��ס�������"));
		}catch (Exception e) {
			file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"gygl","gygl_xyzsjgsqb_10530.xml", FreeMarkerUtil.getFileName(XyzsjgMap.get("xm")+"_У��ס�������"));
		}
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getXyzsjgsqbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyzsglForm myForm = (XyzsglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setSqbh(values[i]);
				File file = getZsjgWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
}
