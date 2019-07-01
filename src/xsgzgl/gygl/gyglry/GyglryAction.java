package xsgzgl.gygl.gyglry;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class GyglryAction extends BasicAction{
	
	private GyglryService service=new GyglryService();
	public static final String KFILE = "û���ļ���";
	public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	/**
	 * ��Ԣ������Ա
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-������Ա-��Ԣ������Ա-{doType}ά��QSH:{fp_qsh}")
	public ActionForward gyglryManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_gyglry_gyglry.do");
		GyglryForm model=(GyglryForm)form;
		User user = getUser(request);
		String doType=request.getParameter("doType");
		if("qxfp".equals(doType)){//ȡ������
			String message=service.gyglryQxfp(model);
			request.setAttribute("message", message);
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
		}
		
		List<String[]> rsList=service.getGyglryList(model,request,user);
		
		request.setAttribute("rsList", rsList);
		request.setAttribute("topTr", service.getTopTr("gyglry"));
		request.setAttribute("searchTj", model.getSearchModel());	//�߼���ѯ����
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("tableName", "xg_view_gygl_new_gyglryb");
		request.setAttribute("realTable", "xg_gygl_new_gyglryb");
		
		return mapping.findForward("gyglryManage");
	}
	
	public ActionForward gyglryQxfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oldxh=request.getParameter("oldxh");
		request.setAttribute("oldxh", oldxh);
		
		
		return mapping.findForward("gyglryQxfp");
	}
	
	/**
	 *���ҳ�ά���Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward qszwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		GyglryForm model=(GyglryForm)form;		
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		request.setAttribute("path", "gyglnew_gyglry_gyglry.do");
		List<HashMap<String,String>> resultList = service.getGyglryExportList(model,request,user);
		
		
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
	 * ��Ԣ������Ա����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-������Ա-��Ԣ������Ա-{doType}ά��QSH:{fp_qsh}")
	public ActionForward gyglryFp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String rzksrq=(String)session.getAttribute("temp_gygl_gyglryfp_rzksrq");//��סʱ��
		GyglryForm model=(GyglryForm)form;
		if(Base.isNull(model.getRzksrq())){
			model.setRzksrq(rzksrq);
		}else{
			session.setAttribute("temp_gygl_gyglryfp_rzksrq", model.getRzksrq());
		}
		if(StringUtils.isNull(request.getParameter("query"))){  //������¥��ʱ����ѯ���Ϊ��ʱ��������Ϊ����¥������ݡ�
			if(Base.isNull(model.getCh())){model.setCh(model.getFp_ch());}
		}
		if(Base.isNull(model.getQsh())){model.setQsh(model.getFp_qsh());}
		String doType=Base.chgNull(request.getParameter("doType"), "",0);
		if("fp".equals(doType)){//����
			String message=service.gyglryFp(model);
			request.setAttribute("message", message);
		}else{
			boolean flag = service.fpFullFlag(model);
			request.setAttribute("gyglryfpczfs",flag);
		}	
			List<String[]> rs=service.getRzxsxxList(model);
			request.setAttribute("rs", rs);
			request.setAttribute("pageSize", rs.size());
			request.setAttribute("topTr", service.getTopTr("cwwh"));
		
			HashMap<String,String> rsp=new HashMap<String, String>();
			rsp.put("fp_ch", model.getFp_ch());
			rsp.put("fp_qsh", model.getFp_qsh());
			request.setAttribute("rsp", rsp);
			
			request.setAttribute("chList", service.getChList(model));
			request.setAttribute("qshList", service.getQshList(model));
		
		return mapping.findForward("gyglryFp"); 
	}
	
	/**
	 * ͬ���ɲ���Ϣ��˼������
	 * jQuery - ajax ����
	 */
	@SystemLog(description="���ʹ�Ԣ����-������Ա-��Ԣ������Ա-ͬ��")
	public ActionForward gyglryTbgbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ͬ������
		boolean result = service.tbgbInfo();		
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(result); 
		return null;
	}
	
	/**
	 * 
	 * @����: �������ѧԺ��Ԣ������Ի����룬����ѧУҪ�󣬷���Ҫ������ͨ�ù���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-19 ����04:04:54
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
	public ActionForward gyglxDr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return mapping.findForward("gyglydr");
	}
	
	/**
	 * 
	 * @����: ���ص���ģ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-19 ����04:18:12
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
	public ActionForward downloadMb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		File file = service.downloadMb();
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("xg_gyglydr.xls","utf-8")); 
		FileUtil.outputFile(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����: ���ش���ģ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-19 ����04:19:52
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
	public ActionForward downloadError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String fileName = request.getParameter("filename");
		File file = new File(TEMP_DIR +"/"+fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8")); 
		FileUtil.outputFile(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-19 ����04:22:25
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
	public ActionForward dr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GyglryForm model=(GyglryForm)form;
		FormFile file = model.getFile();
		if(file != null){
			try {
				HashMap<String,String> resultMap = service.getAndSaveDrData(file.getInputStream());
				JSONObject jsonMap = JSONObject.fromObject(resultMap);
				response.getWriter().print(jsonMap);
			}catch (SystemException e){
				response.getWriter().print(getJsonMessage(e.getMessage()));
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_OPERATE_FAIL));
				e.printStackTrace();
			}
			
		}else{
			response.getWriter().print(getJsonMessage(KFILE));
		}
		return null;
	}
	
}
