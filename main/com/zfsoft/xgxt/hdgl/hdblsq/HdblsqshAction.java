/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdblsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxService;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.hdgl.cssz.CsszForm;
import com.zfsoft.xgxt.hdgl.cssz.CsszService;
import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @className	�� HdblsqshAction
 * @description	�� TODO(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-16 ����05:33:56
 * @version 	V1.0 
 */

public class HdblsqshAction extends SuperAction<HdblsqshForm, HdblsqshService>{
	
	private static final String url = "hdgl_hdbl_hdblsq.do";
	//ѧ��������Ϣ����
	private static List<HashMap<String, String>> jbxxList = null;
	
	public static String HDBL = "hdbl";
	
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(HDBL);
	}
	
	/**
	 * @description	�� ��¼�б�
	 * @author 		�� ������1282��
	 * @date 		��2018-1-16 ����05:46:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdblsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;		
		HdblsqshService service = new HdblsqshService();
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);		
		String path = "hdgl_hdbl_hdblsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdblsqList");
	}
	
	/**
	 * @description	�� ���ӻ��¼����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-17 ����02:16:53
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;
		HdblsqshService service = new HdblsqshService();
		HashMap<String,String> map = service.gethdblInfo(model);
		if(null != map){
			request.setAttribute("rs", map);
			if(StringUtils.isNotNull(model.getLy())){
				HashMap<String,String> map2 = service.gethdInfo(model.getHdid());
				map2.remove("fjpath");//���滻ԭ�и���
				map.putAll(map2);
			}
			BeanUtils.copyProperties(model, StringUtils.formatData(map));
			/*���ǩ*/
			if(StringUtils.isNotNull(map.get("hdbq"))){
				model.setHdbq(map.get("hdbq"));
				model.setHdbqs(map.get("hdbq").split(","));
				model.setHdbqmc(map.get("hdbqmc"));
			}
		/*������ǩ*/
			if(StringUtils.isNotNull(map.get("nlbq"))){
				model.setNlbq(map.get("nlbq"));
				model.setNlbqs(map.get("nlbq").split(","));
				model.setNlbqmc(map.get("nlbqmc"));
			}
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ�� ѧ��list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		String path = "hdgl_hdblsq.do?method=addHdblsq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		//��ȡ��ǰѧ��
		request.setAttribute("currXq", service.getCurrXq());
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		
		//������б�
		List<HashMap<String, String>> hdlxList = service.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);
		
		HdbljgService hdbljgService = new HdbljgService();
		/*�γ������б�*/
		List<HashMap<String,String>> jzlxList = hdbljgService.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//��ȡ��ǰѧ��
		request.setAttribute("currXq", service.getCurrXq());
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));

		/*������ְ��*/
		List<HashMap<String, String>> zjrzcList = service.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);

		/*�ܱ�ǩ�б�*/
		//List<HashMap<String, String>> activityLabelList = hdblsqshService.getActivityLabelList();
		List<HashMap<String, String>> activityLabelList = hdbljgService.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);



		//��ѡ�γ��б�
		List<HashMap<String,String>> zxckclxList = hdbljgService.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);

		/*������ǩ*/
		List<HashMap<String,String>> abilityLabelList = hdbljgService.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);
		
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return mapping.findForward("addHdblsq");
	}
	
	/**
	 * @description	�� �޸Ļ��¼
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����11:13:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;
		HdblsqshService service = new HdblsqshService();
		HashMap<String,String> map = service.gethdblInfo(model);
		if(null != map){
			request.setAttribute("rs", map);
			if(StringUtils.isNotNull(model.getLy())){
				HashMap<String,String> map2 = service.gethdInfo(model.getHdid());//��ȡѡ�еĻ��Ϣ
				map2.remove("fjpath");//���滻ԭ�и���
				map.putAll(map2);
			}
			BeanUtils.copyProperties(model, StringUtils.formatData(map));
			/*���ǩ*/
			if(StringUtils.isNotNull(map.get("hdbq"))){
				model.setHdbq(map.get("hdbq"));
				model.setHdbqs(map.get("hdbq").split(","));
				model.setHdbqmc(map.get("hdbqmc"));
			}
		/*������ǩ*/
			if(StringUtils.isNotNull(map.get("nlbq"))){
				model.setNlbq(map.get("nlbq"));
				model.setNlbqs(map.get("nlbq").split(","));
				model.setNlbqmc(map.get("nlbqmc"));
			}
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		String path = "hdgl_hdblsq.do?method=updateHdblsq&sqid=" + model.getSqid();
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);

		//������б�
		List<HashMap<String, String>> hdlxList = service.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);

		HdbljgService hdbljgService = new HdbljgService();

		/*���ǩ�б�*/
		//List<HashMap<String, String>> activityLabelList = hdblsqshService.getActivityLabelList();
		List<HashMap<String, String>> activityLabelList = hdbljgService.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);

		/*���������б�*/
		List<HashMap<String,String>> jzlxList = hdbljgService.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//��ѡ�γ��б�
		List<HashMap<String,String>> zxckclxList = hdbljgService.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);

		/*������ǩ*/
		List<HashMap<String,String>> abilityLabelList = hdbljgService.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);

		/*������ְ��*/
		List<HashMap<String, String>> zjrzcList = service.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);

		/*���ػ��ʽ*/
		request.setAttribute("hdxs", model.getHdxs());
		return mapping.findForward("updateHdblsq");
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-17 ����04:10:54
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;
		CsszService csszService = new CsszService();
		CsszForm csszForm = csszService.getCsszForm();
		if(null != csszForm){
			model.setSplc(csszForm.getBl());
		}
		HdblsqshService hdblsqshService = TransactionControlProxy.newProxy(new HdblsqshService());
		boolean result = false;
	    User user = getUser(request);
 		if(model.getType().equals("save")||model.getType().equals("submit")){
			result = hdblsqshService.saveSq(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			result = hdblsqshService.saveSqUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� �������¼����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����11:41:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshService service = new HdblsqshService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			HdblsqshForm model = new HdblsqshForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	���ύ���¼����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����11:50:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm) form;
		String values = request.getParameter("values");
		HdblsqshService service = new HdblsqshService();
		model.setSqid(values);
		CsszService csszService = new CsszService();
		CsszForm csszForm = csszService.getCsszForm();
		if(null != csszForm){
			model.setSplc(csszForm.getBl());
		}
		boolean result = service.submitHdblsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� ɾ�����¼����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����01:48:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		HdblsqshService service = new HdblsqshService();
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @description	�� �鿴
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����01:56:17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm) form;
		request.setAttribute("jbxxList", jbxxList);
		HdblsqshService service = new HdblsqshService();
		HashMap<String,String> map = service.gethdblInfo(model);
		if(null != map){
			BeanUtils.copyProperties(model, map);
			/*���ǩ*/
			if(StringUtils.isNotNull(map.get("hdbq"))){
				model.setHdbq(map.get("hdbq"));
				model.setHdbqs(map.get("hdbq").split(","));
				model.setHdbqmc(map.get("hdbqmc"));
			}
		/*������ǩ*/
			if(StringUtils.isNotNull(map.get("nlbq"))){
				model.setNlbq(map.get("nlbq"));
				model.setNlbqs(map.get("nlbq").split(","));
				model.setNlbqmc(map.get("nlbqmc"));
			}
		}
		if(!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", model);
		return mapping.findForward("viewHdblsq");

	}

	/**
	 * ��ȡ��ǰϵͳ���еĻ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getHdxxList(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;
		HdblsqshService hdblsqshService = new HdblsqshService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = hdblsqshService.getHdxxList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("path","hdgl_hdgl_hdqd_wh.do?method=getHdxxList");
		return mapping.findForward("hdxxList");
	}
	
}
