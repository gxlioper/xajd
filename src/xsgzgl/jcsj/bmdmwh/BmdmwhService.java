package xsgzgl.jcsj.bmdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xsgzgl.jcsj.comm.JcsjService;

public class BmdmwhService extends JcsjService{
	
	private BmdmwhDao dao=new BmdmwhDao();
	
	/**
	 * 获取表头
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		return new String[]{"部门代码","部门名称","部门类别代码","部门类别名称","数据异常原因"};
	}
	
	/**
	 * 获取部门信息类别
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getBmdmList(BmdmwhForm model) throws Exception{
		return dao.getBmdmList(model);
	}
	
	/**
	 * 保存部门信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveBmdmInfo(BmdmwhForm model,String type) throws Exception{
		return dao.saveBmdmInfo(model, type);
	}
	
	/**
	 * 删除部门信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteBmdmInfo(BmdmwhForm model) throws Exception{
		boolean b=dao.deleteBmdmInfo(model);
		return b?"删除成功！":"删除失败！";
	}
	
	/**
	 * 获取部门类别列表
	 * @return
	 */
	public List<HashMap<String,String>> getBmlbList(){
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String> map1=new HashMap<String, String>();
		map1.put("dm", "5");
		map1.put("mc", "学院");
		list.add(map1);
		
		HashMap<String,String> map2=new HashMap<String, String>();
		map2.put("dm", "1");
		map2.put("mc", "部门");
		list.add(map2);
		
		return list;
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
