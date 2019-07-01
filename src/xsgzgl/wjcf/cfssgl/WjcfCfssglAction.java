package xsgzgl.wjcf.cfssgl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * 
* 
* �����ƣ�WjcfJcszAction 
* ��������Υ�ʹ������߹���Action
* �����ˣ�yijd 
* ����ʱ�䣺2012-6-19 ����09:20:00 
* �޸ı�ע��  
* @version 
*
 */
public class WjcfCfssglAction extends BasicAction {
	
	/**
	 * Υ�ʹ���  ��������ά����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		
		service.initPage(rForm, model, user, request);
		//ҳ����ת��ַ
		request.setAttribute("path", "cfsswhCx.do");
		
		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		return mapping.findForward("success");
	}
	
	/**
	 * Υ�ʹ��������첽����  ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhCxSj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		


		// ============= ��ʼ����������ֵ ============
		service.initPage(rForm, model, user, request);
		//ҳ����ת��ַ
		request.setAttribute("path", "cfsswhCx.do");
		//WjcfCfsbInterface service = myService.getWjcfCfsbService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setPath("cfsswhCx.do");
		model.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getTopTr(model, user,"sscx");
		// �����
		List<String[]> rsArrList = service.cfssglCx(model,user);
		// ���������
		String html = service.createTableHTML(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList((ArrayList<String[]>)rsArrList);
		rsModel.setSpHtml(html);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================
		
		return null;
	}
	
	/**
	 * ��������ά�� �Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cfsswhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);

		List<HashMap<String,String>> resultList = service.cfssglCxExport(model,user);				
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * Υ�ʹ���  ��������ά���鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		RequestForm rForm = new RequestForm();
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "cfsswhCx.do");
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("rs", service.cfssglCk(pkValue));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cfshxxList", service.ssshxxCk(pkValue));
		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		return mapping.findForward("view");
	}
	
	/**
	 * Υ�ʹ���  ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
	
		if("save".equals(doType)){
			boolean result = false;
			
			 result=service.cfssglGx(model);
			
			 if (result){
				 //=====���칤������==2013-1-16==qph=====begin========
				String id = model.getCfid();
				HashMap<String,String> map = service.cfssglCk(id);
				String xh = map.get("xh");
				String[] spgw = service.getSsshSpgw();
				if (null != spgw && spgw.length > 0){
					Job job = Job.getJobInstance(id, xh, spgw[0], 
							"cfssshCx.do?xtgwid="+spgw[0], 
							"cfsswhCx.do","��������", "��������");
					MyJobsManager manager = new MyJobsImpl();
					manager.pushJob(job);
					
				}
				 //=====���칤������==2013-1-16==qph=====end==========
			 }
			 
			request.setAttribute("result",result);
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cfid", model.getCfid());
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "cfsswhCx.do");
		FormModleCommon.commonRequestSet(request);
		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		
		return mapping.findForward("add");
	}
	
	
	/**
	 * Υ�ʹ���  ���������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		request.setAttribute("cfid",model.getCfid());
		if("save".equals(doType)){
			boolean result = false;
			
			 result=service.cfssglGx(model);
			 
			request.setAttribute("result",result);
		}

		request.setAttribute("rs", service.cfssglCk(model.getCfid()));
		return mapping.findForward("edit");
	}
	
	
	/**
	 * ��ѯ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxCfsswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm)form;
		
		String cfid = request.getParameter("cfid");
		model.setCfid(cfid);
		HashMap<String, String> result = service.cxCfsswh(model);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(JSONArray.fromObject(result));
		return null;
	}
	
	
	/**
	 * Υ�ʹ��� �������߸�������
	 * 
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssfjXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm)form;
		//response.setHeader("Content-Disposition", "attachment;filename=aa.doc");
//		response.setContentType("application/msword");
//		service.cfssfjXz(model, response.getOutputStream());
//		return mapping.findForward("");
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(model.getSsfjmc()));
		InputStream in = service.fjCx(model);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}
	
	/**
	 * Υ�ʹ��� ���ָ�������
	 * 
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cffjXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm)form;
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(model.getSsfjmc()));
		InputStream in = service.cffjCx(model);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
		
	}
	
	/**
	 * �������
	 */
	public ActionForward expCfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		model.getPages().setPageSize(Integer.MAX_VALUE);
		User user = getUser(request);// �û�����
		String[] ColumnNameCN=new String[]{"ѧ��","����","ѧ��","ѧ��","�����������","����ԭ������","�����ĺ�","����ʱ��","�������״̬"};
		String[] ColumnName=new String[]{"xh","xm","xn","xq","cflbmc","cfyymc","sswh","sssj","ssshzt"};
		List<String[]> rs = service.cfssglCx(model,user);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, ColumnName, ColumnNameCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * Υ�ʹ��ֳ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssglSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		
		boolean result=service.cfssSc(pkValue);
		
		if(result){
			//====���칤��==2013-1-16===qph====begin======
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(new String[]{pkValue}, "��������");
			//====���칤��==2013-1-16===qph====end========
		}
		
		request.setAttribute("result", result);//����������
		
		cfsswhCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	
	/**
	 * Υ�ʹ���������� �б�  ҳ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		List<HashMap<String, String>> spgwList=service.spgwCx(user.getUserName());
		//ҳ����ת��ַ
		request.setAttribute("path", "cfssshCx.do");
		service.initPage(rForm, model, user, request);
		request.setAttribute("spgwList", spgwList);
		request.setAttribute("isZgjyh", service.isZgjyh(user));
		if(null==spgwList||spgwList.size()==0){
				String msg = "��ģ��û�и��û���������λ����ȷ�ϣ�";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
		}else{
			// ============= ����request��ֵ =============
			CommForm commModel = new CommForm();
			service.setRequestValue(rForm, request);
			service.setList(commModel, rForm, request);
			return mapping.findForward("success");
		}
	}
	
	/**
	 * Υ�ʹ���������� �б�  ���ݼ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshCxSj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		


		// ============= ��ʼ����������ֵ ============
		service.initPage(rForm, model, user, request);
		//ҳ����ת��ַ
		request.setAttribute("path", "cfssshCx.do");
		//WjcfCfsbInterface service = myService.getWjcfCfsbService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String stylePath=otherValue[1];
		rsModel.setStylePath(stylePath);
		//������Ȩ��
		if(otherValue.length >= 3){
			model.setXtgwid(otherValue[2]);
		}
		
		
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setPath("cfssshCx.do");
		model.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getTopTr(model, user,"ssshcx");
		// �����
		List<String[]> rsArrList = service.cfssshCx(model,user);
		// ���������
		String html = service.createHTMLCfssshCx(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList((ArrayList<String[]>)rsArrList);
		rsModel.setSpHtml(html);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================
		
		return null;
	}
	
	
	/**
	 * ������λ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward spgwCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		
		service.initPage(rForm, model, user, request);
		service.showShgwDiv( user, response);
		//request.setAttribute("spgwList", service.spgwCx(user.getUserName()));
		//ҳ����ת��ַ
		request.setAttribute("path", "cfssshCx.do");
		return null;
	}
	
	
	/**
	 *	������� ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		String xtgwid = Base.chgNull(request.getParameter("xtgwid"), "", 0);
		
		//��ѯ��Ϣ����
		model.setCfid(pkValue);
		model.setXtgwid(xtgwid);
		//���������Ϣbegin
		HashMap<String, String> sjSp=service.splcSjsh(model);
		HashMap<String, String> xjSp=service.splcXjsh(model);
		HashMap<String, String> yjSp=service.splyjCx(model);
		HashMap<String, String> djSp=service.spldjCx(model);
		HashMap<String, String> dqSp=service.ssshCkGjCfidGwjb(model);
		request.setAttribute("splcDjRs", djSp);//�������̶���
		request.setAttribute("splcYjRs", yjSp);//��������һ��
		request.setAttribute("splcSjRs", sjSp);//���������ϼ�
		request.setAttribute("splcXjRs", xjSp);//���������¼�
		//���������Ϣend
		
		request.setAttribute("ssshList", service.ssshCxGjCfid(model));
		request.setAttribute("rsSh", dqSp);
		request.setAttribute("spgwqx", service.spgwQx(sjSp, xjSp, yjSp, djSp, dqSp));
		
		request.setAttribute("rs", service.cfssglCk(pkValue));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xtgwid", xtgwid);
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "cfssshCx.do");
		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		return mapping.findForward("cfssshSh");
		
	}
	

	/**
	 *	����������� ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bccfssshSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		User user=getUser(request);
		boolean sfZgj = false;
		if("save".equals(doType)){
			//���浥���������
			
			if(null!=model.getShyj()){//ת��
				model.setShyj(service.unicode2Gbk(model.getShyj()));
			}
			if(null!=model.getCfggw()&&!"undefined".equals(model.getCfggw())){//ת��
				model.setCfggw(service.unicode2Gbk(model.getCfggw()));
			}
			if(null!=model.getSswh()&&null!=model.getSssj()){
				model.setSswh(service.unicode2Gbk(model.getSswh()));
				sfZgj = true;
			}
			model.setShr(user.getUserName());
			model.setSftj("0");//һ������ǲ��ύ��
			boolean result=service.ssshXg(model);
			
			if (result){
				//====���칤��===2013-1-16==qph===begin========
				String[] id = new String[]{model.getCfid()};
				String curShgw = model.getXtgwid();
				String[] spgw = service.getSsshSpgw();
				int index = StringUtils.getStrIndexInArray(curShgw, spgw);
				
				for (String str : id){
					Job job = null;
					if ("tg".equals(model.getShzt())) {
						String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
						job = Job.getJobInstance(str,nextShgw,
								"cfssshCx.do?xtgwid="+nextShgw,"��������");
					} else if ("th".equals(model.getShzt())){
						String nextShgw = index!=0 ? spgw[index-1] : null;
						job = Job.getJobInstance(str,nextShgw,
								"cfssshCx.do?xtgwid="+nextShgw,"��������");
					} else {
						job = Job.getJobInstance(str,"��������");
					}
					MyJobsManager manager = new MyJobsImpl();
					manager.updateJob(job);
				}
				//====���칤��===2013-1-16==qph===end==========
			}
			
			
			
			request.setAttribute("result", result);
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(sfZgj);
		
		return null;
	}
	
	/**
	 *	�������  ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		String xtgwid = Base.chgNull(request.getParameter("xtgwid"), "", 0);
		
		String[] pks=null;
		if(pkValue!=null && !"".equals(pkValue)){
			pks=pkValue.split("@@");
		}
		//��ѯ��Ϣ����
		//model.setCfid(pkValue);
		model.setCfid(pks[0]);
		model.setXtgwid(xtgwid);
		//���������Ϣbegin
		HashMap<String, String> sjSp=service.splcSjsh(model);
		HashMap<String, String> xjSp=service.splcXjsh(model);
		HashMap<String, String> yjSp=service.splyjCx(model);
		HashMap<String, String> djSp=service.spldjCx(model);
		HashMap<String, String> dqSp=service.ssshCkGjCfidGwjb(model);
		request.setAttribute("splcDjRs", djSp);//�������̶���
		request.setAttribute("splcYjRs", yjSp);//��������һ��
		request.setAttribute("splcSjRs", sjSp);//���������ϼ�
		request.setAttribute("splcXjRs", xjSp);//���������¼�
		//���������Ϣend
		
		//request.setAttribute("ssshList", service.ssshCxGjCfid(model));
		request.setAttribute("rsSh", dqSp);
		request.setAttribute("spgwqx", service.spgwQx(sjSp, xjSp, yjSp, djSp, dqSp));
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xtgwid", xtgwid);
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "cfssshCx.do");
		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		return mapping.findForward("cfssshPlsh");
	}
	
	/**
	 *	�������  ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bccfssshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		User user=getUser(request);
		boolean sfZgj = false;
			if(null!=model.getSswh()){
				sfZgj = true;
			}
			if(null!=model.getShyj()){//ת��
				model.setShyj(service.unicode2Gbk(model.getShyj()));
			}
			if(null!=model.getCfggw()&&!"undefined".equals(model.getCfggw())){//ת��
				model.setCfggw(service.unicode2Gbk(model.getCfggw()));
			}
			if(null!=model.getSswh()){
				model.setSswh(service.unicode2Gbk(model.getSswh()));
			}
			//���������������
			model.setShr(user.getUserName());
			model.setSftj("0");//һ������ǲ��ύ��
			boolean result=service.ssshPlxg(model, pkValue);
			request.setAttribute("result", result);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(sfZgj);
		
		return null;
	}
	
	/**
	 *	�����ύ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		if("tj".equals(doType)){
			boolean result=service.ssshTjWjcf();
			request.setAttribute("result", result);
		}
		request.setAttribute("shtjList", service.ssshShjgTj());
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "cfssshCx.do");
		return mapping.findForward("cfssshTj");
	}
	
	/**
	 *	�����ύ��������˺�ֱ���ύ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshZjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		boolean result=service.ssshTj(pkValue);
		request.setAttribute("result", result);
		request.setAttribute("shtjList", service.ssshShjgTj());
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "cfssshCx.do");
		return mapping.findForward("cfssshTj");
	}
	
	// ������������
	public ActionForward showCflbDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ============= ��ʼ����������ֵ ============
		WjcfCfssglServices service=new WjcfCfssglServices();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���

		if (Base.isNull(message)) {
			service.showCflbDiv(response);
		}
		return null;
	}
	
}
