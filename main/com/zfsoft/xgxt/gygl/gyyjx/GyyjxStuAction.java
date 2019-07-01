/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-24 ����03:45:11 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.cwgl.CwglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-24 ����03:45:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyyjxStuAction extends SuperAction {

	private static BdpzService bdpzService = new BdpzService();
	
	private static List<HashMap<String,String>> jbxxList = null;
	
	static{
		jbxxList = bdpzService.getJbxxpz("gyglyjx");
	}
	
	
	
	
	public ActionForward listStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		User user = getUser(request);
		
		/*if(!"stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ��ֻ����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}*/
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_yjxstugl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("listStu");
	}
	
	
	public ActionForward listGl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_jxjggl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("listGl");
	}
	
	/**
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
	
	public ActionForward actionNavi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		User user = getUser(request);
		String type = model.getType();
		String mappingUri = "gyyjxStuAddUpdate";
		request.setAttribute("actionType", type);
		
		if(StringUtils.equals("update", type)){
			GyyjxForm dataModel = service.getModel(model.getGyyjid());
			xgxt.utils.String.StringUtils.formatData(dataModel);
			BeanUtils.copyProperties(model, dataModel);
		}else if(StringUtils.equals("add", type)){
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
		}else if(StringUtils.equals("yjfk", type)){
			mappingUri = "gyyjfk";
			GyyjxForm dataModel = service.getModel(model.getGyyjid());
			xgxt.utils.String.StringUtils.formatData(dataModel);
			BeanUtils.copyProperties(model, dataModel);
			request.setAttribute("gyyjxx", service.getModelMap(model.getGyyjid())); //��ѯ;
		}
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			CwglService cwglservice = new CwglService();
			HashMap<String , String> cwxx = cwglservice.getCwForXh(model.getXh());
			request.setAttribute("cwxx", cwxx); //��ѯѧ����λ��Ϣ
		}
		request.setAttribute("yjflList",new GyyjxdmwhService().getAllList(model));
		String path = "gygl_gyyjxstu.do?method=actionNavi&type=" + type;
		request.setAttribute("path", URLEncoder.encode(path, "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward(mappingUri);
	}
	
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ�����-��Ԣ�����-����XH:{xh},YJFLDM:{yjfldm},YJMS:{yjms}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();

		if(StringUtils.isNotBlank(model.getYjfldm()) && StringUtils.isNotBlank(model.getXh()) && StringUtils.isNotBlank(model.getYjms())){
			model.setYjsj(DateUtils.getCurrTime());
			//xgxt.utils.String.StringUtils.formatData(model);
			model.setFknr(HtmlUtil.xmlZy(model.getFknr()));
			boolean isSuccess = service.runInsert(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		return null;
	}
	
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ�����-��Ԣ�����-�޸�QYYJID:{gyyjid},YJFLDM:{yjfldm},YJMS:{yjms}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();

		if(StringUtils.isNotBlank(model.getYjfldm()) && StringUtils.isNotBlank(model.getYjms())){
			//xgxt.utils.String.StringUtils.formatData(model);
			model.setFknr(HtmlUtil.xmlZy(model.getFknr()));
			boolean isSuccess = service.runUpdate(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		
		return null;
	}
	
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ�����-ɾ��PK:{pks}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxStuService service  = new GyyjxStuService();
		String pks = request.getParameter("pks");
		
		if(StringUtils.isNotBlank(pks)){
			int isSuccess = service.runDelete(pks.split(","));
			String messageKey = (isSuccess>0) ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		
		return null;
	}
	
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		if(StringUtils.isNotBlank(model.getGyyjid())){
			//BeanUtils.copyProperties(model, service.getModel(model.getYjfldm()));
			request.setAttribute("gyyjxx", service.getModelMap(model.getGyyjid())); //��ѯ;
		}
		if(StringUtils.isNotBlank(model.getGyyjid())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			CwglService cwglservice = new CwglService();
			HashMap<String , String> cwxx = cwglservice.getCwForXh(model.getXh());
			request.setAttribute("cwxx", cwxx); //��ѯѧ����λ��Ϣ
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("queryGyyjxx");
	}
	
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ�����-����PK:{gyyjid}")
	public ActionForward gyyjfk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		User user = getUser(request);
		if(StringUtils.isNotBlank(model.getGyyjid()) && StringUtils.isNotBlank(model.getFknr())){
			model.setFkqk("1");
			model.setFkr(user.getUserName());
			model.setFksj(DateUtils.getCurrTime());
			boolean isSuccess = service.runUpdate(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();

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
}
