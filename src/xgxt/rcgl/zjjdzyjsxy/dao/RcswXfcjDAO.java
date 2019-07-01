package xgxt.rcgl.zjjdzyjsxy.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭�����ճ�����ģ��DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-02</p>
 */
public class RcswXfcjDAO {
	/**
	 * ѧ�Ѵ߽��л�ȡѧ�ѻ�����Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	 public HashMap<String, String> getXfhjInfo(String xh){
		 DAO dao = DAO.getInstance();
		 HashMap<String,String> map = new HashMap<String, String>(); 
		 String nd = Base.currNd;
		 String xn = Base.currXn;
		 String xq = Base.currXq;
		 String[] outputValue = {"xh","xm","xb","nj","xymc","zymc","bjmc"};
		 String hjje = "";
		 //��ѯѧ��������Ϣ
		 String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc from view_xsxxb a where xh=?";
		 map = dao.getMap(sql, new String[]{xh}, outputValue);
		 map.put("nd", nd);
		 map.put("xn", xn);
		 map.put("xq", xq);
		 
		 //��ѯ��ѧ��ѧ�ڵĻ������
		 sql = "select hjje from xfhjb where nd=? and xn=? and xq=? and xh=?";
		 hjje = dao.getOneRs(sql, new String[]{nd,xn,xq,xh}, "hjje");
		 map.put("je", hjje);		 
		 
		 //����ѯ����Ϣ����
		 return map;
	 }
	 
	 /**
	  * ��ȡǷ�������б�
	  * @return List
	  * */
	 public List getQflxList(){
		 DAO dao = DAO.getInstance();
		 List list = null;
		 String sql = "select distinct qflxdm,qflxmc from qflxdmb";
		 
		 list = dao.getList(sql, new String[]{}, new String[]{"qflxdm","qflxmc"});
		 return list;
	 }
}
