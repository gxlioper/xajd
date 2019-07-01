package xgxt.xsgygl.comm.tj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.FormModleCommon;
import xgxt.xsgygl.csmzzyjsxy.GyglCsmzActionForm;
import xgxt.xsgygl.csmzzyjsxy.GyglCsmzModel;
import xgxt.xsgygl.csmzzyjsxy.GyglCsmzServices;

import common.Globals;

/**
 * Title:��Ԣ����ģ�� 
 * Description: ѧ����Ԣ��ס�����ͳ��Action 
 * author:lyl
 */
public class GyglCommTjAction extends DispatchAction {
	/**
	 * author lyl
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsrzqk_Tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// String xxdm = Base.xxdm;

		if (Base.xxdm.equals(Globals.XXDM_ZJCMXY)) {
			return rzqktj(mapping, form, request, response);
		}

		GyglCsmzActionForm csmz_gyglForm = (GyglCsmzActionForm) form;
		GyglCommTjServiceImpl gcServices = new GyglCommTjServiceImpl();
		String xydm = DealString.toString(csmz_gyglForm.getXydm());
		String zydm = DealString.toString(csmz_gyglForm.getZydm());
		String nj = DealString.toString(csmz_gyglForm.getNj());
		String bjdm = DealString.toString(csmz_gyglForm.getBjdm());
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		List list = null;
		if (request.getParameter("go") != null
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			list = gcServices.service_FpDeTailView(userDep, nj, xydm, zydm,
					bjdm);
		}
		request.setAttribute("rs", list);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// ���Ͱ༶�б�
		// request.setAttribute("xxdm",xxdm);
		return mapping.findForward("xsrzqk_TjFx");
	}

	public ActionForward rzqktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglCsmzActionForm model = (GyglCsmzActionForm) form;
		HttpSession session = request.getSession();

		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");

		if ("xy".equals(userType)) {
			model.setXydm(userDep);
		}

		request.setAttribute("path", "stuHouseInfo.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("rzqktj");
	}
	public ActionForward wxsyzy_xsrzqk_Tj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		GyglCsmzActionForm csmz_gyglForm = (GyglCsmzActionForm)form;
		GyglCsmzModel model = new GyglCsmzModel();
		GyglCsmzServices service = new GyglCsmzServices();
		BeanUtils.copyProperties(model,csmz_gyglForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		try {
			service.serv_wxsyzy_xsrzqk_Tj(wwb, model);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
		return mapping.findForward("");
	}
}
