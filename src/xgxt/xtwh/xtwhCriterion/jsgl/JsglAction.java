package xgxt.xtwh.xtwhCriterion.jsgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.xtwhCriterion.QxwhForm;
import xgxt.xtwh.xtwhCriterion.RequestModel;
import xgxt.xtwh.xtwhCriterion.RsModel;
import xgxt.xtwh.xtwhCriterion.entities.JsEntity;
import xgxt.xtwh.xtwhCriterion.yhgl.GnmkModel;

/**
 * @author luning 
 * @describe ��Ϊϵͳά���������ԣ�û�м̳�ͨ�õ�baseAction
 */
public class JsglAction extends DispatchAction{
	
/**
 * @author luning
 * @describe ��ɫά��
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward jswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JsglService jsglservice = new JsglService();
		//��request�ķ�װ
		QxwhForm myForm = (QxwhForm) form;
		JsglRequestModel model = new JsglRequestModel();
		JsglQueryModel queryModel = new JsglQueryModel();
		BeanUtils.copyProperties(queryModel, myForm);
		model.setJsglQueryModel(queryModel);
		
		model = jsglservice.setJsModel(model);
		
		//��ȡ��ѯ���
		RsModel rsModel = jsglservice.getJsList(model);
		
		//�������������
		setRequset(model,rsModel,request);
		
		//ת��·��
		return mapping.findForward("jswhManage");
	}
		
/**
 * @author luning
 * @describe ������ɫά������
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward jswhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JsglService jsglservice = new JsglService();
		//��request�ķ�װ
		QxwhForm myForm = (QxwhForm) form;
		
		JsglRequestModel model = new JsglRequestModel();
		
		BeanUtils.copyProperties(model, myForm);
		
		model = jsglservice.setJsModel(model);
		
		jsglservice.jswhUpdate(model,request);
		
		
		//================= ҳ����ʾ��Ϣ==================
		HashMap<String, String> rs = jsglservice.getJswh(model);
		// =================end ===================
		//�������������
		request.setAttribute("rs",rs);
		FormModleCommon.requestSetList(new String[]{"jslx","jsczfw"}, request);
		
		//ת��·��
		return mapping.findForward("jswhUpdate");
	}
	
/**
 * @author luning
 * @describe ������ɫɾ��
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward jswhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JsglService jsglservice = new JsglService();
		//��request�ķ�װ
		QxwhForm myForm = (QxwhForm) form;
		
		JsglRequestModel model = new JsglRequestModel();
		
		BeanUtils.copyProperties(model, myForm);
		
		model = jsglservice.setJsModel(model);
		
		Boolean yesNo = jsglservice.delJswh(model,request);
		
		jsglservice.setMessage(request, yesNo,"del");
		
		return jswh(mapping,form,request,response);
	}



/**
 * @author luning
 * @describe ��ɫά��ҳ�����ͨ�÷���
 * @param model
 * @param rsModel
 * @param request
 */
	private void setRequset(RequestModel model, RsModel rsModel, HttpServletRequest request) {
		request.setAttribute("tableName", model.getTableName());
		request.setAttribute("realTable", model.getRealTable());
		
		request.setAttribute("rs",rsModel.getRsList());
		List topTr = SearchUtils.getTopTr(model.getTableName(),model.getColList(), null);
		request.setAttribute("topTr",topTr);
		try {
			FormModleCommon.requestSetList(new String[]{"jslx","jsczfw","yesNo"}, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/**
 * @author luning
 * @describe ��ɫ��Ȩҳ��
 * @param model
 * @param rsModel
 * @param request
 */
	public ActionForward jssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��������
		String doType = request.getParameter("doType");
		JsglService jsglservice = new JsglService();
		
		String jsmc = request.getParameter("jsmc");
		
		QxwhForm myForm = (QxwhForm) form;
		request.setAttribute("rs", jsglservice.getEntitry(JsEntity.class, jsmc));
		
		// ������ӵ�Ȩ��
//		if("save".equalsIgnoreCase(doType)){
//			String[] btns = request.getParameterValues("btns");
//			String message = service.saveYhqx(user.getUserName(), jsmc, btns) ? "����ɹ���" : "����ʧ�ܣ�";
//			request.setAttribute("message", message);
//		}
		request.setAttribute("fpdxList", jsglservice.getFpdxList());
		request.setAttribute("gnmkList", jsglservice.getOneGnmkList());
		
		return mapping.findForward("jssqManage");
	}
	
	public ActionForward getJsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JsglService jsglservice = new JsglService();
		//��request�ķ�װ
		QxwhForm myForm = (QxwhForm) form;
		JsglRequestModel model = new JsglRequestModel();
		JsglQueryModel queryModel = new JsglQueryModel();
		BeanUtils.copyProperties(queryModel, myForm);
		//��ѯ���Ľ�ɫ�ڸ�ģ����������õĽ�ɫ
		queryModel.setSfqy("��");
		
		model.setJsglQueryModel(queryModel);
		
		model = jsglservice.setJsQueryModel(model);

		//��ȡ��ѯ���
		RsModel rsModel = jsglservice.getJsList(model);
		
		//�������������
		setRequset(model,rsModel,request);
		
		//ת��·��
		return mapping.findForward("roleInfo");
	}
	
	/**
	 * ��ɫ��Ȩҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");

		JsglService service = new JsglService();
		
		// �����ɫȨ��
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveJsqx(pkValue, request.getParameterValues("btns")) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		// ���й���ģ��
		List<GnmkModel> list = service.getAllGnmkList(pkValue);
		request.setAttribute("allGnmkList", list);
		
		// ��ɫ��Ϣ
		request.setAttribute("rs",service.getJsInfo(pkValue));
		// ����߶�
		int height = (list.size() * 29 + 36) < 800 ? 800 : (list.size() * 29 + 36); 
		request.setAttribute("height", height);
		
		return mapping.findForward("jssqManage");
	}
	
}
