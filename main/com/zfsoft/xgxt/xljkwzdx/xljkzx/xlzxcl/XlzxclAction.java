/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:07:26 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xljkwzdx.common.StringTools;
import com.zfsoft.xgxt.xljkwzdx.common.ZtToCnDesc;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl.XlzxglForm;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl.XlzxglService;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx.XsyysqForm;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx.XsyysqService;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk.YyzxfkForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ -������ѯ����
 * @�๦������: 
 * @���ߣ���־��[����:1060]
 * @ʱ�䣺 2014-4-29 ����03:07:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlzxclAction extends SuperAction{
	
	/**
	 * �ڶ�֤��-ѧ����Ϣ����ʾ���ݿ����ô���
	 */
	public static final String XLZXCL_BDID = "xljkwzdxxlzxcl";
	/**
	 * ѧ����ʾ��Ϣ������
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @���� ���޲ι����� 
	 * @���� ����ʼ��������
	 */
	public XlzxclAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(XLZXCL_BDID);
	}

	private static final String url = "xljk_xljkzx_xlzxcl.do";
	
	/**
	 * 
	 * @����:��ѯ������ѯ������ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-29 ����03:36:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��ȡ����Ȩ��
		request.setAttribute("path", "xljk_xljkzx_xlzxcl.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryXlzxcl");
	}
	
	/**
	 * 
	 * @����:��ѯ������ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-29 ����03:39:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXlzxclAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		model.setZxs(user.getUserName());
		
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:���������ѯ������ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-29����04:32:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model = (XlzxclForm) form;
		
		User user = getUser(request);
		XlzxclService service = new XlzxclService();
		HashMap<String, String> zxsxx = service.getZxsxx(user.getUserName());
		//������ѯʦ��Ϣ
		request.setAttribute("zxsxx", zxsxx);
		request.setAttribute("jbxxList", jbxxList);
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			
		}
		
		String path = "xljk_xlzxcl.do?method=addXlzxcl";
		request.setAttribute("path", path);
		
		return mapping.findForward("addXlzxcl");
	}
	
	/**
	 * 
	 * @����:���������ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-30����10:30:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXlzxclAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		
		String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setZxid(uuid);
		model.setZxzt("0"); //Ĭ�ϴ���ѯ
		
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:�޸�������ѯ������ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-30����10:20:39 
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
	public ActionForward updateXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		
		XlzxclForm dataModel = service.getModel(model.getZxid());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		HashMap<String, String> zxsxx = service.getZxsxx(model.getZxs());
		request.setAttribute("zxsxx", zxsxx);
		request.setAttribute("jbxxList", jbxxList);
		//���ò�������ѡ��
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		
		return mapping.findForward("updateXlzxcl");
	}
	
	/**
	 * 
	 * @����:�޸�������ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-29����10:32:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXlzxclAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴ѧ��ԤԼ��ѯ���뼰������ѯ��Ϣ����ѯʦ������Ϣ
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-5-9����09:33:39 
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
	public ActionForward viewXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		XsyysqService xsyysqservice = new XsyysqService();
		
		XlzxclForm dataModel = service.getModel(model.getZxid());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		model.setBz(StringTools.StringToText(StringTools.strOutNull(model.getBz())));
		model.setXszxpj(StringTools.StringToText(StringTools.strOutNull(model.getXszxpj())));
		model.setZxzt(ZtToCnDesc.zxztChange(model.getZxzt()));
		model.setSfxyzj(ZtToCnDesc.sfxyzjChange(model.getSfxyzj()));
		model.setSfyyxczx(ZtToCnDesc.sfyyxczxChange(model.getSfyyxczx()));
		
		//����ѧ����Ϣ��ʾ�ֶ�
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		
		if(model.getSqid()!=null){
			XsyysqForm xsyysqForm = xsyysqservice.getModel(model.getSqid());
			xsyysqForm.setYyzxzt(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxzt())));
			xsyysqForm.setYyzxxq(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxxq())));
			xsyysqForm.setYysbyy(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYysbyy())));
			request.setAttribute("yyztmc", ZtToCnDesc.yyztChange(xsyysqForm.getYyzt()));
			request.setAttribute("xsyysqForm",StringUtils.formatData(xsyysqForm));
			
			String wtlxarr = xsyysqForm.getWtlx()!=null?xsyysqForm.getWtlx():"";
			wtlxarr = wtlxarr.replaceAll(",", "','");
			wtlxarr = "'"+wtlxarr+"'";
			
			//��������������������String  ��ǰ̨��ʾ
			List<HashMap<String, String>> xlwtList = xsyysqservice.getXlwtAllListByLxdm(wtlxarr);
			
			StringBuffer wtlxMcStr = new StringBuffer();
			for(int i=0;i<xlwtList.size();i++){
				wtlxMcStr.append(xlwtList.get(i).get("lxmc")).append("��");
			}
			request.setAttribute("wtlxMcStr", wtlxMcStr.toString());
			
			//������ѯԤԼ˵��
			String zxyysm = xsyysqservice.getZxyysm();
			request.setAttribute("zxyysm", StringTools.StringToText(StringTools.strOutNull(zxyysm)));
			
			HashMap<String, String> zxsMap = service.getZxsxx(model.getZxs());
			request.setAttribute("zxsxm", zxsMap.get("xm"));
		}else{
			request.setAttribute("xsyysqForm",null);
		}
		
		//��ѯ������ʦѡ���������������  ��ǰ̨��ʾ
		String gswtlx = StringTools.strOutNull(model.getGswtlx());
		String[] gswtlxStr = gswtlx.split("###");
		
		String gswtlxarr = gswtlxStr[0];
		gswtlxarr = gswtlxarr.replaceAll(",", "','");
		gswtlxarr = "'"+gswtlxarr+"'";
		
		List<HashMap<String, String>> gsxlwtList = xsyysqservice.getXlwtAllListByLxdm(gswtlxarr);
		
		StringBuffer gswtlxMcStr = new StringBuffer();
		for(int i=0;i<gsxlwtList.size();i++){
			gswtlxMcStr.append(gsxlwtList.get(i).get("lxmc")).append("��");
		}
		String[] qtwtlxStr = (gswtlxStr.length==1?"none@@":gswtlxStr[1]).split("@@");
		String qtwtlxstr = null;
		if(qtwtlxStr.length == 1){
			qtwtlxstr = "";
		}else{
			qtwtlxstr = qtwtlxStr[1];
		}
		gswtlxMcStr.append(qtwtlxstr);
		//���ø�������������������String  ��ǰ̨��ʾ
		request.setAttribute("gswtlxMcStr", gswtlxMcStr);
		
		//���̶ܳ�
		String jscd = StringTools.strOutNull(model.getJscd());
		String[] jscdStr = jscd.split("###");
		StringBuffer jscdMcStr = new StringBuffer();
		String[] jscdarr = jscdStr[0].split(",");
		String jscdMc = "";
		for(int i = 0;i <jscdarr.length; i++){
			jscdMc = ZtToCnDesc.jscdChange(jscdarr[i]);
			if(!jscdMc.equals("")){
				jscdMcStr.append(jscdMc).append("��");
			}
		}
		if(jscdStr.length==2){
			jscdMcStr.append(jscdStr[1]);
		}
		//������ѯʦ�����߶���ѯ�Ľ��̶ܳ�
		request.setAttribute("jscdMcStr", jscdMcStr);
		
		//���س̶�����
		String yzcdpg = StringTools.strOutNull(model.getYzcdpg());
		String[] yzcdpgStr = yzcdpg.split("###");
		StringBuffer yzcdpgMcStr = new StringBuffer();
		String[] yzcdpgarr = yzcdpgStr[0].split(",");
		String yzcdpgMc = "";
		for(int i = 0;i <yzcdpgarr.length; i++){
			yzcdpgMc = ZtToCnDesc.yzcdpgChange(yzcdpgarr[i]);
			if(!yzcdpgMc.equals("")){
				yzcdpgMcStr.append(yzcdpgMc).append("��");
			}
		}
		if(yzcdpgStr.length==2){
			yzcdpgMcStr.append(yzcdpgStr[1]);
		}
		//������ѯʦ�����߶���ѯ�Ľ��̶ܳ�
		request.setAttribute("yzcdpgMcStr", yzcdpgMcStr);
		
		
		
		
		return mapping.findForward("viewXlzxcl");
	}
	
	/**
	 * 
	 * @����:ɾ��������ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-23 ����03:23:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteXlzxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclService service = new XlzxclService();
		
		String zxids = request.getParameter("zxids"); //��ɾ����������ѯ�����
		
		int isSuccess = service.runDelete(zxids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @����:������ѯ��ѯ��������ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-5-5 ����11:05:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewZxFk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XlzxclForm model  = (XlzxclForm) form;
		
		XlzxclService service = new XlzxclService();
		XsyysqService xsyysqService = new XsyysqService();
		
		XlzxclForm dataModel = service.getModel(model.getZxid());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		if(model.getZxzt().equals("0")){
			model.setZxzt("1");    //����Ĭ��ֵ Ϊ������ѯ��
		}
		if(model.getSfxyzj() == null){
			model.setSfxyzj("1");  //�����Ƿ���Ҫת��Ĭ��ֵ Ϊ  (��)
		}
		if(model.getSfyyxczx() == null){
			model.setSfyyxczx("1");//�����Ƿ�ԤԼ�´���ѯĬ��ֵ Ϊ  (��)
		}
		model.setBz(StringTools.StringToText(StringTools.strOutNull(model.getBz())));
		
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		
		//��ѯѧ����ѯԤԼ������Ϣ
		XsyysqForm xsyysqForm = xsyysqService.getModel(model.getSqid());
		xsyysqForm = xsyysqForm != null ? xsyysqForm : new XsyysqForm();
		xsyysqForm.setYyzxzt(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxzt())));
		xsyysqForm.setYyzxxq(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxxq())));
		xsyysqForm.setYysbyy(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYysbyy())));
		
		request.setAttribute("xsyysqForm",StringUtils.formatData(xsyysqForm));
		
		request.setAttribute("yyztmc", ZtToCnDesc.yyztChange(xsyysqForm.getYyzt()));
		
		String wtlxarr = xsyysqForm.getWtlx()!=null?xsyysqForm.getWtlx():"";
		wtlxarr = wtlxarr.replaceAll(",", "','");
		wtlxarr = "'"+wtlxarr+"'";
		
		//��������������������String  ��ǰ̨��ʾ
		List<HashMap<String, String>> xlwtList = xsyysqService.getXlwtAllListByLxdm(wtlxarr);
		StringBuffer wtlxMcStr = new StringBuffer();
		for(int i=0;i<xlwtList.size();i++){
			wtlxMcStr.append(xlwtList.get(i).get("lxmc"));
			//���һ���ָ�������
			if(i<xlwtList.size()-1){
				wtlxMcStr.append("��");
			}
		}
		request.setAttribute("wtlxMcStr", wtlxMcStr.toString());
		
		//��������������������String  ��ǰ̨��ʾ
		List<HashMap<String, String>> allxlwtList = xsyysqService.getXlwtAllList();
		request.setAttribute("xlwtList", allxlwtList);
		
		
		//������ѯԤԼ˵��
		String zxyysm = xsyysqService.getZxyysm();
		request.setAttribute("zxyysm", StringTools.StringToText(StringTools.strOutNull(zxyysm)));
		
		HashMap<String, String> zxsMap = service.getZxsxx(model.getZxs());
		request.setAttribute("zxsxm", zxsMap.get("xm"));
		
		//����������ʦѡ���������������  ��ǰ̨��ʾ
		String gswtlx = StringTools.strOutNull(model.getGswtlx());
		String[] gswtlxStr = gswtlx.split("###");
		request.setAttribute("gswtlx", gswtlxStr[0]);
		
		String[] qtwtlxStr = (gswtlxStr.length==1?"none@@":gswtlxStr[1]).split("@@");
		request.setAttribute("qtwtlx", qtwtlxStr[0]);
		String qtwtlxstr = null;
		if(qtwtlxStr.length == 1){
			qtwtlxstr = "";
		}else{
			qtwtlxstr = qtwtlxStr[1];
		}
		request.setAttribute("qtwtlxstr", qtwtlxstr);
		
		String jscd = StringTools.strOutNull(model.getJscd());
		String[] jscdStr = jscd.split("###");
		request.setAttribute("jscd", jscdStr[0]);
		
		request.setAttribute("qtjscd", jscdStr.length==1?"":jscdStr[1]);
		
		String yzcdpg = StringTools.strOutNull(model.getYzcdpg());
		String[] yzcdpgStr = yzcdpg.split("###");
		request.setAttribute("yzcdpg", yzcdpgStr[0]);
		
		request.setAttribute("qtyzcdpg", yzcdpgStr.length==1?"":yzcdpgStr[1]);
		
		return mapping.findForward("viewZxFk");
	}
	
	/**
	 * 
	 * @����:������ѯ��ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-5-5 ����4:05:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zxFkAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();
		
		
		//������������
		String[] gswtlxarr = model.getWtlxarray();
		String gswtlx = org.apache.commons.lang.StringUtils.join(gswtlxarr, ",");
		String[] qtwtlxflag = model.getQtwtlxarray();
		gswtlx+="###";
		if(qtwtlxflag != null){
			gswtlx=gswtlx+qtwtlxflag[0]+"@@"+model.getQtwtlx();
		}else{
			gswtlx=gswtlx+"none@@";
		}
		model.setGswtlx(gswtlx);
		
		//���̶ܳ�
		String[] jscdarr = model.getJscdarray();
		String jscd = org.apache.commons.lang.StringUtils.join(jscdarr, ",");
		jscd+="###";
		if(ArrayUtils.contains(jscdarr, "qt")){
			jscd+=model.getQtjscd();
		}
		model.setJscd(jscd);
		
		//���س̶�����
		String[] yzcdpgarr = model.getYzcdpgarray();
		String yzcdpg = org.apache.commons.lang.StringUtils.join(yzcdpgarr, ",");
		yzcdpg+="###";
		if(ArrayUtils.contains(yzcdpgarr, "qt")){
			yzcdpg+=model.getQtyzcdpg();
		}
		model.setYzcdpg(yzcdpg);
		
		if(!StringTools.strOutNull(model.getSfyyxczx()).equals("")){
			if(model.getSfyyxczx().equals("1")){
				model.setXczxsj("");
			}
		}
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-5-8 ����15:53:39 
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxclForm model  = (XlzxclForm) form;
		XlzxclService service = new XlzxclService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		model.setZxs(user.getUserName());
		
		List<HashMap<String,String>> resultList = service.getAllXlzxclList(model);
		HashMap<String,String> mapTemp = null;
		for(int i = 0 ; i<resultList.size();i++){
			mapTemp = resultList.get(i);
			mapTemp.put("yyzxzt", StringTools.TextToString1(StringTools.strOutNull(mapTemp.get("yyzxzt"))));
		}
		
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
