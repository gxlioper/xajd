/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-18 ����04:40:12 
 */  
package com.zfsoft.xgxt.rcsw.txhd.hdkhgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.txhd.dmwh.TxhdDmwhService;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszForm;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-9-18 ����04:40:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HdkhglAction extends SuperAction<hdkhForm, HdkhglService> {
	private HdkhglService service = new  HdkhglService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "rcsw_txhd_hdkhgl.do";
	
	//��ȡ����˹���list
	@SystemAuth(url = url)
	public ActionForward getHdkhList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		hdkhForm model = (hdkhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getKhglList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_txhd_hdkhgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getHdkhList");
	}
	
	/**
	 * ����˹�����Ϣ����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		hdkhForm model = (hdkhForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		

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
	
	//���˳ɼ�����
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward khcjPd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*�ϰ벿�ֻ��Ϣ�鿴*/
		hdkhForm model = (hdkhForm) form;
		TxhdXmszService txservice = new TxhdXmszService();
		TxhdXmszForm myModel = txservice.getModel(model.getHdxmbh());
		if(myModel.getXmdm() != null){
			TxhdDmwhService dmwhservice = new TxhdDmwhService();
			String hdgg = dmwhservice.getHdggmc(myModel.getHdggdm());
			request.setAttribute("hdgg", hdgg);
		}else{
			request.setAttribute("hdgg", "");
		}
		String sqrssx = myModel.getSqrssx();
		String shrssx = myModel.getShrssx();
		if(null!=sqrssx && !StringUtils.isBlank(sqrssx)){
			myModel.setSqrssx(sqrssx+" ��");
		}
		if(null!=shrssx && !StringUtils.isBlank(shrssx)){
			myModel.setShrssx(shrssx+" ��");
		}
		request.setAttribute("data", myModel);
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// ��Ŀ���
		String hdlbmc=null;
		for(HashMap<String, String> hm:lbList){
			if(myModel.getLbdm().equals(hm.get("lbdm"))){
				hdlbmc=hm.get("lbmc");
			}
		}
		request.setAttribute("hdlbmc", hdlbmc);
		request.setAttribute("xxdm", Base.xxdm);
		/*�ϰ벿�ֻ��Ϣ�鿴*/
		request.setAttribute("hdkhcjlist", service.getKhcjList(model.getHdxmbh()));
		request.setAttribute("hdxmbh", myModel.getXmdm());
		request.setAttribute("xn", myModel.getXn());
		request.setAttribute("xq", myModel.getXq());
		OptionUtil op = new OptionUtil();
		request.setAttribute("xflist", op.getOptions("khpf"));
		return mapping.findForward("khcjPd");
	}
	
	//�������ֽ��ά������
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward savePfwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		hdkhForm myForm = (hdkhForm) form;
		String hdxmbh = myForm.getHdxmbh();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String[] ids = request.getParameterValues("id");
		String[] hjqks = request.getParameterValues("hjqk");
		String[] xhs = request.getParameterValues("xh");
		String[] sfhdxfs = request.getParameterValues("sfhdxf");
		boolean result = true;
		for (int i = 0; i < xhs.length; i++) {
			hdkhForm hdform = new hdkhForm();
			if (ids != null && !ids[i].equals("") && ids[i] != null) {
				hdform.setId(ids[i]);
				hdform.setSfhdxf(sfhdxfs[i]);
				hdform.setHdxmbh(hdxmbh);
				hdform.setHjqk(hjqks[i]);
				hdform.setXh(xhs[i]);
				result = service.runUpdate(hdform);
			} else {
				hdform.setSfhdxf(sfhdxfs[i]);;
				hdform.setXh(xhs[i]);
				hdform.setXn(xn);
				hdform.setXq(xq);
				hdform.setHdxmbh(hdxmbh);
				hdform.setHjqk(hjqks[i]);
				hdform.setId(ids[i]);
				result = service.runInsert(hdform);
			}
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		String message = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;

		response.getWriter().print(getJsonMessageByKey(message));
		return null;
		
	}
	
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		/*�ϰ벿�ֻ��Ϣ�鿴*/
		hdkhForm model = (hdkhForm) form;
		TxhdXmszService txservice = new TxhdXmszService();
		TxhdXmszForm myModel = txservice.getModel(model.getHdxmbh());
		if(myModel.getXmdm() != null){
			TxhdDmwhService dmwhservice = new TxhdDmwhService();
			String hdgg = dmwhservice.getHdggmc(myModel.getHdggdm());
			request.setAttribute("hdgg", hdgg);
		}else{
			request.setAttribute("hdgg", "");
		}
		String sqrssx = myModel.getSqrssx();
		String shrssx = myModel.getShrssx();
		if(null!=sqrssx && !StringUtils.isBlank(sqrssx)){
			myModel.setSqrssx(sqrssx+" ��");
		}
		if(null!=shrssx && !StringUtils.isBlank(shrssx)){
			myModel.setShrssx(shrssx+" ��");
		}
		request.setAttribute("data", xgxt.utils.String.StringUtils.formatData(myModel));
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// ��Ŀ���
		String hdlbmc=null;
		for(HashMap<String, String> hm:lbList){
			if(myModel.getLbdm().equals(hm.get("lbdm"))){
				hdlbmc=hm.get("lbmc");
			}
		}
		request.setAttribute("hdlbmc", hdlbmc);
		request.setAttribute("xxdm", Base.xxdm);
		/*�ϰ벿�ֻ��Ϣ�鿴*/
		request.setAttribute("hdkhcjlist", service.getKhcjList(model.getHdxmbh()));
		request.setAttribute("hdxmbh", myModel.getXmdm());
		return mapping.findForward("view");
	}
	
}
