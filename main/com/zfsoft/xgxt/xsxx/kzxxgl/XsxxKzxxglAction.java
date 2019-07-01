/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-4 ����02:25:22 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.extend.model.ExtendModule;
import com.zfsoft.extend.service.ExtendService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgForm;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-4 ����02:25:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

@SuppressWarnings("unchecked")
public class XsxxKzxxglAction extends SuperAction {
	
	private static final String DATA_EXTEND_MODULE = "XSXX";
	
	private ExtendService extendService = new ExtendService();
	
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	private ShlcInterface shlc = new CommShlcImpl(); //����������ӿ�
	
	private XsxxKzxxglService service = new XsxxKzxxglService();
	
	private XsxxKzxxglJgService jgService = new XsxxKzxxglJgService();
	
	private static final String url = "xsxx_kzxxgl_xslr.do";
	
	
	/**
	 * 
	 * @����:��ѯ�����б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-18 ����04:51:52
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
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XsxxKzxxglForm model = (XsxxKzxxglForm) form;
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "xsxx_kzxxgl_xssqgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sqManage");
	}
	
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return null;
	}
	
	
	@SystemAuth(url = "xsxx_kzxxgl_cssz.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ExtendModule queryExtentModule = extendService.queryExtentModule(DATA_EXTEND_MODULE);
		//List<ExtendModuleElement> queryExtendModuleElements = extendService.queryExtendModuleElements(DATA_EXTEND_MODULE);
		//request.setAttribute("extendModuleElements", queryExtendModuleElements);
		request.setAttribute("extendModule", queryExtentModule);
		request.setAttribute("path", "xsxx_kzxxgl_cssz.do");
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xsxx"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	/**
	 * 
	 * @����: �����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-17 ����01:35:49
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
	
	public ActionForward csszAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String extendModuleID = request.getParameter("extendModuleID");
		String sfqy = request.getParameter("sfqy");
		String shlc = request.getParameter("shlc");
		String kssj = request.getParameter("kssj");
		String jssj = request.getParameter("jssj");
		String sfsh = request.getParameter("sfsh");
		String sjxz = request.getParameter("sjxz");
		ExtendModule module = new ExtendModule();
		module.setId(extendModuleID);
		module.setJssj(jssj);
		module.setKssj(kssj);
		module.setSfqy(sfqy);
		module.setSfsh(sfsh);
		module.setSjxz(sjxz);
		module.setSplc(shlc);
		boolean isSuccess = extendService.saveConfig(module);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:ѧ����չ��Ϣ��дҳ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-12 ����09:24:45
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xslr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		ExtendModule queryExtentModule = extendService.queryExtentModule(DATA_EXTEND_MODULE);
		String kssj = queryExtentModule.getKssj();
		String jssj = queryExtentModule.getJssj();
		String sfqy = queryExtentModule.getSfqy();
		String sfsh = queryExtentModule.getSfsh();
		String splc = queryExtentModule.getSplc();
		
		//1.�жϸ���չ���Ƿ�ʹ��
		request.setAttribute("sfqy", sfqy);
		//2.�жϸ���չ���Ƿ�������ʱ����
		String yxqkz = "1";
		Date currentDate = new Date();
		Date beforDate = null;
		Date afterDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(kssj)){
			beforDate = sdf.parse(kssj);
			if(currentDate.compareTo(beforDate) < 0)
				yxqkz = "0";
		}
		if(StringUtils.isNotBlank(jssj)){
			afterDate = sdf.parse(jssj);
			if(currentDate.compareTo(afterDate) > 0)
				yxqkz = "0";
		}
		request.setAttribute("yxqkz", yxqkz);
		//3.�ж��������������Ϣ
		String splsz = "1";
		if(StringUtils.equals("1", sfsh) && StringUtils.isBlank(splc)){
			splsz = "0";
		}
		request.setAttribute("splsz", splsz);
		
		if(StringUtils.equals("0", sfqy) || StringUtils.equals("0", yxqkz) || StringUtils.equals("0", splsz)){
			request.setAttribute("configError", "1");
		}
		String xh = user.getUserName();
		//��ȡѧ����չ��Ϣ
		//1.�����ѧ��û���������ݣ�ѧ����������������
		//2.�����ѧ���Ѿ���������������� ѧ�������ٴ�����������,�������δ��һ����ˣ����Գ���
		//3.�����ѧ���������Ѿ������� ѧ����������������
		//4.���ѧ�������ݻ�δ�ύ��ѧ�������޸�֮ǰ������
		XsxxKzxxglForm oneSqListByXh = service.getOneSqListByXh(xh);
		if(oneSqListByXh!=null){
			request.setAttribute("sqid", oneSqListByXh.getSqid());
			request.setAttribute("splc", oneSqListByXh.getSplc());
			request.setAttribute("shzt", oneSqListByXh.getShzt());
		}
		request.setAttribute("xskzxxSqData", oneSqListByXh);
		request.setAttribute("xh", xh);
		request.setAttribute("dataExtendModule", DATA_EXTEND_MODULE);
		request.setAttribute("path", "xsxx_kzxxgl_xslr.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xslr");
	}
	
	
	/**
	 * 
	 * @����:ѧ����չ��Ϣ�鿴ҳ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-12 ����09:24:45
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
	@SystemAuth(url = "xsxx_kzxxgl_xsck.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xsck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String xh = user.getUserName();
		XsxxKzxxglJgForm modelByXh = jgService.getModelByXh(xh);
		if(modelByXh!=null){
			request.setAttribute("jgid", modelByXh.getJgid());
		}
		request.setAttribute("xh", xh);
		request.setAttribute("dataExtendModule", DATA_EXTEND_MODULE);
		request.setAttribute("path", "xsxx_kzxxgl_xsck.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsck");
	}
	
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-17 ����05:35:01
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
	
	public ActionForward xslrAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		XsxxKzxxglForm model = (XsxxKzxxglForm)form;
		String actionType = model.getActionType();
		String extendData = model.getExtendData();
		String xh = user.getUserName();
		String sqid = model.getSqid();
		String exendDateModuleId = model.getExendDateModuleId();
		ExtendModule extentModule = extendService.queryExtentModule(exendDateModuleId);
		String[] checkDataModuleConfig = extendService.checkDataModuleConfig(extentModule);
		boolean isSuccess = true;
		String messageKey = null;
		if(checkDataModuleConfig[0] == "0"){
			response.getWriter().print(getJsonMessageResult(checkDataModuleConfig[1], Boolean.FALSE));
			return null;
		}else{
			boolean isNewApply = false;
			
			XsxxKzxxglForm sqDateModel = null;
			if(StringUtils.isNotBlank(sqid)){
				sqDateModel = service.getModel(sqid);
			}

			//����������룬֮ǰ���ݱ���˲�ͨ����֮ǰ�������ͨ��������Ҫ�����룬��ô������ID
			if(StringUtils.isBlank(sqid) || sqDateModel == null || 
					StringUtils.equals(Constants.YW_BTG, sqDateModel.getShzt()) || 
					StringUtils.equals(Constants.YW_TG, sqDateModel.getShzt())){
				isNewApply = true;
				sqid = UniqID.getInstance().getUniqIDHash();
			}
			
			JSONArray sqdata = JSONObject.fromString(extendData).getJSONArray("data");
			
			/**
			 * ������ύ����
			 * 1. ������ˣ� ��ʱ�����ʽ���������ݣ�������ʱ������״̬�����ͨ��
			 * 2. �����Ҫ��ˣ� ��ʱ��������ݣ�״̬Ϊ�����
			 */
			if(StringUtils.equals("submit", actionType)){
				if(StringUtils.equals(extentModule.getSfsh(), "1")){
					if(isNewApply){
						sqDateModel = new XsxxKzxxglForm();
						sqDateModel.setSqid(sqid);
						sqDateModel.setShzt(Constants.YW_SHZ);
						sqDateModel.setSplc(extentModule.getSplc());
						sqDateModel.setSqid(sqid);
						sqDateModel.setSqsj(DateUtils.getCurrTime());
						sqDateModel.setXh(xh);
						isSuccess = service.runInsert(sqDateModel);
					}else{
						sqDateModel.setShzt(Constants.YW_SHZ);
						isSuccess = service.runUpdate(sqDateModel);
					}
					//��ʱ�����ݱ���
					isSuccess = extendService.dataPersistentTemp(sqid, extentModule.getId(), sqdata);
					//�ύ�����
					isSuccess = shlc.runSubmit(sqid, extentModule.getSplc(), xh, "xsxx_kzxxgl_sh.do", "xsxx_kzxxgl_sq.do");

				}else{
					if(isNewApply){
						sqDateModel = new XsxxKzxxglForm();
						sqDateModel.setSqid(sqid);
						sqDateModel.setShzt(Constants.YW_TG);
						sqDateModel.setSplc(extentModule.getSplc());
						sqDateModel.setSqid(sqid);
						sqDateModel.setSqsj(DateUtils.getCurrTime());
						sqDateModel.setXh(xh);
						isSuccess = service.runInsert(sqDateModel);
					}else{
						sqDateModel.setShzt(Constants.YW_TG);
						isSuccess = service.runUpdate(sqDateModel);
					}
					XsxxKzxxglJgForm jgModel = jgService.getModelByXh(xh);
					String jgid = null;
					if(jgModel != null){
						jgid = jgModel.getJgid();
						jgModel.setSqid(sqid);
						jgModel.setSjly("1");
						jgModel.setJrsj(DateUtils.getCurrTime());
						isSuccess = jgService.runUpdate(jgModel);
					}else{
						jgModel = new XsxxKzxxglJgForm();
						jgid = UniqID.getInstance().getUniqIDHash();
						jgModel.setJgid(jgid);
						jgModel.setJrsj(DateUtils.getCurrTime());
						jgModel.setSjly("1");
						jgModel.setSqid(sqid);
						jgModel.setXh(xh);
						//������������
						isSuccess = jgService.runInsert(jgModel);
					}
					//��ʱ�����ݱ���
					isSuccess = extendService.dataPersistentTemp(sqid, extentModule.getId(), sqdata);
					//��ʽ�����ݱ���
					isSuccess = extendService.dataPersistent(jgid, extentModule.getId(), sqdata);
				}
			}else if(StringUtils.equals("save", actionType)){
				if(isNewApply){
					sqDateModel = new XsxxKzxxglForm();
					sqDateModel.setSqid(sqid);
					sqDateModel.setShzt(Constants.YW_WTJ);
					sqDateModel.setSplc(extentModule.getSplc());
					sqDateModel.setSqid(sqid);
					sqDateModel.setSqsj(DateUtils.getCurrTime());
					sqDateModel.setXh(xh);
					isSuccess = service.runInsert(sqDateModel);
				}
				//��ʱ�����ݱ���
				isSuccess = extendService.dataPersistentTemp(sqid, extentModule.getId(), sqdata);
			}
		}
		if(StringUtils.equals("submit", actionType)){
			messageKey = isSuccess ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}else{
			messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
