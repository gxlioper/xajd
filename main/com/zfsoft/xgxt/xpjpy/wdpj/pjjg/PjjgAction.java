/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:34:40 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.pjjg;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.export.util.ImportConfig;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.pjpy.xzhcp.sq.ZhcpDjService;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.bbwh.BbwhService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmModel;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjxmsq.PjxmsqService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����-�������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����10:34:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjjgAction extends SuperAction {

	private static final String template_dir = "classpath://templates//pjpy";//ģ��·��
	private static final String default_template = "default.xml";//Ĭ��ģ��
	private static final String JXSQ = "jxsq";
	// ����������֤��ģ���ѧУ
	private static final String[] RYZS_XXDMS = { "10022","10704" };
	private static List<HashMap<String, String>> jbxxList = null;
	
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(JXSQ);
	}
	
	private static final String url = "pj_pjjg.do";
	
	/**
	 * �������㽱ѧ���㽭��ѧ��
	 */
	public ActionForward scyxjxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgService service = new PjjgService();
		User user = getUser(request);
		boolean result = service.scyxjxj(user);
		if(result){
			response.getWriter().print(getJsonMessage("�����ɹ���"));
		} else {
			response.getWriter().print(getJsonMessageResult("����ʧ�ܣ�", false));
		}
		return null;
	}
	
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����10:59:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = {url,"xpj_pjjg.do?method=viewPjjgList&xzdm=2&sindex=1"})
	public ActionForward viewPjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		//�㽭��ýѧԺ����Ŀ���ʡ����ˡ����ҽ�ѧ�𡱲�ѡ�У�����ȫ��Ĭ��ѡ��
		if("11647".equals(Base.xxdm)){
			//��ȡ����Ŀ����list
			XmwhService xmwhservice = new XmwhService();
			//ɸѡ�������ҽ�ѧ��
			List<String> xmxz = xmwhservice.getXmxzmw();
			searchModel.setSearch_tj_xxmxz((String[])xmxz.toArray(new String[xmxz.size()]));
		}
		
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		request.setAttribute("pjzq", CsszService.getPjzq().get("cxxq"));
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xzdm",model.getXzdm());
		if("2".equals(model.getXzdm()))
		{
			request.setAttribute("path","xpj_pjjg.do?method=viewPjjgList&xzdm=2&sindex=1");
		}else{
			request.setAttribute("path","pj_pjjg.do");
		}
		String path = "pj_pjjg.do";
		request.setAttribute("czpath", path);
		//TODO �������ʴ�������czpath

		request.setAttribute("cssz", csszModel);
		request.setAttribute("ryzsFlag", Arrays.asList(RYZS_XXDMS).contains(Base.xxdm));
		request.setAttribute("userType", getUser(request).getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewPjjgList");
	}

	/**
	 * 
	 * @����:����������Ŀ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-7 ����10:58:15
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
	@SystemLog(description="������������-�ҵ�����-�������-����-XMMC:{xmmc},XH:{xh},XN:{xn},XQ:{xq},LXDM:{lxdm},XZDM:{xzdm},SQSJ:{sqsj}")
	public ActionForward addPjxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",  bdpzService.getJbxxpz(JXSQ));
		if (!StringUtil.isNull(model.getXh())) {
//			// ����ѧ��������Ϣ
//			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = service.getPjjgInfo(model.getXh(), model.getXn(), model.getXq());
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ��,��Ŀ���ƣ�
			boolean isExist = service.isExistByPjxmjg(model, SAVE);
			if (!isExist) {
				model.setSqr(user.getUserName());
				// ���������Ϣ
				model.setSjly(user.getUserName());
				//����ʦ����ѧ���Ի�֤����
				if("10511".equals(Base.xxdm)){
					model.setXmdm(service.getXmdm(model));
					model.setYlzd1(service.getZsbm(model));
					
				}
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}

		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		
		//��Ŀ������Ŀ����Ŀ����List
		XmwhService xmwhservice = new XmwhService();
		List<HashMap<String, String>> xmlx = xmwhservice.getXmlx(model.getXzdm());
		List<HashMap<String, String>> xmxz = xmwhservice.getXmxz();
		request.setAttribute("xmlxList", xmlx);
		request.setAttribute("xmxzList", xmxz);
		
//		//��Ŀ����List
//		List<HashMap<String, String>> xmmc = service.getxmmc();
//		request.setAttribute("xmmcList", xmmc);
		
		// ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		// ѧ��list
		request.setAttribute("xqList", Base.getXqList());
		// ��������
		CsszService csszService = new CsszService();
		request.setAttribute("pjzq", csszService.getCsz("pjzq"));
		model.setXn(CsszService.getPjzq().get("xn"));
		// 1:ѧ������
		if(CsszService.PJFS_XQ.equals(csszService.getCsz("pjzq"))){
			model.setXq(CsszService.getPjzq().get("xq"));
		}
		
		String path = "xpj_pjjg.do?method=addPjxmjg&xzdm="+model.getXzdm();
		request.setAttribute("path", path);
		request.setAttribute("xzdm",model.getXzdm());
		this.saveToken(request);
		return mapping.findForward("addPjxmjg");
	}
	
	
	
	/**
	 * 
	 * @����:�޸�������Ŀ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-8 ����10:00:42
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
	@SystemLog(description="������������-�ҵ�����-�������-�޸�-ID:{id},XMMC:{xmmc},XN:{xn},XQ:{xq},LXDM:{lxdm},XZDM:{xzdm},SQSJ:{sqsj}")
	public ActionForward updatePjxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);

		PjjgModel updateForm = service.getModel(model);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
//			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = service.getPjjgInfo(model.getXh(), model.getXn(), model.getXq());
			User u = getUser(updateForm.getSqr());
			xsjbxx.put("sqr",u.getRealName());
			xsjbxx.put("sqrbm",u.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ��,��Ŀ���ƣ�
			boolean isExist = service.isExistByPjxmjg(model, UPDATE);
			if (!isExist) {
				//����ʦ����ѧ���Ի�֤����
				if("10511".equals(Base.xxdm)){
					model.setXmdm(service.getXmdm(model));
					model.setYlzd1(service.getZsbm(model));
				}
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		
		//��Ŀ������Ŀ����Ŀ����list
		XmwhService xmwhservice = new XmwhService();
		List<HashMap<String, String>> xmlx = xmwhservice.getXmlx(model.getXzdm());
		List<HashMap<String, String>> xmxz = xmwhservice.getXmxz();
		request.setAttribute("xmlxList", xmlx);
		request.setAttribute("xmxzList", xmxz);
		
		// ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		// ѧ��list
		request.setAttribute("xqList", Base.getXqList());
		// ��������
		CsszService csszService = new CsszService();
		request.setAttribute("pjzq", csszService.getCsz("pjzq"));

		String path = "xpj_pjjg.do?method=addPjxmjg";
		request.setAttribute("path", path);
		
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",  bdpzService.getJbxxpz(JXSQ));
		request.setAttribute("xzdm",model.getXzdm());
		return mapping.findForward("updatePjxmjg");

	}
	
	
	/**
	 * 
	 * @����:ɾ��������Ŀ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-8 ����10:11:03
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
	@SystemLog(description="������������-�ҵ�����-�������-ɾ��-VALUES:{values}")
	public ActionForward delPjxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgService service = new PjjgService();
		
		//���id
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * 
	 * @����:������������鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-8 ����02:36:40
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
	public ActionForward pjxmjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",  bdpzService.getJbxxpz(JXSQ));
		if (model != null) {

			// ����ѧ��������Ϣ
//			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = service.getPjjgInfo(model.getXh(), model.getXn(), model.getXq());
			User u = getUser(xsjbxx.get("sqr"));
			xsjbxx.put("sqr",u.getRealName());
			xsjbxx.put("sqrbm",u.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);

			// ��ѯ���
			request.setAttribute("rs", service.getOnePjxmjgList(model.getId()));

			return mapping.findForward("pjxmjgView");
		} else {
			return updatePjxmjg(mapping, form, request, response);
		}

	}
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-14 ����03:41:50
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
		
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		User user = getUser(request);
		// ��
		List<HashMap<String,String>> resultList=service.getAllList(model,user);
		//List<HashMap<String, String>> resultList = service.getPjjgExportList(model, user);

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
	 * @����: ��ӡWord�ǼǱ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-29 ����09:36:06
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
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		PjjgModel model = (PjjgModel) form;
		
		File wordFile = getWord(model.getId());
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	
	
	//���ģ����������word�ļ�
	private File getWord(String id)
			throws Exception {
		PjjgService servicePjPy = new PjjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService service = new PjjgService();
		PjjgModel model = service.getModel(id);
		HashMap<String, String> bbMap = null;// ����
		
		//��ȡѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				model.setXqmc(map.get("xqmc"));
				break;
			}
		}

		if (model != null){
			
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn(), model.getXq());//��ѯ��Ŀ��¼
				if(xmMap != null){
					model.setXmdm(xmMap.get("xmdm"));//��Ŀ����
					BbwhService bbwhService = new BbwhService();
					bbMap = bbwhService.getDataById(xmMap.get("dybb"));//��ѯ��Ӧ��� 
				}
			}
			if(bbMap == null || bbMap.size() == 0){
				//��ѯ���������������Ϣ
				throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
			}
			
			String xh = model.getXh();
			
			//������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//�۲��ܷ�
			ZcfsService zcfService = new ZcfsService();
			
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));
			WjcfCfsbService wjcfcfsbService =  new WjcfCfsbService();
			List<HashMap<String , String>> yscfqk = wjcfcfsbService.getYscfqk(xh);
			data.put("currY", DateUtils.getYear());//��ǰ��
			data.put("currM", DateUtils.getMonth());//��ǰ��
			data.put("currD",DateUtils.getDayOfMonth());//��ǰ��
			PjxmsqService pjxmsqService = new PjxmsqService();
			//������
			List<HashMap<String, String>> shyjList = pjxmsqService.getAllShyjList(xh, model.getXn(), model.getXq(), model.getXmdm());
			//����ҽ��ר
			if("13779".equals(Base.xxdm)) {
				XsxxglService xsxxglService = new XsxxglService();
				PjjgService pjjgService = new PjjgService();
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				data.put("currXn", model.getXn());
				data.put("currXq", model.getXqmc());
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}
				}
				//�۲��
				HashMap<String, String> zcf = zcfService.getZczfByXh(xh, model.getXn(), model.getXq());
				data.put("zcf", zcf);
				//ѧϰ�ɼ���ƽ���֣�
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				if(xxcjMap == null){
					xxcjMap = new HashMap<String,String>();
				}
				data.put("xxcjMap", xxcjMap);
				
				// ��ѧ��ѧ�ڳɼ����γ���Ϣ��
				List<HashMap<String, String>> kcxxlist = xsxxglService.getStuCjOfXnXqList(xh, model.getXn(), model.getXq());
				 
			    pjjgService.addBlankList(kcxxlist, 21 - kcxxlist.size());
			    
			    List<HashMap<String, String>> kcxxlistNew = new ArrayList<HashMap<String, String>>();
			    for (int i = 0; i < 21; i = i + 3) {
			    	HashMap<String, String> kcxxMapNew = new HashMap<String, String>();
			    	kcxxMapNew.put("kcmc1", kcxxlist.get(i).get("kcmc"));
			    	kcxxMapNew.put("kcfs1", kcxxlist.get(i).get("cj"));
			    	kcxxMapNew.put("kcmc2", kcxxlist.get(i+1).get("kcmc"));
			    	kcxxMapNew.put("kcfs2", kcxxlist.get(i+1).get("cj"));
			    	kcxxMapNew.put("kcmc3", kcxxlist.get(i+2).get("kcmc"));
			    	kcxxMapNew.put("kcfs3", kcxxlist.get(i+2).get("cj"));
			    	kcxxlistNew.add(kcxxMapNew);
				}
			    data.put("kcxxlist", kcxxlistNew); // ��ѧ��ѧ�ڳɼ����γ���Ϣ��
			}
			
			//������ũ��ְҵѧԺ���Ի�
			if("12727".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				// ========== ��ѧ��ɼ���� begin ============
				HashMap<String,String> bxncjMap = servicePjPy.getDjbZcfListByXhXn(xh, model.getXn(), "����");
				String xndypjcj = bxncjMap.get("xndypjcj");
				data.put("xndypjcj", xndypjcj);
				data.put("sxqdycj", bxncjMap.get("sxqdycj"));
				data.put("xxqdycj", bxncjMap.get("xxqdycj"));
				
				CpmdService cpmdService = new CpmdService();
				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("shxs_xn_bjrs", shxs_xn_bjrs); // ��ѧ��༶����
				data.put("bxncjbjpm", bxncjMap.get("bxncjbjpm")); // ��ѧ��ɼ��༶����
				
				List<HashMap<String,String>> xnjxj_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "����");
				servicePjPy.addBlankList(xnjxj_cjSxqBxList, 16 - xnjxj_cjSxqBxList.size());
				data.put("xnjxj_cjSxqBxList", xnjxj_cjSxqBxList); // ��ѧ����ѧ�ڱ��޿γɼ�
				List<HashMap<String,String>> xnjxj_cjSxqXxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "ѡ��");
				servicePjPy.addBlankList(xnjxj_cjSxqXxList, 6 - xnjxj_cjSxqXxList.size());
				data.put("xnjxj_cjSxqXxList", xnjxj_cjSxqXxList); // ��ѧ����ѧ��ѡ�޿γɼ�
				List<HashMap<String,String>> xnjxj_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "����");
				servicePjPy.addBlankList(xnjxj_cjXxqBxList, 16 - xnjxj_cjXxqBxList.size());
				data.put("xnjxj_cjXxqBxList", xnjxj_cjXxqBxList); // ��ѧ����ѧ�ڱ��޿γɼ�
				List<HashMap<String,String>> xnjxj_cjXxqXxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "ѡ��");
				servicePjPy.addBlankList(xnjxj_cjXxqXxList, 6 - xnjxj_cjXxqXxList.size());
				data.put("xnjxj_cjXxqXxList", xnjxj_cjXxqXxList); // ��ѧ����ѧ��ѡ�޿γɼ�
				
				List<HashMap<String,String>> xnjxj_cjSxqSum = xnjxj_cjSxqBxList;
				xnjxj_cjSxqSum.addAll(xnjxj_cjSxqXxList);
				String xnjxj_cjSxqPjf = service.getPjf(xnjxj_cjSxqSum, 2); // ��ѧ����ѧ��ƽ���ɼ�
				data.put("xnjxj_cjSxqPjf", xnjxj_cjSxqPjf);
				List<HashMap<String,String>> xnjxj_cjXxqSum = xnjxj_cjXxqBxList;
				xnjxj_cjXxqSum.addAll(xnjxj_cjXxqXxList);
				String xnjxj_cjXxqPjf = service.getPjf(xnjxj_cjXxqSum, 2); // ��ѧ����ѧ��ƽ���ɼ�
				data.put("xnjxj_cjXxqPjf", xnjxj_cjXxqPjf);
				String xnjxj_cjXnPjf = "";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_cjXnPjf = service.getPjf(new String[]{ xnjxj_cjSxqPjf, xnjxj_cjXxqPjf }, 2); // ��ѧ��ƽ���ɼ�
				}
				data.put("xnjxj_cjXnPjf", xnjxj_cjXnPjf);
				// ѧ����ѧ�������ۺϳɼ� = ѧ��ѧϰƽ���ɼ���70������ѧ���������ƽ���ɼ���30����
				BigDecimal xnjxj_cjXnPjf_BD = StringUtils.isNotNull(xnjxj_cjXnPjf) ? new BigDecimal(xnjxj_cjXnPjf) : new BigDecimal("0"); 
				BigDecimal xnjxj_dypjcj_BD = StringUtils.isNotNull(xndypjcj) ? new BigDecimal(xndypjcj) : new BigDecimal("0"); 
				String xnjxj_xsjxjpdzhcj = "";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_xsjxjpdzhcj = xnjxj_cjXnPjf_BD.multiply(new BigDecimal("0.7")).add(xnjxj_dypjcj_BD.multiply(new BigDecimal("0.3"))).divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP).toString();
				}
				data.put("xnjxj_xsjxjpdzhcj", xnjxj_xsjxjpdzhcj);
				// ��ѧ�����޲�����γ�
				String xnjxj_bjgXn = "blank";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_bjgXn = service.getBjgcjNum(xh, model.getXn(), "");
				}
				data.put("xnjxj_bjgXn",xnjxj_bjgXn );
				// ========== ��ѧ��ɼ���� end ============
				// ========== ����ɼ���� begin ============
				String xnTemp = model.getXn().substring(0,4);
				String diXn = String.valueOf(Integer.parseInt(xnTemp)-2) + "-" + String.valueOf(Integer.parseInt(xnTemp)-1); // ��һѧ��
				String deXn = String.valueOf(Integer.parseInt(xnTemp)-1) + "-" + xnTemp; // �ڶ�ѧ��
				HashMap<String,String> bxncjMap_diXn = servicePjPy.getDjbZcfListByXhXn(xh, diXn, "����");
				// ��һѧ���������ƽ���ɼ�
				String xndypjcj_diXn = "";
				if(StringUtils.isNotNull(bxncjMap_diXn.get("sxqdycj")) || 
						StringUtils.isNotNull(bxncjMap_diXn.get("xxqdycj"))){
					xndypjcj_diXn = service.getPjf(new String[]{ bxncjMap_diXn.get("sxqdycj"), bxncjMap_diXn.get("xxqdycj") }, 2);
				}
				HashMap<String,String> bxncjMap_deXn = servicePjPy.getDjbZcfListByXhXn(xh, deXn, "����");
				// �ڶ�ѧ���������ƽ���ɼ�
				String xndypjcj_deXn = "";
				if(StringUtils.isNotNull(bxncjMap_deXn.get("sxqdycj")) || 
						StringUtils.isNotNull(bxncjMap_deXn.get("xxqdycj"))){
					xndypjcj_deXn = service.getPjf(new String[]{ bxncjMap_deXn.get("sxqdycj"), bxncjMap_deXn.get("xxqdycj") }, 2);
				}
				// ǰ��ѧ�����������ƽ���ɼ�
				String xndypjcj_qlXn = "";
				if(StringUtils.isNotNull(xndypjcj_diXn) || 
						StringUtils.isNotNull(xndypjcj_deXn)){
					xndypjcj_qlXn = service.getPjf(new String[]{ xndypjcj_diXn, xndypjcj_deXn }, 2);
				}
				data.put("xndypjcj_diXn", xndypjcj_diXn);
				data.put("xndypjcj_deXn", xndypjcj_deXn);
				data.put("xndypjcj_qlXn", xndypjcj_qlXn);
				// ========== ����ɼ���� end ============
				// ========== ����� begin ============
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				String bxnxnjxj = ""; // ��ѧ��У�ڽ�ѧ��
				String bxngjjxj = ""; // ��ѧ����ҽ�ѧ��
				String bxngjlzjxj = ""; // ��ѧ�������־��ѧ��
				String dixnxnjxj = ""; // ��һѧ��У�ڽ�ѧ��
				String dixngjjxj = ""; // ��һѧ����ҽ�ѧ��
				String dexnxnjxj = ""; // �ڶ�ѧ��У�ڽ�ѧ��
				String dexngjjxj = ""; // �ڶ�ѧ����ҽ�ѧ��
				String dixnxn1 = ""; // ��һѧ��У��XXX
				String dixnxn2 = ""; // ��һѧ��У��XXX
				String dixnsj = ""; // ��һѧ��ʡ��XXX
				String dexnxn1 = ""; // �ڶ�ѧ��У��XXX
				String dexnxn2 = ""; // �ڶ�ѧ��У��XXX
				String dexnsj = ""; // �ڶ�ѧ��ʡ��XXX
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					String xn = pj.get("xn");
					if(xmmc.contains("��У�ڽ�ѧ��")){
						if(model.getXn().equals(xn)){
							bxnxnjxj = xmmc;
						}else if(diXn.equals(xn)){
							dixnxnjxj = xmmc;
						}else if(deXn.equals(xn)){
							dexnxnjxj = xmmc;
						}
					}else if(xmmc.contains("���ҽ�ѧ��")){
						if(model.getXn().equals(xn)){
							bxngjjxj = xmmc;
						}else if(diXn.equals(xn)){
							dixngjjxj = xmmc;
						}else if(deXn.equals(xn)){
							dexngjjxj = xmmc;
						}
					}else if(xmmc.contains("������־��ѧ��") && model.getXn().equals(xn)){
						bxngjlzjxj = xmmc;
					}else if(xmmc.startsWith("У��")){
						if(diXn.equals(xn)){
							if("".equals(dixnxn1)){
								dixnxn1 = xmmc;
							}else if(!"".equals(dixnxn1) && "".equals(dixnxn2)){
								dixnxn2 = xmmc;
							}
						}else if(deXn.equals(xn)){
							if("".equals(dexnxn1)){
								dexnxn1 = xmmc;
							}else if(!"".equals(dexnxn1) && "".equals(dexnxn2)){
								dexnxn2 = xmmc;
							}
						}
					}else if(xmmc.startsWith("ʡ��")){
						if(diXn.equals(xn)){
							dixnsj = xmmc;
						}else if(deXn.equals(xn)){
							dexnsj = xmmc;
						}
					}
				}
				data.put("bxnxnjxj", bxnxnjxj);
				data.put("bxngjjxj", bxngjjxj);
				data.put("bxngjlzjxj", bxngjlzjxj);
				data.put("dixnxnjxj", dixnxnjxj);
				data.put("dexnxnjxj", dexnxnjxj);
				data.put("dixngjjxj", dixngjjxj);
				data.put("dexngjjxj", dexngjjxj);
				data.put("dixnxn1", dixnxn1);
				data.put("dixnxn2", dixnxn2);
				data.put("dixnsj", dixnsj);
				data.put("dexnxn1", dexnxn1);
				data.put("dexnxn2", dexnxn2);
				data.put("dexnsj", dexnsj);
				
				String xnjxj_hjxjdj = model.getXmmc().contains("�Ƚ�ѧ��") ? model.getXmmc() : ""; // ��У�ڽ�ѧ���������ѧ��ȼ�
				data.put("xnjxj_hjxjdj", xnjxj_hjxjdj);
				// ========== ����� end ============
				String yxtgpsbXmmc = "";
				if("yxtgpsb_12727".equals(bbMap.get("bbdm"))){
					yxtgpsbXmmc = "�����Ÿ�";
				}else if("yxtypsb_12727".equals(bbMap.get("bbdm"))){
					yxtgpsbXmmc = "������Ա";
				}
				data.put("bbMapBbdm", bbMap.get("bbdm"));
				data.put("yxtgpsbXmmc", yxtgpsbXmmc);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
				//��ȡ������ɼ�����
				String bjgcjs=service.getBjgcjNum(xh, model.getXn(), "");
				// ����Υ�ʹ���
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){ // ���ֹҿ�
					xsjbxx.put("ywcfgk", "��");
				}else{
					xsjbxx.put("ywcfgk", "��");
				}
				if(StringUtils.isNull(bjgcjs)){ // ��ѧ�����޲�����γ�
					xsjbxx.put("bxnywbjgkc", "��");
				}else{
					xsjbxx.put("bxnywbjgkc", "��");
				}
				DwwhService dwwhService = new DwwhService();
				List<HashMap<String , String>> bxnxsgbzwList = dwwhService.getZwForXhXn(xh, model.getXn()); // ѧ���ɲ�ְ������ʱ��
				HashMap<String , String> bxnxsgbzw1 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 0){
					bxnxsgbzw1 = bxnxsgbzwList.get(0);
				}
				HashMap<String , String> bxnxsgbzw2 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 1){
					bxnxsgbzw2 = bxnxsgbzwList.get(1);
				}
				data.put("bxnxsgbzw1", bxnxsgbzw1);
				data.put("bxnxsgbzw2", bxnxsgbzw2);
				
				// ��ͥ���
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkdcForm = new JtqkdcForm();
				jtqkdcForm.setXh(xh);
				JtqkdcForm jtqkmodel_12727 = jtqkService.getModel(jtqkdcForm); 
				if (jtqkmodel_12727 == null) {
					jtqkmodel_12727 = new JtqkdcForm();
				}
				data.put("jtqkmodel_12727", jtqkmodel_12727);
				String jthk = jtqkmodel_12727.getJthk();
				boolean jthkCzFlag = false;
				if(jthk != null && jthk.contains("����")){
					jthkCzFlag = true;
				}
				data.put("jthkCzFlag", String.valueOf(jthkCzFlag));
				// ��ͥ��Ա
				List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
				data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
			}
			
			//����ʦ����ѧ
			if("10718".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				
			}
			
			//���������ѧ
			if("10052".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				data.put("qsxx", qsxx);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				xsjbxx.put("csnyr", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.day));// ��������
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
				// ��ͥ��Ա
				JtqkdcService jtqkService = new JtqkdcService();
				List<HashMap<String, String>> jtcyxxList_zymzdx = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_zymzdx, 5 - jtcyxxList_zymzdx.size());
				data.put("jtcyxxList_zymzdx", jtcyxxList_zymzdx);
				// ��Ŀѧ��ѧ�ڱ��޿γɼ�
				List<HashMap<String,String>> xmXnxqBxCjList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "����");
				servicePjPy.addBlankList(xmXnxqBxCjList, 8 - xmXnxqBxCjList.size());
				data.put("xmXnxqBxCjList", xmXnxqBxCjList); 
				String xmXnxqBxCjPjf = service.getPjf(xmXnxqBxCjList, 2); // ƽ���ɼ�
				data.put("xmXnxqBxCjPjf", xmXnxqBxCjPjf);
				// ���Ž���
				DtxxjgService dtxxjgService = new DtxxjgService();
				List<HashMap<String, String>> jdlist = dtxxjgService.getGrJdxx(xh);
				String rdrtsj = "";
				String zzmmmc = xsjbxx.get("zzmmmc");
				if(StringUtils.isNotNull(zzmmmc)){
					for (HashMap<String, String> jdMap : jdlist) {
						String jddm = jdMap.get("jddm");
						String kssj = jdMap.get("kssj");
						if((zzmmmc.contains("��Ա") && "02".equals(jddm))
							|| (zzmmmc.contains("Ԥ����Ա") && "09".equals(jddm))
							|| (zzmmmc.contains("��Ա") && !zzmmmc.contains("Ԥ����Ա") && "11".equals(jddm))){
							rdrtsj = kssj;
						}
					}
				}
				data.put("rdrtnyr", DateTranCnDate.fomartDateToCn(rdrtsj,FomartDateType.day));
				// �ѻ���
				StringBuffer yhjxBuffer = new StringBuffer();
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("��");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 5 - pjjgList.size());
				data.put("pjjg_10052", pjjgList);
				int size1=(5 - pjjgList.size())<0?0:(5 - pjjgList.size());
				data.put("blank_10052", getBlankList(size1));
				HashMap<String, String> dkfs = pjjgService.getCjfsList(model.getXh(),model.getXn());
				data.put("dkzgf", dkfs.get("max"));
				data.put("dkzdf", dkfs.get("min"));
				data.put("pjcjjdfs", dkfs.get("pjcjjdfs"));
				List<HashMap<String, String>> hjqkList = service.getHjqk(model.getXh(),model.getXn());
				data.put("hjqkList",hjqkList);
				KnsjgService knsjgService = new KnsjgService();
				HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
				data.put("rddcmc", knsjg.get("dcmc"));//�϶�����
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("pm", pm);
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("Ʒ��")){
						data.put("pdfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("ѧҵ")){
						data.put("xyfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("����")){
						data.put("wtfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("�۲�")){
						data.put("zcpm", zcfMap.get("bjpm"));
						continue;
					}
				}
				//========== ��ȡ��һѧ��ɼ������������������޿μ������� begin ============
				String xnTemp = Base.currXn.substring(0,4);
				String beforXn = String.valueOf(Integer.parseInt(xnTemp)-1) + "-" + String.valueOf(Integer.parseInt(xnTemp)); // ��ѧ��
					//���޿μ�����
				// �༶����
				CpmdService cpmdService = new CpmdService();
				String bjrsForZymzdx = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				data.put("bjrsForZymzdx", bjrsForZymzdx);	
				String cjpm = zcfService.getCjpm(beforXn, xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(beforXn, xh);
				String bxkjgs = zcfService.getBxkjgs(beforXn, xh);
				data.put("cjpmForZymzdx", cjpm);// �ܳɼ�����
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// ���޿���
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// ���޿μ�����
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// ��������
				data.put("cpzrsForZymzdx", cpzrs);
				
				//========== ��ȡ��һѧ��ɼ������������������޿μ������� end ============
				
				/*��ȡ��һ��������*/
				data.put("shyj1", new CommShlcImpl().getShyjListByYwid(model.getId()).get(0).get("shyj"));
				data.put("shyj2", new CommShlcImpl().getShyjListByYwid(model.getId()).get(1).get("shyj"));
				data.put("shyj3", new CommShlcImpl().getShyjListByYwid(model.getId()).get(2).get("shyj"));
			}
			
			//����ʦ��ѧԺ���Ի�������ʦ��ѧԺ��
			if("10647".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // �������
				data.put("pjnds", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
				data.put("nd", Base.currNd);
				data.put("njzwmc", xsjbxx.get("nj").substring(2, 4) + "��"); // �������
				// ============ �������� begin==========
				String nl = "";
				//1994-12-24 19920217 ����ʽ
				String csrq = xsjbxx.get("csrq");//��ȡ��������
				if(StringUtils.isNotNull(csrq)){
					String[] yearAndMonth = DateUtils.calYears(csrq);
					if(yearAndMonth[0] != null)
						nl = yearAndMonth[0];
				}
				data.put("nl", nl);
				// ============ �������� end==========
				// ========== ���У�������ҵ�������ƺŵ�ʱ�� begin ============
				StringBuffer xjyxbysHjsjBuffer = new StringBuffer();
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(xmmc.contains("У�������ҵ��")){
						xjyxbysHjsjBuffer.append(pj.get("sqsjs")).append("��");
					}
				}
				String xjyxbysHjsj = xjyxbysHjsjBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("xjyxbysHjsj", xjyxbysHjsj);
				// ========== ���У�������ҵ�������ƺŵ�ʱ�� end ============
				// ========== ��������Ի� begin ============
				List<HashMap<String, String>> pjjgList =  pjjgService.getPjjgGroupByXn(xh);
				if(pjjgList.size() == 0){
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
				// ========== ��������Ի� end ============
			}
			
			//Ϋ��ѧԺ���Ի�
			if("11067".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				String rxny = xsjbxx.get("rxny") == null ? "" : xsjbxx.get("rxny");
				xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				xsjbxx.put("rxny_num", rxny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				// �ֽ����֤��begin
				String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
				for (int i = 0,j = xssfzh.length(); i < j; i++) {
					xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
				}
//				// ===============������������ begin===============
//				// ===========ɽ��ʡ��ʦ���������ҵ�������ɽ��ʡʦ���������ҵ������� begin==========
//				String sqly_11067_1 = sqly;
//				String sqly_11067_2 = "";
//				String bbdm = bbMap.get("bbdm");
//				int sqly_11067_max = 0;
//				if("sdsfsflyxbys_11067".equals(bbdm)){
//					sqly_11067_max = 224;
//				}else if("sdssflyxbys_11067".equals(bbdm)){
//					sqly_11067_max = 646;
//				}
//				if(sqly_11067_1.length() > sqly_11067_max){
//					sqly_11067_1 = sqly.substring(0, sqly_11067_max);
//					sqly_11067_2 = sqly.substring(sqly_11067_max);
//				}
//				data.put("sqly_11067_1", sqly_11067_1);
//				data.put("sqly_11067_2", sqly_11067_2);
//				// ===========ɽ��ʡ��ʦ���������ҵ�������ɽ��ʡʦ���������ҵ������� end==========
//				// ===========ɽ��ʡ�ߵ�ѧУ����ѧ���ǼǱ�ɽ��ʡ�ߵ�ѧУ����ѧ���ɲ��ǼǱ� begin==========
//				HashMap<String, String> sqly_11067_map = new HashMap<String, String>();
//				if("sdsgdxxyxxsgb_11067".equals(bbdm) || "sdsgdxxyxxs_11067".equals(bbdm)){
//					int rows = 12; // ����
//					int words = 35; // ÿ���������
//					sqly_11067_max = 420;
//					String sqly_11067_d2y = ""; // �ڶ�ҳ
//					if(sqly.length() > sqly_11067_max){
//						sqly_11067_d2y = sqly.substring(sqly_11067_max); 
//					}
//					sqly_11067_map.put("sqly_11067_d2y", sqly_11067_d2y);
//					String temp = sqly;
//					for (int i = 1; i <= rows; i++) {
//						if(temp.length() > words){
//							sqly_11067_map.put("sqly_11067_" + i, temp.substring(0, words));
//							temp = temp.substring(words);
//						}else{
//							sqly_11067_map.put("sqly_11067_" + i, temp);
//							temp = "";
//						}
//					}
//				}
//				data.put("sqly_11067_map", sqly_11067_map);
//				// ===========ɽ��ʡ�ߵ�ѧУ����ѧ���ǼǱ�ɽ��ʡ�ߵ�ѧУ����ѧ���ɲ��ǼǱ� end==========
//				// ===============������������ end===============
				// ͨ��ѧ�Ų�ѯ�۲����
				List<HashMap<String,String>> zcfNList = zcfService.getZcfsNList(xh);
				data.put("zcfNList_11067", zcfNList);
			}
			
			// ����ְ��
			DwwhService dwwhService = new DwwhService();
			data.put("zwmc", dwwhService.getZwForXh(xh));
			
			// �ֽ����֤��begin
			String xssfzh = xsjbxx.get("sfzh") == null || "".equals("sfzh") ? ""
					: xsjbxx.get("sfzh");
			int sylen = 18 - xssfzh.length();
			for (int i = 0; i < xssfzh.length(); i++) {
				xsjbxx.put("sfzh" + i, xssfzh.charAt(i) + "");
			}
			for (int i = 0; i < sylen; i++) {
				xsjbxx.put("sfzh" + (xssfzh.length() + i), "");
			}
			
			// �ֽ����֤��end
			
			//ѧ����Ƭ
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);
			
			//�ɶ�����ѧԺ���Ի�
			if("10653".equals(Base.xxdm)){
				String nl = "";
				//��������
				//1994-12-24 19920217 ����ʽ
				String csrq = xsjbxx.get("csrq");//��ȡ��������
				
				if(StringUtils.isNotNull(csrq)){
					String[] yearAndMonth = DateUtils.calYears(csrq);
					if(yearAndMonth[0] != null)
						nl = yearAndMonth[0];
				}
				data.put("nl", nl);
				String[] code = new String[]{"01","02","03"}; //����Ա���� �й�������01 Ԥ��02 ��Ա03
				//�ж��Ƿ��ǵ���Ա
				if((!StringUtil.isNull(xsjbxx.get("zzmm")))&&(ArrayUtil.contains(code, xsjbxx.get("zzmm")))){
					data.put("sfsdty", "��");
				}else{
					data.put("sfsdty", "��");
				}
				//��ȡͬ�꼶ͬרҵ����
				String zyrs = service.getTjzyrs(model.getXh(), model.getXn(), model.getXq());
				data.put("tjzyrs", zyrs);
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				
				String[] shyj = new String[]{"","","",""};
				for (int i = 0; i < shyjList.size(); i++) {
					shyj[i] = shyjList.get(i).get("shyj");
				}
				data.put("shyj1", shyj[0]);
				data.put("shyj2", shyj[1]);
				data.put("shyj3", shyj[2]);
				data.put("shyj4", shyj[3]);
				
				//��ͥ��Ա��Ϣ
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList_10653 = xsxxglService.getJtcyxxXsList(xh);
				
				data.put("jtcyxxList_10653", jtcyxxList_10653);
				int size=(3 - jtcyxxList_10653.size())<0?0:(3 - jtcyxxList_10653.size());
				data.put("blankList_10653", getBlankList(size));
				
				//�������϶���������
				KnsjgService knsjgService = new KnsjgService();
				HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
				data.put("dcmc", knsjg.get("dcmc")==null?"":knsjg.get("dcmc"));
			}
			
			
			
			//����ҽ�ƴ�ѧ���Ի����ű���
//			if("10598".equals(Base.xxdm)){
//				if("�������ء�����ѧ���ɲ���ѧ��".equals(model.getXmmc()) 
//						|| "�˽��ѧ��".equals(model.getXmmc())
//						|| "�ٺͽ�����ѧ��".equals(model.getXmmc())
//						|| "����ѧ��".equals(model.getXmmc()) 
//						|| "����ѧ���ɲ�".equals(model.getXmmc())
//				){
					//��Ŀ��������
				if(null!=model && null!=model.getLxdm() && !"".equals(model.getLxdm())){
					PjdmModel pjdmModel = new PjdmModel();
					pjdmModel.setXmlxdm(model.getLxdm());
					PjdmService pjdmService = new PjdmService();
					PjdmModel xmlxModel = pjdmService.getModel(pjdmModel);
					model.setLxdmmc(xmlxModel.getXmlxmc());
				}
//				}
//			}
			//����ʹ��
			//xh="3060601025"; 
			HashMap<String, String> zcf = zcfService.getZczfByXh(xh, model.getXn(), model.getXq());
			
			//����ѧ�ż���ѧ��ѧ��ɼ�
			List<HashMap<String,String>> cjList = xsxxService.getCjList(xh,model.getXn(),model.getXq());
			//��ȡƽ������
			String pjfs=service.getAverage(cjList);
			xsjbxx.put("pjfs",pjfs );
			
			//�������϶�����
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
			data.put("rddc", knsjg.get("rddc")==null?"":knsjg.get("rddc"));//�϶�����
			
			//��ȡ������ɼ�����
			String bjgcjs=service.getBjgcjNum(xh, model.getXn(), model.getXq());
			xsjbxx.put("bjgcjs",bjgcjs );
			
			//�㽭��ѧ������ѧԺ���Ի�
			if("13022".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				// ���޲�����γ�
				if(StringUtils.isNull(bjgcjs)){
					xsjbxx.put("ywbjgmc", "��");
				}else{
					xsjbxx.put("ywbjgmc", "��");
				}
				// ����Υ�ʹ���
				if(yscfqk==null || yscfqk.size() == 0){
					xsjbxx.put("ywwjcfmc", "��");
				}else{
					xsjbxx.put("ywwjcfmc", "��");
				}
				// ��������Ŀ
				StringBuffer ysqxmBuffer = new StringBuffer();
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					// ƴ����������Ŀ
					if(StringUtils.isNotNull(xmmc)){
						ysqxmBuffer.append(xmmc).append(" ");
					}
				}
				data.put("ysqxmmc", ysqxmBuffer.toString());
				
				// ============ �����������Ŀ�����ж��Ƿ�ѡ�� begin ============
				String sqxmmc = model.getXmmc();
				// ��ѧ�� ģ���Ƿ�ѡ���ж�
				if(sqxmmc.equals("һ�Ƚ�ѧ��")){
					data.put("ydjxjflag", "true");
				}else if(sqxmmc.equals("���Ƚ�ѧ��")){
					data.put("edjxjflag", "true");
				}else if(sqxmmc.equals("���Ƚ�ѧ��")){
					data.put("sdjxjflag", "true");
				}else if(sqxmmc.equals("ѧϰ���㽱ѧ��")){
					data.put("xxyxjxjflag", "true");
				}else if(sqxmmc.equals("��Ṥ�����㽱ѧ��")){
					data.put("shgzyxjxjflag", "true");
				}else if(sqxmmc.equals("������ѧ��")){
					data.put("xthdjxjflag", "true");
				}
				// �����ƺ� ģ���Ƿ�ѡ���ж�
				if(sqxmmc.equals("����ѧ��")){
					data.put("shxsrychflag", "true");
				}else if(sqxmmc.equals("����ѧ���ɲ�")){
					data.put("yxxsgbrychflag", "true");
				}
				// ============ �����������Ŀ�����ж��Ƿ�ѡ�� end ============
				
				// �ɼ�
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.equals("ѧҵˮƽ")){
						xsjbxx.put("xyspnjzypmdy", zcfMap.get("njzypm"));
					}else if(xmmc.equals("˼��Ʒ��")){
						xsjbxx.put("sxpdbjpmdy", zcfMap.get("bjpm"));
					}else if(xmmc.equals("�ۺ�����")){
						xsjbxx.put("zhnlbjpmdy", zcfMap.get("bjpm"));
					}else if(xmmc.equals("��������")){
						xsjbxx.put("stszfsdy", zcfMap.get("fs"));
					}
				}
				// �༶����
				CpmdService cpmdService = new CpmdService();
				String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				xsjbxx.put("bjrs", bjrs);
				//��ȡͬ�꼶ͬרҵ����
				String zyrs = servicePjPy.getTjzyrs(model.getXh(), model.getXn(), model.getXq());
				data.put("tjzyrs", zyrs);
			}
			
			//������ҵ��ѧ���Ի�
			if("10022".equals(Base.xxdm)){
				//��ȡ��ӡ����
				XsxxglService xsxxglService = new XsxxglService();
				String dyrq = xsxxglService.getDqrq(xh);
				data.put("dyrq", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				// �༶����
				CpmdService cpmdService = new CpmdService();
				String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				xsjbxx.put("bjrs", bjrs);
				// ��������Ŀ
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				// ��ǰ��Ŀ��ѧ��ѧ��
				String xnDqxm = model.getXn();
				if(xnDqxm != null){
					xnDqxm = xnDqxm.trim();
				}
				String xqmcDqxm = (model.getXqmc() == null) ? "" : model.getXqmc();
				if(xqmcDqxm != null){
					xqmcDqxm = xqmcDqxm.trim();
				}
				String xnXqmcDqxm = xnDqxm + xqmcDqxm;
				List<HashMap<String, String>> pjzqPjjgList = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xnTemp = pj.get("xn");
					String xqmcTemp = pj.get("xqmc");
					if(xnTemp != null){
						xnTemp = xnTemp.trim();
					}
					if(xqmcTemp != null){
						xqmcTemp = xqmcTemp.trim();
					}
					// ��ǰ��Ŀ��ѧ��ѧ���£�ѧ��������
					if(xnXqmcDqxm.equals(xnTemp + xqmcTemp)){
						pjzqPjjgList.add(pj);
					}
				}
				// ��ǰ��Ŀ��ѧ���£�ѧ��������
				String xnXqmcDqxn = xnDqxm;
				List<HashMap<String, String>> XnPjjgList = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xnTemp = pj.get("xn");
					if(xnTemp != null){
						xnTemp = xnTemp.trim();
					}
					if(xnXqmcDqxn.equals(xnTemp)){
						XnPjjgList.add(pj);
					}
				}
				data.put("XnPjjgList", XnPjjgList);
				data.put("XnPjjgListSize", XnPjjgList.size());
				data.put("pjzqPjjgList", pjzqPjjgList);
				data.put("pjzqPjjgListSize", pjzqPjjgList.size());
			}
			
			//������·ְҵ����ѧԺ���Ի�
			if("13943".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				servicePjPy.addBlankList(pjjg, 1 - pjjg.size());
				data.put("pjjg", pjjg);
			}
			
			//�й�����ѧԺ���Ի�
			if("10355".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				PjjgService pjjgService = new PjjgService();
//				List<HashMap<String, String>> pjjgL4 =  pjjgService.getHjqkByXhMap(xh,4);
//				data.put("pjjgLine1", pjjgL4.get(0));
//				data.put("pjjgLine2", pjjgL4.get(1));
//				data.put("pjjgLine3", pjjgL4.get(2));
//				data.put("pjjgLine4", pjjgL4.get(3));
				String xq = StringUtils.isNotNull(model.getXq()) ? model.getXq() : "on";
				List<HashMap<String, String>> pjjgL4 =  pjjgService.getHjxxList(xh,model.getXn(),xq,"4");
				data.put("pjjgLine1", pjjgL4.get(0));
				data.put("pjjgLine2", pjjgL4.get(1));
				data.put("pjjgLine3", pjjgL4.get(2));
				data.put("pjjgLine4", pjjgL4.get(3));
				data.put("pjjgL4", pjjgL4);
				data.put("xxdm", Base.xxdm);
				data.put("ywwj", yscfqk.size()>0);
				String xmmc = model.getXmmc();
				String xmdm = model.getXmdm();
				
				//��ȡѧ��ѧϰ���
				CpmdService cpmdService = new CpmdService();
				HashMap<String,String> bxncjMap = servicePjPy.getDjbZcfListByXhXn(xh, model.getXn(), "����");
				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("bxncjbjpm", bxncjMap.get("bxncjbjpm")); // ��ѧ��ɼ��༶����
				data.put("shxs_xn_bjrs", shxs_xn_bjrs); // ��ѧ��༶����
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// ��������
				data.put("cpzrsForZymy", cpzrs);
				//���޿μ�����
				String bjrsForZymy = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bxksForZymy", "0".equals(bxkjgs) ? "" : bxks);// ���޿���
				data.put("bxkjgsForZymy", "0".equals(bxkjgs) ? "" : bxkjgs );// ���޿μ�����
				data.put("cjpmForZymy", cjpm);// �ܳɼ�����
				data.put("bjrsForZymy", "".equals(cjpm) ? "" : bjrsForZymy);// �༶����
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String zCxmmc = zcfMap.get("xmmc").trim();
					if(zCxmmc.contains("�۲�")){
						data.put("zcpm", zcfMap.get("bjpm"));
						continue;
					}
				}
				String cprs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// ��������
				data.put("cpzrsForZymy", cprs);
				//����word����ѧԺ������ȥ��xymc�����2����
				String xymc = model.getCpxymc().substring(0,model.getCpxymc().length() -2);
				data.put("xymc", xymc);

				//��ȡ��������
				String jxmc = model.getXmmc();
				data.put("jxmc",jxmc);
				//�жϽ��������Ƿ��������ѧ���ֶ�
				if(jxmc.indexOf("����ѧ��")!= -1){
					data.put("isShxs","1");
				} else {
					data.put("isShxs","0");
				}
				// ����Υ�ʹ���
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("ywcfmc", "��");
				}else{
					data.put("ywcfmc", "��");
				}
				//��������
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				String bkks = xxcjMap.get("bkcjnum");
				//�����������
				String bkjg = xxcjMap.get("xscjstr");
				data.put("bkjg", bkjg);
				if(bkks == null || "0".equals(bkks)){
					data.put("sfbk", "��");
				}else {
					data.put("sfbk", "��");
				}

				//�й���Ժ ѧ�����β���Υ����Ϣ
				HashMap<String,String> kkbkxxMap = servicePjPy.getKkbkxx(xh);
				data.put("kkbkxx", kkbkxxMap);

				//���ܲ��Գɼ�
				data.put("tycj",zcfService.getZcfsByXmXnXqXh("������",model.getXn(),model.getXq(),xh).get("fs"));
				
				//ʱ�����
				HashMap<String,String> sqsj = new HashMap<String, String>();
				HashMap<String,String> shsjfdy = new HashMap<String, String>();//����Ա
				HashMap<String,String> shsjyx = new HashMap<String, String>();//Ժϵ
				HashMap<String,String> shsjxx = new HashMap<String, String>();//ѧУ
				shsjxx = pjjgService.getHjshsjList(xmdm, "0");
				if(xmmc.indexOf("���ҽ�ѧ��") != -1 || xmmc.indexOf("ʡ����") != -1 ){
					sqsj = pjjgService.getHjshsjList(xmdm, "13");
					shsjfdy = pjjgService.getHjshsjList(xmdm,"11");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
					
				}else if(xmmc.indexOf("������־��ѧ��") != -1){
					sqsj = pjjgService.getHjshsjList(xmdm, "9");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
				}else if(xmmc.indexOf("������ѧ��") != -1){
					sqsj = pjjgService.getHjshsjList(xmdm, "12");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
				}else {
					
				}
				data.put("sqsjs", sqsj);
				data.put("shsjfdy", shsjfdy);
				data.put("shsjyx", shsjyx);
				data.put("shsjxx", shsjxx);
			}

			//��̶��ѧ
			if("10530".equals(Base.xxdm)){
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> pjjgL4 =  xsxxglService.getXsxxHjqkNewList(xh,4);
				data.put("pjjgL4", pjjgL4);

				String zzmmmc = xsjbxx.get("zzmmmc");

				if(zzmmmc!=null){
					if (zzmmmc.equals("�й���������Ա")) {
						xsjbxx.put("zzmmmc", "�й���Ա");
					}else if(zzmmmc.equals("�й�����������������Ա")){
						xsjbxx.put("zzmmmc", "������Ա");
					}
				}
			}
			
			//�������ϴ�ѧ���Ի�
			if("11417".equals(Base.xxdm)){
				// ���ñ������ϴ�ѧ����
				data.put("bjlhdxbt", model.getXmmc());
				
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // �������
				// ��������Ŀ
				StringBuffer ysqxmBuffer = new StringBuffer();
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					if(StringUtils.isNotNull(pj.get("xmmc"))){
						ysqxmBuffer.append(pj.get("xmmc")).append(" ");
					}
				}
				xsjbxx.put("ysqxmmc", ysqxmBuffer.toString());
				// ��ȡ���ֳɼ�
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("ѧҵ")){
						xsjbxx.put("xycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("����")){
						xsjbxx.put("wtcjfs", zcfMap.get("fs"));
					}
				}
			}
			//������ְҵ����ѧУ
			if("90211".equals(Base.xxdm)) {
				
				data.put("xmmc", model.getXmmc());// ��Ŀ����
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				//�ж��Ƿ��ǵ���Ա
				if((!StringUtil.isNull(xsjbxx.get("zzmm")))&&( "03".equals(xsjbxx.get("zzmm")))){
					data.put("sfty", "��");
				}else{
					data.put("sfty", "��");
				}
				// ��ȡ���ֳɼ�
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("��ѧ")){
						xsjbxx.put("jxcjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("����")){
						xsjbxx.put("tycjfs", zcfMap.get("fs"));
					}
				}		
				
			}
			
			
			//�㽭����ְҵѧԺ���Ի�
			if("12867".equals(Base.xxdm)){
				// ��ȡ���ֳɼ�
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						xsjbxx.put("zyfbjpm", zcfMap.get("bjpm"));
					}
				}
				// ��߷֡���ͷ֡�ƽ���֡�����������ѧϰ�ɼ�
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				if(xxcjMap == null){
					xxcjMap = new HashMap<String,String>();
				}
				data.put("xxcjMap", xxcjMap);
				// �༶����
				CpmdService cpmdService = new CpmdService();
				String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				xsjbxx.put("bjrs", bjrs);
				//�㽭���ι��ҽ�ѧ��
				//����ȡֵ
				PjjgService pjjgService = new PjjgService();						
				//���ҽ�ѧ�� ȡ����������
				HashMap<String, String> pjjg12867 =  pjjgService.getZjlyByXhMap(xh,model.getXn());
				data.put("pjjg12867", pjjg12867);
				//�ۺ���������
				HashMap<String, String> rspm = pjjgService.getZjlyByPm(xh,model.getXn());
				data.put("rspm", rspm);	
				//�㽭������־��ѧ���л�ȡ�ֶΣ���ͥ�����
				HashMap<String,String> lzjxj = pjjgService.getZjlylzByXhMap(xh, model.getXn());
				data.put("lzjxj",lzjxj);	
				//�㽭����ѧϰ�ɼ�������������ĳɼ���������У��ѧ��
				HashMap<String,String> zjlyxxcj = pjjgService.getZjlyXxqkCj(xh, model.getXn());
				data.put("zjlyxxcj",zjlyxxcj);
				//�㽭ʡ������ѧ���㽭����
				HashMap<String, String> zjszf12867 =  pjjgService.getZjszfByXhMap(xh,model.getXn());
				data.put("zjszf12867", zjszf12867);
				//�㽭����ְҵѧԺ��ѧ��
				HashMap<String, String> zjlyzyxy12867 =  pjjgService.getZjlyzyxyfByXhMap(xh,model.getXn());
				data.put("zjlyzyxy12867", zjlyzyxy12867);
				//�㽭����ʡ�������ҵ��
			if (model.getXmmc().indexOf("ʡ�������ҵ��")!=-1) {
				HashMap<String, String> sjbys =  pjjgService.getZjlySjyxbys(xh,model.getXn());
				if (!sjbys.isEmpty()) {
					sjbys.put("f_brjl", HtmlUtil.xmlZy(sjbys.get("f_brjl")));
					sjbys.put("f_zysj", HtmlUtil.xmlZy(sjbys.get("f_zysj")));
					sjbys.put("f_zxqjhjqk", HtmlUtil.xmlZy(sjbys.get("f_zxqjhjqk")));
					sjbys.put("sqsj",  DateUtils.getDayOfCn(sjbys.get("sqsj")));
					sjbys.put("f_usertask2shsj", StringUtil.isNull(sjbys.get("f_usertask2shsj")) ? "" : DateUtils.getDayOfCn(sjbys.get("f_usertask2shsj")));
					sjbys.put("f_usertask3shsj", StringUtil.isNull(sjbys.get("f_usertask3shsj")) ? "" : DateUtils.getDayOfCn(sjbys.get("f_usertask3shsj")));
					sjbys.put("f_usertask4shsj", StringUtil.isNull(sjbys.get("f_usertask4shsj")) ? "" : DateUtils.getDayOfCn(sjbys.get("f_usertask4shsj")));
				}
				data.put("sjbys", sjbys);
			}
				//�㽭ʡ����ѧԺ�����ҵ��
			if (model.getXmmc().indexOf("Ժ�������ҵ��")!=-1) {
				HashMap<String, String> yjbys =  pjjgService.getZjlyxyyxbys(xh,model.getXn());
				if (!yjbys.isEmpty()) {
					yjbys.put("f_brjl", HtmlUtil.xmlZy(yjbys.get("f_brjl")));
					yjbys.put("f_zysj", HtmlUtil.xmlZy(yjbys.get("f_zysj")));
					yjbys.put("f_zxqjhjqk", HtmlUtil.xmlZy(yjbys.get("f_zxqjhjqk")));
					yjbys.put("sqsj",  DateUtils.getDayOfCn(yjbys.get("sqsj")));
					yjbys.put("f_usertask2shsj", StringUtil.isNull(yjbys.get("f_usertask2shsj")) ? "" : DateUtils.getDayOfCn(yjbys.get("f_usertask2shsj")));
					yjbys.put("f_usertask3shsj", StringUtil.isNull(yjbys.get("f_usertask3shsj")) ? "" : DateUtils.getDayOfCn(yjbys.get("f_usertask3shsj")));
					yjbys.put("f_usertask4shsj", StringUtil.isNull(yjbys.get("f_usertask4shsj")) ? "" : DateUtils.getDayOfCn(yjbys.get("f_usertask4shsj")));
				}
				data.put("yjbys",yjbys);
			}
			
			}
						
			//�Ϻ����ѧԺ���Ի�
			if("11458".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				// ��ǰ��Ŀ��ѧ��
				data.put("xn_11458", model.getXn().replace("-", "/"));
			}
			
			List<HashMap<String, String>> pjjg =  service.getPjpyInfoMapForDjb(xh);
			List<HashMap<String, String>> pjjgAll =  service.getPjpyInfoList(xh);
			
			//����ũҵ��ѧ
			if("10504".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				//����ȡֵ
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg10504 =  pjjgService.getHznydxPjpyMap(xh);
				data.put("pjjg10504", pjjg10504);
				int size1=(4 - pjjg10504.size())<0?0:(4 - pjjg10504.size());
				data.put("blankList1", getBlankList(size1));
				
			}
			//�㽭��ҽҩ��ѧ
			if("10344".equals(Base.xxdm)){
				//������Ŀͬѧ���ý�ѧ�����ƣ��ȼ���
				PjjgService pjjgService = new PjjgService();
				String jxjdj = pjjgService.getJxjmcByXhXn(xh,model.getXn());
				data.put("jxjdj",jxjdj);
				// ����������
				CpmdService cpmdService = new CpmdService();
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());
				data.put("cpzrs", cpzrs);
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("pm", pm);
				String bjrs = zcfService.getBjrs(xh);
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrs", "".equals(cjpm) ? "" : bjrs);// �༶����
				data.put("cjpm", cjpm);// �ܳɼ�����
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// ���޿���
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// ���޿μ�����
			}
			/*��������ְҵ����ѧԺ���Ի�**/
			if("13828".equals(Base.xxdm)){
				
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){
					xsjbxx.put("cfbjg", "��");
				}else{
					xsjbxx.put("cfbjg", "��");
				}
				
				StringBuffer buffer = new StringBuffer();
				
				if(pjjg != null && pjjg.size() >= 1)
					buffer.append("�������");
				for (HashMap<String, String> pj : pjjg) {
					if(StringUtils.isNotNull(pj.get("xmmc")))
						buffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(pj.get("xmmc")).append("��");
				}
				
				data.put("hjqk", buffer.toString());
				data.put("bjrs", service.getTbjrs(xh));
			}
			
			//�㽭��ý
			if("11647".equals(Base.xxdm)) {
				HashMap<String, String> bxk = service.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> pm = service.getPm(model.getXh(),model.getXn());
				
				data.put("bxk", bxk);
				data.put("pm", pm);
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				String beforXn = " "+model.getXn().substring(0, 4)+" �� "+model.getXn().substring(5, 9);
				data.put("beforXn", beforXn);
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
			}
			//�ൺ����ѧԺ
			if("10868".equals(Base.xxdm)){
				/*ȡ��ѧ���ۺϳɼ���*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*ȡѧ���ۺϳɼ�����*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				/*����ѧ��ȡ��ǰ�������ڵĲ����༶����*/
				String xsszcpbjRs = service.getXsszcpbjRsForxh(model.getXh(),model.getXn());
				data.put("xsszcpbjRs", xsszcpbjRs);
			}
			//���ݴ�ѧ
			if("10351".equals(Base.xxdm)) {
				HashMap<String, String> bxk = service.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("bxk", bxk);
				data.put("pm", pm);
			}	
			//��������ְҵѧԺ
			if("13957".equals(Base.xxdm)) {
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
			}		
			
			//������Ͽҽҩ
			if("14008".equals(Base.xxdm)) {
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				HashMap<String,String> bjxxMap = servicePjPy.getBjxx(xsjbxx.get("bjdm"));
				data.put("xmmc", model.getXmmc());
				data.put("bjrs", bjxxMap.get("bjrs"));
				data.put("bzrxm", bjxxMap.get("bzrxm"));
				data.put("fdyxm", bjxxMap.get("fdyxm"));
				data.put("currXn", Base.currXn);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (zzmmmc!=null && zzmmmc.equals("�й���������Ա")) {
					data.put("zzmmmc", "�й���Ա");
				}else {
					data.put("zzmmmc", zzmmmc);
				}
			}
			//�㽭����ְҵѧԺ
			if("12869".equals(Base.xxdm)) {
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				/*��ӡ����*/
				data.put("dyrq",DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (zzmmmc!=null && zzmmmc.equals("�й���������Ա")) {
					data.put("zzmmmc", "�й���Ա");
				}else {
					data.put("zzmmmc", zzmmmc);
				}
			}
			//�㽭����ѧԺ���Ի�
			if("11483".equals(Base.xxdm)){
				StringBuilder buffer = new StringBuilder();
				if(pjjg != null && pjjg.size() >= 1)
				for (HashMap<String, String> pj : pjjg) {
					if(StringUtils.isNotNull(pj.get("xmmc")))
						buffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(pj.get("xmmc")).append(";");
				}
				data.put("zwmc", dwwhService.getZwForXh(xh));
				data.put("hjqk", buffer.toString());
				DtxxjgService dtxxjgService = new DtxxjgService();
				StringBuilder ber = new StringBuilder();
				String rtrq = dtxxjgService.getGrDtjgxx(xh,"02").get("kssj");
				String dyrq = dtxxjgService.getGrDtjgxx(xh,"09").get("kssj");
				ber.append(rtrq==null?"��":rtrq).append("��").append(dyrq==null?"��":dyrq);
				data.put("zzmmrq", ber.toString().replace(" ",""));
				String zjjcpjcj = xsxxService.getZjjcPjcj(xh, model.getXn(), model.getXq());
				String zjjczdcj = xsxxService.getZjjcZdcj(xh, model.getXn(), model.getXq());
				data.put("zjjcpjcj", StringUtils.isNull(zjjcpjcj)?"0":zjjcpjcj );
				data.put("zjjczdcj", StringUtils.isNull(zjjczdcj)?"0":zjjczdcj );
				String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						data.put("dyf", zcfMap.get("fs"));
					}
					if(xmmc.contains("����")){
						data.put("tyf", zcfMap.get("fs"));
					}
					
					
				}
				if(model.getXmmc().indexOf("��ѧ��")!=-1){
					String xmdj = model.getXmmc().substring(0,model.getXmmc().indexOf("��ѧ��"));
					data.put("xmdj", xmdj);
				}	
			}
			//����ũҵ��ѧ
			if("10504".equals(Base.xxdm)){
				
				//��ȡѧ����ƽ���ɼ���������
				HashMap<String,String> pjcj = xsxxService.getHznyPjcjWithPm(xh,model.getXn(), model.getXq());
				data.put("pjcj", pjcj);
				
				//��ȡ��ѧ��ȼ�
				List<HashMap<String,String>> hjjx = service.getPjpyInfoMap(xh);
				if (null != hjjx && hjjx.size() >= 1) {
					for (HashMap<String, String> pj : hjjx) {
						if (!StringUtil.isNull(pj.get("xq"))
								&& !StringUtil.isNull(model.getXq())) {
							if (pj.get("xn").equals(model.getXn())
									&& pj.get("xq").equals(model.getXq())
									&& pj.get("xmmc").contains("�Ƚ�ѧ��")) {
								String xmdj = pj.get("xmmc").substring(0,
										pj.get("xmmc").indexOf("��ѧ��"));
								data.put("xmdj", xmdj);
							}
						} else {
							if (model.getXn().equals(pj.get("xn"))
									&& pj.get("xmmc").contains("�Ƚ�ѧ��")) {
								String xmdj = pj.get("xmmc").substring(0,
										pj.get("xmmc").indexOf("��ѧ��"));
								data.put("xmdj", xmdj);
							}
						}
					}
				}
				
				//��������
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);
				data.put("crzwmc", dwwhService.getCrZwForXh(xh));
				if(model.getXmmc().contains("���")){
					WdgwsqService wdgwsqService = new WdgwsqService();
					HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
					String qsbh=null;
					if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
						qsbh="";
					}else{
						qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
					}
					data.put("qsbh", qsbh);
				}
			}
			
			//�㽭��ҵְҵ��ѧ��ǼǱ�
			if(Globals.XXDM_ZJSYZYXY.equals(Base.xxdm)){
					StringBuilder buffer = new StringBuilder();
					if(pjjg != null && pjjg.size() >= 1)
					for (HashMap<String, String> pj : pjjg) {
						if(StringUtils.isNotNull(pj.get("xmmc"))){
							buffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(pj.get("xmmc")).append("; \n ");
						}	
					}
					data.put("hjqk",buffer.toString());
					data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // �������
					data.put("zwmc", dwwhService.getZwForXh(xh));//ְ��					
					data.put("dyrq",DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
			}
			
			xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
			// ����ѧ����ͥ������Ϣ
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}						
			//���ؼ�ͥ��Ա��Ϣ			
		
			XsxxglService xsxxglService = new XsxxglService();
			List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
			
			data.put("jtcyxxList", jtcyxxList);
			int size=(5 - jtcyxxList.size())<0?0:(5 - jtcyxxList.size());
			data.put("blankList", getBlankList(size));
			
			data.put("jtqk", jtqkmodel);//��ͥ���
			
			KnsdcService knsdcService = new KnsdcService();
			data.put("knsdcList", knsdcService.getKnsdcList());// ����������list
			//�γ̳ɼ�
			data.put("kccjList", xsxxglService.getStuCjOfXnList(xh, model.getXn()));
			//
			//����ʦ����������������Ϣ
			HashMap<String, String> spxxInfo =service.getSpxxInfo(model.getId());
			if(spxxInfo.isEmpty()){
				spxxInfo=new HashMap<String,String>();
				spxxInfo.put("ejtjdcdm", "");
				spxxInfo.put("sjtjdcdm", "");
			}
			data.putAll(spxxInfo);
			   //ɽ��������ҽְҵѧԺ
			if("12947".equals(Base.xxdm)){
				data.put("xn", model.getXn());
				String sfzh = xsjbxx.get("sfzh");
				for(int i = 0;i < sfzh.length();i++){
					data.put("z"+i, sfzh.substring(i, i+1));
				}
				//��ȡ�������Ʋ�����datamap
				String mzmc = xsjbxx.get("mzmc");
				data.put("mzmc", mzmc);
				//��������
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjgList =  pjjgService.getPjjgGroupByXn(xh);
				if(pjjgList.size() == 0){
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
				String  Jtyzsr = Float.parseFloat(jtqkmodel.getJtrjysr())*Integer.parseInt(jtqkmodel.getJtrs())+"";
				data.put("jtyzsr", Jtyzsr);
				List<HashMap<String , String>> bxnxsgbzwList = dwwhService.getZwForXhXn(xh, model.getXn()); // ѧ���ɲ�ְ������ʱ��
				HashMap<String , String> bxnxsgbzw1 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 0){
					bxnxsgbzw1 = bxnxsgbzwList.get(0);
				}
				HashMap<String , String> bxnxsgbzw2 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 1){
					bxnxsgbzw2 = bxnxsgbzwList.get(1);
				}
				data.put("bxnxsgbzw1", bxnxsgbzw1);
				data.put("bxnxsgbzw2", bxnxsgbzw2);
			    PjjgDao pjjgdao = new PjjgDao();
				HashMap<String, String> zccjmap = pjjgdao.getzccj(model);
//				CpmdService cpmdService = new CpmdService();
//				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("zccjmap", zccjmap);
//				data.put("bjrs", shxs_xn_bjrs);
				List<HashMap<String, String>> zccjlist = pjjgdao.getzccjList(model.getXh());
				data.put("zccjlist", zccjlist);
				data.put("xmmc", model.getXmmc());
			}
			CpmdService cpmdService = new CpmdService();
			String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
			
			
			//����ʦ����ѧ
			if("10718".equals(Base.xxdm)){
				//���޿γɼ�ƽ�����Լ�����
			    HashMap<String, String> pjfpmMap01 = service.getPjfRank(model, "01" ,"���޿�");
			    HashMap<String, String> pjfpmMap02 = service.getPjfRank(model, "02" ,"���޿�");
			    HashMap<String, String> zcMap= service.getZcPjfRank(model);
			    HashMap<String, String> djksMap= service.getDjksMap(xh);
			    List<HashMap<String, String>> pjjglist = service.getPjjgByxn(model);
			    data.putAll(pjfpmMap01);
			    data.put("pjf02", pjfpmMap02.get("pjf"));
			    data.put("pm02", pjfpmMap02.get("no"));
			    data.put("pjjglist", pjjglist);
			    data.putAll(zcMap);
			    if(djksMap != null){
			    	data.putAll(djksMap);
			    }
			    List<HashMap<String, String>> xnxqlist = service.getXnXqlist(xh,"bxk");
			    HashMap<String, String> tempMap = null;
			    double total = 0.0;
//			    for (HashMap<String, String> hashMap : xnxqlist) {
//			    	model.setXn(hashMap.get("xn"));
//			    	tempMap = service.getPjfRank(model, hashMap.get("xq"), "���޿�");
//			    	data.put("no"+hashMap.get("no"),tempMap.get("no") );
//			    	total += Float.parseFloat(tempMap.get("pjf"));
//				}
			    total = total/xnxqlist.size();
			    DecimalFormat decimalFormat=new DecimalFormat(".00");
			    String totalpjf=decimalFormat.format(total);
			    data.put("totalpjf", totalpjf);
			    List<HashMap<String, String>> xnxqlist1 = service.getXnXqlist(xh,"bxk");
			    HashMap<String, String> tempMap1 = null;
			    double total1 = 0;
			    for (HashMap<String, String> hashMap : xnxqlist1) {
			    	model.setXn(hashMap.get("xn"));
			    	tempMap1 = service.getZcPjfRank(model);
			    	data.put("zcmp"+hashMap.get("no"),tempMap1.get("zcpm") );
			    	data.put("zcmp02"+hashMap.get("no"),tempMap1.get("zcpm02") );
			    	total1 += Float.parseFloat(tempMap1.get("zcpjf"))+Float.parseFloat(tempMap1.get("zcpjf02"));
				}
			    total1 = total1/xnxqlist1.size();
			    String totalpjf1=decimalFormat.format(total1);
			    data.put("totalpjf1", totalpjf1);
			    List<HashMap<String, String>> jsjdjkslist = service.getJsjdjkslist(xh, "NCRE", "������ȼ�");
			    data.put("jsjdjkslist", jsjdjkslist);
			    List<HashMap<String, String>> yxxslist = service.getHjrycssj(xh, "����ѧ��");
			    List<HashMap<String, String>> yxxglist = service.getHjrycssj(xh, "����ѧ���ɲ�");
			    data.put("yxxslist", yxxslist);
			    data.put("yxxglist", yxxglist);
			    if(yxxslist.size() != 0 ){
				    data.put("yxxscs", yxxslist.get(0).get("cs"));
			    }
			    if(yxxglist.size() != 0){
			    	 data.put("yxxgcs", yxxslist.get(0).get("cs"));
			    }
			    if(pjjg != null){
			    	 for (HashMap<String, String> hashMap : pjjg) {
			    		    if( hashMap.get("sqsjs") != null ){
			    		    	hashMap.put("year", hashMap.get("sqsjs").substring(0, 4));
			    		    }else{
			    		    	hashMap.put("year","");
			    		    }
			    		    if( hashMap.get("sqsjs") != null ){
			    		    	hashMap.put("mon", hashMap.get("sqsjs").substring(5, 7));
			    		    }else{
			    		    	hashMap.put("mon","");
			    		    }
					    	
						}
			    }
			   
			    data.put("xh",xh);
			    HashMap<String, String> bxkcj = service.getPjfRank(model, null, "����");
			    data.put("xncj", bxkcj);
			    if(shxs_xn_bjrs != null && bxkcj.get("no") != null){
				    data.put("noper", decimalFormat.format(Double.parseDouble(bxkcj.get("no"))/Double.parseDouble(shxs_xn_bjrs)*100));
			    }
			    String xsdc = xsjbxx.get("pyccmc");
			    String sub="����";
			    String sub1="˶ʿ";
			    String sub2="��ʿ";
			    int bz1 = -1;
			    int bz2 = -1;
			    int bz3 = -1;
			    if(xsdc != null){
			    	 bz1 = xsdc.indexOf(sub);
				     bz2 = xsdc.indexOf(sub1);
				     bz3 = xsdc.indexOf(sub2);
			    }
			    if(bz1 >= 0){
			    	data.put("xsdc", "����");
			    }else if(bz2 >= 0){
			    	data.put("xsdc", "˶ʿ");
			    }else if(bz3 >= 0){
			    	data.put("xsdc", "��ʿ");
			    }
			    HashMap<String, String> zycj = service.getZyPjfRank(model, "���޿�");
			    HashMap<String, String> zyrs = service.getZyRs(model.getCpzydm());
			    data.put("zycj", zycj);
			    data.put("zyrs", zyrs);
			    if(model.getXmmc().indexOf("����")>=0){
			    	data.put("jxlx", "����");
			    }else if(model.getXmmc().indexOf("����")>=0){
			    	data.put("jxlx", "Т��");
			    }
			    List<HashMap<String,String>> zzxmlist = service.getzzxmjg(model.getXh(),model.getXn());
			    data.put("zzxmlist",zzxmlist);
			    HashMap<String, String> sqcs = service.getSqcs(model);
			    data.putAll(sqcs);
			    HashMap<String, String> jxcs = service.getJxcs(model);
			    data.putAll(jxcs);
			    List<HashMap<String, String>> xnlist = service.getXnList(xh);
//			    HashMap<String, String> tempMap01 = null;
//			    for (HashMap<String, String> hashMap : xnlist) {
//			    	model.setXn(hashMap.get("xn"));
//			    	tempMap01 = service.getPjfRank(model, null, "���޿�");
//			    	data.put("no"+hashMap.get("no"),tempMap.get("no") );
//			    	data.put("pjf"+hashMap.get("no"), hashMap.get("pjf"));
//				}
//			    HashMap<String, String> tempMap02 = null;
//			    for (HashMap<String, String> hashMap : xnlist) {
//			    	model.setXn(hashMap.get("xn"));
//			    	tempMap02 = service.getZyPjfRank(model, "���޿�");
//			    	data.put("zyno"+hashMap.get("no"), tempMap02.get("no"));
//				}
			}
			
			if("10277".equals(Base.xxdm)){
				data.put("jd",new PjxmsqService().getJd_10277(xh,model.getXn()));
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 4 - pjjgList.size());
				data.put("pjjgList", pjjgList);
				String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("���ز�����")){
						data.put("stzcf", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("�۲��ܷ�")){
						data.put("zczf", zcfMap.get("fs"));
						continue;
					}					
				}
			}
			//����ҽҩְҵѧУ
			if("70002".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);
				StringBuilder sb = new StringBuilder();
//				if(pjjg != null && pjjg.size() >= 1)
//					sb.append("�������");
				for (HashMap<String, String> pj : pjjg) {
					if(StringUtils.isNotNull(pj.get("xmmc")))
						sb.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(pj.get("xmmc")).append("��");
				}
				
				data.put("hjqk", sb.toString());
				Map<String,String> map = CsszService.getPjzq();
				String xn = map.get("xn");
				String xq = map.get("xq");
				String zccjBjpm = new CxddJgService().getZhcjPm(model.getXh(), xn, xq);
				data.put("zccjBjpm", zccjBjpm);
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						data.put("dyxf", zcfMap.get("fs"));
					}
					if(xmmc.contains("�۲��ܷ�")){
						data.put("zhpm", zcfMap.get("bjpm"));
					}
				}
				data.put("djjl", HtmlUtil.xmlZy(model.getDjjl()));
				data.putAll(pjxmsqService.getMaxOrMinWfkCj(xh, model.getXn(),model.getXq()));
				//������
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
				String rtsj = service.getkssj(model.getXh());
				data.put("rtsj", rtsj);
				String tyrs = service.gettyrs(model.getCpbjdm());
				data.put("tyrs", tyrs);
				//ѧ��ƴ�ӣ����
				String[] xnArr = model.getXn().split("-");
				data.put("qsxn", xnArr[0]);
				data.put("zhxn", xnArr[1]);
				int uqsnx = Integer.parseInt(xnArr[0])-1;//such as:2016->2015
				int uzhxn = Integer.parseInt(xnArr[1])-1;//such as:2017->2016
				StringBuilder usxn = new StringBuilder();
				String upsxn1 = usxn.append(uqsnx+"-"+uzhxn).toString();//such as:2016-2017 ->2015-2016
				String sxnhlw = xnArr[0].substring(xnArr[0].lastIndexOf("/")+3, xnArr[0].lastIndexOf("/")+5);//such as:2016->16
				String zxnhlw = xnArr[1].substring(xnArr[1].lastIndexOf("/")+3, xnArr[1].lastIndexOf("/")+5);//such as:2017->17
				data.put("sxnhlw", sxnhlw);
				data.put("zxnhlw", zxnhlw);
				int ssxnhlw = Integer.parseInt(sxnhlw)-1;//such as:16->15
				int zxxnhlw = Integer.parseInt(zxnhlw)-1;//such as:17->16
				data.put("upqsxn", ssxnhlw);
				data.put("upzhxn", zxxnhlw);
				//ѧ��д��ȡ02 ��01 
				String sxnxq = "02";
				String zxnxq = "01";
				int pjpm =0;
				List<HashMap<String,String>> zcfList2 = zcfService.getZcfListByXh(xh, upsxn1, sxnxq);
				for (HashMap<String, String> zcfMap : zcfList2) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						data.put("dyfs02_70002", zcfMap.get("fs"));
					}
					if(xmmc.contains("�۲��ܷ�")){
						data.put("zhpm_70002", zcfMap.get("bjpm"));
						if(StringUtils.isNotNull(zcfMap.get("bjpm"))){
							pjpm=pjpm+Integer.parseInt(zcfMap.get("bjpm"));
						}
					}
				}
				List<HashMap<String,String>> zcfList3 = zcfService.getZcfListByXh(xh, model.getXn(), zxnxq);
				for (HashMap<String, String> zcfMap : zcfList3) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						data.put("dyfs01_70002", zcfMap.get("fs"));
					}
					if(xmmc.contains("�۲��ܷ�")){
						data.put("pjcjjdpm", zcfMap.get("bjpm"));
						if(StringUtils.isNotNull(zcfMap.get("bjpm"))){
							pjpm=pjpm+Integer.parseInt(zcfMap.get("bjpm"));
						}
					}
				}
				data.put("pjpm_70002", pjpm/2);
			}
			//�㽭����ѧԺ
			if("10876".equals(Base.xxdm)){
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("sfwj", "��");
				}else{
					data.put("sfwj", "��");
				}
				//�����ɼ�
				data.put("tycj",zcfService.getZcfsByXmXnXqXh("������",model.getXn(),model.getXq(),xh).get("fs"));
				//־Ը�߷���Сʱ��
				data.put("zyfwxs",zcfService.getZcfsByXmXnXqXh("־Ը����Сʱ",model.getXn(),model.getXq(),xh).get("fs"));
				//��Ϊ�淶�ɼ��������֣�
				data.put("dyf",zcfService.getZcfsByXmXnXqXh("������",model.getXn(),model.getXq(),xh).get("fs"));
				//������
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
			}
			//�����Ƽ���ѧ
			if("10704".equals(Base.xxdm)){
				//data.put("jd",new PjxmsqService().getJd_10277(xh,model.getXn()));
				//������Ҫ�ǹ�����ѧ�𣬻�ȡ��ѧ�ڼ�������װ��list��
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 4 - pjjgList.size());
				data.put("pjjgList", pjjgList);
				int size1=(4 - pjjgList.size())<0?0:(4 - pjjgList.size());
				data.put("blankList1", getBlankList(size1));
				//���޿�~��·��רҵ�ɼ���רҵ����
				String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
				//�����Ƽ���ѧ�������м��Լ����޿�����
				HashMap<String, String> bxkms_10704 = service.getXakjdxylzjbxkms(model.getXh(),model.getXn());
				 //������޿�����
				data.put("yxkc", bxkms_10704.get("yxms"));
				//���ñ��޿�����
				data.put("lhkc", bxkms_10704.get("lhms"));
				//�еȱ��޿�����
				data.put("zdkc", bxkms_10704.get("zdms"));
				//������޿�����
				data.put("jgkc", bxkms_10704.get("jgms"));
				//���޿μ�������
				data.put("bxkjgms", bxkms_10704.get("bxkjgms"));
				//���޿�����
				data.put("bxkms", bxkms_10704.get("bxkms"));
				//��ѧ������
				List<HashMap<String, String>> hjqkList = service.getHjqk(model.getXh(),model.getXn());
				data.put("hjqkList",hjqkList);
			    //��һ�ַ����ı��޿κͳɼ�����
			    HashMap<String, String> bxk = service.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> cjpm = service.getCjPm(model.getXh(),model.getXn());
				data.put("bxk", bxk);
				data.put("cjpm", cjpm);
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("���ز�����")){
						data.put("stzcf", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("�۲��ܷ�")){
						data.put("zczf", zcfMap.get("fs"));
						continue;
					}					
				}
			}
			if("11799".equals(Base.xxdm)){
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						data.put("zyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("����")){
						data.put("dycjfs", zcfMap.get("fs"));
						data.put("dyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("ƽ���ɼ�")){
						data.put("pjcjjdfs", zcfMap.get("fs"));
						data.put("pjcjjdpm", zcfMap.get("bjpm"));
					}
				}
				HashMap<String,String> bjxxMap = servicePjPy.getBjxx(xsjbxx.get("bjdm"));
				data.put("fdyxm", bjxxMap.get("fdyxm"));
				PjjgService pjjgservice_11799 = new PjjgService();
				List<HashMap<String, String>> shrList = pjjgservice_11799.getShrList(xh, model.getXn(), model.getXq(), model.getXmdm());

				for (int i = 0; i < shrList.size(); i++) {
					data.put("shr"+i, shrList.get(i).get("xm"));
					data.put("time"+i, DateUtils.getDayOfCn(shrList.get(i).get("shsj")));
					data.put("shyj"+i, shrList.get(i).get("shyj"));
				}
				
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){ 
					data.put("ywcfgk", "��");
				}else{
					data.put("ywcfgk", "��");
				}
				if(StringUtils.isNull(bjgcjs)){ // ��ѧ�����޲�����γ�
					data.put("bxnywbjgkc", "��");
				}else{
					data.put("bxnywbjgkc", "��");
				}
				
				
				List<HashMap<String, String>> cjList1 = service.getCjsxqList(model.getXn(),model.getXh());
				/**
				 * �ɼ�����д������γ̰���ɼ� ��һѧ��
				 */
				HashMap<String,String> cjMap1 = new HashMap<String, String>();
				for (int i = 0; i < 10; i++) {
					cjMap1.put("kcmc"+i,"");
					cjMap1.put("cj"+i,"");
				}
				for (int i = 0;  i< cjList1.size(); i++) {
					data.put("kcmc"+i,cjList1.get(i).get("kcmc"));
					data.put("cj"+i,cjList1.get(i).get("cj"));
				}
				List<HashMap<String, String>> cjList2 = service.getCjxsqList(model.getXn(),model.getXh());
				/**
				  �ɼ�����д������γ̰���ɼ� �ڶ�ѧ��
				 */
				HashMap<String,String> cjMap2 = new HashMap<String, String>();
				for (int i = 0; i < 10; i++) {
					cjMap2.put("xsqkcmc"+i,"");
					cjMap2.put("xsqcj"+i,"");
				}
				for (int i = 0;  i< cjList2.size(); i++) {
					data.put("xsqkcmc"+i,cjList2.get(i).get("kcmc"));
					data.put("xsqcj"+i,cjList2.get(i).get("cj"));
				}
			}
			//�������ڣ��������ƣ��佱��λ�õ�̫�࣬������ͨ��
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String, String>> pjjgListhjqk =  pjjgService.getHznydxPjpyMap(xh);
			servicePjPy.addBlankList(pjjgListhjqk, 4 - pjjgListhjqk.size());
			data.put("pjjgListhjqk", pjjgListhjqk);
			int size1=(4 - pjjgListhjqk.size())<0?0:(4 - pjjgListhjqk.size());
			data.put("blankListhjqk", getBlankList(size1));
			if(xsjbxx.get("csrq")!=null){
				String[] csArr = xsjbxx.get("csrq").split("\\D");
				if(csArr != null&&csArr.length == 3){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
					data.put("csr1", csArr[2]);
				}else if (csArr != null&&csArr.length ==2){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
				}else if (csArr != null&&csArr.length ==1){
					data.put("csn1", csArr[0]);
				}
				String[] xnArr = model.getXn().split("-");
				if(xnArr.length == 2){
					data.put("qsxn", xnArr[0]);
					data.put("zhxn", xnArr[1]);
				}else if (xnArr.length == 1){
					data.put("qsxn", xnArr[0]);
				}
				//�����������
				if(csArr != null&&csArr.length == 2){
					data.put("csn", csArr[0]);
					data.put("csy", csArr[1]);
				}else if (csArr != null&&csArr.length == 1){
					data.put("csn", csArr[0]);
				}
			}
			data.put("bjrs", shxs_xn_bjrs);
			
				
			
			/**
			 * List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						data.put("zyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("����")){
						data.put("dycjfs", zcfMap.get("fs"));
						data.put("dyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("ƽ���ɼ�")){
						data.put("pjcjjdfs", zcfMap.get("fs"));
						data.put("pjcjjdpm", zcfMap.get("bjpm"));
					}
					
				}
			 */
			//��ʽ������
			cjList=service.formatForDjb(cjList);
			String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
			xsjbxx.put("bjrs", bjrs);
			//ת��Ϊ�������ڸ�ʽ
			xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
			String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
			xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", "")); // 2016.12
			data.put("xmmc", model.getXmmc());// ��Ŀ����
			data.put("currXn", model.getXn());
			data.put("pjjg", pjjg);
			data.put("pjjgAll",pjjgAll);
			data.put("cjList", cjList);
			model.setSqly(HtmlUtil.xmlZy(model.getSqly()));
			data.put("xmxx", model);
			data.put("rs", xsjbxx);
			data.put("sqsj", DateTranCnDate.fomartDateToCn(model.getSqsj(),FomartDateType.day));
			data.put("photo", photo);
			data.put("zcf", zcf);
			data.put("xxmc", Base.xxmc);
			
			HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
			if(xxcjMap == null){
				xxcjMap = new HashMap<String,String>();
			}
			data.put("xxcjMap", xxcjMap);
			String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
			List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
			for (HashMap<String, String> zcfMap : zcfList) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("�۲��ܷ�")){
					data.put("zczf", zcfMap.get("fs"));
					data.put("bjpm", zcfMap.get("bjpm"));
					data.put("njzypm", zcfMap.get("njzypm"));
					data.put("cpzpm", zcfMap.get("cpzpm"));
					break;
				}					
			}
			// ================= ��Ŀ���� begin ==================
			data.put("xmxzmc", "");
			XmwhService xmwhService = new XmwhService();
			List<HashMap<String, String>> xmxzList = xmwhService.getXmxz();
			for (HashMap<String, String> xmxzMap : xmxzList) {
				if (xmxzMap.get("dm").equals(model.getXzdm())){
					data.put("xmxzmc", xmxzMap.get("mc"));
					break;
				}
			}
			//�ӱ���ҵ��ѧ
			if("10080".equals(Base.xxdm)){
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				List<HashMap<String,String>> xmXnBxCjList = zcfService.getCjListByXhXn(xh, model.getXn(), "����");
				data.put("xnpjf", service.getPjf(xmXnBxCjList, 2));// ѧ��ƽ����
				String bjzrs = zcfService.getBjrs(xh);
				data.put("bjzrs",bjzrs);// �༶����
				data.put("cjpm", cjpm);// �ɼ�����
				/*ȡ��ѧ���ۺϳɼ���*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*ȡѧ���ۺϳɼ�����*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				// �ѻ���
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjgAllList =  service.getPjpyInfoList(model.getXh(),model.getXn());
				for (int i = 0; i < pjjgAllList.size(); i++) {
					HashMap<String, String> pj = pjjgAllList.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("��");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
				data.put("xmmc", model.getXmmc());// ��Ŀ����
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
			}
			{
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				List<HashMap<String,String>> xmXnBxCjList = zcfService.getCjListByXhXn(xh, model.getXn(), "����");
				data.put("xnpjf", service.getPjf(xmXnBxCjList, 2));// ѧ��ƽ����
				String bjzrs = zcfService.getBjrs(xh);
				data.put("bjzrs",bjzrs);// �༶����
				data.put("cjpm", cjpm);// �ɼ�����
				/*ȡ��ѧ���ۺϳɼ���*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*ȡѧ���ۺϳɼ�����*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				// �ѻ���
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjgAllList =  service.getPjpyInfoList(model.getXh(),model.getXn());
				for (int i = 0; i < pjjgAllList.size(); i++) {
					HashMap<String, String> pj = pjjgAllList.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("��");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
				data.put("xmmc", model.getXmmc());// ��Ŀ����
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}

				// ��ȡ���ֳɼ�
				List<HashMap<String,String>> hbzcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> hbzcfMap : hbzcfList) {
					String xmmc = hbzcfMap.get("xmmc").trim();
					if(xmmc.equals("ѧ��ѧ�ּ���")){
						data.put("xnxfjd", hbzcfMap.get("fs"));
					}else if(xmmc.equals("ѧ���������Գɼ�")){
						data.put("xntycscj",  hbzcfMap.get("fs"));
					}else if(xmmc.equals("��һ����ѧ��ѧ�ּ�������")){
						data.put("dyktxnxfpx",  hbzcfMap.get("fs"));
					}else if(xmmc.equals("�ڶ�����ѧ��ѧ������")){
						data.put("dektxnxfpx", hbzcfMap.get("fs"));
					}else if(xmmc.equals("ѧ�������Ȩ�ۺ�����")){
						data.put("xnpxjqzhpj",  hbzcfMap.get("fs"));
					}
				}
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				data.put("xmje",model.getXmje());//��Ŀ���
			}
			//���ݹ���
			if("11998".equals(Base.xxdm)){
				// ����Υ�ʹ���
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("ywcfmc", "��");
				}else{
					data.put("ywcfmc", "��");
				}
				//��������
				String bkks = xxcjMap.get("bkcjnum");
				if(bkks == null || "0".equals(bkks)){
					data.put("sfbk", "��");
				}else {
					data.put("sfbk", "��");
				}
				StringBuffer jxj = new StringBuffer();//��ѧ��
				StringBuffer qtjx = new StringBuffer();//��������
				for (HashMap<String, String> pj : pjjgAll) {
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						if(xmmc.contains("��ѧ��")){
							jxj.append(xmmc);
							jxj.append("��");
						}else{
							qtjx.append(xmmc);
							qtjx.append("��");
						}
					}
				}
				if(jxj.length()>0)
				jxj.deleteCharAt(jxj.length() - 1);//ɾ�����һ����
				if(qtjx.length()>0)
				qtjx.deleteCharAt(qtjx.length() - 1);
				String tbrq = model.getSqsj();// ����ʱ��
				data.put("y", tbrq.trim().substring(0, 4));
				data.put("m", tbrq.trim().substring(5, 7));
				data.put("d", tbrq.trim().substring(8, 10));
				data.put("jxj", jxj.toString());
				data.put("qtjx", qtjx.toString());
				List<HashMap<String,String>> zwInfo = dwwhService.getZwxx(xh);//����ְ��
				StringBuffer xrzw = new StringBuffer();
				StringBuffer crzw = new StringBuffer();
				for (HashMap<String, String> map : zwInfo) {
					String lzsj = map.get("lzsj");
					if("1".equals(map.get("zzzt")) && map.get("rzsj").length() >= 10){
						xrzw.append(map.get("zwmc")+" "+map.get("rzsj").substring(0, 10)+"-����"+"��");
					}else{
						if((lzsj != null || !"".equals(lzsj)) && map.get("rzsj").length() >= 10 && map.get("lzsj").length() >= 10){
							crzw.append(map.get("zwmc")+" "+map.get("rzsj").substring(0, 10)+"-"+map.get("lzsj").substring(0,10)+"��");
						}
					}
				}
				if(xrzw.length() > 0) xrzw.deleteCharAt(xrzw.length() - 1);
				if(crzw.length() > 0) crzw.deleteCharAt(crzw.length() - 1);
				data.put("xrzw", xrzw.toString());//����ְ��
				data.put("crzw", crzw.toString());//����ְ��
				
				List<HashMap<String,String>> cjlist = zcfService.getCjListByXh(xh);
				data.put("pjcj", service.getPjf(cjlist, 2));//ƽ���ɼ�
				String cjpm = zcfService.getCjpm("", xh, xsjbxx.get("bjmc"));
				data.put("cjpm", cjpm);
			}
			//�㽭��ְͨҵ����ѧԺ
			if("12036".equals(Base.xxdm)){
				List<HashMap<String,String>> shyjjtzyList = new CommShlcImpl().getShyjListByYwid(model.getId());
				data.put("bjyj", shyjjtzyList.get(0).get("shyj"));
				data.put("xscyj", shyjjtzyList.get(2).get("shyj"));
				//ȡ�ۺϲ�������
				List<HashMap<String,String>> pmfsList = new ZhcpDjService().getZcfsForDjb(model.getXh(), model.getXn(), model.getXq());
				for (int i = 0; i < pmfsList.size(); i++) {
					HashMap<String, String> temp = pmfsList.get(i);
					String xmmc = temp.get("xmmc");
					if("�γ�ѧϰ��ʵ������".equals(xmmc)){
						data.put("kcf", temp.get("fs"));
					}else if("˼��Ʒ����Ϊ���ּ�ʵ������".equals(xmmc)){
						data.put("pdf", temp.get("fs"));
					}else if("�������ּ�ʵ������".equals(xmmc)){
						data.put("tyf", temp.get("fs"));
					}else if("�ۺϲ�����".equals(xmmc)){
						data.put("zf", temp.get("fs"));
					}
				}
				data.put("nd",new PjxmsqService().getHsbyjs(xh));
				
			}
			
			//ͨ��������1-7��
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(model.getId());
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			// ================= ��Ŀ���� end ==================
			//�����ǼǱ��ж��Ƿ��и��Ի���������Ĭ�ϡ�
			String templatePath = template_dir+"//pjdjb_"+Base.xxdm+".xml";
			String templateXmlPath = "pjdjb_"+Base.xxdm+".xml";
			File wordFile = null;
			//����ҽ�ƴ�ѧ���Ի����ű���
			/*if("10598".equals(Base.xxdm)){
				if("�������ء�����ѧ���ɲ���ѧ��".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_yltyxxsgbjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_yltyxxsgbjxj.xml";
				}else if("�˽��ѧ��".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_djbjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_djbjxj.xml";
				}else if("�ٺͽ�����ѧ��".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_rhjyjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_rhjyjxj.xml";
				}else if("����ѧ��".equals(model.getXmmc()) || "����ѧ���ɲ�".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_shxs.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_shxs.xml";
				}
			}*/
			
			/**
			 * ԭ�����������ļ�������ʽΪ ѧ��[����].doc ��ʽ�����з�ʽ����������ͬһ��ѧ����ʱ�������⣬���۵�����������ָ�����ͬһ���ļ���������ΰ������ĳ� ѧ��[����]-��Ŀ����.doc �����Ͳ����ظ�
				1036
			 */
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), xsjbxx.get("bjmc")+""+xsjbxx.get("xh")+"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			}
			
			//�ж��ļ��Ƿ����
			/*try{
				ResourceUtils.getFile(templatePath);
				wordFile = FreeMarkerUtil.getWordFile(data,template_dir,templateXmlPath, FreeMarkerUtil.getFileName(xh+"["+xsjbxx.get("xm")+"]"));
			}catch (Exception e) {
				wordFile = FreeMarkerUtil.getWordFile(data,template_dir,default_template,FreeMarkerUtil.getFileName(xh+"["+xsjbxx.get("xm")+"]"));
			}
			*/
					
			return wordFile;
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: �Ǽ� ��ZIP
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-29 ����11:14:13
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
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i]);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * @����: ��ѯ����֤��ģ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-21 ����10:13:43
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
	public ActionForward cxRyzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		User user = getUser(request);// �û�����
		PjjgService service = new PjjgService();
		Map<String,String> map = service.cxRyzs(csdm, user);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @����: ��������֤��ģ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-21 ����10:13:43
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
	@SystemLog(description="������������-�ҵ�����-�������-��������֤��ģ��-CSDM:{csdm}")
	public ActionForward bcRyzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String csz = request.getParameter("csz");
		// ����ģ��
		csz = csz.replaceAll("\\#\\{", "\\\\#\\{");
		User user = getUser(request);// �û�����
		PjjgService service = new PjjgService();
		boolean result = service.bcRyzs(csdm, csz, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ��ӡ����֤��ģ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-21 ����10:13:43
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
	public ActionForward dyRyzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String value = request.getParameter("value");
		PjjgService service = new PjjgService();
		XsxxService xsxxService = new XsxxService();
		// ��ȡԭʼģ��
		User user = getUser(request);// �û�����
		Map<String,String> map = service.cxRyzs(csdm, user);
		String csz = map.get("csz");
		// ѭ�����ɴ�ӡ����
		StringBuilder cszBuilder = new StringBuilder();
		// ����������
		String cszTemp = "";
		if (StringUtils.isNotNull(value)){
			// ��ǰʱ��
			Calendar now = Calendar.getInstance();
			String year = String.valueOf(now.get(Calendar.YEAR));
			String month = String.valueOf(now.get(Calendar.MONTH) + 1);
			String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
			String[] values = value.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				PjjgModel model = service.getModel(values[i]);
				// ����ѧ��
				String[] xnArr = model.getXn().split("-");
				String ksxn = DateUtils.numToZh(xnArr[0]);
				String jsxn = DateUtils.numToZh(xnArr[1]);
				// ѧ����Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				cszTemp = csz.replaceAll("\\\\#\\{xm\\}", xsjbxx.get("xm"));
				// ================ �滻ģ���ǩ begin =================
				if("10022".equals(Base.xxdm)){
					// ������ҵ��ѧ���Ի�
					cszTemp = cszTemp.replaceAll("����һ\\\\#\\{ksxn\\}", ksxn)
						.replaceAll("����һ\\\\#\\{jsxn\\}", jsxn)
						.replaceAll("\\\\#\\{xxmc\\}", model.getXmmc());
				}
				// ================ �滻ģ���ǩ end =================
				if("10704".equals(Base.xxdm)){
					String ksrq = xnArr[0].substring(xnArr[0].lastIndexOf("/")+3, xnArr[0].lastIndexOf("/")+5);
					String jsrq = xnArr[1].substring(xnArr[1].lastIndexOf("/")+3, xnArr[1].lastIndexOf("/")+5);
					cszTemp = cszTemp.replaceAll("\\\\#\\{ksrq\\}",ksrq)
					.replaceAll("\\\\#\\{jsrq\\}", jsrq)
					.replaceAll("\\\\#\\{xmmc\\}", model.getXmmc());
				}
				cszTemp = cszTemp.replaceAll("\\\\#\\{year\\}", year)
									.replaceAll("\\\\#\\{month\\}", month)
									.replaceAll("\\\\#\\{day\\}", day);
				cszBuilder.append(cszTemp);
				if(i < (n - 1)){
					cszBuilder.append(" LODOP.NewPage(); "); // ��һҳ
				}
			}
		}
		// �����ӡ����
		map.put("csz", cszBuilder.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @����:
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-4 ����01:37:14
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
	private ActionForward configDrSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		ImportConfig config = new ImportConfig();
		config.config("IMPORT_N100102","","XG_PJPY_NEW_PJJGB");
		
		return null;
	}
	
	/**
	 * �յ�list
	 * @param size
	 * @return
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		
		return list;
	}
	
	/**
	 * @����: �����Ƽ�ʦ����ѧ ����֤�顢��ѧ���ӡ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-27 ����09:45:47
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
	public ActionForward getRyzsJxjZip_11318(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		String dytype_11318 = request.getParameter("dytype_11318");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getRyzsJxjFile_11318(values[i], dytype_11318);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	/**
	 * @����: �����Ƽ�ʦ����ѧ ����֤�顢��ѧ���ӡ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-27 ����09:45:47
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
	public ActionForward getRyzsJxjWord_11318(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		String dytype_11318 = request.getParameter("dytype_11318");
		
		File wordFile = getRyzsJxjFile_11318(model.getId(), dytype_11318);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	/**
	 * @����: �����Ƽ�ʦ����ѧ ����֤�顢��ѧ���ӡ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-27 ����09:45:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param dytype_11318
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getRyzsJxjFile_11318(String id, String dytype_11318)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService service = new PjjgService();
		PjjgModel model = service.getModel(id);
		
		if (model != null){
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn(), model.getXq());//��ѯ��Ŀ��¼
				if(xmMap != null){
					model.setXn(xmMap.get("xn"));
					model.setXq(xmMap.get("xq"));
				}
			}
			
			//��ȡѧ������
			List<HashMap<String,String>> xqList = Base.getXqList();
			for (HashMap<String,String> map : xqList){
				if (map.get("xqdm").equals(model.getXq())){
					model.setXqmc(map.get("xqmc"));
					break;
				}
			}
			
			String xh = model.getXh();
			
			//������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			
			String rq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
			data.put("dyrq",rq);
			
			data.put("xm", xsjbxx.get("xm"));
			data.put("xmmc", model.getXmmc());
			data.put("xn",model.getXn());
			data.put("xqmc",model.getXqmc());
			
			String templateXmlPath = "";
			if("ryzs".equals(dytype_11318)){
				templateXmlPath = "ryzs_11318.xml";
			}else{
				templateXmlPath = "jxj_11318.xml";
			}
			
			File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			return wordFile;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:�Ϻ������ѧ֤����ӡ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-15 ����05:20:43
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
	public ActionForward ryzsJxjDy_10264(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		PjjgService service = new PjjgService();
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			
			files = service.getRyzsJxjFile_10264(values);
			
			//������ӡ
			if(files.size()==1){
				FileUtil.outputWord(response, files.get(0));
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}

	//ɽ��������ҽְҵѧԺ���Ի�������άѧ����ܱ�
	@SystemAuth(url = url)
	public ActionForward getSdxm_shjxjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    PjjgModel model = (PjjgModel) form;
		PjjgDao dao = new PjjgDao();
		Map<String,Object> data = new HashMap<String,Object>();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//��ѯ
		List<HashMap<String,String>> resultList = dao.getDclist(model,user);
		String zrs = "";
		float xmze1 = 0;
		for (HashMap<String, String> hashMap : resultList) {
			if(zrs.equals("")&&hashMap.get("total")!= null){
				zrs = hashMap.get("total");
			}
			if(hashMap.get("xmje")!= null){
				xmze1 = xmze1+ Float.parseFloat(hashMap.get("xmje"));
			}
		}
		if(zrs.equals("")){
			zrs = "0";
		}
		String xmze = xmze1+"";
		data.put("xmze",xmze);
		data.put("zrs", zrs);
		data.put("xsxxlist",resultList);
		String xmlb1 = request.getParameter("value");
		String[] xmlb = xmlb1.split(",");
		data.put("xmlb", xmlb[0]);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "sdxmsy_12947_shjxjhzb.xml", "��άѧ��ѧ�����ܱ�");
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	//ɽ��������ҽְҵѧԺ���Ի�����ʡ��־��ѧ����ܱ�
	@SystemAuth(url = url)
	public ActionForward getSdxm_slzjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		User user = getUser(request);
		PjjgService pjjgserice = new PjjgService();
		File excelFile = pjjgserice.getXmGxhDy_12947_slzjhzexcel(values,user);
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	//ɽ�������Ƽ����ӡ
	@SystemAuth(url = url)
	public ActionForward getSdxmTjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
	    PjjgModel model = (PjjgModel) form;
		PjjgDao dao = new PjjgDao();
		Map<String,Object> data = new HashMap<String,Object>();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//��ѯ
		List<HashMap<String,String>> resultList = dao.getDclist(model,user);
		data.put("xsxxlist",resultList);
		String xmmc1 = request.getParameter("value");
		String[] xmmc = xmmc1.split(",");
		data.put("xmmc", xmmc[0]);
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", "sdxmsy_12947_yxxsgbtjb.xml", xmmc[0] );
		FileUtil.outputWord(response, wordFile);
		return null;

   }
	
	/**
	 * 
	 * @����:����ѧ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-20 ����06:19:17
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
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getZjXs(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		String path = "xpj_pjjg.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
		
	}
	/**
	 * @����: ������ҵ��ѧ���Ի����ŵǼǱ��ӡ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-5-11 ����09:19:01
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
	public ActionForward getPjdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgModel myForm = (PjjgModel) form;
		File wordFile = getXjkWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	/**
	 * @����: ������ѧ��ҵ��ѧ���ŵǼǱ�������ӡ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-5-12 ����02:55:51
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
	public ActionForward getPjdjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgModel myForm = (PjjgModel) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			List<String> xhList = new ArrayList<String>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				if(xhList.contains(values[i])){
					continue;
				}else{
					xhList.add(values[i]);
				}
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	/**	
	 * @����: ������ѧ��ҵ��ѧ���ŵǼǱ�������ȡ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-5-12 ����02:56:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getXjkWord (PjjgModel myForm,HttpServletRequest request) throws Exception{
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		PjjgService service = new PjjgService();
		String xn = request.getParameter("xn");
		String xh = myForm.getXh();
		Map<String,Object> data = new HashMap<String,Object>();
		// ����ѧ��������Ϣ
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		data.putAll(xsxxMap);
		//��ô�ӡ����
		String dyrq = xsxxglService.getDqrq(xh);
		data.put("dyrq", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
		// ���ѧ������õĽ���{����ѧ��}
		List<HashMap<String, String>> pjjgList = service.getPjjgList(xh,xn);
		data.put("pjjgList", pjjgList);
		data.put("xn", xn);
		File file = null;
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//pjpy","pydjb_10022.xml",xh+"-"+xsxxMap.get("xm"));
		return file;
	}
	
	/**
	 * @�������������ܱ�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��23�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward getHzbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		File excelFile = service.getHzbPrint(searchModel);
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	/**
	 * @�����������������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��10�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward pjjghzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPjjghzList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		//searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "xpj_pjjg.do?method=pjjghzList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pjjghzList");
	}
	
	public ActionForward getHzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//����ѡ��
		String rows =  request.getParameter("rows");
		rows = "{data:" + URLDecoder.decode(URLDecoder.decode(rows,"UTF-8"),"UTF-8") + "}";
		List rowsList = JsonUtil.jsonToList(rows);
		//��ȡģ�����ݺ�����
		HashMap<String, String> bbMap = null;
		User user = getUser(request);
		PjjgService service = new PjjgService();
		List<HashMap<String, String>> hzmdLists = new ArrayList<HashMap<String, String>>();
		List<HashMap<String,String>> hzmdList = new ArrayList<HashMap<String, String>>();
		PjjgModel model = (PjjgModel) form;
		if(rowsList!=null && rowsList.size()>0){
			for (Object object : rowsList) {
				MorphDynaBean rowBean = (MorphDynaBean) object;
				model.setLxdm((String) rowBean.get("lxdm"));
				model.setXn((String) rowBean.get("xn"));
				model.setXq((String) rowBean.get("xq"));
				model.setXmmc((String) rowBean.get("xmmc"));
				model.setXzdm((String) rowBean.get("xzdm"));
				if(rowBean.get("xmmc")!=null){
					if(bbMap==null){
						bbMap = this.getBbMap(model);
					}
				}
				 hzmdList=service.getPjjghzMdList(model);
				for(HashMap<String, String> map:hzmdList){
					hzmdLists.add(map);
				}
			}	
		}
		if (bbMap == null || bbMap.size() == 0) {//��ѯ���������������Ϣ
			throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
		}
		if(hzmdLists!=null && hzmdLists.size()>0){
			File excelFile = getExcel(hzmdLists,bbMap,user,model);
			if(excelFile!=null){
				FileUtil.outputExcel(response, excelFile);
			}
		}
		return null;
	}
	private File getExcel(List<HashMap<String, String>> hzmdLists,HashMap<String, String> bbMap, User user,PjjgModel model) throws Exception {
		String xn = hzmdLists.get(0).get("xn");
		String xmmc = hzmdLists.get(0).get("xmmc");
		Map<String, Object> data = new HashMap<String, Object>();
		PjjgService service = new PjjgService();
		
		data.put("hzmdList", hzmdLists);//������Ŀ����б�
		data.put("xn", xn);
		data.put("xmmc", xmmc);
		data.put("today", DateUtils.getLyr());
		data.put("xxmc", Base.xxmc);
		String xnFront = xn.substring(0, 4);
		String xnBehind = xn.substring(5, 9);
		data.put("xnFront", xnFront);
		data.put("xnBehind", xnBehind);
		String xmje = "";
		double zje = 0;
		double excleZje = 0;
		//String excleZje = service.getExcleZje(model);
		for (int i = 0; i < hzmdLists.size(); i++) {
			xmje = hzmdLists.get(i).get("xmje"); 
			if(!"".equals(xmje)){
				if(xmje == null){
					xmje = "0";
				}
			}
			zje = Double.parseDouble(xmje);
			excleZje += zje;
		}
		data.put("excleZje", excleZje);
		String templateDirectory = Constants.TEP_DIR + bbMap.get("mblj");
		String templateName = bbMap.get("mbmc");
		String fileName = FreeMarkerUtil.getFileName(xmmc);
		if(StringUtils.equals("getPjjghzMdListByXy",model.getType())){
			String xymc = service.getXymcBydm(model.getXydm());
			fileName = FreeMarkerUtil.getFileName(xmmc+"_"+xymc);
		}
		File wordFile = FreeMarkerUtil.getExcelFile(data, templateDirectory, templateName, fileName);
		return wordFile;
	}
	public HashMap<String, String> getBbMap(PjjgModel model) throws Exception{
		HashMap<String, String> bbMap = null;// ����
		
		if (!StringUtil.isNull(model.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(),model.getXn(),model.getXq());//��ѯ��Ŀ��¼
			if (xmMap != null) {
				BbwhService bbwhService = new BbwhService();
				bbMap = bbwhService.getDataById(xmMap.get("dysbbb"));//��ȡ��Ӧ�ϱ�����
			}
		}
		if (bbMap == null || bbMap.size() == 0) {//��ѯ���������������Ϣ
			throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
		}
		return bbMap;
	}

	public ActionForward getpjjghzMd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPjjghzMdList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xqmc", request.getParameter("xqmc").equals("null")?null:request.getParameter("xqmc"));
		String path = "xpj_pjjg.do?method=pjjghzMdList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pjjghzMdList");
	}
	
	
	
	/**
	 * @����: ����֤�顢��ѧ���ӡ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-27 ����09:45:47
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
	public ActionForward getRyzsJxjZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		String dytype = request.getParameter("dytype");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getRyzsJxjFile(values[i], dytype);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	/**
	 * @����: ����֤�顢��ѧ���ӡ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-27 ����09:45:47
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
	public ActionForward getRyzsJxjWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		String dytype = request.getParameter("dytype");
		
		File wordFile = getRyzsJxjFile(model.getId(), dytype);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	/**
	 * @����: ����֤�顢��ѧ���ӡ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-27 ����09:45:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param dytype
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getRyzsJxjFile(String id, String dytype)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService service = new PjjgService();
		PjjgModel model = service.getModel(id);
		
		if (model != null){
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn(), model.getXq());//��ѯ��Ŀ��¼
				if(xmMap != null){
					model.setXn(xmMap.get("xn"));
					model.setXq(xmMap.get("xq"));
				}
			}
			
			//��ȡѧ������
			List<HashMap<String,String>> xqList = Base.getXqList();
			for (HashMap<String,String> map : xqList){
				if (map.get("xqdm").equals(model.getXq())){
					model.setXqmc(map.get("xqmc"));
					break;
				}
			}
			
			String xh = model.getXh();
			
			//������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			
			String rq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
			String ny = DateTranCnDate.dateToCnDateSubMonth(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			data.put("dyrq",rq);
			data.put("dyny",ny);
			
			data.put("xm", xsjbxx.get("xm"));
			data.put("xmmc", model.getXmmc());
			data.put("xn",model.getXn());
			data.put("xqmc",model.getXqmc());
			data.put("xh", model.getXh());
			
			String templateXmlPath = "";
			if("ryzs".equals(dytype)){
				templateXmlPath = "ryzs_" + Base.xxdm + ".xml";
			}else{
				templateXmlPath = "jxj_" + Base.xxdm + ".xml";
			}
			
			File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			return wordFile;
		}
		
		return null;
	}
	
	/**
	 * @description	�� ������������
	 * @author 		�� ������1282��
	 * @date 		��2017-12-27 ����02:45:25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bzmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();	
		File file = service.createBzmdWord(model);
		FileUtil.outputWord(response, file);
		return null;		
	}

	/**
	 * @description	�� ��֤�Ƿ��б��������ܹ�����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-4 ����02:42:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yzbzmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService pjjgService = new PjjgService();
		boolean result = pjjgService.isExportBzMd(model);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		JSONObject fromBean = JSONObject.fromBean(map);
		response.getWriter().print(fromBean);
		return null;
	}
	
	/**
	 * 
	 * @����: ��ѧ���˺Ż��ܱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-24 ����11:35:26
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
	public ActionForward jxjzhhzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		User user = getUser(request);
		File file = new PjjgService().getJxjhzzhList(model, user);
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("��ѧ���˺Ż��ܱ�.xls","utf-8")); 
		FileUtil.outputFile(response, file);
		return null;
	}
	
	/**
	 * @����:��������ְҵ����ѧԺ���Ի�����ѧ�𷢷Ų����ñ�
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018��3��9�� ����2:30:46
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
	public ActionForward cwybPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getCwybList(model, user);//��ѯ�����м�¼������ҳ
		StringBuilder xmmc = new StringBuilder();
		HashMap<String,String> hashMap = new HashMap<String, String>();
		if(searchModel.getSearch_tj_xmmc() != null){
			for (String s : searchModel.getSearch_tj_xmmc()) {
				xmmc.append(s+"��");
			}
			xmmc.deleteCharAt(xmmc.length()-1);
		}
		hashMap.put("xmmc", xmmc.toString());
		hashMap.put("hjje", service.getCwybSum(model, user));
		String xn = searchModel.getSearch_tj_xn()==null ? "" : searchModel.getSearch_tj_xn()[0];
		hashMap.put("xn",xn);
		if(xn != null && xmmc.toString() != null){
			File file = service.getCwybFile(resultList,hashMap);//���ɵ����ļ�
			FileUtil.outputExcel(response, file);
		}
		return null;
	}

	/**
	 * @����:����ѧԺ��ӡ���ܱ��㽭��ְͨҵ����ѧԺ��
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/8/27 14:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward getHzbXy(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		model.setType("getPjjghzMdListByXy");
		//����ѡ��
		String rows =  request.getParameter("rows");
		String[] xydms =  request.getParameter("xydms").split(",");
		rows = "{data:" + URLDecoder.decode(URLDecoder.decode(rows,"UTF-8"),"UTF-8") + "}";
		List rowsList = JsonUtil.jsonToList(rows);
		//��ȡģ�����ݺ�����
		HashMap<String, String> bbMap = null;
		User user = getUser(request);
		PjjgService service = new PjjgService();
		List<File> files = new ArrayList<File>();

		if(rowsList!=null && rowsList.size()>0){
			for (Object object : rowsList) {
				MorphDynaBean rowBean = (MorphDynaBean) object;
				model.setLxdm((String) rowBean.get("lxdm"));
				model.setXn((String) rowBean.get("xn"));
				model.setXq((String) rowBean.get("xq"));
				model.setXmmc((String) rowBean.get("xmmc"));
				model.setXzdm((String) rowBean.get("xzdm"));
				if(rowBean.get("xmmc")!=null){
					if(bbMap==null){
						bbMap = this.getBbMap(model);
					}
				}

			}
		}
		if (bbMap == null || bbMap.size() == 0) {//��ѯ���������������Ϣ
			throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
		}
		for(String xydm : xydms){
			model.setXydm(xydm);
			List<HashMap<String, String>> hzmdList = service.getPjjghzMdList(model);
			if(hzmdList!=null && hzmdList.size()>0){
				File excelFile = getExcel(hzmdList,bbMap,user,model);
				if(excelFile!=null){
					files.add(excelFile);
				}
			}else{
				//��ȡ�յ�Excel
				String xymc = service.getXymcBydm(xydm);
				String fileName = FreeMarkerUtil.getFileName(model.getXmmc()+"_"+xymc);
				File file = new File(System.getProperty("java.io.tmpdir"),fileName+".xls");
				if(!file.exists()){
					file.createNewFile();
				}
				WritableWorkbook wwb = Workbook.createWorkbook(file);
				WritableSheet sheetNull = wwb.createSheet("���ε�������Ϊ��", 0);
				sheetNull.setColumnView(0, 15);
				Label msg = new Label(0, 0,"�������ݣ�");
				sheetNull.addCell(msg);
				wwb.write();
				wwb.close();
				files.add(file);
			}
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		FileUtil.outputZip(response, zipFile);
		return null;
	}
}
