package xsgzgl.xsxx.byjd.gdjs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

public class XsxxByjdInit {
	
	/**
	 * ��ʼ��ѧ��������Ϣ
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXsqtxx(RequestForm rForm, XsxxByjdForm myForm,
			User user, HttpServletRequest request) {

		// ����·��
		String path = "xsxx_byjd.do?method=byjdManage";
	
		rForm.setQtzd(new String[]{});
		rForm.setQtzdz(new String[]{});
		rForm.setDoType(null);
		rForm.setPath(path);
	}
	
}
