package xsgzgl.xsxx.qtxx;

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
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpModel;
import xsgzgl.comm.BasicModel;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyInit;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyModel;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyService;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxPySaveModel;

import com.zfsoft.basic.BasicAction;

public class XsxxQtxxAjax  extends BasicAction {
	
	// ����
	private final String TABLENAME = "xg_xsxx_qtxxb";
	
	// �����ֶ�
	private final String[] SAVE = {"xh","xmdm","zd1","zd2","zd3","zd4",
			"zd5","zd6","zd7","zd8","zd9","zd10","zd11","zd12","zd13",
			"zd14","zd15","zd16","zd17","zd18","zd19","zd20","zd21","zd22",
			"zd23","zd24","zd25","zd26",
			"czr","czsj"};
	
	// ����
	private final String[] pk = {"xh","xmdm"};
	
	XsxxQtxxService service = new XsxxQtxxService();

	XsxxBzrpyInit init = new XsxxBzrpyInit();
	
	/**
	 * ������˱���
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
		
		HashMap<String,String>valueMap=service.getValueMap(request, SAVE);
		
		// ---------------------�̶��ı���ֵ begin------------
		valueMap.put("czr",user.getUserName());
		
		valueMap.put("czsj",GetTime.getNowTime2());
		// ---------------------�̶��ı���ֵ end--------------
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		// --------------����------------
		model.setTableName(TABLENAME);
		// --------------��Ҫ�����ֵ--------------------
		model.setValueMap(valueMap);
		
		flag=service.saveInfo(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �����޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		model.setPk(pk);
		
		HashMap<String,String>valueMap=service.getValueMap(request, SAVE);
		
		// ---------------------�̶��ı���ֵ begin------------
		valueMap.put("czr",user.getUserName());
		
		valueMap.put("czsj",GetTime.getNowTime2());
		// ---------------------�̶��ı���ֵ end--------------
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		// --------------����------------
		model.setTableName(TABLENAME);
		// --------------��Ҫ�����ֵ--------------------
		model.setValueMap(valueMap);
		
		flag=service.updateInfo(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
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
	public ActionForward qtxxSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxQtxxForm myForm=(XsxxQtxxForm)form;
		
		XsxxQtxxInit init = new XsxxQtxxInit();
		
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
		
		String xmdm=valueMap.get("xmdm");

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		
		searchModel.setSearch_tj_xmdm(new String[]{xmdm});
		
		model.setSearchModel(searchModel);
		
		model.setColList(new String[]{"pkValue","xh","xm","nj","xymc","zymc","bjmc"});
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		
		// ����
		List<HashMap<String, String>> topTr = service.getTopTr(model,
				user);
		
		
		// �����
		ArrayList<String[]> rsArrList =(ArrayList<String[]>)service.getXsQtxxList(model);
		
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
		
		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
}
