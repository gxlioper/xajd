package xgxt.pjpy.tyb.zhszcp.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.bdsz.BdszModel;
import xgxt.bdsz.BdszService;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZctjszService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyZctjszAction extends CommonAction {
	
	private static String ZHSZCP_DM_2J = "2";//2���۲����
	private static String ZHSZCP_DM_3J = "3";//3���۲����
	private static String ZHSZCP_DM_4J = "4";//4���۲����

	/**
	 * �ۺ����ʲ�����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zctjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZctjszActionForm szForm = (PjpyZctjszActionForm) form;
		PjpyZctjszService service = new PjpyZctjszService();
		String dm = request.getParameter("id");
		
		List<HashMap<String, String>> titleList = service.queryEjTitle(); 
		/* ��ȡ��������  Ĭ��Ϊȡ���ݲ�ѯ�����ĵ�һ������ */
		dm = StringUtils.isNotNull(dm) ? dm
				: (titleList.isEmpty() ? "" : titleList.get(0).get("en"));
		
		HashMap<String, String> rs = new HashMap<String, String>();
		if (StringUtils.isNotNull(dm)) {//��ȡ�������룬���Ƶ���Ϣ
			rs = service.queryZcejdmxx(dm);
			request.setAttribute("sjdmstr", service.query3jZcdmStr(dm));//ƴ�����������ַ�����Ϣ
		}
		
		/* �������ݲ��� */
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			PjpyZctjszModel model = new PjpyZctjszModel();
			PjpyTyService tjService = new PjpyTyService();
			BeanUtils.copyProperties(model, szForm);
			SaveForm saveForm = new SaveForm();
			String[] arrzd = {};// ��ȡ�������ݵ��ֶ��б�
			boolean result = false;
			
			//�۲���뼶��2��3��4��
			String type = request.getParameter("type");
			
			if (ZHSZCP_DM_2J.equalsIgnoreCase(type)) {// 2�����뱣��
				model.setJb(ZHSZCP_DM_2J);
				result = service.save2jZhszcpdmxx(model);
			} else if (ZHSZCP_DM_3J.equalsIgnoreCase(type)) {// 3�����뱣��
				arrzd = new String[] { "dm", "mc", "bl", "lb", "xzf", "fdm","dmjb" };
				PjpyZhszcpModel myModel = copyEjdmDataToForm(request, model,
						saveForm, arrzd);
				// ִ�в�����������
				result = tjService.savePjpy(saveForm, myModel);
			} else if (ZHSZCP_DM_4J.equalsIgnoreCase(type)) {// 4�����뱣�����
				//arrzd = new String[] { "dm", "mc", "bl", "lb", "xzf", "bm",
				//		"zd", "shjb", "sfplzj", "tj", "fdm","dmjb", "mrf", "zj", "zjz","sfwh" };
				arrzd = new String[] { "dm", "mc", "bl", "lb", "xzf", "bm",
						"zd", "shjb", "tj", "fdm","dmjb", "mrf","sfwh" };
				PjpyZhszcpModel myModel = copySjdmToForm(model, saveForm, arrzd);
				// ִ�в�����������
				result = tjService.savePjpy(saveForm, myModel);
				
				//�޸����������ı�������˼���
				if (result) {
					result = service.updateFdmbmAndShjb(myModel);
					//�޸Ķ��������������˼���
					if (result) {
						result = service.update2jDmwhxx();
					}
				}
			}
			appendOperResultMes(request, result);
		}

		request.setAttribute("tabName", dm);
		request.setAttribute("pages", titleList);
		request.setAttribute("rs", rs);
		return mapping.findForward("zctjsz");
	}

	/**
	 * ����3���������ݵ�FORM������
	 * @param model
	 * @param saveForm
	 * @param arrzd
	 * @return
	 */
	private PjpyZhszcpModel copySjdmToForm(PjpyZctjszModel model,
			SaveForm saveForm, String[] arrzd) {
		saveForm.setTableName("pjpy_zctjszb");
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(new String[]{});
		saveForm.setPk("fdm");
		saveForm.setPkValue(model.getSjfdm());
		PjpyZhszcpModel myModel = new PjpyZhszcpModel();
		myModel.setDm(model.getSjdm());
		myModel.setMc(model.getSjmc());
		myModel.setLb(model.getSjlb());
		myModel.setBl(model.getSjbl());
		myModel.setXzf(model.getSjxzf());
		myModel.setFdm(model.getSjfdm());
		myModel.setBm(model.getSjbm());
		myModel.setZd(model.getSjzd());
		myModel.setShjb(model.getSjshjb());
		myModel.setSfplzj(model.getSjsfplzj());
		myModel.setTj(model.getSjtj());
		myModel.setMrf(model.getSjmrf());
		myModel.setZj(model.getSjzj());
		myModel.setZjz(model.getSjzjz());
		myModel.setSfwh(model.getSjsfwh());
		
		//����Ĭ��Ҫ����Ĵ��뼶�� 4��
		if (myModel.getDm() != null) {
			String[] array = new String[myModel.getDm().length];
			for (int i = 0; i < array.length; i++) {
				array[i] = ZHSZCP_DM_4J;
			}
			myModel.setDmjb(array);
		}
		return myModel;
	}

	/**
	 * ����2���������ݵ�FORM������
	 * @param request
	 * @param model
	 * @param saveForm
	 * @param arrzd
	 * @return
	 */
	private PjpyZhszcpModel copyEjdmDataToForm(HttpServletRequest request,
			PjpyZctjszModel model, SaveForm saveForm, String[] arrzd) {
		saveForm.setTableName("pjpy_zctjszb");
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(new String[] {});
		saveForm.setPk("fdm");
		saveForm.setPkValue(new String[] { request.getParameter("titName") });
		PjpyZhszcpModel myModel = new PjpyZhszcpModel();
		myModel.setDm(model.getEjdm());
		myModel.setMc(model.getEjmc());
		myModel.setLb(model.getEjlb());
		myModel.setBl(model.getEjbl());
		myModel.setXzf(model.getEjxzf());
		myModel.setFdm(model.getEjfdm());
		//����Ĭ��Ҫ����Ĵ��뼶�� 3��
		if (myModel.getDm() != null) {
			String[] array = new String[myModel.getDm().length];
			for (int i = 0; i < array.length; i++) {
				array[i] = ZHSZCP_DM_3J;
			}
			myModel.setDmjb(array);
		}
		
		return myModel;
	}

	/**
	 * �Զ����ֶ�ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcZdyzdwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZctjszActionForm myForm = (PjpyZctjszActionForm) form;
		PjpyZctjszService service = new PjpyZctjszService();
		String act = request.getParameter("act");
		
		/* ��ѯ���� */
		if (QUERY.equalsIgnoreCase(act)) {
			selectPageDataByPagination(request, myForm,"py_bdszb", "view_py_bdszb", new String[] {
					"pkValue", "�к�", "mc", "tabname",  "zdid", "zdmc", "lxmc", "zdcd",
					"cxxs" });
		} else if (DELETE.equalsIgnoreCase(act)) {
			deleteOperation(request, "py_bdszb");
		}
		
		request.setAttribute("path", "pjpy_tyb_zczdyzdwh.do");
		FormModleCommon.commonRequestSet(request);
		appendTableXx(request, "PY_BDSZB", "PY_BDSZB");
		request.setAttribute("dmList", service.queryZcsjdmxx(ZHSZCP_DM_4J, true));
		return mapping.findForward("zcZdyzdwh");
	}
	
	/**
	 * �Զ����ֶα���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zczdyzdAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZctjszActionForm myForm = (PjpyZctjszActionForm) form;
		BdszModel model = new BdszModel();
		BdszService service = new BdszService();
		PjpyZctjszService myService = new PjpyZctjszService();
		
		HashMap<String, String> rs = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String tableName = "PY_BDSZB";
		String pk = request.getParameter("pk");
		String tabname = request.getParameter("tabname");
		String zdyTable = request.getParameter("zdyTable");
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType))) {
			
			String[] colList = new String[] { "bz","cxxs","cxxspx","mkmc","tabname","zdcd","zdid","zdlx","zdmc","zdpx" , "sfnum", "sfnull", "xzf"};
			
			rs = service.getBdsz(tableName, colList, "zdid||tabname", pk);
			tabname = rs.get("tabname");
			
			//�޸Ļ���ʾʱ�趨�޸�״̬
			request.setAttribute("zt", "edit");
		}
		
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			//�޸�����
			if ("edit".equalsIgnoreCase(request.getParameter("zt"))) {
				result = service.saveBdsz(model,tableName,pk,request);
            	request.setAttribute("result", result);
			} else {//��������
				  //result = service.checkTableCol(tabname,model.getZdid());
		            
		           // if(result){
		            	
		            	result = service.saveBdsz(model,tableName,pk,request);
		            	request.setAttribute("result", result);
		          //  }else{
		            	//request.setAttribute("result", "yzsb");
		            //}
			}
		}
	
		rs.put("tabname", tabname);
		request.setAttribute("mkList",myService.queryZcsjdmxx(ZHSZCP_DM_4J, true));
		request.setAttribute("zdyTable", zdyTable);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);
		request.setAttribute("opList", service.getOpList(pk));
		
		return mapping.findForward("zczdyzdAdd");
	}
	
	/**
	 * �۲��������ü�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zctjpzCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		//��Ⲣ�����۲����������������
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response);
		PjpyZctjszService service = new PjpyZctjszService();
		service.zctjSjCheckResult(wwb);
	
		return mapping.findForward("");
	}
	
	/**
	 * ��ʦ���۲���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jszctjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyZctjszActionForm szForm = (PjpyZctjszActionForm) form;
		PjpyZctjszService service = new PjpyZctjszService();
		
		List<HashMap<String, String>> titleList = service.queryEjTitle(); 
		/* ��ȡ��������  Ĭ��Ϊȡ���ݲ�ѯ�����ĵ�һ������ */
		String dm = request.getParameter("id");
		dm = StringUtils.isNotNull(dm) ? dm
				: (titleList.isEmpty() ? "" : titleList.get(0).get("en"));
		
		HashMap<String, String> rs = new HashMap<String, String>();
		if (StringUtils.isNotNull(dm)) {//��ȡ�������룬���Ƶ���Ϣ
			rs = service.queryZcejdmxx(dm);
			request.setAttribute("sjdmstr", service.query3jZcdmStr(dm));//ƴ�����������ַ�����Ϣ
		}

		request.setAttribute("tabName", dm);
		request.setAttribute("pages", titleList);
		request.setAttribute("rs", rs);
		
		return mapping.findForward("jszctjsz");
	}
	
	/**
	 * ��ʦ���۲��������ñ�����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savejszctjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZctjszActionForm szForm = (PjpyZctjszActionForm) form;
		PjpyZctjszService service = new PjpyZctjszService();
		PjpyZctjszModel model = new PjpyZctjszModel();
		BeanUtils.copyProperties(model, szForm);
		
		//������뼶������
		String type = request.getParameter("type");
		
		//��������ҳ���ȡ��Ϣ
		String[] ejdm = request.getParameterValues("ejdm");
		String[] ejbl = request.getParameterValues("ejbl");
		String[] ejxzf = request.getParameterValues("ejxzf");
		String[] ejmrf = request.getParameterValues("ejmrf");
		String[] ejlb = request.getParameterValues("ejlb");
		model.setEjdm(ejdm);
		model.setEjbl(ejbl);
		model.setEjxzf(ejxzf);
		model.setEjmrf(ejmrf);
		model.setEjlb(ejlb);
		
		//�ļ�����ҳ���ȡ��Ϣ
		String[] sjdm = request.getParameterValues("sjdm");
		String[] sjbl = request.getParameterValues("sjbl");
		String[] sjxzf = request.getParameterValues("sjxzf");
		String[] sjmrf = request.getParameterValues("sjmrf");
		String[] sjlb = request.getParameterValues("sjlb");
		model.setSjdm(sjdm);
		model.setSjbl(sjbl);
		model.setSjxzf(sjxzf);
		model.setSjmrf(sjmrf);
		model.setSjlb(sjlb);
		
		boolean result = service.updateZcdmxx(type, model);
		
		request.setAttribute("result", result);
		jszctjsz(mapping, form, request, response);
		return mapping.findForward("jszctjsz");
	}
}
