/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-5 ����09:59:06 
 */  
package xsgzgl.rcsw.wsbz.yy;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhDao;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhForm;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-5-5 ����09:59:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsbzYyAction extends SuperAction<WsbzYyForm, WsbzYyService> {
	private final String WSBZYY ="wsbz";
	private WsbzYyService service = new  WsbzYyService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private static final String url = "rcsw_wsbz_yy.do";
	
	public ActionForward getWsbzYyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsbzYyForm model = (WsbzYyForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "rcsw_wsbz_yy.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	//�����ѯ
	public ActionForward getWsbzJgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsbzYyForm model = (WsbzYyForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "rcsw_wsbz_jg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgcx");
	}
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsbzYyForm model = (WsbzYyForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WsbzYyForm model = (WsbzYyForm) form;
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(WSBZYY);
		//yyzcList ԤԼ�ܴ�List
		List<HashMap<String, String>> yyzcList = service.getYyzcb();
		//yyrqList ԤԼ����List
		List<HashMap<String, String>> yyrqList = service.getYyrqb();
		request.setAttribute("yyzcList", yyzcList);
		request.setAttribute("yyrqList", yyrqList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("fdmclist", service.getFdmcList());
		request.setAttribute("yyrqday", service.produceHdplDay());
		request.setAttribute("zclist", service.getWeekZc());
		request.setAttribute("sqsj", GetTime.getTimeByFormat(DATE_FORMAT));
		String path = "wsbz_yy.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}
	
	public ActionForward udpate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WsbzYyForm myForm = (WsbzYyForm) form;
		WsbzYyForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(WSBZYY);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("fdmclist", service.getFdmcList());
		request.setAttribute("yyrqday", service.produceHdplDay());
		//yyzcList ԤԼ�ܴ�List
		List<HashMap<String, String>> yyzcList = service.getYyzcb();
		//yyrqList ԤԼ����List
		List<HashMap<String, String>> yyrqList = service.getYyrqb();
		request.setAttribute("yyzcList", yyzcList);
		request.setAttribute("yyrqList", yyrqList);
		String hdpl = new WsbzDmwhService().getModel(model.getFddm()).getHdpl();
		String yyrq = "";
		if("2".equals(hdpl)){
			//yyrq = service.produceHdplDay();
			yyrq =model.getYyzc();
			//request.setAttribute("yyrqzc", model.getYyrq());
			request.setAttribute("flag", "z");
		}else{
			yyrq = model.getYyrq();
			request.setAttribute("flag", "t");
		}
		request.setAttribute("syrs", service.getSyrs(model.getFddm(), yyrq));
		request.setAttribute("fdmcinfo", service.getFdmcInfo(model.getFddm()));
		request.setAttribute("jg", model);
		
		HashMap<String, String> datamap = service.getFdmcInfo(model.getFddm());
		request.setAttribute("syrs", service.getSyrs(model.getFddm(), model.getYyrq()));
		request.setAttribute("fd", datamap);
		String path = "wsbz_yy.do?method=update";
		request.setAttribute("path", path);
		request.setAttribute("yyrq", yyrq);
		request.setAttribute("hdpl", hdpl);
		return mapping.findForward("udpae");
	}
	
	public ActionForward saveData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WsbzYyForm myForm = (WsbzYyForm) form;
		WsbzDmwhForm dmwhform = new WsbzDmwhService().getModel(myForm.getFddm());
		User user = getUser(request);
		String message = "";
		boolean flag = true;
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		myForm.setSqly(StringEscapeUtils.escapeHtml4(myForm.getSqly()));
		if("add".equals(myForm.getType())){
			if(!service.isNotOverRssx(myForm.getFddm(), dmwhform.getRs(), myForm.getYyrq(),dmwhform.getHdpl())){
				 message = "���������������"+dmwhform.getRs()+"!";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			flag = service.isEveryXhTwoRecode(myForm.getXh());
			if(!flag){
				 message = "ÿ��ѧ��ֻ��ԤԼ���Σ�";
				 response.getWriter().print(getJsonMessage(message));
				 return null;
			}
			if(!service.isNotSameTwo(myForm.getFddm(), myForm.getYyrq(),myForm.getXh())){
					 message = "���Ѿ��ڸ�ԤԼ����ԤԼ���÷ֶӣ�";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
			}else{
				if(StringUtils.isNull(myForm.getYyrq()) || myForm.getYyrq().trim().length() == 0){
					 message = "ԤԼʱ�䲻��Ϊ�գ�";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
				}else {//
					boolean isflag = service.isExist(myForm.getYyrq());;
					if (!isflag) {
						message = "ԤԼʱ�䲻���ڣ�";
						 response.getWriter().print(getJsonMessage(message));
						 return null;
					}
				}
				if(myForm.getYyrq().indexOf("��") != -1){//myForm.getYyrq().indexOf("��") != -1
					myForm.setYyzc(myForm.getYyrq());
					myForm.setYyrq(null);
				}
				flag = service.runInsert(myForm);
			}
		    
		}else{
			//��ѯ���ݿ�ԭ�����������
			WsbzYyForm dbform = service.getModel(myForm.getSqid());
			WsbzDmwhForm dbdmform = new WsbzDmwhDao().getModel(dbform.getFddm());
			//����
			String fddm = dbform.getFddm();
			String yyrq = "1".equals(dbdmform.getHdpl()) ? dbform.getYyrq():dbform.getYyzc();
			if(!fddm.equals(myForm.getFddm()) || !yyrq.equals(myForm.getYyrq())){
				flag = service.isNotOverRssx(myForm.getFddm(), dmwhform.getRs(), myForm.getYyrq(),dmwhform.getHdpl());
				 if(!flag){
					 message = "���������������"+dmwhform.getRs()+"!";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
				 }
				 if(!service.isNotSameTwo(myForm.getFddm(), myForm.getYyrq(),myForm.getXh())){
					 message = "���Ѿ��ڸ�ԤԼ����ԤԼ���÷ֶӣ�";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
				}
			     
			}
//			else{
//				flag = service.isNotOverRssx(myForm.getFddm(), dmwhform.getRs(), myForm.getYyrq(),myForm.getXh());
//			}
			if(StringUtils.isNull(myForm.getYyrq()) || myForm.getYyrq().trim().length() == 0){
				 message = "ԤԼʱ�䲻��Ϊ�գ�";
				 response.getWriter().print(getJsonMessage(message));
				 return null;
			}else {
				boolean isflag = service.isExist(myForm.getYyrq());;
				if (!isflag) {
					message = "ԤԼʱ�䲻���ڣ�";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
				}
			}
			if(myForm.getYyrq().indexOf("��") != -1){
				myForm.setYyzc(myForm.getYyrq());
				myForm.setYyrq(null);
				service.updateYyrqdaynull(myForm.getSqid());
			}else{
				service.updateYyzcnull(myForm.getSqid());
			}
			flag = service.runUpdate(myForm);
		}
		 message = flag ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}
	
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WsbzYyForm myForm = (WsbzYyForm) form;
		WsbzYyForm model = service.getModel(myForm);
		if(null!=model){
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(WSBZYY);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jg", model);
		WsbzDmwhForm dwmwhform =  new WsbzDmwhService().getModel(model.getFddm());
		request.setAttribute("fd",dwmwhform);
		String yyrq = "";
		if(dwmwhform.getHdpl().equals("1")){
			request.setAttribute("yyrq",model.getYyrq());
			yyrq = model.getYyrq();
		}else{
			request.setAttribute("yyrq",model.getYyzc());
			yyrq = model.getYyzc();
		}
		request.setAttribute("syrs", service.getSyrs(model.getFddm(), yyrq));
		return mapping.findForward("ck");
	}
	
	public ActionForward isHaveQx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xh = getUser(request).getUserName();
		String type = request.getParameter("type");
		//ԤԼ����
		String sqsj = request.getParameter("sqsj");
		boolean flag = true;
		//�ܴκ���Ϊ�Ƶ�ʵķֿ��жϣ�����ֻ���޸İ�ť����
		flag = service.updateYyTimeCheck(sqsj);
		String message = "";
		if(!flag && "update".equals(type)){
			 message = "�������޸�ʱ�䣡";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}else{
			if(type.equals("add")){
				flag = service.isEveryXhTwoRecode(xh);
				if(!flag){
					message = "�������������";
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				flag = service.isNowDateHaveYyjl(xh);
				if(!flag){
					message = "����������ԤԼ���룡";
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				message = "";
			}
			 message = "1";
			 response.getWriter().print(getJsonMessage(message));
			 return null;
		}
	}
	
	public ActionForward fdmcChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		String fddm = request.getParameter("fddm");
		String yyrq = request.getParameter("yyrq");
		HashMap<String, String> datamap = new HashMap<String, String>();
		datamap = service.getFdmcInfo(fddm);
		if("1".equals(datamap.get("hdpl")) || yyrq.indexOf("��") != -1){
			String syrs = service.getSyrs(fddm, yyrq);
			datamap.put("syrs", syrs);
			datamap.put("syrsflag","1" );
		}else{
			String syrs = service.getSyrs(fddm, yyrq);
			datamap.put("syrs", syrs);
			datamap.put("syrsflag","0" );
		}
		JSONObject json = JSONObject.fromObject(datamap); 
		response.getWriter().print(json);
		return null;
	}
	
	public ActionForward zcChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String fddm = request.getParameter("fddm");
		String yyrq = request.getParameter("yyrq");
		HashMap<String, String> datamap = new HashMap<String, String>();
		datamap = service.getFdmcInfo(fddm);
		String syrs = service.getSyrs(fddm, yyrq);
		datamap.put("syrs", syrs);
		JSONObject json = JSONObject.fromObject(datamap); 
		response.getWriter().print(json);
		return null;
	}
	/**
	 * 
	 * @������ɾ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-10 ����09:54:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
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
	 * @����:ѧ��ɾ��Ȩ���ж�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-11 ����11:26:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward isHaveQxDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//ԤԼ����
		String sqsj = request.getParameter("sqsj");
		boolean flag = true;
		//�ܴκ���Ϊ�Ƶ�ʵķֿ��жϣ�����ֻ���޸İ�ť����
		String message = "true";
		flag = service.updateYyTimeCheck(sqsj);
		if(!flag){
			 message = "������ɾ��ʱ�䣡";
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
}
