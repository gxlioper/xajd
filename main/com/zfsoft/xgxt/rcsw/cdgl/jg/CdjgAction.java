/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:38:19 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.jg;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.szdw.general.dwwh.DwwhService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqService;
import com.zfsoft.xgxt.rcsw.jzbg.JzbgForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ���ؽ������ACTION
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-29 ����03:38:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdjgAction extends SuperAction {
	/**
	 * @���� ��ȡ�����б�
	 */
	private DwwhService dwwhService = new DwwhService();
	
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private BdpzService bdpzService = new BdpzService();
	
	/**
	 * @���� ѧ����Ϣ����
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @���ԣ� service �������������
	 */
	private CdsqService cdsqService = new CdsqService();
	
	/**
	 * @���ԣ� service ���ؽ��������
	 */
	private CdjgService service = new CdjgService();
	
	/**
	 * @���ԣ� cdxxwhService ������Ϣά������
	 */
	public CdxxwhService cdxxwhService = new CdxxwhService();
	
	/**
	 * @���� ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 *  @���ԣ� PATH ·��
	 */
	public static final String PATH = "rcsw_cdgl_cdjggl.do";
	
	public static final String url = "rcsw_cdgl_cdjggl.do";
	
	/**
	 * @���ԣ� CDGL_BBID ������id
	 */
	private static final String CDGL_BBID = "rcswcdgl"; 
	
	/**
	 * 
	 * @���� �����췽��
	 */
	public CdjgAction(){
		super();
		jbxxList = bdpzService.getJbxxpz(CDGL_BBID);
	}
	
	
	/**
	 * 
	 * @����:ҳ��dispatcher ���������ѯҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
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
	public ActionForward cdjgCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cdjgCx");
	}
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:43:04
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
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdjgForm model = (CdjgForm) form;
		User user = getUser(request);
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
	
	
	/**
	 * 
	 * @����:ҳ��dispatcher ���ؽ������ҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����10:13:15
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
	public ActionForward cdjgSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pathForXh = "rcsw_cdgl_cdjg.do?method=cdjgSq";
		String pathForCd = "rcsw_cdgl_cdjg.do?method=cdjgSq";
		CdjgForm model = (CdjgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			model.setBmlbdm(xsjbxx.get("xydm"));//Ĭ������ѧ������ѧԺ
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			pathForCd = pathForCd + (StringUtil.isNull(model.getXh()) ? "" : ("&xh=" + model.getXh()));
		}
		HashMap<String , String> cdxx = new HashMap<String , String>();
		if(!StringUtil.isNull(model.getCdid())){
			cdxx = cdxxwhService.getCdxxByCdid(model.getCdid());
			pathForXh = pathForXh + (StringUtil.isNull(model.getCdid()) ? "" : ("&cdid=" + model.getCdid()));
		}
		request.setAttribute("cdxx", xgxt.utils.String.StringUtils.formatData(cdxx)); //��ѯ������Ϣ
		// �����б�
		List<HashMap<String, String>> bmList = dwwhService.getBmList();
		request.setAttribute("path", URLEncoder.encode(pathForXh, "gbk"));
		request.setAttribute("pathForCd", URLEncoder.encode(pathForCd, "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", bmList);
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cdjgSq");
	}
	
	/**
	 * 
	 * @����:�������泡�ؽ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:19:28
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ�ý��-����XH:{xh},CDMC:{cdmc},SQSJDKSSJ:{sqsjdkssj},SQSJDJSSJ:{sqsjdjssj},CYRS:{cyrs},FZRXM:{fzrxm},FZRLXFS:{fzrlxfs},SQLY:{sqly}")
	public ActionForward addCdjgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CdjgForm model = (CdjgForm) form;
		JSONObject message;
		
		model.setSjly("0"); //������Դ
		
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			
			boolean checkConditions = service.checkSqSjd(model);

			if(!checkConditions){
				message =  getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);	
				message.put("flag", "checkerror");
				response.getWriter().print(message);
				return null;
			}else{
				super.resetToken(request);
			}
		}
		
		boolean isSuccess = service.runInsert(model);
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴���ؽ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����09:11:20
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
	public ActionForward cdjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdjgForm model  = (CdjgForm) form;
		String jgid = model.getJgid(); //��ȡ���id
		HashMap<String , String> cdjgxx = service.getCdjgxx(jgid);//��ȡ���ؽ����Ϣ
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		String path = "rcsw_cdgl_cdjg.do?method=cdjgCk";
		request.setAttribute("cdjgxx", xgxt.utils.String.StringUtils.formatData(cdjgxx)); 
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("cdjgCk");
	}
	
	/**
	 * 
	 * @����:ҳ��dispatcher ���ؽ���޸�ҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����10:13:15
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
	public ActionForward cdjgXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "rcsw_cdgl_cdjg.do?method=cdjgXg";
		CdjgForm model = (CdjgForm) form;
		String jgid = model.getJgid(); //��ȡ����id
		if(StringUtils.isNotBlank(jgid)){
			CdjgForm dataModel = service.getModel(jgid);
			xgxt.utils.String.StringUtils.formatData(dataModel);
			BeanUtils.copyProperties(model, dataModel);
		}

		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		if(!StringUtil.isNull(model.getCdid())){
			HashMap<String , String> cdxx = cdxxwhService.getCdxxByCdid(model.getCdid());
			request.setAttribute("cdxx", xgxt.utils.String.StringUtils.formatData(cdxx)); //��ѯ������Ϣ
		}
		// �����б�
		List<HashMap<String, String>> bmList = dwwhService.getBmList();
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", bmList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("cdjgXg");
	}
	
	/**
	 * 
	 * @����:���³��ؽ��action
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-29 ����10:04:56
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ�ý��-�޸�JGID:{jgid},XH:{xh},CDMC:{cdmc},SQSJDKSSJ:{sqsjdkssj},SQSJDJSSJ:{sqsjdjssj},CYRS:{cyrs},FZRXM:{fzrxm},FZRLXFS:{fzrlxfs},SQLY:{sqly}")
	public ActionForward cdjgXgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdjgForm model = (CdjgForm) form;
		
		model.setSqly(HtmlUtil.xmlZy(model.getSqly()));
		
		boolean isSuccess = service.runUpdate(model); //�������ݿ�
		
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ�����ؽ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-30 ����11:19:23
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ�ý��-ɾ��CDJGIDS:{cdjgids}")
	public ActionForward deleteCdjgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cdjgids = request.getParameter("cdjgids"); //ɾ����ids
		int isSuccess = 0;
		if(StringUtils.isNotBlank(cdjgids)){
			isSuccess = service.runDelete(cdjgids.split(","));
		}
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);	
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-30 ����04:59:15
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdjgForm model = (CdjgForm) form;
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
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2017-12-18 ����05:57:18
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			CdjgForm model = (CdjgForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
				boolean flag = service.updatePjxx(model);
				String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "rcsw_cdgl_cdjg?method=pj";
		request.setAttribute("path", path);
		return mapping.findForward("pj");
	}
/**
 * @description	�� �Ƿ�������
 * @author 		�� CP��1352��
 * @date 		��2017-12-18 ����08:07:39
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward sfpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdjgForm model = (CdjgForm) form;
		boolean flag = service.isExistPj(model);
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", flag?"0":"1");
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
}
