package xsgzgl.xsxx.qtxx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

public class XsxxQtxxInit {
	
	/**
	 * ��ʼ��ѧ��������Ϣ
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXsqtxx(RequestForm rForm, XsxxQtxxForm myForm,
			User user, HttpServletRequest request) {

		// ����·��
		String path = "xsxx_qtxx.do?method=xsqtxxManage&xmdm=001";
	
		rForm.setQtzd(new String[]{});
		rForm.setQtzdz(new String[]{});
		rForm.setDoType(null);
		rForm.setPath(path);
	}
	
}
