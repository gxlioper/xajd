/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-5 ����09:33:24 
 */  
package com.zfsoft.xgxt.zxdk.tqhk;

import java.util.HashMap;
import java.util.List;

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

import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import com.zfsoft.xgxt.zxdk.hkzt.HkztModel;
import com.zfsoft.xgxt.zxdk.hkzt.HkztService;
import com.zfsoft.xgxt.zxdk.xyddk.DkjgService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����
 * @�๦������: ��ǰ���� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-5 ����09:33:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TqhkAction extends SuperAuditAction<TqhkModel, TqhkService> {

	protected TqhkAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}
	
	public TqhkAction(){
		this("zxdk_xyddk", "zxdk_tqhk_hksq.do", "zxdk_tqhk_hksh.do");
	}

	/**��������*/
	@SystemAuth(url = "zxdk_tqhk_hksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward hksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if (!"stu".equals(user.getUserType())){
			request.setAttribute("message", "��ҳ��ֻ��ѧ���û����ʡ�");
			return mapping.findForward("error");
		}
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		if (Constants.CLOSE.equals(csszModel.getTqhkKg())){
			request.setAttribute("message", "��ǰ����δ���š�");
			return mapping.findForward("error");
		}
		
		DkjgService dkjgService = new DkjgService();
		List<HashMap<String,String>> khkList = null;
		if("10511".equals(Base.xxdm)){
			khkList = dkjgService.getKhkListHsd(user.getUserName());
		}else{
			khkList = dkjgService.getKhkList(user.getUserName());
		}
		if (khkList == null || khkList.size() == 0){
			request.setAttribute("message", "�޿ɻ���Ĵ����¼��");
			return mapping.findForward("error");
		}
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("khkList", khkList);
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("hksq");
	}
	
	
	/**�޸Ļ�������**/
	@SystemAuth(url = "zxdk_tqhk_hksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel tqhkForm = (TqhkModel) form;
		
		TqhkModel model =  service.getModel(tqhkForm);
		
		if (model != null){
			BeanUtils.copyProperties(tqhkForm, model);
		}
		
		DkjgService dkjgService = new DkjgService();
		List<HashMap<String,String>> khkList = null;
		if("10511".equals(Base.xxdm)){
			khkList = dkjgService.getKhkListHsd(model.getXh());
		}else{
			khkList = dkjgService.getKhkList(model.getXh());
		}
		request.setAttribute("khkList", khkList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xgsq");
	}
	
	
	/**���������б�*/
	@SystemAuth(url = "zxdk_tqhk_hksq.do")
	public ActionForward hksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		User user = getUser(request);
		
		if (!"stu".equals(user.getUserType())){
			request.setAttribute("message", "��ҳ��ֻ��ѧ���û����ʡ�");
			return mapping.findForward("error");
		}
		
		DkjgService dkjgService = new DkjgService();
		List<HashMap<String,String>> khkList = dkjgService.getKhkList(user.getUserName());
		
		if (khkList == null || khkList.size() == 0){
			request.setAttribute("message", "�޿ɻ���Ĵ����¼��");
			return mapping.findForward("error");
		}
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "zxdk_tqhk_hksq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hksqList");
	}
	
	
	/**���������б�*/
	@SystemAuth(url = "zxdk_tqhk_hksq.do")
	public ActionForward getHksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel model = (TqhkModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**ɾ����ǰ��������*/
	@SystemAuth(url = "zxdk_tqhk_hksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward delTqhk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel model = (TqhkModel) form;
		
		boolean result = service.runDelete(new String[]{model.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}

	
	
	/**��������б�*/
	@SystemAuth(url = "zxdk_tqhk_hksh.do")
	public ActionForward hkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "zxdk_tqhk_hksh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hkshList");
	}
	
	
	/**��������б�*/
	@SystemAuth(url = "zxdk_tqhk_hksh.do")
	public ActionForward getHkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkModel model = (TqhkModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**�������**/
	@SystemAuth(url = "zxdk_tqhk_hksh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward hksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel tqhkForm = (TqhkModel) form;
		TqhkModel model =  service.getModel(tqhkForm);
		
		if (model != null){
			BeanUtils.copyProperties(tqhkForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			DkjgService dkjgService = new DkjgService();
			List<HashMap<String,String>> khkList = null;
			if("10511".equals(Base.xxdm)){
				khkList = dkjgService.getKhkListHsd(model.getXh());
			}else{
				khkList = dkjgService.getKhkList(model.getXh());
			}
			request.setAttribute("khkList", khkList);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("gjzxdk");
		request.setAttribute("jbxxList", jbxxList);
		
		HkztService hkztService = new HkztService();
		List<HashMap<String,String>> hkztList = hkztService.getAllList(new HkztModel());
		request.setAttribute("hkztList", hkztList);
		
		return mapping.findForward("hksh");
	}

	/**�鿴��ǰ��������**/
	public ActionForward viewHksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel tqhkForm = (TqhkModel) form;
		
		TqhkModel model =  service.getModel(tqhkForm);
		
		if (model != null){
			BeanUtils.copyProperties(tqhkForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//ѧ��������Ϣ��ʾ����
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("gjzxdk");
			request.setAttribute("jbxxList", jbxxList);
		}
		
		DkjgService dkjgService = new DkjgService();
		List<HashMap<String,String>> khkList = null;
		if("10511".equals(Base.xxdm)){
			khkList = dkjgService.getKhkListHsd(model.getXh());
		}else{
			khkList = dkjgService.getKhkList(model.getXh());
		}
		request.setAttribute("khkList", khkList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewHksq");
	}
	
	/**
	 * 
	 * @����: ��д���������淽��
	 * @���ߣ� yxy[����:1206]
	 * @���ڣ�2014��6��9�� ����9:51:00
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
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TqhkModel model = (TqhkModel) form;
		TqhkService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����: �������ύ
	 * @���ߣ� yxy[����:1206]
	 * @���ڣ�2014��6��9�� ����9:51:00
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
	@Override
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TqhkModel model = (TqhkModel) form;
		TqhkService service = getService();
		boolean isSuccess = false;
		//���������¼��Ӧ�����״̬������С�
		model.setShzt(Constants.YW_SHZ);
		
		//��֤����ID�����ID��һ���ԣ���ϵͳ����ΨһID
		if (StringUtil.isNull(model.getId())){
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(uuid);
			
			//���������¼
			isSuccess = service.runInsert(model);
		} else {
			isSuccess = service.runUpdate(model);
		}
		
		JSONObject message = null;
		
		if (isSuccess){
			//�ύ���뵽�������
			message = submit("zxdk_xyddk" , model.getId(), "zxdk_tqhk_hksq.do", "zxdk_tqhk_hksh.do");
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����: ��д�ύ ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-3 ����11:57:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	private JSONObject submit(String gnid,String id,String squrl,String shurl)
	throws Exception {

		ShlcInterface shlc = new CommShlcImpl();
		
		TqhkService service = getService();
		//��ѯ�����¼
		TqhkModel model = service.getModel(id);
		String splcid = model.getSplcid();
		//�ύ��������
		boolean isSuccess = shlc.runSubmit(id, splcid, model.getXh(), shurl, squrl);
		
		if(isSuccess){
			//���������¼״̬
			model.setShzt(Constants.YW_SHZ);
			//model.setSplcid(splcid);
			isSuccess = service.runUpdate(model);
		}
		
		String message = isSuccess ? 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) : 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
		
		return getJsonMessage(message);
	}

}
