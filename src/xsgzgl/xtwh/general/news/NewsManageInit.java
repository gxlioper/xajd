package xsgzgl.xtwh.general.news;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xtwh.general.qxgl.yhzgl.YhzglNewForm;

public class NewsManageInit {
	public void initSearch(RequestForm rForm, NewsManageForm myForm, User user,
			HttpServletRequest request){
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xtwh_newsManage.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
}
