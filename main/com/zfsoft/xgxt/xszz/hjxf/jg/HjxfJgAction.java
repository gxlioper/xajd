/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-15 ����11:51:16 
 */  
package com.zfsoft.xgxt.xszz.hjxf.jg;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqForm;
import com.zfsoft.xgxt.xszz.hjxf.unit.Unit;
import com.zfsoft.xgxt.xszz.hjxf.unit.UnitForm;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgModel;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-15 ����11:51:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjxfJgAction extends SuperAction<HjxfJgForm, HjxfJgService> {
	private final String HJXFDK="hjxfdk";
	private HjxfJgService service = new HjxfJgService();
	
	private static final String url = "xszz_hjxf_jg.do";
	
	/**
	 * У����Ϣ��������ʼ����ѯ����
	 */
	@SystemAuth(url = url)
	public ActionForward getJgCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjxfJgForm model = (HjxfJgForm) form;
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
		String path = url;
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * У����Ϣ����������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjxfJgForm model = (HjxfJgForm) form;
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(HJXFDK);
		request.setAttribute("jbxxList", jbxxList);
		String path = "hjxf_jg.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqlist", Base.getXqList());
		request.setAttribute("xnlist", Base.getXnndList());
		model.setXq(Base.currXq);
		model.setXn(Base.currXn);
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(Base.currXq.equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		
		request.setAttribute("jqjzsj", new Unit().getJqjzsj());
		//������Ϣ����
		return mapping.findForward("add");
	}
	
	public ActionForward selectXsYzFz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String xq = request.getParameter("xq");
		String xn = request.getParameter("xn");
		String flag = request.getParameter("flag");
		User user = getUser(request);
		Unit util = new Unit();
		UnitForm utilform = new UnitForm();
		utilform.setXh(xh);
		utilform.setXn(xn);
		utilform.setXq(xq);
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
		String  djmc = util.getKnsdj(xh, xn, xq);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("cfbz", cfbz);
		map.put("djmc", djmc);
		map.put("hjxx", util.getHistoryHjInfo(xh));
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * У����Ϣ�������뱣��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveHjxfjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjxfJgForm model = (HjxfJgForm) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		Unit util = new Unit();
		UnitForm utilform = new UnitForm();
		utilform.setType("jg");
		utilform.setXh(model.getXh());
		utilform.setXn(model.getXn());
		utilform.setXq(model.getXq());
		boolean isExist = util.isNotExists(utilform);
		if(model.getType().equals("save")){
			
			// �жϵ�ǰѧ���Ƿ���У��ס�޽��
			if (!isExist) {
				message = MessageUtil.getText(MessageKey.XSZZ_HJXF_REPEATED,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.saveHjxf(model, user);
		}else if(model.getType().equals("update")){
//			utilform.setType("jg");
//			boolean isExist = util.isNotExists(utilform);
//			if (!isExist) {
//				message = MessageUtil.getText(MessageKey.ZXDK_WXDKSQ_REPEATED,model.getXh());;
//				response.getWriter().print(getJsonMessage(message));
//				return null;
//			}
			boolean isNotExist = service.isNotExists(model);
			if (!isNotExist) {
				message = MessageUtil.getText(MessageKey.XSZZ_HJXF_REPEATED,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.saveHjxfUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-3-16 ����07:20:52
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
	public ActionForward editHjxf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjxfJgForm myForm = (HjxfJgForm) form;
		HjxfJgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(HJXFDK);
		request.setAttribute("jbxxList", jbxxList);
		String path = "hjxf_jg.do?method=editHjxf";
		request.setAttribute("path", path);
		request.setAttribute("hjxx", new Unit().getHistoryHjInfo(model.getXh()));
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		request.setAttribute("xn", model.getXn());
		request.setAttribute("xq", model.getXq());
		request.setAttribute("xqlist", Base.getXqList());
		request.setAttribute("xnlist", Base.getXnndList());
		request.setAttribute("djmc", service.getKnsdj(model));
		request.setAttribute("jqjzsj", new Unit().getJqjzsj());
		return mapping.findForward("update");
	}
	
	/**
	 * ����ɾ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delHjxf(ActionMapping mapping, ActionForm form,
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
	 * ����ѧ�ѵ���
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HjxfJgForm model = (HjxfJgForm) form;

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
	
	/*
	 * �鿴
	 */
	@SystemAuth(url = url)
	public ActionForward HjxfView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjxfJgForm myForm = (HjxfJgForm) form;
		HjxfJgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(HJXFDK);
//		HashMap<String, String> xl = service.getXlCk(model);
//		HashMap<String, String> xyjzyy = service.getXyZsyy(model);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("ck", model);
		request.setAttribute("hjxx",new Unit().getHistoryHjInfo(model.getXh()));
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		request.setAttribute("djmc", service.getKnsdj(model));
//		request.setAttribute("zsjgxx", StringUtils.formatData(model));
//		request.setAttribute("xlxx", xl);
//		request.setAttribute("xyjzyy", StringUtils.formatData(xyjzyy));
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 *�������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getHjxfjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HjxfJgForm myForm = (HjxfJgForm) form;
		File wordFile = getHjxfWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getHjxfWord(HjxfJgForm myForm,HttpServletRequest request) throws Exception{

		User user = getUser(request); 
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HjxfJgForm jg = service.getModel(myForm.getJgid());
		String sqyy = HtmlUtil.xmlZy(jg.getSqyy());
		jg.setSqyy(sqyy);
	    data.put("jg", jg);
		Unit util = new Unit();
		HashMap<String, String> xsxx = util.getxsxx(jg.getXh());
		List<HashMap<String, String>> jtcylist = util.getjtcyxx(jg.getXh());
		List<String> nulllist = util.getNullList(4-jtcylist.size());
		data.putAll(xsxx);
		data.put("jtcylist", jtcylist);
		data.put("nulllist", nulllist);
		HashMap<String,String> jtqkdcMap = util.getJtqkdc(jg.getXh());
		if(jtqkdcMap != null){
			data.putAll(jtqkdcMap);
		}
		String dkflag = "0";//��
		if(jg.getDkqk().indexOf("��У������ѧ����") != -1){
			dkflag = "1";
		}else if(jg.getDkqk().indexOf("��Դ����ѧ����") != -1){
			dkflag = "2";
		}else{
			dkflag = "0";
		}
		data.put("dkflag", dkflag);
		String xlflag = "";//ר��
		if(StringUtils.isNotNull(xsxx.get("xlmc"))){
			if(xsxx.get("xlmc").indexOf("��") != -1){
				xlflag = "1";//����
			}else if(xsxx.get("xlmc").indexOf("ר") != -1){
				xlflag = "0";
			}else{
				xlflag = "";
			}
		}
		data.put("xlflag", xlflag);
		String[] xns = jg.getXn().split("-");
		data.put("ksxn", xns[0]);
		data.put("jsxn", xns[1]);
		data.putAll(service.getJqNdYf(jg.getJqjzsj()));
		data.put("dcmc",service.getKnsdj(jg));
		data.put("ljqfje", Float.parseFloat(jg.getWnqfje())+Float.parseFloat(jg.getHjje()));
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xszz","hjxfsqb.xml",jg.getXh()+xsxx.get("xm"));
		
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward gettHjxfTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HjxfJgForm myForm = (HjxfJgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setJgid(values[i]);
				File file = getHjxfWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	//���ܱ���
	public ActionForward getHzbexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HjxfJgForm model = (HjxfJgForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();

		User user = getUser(request);
		List<HashMap<String, String>>  resultList = null;
		// ��ѯ
	    resultList = service.getJtknXshjList(model.getXn(), user);
	    HashMap<String, String> hzmap = service.getSumHz(model.getXn(), user);
		// ��ѯ�����м�¼������ҳ
		Map<String,Object> data = new HashMap<String,Object>();
		data.putAll(hzmap);
		data.put("rs", resultList);
		File  file = FreeMarkerUtil.getExcelFile(data, "classpath://templates//xszz", "hjxf_hz.xml", "����ѧԺ����ѧ�ѻ��ܱ�");
		FileUtil.outputExcel(response, file);
		return null;
    }
}
