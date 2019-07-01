/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-17 ����04:07:33 
 */
package com.zfsoft.xgxt.zxdk.tyxs.shjg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.tyxs.cssz.TyxsCsszService;
import com.zfsoft.xgxt.zxdk.tyxs.zzsq.TyxsZzsqService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-4-17 ����04:07:33
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TyxsZzjgAction extends SuperAction<TyxsZzjg, TyxsZzjgService> {
	private static final String ZZPZ = "tyxs";
	private Log logger = LogFactory.getLog(TyxsZzjgAction.class);
	
	private static final String url = "zxdk_tyxs_zzjg.do";

	/**
	 * 
	 * @����: ������
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2014-9-28 ����02:16:33
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
	public ActionForward tyxsZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsCsszService csszService = new TyxsCsszService();
		request.setAttribute("cssz", csszService.getModel());

		request.setAttribute("path", "zxdk_tyxs_zzjg.do");
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);

		return mapping.findForward("zzjgList");
	}

	/**
	 * 
	 * @����: ajax���ش������б�
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2014-9-25 ����03:38:22
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
	public ActionForward getZzjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjgService service = getService();
		TyxsZzjg model = (TyxsZzjg) form;
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

	/**
	 * /**
	 * 
	 * @����: ��������
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2014-9-28 ����03:05:35
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
	public ActionForward addZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjg model = (TyxsZzjg) form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xn", Base.currXn);	
		
		TyxsCsszService csszService = new TyxsCsszService();
		request.setAttribute("cssz", csszService.getModel());
		String path = "tyxs_zzjg.do?method=addZzjg";
		request.setAttribute("path", path);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhlist", new TyxsZzsqService().getYhList());
		}
		this.saveToken(request);
		return mapping.findForward("addZzjg");
	}

	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-18 ����11:52:09
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
	public ActionForward editZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjgService service = getService();
		TyxsZzjg myForm = (TyxsZzjg) form;

		TyxsZzjg model = service.getModel(myForm.getId());

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xn", Base.currXn);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhlist", new TyxsZzsqService().getYhList());
		}
		return mapping.findForward("editZzjg");
	}

	/** ��ѧ��ѧ���ѯ��¼���� **/
	public ActionForward getCountByXhAndXn(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TyxsZzjgService service = getService();
		TyxsZzjg model = (TyxsZzjg) form;

		String count = service.getCountByXhAndXn(model);
		response.getWriter().print(count);
		return null;
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-18 ����02:10:29
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
	@SystemLog(description = "������ѧ����-����ѧ����ѧѧ�ѹ���-��˽��-ɾ����values:{ids}")
	public ActionForward delZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjgService service = getService();
		String ids = request.getParameter("ids");

		boolean result = service.runDelete(ids.split(",")) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;
	}

	/**
	 * 
	 * @����:�鿴
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-18 ����02:11:51
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
	public ActionForward ckZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjgService service = getService();
		TyxsZzjg myForm = (TyxsZzjg) form;

		TyxsZzjg model = service.getModel(myForm.getId());
		
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}

		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhmc", new TyxsZzsqService().getYhListByYhdm(model.getYhdm()));
		}
		return mapping.findForward("ckZzjg");
	}

	/**
	 * 
	 * @����:����ID��ѯ�����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-25 ����03:16:14
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
	public ActionForward zzxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		TyxsZzjgService service = getService();

		HashMap<String, String> dkxxMap = service.getZzjgById(id);

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(dkxxMap));
		return null;
	}

	/**
	 * 
	 * @����������
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-18 ����04:02:27
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
	public ActionForward dcjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsZzjgService service = getService();
		TyxsZzjg model = (TyxsZzjg) form;

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
	
	

	/**��ӡ�����**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String[] ids = request.getParameter("ids").split(",");
			
			if(null!=ids && ids.length == 1){//һ������
				File file=print(ids[0],request);
				FileUtil.outputWord(response, file);
			}else{//��������
				List<File> files = new ArrayList<File>();
				for(String id : ids){
					File file=print(id,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
			return null;
	}
	
	
	
	private synchronized File print(String id,HttpServletRequest request) throws Exception{

		Map<String, Object> data = new HashMap<String, Object>();
		
		TyxsZzjgService service = getService();
		TyxsZzjg model = service.getModel(id);
		
		HashMap<String, String> models = service.getDjbById(id);
		
		data.put("m", models);		
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		data.put("j", xsjbxx);
		if(Base.xxdm.equals("10704")){
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "tyxs_zzsq_10704.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
		}else{
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "tyxs_zzsq.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
		}
	}
	
	/**
	 * 
	 * @���������������淽����д
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-4-18 ����04:02:27
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
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TyxsZzjg model = (TyxsZzjg) form;
		TyxsZzjgService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	
}
