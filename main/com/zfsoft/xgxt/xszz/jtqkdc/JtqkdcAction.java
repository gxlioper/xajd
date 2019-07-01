package com.zfsoft.xgxt.xszz.jtqkdc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdcjcsz.JtqkdcJcszForm;
import com.zfsoft.xgxt.xszz.jtqkdcjcsz.JtqkdcJcszService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: ѧ������2013��֮��ͥ�������
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-18 ����05:52:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JtqkdcAction extends SuperAction {

	private static final String JTCY = "jtcy";
	private static final String JTQKDC = "jtqkdc";
	private static final String QTXX = "qtxx";
	private static final String FJXX = "fjxx";
	private static final String YXJTJJQK = "yxjtjjqk";
	private static final int CYSIZE = 6;//��ͥ��Ա���֧������
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> qtxxList = null;
	private List<HashMap<String,String>> jtcyList = null;
	private List<HashMap<String,String>> mkxxList = null;
	private List<HashMap<String,String>> jbxxList = null;
	private List<HashMap<String,String>> yxjtjjzkList = null;
	
	private static final String url = "xszz_jtqkdc_sq.do";
	
	/**
	 * 
	 * @����:������д��ͥ�������ҳ��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-18 ����05:51:42
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
	public ActionForward dcxxModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcJcszService jtszService = new JtqkdcJcszService();
		JtqkdcJcszForm jcszModel = jtszService.getModel();
		
		//��ת����Ҫ����Ȩ��(�������϶�)
		String writeAble = request.getParameter("writeAble");
		if (!"yes".equals(writeAble)&&(jcszModel== null||(jcszModel != null && !Boolean.valueOf(jcszModel.getIsOpen())))){
			request.setAttribute("message", "����δ���ż�ͥ�����д��");
			return mapping.findForward("error");
		}
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//��ѧ�ż��س�Ա�б� 
			List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
			request.setAttribute("cyList", cyList);
			
			//��ͥ��Ա�б����
			int blankSize = (CYSIZE - cyList.size()) < 0 ? 0 : (CYSIZE - cyList.size());
			request.setAttribute("blankList", service.getBlankList(blankSize));
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			request.setAttribute("type", model.getType());
			//��ͥ���������Ϣ����
			JtqkdcForm jtqkModel = service.getModel(model.getXh());
			
			if (jtqkModel != null){
				BeanUtils.copyProperties(model, StringUtils.formatData(jtqkModel));
			}else{
				model.setJtszdssxdm(xsjbxx.get("jtdzx"));
				model.setJtdz(xsjbxx.get("jtdz"));
			}
		} else {
			//��ͥ��Ա�б����
			request.setAttribute("blankList", service.getBlankList(CYSIZE));
		}

		//��ͥ��Ա��ʾ����
		jtcyList = bdpzService.getBdpz(JTCY);
		request.setAttribute("jtcyList", jtcyList);
		
		//������Ϣ��ʾ����
		qtxxList = bdpzService.getBdpz(QTXX);

		//��ͥ�����ʾ����
		mkxxList = bdpzService.getBdpz(JTQKDC);

		//Ӱ���ͥ����״���й���ϸ��Ϣ
		yxjtjjzkList = bdpzService.getBdpz(YXJTJJQK);
		
		//������Ϣ��ʾ���ã��Ƿ�������ã�
		List<HashMap<String,String>> fjxxList = bdpzService.getBdpz(FJXX);
		if(null != fjxxList && fjxxList.size()>0){			
			HashMap<String,String> fjxx = fjxxList.get(0);
			request.setAttribute("fjxx", fjxx);
		}
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("qtxxList", qtxxList);
		request.setAttribute("yxjtjjzkList", yxjtjjzkList);
		

		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(JTQKDC);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		
		String path = "xszz_jtqkdc_sq.do";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", model);
		request.setAttribute("jtrjysrsx", MessageUtil.getText("jtrjysrsx"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dcxxModify");
	}
	
	
	
	
	/**
	 * 
	 * @����:��ͥ������鵥����ʾ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-19 ����11:14:37
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
	public ActionForward dcxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		JtqkdcForm jtqkModel = service.getModel(model);
		
		if (jtqkModel != null){
			//��ѧ�ż��س�Ա�б� 
			List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
			request.setAttribute("cyList", cyList);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//����������е�������Ϣ
			qtxxList = bdpzService.getBdpz(QTXX);
			

			//��ͥ��Ա��ʾ����
			jtcyList = bdpzService.getBdpz(JTCY);

			//��ͥ�����ʾ����
			mkxxList = bdpzService.getBdpz(JTQKDC);

			//Ӱ���ͥ����״���й���ϸ��Ϣ
			yxjtjjzkList = bdpzService.getBdpz(YXJTJJQK);

					//ѧ��������Ϣ��ʾ����
			jbxxList = bdpzService.getJbxxpz(JTQKDC);
			request.setAttribute("jtcyList", jtcyList);
			request.setAttribute("mkxxList", mkxxList);
			request.setAttribute("qtxxList", qtxxList);
			request.setAttribute("yxjtjjzkList", yxjtjjzkList);
			
			request.setAttribute("jbxxList", jbxxList);
			request.setAttribute("mkxxForm", model);
			request.setAttribute("xxdm", Base.xxdm);
			request.setAttribute("xh", model.getXh());
			BeanUtils.copyProperties(model, StringUtils.formatData(jtqkModel));
			return mapping.findForward("dcxxView");
		} else {
			return dcxxModify(mapping, form, request, response);
		}
		
	}
	
	
	
	
	/**
	 * 
	 * @����:�����ͥ���������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-18 ����05:52:35
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
	@SystemLog(description="����ѧ������-��ͥ���-��д��ͥ���-���ӻ��޸ı���-XH:{xh},SFDQ:{sfdq},LSZN:{lszn}," +
			"SFGC:{sfgc},JTDZ:{jtdz},JTDH:{jtdh},JTYB:{jtyb},JTRJSR:{jtrjsr},JTNZSR:{jtnzsr},YHZZQK:{yhzzqk}," +
			"JTSYQK:{jtsyqk},CJNMQK:{cjnmqk},JTSZQK:{jtszqk},JTQZQK:{jtqzqk},JTQTQK:{jtqtqk},TFSJQK:{tfsjqk},JTHK:{jthk}")
	public ActionForward saveDcxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		boolean result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @����:��ͥ�����������ѯ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-19 ����11:13:59
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
	@SystemAuth(url = "xszz_jtqkdc_cx.do")
	public ActionForward dcxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xszz_jtqkdc_cx.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dcxxManage");
	}
	
	
	
	/**
	 * 
	 * @����:��ͥ�������ɾ��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-19 ����01:47:24
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
	@SystemLog(description="����ѧ������-��ͥ���-��д��ͥ���-ɾ��-VALUES:{values}")
	public ActionForward dcxxDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcService service = new JtqkdcService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
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
	 * 
	 * @����:��ͥ�����ʾ--�ṩ������ҳ��Ƕ��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-20 ����03:24:13
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
	public ActionForward jtqkInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		JtqkdcForm jtqkModel = service.getModel(model);
		
		//��ѧ�ż��س�Ա�б� 
		List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
		request.setAttribute("cyList", cyList);

		//��ͥ��Ա��ʾ����
		jtcyList = bdpzService.getBdpz(JTCY);

		//��ͥ�����ʾ����
		mkxxList = bdpzService.getBdpz(JTQKDC);


			
		request.setAttribute("jtcyList", jtcyList);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		
		if (jtqkModel != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(jtqkModel));
		}
		
		
		return mapping.findForward("jtqkInfo");
	}
	/**
	 * ��ͥ�����ʾ--�ṩ������ҳ��Ƕ�루�༶�������鿴����ѧ����Ϣ��ѧ������-�������϶�-�༶��������ѯ����
	 */
	@SystemAuth(url = url)
	public ActionForward jtqkInfoForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		JtqkdcForm jtqkModel = service.getModel(model);
		
		//��ѧ�ż��س�Ա�б� 
		List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
		request.setAttribute("cyList", cyList);
		
		//��ͥ��Ա��ʾ����
		jtcyList = bdpzService.getBdpz(JTCY);
		
		//��ͥ�����ʾ����
		mkxxList = bdpzService.getBdpz(JTQKDC);
		
		request.setAttribute("jtcyList", jtcyList);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		
		if (jtqkModel != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(jtqkModel));
		}
		
		
		return mapping.findForward("jtqkInfo");
	}
	
	
	
	
	
	
	/**
	 * 
	 * @����: ��ӡWord
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-16 ����02:22:14
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
	public ActionForward getJtqkdcWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcForm myForm = (JtqkdcForm) form;
		
		String xh = myForm.getXh();
		File wordFile = getJtqkdcWord(xh);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}


	
	//���ģ����������word�ļ�
	private File getJtqkdcWord(String xh)
			throws Exception {
		String xxdm = Base.xxdm;
		JtqkdcService service = new JtqkdcService();
		Map<String,Object> data = new HashMap<String,Object>();
		//ѧ����Ƭ
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		if (!StringUtils.isNull(xh)){
			//��ͥ���
			JtqkdcForm model = service.getModel(xh);
			//��ѧ�ż��س�Ա�б� 
			List<HashMap<String,String>> cyList = service.getJtcyList(model.getXh());
			
			List<HashMap<String, String>> cyList4line = service.getJtcyList(xh,4);
			List<HashMap<String, String>> cyList5line = service.getJtcyList(xh,5);
			data.put("cyList4line", cyList4line);
			data.put("cyList5line", cyList5line);

			//������Ϣ
			//XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//if(xxdm.equals("13871")||xxdm.equals("10279")) {
			if(StringUtils.isNotNull(xsjbxx.get("csrq"))){
				data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month).replaceAll("��", ".").replaceAll("��", ""));// ��������
			}
			//}
			data.putAll(xsjbxx);
			data.put("xxmc", Base.xxmc);
			data.put("jtqk", model);
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (CYSIZE - cyList.size()) < 0 ? 0 : (CYSIZE - cyList.size());
			data.put("blankList", service.getBlankList(blankSize));
			File wordFile=null;
			try{
				ResourceUtils.getFile("classpath://templates//xszz//jtqkdc_"+Base.xxdm+".xml");
				wordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//xszz","jtqkdc_"+xxdm+".xml",xh+"-"+xsjbxx.get("xm"));
			}catch (Exception e) {
				wordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//xszz","jtqkdc.xml",xh+"-"+xsjbxx.get("xm"));
			}
			return wordFile;
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: ����ZIP
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-21 ����05:27:29
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
	public ActionForward getJtqkdcZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getJtqkdcWord(values[i]);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	


	/**
	 * 
	 * @����: ����
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-11-21 ����03:49:27
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtqkdcForm model = (JtqkdcForm) form;
		JtqkdcService service = new JtqkdcService();
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
		
		
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
