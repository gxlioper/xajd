package xsgzgl.xsxx.general.jcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;

public class XsxxJcszService extends CommService{
	
	XsxxJcszDao dao=new XsxxJcszDao();
	/**
	 * 根据模块类型获取审核流程信息
	 * @param model
	 * @return 
	 */
	public boolean jcSz(XsxxJcszForm model) throws Exception {
		boolean flag=true;
		flag = dao.scjcSz(model);
		flag = dao.bcjcSz(model);
		return flag;
	}
	
	/**
	 * 获取一条数据
	 * @param model
	 * @return 
	 */
	public HashMap<String, String> getOnesCssz() throws Exception{
		return dao.getOnesCssz();
	}
	
	/**
	 * 根据用户名获取审批岗位接口
	 * @param yhm
	 * @return 
	 */
	public HashMap<String,Object> getShlcByYh(String yhm){
		String splc = dao.getShlc();
		List<HashMap<String, String>> maplist = dao.getSpgwList(yhm);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("splc", splc);
		map.put("spgwList", maplist);
		return map;
	}

	/**
	 * 获取一条数据
	 * @param model
	 * @return 
	 */
	public HashMap<String, String> splCx() throws Exception{
		return dao.splCx();
	}
	

	public List<HashMap<String, String>> getSpgwBySplc(String splc){
		return dao.getSpgwBySplc(splc);
	}
}
