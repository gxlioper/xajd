/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-13 ����09:54:34 
 */  
package com.zfsoft.xgxt.dekt.qnzyhd;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����־Ը�action(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-7-13 ����09:54:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QnzyhdAction extends SuperAction<QnzyhdForm, QnzyhdService>{
	public static final String OVERSIZE = "oversize";
	public static final String ERRORTYPE = "errortype";
	public static final String YFB = "1";
	public static final String SHZ = "0";
	private QnzyhdService service = new QnzyhdService();
	
	private static final String url = "dekt_qnzyhd_hdlb.do";
	
	private static final String YfbUrl = "dekt_qnzyhd_yfbhd.do";
	
	private static final String YcjUrl = "dekt_qnzyhd_ycjhd.do";
	
	private static final String HDFBSHUrl = "dekt_qnzyhd_hdfbsh.do";
	
	/** 
	 * @����:������б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-17 ����01:40:42
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
	public ActionForward zyhdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "dekt_qnzyhd_hdlb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyhdList");
	}
	
	/** 
	 * @����:����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-17 ����03:34:05
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
	public ActionForward addHdfb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HashMap<String,String>> list = service.getFwlxList();
		request.setAttribute("fwlxList", list);
		request.setAttribute("xyList",Base.getXyNewList());
		return mapping.findForward("addHdfb");
	} 
	
	/** 
	 * @����:����(������һ�仰�����������������)
	 * @���ߣ��첽�ϴ��ļ�[���ţ�1282]
	 * @���ڣ�2017-7-18 ����11:25:04
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
	public ActionForward upLoadPic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyhdForm model = (QnzyhdForm)form;
		String size = model.getMaxsize();
		String accept = model.getAccept();
		FileUtil.conversionFormFile(model.getFile());
		String contextPath = request.getSession().getServletContext().getRealPath("");
		String dir = (contextPath.substring(0,contextPath.lastIndexOf(File.separator))) + "\\pic";
		String lastPath = model.getLastPath();
		String str = service.upLoadFile(model.getFile(),lastPath,dir, size, accept);
		JSONObject j = new JSONObject();
		if(str.equals(OVERSIZE) || str.equals(ERRORTYPE)){
			j.put("result", "false");
			j.put("message", str);
		}else{
			j.put("result", "true");
			j.put("path", str);
			//�ϴ�������ͬʱ���浽���ݿ⣬ʵ��ʵʱ���£���ְҵ����ѧԺ��������Ҫ������������ϴ�������
			if(null != model.getHdid()){
				model.setFjpath(str);
				service.updateFbShzt(model);
			}
		}
		response.getWriter().print(j);
		return null;
	}
	
	/** 
	 * @����:���ӱ���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-18 ����03:17:43
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
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyhdForm model = (QnzyhdForm)form;
		User user = getUser(request);
		String id = UniqID.getInstance().getUniqIDHash();
		model.setHdid(id);
		model.setHdfbr(user.getUserName());//���÷�����
		model.setFbzt(YFB);//���÷���״̬
		model.setShzt(SHZ);//�������״̬Ϊ�����
		String content = DealString.toGBK(request.getParameter("editorid"));//���û����
		model.setHdxq(content);//���û����
		boolean result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
	
	/** 
	 * @����:����־Ը��Ա����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-19 ����09:34:05
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
	public ActionForward qnhdrybm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm)form;
		HashMap<String,String> data = service.getQnzyhdInfo(model.getHdid());
		request.setAttribute("data", data);
		return mapping.findForward("qnhdrybm");
	}
	
	/** 
	 * @����:�ѷ�����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-20 ����02:37:29
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
	@SystemAuth(url = YfbUrl)
	public ActionForward yfbhdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getYfbhdList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "dekt_qnzyhd_yfbhd.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yfbhdList");
	}
	
	/** 
	 * @����:�޸Ļ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-20 ����04:15:24
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
	@SystemAuth(url = YfbUrl,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateHdfb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		List<HashMap<String,String>> list = service.getFwlxList();
		request.setAttribute("fwlxList", list);
		HashMap<String,String> data = service.getQnzyhdInfo(model.getHdid());
		request.setAttribute("data", data);
		List<String> xydms = service.getGlXy(model);
		model.setXydms(xydms.toArray(new String[]{}));
		request.setAttribute("xyList",Base.getXyNewList());
		return mapping.findForward("updateHdfb");
	}
	
	/** 
	 * @����:�޸ı��淢���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-20 ����04:47:38
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
	public ActionForward updateBcHdfb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if(StringUtils.isNull(model.getType())){		
			String content = DealString.toGBK(request.getParameter("editorid"));//���û����
			model.setHdxq(content);
		}
		if(StringUtils.isNotNull(model.getOldPath()) && StringUtils.isNotNull(model.getLastPath())){//ɾ��ԭ�еĸ���ͼƬ
			String contextPath = request.getSession().getServletContext().getRealPath("");
			String oldPath = (contextPath.substring(0,contextPath.lastIndexOf("\\"))) + model.getOldPath().replaceAll("/", "\\\\");
			model.setOldPath(oldPath);
		}
		//Ĭ��Ϊ�ύ״̬
		model.setShzt(SHZ);
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:�Ѳμӻ�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-24 ����02:18:05
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
	@SystemAuth(url = YcjUrl)
	public ActionForward ycjhdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getYcjhdList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "dekt_qnzyhd_ycjhd.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ycjhdList");
	}
	
	/** 
	 * @����:��������list(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����03:47:28
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
	@SystemAuth(url = HDFBSHUrl)
	public ActionForward hdfbshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getfbshList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "dekt_qnzyhd_hdfbsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdfbshList");
	}
	
	/** 
	 * @����:�걨��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����05:49:28
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
	public  ActionForward sbDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		HashMap<String,String> map = service.getQnzyhdInfo(model.getHdid());
		request.setAttribute("data", map);
		return mapping.findForward("sbDgsh");
	} 
	
	/** 
	 * @����:���浥�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����06:51:08
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
	public  ActionForward BcDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		boolean result = service.updateFbShzt(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
	
	/** 
	 * @����:�����������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����07:11:16
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
	public  ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		boolean result = service.cxFbsh(model.getIds());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:�걨�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����07:34:06
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
	public ActionForward sbPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if(SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.plshFb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("sbPlsh");
	}
	
	/** 
	 * @����:�������״̬(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-11 ����02:49:19
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
	public ActionForward bgFb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		boolean result = service.updateFbShzt(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	
}
