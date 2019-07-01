package xgxt.pjpy.comm.pjpy.pjlc.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.xljk.hzny.HznyXljkZxzxForm;
import xgxt.xljk.hzny.HznyXljkZxzxInit;
import xgxt.xszz.whtl.ybbx.XszzYbbxForm;
import xgxt.xszz.whtl.ybbx.XszzYbbxSaveModel;
import xsgzgl.comm.BasicModel;

public class PjpyQtxxAjax extends BasicAction{
	
	PjpyQtxxService service = new PjpyQtxxService();
	
	PjpyQtxxInit init = new PjpyQtxxInit();
	
	// -------------------------��ѯʦ��Ϣ���� begin ------------------------------------
	/**
	 * ��ѧ�ɲ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qtxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyQtxxForm myForm = (PjpyQtxxForm) form;

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		
		myForm.getSearchModel().setPath("pjpy_qtxx_qtjl.do");
		
		init.initQtxxManage(rForm, request);

		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);

		// =================== end ===================
	    
		List<HashMap<String, String>> topTr = service.getTopTr();

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getQtxxList(myForm);

		// ���������
		String spHtml = service.createSearchHTML(rsModel,rsArrList, user);

		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);

		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ����ɾ��ѧ�ɲ���Ϣ
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

		model.setPk(new String[] { "xn","xh"});

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_pjpy_qtjlb");

		// ����
		model.setPkValue(pkValue);


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
	
	/**
	 * ��˱���
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
		
		//PjpyQtxxForm myForm=(PjpyQtxxForm)form;
		
		User user=getUser(request);
		
		BasicModel basicModel=new BasicModel();
		
		QtxxSaveModel saveModel=new QtxxSaveModel();
		
		// ------ͨ�����췽����ʼ�� XszzYbbxSaveModel��BasicModel������-------------
		init.initSaveBatch(basicModel, saveModel, request);
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		flag=service.saveBatch(basicModel,saveModel);

		if(Base.isNull(message)){
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
}
