package xsgzgl.xtwh.general.homepage;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.customForm.demo.DemoFormForm;
import xsgzgl.customForm.demo.DemoFormInit;
import xsgzgl.customForm.demo.DemoFormModel;
import xsgzgl.customForm.demo.DemoFormService;
import xsgzgl.customForm.gnmk.CustomGnmkForm;
import xsgzgl.customForm.gnmk.CustomGnmkService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵ�y�S�o_���_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class HomePageAction extends BasicAction {

	/**
	 * ��ʼ���ҵĹ�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultWdgzInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HomePageService service = new HomePageService();
		HomePageModel model = new HomePageModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		boolean flag = service.defaultWdgzInfo(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * �@ʾ�ҵĹ�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showWdgzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HomePageService service = new HomePageService();
		HomePageModel model = new HomePageModel();
		User user = getUser(request);// �û�����

		//ȡ�ҵ������б�
		List<HashMap<String,String>> list = service.getWdgzList(model, user);
		
		JSONArray wdsqList = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(wdsqList);

		return null;
	}
}
