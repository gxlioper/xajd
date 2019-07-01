/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����02:36:16 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.sh;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.xnwxdk.cssz.XnwxdkCsszDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.cssz.XnwxdkCsszService;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqService;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.util.Util;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-18 ����02:36:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkjmshAction extends SuperAction<XnwxdkjmshModel, XnwxdkjmshService> {
	private XnwxdkjmshService service = new XnwxdkjmshService();
	private final String XNWXDK = "xnwxdk";
	
	private static final String url = "zxdk_xnwxdkjm_sh.do";
	
	@SystemAuth(url = url)
	public ActionForward getXnwxdkshCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmshModel model = (XnwxdkjmshModel) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xnwxdkDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmshModel model = (XnwxdkjmshModel) form;
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ���浥�����
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XnwxdkSqModel myForm = new XnwxdkSqModel() ;
		myForm.setSqid(model.getSqid());
//		XnwxdkSqService service1 = new  XnwxdkSqService();
//		XnwxdkSqModel model1 = service1.getModel(myForm);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XNWXDK);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("zsjgxx", StringUtils.formatData(model));
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		Util util = new Util();
		request.setAttribute("jmllist",util.getJml());
		String zje = util.getXsxxWxjkzh(model.getXh());
		request.setAttribute("zje", zje);
		request.setAttribute("jmyjlist", util.getJmlCk(model.getJmyj().split(","), model.getJmbl()));
//		XnwxdkSqService xnwxdksqService = new XnwxdkSqService();
//		request.setAttribute("jesx", xnwxdksqService.getJesx());
		return mapping.findForward("dgsh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xnwxdkPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmshModel model = (XnwxdkjmshModel) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		Util util = new Util();
		request.setAttribute("jmllist",util.getJml());
		return mapping.findForward("plsh");
	}
	
	/**
	 * 
	 * @����:���һ����˳���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-1-8 ����04:08:06
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmshModel model = (XnwxdkjmshModel) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		// ���һ������
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��˳���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-20 ����06:50:51
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnwxdkjmshModel model =new XnwxdkjmshModel();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		

		// ��˳����ɹ�
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * У����Ϣ��������鿴
	 */
	@SystemAuth(url = url)
	public ActionForward DkshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmshModel myForm = (XnwxdkjmshModel) form;
		XnwxdkjmshModel model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XNWXDK);
//		HashMap<String, String> xl = service.getXlCk(model);
//		HashMap<String, String> xyjzyy = service.getXyZsyy(model);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", StringUtils.formatData(model));
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		Util util = new Util();
		request.setAttribute("jmyjlist", util.getJmlCk(model.getJmyj().split(","), model.getJmbl()));
		String zje = util.getXsxxWxjkzh(model.getXh());
		request.setAttribute("zje", zje);
//		request.setAttribute("zsjgxx", StringUtils.formatData(model));
//		request.setAttribute("xlxx", xl);
//		request.setAttribute("xyjzyy", StringUtils.formatData(xyjzyy));
		return mapping.findForward("view");
	}
}
