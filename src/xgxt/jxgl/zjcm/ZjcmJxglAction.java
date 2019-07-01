package xgxt.jxgl.zjcm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.dtjs.DtjsForm;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.SaveForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xsgygl.GyglTyForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �㽭��ýѧԺѧ����Ϣ�����ѵ����-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class ZjcmJxglAction extends DispatchAction {

	/**
	 * ��ѵ�ɼ�����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxcjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglTyForm myForm = (JxglTyForm) form;
		ZjcmJxglService service = new ZjcmJxglService();
		ZjcmJxglModel model = new ZjcmJxglModel();

		String doType = request.getParameter("doType");
		String tableName = "view_jxgl_cjb";
		String realTable = "jxgl_cjb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// ���õ�ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		
		BeanUtils.copyProperties(model, myForm);
		
		if(!Base.isNull(doType)&&"tb".equalsIgnoreCase(doType)){
			xn = myForm.getXn();
			result = service.tbJxcj(xn);
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xh", "xm", "xb",
					"xymc", "zymc", "bjmc", "cj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getJxglList(tableName, model, colList,"");

		}
		// ��ʼ���б�ֵ
		service.setList(myForm, request, "jxcj");
		
		request.setAttribute("path", "jxgl_jxllcj.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("jxcjManage");
	}
	
}
