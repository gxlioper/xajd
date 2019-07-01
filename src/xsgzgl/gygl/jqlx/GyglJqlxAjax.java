package xsgzgl.gygl.jqlx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.hzny.HznyXljkZxzxForm;
import xgxt.xljk.hzny.HznyXljkZxzxInit;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;
import xsgzgl.rcsw.zjbb.RcswZjbbDAO;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-20 ����02:05:02</p>
 */
public class GyglJqlxAjax extends BasicAction {
	
	GyglJqlxService service = new GyglJqlxService();
	
	GyglJqlxInit init =new GyglJqlxInit();
	
	/**
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveLxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initLxszSave(model, request);
		
		
		flag=service.batchDelete(model);
		
		if(flag){
		
			flag = service.saveInfo(model);
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
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] {"id"});

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_gygl_jqlxsqb");

		// ����
		model.setPkValue(pkValue);

		// ����ɾ��

		flag = service.batchDelete(model);
		
		if(flag){
			model.setPk(new String[] {"sqid"});
			model.setTableName("xg_gygl_jqlxshb");
			service.batchDelete(model);
			
			//=====ɾ������===2013-1-15=====qph====begin===========
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(pkValue, "������У");
			//=====ɾ������===2013-1-15=====qph====end==============
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jqlxsqSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglJqlxForm myForm = (GyglJqlxForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initLxsqSearch(rForm, model, request, user);

		// �������ʾ�ֶ�
		String[]otherValue=request.getParameter("otherValue").split("!!@@!!");

		String ie=otherValue[0];
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
				user, model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getLxsqList(model);

		// ���������
		String spHtml = service
				.createlxsQSearchHTML(rsModel, model, rsArrList, user);

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
	 * ������У��˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jqlxshSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglJqlxForm myForm = (GyglJqlxForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		model.setColList(new String[] { "id","xh","xm","nj","bjmc","sqsj","shzt" });
		
		model.setPath("jqlx_lxsh.do");
		init.initLxshSearch(rForm, model, request, user);

		// �������ʾ�ֶ�

		String[]otherValue=request.getParameter("otherValue").split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String stylePath=otherValue[1];
		rsModel.setStylePath(stylePath);
		
		// 
		String spgw = otherValue[2];
		model.getValueMap().put("spgw", spgw);

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
				user, "jqlx_lxsh.do");

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getLxshList(model);

		// ���������
		String spHtml = service
				.createLxshSearchHTML(rsModel, model, rsArrList, user);

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
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jqlxjgSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglJqlxForm myForm = (GyglJqlxForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		model.setColList(new String[] { "id", "xh", "xm", "nj", "yjzxsj",
				"sqsj", "sqjg" });
		
		init.initLxjgSearch(rForm, model, request, user);

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
				user,model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getLxjgList(model);

		// ���������
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

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
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyglJqlxForm myForm = (GyglJqlxForm) form;
		
		service.setJbszInfo(myForm);
		
		User user=getUser(request);

		BasicModel model = new BasicModel();
		
		model.setUser(user);

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initLxsqSave(model, request);
		
		
		flag = service.saveInfo(model);
		
		if(flag){
		
			HashMap<String,String> map = model.getValueMap();
			String id=map.get("id");
			
			if(!"no".equalsIgnoreCase(myForm.getLcid()))
			flag = service.saveJqlxShb(id,user);
			
			
			//========���칤��  2013-1-15 qph beign=========
			String xh = map.get("xh");
			String lcid = map.get("lcid");
			
			String[] spgw = service.getSpgwByLcid(lcid);
			
			if (null != spgw && spgw.length > 0){
				Job job = Job.getJobInstance(id, xh, spgw[0], 
						"gyglnew_jqlx.do?method=lxshManage&spgw="+spgw[0], 
						"jqlx_lxjg.do","������У", "������У");
				MyJobsManager manager = new MyJobsImpl();
				manager.pushJob(job);
			}
			//========���칤��  2013-1-15 qph end=========
			
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
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BasicModel basicModel=new BasicModel();
		
		GyglJqlxForm myForm = (GyglJqlxForm) form;
		
		init.initJqlxSh(basicModel, request);

		User user = getUser(request);// �û�����
	
		// ============= end ============

		String message="";
		
		boolean flag = false;
	
		flag = service.updateShzt(myForm,basicModel,request, user);
		message = flag ? "��˳ɹ�" : "���ʧ�ܣ�����ϵ�����Ա";
	
		if (flag){
			//===���칤��=====2013-1-15 qph==beign======
			
			String[] id = myForm.getSqid();
			String curShgw = myForm.getSpgw();
			String[] spgw = service.getSpgwByLcid(myForm.getLcid());
			int index = StringUtils.getStrIndexInArray(curShgw, spgw);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(myForm.getShzt())) {
					String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"gyglnew_jqlx.do?method=lxshManage&spgw="+nextShgw,"������У");
				}  else {
					job = Job.getJobInstance(str,"������У");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
			
			//===���칤��=====2013-1-15 qph==end=========
		}
		
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������˸�λѡ��DIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showShgwDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		GyglJqlxForm myForm=(GyglJqlxForm)form;
		
		User user = getUser(request);// �û�����

		service.setJbszInfo(myForm);
		// ============= ��ʼ����������ֵ ============
		
		service.showShgwDiv(myForm, user ,response);
			
		return null;
	}
	
	/**
	 * ������˸�λѡ��DIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkChangeShlc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		GyglJqlxForm myForm=(GyglJqlxForm)form;
		
		String lcid=request.getParameter("lcid");
		
		service.setJbszInfo(myForm);
		// ============= ��ʼ����������ֵ ============
		
		boolean flag=service.checkShjs(lcid);
		
		String message = flag ? "" : "�������������������¼<font color='blue'>δ������,�޷�����</font>�����������̣�";
		
		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);
			
		return null;
	}
}
