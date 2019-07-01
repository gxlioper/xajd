/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-19 ����11:03:19 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjtx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xnxj.jcsz.JcszForm;
import com.zfsoft.xgxt.xsxx.xnxj.jcsz.JcszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-19 ����11:03:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnxjtxAction extends SuperAction {

	private static final String XSXXXNXJ = "xsxxxnxj";
	private static BdpzService bdpzService = new BdpzService();
	private static JcszService jcszService = new JcszService();
	private static List<HashMap<String,String>> jbxxList = null;
	
	static{
		jbxxList = bdpzService.getJbxxpz(XSXXXNXJ);
	}
	
	private static final String url = "xsxx_xnxj_xjtx.do";
	
	@SystemAuth(url = url)
	public ActionForward viewXnxjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnxjForm model  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		
		HashMap<String , String> jcszMap =jcszService.getOneKgzt();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			String xn = null;
			
			if("stu".equals(user.getUserType())){
				xn = Base.currXn;
			}else{
				xn = searchModel.getSearch_tj_xn()[0];
			}
			
			model.setSearchXn(xn);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{jcszMap.get("xjxn")});
		searchModel.setSearch_tj_txzt(new String[]{"1"});
		request.setAttribute("searchTj", searchModel);
			
		HashMap<String , String> jcszModel = jcszService.getOneKgzt();
		request.setAttribute("jcszModel", jcszModel);
		
		model.setSearchXn(Base.currXn);
		
		String path = "xsxx_xnxj_xjtx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewXnxjList");
	}
	
	/**
	 * 
	 * @����:ѧ��С����д
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-19 ����04:16:10
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
	public ActionForward xnxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjForm model  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		User user = getUser(request);
		HashMap<String , String> jcszMap =jcszService.getOneKgzt();
		model.setXn(jcszMap.get("xjxn"));  //����ѧ��С��
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxx(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			
			XnxjForm xnxjModel = service.getModel(model); //��ѯѧ��С��
			
			if(xnxjModel != null){
				BeanUtils.copyProperties(model, StringUtils.formatData(xnxjModel));
			}
			
			
			JcszForm jcszModel = jcszService.getModel();
			
			request.setAttribute("jcszModel", jcszModel);
			
		}
		
		String path = "xsxx_xnxj_xjtxgl.do?method=xnxjsq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnxjModel", model);
		return mapping.findForward("toTx");
		
	}
	
	/**
	 * 
	 * @����:ѧ��С���޸�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-25 ����04:25:08
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
	public ActionForward xnxjxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjForm myform  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		
		User user = getUser(request);
		
		if(!StringUtil.isNull(myform.getId())){
			
			String id = myform.getId();
			
			XnxjForm model = service.getModel(id);
			
			if(model != null ){
				BeanUtils.copyProperties(myform, StringUtils.formatData(model));
			}
			
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxx(myform.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		
			JcszForm jcszModel = jcszService.getModel();
			
			request.setAttribute("jcszModel", jcszModel);
			
		}

		request.setAttribute("jbxxList", jbxxList);
		//request.setAttribute("xnxjModel", myform);
		return mapping.findForward("toXg");
	}
	
	@SystemAuth(url = url)
	public ActionForward xnxjjdck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjForm model  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		String xh = model.getXh();
		if(!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxx(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
//			HashMap<String , String> newMap = service.getXnxjInfo(xh, jcszService.getOneKgzt().get("xjxn"));
			HashMap<String , String> newMap = service.getXnxjInfo(xh, model.getXn());
			List<HashMap<String , String>> xnxjList = new ArrayList<HashMap<String,String>>();
			xnxjList.add(newMap);
			request.setAttribute("xnxjList", xnxjList);
		}
		String path = "xsxx_xnxj_xjjggl.do?method=xnxjjdadd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("toCk2");
	}
	
	
	/**
	 * 
	 * @����:����ѧ��С��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-31 ����05:31:09
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С����д-�޸�PK:{id}")
	public ActionForward doUpdateXnxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XnxjForm myform  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		
		boolean isSuccess  = service.updateXnxj(myform);
		
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
		
	}
	
	
	/**
	 * 
	 * @����:������������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-20 ����08:45:53
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С����д-����")
	public ActionForward doXnxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjForm model  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		
		/*User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "����ʧ�ܣ����Ѿ������С����д����ȷ�ϣ�";
			HashMap<String , String> result = service.getXnxjInfo(model.getXh(), Base.currXn);
			if(result != null && result.size() > 0){
				response.getWriter().print(getJsonMessage(msg));
				return null;
			}
		}*/
		
		JcszForm jcszModel = jcszService.getModel();
		
		if(jcszModel == null || "n".equals(jcszModel.getKg())){//1,�жϿ���״̬
			String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		if(StringUtils.isNull(model.getXh())){
			String messageKey = "ѧ�Ų���Ϊ�գ���ȷ��";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		HashMap<String , String> jcszMap =jcszService.getOneKgzt();
		model.setXn(jcszMap.get("xjxn"));//���õ�ǰѧ��
		boolean isSuccess  = service.saveXnxj(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if(StringUtils.isEqual(model.getType(), "submit")){
			messageKey = isSuccess ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
		
	}
	
	/**
	 * 
	 * @����:ɾ��ѧ��С���¼
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-20 ����11:55:53
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С����д-ɾ��PK:{values}")
	public ActionForward doXnxjdel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjForm model  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		
		String ids = request.getParameter("values");
		
		if(StringUtils.isNull(ids)){
			String messageKey = MessageKey.SYS_CANCEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		int delNum = service.delXnxjsq(ids.split(","));
		
		boolean isSuccess = delNum > 0;
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_DEL_NUM , new Object[]{delNum}) :  MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
		
	}
	
	/**
	 * 
	 * @����:ѧ��С���ύ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-20 ����02:36:14
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С����д-�ύPK:{values}")
	public ActionForward doSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjService service = new XnxjService();
		String ids = request.getParameter("values");
//		String shlcidnew = request.getParameter("shlcidnew");
		if(StringUtils.isNull(ids)){
			String messageKey = MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		int submitNum = service.submitXnxjs(ids.split(","));
		
		boolean isSuccess = submitNum > 0;
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS_NUM , new Object[]{submitNum}) :  MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
		
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-20 ����03:10:56
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С����д-����PK:{values}")
	public ActionForward doXnxjCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjForm model  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		
		String ids = request.getParameter("values");
		
		if(StringUtils.isNull(ids)){
			String messageKey = MessageKey.SYS_CANCEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		int[] canelRst = service.cancelXnxjs(ids.split(","));
		
		String messageKey = MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS_NUM , new Object[]{canelRst[0] , canelRst[1]});
		
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
		
	}
	
	
	///////////////////////////////////////////////////////////ѧ��С�����//////////////////////////////////////////////////////////////////////
	
	
	@SystemAuth(url = url)
	public ActionForward viewXnxjshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjForm model  = (XnxjForm) form;
		XnxjService service = new XnxjService();
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
			List<HashMap<String,String>> resultList = service.getXnxjShPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		HashMap<String , String> jcszMap =jcszService.getOneKgzt();
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{jcszMap.get("xjxn")});
		request.setAttribute("searchTj", searchModel);
		HashMap<String , String> jcszModel = jcszService.getOneKgzt();
		request.setAttribute("jcszModel", jcszModel);
		
		model.setSearchXn(Base.currXn);
		
		String path = "xsxx_xnxj_xjsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewXnxjshList");
		
		
	}
	
	/**
	 * 
	 * @����:ѧ��С�ᵥ�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-23 ����09:53:27
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
	public ActionForward toXnxjDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XnxjForm myForm  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		User user = getUser(request);
		
		String xtgwid = myForm.getXtgwid();
		String shid = myForm.getShid();
		XnxjForm model = service.getModel(myForm.getId());
		
		if(model != null){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxx(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			
			BeanUtils.copyProperties(myForm, model);
		}
		
		myForm.setXtgwid(xtgwid);
		myForm.setShid(shid);
		
		JcszForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);//��ѯ��������
		
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("toDgSh");
	}
	
	/**
	 * 
	 * @����:���浥����˲���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-23 ����11:54:23
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С�����-���PK:{id}")
	public ActionForward doXnxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjForm myForm  = (XnxjForm) form;
		XnxjService service = new XnxjService();
		User user = getUser(request);
		
		XnxjForm model = service.getModel(myForm.getId());
		
		model.setShjg(myForm.getShjg());
		model.setShyj(myForm.getShyj());
		model.setThgw(myForm.getThgw());
		model.setXtgwid(myForm.getXtgwid());
		
		boolean isSuccess = service.saveXnxjSh(model, user);
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_AUD_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
		
	}
	/**
	 * 
	 * @����:����ѧ��С�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-23 ����04:08:33
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-ѧ��С�����-����PK:{id}")
	public ActionForward doXnxjShCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XnxjForm myForm  = (XnxjForm) form;
		XnxjService service = new XnxjService();
	
		String ywid = myForm.getId();   //ҵ��id
		
		boolean isSuccess = service.cancelXnxjSh(ywid);
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-25 ����11:09:02
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
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String vals = request.getParameter("vals");
		
		if(vals != null && vals.split(",").length == 1){
			File file=printForWord(vals.split("\\|")[0] , vals.split("\\|")[1]);
			FileUtil.outputWord(response, file);
		}else{
			List<File> files = new ArrayList<File>();
			for(String val:vals.split(",")){
				File file=printForWord(val.split("\\|")[0] , val.split("\\|")[1]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	
	
	
	private  File printForWord(String xh , String xn) throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();
		
		XnxjService xnxjService = new XnxjService();
		
		XsxxService xsxxService = new XsxxService();
		
		HashMap<String , String> xnxjInfo = xnxjService.getXnxjInfo(xh , xn);
		
		String xnxj = xnxjInfo.get("xjnr");
		String xnxjBegin = "";
		String xnxjEnd = "";
		if(xnxj.length()>1000){
			xnxjBegin= xnxj.substring(0, 1000);
			xnxjEnd = xnxj.substring(1000,xnxj.length());
		}else{
			xnxjBegin=xnxj;
		}
		xnxjInfo.put("xjnrBegin", HtmlUtil.xmlZy(xnxjBegin));
		xnxjInfo.put("xjnrEnd", HtmlUtil.xmlZy(xnxjEnd));
		//xnxjInfo.put("xjnr", HtmlUtil.xmlZy(xnxj));
		xnxjInfo.put("txny", DateTranCnDate.fomartDateToCn(xnxjInfo.get("txsj"),FomartDateType.day));
		
		data.putAll(xnxjInfo);
		
		HashMap<String , String> xsxx = xsxxService.getXsjbxx(xh);

		data.putAll(xsxx);
		
		// =======��ѯ���������������ʱ������ beign======
		HashMap<String , String> xnxjShyjMap0 = new HashMap<String , String>();
		HashMap<String , String> xnxjShyjMap1 = new HashMap<String , String>();
		List<HashMap<String , String>> xnxjShyjList = xnxjService.getXnxjShyjList(xnxjInfo.get("id"));
		if(xnxjShyjList.size() >= 1){
			xnxjShyjMap0 = xnxjShyjList.get(0);
			xnxjShyjMap0.put("shny", DateTranCnDate.fomartDateToCn(xnxjShyjMap0.get("shsj"),FomartDateType.day));
		}
		data.put("xnxjShyjMap0", HtmlUtil.formatXmlMap(xnxjShyjMap0));
		if(xnxjShyjList.size() >= 2){
			xnxjShyjMap1 = xnxjShyjList.get(1);
			xnxjShyjMap1.put("shny", DateTranCnDate.fomartDateToCn(xnxjShyjMap1.get("shsj"),FomartDateType.day));
		}
		data.put("xnxjShyjMap1", HtmlUtil.formatXmlMap(xnxjShyjMap1));
		// =======��ѯ���������������ʱ������ end======
		data.put("xxmc", Base.xxmc);
		
		return getWord(data);
	}
	
	
	private File getWord(Map<String, Object> data) throws FileNotFoundException {
		String templatePath = Constants.TEP_DIR+"xsxx/xnxj" + ".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xsxx", "xnxj"+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}

		return wordFile;
	
	}
	
	/**
	 * 
	 * @����:��֤��д�����Ƿ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-26 ����04:04:28
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
	
	public ActionForward checkKg (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		JcszForm jcszModel = jcszService.getModel();
		
		//true:���ύ/false:�����ύ
		response.getWriter().print("y".equalsIgnoreCase(jcszModel.getKg()));
		
		return null;
	}
	
	
	
}
