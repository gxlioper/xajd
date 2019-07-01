/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-8 ����06:51:54 
 */
package com.zfsoft.xgxt.axcs.wpjg;

import java.io.File;
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
import com.zfsoft.xgxt.axcs.wpsz.WpszService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-8 ����06:51:54
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WpjgAction extends SuperAction {
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String AXCSXSZBB = "axcs";
	private static final String url = "axcs_axcswpjg.do";
	WpjgService service = new WpjgService();

	@SystemAuth(url = url)
	public ActionForward wpjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpjgForm model = (WpjgForm) form;
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "axcs_axcswpjg.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wpjgList");
	}
	
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����12:01:58
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
	public ActionForward wpjgZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpjgForm model = (WpjgForm) form;
		if (!StringUtil.isNull(model.getXh())) {
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ��+ѧ��+��Ŀ���룩
			boolean isExist=service.isExistBysqjg(model);
			if (!isExist) {
				//�������������ԴΪ��0��
				model.setSjly("0");
				boolean result = service.saveSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				WpszService wpszService = new WpszService();
				HashMap<String,String> wpxxMap = wpszService.getWpxxByXmdm(model.getXmdm());
				String message=MessageUtil.getText(MessageKey.AXCS_WPFZ_REPEAT, wpxxMap.get("xmmc"));
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		String path = "axcswpjg.do?method=wpjgZj";
		request.setAttribute("path", path);
		//ѧ�� ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		
		//��Ŀ����
		WpszService wpszService = new WpszService();
		List<HashMap<String, String>> wpmcList = wpszService.getWpmcList(Base.currXn);
		request.setAttribute("wpmcList", wpmcList);
		
		
		
		//Ĭ�ϲ�����ѧ�ꡢʱ��
		model.setXn(Base.currXn);
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return mapping.findForward("wpjgZj");
	}

	/**
	 * 
	 * @����:�޸Ľ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����03:21:16
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
	public ActionForward wpjgXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpjgForm model = (WpjgForm) form;
		WpjgForm updateForm = service.getModel(model);
		
		
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�ţ�
			boolean isExist=service.isExistBysqjg(model);
			if (!isExist) {
				// �޸Ľ��
				boolean result = service.updateSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				WpszService wpszService = new WpszService();
				HashMap<String,String> wpxxMap = wpszService.getWpxxByXmdm(model.getXmdm());
				String message=MessageUtil.getText(MessageKey.AXCS_WPFZ_REPEAT, wpxxMap.get("xmmc"));
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}

		//ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		
		//��Ŀ����
		WpszService wpszService = new WpszService();
		List<HashMap<String, String>> wpmcList = wpszService.getWpmcList(updateForm.getXn());
		request.setAttribute("wpmcList", wpmcList);
		request.setAttribute("rs", new WpszService().getWpxxByXmdm(updateForm.getXmdm()));
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("wpjgXg");
	}


	
	/**
	 * 
	 * @����:ɾ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����03:25:20
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
	public ActionForward delWpjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpjgService service = new WpjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteSqjg(values.split(","));
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
	 * @����:�����鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����03:28:49
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
	public ActionForward wpjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WpjgForm model = (WpjgForm) form;
		WpjgService service = new WpjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		
		//��ѯ�������
		request.setAttribute("rs", StringUtils.formatData(service.viewOneWpjgList(model.getJgid())));
		
		return mapping.findForward("wpjgCk");
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����03:29:51
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
		WpjgForm model = (WpjgForm) form;
		WpjgService service = new WpjgService();

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
	 * ���������Ʒ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWpmcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xn = request.getParameter("xn");
		WpszService wpszService = new WpszService();
		List<HashMap<String, String>> wpmcList = wpszService.getWpmcList(xn);
		String json = JSONArray.fromCollection(wpmcList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	

}
