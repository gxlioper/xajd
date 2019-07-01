package xgxt.xljk.hzny;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.xszz.whtl.ybbx.XszzYbbxForm;
import xgxt.xszz.whtl.ybbx.XszzYbbxInit;
import xgxt.xszz.whtl.ybbx.XszzYbbxSaveModel;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

public class HznyXljkZxzxAjax extends BasicAction {

	HznyXljkZxzxService service = new HznyXljkZxzxService();

	// -------------------------��ѯʦ��Ϣ���� begin
	// ------------------------------------
	/**
	 * ��ѯʦ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxsglSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxForm myForm = (HznyXljkZxzxForm) form;

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		model.setColList(new String[] { "pkValue", "zgh", "xm", "xb", "bmmc",
				"zxszg_info", "zxszg" });
		model.setPath("hn_xljk_zxsgl.do");
		init.initZxsglManage(rForm, model, request, user);

		// �������ʾ�ֶ�

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "hn_xljk_zxsgl.do");

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getZxsglList(model);

		// ���������
		String spHtml = service
				.createZxsglHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ������ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		model.setTableName("xg_xljk_zxsxxb");
		// flag=service.checkInfo(model);

		// if(flag){

		init.initZxsSave(model, request);

		flag = service.saveInfo(model);

		// }else {
		// message="�ü�¼������˲������޷��޸ģ�";
		//		
		// }

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �޸���ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiZxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		model.setTableName("xg_xljk_zxsxxb");

		init.initZxsModi(model, request);

		flag = service.updateInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����ɾ��ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "zgh" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_xljk_zxsxxb");

		// ����
		model.setPkValue(pkValue);

		// flag=service.checkInfo(model);
		// if(!flag){
		//			
		// message="��ѡ��¼�д�������˲������޷�ɾ����";
		// }

		// ����ɾ��

		flag = service.batchDelete(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------��ѯʦ��Ϣ���� end ------------------------------------

	// -------------------------����ѧ������ begin
	// ------------------------------------
	/**
	 * ��ѯʦ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxsglSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		model.setColList(new String[] { "pkValue", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc" });
		model.setPath("hn_xljk_tsxsgl.do");
		init.initZxsglManage(rForm, model, request, user);

		// �������ʾ�ֶ�

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "hn_xljk_tsxsgl.do");

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getTsxsglList(model);

		// ���������
		String spHtml = service.createSearchHTML(rsModel, model, rsArrList,
				user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ������ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initTsxsSave(model, request, user);

		flag = service.saveInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �޸���ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initTsxsModi(model, request);

		flag = service.updateInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����ɾ��ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "xh" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_xljk_tsxsxxb");

		// ����
		model.setPkValue(pkValue);

		// flag=service.checkInfo(model);
		// if(!flag){
		//			
		// message="��ѡ��¼�д�������˲������޷�ɾ����";
		// }

		// ����ɾ��

		flag = service.batchDelete(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------��ѯʦ��Ϣ���� end ------------------------------------

	// ------------------------������ѯ���� begin ------------------------------------
	/**
	 * ��ѯʦ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzxglSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxForm myForm = (HznyXljkZxzxForm) form;

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String userType = user.getUserType();

		// --------------------����Ȩ������ ѧ������ѯʦ�Ľ�����ֶ�-------------------------
		if ("stu".equalsIgnoreCase(userType)) {

			model.setColList(new String[] { "pkValue", "xh", "xm", "zxsj",
					"zxwt_info", "sfhf", "zxwt" });
		} else {

			model.setColList(new String[] { "pkValue", "xh", "xsxm", "nj",
					"bjmc", "zxsj", "zxwt_info", "sfhf","sftsxs", "zxwt" });
		}

		model.setPath("hn_xljk_zxzx.do");
		init.initZxzxglManage(rForm, model, request, user);

		// �������ʾ�ֶ�

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setPath("hn_xljk_zxzx.do");
		model.setSearchModel(searchModel);

		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "hn_xljk_zxzx.do");

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getZxzxglList(model);

		// ���������
		String spHtml = service.createZxzxHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ������ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initZxzxSave(model, request);

		flag = service.saveInfo(model);

		String czcg="��л�������Σ����ǽ�����ظ����б�Ҫʱ���绰��ϵ����������ϵ�绰027-87670586�����ĵ�ַ����ѧ�������208�ҡ�";
		if (Base.isNull(message)) {
			message = flag ? czcg
					:MessageInfo.MESSAGE_SAVE_FALSE ;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �޸���ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiZxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);
		
		String userName=user.getUserName();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initZxzxModi(model, request, user);

		String guid = request.getParameter("guid");
		
		flag= service.checkZxs(userName);
		
		if(!flag){
		
			flag = service.checkZxzx(guid);
		}	
		
		if (flag) {
			
			flag = service.updateInfo(model);
		
		} else {
			
			message = "������ѯʦ�����ظ���¼�޷��޸ģ�";
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����ɾ��ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteZxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "guid" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_xljk_zxzxb");

		// ����
		model.setPkValue(pkValue);
		

		// ����ɾ��
		flag=service.checkZxzx(pkValue);
		if(!flag){
					
			 message="��ѡ��¼�д�����ѯʦ�ѻظ���¼���޷�ɾ����";
		}else{

			flag = service.batchDelete(model);
		}
		
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------������ѯ���� end ------------------------------------

	// ------------------------ѧ����ѯ begin ------------------------------------
	/**
	 * ѧ����ѯ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszxSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxForm myForm = (HznyXljkZxzxForm) form;

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String userType = user.getUserType();

		// --------------------����Ȩ������ ѧ������ѯʦ�Ľ�����ֶ�-------------------------
		if ("stu".equalsIgnoreCase(userType)) {

			model.setColList(new String[] { "pkValue", "zxsxm", "zxsj",
					"zxnrjyj", "sffk","sfbrpj" });
		} else {

			model.setColList(new String[] { "pkValue", "zxsxm", "zxsj",
					"zxnrjyj", "sffk", "sfzdbr" });
		}
		
		String path="hn_xljk_xszx.do";
//		��ѯ�Ľ����
		if("stu".equalsIgnoreCase(userType)){
			
			model.setColList(new String[]{"pkValue","zxsxm","zxsj", "zxnrjyj", "sffk"});
		
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			path="hn_xljk_xszx.do?searchType=admin";
			model.setColList(new String[]{"pkValue","zgh","zxsxm","xh","xm","zxsj", "sffk"});
		
		}else {
			path="hn_xljk_xszx.do?searchType=zxs";
			model.setColList(new String[]{"pkValue","zxsxm","zxsj", "zxnrjyj", "sffk","sfzdbr"});
		}
		
		
		model.setPath(path);
		init.initXszxManage(rForm, model, request, user);

		// �������ʾ�ֶ�

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, path);

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getXszxList(model);

		// ���������
		String spHtml = service.createSearchHTML(rsModel, model, rsArrList,
				user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ������ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXszx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initXszxSave(model, request, user);

		flag = service.saveInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �޸���ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiXszx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);
		
		String userType=user.getUserType();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = true;

		init.initXszxModi(model, request, user);
		
		if("stu".equalsIgnoreCase(userType)){
			flag=service.checkXszx(model.getValueMap().get("guid"));
		}
		
		if(!flag){
			
			message="��ѡ�ļ�¼��ѯʦ�����������޷��޸ģ�";
		}else{
			
			flag = service.updateInfo(model);

		}

	
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����ɾ��ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteXszx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "guid" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_xljk_xszxfkb");

		// ����
		model.setPkValue(pkValue);

	    flag=service.checkXszx(pkValue);
		if(!flag){
					
			message="��ѡ��¼�д�����ѯʦ�����ظ���¼���޷�ɾ������ȷ�ϣ�";
		 
		}else {

			// ����ɾ��
	
			flag = service.batchDelete(model);
		
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------������ѯ���� end ------------------------------------

	// ------------------------ѧ����ѯ begin ------------------------------------
	/**
	 * ѧ����ѯ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjlSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxForm myForm = (HznyXljkZxzxForm) form;

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String userType = user.getUserType();

		// --------------------����Ȩ������ ѧ������ѯʦ�Ľ�����ֶ�-------------------------
		if("stu".equalsIgnoreCase(userType)){
			model.setColList(new String[] { "pkValue", "xh", "xm", "nj", "bjmc","zxsj" });
		}else {
			
			model.setColList(new String[] { "pkValue", "xh", "xsxm", "nj", "bjmc",
					"zxsj","sftsxs" });
		}
		model.setPath("hn_xljk_zxjl.do");
		init.initXszxManage(rForm, model, request, user);

		// �������ʾ�ֶ�

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "hn_xljk_zxjl.do");

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getZxjlList(model);

		// ���������
		String spHtml = service.createSearchHTML(rsModel, model, rsArrList,
				user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ������ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZxjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initZxjlSave(model, request, user);

		flag = service.saveInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �޸���ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiZxjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initZxjlModi(model, request, user);

		flag = service.updateInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����ɾ��ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteZxjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "guid" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_xljk_xlzxjlb");

		// ����
		model.setPkValue(pkValue);

		// flag=service.checkInfo(model);
		// if(!flag){
		//			
		// message="��ѡ��¼�д�������˲������޷�ɾ����";
		// }

		// ����ɾ��

		flag = service.batchDelete(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------������ѯ���� end ------------------------------------

}
