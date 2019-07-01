package xsgzgl.xsxx.bzrpy.bzrpycx;

import java.util.List;

import xgxt.comm.CommService;

import xsgzgl.xsxx.bzrpy.BasicModel;

public class XsxxBzrpycxService extends CommService{

	XsxxBzrpycxDAO dao=new XsxxBzrpycxDAO();
	
	/**
	 * 通用查询方法
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBasicList(BasicModel model) throws Exception{

		return dao.getBasicList(model);
	}
	

}
