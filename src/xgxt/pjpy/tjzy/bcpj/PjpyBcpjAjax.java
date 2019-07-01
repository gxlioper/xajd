package xgxt.pjpy.tjzy.bcpj;

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
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpModel;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

public class PjpyBcpjAjax extends BasicAction {

	// ����
	private final String TABLENAME = "xg_pjpy_pjxmsqb";

	// ����
	private final String[] pk = {"pjxn","pjxq","pjnd","xmdm","xh"};
	
	PjpyBcpjService service = new PjpyBcpjService();

	PjpyBcpjInit init = new PjpyBcpjInit();

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
		
		model.setPk(pk);
		
		String pkStr=service.unicode2Gbk(request.getParameter("pkValue"));
		
		
		String[]pkValue=pkStr.split("!!array!!");
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		// --------------����------------
		model.setTableName(TABLENAME);
	
		//����
		model.setPkValue(pkValue);
		
		//����ɾ��
		flag=service.batchDelete(model);
		//ɾ����Ч����
		service.deletePjxmInfo();
		
		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}

	/**
	 * ��ѯ�ۺϲ������۲���Ϣ��ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZhcpZcxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initZhcp(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// ��Ŀ����
		String xmdm = otherValue[1];
		model.setXmdm(xmdm);
		
		// ��Ŀ����
		String xmmc = otherValue[2];
		
		// ��Դ��
		String lyb =otherValue[3];
		model.setLyb(lyb);
		
		//��Ŀ����
		String xmjb =otherValue[4];
		model.setXmjb(xmjb);
		
		if(!Base.isNull(xmmc)){
			model.setXmmc(myService.unicode2Gbk(xmmc));
		}
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		
		// ����
		List<HashMap<String, String>> topTr = service.getZhcpZcxxTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getZhcpZcxxList(myForm, model,
				user);
		// ���������
		String spHtml = service.createZhcpZcxxHTML(rsModel, model, rsArrList,
				user);
		
		// ����ѧУ���Ի���Ϣ������
		spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
}
