/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-28 ����09:24:38 
 */  
package xgxt.gygl.sspy.pyjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.gygl.sspy.pysq.SspysqService;
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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ�������ģ��
 * @�๦������: �������Ž��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-28 ����09:24:38 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SspyjgAction extends SuperAction<SspyjgForm,SspyjgService>{
	private static final String url = "sspy_jg.do";
	private SspyjgService service = new SspyjgService();
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����10:21:50
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
	@SystemLog(description = "���ʹ�Ԣ����-�������Ž��")
	public ActionForward getSspyjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*����path*/
		String path = "sspy_jg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sspyjgList");
	}
	
	/**
	 * @����: ����Json����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����10:26:54
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
	@SystemLog(description = "���ʹ�Ԣ����-��ѯ�������Ž������")
	public ActionForward seachForSspyjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		SspyjgForm model = (SspyjgForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//��ѯ
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����11:29:07
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
	@SystemLog(description = "���ʹ�Ԣ����-�������Ž��-����")
	public ActionForward sspyjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*¥����Ϣ*/
		List<HashMap<String,String>> ldxxList = service.getLdxxList();
		request.setAttribute("ldxxList", ldxxList);
		
		/*������Ŀ�б�*/
		SspysqService sspysqService = new SspysqService();
		List<HashMap<String,String>> pyxmList = sspysqService.getPyxmList();
		request.setAttribute("pyxmList", pyxmList);
		
		//ѧ�� ѧ��
		List<HashMap<String,String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		List<HashMap<String,String>> xqList = Base.getXqList();
		request.setAttribute("xqList", xqList);
		
		return mapping.findForward("sspyjgAdd");
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����03:26:44
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
	@SystemLog(description="������������-�������Ž��-����:ѧ��:{lddm},��Ŀ����:{qsh},����ʱ��:{xn},ѧ��:{xq},������Ŀ:{pyxmdm}")
	public ActionForward sspyjgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		SspyjgForm model = (SspyjgForm)form;
		
		User user = getUser(request);
		boolean rs = true;
		try{
			rs = service.saveFormSspyjg(model,user);
		}catch(SystemException e){
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: �޸�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����04:53:04
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
	@SystemLog(description = "������������-�����޸İ�ť")
	public ActionForward sspyjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		SspyjgForm sspyjgForm = (SspyjgForm)form;
		SspyjgForm model = service.getModel(sspyjgForm);
		if(model != null){
			BeanUtils.copyProperties(sspyjgForm, StringUtils.formatData(model));
			/*¥����Ϣ*/
			List<HashMap<String,String>> ldxxList = service.getLdxxList();
			request.setAttribute("ldxxList", ldxxList);
			
			/*������Ϣ*/
			HashMap<String,String> qsxxMap = service.getQsxxForParam(model.getLddm(),model.getCh(),model.getQsh());
			request.setAttribute("qsxxMap", qsxxMap);
			
			/*������Ŀ�б�*/
			SspysqService sspysqService = new SspysqService();
			List<HashMap<String,String>> pyxmList = sspysqService.getPyxmList();
			request.setAttribute("pyxmList", pyxmList);
			
			//ѧ�� ѧ��
			List<HashMap<String,String>> xnList = Base.getXnndList();
			request.setAttribute("xnList", xnList);
			List<HashMap<String,String>> xqList = Base.getXqList();
			request.setAttribute("xqList", xqList);
		}
		return mapping.findForward("sspyjgUpdate");
	}
	
	/**
	 * @����: ɾ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����06:01:06
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
	@SystemLog(description="������������-�������Ž��-ɾ��-VALUES:{values}")
	public ActionForward sspyjgDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");

		if(!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����: �������Ž������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-2 ����07:29:45
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
	@SystemLog(description="���ʹ�Ԣ����-�������Ž��-����")
	public ActionForward sspyjgExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		SspyjgForm model = (SspyjgForm)form;
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		/*��ѯ�����м�¼������ҳ*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);
		/*�������ܴ���*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*��ǰ����Ա*/
		exportModel.setZgh(user.getUserName());
		/*��������*/
		exportModel.setDataList(resultList);
		/*���õ�ǰ�������ܱ��*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*���ɵ����ļ�*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����: �鿴
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-3 ����08:49:26
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
	@SystemLog(description = "�������۽��-�鿴")
	public ActionForward sspyjgView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		SspyjgForm sspyjgForm = (SspyjgForm)form;
		SspyjgForm model = service.getModel(sspyjgForm);
		
		/*����ID��ȡ�����Ϣ*/
		HashMap<String,String> getInfoByGuid = service.getInfoByGuid(model.getGuid());
		request.setAttribute("rs", getInfoByGuid);
		return mapping.findForward("sspyjgView");
	}
}
