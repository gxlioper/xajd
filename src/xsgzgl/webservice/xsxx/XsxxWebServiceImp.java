package xsgzgl.webservice.xsxx;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xsgzgl.xsxx.zgdzdx.dljc.XsxxDljcService;

public class XsxxWebServiceImp implements XsxxWebService {

	/**
	 * ����ѧ�Ż�ȡ��ѧ���Ƿ�������Ϣ��д�Ĺ���
	 * 
	 * @school �й����ʴ�ѧ
	 * @param xh
	 * @date 2012-12-20
	 * @author ΰ�����
	 * */
	public String getXsxxXxws(String xh) {

		DAO dao = DAO.getInstance();

		String userName = dao.getOneValue("view_xsjbxx", "xh", "xh", xh);
	
		if (!Base.isNull(userName)) {//ѧ���û�
			XsxxDljcService service = new XsxxDljcService();
			boolean isXxws = service.isXxws(xh);

			return isXxws ? "1" : "0";
		} else {//��ѧ���û�
			return "1";
		}
	}
}
