/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-4 ����06:11:52 
 */  
package com.zfsoft.xgxt.xpjpy.tjcx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: ͳ�Ʋ�ѯ 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-11-4 ����06:11:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjcxAction extends SuperAction{
	
	private String targetFilePath;
	
	private static final String url = "pj_tjcx.do";

	/**
	 * 
	 * @����:����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-11-7 ����06:51:43
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
	public ActionForward viewTjcxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();

		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = new ArrayList<HashMap<String,String>>();
			if(Base.xxdm.equals("10277")){
				resultList = service.getPageListShTyxy(model, user);
			}else{
				resultList = service.getPageList(model,user);
			}
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//Ĭ�ϸ߼���ѯ����
		String pjzq = csszService.getCsz("pjzq");
		SearchModel searchModel=new SearchModel();	
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		if("1".equals(pjzq)){ 
			searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		}

		request.setAttribute("searchTj", searchModel);
		
		String path = "pj_tjcx.do";
		
		request.setAttribute("pjzq", pjzq);
		request.setAttribute("path", path);
		request.setAttribute("cssz", csszModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewTjcxList");
	}
	
	/**
	 * 
	 * @����:����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-2 ����11:06:08
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
	public ActionForward expHjmdtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		TjcxService service = new TjcxService();
		CommService comService = new CommService();
		HjmdExportModel exporModel = new HjmdExportModel();
		comService.getModelValue(exporModel, request);
		//��Ŀ������������
		String array_xmlx = request.getParameter("array_xmlx");
		array_xmlx = java.net.URLDecoder.decode(array_xmlx,"UTF-8");
		String xmlxs[] = array_xmlx.split("!!array!!");
		String array_xmmc = request.getParameter("array_xmmc");
		if(null!=array_xmmc){
		array_xmmc = java.net.URLDecoder.decode(array_xmmc,"UTF-8");
		String xmmcs[] = array_xmmc.split("!!array!!");
		exporModel.setXmmc(xmmcs);
		}
		exporModel.setXmlx(xmlxs);
		response.setContentType("application/vnd.ms-excel");
		//�����ļ�������ֹ��Ϊapi����ϵͳ�����Բ��ö������excel��׺���ĳ�.do
		 response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("xpj_tjcx.xls".getBytes(), "GBK") + "\"");
		String dcfs = request.getParameter("dcfs");
		
		if(Base.xxdm.equals("10335")){ //�㽭��ѧ
			//������������word
			if("dcwd".equals(dcfs)){
			String path =	servlet.getServletContext().getRealPath(
				"/temp/")+"/"+"/dummy.doc";
				service.createWord(response,exporModel,user,path);
				return null;
			}
			
			if("njdcwd".equals(dcfs)){
				service.createWordNjdc(response,exporModel,user);
				return null;
			}
			if("njdc".equals(dcfs)){
				service.exportHjmdtj_10335_njdc(exporModel, response.getOutputStream(),user);
			}else{
				service.exportHjmdtj_10335(exporModel, response.getOutputStream(),user);
			}
		}else if(Base.xxdm.equals("12072")){
			service.exportHjmdtj_12072(exporModel, response.getOutputStream(),user);
		}else{
			service.exportHjmdtj(exporModel, response.getOutputStream(),user);
		}
		return null;
	}
	
	/**
	 * @����:�������������Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-6 ����05:24:54
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
	public ActionForward expHjmdtj_11458(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		TjcxService service = new TjcxService();
		CommService comService = new CommService();
		HjmdExportModel exporModel = new HjmdExportModel();
		comService.getModelValue(exporModel, request);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String("��ʾ����".getBytes("gb2312"),"iso-8859-1")+".xls");
		
		service.exportHjmdtj_11458(exporModel, response.getOutputStream());
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:�㽭��ѧ��ѧ���������һ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-3-10 ����02:52:08
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
	public ActionForward viewJxjmefpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			model.setXn(searchModel.getSearch_tj_xn()[0]);//ѧ��
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getJxjmefpList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		request.setAttribute("searchTj", searchModel);
		model.setSearchModel(searchModel);
		model.setXn(searchModel.getSearch_tj_xn()[0]);
		
		//��ͳ����Ŀ
		List<HashMap<String, String>> pjxmList = service.getPjxmList(model);

		request.setAttribute("pjxmList", pjxmList);
		request.setAttribute("path", "pj_jxjmefp.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJxjmefpList");
	}
	
	
	/**
	 * 
	 * @����:���ͳ����Ŀ���㽭��ѧ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-3-11 ����11:22:32
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
	
	public ActionForward initPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		String xn = request.getParameter("xn");//ѧ��
		
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			xn=csszModel.getXn();
		}
		model.setXn(xn);
		
		List<HashMap<String, String>> pjxmList = service.getPjxmList(model);
		JSONArray dataList = JSONArray.fromObject(pjxmList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @����:ͳ�Ƶ������㽭��ѧ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-17 ����11:43:51
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
	public ActionForward exportViewTjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
			
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			xn=csszModel.getXn();
		}
		model.setXn(xn);
		
		User user = getUser(request);
		
		File file = service.getTjjgFile(model,user);
		//�����ļ�
		FileUtil.outputExcel(response, file);
		return null;
			
	}
	
	
	/**
	 * 
	 * @����:���Ż��ܵ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-29 ����03:10:57
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
	public ActionForward exportFfhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
			
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			xn=csszModel.getXn();
		}
		model.setXn(xn);
		
		User user = getUser(request);
		
		File file = service.exportFfhz(model,user);
		//�����ļ�
		FileUtil.outputExcel(response, file);
		return null;
			
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportGjjxjhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
			
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			xn=csszModel.getXn();
		}
		model.setXn(xn);
		response.setContentType("application/vnd.ms-excel");
		User user = getUser(request);
		
		service.exportGjjxjhz(model,response.getOutputStream(),user);
		
		return null;
			
	}
	
	
	/**
	 * 
	 * @����:�㽭��ѧ����֤���ӡ
	 * @���ߣ�cq
	 * @���ڣ�2015-5-5 ����09:34:05
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
	@SystemAuth(url = "pj_zsdy.do")
	public ActionForward viewZsdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.viewZsdy(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
//		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
//		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
//			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
//		}
		request.setAttribute("searchTj", searchModel);
		String path = "pj_zsdy.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
	
		return mapping.findForward("viewZsdy");
	}
	
	/**
	 * 
	 * @����:�㽭��ѧ֤���ӡ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-6 ����02:26:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	@SystemAuth(url = "pj_zsdy.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportZs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String fileName = "zsprint.xls"; // �����ļ����ư�����չ��
		response.setContentType("application/octet-stream");
		try {
			response.setHeader( "Content-Disposition", "attachment;filename="  + new String( "֤���ӡ���.xls".getBytes("gb2312"), "ISO8859-1" ) );
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		OutputStream os = null;
		FileInputStream fis = null;
		try {
			//�����������ļ�
			os = response.getOutputStream();
			fis = new FileInputStream(ResourceUtils.getFile("classpath:templates/xszz/excel")+"/" + fileName); //���������ļ�·��
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer)) > 0){
				os.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(os != null){
					os.flush();
					os.close();
				}
				if(fis != null){
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * �㽭��ѧ֤���ӡ�������NEW
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportZsNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		TjcxModel myForm = (TjcxModel) form;
		TjcxService service = new TjcxService();
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		String filePath = servlet.getServletContext().getRealPath("")+"/WEB-INF/classes/templates/xszz/excel/zsprint.xls";
		response.setContentType("application/octet-stream");
		response.setHeader( "Content-Disposition", "attachment;filename="  + new String( "֤���ӡ���.xls".getBytes("gb2312"), "ISO8859-1" ) );
		service.exportZsNew(myForm, user, response, filePath);
		return null;
	}
	
	
	/**
	 * 
	 * @����:������ݵ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-6 ����05:06:36
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
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		String lxmc = request.getParameter("lxmc");
		
		User user = getUser(request);
		
		File file = service.exportData(model,user,lxmc);
		//�����ļ�
		FileUtil.outputExcel(response, file);
		return null;
		
	}
	
	/**
	 * �Ϻ�����ѧԺ���������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportDataShty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel  model = (TjcxModel ) form;
		TjcxService service = new TjcxService();
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageListShTyxy(model,
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
	 *  ��ȡ�����������ܱ�Word��.
	 *  <P>��Ϊ��̶��ѧ���Ի�</P>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-19 15:39
	 * @param
	 * @return org.apache.struts.action.ActionForward
	 * @throw IOException
	 */
	public ActionForward getPymdhzb(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response) throws IOException {

		TjcxModel  model = (TjcxModel ) form;
		TjcxService service = new TjcxService();

		//����ѧ�ꡢѧԺ�����ѯ�����������ܱ��ļ�����
		File[] files = service.getPymdhzbFiles(model);

		if(files.length>0){
			if(files.length>1){
				File zipFile = ZipUtil.zip(files);
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files[0]);
			}
		}
		return null;
	}

	public ActionForward getJxjExcel(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("userName",user.getRealName());
		map.put("xn",searchModel.getSearch_tj_xn()[0]);
		map.put("xqmc",Base.getXqmcForXqdm(searchModel.getSearch_tj_xq()[0]));
		if(searchModel.getSearch_tj_xy()!=null) {
			String[] xymcs = service.getXymcByDms(searchModel.getSearch_tj_xy());
			String xymc = Arrays.toString(xymcs);
			xymc=xymc.substring(xymc.indexOf("[")+1,xymc.indexOf("]"));
			map.put("xymc",xymc);
		}

		if("tj".equals(model.getType())){
			model.setType("jxjtjExcel");
			List<HashMap<String,String>> resultList = service.getAllList(model,user);
			File file = service.getTjExcelFile(resultList,map);//���ɵ����ļ�
			FileUtil.outputExcel(response, file);
		}else{
			model.setType("jxjlqExcel");
			String sj = new SimpleDateFormat("yyyy��MM��dd��").format(new Date());
			map.put("sj", sj);
			List<HashMap<String,String>> resultList = service.getAllList(model,user);
			File file = service.getLqExcelFile(resultList,map);//���ɵ����ļ�
			FileUtil.outputExcel(response, file);
		}

		return null;
	}
	
}
