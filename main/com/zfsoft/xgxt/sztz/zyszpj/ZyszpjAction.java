/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-6 ����04:49:21 
 */
package com.zfsoft.xgxt.sztz.zyszpj;

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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ְҵ��������
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�· [���ţ�982]
 * @ʱ�䣺 2013-6-6 ����04:55:33
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZyszpjAction extends SuperAction {
	
	private static final String url = "zyszpjwh.do?method=list&query=lscx";
	
	/**
	 * 
	 * @����:ְҵ���������б��ѯ��ʾ
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
		ZyszpjService service = new ZyszpjService();
		CommService cs = new CommService();
		ZyszpjForm myForm = (ZyszpjForm) form;
		User user = getUser(request);
		String xh = user.getUserName();
		String query = request.getParameter("query");
		myForm.setXh(xh);
		myForm.setDqqx(query);
		request.setAttribute("query", service.getQueryStr(query, myForm));
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("zyszpjwh.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// ��ѯ��ʽ �Ǳ��˲�ѯ ����ͬѧ ������ʦ
		request.setAttribute("stuInfo", service.selectStuinfo(xh));
		//String path = "zyszpjwh.do?method=list&query="+query;
		String path="zyszpjwh.do";
		request.setAttribute("path", service.getPath(path, query));//����path�������õ�ǰλ��
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path",path);//����ԭpath���ڸ߼���ѯ
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @����:����ɾ��ְҵ��������
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDeleteZysz(values.split(","));
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
	 * @����:�޸���������
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
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		ZyszpjForm myForm = (ZyszpjForm) form;
		String query = request.getParameter("query");
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String[] xmlbid = request.getParameterValues("xmlbid");
			String[] sj = request.getParameterValues("sj");
			String[] dd = request.getParameterValues("dd");
			String[] hdnr = request.getParameterValues("hdnr");
			String[] cyjhjqk = request.getParameterValues("cyjhjqk");
			// ��ɾ����Ӧ������Ϣ��Ȼ��������ӣ����Ľ���ֱ�Ӹ��Ļ�ȡҳ��ֵ�Ƚ��鷳��
			// service.deleteZyszxxAll(myForm.getZyszid());//�Ѿ����� ְҵ����Ϊ����
			// ��Ӧ����ĿΪɾ��������
			service.zlForm(myForm, xmlbid, sj, dd, hdnr, cyjhjqk);
			boolean result = service.updateZyszxxAll(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		/*
		 * if(service.isCanUpdate(myForm,pjlx).equals("-1")){//�Ѿ���ʦ�������ڽ����޸�
		 * return showView(mapping, myForm, request, response); }
		 */
		String path = "zyszpjwh.do?method=list&query="+query;
		request.setAttribute("path", path);
		ZyszpjForm model = service.getModel(myForm);
		service.zlHprAndSpr(model);
		service.formatZyszpjForXq(model);
		request.setAttribute("xmlb", service.getXmlb());// ��Ŀ���
		request.setAttribute("xq", model.getXq());// ѧ��
		request.setAttribute("xqmc", model.getXqmc());// ѧ��
		request.setAttribute("xn", model.getXn());// ѧ��
		request.setAttribute("zyszid", model.getZyszid());
		service.zlXm(model);
		request.setAttribute("zxm", model.getZxm());// ����Ŀ��Ϣ
		request.setAttribute("zpxx", model.getZpxx());// ������Ϣ
		// ѧ����Ϣ
		request.setAttribute("stuInfo", StringUtils.formatData(service.selectStuinfo(model.getXh())));
		return mapping.findForward("update");
	}

	/**
	 * 
	 * @����:��дְҵ�������� ������Ϣ��������ʦ����
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
	public ActionForward zyszpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjForm myForm = (ZyszpjForm) form;
		String hpxx = myForm.getHpxx();
		String spxx = myForm.getSpxx();
		String pjlx = request.getParameter("pjlx");// ��������
		ZyszpjService service = new ZyszpjService();
		User user = getUser(request);
		String xh = user.getUserName();
		ZyszpjForm model = service.getModel(myForm);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (ZyszpjService._TXPJ.equalsIgnoreCase(pjlx)) {
				// ������Ϣ
				model.setHpxx(hpxx);
				model.setHprid(xh);
			} else if (ZyszpjService._LSPJ.equalsIgnoreCase(pjlx)) {
				// ʦ����Ϣ
				model.setPjdj(request.getParameter("pjdj"));
				model.setSpxx(spxx);
				model.setSprid(xh);
			}
			boolean result = service.updateZyszPjxx(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		BeanUtils.copyProperties(myForm, model);
		service.zlXm(myForm);
		String isCanPj = service.isCanUpdate(myForm, pjlx, xh);
		service.zlHpSp(myForm, isCanPj);
		request.setAttribute("sfkypj", isCanPj);// �Ƿ��������
		request.setAttribute("zxm", myForm.getZxm());// ����Ŀ
		request.setAttribute("stuInfo", StringUtils.formatData(service.selectStuinfo(model.getXh())));// ѧ����Ϣ
		request.setAttribute("data", StringUtils.formatData(myForm));
		request.setAttribute("pjlx", pjlx);// �������� ��������ʦ��
		request.setAttribute("pjdjlist", service.getPjdj()); // �ȼ��б�
		return mapping.findForward("zyszpj");
	}
	/**
	 * 
	 * @����:�Ƿ��ǹ���Ա�����޸�Ϊ�Ƿ��Ƿ�ѧ���û���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-24 ����05:03:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	private boolean isZfgly(User user){
		if(!user.getUserType().equals("stu")){//�������ѧ��
			return true;
		}
		/*user.getUserType().equals("");
		if(null!=user&&user.getUserName().equals("zf01")&&user.getRealName().equals("��������Ա")){
			return true;
		}*/
		return false;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		User user = getUser(request);
		String xh = user.getUserName();
		Object xhobj=request.getParameter("xh");
		if(xhobj!=null&&!xhobj.equals("undefined")&&!xhobj.equals("")){
			xh=xhobj.toString();
		}
		ZyszpjForm myForm = (ZyszpjForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// ǰ̨���ݲ���
			String[] xmlbid = request.getParameterValues("xmlbid");
			String[] sj = request.getParameterValues("sj");
			String[] dd = request.getParameterValues("dd");
			String[] hdnr = request.getParameterValues("hdnr");
			String[] cyjhjqk = request.getParameterValues("cyjhjqk");
			// ��������
			service.zlForm(myForm, xmlbid, sj, dd, hdnr, cyjhjqk);
			// ��������
			boolean result = service.saveZysz(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		// ѧ����Ϣ
		request.setAttribute("stuInfo", service.selectStuinfo(xh));
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("xmlb", service.getXmlb());// ��Ŀ���
		request.setAttribute("iszfgly",isZfgly(user));
		return mapping.findForward("add");
	}

	/**
	 * 
	 * @����:��ʾְҵ�������۾�����Ϣ���鿴ְҵ�������ۣ�
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
		ZyszpjService service = new ZyszpjService();
		ZyszpjForm myForm = (ZyszpjForm) form;
		ZyszpjForm model = service.getModel(myForm);
		// ��Ŀ���
		request.setAttribute("xmlb", service.getXmlb());
		request.setAttribute("zyszid", model.getZyszid());
		service.zlXm(myForm);
		// ����Ŀ
		request.setAttribute("zxm", myForm.getZxm());
		BeanUtils.copyProperties(myForm, model);
		// ѧ����Ϣ
		request.setAttribute("stuInfo", StringUtils.formatData(service.selectStuinfo(model.getXh())));
		service.zlHprAndSpr(myForm);
		service.formatZyszpjForXq(myForm);
		request.setAttribute("data", StringUtils.formatData(myForm));
		// ��ѯ����
		String query = request.getParameter("query");
		request.setAttribute("query", service.getQueryStr(query, myForm));
		return mapping.findForward("ckxx");
	}

	/**
	 * 
	 * @����:������ӡ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:24:20
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
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		String zyszid = request.getParameter("zyszid");
		zyszid = zyszid.substring(0, zyszid.length() - 1);
		String ids[] = zyszid.split(",");
		List<File> files = new ArrayList<File>();
		int i = 1;
		for (String id : ids) {
			// ��������
			ZyszpjForm myForm = (ZyszpjForm) form;
			myForm.setZyszid(id);
			ZyszpjForm model = service.getModel(myForm);
			// ���û��� ʦ����
			service.zlHprAndSpr(model);
			// ��������Ŀ��Ϣ
			model.setZxmMap(service.getPrinForZxm(model));
			// ����ѧ����Ϣ
			model.setXsxx(service.selectStuinfo(model.getXh()));
			File file = service.printWord(model);
			if (i == 1 && i == ids.length) {// ���ֻѡ����һ������
				FileUtil.outputWord(response, file);
				return null;
			}
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		FileUtil.outputZip(response, zipFile);
		return null;
	}

	/**
	 * 
	 * @����:�Ƿ��������
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:24:41
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
	public ActionForward isCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		ZyszpjForm myForm = (ZyszpjForm) form;
		User user = getUser(request);
		myForm.setXh(user.getUserName());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.getDqxqmc());
		// �Ƿ��������
		boolean result = service.isCanAdd(myForm);
		if (!result) {// ������
			response.getWriter().print(
					getJsonResult(MessageKey.ZYSZPJ_CANNOT_ADD, false));
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
}
