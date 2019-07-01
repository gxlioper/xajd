/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-12 ����02:43:30 
 */  
package com.zfsoft.xgxt.khgl.khpf;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.khgl.khxmgl.KhxmglService;
import com.zfsoft.xgxt.xljkwzdx.common.StringTools;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: action
 * @���ߣ�cq [����:785]
 * @ʱ�䣺 2015-8-12 ����02:43:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KhpfAction extends SuperAction<KhpfForm, KhpfService> {
	
	private KhpfService service = new KhpfService();
	
	private static final String url = "khgl_khpf.do";
	private static final String pfUrl = "khgl_pf_hncs.do";
	private static final String XSZP = "�ۺ����ʲ���";
	private static final String BZPF = "�ۺ����ʲ���";
	private static final String BZRPF = "�ۺ����ʲ���";
	private static final String PFLX_XSZP = "xszp";
	private static final String PFLX_BZPF = "bzpf";
	private static final String PFLX_BZRPF = "bzrpf";
	
	private KhxmglService khxmService = new KhxmglService();
	
	/**
	 * 
	 * @����:��������list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-12 ����04:13:37
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

	public ActionForward khpflList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			long a=System.currentTimeMillis();
			List<HashMap<String, String>> resultList = service.getKhpflList(
					model, user);
			System.out.println("\rִ�к�ʱ : "+(System.currentTimeMillis()-a)/1000f+" �� \r");
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_khpf.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("khpflList");
	}
	
	
	
	/**
	 * 
	 * @����:��ʼ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-13 ����04:22:06
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
	public ActionForward quePf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		KhpfForm xmInfo = service.getXmKhb(model.getXmid(), model.getKhbid());
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			User user = getUser(request);
			 
			model.setXmid(xmInfo.getXmid());
			model.setXmmc(xmInfo.getXmmc());
			model.setKhbid(xmInfo.getKhbid());
			model.setKhbmc(xmInfo.getKhbmc());
			model.setKhlx(xmInfo.getKhlx());
			model.setSearchModel(searchModel);
			long a=System.currentTimeMillis();
			List<HashMap<String, String>> resultList = service.quePf(
					model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		BeanUtils.copyProperties(model, xmInfo);
		String path = "khgl_khpf.do";
		request.setAttribute("path", path);
		request.setAttribute("xmInfo", model);
		FormModleCommon.commonRequestSet(request);
		if(KhpfService.YHLX_JS.equals(model.getKhlx())){
			request.setAttribute("path", "khglKhpf.do?method=quePfJs");
			return mapping.findForward("quePfJs");
		}else{
			request.setAttribute("path", "khglKhpf.do?method=quePfXs");
			return mapping.findForward("quePfXs");
		}
	}
	
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-14 ����03:13:02
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
	public ActionForward addPf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
		// ��ѯ
			List<HashMap<String, String>> resultList = service.getPfList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if(Base.xxdm.equalsIgnoreCase("12309") || Base.xxdm.equalsIgnoreCase("33333")){//���������Ի�
			request.setAttribute("userStatus",user.getUserStatus());
			request.setAttribute("khlx", model.getKhlx());
			if(KhpfService.YHLX_JS.equals(model.getKhlx())){//������˶���Ϊ��ʦ				
				String yjjy = service.getKhYjjy(model, user);
				model.setYjjy(yjjy);
			}
		}
		HashMap<String,String> pfxxMap =service.getPfxxInfo(model.getXmid(), user);
		request.setAttribute("pfxxMap", pfxxMap);
		request.setAttribute("xmInfo", service.getXmInfo(model.getXmid()));
		request.setAttribute("ryInfo", service.getRyInfo(model.getKhdxr()));
		request.setAttribute("model", model);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("addPf");
	}
	
	public ActionForward addKhPf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
		// ��ѯ
			List<HashMap<String, String>> resultList = service.getKhPfList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		HashMap<String,String> pfxxMap =service.getPfxxInfo(model.getXmid(), user);
		request.setAttribute("pfxxMap", pfxxMap);
		request.setAttribute("xmInfo", service.getXmInfo(model.getXmid()));
		request.setAttribute("ryInfo", service.getRyInfo(model.getKhdxr()));
		request.setAttribute("model", model);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("addKhPf");
	}
	/**
	 * 
	 * @����:�޸�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-1-13 ����08:57:47
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
	public ActionForward xgpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
		// ��ѯ
			List<HashMap<String, String>> resultList = service.getKhPfList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		HashMap<String,String> pfxxMap =service.getPfxxInfo_HNCS(model.getXmid(), model.getKhdxr());
		request.setAttribute("pfxxMap", pfxxMap);
		request.setAttribute("xmInfo", service.getXmInfo(model.getXmid()));
		request.setAttribute("ryInfo", service.getRyInfo(model.getKhdxr()));
		request.setAttribute("model", model);
		return mapping.findForward("xgpf");
	}
	/**
	 * 
	 * @����:���ز�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-12-23 ����06:39:03
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
	public ActionForward getCpcjWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhpfForm model = (KhpfForm) form;
		User user =getUser(request);
		File wordFile = getWord(model,user);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	public ActionForward getCpcjWordZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhpfForm model = (KhpfForm) form;
		String value = request.getParameter("value");
		User user =getUser(request);
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				model.setKhdxr(values[i]);
				File file = getWord(model,user);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	private File getWord(KhpfForm model,User user) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String,String> zpxx = service.getZpxx(model);
		List<HashMap<String, String>> resultList = service.getPfTjList(model,user);
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getKhdxr());
		data.put("zpxx", zpxx);
		data.put("rs", xsjbxx);
		data.put("xn", Base.currXn);
		data.put("xxmc", Base.xxmc);
		data.put("pfList", resultList);
		File wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "khgl","cptj_11527.xml", xsjbxx.get("xh") + "-" +xsjbxx.get("xm"));
		return wordFile;
	}
	/**
	 * 
	 * @����:ѧ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-12-22 ����10:53:16
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
	public ActionForward xszp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		User user = getUser(request);
		model.setPflx(PFLX_XSZP);
		model.setKhdxr(user.getUserName());
		if (QUERY.equalsIgnoreCase(model.getType())) {
		// ��ѯ
			List<HashMap<String, String>> resultList = service.getKhPfList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		HashMap<String,String> xmMap = khxmService.getKhxmByXmmc(XSZP);
		BeanUtils.copyProperties(model, xmMap);
		HashMap<String,String> pfxxMap =service.getPfxxInfo_HNCS(model.getXmid(), model.getKhdxr());
		request.setAttribute("xmInfo", xmMap);
		request.setAttribute("pfxxMap", pfxxMap);
		request.setAttribute("ryInfo", service.getRyInfo(model.getKhdxr()));
		request.setAttribute("model", model);
		return mapping.findForward("addKhPf");
	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-12-22 ����04:36:29
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
	
	public ActionForward bzpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		model.setPflx(PFLX_BZPF);
		HashMap<String,String> xmMap = khxmService.getKhxmByXmmc(BZPF);
		BeanUtils.copyProperties(model, xmMap);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			User user = getUser(request);
			 
			model.setXmid(model.getXmid());
			model.setXmmc(model.getXmmc());
			model.setKhbid(model.getKhbid());
			model.setKhbmc(model.getKhbmc());
			model.setKhlx(model.getKhlx());
			model.setSearchModel(searchModel);
			long a=System.currentTimeMillis();
			List<HashMap<String, String>> resultList = service.quePf(
					model, user);
			System.out.println("\rִ�к�ʱ : "+(System.currentTimeMillis()-a)/1000f+" �� \r");
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khglKhpf.do?method=bzpf";
		request.setAttribute("path", path);
		request.setAttribute("xmInfo", model);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", pfUrl);
		return mapping.findForward("bzpf");
	}
	/**
	 * 
	 * @����:����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-12-22 ����05:42:23
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
	
	public ActionForward bzrpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		HashMap<String,String> xmMap = khxmService.getKhxmByXmmc(BZRPF);
		BeanUtils.copyProperties(model, xmMap);
		model.setPflx(PFLX_BZRPF);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			User user = getUser(request);
			 
			model.setXmid(model.getXmid());
			model.setXmmc(model.getXmmc());
			model.setKhbid(model.getKhbid());
			model.setKhbmc(model.getKhbmc());
			model.setKhlx(model.getKhlx());
			model.setSearchModel(searchModel);
			long a=System.currentTimeMillis();
			List<HashMap<String, String>> resultList = service.quePf(
					model, user);
			System.out.println("\rִ�к�ʱ : "+(System.currentTimeMillis()-a)/1000f+" �� \r");
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		
		String path = "khglKhpf.do?method=bzrpf";
		request.setAttribute("path", path);
		request.setAttribute("xmInfo", model);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", pfUrl);
		return mapping.findForward("bzrpf");
		
	}
	
	
	/**
	 * 
	 * @����:���濼�˷���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-15 ����11:19:39
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
	public ActionForward saveKhfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		User user = getUser(request);
		
		if(model.getZbmxid()==null || (model.getZbmxid().length()<32 && !Globals.XXDM_HNCSXY.equals(Base.xxdm))){
			response.getWriter().print(getJsonMessageByKey(MessageKey.KHGL_KHPF_FSBC_FAIL));
			return null;
		}
		
		boolean result = service.saveKhfs(model, user);
		if (!result){
			//���ʧ�ܣ�����ʾ
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		
		return null;
	}
	
	public ActionForward saveBz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		
		User user = getUser(request);
		
		boolean result = service.saveBz(model, user);
		
		if (!result){
			//���ʧ�ܣ�����ʾ
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		
		return null;
	}
	
	/** 
	 * @����:���濼���������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-4 ����03:18:27
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
	public ActionForward saveKhYjjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		
		User user = getUser(request);
		
		boolean result = service.saveKhYjjy(model, user);
		
		if (!result){
			//���ʧ�ܣ�����ʾ
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-17 ����11:40:27
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
	public ActionForward checkIsCanSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		User user = getUser(request);
		
		boolean checkIsCanSubmit = service.checkIsCanSubmit(model, user);
		
		response.getWriter().print(checkIsCanSubmit);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-17 ����11:50:48
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
	
	public ActionForward submitTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		
		User user = getUser(request);
		boolean submitTj =true;
		if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
			submitTj = service.submitKhpf(model, user);
		}else{
			submitTj = service.submitBjzcf(model, user);
		}
		
		response.getWriter().print(getJsonMessageByKey(submitTj ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL));
		
		return null;
	}
	/**
	 * 
	 * @����:���ͨ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-1-11 ����04:45:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		KhpfForm model = (KhpfForm) form;
		
		User user = getUser(request);
	    boolean shjg;
		try {
			shjg = service.sh(model, user);
			response.getWriter().print(getJsonMessageByKey(shjg ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL));
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			logger.debug(e.getMessage());
		}
		return null;
	}

	/**
	 *  �������.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-19 10:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward cx(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response){

		KhpfForm model = (KhpfForm) form;

		User user = getUser(request);
		boolean cxjg;
		try {
			cxjg = service.cx(model, user);
			response.getWriter().print(getJsonMessageByKey(cxjg ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL));
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			logger.debug(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * @����:��ѯ��һ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-17 ����03:44:31
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
	
	public ActionForward submitNext(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		User user = getUser(request);
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);

		KhpfForm xmInfo = service.getXmKhb(model.getXmid(), model.getKhbid());
		model.setKhlx(xmInfo.getKhlx());

		if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
			model.setSftj("1");
		}

		List<HashMap<String, String>> resultList = service.quePf(model, user);
		String khdxr = "null";
		if(null != resultList && resultList.size()!=0){
			if(KhpfService.YHLX_XS.equals(model.getKhlx())||Globals.XXDM_HNCSXY.equals(Base.xxdm)){ 
				khdxr = resultList.get(0).get("xh");	//���˶���Ϊѧ��
			}else{
				khdxr = resultList.get(0).get("zgh");	//���˶���Ϊ��ʦ
			}
		}
		response.getWriter().print(khdxr);
		
		return null;
	}

	
	/**
	 * 
	 * @����:��������鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-18 ����10:42:59
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
	
	public ActionForward viewPf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KhpfForm model = (KhpfForm) form;
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
		// ��ѯ
			List<HashMap<String, String>> resultList = service.getPfList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if(Base.xxdm.equalsIgnoreCase("12309") || Base.xxdm.equalsIgnoreCase("33333")){//���������Ի����㽭��ҵְҵ����ѧԺ���Ի�
			request.setAttribute("userStatus", user.getUserStatus());
			if(service.isJs(model)){//������˶���Ϊ��ʦ���ҵ�¼�û�Ϊѧ���Ļ�
				if(Base.xxdm.equalsIgnoreCase("33333") && "stu".equals(user.getUserStatus())){
					model.setPflx("1");//��������Ϊѧ��
				}
				request.setAttribute("khlx", KhpfService.YHLX_JS);
				String yjjy = StringTools.StringToText(service.getKhYjjy(model, user));
				model.setYjjy(yjjy);
			}
		}
		request.setAttribute("xmInfo", service.getXmInfo(model.getXmid()));
		request.setAttribute("ryInfo", service.getRyInfo(model.getKhdxr()));
		request.setAttribute("model", model);
		return mapping.findForward("viewPf");
	}
	
	/**
	 * 
	 * @����: ȡ���ύ��¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-7 ����03:25:35
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
	public ActionForward cancelTjRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		KhpfForm model = (KhpfForm) form;
		User user = getUser(request);
		model.setPfr(user.getUserName());
		boolean rs = service.cancelTjRecord(model);
		String message = rs ? MessageKey.SYS_CANCEL_SUCCESS:MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}

	/**
	 *  ��ת������������������ҳ��.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 11:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw Exception
	 */
	public ActionForward scbzpfmm(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception{

		User user = getUser(request);
		List<HashMap<String,String>> bzpfmmList = service.getBzpfmmList(user);
		request.setAttribute("bzpfmmList",bzpfmmList);
		return mapping.findForward("scbzpfmm");
	}

	/**
	 *  ���ɰ�����������ı���.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 11:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw Exception
	 */
	public ActionForward scbzpfmmSave(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception{

		String [] usernameArr = request.getParameterValues("username");
		String [] passwordArr = request.getParameterValues("password");

		boolean rs = service.scbzpfmmSave(usernameArr,passwordArr);
		String message = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}

	/**
	 *  �༶�����ɼ��ܱ�����.
	 *  <p>���ϳ���ѧԺ</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-21 19:31
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward bjzccjzbxz(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response) throws Exception{

		KhpfForm model = (KhpfForm) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//��ѯ�����м�¼������ҳ
		List<HashMap<String,String>> resultList = service.getBjzccjzbList(model, user);

		//���ɵ����ļ�����
		File [] files = service.getBjzccjzbFiles(resultList);
		if(files != null&&files.length != 0){
			if(files.length == 1){
				FileUtil.outputExcel(response, files[0]);
			}else {
				File zipFile = ZipUtil.zip(files);
				FileUtil.outputZip(response, zipFile);
			}
		}
		return null;
	}

}
