/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:39:45 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg;

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
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh.YlbxshService;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsq.YlbxsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ҽ�Ʊ��ս������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-18 ����04:05:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxjgAction extends SuperAction {
	//�����ճ�������ѧ��֤���쳣�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String DXSYLBX = "dxsylbx";
	private static List<HashMap<String, String>> jbxxList = null;

	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(DXSYLBX);
		
	}
	
	private static final String url = "rcsw_dxsylbx_ylbxjg.do";

	/**
	 * 
	 * @����:TODO(��ѯ��ȡҽ�Ʊ��ս������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����02:01:56
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
	public ActionForward ylbxjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ��ȡҽ�Ʊ��ս������
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_dxsylbx_ylbxjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxjgManage");
		
		
	}

	/**
	 * 
	 * @����ҽ�Ʊ��ս������
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-8 ����11:42:40
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ��ս��-����XH:{xh},XN:{xn},XQ:{xq},CZQEBZ:{czqebz},CBZK:{cbzk},SQLY:{sqly}")
	public ActionForward addYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}
			// Ψһ���жϣ�ѧ�ţ�
		    boolean isExist=service.isExistByYlbxjg(model,SAVE);
		    model.setCbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			if (!isExist) {
				super.resetToken(request);
				// ���ѧ��֤������
				boolean result = service.saveYlbxjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_DXSYLBX_YLBXJGCZ));
				return null;
			}
		}
		String path = "rcsw_dxsylbx_ylbxjggl.do?method=addYlbxjg";
		request.setAttribute("path", path);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		//model.setXn(Base.currXn);
		//model.setXq(Base.currXq);
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		model.setXqmc(service.getCurrentXqmc(model));
		
		YlbxsqService ylbxsqService = new YlbxsqService();
		request.setAttribute("czqebzryList", ylbxsqService.getCzqebzryList());
		request.setAttribute("cbzkList", ylbxsqService.getCbzkList());
		this.saveToken(request);
		return mapping.findForward("addYlbxjg");
		
	}
	

	/**
	 * 
	 * @����:TODO(�޸�ҽ�Ʊ��ս��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����11:22:57
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ��ս��-�޸�YLJGID:{yljgid},XN:{xn},XQ:{xq},CZQEBZ:{czqebz},CBZK:{cbzk},SQLY:{sqly}")
	public ActionForward updateYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
				model.setCbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				// �޸�ѧ��֤������
				boolean result = service.updateYlbxjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			
		}
		String path = "rcsw_dxsylbx_ylbxjggl.do?method=updateYlbxjg";
		request.setAttribute("path", path);
		
		request.setAttribute("jbxxList", jbxxList);
		YlbxjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		model.setXqmc(service.getCurrentXqmc(model));
		
		YlbxsqService ylbxsqService = new YlbxsqService();
		request.setAttribute("czqebzryList", ylbxsqService.getCzqebzryList());
		request.setAttribute("cbzkList", ylbxsqService.getCbzkList());
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		
		 //ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("updateYlbxjg");
		
		
	}


	
	/**
	 * 
	 * @����:TODO(ɾ��ҽ�Ʊ��ս��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����01:43:33
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ��ս��-ɾ��VALUES:{values}")
	public ActionForward delYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgService service = new YlbxjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteYlbxjg(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	/**
	 * 
	 * @����:TODO(�鿴ҽ�Ʊ��ս��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����02:43:58
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
	public ActionForward viewYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//�鿴ҽ�Ʊ��ս��
		request.setAttribute("rs", StringUtils.formatData(service.viewOneYlbxjgList(model.getYljgid())));
		
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		YlbxshService ylbxshService = new YlbxshService();
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", ylbxshService.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", ylbxshService.getCbzkdmcsList(cbzkdms));
		}
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		
		return mapping.findForward("viewYlbxjg");
		
	}
	
	/**
	 * 
	 * @����:TODO(�鿴�α�״��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����04:27:08
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
	public ActionForward viewCbzk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xm = request.getParameter("xm");
		if("null".equals(xm)){
			xm = "";
		}
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		model = service.getModel(model);
		YlbxshService ylbxshService = new YlbxshService();
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", ylbxshService.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", ylbxshService.getCbzkdmcsList(cbzkdms));
		}
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		request.setAttribute("xm",xm);
		request.setAttribute("model",StringUtils.formatData(model));
		return mapping.findForward("viewCbzk");
		
	}
	
	/**
	 * 
	 * @����:TODO(�Զ��嵼������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:22:51
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
		
		YlbxjgForm model = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
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
	
	/**
	 * 
	 * @����:TODO(��ѧ��ҽ�Ʊ��մ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-16 ����11:01:37
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
	public ActionForward getDxsylbxZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getDxsylbxWord(values[i]);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
		
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
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getDxsylbxWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm myForm = (YlbxjgForm) form;
		//String xh = myForm.getXh();
		String yljgid = myForm.getYljgid();
		File wordFile = getDxsylbxWord(yljgid);
		FileUtil.outputWord(response, wordFile);
		return null;
		
	}

	// ���ģ����������word�ļ�
	private File getDxsylbxWord(String yljgid) throws Exception {
  
		YlbxjgService service = new YlbxjgService();
		Map<String, Object> data = new HashMap<String, Object>();
		YlbxjgForm model = new YlbxjgForm();
		model.setYljgid(yljgid);
		model = service.getModel(model);
		String xh = model.getXh();
		if (!StringUtils.isNull(xh)) {
			// ������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.put("xxmc", Base.xxmc);
			data.putAll(xsjbxx);
			
			YlbxsqService ylbxsqService = new YlbxsqService();
			if(StringUtils.isNull(model.getQtcbzkval())){
				data.put("qtcbzkval", "");
			}else{
				data.put("qtcbzkval", model.getQtcbzkval());
			}
			List<HashMap<String, String>> dataCzqebzryMap = ylbxsqService.getCzqebzryList();
			data.put("czqebzryList",fomatCzqebzryData(model.getCzqebzdm(), dataCzqebzryMap));
			List<HashMap<String, String>> dataCbzkMap = ylbxsqService.getCbzkList();
			data.put("cbzkList", fomatCbzkdmData(model.getCbzkdm(), dataCbzkMap));
			data.put("model",model);
			File wordFile = FreeMarkerUtil.getWordFile(data,
					"classpath://templates//rcsw", "dxsylbx.xml", xh + "-"
							+ xsjbxx.get("xm"));
			return wordFile;
		}
		return null;
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-16 ����09:32:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @param data
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	private List<HashMap<String, String>> fomatCzqebzryData(String str,
			List<HashMap<String, String>> data) {
		
		List<HashMap<String, String>> newData=new ArrayList<HashMap<String,String>>();
		if(str.length() >0){
			for(HashMap<String,String> hm:data){
					if (str.indexOf(hm.get("czqebzdm"))>=0) {
						hm.put("isExsit", "1");
					} else {
						hm.put("isExsit", "0");
					}
					newData.add(hm);
				}
		}else{
			for(HashMap<String,String> hm:data){
				hm.put("isExsit","0");
				newData.add(hm);
			}
		}
		return newData;
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-16 ����09:32:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @param data
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	private List<HashMap<String, String>> fomatCbzkdmData(String str,List<HashMap<String, String>> data) {
		
		List<HashMap<String, String>> newData=new ArrayList<HashMap<String,String>>();
		if(str.length() >0){
			for(HashMap<String,String> hm:data){
				if (str.indexOf(hm.get("cbzkdm"))>=0) {
					hm.put("isExsit", "1");
				} else {
					hm.put("isExsit", "0");
				}
			   newData.add(hm);
			}
		}else{
			for(HashMap<String,String> hm:data){
				hm.put("isExsit","0");
				newData.add(hm);
			}
		}
		return newData;
		
	}
	
}
