package xsgzgl.xsxx.qtxx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

public class XsxxQtxxInit {
	
	/**
	 * 初始化学生其他信息
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXsqtxx(RequestForm rForm, XsxxQtxxForm myForm,
			User user, HttpServletRequest request) {

		// 访问路径
		String path = "xsxx_qtxx.do?method=xsqtxxManage&xmdm=001";
	
		rForm.setQtzd(new String[]{});
		rForm.setQtzdz(new String[]{});
		rForm.setDoType(null);
		rForm.setPath(path);
	}
	
}
