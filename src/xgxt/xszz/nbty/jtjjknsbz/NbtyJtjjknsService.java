package xgxt.xszz.nbty.jtjjknsbz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;

public class NbtyJtjjknsService {
	public List<HashMap<String,String>> getXszzList()
	{
		XszzDao xszzDao =new XszzDao();
		return xszzDao.getXszzList();
	}
	
	public HashMap<String, String> getStuInfo(String xh)
	{
		CommonQueryDAO commonQueryDao =new CommonQueryDAO();
		return commonQueryDao.getStuInfo(xh);
	}
	
	/**
	 * 获取审核级别
	 */
	public List<HashMap<String, String>> getShjb(String viewName)
	{
		XszzDao xszzDao =new XszzDao();
		return xszzDao.getShjb(viewName);
	}
	

	/**
	 * 是否是困难生
	 */
	public boolean isKns(String userName,String viewName){
		XszzDao xszzDao=new XszzDao();
		DAO dao=new DAO();
		boolean blog=false;
		//是否判断困难生
		if(xszzDao.judgeKn(viewName)){
			//是否是困难生
			blog=dao.isKns(userName);
		}else{
			blog=true;
		}
		
		return blog;
	}
	
	/**
	 * 判断补助是否在申请时间内
	 */
	public boolean sqsj(String xydm,String viewName)
	{
		XszzDao xszzDao=new XszzDao();
		//根据学院代码判断申请是否在申请日期内
		return xszzDao.jtjjknsSqSj(xydm,viewName);
	
	}
	
	
	/**
	 * 获取学期名
	 */
	public String getXqMc(String xq)
	{
		XszzDao xszzDao=new XszzDao();
		//根据学院代码判断申请是否在申请日期内
		return xszzDao.getXqMc(xq);
	
	}
	
}
