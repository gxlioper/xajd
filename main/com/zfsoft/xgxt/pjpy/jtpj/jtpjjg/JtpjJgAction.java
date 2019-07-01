/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-5 ����10:33:39 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjjg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.sdftj.sdfTjService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqService;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzService;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-5-5 ����10:33:39
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjJgAction extends SuperAction {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	private static final String template_dir = "classpath://templates//jtpj";//ģ��·��
	
	private static final String url = "jtpjjgbase.do";
	
	/**
	 * 
	 * @����:���������б��ѯ��ʾ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		JtpjJgForm myForm = (JtpjJgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("jtpjjgbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "jtpjjgbase.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jtpjjglist");
	}

	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	@SystemLog(description = "������������-��������-�����������-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			Map<String, Object> map = service.delete(values.split(","));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @����: ��������ģ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	@SystemLog(description = "������������-��������-�����������-�޸�JGID:{jgid},SQXN:{sqxn},SQXQ:{sqxq},JXID:{jxid},SQLY:{sqly}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		JtpjJgForm myForm = (JtpjJgForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			JtpjSqService jtpjSqService=new JtpjSqService();
			//�ظ��ж�
			
			if(!("qt").equals(myForm.getJtpjdw())&&!jtpjSqService.checkIsNotExists(myForm.getJxid(), myForm.getPjjtid(),null,myForm.getJgid(),"jg")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			if(("qt").equals(myForm.getJtpjdw())&&!jtpjSqService.checkIsNotExists(myForm.getJxid(),null,myForm.getPjjtmc(),myForm.getJgid(),"jg")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			myForm.setSqr(user.getUserName());
			JtpjSqService jss=new JtpjSqService();
			myForm.setSqrylx(jss.getSqrlx(user.getUserType()));
			if(Base.xxdm.equals("10704")){
				myForm.setRdfs(myForm.getRdfs().trim());
			}
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm = service.getModel(myForm.getJgid());
		request.setAttribute("xqmc", service.getXqmc(myForm.getSqxq()));
		request.setAttribute("data", StringUtils.formatData(myForm));
		JtpjSzService jss = new JtpjSzService();
		request.setAttribute("jxList", jss.getJxList(myForm.getSqxn(),myForm.getSqxq(), "0",user));
		request.setAttribute("xxdm", Base.xxdm);
		if(Base.xxdm.equalsIgnoreCase("10704")){//�����Ƽ���ѧ���Ի�
			if(myForm.getJtpjdw().equalsIgnoreCase("bj")){
				request.setAttribute("bjpj", "true");
			}else{
				request.setAttribute("bjpj", "false");
			}
			request.setAttribute("fs",myForm.getRdfs());
		}
		return mapping.findForward("jtpjjgupdate");
	}

	/**
	 * 
	 * @����: ��ȡ�޸�Ĭ����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����05:32:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward loadUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		String jgid = request.getParameter("jgid");
		JtpjJgForm myForm = service.getModel(jgid);
		response.getWriter().print(JSONObject.fromObject(myForm));
		return null;
	}

	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:44:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	@SystemLog(description = "������������-��������-�����������-����SQXN:{sqxn},SQXQ:{sqxq},JXID:{jxid},SQLY:{sqly}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		JtpjJgForm myForm = (JtpjJgForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			JtpjSqService jtpjSqService=new JtpjSqService();
			//�ظ��ж�
			if(!("qt").equals(myForm.getJtpjdw())&&!jtpjSqService.checkIsNotExists(myForm.getJxid(), myForm.getPjjtid(), null,null,"jg")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			if(("qt").equals(myForm.getJtpjdw())&&!jtpjSqService.checkIsNotExists(myForm.getJxid(),null,myForm.getPjjtmc(),null,"jg")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			myForm.setSqr(user.getUserName());
			JtpjSqService jss=new JtpjSqService();
			myForm.setSqrylx(jss.getSqrlx(user.getUserType()));
			if(Base.xxdm.equals("10704")){
				if(StringUtils.isNotNull(myForm.getRdfs())){
					myForm.setRdfs(myForm.getRdfs().trim());
				}
			}
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		myForm.setSqxn(Base.currXn);
		myForm.setSqxq(Base.currXq);
		
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("data", myForm);
		request.setAttribute("xxdm", Base.xxdm);
		JtpjSzService jss = new JtpjSzService();
		request.setAttribute("jxList", jss.getJxList(myForm.getSqxn(),myForm.getSqxq(), "0",user));
		this.saveToken(request);
		return mapping.findForward("jtpjjgadd");
	}

	/**
	 * 
	 * @����:��ʾ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-17 ����05:23:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		JtpjJgForm myForm = (JtpjJgForm) form;
		myForm = service.getModel(myForm.getJgid());
		JtpjSzService jss = new JtpjSzService();
		myForm.setJxmc(jss.getModel(myForm.getJxid()).getJxmc());
		request.setAttribute("sqxqmc", service.getXqmc(myForm.getSqxq()));
		request.setAttribute("pdxqmc", service.getXqmc(myForm.getPdxq()));
		request.setAttribute("data", StringUtils.formatData(myForm));
		request.setAttribute("xxdm", Base.xxdm);
		if("10704".equalsIgnoreCase(Base.xxdm)){			
			if(myForm.getJtpjdw().equalsIgnoreCase("bj")){
				request.setAttribute("bjpj", "true");
			}else{
				request.setAttribute("bjpj", "false");
			}
		}
		return mapping.findForward("jtpjjgview");
	}

	/**
	 * 
	 * @����: ���ذ༶������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����01:57:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward loadBjpjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		User user = getUser(request);
		String bjdm = request.getParameter("bjdm");
		List<HashMap<String, String>> bjlist = service.getBjList(user);
		request.setAttribute("bjList", bjlist);
		// StringUtils.isNull(bjdm) �ղ���Ĭ��ֵ
		if ("stu".equals(user.getUserType())) {
			if(null != bjlist && bjlist.size()>0){
				bjdm = bjlist.get(0).get("bjdm");
			}
		}
		JtpjSqService jss=new JtpjSqService();
		request.setAttribute("bjdm", bjdm);
		if(StringUtils.isNotNull(bjdm)){
			request.setAttribute("bjmc", new JtpjSqService().getBjmc(bjdm));
		}else{
			request.setAttribute("bjmc", "");
		}
		request.setAttribute("qss", jss.getQss(bjdm));
		request.setAttribute("bjrs", jss.getBjrs(bjdm));
		request.setAttribute("bzrList", jss.getBzrxx(bjdm));
		request.setAttribute("fdyList", jss.getFdyxx(bjdm));
		//�Ƿ����ݴ�ѧ
		request.setAttribute("iswzdx", Globals.XXDM_WZDX.equals(Base.xxdm)?"1":"0");
		if (VIEW.equals(request.getParameter("type"))) {
			String jgid = request.getParameter("jgid");
			JtpjJgForm myForm = service.getModel(jgid);
			request.setAttribute("data", myForm);
			return mapping.findForward("bjview");
		}
		return mapping.findForward("bj");
	}

	/**
	 * 
	 * @����: ����ѧԺ�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����01:56:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward loadXypjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		User user = getUser(request);
		String xydm = request.getParameter("xydm");
		List<HashMap<String, String>> xylist = service.getXyList(user);
		request.setAttribute("xyList", xylist);
		if (StringUtils.isNull(xydm) && null != xylist && xylist.size() > 0) {
			xydm = xylist.get(0).get("xydm");
		}
		request.setAttribute("xydm", xydm);
		JtpjSqService jss=new JtpjSqService();
		request.setAttribute("qss", jss.getXyQss(xydm));
		request.setAttribute("xylable", message.getMessage("lable.xb"));
		request.setAttribute("xyrs", jss.getXyrs(xydm));
		if (VIEW.equals(request.getParameter("type"))) {
			String jgid = request.getParameter("jgid");
			JtpjJgForm myForm = service.getModel(jgid);
			request.setAttribute("data", myForm);
			return mapping.findForward("xyview");
		}
		return mapping.findForward("xy");
	}
	
	public ActionForward loadld(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		sdfTjService sdfTjService=new sdfTjService();
		if (VIEW.equals(request.getParameter("type"))) {
			JtpjJgService service = new JtpjJgService();
			String jgid = request.getParameter("jgid");
			JtpjJgForm myForm = service.getModel(jgid);
			String lddm=myForm.getPjjtid().split("@@")[0];
			request.setAttribute("ldmc", sdfTjService.getLdmc(lddm));
			String qsh=myForm.getPjjtid().split("@@")[1];
			request.setAttribute("qsh", qsh);
			return mapping.findForward("qsview");
		}
		List<HashMap<String, String>> ldlist = sdfTjService.getLddmList();
		request.setAttribute("ldlist", ldlist);
		return mapping.findForward("qs");
	}
	
	public ActionForward loadOtherPjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgService service = new JtpjJgService();
		if (VIEW.equals(request.getParameter("type"))) {
			String jgid = request.getParameter("jgid");
			JtpjJgForm myForm = service.getModel(jgid);
			request.setAttribute("data", StringUtils.formatData(myForm));
			return mapping.findForward("qtview");
		}
		return mapping.findForward("qt");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgForm model = (JtpjJgForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		JtpjJgService service = new JtpjJgService();
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
	 * @����: ��ӡWord�ǼǱ��Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-4 ����11:30:24
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
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgForm model = (JtpjJgForm) form;
		File wordFile = getWord(model.getJgid());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	//���ģ����������word�ļ����Ϻ����ѧԺ��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private File getWord(String jgid) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		JtpjJgService jtpjJgService = new JtpjJgService();
		HashMap<String, String> djbMap = null;
		if ("11458".equals(Base.xxdm)){
			djbMap=jtpjJgService.getDjbInfo(jgid);
			if (djbMap != null){
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				djbMap.put("sqxn", djbMap.get("sqxn").replace("-", "/"));
				data.putAll(djbMap);
				String templateXmlPath = "xjjtdjb_11458.xml";
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}else if ("11067".equals(Base.xxdm)){
			djbMap=jtpjJgService.getDjbInfo(jgid);
			if (djbMap != null){
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				djbMap.put("sqxn", djbMap.get("sqxn").replace("-", "/"));
				data.putAll(djbMap);
				String templateXmlPath = "xjbjtdjb_11067.xml";
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}
		else if("10080".equals(Base.xxdm)){
			//�ӱ���ҵ��ѧ���Ի�
			djbMap=jtpjJgService.gethbgydxDjb(jgid);//������Ϣ
			if (djbMap != null){
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				String bjdm = djbMap.get("bjdm");
				data.putAll(djbMap);
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, djbMap.get("mbmc"), djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}else{
			//�ǼǱ����ú����˷���
			djbMap= jtpjJgService.getDjb(jgid);
			if(djbMap!=null){
				//�ǼǱ��Զ������������
				data.putAll(jtpjJgService.fillData(djbMap));
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				data.put("currY", DateUtils.getYear());//��ǰ��
				data.put("currM", DateUtils.getMonth());//��ǰ��
				data.put("currD",DateUtils.getDayOfMonth());//��ǰ��
				String[] xnArr = djbMap.get("sqxn").split("-");
				data.put("qsxn", xnArr[0]);
				data.put("zhxn", xnArr[1]);
				//��ȡ������λ��Ϣ
				HashMap<String, String> dwMap = new JtpjSqService().getBjxgxx(djbMap.get("pjjtmc"));
				if("10876".equals(Base.xxdm)){
					data.put("xymc", dwMap.get("xymc"));
				}
				if(StringUtils.isNull(djbMap.get("mbmc"))){
					throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
				}
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, djbMap.get("mbmc"), djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}
		return null;
	}
	
	/**
	 * @����: �Ǽ� ��ZIP ���Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-4 ����11:30:24
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
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	//ɽ��������ҽְҵѧԺ���Ի������Ƚ��༯���Ƽ���
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getSdxmWord_xjbjt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		JtpjJgService pjjgserice = new JtpjJgService();
		File wordFile = pjjgserice.getXmGxhDy_12947_xjbjt(values);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����:���ģ����������word�ļ���ɽ��������ҽְҵѧԺ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-5 ����04:51:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getWord_12947(String jgid) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		JtpjJgService jtpjJgService = new JtpjJgService();
		HashMap<String, String> djbMap = jtpjJgService.getDjbInfo(jgid);
		if (djbMap != null){
			djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
			djbMap.put("sqxn", djbMap.get("sqxn").replace("-", "/"));
			
			HashMap<String, String> rxrqMap = jtpjJgService.getRxrq(jgid);
			String rxrq = rxrqMap.get("rxrq");
			djbMap.put("rxrq", rxrq);
			data.putAll(djbMap);
			String templateXmlPath = "sdxmsy_12947_xjbjt.xml";
			File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
			return wordFile;
		}
		return null;
	}
	
	/**
	 * 
	 * @����:ɽ���������������Ƚ��༯��ǼǱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-5 ����04:53:42
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
	public ActionForward getDjbZip_12947(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord_12947(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ɽ�����������Ƚ��༯��ǼǱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-5 ����05:19:29
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
	public ActionForward getDjbWord_12947(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgForm model = (JtpjJgForm) form;
		File wordFile = getWord_12947(model.getJgid());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/** 
	 * @����:�Ƿ�Ϊ�༶����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-20 ����03:11:38
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
	public ActionForward isBjjx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjJgForm model = (JtpjJgForm) form;
		JtpjJgService service = new JtpjJgService();
		boolean result = service.isBjjx(model.getJxid());
		response.getWriter().print(result);
		return null;
	}
	
	
}
