/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyqksh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh.HcccqjshForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh.HcccqjshService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class GfjyshAction extends SuperAction{
	private static final String RCSWRCXW = "rcswqjgl_qjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String url = "gfjy_gfjyqk_sh.do";
	private GfjyshService service = new GfjyshService();
	
	
	/**
	 * @description	�� ����б�
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-4 ����04:25:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward gfjyShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GfjyshForm model = (GfjyshForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			
			//��ѯ��ȡѧ��֤�����������
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gfjySh");
		
	}
	
	
	/**
	 * @description	�� �鿴
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-5 ����10:08:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GfjyshForm model = (GfjyshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		HashMap<String,String> gfqkMap = service.getGfjyqkInfo(model);
		request.setAttribute("rs", StringUtils.formatData(gfqkMap));
		request.setAttribute("gfqkfl", gfqkMap.get("gfqkfl"));
		request.setAttribute("filepath", gfqkMap.get("filepath"));
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("gfqkck");
		
	}
	
	
	/**
	 * @description	�� �������
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-5 ����10:51:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward gfqkDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GfjyshForm model = (GfjyshForm) form;
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);

			HashMap<String,String> gfqkMap = service.getGfjyqkInfo(model);
			request.setAttribute("rs", StringUtils.formatData(gfqkMap));
			request.setAttribute("gfqkfl", gfqkMap.get("gfqkfl"));
			request.setAttribute("filepath", gfqkMap.get("filepath"));
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			//���浥�����
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		model=service.getModel(model);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("gfqkDgsh");
		
	}
	/**
	 * @description	�� �������
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-5 ����02:47:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GfjyshForm model = (GfjyshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		//������ˣ����һ����
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	/**
	 * @description	�� �������
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-5 ����10:51:11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward gfqkPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GfjyshForm model = (GfjyshForm) form;
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("gfqkPlsh");
	}
	
	
	/**
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-5 ����03:47:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GfjyshForm model = (GfjyshForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		model.setShzt(shlx);
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
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
