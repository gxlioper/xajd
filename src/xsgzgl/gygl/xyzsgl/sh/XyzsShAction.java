/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-26 ����02:01:49 
 */  
package xsgzgl.gygl.xyzsgl.sh;

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
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszService;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqService;
import xsgzgl.qgzx.mrgzkh.jcsz.GzkhJcszService;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshService;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-5-26 ����02:01:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyzsShAction extends SuperAction<XyzsShForm, XyzsShService> {
	private XyzsShService service = new XyzsShService();
	private final String XYZSSH = "xyzsjg";
	
	private static final String url = "gygl_xyzssh.do";
	
	@SystemAuth(url = url)
	public ActionForward getXyzsshlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsShForm model = (XyzsShForm) form;
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
		XyzsJcszService  jcszService = new XyzsJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("shkg", sqshkg==null?"0":sqshkg[1]);
		request.setAttribute("path", "gygl_xyzssh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xyzsshlist");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zsDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsShForm model = (XyzsShForm) form;
		//GzkhKhsqService khsqService = new GzkhKhsqService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//HashMap<String, String> infoList = service.getKhshInfo(model);
			//request.setAttribute("rs", infoList);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
//			String message = khsqService.checkZjeAndGs(model.getXh(),model.getXn(),model.getYxgs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm());
//			if(!"true".equals(message)){
//				 response.getWriter().print(getJsonMessage(message));
//				 return null;
//			}
			User user = getUser(request);
			// ���浥�����
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XyzsSqForm myForm = new XyzsSqForm() ;
		myForm.setSqbh(model.getSqbh());
		XyzsSqService service1 = new  XyzsSqService();
		XyzsSqForm model1 = service1.getModel(myForm);
		HashMap<String, String> xl = service1.getXlCk(model1);
		HashMap<String, String> xyjzyy = service1.getXyZsyy(model1);
		request.setAttribute("xlxx", xl);
		request.setAttribute("xyjzyy", xyjzyy);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSSH);
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, service.getModel(model));
		model.setShid(request.getParameter("shid"));
		request.setAttribute("zsjgxx", StringUtils.formatData(model));
		return mapping.findForward("zsDgsh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zsPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsShForm model = (XyzsShForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("zsPlsh");
	}
	
	/**
	 * 
	 * @����:���һ����˳���
	 * @���ߣ�xiaxia[���ţ�1104]
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
		XyzsShForm model = (XyzsShForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqbh(sqid);
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
		XyzsShForm model =new XyzsShForm();
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
}
