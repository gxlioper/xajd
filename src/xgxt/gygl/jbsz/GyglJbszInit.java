package xgxt.gygl.jbsz;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;

public class GyglJbszInit {

	/**
	 * ��Ԣ������������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getGyjbszRForm(RequestForm rForm, GyglJbszForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "gyjbsz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_jbsz_gyjbsz.do";
		// ��
		String tableName = "xg_view_xsxx_zdszb";

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);

	}
}
