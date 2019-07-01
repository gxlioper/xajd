package xgxt.xsgygl.ghxy.qyfdywh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhDao;

public class GhxyQyfdywhService {
	
	
	/**
	 *学区List
	 */
	public void  yqList(HttpServletRequest request){
		GhxyQyfdywhDao ghxyQyfdywhDao=new GhxyQyfdywhDao();
		request.setAttribute("yqList", ghxyQyfdywhDao.yqList());
	}
	
	/**
	 * 根据职工号给出年级主任的管辖年级
	 */
	public List<HashMap<String,String>>getQyfdy(String zgh){
		GhxyQyfdywhDao ghxyQyfdywhDao=new GhxyQyfdywhDao();
		return ghxyQyfdywhDao.getQyfdyxx(zgh);
	}
	
	/**
	 * 根据职工号判断是否是区域辅导员
	 */
	public boolean isQyfdy(String userName){
		GhxyQyfdywhDao dao=new GhxyQyfdywhDao();
		if(dao.getQyfdy(userName).size()>0){
			return true;
		}else{
			return false;
		}
	}
}
