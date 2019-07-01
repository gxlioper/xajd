package xgxt.rcgl.zjjdzyjsxy.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江机电日常事务模块DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-02</p>
 */
public class RcswXfcjDAO {
	/**
	 * 学费催缴中获取学费缓交信息
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
		 //查询学生基本信息
		 String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc from view_xsxxb a where xh=?";
		 map = dao.getMap(sql, new String[]{xh}, outputValue);
		 map.put("nd", nd);
		 map.put("xn", xn);
		 map.put("xq", xq);
		 
		 //查询本学年学期的缓交金额
		 sql = "select hjje from xfhjb where nd=? and xn=? and xq=? and xh=?";
		 hjje = dao.getOneRs(sql, new String[]{nd,xn,xq,xh}, "hjje");
		 map.put("je", hjje);		 
		 
		 //将查询的信息返回
		 return map;
	 }
	 
	 /**
	  * 获取欠费类型列表
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
