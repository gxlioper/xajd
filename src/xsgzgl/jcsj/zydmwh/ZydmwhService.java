package xsgzgl.jcsj.zydmwh;

import java.util.HashMap;
import java.util.List;

import xsgzgl.jcsj.comm.JcsjService;

public class ZydmwhService extends JcsjService{
	
	private ZydmwhDao dao=new ZydmwhDao();
	
	/**
	 * 获取表头
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		return new String[]{"专业代码","专业名称","部门","学制","数据异常原因"};
	}
	
	/**
	 * 获取专业信息类别
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getZydmList(ZydmwhForm model) throws Exception{
		return dao.getZydmList(model);
	}
	
	/**
	 * 保存专业信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveZydmInfo(ZydmwhForm model,String type) throws Exception{
		return dao.saveZydmInfo(model, type);
	}
	
	/**
	 * 删除专业信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteZydmInfo(ZydmwhForm model) throws Exception{
		boolean b=dao.deleteZydmInfo(model);
		return b?"删除成功！":"删除失败！";
	}
	
	/**
	 * 获取部门列表
	 * @return
	 */
	public List<HashMap<String,String>> getBmdmList(){
		return dao.getBmdmList();
	}
	
	/**
	 * 检查异常数据
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		return dao.checkExceptionData();
	}

}
