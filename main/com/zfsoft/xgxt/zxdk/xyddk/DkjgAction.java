/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-28 ����02:13:48 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.gygl.gyjl.GyjltjForm;
import com.zfsoft.xgxt.xpjpy.tjcx.TjcxModel;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdDao;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdForm;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdService;

import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-28 ����02:13:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DkjgAction extends SuperAction<XyddkModel, DkjgService> {

	private static final String GJZXDK = "gjzxdk";
	
	private static final String url = "zxdk_gjdk_dkjg.do";
	
	/**
	 * 
	 * @����: ������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-28 ����02:16:33
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
	public ActionForward dkjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "zxdk_gjdk_dkjg.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		if(Globals.XXDM_ZJJDZYJSXY.equals(Base.xxdm)){
			searchModel.setSearch_tj_xn(new String[]{Base.afterXn});
		}else{
			searchModel.setSearch_tj_xn(new String[]{Base.currXn});
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xxdm", Base.xxdm);
		
		User user = getUser(request);
		request.setAttribute("userName", user.getUserName());
		
		return mapping.findForward("dkjgList");
	}
	
	
	/**
	 * 
	 * @����: ajax���ش������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:38:22
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
	public ActionForward getDkjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkjgService service = getService();
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-28 ����03:05:35
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
	public ActionForward addDkjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkModel model = (XyddkModel) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("dqxn", Base.currXn);
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		String path = "zxdkDkjg.do?method=addDkjg";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("addDkjg");
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editDkjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkjgService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		XyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("xxdm", Base.xxdm);
		/**
		 * ��ʦ����Ի�����
		 */
		if("10511".equals(Base.xxdm)){
			XyddkService sqService = new XyddkService();
			List<HashMap<String, String>> nddkList = sqService.getNdkbList(myForm.getId());
			request.setAttribute("nddkList", nddkList);
			HashMap<String, String> jesxMap = sqService.getXsxxByHsd(model.getXh());
			request.setAttribute("jesx", jesxMap.get("jesx"));
			request.setAttribute("mkxxForm", myForm);
		}
		return mapping.findForward("editDkjg");
	}
	
	@SystemAuth(url = url)
	public ActionForward ckDkjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		DkjgService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		XyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> fdxxList = null;
			
			if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
				fdxxList = service.getFdxxListForXh(model.getXh());
			}else if("10511".equals(Base.xxdm)){
				fdxxList = service.getFdxxListHsd(model.getHtbh());
			}else{
				fdxxList = service.getFdxxList(model.getHtbh());
			}
			request.setAttribute("fdxxList", fdxxList);
			
			WyxxService wyxxService = new WyxxService();
			request.setAttribute("wyxx", StringUtils.formatData(wyxxService.getModel(model.getHtbh())));
			
			List<HashMap<String,String>> xdsqList = service.getDkjgXdxxTg(model.getHtbh());
			/**
			 * ��ʦ����Ի�����
			 */
			if("10511".equals(Base.xxdm)){
				XyddkService sqService= new XyddkService();
				List<HashMap<String, String>> nddkList = sqService.getNdkbList(myForm.getId());
				request.setAttribute("nddkList", nddkList);
			}
			request.setAttribute("xdsqList", xdsqList);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		//��ʾ������Ϣ
//		request.setAttribute("xdxxlist", new DkjgService().getDkjgXdxxTg(model.getHtbh()));
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("ckDkjg_zjdx");
		}
		
		return mapping.findForward("ckDkjg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������ѧ����-������ѧ����-������-ɾ��VALUES:{ids}")
	public ActionForward delDkjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkjgService service = getService();
		String ids = request.getParameter("ids");
		XyddkService sqservice = new XyddkService();
		/**
		 * ��ʱ�ǵ�ѡ�ģ��������»�ʦ���жϰ���ѡ����
		 * ���зŴ���¼�Ľ�����ܱ�ɾ��
		 *
		 */
		if("10511".equals(Base.xxdm)){
			String htbh = service.getModel(ids.split(",")[0]).getHtbh();
			String fdnum = new HsdxdService().getfdNum(htbh);
		
			if(!("0".equals(fdnum))){
				String message = "���зŴ���¼�� ������ɾ����";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		boolean result = service.runDelete(ids.split(",")) > 0;
		/**
		 * ����ǻ���ʦ����ѧ,ɾ��xg_hsd_zxdk_ndkb�е�����
		 */
		if("10511".equals(Base.xxdm)){
			sqservice.delFdb(ids.split(","));
			sqservice.delNdkb(ids.split(","));
		}
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward dcjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DkjgService service = getService();
		XyddkModel model = (XyddkModel) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ

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
	 * @����: ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-11 ����02:04:36
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
	@SystemAuth(url = "zxdk_gjdk_xdsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if (!"stu".equals(user.getUserType())){
			request.setAttribute("message", "��ҳ��ֻ��ѧ���û����ʡ�");
			return mapping.findForward("error");
		}
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		if (Constants.CLOSE.equals(csszModel.getXdKg())){
			request.setAttribute("message", "��������δ���š�");
			return mapping.findForward("error");
		}
		
		DkjgService service = getService();
		XyddkModel dkxx = service.getModelByXh(user.getUserName());
		
		if (dkxx == null){
			request.setAttribute("message", "��δ�����������ѧ���");
			return mapping.findForward("error");
		}
		
		List<HashMap<String,String>> xdsqList = service.getXdsqList(dkxx.getHtbh());
		request.setAttribute("xdsqList", xdsqList);
		request.setAttribute("dkxx", StringUtils.formatData(dkxx));
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("path", "zxdk_gjdk_xdsq.do");
		request.setAttribute("xxdm", Base.xxdm);
		//�����Ƽ���ѧ���Ի���ʾ����ȵ�����ͷ�
//		if("10704".equals(Base.xxdm)){
//			int fs = service.getLowestLastYear(user.getUserName());
//			request.setAttribute("fs", fs);
//		}
	//	request.setAttribute("splc", csszModel.getXydxdshlc());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xdsq");
	}
	
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ������[���ţ�445]
	 * @�޸��ߣ�yxy �޸����ݣ������ύ����
	 * @���ڣ�2014-10-11 ����04:11:04
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
	
	public ActionForward saveXdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkjgService service = getService();
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		boolean result = service.saveXdsq(model, user);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**��ѧ��ѧ���ѯ��¼����**/
	public ActionForward getCountByXhAndXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DkjgService service = getService();
		XyddkModel model = (XyddkModel) form;
		
		String count = service.getCountByXhAndXn(model);
		response.getWriter().print(count);
		return null;
	}
	
	
	
	
	/**��ӡ�����**/
	@SystemAuth(url = url)
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String[] ids = request.getParameter("ids").split(",");
			
			if(null!=ids && ids.length == 1){//һ������
				File file=print(ids[0],request);
				FileUtil.outputWord(response, file);
			}else{//��������
				List<File> files = new ArrayList<File>();
				for(String id : ids){
					File file=print(id,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
			return null;
	}
	
	
	/**
	 * 
	 * @����:�����Ի���������˸��Ի����� returnData_10335()
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-25 ����01:56:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private synchronized File print(String id,HttpServletRequest request) throws Exception{

		Map<String, Object> data = new HashMap<String, Object>();
		
		DkjgService service = getService();
		XyddkModel model = service.getModel(id);
		if(Base.xxdm.equalsIgnoreCase("10335") && StringUtils.isNotNull(model.getXzf())){
			model.setZsfdks(Integer.parseInt(model.getZsfdks())*Integer.parseInt(model.getXzf())+"");
			model.setXfdks(Integer.parseInt(model.getXfdks())*Integer.parseInt(model.getXzf())+"");
		}
		data.put("m", model);
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		if(Base.xxdm.equalsIgnoreCase("10335")){
			data = this.returnData_10335(data, xsjbxx, model);
		}
		data.put("j", xsjbxx);
		if(Base.xxdm.equalsIgnoreCase("10335")){
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "zxdksqb_10335.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
		}
		return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "shhy_gjdk.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
	}
	
	/**
	 * 
	 * @����:����ID��ѯ�����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-25 ����03:16:14
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
	public ActionForward dkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		DkjgService service = getService();
		
		HashMap<String, String> dkxxMap = service.getDkxxSq(id);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(dkxxMap));
		return null;
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�����ѧ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-11-25 ����01:51:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @param xsjbxx
	 * @param model
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public Map<String, Object> returnData_10335(Map<String, Object> data,HashMap<String,String> xsjbxx,XyddkModel model) throws Exception{
		XsxxService xsxxService = new XsxxService();
		data.putAll(xsxxService.transformYear_Month(xsjbxx.get("csrq")));
		/*ѧ���ж�*/
		String xlmc = "";
		if(StringUtils.isNotNull(xsjbxx.get("xlmc"))){
			 xlmc = xsjbxx.get("xlmc");
		}
		if(xlmc.indexOf("��") >= 0){
			xlmc = "��";
			String xz = xsjbxx.get("xz");
			String bkxzpd = "";
			if(xz.indexOf("��") >= 0 || xz.indexOf("4") >= 0){
				bkxzpd = "4";
			}else if(xz.indexOf("��") >= 0 || xz.indexOf("5") >= 0){
				bkxzpd = "5";
			}else if(xz.indexOf("��") >= 0 || xz.indexOf("7") >= 0){
				bkxzpd = "7";
			}else if(xz.indexOf("��") >= 0 || xz.indexOf("8") >= 0){
				bkxzpd = "8";
			}
			data.put("bkxzpd", bkxzpd);
		}else if(xlmc.indexOf("ר") >= 0){
			xlmc = "ר";
		}else if(xlmc.indexOf("��") >= 0 || xlmc.indexOf("˶") >= 0 ||  xlmc.indexOf("��") >= 0){
			xlmc = "��";			
		}
		data.put("xlmc", xlmc);
		//����������
		String qxmon = "";
		if(StringUtils.isNotNull(model.getDkqx())){
			qxmon = String.valueOf(Integer.parseInt(model.getDkqx())*12);
		}
		data.put("qxmon",qxmon);
		//2016��10��14�� ����������������� = �������� �� 12 �Ѿ�������û�й�ϵ��
//		if(StringUtils.isNotNull(model.getXzf())){
//			qxmon = String.valueOf(Integer.parseInt(model.getXzf())*12);
//		}
//		data.put("qxmon",qxmon);
		//��ĸ����Ϣ
		data.put("fqxx", xsxxService.getFather(xsjbxx.get("xh")));
		data.put("mqxx", xsxxService.getMother(xsjbxx.get("xh")));
		JtqkdcService service = new JtqkdcService();
		JtqkdcForm jtqk = service.getModel(xsjbxx.get("xh"));
		if(jtqk != null  && StringUtils.isNotNull(jtqk.getSnjtsr()) ){
			 data.put("jtrjysr", jtqk.getSnjtsr());
		}else{
			 data.put("jtrjysr", "");
		}
	   
		return data;
	}
	
	/**
	 * @����:�㽭��ѧ������ѧ����ѧԺ��԰�����ܱ�
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-11-30 ����02:46:56
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
	public ActionForward getDkjgtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkModel myForm = (XyddkModel) form;
		DkjgService service = getService();
		User user = getUser(request);
		List<HashMap<String, String>> dkjglist = service.getDkjgtj(myForm, user);
		
		String filePath = servlet.getServletContext().getRealPath("")+"/WEB-INF/classes/templates/zxdk/excel/dkprint.xls";
		response.setContentType("application/octet-stream");
		response.setHeader( "Content-Disposition", "attachment;filename="  + new String( "�㽭��ѧ������ѧ����ѧԺ��԰�����ܱ�.xls".getBytes("gb2312"), "ISO8859-1" ) );
		
		service.exportHzbNew(dkjglist, user, response, filePath);
		
		return null;
	}
	
	/**
	 * 
	 * @����: ���������ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����02:32:59
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
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String value = request.getParameter("values");
		HashMap<String, String> model = new DkjgService().getXdxx(value);
		User user = getUser(request);
		boolean result = new DkjgService().submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����02:57:17
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
	public ActionForward cancelXdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = new DkjgService().cancelRecord(id, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			HashMap<String, String> model = new HashMap<String, String>();
			model.put("id", id);
			model.put("splc", lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(id)) > 0) {
				model.put("shzt", Constants.YW_YTH);
			} else {
				model.put("shzt", Constants.YW_WTJ);

			}
			new DkjgDao().updateXdxx(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ��������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����03:18:37
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
	public ActionForward delXdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
//			if("10511".equals(Base.xxdm) ){
//				String fdNum = new HsdxdDao().getfdNumXd(values.split(","));
//				if(!("0").equals(fdNum)){
//					String message = "���зŴ���¼����������¼������ɾ����";
//					response.getWriter().print(getJsonMessage(message));
//					return null;
//				}
//			}
			boolean result = new DkjgService().delxdxx(ids);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, ids.length) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: �޸�������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����03:21:48
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
	public ActionForward updatedkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		XyddkModel myForm = (XyddkModel) form;
		String id = request.getParameter("id2");
		String xdly = request.getParameter("xdlys");
		String type = request.getParameter("type");
		XyddkModel myForm = new XyddkModel();
		myForm.setId(id);
		myForm.setXdly(xdly);
		myForm.setType(type);
		User user = getUser(request);
		boolean result = new DkjgService().saveXdsqUpdate(myForm, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
		
	}
	
	/**
	 * 
	 * @����: ������˲�ѯ�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-12 ����10:19:23
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
	public ActionForward getXdshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = null;
			if("10511".equals(Base.xxdm)){
				HsdxdForm hsdxdForm = new HsdxdForm();
				HsdxdDao hsdxdDao = new HsdxdDao();
				hsdxdForm.setSearchModel(model.getSearchModel());
				hsdxdForm.setShzt(model.getShzt());
				resultList =  hsdxdDao.getPageList(hsdxdForm, user);
			}else{
				resultList = new DkjgService().getXdshList(model, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "zxdk_gjdk_xdsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xdshlist");
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-12 ����11:00:06
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
	public ActionForward DgshXdxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		//GzkhKhsqService khsqService = new GzkhKhsqService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//HashMap<String, String> infoList = service.getKhshInfo(model);
			//request.setAttribute("rs", infoList);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
//			String message = khsqService.checkZjeAndGs(model.getXh(),model.getXn(),model.getYxgs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm());
//			if(!"true".equals(message)){
//				 response.getWriter().print(getJsonMessage(message));
//				 return null;
//			}
			User user = getUser(request);
			// ���浥�����
			boolean result = new DkjgService().saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> xdxx = null;
		if("10511".equals(Base.xxdm)){
			HsdxdDao hsdxddao = new HsdxdDao();
			xdxx = hsdxddao.getViewCk(model.getId());
		}else{
			xdxx = new DkjgService().getViewCk(model.getId());
		}
		xdxx.put("shid", request.getParameter("shid"));
		request.setAttribute("zsjgxx", xdxx);
		return mapping.findForward("xdDgsh");
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-12 ����11:10:33
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
	public ActionForward xdPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = new DkjgService().savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("xdPlsh");
	}
	
	/**
	 * @����: ���һ����˳���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-12 ����11:10:53
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setId(sqid);
		// ���һ������
		boolean isSuccess = new DkjgService().cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ��˳������ж�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-12 ����11:11:19
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkModel model =new XyddkModel();
		if("10511".equals(Base.xxdm)){
			String id = request.getParameter("id");
			String fdNum = new HsdxdDao().getfdNumXd(new String[]{id});
			if(!("0").equals(fdNum)){
				String message = "���зŴ���¼����������¼������������";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = new DkjgService().cxshnew(shxx.get("ywid"),model,user);
		

		// ��˳����ɹ�
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-12 ����11:00:06
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
	public ActionForward viewXdsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		//GzkhKhsqService khsqService = new GzkhKhsqService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//HashMap<String, String> infoList = service.getKhshInfo(model);
			//request.setAttribute("rs", infoList);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> xdxx = null;
		if("10511".equals(Base.xxdm)){
			HsdxdDao hsdxddao = new HsdxdDao();
			xdxx = hsdxddao.getViewCk(model.getId());
		}else{
			xdxx = new DkjgService().getViewCk(model.getId());
		}
		xdxx.put("shid", request.getParameter("shid"));
		request.setAttribute("zsjgxx", xdxx);
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 * @����: ��ʦ����Ի����뱣��ݸ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����10:29:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveDkjgForHsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XyddkModel model = (XyddkModel)form;
		/**
		 * xns:ѧ������
		 * nzsfdks:ÿ��ס�޷Ѵ�������
		 * nxfdks:ÿ��ѧ�Ѵ�������
		 * nshfdks:ÿ������Ѵ�������
		 * nzsfyjes:ÿ��ס�޷�Ӧ�ɶ�����
		 * nxfyjes:ÿ��ѧ��Ӧ�ɶ�����
		 * filepath:�ϴ�gid
		 * xh:ѧ��
		 * rs:���
		 * message:������Ϣ��ʾ�ַ���
		 */
		String[] xns = request.getParameterValues("xn");
		String[] nzsfdks = request.getParameterValues("nzsfdk");
		String[] nxfdks = request.getParameterValues("nxfdk");
		String[] nshfdks = request.getParameterValues("nshfdk");
		String[] nzsfyjes = request.getParameterValues("nzsfyje");
		String[] nxfyjes = request.getParameterValues("nxfyje");
		String filepath = model.getFilepath();
		XyddkService service = new XyddkService();
		String xh = model.getXh();
		String message = "";
		boolean rs = true;
		if(xgxt.utils.String.StringUtils.isNotNull(model.getFilepath())){
			/**
			 * ����������Ƿ���ͬ���ļ�
			 */
			if(!service.checkWjmIsSame(model.getFilepath())){
				message = "����ͬ����pdf�ļ��������pdf�ļ����ƣ�";
				try {
					response.getWriter().print(getJsonMessage(message));
				} catch (IOException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				return null;
			}
		}
		
		/**
		 * �Ƿ������ͬ�ļ���,�������ֱ�ӷ���
		 */
	    boolean isFileExist = service.getWjmSameRs(xh, filepath);
		if(isFileExist){
			try {
				message = "��ʷ��¼����ͬ����pdf�ļ��������pdf�ļ����ƣ�";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * �Ƿ�������ͬѧ��������¼�������ֱ�ӷ���
		 */
		boolean isRecordSameXn = service.getXnXhSameRs(xns, xh,null);
		if(isRecordSameXn){
			try {
				message = "�Ѵ�����ͬѧ��������¼����ȷ�ϣ�";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
		}
		boolean htbhIsNotExists = new DkjgService().checkHtbhIsNotExists(null, model.getHtbh());
		if(!htbhIsNotExists){
			try {
				message = "��ͬ����Ѵ��ڣ���ȷ�ϣ�";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
		}
		String id =  UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setId(id);
		DkjgService dkjgService = new DkjgService();
		try {
			rs = dkjgService.runInsert(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		if(!rs){
			/**
			 * ���Ϊfalse;ֱ�ӷ���
			 */
			try {
				response.getWriter().print(rs ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL);
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		HashMap<String, String[]> paraMap = new HashMap<String, String[]>();
		paraMap.put("xns", xns);
		paraMap.put("nzsfdks", nzsfdks);
		paraMap.put("nxfdks", nxfdks);
		paraMap.put("nshfdks", nshfdks);
		paraMap.put("nzsfyjes", nzsfyjes);
		paraMap.put("nxfyjes", nxfyjes);
		/**
		 *��������xg_hsd_zxdk_ndkb��
		 */
		rs = service.saveRsBatch(paraMap, xh, id);
		/**
		 * ����xg_zxdk_hsd_xydfdb��
		 */
		try {
			rs = service.InsertIntoFdb(id);
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
			message = "����xg_zxdk_hsd_xydfdbʧ�ܣ�����id="+id+"��";
			try {
				response.getWriter().print(getJsonMessage(message));
				return null;
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		/**
		 * ����ʧ�ܣ�ɾ��ԭ�ȱ���ļ�¼
		 */
	    if(!rs){
	    	try {
	    		dkjgService.runDelete(new String[]{id});
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
	    }
	    String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		try {
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ��ʦ����Ի��޸ı���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����10:31:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveUpdateForHsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XyddkModel model = (XyddkModel)form;
		/**
		 * id:����id
		 * xns:ѧ������
		 * nzsfdks:ÿ��ס�޷Ѵ�������
		 * nxfdks:ÿ��ѧ�Ѵ�������
		 * nshfdks:ÿ������Ѵ�������
		 * nzsfyjes:ÿ��ס�޷�Ӧ�ɶ�����
		 * nxfyjes:ÿ��ѧ��Ӧ�ɶ�����
		 * filepath:�ϴ�gid
		 * xh:ѧ��
		 * rs:���
		 * message:������Ϣ��ʾ�ַ���
		 */
		String id = request.getParameter("id");
		String[] xns = request.getParameterValues("xn");
		String[] nzsfdks = request.getParameterValues("nzsfdk");
		String[] nxfdks = request.getParameterValues("nxfdk");
		String[] nshfdks = request.getParameterValues("nshfdk");
		String[] nzsfyjes = request.getParameterValues("nzsfyje");
		String[] nxfyjes = request.getParameterValues("nxfyje");
		String filepath = model.getFilepath();
		XyddkService service = new XyddkService();
		
		String xh = model.getXh();
		String message = "";
		boolean rs = true;
		if(xgxt.utils.String.StringUtils.isNotNull(model.getFilepath())){
			/**
			 * ����������Ƿ���ͬ���ļ�
			 */
			if(!service.checkWjmIsSame(model.getFilepath())){
				message = "����ͬ����pdf�ļ��������pdf�ļ����ƣ�";
				try {
					response.getWriter().print(getJsonMessage(message));
				} catch (IOException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				return null;
			}
		}
		/**
		 * �Ƿ������ͬ�ļ���,�������ֱ�ӷ���
		 */
	    boolean isFileExist = service.getWjmSameRsUpdate(xh, filepath);
		if(isFileExist){
			try {
				message = "��ʷ��¼����ͬ����pdf�ļ��������pdf�ļ����ƣ�";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
		}
		
		DkjgService dkjgService = new DkjgService();
		try {
			rs = dkjgService.runUpdate(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			try {
				try {
					response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
				} catch (IOException e1) {
					// TODO �Զ����� catch ��
					e1.printStackTrace();
				}
			} catch (Exception e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			}
			return null;
		}
		if(!rs){
			/**
			 * ���Ϊfalse;ֱ�ӷ���
			 */
			try {
				response.getWriter().print(getJsonMessageByKey(rs ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL));
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		HashMap<String, String[]> paraMap = new HashMap<String, String[]>();
		paraMap.put("xns", xns);
		paraMap.put("nzsfdks", nzsfdks);
		paraMap.put("nxfdks", nxfdks);
		paraMap.put("nshfdks", nshfdks);
		paraMap.put("nzsfyjes", nzsfyjes);
		paraMap.put("nxfyjes", nxfyjes);
		/**
		 * �޸ı���ǰ��Ҫ��ɾ��ԭ��xg_hsd_zxdk_ndkb������,Ȼ���ٲ������
		 */
		try {
			rs = new XyddkService().delFdb(new String[]{id});
			rs = service.delRs(id);
			if(!rs){
				message = "ɾ��xg_hsd_zxdk_ndkbʧ�ܣ�ɾ��id="+id+"��";
				response.getWriter().print(getJsonMessage(message));
			}
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		/**
		 *��������xg_hsd_zxdk_ndkb��
		 */
		rs = service.saveRsBatch(paraMap, xh, id);
		/**
		 * ����xg_zxdk_hsd_xydfdb��
		 */
		try {
			rs = service.InsertIntoFdb(id);
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
			message = "����xg_zxdk_hsd_xydfdbʧ�ܣ�����id="+id+"��";
			try {
				response.getWriter().print(getJsonMessage(message));
				return null;
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
	    String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		try {
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @����:��д���淽��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-2-22 ����01:08:08
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
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XyddkModel model = (XyddkModel) form;
		DkjgService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @throws IOException  
	 * @����:�Ƿ������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-19 ����10:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward checkSfXd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		User user = getUser(request);
		String xh = user.getUserName();
		DkjgService service = new DkjgService();
		boolean result = service.countBk(xh);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		JSONObject obj = JSONObject.fromBean(map);
		response.getWriter().print(obj);
		return null;
	}
	
	
	public ActionForward printJsxxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DkjgService service = new DkjgService();
		XyddkModel modelForm = (XyddkModel) form;
		XyddkModel model = service.getModel(modelForm.getId());
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	//���ģ����������word�ļ�
	private File getWord(XyddkModel xyddkModel)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		XyddkModel model = xyddkModel;
		String xh = model.getXh();
		//������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		String rxny = xsjbxx.get("rxrq");
		if(rxny!= null && rxny.length() >= 7){
			rxny = rxny.substring(0, 7);
		}
		String dkqx = model.getDkqx();
		if(StringUtil.isNull(dkqx)) {
			data.put("dknx", "");
		}else{
			double dknx = Double.parseDouble(dkqx)/12.0;
			data.put("dknx", dknx);
		}
		
		xsjbxx.put("rxny", rxny);
		data.put("rs", xsjbxx);
		data.put("model", model);
		//����������Ϣ
		KnsjgService knservice = new KnsjgService();
		HashMap<String, String> knmap = knservice.getKnsInfo(model.getXh(),model.getXn());
		data.put("knlx", knmap.get("dcmc"));
		data.put("knyy", knmap.get("sqly"));
		
		File wordFile = null;
		wordFile = FreeMarkerUtil.getWordFile(data,  Constants.TEP_DIR + "zxdk","jsdkxxcjb_12688.xml", model.getXh() +"["+xsjbxx.get("xm")+"]" + "-");
		return wordFile;
		
	}
}
