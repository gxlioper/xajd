package xsgzgl.gygl.wsjc.jcrcgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.wsjc.fslr.KflrService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class GyglJcrcglAjax extends BasicAction {
	
	/**
	 * ��ѯʦ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		service.initJcrcglManage(rForm, request);
		myForm.getSearchModel().setPath("gyglnew_wsjc_jcrcgl.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// =================== end ===================
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getJcrcglList(myForm);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}

	
	/**
	 *  ����ճ̹��� �Զ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public ActionForward jcrcglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {			
		GyglJcrcglService service = new GyglJcrcglService();
		GyglJcrcglForm model = (GyglJcrcglForm) form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		
		List<HashMap<String,String>> resultList =   service.getJcrcglExportList(model,user);				
		
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
	 * ������ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "���ʹ�Ԣ����-�������-����ճ̹���-����PK:{guid},XN:{xn},XQ:{xq},MC:{mc},JCLX:{jclx},KSSJ:{kssj},JSSJ:{jssj}")
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		// ��Ϣ��Ϣ
		String message = "";
		// �������ݷ���
		boolean flag = false;
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		//��������ְҵ����ѧԺ
		if("12688".equals(Base.xxdm)){
			//���������ּ���ճ�ʱ�����Ի�������������ճ̣��Ը��Ի��ֶ���Ϊ���֣�һ��ΪУ������ճ̣�һ��ΪԺ������ճ�
			service.initSave(model, request);
			HashMap<String, String> map = model.getValueMap();
			map.put("pfjb", "xj");
			model.setValueMap(map);
			flag = service.saveInfo(model);
			
			service.initSave(model, request);
			map.put("pfjb", "yj");
			model.setValueMap(map);
			flag = service.saveInfo(model);
		}else{
			service.initSave(model, request);
			flag = service.saveInfo(model);
		}
		
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * �޸���ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		// ��Ϣ��Ϣ
		String message = "";
		// �������ݷ���
		boolean flag = false;
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		service.initModi(model, request);
		flag = service.updateInfo(model);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * �ճ����Ʋ����ظ��ļ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkMc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		boolean flag = false;
		String Mc = service.unicode2Gbk(request.getParameter("mc"));
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		service.initModi(model, request);
		flag = service.findInfo(model, Mc);
		if (!"".equals(Mc)) {
			if (flag == true) {
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print("�����Ѵ��ڣ�");
			} else {
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print("���ƿ��ã�");
			}
		}
		return null;
	}

	/**
	 * ��ֹʱ�䲻���ص��ļ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkQzsj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		String mess = "";
		String Xn = service.unicode2Gbk(request.getParameter("xn"));
		String Xq = service.unicode2Gbk(request.getParameter("xq"));
		String Kssj = service.unicode2Gbk(request.getParameter("kssj"));
		String Jssj = service.unicode2Gbk(request.getParameter("jssj"));
		String jclx = service.unicode2Gbk(request.getParameter("jclx"));
		
		String pkValue = service.unicode2Gbk(request.getParameter("pkValue"));
		model.setPkValue(new String[]{pkValue});
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		service.initModi(model, request);
		mess = service.findQzsj(model, Kssj, Jssj, Xn, Xq, jclx);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(mess);
		return null;
	}

	/**
	 * ����ɾ��ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "���ʹ�Ԣ����-�������-����ճ̹���-ɾ��PK:{guid}")
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		model.setPk(new String[] { "guid" });
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		String[] pkValue = pkStr.split("!!array!!");
		// ��Ϣ��Ϣ
		String message = "";
		// �������ݷ���
		boolean flag = false;
		// --------------����------------
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		// ����
		model.setPkValue(pkValue);
		// ����ɾ��
		if(Base.xxdm.equals("33333")){
			KflrService kflrService = TransactionControlProxy.newProxy(new KflrService());
			flag = kflrService.plscJcrc(pkValue);
		}else{
			flag = service.batchDelete(model); 
		}
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}