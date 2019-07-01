/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-2 ����06:13:18 
 */
package com.zfsoft.xgxt.axcs.wpsz;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-2 ����06:13:18
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WpszAction extends SuperAction {
	DAO dao = DAO.getInstance();
	private String messageKey;

	private static final String url = "axcs_axcswpsz.do";
	
	/**
	 * 
	 * @����:��Ʒ�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����06:52:30
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
	public ActionForward wpszList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		List<HashMap<String, String>> xnList = dao.getXnndList();
		request.setAttribute("xnList", xnList);
		model.setXn(Base.currXn);
		request.setAttribute("model", model);
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		request.setAttribute("path", "axcs_axcswpsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wpszList");

	}

	/**
	 * 
	 * @����:������Ʒ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-3 ����03:22:13
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
	public ActionForward addWp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		if (SAVE.equals(model.getType())) {
			boolean result = service.addWp(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		// ������Ʒ��Ŀ���룬�ϴ�ͼƬʱʹ��
		String xmdm = StringUtils.getGuid();
		request.setAttribute("xmdm", xmdm);
		List<HashMap<String, String>> wplbList = service.getWplbList();
		request.setAttribute("wplbList", wplbList);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("path", "axcs_axcswpsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("addWp");
	}

	/**
	 * 
	 * @����:������Ʒ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-4 ����10:18:21
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
	public ActionForward updateWp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		WpszForm model = service.getModel(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String, String>> wplbList = service.getWplbList();
		request.setAttribute("wplbList", wplbList);
		request.setAttribute("xmdm", model.getXmdm());
		request.setAttribute("xn", model.getXn());
		BeanUtils.copyProperties(myForm, StringUtils.formatData(StringUtils.formatData(model)));
		return mapping.findForward("updateWp");
	}

	/**
	 * 
	 * @����:ɾ����Ʒ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-4 ����04:30:15
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
	public ActionForward delWp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszService service = new WpszService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {

			if (service.isHaveSqJl(values)) {// ������
				messageKey = MessageKey.AXCS_AXWP_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}

	/**
	 * 
	 * @����:�鿴��Ʒ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-4 ����11:21:42
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
	public ActionForward ckWp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		HashMap<String,String> rs = service.getWpxxByXmdm(myForm.getXmdm());
		request.setAttribute("xmdm", myForm.getXmdm());
		request.setAttribute("rs", StringUtils.formatData(rs));
		return mapping.findForward("ckWp");

	}

	/**
	 * 
	 * @����:��Ʒ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-3 ����03:10:36
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
	public ActionForward wpJbsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.bcWpJbsz(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		WpszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("axjz");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "axcs_axcswpsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wpJbsz");
	}

	/**
	 * 
	 * @����:��Ʒ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-4 ����04:38:41
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
	public ActionForward wpTjsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.bcWpTjsz(myForm,request);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		WpszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		// �����������б�
		KnsdcService knsdcSerivce = new KnsdcService();
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		request.setAttribute("tjpzList", service.getTjpzList());
		request.setAttribute("path", "axcs_axcswpsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wpTjsz");
	}

	/**
	 * 
	 * @����:��Ʒ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-4 ����05:24:12
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
	public ActionForward wpFz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		
		 if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.wpFz(myForm.getXn());
			String messageKey = result ? MessageKey.AXCS_WPFZ_SUCCESS : MessageKey.AXCS_WPFZ_NOTJL;
			response.getWriter().print(getJsonResult(messageKey, result));
			return null;
		}
		 String xn=Base.currXn;
		List<HashMap<String, String>> wpfzList = service.getFzXnList(xn);// ��Ʒ����ѧ���б�
		request.setAttribute("fzmbxn", Base.currXn);
		request.setAttribute("wpfzList", wpfzList);
		String path = "xpj_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("wpfz");
	}

	/**
	 * 
	 * @����:��ʾ��Ƭ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-3 ����04:45:30
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
	public ActionForward showPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		String type = request.getParameter("type");
		String xmdm = request.getParameter("xmdm");
		if (null == model.getXmdm() || "".equals(model.getXmdm())||"add".equals(type)) {
			ServletContext application = request.getSession().getServletContext();
			InputStream is = new FileInputStream(new File(application.getRealPath("/images/axcsbgtp.jpg")));
			FileUtil.outputFileStream(is, response.getOutputStream());
		} else {
			InputStream is = service.getPhotoStream(xmdm);
			if (is == null) {
				ServletContext application = request.getSession().getServletContext();
				is = new FileInputStream(new File(application.getRealPath("/images/axcsbgtp.jpg")));
			}
			FileUtil.outputFileStream(is, response.getOutputStream());
		}
		return null;
	}

}
