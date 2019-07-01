/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-3-26 ����09:35:09 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.bxbxsq;

import java.io.File;
import java.io.IOException;
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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.bxgl.cssz.CsszService;
import com.zfsoft.xgxt.rcsw.bxgl.xsbxbx.XsbxbxForm;
import com.zfsoft.xgxt.rcsw.bxgl.xsbxbx.XsbxbxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ձ�������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-3-26 ����09:35:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BxbxSqAction extends SuperAction{
	
	private static final String RCSWBXGL = "rcswbxgl";
	private BdpzService bdpzService = new BdpzService();
	private BxbxSqService service = new BxbxSqService();
	private CsszService csszService = new CsszService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rcsw_bxgl_bxbxsq.do";
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-26 ����11:29:10
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
	public ActionForward bxbxsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BxbxSqForm myForm = (BxbxSqForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("rcsw_bxgl_bxbxsq.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String[] sqshkg = csszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "rcsw_bxgl_bxbxsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bxbxsqList");
	}
	/**
	 * 
	 * @����:������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-26 ����02:32:10
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
	public ActionForward bxbxsqZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BxbxSqForm myForm = (BxbxSqForm) form;
		User user = getUser(request);
		String ze = "0";
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		//ѧ��������Ϣ����
		xsxxInit(request,myForm.getXh());
		request.setAttribute("path", "rcswBxglBxbxsq.do?method=bxbxsqZj");
		request.setAttribute("xxdm", Base.xxdm);
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			request.setAttribute("bxsx", new CsszService().getModel().getMzbxsx());
		}
		this.saveToken(request);
		return mapping.findForward("bxbxsqZj");
	}
	/**
	 * 
	 * @����:���뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����01:34:13
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
	@SystemLog(description = "�����ճ�����-���չ���-ѧ����������-����XH:{xh},ZLBH:{zlbh},BXJE:{bxje},CLZB:{clzb},BXXZ:{bxxz},YFJSD:{yfjsd},LX:{lx},CSFYSJ:{csfysj},JBNR:{jbnr},JZYY:{jzyy},JZSJ:{jzsj},SHZT:{shzt}")
	public ActionForward bxbxsqBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		BxbxSqForm myForm = (BxbxSqForm) form;
		if("13871".equals(Base.xxdm)&&"���ﱨ��".equals(myForm.getBxxz())
				&&!service.checkMzje(myForm.getBxje(),myForm.getCsfysj(),myForm.getXh(),myForm.getSqid())){
			response.getWriter().print(getJsonMessageResult("��ѧ��ȡ����ﱨ��������ѳ����޶���ɼ����걨��",false));
			return null;
		}
		super.resetToken(request);
		boolean result = service.saveBxbxsq(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
		
	}
	
	
	/**
	 * 
	 * @����:���������޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-27 ����02:52:34
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
	public ActionForward bxbxsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BxbxSqForm myForm = (BxbxSqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		BxbxSqForm model = service.getModel(myForm.getSqid());
		request.setAttribute("model", StringUtils.formatData(model));
		//ѧ��������Ϣ����
		xsxxInit(request,myForm.getXh());
		request.setAttribute("path", "rcswBxglBxbxsq.do?method=bxbxsqXg");
		request.setAttribute("xxdm", Base.xxdm);
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			request.setAttribute("bxsx", new CsszService().getModel().getMzbxsx());
		}
		return mapping.findForward("bxbxsqXg");
	}
	/**
	 * 
	 * @����:���ձ�������鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-2 ����10:21:31
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
	public ActionForward bxbxsqCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BxbxSqForm myForm = (BxbxSqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		BxbxSqForm model = service.getModel(myForm.getSqid());
		request.setAttribute("model", StringUtils.formatData(model));
		xsxxInit(request,myForm.getXh());
		request.setAttribute("path", "rcswBxglBxbxsq.do?method=bxbxsqCk");
		return mapping.findForward("bxbxsqCk");
	}
	/**
	 * 
	 * @����:�޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����01:34:35
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
	@SystemLog(description = "�����ճ�����-���չ���-ѧ����������-�޸�SQID:{sqid},XH:{xh},ZLBH:{zlbh},BXJE:{bxje},CLZB:{clzb},BXXZ:{bxxz},YFJSD:{yfjsd},LX:{lx},CSFYSJ:{csfysj},JBNR:{jbnr},JZYY:{jzyy},JZSJ:{jzsj},SHZT:{shzt}")
	public ActionForward bxbxsqXgBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BxbxSqForm myForm = (BxbxSqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if("13871".equals(Base.xxdm)&&"���ﱨ��".equals(myForm.getBxxz())
				&&!service.checkMzje(myForm.getBxje(),myForm.getCsfysj(),myForm.getXh(),myForm.getSqid())){
			response.getWriter().print(getJsonMessageResult("��ѧ��ȡ����ﱨ��������ѳ����޶���ɼ����걨��",false));
			return null;
		}
		boolean result = service.updateBxbxsq(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	/**
	 * 
	 * @����:���ձ�������ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����03:36:37
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
	@SystemLog(description = "�����ճ�����-���չ���-ѧ����������-�޸�VALUES:{values}")
	public ActionForward bxbxsqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
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
	 * @throws Exception 
	 * 
	 * @����:�����ύ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����04:44:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitBxbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BxbxSqForm myForm = (BxbxSqForm) form;
		String values = request.getParameter("values");
		myForm.setSqid(values);
		if("13871".equals(Base.xxdm)&&"���ﱨ��".equals(myForm.getBxxz())
				&&!service.checkMzje(myForm.getBxje(),myForm.getCsfysj(),myForm.getXh(),myForm.getSqid())){
			response.getWriter().print(getJsonMessageResult("��ѧ��ȡ����ﱨ��������ѳ����޶���ɼ����걨��",false));
			return null;
		}
		boolean result = service.submitBxbxsq(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
		
	}
	/**
	 * 
	 * @����:���볷��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����04:56:39
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
	public ActionForward cancelBxbxsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			BxbxSqForm model = new BxbxSqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡ���ձ�����ϸ��Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-27 ����03:03:05
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
	@SuppressWarnings("unchecked")
	public ActionForward getBxsqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("bxid");
		Map<String, String> bxbxXxMap = service.getBxsqxx(sqid);
		bxbxXxMap = (Map<String, String>) StringUtils.formatData(bxbxXxMap);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(bxbxXxMap));
		return null;
	}
	
	private void xsxxInit(HttpServletRequest request,String xh) {
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
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward bxbxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BxbxSqForm model = (BxbxSqForm) form;
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
	 * @����:���ձ�����ӡ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-3 ����09:52:55
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
		BxbxSqForm myForm = (BxbxSqForm) form;
		
		File wordFile = getBxzmWord(myForm.getSqid());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
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
	
	private File getBxzmWord(String sqid) throws Exception{
		
		//�õ���ӡ��Ϣ
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> bxData = service.getBxsqxx(sqid);
		
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
	
	/**
	 * ��ȡ�ܶ�
	 * @throws Exception 
	 */
	public ActionForward getZe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String csfysj = request.getParameter("csfysj");
		String xh = request.getParameter("xh");
		String ze = service.getZe(xh, csfysj);
		response.getWriter().print(ze);
		return null;
	}

}
