package xsgzgl.gyjc.jcjglr;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gyjc.jcrc.JcrcForm;
import xsgzgl.gyjc.jcrc.JcrcService;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq.XsgzzbsqService;

public class JcjglrAction extends SuperAction<JcjglrForm,JcjglrService> {
	private JcjglrService service = new  JcjglrService();
	private static final String url = "xg_gyjc_jcjglr.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");

	/**
	 * 
	 * @����: �鿴�ύ������ϸ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-20 ����07:23:25
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
	public ActionForward getJcjgLrcxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		JcjglrForm model = (JcjglrForm) form;
		User user = getUser(request);
		if(StringUtils.isNotNull(request.getParameter("username"))){
			model.setTjr(user.getUserName());
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getCommWpdjList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		if(StringUtils.isNotNull(model.getFlag()) && "jgcx".equals(model.getFlag())){
			searchModel.setSearch_tj_xn(new String[]{Base.currXn});
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		if("jgcx".equals(model.getFlag())){
			request.setAttribute("path", "gyjc_jcjglr.do?method=getJcjgLrcxList&flag=jgcx&tjzt=1");
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "gyjc_jcjglr.do?method=getJcjgLrcxList");
		return mapping.findForward("searchlrcx");
	}
	
	/**
	 * 
	 * @����: ��ȡ�����¼��List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-21 ����09:32:10
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
	public ActionForward getJcjgLrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JcjglrForm model = (JcjglrForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("today", GetTime.getTimeByFormat(DATE_FORMAT));
		//���û�����û�ҳ��
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}
	
	
	/**
	 * 
	 * @����: �ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-21 ����06:01:10
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
	@SystemLog(description = "������������-�����¼��-�ύMXIDS:{mxidString}")
	public ActionForward submitRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String mxidString = request.getParameter("mxids");
		boolean rs = false;
		if(StringUtils.isNotNull(mxidString)){
			String[] mxids = mxidString.split(",");
			rs = service.tjRecode(mxids,getUser(request).getUserName());
		}
		String messageKey = (rs) ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-21 ����06:02:27
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
	@SystemLog(description = "������������-�����¼��-����")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcjglrForm model = (JcjglrForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getCommWpdjList(model, user);
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
	 * @throws Exception 
	 * 
	 * @����: ����¼����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-24 ����05:32:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������������-�����¼��-����MXIDS:{mxids),PFIDS:{pfids},MXIDFLAGS:{mxidflags}")
	public ActionForward saveLrjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JcjglrForm model = (JcjglrForm) form;
		model.setTjr(getUser(request).getUserName());
		String[] mxids = request.getParameterValues("mxid");
		model.setMxids(mxids);
		String[] pfids = request.getParameterValues("pfid");
		model.setPfids(pfids);
		String[] mxidflags = request.getParameterValues("mxidflag"); 
		model.setMxidflags(mxidflags);
		String[] indexs = request.getParameterValues("index");
		model.setIndexs(indexs);
		String[] fids = request.getParameterValues("fid");
		model.setFids(fids);
		//JcjglrService tranService = TransactionControlProxy.newProxy(new JcjglrService());
		boolean rs = true;
    	try {
			rs = service.saveJcjglr(model);
		} catch (SystemException e) {
			// TODO �Զ����� catch ��
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_OPERATE_FAIL));
			return null;
		}
	    
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:�����·��ά��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-25 ����01:42:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addJcJgLr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JcjglrForm model = (JcjglrForm) form;
		//���һ�����Ϣ
		HashMap<String, String> qsxx = service.getQsjbxx(model.getLddm(),model.getQsh());
		request.setAttribute("qsxx", qsxx);
		//(������飬��ȫ��飬���ɼ���������)
		HashMap<String,String> jcmxMap = service.getJcmxMap(model.getRcid(),model.getLddm(),model.getQsh(),getUser(request).getUserName(),null);
		request.setAttribute("jcmxMap", jcmxMap);
		request.setAttribute("yscwjList", service.getYscfjxx(jcmxMap.get("fjid")));
		//����������ҳ��
		if(StringUtils.isNotNull(jcmxMap.get("wsjc"))){
			request.setAttribute("wsjcFjSeList",service.getFjSelectList(model.getXydm(),"1",model.getJs()));
			request.setAttribute("wsjcZjSeList",service.getZjSelectList(model.getXydm(), "1", model.getJs()));
			request.setAttribute("wsjcPfList", service.getJcxFxCx(model.getRcid(),model.getLddm(),model.getQsh(),"1"));
		}
		if(StringUtils.isNotNull(jcmxMap.get("aqjc"))){
			request.setAttribute("aqjcFjSeList",service.getFjSelectList(model.getXydm(),"2",model.getJs()));
			request.setAttribute("aqjcZjSeList",service.getZjSelectList(model.getXydm(), "2", model.getJs()));
			request.setAttribute("aqjcPfList", service.getJcxFxCx(model.getRcid(), model.getLddm(), model.getQsh(),"2"));
		}
		if(StringUtils.isNotNull(jcmxMap.get("jljc"))){
			request.setAttribute("jljcFjSeList",service.getFjSelectList(model.getXydm(),"3",model.getJs()));
			request.setAttribute("jljcZjSeList",service.getZjSelectList(model.getXydm(), "3", model.getJs()));
			request.setAttribute("jljcPfList", service.getJcxFxCx(model.getRcid(),  model.getLddm(), model.getQsh(), "3"));
		}
		return mapping.findForward("jcjglr");
	}
	
	/**
	 * 
	 * @����: �鿴�����¼��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-26 ����03:55:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward ckJcJgLr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JcjglrForm model = (JcjglrForm) form;
		//���һ�����Ϣ
		HashMap<String, String> qsxx = service.getQsjbxx(model.getLddm(),model.getQsh());
		request.setAttribute("qsxx", qsxx);
		//(������飬��ȫ��飬���ɼ���������)
		HashMap<String,String> jcmxMap = service.getJcmxMap(model.getRcid(),model.getLddm(),model.getQsh(),getUser(request).getUserName(),model.getFlag());
		request.setAttribute("jcmxMap", jcmxMap);
		request.setAttribute("djList", service.getFxdjcxForView(model.getRcid(), model.getXydm(), model.getQsh(),model.getLddm(),"jc", null));
		//����������ҳ��
		if(StringUtils.isNotNull(jcmxMap.get("wsjc"))){
			request.setAttribute("wsjcPfList", service.getJcxFxCx(model.getRcid(),model.getLddm(),model.getQsh(),"1"));
		}
		if(StringUtils.isNotNull(jcmxMap.get("aqjc"))){
			request.setAttribute("aqjcPfList", service.getJcxFxCx(model.getRcid(), model.getLddm(), model.getQsh(),"2"));
		}
		if(StringUtils.isNotNull(jcmxMap.get("jljc"))){
			request.setAttribute("jljcPfList", service.getJcxFxCx(model.getRcid(),  model.getLddm(), model.getQsh(), "3"));
		}
		return mapping.findForward("jcjglrck");
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-21 ����06:01:10
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
	public ActionForward cxRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String mxidString = request.getParameter("mxids");
		boolean rs = false;
		if(StringUtils.isNotNull(mxidString)){
			String[] mxids = mxidString.split(",");
			rs = service.cxRecode(mxids);
		}
		String messageKey = (rs) ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_OPERATE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����: �ļ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-28 ����03:08:43
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
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fid = request.getParameter("fid");
		HashMap<String, String> wjxx = service.getWjxxx(fid);
		String basePath = resource.getString("filesys.local.dir");
		//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
		if(StringUtils.isNull(basePath)){
			basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
		}
		if (StringUtils.isNotNull(wjxx.get("generatename"))){
			File file = new File(basePath+"/"+wjxx.get("generatename"));
			if (file.exists()){
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(wjxx.get("originalname"),"utf-8")); 
					FileUtil.outputFile(response, file);
				}
			}
		}
		return null;
	}
	
}
