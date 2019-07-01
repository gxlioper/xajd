/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����4:15:34 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.szdw.fdyrz.FdyrzsqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:����Ա��ѵ����
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-24 ����4:15:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdypxXmsqAction extends SuperAction {
	
	private static final String url = "szdw_fdypxxmsq.do?method=fdypxxmsqList";

	/**
	 * @����:����Ա������ѵ�б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-24 ����6:41:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward fdypxxmsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdypxXmsqForm myForm = (FdypxXmsqForm) form;
		FdypxXmsqService service = new FdypxXmsqService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_fdypxxmsq.do?method=fdypxxmsqList");
		return mapping.findForward("list");
		
	}
	/**
	 * @����:����Ա��ѵ��Ŀ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-25 ����9:53:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-����Ա��ѵ-����Ա��ѵ����-����XMDM:{xmdm}")
	public ActionForward fdypxxmSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmsqForm myForm = (FdypxXmsqForm) form;
		FdypxXmsqService service = new FdypxXmsqService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType()) || SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setSqr(user.getUserName());
			boolean result = service.fdypxxmsq(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		HashMap<String,String> map = new FdyrzsqService().getFdyjbxx(user.getUserName());
		request.setAttribute("rs", StringUtils.formatData(map));
		request.setAttribute("model", myForm);
		return mapping.findForward("add");
	}
	/**
	 * @����:��֤����Ա��ѵ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-25 ����2:11:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward yzFdypxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdypxXmsqForm myForm = (FdypxXmsqForm) form;
		User user = getUser(request);
		myForm.setSqr(user.getUserName());
		FdypxXmsqService service = new FdypxXmsqService();
		response.getWriter().print(service.yzFdypxsq(myForm));
		return null;
	}
	/**
	 * @����:����ȡ������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-25 ����3:17:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		/*String sqids = request.getParameter("sqids");
		String[]sqid =null;
		if(sqids!=null && !"".equals(sqids)){
			sqid = sqids.split(",");
		}*/
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		FdypxXmsqService service = new FdypxXmsqService();
		boolean result = service.cancelRecord(sqid,lcid);
		//boolean result = service.fdypxqxsq(sqid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			FdypxXmsqForm model = new FdypxXmsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcDao = new ShlcDao();
			if(Integer.valueOf(shlcDao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateFdypxsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitFdypxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmsqForm model = (FdypxXmsqForm) form;
		String sqid = request.getParameter("values");
		String fbr = request.getParameter("fbr");
		String splcid = request.getParameter("splcid");
		String xmdm = request.getParameter("xmdm");
		String shzt = request.getParameter("shzt");
		
		model.setSqid(sqid);
		model.setFbr(fbr);
		model.setSplc(splcid);
		FdypxXmsqService service = new FdypxXmsqService();
		String messageKey = "";
		
		if(!service.getXmkg(xmdm)&&!Constants.YW_YTH.equalsIgnoreCase(shzt)){
			messageKey = MessageKey.SZDW_FYDPX_XMWKF;
		}else{
			boolean result = service.submitFdypxsq(model);
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	
	/**
	 * 
	 * @����:TODO(����Ա��ѵ��Ŀ�޸�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-2 ����11:00:47
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
	@SystemLog(description = "����˼������-����Ա��ѵ-����Ա��ѵ����-�޸�SQID:{sqid},XMDM:{xmdm}")
	public ActionForward fdypxxmsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmsqForm myForm = (FdypxXmsqForm) form;
		
		FdypxXmsqService service = new FdypxXmsqService();
		User user = getUser(request);
		if (UPDATE.equalsIgnoreCase(myForm.getType()) || SUBMIT.equalsIgnoreCase(myForm.getType())){
			//�ж���Ŀ�����Ƿ���
			String messageKey = "";
			boolean result = true;
			if(!service.getXmkg(myForm.getXmdm())&& SUBMIT.equalsIgnoreCase(myForm.getType())){
				messageKey = MessageKey.SZDW_FYDPX_XMWKF;
			}else{
				myForm.setSqr(user.getUserName());
				result = service.fdypxxmsqXg(myForm);
				
				if(SUBMIT.equalsIgnoreCase(myForm.getType())){
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}else{
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				}
			}
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		HashMap<String,String> map = new FdyrzsqService().getFdyjbxx(user.getUserName());
		request.setAttribute("rs", StringUtils.formatData(map));
		FdypxXmsqForm updatemyForm = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(updatemyForm));
		request.setAttribute("model", updatemyForm);
		request.setAttribute("oldxmdm", updatemyForm.getXmdm());
		return mapping.findForward("fdypxxmsqXg");
		
	}	
	

}
