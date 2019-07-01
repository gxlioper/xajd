/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-20 ����01:12:13 
 */  
package com.zfsoft.xgxt.xszz.knsjg;

import java.io.File;
import java.net.URLEncoder;
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
import xgxt.utils.GetTime;
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
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsrd.KnsrdForm;
import com.zfsoft.xgxt.xszz.knsrd.KnsrdService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;

import common.Globals;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ѯ����ģ��
 * @�๦������: Action 
 * @���ߣ� maxh
 * @ʱ�䣺 2013-4-20 ����01:12:13 
 * @�汾�� V1.0
 */
public class KnsjgAction extends SuperAction{
	
	
	private static final String KNSRD = "knsrd";
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "xszz_knsjg_cx.do";
	
	/**
	 * 
	 * @����:��������ѯ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-20 ����03:10:33
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
	public ActionForward getKnsjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		
		if (QUERY.equals(model.getType())){
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
		
		if(!Globals.XXDM_ZJDX.equals(Base.xxdm)){
			SearchModel searchModel=new SearchModel();
			//���������Ի�
			if("12309".equals(Base.xxdm)){
				searchModel.setSearch_tj_sfzx(new String[]{"��У"});
			}else{
				searchModel.setSearch_tj_xn(new String[]{Base.currXn});
				if("xq".equals(SQZQ)){
					searchModel.setSearch_tj_xq(new String[]{Base.currXq});
				}
			}
			request.setAttribute("searchTj", searchModel);
		}
		String xq=Base.currXq;
		if("xn".equals(SQZQ)){
			xq="on";
		}
		List<HashMap<String, String>> list=service.getKnsjgZqListNotIsHave(Base.currXn + "," + xq);
		request.setAttribute("iscanCopy", null==list||list.size()<=0?"0":"1");
		String path = "xszz_knsjg_cx.do";
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("knsjg");
	}
	/**
	 * 
	 * @����:�����������϶���Ϣ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-22 ����10:49:48
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
	@SystemLog(description="����ѧ������-�������϶�-��������ѯ-����-XH:{xh},XN:{xn},RDDC:{rddc}")
	public ActionForward addKnsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjgForm model = (KnsjgForm) form;
		KnsdcService knsdcService = new KnsdcService();
		KnsjgService service = new KnsjgService();
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
	
        if (SAVE.equalsIgnoreCase(model.getType())){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		} else {
    			super.resetToken(request);
    		}
        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
        	boolean isExist=service.isExistByKnsjg(model,SAVE);
        	if(!isExist){
	        	//����������϶���Ϣ
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		String message = MessageUtil.getText(MessageKey.XSZZ_KNSJG_RESULT_REPEAT_XN,model.getXn());
        		 if("xq".equals(SQZQ)){
        			 message = MessageUtil.getText(MessageKey.XSZZ_KNSJG_RESULT_REPEAT_XQ,new String[]{model.getXn(),model.getXq()});
        		}
        		response.getWriter().print(getJsonMessage(message));
				return null;
        	}
		}
        model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
        model.setXn(Base.currXn);
        if("xq".equals(SQZQ)){
        	 model.setXq(Base.currXq);
		}
        //����������list
		request.setAttribute("knsdcList", knsdcService.getKnsdcList());
		//�����Ի��޳���������б�
		request.setAttribute("wczzjeList", knsdcService.getWczzList());
		request.setAttribute("xxdm", Base.xxdm);
		//ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ��list
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("knyyList", knsdcService.getKnyyList());//����ԭ��
		String path = "xszz_knsjg.do?method=addKnsjg";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));

		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("jbxxList", jbxxList);
		if("10742".equals(Base.xxdm)) {
			KnsrdService knsrdService = new KnsrdService();
			request.setAttribute("sqlymcList", knsrdService.getSqlymcList());		
		}
		//����ʦ����ѧ���ؼ�ͥ�������ͺ͸ߵ�����Ʒ���� 
		if("10346".equals(Base.xxdm)){
			KnsrdService knsrdService = new KnsrdService();
			request.setAttribute("jtknlxList", knsrdService.getJtknlxList());
			request.setAttribute("gdxfplxList", knsrdService.getGdxfpLxList());
		}
		this.saveToken(request);
		return mapping.findForward("addknsjg");
	}
	/**
	 * 
	 * @����:�޸��������϶���Ϣ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-22 ����04:49:36
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
	@SystemLog(description="����ѧ������-�������϶�-��������ѯ-�޸�GUID:{guid},XH:{xh},XN:{xn},RDDC:{rddc}")
	public ActionForward updateKnsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjgForm model = (KnsjgForm) form;
		KnsdcService knsdcService = new KnsdcService();
		KnsjgService service = new KnsjgService();
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
	
        if (UPDATE.equalsIgnoreCase(model.getType())){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		} else {
    			super.resetToken(request);
    		}
        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
        	boolean isExist=service.isExistByKnsjg(model,UPDATE);
        	if(!isExist){
				//�޸��������϶���Ϣ
	        	boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessage(null));
				return null;
        		
        	}
		}
        //����������list
		request.setAttribute("knsdcList", knsdcService.getKnsdcList());
		//�����Ի��޳���������б�
		request.setAttribute("wczzjeList", knsdcService.getWczzList());
		request.setAttribute("xxdm", Base.xxdm);
		//ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ��list
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);
		if("10742".equals(Base.xxdm)) {
			KnsrdService knsrdService = new KnsrdService();
			request.setAttribute("sqlymcList", knsrdService.getSqlymcList());		
		}
		request.setAttribute("type", UPDATE);
		request.setAttribute("sqzq", SQZQ);
		KnsjgForm updateForm = service.getModel(model);
		//����ԭ��list
        if("10277".equals(Base.xxdm)) {
        	String knyydm=updateForm.getYlzd5();
			request.setAttribute("knyyList", new KnsrdService().getKnnyList(knyydm));	
		}
		
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		//����ʦ����ѧ���ؼ�ͥ�������ͺ͸ߵ�����Ʒ���� 
		if("10346".equals(Base.xxdm)){
			KnsrdService knsrdService = new KnsrdService();
			request.setAttribute("jtknlxList", knsrdService.getJtknlxList());
			request.setAttribute("gdxfplxList", knsrdService.getGdxfpLxList());
		}
		this.saveToken(request);
		return mapping.findForward("updateKnsjg");
	}
	/**
	 * 
	 * @����:ɾ���������϶���Ϣ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-22 ����05:24:04
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
	@SystemLog(description="����ѧ������-�������϶�-��������ѯ-ɾ��VALUES:{values}")
	public ActionForward delKnsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjgService service = new KnsjgService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			
			if (result){
				KnsrdService knsrdService = new KnsrdService();
				knsrdService.delKnssqFromKnsjg(values.split(","));
			}
			
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	/**
	 * 
	 * @����:�����������϶��������
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2014-6-27 ����05:42:17
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
	@SystemLog(description="����ѧ������-�������϶�-��������ѯ-���Ʊ���LYXN:{lyxn}")
	public ActionForward savecopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		KnsjgService service = new KnsjgService();
		String lyxn=request.getParameter("lyxn");
		String lyxq=request.getParameter("lyxq");
		String xn=request.getParameter("mbxn");
		String xq=request.getParameter("mbxq");
		
		String msg = service.copy(lyxn,lyxq,xn,xq);
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}
	/**
	 * 
	 * @����:�������϶��������
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2014-6-27 ����05:43:16
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
	public ActionForward knsjgcopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgService service = new KnsjgService();
		String xq=Base.currXq;
		if("xn".equals(SQZQ)){
			xq="on";
		}
		request.setAttribute("knsjgzqList", service.getKnsjgZqListNotIsHave(Base.currXn +","+ xq));
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("xq",xq);
		request.setAttribute("xqmc",service.getXqmc(xq));
		this.saveToken(request);
		return mapping.findForward("knsjgcopy");
	}
	/**
	 * 
	 * @����:���������������ʾ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-23 ����02:56:11
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
	@SystemAuth(url = url)
	public ActionForward knsjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		request.setAttribute("xxdm", Base.xxdm);
		if (model == null){
			return updateKnsjg(mapping, form, request, response);
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		//��ѯ�������������
		Map<String,String> map=service.getOneKnsjgList(model.getGuid());
		if("10277".equals(Base.xxdm)){//�Ϻ�����ѧԺ���Ի�
			String knyy=new KnsrdService().getKnyymc(map.get("ylzd5"));
			map.remove("yymc");
			map.put("yymc", knyy);
		}
		request.setAttribute("rs",map );
		
		//�㽭��ѧ��ȡȡ���������ʸ�
		if(Globals.XXDM_ZJDX.equalsIgnoreCase(Base.xxdm)){				
		request.setAttribute("knsqxjlrs", service.getOneKnsqxjlList(model.getGuid()));			
		}
		
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sqzq", SQZQ);
		return mapping.findForward("viewKnsjg");
		
	}
	
	/**
	 * 
	 * @����:��ӡ������������Ϣ
	 * @���ߣ�honglin
	 * @���ڣ�2013-5-3 
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
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			KnsjgForm myForm = (KnsjgForm) form;
			String guids[]=myForm.getGuid().split(",");
			//String xhs[]=myForm.getXh().split(",");
			if(null!=guids&&guids.length==1){//һ������
				File file=print(myForm,guids[0],request);
				FileUtil.outputWord(response, file);
			}else{//��������
				List<File> files = new ArrayList<File>();
				for(String guid:guids){
					myForm.setGuid(guid);
					File file=print(myForm,guid,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
			return null;
	}
	/**
	 * 
	 * @����:��ȡ��Ӧ��ӡFile����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-20 ����05:42:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param guid
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File print(KnsjgForm myForm,String guid,HttpServletRequest request) throws Exception{
		myForm.setGuid(guid);
		KnsjgService service = new KnsjgService();
		XsxxService xsxxService = new XsxxService();
		KnsdcService knsdcService = new KnsdcService();

		KnsjgForm kf=service.getModel(myForm);
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(kf.getXh());
		//�������������еĴ�ӡ
		KnsrdService kservice = new KnsrdService();
		KnsrdbjpyService sjservice = new KnsrdbjpyService();
		KnsrdForm printForm=new KnsrdForm();
		KnsrdbjpyForm printForm1 = new KnsrdbjpyForm();
		//������������
		printForm.setSqly(kf.getSqly());

		printForm.setGuid(guid);
		printForm.setJtrjnsr(kf.getJtrjnsr());
		printForm1.setSqly(kf.getSqly());//������������
		printForm1.setGuid(guid);
		File file = null;
		if("10718".equals(Base.xxdm)) {
			file=sjservice.printjgForWord(xsjbxx,null,printForm1,knsdcService.getKnsdcList(), kf.getRddc(),request);
		}else {
			file=kservice.printjgForWord(xsjbxx,null,printForm,knsdcService.getKnsdcList(), kf.getRddc(),request);
		}	
		return file;
	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-22 ����10:44:47
	 * @�޸ļ�¼: 
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

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
	/**
	 * 
	 * @����:������ͳ�Ƶ��������ݴ�ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-20 ����03:06:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward knsrdTjExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		KnsjgForm exporModel = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(exporModel,user);//��ѯ�����м�¼������ҳ
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.knsrdTjExport(resultList, response.getOutputStream(), user);
		return null;
	}
	
	/**
	 * 
	 * @����:������ͳ�Ƶ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-7 ����03:06:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward knsExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		KnsjgForm exporModel = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(exporModel,user);//��ѯ�����м�¼������ҳ
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.knsExport(resultList, response.getOutputStream(), user);
		return null;
	}
	/**
	 * @description	�������մ����������ܱ�
	 * @author 		�� CP��1352��
	 * @date 		��2017-12-1 ����02:21:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward rdhzbExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		KnsjgForm exporModel = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(exporModel,user);//��ѯ�����м�¼������ҳ
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("����ѧ��ͳ�ƻ���.xls","utf-8")); 
		if ("12309".equals(Base.xxdm)) {
			service.rdhzbExport_12309(resultList, response.getOutputStream(), user);
		}
		else if("10098".equals(Base.xxdm)) {
			service.rdhzbExport_10098(resultList, response.getOutputStream(), user);
		}else {
			service.rdhzbExport(resultList, response.getOutputStream(), user);
		}
		return null;
	}
	/**
	 * @����: �㽭��ѧ���Ի��������������������������������ť
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-11-21 ����08:56:31
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
	public ActionForward knstksPercent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			List<HashMap<String,String>> resultList = service.knstksPercent(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("knstksPercent");
	}
	
	/**
	 * @���������� �������ݿ� �϶������ ����ְҵ���Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��30�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward ExportKnsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		KnsjgForm exporModel = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		String expType=request.getParameter("expType");
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<File> files = new ArrayList<File>();
		List<HashMap<String,String>>bjlist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>knsjglist=new ArrayList<HashMap<String,String>>();
		if("rdpxb".equals(expType)){
			bjlist=service.getKnsjgBj(exporModel,user);
			for(HashMap<String,String> map:bjlist){
				knsjglist=service.getKnsjgListBybj(exporModel,user,map.get("bjdm"));
				if(null==knsjglist||0==knsjglist.size()){
					continue;
				}
				File file=service.printFile(knsjglist, map.get("bjmc")+"["+map.get("bjdm")+"]", "knsrdpxb_12898.xml");
				files.add(file);
			}
			if(0==files.size()){
				knsjglist.add(new HashMap<String,String>());
				files.add(service.printFile(knsjglist, "�ò�ѯ������������ѧ��", "knsrdpxb_12898.xml"));
			}
		}else{
			bjlist=service.getKnsjgXy(exporModel,user);
			for(HashMap<String,String> map:bjlist){
				knsjglist=service.getKnsjgListByxy(exporModel,user,map.get("xydm"));
				if(null==knsjglist||0==knsjglist.size()){
					continue;
				}
				File file=service.printFile(knsjglist, map.get("xymc")+"["+map.get("xydm")+"]", "knsdasjk_12898.xml");
				files.add(file);
			}
			if(0==files.size()){
				knsjglist.add(new HashMap<String,String>());
				files.add(service.printFile(knsjglist, "�ò�ѯ������������ѧ��", "knsdasjk_12898.xml"));
			}
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		FileUtil.outputZip(response, zipFile);
		return null;
	}
	
	public ActionForward export_12688(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		// ============= ��ʼ����������ֵ ============
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("knstj.xls".getBytes(), "GBK") + "\"");
		service.gwcjffDc(response.getOutputStream(),model);
		
		return null;
	}
	
	/**
	 * 
	 * @����: ���ݹ��̸��Ի�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-26 ����11:27:02
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
	public ActionForward exportConfigXzyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.getXzyyExportData(model,user);//��ѯ�����м�¼������ҳ
		
		
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
	
	/**
	 * 
	 * @����: �㽭��ҽҩ���Ի�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-26 ����11:27:02
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
	public ActionForward exportConfigZjzyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.getZjzyyExportData(model,user);//��ѯ�����м�¼������ҳ
		
		
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
	
	/**
	 * 
	 * @����: ��ʦ����Ի�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-16 ����01:12:44
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
	public ActionForward printHzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		File file = null;
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		int zs = 10;
		int dataLine = 0;
		//��ѯ
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		if(resultList != null && resultList.size() >= 1){
			dataLine = resultList.size();
		}
		if(zs - dataLine >= 1){
			for (int i = 0; i < zs - dataLine; i++) {
				HashMap<String,String> tmpMap = new HashMap<String, String>();
				tmpMap.put("xh", "");
				tmpMap.put("xm", "");
				tmpMap.put("sjhm", "");
				resultList.add(tmpMap);
			}
		}
		data.put("rs", resultList);
		data.put("year", GetTime.getTimeByFormat("yyyy"));
		data.put("mon", GetTime.getTimeByFormat("mm"));
		data.put("day", GetTime.getTimeByFormat("dd"));
		String xymc = service.getXymc(request.getParameter("xydm"));
		data.put("xymc",xymc);
		ResourceUtils.getFile(Constants.TEP_DIR+"xszz/hzbhsd_10346.xml");
		file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"xszz","hzbhsd_10346.xml",FreeMarkerUtil.getFileName(xymc+"_��Ϣ���ܱ�"));
		FileUtil.outputWord(response, file);
		return null;
	}
	
	/**
	 * @description	�� ��������������
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-24 ����04:53:50
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knspddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		KnsjgForm model = (KnsjgForm) form;
		KnsjgService service = new KnsjgService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		User user = getUser(request);
		
		List<HashMap<String, String>> resultList = null;
		
		// ��ѯ
		String dcclbh = request.getParameter("dcclbh");
		//��У��ר������ѧ���϶�����
		if(dcclbh.equalsIgnoreCase("xg_knsrd_knsrddc.do")){//�������϶�����			
			resultList = service.knsrddc(model,user);// ��ѯ�����м�¼������ҳ
		}else if(dcclbh.equalsIgnoreCase("kns_jtjjkn_jtxx_ahjzdx.do")){//��������ͥ���
			resultList = service.jtxxdc(model, user);
		}else if(dcclbh.equalsIgnoreCase("kns_knsjg_knsjdlkdc.do")){//������������������
			resultList = service.knsjdlkdc(model, user);
		}
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(dcclbh);// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
