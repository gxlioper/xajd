/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-5 ����02:21:14 
 */
package com.zfsoft.xgxt.axcs.wpsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.axcs.wpsq.js.WpsqJsService;
import com.zfsoft.xgxt.axcs.wpsz.WpszDao;
import com.zfsoft.xgxt.axcs.wpsz.WpszForm;
import com.zfsoft.xgxt.axcs.wpsz.WpszService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-5 ����02:21:14
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WpsqAction extends SuperAction {
	DAO dao = DAO.getInstance();
	private BdpzService bdpzService = new BdpzService();
	private WpszService wpszService = new WpszService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String AXCSXSZBB = "axcs";
	WpsqService service = new WpsqService();
	
	private static final String url = "axcs_axcswpsq_stu.do";

	/**
	 * 
	 * @����:��Ʒ�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-5 ����02:36:04
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
	public ActionForward wpsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqForm model = (WpsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		model.setXn(Base.currXn);
		List<HashMap<String, String>> xnList = dao.getXnndList();
		List<HashMap<String, String>> wplbList = wpszService.getWplbList();
		request.setAttribute("wplbList", wplbList);
		request.setAttribute("xnList", xnList);
		request.setAttribute("path", "axcs_axcswpsq_stu.do");
		FormModleCommon.commonRequestSet(request);
		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			request.setAttribute("xh", user.getUserName());
			return mapping.findForward("wpsqList");
		}
	}

	/**
	 * 
	 * @����:��֤ѧ���Ƿ�������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-11 ����02:20:59
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
	public ActionForward checkSqTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqForm model = (WpsqForm) form;
		List<HashMap<String, String>> resultList = new WpsqJsService().checkTj(model.getXmdm(), model.getXh());
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	/**
	 * 
	 * @����:��Ʒ���루ѧ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-9 ����07:04:48
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
	public ActionForward wpsqxsZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqForm model = (WpsqForm) form;
		User user = getUser(request);
		String xh = user.getUserName();
		if (!StringUtil.isNull(xh)) {
			// ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		WpszService wpszService = new WpszService();
		request.setAttribute("wpModel", StringUtils.formatData(wpszService.getWpxxByXmdm(model.getXmdm())));
		return mapping.findForward("wpsqxsZj");
	}

	/**
	 * 
	 * @����:��Ʒ���뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����11:34:04
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
	public ActionForward saveWpsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqForm model = (WpsqForm) form;
		String[] xmdm = request.getParameterValues("xmdm");
		boolean result = service.wpsqBc(xmdm, model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:��Ʒ�����޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����11:50:04
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
	public ActionForward wpsqUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqForm WpsqForm = (WpsqForm) form;
		WpsqForm model = service.getModel(WpsqForm);
		if (model != null) {
			BeanUtils.copyProperties(WpsqForm, model);
			// ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			// ������Ʒ��Ϣ
			WpszService wpszService = new WpszService();
			HashMap<String, String> wpxxMap = wpszService.getWpxxByXmdm(model.getXmdm());
			request.setAttribute("wpxxMap", StringUtils.formatData(wpxxMap));
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("wpsqUpdate");
	}

	/**
	 * 
	 * @����:�����޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����12:46:38
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
	public ActionForward saveUpdateWpsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqForm WpsqForm = (WpsqForm) form;
		boolean result = false;
		// ����ύ���������״̬
		if ("submit".equalsIgnoreCase(WpsqForm.getType()) || "tj".equalsIgnoreCase(WpsqForm.getType())) {
			if ("tj".equalsIgnoreCase(WpsqForm.getType())) {
				String values = request.getParameter("values");
				WpsqForm.setSqid(values);
			}
			WpsqForm.setShzt(Constants.YW_SHZ);// �����
			result = service.runUpdate(WpsqForm);
			WpszDao wpszDao = new WpszDao();
			WpszForm wpszForm = wpszDao.getModel(WpsqForm.getXmdm());
			String splc = wpszForm.getSplc();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(WpsqForm.getSqid(), splc, WpsqForm.getXh(), "axcs_axcswpsh.do", "axcs_axcswpsq_tea.do");
			}
		} else {
			result = service.runUpdate(WpsqForm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:��Ʒ���볷��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����01:46:22
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
	public ActionForward cancelWpsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			WpsqForm model = new WpsqForm();
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
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
