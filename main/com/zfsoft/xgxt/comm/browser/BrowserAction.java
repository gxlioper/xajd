/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-26 ����10:18:53 
 */
package com.zfsoft.xgxt.comm.browser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����㹫������action
 * @�๦������: ���ڵ������滻�����ҳ����תaction
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-8-26 ����10:18:53
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BrowserAction extends SuperAction {
	public ActionForward divshow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BrowserForm bf = new BrowserForm();
		// Ҫ����������
		String content = request.getParameter("content");
		// ����ҳ����ѧ����
		String param = request.getParameter("param");
		bf.setContent(content);
		bf.setParam(param);
		request.setAttribute("rs", bf);
		return mapping.findForward("show");
	}

	/**
	 * 
	 * @����: ������ʾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-27 ����05:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward showMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer btnH = 22;
		// ��������
		String height = request.getParameter("height");
		String html = request.getParameter("html");
		Integer heightI = Integer.parseInt(height) - btnH;
		request.setAttribute("height", heightI);
		request.setAttribute("html", html);
		request.setAttribute("btnH", btnH);
		return mapping.findForward("message");
	}

	/**
	 * 
	 * @����: ��ȡ��ǰ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-18 ����04:26:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward getProgressBar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String key = request.getParameter("barkey");
		ProgressBar pb = BarSorce.getProgressBar(key);
		request.setAttribute("bar", pb);
		response.getWriter().print(JSONObject.fromObject(pb));
		if (pb.isFinish()) {
			pb.reset();
			BarSorce.finishBar(key);
		}
		// return mapping.findForward("progressbar");
		return null;
	}

	/**
	 * 
	 * @����: ���ý�����ʾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-25 ����09:58:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward barDemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String key = request.getParameter("barkey");
		//���key��Ӧ��������ʵ��Ӧ�ò�����ʹ�á�
	//	BarSorce.finishBar(key);
		ProgressBar pb = BarSorce.initProgressBar(key, 1000);
		for (int i = 0; i < 1000; i++) {
			// ��������1
			pb.change();
			// �������� 5
			// pb.change(5);
			// ģ��ҵ��������ӳ�100����
			Thread.sleep(100);
		}
		return null;
	}

	/**
	 * 
	 * @����: �Զ�ִ�н�����ʾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-25 ����09:58:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward autoBarDemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String key = request.getParameter("barkey");
		ProgressBar pb = BarSorce.initAutoProgressBar(key);
		for (int i = 0; i < 1000; i++) {
			Thread.sleep(10);
		}
		//ҵ�����
		pb.autoFinish();
		return null;
	}
	/**
	 * 
	 * @����: ������ʾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-6 ����11:10:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward demoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("demolist");
	}
}
