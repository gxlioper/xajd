package com.zfsoft.xgxt.gygl.ssyd.ydjg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import xgxt.utils.date.MoneyFormat;
import xsgzgl.gygl.cwgl.CwglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcForm;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcService;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-�����춯
 * @�๦������:�����춯���
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-27
 * @�汾�� V1.0
 */
public class YdjgAction extends SuperAction {
	
	private static final String url = "ydjgbase.do";
	
	private static final String zslsurl = "lsxxgl_lsxxgl_zslsxxgl.do";
	/**
	 * ���幫Ԣ���������춯���Դӻ�����Ϣ���л�ȡѧ����Ϣ
	 */
	private static final String GYGLSSYD = "gyglssyd";
	
	private YdjgService service = new YdjgService();

	private static List<HashMap<String, String>> jbxxList = null;

	private BdpzService bdpzService = new BdpzService();
	/**
	 * @����:�����춯����б�
	 * @���ߣ� qilm
	 * @ʱ�䣺 2013-9-27
	 * @�汾�� V1.0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdjgForm myForm = (YdjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("ydjgbase.do");
			myForm.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "ydjgbase.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @����: �鿴ѧ���춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����09:58:06
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
	public ActionForward ckXsydInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdjgForm myForm = (YdjgForm) form;
		
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		request.setAttribute("jbxxList", jbxxList);
	
		// ѧ�������һ�������춯��Ϣ
		HashMap<String, String> xsydInfo = service.getXsydInfo(myForm);
		request.setAttribute("xsydInfo", StringUtils.formatData(xsydInfo));

		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(xsydInfo.get("ssydlx"));
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}
		
		// ѧ������ĸ��������춯��Ϣ
		List<HashMap<String, String>> xsYdList = service.getXsYdList(
				myForm);
		request.setAttribute("xsYdList", xsYdList);
		
		return mapping.findForward("ckXsydInfo");
	}
	

	/**
	 * 
	 * @����: �鿴��λ�춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����09:58:06
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
	public ActionForward ckQsydInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdsqService sqservice = new YdsqService();

		YdjgForm myForm = (YdjgForm) form;

		// ��λ��Ϣ
		request.setAttribute("cwxxData", StringUtils.formatData(sqservice.getCwxx(myForm.getYdqlddm(),
				myForm.getYdqqsh(),myForm.getYdqcwh())));
		
		//��ǰѧ��ѧ��
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);

		// ��ǰѧ��ѧ�ڴ�λ�춯��Ϣ
		List<HashMap<String, String>> currQsYdList = service.getQsYdList(
				myForm,YdjgService.CURR_XNXQ_FLG_Y);
		
		// �ǵ�ǰѧ��ѧ�ڴ�λ�춯��Ϣ
		List<HashMap<String, String>> notCurrQsYdList = service.getQsYdList(
				myForm,YdjgService.CURR_XNXQ_FLG_N);
		
		request.setAttribute("currQsYdList", currQsYdList);
		
		request.setAttribute("notCurrQsYdList", notCurrQsYdList);
		
		return mapping.findForward("ckQsydInfo");
	}

	/**
	 * 
	 * @����:�鿴�����춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 * 
	 */
	@SystemAuth(url = url)
	public ActionForward ydjgck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdjgForm myForm = (YdjgForm) form;

		HashMap<String, String> ydjg = service.getYdjg(myForm);

		if (!StringUtil.isNull(ydjg.get("xh"))) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(ydjg.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(ydjg.get("ssydlx"));
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}
		
		// ѧ��������Ϣ
		String path = "ydjg.do?method=ydjgck";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		request.setAttribute("data", StringUtils.formatData(ydjg));
		
		return mapping.findForward("ydjgck");
	}
	/**
	 * 
	 * @����:���Ӯ��ӽY��
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 * 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʹ�Ԣ����-�����춯-�����춯���-����XH:{xh},SSYDLX:{ssydlx},XN:{xn},XQ:{xq},TSTZYY:{tstzyy},TSTZSJ:{tstzsj},SFCWCSH:{sfcwcsh}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdjgForm myForm = (YdjgForm) form;

		YdsqService ydsqService = new YdsqService();
		
		User user = getUser(request);
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc();
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}
		
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			myForm.setTjsqrxm(user.getUserName());
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		// ѧ��������Ϣ
		String path = "ydjg.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		// ����ԭ��
		request.setAttribute("tstzyyList", ydsqService.getTstzyy());
		//����ԭ��
		request.setAttribute("tzyyList", ydsqService.getTzyy());
		//��סԭ��
		CwglService cwglService = new CwglService();
		request.setAttribute("rzyyList", cwglService.getRzyyList());
		request.setAttribute("dqxq",Base.getDqxqmc());
		request.setAttribute("dqxn",Base.currXn);
		// ��λ��Ϣ
		request.setAttribute("cwxxData", StringUtils.formatData(ydsqService.getCwxxForXh(myForm.getXh())));
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyy-MM-dd"));
		return mapping.findForward("ydjgzj");
	}

	
	/**
	 * 
	 * @����: �����춯��˵���
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdjgForm model = (YdjgForm) form;
		YdjgService service = new YdjgService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// ��ѯ�����м�¼������ҳ

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
	 * ���ر��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printTstzd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdjgService service = new YdjgService();
		String ssydid = request.getParameter("ssydid");
		
		String rq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
		if(ssydid != null && ssydid.split(",").length == 1){	/*-->���ص������*/
			HashMap<String , String> data = service.getYdxx(ssydid);
			HashMap<String , Object> objectData = new HashMap<String, Object>();
			data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
			data.put("dyrq",rq);
			data.put("zsfjedx", MoneyFormat.format(data.get("zsfje")));
			
			objectData.putAll(data);
			File file = null;
			String xxdm = Base.xxdm;
			String guid = "gygl_tstzd_"+xxdm;
			file = BbdmUtils.getSigleFile(guid, objectData);
			if(null==file){
				//ͨ�õǼǱ�
				file = BbdmUtils.getSigleFile("gygl_tstzd", objectData);
			}
			FileUtil.outputWord(response, file);
		}else{
			List<File> files = new ArrayList<File>();
			for(String ssydids:ssydid.split(",")){
				HashMap<String , String> data = service.getYdxx(ssydids);
				HashMap<String , Object> objectData = new HashMap<String, Object>();
				data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
				data.put("dyrq",rq);
				data.put("zsfjedx", MoneyFormat.format(data.get("zsfje")));
				objectData.putAll(data);
				File file = null;
				String xxdm = Base.xxdm;
				String guid = "gygl_tstzd_"+xxdm;
				file = BbdmUtils.getSigleFile(guid, objectData);
				if(null==file){
					//ͨ�õǼǱ�
					file = BbdmUtils.getSigleFile("gygl_tstzd", objectData);
				}
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	//���޵���ӡ
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printTsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdjgService service = new YdjgService();
		String ssydid = request.getParameter("ssydid");
		String rq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
		if(ssydid != null && ssydid.split(",").length == 1){	/*-->���ص������*/
			HashMap<String , String> data = service.getYdxx(ssydid);
			HashMap<String , Object> objectData = new HashMap<String, Object>();
			data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
			data.put("dyrq",rq);
			data.put("rzsj", DateTranCnDate.fomartDateToCn(data.get("ydqqsrzsj")));
			data.put("zsfjedx", MoneyFormat.format(data.get("zsfje")));
			objectData.putAll(data);
			File file = null;
			String xxdm = Base.xxdm;
			String guid = "gygl_tsd_"+xxdm;
			file = BbdmUtils.getSigleFile(guid, objectData);
			if(null==file){
				//ͨ�õǼǱ�
				file = BbdmUtils.getSigleFile("gygl_tsd", objectData);
			}
			FileUtil.outputWord(response, file);
		}else{
			List<File> files = new ArrayList<File>();
			for(String ssydids:ssydid.split(",")){
				HashMap<String , String> data = service.getYdxx(ssydids);
				HashMap<String , Object> objectData = new HashMap<String, Object>();
				data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
				data.put("dyrq",rq);
				data.put("rzsj", DateTranCnDate.fomartDateToCn(data.get("ydqqsrzsj")));
				data.put("zsfjedx", MoneyFormat.format(data.get("zsfje")));
				objectData.putAll(data);
				File file = null;
				String xxdm = Base.xxdm;
				String guid = "gygl_tsd_"+xxdm;
				file = BbdmUtils.getSigleFile(guid, objectData);
				if(null==file){
					//ͨ�õǼǱ�
					file = BbdmUtils.getSigleFile("gygl_tsd", objectData);
				}
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @description	�� ס����ʷ�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-16 ����05:09:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = zslsurl)
	public ActionForward zslslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdjgForm myForm = (YdjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("lsxxgl_lsxxgl_zslsxxgl.do");
			myForm.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "lsxxgl_lsxxgl_zslsxxgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zslslist");
	}
	
	/**
	 * @description	��ס����Ϣ�鿴
	 * @author 		�� ������1282��
	 * @date 		��2017-11-16 ����05:09:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = zslsurl)
	public ActionForward zsxxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdjgForm myForm = (YdjgForm) form;

		HashMap<String, String> ydjg = service.getYdjg(myForm);

		if (!StringUtil.isNull(ydjg.get("xh"))) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(ydjg.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(ydjg.get("ssydlx"));
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}
		
		// ѧ��������Ϣ
		String path = "ydjg.do?method=ydjgck";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		request.setAttribute("data", StringUtils.formatData(ydjg));
		
		return mapping.findForward("zsxxck");
	}
	
	/**
	 * @description	�� �鿴ѧ��ס����ʷ��Ϣ
	 * @author 		�� ������1282��
	 * @date 		��2017-11-16 ����05:30:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = zslsurl)
	public ActionForward ckXsydInfoLsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdjgForm myForm = (YdjgForm) form;
		
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		request.setAttribute("jbxxList", jbxxList);
	
		// ѧ�������һ�������춯��Ϣ
		HashMap<String, String> xsydInfo = service.getXsydInfo(myForm);
		request.setAttribute("xsydInfo", StringUtils.formatData(xsydInfo));
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(xsydInfo.get("ssydlx"));
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}

		// ѧ������ĸ��������춯��Ϣ
		List<HashMap<String, String>> xsYdList = service.getXsYdList(
				myForm);
		request.setAttribute("xsYdList", xsYdList);
		
		return mapping.findForward("ckXsydInfoLsxx");
	}
	
}
