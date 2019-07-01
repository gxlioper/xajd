/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-28 ����10:54:02 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpzsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
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

import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����������������
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-28 ����10:54:02
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjSzAction extends SuperAction {
	public final static String JTPJ_SHLC = "pjpy";
	
	private static final String url = "jtpjszbase.do";

	/**
	 * 
	 * @����:���������б��ѯ��ʾ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("jtpjszbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("sqzq", Base.currXn + "," + Base.currXq);
		request.setAttribute("sqzqList", service.getNowSqZqList());
		List<HashMap<String, String>>  list=service.getSqZqListNotIsHave(Base.currXn + "," + Base.currXq);
		request.setAttribute("iscanCopy", null==list||list.size()<=0?"0":"1");
		request.setAttribute("path", "jtpjszbase.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jtpjszlist");
	}

	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
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
	@SystemLog(description = "������������-��������-������������-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//�ж�������Ŀ�����Ƿ���ʹ��
			String resultjxmc = service.isCheckExistForDel(values);
			if(!resultjxmc.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_JTPJ_JXMC_DEL, resultjxmc);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			String message = (num > 0) ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num)
					: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @����: ��������ģ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
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
	@SystemLog(description = "������������-��������-������������-�޸�JXMC:{jxmc},JTPJDW:{jtpjdw},PDXN:{pdxn},PDXQ:{pdxq},SFKFSQ:{sfkfsq},JXID:{jxid},SPLCID:{splcid}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			//����������Ŀ�����ظ����ж�
			boolean isExist=service.isExistByJxmc(myForm);
			if(isExist){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_JXMC_REPEAT));
				return null;
			}
		}
		JtpjSzForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		// ѧ�� ѧ��
		request.setAttribute("dqxn", myForm.getSqxn());
		request.setAttribute("dqxq", BaseDAO.getInstance().getXqmcForXqdm(
				myForm.getSqxq()));
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		// ְ��list
		request.setAttribute("zwList", service.getZwList());
		// ����
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService
				.getShlcByDjlx(JTPJ_SHLC);
		request.setAttribute("shlcList", shlc);
		//�Ƿ����ݴ�ѧ
		
		request.setAttribute("sfkxg", service.checkIsNotExists(myForm.getJxid()) ? "1" : "0");
		request.setAttribute("iswzdx", Globals.XXDM_WZDX.equals(Base.xxdm)?"1":"0");
		return mapping.findForward("jtpjszupdate");
	}

	/**
	 * 
	 * @����: ��������ģ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
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
	public ActionForward copy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		myForm = service.getModel(myForm);
		myForm.setJxid(null);
		// ��������
		myForm.setSfkfsq("0");
		boolean result = service.runInsert(myForm);
		String messageKey = result ? MessageKey.SYS_COPY_SUCCESS
				: MessageKey.SYS_COPY_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����: ���潱���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-7 ����04:43:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward savecopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		String lyxn=request.getParameter("lyxn");
		String lyxq=request.getParameter("lyxq");
		String sqxn=request.getParameter("sqxn");
		String sqxq=request.getParameter("sqxq");

		boolean result = service.copy(lyxn,lyxq,sqxn,sqxq);
		String messageKey = result ? MessageKey.SYS_COPY_SUCCESS
				: MessageKey.SYS_COPY_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����: �����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-7 ����04:43:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jtpjszcopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		request.setAttribute("sqzqList", service.getSqZqListNotIsHave(myForm.getSqxn()+","+myForm.getSqxq()));
		request.setAttribute("data",myForm);
		request.setAttribute("xqmc",service.getXqmc(myForm.getSqxq()));
		return mapping.findForward("jtpjszcopy");
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:44:10
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
	@SystemLog(description = "������������-��������-������������-����JXMC:{jxmc},JTPJDW:{jtpjdw},PDXN:{pdxn},PDXQ:{pdxq},SFKFSQ:{sfkfsq},SPLCID:{splcid}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			myForm.setSqxn(Base.currXn);
			myForm.setSqxq(Base.currXq);
			//����������Ŀ�����ظ����ж�
			boolean isExist=service.isExistByJxmc(myForm);
			if(isExist){
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_JXMC_REPEAT));
				return null;
			}
		}
		//�Ƿ����ݴ�ѧ
		request.setAttribute("iswzdx", Globals.XXDM_WZDX.equals(Base.xxdm)?"1":"0");
		// ѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		// ְ��list
		request.setAttribute("zwList", service.getZwList());
		// ����
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService
				.getShlcByDjlx(JTPJ_SHLC);
		request.setAttribute("shlcList", shlc);
		// Ĭ��ֵ
		//myForm.setJtpjdw("xy");
		//myForm.setSfkfsq("0");
		this.saveToken(request);
		return mapping.findForward("jtpjszadd");
	}

	/**
	 * 
	 * @����:��ʾ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-17 ����05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		myForm = service.getModel(myForm);
		// ְ��list
		request.setAttribute("zwmc", service.getZwMc(myForm.getKsqxslx()));
		request.setAttribute("sfkfsqmc", service
				.getSfkfsqmc(myForm.getSfkfsq()));
		request.setAttribute("sqxqmc", BaseDAO.getInstance().getXqmcForXqdm(
				myForm.getSqxq()));
		request.setAttribute("pdxqmc", BaseDAO.getInstance().getXqmcForXqdm(
				myForm.getPdxq()));
		// ����
		request.setAttribute("splcmc", service.getLcxxMc(myForm.getSplcid(),
				JTPJ_SHLC));
		request.setAttribute("data", StringUtils.formatData(myForm));
		//�Ƿ����ݴ�ѧ
		request.setAttribute("iswzdx", Globals.XXDM_WZDX.equals(Base.xxdm)?"1":"0");
		return mapping.findForward("jtpjszview");
	}

	/**
	 * 
	 * @����: ���ؽ�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����09:50:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward loadJxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		String jxid = request.getParameter("jxid");
		JtpjSzForm myForm = new JtpjSzForm();
		if(StringUtils.isNotNull(jxid) && !"null".equals(jxid)){
			myForm = service.getModel(jxid);
		}
		response.getWriter().print(JSONObject.fromObject(StringUtils.formatData(myForm)));
		return null;
	}
	
	
	/**
	 * 
	 * @����:ͨ������ѧ��ѧ��ȡ�ü��������б�
	 * @���ߣ�������
	 * @���ڣ�2014-5-19 ����09:39:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward loadJtpjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService jss = new JtpjSzService();
		// ����ѧ��
		String sqxn = request.getParameter("sqxn");

		// ����ѧ��
		String sqxq = request.getParameter("sqxq");
		
		User user = getUser(request);
		// ���������б�
		List<HashMap<String, String>> jtpjList = jss.getJxList(sqxn,sqxq, "0",user);

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("jtpjList", jtpjList);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
		
	}
	
	/**
	 * @����:����б�Ԥ��
	 */
	public ActionForward bgylList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		JtpjSzForm model = (JtpjSzForm)form;
		JtpjSzService service = new JtpjSzService();
		//��Ŀ��Ϣ
		JtpjSzForm xmxx = service.getModel(model.getJxid());
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(xmxx.getSqxq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		//����ͼƬ�б�
		List<HashMap<String,String>> bbxxList = service.getBbxxList();
		
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("djblist");
	}
	
	/**
	 * @����:�ǼǱ�Ԥ��
	 */
	public ActionForward showYlbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtpjSzForm model = (JtpjSzForm)form;
		JtpjSzService service = new JtpjSzService();
		//��Ŀ��Ϣ
		JtpjSzForm xmxx = service.getModel(model.getJxid());
		String bbdm=request.getParameter("bbdm");
		
		//����ͼƬ�б�
		List<HashMap<String,String>> bbxxList = service.getBbxxList(bbdm);
		
		request.setAttribute("bbdm", bbdm);//ѡ�еĵǼǱ�ID
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("djbview");

	}
	
	/**
	 * @����:������Ŀ�ǼǱ�
	 */
	@SystemLog(description="������������-��������-��Ŀ����-�ǼǱ�����-����XMDM��{xmdm}")
	public ActionForward updateXmbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtpjSzForm model=new JtpjSzForm();
		model.setDybbid(request.getParameter("bbdm"));
		model.setJxid(request.getParameter("xmdm"));
		JtpjSzService service = new JtpjSzService();
		boolean isSuccess = service.runUpdate(model);
		response.getWriter().print(isSuccess);
		return null;
	}
}
