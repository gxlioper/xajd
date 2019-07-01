package xsgzgl.webservice.xsxx;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xsgzgl.xsxx.zgdzdx.dljc.XsxxDljcService;

public class XsxxWebServiceImp implements XsxxWebService {

	/**
	 * 根据学号获取该学生是否完善信息填写的工作
	 * 
	 * @school 中国地质大学
	 * @param xh
	 * @date 2012-12-20
	 * @author 伟大的骆
	 * */
	public String getXsxxXxws(String xh) {

		DAO dao = DAO.getInstance();

		String userName = dao.getOneValue("view_xsjbxx", "xh", "xh", xh);
	
		if (!Base.isNull(userName)) {//学生用户
			XsxxDljcService service = new XsxxDljcService();
			boolean isXxws = service.isXxws(xh);

			return isXxws ? "1" : "0";
		} else {//非学生用户
			return "1";
		}
	}
}
