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
	 * ��ȡ�������Ҹ���ά����Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getWmqsgswhInfoList(GyglWmqsForm model,HttpServletRequest request) throws Exception{
		return dao.getWmqsgswhInfoList(model,request);
	}
	
	/**
	 * �����������Ҹ���ά��
	 * @param model
	 * @return
	 */
	public boolean saveWmqsgs(GyglWmqsForm model){
		return dao.saveWmqsgs(model);
	}
	
	/**
	 * ������������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveWmqssqxx(GyglWmqsForm model) throws Exception{
		return dao.saveWmqssqxx(model);
	}
	
	/**
	 * ��ȡ��������������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getWmqssqxx(GyglWmqsForm model,HttpServletRequest request){
		return dao.getWmqssqxx(model,request);
	}
	
	/**
	 * ��ȡ������������б�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getWmqsshList(GyglWmqsForm model,HttpServletRequest request,User user) throws Exception{
		return dao.getWmqsshList(model,request,user);
	}
	
	/**
	 * �����������������Ϣ
	 * @param myForm
	 * @return
	 */
	public boolean saveWmqsshxx(GyglWmqsForm myForm,HttpServletRequest request,User user) throws Exception{
		return dao.saveWmqsshxx(myForm,request,user);
	}
	
	/**
	 * ɾ���������������Ϣ
	 * @param myForm
	 * @return
	 */
	public boolean delWmqsshxx(GyglWmqsForm myForm) throws Exception{
		return dao.delWmqsshxx(myForm);
	}
	
	/**
	 * ��ȡ�������ҹ����б�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getWmqsglList(GyglWmqsForm model,HttpServletRequest request) throws Exception{
		return dao.getWmqsglList(model,request);
	}
	
	/**
	 * ��ȡ�������Ұٷֱ�
	 * @param csName
	 * @return
	 */
	public List<HashMap<String,String>> getWmqsbfb(String[] csName){
		return dao.getWmqsbfb(csName);
	}
	
	/**
	 * �����������Ұٷֱ�
	 * @param csz
	 * @return
	 */
	public boolean saveWmqsbfb(String csz){
		return dao.saveWmqsbfb(csz);
	}
	
	/**
	 * ��ȡ¥����Ϣ�б�
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> getLdxxList(HttpServletRequest request){
		return dao.getLdxxList(request);
	}

}
