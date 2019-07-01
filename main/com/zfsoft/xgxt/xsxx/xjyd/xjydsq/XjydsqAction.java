package com.zfsoft.xgxt.xsxx.xjyd.xjydsq;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;
import com.zfsoft.xgxt.xsxx.xjyd.XjydService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ���춯����
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-11-28 ����09:40:48 
 * @�汾�� V5.12.20
 */
public class XjydsqAction extends SuperAction {

	//�ӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String XJYDSQ = "xjydsq";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private XsxxService xsxxService = new XsxxService();
	
	private static final String url = "xjyd_xjydsq.do";
	
	/**
	 * 
	 * @����:ѧ���춯�����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����10:06:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xjydsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydsqForm model = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();
		
		if (QUERY.equals(model.getType())){

			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xjyd_xjydsq.do";
		request.setAttribute("path", path);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydsqList");
	}

	/**
	 * ����ѧ���춯����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯����-����XH:{xh},YDLBDM:{ydlbdm}")
	public ActionForward xjydsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydsqForm myForm = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();

		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			boolean result = false;
			String messageKey = "";
			// �Ƿ�������жϣ���ѧ����δ������ѧ���춯�����������룩
			if(StringUtils.isNotNull(myForm.getXh()) && !service.sfKsq(myForm)){

				super.resetToken(request);
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
				myForm.setXn(Base.currXn);
				myForm.setXq(Base.currXq);
				myForm.setYdqnj(xsjbxx.get("nj"));
				myForm.setYdqxydm(xsjbxx.get("xydm"));
				myForm.setYdqzydm(xsjbxx.get("zydm"));
				myForm.setYdqbjdm(xsjbxx.get("bjdm"));

				myForm.setYdqxjlb(xsjbxx.get("xjlbdm"));//ѧ��������
				myForm.setYdqxjlbmc(xsjbxx.get("xjlbmc"));//ѧ�����
				myForm.setYdqsfyxjmc(xsjbxx.get("xjztm"));//�Ƿ���ѧ��
				myForm.setYdqsfzxmc(xsjbxx.get("sfzx"));//�Ƿ���У
				//����ʦ����ѧ���Ի��ֶΣ��Ƿ�ʦ������
				if("10511".equalsIgnoreCase(Base.xxdm)) {
					myForm.setSfsfs(xsjbxx.get("sfsfs"));//�Ƿ�ʦ����
				}
				
				myForm.setSqsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				myForm.setSqr(user.getUserName());

				//�趨�������
				XjydForm xjydlxForm = new XjydForm();
				xjydlxForm.setXjlbdm(myForm.getYdlbdm());			
				XjydService xjydlxService = new XjydService();
				XjydForm xjydlx = xjydlxService.getModelShpz(xjydlxForm);
				if(xjydlx != null){
					myForm.setSplcid(xjydlx.getShlcid());
				}
				
				result = service.runInsert(myForm);

				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				
				// �ύ�ɹ�/ʧ����ʾ
				if(Constants.YW_SHZ.equals(myForm.getShzt())){
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessage("��ѧ����δ������ѧ���춯���룡"));
				return null;
			}
			
		}
		
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		//ѧ��������Ϣ
		String path = "xjydsq.do?method=xjydsqAdd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);		
		
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}else{
			HashMap<String,String> xsjbxx = new HashMap<String,String>();
			xsjbxx.put("xjlb", "");
			xsjbxx.put("xjztm", "");
			xsjbxx.put("sfzx", "");			
			xsjbxx.put("nj", "");
			xsjbxx.put("xymc", "");
			xsjbxx.put("zymc", "");
			xsjbxx.put("bjmc", "");
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//������ѧ�����LIST
		XjydService xjlbservice = new XjydService();
		List<HashMap<String,String>> xjlbList = xjlbservice.getXjlbList("1","");
		request.setAttribute("xjlbList", xjlbList);
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			//�춯ԭ��
			List<HashMap<String,String>> ydyyList = service.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//ά����ԴѧУ/ȥ��ѧУ
			List<HashMap<String,String>> lyqxxxList = service.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
		}
		
		//ѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		
		FormModleCommon.commonRequestSet(request);
		//�꼶��ѧԺ��רҵ���༶
		FormModleCommon.setAllNjXyZyBjList(request);
		this.saveToken(request);
		return mapping.findForward("xjydsqAdd");
	}
	
	
	
	/**
	 * �޸�ѧ���춯����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯����-�޸�XJYDSQID:{xjydsqid},XH:{xh},YDLBDM:{ydlbdm}")
	public ActionForward xjydsqUpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XjydsqForm myForm = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();

		if (UPDATE.equalsIgnoreCase(myForm.getType())){

			User user = getUser(request);
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			myForm.setYdqnj(xsjbxx.get("nj"));
			myForm.setYdqxydm(xsjbxx.get("xydm"));
			myForm.setYdqzydm(xsjbxx.get("zydm"));
			myForm.setYdqbjdm(xsjbxx.get("bjdm"));
			myForm.setSqr(user.getUserName());
			myForm.setYdqxjlb(xsjbxx.get("xjlbdm"));//ѧ��������
			myForm.setYdqxjlbmc(xsjbxx.get("xjlbmc"));//ѧ�����
			myForm.setYdqsfyxjmc(xsjbxx.get("xjztm"));//�Ƿ���ѧ��
			myForm.setYdqsfzxmc(xsjbxx.get("sfzx"));//�Ƿ���У
			
			//����ʦ����ѧ���Ի��ֶΣ��Ƿ�ʦ������
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				myForm.setSfsfs(xsjbxx.get("sfsfs"));//�Ƿ�ʦ����
			}
			
			// �Ƿ����˻ر��
			String sfyth = request.getParameter("sfyth");
			
			// �����˻ؼ�¼��������ȡ���µ��������ID
			if(!Constants.YTH.equals(sfyth)){

				//�趨�������
				XjydForm xjydlxForm = new XjydForm();
				xjydlxForm.setXjlbdm(myForm.getXjlbdm());			
				XjydService xjydlxService = new XjydService();
				XjydForm xjydlx = xjydlxService.getModelShpz(xjydlxForm);
				if(xjydlx!=null){
					myForm.setSplcid(xjydlx.getShlcid());
				}
				// ����ݸ� �� �����˻ؼ�¼�����޸ĺ󣬻����˻�
			}if(Constants.YTH.equals(sfyth) && !Constants.YW_SHZ.equals(myForm.getShzt())){
				myForm.setShzt(Constants.YW_YTH);
			}
			boolean result = service.runUpdate(myForm);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			// �ύ�ɹ�/ʧ����ʾ
			if(Constants.YW_SHZ.equals(myForm.getShzt())){
				messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		XjydsqForm model = service.getModel(myForm.getXjydsqid());

		model.setXjlbdmold(model.getXjlbdm());

		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);		
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		//������ѧ�����LIST
		XjydService xjlbservice = new XjydService();
		List<HashMap<String,String>> xjlbList = null;
		if(Constants.YW_YTH.equals(model.getShzt())){
			xjlbList = xjlbservice.getXjlbList("1", model.getYdlbdm());
		}else{
			xjlbList = xjlbservice.getXjlbList("1","");
		}		
		request.setAttribute("xjlbList", xjlbList);	
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {	
			//�춯ԭ��
			List<HashMap<String,String>> ydyyList = service.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//ά����ԴѧУ/ȥ��ѧУ
			List<HashMap<String,String>> lyqxxxList = service.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
		}
		//ѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		
		FormModleCommon.commonRequestSet(request);
		
		//�꼶��ѧԺ��רҵ���༶
		FormModleCommon.setAllNjXyZyBjList(request);

		String path = "xjydsq.do?method=xjydsqUpd";
		request.setAttribute("path", path);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("xjydsqUpd");
	}
	
	
	/**
	 * ɾ��ѧ���춯����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯����-ɾ��VALUES:{values}")
	public ActionForward xjydsqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");
		XjydsqService service = new XjydsqService();
		
		if (!StringUtil.isNull(values)){
			
			int num =  service.runDelete(values.split(","));
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * �����ύ/ȡ���ύѧ���춯����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯����-�����ύVALUES:{values}")
	public ActionForward xjydsqPltj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydsqService service = new XjydsqService();
		String values = request.getParameter("values");
		String shzt = request.getParameter("shzt");
		
		// �������״̬
		boolean result = service.submitRecord(values ,shzt);
		
		// ���������
		if(result){
			result = service.pltjXjydsq(values, shzt);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;

		// �����ɹ�/ʧ����ʾ
		if(Constants.YW_WTJ.equals(shzt)){
			messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));		
		return null;
	}
	
	/**
	 * 
	 * @����: �鿴ѧ���춯��Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����09:13:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xjydck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydsqForm myForm = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();
		XjydsqForm model = service.getModel(myForm.getXjydsqid());

		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);	

		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}else{
			HashMap<String,String> xsjbxx = new HashMap<String,String>();
			xsjbxx.put("xjlb", "");
			xsjbxx.put("xjztm", "");
			xsjbxx.put("sfzx", "");			
			xsjbxx.put("nj", "");
			xsjbxx.put("xymc", "");
			xsjbxx.put("zymc", "");
			xsjbxx.put("bjmc", "");
			request.setAttribute("jbxx", xsjbxx);
		}
		
		XjydjgForm xjydjg = new XjydjgForm();
		XjydjgService xjydjgService = new XjydjgService();
		xjydjg = xjydjgService.getModelBySqid(myForm.getXjydsqid());
		
		request.setAttribute("xjydjg", xjydjg);
		request.setAttribute("shzt", model.getShzt());
		BeanUtils.copyProperties(myForm, StringUtils.formatDataCk(model));
		return mapping.findForward("xjydck");
	}

	/**
	 * 
	 * @����: ѧ���춯���뵼��
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydsqForm model = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// ��ѯ�����м�¼������ҳ

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
	
	
	/**
	 * 
	 * @����:�ǼǱ�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-30 ����04:35:21
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
		XjydsqService service = new XjydsqService();
		String value = request.getParameter("value");
		if(value != null){
			List<File> files = new ArrayList<File>();
			for(String id : value.split(",")){
				HashMap<String , String> modelMap = service.getModelMap(id);
				String dybb = modelMap.get("dybb");
				if(StringUtil.isNull(dybb)){
					String msg = "δ���õǼǱ������ϵ����Ա��";
					request.setAttribute("yhInfo", msg);
					return new ActionForward("/yhInfo.do", false);
				}
				HashMap<String , Object> data = printData(modelMap);
				File file = BbdmUtils.getSigleFile(dybb, data);
				files.add(file);
			}
			if(value.split(",").length == 1){
				File file = files.get(0);
				FileUtil.outputWord(response, file);
			}else{
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
		}
		return null;
	}
	
	private HashMap<String , Object> printData(HashMap<String , String> modelMap) throws Exception{
		HashMap<String , Object> data = new HashMap<String, Object>();
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(modelMap.get("xh"));
		if(StringUtils.isNotNull(modelMap.get("ydqbjdm"))){
			HashMap<String , String> bzrxx = xsxxService.getBzrxxByBjdm(modelMap.get("ydqbjdm"));
			data.put("ydqbzr" , bzrxx.get(bzrxx));  //��������Ϣ
		}
		if(StringUtils.isNotNull(modelMap.get("ydhbjdm"))){
			HashMap<String , String> ydhbzrxx = xsxxService.getBzrxxByBjdm(modelMap.get("ydhbjdm"));
			data.put("ydhbzr" , ydhbzrxx.get("bzrxx"));  //��������Ϣ
		}
		data.putAll(xsjbxx);  //ѧ����Ϣ
		data.putAll(modelMap); //ѧ���춯
		data.put("xxmc", Base.xxmc); //ѧУ����
		data.put("nd", Base.currNd);
		//��ȡ���������
		String tbnf = modelMap.get("sqsj").substring(0,4);
		String tbyf = modelMap.get("sqsj").substring(5,7);
		String tbts = modelMap.get("sqsj").substring(8,10);
		data.put("tbnf", tbnf);
		data.put("tbyf", tbyf);
		data.put("tbts", tbts);
		//��ȡ��ѧԭ��
		XjydjgService service = new XjydjgService();
		XjydjgForm xjydjg = new XjydjgForm();
		xjydjg.setXh(modelMap.get("xh"));
		HashMap<String, String> xsydInfo = service.getXsydInfo(xjydjg);
		data.put("xsydInfo", xsydInfo);
		
		String sqly = HtmlUtil.xmlZy(data.get("sqly")== null ?  "" : data.get("sqly").toString());
		data.put("sqly", sqly);  //��������
		/**
		 * ���Ľ���ְҵ����ѧԺ ���Ի�
		 */
		if(StringUtils.isEqual(Base.xxdm, "13151")){
			String sfzh = (String) data.get("sfzh");
			if(sfzh != null){
				for (int i = 0; i < sfzh.length(); i++) {
					data.put("sfzh" + (i + 1), org.apache.commons.lang.StringUtils.substring(sfzh, i, i+1));
				}
			}
		}
		return data;
	}

	/**
	 * 
	 * @����: ��֤�Ƿ���ύ
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward checkSfktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydsqForm model = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();
		String ydlbdm = request.getParameter("ydlbdm");
		model.setXjlbdm(ydlbdm);
		// ȡ���Ƿ������֤(�����춯���) true:���ύ/false�������ύ
		boolean isSfktj = service.checkSfktj(model);
		response.getWriter().print(isSfktj);
		return null;
	}
	
}
