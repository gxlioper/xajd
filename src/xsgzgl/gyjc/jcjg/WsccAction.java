/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018��5��28�� ����3:45:47 
 */  
package xsgzgl.gyjc.jcjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018��5��28�� ����3:45:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsccAction extends SuperAction<WsccForm, WsccService> {
	
	private static final String url = "xg_gyjc_wscc.do";
	private WsccService service = new WsccService();
	
	public ActionForward wsccList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WsccForm model = (WsccForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getList(model,user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		//���û�����û�ҳ��
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("xydm",user.getUserDep());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}


	public ActionForward ckWscc(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response){
		WsccForm model = (WsccForm) form;
		//���һ�����Ϣ
		HashMap<String, String> qsxx = service.getQsjbxx(model.getLddm(),model.getQsh());
		request.setAttribute("qsxx", qsxx);
		//�����Ϣ
		HashMap<String, String> jcxx = service.getJcxx(model.getCcid(),model.getLddm(),model.getQsh());
		request.setAttribute("jcxx", jcxx);
		//������������
		List<HashMap<String,String>> ztpjList = service.getZtwspj(model.getCcid(),model.getLddm(),model.getQsh());
		request.setAttribute("ztpjList", ztpjList);
		//������������
		List<HashMap<String,String>> grwspjList =service.getGrwspj(model.getCcid(),model.getLddm(),model.getQsh());
		List<GrpjModel> grpjModelList = service.getGrpjModelList(grwspjList);

		request.setAttribute("grpjModelList", grpjModelList);
		return mapping.findForward("wsccck");
	}


	public ActionForward update(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response){
		WsccForm model = (WsccForm) form;
		//���һ�����Ϣ
		HashMap<String, String> qsxx = service.getQsjbxx(model.getLddm(),model.getQsh());
		request.setAttribute("qsxx", qsxx);
		//�����Ϣ
		HashMap<String, String> jcxx = service.getJcxx(model.getCcid(),model.getLddm(),model.getQsh());
		request.setAttribute("jcxx", jcxx);

		//������������
		List<HashMap<String,String>> ztpjList = service.getZtwspj(model.getCcid(),model.getLddm(),model.getQsh());
		request.setAttribute("ztpjList", ztpjList);
		request.setAttribute("ztpjs",ztpjList.size());

		List<HashMap<String,String>> optionsList=service.getOptions();
		request.setAttribute("optionsList",optionsList);

		//������������
		List<HashMap<String,String>> grwspjList =service.getGrwspj(model.getCcid(),model.getLddm(),model.getQsh());
		List<GrpjModel> grpjModelList = service.getGrpjModelList(grwspjList);
		request.setAttribute("grpjModelList",grpjModelList);
		StringBuilder cwhs = new StringBuilder("");
		StringBuilder grpjs = new StringBuilder("");
		for (int i = 0; i < grpjModelList.size(); i++) {
			cwhs.append(grpjModelList.get(i).getCwh());
			grpjs.append(grpjModelList.get(i).getPjList().size());
			if(i!=grpjModelList.size()-1){
				cwhs.append(",");
				grpjs.append(",");
			}

		}
		request.setAttribute("cwhs",cwhs);
		request.setAttribute("grpjs",grpjs);
		List<HashMap<String,String>> GroptionsList =service.getGrOptions();
		request.setAttribute("GroptionsList",GroptionsList);

		return mapping.findForward("updatepage");
	}

	public ActionForward updateWscc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		WsccForm model = (WsccForm) form;
		boolean rs = false;
		boolean jg = service.delZtpj(model);//ɾ��
		if(model.getZtpjs()!=null){

			//		//������������
			rs = service.insertZtpj(model);
		}
		rs = service.updatePjdj(model);
//		ɾ����������
		if(model.getCwhs()!=null)
		{
			jg =service.delGrpj(model);
			if(model.getGrpjs()!=null){
				rs = service.insertGrpj(model);
			}
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}



	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WsccForm model = (WsccForm) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getDCList(model,user);

		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}


}
