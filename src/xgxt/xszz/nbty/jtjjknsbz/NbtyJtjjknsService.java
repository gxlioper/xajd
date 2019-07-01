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
	 * ��ȡ��˼���
	 */
	public List<HashMap<String, String>> getShjb(String viewName)
	{
		XszzDao xszzDao =new XszzDao();
		return xszzDao.getShjb(viewName);
	}
	

	/**
	 * �Ƿ���������
	 */
	public boolean isKns(String userName,String viewName){
		XszzDao xszzDao=new XszzDao();
		DAO dao=new DAO();
		boolean blog=false;
		//�Ƿ��ж�������
		if(xszzDao.judgeKn(viewName)){
			//�Ƿ���������
			blog=dao.isKns(userName);
		}else{
			blog=true;
		}
		
		return blog;
	}
	
	/**
	 * �жϲ����Ƿ�������ʱ����
	 */
	public boolean sqsj(String xydm,String viewName)
	{
		XszzDao xszzDao=new XszzDao();
		//����ѧԺ�����ж������Ƿ�������������
		return xszzDao.jtjjknsSqSj(xydm,viewName);
	
	}
	
	
	/**
	 * ��ȡѧ����
	 */
	public String getXqMc(String xq)
	{
		XszzDao xszzDao=new XszzDao();
		//����ѧԺ�����ж������Ƿ�������������
		return xszzDao.getXqMc(xq);
	
	}
	
}
