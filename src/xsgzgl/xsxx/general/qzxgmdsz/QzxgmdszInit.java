package xsgzgl.xsxx.general.qzxgmdsz;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

public class QzxgmdszInit {

	/**
	 * ��ʼ�����ݡ�ǿ���޸��������á�
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initQzxgmd(RequestForm rForm, QzxgmdszForm myForm, User user,
			HttpServletRequest request) throws Exception {

		// ����·��
		String path = "general_xsxx_qzxgmdsz.do";
	
		// ѧУ����
		String tableName = "";
		String realTable = "";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		
		//====================��ʼ��ҳ���������=====================
		// �߼���ѯ
		SearchModel searchModel = new SearchModel();
		// ѧУ,����Ա��ť����Ȩ�޿���
		request.setAttribute("czqx", "admin".equalsIgnoreCase(user.getUserType())
				|| "xx".equalsIgnoreCase(user.getUserType()) ? "yes" : "no");
		request.setAttribute("searchTj", searchModel);
	}
	
}
