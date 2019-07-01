package xgxt.xszz.whtl.ybbx;

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

import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.xsxx.byjd.gdjs.XsxxByjdForm;
import xsgzgl.xsxx.byjd.gdjs.XsxxByjdInit;

import com.zfsoft.basic.BasicAction;

public class XszzYbbxAjax extends BasicAction{
	
	XszzYbbxService service = new XszzYbbxService();
	
	/**
	 * ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ybbxcxSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XszzYbbxForm myForm=(XszzYbbxForm)form;
		
		XszzYbbxInit init=new XszzYbbxInit();
		
		BasicModel model = new BasicModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		model.setColList(new String[]{"pkValue","xh","xm", "nj",  "bjmc","xysh","xxsh"});
		model.setPath("xszz_ybbx_cx.do");
		init.initYbbxcx(rForm, model, request,user);
		
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
		
		
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		
		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),user,"xszz_ybbx_cx.do");
		
		
		// �����
		ArrayList<String[]> rsArrList =(ArrayList<String[]>)service.getYbbxcxList(model);
		
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
	 * ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ybbxshSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XszzYbbxForm myForm=(XszzYbbxForm)form;
		
		XszzYbbxInit init=new XszzYbbxInit();
		
		BasicModel model = new BasicModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		model.setPath("xszz_ybbx_sh.do");
		init.initYbbxsh(rForm, model, request, user);
		
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
		
		model.setUser(user);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		
		// ����
		
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),user,"xszz_ybbx_sh.do");
		
		
		// �����
		ArrayList<String[]> rsArrList =(ArrayList<String[]>)service.getYbbxshList(model);
		
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
	 * ����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel model=new BasicModel();
		
		model.setPk(new String[]{"xn","xh"});
		
		String pkStr=service.unicode2Gbk(request.getParameter("pkValue"));
		
		
		String[]pkValue=pkStr.split("!!array!!");
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		// --------------����------------
		model.setTableName("xg_xszz_ybbxsqb");
	
		//����
		model.setPkValue(pkValue);
		
		flag=service.checkInfo(model);
		if(!flag){
			
			message="��ѡ��¼�д�������˲������޷�ɾ����";
		}
		
		//����ɾ��
		if(flag){
			flag=service.batchDelete(model);
		}
		//ɾ����Ч����
		if(flag){
			flag=service.deleteYbbxInfo();
		}
		
		if(Base.isNull(message)){
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	
	public ActionForward dgsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		XszzYbbxInit init=new XszzYbbxInit();
		
		// ------ͨ�����췽����ʼ�� XszzYbbxSaveModel��BasicModel������-------------
		XszzYbbxSaveModel saveModel=new XszzYbbxSaveModel();
		
		init.initDgshInfo(saveModel, model, request, user);
		

		service.trimZd(saveModel, new String[]{"mzyy","bxje","wzsj","ylyt","shje"});
		
		boolean flag=service.saveBatch(model,saveModel);
		
		// ---------------------�̶��ı���ֵ end--------------
		init.initShInfo(saveModel, model, request, user);
		
		flag=service.updateInfo(model);
		
		//��Ϣ��Ϣ
		String message="";
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	public ActionForward plsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		model.setUser(user);
		XszzYbbxInit init=new XszzYbbxInit();
		
		// ------ͨ�����췽����ʼ�� XszzYbbxSaveModel��BasicModel������-------------
		XszzYbbxSaveModel saveModel=new XszzYbbxSaveModel();
		
		init.initPlsh(saveModel, model, request, user);
		
		boolean flag=service.plsh(model);
		
		// ---------------------�̶��ı���ֵ end--------------
	
		//��Ϣ��Ϣ
		String message="";
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������˱���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBatch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		XszzYbbxForm myForm=(XszzYbbxForm)form;
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		// ------ͨ�����췽����ʼ�� XszzYbbxSaveModel��BasicModel������-------------
		XszzYbbxSaveModel saveModel=new XszzYbbxSaveModel(request,user,model);
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		model.setTableName("xg_xszz_ybbxsqb");
		flag=service.checkInfo(model);
		
		if(flag){
			model.setTableName("xg_xszz_ybbxxxb");
			flag=service.saveBatch(model,saveModel);

			
			model.setTableName("xg_xszz_ybbxsqb");
			
			myForm.setPkValue(model.getPkValue()[0]);
			
			HashMap<String,String>ybbxSqbInfo=service.getYbbxSqbInfo(myForm);
			
			if(ybbxSqbInfo==null || ybbxSqbInfo.size()==0){
				service.saveInfo(model);
			}
		}else {
			message="�ü�¼������˲������޷��޸ģ�";
		
		}
		
		if(Base.isNull(message)){
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}

}
