/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.xmwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xszz.bbwh.BbwhService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmlbwh.XmlbwhService;
import com.zfsoft.xgxt.xszz.xmwh.rssz.XmwhRsszService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhAction extends SuperAction {
	private XmwhService service = new XmwhService();
	private String messageKey;
	
	private static final String url = "xszz_xmwh.do?method=xmwhCx";

	/**
	 * 
	 * @����:������ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xmwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm myForm = (XmwhForm) form;
		if (QUERY.equals(myForm.getType())) {
			if (StringUtil.isNull(myForm.getSqzqdm())) {
				myForm.setSqxn(Base.currXn);
				myForm.setSqxq(Base.currXq);
			} else {
				myForm.setSqxn(myForm.getSqzqdm().split(";")[0]);
				myForm.setSqxq(myForm.getSqzqdm().split(";")[1]);
			}

			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String currDate = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		request.setAttribute("currDate", currDate);
		request.setAttribute("XmzqList", service.getXmzqList("Query"));
		
		List<HashMap<String, String>> xmlbList = service.getXmlb();// �������
		request.setAttribute("xmlbList", xmlbList);

		
		myForm.setSqzqdm(Base.currXn + ";" + Base.currXq);
		
		request.setAttribute("currXnXqmc", Base.currXn + " " + Base.getXqmcForXqdm(Base.currXq));
		
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmwhCx");
	}

	/**
	 * 
	 * @����:���ӷ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-����XMMC:{xmmc},LBDM:{lbdm},JE:{je}")
	public ActionForward xmwhZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm myForm = (XmwhForm) form;

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (service.isRepeat(myForm)) {// �����ظ���֤
				messageKey = MessageKey.XSZZ_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			// ���ӵ���ĿĬ��Ϊ��ǰѧ��ѧ�ڡ�
			myForm.setSqxn(Base.currXn);
			myForm.setSqxq(Base.currXq);
			boolean result = service.runInsert(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XmlbwhService xmlbwhService = new XmlbwhService();
		List<HashMap<String, String>> xmlb = xmlbwhService.getXmlb();
		request.setAttribute("xmlbList", xmlb);// ��Ŀ����������

		BbwhService bbwhService = new BbwhService();
		List<HashMap<String, String>> bb = bbwhService.getBb();
		request.setAttribute("bbList", bb);// �����������
		request.setAttribute("dqzq", Base.currXn + " " + Base.getDqxqmc());

		return mapping.findForward("xmwhZj");
	}

	/**
	 * 
	 * @����:�޸ķ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-�޸�XMYXG:{xmyxg},XMDM:{xmdm},XMMC:{xmmc},LBDM:{lbdm},JE:{je}")
	public ActionForward xmwhXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhForm myForm = (XmwhForm) form;
		String xmmcyxg = request.getParameter("xmyxg");
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if ("true".equals(xmmcyxg)) {
				if (service.isRepeat(myForm)) {// �����ظ���֤
					messageKey = MessageKey.XSZZ_XMMC_EXIST;
					response.getWriter()
							.print(getJsonResult(messageKey, false));
					return null;
				}
			}
			if (service.isRalate(myForm)) {// ������
				messageKey = MessageKey.XSZZ_XM_NOTUPDATE;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			boolean result = service.runUpdate(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XmlbwhService xmlbwhService = new XmlbwhService();
		List<HashMap<String, String>> xmlb = xmlbwhService.getXmlb();
		request.setAttribute("xmlbList", xmlb);// ��Ŀ����������

		BbwhService bbwhService = new BbwhService();
		List<HashMap<String, String>> bb = bbwhService.getBb();
		request.setAttribute("bbList", bb);// �����������
		request.setAttribute("dqzq", Base.currXn + " " + Base.getDqxqmc());

		XmwhForm model = service.getModel(myForm);
		request.setAttribute("sqzq", model.getSqxn() + " "
				+ Base.getXqmcForXqdm(model.getSqxq()));// ��������
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("xmmcxgq", myForm.getXmmc());
		return mapping.findForward("xmwhXg");
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-��������RSKZFWOLD:{rskzfwOld}")
	public ActionForward xmwhJbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhForm myForm = (XmwhForm) form;

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String rskzfwOld = request.getParameter("rskzfwOld");
			XmwhRsszService xmwhRsszService = new XmwhRsszService();
			String xmdm = myForm.getXmdm();
			if (rskzfwOld != null && !rskzfwOld.equals(myForm.getRskzfw())) {// �������Ʒ�Χ�޸�
				xmwhRsszService.runDeleteByXmdm(xmdm);
			}

			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XmwhForm model = service.getModel(myForm);
		if(null==model.getXslb()){
			model.setXslb("1");
		}
		BeanUtils.copyProperties(myForm, model);

		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xszz");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> isnotList = new OptionUtil()
				.getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("kgList", isnotList);

		List<HashMap<String, String>> onoffList = new OptionUtil()
				.getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);

		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��

		KnsjgService knsjgService = new KnsjgService();
		List<HashMap<String, String>> xn = knsjgService.getKnsjgXn();
		request.setAttribute("knsrdxnList", xn);// �������϶�ѧ��

		List<HashMap<String, String>> xq = knsjgService.getKnsjgXq();
		request.setAttribute("knsrdxqList", xq);// �������϶�ѧ��

		List<HashMap<String, String>> xqList = Base.getXqList();
		String currXn = Base.currXn;// ��ǰѧ��
		String currXq = Base.currXq;// ����ѧ��
		String currXqmc = "";
		for (HashMap<String, String> map : xqList) {
			if (currXq.equals(map.get("xqdm"))) {
				currXqmc = map.get("xqmc");
				break;
			}
		}
		request.setAttribute("currXn", currXn);
		request.setAttribute("currXq", currXq);
		request.setAttribute("currXqmc", currXqmc);
		request.setAttribute("sqzq", model.getSqxn() + " "
				+ Base.getXqmcForXqdm(model.getSqxq()));

		// �Ƿ���ѧ��������Ŀ
		XszzSqshService xszzSqshService = new XszzSqshService();
		String xmdm = request.getParameter("xmdm");
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		request.setAttribute("spzt", flag);
		
		request.setAttribute("xxdm", Base.xxdm);
		String xmmc = service.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		request.setAttribute("pyccList",service.getPyccList());
		return mapping.findForward("xmwhJbsz");
	}

	/**
	 * 
	 * @����:����������̣���ȡ��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
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
	 * @����:�����������϶����ڣ���ȡ�϶�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public ActionForward xmwhKnsrddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		if (xn != null) {
			KnsdcService knsdcService = new KnsdcService();
			List<HashMap<String, String>> list = knsdcService.getKnsjgRddc(xn,
					xq);
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(list));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}

	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-ɾ��VALUES:{values}")
	public ActionForward xmwhSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// //�жϺϷ���////
			if (service.isRalate(values)) {// ������
				messageKey = MessageKey.XSZZ_XM_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			if (result) {
				try {
					service.delRalate(values);// ɾ�����еĹ�����
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
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
	 * @����:�ж���Ŀ�Ƿ������ñ���
	 * @���ߣ�honglin
	 * @���ڣ�2013-5-9 ����03:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public ActionForward getXszzdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmmc = request.getParameter("xmmc");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");

		xmmc = xmmc != null ? xmmc.trim() : "";
		String result = service.getXszzdm(xmmc, xn, xq);
		response.getWriter().print(getJsonMessage(result));

		return null;

	}

	/**
	 * 
	 * @����:�ж��Ƿ������������е�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-10 ����09:24:40
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public ActionForward isExistShlcData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzSqshService xszzSqshService = new XszzSqshService();
		String xmdm = request.getParameter("xmdm");
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		response.getWriter().print(getJsonMessage(String.valueOf(flag)));
		return null;
	}

	/**
	 * 
	 * @����:������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-21 ����04:17:51
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
	@SystemLog(description="����ѧ������-��������-������Ŀ����-����XMFZND:{xmfznd}")
	public ActionForward xmwhfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhForm myForm = (XmwhForm) form;

		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> xmfzList = service.getXmzqList("fz");// ������б�
			JSONArray dataList = JSONArray.fromObject(xmfzList);
			response.getWriter().print(dataList);
			return null;
		} else if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String xmfznd = request.getParameter("xmfznd");
			boolean result = service.runXmfz(xmfznd);
			String messageKey = result ? MessageKey.PJPY_JXFZ_SUCCESS
					: MessageKey.PJPY_JXFZ_NOTJL;
			response.getWriter().print(getJsonResult(messageKey, result));
			return null;
		}

		request
				.setAttribute("currXnXqmc", Base.currXn + " "
						+ Base.getDqxqmc());

		String path = "xpj_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmwhfz");
	}




	/**
	 * 
	 * @����: ��Ŀά��-��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-21 ����03:48:46
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
	public ActionForward xmwhJfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmwhForm myForm = (XmwhForm) form;
		XmwhForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		
		List<HashMap<String, String>> others = service.getOthers(model.getXmdm());
		List<HashMap<String, String>> sameGroupList = service.getSameGroupXmList(model.getXmdm());
		Map<String,String> xmjfMap = service.getXyjf(model.getXmdm());
		
		request.setAttribute("xmjfMap", xmjfMap);
		request.setAttribute("others", others);
		request.setAttribute("sameGroupList", sameGroupList);
		FormModleCommon.setNjXyZyBjList(request);
		return mapping.findForward("xmwhJfsz");
	}


	/**
	 * 
	 * @����: ���澭������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-22 ����09:42:03
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
	@SystemLog(description="����ѧ������-��������-������Ŀ����-�������ñ���GROUPXMDM:{groupXmdm}")
	public ActionForward saveJfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhForm myForm = (XmwhForm) form;
		String[] groupXmdm = request.getParameterValues("groupXmdm");
		String[] xydm = request.getParameterValues("xydm");
		String[] je = request.getParameterValues("je");
		
		boolean result = service.savJfsz(myForm.getXmdm(), groupXmdm, xydm, je);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}






}
