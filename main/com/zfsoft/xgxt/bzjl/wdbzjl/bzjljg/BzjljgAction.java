/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:34:40 
 */  
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg;

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
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjlxssq.BzjlxssqService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.bbwh.BbwhService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmModel;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����-�������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����10:34:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BzjljgAction extends SuperAction {

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
	
	private static final String url = "bzjl_jg.do";

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
	@SystemAuth(url = url)
	public ActionForward viewBzjljgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();

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
		String path = "pj_pjjg.do";
		request.setAttribute("path", path);

		request.setAttribute("cssz", csszModel);
		request.setAttribute("ryzsFlag", Arrays.asList(RYZS_XXDMS).contains(Base.xxdm));
		request.setAttribute("userType", getUser(request).getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewBzjljgList");
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
	public ActionForward addBzjljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();
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
		List<HashMap<String, String>> xmlx = xmwhservice.getXmlx("2");
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
		model.setXn(CsszService.getPjzq().get("xn"));
		// 1:ѧ������
		if(CsszService.PJFS_XQ.equals(csszService.getCsz("pjzq"))){
			model.setXq(CsszService.getPjzq().get("xq"));
		}

		String path = "bzjl_bzjg.do?method=addBzjljg";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("addBzjljg");
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
	public ActionForward updateBzjljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();
		User user = getUser(request);

		BzjljgModel updateForm = service.getModel(model);
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
		List<HashMap<String, String>> xmlx = xmwhservice.getXmlx("2");
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

		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));

		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",  bdpzService.getJbxxpz(JXSQ));
		request.setAttribute("xzdm",model.getXzdm());
		return mapping.findForward("updateBzjljg");

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
	public ActionForward delBzjljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BzjljgService service = new BzjljgService();

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
	public ActionForward bzjljgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();
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

			return mapping.findForward("bzjljgView");
		} else {
			return updateBzjljg(mapping, form, request, response);
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

		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();

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


		BzjljgModel model = (BzjljgModel) form;

		File wordFile = getWord(model.getId());
		FileUtil.outputWord(response, wordFile);

		return null;
	}


	//���ģ����������word�ļ�
	private File getWord(String id)
			throws Exception {
		BzjljgService servicePjPy = new BzjljgService();
		Map<String,Object> data = new HashMap<String,Object>();
		BzjljgService service = new BzjljgService();
		BzjljgModel model = service.getModel(id);
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
			BzjlxssqService pjxmsqService = new BzjlxssqService();
			//������
			List<HashMap<String, String>> shyjList = pjxmsqService.getAllShyjList(xh, model.getXn(), model.getXq(), model.getXmdm());
			//����ҽ��ר
			if("13779".equals(Base.xxdm)) {
				XsxxglService xsxxglService = new XsxxglService();
				BzjljgService pjjgService = new BzjljgService();
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
				BzjljgService pjjgService = new BzjljgService();
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

			List<HashMap<String, String>> pjjg =  service.getPjpyInfoMapForDjb(xh);
			List<HashMap<String, String>> pjjgAll =  service.getPjpyInfoList(xh);

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

			CpmdService cpmdService = new CpmdService();
			String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);

			//�������ڣ��������ƣ��佱��λ�õ�̫�࣬������ͨ��
			BzjljgService pjjgService = new BzjljgService();
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
		BzjljgService service = new BzjljgService();
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
		BzjljgService service = new BzjljgService();
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
		BzjljgService service = new BzjljgService();
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
				BzjljgModel model = service.getModel(values[i]);
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

		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();

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
		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();
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
	public ActionForward bzjljghzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();
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
		String path = "bzjl_bzjg.do?method=bzjljghzList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bzjljghzList");
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
		BzjljgService service = new BzjljgService();
		List<HashMap<String, String>> hzmdLists = new ArrayList<HashMap<String, String>>();
		List<HashMap<String,String>> hzmdList = new ArrayList<HashMap<String, String>>();
		BzjljgModel model = (BzjljgModel) form;
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
	private File getExcel(List<HashMap<String, String>> hzmdLists,HashMap<String, String> bbMap, User user,BzjljgModel model) throws Exception {
		String xn = hzmdLists.get(0).get("xn");
		String xmmc = hzmdLists.get(0).get("xmmc");
		Map<String, Object> data = new HashMap<String, Object>();
		BzjljgService service = new BzjljgService();

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
	public HashMap<String, String> getBbMap(BzjljgModel model) throws Exception{
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
		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();
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
		String path = "bzjl_bzjg.do?method=bzjljghzMdList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bzjljghzMdList");
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

		BzjljgModel model = (BzjljgModel) form;
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
		BzjljgService service = new BzjljgService();
		BzjljgModel model = service.getModel(id);

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
		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();
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
		BzjljgModel model = (BzjljgModel) form;
		BzjljgService pjjgService = new BzjljgService();
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
		BzjljgModel model = (BzjljgModel) form;
		User user = getUser(request);
		File file = new BzjljgService().getJxjhzzhList(model, user);
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
		BzjljgModel model = (BzjljgModel) form;
		BzjljgService service = new BzjljgService();
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
		BzjljgModel model = (BzjljgModel) form;
		model.setType("getPjjghzMdListByXy");
		//����ѡ��
		String rows =  request.getParameter("rows");
		String[] xydms =  request.getParameter("xydms").split(",");
		rows = "{data:" + URLDecoder.decode(URLDecoder.decode(rows,"UTF-8"),"UTF-8") + "}";
		List rowsList = JsonUtil.jsonToList(rows);
		//��ȡģ�����ݺ�����
		HashMap<String, String> bbMap = null;
		User user = getUser(request);
		BzjljgService service = new BzjljgService();
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
