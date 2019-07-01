 
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * �ճ���Ϊ����ģ��
 */
public class RcxwdmwhAction extends SuperAction {
	
	private static final String url = "rcsw_rcxwwhnew_rcxwdmwh.do";
	
	// ====================== ��Ϊ��� begin ==================
	
	/**
	 * �ճ���Ϊ���
	 */
	@SystemAuth(url = url)
	public ActionForward rcxwlbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getRcxwlbList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_rcxwwhnew_rcxwdmwh.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rcxwlbManage");
	}
	/**
	 * ������Ϊ���
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��Ϊ���-����RCXWLBMC:{rcxwlbmc}")
	public ActionForward addRcxwlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveRcxwlbInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("addRcxwlb");
	}
	/**
	 * �޸���Ϊ���
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��Ϊ���-�޸�RCXWLBDM:{rcxwlbdm},RCXWLBMC:{rcxwlbmc}")
	public ActionForward updateRcxwlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		RcxwdmwhForm model = service.getRcxwlbForm(myForm);
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveRcxwlbInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateRcxwlb");
	}
	/**
	 * ɾ����Ϊ���
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��Ϊ���-ɾ��VALUES:{values}")
	public ActionForward delRcxwlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();	
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			//�ж���Ϊ����Ƿ�ʹ��
			String checkRcxwlb=service.checkRcxwlb(values);
			if(!checkRcxwlb.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_RCXWWH_NEW_RCXWLBDEL,checkRcxwlb);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.deleteRcxwlbInfo(values);
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	// ====================== ��Ϊ��� end ==================
	
	// ====================== ��Ϊ���� begin ==================

	/**
	 * ��Ϊ����
	 */
	@SystemAuth(url = url)
	public ActionForward rcxwdlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getRcxwdlPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		List<HashMap<String,String>> bmList = service.getBmlist(user);
		request.setAttribute("bmList", bmList);
		request.setAttribute("userDep", "xx".equals(user.getUserStatus()) ? "qx":user.getUserDep());

		String path = "rcsw_rcxwwhnew_rcxwdmwh.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("currDate", df.format(new Date()));
		List<HashMap<String,String>> rcxwlbListByYhsq = service.getRcxwlbListByYhsq(user);
		request.setAttribute("rcxwlbListByYhsq", rcxwlbListByYhsq);
		return mapping.findForward("rcxwdlManage");
	}
	/**
	 * ʱ�俪��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��Ϊ����-���뿪��-RCXWLBDLDM��{rcxwlbdldm}")
	public ActionForward rcxwlbdldmSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		String rcxwlbdldm = request.getParameter("rcxwlbdldm");
		String rcxwlbdlmc = service.getRcxwlbdlmcById(rcxwlbdldm);
		request.setAttribute("rcxwlbdlmc", rcxwlbdlmc);

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("kgList", isnotList);

		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);

		String path = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwdlManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		RcxwdmwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);

		return mapping.findForward("rcxwlbdldmSjkg");
	}
	/**
	 * ���� ��Ϊ���� 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��Ϊ����-����RCXWLBDLMC:{rcxwlbdlmc},RCXWLBDM:{rcxwlbdm},SPLC:{splc}")
	public ActionForward addRcxwdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveRcxwdlInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		if("xx".equalsIgnoreCase(user.getUserStatus())){
			model.setSsxydm("qx");
			request.setAttribute("ssxymc","ȫУ");
		}else{
			HashMap<String,String> bmMap = service.getBmlist(user).get(0);
			model.setSsxydm(bmMap.get("bmdm"));
			request.setAttribute("ssxymc", bmMap.get("bmmc"));
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		//��ȡ�ճ����������
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		List<HashMap<String,String>> rcxwlbListByYhsq = service.getRcxwlbListByYhsq(user);
		request.setAttribute("rcxwlbListByYhsq", rcxwlbListByYhsq);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("addRcxwdl");
	}
	/**
	 * �޸���Ϊ���� 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��Ϊ����-�޸�RCXWLBDLDM:{rcxwlbdldm},RCXWLBDLMC:{rcxwlbdlmc},RCXWLBDM:{rcxwlbdm},SPLC:{splc}")
	public ActionForward updateRcxwdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		RcxwdmwhForm model = service.getModel(myForm);
		User user = getUser(request);
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveRcxwdlInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if("xx".equalsIgnoreCase(user.getUserStatus())){
			model.setSsxydm("qx");
			request.setAttribute("ssxymc","ȫУ");
		}else{
			HashMap<String,String> bmMap = service.getBmlist(user).get(0);
			model.setSsxydm(bmMap.get("bmdm"));
			request.setAttribute("ssxymc", bmMap.get("bmmc"));
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		List<HashMap<String,String>> rcxwlbListByYhsq = service.getRcxwlbListByYhsq(user);
		request.setAttribute("rcxwlbListByYhsq", rcxwlbListByYhsq);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		//�ж���Ϊ�����Ƿ�ʹ��
		String checkRcxwdl=service.checkRcxwdl(myForm.getRcxwlbdldm());
		request.setAttribute("update_n", !checkRcxwdl.trim().equals(""));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateRcxwdl");
	}
	/**
	 * ɾ�� ��Ϊ���� 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��Ϊ����-ɾ��VALUES:{values}")
	public ActionForward delRcxwdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();	
		String values = request.getParameter("values");
	
		if (!StringUtil.isNull(values)){
			//�ж���Ϊ�����Ƿ�ʹ��
			String checkRcxwdl=service.checkRcxwdl(values);
			
			if(!checkRcxwdl.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_RCXWWH_NEW_RCXWDLDEL,checkRcxwdl);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.deleteRcxwdlInfo(values);
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	// ====================== ��Ϊ���� end ==================
	
	// ========================== ��ΪС�� beign ========================
	/**
	 * ����/ͣ�� ��ΪС��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��ΪС��-����/ͣ��VALUES:{values}")
	public ActionForward sfqyRcxwxl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();	
		String values = request.getParameter("values");
		String sfqy = request.getParameter("sfqy");
		boolean sfqy_y = "1".equals(sfqy);
		String RCSW_RCXWWH_SFQY_FAIL = MessageKey.RCSW_RCXWWH_SFQY_N_FAIL;
		String RCSW_RCXWWH_SFQY_NUM = MessageKey.RCSW_RCXWWH_SFQY_N_NUM;
		String RCSW_RCXWWH_SFQY_NULL = MessageKey.RCSW_RCXWWH_SFQY_N_NULL;
		if(sfqy_y){
			RCSW_RCXWWH_SFQY_FAIL = MessageKey.RCSW_RCXWWH_SFQY_Y_FAIL;
			RCSW_RCXWWH_SFQY_NUM = MessageKey.RCSW_RCXWWH_SFQY_Y_NUM;
			RCSW_RCXWWH_SFQY_NULL = MessageKey.RCSW_RCXWWH_SFQY_Y_NULL;
		}
		if (!StringUtil.isNull(values)){
			boolean result = true;
			String[] ids = values.split(",");
			int okNum = 0;
			RcxwdmwhForm myForm = new RcxwdmwhForm();
			if(sfqy_y){ // ����
				for (int i = 0; i < ids.length; i++) {
					myForm.setRcxwlbxldm(ids[i]);
					myForm.setSfqy(sfqy);
					if(result){
						result = service.updateRcxwxlSfqy(myForm);
						okNum++;
					}
				}
				String message = result ? MessageUtil.getText(RCSW_RCXWWH_SFQY_NUM, okNum) : MessageUtil.getText(RCSW_RCXWWH_SFQY_FAIL);
				response.getWriter().print(getJsonMessage(message));
			}else{ // ͣ��
				// ��ȡ�����δ�����ļ�¼
				List<HashMap<String,String>> rcxwxlShwjsList = service.getRcxwxlShwjs(values);
				StringBuilder notOk = new StringBuilder();
				for (int i = 0; i < ids.length; i++) {
					boolean rcxwxlShwjsFlag = false;
					for (HashMap<String,String> rcxwxlShwjsMap : rcxwxlShwjsList) {
						if(rcxwxlShwjsMap.get("rcxwxldm").equals(ids[i])){
							rcxwxlShwjsFlag = true;
							break;
						}
					}
					if(rcxwxlShwjsFlag){ // �����ϼ�¼
						notOk.append(ids[i]).append(",");
					}else{
						myForm.setRcxwlbxldm(ids[i]);
						myForm.setSfqy(sfqy);
						if(result){
							result = service.updateRcxwxlSfqy(myForm);
							okNum++;
						}
					}
				}
				String resultMsg = MessageUtil.getText(RCSW_RCXWWH_SFQY_NUM, okNum);
				if(rcxwxlShwjsList.size() > 0){
					String notOkRs = notOk.toString().substring(0, notOk.toString().length() - 1);
					resultMsg += "����Ϊ<font color='red'>��"+ notOkRs +"��</font>�ļ�¼����δ���������̣�����ͣ��";
				}
				String message = result ? resultMsg : MessageUtil.getText(RCSW_RCXWWH_SFQY_FAIL);
				response.getWriter().print(getJsonMessage(message));
			}
		} else {
			throw new SystemException(RCSW_RCXWWH_SFQY_NULL);
		}
		return null;
	}
	/**
	 * �ճ���ΪС��
	 */
	@SystemAuth(url = url)
	public ActionForward rcxwxlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getRcxwxlList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		List<HashMap<String,String>> bmList = service.getBmlist(user);
		request.setAttribute("bmList", bmList);
		request.setAttribute("userDep", "xx".equals(user.getUserStatus()) ? "qx":user.getUserDep());

		String path = "rcsw_rcxwwhnew_rcxwdmwh.do";
		request.setAttribute("path", path);
		List<HashMap<String,String>> rcxwlbdlListByYhsq = service.getRcxwlbdlListByYhsq(user);
		request.setAttribute("rcxwlbdlListByYhsq", rcxwlbdlListByYhsq);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rcxwxlManage");
	}
	/**
	 * ������ΪС��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��ΪС��-����RCXWLBXLMC:{rcxwlbxlmc},RCXWLBDM:{rcxwlbdm},RCXWLBDLDM:{rcxwlbdldm},RCXWFZLX:{rcxwfzlx},RCXWLBZDFZ:{rcxwlbzdfz},RCXWLBZGFZ:{rcxwlbzgfz}")
	public ActionForward addRcxwxl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveRcxwxlInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		List<HashMap<String,String>> rcxwlbListByYhsq = service.getRcxwlbListByYhsq(user);
		request.setAttribute("rcxwlbListByYhsq", rcxwlbListByYhsq);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("addRcxwxl");
	}
	/**
	 * �޸���ΪС��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��ΪС��-�޸�RCXWLBXLMC:{rcxwlbxlmc},RCXWLBDM:{rcxwlbdm},RCXWLBDLDM:{rcxwlbdldm},RCXWFZLX:{rcxwfzlx},RCXWLBZDFZ:{rcxwlbzdfz},RCXWLBZGFZ:{rcxwlbzgfz}")
	public ActionForward updateRcxwxl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		
		RcxwdmwhForm model = service.getRcxwxlForm(myForm);
		User user = getUser(request);
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveRcxwxlInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		List<HashMap<String,String>> rcxwlbListByYhsq = service.getRcxwlbListByYhsq(user);
		request.setAttribute("rcxwlbListByYhsq", rcxwlbListByYhsq);
		request.setAttribute("rs", StringUtils.formatData(myForm));
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateRcxwxl");
	}
	/**
	 * ɾ����ΪС��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ����ά��-��ΪС��-ɾ��VALUES:{values}")
	public ActionForward delRcxwxl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwdmwhService service = new RcxwdmwhService();	
		String values = request.getParameter("values");
	
		if (!StringUtil.isNull(values)){
			//�ж���ΪС���Ƿ���Ϊά��Ӧ��
			String checkRcxwxl=service.checkRcxwxl(values);
			
			if(!checkRcxwxl.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_RCXWWH_NEW_RCXWXLDEL,checkRcxwxl);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.deleteRcxwxlInfo(values);
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * �ж���ΪС���Ƿ���Ϊά��Ӧ��
	 */
	public ActionForward checkRcxwxl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		//�ж���ΪС���Ƿ���Ϊά��Ӧ��
		String checkRcxwxl=service.checkRcxwxl(myForm.getRcxwlbxldm());
		
		if(!checkRcxwxl.trim().equals("")){
			String message=MessageUtil.getText(MessageKey.RCSW_RCXWWH_NEW_RCXWLBUPDATE,checkRcxwxl);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		response.getWriter().print(getJsonMessage(""));
		return null;
	}
	/**
	 * �����������ѯ����
	 */
	@SystemAuth(url = url)
	public ActionForward queryRcxwlbdlListByLbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		JSONArray json = JSONArray.fromObject(service.queryRcxwlbdlListByLbdm(myForm.getRcxwlbdm(),user));
		response.getWriter().print(json);
		return null;
	}
	/**
	 * ���ݴ�������ѯС��
	 */
	@SystemAuth(url = url)
	public ActionForward queryRcxwlbxlListByDldm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		JSONArray json = JSONArray.fromObject(service.queryRcxwlbxlListByDldm(myForm.getRcxwlbdldm())); 
		response.getWriter().print(json);
		return null;
	}
	/**
	 * @����:������Ϊ��������ȡ����ѧԺ����
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/4 10:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward getSsxy(ActionMapping mapping, ActionForm form,
												 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		String ssxymc = service.getSsxy(myForm.getRcxwlbdldm());
		response.getWriter().print(ssxymc);
		return null;
	}
	// ========================== ��ΪС�� end ========================



}
