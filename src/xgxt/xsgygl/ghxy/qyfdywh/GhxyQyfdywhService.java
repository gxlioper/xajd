package xgxt.xsgygl.ghxy.qyfdywh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhDao;

public class GhxyQyfdywhService {
	
	
	/**
	 *ѧ��List
	 */
	public void  yqList(HttpServletRequest request){
		GhxyQyfdywhDao ghxyQyfdywhDao=new GhxyQyfdywhDao();
		request.setAttribute("yqList", ghxyQyfdywhDao.yqList());
	}
	
	/**
	 * ����ְ���Ÿ����꼶���εĹ�Ͻ�꼶
	 */
	public List<HashMap<String,String>>getQyfdy(String zgh){
		GhxyQyfdywhDao ghxyQyfdywhDao=new GhxyQyfdywhDao();
		return ghxyQyfdywhDao.getQyfdyxx(zgh);
	}
	
	/**
	 * ����ְ�����ж��Ƿ������򸨵�Ա
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
