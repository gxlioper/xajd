/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-10 ����08:47:43 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.dcjg;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

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
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.zxdk.dkbc.sqsh.DkbcService;
import com.zfsoft.xgxt.zxdk.rwfbybc.sqsh.RwfbysqshService;

public class RwfbydcjgAction extends SuperAction<RwfbydcjgModel, RwfbydcjgService> {
	private RwfbydcjgService service = new RwfbydcjgService();
	
	private static final String url = "zxdk_rwfby_dcjg.do";

	@SystemAuth(url = url)
	public ActionForward dcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "zxdk_rwfby_dcjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dcjgList");

	}

	@SystemAuth(url = url)
	public ActionForward getDcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		List<HashMap<String, String>> resultList = service.getPageList(model,
				user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dcjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("rxrq", xsjbxx.get("rxrq"));
		}

		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService
				.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkbcservice = new DkbcService();
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("yhlist", dkbcservice.getYhList());
		request.setAttribute("dclblist", dkbcservice.getDclbList());
		request.setAttribute("yjxflist", dkbcservice.getXfList());
		request.setAttribute("path", "rwfby_dcjg.do?method=dcjgAdd");
		this.saveToken(request);
		return mapping.findForward("dcjgAdd");
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dcjgEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
		RwfbydcjgModel myForm = service.getModel(model.getId());

		if (myForm != null) {
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		}

		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService
				.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkbcservice = new DkbcService();
		request.setAttribute("yhlist", dkbcservice.getYhList());
		request.setAttribute("dclblist", dkbcservice.getDclbList());
		request.setAttribute("yjxflist", dkbcservice.getXfList());
		request.setAttribute("dclb", model.getDclb());
		request.setAttribute("rwqsfsqdc", model.getRwqsfsqdc());
		
		//�㽭��ѧ���Ŵ������Ի��ֶ�
		if("10335".equals(Base.xxdm)){
			List<HashMap<String,String>> ffcsList = service.getDcjgffcsList(model.getXh(),model.getXn());
			Integer ffcsCount = ffcsList.size();
			request.setAttribute("ffcsList", ffcsList);
			request.setAttribute("ffcsCount", ffcsCount);
		}
		
		return mapping.findForward("dcjgEdit");
	}

	@SystemAuth(url = url)
	public ActionForward dcjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
		RwfbydcjgModel myForm = service.getModel(model.getId());

		if (myForm != null) {
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		}

		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService
				.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dclblist", new DkbcService().getDclbList());
		return mapping.findForward("dcjgView");
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������ѧ����-��������۲�������-�������-ɾ��values:{ids}")
	public ActionForward dcjgDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		String [] idArr = ids.split(",");
		if (!StringUtil.isNull(ids)) {
			boolean result = true;
			//�㽭��ѧ���Ի����Ų�����Ϣɾ��
			if("10335".equals(Base.xxdm)){
				List<HashMap<String,String>> xhxnList = service.getXhxnList(idArr);
				result = service.deleteDcjgffcs(xhxnList);
			}
			
			int num = 0;
			if(result){
				num = service.runDelete(idArr);
				result = num > 0;
			}
			
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}

		return null;
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RwfbydcjgModel model = (RwfbydcjgModel) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		List<HashMap<String, String>> resultList = null;
		if("10335".equals(Base.xxdm)){
			resultList = service.getDcListForZD(model,user); //��ѯ���м�¼�����Ŵ����������
		}else{
			resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ
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
	 * @����:��֤�Ƿ�������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-7 ����06:16:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean ��������
	 * @throws
	 */
	public ActionForward isExists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel model = (RwfbydcjgModel) form;
		boolean flag = service.isExists(model);
		response.getWriter().print(flag);
		return null;
	}
	
	/**
	 * @����:����
	 * @���ߣ�XuWen[���ţ�1426]
	 * @���ڣ�2016-1-12 ����02:16:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//�������������Ϣ
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		RwfbydcjgModel rwfbydcjgModel = (RwfbydcjgModel)form;
		RwfbydcjgService rwfbydcjgService = new RwfbydcjgService();
		boolean dcjgSaveResult = rwfbydcjgService.runInsert(rwfbydcjgModel);
		
		//�㽭��ѧ���Ի��ֶ�
		if("10335".equals(Base.xxdm)){
			//���淢�Ŵ�����Ϣ
			if(dcjgSaveResult){
				List<DcjgffcsModel> ffcsList = rwfbydcjgModel.getFfcsList();
				if(ffcsList.size()!=0){
					dcjgSaveResult = false;
					dcjgSaveResult = rwfbydcjgService.saveDcjgffcs(rwfbydcjgModel.getXh(),rwfbydcjgModel.getXn(),ffcsList);
				}
			}
		}
		
		String messageKey = dcjgSaveResult ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:����
	 * @���ߣ�XuWen[���ţ�1426]
	 * @���ڣ�2016-1-12 ����02:16:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//���´���������Ϣ
		RwfbydcjgModel rwfbydcjgModel = (RwfbydcjgModel)form;
		RwfbydcjgService rwfbydcjgService = new RwfbydcjgService();
		boolean dcjgUpdateResult = rwfbydcjgService.runUpdate(rwfbydcjgModel);
		
		//�㽭��ѧ���Ի��ֶ�
		if("10335".equals(Base.xxdm)){
			//���·��Ŵ�����Ϣ
			if(dcjgUpdateResult){
				List<DcjgffcsModel> ffcsList = rwfbydcjgModel.getFfcsList();
				if(ffcsList.size()!=0){
					dcjgUpdateResult = false;
					dcjgUpdateResult = rwfbydcjgService.updateDcjgffcs(rwfbydcjgModel.getXh(),rwfbydcjgModel.getXn(),ffcsList);
				}
			}
		}
		
		String messageKey = dcjgUpdateResult ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	//=================���Ի����뵼�����Զ�����=====================
	/**
	 * @����:�㽭��ѧ���Ի����룬�����Ŵ�����Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��17�� ����4:25:35
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
	public ActionForward importForZD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//��ȡ����ģ�����
		String drmkdm = request.getParameter("drmkdm");
		//��ѯģ����Ϣ
		HashMap<String,String> drmbxx = service.getDrmbxx(drmkdm);
		//��ѯ���������Ϣ
		List<HashMap<String,String>>  drgzxxList =  service.getDrgzxxList(drmkdm);
		
		request.setAttribute("drmbxx", drmbxx);
		request.setAttribute("drgzxxList", drgzxxList);
		return mapping.findForward("dcjgImport");
	}
	
	/**
	 * @����:�Զ���ģ�����أ��㽭��ѧ���Ի���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����2:52:55
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
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//��ȡ����ģ�����
		String drmkdm = request.getParameter("drmkdm");
		
		//���ģ��·��
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
		
		//����excelģ���ļ�
		File file = service.getExcelTemplate(path,drmkdm);
		
		//��Ӧ����
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(file.getName(),"utf-8")); 
		FileUtil.outputFile(response, file);
		
		//������ɺ�ɾ�����ɵ�ģ��
		if(file.exists()){
			file.delete();
		}
		return null;
	}
	
	/**
	 * @throws SQLException 
	 * @����:���浼��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��19�� ����12:00:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		//��ȡ����ģ�����
		String drmkdm = request.getParameter("drmkdm");
		
		//�õ�FormFile���󣬶�ȡ�ϴ���Excel�ļ�
		RwfbydcjgModel rwfbydcjgModel = (RwfbydcjgModel)form;
		FormFile formFile = rwfbydcjgModel.getImportFile();
		
		//���ر�������ģ�����󡢵���ɹ���Ϣ������ʧ����Ϣ
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
		HashMap<String,Object> resultMap = service.saveImport(formFile.getInputStream(),path,drmkdm);
		
		JSONObject resultJson = JSONObject.fromObject(resultMap); 
	    response.getWriter().print(resultJson);
		return null;
	}
	
	/**
	 * @����:���ص�������ļ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��19�� ����12:32:08
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
	public ActionForward downloadImportError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//�õ�tomcat/webapp/temp/importTemp�´����ļ���·��
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
		File file = new File(path,filename);
		if (file.exists()){
			response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(filename,"utf-8")); 
			FileUtil.outputFile(response, file);
		}
		return null;
	}
	
	/**
	 * 
	 * @����:�ǼǱ�����
	 * @���ߣ�lgx[����:1553]
	 * @���ڣ�2018-5-15 ����17:33:55
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
	
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbydcjgModel modelForm = (RwfbydcjgModel) form;
		RwfbydcjgModel model = service.getModel(modelForm.getId());
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		RwfbydcjgService service = new RwfbydcjgService();
		RwfbydcjgModel model = null;
		RwfbydcjgModel modelForm = null;
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				modelForm = new RwfbydcjgModel();
				modelForm.setId(values[i]);
				model = service.getModel(modelForm);
				File file = getWord(model);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	
	//���ģ����������word�ļ�
	private File getWord(RwfbydcjgModel rwfbydcjgModel)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		RwfbydcjgModel model = rwfbydcjgModel;
		String xh = model.getXh();
		//������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
		JtqkdcService jtqkService = new JtqkdcService();
		RwfbysqshService service = new RwfbysqshService();
		List<HashMap<String, String>> jtcyxxList= jtqkService.getJtcyList(xh);
		String fq = "";
		String mq = "";
		String qtcy = "";
		for (HashMap<String, String> map : jtcyxxList) {
			if("����".equals(map.get("cygxmc"))){
				fq = map.get("cyxm")+" " ;
				fq += (map.get("cylxdh") == null ? "" : map.get("cylxdh"));
			}else if("ĸ��".equals(map.get("cygxmc"))){
				mq = map.get("cyxm") + " ";
				mq += (map.get("cylxdh") == null ? "": map.get("cylxdh"));
			}else{
				qtcy +=  map.get("cyxm") + " " ;
				qtcy += map.get("cylxdh") == null ? "" : map.get("cylxdh");
				qtcy += "   ";
			}
		}
		HashMap< String, String> syddk = service.getSyddkxx(model.getId());
		data.put("syddk", syddk);
		HashMap< String, String> xyddk = service.getXyddkxx(model.getId());
		data.put("xyddk", xyddk);
		data.put("fq", fq);
		data.put("mq", mq);
		data.put("qtcy", qtcy);
		data.put("yx",Base.xxmc);
		data.put("xsjbxx", xsjbxx);
		data.put("model", model);
		File wordFile = null;
		wordFile = FreeMarkerUtil.getWordFile(data,  Constants.TEP_DIR + "yzrw","yzrwxfbcsqb_12688.xml", model.getXh() +"["+xsjbxx.get("xm")+"]" + "-");
		return wordFile;
		
	}
}
