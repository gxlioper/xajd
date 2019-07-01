/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����03:59:30 
 */  
package com.zfsoft.xgxt.xsxx.xsxxcj;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.studentInfo.ynys.XsxxYnysService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(ѧ����Ϣ�ɼ�--���ְҵ��ѧ) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-7-30 ����03:59:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxcjAction extends SuperAction {
	
	private static final String url = "xsxx_gygl_xsxxcj.do?method=Xsxxcj";
	
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList")
	public ActionForward cxXsxxcjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		if (QUERY.equals(model.getType())){
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
		
		String path = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxXsxxcjList");
	}
	
	
	/**
	 * 
	 * @����:TODO(����ѧ���ɼ���Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-1 ����07:06:59
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ�ɼ������ְ��-��ʦ�ɼ�ѧ����Ϣ-����")
	public ActionForward addXsxxcj (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		XsxxYnysService ynysService = new XsxxYnysService();
		request.setAttribute("ssList", ynysService.getSsList());//ʡ�б�
		request.setAttribute("hkszdshiList", ynysService.getShiList( "9999999" ).get("shiList"));//���б�
		request.setAttribute("hkszdxianList",  ynysService.getShiListNew("9999999").get("xianList"));//���б�
		
		request.setAttribute("jtdzshiList", ynysService.getShiList( "9999999" ).get("shiList"));//���б�
		request.setAttribute("jtdzxianList",  ynysService.getShiListNew("9999999").get("xianList"));//���б�
		
		request.setAttribute("sydshiList", ynysService.getShiList( "9999999" ).get("shiList"));//���б�
		request.setAttribute("sydxianList",  ynysService.getShiListNew("9999999").get("xianList"));//���б�
		
		request.setAttribute("jgshiList", ynysService.getShiList( "9999999" ).get("shiList"));//���б�
		request.setAttribute("jgxianList",  ynysService.getShiListNew("9999999").get("xianList"));//���б�
		DAO dao=DAO.getInstance();
		List<HashMap<String, String>> mzList=dao.getMzList();
		mzList.remove(0);
		mzList.remove(0);
		request.setAttribute("mzList", mzList);
		if (SAVE.equals(model.getType())){
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "xsxx_gygl_xsxxcj.do?method=addXsxxcj";
		request.setAttribute("path", path);
		return mapping.findForward("addXsxxcj");
	}
	
	/**
	 * 
	 * @����:TODO(�鿴ѧ���ɼ���Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-1 ����07:06:28
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList")
	public ActionForward viewXsxxcj (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm myForm=(XsxxcjForm) form;
		XsxxcjForm model=service.getModel(myForm);
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("viewXsxxcj");
	}
	/**
	 * 
	 * @����:TODO(�޸�ѧ���ɼ���Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-1 ����07:07:26
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ�ɼ������ְ��-��ʦ�ɼ�ѧ����Ϣ-�޸�PK:{xh}")
	public ActionForward updateXsxxcj (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm myForm=(XsxxcjForm) form;
		XsxxcjForm model=service.getModel(myForm);
		XsxxYnysService ynysService = new XsxxYnysService();
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("ssList", ynysService.getSsList());//ʡ�б�
        request.setAttribute("hkszdshiList", ynysService.getShiList(model.getHkszdshen() == null ? "9999999" : model.getHkszdshen()).get("shiList"));//���б�
		request.setAttribute("hkszdxianList",  ynysService.getShiListNew(model.getHkszdshi() == null ? ( model.getHkszdshen() != null ? model.getHkszdshen().substring(0, 2) :"9999999"): model.getHkszdshi()).get("xianList"));//���б�

        request.setAttribute("jtdzshiList", ynysService.getShiList(model.getJtdzshen() == null ? "9999999" : model.getJtdzshen()).get("shiList"));//���б�
		request.setAttribute("jtdzxianList",  ynysService.getShiListNew(model.getJtdzshi() == null ? ( model.getJtdzshen() != null ? model.getJtdzshen().substring(0, 2) :"9999999"): model.getJtdzshi()).get("xianList"));//���б�

        request.setAttribute("sydshiList", ynysService.getShiList(model.getSydshen() == null ? "9999999" : model.getSydshen()).get("shiList"));//���б�
		request.setAttribute("sydxianList",  ynysService.getShiListNew(model.getSydshi() == null ? ( model.getSydshen() != null ? model.getSydshen().substring(0, 2) :"9999999"): model.getSydshi()).get("xianList"));//���б�

        request.setAttribute("jgshiList", ynysService.getShiList(model.getJgshen() == null ? "9999999" : model.getJgshen()).get("shiList"));//���б�
		request.setAttribute("jgxianList",  ynysService.getShiListNew(model.getJgshi() == null ? ( model.getJgshen() != null ? model.getJgshen().substring(0, 2) :"9999999"): model.getJgshi()).get("xianList"));//���б�
		DAO dao=DAO.getInstance();
		List<HashMap<String, String>> mzList=dao.getMzList();
		mzList.remove(0);
		mzList.remove(0);
		request.setAttribute("mzList", mzList);
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateXsxxcj");
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ѧ���ɼ���Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-1 ����07:07:54
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ�ɼ������ְ��-��ʦ�ɼ�ѧ����Ϣ-ɾ��VALUES:{values}")
	public ActionForward delXsxxcj (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XsxxcjForm myForm = (XsxxcjForm) form;
		XsxxcjService service = new XsxxcjService();
		
		String values = request.getParameter("values");
		
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	/**
	 * 
	 * @����:TODO(����ѧ������)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-1 ����07:08:19
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		
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
	 * @����:TODO(��ȡѡ��ѧ��list)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-1 ����06:18:53
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
	
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getXsPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
	}
	/**
	 * 
	 * @����:TODO(����ѧ��������Ϣͳ�ơ�̨�ˡ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-2 ����10:22:29
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
	
	public ActionForward exportXsjbxxtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		//���������ļ�
		File file = new File( tempDir.getPath() + "/" + String.valueOf(System.currentTimeMillis())+".xls");
		file.setWritable(true);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<String[]> resultList = service.getXsjbxxtzList(model,user);//��ѯ�����м�¼������ҳ
		String[] title=new String[]{"ѧ��������","��У������","����ʵϰѧ����","ס��ѧ����","�뵳������ѧ����","��������ѧ����"};//��ͷ����
		File file1=service.exportXsjbxxtz(response,resultList,file,title);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(����ѧ��������Ϣͳ�ơ�̨�ˡ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-2 ����10:22:29
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
	
	public ActionForward exportXsknxxtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		//���������ļ�
		File file = new File( tempDir.getPath() + "/" + String.valueOf(System.currentTimeMillis())+".xls");
		file.setWritable(true);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<String[]> resultList = service.exportXsknxxtz(model,user);//��ѯ�����м�¼������ҳ
		String[] title=new String[]{"��ͥ����ѧ����","��������ѧ����","��������ѧ����","ѧϰ����ѧ����","���弲��ѧ����","��������ѧ��������顢���εȣ�","�ڽ�����ѧ����"};//��ͷ����
		File file1=service.exportXsjbxxtz(response,resultList,file,title);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����:(ѧ����Ϣ�ɼ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-8 ����06:17:06
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
	public ActionForward Xsxxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm myForm=(XsxxcjForm) form;
		User user = getUser(request);
		String userType=user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
		String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			String xh=user.getUserName();
			myForm.setXh(xh);
			XsxxcjForm model=service.getModel(myForm);
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			XsxxYnysService ynysService = new XsxxYnysService();
			request.setAttribute("ssList", ynysService.getSsList());//ʡ�б�
	        request.setAttribute("hkszdshiList", ynysService.getShiList(model.getHkszdshen() == null ? "9999999" : model.getHkszdshen()).get("shiList"));//���б�
			request.setAttribute("hkszdxianList",  ynysService.getShiListNew(model.getHkszdshi() == null ? ( model.getHkszdshen() != null ? model.getHkszdshen().substring(0, 2) :"9999999"): model.getHkszdshi()).get("xianList"));//���б�

	        request.setAttribute("jtdzshiList", ynysService.getShiList(model.getJtdzshen() == null ? "9999999" : model.getJtdzshen()).get("shiList"));//���б�
			request.setAttribute("jtdzxianList",  ynysService.getShiListNew(model.getJtdzshi() == null ? ( model.getJtdzshen() != null ? model.getJtdzshen().substring(0, 2) :"9999999"): model.getJtdzshi()).get("xianList"));//���б�

	        request.setAttribute("sydshiList", ynysService.getShiList(model.getSydshen() == null ? "9999999" : model.getSydshen()).get("shiList"));//���б�
			request.setAttribute("sydxianList",  ynysService.getShiListNew(model.getSydshi() == null ? ( model.getSydshen() != null ? model.getSydshen().substring(0, 2) :"9999999"): model.getSydshi()).get("xianList"));//���б�

	        request.setAttribute("jgshiList", ynysService.getShiList(model.getJgshen() == null ? "9999999" : model.getJgshen()).get("shiList"));//���б�
			request.setAttribute("jgxianList",  ynysService.getShiListNew(model.getJgshi() == null ? ( model.getJgshen() != null ? model.getJgshen().substring(0, 2) :"9999999"): model.getJgshi()).get("xianList"));//���б�
			DAO dao=DAO.getInstance();
			List<HashMap<String, String>> mzList=dao.getMzList();
			mzList.remove(0);
			mzList.remove(0);
			request.setAttribute("mzList", mzList);
		}
		return mapping.findForward("Xsxxcj");
	}
	/**
	 * 
	 * @����:(������Ϣ��д��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-8 ����06:48:34
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
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ�ɼ������ְ��-��ʦ�ɼ�ѧ����Ϣ-����XH:{xh}")
	public ActionForward saveXsxxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm myForm=(XsxxcjForm) form;
		XsxxcjForm model=service.getModel(myForm);
		boolean result;
		if(model.getXh()!=null&&!"".equalsIgnoreCase(model.getXh())){
			result = service.runUpdate(myForm);
		}else{
			result=service.runInsert(myForm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
		

}
