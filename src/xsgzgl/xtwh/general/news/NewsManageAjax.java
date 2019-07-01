package xsgzgl.xtwh.general.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.SearchUtil;

/**
 * ���Ź����첽��
 */
public class NewsManageAjax extends SuperAction {
	// =========== ��ʼ�� begin ============
	private List<HashMap<String, String>> radioList = new ArrayList<HashMap<String, String>>();
	{
		addRadioList("ȫУ", "ʦ��", "teastu", radioList);
		addRadioList("��ʦ", "��ʦ", "tea", radioList);
		addRadioList("ѧ��", "ѧ��", "stu", radioList);
	}

	private void addRadioList(String pName, String cName, String toWho, List<HashMap<String, String>> radioList){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pName", pName);
		map.put("cName", cName);
		map.put("toWho", toWho);
		radioList.add(map);
	}
	// =========== ��ʼ�� end ============
	/**
	 * ���Ź���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newsSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO �Զ����ɷ������
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		SearchRsModel rsModel = new SearchRsModel();

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// ���ݲ���
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ��ͷ
		List<HashMap<String, String>> topTr = service.getNewsTop();
		// �����
		ArrayList<String[]> rs = (ArrayList<String[]>) service
				.getNewsInfo(myForm);

		// ���������
		String spHtml = "";
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rs);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.myCreateRs(rsModel, myForm.getPages(), response);

		return null;
	}

	/**
	 * ����ɾ��
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	@SystemLog(description="����ϵͳά��-ϵͳ����-֪ͨ����-ɾ��VALUES:{array_primarykey_checkVal}")
	public ActionForward newsDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// ������ֵ
		service.getModelValue(myForm, request);

		// ��Ϣ��Ϣ
		String message = "";

		boolean flag = false;

		flag = service.delNews(myForm);

		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ���ŷ���
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	public ActionForward newsFb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// ������ֵ
		service.getModelValue(myForm, request);

		// ��Ϣ��Ϣ
		String message = "";

		boolean flag = false;

		flag = service.newsFb(myForm);

		message = flag ? "�����ɹ�" : "����ʧ��";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;

	}

	/**
	 * ����ȡ������
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	public ActionForward newsQxfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// ������ֵ
		service.getModelValue(myForm, request);

		// ��Ϣ��Ϣ
		String message = "";

		boolean flag = false;

		flag = service.newsQxfb(myForm);

		message = flag ? "ȡ�������ɹ�" : "ȡ������ʧ��";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;

	}

	/**
	 * �����ö�
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	public ActionForward newsZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// ������ֵ
		service.getModelValue(myForm, request);

		// ��Ϣ��Ϣ
		String message = "";

		boolean flag = false;

		flag = service.newsZd(myForm);

		message = flag ? "�ö��ɹ�" : "�ö�ʧ��";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;

	}

	/**
	 * ����ȡ���ö�
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	public ActionForward newsQxzd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// ������ֵ
		service.getModelValue(myForm, request);

		// ��Ϣ��Ϣ
		String message = "";

		boolean flag = false;

		flag = service.newsQxzd(myForm);

		message = flag ? "ȡ���ö��ɹ�" : "ȡ���ö�ʧ��";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;

	}

	/**
	 * �����޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		NewsManageForm myForm = (NewsManageForm) form;
		//myForm.setTypeid(service.getTypeId(myForm));
		List<HashMap<String, String>> typeList = service.getNewsListType();
		RequestForm rForm = new RequestForm();
		service.setRequestValue(rForm, request);
		NewQxfwForm nqf = SearchUtil.getInstance().getNewQxfwForm(
				request.getParameter("newsId"));
		request.setAttribute("search", nqf);
		request.setAttribute("searchTj", nqf.getSearchModel());
		request.setAttribute("selectTj", SearchUtil.getInstance().getSelectTj(
				nqf)); 
		request.setAttribute("typeList", typeList);
		request.setAttribute("radioList", radioList);
		service.newsUpdate(request);
		myForm.setTypeid(request.getAttribute("typeid").toString());
		return mapping.findForward("newsUpdate");
	}

	/**
	 * �������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward newsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		NewsManageForm myForm = (NewsManageForm) form;
		List<HashMap<String, String>> typeList = service.getNewsListType();
		RequestForm rForm = new RequestForm();
		service.setRequestValue(rForm, request);
		request.setAttribute("typeList", typeList);
		request.setAttribute("radioList", radioList);
		return mapping.findForward("newsAdd");
	}

	/**
	 * ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="����ϵͳά��-ϵͳ����-֪ͨ����-���ӻ��޸�NEWSTITLE:{newsTitle},EDITORID:{editorid},NEWSID:{newsId}")
	public ActionForward saveNews(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		String doType = request.getParameter("dotype");
		List<HashMap<String, String>> typeList = service.getNewsListType();
		request.setAttribute("typeList", typeList);
		request.setAttribute("radioList", radioList);
		boolean b = true;
		if (doType.equalsIgnoreCase("update")) {
			b = service.updateNews(request);

//			return mapping.findForward("newsUpdate");
		} else {
			b = service.saveNews(request);
//			return mapping.findForward("newsAdd");
		}
		String messageKey = b ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * ��ҳ���������б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNewsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();

		List<HashMap<String, String>> result = service
				.getNewsPageList(myForm, user);
		JSONArray resultList = JSONArray.fromObject(result); // תΪjson��ʽ
		response.setContentType("text/html;charset=gbk"); // ajax���󷵻�����ת�룬�������������
		response.getWriter().print(resultList);

		return null;
	}

	public ActionForward getNewsListType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NewsManageService service = new NewsManageService();

		List<HashMap<String, String>> result = service.getNewsListType();
		JSONArray resultList = JSONArray.fromObject(result); // תΪjson��ʽ
		response.setContentType("text/html;charset=gbk"); // ajax���󷵻�����ת�룬�������������
		response.getWriter().print(resultList);

		return null;
	}
}
