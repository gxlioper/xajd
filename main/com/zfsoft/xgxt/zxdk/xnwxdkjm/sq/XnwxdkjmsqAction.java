/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����02:46:33 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.sq;

import java.io.File;
import java.util.ArrayList;
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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.xnwxdk.cssz.XnwxdkCsszService;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.cssz.XnwxdkjmCsszService;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.jg.XnwxdkjmJgModel;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.jg.XnwxdkjmJgService;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.util.Util;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.util.UtilForm;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-24 ����02:46:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkjmsqAction extends SuperAction<XnwxdkjmsqModel, XnwxdkjmsqService> {
	private final String XNWXDK="xnwxdk";
	private XnwxdkjmsqService service = new  XnwxdkjmsqService();
	
	private static final String url = "zxdk_xnwxdkjm_sq.do";
	
	/**
	 * У����Ϣ���������ʼ����ѯ����
	 */
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2016-2-29 ����01:52:46
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
	@SystemAuth(url = url)
	public ActionForward getXnwxdksqCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmsqModel model = (XnwxdkjmsqModel) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// ��ѯ
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
		XnwxdkjmCsszService csszService = new XnwxdkjmCsszService();
		String sqkg = csszService.getSqShKg();
		request.setAttribute("sqkg", sqkg==null?"0":sqkg);
		String path = "zxdk_xnwxdkjm_sq.do";
		request.setAttribute("path", path);
		model.setXh(user.getUserName());
		UtilForm utilform = new UtilForm();
		Util util = new Util();
		utilform.setXh(user.getUserName());
		utilform.setType("qb");
		boolean isExist = util.isNotExists(utilform);
		String cfbz = "0";
		if(!isExist){
			cfbz = "1";
		}
		request.setAttribute("cfbz", cfbz);
		//�ж��Ƿ��Ǳ�ҵ��
		request.setAttribute("sfbys", util.getSfbys(user.getUserName(), user.getUserType())?"��":"��");//1�Ǳ�ҵ����0���Ǳ�ҵ��
		request.setAttribute("sfydk", util.getWxdkjl(user.getUserName()));
		request.setAttribute("usertype", user.getUserType());
		request.setAttribute("dkjl", util.getWxdkjl(user.getUserName()));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * У����Ϣ���������������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmsqModel model = (XnwxdkjmsqModel) form;
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
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XNWXDK);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xnwxdkjm_sq.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("xn", Base.currXn);
		model.setXq(Base.currXq);
		model.setXn(Base.currXn);
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(Base.currXq.equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		Util util = new Util();
		request.setAttribute("jmllist",util.getJml());
//		if( util != null  && util.getJml().size()>0 ){
//			request.setAttribute("jmyjlist",util.getjmyjList(util.getJml().get(0).get("jmbl")));
//		}else{
//			request.setAttribute("jmyjlist","");
//		}
		
		//������Ϣ����
		return mapping.findForward("add");
	}
	
	/**
	 * У����Ϣ����������뱣��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveDksq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmsqModel model = (XnwxdkjmsqModel) form;
		String[] items = request.getParameterValues("jmyj");
		String jmyj = StringUtils.joinArr(items);
		model.setJmyj(jmyj);
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		Util util = new Util();
		UtilForm utilform = new UtilForm();
		utilform.setType("qb");
		utilform.setXh(model.getXh());
		if(model.getType().equals("save")||model.getType().equals("submit")){
			boolean isExist = util.isNotExists(utilform);
			// �жϵ�ǰѧ���Ƿ��м�¼
			if (!isExist) {
				message = MessageUtil.getText(MessageKey.ZXDK_WXDKSQ_REPEATED,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.saveDksq(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
//			utilform.setType("jg");
//			boolean isExist = util.isNotExists(utilform);
//			if (!isExist) {
//				message = MessageUtil.getText(MessageKey.ZXDK_WXDKSQ_REPEATED,model.getXh());;
//				response.getWriter().print(getJsonMessage(message));
//				return null;
//			}
			result = service.saveDksqUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * ס������ɾ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
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
	 * У����Ϣ��������鿴
	 */
	@SystemAuth(url = url)
	public ActionForward DksqView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmsqModel myForm = (XnwxdkjmsqModel) form;
		XnwxdkjmsqModel model = service.getModel(myForm);
		Util util = new Util();
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
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jg", model);
		request.setAttribute("jmyjlist", util.getJmlCk(model.getJmyj().split(","), model.getJmbl()));
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		String zje = util.getXsxxWxjkzh(model.getXh());
		request.setAttribute("zje", zje);
		return mapping.findForward("view");
	}
	
	/**
	 * У����Ϣ���������޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editDksq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmsqModel myForm = (XnwxdkjmsqModel) form;
		XnwxdkjmsqModel model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XNWXDK);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xnwxdkjm_sq.do?method=editDksq";
		request.setAttribute("path", path);
		XnwxdkCsszService jcszService = new XnwxdkCsszService();
		String sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg);
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		Util util = new Util();
		request.setAttribute("jmllist",util.getJml());
		request.setAttribute("jmyjlist",util.getjmyjList(model.getJmbl()));
		request.setAttribute("xn", model.getXn());
		String zje = util.getXsxxWxjkzh(model.getXh());
		request.setAttribute("zje", zje);
		request.setAttribute("checkboxvlaue", model.getJmyj());
		return mapping.findForward("update");
	}
	
	//�ύ
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmsqModel myForm = (XnwxdkjmsqModel) form;
		String value = request.getParameter("values");
		myForm.setSqid(value);
		XnwxdkjmsqModel model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//����
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelDksq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqbh = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqbh, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			XnwxdkjmsqModel model = new XnwxdkjmsqModel();
			model.setSqid(sqbh);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqbh)) > 0) {
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
	 * У��ס�޽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XnwxdkjmsqModel model = (XnwxdkjmsqModel) form;

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
	
	/**
	 * 
	 *�������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getXnwxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnwxdkjmsqModel myForm = (XnwxdkjmsqModel) form;
		File wordFile = getXnwxdkWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getXnwxdkWord(XnwxdkjmsqModel myForm,HttpServletRequest request) throws Exception{

		User user = getUser(request); 
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
//		HashMap<String, String> XnwxdkMap = null;
		XnwxdkjmsqModel dkxx = service.getModel(myForm.getSqid());
		data.put("dkxx", dkxx);
		data.put("sqbl", dkxx.getJmbl());
		String zzbl = "";
		XnwxdkjmJgModel jgModel = new XnwxdkjmJgService().getModel(dkxx.getSqid());
		if(jgModel != null && StringUtils.isNotNull(jgModel.getJmbl())){
			zzbl = jgModel.getJmbl();
		}
		data.put("zzbl", zzbl);
//		data.put("xyzsjg", myForm);
		Util util = new Util();
		HashMap<String, String> xsxx = util.getxsxx(dkxx.getXh());
		data.putAll(xsxx);
		String dkzje = util.getXsxxWxjkzh(dkxx.getXh());
		String jml = dkxx.getJmbl().replace("%", "");
		data.put("dkzje", dkzje);
		data.put("jmje", (Float.parseFloat(dkzje)*Float.parseFloat(jml))/100);
		data.put("jtcylist", util.getjtcyxx(dkxx.getXh()));
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//zxdk","zjdx_wxjm_sq.xml",myForm.getSqid()+dkxx.getXh());
		
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward gettXnwxdkTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnwxdkjmsqModel myForm = (XnwxdkjmsqModel) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setSqid(values[i]);
				File file = getXnwxdkWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	//ѡ��ѧ����֤��ֵ��ѧ��������ʦά����Ϣ��ʱ������ظ��жϣ��Ƿ��д�����жϣ�
	//��ȡ�ܽ��
	public ActionForward selectXsYzFz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String flag = request.getParameter("flag");
		User user = getUser(request);
		Util util = new Util();
		UtilForm utilform = new UtilForm();
		utilform.setXh(xh);
		if(flag.equals("qb")){
			utilform.setType("qb");
		}else{
			utilform.setType("jg");
		}
		boolean isExist = util.isNotExists(utilform);
		String cfbz = "wsj";
		if(!isExist){
			cfbz = "ysj";
		}
		String dkjl = util.getWxdkjl(xh);
		String zje = util.getXsxxWxjkzh(xh);
		String sfbys = util.getSfbys(user.getUserName(), user.getUserType())?"��":"��";
		Map<String,String> map = new HashMap<String, String>();
		map.put("cfbz", cfbz);
		map.put("dkjl", dkjl);
		map.put("zje", zje);
		map.put("sfbys",sfbys);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	//�ı�������ʱ�ı��Ӧ�ļ�������
	public ActionForward changeJmbl(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response) throws Exception{
		String jmbl = request.getParameter("jmbl");
		Util util = new Util();
		List<HashMap<String, String>> jmbllist = util.getjmyjList(jmbl);
		StringBuilder html = new StringBuilder();
		html.append("<table  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		for (HashMap<String, String> hashMap : jmbllist) {
			html.append("<tr>");
			html.append("<td>");
			html.append("<input type=\"checkbox\" name=\"jmyj\"");
			html.append(" value=\""+hashMap.get("xssx")+"\"/>");
			html.append("</td>");
			html.append("<td>");
			html.append(hashMap.get("jmyj"));
			html.append("</td>");
			html.append("</tr>");
		}
		html.append("</table>");
		Map<String,String> map = new HashMap<String, String>();
		map.put("html", html.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
	    return null;
	}
}
