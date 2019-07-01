package xsgzgl.gygl.wmqs.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewService;

public class GyglWmqsService extends GyglNewService{
	
	private GyglWmqsDao dao=new GyglWmqsDao();
	
	/**
	 * 获取文明寝室个数维护信息列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getWmqsgswhInfoList(GyglWmqsForm model,HttpServletRequest request) throws Exception{
		return dao.getWmqsgswhInfoList(model,request);
	}
	
	/**
	 * 保存文明寝室个数维护
	 * @param model
	 * @return
	 */
	public boolean saveWmqsgs(GyglWmqsForm model){
		return dao.saveWmqsgs(model);
	}
	
	/**
	 * 保存文明寝室申请信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveWmqssqxx(GyglWmqsForm model) throws Exception{
		return dao.saveWmqssqxx(model);
	}
	
	/**
	 * 获取文明寝室申请信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getWmqssqxx(GyglWmqsForm model,HttpServletRequest request){
		return dao.getWmqssqxx(model,request);
	}
	
	/**
	 * 获取文明寝室审核列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getWmqsshList(GyglWmqsForm model,HttpServletRequest request,User user) throws Exception{
		return dao.getWmqsshList(model,request,user);
	}
	
	/**
	 * 保存文明寝室审核信息
	 * @param myForm
	 * @return
	 */
	public boolean saveWmqsshxx(GyglWmqsForm myForm,HttpServletRequest request,User user) throws Exception{
		return dao.saveWmqsshxx(myForm,request,user);
	}
	
	/**
	 * 删除文明寝室审核信息
	 * @param myForm
	 * @return
	 */
	public boolean delWmqsshxx(GyglWmqsForm myForm) throws Exception{
		return dao.delWmqsshxx(myForm);
	}
	
	/**
	 * 获取文明寝室管理列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getWmqsglList(GyglWmqsForm model,HttpServletRequest request) throws Exception{
		return dao.getWmqsglList(model,request);
	}
	
	/**
	 * 获取文明寝室百分比
	 * @param csName
	 * @return
	 */
	public List<HashMap<String,String>> getWmqsbfb(String[] csName){
		return dao.getWmqsbfb(csName);
	}
	
	/**
	 * 保存文明寝室百分比
	 * @param csz
	 * @return
	 */
	public boolean saveWmqsbfb(String csz){
		return dao.saveWmqsbfb(csz);
	}
	
	/**
	 * 获取楼栋信息列表
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> getLdxxList(HttpServletRequest request){
		return dao.getLdxxList(request);
	}

}
