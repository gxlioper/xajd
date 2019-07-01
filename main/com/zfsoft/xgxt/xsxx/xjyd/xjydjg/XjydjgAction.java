package com.zfsoft.xgxt.xsxx.xjyd.xjydjg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import xsgzgl.gygl.cwgl.CwglService;

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
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xjyd.XjydService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-ѧ���춯
 * @�๦������:ѧ���춯���
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-27
 * @�汾�� V1.0
 */
public class XjydjgAction extends SuperAction {
	
	/**
	 * ���幫Ԣ����ѧ���춯���Դӻ�����Ϣ���л�ȡѧ����Ϣ
	 */
	private static final String XJYDSQ = "xjydsq";
	
	/**
	 * ����ʯ�ʹ�ѧ����༶�������춯���
	 */
	public static final String DBSYDX_TSBJTZ      = "99";
	
	private XjydjgService service = new XjydjgService();

	private XsxxService xsxxService = new XsxxService();
	
	private static List<HashMap<String, String>> jbxxList = null;

	private BdpzService bdpzService = new BdpzService();
	
	private static final String url = "xjyd_xjydjg.do";
	
	/**
	 * @����:ѧ���춯����б�
	 * @���ߣ� qilm
	 * @ʱ�䣺 2013-9-27
	 * @�汾�� V1.0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward xjydjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydjgForm myForm = (XjydjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xjyd_xjydjg.do");
			myForm.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xjyd_xjydjg.do");

		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xxdm", Base.xxdm);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydjgList");
	}

	/**
	 * 
	 * @����: �鿴ѧ���춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����09:58:06
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
	public ActionForward ckXsydInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XjydjgForm myForm = (XjydjgForm) form;
		
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		request.setAttribute("jbxxList", jbxxList);
		
		// ѧ�������һ��ѧ���춯��Ϣ
		HashMap<String, String> xsydInfo = service.getXsydInfo(myForm);
		request.setAttribute("xsydInfo", StringUtils.formatData(xsydInfo));

		// ѧ������ĸ���ѧ���춯��Ϣ
		List<HashMap<String, String>> xsYdList = service.getXsydList(
				myForm);
		request.setAttribute("xsYdList", xsYdList);
		
		return mapping.findForward("ckXsydInfo");
	}
	
	/**
	 * 
	 * @����:���Ӯ��ӽY��
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 * 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯���-����:XH:{xh},XN:{xn},XQ:{xq},ydlbdm:{ydlbdm},XJYDSJ:{xjydsj},XJYDWH:{xjydwh}")
	public ActionForward xjydjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydjgForm myForm = (XjydjgForm) form;
		User user = getUser(request);
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			boolean isExist = !"".equals(service.queryExistId(myForm, "add"));
			if (isExist) {
				response.getWriter().print(getJsonMessage("ѧ����ѧ���춯�ĺ��Ѵ��ڣ�"));
				return null;
			}

			String guid = UniqID.getInstance().getUniqIDHash();
			myForm.setXjydjgid(guid);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());
			myForm.setJrsj(date);
			myForm.setSqr(user.getUserName());

			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());

			myForm.setYdqnj(xsjbxx.get("nj"));
			myForm.setYdqxydm(xsjbxx.get("xydm"));
			myForm.setYdqzydm(xsjbxx.get("zydm"));
			myForm.setYdqbjdm(xsjbxx.get("bjdm"));
			myForm.setYdqxjlb(xsjbxx.get("xjlbdm"));//ѧ��������
			myForm.setYdqxjlbmc(xsjbxx.get("xjlbmc"));//ѧ�����
			myForm.setYdqsfyxjmc(xsjbxx.get("xjztm"));//�Ƿ���ѧ��
			myForm.setYdqsfzxmc(xsjbxx.get("sfzx"));//�Ƿ���У

			myForm.setYdqzybjdm(xsjbxx.get("zybj"));//רҵ�༶
			myForm.setYdqsydm(xsjbxx.get("sydm"));//��Ժ


			//����ʦ����ѧ���Ի��ֶΣ��Ƿ�ʦ������
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				myForm.setSfsfs(xsjbxx.get("sfsfs"));//�Ƿ�ʦ����
			}
			
			//������Դ
			myForm.setSjly(Constants.SJLY_JGK);

			// ѧУ����
			String xxdm = Base.xxdm;	
			
			// �|��ʯ�ʹ�W�����Дࣨ�Ƿ�����⮐����������ֻ���༶����רҵ����
			if(DBSYDX_TSBJTZ.equals(myForm.getYdlbdm()) &&  Globals.XXDM_DBSYDX.equals(xxdm)){
				
				// רҵѧԺ����
				myForm.setYdhxydm(xsjbxx.get("xydm"));
				myForm.setYdhzydm(xsjbxx.get("zydm"));
				
			}
			
			boolean result = service.runInsert(myForm);
			if(result){
				result = service.updateXsxx(myForm);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			String message =  MessageUtil.getText(messageKey);
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			
			if("12309".equalsIgnoreCase(Base.xxdm)) {//��ѯ��λ��Ϣ�����������Ի�
				CwglService cwglService=new CwglService();
				Map<String, String> cwglmap = cwglService.getXsxx(myForm.getXh(),request);
				if(cwglmap.get("xh") != null){
					map.put("sfycw", "y");
				}
			}
			
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
		}

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
		
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		
		// ѧ��������Ϣ
		String path = "xjydjg.do?method=xjydjgAdd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		//ѧ�����LIST
		XjydService serviceYd = new XjydService();
		List<HashMap<String,String>> xjlbList = serviceYd.getXjlbList("0","");
		request.setAttribute("xjlbList", xjlbList);
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			//�춯ԭ��
			XjydsqService xjydsqService = new XjydsqService();
			List<HashMap<String,String>> ydyyList = xjydsqService.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//ά����ԴѧУ/ȥ��ѧУ
			List<HashMap<String,String>> lyqxxxList = xjydsqService.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
		}
		
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xxdm", Base.xxdm);

		FormModleCommon.commonRequestSet(request);
		//�꼶��ѧԺ��רҵ���༶
		FormModleCommon.setAllNjXyZyBjList(request);
		this.saveToken(request);
		return mapping.findForward("xjydjgAdd");
	}

	/**
	 * 
	 * @����: �޸ļӮ��ӽY��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-3 ����03:28:16
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
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯���-�޸�XJYDJGID:{xjydjgid},XH:{xh},XN:{xn},XQ:{xq},YDLBDM:{ydlbdm},XJYDSJ:{xjydsj},XJYDWH:{xjydwh}")
	public ActionForward xjydjgUpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydjgForm myForm = (XjydjgForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean isExist = !"".equals(service.queryExistId(myForm, "update"));
			if (isExist) {
				response.getWriter().print(getJsonMessage("ѧ����ѧ���춯�ĺ��Ѵ��ڣ�"));
				return null;
			}

			// ѧ���춯�����ʷȡ��
			XjydjgForm jgOld = service.getModel(myForm.getXjydjgid());
			
			//����ѧ��������Ϣ
			myForm.setYdqnj(jgOld.getYdqnj());
			myForm.setYdqxydm(jgOld.getYdqxydm());
			myForm.setYdqzydm(jgOld.getYdqzydm());
			myForm.setYdqbjdm(jgOld.getYdqbjdm());
			myForm.setYdqxjlb(jgOld.getYdqxjlb());			
			myForm.setYdqxjlbmc(jgOld.getYdqxjlbmc());
			myForm.setYdqsfyxjmc(jgOld.getYdqsfyxjmc());
			myForm.setYdqsfzxmc(jgOld.getYdqsfzxmc());
			//����ʦ����ѧ���Ի��ֶΣ��Ƿ�ʦ������
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				myForm.setSfsfs(jgOld.getSfsfs());//�Ƿ�ʦ����
			}
			
			// ����
			boolean result = service.runUpdate(myForm);
			if(result){
				// ����ѧ����Ϣ
				result = service.updateXsxx(myForm);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XjydjgForm model = service.getModel(myForm.getXjydjgid());
		
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
				
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		
		// ѧ��������Ϣ
		String path = "xjydjg.do?method=xjydjgUpd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		//ѧ�����LIST
		XjydService serviceYd = new XjydService();
		List<HashMap<String,String>> xjlbList = serviceYd.getXjlbList("0","");
		request.setAttribute("xjlbList", xjlbList);
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			//�춯ԭ��
			XjydsqService xjydsqService = new XjydsqService();
			List<HashMap<String,String>> ydyyList = xjydsqService.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//ά����ԴѧУ/ȥ��ѧУ
			List<HashMap<String,String>> lyqxxxList = xjydsqService.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
		}
		
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());

		FormModleCommon.commonRequestSet(request);
		//�꼶��ѧԺ��רҵ���༶
		FormModleCommon.setAllNjXyZyBjList(request);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		
		return mapping.findForward("xjydjgUpd");
	}
	/**
	 * 
	 * @����: ɾ��ѧ���춯���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����08:59:23
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
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯���-ɾ��VALUES:{values}")
	public ActionForward xjydjgDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = 0;
			boolean bolFlg = service.rollBackXsxx(values);
			if(bolFlg){
				num =  service.runDelete(values.split(","));
			}
			String message = bolFlg && num >0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ѧ���춯ѧ����Ϣ�鿴
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
	public ActionForward xjydXsInfoCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydjgForm myForm = (XjydjgForm) form;

		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
				
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		
		// ѧ��������Ϣ
		String path = "xjydjg.do?method=xjydjgUpd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		// ѧ�������һ��ѧ���춯��Ϣ
		HashMap<String, String> xsydInfo = service.getXsydInfo(myForm);
		request.setAttribute("xsydInfo", xsydInfo);

		// ѧ������ĸ���ѧ���춯��Ϣ
		List<HashMap<String, String>> xsYdList = service.getXsydList(
				myForm);
		request.setAttribute("xsYdList", xsYdList);
		
		return mapping.findForward("xjydXsInfoCk");
	}

	/**
	 * 
	 * @����: ѧ���춯ѧ����Ϣ�鿴
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
	public ActionForward xjydjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydjgForm myForm = (XjydjgForm) form;
		XjydjgForm model = service.getModel(myForm.getXjydjgid());
		
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
				
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		
		// ѧ��������Ϣ
		String path = "xjydjg.do?method=xjydjgUpd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("xjydjgCk");
	}
	
	/**
	 * @����:ѧ���춯��������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����11:04:03
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
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯���-�����춯XZXSKEY:{xzxskey}")
	public ActionForward xjydPlcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydjgForm myForm = (XjydjgForm) form;
		User user = getUser(request);
		
		// ����
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
		
			boolean result = service.xjydPlcl(myForm , user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else if ("close".equalsIgnoreCase(myForm.getType())) {
			
			if(StringUtils.isNotNull(myForm.getXzxsKey())){
				// ȡ��ѧ���б�
				XsxxService xsService = new XsxxService();

				// ɾ����ʱ��
				boolean result = xsService.runDelSelectAll(myForm.getXzxsKey());
				String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
						: MessageKey.SYS_DEL_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}

		
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
				
		// ��ѡ��ѧ��
		if(StringUtils.isNotNull(myForm.getXzxsKey())){
			XsxxService service = new XsxxService();
			int counts = service.getSelectedCount(myForm.getXzxsKey());
			//�趨��ѡ��ѧ����
			request.setAttribute("yxzxss", counts+"");
		}else{

			//�趨��ѡ��ѧ����
			request.setAttribute("yxzxss", "0");
		}
		//�趨xzxsKey
		request.setAttribute("xzxsKey", myForm.getXzxsKey());
		
		//ѧ�����LIST
		XjydService serviceYd = new XjydService();
		List<HashMap<String,String>> xjlbList = serviceYd.getXjlbList("0","");
		request.setAttribute("xjlbList", xjlbList);
		
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());

		// ѧ��������Ϣ
		String path = "xjydjg.do?method=xjydPlcl";
		request.setAttribute("path", path);
		
		FormModleCommon.commonRequestSet(request);
		//�꼶��ѧԺ��רҵ���༶
		FormModleCommon.setAllNjXyZyBjList(request);
		
		return mapping.findForward("xjydPlcl");
	}
	
	/**
	 * 
	 * @����: ѧ���춯��˵���
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
		XjydjgForm model = (XjydjgForm) form;
		XjydjgService service = new XjydjgService();

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
		String value = request.getParameter("value");
		List<File> files = new ArrayList<File>();
		if(value != null){
			for(String id:value.split(",")){
				HashMap<String , String> modelMap = service.getModelInfoMap(id);
				String dybb = modelMap.get("dybb");
				if(StringUtil.isNull(dybb)){
					String msg = "δ���õǼǱ������ϵ����Ա��";
					request.setAttribute("yhInfo", msg);
					return new ActionForward("/yhInfo.do", false);
				}
				Map<String , Object> data = printData(modelMap);
				File file = BbdmUtils.getSigleFile(dybb, data);
				files.add(file);
			}
			if(value.split(",").length == 1){
				FileUtil.outputWord(response, files.get(0));
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
		data.putAll(xsjbxx);  //ѧ����Ϣ
		data.putAll(modelMap); //ѧ���춯
		//data.put("sqly", HtmlUtil.xmlZy(modelMap.get("sqly")));
		data.put("xxmc", Base.xxmc); //ѧУ����
		data.put("nd", Base.currNd);
		//��ȡ���������
		String tbnf = modelMap.get("jrsj").substring(0,4);
		String tbyf = modelMap.get("jrsj").substring(5,7);
		String tbts = modelMap.get("jrsj").substring(8,10);
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
	
}
