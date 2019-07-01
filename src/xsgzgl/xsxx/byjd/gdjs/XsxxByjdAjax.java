package xsgzgl.xsxx.byjd.gdjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyInit;

import com.zfsoft.basic.BasicAction;

public class XsxxByjdAjax  extends BasicAction {
	
	// ����
	private final String TABLENAME = "xg_xsxx_byjdb";
	
	// �����ֶ�
	private final String[] SAVE = {"xh","byjd","czr","czsj"};
	
	// ����
	private final String[] pk = {"xh"};
	
	XsxxByjdService service = new XsxxByjdService();

	XsxxBzrpyInit init = new XsxxBzrpyInit();
	
	/**
	 * ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		XsxxByjdSaveModel saveModel=new XsxxByjdSaveModel();
	
		model.setPk(pk);
	
		service.getModelValue(saveModel, request);
		
		// ---------------------�̶��ı���ֵ begin------------
		saveModel.setCzr(user.getUserName());
		saveModel.setCzsj( GetTime.getNowTime2());
		
		// ---------------------�̶��ı���ֵ end--------------
		model.setPkValue(saveModel.getPkValue());
		
		model.setOneZd(new String[]{"byjd","czr","czsj"});
		
		model.setArrayZd(new String[]{"xh"});
		
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		// --------------����------------
		model.setTableName(TABLENAME);
		// --------------��Ҫ�����ֵ--------------------
		flag=service.saveBatch(model,saveModel);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	
	/**
	 * ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byjdSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxByjdForm myForm=(XsxxByjdForm)form;
		
		XsxxByjdInit init = new XsxxByjdInit();
		
		BasicModel model = new BasicModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initXsqtxx(rForm, myForm, user, request);
		
		// �������ʾ�ֶ�
		
		HashMap<String,String>valueMap=service.getValueMap(request,new String[]{});
		
		
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
		
		model.setColList(new String[]{"pkValue","xh","xm","nj","xymc","zymc","bjmc","sfyjd"});
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		
		// ����
		
		List<HashMap<String, String>> topTr = service.getTopTr(model,
				user);
		
		
		// �����
		ArrayList<String[]> rsArrList =(ArrayList<String[]>)service.getByjdList(model);
		
		// ���������
		String spHtml = service.createSearchHTML(rsModel, model, rsArrList,
				user);
		
		// ����ѧУ���Ի���Ϣ������
		//spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	 * ��ʾ��ϸ��ϢDIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDetailDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxByjdForm myForm=(XsxxByjdForm)form;
		
		User user = getUser(request);// �û�����

		// ==================����ǰ̨ҳ��========================
		service.showDetailDiv(myForm, user, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ��ʾ��ϸ��ϢDIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showByjdDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxByjdForm myForm=(XsxxByjdForm)form;
		
		User user = getUser(request);// �û�����

		// ==================����ǰ̨ҳ��========================
		service.showByjdDiv(myForm, user, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	
	
}
