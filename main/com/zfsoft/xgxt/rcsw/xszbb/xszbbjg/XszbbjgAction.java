/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:39:45 
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbbjg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.zfsoft.xgxt.rcsw.xszbb.xszbbsh.XszbbshService;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbsq.XszbbsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-18 ����04:05:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbjgAction extends SuperAction {
	//�����ճ�������ѧ��֤���쳣�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWXSZBB = "rcswxszbb";
	private static List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "rcsw_xszbb_bbjg.do";
	
	/**
	 * ѧ��֤����:"001"
	 */
	private static final String XSZDM = "001";

	/**
	 * 
	 * ��ѯѧ��֤��������
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-8 ����11:42:51
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
	@SystemAuth(url = url)
	public ActionForward xszbbjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ��ȡѧ��֤����������
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_xszbb_bbjg.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszbbjgManage");
	}

	/**
	 * 
	 * ����ѧ��֤������
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-8 ����11:42:40
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
	@SystemLog(description="�����ճ�����-֤������-֤��������-����XH:{xh},XSZBBLXDM:{xszbblxdm},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward addXszbbsqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			// Ψһ���жϣ�ѧ�ţ�
		   //boolean isExist=service.isExistByXszbbsqjg(model,SAVE);
			boolean isExist= false;
			if (!isExist) {
				super.resetToken(request);
				//��ѧ��֤����ղ����Żݿ���Ϣ
        		if(!XSZDM.equalsIgnoreCase(model.getXszbblxdm())){
        			model.setSfbbhcyhk("");
        		}
				// ���ѧ��֤������
				boolean result = service.saveXszbbsqjg(model);
				if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//�ൺ�Ƶ���Ի�
					XszbbshService shService = new XszbbshService();
					shService.hcccqjUpdate(model);
				}
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {

				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSZBB_XSZBBJG_REPEAT));
				return null;
			}
		}

		String path = "rcsw_xszbb_bbjggl.do?method=addXszbbsqjg";
		request.setAttribute("path", path);

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		//��������ά������
		XszbbsqService xszbbsqService = new XszbbsqService();
		request.setAttribute("bblxwhList", xszbbsqService.getBblxwhList());
		//��ǰ������ʱ��
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setSfbbhcyhk("y");
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addXszbbjg");
	}

	/**
	 * 
	 * @����:�޸�ѧ��֤������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����10:05:52
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
	@SystemLog(description="�����ճ�����-֤������-֤��������-�޸�BBJGID:{bbjgid},XSZBBLXDM:{xszbblxdm},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward updateXszbbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
				//��ѧ��֤����ղ����Żݿ���Ϣ
	    		if(!XSZDM.equalsIgnoreCase(model.getXszbblxdm())){
	    			model.setSfbbhcyhk("");
	    			if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//�ൺ�Ƶ����ְҵ����ѧԺ���Ի�
	    				model.setCcqdz("");
	    				model.setCczdz("");
	    			}
	    		}
	    		else{
	    			if((Base.xxdm.equals("13011") || Base.xxdm.equals("10695")) && "n".equalsIgnoreCase(model.getSfbbhcyhk())){//�ൺ�Ƶ����ְҵ����ѧԺ���Ի�
	    				model.setCcqdz("");
	    				model.setCczdz("");
	    			}
	    		}
				// �޸�ѧ��֤������
				boolean result = service.updateXszbbsqjg(model);
				if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//�ൺ�Ƶ���Ի�
					XszbbshService shService = new XszbbshService();
					shService.hcccqjUpdate(model);
				}
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			
		}

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		//��������ά������
		XszbbsqService xszbbsqService = new XszbbsqService();
		request.setAttribute("bblxwhList", xszbbsqService.getBblxwhList());
		XszbbjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateXszbbjg");
	}


	
	/**
	 * 
	 * @����:(ɾ��ѧ��֤������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����10:06:16
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
	@SystemLog(description="�����ճ�����-֤������-֤��������-ɾ��VALUES:{values}")
	public ActionForward delXszbbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbjgService service = new XszbbjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteXszbbjg(values.split(","));
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
	 * @����:(�鿴����ѧ��֤������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����10:52:36
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
	public ActionForward viewOneXszbbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//��ѯ����ѧ��֤������
		request.setAttribute("rs", StringUtils.formatData(service.viewOneXszbbjgList(model.getBbjgid())));

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewXszbbjg");
	}
	
	/**
	 * 
	 * @����:(�Զ��嵼������)
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
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		
		

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
	 * @����: ֤����ȡ 
	 * @���ߣ� cq [���ţ�785]
	 * @���ڣ�2014-3-5 ����11:58:39
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
	@SystemLog(description="�����ճ�����-֤������-֤��������-֤����ȡ BBJGID:{bbjgid}")
	public ActionForward lingqXszbbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		User user = getUser(request);
		
		
		if("save".equals(model.getType())){
			String bbjgid = request.getParameter("bbjgid"); //������id
			String bbjgids[] = bbjgid.split(",");
			String sflq = request.getParameter("sflq");
			
			boolean result= false;
			
			for(int i=0; i<bbjgids.length; i++){
				model.setBbjgid(bbjgids[i]);
				model.setSflq(sflq);
				
				//��ȡΪ�����ʱ��ͷ�����Ա
				if("0".equalsIgnoreCase(sflq)){
					model.setLqsj("");
					model.setFfyh("");
				}else{
					model.setLqsj(request.getParameter("lqsj"));
					model.setBz(request.getParameter("bz"));
					model.setFfyh(user.getUserName());
				}
				
				result = service.runUpdate(model);
			}
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		XszbbjgForm myForm  = new XszbbjgForm();
		myForm.setSflq("1");

		// ȡ��������¼��model
		String ids =  request.getParameter("ids");
		if(ids !=null && !"".equals(ids)){
			model.setBbjgid(ids);
			myForm = service.getModel(model);
		}
		request.setAttribute("model", StringUtils.formatData(myForm));
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("lingqXszbbjg");
	}
	
	/**
	 * 
	 * @����:TODO(��У֤�����)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-26 ����11:01:37
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
	public ActionForward getZxzmZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbjgService service = new XszbbjgService();
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				XszbbjgForm temp=new XszbbjgForm();
				temp.setBbjgid(values[i]);
				XszbbjgForm myForm = service.getModel(temp);
				File file = getWord(myForm.getXh());
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
	 * @���ߣ�lgx
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
	public ActionForward getZxzmWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xh = request.getParameter("xh");
		File wordFile = getWord(xh);
		FileUtil.outputWord(response, wordFile);
		return null;
		
	}

	// ���ģ����������word�ļ�
	private File getWord(String xh) throws Exception {
  
		Map<String, Object> data = new HashMap<String, Object>();
		if (!StringUtils.isNull(xh)) {
			// ������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.putAll(xsjbxx);
			data.put("dqrq", new SimpleDateFormat("yyyy��MM��dd��").format(new Date()));
			File wordFile = FreeMarkerUtil.getWordFile(data,
					"classpath://templates//rcsw", "zxzm_13431.xml", xh + "-"
							+ xsjbxx.get("xm"));
			return wordFile;
		}
		return null;
		
	}
}
