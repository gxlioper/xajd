/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjsq;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.szdw.general.dwwh.DwwhService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class JspjsqAction extends SuperAction{
	private static final String url = "xg_dekt_jspjglsq.do";
	private JspjsqService service = new JspjsqService();
	
	@SystemAuth(url = url)
	public ActionForward jspjSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JspjsqForm model = (JspjsqForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())){

            //���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
	
		return mapping.findForward("jspjcx");
		
	}
	
	
	/**
	 * @description	�� ��������
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-19 ����11:37:54
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			JspjsqForm model = (JspjsqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		if (SAVE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
        		boolean result = service.saveJspj(model);
        		String messageKey = "";
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		
		String zgh = request.getParameter("zgh");
		HashMap<String, String> jsList = new HashMap<String,String>();
		if(!StringUtil.isNull(zgh)){
			jsList.putAll(service.getFdyInfo(zgh));
	 	}
		request.setAttribute("jsList", jsList);
		//ѧ�� ѧ��
		request.setAttribute("lx", request.getParameter("lx")==null?"0":request.getParameter("lx"));
		if (!StringUtil.isNull(model.getPjjszgh())) {
			request.setAttribute("jszgh", model.getPjjszgh());
			request.setAttribute("jsxm", URLDecoder.decode(model.getPjjsxm(),"UTF-8"));
		}
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("rstjList", service.getRstjList());
		String path = "dekt_jspjglsq.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("jspjzj");
	}
	@SystemAuth(url = url)
	public ActionForward addyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JspjsqForm model = (JspjsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		String zgh = request.getParameter("pjjszgh");
		//request.setAttribute("zzshen", model.getPjjszgh());
		JspjsqService service = new JspjsqService();
		boolean flag = service.xssqyy(zgh,model.getXh(),model.getZzshen());
		System.out.println(flag);
		return mapping.findForward("jspjzj");
	}

	@SystemAuth(url = url)
	public ActionForward deladd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JspjsqForm model = (JspjsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		String zgh = request.getParameter("pjjszgh");
		//request.setAttribute("zzshen", model.getPjjszgh());
		JspjsqService service = new JspjsqService();
		boolean flag = service.deladd(zgh,model.getXh());
		String messageKey = flag ? MessageKey.SYS_OPERATE_SUCCESS : MessageKey.SYS_OPERATE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� �޸�
	 * @author 		��CP��1352��
	 * @date 		��2018-1-19 ����03:47:28
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			JspjsqForm model = (JspjsqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
		if (UPDATE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.updateJspj(model);
        		String messageKey = "";
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		//ѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		JspjsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jsxm", service.getfdyxm(updateForm.getPjjszgh()));
		String path = "dekt_jspjglsq.do?method=update";
		request.setAttribute("rstjList", service.getRstjList());
		request.setAttribute("path", path);
		return mapping.findForward("jspjxg");
	}
	
	/**
	 * 
	 * @description	�� ɾ��
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-19 ����03:47:15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteJspj(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * @description	�� �ύ����
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-19 ����03:54:31
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JspjsqForm model = (JspjsqForm) form;
		String sqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setSqid(sqid);
		model.setXh(xh);
		//�ж��ύʱ����Ƿ񿪷�
		boolean result = service.submitJspj(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� ��������
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-19 ����03:56:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelJspj(sqid,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			JspjsqForm model = new JspjsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateCxJspj(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� �鿴
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-19 ����05:17:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JspjsqForm model = (JspjsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//��ѯ������Ϊ��Ϣ���
		HashMap<String,String> gfqkMap = service.getJspjInfo(model);
		request.setAttribute("rs", StringUtils.formatData(gfqkMap));
		//ѧ��������Ϣ��ʾ����
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("jspjck");
	}
	
	
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JspjsqForm model = (JspjsqForm) form;
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;	
	}
	
	public ActionForward jspjyy_10698(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		request.setAttribute("zgh", request.getParameter("zgh"));
		request.setAttribute("xm", request.getParameter("xm"));
		request.setAttribute("zzshen", request.getParameter("zzshen"));
		return mapping.findForward("jspjyy_10698");
	}
	public ActionForward jspjSave_10698(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zgh = request.getParameter("zgh");
		String zzshen = request.getParameter("zzshen");
		String xsyyxx = request.getParameter("xsyyxx");
		//DwwhService service = new DwwhService();
		JspjsqForm model = (JspjsqForm) form;
		User user = getUser(request);
		JspjsqService service = new JspjsqService();
		boolean flag = service.jspjSzSave(zgh, user.getUserName(), zzshen, xsyyxx);
		String message = flag ? "����ɹ���":"����ʧ�ܣ�";
		request.setAttribute("message", message);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	
	
	
	
	
	
	
}
