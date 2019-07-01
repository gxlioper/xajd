/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����02:40:52 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.xmwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszModel;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tzsz.TzszService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����02:40:52
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmwhAction extends SuperAction {

	private String messageKey;
	private static final String urlJxj = "xpj_xmwh.do?method=xmwhCx&xmxz=1&sindex=0";
	private static final String urlBz = "xpj_xmwh.do?method=xmwhCx&xmxz=2&sindex=1";
	
	/**
	 * 
	 * @����:������ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhModel myForm = (XmwhModel) form;
		XmwhService service = new XmwhService();
		User user = getUser(request);
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);

		if (QUERY.equals(myForm.getType())){
			xmxz = request.getParameter("xmxz");
			myForm.setXmxz(xmxz);
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("currXnXqmc", CsszService.getPjzq().get("pjzqmc")); // ��ǰѧ��ѧ��

		String dateFormat = "yyyy-MM-dd HH:mm";
		request.setAttribute("currDate", service.getCurrTime(dateFormat));
		String path=null;
		if("2".equals(xmxz))
		{
			request.setAttribute("czpath",urlBz);
		}else{
			request.setAttribute("czpath",urlJxj);
		}

		if(!StringUtils.isNull(myForm.getCzfs())&&myForm.getCzfs().equals("xyrssz")){
			path = "xpj_xmwh.do?method=xmwhCx&czfs=xyrssz";
			RsszService rsszService = new RsszService();
			//���ѧԺ�������ý�ѧ������ʾ
			List<HashMap<String,String>> jxjList = rsszService.getJxjze(new RsszModel(), user);
			request.setAttribute("jxjjeMap", jxjList.get(0));
		}
		
		List<HashMap<String, String>> xmlxList = service.getnewXmlx(xmxz);// ��Ŀ����
//		List<HashMap<String, String>> xmxzList = service.getXmxz();// ��Ŀ����
		request.setAttribute("xmlxList", xmlxList);
//		request.setAttribute("xmxzList", xmxzList);
		request.setAttribute("path", path);
		request.setAttribute("czfs", myForm.getCzfs());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhCx");
	}

	/**
	 * 
	 * @����:���ӡ��޸ķ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth( url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-���ӻ��޸�-XMDM��{xmdm},XMMC:{xmmc},LXDM:{lxdm},XZDM:{xzdm},XSXH:{xsxh},SHLC:{shlc},RSKZJB:{rskzjb},RSFPFS:{rsfpfs}")
	public ActionForward xmwhZjXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhModel myForm = (XmwhModel) form;
		XmwhService service = new XmwhService();
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			if(service.isExistXssx(myForm)) {// ��Ŀ����ظ���֤
				messageKey = MessageKey.PJPY_XSXH_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			if (service.isRepeat(myForm)) {// �����ظ���֤
				messageKey = MessageKey.PJPY_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			
			myForm.setXn(CsszService.getPjzq().get("xn"));
			myForm.setXq(CsszService.getPjzq().get("xq"));
			myForm.setXzdm(xmxz);
			boolean result = service.runInsert(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			if (service.isRepeat(myForm)) {// �����ظ���֤
				messageKey = MessageKey.PJPY_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			
			if(service.isExistXssx(myForm)) {// ��Ŀ����ظ���֤
				messageKey = MessageKey.PJPY_XSXH_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			
			if (service.isRalate(myForm)) {// ������
				messageKey = MessageKey.PJPY_XM_NOTUPDATE;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			String rsfpfsOld = request.getParameter("rsfpfsOld");
			RsszService rsszService = new RsszService();
			String xmdm = myForm.getXmdm();
			if (rsfpfsOld != null && !rsfpfsOld.equals(myForm.getRsfpfs())) {// �������Ʒ�Χ�޸�
				rsszService.runDeleteByXmdm(xmdm);
			}
			
			String shlcOld = request.getParameter("shlcOld");
			TzszService tzszService = new TzszService();
			if(shlcOld !=null && !shlcOld.equals(myForm.getShlc())){
				tzszService.runDeleteByXmdm(xmdm);
			}

			boolean result = service.runUpdate(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		if(myForm.getXmdm() != null && !myForm.getXmdm().equals("")){
			// �Ƿ���ѧ��������Ŀ
			SqshService sqshService = new SqshService();
			boolean flag = sqshService.isExistsFlowData(myForm.getXmdm());
			request.setAttribute("spzt", flag);
		}
				
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("pjpy");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);

		List<HashMap<String, String>> xmlxList = service.getnewXmlx(xmxz);// ��Ŀ����
//		List<HashMap<String, String>> xmxzList = service.getXmxz();// ��Ŀ����
		request.setAttribute("xmlxList", xmlxList);
//		request.setAttribute("xmxzList", xmxzList);
		String path= null;
		if("2".equals(xmxz))
		{
			path= urlBz;
		}else{
			path= urlJxj;
		}

		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		XmwhModel model = service.getModel(myForm);
		
		if(Base.xxdm.equals("10704") && null != model){//�����Ƽ���ѧ�����Ƿ�����ѧ���ɲ�
			String sfyxgb = service.getSfyxgb(model.getXmdm());
			model.setSfyxgb(sfyxgb);
		}
		//�������
		request.setAttribute("pyccList",service.getPyccList());

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}

		//�Զ��������
		if(StringUtils.isNull(myForm.getXsxh())){
			String xsxh = service.getXsxh(xmxz);
			myForm.setXsxh(xsxh);
		}
		return mapping.findForward("xmwhZjXg");

	}

	/**
	 * 
	 * @����:����������̣���ȡ��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @throws
	 */
	public ActionForward xmwhShfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (!StringUtil.isNull(value)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(value);// spgwid Ϊѡ����������̴���ֵ
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		return null;

	}

	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-ɾ��-VALUES��{values}")
	public ActionForward xmwhSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhService service = new XmwhService();

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// //�жϺϷ���////
			if (service.isRalate(values)) {// ������
				messageKey = MessageKey.PJPY_XM_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			if (result) {
				service.delRalate(values);// ɾ�����еĹ�����
			}
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;

	}

	/**
	 * 
	 * @����:ʱ�俪��
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-8 ����11:43:20
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-���뿪��-XMDM��{xmdm}")
	public ActionForward xmwhSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhModel myForm = (XmwhModel) form;
		XmwhService service = new XmwhService();
		String xmdm = request.getParameter("xmdm");
		String xmmc = service.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		List<HashMap<String, String>> isnotList = new OptionUtil()
				.getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("kgList", isnotList);

		List<HashMap<String, String>> onoffList = new OptionUtil()
				.getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		String path = null;
		if("2".equals(xmxz))
		{
			path= urlBz;
		}else{
			path= urlJxj;
		}

		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		XmwhModel model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		return mapping.findForward("xmwhSjkg");
	}

	/**
	 * 
	 * @����:�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-14 ����10:55:14
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-�����-JXFZND��{jxfznd}")
	public ActionForward xmwhJxfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhModel myForm = (XmwhModel) form;
		XmwhService service = new XmwhService();
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> jxfzList = service.getJxfz();// ������б�
			JSONArray dataList = JSONArray.fromObject(jxfzList);
			response.getWriter().print(dataList);
			return null;
		} else if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String jxfznd = request.getParameter("jxfznd");
			boolean result = service.runJxfz(jxfznd);
			String messageKey = result ? MessageKey.PJPY_JXFZ_SUCCESS
					: MessageKey.PJPY_JXFZ_NOTJL;
			response.getWriter().print(getJsonResult(messageKey, result));
			return null;
		}

		CsszService csszService = new CsszService();
		String currXnXqmc = csszService.getModel().getZqmc();// ��ǰѧ��ѧ��
		request.setAttribute("currXnXqmc", currXnXqmc);
		String path = null;
		if("2".equals(xmxz))
		{
			path= urlBz;
		}else{
			path= urlJxj;
		}

		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmwhJxfz");
	}
	
	
	/**
	 * 
	 * @����:������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-14 ����11:01:57
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
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmwhModel model = (XmwhModel) form;
		XmwhService service = new XmwhService();
		User user = getUser(request);
		String xmxz = request.getParameter("xmxz");
		model.setXzdm(xmxz);
		model.setXmxz(xmxz);
		
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
	

}
