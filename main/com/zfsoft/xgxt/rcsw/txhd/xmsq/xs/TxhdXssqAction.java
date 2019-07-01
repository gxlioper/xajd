/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-24 ����09:38:17 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsq.xs;

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
import xgxt.action.base.BaseDAO;
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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.txhd.xmsq.js.TxhdXmsqJsService;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszForm;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ��������ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-6-25 ����12:38:17
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdXssqAction extends SuperAction {
	// �����ճ��������ճ���Ϊ�������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "rcsw_txhd_xmsq_xs.do";

	/**
	 * 
	 * @����:����루ѧ����ҳ��
	 * @���ߣ����� [���ţ�1104]
	 * @���ڣ�2014-6-25 ����12:38:17
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
	public ActionForward xmsqCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// ��ǰ��¼ѧ��
		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("curXn", Base.currXn);
		String path = "rcsw_txhd_xmsq_xs.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsqCk");
	}

	/**
	 * 
	 * @����:ѧ�����������
	 * @���ߣ����� [���ţ�1104]
	 * @���ڣ�2014-6-26 ����19:38:17
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
	@SystemLog(description="�����ճ�����-��ѧ�-����루ѧ����-����SQID:{sqid}")
	public ActionForward xmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqForm model = (TxhdXssqForm) form;
		TxhdXssqService service = new TxhdXssqService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveSq(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(model.getType())) {
			TxhdXmsqJsService txhdXmsqJsService =  new TxhdXmsqJsService();
			boolean sfksq = txhdXmsqJsService.getSymeForXmdm(model.getXmdm(),model.getType());
			if(sfksq){
				boolean result = service.saveSq(model, "submit");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
				return null;
			}
			
		}
		String path = "rcsw_txhd_xs_xmsq?method=xmsq";
		request.setAttribute("path", path);
		request.setAttribute("model", model);
		return mapping.findForward("xmsq");
	}

	/**
	 * 
	 * @����:��������ݲ�ѯ
	 * @���ߣ����� [���ţ�1104]
	 * @���ڣ�2014-6-26 ����10:38:17
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
	public ActionForward xmsqAjaxXs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqForm model = (TxhdXssqForm) form;
		TxhdXssqService service = new TxhdXssqService();
		User user = getUser(request); // ��ǰ��¼ѧ��
		String type = model.getType();
		if (org.apache.commons.lang.StringUtils.equals(type, "wsq")) {
			List<HashMap<String, String>> data = service.getXmsqPageListXs(
					model, user);
			HashMap<String, String> map = null;
//			for (HashMap<String, String> hashMap : data) {
//				hashMap.put("hdsj", hashMap.get("hdkssj") + "~"
//						+ hashMap.get("hdjssj"));
//				map = service.getCount(hashMap.get("xmdm"), hashMap.get("xq"),
//						hashMap.get("xn"));
//				String syme = null;
//				if(null ==hashMap.get("sqrssx")){
//					syme = "";
//				}else{
//					if (null == map.get("sqrs")) {
//						syme = "0";
//					} else {
//						syme = (Integer.parseInt(hashMap.get("sqrssx")) - Integer.parseInt(map.get("sqrs")))+ "";
//					}
//				}
//
//				hashMap.put("syme", syme);
//			}
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		} else if (org.apache.commons.lang.StringUtils.equals(type, "ysq")) {
			List<HashMap<String, String>> data = service.getXmsqPageListXsYsq(
					model, user);
			HashMap<String, String> map = null;
			for (HashMap<String, String> hashMap : data) {
				hashMap.put("hdsj", hashMap.get("hdkssj") + "~"
						+ hashMap.get("hdjssj"));
				map = service.getCount(hashMap.get("xmdm"), hashMap.get("xq"),
						hashMap.get("xn"));
				String ysqrs = null;
				if (null == map.get("sqrs")) {
					ysqrs = "0";
				} else {
					ysqrs = map.get("sqrs");
				}

				hashMap.put("ysqrs", ysqrs);
			}
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		}

		return null;
	}

	/**
	 * 
	 * @����:��Ŀ����
	 * @���ߣ����� [���ţ�1104]
	 * @���ڣ�2014-6-26 ����15:38:17
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
	public ActionForward xmsqXs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmszService xmszService = new TxhdXmszService();
		TxhdXssqForm myForm = (TxhdXssqForm) form;

		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		// ѧ�� ѧ��
		myForm.setXq(Base.currXq);
		myForm.setXn(Base.currXn);
		// ����
		TxhdXmszForm model = xmszService.getModel(myForm.getXmdm());
		model.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(model.getXq()));
		request.setAttribute("data", StringUtils.formatData(model));

		return mapping.findForward("xmsqXs");
	}

	/**
	 * 
	 * @����:TODO�ύ�����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-6-27 ����02:36:33
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
	@SystemLog(description="�����ճ�����-��ѧ�-����루ѧ����-�ύSQID:{sqid}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqService service = new TxhdXssqService();
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		String xmdm = request.getParameter("xmdm");
		System.out.println("shlc:" + splc);
		String xh = request.getParameter("xh");
		
		TxhdXmsqJsService txhdXmsqJsService =  new TxhdXmsqJsService();
		boolean sfksq = txhdXmsqJsService.getSymeForXmdm(xmdm,"submit");
		if(sfksq){
			boolean result = service.submitRecord(sqid, splc, xh);
			if (result) {
				// ����ҵ��״̬Ϊ'�����'
				TxhdXssqForm model = new TxhdXssqForm();
				model.setSqid(sqid);
				model.setShzt(Constants.YW_SHZ);
				service.updateModel(model);
			}
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}
		return null;
	}

	/**
	 * 
	 * @����:������޸�
	 * @���ߣ����� [���ţ�1104]
	 * @���ڣ�2014-6-25 ����12:38:17
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
	@SystemLog(description="�����ճ�����-��ѧ�-����루ѧ����-�޸�SQID:{sqid}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqService service = new TxhdXssqService();
		TxhdXssqForm myForm = (TxhdXssqForm) form;
		TxhdXmszService xmszService = new TxhdXmszService();
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			myForm.setShzt(Constants.YW_WTJ);
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(myForm.getType())) {
			myForm.setShzt(Constants.YW_SHZ);
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		TxhdXssqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		// ѧ��������Ϣ
		String path = "rcsw_txhd_xs_xmsq.do?method=update";
		request.setAttribute("path", path);
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		TxhdXmszForm xmszmodel = xmszService.getModel(myForm.getXmdm());
		model.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(model.getXq()));
		request.setAttribute("sqdata", StringUtils.formatData(model));
		request.setAttribute("data", StringUtils.formatData(xmszmodel));
		return mapping.findForward("update");
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-06-27 ����14:18:05
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
	@SystemLog(description="�����ճ�����-��ѧ�-����루ѧ����-����VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqService service = new TxhdXssqService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values, lcid);
		if (result) {

			// ����ҵ��״̬Ϊ'δ�ύ'
			TxhdXssqForm model = new TxhdXssqForm();
			model.setSqid(values);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(values)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateModel(model);

		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ����� [���ţ�1104]
	 * @���ڣ�2014-06-24 ����15:50:35
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
	@SystemLog(description="�����ճ�����-��ѧ�-����루ѧ����-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqService service = new TxhdXssqService();
		String values = request.getParameter("values");
		int result = service.runDelete(values.split(","));
		String messageKey = null;
		if (result > 0) {
			messageKey = ShlcUtil.deleteSpxx(values.split(",")) ? MessageKey.SYS_DEL_SUCCESS
					: MessageKey.SYS_DEL_FAIL;
		} else {
			messageKey = MessageKey.SYS_DEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

}
