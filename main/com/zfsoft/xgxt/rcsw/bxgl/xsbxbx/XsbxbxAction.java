/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:09:49 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsbxbx;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.bxgl.bxbxsq.BxbxSqService;
import com.zfsoft.xgxt.rcsw.bxgl.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ���չ���-ѧ�����ձ��� 
 * @���ߣ� ������ [����:1123]
 * @ʱ�䣺 2015-1-26 ����02:09:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsbxbxAction extends SuperAction {

	private static final String RCSWBXGL = "rcswbxgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rcsw_bxgl_xsbxbx.do";
	
	/**
	 * 
	 * @����: ѧ�����ձ����б�
	 * @���ߣ������� [����:1123]
	 * @���ڣ�2015-1-26 ����07:27:47
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
	public ActionForward xsbxbxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsbxbxService service = new XsbxbxService();
		XsbxbxForm myForm = (XsbxbxForm) form;
		User user = getUser(request);
		
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("rcsw_bxgl_xsbxbx.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_bxgl_xsbxbx.do";
		request.setAttribute("path", path);
		request.setAttribute("tableName", "XG_RCSW_BXGL_XSBXBXB");
		request.setAttribute("realTable", "XG_RCSW_BXGL_XSBXBXB");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xsbxbxList");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ������� [����:1123]
	 * @���ڣ�2015-1-26 ����07:27:47
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
	@SystemLog(description = "�����ճ�����-���չ���-ѧ�����ձ���-����XH:{xh},ZLBH:{zlbh},CLZB:{clzb},BXXZ:{bxxz},BXJE:{bxje},YFJSD:{yfjsd},LX:{lx},CSFYSJ:{csfysj},JBNR:{jbnr},JZYY:{jzyy},JZSJ:{jzsj}")
	public ActionForward addBx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsbxbxService service = new XsbxbxService();
		XsbxbxForm myForm = (XsbxbxForm) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			BxbxSqService bxbxSqService = new BxbxSqService();
			if("13871".equals(Base.xxdm)&&"���ﱨ��".equals(myForm.getBxxz())
					&&!bxbxSqService.checkMzje(myForm.getBxje(),myForm.getCsfysj(),myForm.getXh(),myForm.getBxid())){
				response.getWriter().print(getJsonMessage("���ﱨ�������������"));
				return null;
			}
			super.resetToken(request);
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			request.setAttribute("bxsx", new CsszService().getModel().getMzbxsx());
		}
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "rcswBxglXsbxbx.do?method=addBx");
		request.setAttribute("model", myForm);
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addBx");
	}
	
	/**
	 * 
	 * @����: ѧ����Ϣ����
	 * @���ߣ������� [����:1123]
	 * @���ڣ�2015-1-26 ����07:27:47
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
	private void szXsxx(HttpServletRequest request,String xh) {
		//��ѯѧ����Ϣ
		if(xh != null && !"".equals(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWBXGL);
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ������� [����:1123]
	 * @���ڣ�2015-1-26 ����07:27:47
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
	@SystemLog(description = "�����ճ�����-���չ���-ѧ�����ձ���-ɾ��VALUES:{values}")
	public ActionForward delBx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		XsbxbxService service = new XsbxbxService();
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ������� [����:1123]
	 * @���ڣ�2015-1-26 ����07:27:47
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
	public ActionForward bxbxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsbxbxForm model = (XsbxbxForm) form;
		XsbxbxService service = new XsbxbxService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ������� [����:1123]
	 * @���ڣ�2015-1-28 ����09:16:17
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
	@SystemLog(description = "�����ճ�����-���չ���-ѧ�����ձ���-�޸�BXID:{bxid},XH:{xh},ZLBH:{zlbh},CLZB:{clzb},BXXZ:{bxxz},BXJE:{bxje},YFJSD:{yfjsd},LX:{lx},CSFYSJ:{csfysj},JBNR:{jbnr},JZYY:{jzyy},JZSJ:{jzsj}")
	public ActionForward updateBx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsbxbxService service = new XsbxbxService();
		XsbxbxForm myForm = (XsbxbxForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			BxbxSqService bxbxSqService = new BxbxSqService();
			if("13871".equals(Base.xxdm)&&"���ﱨ��".equals(myForm.getBxxz())
					&&!bxbxSqService.checkMzje(myForm.getBxje(),myForm.getCsfysj(),myForm.getXh(),myForm.getBxid())){
				response.getWriter().print(getJsonMessage("���ﱨ�������������"));
				return null;
			}
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		
		request.setAttribute("path", "rcswBxglXsbxbx.do?method=updateBx");
//		request.setAttribute("type", "update");
//		XsyybxxxForm model = service.getModel(myForm);
//		BeanUtils.copyProperties(myForm, xgxt.utils.String.StringUtils.formatData(model));
		XsbxbxForm model = (XsbxbxForm)StringUtils.formatData(service.getModel(myForm));
		request.setAttribute("model", model);
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			request.setAttribute("bxsx", new CsszService().getModel().getMzbxsx());
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateBx");
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ������� [����:1123]
	 * @���ڣ�2015-1-28 ����09:16:17
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
	public ActionForward ckBx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsbxbxService service = new XsbxbxService();
		XsbxbxForm myForm = (XsbxbxForm) form;
		
		String bxid = myForm.getBxid();
		HashMap<String ,String> data = service.getXsbxbx(bxid);
		request.setAttribute("xsbxbx", StringUtils.formatData(data));
		
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "rcswBxglXsbxbx.do?method=ckBx");
		XsbxbxForm model = service.getModel(myForm);
		request.setAttribute("model", StringUtils.formatData(model));
		
		return mapping.findForward("ckBx");
	}
	/**
	 * 
	 * @����:Ajax����ѧ�����ձ�����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-3 ����09:01:19
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
	public ActionForward getBxbxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsbxbxService service = new XsbxbxService();
		String bxid = request.getParameter("bxid");
		Map<String, String> bxbxXxMap = service.getXsbxbx(bxid);
		bxbxXxMap = (Map<String, String>) StringUtils.formatData(bxbxXxMap);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(bxbxXxMap));
		return null;
	}
	
	/**
	 * 
	 * @����: ���ձ�����ӡ
	 * @���ߣ������� [����:1123]
	 * @���ڣ�2015-2-3 ����02:11:48
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
	public ActionForward getBxzm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsbxbxForm myForm = (XsbxbxForm) form;
		
		File wordFile = getBxzmWord(myForm.getBxid());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward getBxzmZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				File file = getBxzmWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	private File getBxzmWord(String bxid) throws Exception{
		
		XsbxbxService service = new XsbxbxService();
		//�õ���ӡ��Ϣ
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> bxData = service.bxbxZm(bxid);
		
		data.put("xh",bxData.get("xh"));
		data.put("zlbh",bxData.get("zlbh"));
		data.put("bxje",bxData.get("bxje"));
		data.put("jbnr",bxData.get("jbnr"));
		data.put("jzyy",bxData.get("jzyy"));
		data.put("jzsj",bxData.get("jzsj"));
		data.put("bzqk",bxData.get("bzqk"));
		data.put("xymc",bxData.get("xymc"));
		data.put("zymc",bxData.get("zymc"));
		data.put("bjmc",bxData.get("bjmc"));
		data.put("xm",bxData.get("xm"));
		data.put("xb",bxData.get("xb"));
		data.put("sfzh",bxData.get("sfzh"));
		
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//rcsw",
				"ybzm_13023.xml", bxData.get("xh") + "-" + bxData.get("xm"));
		return file;
	}
}
