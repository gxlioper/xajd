package xsgzgl.xtwh.general.news;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xtwh.general.qxgl.yhzgl.YhzglNewForm;

public class NewsManageInit {
	public void initSearch(RequestForm rForm, NewsManageForm myForm, User user,
			HttpServletRequest request){
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xtwh_newsManage.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
}
