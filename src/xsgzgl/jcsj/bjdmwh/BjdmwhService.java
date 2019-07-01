package xsgzgl.jcsj.bjdmwh;

import java.util.HashMap;
import java.util.List;

import xsgzgl.jcsj.comm.JcsjService;

public class BjdmwhService extends JcsjService{
	
	private BjdmwhDao dao=new BjdmwhDao();
	
	/**
	 * 获取表头
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		return new String[]{"班级代码","班级名称","专业","学院","年级","数据异常原因"};
	}
	
	/**
	 * 获取班级信息类别
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getBjdmList(BjdmwhForm model) throws Exception{
		return dao.getBjdmList(model);
	}
	
	/**
	 * 保存班级信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveBjdmInfo(BjdmwhForm model,String type) throws Exception{
		return dao.saveBjdmInfo(model, type);
	}
	
	/**
	 * 删除班级信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteBjdmInfo(BjdmwhForm model) throws Exception{
		boolean b=dao.deleteBjdmInfo(model);
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
	 * 获取专业列表
	 * @return
	 */
	public List<HashMap<String,String>> getZydmList(String bmdm){
		return dao.getZydmList(bmdm);
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
