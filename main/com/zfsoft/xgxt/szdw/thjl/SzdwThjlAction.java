/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-17 ����10:04:32 
 */
package com.zfsoft.xgxt.szdw.thjl;

import java.io.File;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypx.YwxsypxForm;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.thjl.thlx.SzdwThlxService;
import com.zfsoft.xgxt.xlzx.tsxsgl.TsxsService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������: ̸����¼ά�� 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-7-17 ����10:04:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwThjlAction extends SuperAction {
	
	private static SzdwThjlService service = new SzdwThjlService();
	private static final String SZDWTHJL = "szdwthjl";
	private static List<HashMap<String,String>> jbxxList = null;
	private static List<HashMap<String,String>> thlxList = null;
	private ShlcInterface shlc = new CommShlcImpl();

	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(SZDWTHJL);
		SzdwThlxService thlxService = new SzdwThlxService();
		// ��ѯ����̸������
		try {
			thlxList = thlxService.getAllThlxList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final String url = "szdw_thjl_thjl.do";
	
	/**
	 * 
	 * @����:����ѧ��������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-7-17 ����03:43:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param xh
	 * void �������� 
	 * @throws
	 */
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
		request.setAttribute("jbxxList", jbxxList);
	}
	
	
	
	/** 
	 * @����:̸����¼��ѯ�б�
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward thjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> thjlInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(thjlInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("userType", user.getUserType());
		request.setAttribute("path", "szdw_thjl_thjl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("thjlManage");
	}
	
	/**
	 * 
	 * @����: ̸����¼����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-25 ����10:02:58
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
	public ActionForward thjlzj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		// ��ȡְ����
		User user = getUser(request);
		String zgh = user.getUserName();

		String path = "szdw_thjl.do?method=thjlzj";
		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("thjlzj");
	}
	

	/**
	 * 
	 * @����: ̸����¼����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-25 ����10:02:58
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
	public ActionForward thjlxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		// ��ȡְ����
		User user = getUser(request);
		String zgh = user.getUserName();

		if(StringUtils.isNotNull(zgh)){
			HashMap<String, String> jsInfoList = service.getInfoByZgh(zgh);
			request.setAttribute("thjlInfo", StringUtils.formatData(jsInfoList));
		}
		String path = "szdw_thjl.do?method=thjlxg";
		
		//ѧ��������Ϣ��ʾ����
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		
		return mapping.findForward("thjlxg");
	}
	
	/** 
	 * @����:̸����¼�����������ѯ
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward thjlDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		User user = getUser(request);
		String xh = request.getParameter("xh");
		if(StringUtil.isNull(xh) ){
			xh = myForm.getXh();
		}
		String zgh = request.getParameter("zgh");
		if(StringUtil.isNull(zgh)){
			zgh = myForm.getZgh();
		}
		String doType = request.getParameter("doType");
	 	HashMap<String, String> thjlInfo=new HashMap<String,String>();
		if(doType==null || "add".equals(doType)){//����̸����¼
			// ѧ�Ŵ��� ��ȡѧ����Ϣ
			if(user.getUserStatus().equals("stu") && StringUtil.isNull(xh)){
	 			xh = user.getUserName();
 			}
			// ְ���Ŵ��� ��ȡ��ʦ��Ϣ
 			if(StringUtil.isNull(zgh)){
	 			zgh = user.getUserName();
 			}
 			thjlInfo.put("sfzdgz", "0");
			thjlInfo.put("sfsdkt","0");
 			thjlInfo.put("gzdj", "����");
 			// ְ������Ϣ
 			if(StringUtils.isNotNull(zgh)){
 				HashMap<String, String> jsInfoList = service.getInfoByZgh(zgh);
 				if(jsInfoList!=null && jsInfoList.size()>0){
 		 			thjlInfo.put("zgh", zgh);
 		 			thjlInfo.put("jsxb", jsInfoList.get("xb"));
 		 			thjlInfo.put("jsxm", jsInfoList.get("xm"));
 		 			thjlInfo.put("jsbmmc", jsInfoList.get("bmmc"));
 				}
 			}
		}else if("update".equals(doType) || "view".equals(doType)){
			if(!StringUtil.isNull(myForm.getId())){

				thjlInfo = service.getThjlListById(myForm.getId());
				if(thjlInfo!=null && thjlInfo.size()>0){
					if("update".equals(doType) && StringUtils.isNull(thjlInfo.get("gzdj"))){
						thjlInfo.put("gzdj", "����");
					}
					if(StringUtils.isNotNull(thjlInfo.get("xh"))){
						xh = thjlInfo.get("xh");
					}
				}
				
	 		}
		}
		
		String path = "szdw_thjl.do?method=thjlDetail";
		
		//����ѧ��������Ϣ
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,xh);
		}
		request.setAttribute("jbxxList", jbxxList);
		SzdwThlxService thlxService = new SzdwThlxService();
		// ��ѯ����̸������
		List<HashMap<String,String>> thlxList = thlxService.getAllThlxList();
		request.setAttribute("thlxList", thlxList);

		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		
		request.setAttribute("doType", doType);
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("userStatus", user.getUserStatus());
		OptionUtil optionUtil = new OptionUtil();
		request.setAttribute("gzdjList", optionUtil.getOptions(OptionUtil.THJL_GZDJ));
		request.setAttribute("isnotList", optionUtil.getOptions(OptionUtil.ISNOT));
		request.setAttribute("xh",xh);
		List<HashMap<String,String>> thjlList = null;
		thjlList = service.getThjlListByXh(xh);
		if(thjlList!=null && thjlList.size()>0){
			thjlList.remove(0);//��ȡ��ʷ̸����¼
		}
		request.setAttribute("hisThjlList", thjlList);
		return mapping.findForward("thjlDetail");
	}
	public ActionForward getXjydxx(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		List<HashMap<String,String>> xjydList = service.getAllXjydList(xh);
		response.getWriter().print(JSONArray.fromObject(xjydList));
		return null;
	}
	public ActionForward getBjgcj(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		List<HashMap<String,String>> list  = service.getBjgcj(xh);
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
	/** 
	 * @����:����ѧ�Ų�ѯ̸����¼
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward thjlDetailByXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		User user = getUser(request);
		List<HashMap<String,String>> thjlList = null;

		thjlList = service.getThjlListByXh(myForm.getXh());
		List<HashMap<String,String>> xjydList = service.getAllXjydList(myForm.getXh());
 		HashMap<String,String> thjlInfo = new HashMap<String,String>();
 		//����ѧ��������Ϣ
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,myForm.getXh());
		}
		request.setAttribute("jbxxList", jbxxList);
		
		//�������ά����һ��̸����¼
		if(thjlList!=null && thjlList.size()>0){
			thjlInfo = thjlList.get(0);
			thjlList.remove(0);//��ȡ��ʷ̸����¼
		}
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("xjydList",xjydList);
		request.setAttribute("hisThjlList", thjlList);
		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("thjlDetailByXh");
		
	}
	
	/**
	 * @����:̸����¼����
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-26 ����4:25:25
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
	public ActionForward saveThjlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		if(!StringUtil.isNull(doType) && doType.equals("add")){
			
			SzdwThjlForm model = (SzdwThjlForm) form;
//			model.setKhhwt(URLDecoder.decode(URLDecoder.decode(model.getKhhwt(),"UTF-8"),"UTF-8"));
//			model.setMtyd(URLDecoder.decode(URLDecoder.decode(model.getMtyd(),"UTF-8"),"UTF-8"));
			if(model.getThnr() != null){
				model.setThnr(URLDecoder.decode(URLDecoder.decode(model.getThnr(),"UTF-8"),"UTF-8"));
			}

			/*if(model.getGzdj() != null) {
				model.setGzdj(URLDecoder.decode(URLDecoder.decode(model.getGzdj(),"UTF-8"),"UTF-8"));
			}
			if(model.getBz() != null) {
				model.setBz(URLDecoder.decode(URLDecoder.decode(model.getBz(),"UTF-8"),"UTF-8"));
			}*/
			if(model.getYyfx() != null){
				model.setYyfx(URLDecoder.decode(URLDecoder.decode(model.getYyfx(),"UTF-8"),"UTF-8"));
			}
			if(model.getGjcs() != null){
				model.setGjcs(URLDecoder.decode(URLDecoder.decode(model.getGjcs(),"UTF-8"),"UTF-8"));
			}
			if(model.getQtjy() != null){
				model.setQtjy(URLDecoder.decode(URLDecoder.decode(model.getQtjy(),"UTF-8"),"UTF-8"));
			}
			/*if("10351".equals(Base.xxdm)){
				model.setGzqx(URLDecoder.decode(URLDecoder.decode(model.getGzqx(),"UTF-8"),"UTF-8"));
			}*/
			Boolean flag;
			try {
				/*if("10351".equals(Base.xxdm)){
					 flag = service.saveThjlInfoForWzdx(model);
				}else{*/
					 flag = service.saveThjlInfo(model);
//				}
				response.getWriter().print(flag);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("thjlDetail");
		
	}
	/**
	 * @����:̸����¼�޸�
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-26 ����5:25:25
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
	public ActionForward updateThjlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm model = (SzdwThjlForm) form;
//		model.setKhhwt(URLDecoder.decode(URLDecoder.decode(model.getKhhwt(),"UTF-8"),"UTF-8"));
//		model.setMtyd(URLDecoder.decode(URLDecoder.decode(model.getMtyd(),"UTF-8"),"UTF-8"));

		if(model.getThnr() != null){
			model.setThnr(URLDecoder.decode(URLDecoder.decode(model.getThnr(),"UTF-8"),"UTF-8"));
		}
		/*if(model.getGzdj() != null) {
			model.setGzdj(URLDecoder.decode(URLDecoder.decode(model.getGzdj(),"UTF-8"),"UTF-8"));
		}*/
		/*if(model.getBz() != null) {
			model.setBz(URLDecoder.decode(URLDecoder.decode(model.getBz(),"UTF-8"),"UTF-8"));
		}*/
		if(model.getYyfx() != null){
			model.setYyfx(URLDecoder.decode(URLDecoder.decode(model.getYyfx(),"UTF-8"),"UTF-8"));
		}
		if(model.getGjcs() != null){
			model.setGjcs(URLDecoder.decode(URLDecoder.decode(model.getGjcs(),"UTF-8"),"UTF-8"));
		}
		if(model.getQtjy() != null){
			model.setQtjy(URLDecoder.decode(URLDecoder.decode(model.getQtjy(),"UTF-8"),"UTF-8"));
		}

		try {
			boolean flag = service.updateThjlInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/**
	 * @����:�Ƿ���̸����¼
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-10-09 ����10:25:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward haveThjlFlagByXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm model = (SzdwThjlForm) form;
		boolean flag = false;
		try {
			List<HashMap<String,String>> thjlList = service.getThjlListByXh(model.getXh());
			if(thjlList!=null && thjlList.size()>0){
				flag = true;
			}
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @����:ɾ��̸����¼
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-26 ����5:25:25
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
	public ActionForward deleteThjlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dealThjl = request.getParameter("dealThjl");
		String[] id = dealThjl.split(",");
		int count = 0;
		try {
			count = service.delThjlById(id);
			response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return null;
	}
	
	/** 
	 * @����:ѡ���ʦ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-24
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward getJsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		String doType = request.getParameter("doType");
		String gotoPath = request.getParameter("gotoPath");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> jsInfoList = service.getJsInfoList(myForm);
			JSONArray dataList = JSONArray.fromObject(jsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		gotoPath = gotoPath.replaceAll("[$]", "&");
		request.setAttribute("path", "szdw_thjl.do?method=getJsInfo");
		request.setAttribute("gotoPath", gotoPath);
		
		return mapping.findForward("getJsInfo");
	}
	
	/**
	 * @����:̸����¼����
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-26 ����4:25:25
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
	public ActionForward exportThjlData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm=(SzdwThjlForm)form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		
		TsxsService tsxsSv = new TsxsService();
		for(int i=0;i<resultList.size();i++){
			String knlxdm = resultList.get(i).get("knlxdm");
			if(!StringUtil.isNull(knlxdm)){
				resultList.get(i).put("knlxmc", tsxsSv.getKnlxMc(knlxdm));
			}
		}
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}

	@SystemAuth(url = url)
	public ActionForward sqList(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			//��ѯ
			List<HashMap<String,String>> thjlInfoList = service.getSqList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(thjlInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "szdw_thjl_thjl_sq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sqList");
	}

	/**
	 * ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zjsq(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		User user = getUser(request);
		String xh = request.getParameter("xh");
		if(StringUtil.isNull(xh) ){
			xh = myForm.getXh();
		}
		String zgh = request.getParameter("zgh");
		if(StringUtil.isNull(zgh)){
			zgh = myForm.getZgh();
		}
		String doType = request.getParameter("doType");
		HashMap<String, String> thjlInfo=new HashMap<String,String>();
		if(doType==null || "add".equals(doType)){//����̸����¼
			// ѧ�Ŵ��� ��ȡѧ����Ϣ
			if(user.getUserStatus().equals("stu") && StringUtil.isNull(xh)){
				xh = user.getUserName();
			}
			// ְ���Ŵ��� ��ȡ��ʦ��Ϣ
			if(StringUtil.isNull(zgh)){
				zgh = user.getUserName();
			}
			thjlInfo.put("sfzdgz", "0");
			thjlInfo.put("sfsdkt","0");
			thjlInfo.put("gzdj", "����");
			// ְ������Ϣ
			if(StringUtils.isNotNull(zgh)){
				HashMap<String, String> jsInfoList = service.getInfoByZgh(zgh);
				if(jsInfoList!=null && jsInfoList.size()>0){
					thjlInfo.put("zgh", zgh);
					thjlInfo.put("jsxb", jsInfoList.get("xb"));
					thjlInfo.put("jsxm", jsInfoList.get("xm"));
					thjlInfo.put("jsbmmc", jsInfoList.get("bmmc"));
				}
			}
		}else if("update".equals(doType) || "view".equals(doType)){
			if(!StringUtil.isNull(myForm.getSqid())){

				thjlInfo = service.getSqMap(myForm.getSqid());
				if(thjlInfo!=null && thjlInfo.size()>0){
					if("update".equals(doType) && StringUtils.isNull(thjlInfo.get("gzdj"))){
						thjlInfo.put("gzdj", "����");
					}
					if(StringUtils.isNotNull(thjlInfo.get("xh"))){
						xh = thjlInfo.get("xh");
					}
				}

			}
		}

		String path = "szdw_thjl.do?method=zjsq";

		//����ѧ��������Ϣ
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,xh);
		}
		request.setAttribute("jbxxList", jbxxList);
		SzdwThlxService thlxService = new SzdwThlxService();
		// ��ѯ����̸������
		List<HashMap<String,String>> thlxList = thlxService.getAllThlxList();
		request.setAttribute("thlxList", thlxList);

		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);

		request.setAttribute("doType", doType);
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("userStatus", user.getUserStatus());
		OptionUtil optionUtil = new OptionUtil();
		request.setAttribute("gzdjList", optionUtil.getOptions(OptionUtil.THJL_GZDJ));
		request.setAttribute("isnotList", optionUtil.getOptions(OptionUtil.ISNOT));
		request.setAttribute("xh",xh);
		return mapping.findForward("zjsq");
	}

	/**
	 * �������ӱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjsqBc(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		String doType = request.getParameter("doType");
		boolean flag = service.zjsqBc(myForm,doType);
		String key = flag ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(key));
		return null;
	}
	public ActionForward submit(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm model = (SzdwThjlForm) form;
		String id = request.getParameter("values");
		model.setSqid(id);
		model.setShzt(Constants.YW_SHZ);
		model.setType(SUBMIT);
		boolean result = service.update(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splc");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���

		boolean result = shlc.firstStepCancle(sqid,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			SzdwThjlForm model = new SzdwThjlForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			model.setShzt(Constants.YW_WTJ);
			result = service.update(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	public ActionForward del(ActionMapping mapping, ActionForm form,
							 HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("values");
		boolean f = service.del(ids.split(","));
		String msg = f ? MessageUtil.getText(MessageKey.SYS_DEL_SUCCESS) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}
}
