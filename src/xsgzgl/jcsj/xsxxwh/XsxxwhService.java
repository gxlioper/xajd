package xsgzgl.jcsj.xsxxwh;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xsgzgl.jcsj.comm.JcsjService;

public class XsxxwhService extends JcsjService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	XsxxwhDao dao=new XsxxwhDao();
	
	public String[] getTopTr(){
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] topTr=new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY,"专业","班级","数据异常原因"};
		return topTr;
	}
	/**
	 * 获取学生信息列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getXsxxList(XsxxwhForm model) throws Exception{
		return dao.getXsxxList(model);
	}
	
	/**
	 * 删除学生信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String deleteXsxxInfo(XsxxwhForm model) throws Exception{
		boolean b=dao.deleteXsxxInfo(model);
		return b?"删除成功！":"删除失败！";
	}
	
	/**
	 * 检查异常数据
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		return dao.checkExceptionData();
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
	 * 获取专业列表
	 * @return
	 */
	public List<HashMap<String,String>> getBjdmList(String zydm){
		return dao.getBjdmList(zydm);
	}
	
}
