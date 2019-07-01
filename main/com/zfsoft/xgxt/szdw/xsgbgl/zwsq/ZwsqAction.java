/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����4:15:34 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsq;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
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
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ������
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-8 ����2:30:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZwsqAction extends SuperAction {
	
	private ZwsqService service = new ZwsqService();
	private static final String  CJFF = "cjff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "szdw_zwsq.do?method=zwsqList";
	/**
	 * @����:ְ�������б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����2:31:33
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
	public ActionForward zwsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwsqForm myForm = (ZwsqForm) form;
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
		request.setAttribute("path", "szdw_zwsq.do?method=zwsqList");
		return mapping.findForward("list");
	}
	/**
	 * @����:ְ������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����2:32:12
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
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ�ְ������-����XH:{xh},LXDM:{lxdm},ZWID:{zwid}")
	public ActionForward zwSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwsqForm myForm = (ZwsqForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())||SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setSqr(user.getUserName());
			boolean result = service.zwsq(myForm);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(myForm.getType())){
				 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}else{
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//����ѧ��������Ϣ
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,myForm.getXh());
		}
		String path = "szdw_zwsq.do?method=zwSq";
		request.setAttribute("path", path);
		request.setAttribute("model", StringUtils.formatData(myForm));
		return mapping.findForward("add");
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//��ѯѧ����Ϣ
		if(xh != null && !"".equals(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
	}
	/**
	 * @����:��ְ֤������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����2:32:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward yzZwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZwsqForm myForm = (ZwsqForm) form;
		response.getWriter().print(service.yzZwsq(myForm));
		return null;
	}
	/**
	 * @����:����ȡ������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����2:33:27
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
		
		//boolean result = service.qxsq(sqid);
		
		boolean result = service.cancelRecord(sqid,lcid);
		//boolean result = service.fdypxqxsq(sqid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			ZwsqForm model = new ZwsqForm();
			model.setSqid(sqid);
			ShlcDao splcdao = new ShlcDao();
			if(Integer.valueOf(splcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			model.setSplc(lcid);
			service.updateZwsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-5 ����10:58:59
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
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ�ְ������-ɾ��SQIDS:{sqids}")
	public ActionForward deleteZwsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqids = request.getParameter("sqids"); //��ɾ����sqids
		int isSuccess = 0 ; 
		if(StringUtils.isNotNull(sqids))
			isSuccess = service.runDelete(sqids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	/**
	 * 
	 * @����:TODO(�ύ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-11 ����02:22:51
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
	public ActionForward submitZwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZwsqForm model = (ZwsqForm) form;
		String sqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		String splcid = request.getParameter("splcid");
		model.setSqid(sqid);
		model.setXh(xh);
		model.setSplc(splcid);
		boolean result = service.submitZwsq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(ѧ���ɲ�ְ�������޸�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-2 ����05:25:14
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
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ�ְ������-�޸�SQID:{sqid},XH:{xh},LXDM:{lxdm},ZWID:{zwid}")
	public ActionForward zwsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwsqForm model = (ZwsqForm) form;
		User user = getUser(request);
		if (UPDATE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())){
			model.setSqr(user.getUserName());
			boolean result = service.zwsqXg(model);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(model.getType())){
				 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}else{
				 messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		
		//����ѧ��������Ϣ
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,model.getXh());
		}
		
		String path = "szdw_zwsq.do?method=zwsqXg";
		request.setAttribute("path", path);
		ZwsqForm updatemyForm = service.getModel(model);
		request.setAttribute("model", StringUtils.formatData(updatemyForm));
		request.setAttribute("oldzwid", updatemyForm.getZwid());
		BeanUtils.copyProperties(model, StringUtils.formatData(updatemyForm));
		return mapping.findForward("zwsqXg");
		
	}
	
	/**
	 * @����:���ر�����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-13 ����02:55:37
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
	public ActionForward printXsgbbab(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhService dwwhService = new DwwhService();
		PjjgService pjjgService = new PjjgService();
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		ZwsqForm myForm = (ZwsqForm) form;
		if(StringUtils.isNotNull(myForm.getXh())){
			List<File> files = new ArrayList<File>();
			String[] xhs = myForm.getXh().split(",");
			// ȥ���ظ���ѧ��
			Set<String> xhSet = new HashSet<String>();
			for (String xh : xhs) {
				xhSet.add(xh);
			}
			// ѭ�����ɴ�ӡ��
			for (String xh : xhSet) {
				//����ѧ��������Ϣ
				HashMap<String,String> xsjbxx = xsxxglService.getXsxxByXh(xh);
				Map<String, Object> data = new HashMap<String, Object>();
				
				// ��ǰ���ε�����ְ��
				List<HashMap<String , String>> zwList = dwwhService.getAllZwListByXh(xh);
				for (HashMap<String, String> zwMap : zwList) {
					String lxmc = zwMap.get("lxmc");
					String zwmc = zwMap.get("zwmc");
					if("У��".equals(lxmc)){
						xsjbxx.put("xjzwmc", zwmc);
					}else if("Ժ��".equals(lxmc)){
						xsjbxx.put("yjzwmc", zwmc);
					}else if("�༶".equals(lxmc)){
						xsjbxx.put("bjzwmc", zwmc);
					}
				}
				//��������
				String csny = DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month);
				csny = (csny == null) ? "" : csny;
				xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				// ��ͥ��Ա��Ϣ
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
				for (HashMap<String, String> jtcyxxMap : jtcyxxList) {
					String jtcygxmc = jtcyxxMap.get("jtcygxmc"); // �뱾�˹�ϵ
					String jtcyxm = jtcyxxMap.get("jtcyxm"); // ����
					String zzmmmc = jtcyxxMap.get("zzmmmc"); // ������ò
					String jtcygzdz = jtcyxxMap.get("jtcygzdz"); // ������λ����ַ
					if("����".equals(jtcygxmc)){
						xsjbxx.put("fqjtcyxm", jtcyxm);
						xsjbxx.put("fqzzmmmc", zzmmmc);
						xsjbxx.put("fqjtcygzdz", jtcygzdz);
					}else if("ĸ��".equals(jtcygxmc)){
						xsjbxx.put("mqjtcyxm", jtcyxm);
						xsjbxx.put("mqzzmmmc", zzmmmc);
						xsjbxx.put("mqjtcygzdz", jtcygzdz);
					}
				}
//				// ѧ����ᾭ����Ϣ ���޸ģ���ʱ�滻��xsxxb.grjlcxxtq���˼�������Сѧ���𣩡�
//				List<HashMap<String, String>> xlshjlxxList = xsxxglService.getXlshjlList(xh);
//				data.put("xlshjlxxList", xlshjlxxList);
//				data.put("xlshjlxxListSize", xlshjlxxList.size());
				// ===================���� ���˼�������Сѧ���𣩣���word�ܻ��� begin===================
				String zd6 = xsjbxx.get("zd6");
				List<String> zd6List = new ArrayList<String>();
				if(zd6 != null){
					String[] zd6Arr = zd6.split("\r\n");
					zd6List = Arrays.asList(zd6Arr);
				}
				data.put("zd6List", zd6List);
				// ===================���� ���˼�������Сѧ���𣩣���word�ܻ��� end===================
				// ����ѧ�Ų�ѯ�����
				List<HashMap<String, String>> hjqkList = pjjgService.getHjqkList(xh);
				StringBuffer hjqkBuffer = new StringBuffer();
				for (HashMap<String, String> hjqkMap : hjqkList) {
					hjqkBuffer.append(hjqkMap.get("xmmc")).append(" ");
				}
				xsjbxx.put("hjqkmcs", hjqkBuffer.toString());
				//ѧ����Ƭ
				InputStream is = xsxxService.getPhotoStream(xh);
				File photoFile = FileUtil.inputstreamToFile(is, "doc");
				String photo = FileUtil.getBASE64(photoFile);
				xsjbxx.put("photo", photo);
				
				data.putAll(xsjbxx);
				File file = service.getWord(data);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZwsqForm model = (ZwsqForm) form;
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
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
}
